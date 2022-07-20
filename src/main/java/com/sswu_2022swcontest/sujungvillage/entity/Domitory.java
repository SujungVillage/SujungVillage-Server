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
    @Column (name = "DOMITORY_ID")
    private Long id;            // 기숙사 id

    private String domitoryName;        // 기숙사명

    private String address;             // 기숙사 주소

    public Domitory(Long id, String domitoryName, String address) {
        this.id = id;
        this.domitoryName = domitoryName;
        this.address = address;
    }
}
