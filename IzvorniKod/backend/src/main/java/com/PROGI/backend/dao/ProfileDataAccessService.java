package com.PROGI.backend.dao;

import com.PROGI.backend.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("profilePostgres")
public class ProfileDataAccessService implements ProfileDao {

    @Override
    public int insertProfile(UUID id, Profile profile) {
        return 0;
    }

    @Override
    public List<Profile> selectAllProfiles() {
        return null;
    }

    @Override
    public Optional<Profile> selectProfileById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Profile> selectProfileByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<Profile> selectProfileByPassword(String password) {
        return Optional.empty();
    }

    @Override
    public int deleteProfileById(UUID id) {
        return 0;
    }

    @Override
    public int updateProfileById(UUID id, Profile profile) {
        return 0;
    }
}
