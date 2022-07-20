package com.sswu_2022swcontest.sujungvillage.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resident {

    @Id
    @GeneratedValue
    private Long id;           // 재사생 id

    @OneToOne
    @JoinColumn (name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn (name = "DOMITORY_ID")
    private Domitory domitory;          // 기숙사 id

    private String detailedAddress;     // 상세주소

    public Resident(Long id, User user, Domitory domitory, String detailedAddress) {
        this.id = id;
        this.user = user;
        this.domitory = domitory;
        this.detailedAddress = detailedAddress;
    }


}
