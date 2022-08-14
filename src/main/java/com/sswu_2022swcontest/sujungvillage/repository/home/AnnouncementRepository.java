package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.entity.home.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Query(value = "SELECT * FROM announcement ORDER BY reg_date DESC ;"
            , nativeQuery = true)
    List<Announcement> getAllAnnouncement();

    @Query(value = "SELECT * FROM announcement WHERE dormitory_id = ?1 ORDER BY reg_date DESC ;"
            , nativeQuery = true)
    List<Announcement> getAllAnnouncementByDormitoryId(Integer DormitoryId);

}
