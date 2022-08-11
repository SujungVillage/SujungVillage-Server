package com.sswu_2022swcontest.sujungvillage.dto.request.rollcall;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRollcallDateRequest {

    public String date;
    public String startTime;
    public String endTime;
    public String dormitoryName;

}
