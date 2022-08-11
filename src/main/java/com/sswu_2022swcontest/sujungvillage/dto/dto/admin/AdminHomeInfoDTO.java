package com.sswu_2022swcontest.sujungvillage.dto.dto.admin;

import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.ResidentInfoDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDayDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AdminHomeInfoDTO {

    private AdminInfoDTO adminInfoDTO;
    private List<RollcallDayDTO> rollcallDays;

}
