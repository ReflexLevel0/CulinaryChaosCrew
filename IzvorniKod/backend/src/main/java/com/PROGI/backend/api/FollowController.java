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

@RequestMapping("/follow")
@RestController
public class FollowController {
    private final FollowService followService;
    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping(path = "")
    public ResponseEntity<?> follow(@RequestBody @NonNull Follow follow) {
        if(follow.getUserId() == follow.getFollowerId()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        try{
            followService.follow(follow);
        } catch (ProfileNotFound e) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping(path = "")
    public ResponseEntity<?> unfollow(@RequestBody @NonNull Follow follow) {
        try{
            followService.unfollow(follow);
        } catch (ProfileNotFound e) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(path = "followers/{uid}")
    public ResponseEntity<?> getFollowers (@PathVariable("uid") UUID uid) {
        List<Profile> list;
        try{
            list = followService.getFollowers(uid);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "following/{uid}")
    public ResponseEntity<?> getFollowing (@PathVariable("uid") UUID uid) {
        List<Profile> list;
        try{
            list = followService.getFollowing(uid);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "follower/count/{uid}")
    public ResponseEntity<?> followerCount (@PathVariable("uid") UUID uid) {
        int followerCount;
        try {
            followerCount = followService.followerCount(uid);
        } catch (ProfileNotFound ex){
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(followerCount, HttpStatus.OK);
    }

    @GetMapping(path = "following/count/{uid}")
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
