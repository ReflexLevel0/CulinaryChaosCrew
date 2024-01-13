package com.PROGI.backend.exceptions;

import java.util.UUID;

public class RecipeNotFound extends Exception {
    public RecipeNotFound(UUID recipeId){
        super(String.format("Recipe with ID '%s' not found", recipeId.toString()));
    }
}
