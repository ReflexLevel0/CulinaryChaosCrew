package com.PROGI.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Follow {
    @NonNull
    private final UUID userId;

    @NonNull
    private final UUID followerId;

    public Follow(@NonNull UUID userId, @NonNull UUID followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    @NonNull
        public UUID getUserId() {
            return userId;
        }

        @NonNull
        public UUID getFollowerId() {
            return followerId;
        }
}
