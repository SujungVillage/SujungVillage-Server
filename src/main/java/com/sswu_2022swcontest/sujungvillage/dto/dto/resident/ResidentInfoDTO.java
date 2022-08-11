package com.sswu_2022swcontest.sujungvillage.dto.dto.resident;

import com.sswu_2022swcontest.sujungvillage.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResidentInfoDTO {

    private String name;
    private String dormitoryName;
    private String detailedAddress;
    private int plusLMP;
    private int minusLMP;

}
