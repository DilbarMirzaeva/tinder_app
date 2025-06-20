package az.turing.service;

import az.turing.domain.entity.User;
import az.turing.dto.request.UserRequest;
import az.turing.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest user);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void deleteUserById(Long id);
    UserResponse getUserByName(String name);
    UserResponse updateUser(UserRequest user,Long id);
    User saveAndReturnUser(UserRequest user);
}
