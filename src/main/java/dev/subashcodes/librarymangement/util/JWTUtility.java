package dev.subashcodes.librarymangement.util;

import dev.subashcodes.librarymangement.service.MyCustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtility {


    @Autowired
    MyCustomUserDetailsService myUserDetails;

    private SecretKey secretKey;

    private static final String secret = "thisIsMysecregtfrdesww233eggtffeeddgkjjhhtdhttebd54ndhdhfhhhshs8877465sbbdd"; // Example secret

    private void ensureSecretKeyInitialized() {
        if (this.secretKey == null) {
            this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        }
    }

    //this method is used to generate JWT token
    public String generateToken(String username){
        ensureSecretKeyInitialized();
        return Jwts.builder()
                .setSubject(username) //subject refers to the username
                .setIssuedAt(new Date())  //current time
                .setExpiration(new Date((new Date()).getTime() + 1000 * 60 * 60)) //1 hour expiry
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean validateToken(String token){
        try {
            String username = extractUsername(token);
            if (username == null) return false;

            UserDetails userDetails = myUserDetails.loadUserByUsername(username);

            return userDetails.getUsername().equals(username) && !checkTokenExpiry(token);
        } catch (JwtException | IllegalArgumentException ex) {
            // Token invalid or parsing failed
            return false;
        }
    }


    //headers : Authorization: Bearer <token>
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        String raw = normalizeToken(token);
        if (raw == null || raw.isEmpty()) {
            throw new IllegalArgumentException("JWT token is null or empty");
        }
        ensureSecretKeyInitialized();
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(raw)
                .getBody();
    }

    private String normalizeToken(String token) {
        if (token == null) return null;
        token = token.trim();
        if (token.startsWith("Bearer ")) {
            return token.substring(7).trim();
        }
        return token;
    }


    public  boolean checkTokenExpiry(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
