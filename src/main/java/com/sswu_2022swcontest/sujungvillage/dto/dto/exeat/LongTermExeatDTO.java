package com.sswu_2022swcontest.sujungvillage.dto.dto.exeat;

import com.sswu_2022swcontest.sujungvillage.entity.home.Exeat;
import com.sswu_2022swcontest.sujungvillage.entity.home.LongTermExeat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LongTermExeatDTO {

    private Long id;
    private String userId;
    private String destination;
    private String reason;
    private String emergencyPhoneNumber;
    private LocalDate startDate;
    private LocalDate endDate;

    public static LongTermExeatDTO entityToDTO(LongTermExeat e){
        return new LongTermExeatDTO(
                e.getId(),
                e.getUser().getId(),
                e.getDestination(),
                e.getReason(),
                e.getEmergencyPhoneNumber(),
                e.getStartDate(),
                e.getEndDate()
        );
    }

}
