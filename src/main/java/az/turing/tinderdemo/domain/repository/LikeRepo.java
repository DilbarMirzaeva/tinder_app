package az.turing.tinderdemo.domain.repository;

import az.turing.tinderdemo.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Like,Long> {
}
