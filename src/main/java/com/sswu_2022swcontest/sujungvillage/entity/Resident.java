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
    private String studentId;           // 학생의 학번이나 관리자의 id

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @MapsId
    @JoinColumn(name = "domitory_id")
    private Domitory domitory;          // 기숙사 id

    private String detailedAddress;     // 상세주소

    public Resident(String studentId, User user, Domitory domitory, String detailedAddress) {
        this.studentId = studentId;
        this.user = user;
        this.domitory = domitory;
        this.detailedAddress = detailedAddress;
    }


}
