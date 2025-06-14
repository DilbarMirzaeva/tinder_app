package az.turing.service.impl;

import az.turing.domain.entity.User;
import az.turing.domain.enums.Status;
import az.turing.domain.repository.UserRepo;
import az.turing.dto.request.UserRequest;
import az.turing.dto.response.UserResponse;
import az.turing.exception.AlreadyDeletedException;
import az.turing.exception.AlreadyExistsException;
import az.turing.exception.EmptyResultException;
import az.turing.exception.NotFoundException;
import az.turing.mapper.UserMapper;
import az.turing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserResponse saveUser(UserRequest user) {
        if(userRepo.existsUserByUsername(user.getUsername())){
            throw new AlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        User userEntity =userMapper.toEntityFromRequest(user);
        userEntity.setStatus(Status.ACTIVE);
        User savedUser = userRepo.save(userEntity);
        return  userMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> list= userRepo.findAllByStatus(Status.ACTIVE).stream()
                .map(userMapper::toDto)
                .toList();
        if (list.isEmpty()) {
            throw new EmptyResultException("User list is empty");
        }
        return list;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userFindById(id);
        return  userMapper.toDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userFindById(id);
        if(user.getStatus() == Status.DELETED){
            throw new AlreadyDeletedException("User with id " + id + " already deleted");
        }
        user.setStatus(Status.DELETED);
        userRepo.save(user);
    }

    @Override
    public UserResponse getUserByName(String name) {
        User user=userRepo.findByUsername(name)
                .orElseThrow(()->new NotFoundException("User with name " + name + " not found"));
        return  userMapper.toDto(user);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest,Long id) {
        User user =userFindById(id);
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAge(userRequest.getAge());
        user.setStatus(Status.ACTIVE);
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

    public User userFindById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(()->new NotFoundException("User with id " + id + " not found"));
    }
}
