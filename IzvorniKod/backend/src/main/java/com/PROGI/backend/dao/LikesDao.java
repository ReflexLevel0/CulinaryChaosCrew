package com.PROGI.backend.dao;

import com.PROGI.backend.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface LikesDao {
    void addLike(UUID userId, UUID recipeId);
    void deleteLike(UUID userId, UUID recipeId);
    List<Recipe> getLikedRecipes(UUID id);
}
