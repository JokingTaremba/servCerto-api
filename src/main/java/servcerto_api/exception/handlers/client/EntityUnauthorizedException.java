package servcerto_api.exception.handlers.client;

public class EntityUnauthorizedException extends RuntimeException{
    public EntityUnauthorizedException(String message){
        super(message);
    }
}
