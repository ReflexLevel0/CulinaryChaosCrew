package com.PROGI.backend.mappers;

import com.PROGI.backend.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID userId = UUID.fromString(rs.getString("userId"));
        UUID recipeId = UUID.fromString(rs.getString("recipeId"));
        Timestamp timestamp = rs.getTimestamp("timestamp");
        String text = rs.getString("text");
        var comment = new Comment(userId, recipeId, text);
        comment.setDate(timestamp);
        return comment;
    }
}
