package com.PROGI.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Like {
    @NonNull
    private final UUID userId;

    @NonNull
    private final UUID recipeId;

    public Like(@JsonProperty("uid") UUID userId, @JsonProperty("rid") UUID recipeId){
        this.userId = userId;
        this.recipeId = recipeId;
    }

    @NonNull
    public UUID getUserId() {
        return userId;
    }

    @NonNull
    public UUID getRecipeId() {
        return recipeId;
    }
}
