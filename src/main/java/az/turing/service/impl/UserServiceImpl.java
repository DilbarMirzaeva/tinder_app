package az.turing.service.impl;

import az.turing.domain.entity.User;
import az.turing.domain.repository.UserRepo;
import az.turing.dto.request.UserCreateRequest;
import az.turing.dto.response.UserResponse;
import az.turing.exception.AlreadyExistsException;
import az.turing.exception.NotFoundException;
import az.turing.mapper.UserMapper;
import az.turing.service.UserService;
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
        User userEntity =userMapper.toEntityFromRequest(user);
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

    @Override
    public UserResponse getUserByName(String name) {
        User user=userRepo.findByUsername(name)
                .orElseThrow(()->new NotFoundException("User with name " + name + " not found"));
        return  userMapper.toDto(user);
    }
}
