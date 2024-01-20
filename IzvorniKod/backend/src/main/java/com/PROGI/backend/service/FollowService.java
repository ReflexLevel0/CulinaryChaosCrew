package com.PROGI.backend.service;

import com.PROGI.backend.dao.FollowDao;
import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.model.Follow;
import com.PROGI.backend.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FollowService {
    private final FollowDao followDao;

    @Autowired
    public FollowService(@Qualifier("postgresFollow") FollowDao followDao) {
        this.followDao = followDao;
    }

    public void follow(Follow follow) throws ProfileNotFound {
        followDao.follow(follow);
    }

    public void unfollow(Follow follow) throws ProfileNotFound{
        followDao.unfollow(follow);
    }

    public List<Profile> getFollowers (UUID userId) throws ProfileNotFound{
        return followDao.getFollowers(userId);
    }

    public List<Profile> getFollowing (UUID userId) throws ProfileNotFound{
        return followDao.getFollowing(userId);
    }

    public int followerCount(UUID uid) throws ProfileNotFound{
        return followDao.followerCount(uid);
    }

    public int followingCount(UUID uid) throws ProfileNotFound{
        return followDao.followingCount(uid);
    }


}
