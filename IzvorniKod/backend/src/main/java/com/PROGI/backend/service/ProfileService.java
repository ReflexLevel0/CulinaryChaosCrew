package com.PROGI.backend.service;

import com.PROGI.backend.dao.ProfileDao;
import com.PROGI.backend.model.Profile;
import com.PROGI.backend.validEmail;
import com.PROGI.backend.validPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfileService {
    private final ProfileDao profileDao;

    @Autowired
    public ProfileService(@Qualifier("postgresProfile") ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public int addProfile(Profile profile) {
        return profileDao.insertProfile(profile);
    }

    public List<Profile> getAllProfiles() {
        return profileDao.getAllProfiles();
    }

    public Optional<Profile> getProfileById(UUID id) {
        return profileDao.selectProfileById(id);
    }

    public Optional<Profile> getProfileByUsername(String username) {
        return profileDao.selectProfileByUsername(username);
    }

    public Optional<Profile> getProfileByCredentials(String username, String password){
        return profileDao.selectProfileByCredentials(username, password);
    }

    public int deleteProfile(UUID id) {
        return profileDao.deleteProfileById(id);
    }

    public int updateProfile(UUID id, Profile profile) {
        return profileDao.updateProfileById(id, profile);
    }

    public void deleteAllProfiles(){ profileDao.deleteAllProfiles();}

    public List<Profile> searchProfile(String guess) { return profileDao.searchProfile(guess); }

    public boolean usernameTaken(String username) {
        List<Profile> allProfiles = getAllProfiles();
        for (Profile profile : allProfiles) {
            if (username.equals(profile.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean emailTaken(String email) {
        List<Profile> allProfiles = getAllProfiles();
        for (Profile profile : allProfiles) {
            if (email.equals(profile.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean profileExists(String username, String password) {
        List<Profile> allProfiles = getAllProfiles();
        for (Profile profile : allProfiles) {
            if (profile.getUsername().equals(username) && profile.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean goodEmailFormat(String email) {
        validEmail validEmail = new validEmail();
        return validEmail.test(email);
    }

    public boolean strongPassword(String password) {
        validPassword validPassword = new validPassword();
        return validPassword.test(password);
    }

}
