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
    Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    User fromUser;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    User toUser;

    @NotBlank(message = "there has not content")
    String content;

    @NotNull
    LocalDateTime sentAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_status", nullable = false)
    @NotNull
    MessageStatus messageStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    RequestStatus requestStatus;

    @NotNull
    Boolean isMatch;


}
