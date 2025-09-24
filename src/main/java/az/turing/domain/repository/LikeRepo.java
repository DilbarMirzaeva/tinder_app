package az.turing.domain.repository;

import az.turing.domain.entity.Like;
import az.turing.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Like,Long> {
    boolean existsByFromUserAndToUser(User fromUser, User toUser);
}
