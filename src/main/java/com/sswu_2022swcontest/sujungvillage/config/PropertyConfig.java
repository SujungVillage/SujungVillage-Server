package com.sswu_2022swcontest.sujungvillage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfig {

    @Value("${jwtkey}")
    private String jwtkey;

    @Value("${googleClientId1}")
    private String googleClientId1;

    @Value("${googleClientId2}")
    private String googleClientId2;

    @Value("${googleClientId3}")
    private String googleClientId3;

    @Value("${googleClientId5}")
    private String googleClientId5;

    @Value("${googleClientId4}")
    private String googleClientId4;

    @Value("${googleClientId6}")
    private String googleClientId6;

    public String getJwtKey(){
        return jwtkey;
    }

    public String getGoogleClientId1() {
        return googleClientId1;
    }

    public String getGoogleClientId2() {
        return googleClientId2;
    }

    public String getGoogleClientId3() {
        return googleClientId3;
    }

    public String getGoogleClientId4() {
        return googleClientId4;
    }

    public String getGoogleClientId5() {
        return googleClientId5;
    }

    public String getGoogleClientId6() {
        return googleClientId6;
    }

}
