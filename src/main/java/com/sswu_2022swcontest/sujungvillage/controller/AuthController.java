package com.sswu_2022swcontest.sujungvillage.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.sswu_2022swcontest.sujungvillage.config.PropertyConfig;
import com.sswu_2022swcontest.sujungvillage.dto.request.login.*;
import com.sswu_2022swcontest.sujungvillage.dto.response.login.AdminLoginResponse;
import com.sswu_2022swcontest.sujungvillage.dto.response.login.RefreshResponse;
import com.sswu_2022swcontest.sujungvillage.dto.response.login.StudentLoginResponse;
import com.sswu_2022swcontest.sujungvillage.service.UserService;
import com.sswu_2022swcontest.sujungvillage.util.JwtUtil;
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
    private final JwtUtil jwtUtil;

    @PostMapping("/api/student/login")
    public StudentLoginResponse studentLogin(
            @RequestBody StudentLoginRequest body
    )  throws GeneralSecurityException, IOException {

        // 아이디와 비밀번호 확인
        if (!userService.userLogin(body.getId(), body.getPassword())) {
            return null;
        }

        // fcm 토큰 저장
        if(body.getFcm_token() != null){

            userService.setFcmToken(body.getId(), body.getFcm_token());
        }else{
            System.out.println("fcm token이 존재하지 않음");
        }

        // jwt 토큰 생성
        String jwt = jwtUtil.createJwtToken(body.getId());

        // refresh 토큰 생성
        String rt = jwtUtil.createRefreshToken(body.getId());

        System.out.println("로그인성공 : "+body.getId());

        // 응답 반환
        return new StudentLoginResponse(jwt, rt);

    }


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
        String jwt = jwtUtil.createJwtToken(userId);

        String rt = jwtUtil.createRefreshToken(userId);

        System.out.println("로그인성공 : "+userId);

        // 응답 반환
        return new StudentLoginResponse(jwt, rt);

    }

    @PostMapping("/api/admin/login")
    public AdminLoginResponse adminLogin(
            @RequestBody AdminLoginRequest body
    ) throws GeneralSecurityException, IOException {

        // TODO: id, password 보안적용하기

        if (!userService.userLogin(body.getId(), body.getPassword())) {
            // TODO: 로그인에 실패했을 때 오류처리
            return null;
        }

        if(body.getFcm_token() != null){
            userService.setFcmToken(body.getId(), body.getFcm_token());
        }

        // jwt 토큰 생성
        String jwt = jwtUtil.createJwtToken(body.getId());

        // refresh 토큰 생성
        String rt = jwtUtil.createRefreshToken(body.getId());

        // 응답 반환
        return new AdminLoginResponse(jwt, rt);

    }

    @PostMapping("/api/student/signup")
    public String studentSignup(
            @RequestBody StudentSignupRequest body
    )  throws GeneralSecurityException, IOException {

        return userService.userSignup(
                body.getId(),
                body.getPassword(),
                body.getName(),
                body.getDormitoryName(),
                body.getDetailedAddress(),
                body.getPhoneNumber()
        );

    }

    @GetMapping("/api/common/isAvailableId")
    public String isAvailableId(
            @RequestParam String id
    ){
        return userService.isAvailableId(id).toString();
    }

    @GetMapping("/api/common/getFcmToken")
    public String getFcmToken(){
        return userService.getFcmToken();
    }

    @PostMapping("/api/common/refresh")
    public RefreshResponse refresh(
            @RequestBody RefreshRequest body
            ){

        String jwt = jwtUtil.refresh(body.getUserId(), body.getRefreshToken());

        if (jwt == null) {
            return null;
        }

        return new RefreshResponse(jwt);
    }

}
