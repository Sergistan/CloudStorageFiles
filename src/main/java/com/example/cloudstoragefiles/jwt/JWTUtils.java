package com.example.cloudstoragefiles.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.cloudstoragefiles.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 64 * 24))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

//    public String generateToken(User user) {
//        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
//
//        return JWT.create()
//                .withSubject("User details")
//                .withClaim("email", user.getEmail())
//                .withClaim("password", user.getPassword())
//                .withIssuedAt(new Date())
//                .withIssuer("CloudStorageFiles")
//                .withExpiresAt(expirationDate)
//                .sign(Algorithm.HMAC256(jwtSecret));
//    }
//
//    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret))
//                .withSubject("User details")
//                .withIssuer("CloudStorageFiles")
//                .build();
//
//        DecodedJWT jwt = verifier.verify(token);
//        return jwt.getClaim("email").asString();
//    }

}
