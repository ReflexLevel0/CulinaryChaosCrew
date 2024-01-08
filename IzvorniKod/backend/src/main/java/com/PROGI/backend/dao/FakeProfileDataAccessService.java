package com.PROGI.backend.dao;

import com.PROGI.backend.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeProfileDao")
public class FakeProfileDataAccessService implements ProfileDao {

    private static List<Profile> DB = new ArrayList<>();

    @Override
    public int insertProfile(UUID id, Profile profile) {
        DB.add(new Profile(id,
                profile.getUsername(),
                profile.getPassword(),
                profile.getEmail(),
                profile.getName(),
                profile.getSurname(),
                profile.getAge())
        );
        return 1;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return DB;
    }

    @Override
    public Optional<Profile> selectProfileById(UUID id) {
        return DB.stream()
                .filter(profile -> profile.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Profile> selectProfileByUsername(String username) {
        return DB.stream()
                .filter(profile -> profile.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public int deleteProfileById(UUID id) {
        Optional<Profile> profileMyb = selectProfileById(id);
        if(profileMyb.isEmpty()) return 0;
        else {
            DB.remove(profileMyb.get());
            return 1;
        }
    }

    @Override
    public int updateProfileById(UUID id, Profile profile) {
        return selectProfileById(id)
                .map(profile1 -> {
                    int indexOfProfile = DB.indexOf(profile1);
                    if(indexOfProfile >= 0) {
                        DB.set(indexOfProfile, new Profile(profile.getUserId(),
                                                            profile.getUsername(),
                                                            profile.getPassword(),
                                                            profile.getEmail(),
                                                            profile.getName(),
                                                            profile.getSurname(),
                                                            profile.getAge()));
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }

    @Override
    public void deleteAllProfiles() {
        DB.clear();
    }

    @Override
    public Optional<Profile> selectProfileByCredentials(String username, String password) {
        return DB.stream()
                .filter(profile -> profile.getUsername().equals(username) && profile.getPassword().equals(password))
                .findFirst();
    }
}
