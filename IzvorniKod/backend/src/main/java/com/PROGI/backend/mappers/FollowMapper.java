package com.PROGI.backend.mappers;

import com.PROGI.backend.model.Follow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class FollowMapper implements RowMapper<Follow>{
        @Override
        public Follow mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            UUID userId = UUID.fromString(resultSet.getString("userId"));
            UUID followerId = UUID.fromString(resultSet.getString("followerId"));
            return new Follow(userId, followerId);
        }
}
