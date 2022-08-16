package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.entity.home.RollcallDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RollcallDateRepository extends JpaRepository<RollcallDate, Long> {

    @Query(value = " SELECT * FROM rollcall_date " +
            "WHERE YEAR(start_date_time) = ?1 AND MONTH(start_date_time) = ?2 AND (dormitory_id = 0 OR dormitory_id = ?3 ); ",
            nativeQuery = true)
    List<RollcallDate> getRollCallDays(int year, int month, Integer dormitoryId);

    @Query(value="SELECT * FROM rollcall_date WHERE DATE(end_date_time) = DATE( ?2 ) AND (dormitory_id = 0 OR dormitory_id = ?1 ) limit 1 ; "
            , nativeQuery = true)
    RollcallDate getTodayRollcall(Integer dormitoryId, LocalDateTime rdt);

}
