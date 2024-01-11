package com.PROGI.backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class HashHelper {
    public static Optional<String> HashString(String value) {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(value.getBytes());
            return Optional.of(new String(messageDigest.digest()));
        }catch(NoSuchAlgorithmException ex){
            return Optional.ofNullable(null);
        }
    }
}
