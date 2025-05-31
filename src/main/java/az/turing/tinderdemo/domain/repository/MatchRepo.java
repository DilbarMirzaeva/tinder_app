package az.turing.tinderdemo.domain.repository;

import az.turing.tinderdemo.domain.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepo extends JpaRepository<Match,Long> {
}
