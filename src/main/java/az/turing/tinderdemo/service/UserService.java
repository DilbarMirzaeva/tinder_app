package az.turing.tinderdemo.service;

import az.turing.tinderdemo.dto.request.UserCreateRequest;
import az.turing.tinderdemo.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserCreateRequest user);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void deleteUserById(Long id);
}
