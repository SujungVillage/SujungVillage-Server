package com.sswu_2022swcontest.sujungvillage.dto.request.exeat;

import lombok.Getter;

import java.util.List;

@Getter
public class AddLmpRequest {

    List<String> residentList;
    Short score;
    String reason;

}
