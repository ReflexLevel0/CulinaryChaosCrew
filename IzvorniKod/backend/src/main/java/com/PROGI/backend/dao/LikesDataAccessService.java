package com.PROGI.backend.dao;

import com.PROGI.backend.model.Recipe;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public void addLike(UUID userId, UUID recipeId) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteLike(UUID userId, UUID recipeId) {
        throw new NotImplementedException();
    }

    @Override
    public List<Recipe> getLikedRecipes(UUID id){
        String sql = "SELECT * FROM likes l JOIN recipe r on l.recipeId = r.recipeid WHERE l.userid = ''" + id + "''";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID recipeId = UUID.fromString(resultSet.getString("r.recipeId"));
            UUID userId = UUID.fromString(resultSet.getString("r.userId"));
            String name = resultSet.getString("r.name");
            String category = resultSet.getString("r.category");
            String ingredients = resultSet.getString("r.ingredients");
            String instructions = resultSet.getString("r.instructions");
            String origin = resultSet.getString("r.origin");
            String tags = resultSet.getString("r.tags");
            URL imageURL = resultSet.getURL("r.imageURL");
            URL videoURL = resultSet.getURL("r.videoURL");
            int preparationTime = resultSet.getInt("r.preparationTime");
            return new Recipe(recipeId, userId, name, category, ingredients, instructions, origin, tags, imageURL, videoURL, preparationTime);
        });
    }
}