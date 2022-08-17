package com.sswu_2022swcontest.sujungvillage.dto.request.rollcall;

import lombok.Getter;

import java.util.List;

@Getter
public class ChangeRollcallStateRequest {

    List<Long> rollcallIds;
    String state;

}

