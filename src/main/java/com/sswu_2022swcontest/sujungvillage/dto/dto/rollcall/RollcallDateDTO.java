package com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall;

import com.sswu_2022swcontest.sujungvillage.entity.home.RollcallDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class RollcallDateDTO {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String dormitoryName;

    public static RollcallDateDTO entityToDTO(RollcallDate e){
        return new RollcallDateDTO(
                e.getId(),
                e.getStartDateTime(),
                e.getEndDateTime(),
                e.getDormitory().getDormitoryName()
        );
    }

}
