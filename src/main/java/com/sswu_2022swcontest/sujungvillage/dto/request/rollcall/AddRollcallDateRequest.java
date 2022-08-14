package com.sswu_2022swcontest.sujungvillage.dto.request.rollcall;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AddRollcallDateRequest {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String dormitoryName;

}
