package az.turing.dto.request;

import az.turing.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateRequest {

    @NotBlank(message = "status can't be empty")
    private Status status;
}
