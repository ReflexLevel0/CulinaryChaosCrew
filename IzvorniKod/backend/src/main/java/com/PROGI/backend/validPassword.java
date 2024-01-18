package com.PROGI.backend;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class validPassword implements Predicate<String> {
    private static final Predicate<String> IS_PASSWORD_VALID = Pattern.compile(
            "^(?=.*[a-zA-Z0-9])(?=.*[!_?.]).{8,}$").asPredicate();

    @Override
    public boolean test(String password) {
        return IS_PASSWORD_VALID.test(password);
    }
}
