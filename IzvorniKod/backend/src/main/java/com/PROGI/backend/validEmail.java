package com.PROGI.backend;

import com.PROGI.backend.dao.ProfileDao;
import com.PROGI.backend.model.Profile;
import com.PROGI.backend.service.ProfileService;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

// This class is used to check if a username is available
public class validEmail implements Predicate<String> {
        private static final Predicate<String> IS_EMAIL_VALID = Pattern.compile(
                        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                        Pattern.CASE_INSENSITIVE).asPredicate();
        @Override
        public boolean test(String email) {
            return IS_EMAIL_VALID.test(email);
        }
}
