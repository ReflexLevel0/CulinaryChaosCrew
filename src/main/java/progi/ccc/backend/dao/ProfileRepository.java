package progi.ccc.backend.dao;

import progi.ccc.backend.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserName(String userName);

    int countByUserName(String username);

    // Note: exists- query is the best if we just want to predict conflicts
    boolean existsByUserNameAndUserIDNot(String userName, Long userID);

}

