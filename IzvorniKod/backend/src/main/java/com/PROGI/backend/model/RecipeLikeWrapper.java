package com.PROGI.backend.model;

import org.springframework.lang.NonNull;

import java.net.URL;
import java.util.UUID;

public class RecipeLikeWrapper extends Recipe {
    @NonNull
    private final boolean likedByLoggedInUser;

    public RecipeLikeWrapper(UUID recipeId, UUID userId, String name, String category, String ingredients, String instructions, String origin, String tags, URL imageURL, URL videoURL, int preparationTime, boolean likedByLoggedInUser) {
        super(recipeId, userId, name, category, ingredients, instructions, origin, tags, imageURL, videoURL, preparationTime);
        this.likedByLoggedInUser = likedByLoggedInUser;
    }

    public boolean getLikedByLoggedInUser() { return likedByLoggedInUser; }
}
