package com.sswu_2022swcontest.sujungvillage.dto.request.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshRequest {

    private String userId;
    private String refreshToken;

}
