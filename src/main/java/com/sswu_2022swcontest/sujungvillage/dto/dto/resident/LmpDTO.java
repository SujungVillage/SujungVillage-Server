package com.sswu_2022swcontest.sujungvillage.dto.dto.resident;

import com.sswu_2022swcontest.sujungvillage.entity.home.LivingMannerPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LmpDTO {

    private Long id;
    private String userId;
    private Short score;
    private String reason;
    private LocalDateTime regDate;

    public static LmpDTO entityToDTO(LivingMannerPoint e){
        return new LmpDTO(
                e.getId(),
                e.getUser().getId(),
                e.getScore(),
                e.getReason(),
                e.getRegDate()
        );
    }

}

