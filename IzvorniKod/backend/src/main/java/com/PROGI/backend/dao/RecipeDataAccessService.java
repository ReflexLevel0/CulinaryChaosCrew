package com.PROGI.backend.dao;

import com.PROGI.backend.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresRecipe")
public class RecipeDataAccessService implements RecipeDao{
    @Override
    public int insertRecipe(UUID id, Recipe recipe) {
        return 0;
    }

    @Override
    public List<Recipe> selectAllRecipes() {
        return null;
    }

    @Override
    public Optional<Recipe> selectRecipeById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteRecipeById(UUID id) {
        return 0;
    }

    @Override
    public int updateRecipeById(UUID id, Recipe recipe) {
        return 0;
    }
}
