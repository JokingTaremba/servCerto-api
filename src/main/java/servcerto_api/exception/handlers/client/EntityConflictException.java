package servcerto_api.exception.handlers.client;

public class EntityConflictException extends RuntimeException{
    public EntityConflictException(String message){
        super(message);
    }
}