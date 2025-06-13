package az.turing.domain.repository;

import az.turing.domain.entity.User;
import az.turing.domain.enums.Status;
import az.turing.dto.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsUserByUsername(String username);

    Optional<User> findByUsername(String name);

    List<User> findAllByStatus(Status status);
}
