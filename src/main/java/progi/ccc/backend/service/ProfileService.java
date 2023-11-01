package progi.ccc.backend.service;

import progi.ccc.backend.domain.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<Profile> listAll();

    Profile createStudent(Profile student);

    Optional<Profile> findByUserID(long userID);

    Optional<Profile> findByUsername(String username);

    Profile updateProfile(Profile profile);

    Profile fetch(long userID);
    Profile deleteProfile(long userID);
}
