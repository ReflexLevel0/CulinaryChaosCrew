package com.PROGI.backend.service;

import com.PROGI.backend.dao.ProfileDao;
import com.PROGI.backend.model.Profile;
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

    //    fakeProfileDao za fake bazu, postgresProfile za real bazu (valjda)
    @Autowired
    public ProfileService(@Qualifier("fakeProfileDao") ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public int addProfile(Profile profile) {
        return profileDao.insertProfile(profile);
    }

    public List<Profile> getAllProfiles() {
        return profileDao.selectAllProfiles();
    }

    public Optional<Profile> getProfileById(UUID id) {
        return profileDao.selectProfileById(id);
    }

    public Optional<Profile> getProfileByUsername(String username) {
        return profileDao.selectProfileByUsername(username);
    }

    public Optional<Profile> getProfileByPassword(String password) {
        return profileDao.selectProfileByPassword(password);
    }

    public int deleteProfile(UUID id) {
        return profileDao.deleteProfileById(id);
    }

    public int updateProfile(UUID id, Profile newProfile) {
        return profileDao.updateProfileById(id, newProfile);
    }

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
        String emailRegex = "^(.+)@([^.]+)\\.(.+)$";
        if (email.endsWith(".")) return false;
        return email.matches(emailRegex);
    }

    public boolean strongPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(.{6,})$";
        return password.matches(passwordRegex);
    }

}
