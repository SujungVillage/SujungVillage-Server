package com.sswu_2022swcontest.sujungvillage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SujungvillageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SujungvillageApplication.class, args);
    }

}
