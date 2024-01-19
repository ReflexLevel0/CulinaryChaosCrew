package com.PROGI.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.net.URL;
import java.util.UUID;

public class RecipeLikeWrapper extends Recipe {
    @NonNull
    private final boolean likedByLoggedInUser;

    public RecipeLikeWrapper(@JsonProperty("rid") UUID rid,
                             @JsonProperty("uid") UUID uid,
                             @JsonProperty("name") String name,
                             @JsonProperty("category") String category,
                             @JsonProperty("ingr") String ingredients,
                             @JsonProperty("instr") String instructions,
                             @JsonProperty("origin") String origin,
                             @JsonProperty("tags") String tags,
                             @JsonProperty("iurl") URL imageURL,
                             @JsonProperty("vurl") URL videoURL,
                             @JsonProperty("preptime") int preparationTime,
                             @JsonProperty("likedByLoggedInUser") boolean likedByLoggedInUser) {
        super(rid, uid, name, category, ingredients, instructions, origin, tags, imageURL, videoURL, preparationTime);
        this.likedByLoggedInUser = likedByLoggedInUser;
    }

    public boolean getLikedByLoggedInUser() { return likedByLoggedInUser; }
}
