package com.sswu_2022swcontest.sujungvillage.util;

import com.sswu_2022swcontest.sujungvillage.config.PropertyConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final PropertyConfig propertyConfig;

    public String createJwtToken(String userId) {

        Map<String, Object> jwtHeader = new HashMap<>();
        jwtHeader.put("typ", "JWT");
        jwtHeader.put("alg", "HS256");

        Map<String, Object> jwtPayload = new HashMap<>();
        jwtPayload.put("user_id", userId);

        Long expiredTime = 1000 * 60L * 60L * 24L; // 24시간
        Date date = new Date();
        date.setTime(date.getTime() + expiredTime);

        Key key = Keys.hmacShaKeyFor(propertyConfig.getJwtKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setHeader(jwtHeader)
                .setClaims(jwtPayload)
                .setExpiration(date)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }
}
