package com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall;

import com.sswu_2022swcontest.sujungvillage.entity.home.Rollcall;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class RollcallDTO {

    private Long id;
    private String userId;
    private Byte[] image;
    private String location;
    private LocalDateTime rollcallDateTime;
    private String state;

    public static RollcallDTO entityToDTO(Rollcall e){
        return new RollcallDTO(
                e.getId(),
                e.getUser().getId(),
                e.getImage(),
                e.getLocation(),
                e.getRollcallTime(),
                e.getState()
        );
    }

}