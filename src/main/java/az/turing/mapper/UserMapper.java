package az.turing.mapper;

import az.turing.domain.entity.User;
import az.turing.dto.request.UserCreateRequest;
import az.turing.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserResponse, UserCreateRequest> {
    User toEntityFromResponse(UserResponse userResponse);
    UserResponse toDto(User user);
    UserCreateRequest toRequest(User user);
    User toEntityFromRequest(UserCreateRequest userCreateRequest);
}
