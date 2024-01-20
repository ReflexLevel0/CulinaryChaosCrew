package com.PROGI.backend.dao;

import com.PROGI.backend.exceptions.CommentNotFound;
import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.exceptions.RecipeNotFound;
import com.PROGI.backend.model.Comment;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentDao {
    void addComment(Comment comment) throws ProfileNotFound, RecipeNotFound;
    void deleteComment(UUID userId, UUID recipeId, Timestamp timestamp) throws CommentNotFound;
    Optional<Comment> getComment(UUID userId, UUID recipeId, Timestamp timestamp);
    List<Comment> getComments(UUID recipeId) throws RecipeNotFound;
}