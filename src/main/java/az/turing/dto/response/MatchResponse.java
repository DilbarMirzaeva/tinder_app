package az.turing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchResponse {
    private Long id;
    private UserResponse user1;
    private UserResponse user2;
    private LocalDateTime matchedAt;
}
