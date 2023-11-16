package com.PROGI.backend.dao;

import com.PROGI.backend.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresProfile")
public class ProfileDataAccessService implements ProfileDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertProfile(UUID id, Profile profile) {
        return 0;
    }

    @Override
    public List<Profile> selectAllProfiles() {
        String sql = "SELECT * FROM profile";
        List<Profile> profiles = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("userID"));
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = Integer.parseInt(resultSet.getString("age"));
            return new Profile(id, password, username, email, name, surname, age);
        });

        return profiles;
    }

    @Override
    public Optional<Profile> selectProfileById(UUID id) {
        String sql = "SELECT * FROM profile WHERE userId = ?";
        Profile profile = jdbcTemplate.queryForObject
                (sql, new Object[]{id}, (resultSet, i) -> {
            UUID uid = UUID.fromString(resultSet.getString("userId"));
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = Integer.parseInt(resultSet.getString("age"));
            return new Profile(uid, password, username, email, name, surname, age);
        });
        return Optional.ofNullable(profile);
    }

    @Override
    public Optional<Profile> selectProfileByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<Profile> selectProfileByPassword(String password) {
        return Optional.empty();
    }

    @Override
    public int deleteProfileById(UUID id) {
        return 0;
    }

    @Override
    public int updateProfileById(UUID id, Profile profile) {
        return 0;
    }
}
