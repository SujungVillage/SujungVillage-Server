package com.sswu_2022swcontest.sujungvillage.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.sswu_2022swcontest.sujungvillage.dto.dto.fcm.FcmMessage;
import com.sswu_2022swcontest.sujungvillage.repository.FcmRepository;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FcmService {

    private final String API_URL = "https://fcm.googleapis.com/v1/projects/sujungvillage-1660023039079/messages:send";
    private final ObjectMapper objectMapper;
    private final FcmRepository fcmRepo;

    // message 생성
    private String makeMessage(
            String targetToken,
            String title,
            String body
    ) throws JsonParseException, JsonProcessingException {

        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    // message 전송
    public void sendMessageTo(String targetToken, String title, String body) {

        try {

            String message = makeMessage(targetToken, title, body);

            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = RequestBody.create(message,
                    MediaType.get("application/json; charset=utf-8"));

            Request request = new Request.Builder()
                .url(API_URL)
                .post((okhttp3.RequestBody) requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

            Response response = client.newCall(request).execute();

        }catch (Exception e){
            System.out.println("메시지 전송 실패");
        }

    }

    // fcm 토큰 조회
    private String getAccessToken() throws IOException {

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream("/Users/kimyoojin/Desktop/sujungvillage/src/main/resources/firebase/firebase_service_key.json"))
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();

        return googleCredentials.getAccessToken().getTokenValue();

    }

    // user의 device 토큰 반환
    public String getDeviceToken(String userId){
        return fcmRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다 userId="+userId))
                .getFcmToken();
    }
}
