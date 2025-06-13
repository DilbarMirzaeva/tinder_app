package az.turing.service;

import az.turing.dto.request.UserCreateRequest;
import az.turing.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserCreateRequest user);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void deleteUserById(Long id);
    UserResponse getUserByName(String name);
}
