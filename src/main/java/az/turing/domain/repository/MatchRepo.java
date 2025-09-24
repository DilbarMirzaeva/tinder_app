package az.turing.domain.repository;

import az.turing.domain.entity.Match;
import az.turing.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepo extends JpaRepository<Match,Long> {
    List<Match> findByUser1OrUser2(User fromUser, User toUser);
    boolean existsByUser1AndUser2OrUser2AndUser1(User user1, User user2, User user2Alt, User user1Alt);
}
