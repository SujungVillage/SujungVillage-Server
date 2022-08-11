package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.announcement.AnnouncementDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.announcement.WriteAnnouncementRequest;
import com.sswu_2022swcontest.sujungvillage.entity.home.Announcement;
import com.sswu_2022swcontest.sujungvillage.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    // 공지사항 작성
    @PostMapping("/api/admin/announcement/writeAnnouncement")
    public AnnouncementDTO writeAnnouncement(
            @RequestBody WriteAnnouncementRequest body
    ){
        return announcementService.writeAnnouncement(
                body.getTitle(),
                body.getContent(),
                body.getDormitoryName());
    }

}
