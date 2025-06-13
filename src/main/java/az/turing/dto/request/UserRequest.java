package az.turing.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String password;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18,message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be at most 100")
    private Integer age;
}
