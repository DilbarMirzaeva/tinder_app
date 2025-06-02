package az.turing.tinderdemo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "email cannot be empty")
    @Email
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String password;

    @NotBlank(message = "age cannot be empty")
    private Integer age;
}
