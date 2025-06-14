package az.turing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<GlobalResponse> alreadyExistHandler(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                GlobalResponse.builder()
                        .message(ex.getMessage())
                        .error(ErrorMessage.ALREADY_EXIST)
                        .timestamp(LocalDateTime.now())
                        .uuid(UUID.randomUUID())
                        .build()
        );
    }

    @ExceptionHandler(AlreadyDeletedException.class)
    public ResponseEntity<GlobalResponse> alreadyDeleteHandler(AlreadyDeletedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                GlobalResponse.builder()
                        .message(ex.getMessage())
                        .error(ErrorMessage.ALREADY_DELETE)
                        .timestamp(LocalDateTime.now())
                        .uuid(UUID.randomUUID())
                        .build()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalResponse> notFoundHandler(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                GlobalResponse.builder()
                        .message(ex.getMessage())
                        .error(ErrorMessage.NOT_FOUND)
                        .timestamp(LocalDateTime.now())
                        .uuid(UUID.randomUUID())
                        .build()
        );
    }

    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<GlobalResponse> emptyResultHandler(EmptyResultException ex){
        return ResponseEntity.status(HttpStatus.OK).body(
                GlobalResponse.builder()
                        .message(ex.getMessage())
                        .error(ErrorMessage.EMPTY_RESULT)
                        .timestamp(LocalDateTime.now())
                        .uuid(UUID.randomUUID())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException ex){
        StringBuilder errors = new StringBuilder();

        for(FieldError fieldError:ex.getBindingResult().getFieldErrors()){
            errors.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append(";");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                GlobalResponse.builder()
                        .message(errors.toString().trim())
                        .error(ErrorMessage.METHOD_ARGUMENT_NOT_VALID)
                        .timestamp(LocalDateTime.now())
                        .uuid(UUID.randomUUID())
                        .build()
        );
    }
}
