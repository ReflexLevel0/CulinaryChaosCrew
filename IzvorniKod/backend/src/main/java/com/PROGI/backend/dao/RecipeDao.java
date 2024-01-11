package com.PROGI.backend.dao;

import com.PROGI.backend.model.Recipe;
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

    List<Recipe> selectAllRecipes();

    Optional<Recipe> selectRecipeById(UUID id);
    int deleteRecipeById(UUID id);

    int updateRecipeById(UUID id, Recipe recipe);
}
