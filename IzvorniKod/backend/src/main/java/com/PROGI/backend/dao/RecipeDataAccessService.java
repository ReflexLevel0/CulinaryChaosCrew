package com.PROGI.backend.dao;

import com.PROGI.backend.exceptions.RecipeSearchEmpty;
import com.PROGI.backend.mappers.RecipeLikeWrapperMapper;
import com.PROGI.backend.mappers.RecipeMapper;
import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.model.RecipeLikeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return jdbcTemplate.query(sql, new RecipeMapper());
    }

    @Override
    public List<RecipeLikeWrapper> selectAllWrappedRecipes(UUID userId) {
        String sql = "SELECT *, (SELECT COUNT(*) FROM likes WHERE recipe.recipeid = likes.recipeid AND likes.userid = ?) > 0 as liked FROM recipe;";
        return jdbcTemplate.query(sql, new RecipeLikeWrapperMapper(), userId.toString());
    }

    @Override
    public Optional<Recipe> selectRecipeById(UUID recipeId) {
        String sql = "SELECT * FROM recipe WHERE recipeID = ?";
        List<Recipe> recipes = jdbcTemplate.query(sql, new RecipeMapper(), recipeId.toString());
        for (Recipe r : recipes) {
            return Optional.of(r);
        }
        return Optional.ofNullable(null);
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

    @Override
    public List<Recipe> searchRecipe(String guess) {
        String sql = "SELECT * FROM recipe WHERE name LIKE CONCAT('%', ?, '%') OR specialTags LIKE CONCAT('%', ?, '%') OR ingredients LIKE CONCAT('%', ?, '%') OR origin LIKE CONCAT('%', ?, '%')";
        List<Recipe> recipes = jdbcTemplate.query(sql, new RecipeMapper(), guess, guess, guess, guess);
        try{
            if(recipes.isEmpty()) throw new RecipeSearchEmpty();
        }catch (RecipeSearchEmpty e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesFromCategory(String category) {
        String sql = "SELECT * FROM recipe WHERE category = ?";
        List<Recipe> recipes = jdbcTemplate.query(sql, new RecipeMapper(), category.toLowerCase());
        try{
            if(recipes.isEmpty()) throw new RecipeSearchEmpty();
        }catch (RecipeSearchEmpty e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }
}
