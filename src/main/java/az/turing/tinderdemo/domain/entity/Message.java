package az.turing.tinderdemo.domain.entity;

import az.turing.tinderdemo.domain.enums.MessageStatus;
import az.turing.tinderdemo.domain.enums.RequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "seq", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @NotBlank(message = "no content")
    private String content;

    @NotNull
    private LocalDateTime sentAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_status", nullable = false)
    @NotNull
    private MessageStatus messageStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @NotNull
    private Boolean isMatch;
}
