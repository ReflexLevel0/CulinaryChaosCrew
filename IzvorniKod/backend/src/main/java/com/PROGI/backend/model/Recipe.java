package com.PROGI.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;

import java.net.URL;
import java.util.UUID;

public class Recipe {
    @NonNull
    private final UUID recipeId;
    @NonNull
    private final UUID userId;
    private final String name;
    private final String category;
    private final String ingredients;
    private final String instructions;
    private final String origin;
    private final String tags;
    private final URL imageURL;
    private final URL videoURL;
    private final int likes;

    public Recipe(@JsonProperty("rid") UUID recipeId,
                  @JsonProperty("uid") UUID userId,
                  @JsonProperty("name") String name,
                  @JsonProperty("category") String category,
                  @JsonProperty("ingr") String ingredients,
                  @JsonProperty("instr") String instructions,
                  @JsonProperty("origin") String origin,
                  @JsonProperty("tags") String tags,
                  @JsonProperty("iurl") URL imageURL,
                  @JsonProperty("vurl") URL videoURL) {
        this.recipeId = recipeId;
        this.userId = userId;
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.origin = origin;
        this.tags = tags;
        this.videoURL = videoURL;
        this.imageURL = imageURL;
        this.likes = 0;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public URL getVideoURL() {
        return videoURL;
    }

    public String getCategory() {
        return category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTags() {
        return tags;
    }

    public int getLikes() {
        return likes;
    }
}
