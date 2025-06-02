package az.turing.tinderdemo.dto.response;

import az.turing.tinderdemo.domain.entity.User;
import az.turing.tinderdemo.domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private Long id;
    private UserResponse user;
    private String photoUrl;
    private Gender gender;
    private String bio;
    private String location;
}
