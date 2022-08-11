package com.sswu_2022swcontest.sujungvillage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Value("${jwtkey}")
    private String jwtkey;

    @Value("${googleClientId}")
    private String googleClientId;

    public String getJwtKey(){
        return jwtkey;
    }

    public String getGoogleClientId() {
        return googleClientId;
    }
}
