package az.turing.domain.repository;

import az.turing.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Like,Long> {
}
