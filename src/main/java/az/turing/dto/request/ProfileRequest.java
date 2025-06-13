package az.turing.dto.request;

import az.turing.domain.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    @NotNull
    @Valid
    private UserCreateRequest userCreateRequest;

    private String photoUrl;

    @NotNull(message = "gender cannot be empty")
    private Gender gender;

    private String bio;
    private String location;
}
