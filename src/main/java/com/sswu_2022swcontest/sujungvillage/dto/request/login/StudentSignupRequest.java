package com.sswu_2022swcontest.sujungvillage.dto.request.login;

import lombok.Getter;

@Getter
public class StudentSignupRequest {

    private String id;
    private String password;
    private String name;
    private String dormitoryName;
    private String detailedAddress;
    private String phoneNumber;

}
