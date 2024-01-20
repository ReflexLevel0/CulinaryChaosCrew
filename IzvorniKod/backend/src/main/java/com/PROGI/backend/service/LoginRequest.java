package com.PROGI.backend.service;

import com.PROGI.backend.HashHelper;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class LoginRequest {
    @NonNull
    private final String username;
    @NonNull
    private final String password;

    public LoginRequest(@JsonProperty("username") String username,
                        @JsonProperty("password") String password) {
        this.username = username;
        this.password = HashHelper.HashString(password).get();
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}
