package com.PROGI.backend.dao;

import com.PROGI.backend.exceptions.RecipeNotFound;
import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.mappers.LikeMapper;
import com.PROGI.backend.mappers.RecipeMapper;
import com.PROGI.backend.model.Like;
import com.PROGI.backend.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresLike")
public class LikesDataAccessService implements LikesDao {
    private final JdbcTemplate jdbcTemplate;
    private final RecipeDao recipeDao;
    private final ProfileDao profileDao;

    @Autowired
    public LikesDataAccessService(JdbcTemplate jdbcTemplate, @Qualifier("postgresRecipe") RecipeDao recipeDao, @Qualifier("postgresProfile") ProfileDao profileDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.recipeDao = recipeDao;
        this.profileDao = profileDao;
    }

    @Override
    public void addLike(Like like) throws RecipeNotFound, ProfileNotFound {
        if(recipeDao.selectRecipeById(like.getRecipeId(), Optional.empty()).isEmpty()) throw new RecipeNotFound(like.getRecipeId());
        if(profileDao.selectProfileById(like.getUserId()).isEmpty()) throw new ProfileNotFound(like.getUserId());
        String sql = "INSERT INTO likes(userId, recipeId) VALUES(?, ?)";
        jdbcTemplate.update(sql, like.getUserId(), like.getRecipeId());
    }

    @Override
    public List<Like> getAllLikes() {
        String sql = "SELECT * FROM likes";
        return jdbcTemplate.query(sql, new LikeMapper());
    }

    @Override
    public int likesCount(UUID rid) {
        String sql = "SELECT * FROM likes where recipeId = ?";
        return jdbcTemplate.query(sql,new LikeMapper(), rid.toString()).toArray().length;
    }

    @Override
    public List<Recipe> getLikedRecipes(UUID uid) {
        String sql = "SELECT * FROM likes l JOIN recipe r ON l.recipeId = r.recipeId WHERE l.userid = ?";
        return jdbcTemplate.query(sql, new RecipeMapper(), uid.toString());
    }

    @Override
    public void deleteLike(UUID uid, UUID rid) throws RecipeNotFound, ProfileNotFound {
        if(recipeDao.selectRecipeById(rid, Optional.empty()).isEmpty()) throw new RecipeNotFound(rid);
        if(profileDao.selectProfileById(uid).isEmpty()) throw new ProfileNotFound(uid);
        String uidString = uid.toString();
        String ridString = rid.toString();
        String sql = "DELETE FROM likes WHERE userId = ? AND recipeId = ?";
        jdbcTemplate.update(sql, uidString, ridString);
    }


}