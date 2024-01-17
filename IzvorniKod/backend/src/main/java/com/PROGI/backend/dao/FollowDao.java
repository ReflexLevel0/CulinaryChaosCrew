package com.PROGI.backend.dao;

import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.model.Follow;
import com.PROGI.backend.model.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface FollowDao {
    public void follow(Follow follow) throws ProfileNotFound;

    public void unfollow(Follow follow) throws ProfileNotFound;

    public List<Profile> getFollowers (UUID userId) throws ProfileNotFound;

    public List<Profile> getFollowing (UUID userId) throws ProfileNotFound;

    public int followerCount(UUID uid) throws ProfileNotFound;

    public int followingCount(UUID uid) throws ProfileNotFound;
}
