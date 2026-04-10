package servcerto_api.exception.handlers.server;

public class EntityInternalServerErrorException extends RuntimeException{
    public EntityInternalServerErrorException(String message) {
        super(message);
    }
}