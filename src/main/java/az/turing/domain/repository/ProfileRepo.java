package az.turing.domain.repository;

import az.turing.domain.entity.Profile;
import az.turing.domain.entity.User;
import az.turing.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    List<Profile> findAllByStatus(Status status);
}
