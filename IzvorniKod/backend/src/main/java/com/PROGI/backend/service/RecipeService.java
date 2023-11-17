package com.PROGI.backend.service;

import com.PROGI.backend.dao.RecipeDao;
import com.PROGI.backend.model.Recipe;
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
//    fakeRecipeDao za fake bazu, postgresRecipe za real bazu (valjda)
    public RecipeService(@Qualifier("postgresRecipe") RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public int addRecipe(Recipe recipe) {
        return recipeDao.insertRecipe(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeDao.selectAllRecipes();
    }

    public Optional<Recipe> getRecipeById(UUID id) {
        return recipeDao.selectRecipeById(id);
    }

    public int deleteRecipe(UUID id) {
        return recipeDao.deleteRecipeById(id);
    }

    public int updateRecipe(UUID id, Recipe recipe) {
        return recipeDao.updateRecipeById(id, recipe);
    }

}
