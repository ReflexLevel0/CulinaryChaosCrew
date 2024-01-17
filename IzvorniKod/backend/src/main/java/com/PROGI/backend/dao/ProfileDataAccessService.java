package com.PROGI.backend.dao;

import com.PROGI.backend.HashHelper;
import com.PROGI.backend.exceptions.ProfileSearchEmpty;
import com.PROGI.backend.exceptions.RecipeSearchEmpty;
import com.PROGI.backend.mappers.ProfileMapper;
import com.PROGI.backend.mappers.RecipeMapper;
import com.PROGI.backend.model.Profile;
import com.PROGI.backend.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        String sql = "INSERT INTO profile (userid, username, email, password, name, surname, age)" +
                "VALUES (?, ?, ?, ?, ?, ? ,?)";
        String hashString = HashHelper.HashString(profile.getPassword()).get();
        jdbcTemplate.update(sql,
                id,
                profile.getUsername(),
                profile.getEmail(),
                hashString,
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
            return new Profile(id, username, password, email, name, surname, age);
        });
    }

    @Override
    public Optional<Profile> selectProfileById(UUID id) {
        String sql = "SELECT * FROM profile WHERE userID = ?";
        Profile profile;
        try{
            profile = jdbcTemplate.queryForObject(sql, new ProfileMapper(), id.toString());
        }catch(DataAccessException ex){
            profile = null;
        }
        return Optional.ofNullable(profile);
    }

    @Override
    public Optional<Profile> selectProfileByUsername(String username) {
        String sql = "SELECT * FROM profile WHERE username = ?";
        Profile profile;
        try{
            profile = jdbcTemplate.queryForObject(sql, new ProfileMapper(), username);
        }catch(DataAccessException ex){
            profile = null;
        }
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
        String hashString = HashHelper.HashString(profile.getPassword()).get();
        return jdbcTemplate.update(
                sql,
                profile.getUsername(),
                hashString,
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
    public Optional<Profile> selectProfileByCredentials(String username, String hashedPassword) {
        String sql = "SELECT * FROM profile WHERE username = ? AND password = ?";
        Profile profile;
        try {
            profile = jdbcTemplate.queryForObject(sql, new ProfileMapper(), username, hashedPassword);
        } catch (Exception ex) {
            profile = null;
        }
        return Optional.ofNullable(profile);
    }

    @Override
    public List<Profile> searchProfile(String guess) {
        String sql = "SELECT * FROM profile WHERE username LIKE CONCAT('%', ?, '%') OR name LIKE CONCAT('%', ?, '%')";
        List<Profile> profiles = jdbcTemplate.query(sql, new ProfileMapper(), guess, guess);
        try{
            if(profiles.isEmpty()) throw new ProfileSearchEmpty();
        }catch (ProfileSearchEmpty e) {
            throw new RuntimeException(e);
        }
        return profiles;
    }
}
