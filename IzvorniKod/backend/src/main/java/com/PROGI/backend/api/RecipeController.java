package com.PROGI.backend.api;

import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.List;
import java.util.UUID;

@RequestMapping("/recipes")
@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(path = "add")
    public void addRecipe(@NonNull @RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "allRecipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping(path = "get/{rid}")
    public Recipe getRecipeById(@PathVariable("rid") UUID id) {
        return recipeService.getRecipeById(id).orElse(null);
    }

    @DeleteMapping(path = "delete/{rid}")
    public void deleteRecipeById(@PathVariable("rid") UUID id){
        recipeService.deleteRecipe(id);
    }

    @PutMapping(path = "update/{rid}")
    public void updateRecipeById(@PathVariable("rid") UUID id, @NonNull @RequestBody Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
    }


}
