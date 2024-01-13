package com.PROGI.backend.service;

import com.PROGI.backend.dao.LikesDao;
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

    public void addLike(Like like) {
        likesDao.addLike(like);
    }

    public void deleteLike(UUID uid, UUID rid) {
        likesDao.deleteLike(uid, rid);
    }

    public List<Recipe> getLikedRecipesForUser(UUID uid) {
        return likesDao.getLikedRecipes(uid);
    }

    public List<Like> getAllLikes(){
        return likesDao.getAllLikes();
    }
}