package com.PROGI.backend.api;

import com.PROGI.backend.model.Profile;
import com.PROGI.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public void addProfile(@NonNull @RequestBody Profile profile) throws IllegalAccessException {
        if (profileService.usernameTaken(profile.getUsername())) {
            throw new IllegalArgumentException("Username is taken");
        }
        if (profileService.emailTaken(profile.getEmail())) {
            throw new IllegalArgumentException("Email is taken");
        }
        profileService.addProfile(profile);
    }

    @GetMapping(path = "login")
    public Profile getProfileByUsernameAndPassword(@RequestBody String username, String password) throws IllegalAccessException {
        Profile profile1 = profileService.getProfileByUsername(username).orElse(null);
        Profile profile2 = profileService.getProfileByPassword(password).orElse(null);
        if (profile1 == null || profile2 == null || !profile1.equals(profile2)) {
            throw new IllegalArgumentException("Wrong username or password");
        }
        return profile1;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "allProfiles")
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping(path = "get/{uid}")
    public Profile getProfileById(@PathVariable("uid") UUID id) {
        return profileService.getProfileById(id).orElse(null);
    }

    @DeleteMapping(path = "delete/{uid}")
    public void deleteProfileById(@PathVariable("uid") UUID id){
        profileService.deleteProfile(id);
    }

    @PutMapping(path = "update/{uid}")
    public void updateProfileById(@PathVariable("uid") UUID id, @NonNull @RequestBody Profile profile) {
        profileService.updateProfile(id, profile);
    }


}