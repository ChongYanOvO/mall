package com.mars.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

//@Slf4j
public class JwtUtil {
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;
    private static final String SUBJECT = "User";
    private static final String SECRET = "mars";

    public static String createToken(Map<String, Object> claims) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String JwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .setClaims(claims)
                .setSubject(SUBJECT)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return JwtToken;
    }

    public static Map<String, Object> parseJwt(String token) {
        try {
            Map<String, Object> tokenClaims =
                    Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return tokenClaims;
        } catch (Exception e) {
//            log.error("token错误!");
            return null;
        }
    }

    public static boolean checkToken(String token) {
        if (token == null) {
            return false;
        } else {
            try {
                Map<String, Object> tokenClaims = parseJwt(token);
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            } catch (UnsupportedJwtException e) {
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                e.printStackTrace();
            } catch (SignatureException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}