package com.PROGI.backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class HashHelper {
    public static Optional<String> HashString(String value) {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(value.getBytes());
            String hashedString = new String(messageDigest.digest());
            byte[] var = hashedString.getBytes();
            StringBuilder finalString = new StringBuilder();
            for (int i = 0; i < var.length; i++) {
                int b = var[i];
                finalString.append(b);
            }
            return Optional.of(finalString.toString());
        }catch(NoSuchAlgorithmException ex){
            return Optional.ofNullable(null);
        }
    }
}
