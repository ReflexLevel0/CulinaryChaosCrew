package com.PROGI.backend.dao;

import com.PROGI.backend.model.Profile;
import com.PROGI.backend.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresRecipe")
public class RecipeDataAccessService implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertRecipe(UUID id, Recipe recipe) {
        String sql = "INSERT INTO recipe (recipeId, userId, name, category, ingredients, instructions, origin, specialtag, imageURL, videoURL, preparationTime)" +
                "VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,?)";
        URL imageUrl = recipe.getImageURL();
        String imageUrlString = imageUrl == null ? null : imageUrl.toString();
        URL videoUrl = recipe.getVideoURL();
        String videoUrlString = videoUrl == null ? null : videoUrl.toString();
        jdbcTemplate.update(sql,
                id,
                recipe.getUserId(),
                recipe.getName(),
                recipe.getCategory(),
                recipe.getIngredients(),
                recipe.getInstructions(),
                recipe.getOrigin(),
                recipe.getTags(),
                imageUrlString,
                videoUrlString,
                recipe.getPreparationTime()
        );
        return 0;
    }

    @Override
    public List<Recipe> selectAllRecipes() {
        String sql = "SELECT * FROM recipe";
        return selectRecipes(sql);
    }

    @Override
    public Optional<Recipe> selectRecipeById(UUID id) {
        String sql = "SELECT * FROM recipe WHERE recipeID = ?";
        List<Recipe> recipes = selectRecipes(sql);
        for (Recipe r : recipes) {
            return Optional.of(r);
        }
        return Optional.of(null);
    }

    private List<Recipe> selectRecipes(String sql) {
        List<Recipe> recipes = jdbcTemplate.query
                (sql, (resultSet, i) -> {
                    UUID rid = UUID.fromString(resultSet.getString("recipeID"));
                    UUID uid = UUID.fromString(resultSet.getString("userID"));
                    String name = resultSet.getString("name");
                    String category = resultSet.getString("category");
                    String ingredients = resultSet.getString("ingredients");
                    String instructions = resultSet.getString("instructions");
                    String origin = resultSet.getString("origin");
                    String tags = resultSet.getString("specialTag");

                    String imageUrlString = resultSet.getString("imageUrl");
                    URL imageURL = null;
                    try {
                        imageURL = imageUrlString == null ? null : new URI(imageUrlString).toURL();
                    } catch (MalformedURLException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                    String videoUrlString = resultSet.getString("videoUrl");
                    URL videoURL = null;
                    try {
                        videoURL = videoUrlString == null ? null : new URI(videoUrlString).toURL();
                    } catch (MalformedURLException | URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                    int preparationTime = Integer.parseInt(resultSet.getString("preparationTime"));
                    return new Recipe(rid, uid, name, category, ingredients, instructions, origin, tags, imageURL, videoURL, preparationTime);
                });
        return recipes;
    }

    @Override
    public int deleteRecipeById(UUID id) {
        String sql = "DELETE FROM recipe WHERE recipeID = ?";
        jdbcTemplate.update(sql, id);
        return 0;
    }

    @Override
    public int updateRecipeById(UUID id, Recipe recipe) {
        String sql = "UPDATE recipe SET userId = ?, name = ?, category = ?, ingredients = ?, instructions = ?, origin = ?, specialtag = ?, imageURL = ?, videoURL = ?, likes = ?, preparationTime = ? WHERE recipeID = ?";
        return jdbcTemplate.update(
                sql,
                recipe.getUserId(),
                recipe.getName(),
                recipe.getCategory(),
                recipe.getIngredients(),
                recipe.getInstructions(),
                recipe.getOrigin(),
                recipe.getTags(),
                recipe.getImageURL(),
                recipe.getVideoURL(),
                recipe.getLikes(),
                recipe.getPreparationTime(),
                id.toString()
        );
    }
}
