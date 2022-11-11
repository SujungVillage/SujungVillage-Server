package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.entity.home.Exeat;
import com.sswu_2022swcontest.sujungvillage.entity.home.LongTermExeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public interface LongTermExeatRepository extends JpaRepository<LongTermExeat, Long> {

    @Query(value = " SELECT * FROM long_term_exeat WHERE user_id = ?1 " +
            "AND (YEAR(start_date) <= ?2 AND ?2 <= YEAR(end_date)) AND " +
            "(MONTH(start_date) <= ?3 AND ?3 <= MONTH(end_date)) ; ",
            nativeQuery = true)
    List<LongTermExeat> getLongTermExeats(String id, int year, int month);

    @Query(value = " SELECT Count(long_term_exeat_id) FROM long_term_exeat " +
            "WHERE user_id = ?1 AND DATE(start_date) <= ?2 AND DATE(end_date) >= ?2 ; ",
            nativeQuery = true)
    Integer checkOverlap(String id, LocalDate date);

}
