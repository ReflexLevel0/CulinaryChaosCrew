package com.PROGI.backend.mappers;

import com.PROGI.backend.model.Profile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProfileMapper implements RowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("userID"));
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        int age = resultSet.getInt("age");
        return new Profile(id, username, password, email, name, surname, age);
    }
}
