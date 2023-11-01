package progi.ccc.backend.service.impl;

import org.springframework.util.Assert;
import progi.ccc.backend.dao.ProfileRepository;
import progi.ccc.backend.service.EntityMissingException;
import progi.ccc.backend.service.ProfileService;
import progi.ccc.backend.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import progi.ccc.backend.service.RequestDeniedException;

import java.util.List;
import java.util.Optional;

public class ProfileServiceJpa implements ProfileService {
    @Autowired
    private ProfileRepository profileRepo;
    @Override
    public List<Profile> listAll() {
        return profileRepo.findAll();
    }

    //private static final String USERNAME_FORMAT = "[0-9]{10}";
    //pasword also

    @Override
    public Optional<Profile> findByUserID(long userId) {
        return profileRepo.findById(userId);
    }

    @Override
    public Optional<Profile> findByUsername(String username) {
        Assert.notNull(username, "Username must be given");
        return profileRepo.findByUserName(username);
    }

    @Override
    public Profile fetch(long userId) {
        return findByUserID(userId).orElseThrow(
                () -> new EntityMissingException(Profile.class, userId)
        );
    }

    @Override
    public Profile createStudent(Profile profile) {
        validate(profile);
        Assert.isNull(profile.getUserID(), "User ID must be null, not: " + profile.getUserID());
        if (profileRepo.countByUserName(profile.getUserName()) > 0)
            throw new RequestDeniedException(
                    "Student with Username " + profile.getUserName() + " already exists"
            );
        return profileRepo.save(profile);
    }


    @Override
    public Profile updateProfile(Profile profile) {
        validate(profile);
        Long userID = profile.getUserID();
        if (!profileRepo.existsById(userID))
            throw new EntityMissingException(Profile.class, userID);
        if (profileRepo.existsByUserNameAndUserIDNot(profile.getUserName(), userID))
            throw new RequestDeniedException(
                    "Profile with username " + profile.getUserName() + " already exists"
            );
        return profileRepo.save(profile);
    }

    @Override
    public Profile deleteProfile(long userID) {
        Profile profile = fetch(userID);
        profileRepo.delete(profile);
        return profile;
    }

    private void validate(Profile profile) {
        Assert.notNull(profile, "Profile object must be given");
        String username = profile.getUserName();
        Assert.hasText(username, "Username must be given");
        //Assert.isTrue(jmbag.matches(JMBAG_FORMAT), "JMBAG must have 10 digits, not '" + jmbag + "'");
    }


}
