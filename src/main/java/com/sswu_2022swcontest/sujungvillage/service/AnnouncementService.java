package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.announcement.AnnouncementDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.announcement.SimpleAnnouncementDTO;
import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.home.Announcement;
import com.sswu_2022swcontest.sujungvillage.repository.DormitoryRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository annoRepo;
    private final DormitoryRepository dormitoryRepo;
    private final UserService userService;

    // 공지사항 작성
    public AnnouncementDTO writeAnnouncement(String title, String content, String dormitoryName) {

        Dormitory dormitory = dormitoryRepo.findByDormitoryName(dormitoryName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기숙사명, 기숙사명="+dormitoryName));

        return AnnouncementDTO.entityToDTO(
                annoRepo.save(new Announcement(
                        null,
                        userService.getUser(),
                        title,
                        content,
                        dormitory,
                        null,
                        null
                ))
        );

    }

    // 공지사항 제목 리스트 조회
    public List<SimpleAnnouncementDTO> getAnnouncementTitles(String dormitoryName) {

        Dormitory dormitory = dormitoryRepo.findByDormitoryName(dormitoryName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기숙사명, 기숙사명="+dormitoryName));

        if (dormitoryName.equals("전체")) {
            return annoRepo.getAllAnnouncement()
                    .stream()
                    .map(anno -> {
                        return SimpleAnnouncementDTO.entityToDTO(anno);
                    }).collect(Collectors.toList());
        }

        return annoRepo.getAllAnnouncementByDormitoryId(dormitory.getId())
                .stream()
                .map(anno -> {
                    return SimpleAnnouncementDTO.entityToDTO(anno);
                }).collect(Collectors.toList());

    }

    // 공지사항 상세조회
    public AnnouncementDTO getAnnouncement(Long announcementId) {

        Announcement announcement = annoRepo.findById(announcementId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 공지사항 id, announcementId="+announcementId));

        return AnnouncementDTO.entityToDTO(announcement);

    }
}
