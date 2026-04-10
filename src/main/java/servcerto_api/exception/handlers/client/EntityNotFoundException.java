package servcerto_api.exception.handlers.client;

public class EntityNotFoundException extends EntityBadRequestException {
    public EntityNotFoundException(String message){
        super(message);
    }
}
