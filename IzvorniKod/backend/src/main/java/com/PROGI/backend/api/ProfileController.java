package com.PROGI.backend.api;

import com.PROGI.backend.model.Profile;
import com.PROGI.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
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

    @PostMapping(path = "add")
    public void addRecipe(@NonNull @RequestBody Profile profile) {
        profileService.addProfile(profile);
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

    @PutMapping(path = "update/{rid}")
    public void updateProfileById(@PathVariable("uid") UUID id, @NonNull @RequestBody Profile profile) {
        profileService.updateProfile(id, profile);
    }


}