
CREATE TYPE user_role AS ENUM('ADMINISTRATOR', 'PROVIDER', 'CLIENT');

CREATE TABLE addresses(
    id UUID PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(100),
    country VARCHAR(100) NOT NULL DEFAULT 'MZ',
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6),
    description TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE categories(
    id UUID PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE users(
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    secondary_phone VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    profile_image VARCHAR(255),
    role user_role NOT NULL DEFAULT 'CLIENT',
    address_id UUID REFERENCES addresses(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE providers(
    id UUID PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    biography TEXT,
    average_rating DECIMAL(2,1) DEFAULT 0,
    total_reviews INT DEFAULT 0,
    is_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE reviews(
    id UUID PRIMARY KEY,
    provider_id UUID REFERENCES providers(id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    rating INT NOT NULL DEFAULT 5 CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    UNIQUE (user_id, provider_id)
);

CREATE TABLE services(
    id UUID PRIMARY KEY,
    provider_id UUID REFERENCES providers(id) ON DELETE CASCADE,
    category_id UUID REFERENCES categories(id) ON DELETE SET NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    min_price DECIMAL(10,2),
    max_price DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),
    CHECK (min_price IS NULL OR max_price IS NULL OR min_price <= max_price)
);

    CREATE INDEX idx_services_provider ON services(provider_id);
    CREATE INDEX idx_reviews_provider ON reviews(provider_id);
    CREATE INDEX idx_services_category ON services(category_id);

    CREATE OR REPLACE FUNCTION update_updated_at()
    RETURNS TRIGGER AS $$
    BEGIN
       NEW.updated_at = NOW();
       RETURN NEW;
    END;
    $$ LANGUAGE plpgsql;

    CREATE TRIGGER trigger_updated_at_users
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at();

    CREATE TRIGGER trigger_updated_at_providers
    BEFORE UPDATE ON providers
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at();

    CREATE TRIGGER trigger_updated_at_reviews
    BEFORE UPDATE ON reviews
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at();

    CREATE TRIGGER trigger_updated_at_services
    BEFORE UPDATE ON services
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at();

    CREATE TRIGGER trigger_updated_at_addresses
    BEFORE UPDATE ON addresses
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at();

    CREATE TRIGGER trigger_updated_at_categories
    BEFORE UPDATE ON categories
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at();


CREATE OR REPLACE FUNCTION recalc_provider_rating()
RETURNS TRIGGER AS $$
DECLARE
    v_provider_id UUID;
BEGIN
    v_provider_id := COALESCE(NEW.provider_id, OLD.provider_id);

    UPDATE providers
    SET
        average_rating = COALESCE(
            (SELECT ROUND(AVG(rating)::NUMERIC, 1)
             FROM reviews
             WHERE provider_id = v_provider_id),
            0
        ),
        total_reviews = (
            SELECT COUNT(*)
            FROM reviews
            WHERE provider_id = v_provider_id
        )
    WHERE id = v_provider_id;

    RETURN COALESCE(NEW, OLD);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_reviews_after_insert
    AFTER INSERT ON reviews
    FOR EACH ROW EXECUTE FUNCTION recalc_provider_rating();

CREATE TRIGGER trg_reviews_after_update
    AFTER UPDATE ON reviews
    FOR EACH ROW EXECUTE FUNCTION recalc_provider_rating();

CREATE TRIGGER trg_reviews_after_delete
    AFTER DELETE ON reviews
    FOR EACH ROW EXECUTE FUNCTION recalc_provider_rating();