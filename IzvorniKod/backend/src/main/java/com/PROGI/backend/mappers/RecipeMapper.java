package com.PROGI.backend.mappers;

import com.PROGI.backend.model.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RecipeMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UUID rid = UUID.fromString(resultSet.getString("recipeID"));
        UUID uid = UUID.fromString(resultSet.getString("userID"));
        String name = resultSet.getString("name");
        String category = resultSet.getString("category");
        String ingredients = resultSet.getString("ingredients");
        String instructions = resultSet.getString("instructions");
        String origin = resultSet.getString("origin");
        String tags = resultSet.getString("specialTag");

        String imageUrlString = resultSet.getString("imageUrl");
        URL imageURL;
        try {
            imageURL = imageUrlString == null ? null : new URI(imageUrlString).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String videoUrlString = resultSet.getString("videoUrl");
        URL videoURL;
        try {
            videoURL = videoUrlString == null ? null : new URI(videoUrlString).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        int preparationTime = Integer.parseInt(resultSet.getString("preparationTime"));
        return new Recipe(rid, uid, name, category, ingredients, instructions, origin, tags, imageURL, videoURL, preparationTime);
    }
}
