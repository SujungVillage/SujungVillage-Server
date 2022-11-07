package com.sswu_2022swcontest.sujungvillage.dto.dto.resident;

import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.AppliedExeatDayDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.AppliedLongTermExeatDayDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.LongTermExeatDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.AppliedRollcallDayDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDayDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResidentHomeInfoDTO {

    private ResidentInfoDTO residentInfo;
    private List<RollcallDayDTO> rollcallDays;
    private List<AppliedRollcallDayDTO> appliedRollcallDays;
    private List<AppliedExeatDayDTO> appliedExeatDays;
    private List<AppliedLongTermExeatDayDTO> appliedLongTermExeatDays;

}
