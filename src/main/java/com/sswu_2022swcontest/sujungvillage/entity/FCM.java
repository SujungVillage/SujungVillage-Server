package com.sswu_2022swcontest.sujungvillage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FCM {

    @Id
    private String userId;

    private String fcmToken;

}
