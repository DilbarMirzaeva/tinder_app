package az.turing.mapper;

import az.turing.domain.entity.User;
import az.turing.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserResponse> {
    User toEntity(UserResponse userResponse);
    UserResponse toResponse(User user);
}
