package com.sswu_2022swcontest.sujungvillage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {

    @Id
    @Column(name = "dormitory_id")
    private Integer id;

    private String dormitoryName;

    private String address;

}
