package com.PROGI.backend.mappers;

import com.PROGI.backend.model.Like;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LikeMapper implements RowMapper<Like> {
    @Override
    public Like mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UUID userId = UUID.fromString(resultSet.getString("userId"));
        UUID recipeId = UUID.fromString(resultSet.getString("recipeId"));
        return new Like(userId, recipeId);
    }
}
