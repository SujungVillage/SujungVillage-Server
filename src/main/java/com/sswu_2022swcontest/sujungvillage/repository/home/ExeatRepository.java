package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.AppliedExeatDayDTO;
import com.sswu_2022swcontest.sujungvillage.entity.home.Exeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface ExeatRepository extends JpaRepository<Exeat, Long> {

    @Query(value = " SELECT * FROM exeat WHERE user_id = ?1 AND YEAR(date_to_use) = ?2 AND MONTH(date_to_use) = ?3 ; ",
            nativeQuery = true)
    List<Exeat> getAppliedExeatlDays(String userId, int year, int month);
}
