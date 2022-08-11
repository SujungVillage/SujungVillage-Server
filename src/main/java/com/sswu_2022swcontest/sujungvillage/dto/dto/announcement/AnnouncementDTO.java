package com.sswu_2022swcontest.sujungvillage.dto.dto.announcement;


import com.sswu_2022swcontest.sujungvillage.entity.home.Announcement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AnnouncementDTO {

    private Long id;
    private String writerId;
    private String title;
    private String content;
    private String DormitoryName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static AnnouncementDTO entityToDTO(Announcement e){
        return new AnnouncementDTO(
                e.getId(),
                e.getWriter().getId(),
                e.getTitle(),
                e.getContent(),
                e.getDormitory().getDormitoryName(),
                e.getRegDate(),
                e.getModDate()
        );
    }

}
