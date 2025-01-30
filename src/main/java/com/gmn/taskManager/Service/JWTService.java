package com.gmn.taskManager.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    public JWTService() {
        System.out.println("secret key "+secretKey +"========================");
    }

    private final int jwtExpirationMs = 30000;

    public String generateToken(String username) {
        System.out.println(secretKey);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(secretKey).compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs*60))
                .signWith(secretKey)
                .compact();
    }

    public String extractUserName(String token) {

        return extractClaim(token, Claims::getSubject);
    }

//    public String extractPassword(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

    private <T> T extractClaim(String token, Function<Claims ,T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey).setAllowedClockSkewSeconds(60000)
                .build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUserName(token);

        return (username.equals(userDetails.getUsername())&& ! isTokenExpired(token));

    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
