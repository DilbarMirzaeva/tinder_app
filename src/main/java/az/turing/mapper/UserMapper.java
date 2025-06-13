package az.turing.mapper;

import az.turing.domain.entity.User;
import az.turing.dto.request.UserRequest;
import az.turing.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserResponse, UserRequest> {
    User toEntityFromResponse(UserResponse userResponse);
    UserResponse toDto(User user);
    UserRequest toRequest(User user);
    User toEntityFromRequest(UserRequest userRequest);
}
