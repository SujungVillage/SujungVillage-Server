package com.sswu_2022swcontest.sujungvillage.dto.request.exeat;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ApplyLongTermExeatRequest {

    public String destination;
    public String reason;
    public String emergencyPhoneNumber;
    public LocalDate startDate;
    public LocalDate endDate;

}
