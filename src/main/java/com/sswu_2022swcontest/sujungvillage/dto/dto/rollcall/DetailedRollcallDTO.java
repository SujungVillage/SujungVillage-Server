package com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall;

import com.sswu_2022swcontest.sujungvillage.entity.home.Rollcall;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DetailedRollcallDTO {

    private Long id;
    private String userId;
    private String userName;
    private String dormitoryName;
    private String detailedAddress;
    private String imageURL;
    private String location;
    private LocalDateTime rollcallDateTime;
    private String state;

    public static DetailedRollcallDTO entityToDTO(Rollcall e){
        return new DetailedRollcallDTO(
                e.getId(),
                e.getUser().getId(),
                e.getUser().getName(),
                e.getUser().getDormitory().getDormitoryName(),
                e.getUser().getDetailedAddress(),
                e.getImageURL(),
                e.getLocation(),
                e.getRollcallTime(),
                e.getState()
        );
    }
}
