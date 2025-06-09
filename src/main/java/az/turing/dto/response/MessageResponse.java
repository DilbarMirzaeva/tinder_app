package az.turing.dto.response;

import az.turing.domain.enums.MessageStatus;
import az.turing.domain.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private Long id;
    private UserResponse fromUser;
    private UserResponse toUser;
    private String content;
    private LocalDateTime sentAt;
    private MessageStatus messageStatus;
    private RequestStatus requestStatus;
    private Boolean isMatch;
}
