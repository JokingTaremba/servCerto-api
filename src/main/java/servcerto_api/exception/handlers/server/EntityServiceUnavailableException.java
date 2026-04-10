package servcerto_api.exception.handlers.server;

public class EntityServiceUnavailableException extends RuntimeException{
    public EntityServiceUnavailableException(String message) {
        super(message);
    }
}
