package com.ssm.systemmeetmanagement.utils;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public abstract class Utilities {

    @Autowired
    private UserRepository userRepository;
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

    public static final String SECRET_KEY = System.getenv("SECRET_KEY");

    public static String generateToken(User user){

        Claims claims  = Jwts.claims().setSubject(user.getName());
        claims.put("userId", user.getId());
        claims.put("role", user.getRoles());
        claims.put("email", user.getEmail());

        return Jwts.builder().setClaims(claims)
                .signWith(getSigningKey(), SignatureAlgorithm.ES256).compact();
    }

    private static SecretKey getSigningKey(){
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
