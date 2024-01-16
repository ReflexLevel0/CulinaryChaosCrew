package com.PROGI.backend.api;

import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.model.Follow;
import com.PROGI.backend.model.Profile;
import com.PROGI.backend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/follow")
@RestController
public class FollowController {
    private final FollowService followService;
    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "")
    public ResponseEntity<?> follow(@RequestBody @NonNull Follow follow) {
        try{
            followService.follow(follow);
        } catch (ProfileNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "")
    public ResponseEntity<?> unfollow(@RequestBody @NonNull Follow follow) {
        try{
            followService.unfollow(follow);
        } catch (ProfileNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}")
    public List<Profile> getFollowers (@PathVariable("uid") UUID uid) throws ProfileNotFound{
        return followService.getFollowers(uid);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}")
    public List<Profile> getFollowing (@PathVariable("uid") UUID uid) throws ProfileNotFound{
        return followService.getFollowing(uid);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}")
    public int followerCount (@PathVariable("uid") UUID uid) throws ProfileNotFound{
        return followService.followerCount(uid);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}")
    public int followingCount (@PathVariable("uid") UUID uid) throws ProfileNotFound{
        return followService.followingCount(uid);
    }
}
