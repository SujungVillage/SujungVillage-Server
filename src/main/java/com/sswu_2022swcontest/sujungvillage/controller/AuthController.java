package com.sswu_2022swcontest.sujungvillage.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.sswu_2022swcontest.sujungvillage.config.PropertyConfig;
import com.sswu_2022swcontest.sujungvillage.dto.request.login.AdminLoginRequest;
import com.sswu_2022swcontest.sujungvillage.dto.request.login.StudentGoogleLoginRequest;
import com.sswu_2022swcontest.sujungvillage.dto.response.login.AdminLoginResponse;
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
import java.util.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PropertyConfig propertyConfig;

    @PostMapping("/api/student/google_login")
    public StudentLoginResponse studentGoogleLogin(
            @RequestBody StudentGoogleLoginRequest body
    ) throws GeneralSecurityException, IOException {

        // 토큰 무결성 인증
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(transport, jsonFactory)
                .setAudience(Arrays.asList(
                        propertyConfig.getGoogleClientId1(),
                        propertyConfig.getGoogleClientId2(),
                        propertyConfig.getGoogleClientId3(),
                        propertyConfig.getGoogleClientId4(),
                        propertyConfig.getGoogleClientId5(),
                        propertyConfig.getGoogleClientId6()
                ))
                .build();

        GoogleIdToken idToken = verifier.verify(body.getAccess_token());

        if (idToken == null) {
            // TODO: 유효하지 않은 accss token 오류 반환
            System.out.println("google access token이 유효하지 않음");
            return null;
        }

        // 토큰에서 email 추출
        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();

        if (!email.endsWith("@sungshin.ac.kr")) {
            // TODO: 이메일 주소가 @sungshin.ac.kr로 끝나지 않으면 유효하지 않은 사용자 오류 반환
            System.out.println("사용자의 이메일주소가 @sungshin.ac.kr로 끝나지 않음");
            return null;
        }

        String userId = email.substring(0, 8);

        if (!userService.isResident(userId)) {
            // TODO: 데베에 존재하는 Resident가 아니면 유효하지 않은 사용자 오류 반환
            System.out.println("데베에 존재하지 않는 사용자임");
            return null;
        }

        // 구글 access token이 없을 때 jwt토큰을 얻기위한 임시코드
//        String userId = "20190959";
//        if (!userService.isResident(userId)) {
//            return null;
//        }
        // 구글 access token이 없을 때 jwt토큰을 얻기위한 임시코드코드 끝

        // fcm 토큰 설정하기
        if(body.getFcm_token() != null){

            userService.setFcmToken(userId, body.getFcm_token());
        }else{
            System.out.println("fcm token이 존재하지 않음");
        }

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

        System.out.println("로그인성공 : "+userId);

        // 응답 반환
        return new StudentLoginResponse(jwt);

    }

    @PostMapping("/api/admin/login")
    public AdminLoginResponse adminLogin(
            @RequestBody AdminLoginRequest body
    ) throws GeneralSecurityException, IOException {

        // TODO: id, password 보안적용하기

        if (!userService.adminLogin(body.getId(), body.getPassword())) {
            // TODO: 로그인에 실패했을 때 오류처리
            return null;
        }

        if(body.getFcm_token() != null){
            userService.setFcmToken(body.getId(), body.getFcm_token());
        }

        // jwt 토큰 생성
        Map<String, Object> jwtHeader = new HashMap<>();
        jwtHeader.put("typ", "JWT");
        jwtHeader.put("alg", "HS256");

        Map<String, Object> jwtPayload = new HashMap<>();
        jwtPayload.put("user_id", body.getId());

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
        return new AdminLoginResponse(jwt);

    }

    @GetMapping("/api/common/getFcmToken")
    public String getFcmToken(){
        return userService.getFcmToken();
    }

}
