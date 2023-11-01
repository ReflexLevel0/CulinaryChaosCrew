package progi.ccc.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import progi.ccc.backend.dao.RecipeRepository;
import progi.ccc.backend.domain.Profile;
import progi.ccc.backend.domain.Recipe;
import progi.ccc.backend.service.EntityMissingException;
import progi.ccc.backend.service.RecipeService;

import java.util.List;
import java.util.Optional;

public class RecipeServiceJpa implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepo;
    @Override
    public List<Recipe> listAll() {
        return recipeRepo.findAll();
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        validate(recipe);
        Assert.isNull(recipe.getRecipeID(), "Recipe ID must be null, not: " + recipe.getRecipeID());
        return recipeRepo.save(recipe);
    }

    @Override
    public Optional<Recipe> findByRecipeID(long recipeID) {
        return recipeRepo.findById(recipeID);
    }

    @Override
    public Optional<Recipe> findByRecipeName(String recipeName) {
        return recipeRepo.findByRecipeName(recipeName);
    }

 /*   @Override
    public Optional<List<Recipe>> findByRecipesByUserID(String userID) {
        return recipeRepo.findByUserID(userID);
    }
    Izbacuje grešku, cilj je kreirati cijelu listu recepata pojedinog korisnika
  */

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        validate(recipe);
        Long recipeID = recipe.getRecipeID();
        if (!recipeRepo.existsById(recipeID))
            throw new EntityMissingException(Profile.class, recipeID);
        return recipeRepo.save(recipe);
    }

    @Override
    public Recipe fetch(long recipeID) {
        return findByRecipeID(recipeID).orElseThrow(
                () -> new EntityMissingException(Profile.class, recipeID)
        );
    }

    @Override
    public Recipe deleteRecipe(long recipeID) {
        Recipe recipe = fetch(recipeID);
        recipeRepo.delete(recipe);
        return recipe;
    }

    private void validate(Recipe recipe) {
        Assert.notNull(recipe, "Profile object must be given");
        String recipeName = recipe.getRecipeName();
        Assert.hasText(recipeName, "Username must be given");
    }

}
