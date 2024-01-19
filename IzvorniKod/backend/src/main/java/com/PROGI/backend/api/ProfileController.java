package com.PROGI.backend.api;

import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.model.Profile;
import com.PROGI.backend.service.LoginRequest;
import com.PROGI.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/profile")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping(path = "register")
    public void addProfile(@NonNull @RequestBody Profile profile) throws IllegalArgumentException {
        if (!profileService.goodEmailFormat(profile.getEmail())) {
            throw new IllegalArgumentException("Email is in the wrong format");
        }
        if (!profileService.strongPassword(profile.getPassword())) {
            throw new IllegalArgumentException("Password must have at least one number, at least 6 characters (including numbers)");
        }
        if (profileService.usernameTaken(profile.getUsername())) {
            throw new IllegalArgumentException("Username is taken");
        }
        if (profileService.emailTaken(profile.getEmail())) {
            throw new IllegalArgumentException("Email is taken");
        }
        profileService.addProfile(profile);
    }

    @PostMapping(path = "login")
    public String getProfileByUsernameAndPassword(@RequestBody LoginRequest loginRequest) throws IllegalAccessException {
        Profile profile1 = profileService.getProfileByUsername(loginRequest.getUsername()).orElse(null);
        Profile profile2 = profileService.getProfileByCredentials(loginRequest.getUsername(), loginRequest.getPassword()).orElse(null);
        if (profile1 == null) {
            throw new IllegalArgumentException("Username doesn't exist");
        }
        if (!profile1.equals(profile2)) {
            throw new IllegalArgumentException("Wrong password");
        }
        return loginRequest.getUsername();
    }

    @GetMapping(path = "allProfiles")
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping(path = "userid/{uid}")
    public Profile getProfileById(@PathVariable("uid") UUID id) {
        return profileService.getProfileById(id).orElse(null);
    }

    @GetMapping(path = "username/{username}")
    public Profile getProfileByUsername(@PathVariable("username") String username){
        return profileService.getProfileByUsername(username).orElse(null);
    }

    @DeleteMapping(path = "{uid}")
    public void deleteProfileById(@PathVariable("uid") UUID id) throws ProfileNotFound {
        if(profileService.profileExists(id)) {
            throw new ProfileNotFound(id);
        }
        profileService.deleteProfile(id);
    }

    @PutMapping(path = "{uid}")
    public void updateProfileById(@PathVariable("uid") UUID id, @NonNull @RequestBody Profile profile) {
        profileService.updateProfile(id, profile);
    }

    @DeleteMapping(path = "deleteAllProfiles")
    public void deleteAllProfiles(){
        profileService.deleteAllProfiles();
    }

    @GetMapping(path="search/{querry}")
    public List<Profile> searchProfile(@NonNull @PathVariable("querry") String guess){
        return profileService.searchProfile(guess);
    }
}