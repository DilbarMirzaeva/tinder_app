package az.turing.domain.repository;

import az.turing.domain.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepo extends JpaRepository<Match,Long> {
}
