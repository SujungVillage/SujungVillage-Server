package com.sswu_2022swcontest.sujungvillage.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.sswu_2022swcontest.sujungvillage.config.PropertyConfig;
import com.sswu_2022swcontest.sujungvillage.dto.request.login.StudentLoginRequest;
import com.sswu_2022swcontest.sujungvillage.dto.response.login.StudentLoginResponse;
import com.sswu_2022swcontest.sujungvillage.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PropertyConfig propertyConfig;

    @PostMapping("/api/student/login")
    public StudentLoginResponse studentLogin(
            @RequestBody StudentLoginRequest body
    ) throws GeneralSecurityException, IOException {

        // 토큰 무결성 인증
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(propertyConfig.getGoogleClientId()))
                .build();

        GoogleIdToken idToken = verifier.verify(body.getAccess_token());

        if (idToken == null) {
            // TODO: 유효하지 않은 accss token 오류 반환
            return null;
        }

        // 토큰에서 email 추출
        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();

        if (!email.endsWith("@sungshin.ac.kr")) {
            // TODO: 이메일 주소가 @sungshin.ac.kr로 끝나지 않으면 유효하지 않은 사용자 오류 반환
            return null;
        }

        String userId = email.substring(0, 8);

        if (!userService.isResident(userId)) {
            // TODO: 데베에 존재하는 Resident가 아니면 유효하지 않은 사용자 오류 반환
            return null;
        }

        // 구글 access token이 없을 때 jwt토큰을 얻기위한 임시코드
//        String userId = "20190959";
//        if (!userService.isResident(userId)) {
//            return null;
//        }
        // 구글 access token이 없을 때 jwt토큰을 얻기위한 임시코드코드 끝

        // jwt 토큰 생성
        Map<String, Object> jwtHeader = new HashMap<>();
        jwtHeader.put("typ", "JWT");
        jwtHeader.put("alg", "HS256");

        Map<String, Object> jwtPayload = new HashMap<>();
        jwtPayload.put("user_id", userId);

        Long expiredTime = 1000 * 60L * 60L * 24L;
        Date date = new Date();
        date.setTime(date.getTime() + expiredTime);

        Key key = Keys.hmacShaKeyFor(propertyConfig.getJwtKey().getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .setHeader(jwtHeader)
                .setClaims(jwtPayload)
                .setExpiration(date)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // 응답 반환
        return new StudentLoginResponse(jwt);

    }

}
