package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.entity.home.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByDormitoryId(Integer id);
}
