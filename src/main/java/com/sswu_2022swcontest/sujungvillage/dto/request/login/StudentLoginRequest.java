package com.sswu_2022swcontest.sujungvillage.dto.request.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLoginRequest {

    private String access_token;

    private String fcm_token;

}
