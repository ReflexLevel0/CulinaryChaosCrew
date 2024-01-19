package com.PROGI.backend.api;

import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.model.Follow;
import com.PROGI.backend.model.Profile;
import com.PROGI.backend.service.FollowService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/profile")
@RestController
public class FollowController {
    private final FollowService followService;
    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "{uid}/follow")
    public ResponseEntity<?> follow(@RequestBody @NonNull Follow follow) {
        if(follow.getUserId() == follow.getFollowerId()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        try{
            followService.follow(follow);
        } catch (ProfileNotFound e) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "{uid}/unfollow")
    public ResponseEntity<?> unfollow(@RequestParam @NonNull UUID uid, @RequestParam @NonNull UUID uid2) {
        Follow follow = new Follow(uid, uid2);
        try{
            followService.unfollow(follow);
        } catch (ProfileNotFound e) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}/followers")
    public ResponseEntity<?> getFollowers (@PathVariable("uid") UUID uid) {
        List<Profile> list;
        try{
            list = followService.getFollowers(uid);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}/following")
    public ResponseEntity<?> getFollowing (@PathVariable("uid") UUID uid) {
        List<Profile> list;
        try{
            list = followService.getFollowing(uid);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}/followers/count")
    public ResponseEntity<?> followerCount (@PathVariable("uid") UUID uid) {
        int followerCount;
        try {
            followerCount = followService.followerCount(uid);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(followerCount, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}/following/count")
    public ResponseEntity<?> followingCount (@PathVariable("uid") UUID uid) {
        int followingCount;
        try {
            followingCount = followService.followingCount(uid);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(followingCount, HttpStatus.OK);
    }
}
