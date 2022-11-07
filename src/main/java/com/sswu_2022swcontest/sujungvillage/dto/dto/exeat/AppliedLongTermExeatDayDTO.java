package com.sswu_2022swcontest.sujungvillage.dto.dto.exeat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliedLongTermExeatDayDTO {

    private long id;
    private LocalDate startDate;
    private LocalDate endDate;

}
