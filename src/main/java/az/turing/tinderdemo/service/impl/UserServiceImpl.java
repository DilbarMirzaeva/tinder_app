package az.turing.tinderdemo.service.impl;

import az.turing.tinderdemo.domain.entity.User;
import az.turing.tinderdemo.domain.repository.UserRepo;
import az.turing.tinderdemo.dto.request.UserCreateRequest;
import az.turing.tinderdemo.dto.response.UserResponse;
import az.turing.tinderdemo.exception.AlreadyExistsException;
import az.turing.tinderdemo.exception.NotFoundException;
import az.turing.tinderdemo.mapper.UserMapper;
import az.turing.tinderdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserResponse saveUser(UserCreateRequest user) {
        if(userRepo.existsUserByUsername(user.getUsername())){
            throw new AlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        User userEntity = new User();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setAge(user.getAge());
        User savedUser = userRepo.save(userEntity);
        return  userMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepo.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(()->new NotFoundException("User with id " + id + " not found"));
        return  userMapper.toDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        if(userRepo.existsById(id)){
            throw new NotFoundException("User with id " + id + " not found");
        }
        userRepo.deleteById(id);
    }
}
