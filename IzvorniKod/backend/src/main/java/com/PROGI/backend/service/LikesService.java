package com.PROGI.backend.service;

import com.PROGI.backend.dao.LikesDao;
import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.exceptions.RecipeNotFound;
import com.PROGI.backend.model.Like;
import com.PROGI.backend.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikesService {
    private final LikesDao likesDao;

    @Autowired
    public LikesService(@Qualifier("postgresLike") LikesDao likesDao) {
        this.likesDao = likesDao;
    }

    public void addLike(Like like) throws RecipeNotFound, ProfileNotFound {
        likesDao.addLike(like);
    }

    public void deleteLike(UUID uid, UUID rid) throws RecipeNotFound, ProfileNotFound {
        likesDao.deleteLike(uid, rid);
    }

    public List<Recipe> getLikedRecipesForUser(UUID uid) throws ProfileNotFound {
        return likesDao.getLikedRecipes(uid);
    }

    public List<Like> getAllLikes(){
        return likesDao.getAllLikes();
    }

    public int likesCount(UUID rid){ return likesDao.likesCount(rid); }
}