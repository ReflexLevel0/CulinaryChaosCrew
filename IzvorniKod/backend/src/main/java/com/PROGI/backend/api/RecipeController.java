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
    public List<RecipeLikeWrapper> getAllRecipes(@RequestParam("loggedInUserId") Optional<UUID> loggedInUserId, @RequestParam("authorId") Optional<UUID> authorId){
        return recipeService.getAllRecipes(loggedInUserId, authorId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{rid}")
    public RecipeLikeWrapper getRecipeById(@PathVariable("rid") UUID recipeId, @RequestParam("loggedInUserId") Optional<UUID> loggedInUserId) {
        return recipeService.getRecipeById(recipeId, loggedInUserId).orElse(null);
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

    @GetMapping(path = "search/{guess}")
    public List<RecipeLikeWrapper> searchRecipe(@NonNull @PathVariable("guess") String guess, @RequestParam("loggedInUserId") Optional<UUID> loggedInUserId){
        return recipeService.searchRecipe(guess, loggedInUserId);
    }

    @GetMapping(path = "category/{cat}")
    public List<RecipeLikeWrapper> getRecipesFromCategory(@NonNull @PathVariable("cat") String category, @RequestParam("loggedInUserId") Optional<UUID> loggedInUserId) {
        return recipeService.getRecipesFromCategory(category, loggedInUserId);
    }

}
