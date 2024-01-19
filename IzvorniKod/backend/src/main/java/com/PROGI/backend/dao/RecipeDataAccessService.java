package com.PROGI.backend.dao;

import com.PROGI.backend.exceptions.RecipeSearchEmpty;
import com.PROGI.backend.mappers.RecipeLikeWrapperMapper;
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
                recipe.getUid(),
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
    public List<RecipeLikeWrapper> selectAllWrappedRecipes(Optional<UUID> userId, Optional<UUID> authorId) {
        String sql = "SELECT *, " + (userId.map(uuid -> "(SELECT COUNT(*) FROM likes WHERE recipe.recipeid = likes.recipeid AND likes.userid LIKE '%" + uuid + "%') > 0").orElse("false")) + " as liked FROM recipe";
        if(authorId.isPresent()){
            sql += " WHERE userid LIKE '%" + authorId.get() + "%'";
        }
        return jdbcTemplate.query(sql, new RecipeLikeWrapperMapper());
    }

    @Override
    public Optional<RecipeLikeWrapper> selectRecipeById(UUID recipeId, Optional<UUID> loggedInUserId) {
        String sql = "SELECT *, " +  (loggedInUserId.map(uuid -> "(SELECT COUNT(*) FROM likes WHERE recipe.recipeid = likes.recipeid AND likes.userid LIKE '%" + uuid + "%') > 0").orElse("false")) + " as liked FROM recipe WHERE recipeID = ?";
        List<RecipeLikeWrapper> recipes = jdbcTemplate.query(sql, new RecipeLikeWrapperMapper(), recipeId.toString());
        for (RecipeLikeWrapper r : recipes) {
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
                recipe.getUid(),
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
    public List<RecipeLikeWrapper> searchRecipe(String guess, Optional<UUID> loggedInUserId) {
        String search = "'%"+guess+"%'";
        String sql = "SELECT *, " + (loggedInUserId.map(uuid -> "(SELECT COUNT(*) FROM likes WHERE recipe.recipeid = likes.recipeid AND likes.userid LIKE '%" + uuid + "%') > 0").orElse("false")) + " FROM recipe WHERE name LIKE "+search+" OR specialTag LIKE "+search+" OR ingredients LIKE "+search+" OR origin LIKE "+search;
        List<RecipeLikeWrapper> recipes = jdbcTemplate.query(sql, new RecipeLikeWrapperMapper());
        return recipes;
    }

    @Override
    public List<RecipeLikeWrapper> getRecipesFromCategory(String category, Optional<UUID> loggedInUserId) {
        String sql = "SELECT *, " + (loggedInUserId.map(uuid -> "(SELECT COUNT(*) FROM likes WHERE recipe.recipeid = likes.recipeid AND likes.userid LIKE '%" + uuid + "%') > 0").orElse("false")) + " FROM recipe WHERE category = ?";
        List<RecipeLikeWrapper> recipes = jdbcTemplate.query(sql, new RecipeLikeWrapperMapper(), category.toLowerCase());
        try{
            if(recipes.isEmpty()) throw new RecipeSearchEmpty();
        }catch (RecipeSearchEmpty e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }
}
