package com.PROGI.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.net.URL;
import java.util.UUID;

public class Recipe {
    @NonNull
    private final UUID rid;
    @NonNull
    private final UUID uid;
    private final String name;
    private final String category;
    private final String ingredients;
    private final String instructions;
    private final String origin;
    private final String tags;
    private final URL imageURL;
    private final URL videoURL;
    private final int likes;
    private final int preparationTime;

    public Recipe(@JsonProperty("rid") UUID rid,
                  @JsonProperty("uid") UUID uid,
                  @JsonProperty("name") String name,
                  @JsonProperty("category") String category,
                  @JsonProperty("ingr") String ingredients,
                  @JsonProperty("instr") String instructions,
                  @JsonProperty("origin") String origin,
                  @JsonProperty("tags") String tags,
                  @JsonProperty("iurl") URL imageURL,
                  @JsonProperty("vurl") URL videoURL,
                  @JsonProperty("preptime") int preparationTime) {
        this.rid = rid;
        this.uid = uid;
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.origin = origin;
        this.tags = tags;
        this.videoURL = videoURL;
        this.imageURL = imageURL;
        this.likes = 0;
        this.preparationTime = preparationTime;
    }

    public UUID getUid() {
        return uid;
    }

    public UUID getRid() {
        return rid;
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
    public int getPreparationTime() { return preparationTime; }
}
