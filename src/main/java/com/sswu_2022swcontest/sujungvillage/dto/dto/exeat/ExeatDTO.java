package com.sswu_2022swcontest.sujungvillage.dto.dto.exeat;

import com.sswu_2022swcontest.sujungvillage.entity.home.Exeat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExeatDTO {

    private Long id;
    private String userId;
    private String destination;
    private String reason;
    private String emergencyPhoneNumber;
    private LocalDate dateToUse;

    public static ExeatDTO entityToDTO(Exeat e){
        return new ExeatDTO(
                e.getId(),
                e.getUser().getId(),
                e.getDestination(),
                e.getReason(),
                e.getEmergencyPhoneNumber(),
                e.getDateToUse()
        );
    }

}
