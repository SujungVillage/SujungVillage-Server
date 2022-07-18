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
    private Long administrationId;

    @ManyToOne
    @JoinColumn(name = "domitory_id")
    private Domitory domitory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String description;     // 상세주소

    public Administration(Long administrationId, Domitory domitory, User user, String description) {
        this.administrationId = administrationId;
        this.domitory = domitory;
        this.user = user;
        this.description = description;
    }


}
