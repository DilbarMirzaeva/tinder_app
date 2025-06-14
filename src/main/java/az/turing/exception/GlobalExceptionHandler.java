package az.turing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
