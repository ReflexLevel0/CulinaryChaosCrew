package com.PROGI.backend.model;

import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return age == profile.age && userId.equals(profile.userId) && username.equals(profile.username) && password.equals(profile.password) && email.equals(profile.email) && Objects.equals(name, profile.name) && Objects.equals(surname, profile.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, email, name, surname, age);
    }
}
