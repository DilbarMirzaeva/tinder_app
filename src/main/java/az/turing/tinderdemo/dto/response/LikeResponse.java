package az.turing.tinderdemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponse {
    private Long id;
    private UserResponse fromUser;
    private UserResponse toUser;
    private LocalDateTime createdAt;
}
