package com.sswu_2022swcontest.sujungvillage.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Administration {

    @GeneratedValue
    @Id
    @Column(name = "ADMINISTRATION_ID")
    private Long id;

    @OneToOne
    @JoinColumn (name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn (name = "DOMITORY_ID")
    private Domitory domitory;          // 기숙사 id

    private String description;     // 상세주소

    public Administration(Long id, User user, Domitory domitory, String description) {
        this.id = id;
        this.user = user;
        this.domitory = domitory;
        this.description = description;
    }
}
