package az.turing.dto.response;

import az.turing.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponse {
    private Long id;
    private User user1;
    private User user2;
    private LocalDateTime matchedAt;
}
