package az.turing.tinderdemo.dto.response;

import az.turing.tinderdemo.domain.entity.User;
import az.turing.tinderdemo.domain.enums.MessageStatus;
import az.turing.tinderdemo.domain.enums.RequestStatus;
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
