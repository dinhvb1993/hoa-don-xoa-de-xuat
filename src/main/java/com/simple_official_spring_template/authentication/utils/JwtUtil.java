package com.simple_official_spring_template.authentication.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String REDIS_SET_ACTIVE_SUBJECTS = "bk";
    private static final String signingKey = "signingKeyDinhVBQNginanduyminhkeynayduocsinhratudovietnhanh";

    public static String generateToken(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 3 * 24 * 3600 * 1000))
                .signWith(SignatureAlgorithm.HS256, signingKey);

        String token = builder.compact();

        RedisUtil.INSTANCE.sadd(REDIS_SET_ACTIVE_SUBJECTS, subject);

        return token;
    }

    public static String parseToken(String token){
        if(token == null) {
            return null;
        }

        String subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        if (!RedisUtil.INSTANCE.sismember(REDIS_SET_ACTIVE_SUBJECTS, subject)) {
            return null;
        }

        return subject;
    }

    public static void invalidateRelatedTokens(String token) {
        RedisUtil.INSTANCE.srem(REDIS_SET_ACTIVE_SUBJECTS, parseToken(token));
    }
}

