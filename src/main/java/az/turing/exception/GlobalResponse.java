package az.turing.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalResponse {
    private UUID uuid;
    private String message;
    private ErrorMessage error;
    private LocalDateTime timestamp;

}
