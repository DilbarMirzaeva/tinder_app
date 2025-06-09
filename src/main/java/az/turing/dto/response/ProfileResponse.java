package az.turing.dto.response;

import az.turing.domain.enums.Gender;
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
