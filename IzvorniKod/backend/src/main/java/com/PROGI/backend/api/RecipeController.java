package com.PROGI.backend.api;

import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.model.RecipeLikeWrapper;
import com.PROGI.backend.service.RecipeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/recipe")
@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "")
    public void addRecipe(@NonNull @RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "allRecipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "allRecipes/{uid}")
    public List<RecipeLikeWrapper> getAllRecipes(@PathVariable("uid") UUID userId){
        return recipeService.getAllRecipes(userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{rid}")
    public Recipe getRecipeById(@PathVariable("rid") UUID recipeId) {
        return recipeService.getRecipeById(recipeId).orElse(null);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "{rid}")
    public void deleteRecipeById(@PathVariable("rid") UUID id){
        recipeService.deleteRecipe(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "{rid}")
    public void updateRecipeById(@PathVariable("rid") UUID id, @NonNull @RequestBody Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "search")
    public List<Recipe> searchRecipe(@NonNull @RequestBody String guess){
        return recipeService.searchRecipe(guess);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "category/{cat}")
    public List<Recipe> getRecipesFromCategory(@NonNull @PathVariable("cat") String category) {
        return recipeService.getRecipesFromCategory(category);
    }

}
