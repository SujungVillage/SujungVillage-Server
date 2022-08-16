package com.sswu_2022swcontest.sujungvillage.dto.request.rollcall;

import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallIdAndState;
import lombok.Getter;

import java.util.List;

@Getter
public class ChangeRollcallStateRequest {

    List<RollcallIdAndState> rollcallIdAndStates;

}

