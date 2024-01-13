package com.PROGI.backend.dao;

import com.PROGI.backend.exceptions.RecipeNotFound;
import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.model.Like;
import com.PROGI.backend.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface LikesDao {
    void addLike(Like like) throws RecipeNotFound, ProfileNotFound;
    void deleteLike(UUID uid, UUID rid) throws RecipeNotFound, ProfileNotFound;
    List<Recipe> getLikedRecipes(UUID uid) throws ProfileNotFound;
    List<Like> getAllLikes();
}
