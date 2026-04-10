package servcerto_api.exception.handlers.client;

public class EntityBadRequestException extends RuntimeException{
    public EntityBadRequestException(String message){
        super(message);
    }
}
