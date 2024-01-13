package com.PROGI.backend.dao;

import com.PROGI.backend.model.Like;
import com.PROGI.backend.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface LikesDao {
    void addLike(Like like);
    void deleteLike(UUID uid, UUID rid);
    List<Recipe> getLikedRecipes(UUID uid);
    List<Like> getAllLikes();
    int likesCount(UUID rid);
}
