package com.PROGI.backend.exceptions;

import java.util.UUID;

public class ProfileNotFound extends Exception {
    public ProfileNotFound(UUID userId){
        super(String.format("Profile with ID '%s' not found", userId.toString()));
    }
}
