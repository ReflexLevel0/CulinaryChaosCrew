package com.PROGI.backend.dao;

import com.PROGI.backend.mappers.LikeMapper;
import com.PROGI.backend.mappers.RecipeMapper;
import com.PROGI.backend.model.Like;
import com.PROGI.backend.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Repository("likesDao")
public class LikesDataAccessService implements LikesDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LikesDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLike(Like like) {
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
    public void deleteLike(UUID uid, UUID rid) {
        String uidString = uid.toString();
        String ridString = rid.toString();
        String sql = "DELETE FROM likes WHERE userId = ? AND recipeId = ?";
        jdbcTemplate.update(sql, uidString, ridString);
    }


}