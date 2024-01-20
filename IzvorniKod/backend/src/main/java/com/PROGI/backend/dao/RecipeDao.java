package com.PROGI.backend.dao;

import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.model.RecipeLikeWrapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface RecipeDao {

    int insertRecipe(UUID id, Recipe recipe);

    default int insertRecipe(Recipe recipe){
        UUID id = UUID.randomUUID();
        return insertRecipe(id, recipe);
    }

    List<RecipeLikeWrapper> selectAllWrappedRecipes(Optional<UUID> userId, Optional<UUID> authorId);

    Optional<RecipeLikeWrapper> selectRecipeById(UUID recipeId, Optional<UUID> loggedInUserId);

    int deleteRecipeById(UUID id);

    int updateRecipeById(UUID id, Recipe recipe);

    List<RecipeLikeWrapper> searchRecipe(String guess, Optional<UUID> loggedInUserId);

    List<RecipeLikeWrapper> getRecipesFromCategory(String category, Optional<UUID> loggedInUserId);
}
