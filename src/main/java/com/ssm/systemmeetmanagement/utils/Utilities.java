package com.ssm.systemmeetmanagement.utils;
import java.security.SecureRandom;

public abstract class Utilities {
    private Utilities() {
        throw new IllegalStateException("Utility class");
    }
    public static String generateRandomPassword()
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }
}
