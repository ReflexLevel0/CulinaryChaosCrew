package com.PROGI.backend.service;

import com.PROGI.backend.dao.ProfileDao;
import com.PROGI.backend.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {
    private final ProfileDao profileDao;

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

    public int deleteProfile(UUID id) {
        return profileDao.deleteProfileById(id);
    }

    public int updateProfile(UUID id, Profile newProfile) {
        return profileDao.updateProfileById(id, newProfile);
    }
}
