package com.PROGI.backend.dao;

import com.PROGI.backend.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeRecipeDao")
public class FakeRecipeDataAccessService implements RecipeDao{

    private static List<Recipe> DB = new ArrayList<>();
    @Override
    public int insertRecipe(UUID id, Recipe recipe) {
        DB.add(new Recipe(id,
                recipe.getUserId(),
                recipe.getName(),
                recipe.getCategory(),
                recipe.getIngredients(),
                recipe.getInstructions(),
                recipe.getOrigin(),
                recipe.getTags(),
                recipe.getVideoURL(),
                recipe.getImageURL())
        );
        return 1;
    }

    @Override
    public List<Recipe> selectAllRecipes() {
        return DB;
    }

    @Override
    public Optional<Recipe> selectRecipeById(UUID id) {
        return DB.stream()
                .filter(recipe -> recipe.getRecipeId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteRecipeById(UUID id) {
        Optional<Recipe> recipeMyb = selectRecipeById(id);
        if(recipeMyb.isEmpty()) return 0;
        else {
            DB.remove(recipeMyb.get());
            return 1;
        }
    }

    @Override
    public int updateRecipeById(UUID id, Recipe recipe) {
        return selectRecipeById(id)
                .map(recipe1 -> {
                    int indexOfRecipe = DB.indexOf(recipe1);
                    if(indexOfRecipe >= 0) {
                        DB.set(indexOfRecipe, new Recipe(recipe.getRecipeId(),
                                                        recipe.getUserId(),
                                                        recipe.getName(),
                                                        recipe.getCategory(),
                                                        recipe.getIngredients(),
                                                        recipe.getInstructions(),
                                                        recipe.getOrigin(),
                                                        recipe.getTags(),
                                                        recipe.getVideoURL(),
                                                        recipe.getImageURL()));
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }
}
