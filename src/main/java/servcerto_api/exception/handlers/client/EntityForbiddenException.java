package servcerto_api.exception.handlers.client;

public class EntityForbiddenException extends RuntimeException{
    public EntityForbiddenException(String message){
        super(message);
    }
}
