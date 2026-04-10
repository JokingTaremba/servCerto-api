package servcerto_api.exception.handlers.client;

public class EntityAccessDeniedException extends RuntimeException{
    public EntityAccessDeniedException(String message){
        super(message);
    }
}
