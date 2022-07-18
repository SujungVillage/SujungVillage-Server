package com.sswu_2022swcontest.sujungvillage.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Domitory {

    @GeneratedValue
    @Id
    private Long domitoryId;            // 기숙사 id

    private String domitoryName;        // 기숙사명

    private String address;             // 기숙사 주소

    public Domitory(Long domitoryId, String domitoryName, String address) {
        this.domitoryId = domitoryId;
        this.domitoryName = domitoryName;
        this.address = address;
    }
}
