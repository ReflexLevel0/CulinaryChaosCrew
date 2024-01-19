package com.PROGI.backend.service;

import com.PROGI.backend.dao.RecipeDao;
import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.model.RecipeLikeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {
    private final RecipeDao recipeDao;

    @Autowired
    public RecipeService(@Qualifier("postgresRecipe") RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public int addRecipe(Recipe recipe) {
        return recipeDao.insertRecipe(recipe);
    }

    public List<RecipeLikeWrapper> getAllRecipes(Optional<UUID> loggedInUserId, Optional<UUID> authorId){
        return recipeDao.selectAllWrappedRecipes(loggedInUserId, authorId);
    }

    public Optional<RecipeLikeWrapper> getRecipeById(UUID recipeId, Optional<UUID> loggedInUserId) {
        return recipeDao.selectRecipeById(recipeId, loggedInUserId);
    }

    public int deleteRecipe(UUID id) {
        return recipeDao.deleteRecipeById(id);
    }

    public int updateRecipe(UUID id, Recipe recipe) {
        return recipeDao.updateRecipeById(id, recipe);
    }

    public List<RecipeLikeWrapper> searchRecipe(String guess, Optional<UUID> loggedInUserId) { return recipeDao.searchRecipe(guess, loggedInUserId); }

    public List<RecipeLikeWrapper> getRecipesFromCategory(String category, Optional<UUID> loggedInUserId) { return recipeDao.getRecipesFromCategory(category, loggedInUserId); }
}
