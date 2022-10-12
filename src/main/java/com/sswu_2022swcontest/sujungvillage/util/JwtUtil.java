package com.sswu_2022swcontest.sujungvillage.util;

import com.sswu_2022swcontest.sujungvillage.config.PropertyConfig;
import com.sswu_2022swcontest.sujungvillage.entity.RefreshToken;
import com.sswu_2022swcontest.sujungvillage.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final PropertyConfig propertyConfig;
    private final RefreshTokenRepository refreshTokenRepository;

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

    @Transactional
    public String createRefreshToken(String userId) {

        // 유저정보가 들어있지 않은 refresh 토큰 생성
        Map<String, Object> jwtHeader = new HashMap<>();
        jwtHeader.put("typ", "JWT");
        jwtHeader.put("alg", "HS256");

        Map<String, Object> jwtPayload = new HashMap<>();

        Long expiredTime = 1000 * 60L * 60L * 24L * 7L; // 7일
        Date date = new Date();
        date.setTime(date.getTime() + expiredTime);

        Key key = Keys.hmacShaKeyFor(propertyConfig.getJwtKey().getBytes(StandardCharsets.UTF_8));

        String refreshToken = Jwts.builder()
                .setHeader(jwtHeader)
                .setClaims(jwtPayload)
                .setExpiration(date)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // refresh토큰과 userid를 refresh_token 테이블에 저장
        refreshTokenRepository.save(new RefreshToken(userId, refreshToken));

        return refreshToken;

    }
}
