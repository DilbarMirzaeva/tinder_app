package az.turing.tinderdemo.domain.repository;

import az.turing.tinderdemo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
