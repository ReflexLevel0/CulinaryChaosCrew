package com.PROGI.backend.model;

import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Profile {
    @NonNull
    private final UUID userId;
    @NonNull
    private final String username;
    @NonNull
    private final String password;
    @NonNull
    private final String email;
    private final String name;
    private final String surname;
    private final int age;

    public Profile(@JsonProperty("uid") UUID userId,
                   @JsonProperty("username") String username,
                   @JsonProperty("password") String password,
                   @JsonProperty("email")String email,
                   @JsonProperty("name")String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("age") int age) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @NonNull
    public UUID getUserId() {
        return userId;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
}
