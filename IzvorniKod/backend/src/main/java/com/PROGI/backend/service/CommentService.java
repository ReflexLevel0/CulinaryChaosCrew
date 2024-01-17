package com.PROGI.backend.service;

import com.PROGI.backend.dao.CommentDao;
import com.PROGI.backend.exceptions.CommentNotFound;
import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.exceptions.RecipeNotFound;
import com.PROGI.backend.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentDao commentDao;

    @Autowired
    public CommentService(@Qualifier("postgresComment") CommentDao commentDao){
        this.commentDao = commentDao;
    }

    public void addComment(Comment comment) throws RecipeNotFound, ProfileNotFound {
        commentDao.addComment(comment);
    }

    public void deleteComment(UUID userId, UUID recipeId, Timestamp timestamp) throws CommentNotFound {
        commentDao.deleteComment(userId, recipeId, timestamp);
    }

    public List<Comment> getComments(UUID recipeId) throws RecipeNotFound {
        return commentDao.getComments(recipeId);
    }
}
