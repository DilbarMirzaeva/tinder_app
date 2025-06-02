package az.turing.tinderdemo.mapper;

import az.turing.tinderdemo.domain.entity.User;
import az.turing.tinderdemo.dto.request.UserCreateRequest;
import az.turing.tinderdemo.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserResponse> {
    User toEntity(UserResponse userResponse);
    UserResponse toResponse(User user);
}
