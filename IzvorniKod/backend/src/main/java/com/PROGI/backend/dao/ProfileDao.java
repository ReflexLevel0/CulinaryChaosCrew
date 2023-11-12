package com.PROGI.backend.dao;

import com.PROGI.backend.model.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface ProfileDao {

    int insertProfile(UUID id, Profile profile);

    default int insertProfile(Profile profile){
        UUID id = UUID.randomUUID();
        return insertProfile(id, profile);
    }

    List<Profile> selectAllProfiles();


    Optional<Profile> selectProfileById(UUID id);
    int deleteProfileById(UUID id);

    int updateProfileById(UUID id, Profile profile);
}
