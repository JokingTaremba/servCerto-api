package servcerto_api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class StandardErrorResponse {

    private int code;
    private HttpStatus status;
    private OffsetDateTime timestamp;
    private String title;
    private String path;
    private List<ValidationError> fields;

    @Data
    @AllArgsConstructor
    public static class ValidationError{
        private String field;
        private String message;
    }
}
