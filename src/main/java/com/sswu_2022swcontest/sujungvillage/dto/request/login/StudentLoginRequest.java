package com.sswu_2022swcontest.sujungvillage.dto.request.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLoginRequest {

    private String id;
    private String password;
    private String fcm_token;

}
