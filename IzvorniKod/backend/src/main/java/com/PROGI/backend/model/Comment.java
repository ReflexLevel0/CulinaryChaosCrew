package com.PROGI.backend.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private final UUID userId;
    private final UUID recipeId;
    private Timestamp timestamp;
    private final String text;

    public Comment(UUID userId, UUID recipeId, String text){
        this.userId = userId;
        this.recipeId = recipeId;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.text = text;
    }

    public UUID getUserId(){
        return userId;
    }

    public UUID getRecipeId() {
        return recipeId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public void setDate(Timestamp timestamp){ this.timestamp = timestamp; }
}
