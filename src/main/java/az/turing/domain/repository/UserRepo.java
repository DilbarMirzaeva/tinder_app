package az.turing.domain.repository;

import az.turing.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsUserByUsername(String username);

    Optional<User> findByUsername(String name);
}
