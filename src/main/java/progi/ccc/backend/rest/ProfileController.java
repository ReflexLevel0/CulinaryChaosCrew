package progi.ccc.backend.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import progi.ccc.backend.domain.Profile;
import progi.ccc.backend.service.ProfileService;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/profiles")
@Service
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("")
    public List<Profile> listProfiles() {
        return profileService.listAll();
    }

    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable("id") long id) {
        return profileService.fetch(id);
    }

   /* @PostMapping("")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Profile> createStudent(@RequestBody Profile profile) {
        Profile saved = profileService.createStudent(profile);
        return ResponseEntity.created(URI.create("/profiles/" + saved.getUserID())).body(saved);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Profile updateStudent(@PathVariable("id") Long id, @RequestBody Profile profile) {
        if (!profile.getUserID().equals(id))
            throw new IllegalArgumentException("Profile ID must be preserved");
        return profileService.updateProfile(profile);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Profile deleteStudent(@PathVariable("id") long userID) {
        return profileService.deleteProfile(userID);
    }*/
    //ne uspjevam pristupiti securty dijelu
}
