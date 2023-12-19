package com.PROGI.backend.dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.PROGI.backend.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.CryptoPrimitive;
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
        String sql = "INSERT INTO profile (userid, username, email, password, name, surname, age)" +
                "VALUES (?, ?, ?, ?, ?, ? ,?)";
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, profile.getPassword().toCharArray());
        jdbcTemplate.update(sql,
                id,
                profile.getUsername(),
                profile.getEmail(),
                bcryptHashString,
                profile.getName(),
                profile.getSurname(),
                profile.getAge());
        return 0;
    }

    @Override
    public List<Profile> getAllProfiles() {
        String sql = "SELECT * FROM profile";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("userID"));
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = Integer.parseInt(resultSet.getString("age"));
            return new Profile(id, password, username, email, name, surname, age);
        });
    }

    @Override
    public Optional<Profile> selectProfileById(UUID id) {
        String sql = "SELECT * FROM profile WHERE userID = ?";
        Profile profile = jdbcTemplate.queryForObject
                (sql, new Object[]{id}, (resultSet, i) -> {
            UUID uid = UUID.fromString(resultSet.getString("userID"));
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
        String sql = "SELECT * FROM profile WHERE username = ?";
        Profile profile = jdbcTemplate.queryForObject(sql,
                new Object[]{username},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("userID"));
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    int age = resultSet.getInt("age");

                    return new Profile(id, username, password, email, name, surname, age);
                });
        return Optional.ofNullable(profile);
    }


    @Override
    public int deleteProfileById(UUID id) {
        String sql = "DELETE FROM profile WHERE userId = ?";
        jdbcTemplate.update(sql, id);
        return 0;
    }

    @Override
    public int updateProfileById(UUID id, Profile profile) {
        String sql = "UPDATE profile SET username = ?, password = ?, email = ?, name = ?, surname = ?, age = ? WHERE userID = ?";
        return jdbcTemplate.update(
                sql,
                profile.getUsername(),
                profile.getPassword(),
                profile.getEmail(),
                profile.getName(),
                profile.getSurname(),
                profile.getAge(),
                id.toString());
    }

    @Override
    public void deleteAllProfiles() {
        String sql = "DELETE FROM profile";
        jdbcTemplate.update(sql);
    }

    @Override
    public Optional<Profile> selectProfileByCredentials(String username, String password) {
        String sql = "SELECT * FROM profile WHERE username = ? AND password = ?";
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        Profile profile = jdbcTemplate.queryForObject(sql,
         new Object[]{username, bcryptHashString},
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("userID"));
                    String email = resultSet.getString("email");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    int age = resultSet.getInt("age");

                    return new Profile(id, username, bcryptHashString, email, name, surname, age);
                });
        return Optional.ofNullable(profile);
    }
}
