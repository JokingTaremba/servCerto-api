package servcerto_api.exception.handlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import servcerto_api.exception.StandardErrorResponse;
import servcerto_api.exception.handlers.client.*;
import servcerto_api.exception.handlers.server.EntityInternalServerErrorException;
import servcerto_api.exception.handlers.server.EntityServiceUnavailableException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource, StandardErrorResponse standardErrorResponse){
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<StandardErrorResponse.ValidationError> validationErrors = new ArrayList<>();

        for(ObjectError objectError: ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) objectError).getField();
            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            validationErrors.add(new StandardErrorResponse.ValidationError(name, message));
        }
        StandardErrorResponse response = new StandardErrorResponse();

        response.setTitle("INVALID DATA! Some fields contain errors. Please check and try again.");
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setFields(validationErrors);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return super.handleExceptionInternal(ex, response, headers, httpStatus, request);
    }


    @ExceptionHandler(EntityBadRequestException.class)
    public ResponseEntity<Object> handleEntityNotNullException(EntityBadRequestException notNullException, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setTitle(notNullException.getMessage());
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException notFound, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setTitle(notFound.getMessage());
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(EntityConflictException.class)
    public ResponseEntity<Object> handleEntityConflictException(EntityConflictException existException, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setTitle(existException.getMessage());
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(EntityForbiddenException.class)
    public ResponseEntity<Object> handleEntityForbiddenException(EntityForbiddenException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setTitle(ex.getMessage());
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(EntityUnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(EntityUnauthorizedException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setTitle(ex.getMessage());
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(EntityAccessDeniedException.class)
    public ResponseEntity<Object> handleEntityAccessDeniedException(EntityAccessDeniedException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setTitle(ex.getMessage());
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(EntityInternalServerErrorException.class)
    public ResponseEntity<Object> handleEntityGenericException(EntityInternalServerErrorException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setTitle("An unexpected error occurred. Please try again later.");
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(EntityServiceUnavailableException.class)
    public ResponseEntity<Object> handleEntityServiceUnavailableException(EntityServiceUnavailableException ex, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setTitle(ex.getMessage());
        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setPath(request.getContextPath());

        return ResponseEntity.status(httpStatus).body(response);
    }

}