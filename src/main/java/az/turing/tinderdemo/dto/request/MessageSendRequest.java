package az.turing.tinderdemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSendRequest {

    @NotBlank(message = "Username cannot be empty ")
    private String username;

    @NotBlank(message = "Content cannot be empty")
    private String content;

}
