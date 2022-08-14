package com.sswu_2022swcontest.sujungvillage.dto.dto.announcement;

import com.sswu_2022swcontest.sujungvillage.entity.home.Announcement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SimpleAnnouncementDTO {

    private Long id;
    private String title;
    private String dormitoryName;
    private LocalDateTime regDate;

    public static SimpleAnnouncementDTO entityToDTO(Announcement e){
        return new SimpleAnnouncementDTO(
                e.getId(),
                e.getTitle(),
                e.getDormitory().getDormitoryName(),
                e.getRegDate()
        );
    }

}
