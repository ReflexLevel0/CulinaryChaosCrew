package progi.ccc.backend.service;

import progi.ccc.backend.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<Recipe> listAll();

    Recipe createRecipe(Recipe r);

    Optional<Recipe> findByRecipeID(long recipeID);

    Optional<Recipe> findByRecipeName(String recipeName);

    Recipe updateRecipe(Recipe recipe);

    Recipe fetch(long recipeID);
    Recipe deleteRecipe(long recipeID);

}
