package com.PROGI.backend.dao;

import com.PROGI.backend.exceptions.CommentNotFound;
import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.exceptions.RecipeNotFound;
import com.PROGI.backend.mappers.CommentMapper;
import com.PROGI.backend.model.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresComment")
public class CommentDataAccessService implements CommentDao {
    private final JdbcTemplate jdbcTemplate;
    private final ProfileDao profileDao;
    private final RecipeDao recipeDao;

    public CommentDataAccessService(JdbcTemplate jdbcTemplate, @Qualifier("postgresProfile") ProfileDao profileDao, @Qualifier("postgresRecipe") RecipeDao recipeDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.profileDao = profileDao;
        this.recipeDao = recipeDao;
    }

    @Override
    public void addComment(Comment comment) throws ProfileNotFound, RecipeNotFound {
        if(profileDao.selectProfileById(comment.getUserId()).isEmpty()) throw new ProfileNotFound(comment.getUserId());
        if(recipeDao.selectRecipeById(comment.getRecipeId()).isEmpty()) throw new RecipeNotFound(comment.getRecipeId());
        String sql = "INSERT INTO comment(userId, recipeId, timestamp, text) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, comment.getUserId(), comment.getRecipeId(), comment.getTimestamp(), comment.getText());
    }

    @Override
    public void deleteComment(UUID userId, UUID recipeId, Timestamp timestamp) throws CommentNotFound {
        Optional<Comment> comment = getComment(userId, recipeId, timestamp);
        if(comment.isEmpty()) throw new CommentNotFound();
        jdbcTemplate.update("DELETE FROM comment WHERE userId = ? AND recipeId = ? AND extract(epoch from timestamp) = ?", userId.toString(), recipeId.toString(), timestamp.toString());
    }

    @Override
    public Optional<Comment> getComment(UUID userId, UUID recipeId, Timestamp timestamp){
        Comment comment;
        try{
            comment = jdbcTemplate.queryForObject("SELECT * FROM comment WHERE userId = ? AND recipeId = ? AND extract(epoch from timestamp) = ?", new CommentMapper(), userId.toString(), recipeId.toString(), timestamp.toString());
        }catch(DataAccessException ex){
            return Optional.ofNullable(null);
        }

        return Optional.of(comment);
    }

    @Override
    public List<Comment> getComments(UUID recipeId) throws RecipeNotFound {
        if(recipeDao.selectRecipeById(recipeId).isEmpty()) throw new RecipeNotFound(recipeId);
        String sql = "SELECT * FROM comment WHERE recipeId = ?";
        return jdbcTemplate.query(sql, new CommentMapper(), recipeId.toString());
    }
}
