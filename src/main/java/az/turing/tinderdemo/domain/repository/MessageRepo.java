package az.turing.tinderdemo.domain.repository;

import az.turing.tinderdemo.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message,Long> {
}
