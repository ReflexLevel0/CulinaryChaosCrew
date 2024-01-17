package com.PROGI.backend.dao;

import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.mappers.ProfileMapper;
import com.PROGI.backend.model.Follow;
import com.PROGI.backend.model.Profile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("postgresFollow")
public class FollowDataAccessService implements FollowDao{
    private final JdbcTemplate jdbcTemplate;

    private final ProfileDao profileDao;
    public FollowDataAccessService(JdbcTemplate jdbcTemplate, @Qualifier("postgresProfile") ProfileDao profileDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.profileDao = profileDao;
    }

    @Override
    public void follow(Follow follow) throws ProfileNotFound {
        if(profileDao.selectProfileById(follow.getUserId()).isEmpty()) throw new ProfileNotFound(follow.getUserId());
        if(profileDao.selectProfileById(follow.getFollowerId()).isEmpty()) throw new ProfileNotFound(follow.getFollowerId());
        String sql = "INSERT INTO follow(userId, followerId) VALUES(?, ?)";
        jdbcTemplate.update(sql, follow.getUserId(), follow.getFollowerId());
    }

    @Override
    public void unfollow(Follow follow) throws ProfileNotFound {
        if(profileDao.selectProfileById(follow.getUserId()).isEmpty()) throw new ProfileNotFound(follow.getUserId());
        if(profileDao.selectProfileById(follow.getFollowerId()).isEmpty()) throw new ProfileNotFound(follow.getFollowerId());
        String sql = "DELETE FROM follow WHERE userId = ? AND followerId = ?";
        jdbcTemplate.update(sql, follow.getUserId(), follow.getFollowerId());
    }

    @Override
    public List<Profile> getFollowers(UUID userId) throws ProfileNotFound {
        if(profileDao.selectProfileById(userId).isEmpty()) throw new ProfileNotFound(userId);
        String sql = "SELECT * FROM follow f JOIN profile p on f.userId = p.userid WHERE f.userId = ?";
        return jdbcTemplate.query(sql, new ProfileMapper(), userId.toString());
    }

    @Override
    public List<Profile> getFollowing(UUID userId) throws ProfileNotFound {
        if(profileDao.selectProfileById(userId).isEmpty()) throw new ProfileNotFound(userId);
        String sql = "SELECT * FROM follow f JOIN profile p on f.followerId = p.userid WHERE f.followerId = ?";
        return jdbcTemplate.query(sql, new ProfileMapper(), userId.toString());
    }
    public int followerCount(UUID uid) throws ProfileNotFound{
        if(profileDao.selectProfileById(uid).isEmpty()) throw new ProfileNotFound(uid);
        return getFollowers(uid).size();
    }

    public int followingCount(UUID uid) throws ProfileNotFound{
        if(profileDao.selectProfileById(uid).isEmpty()) throw new ProfileNotFound(uid);
        return getFollowing(uid).size();
    }


}
