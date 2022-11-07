package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.entity.home.Exeat;
import com.sswu_2022swcontest.sujungvillage.entity.home.LongTermExeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface LongTermExeatRepository extends JpaRepository<LongTermExeat, Long> {

    @Query(value = " SELECT * FROM long_term_exeat WHERE user_id = ?1 AND YEAR(start_date) = ?2 AND MONTH(start_date) = ?3 ; ",
            nativeQuery = true)
    List<LongTermExeat> getLongTermExeats(String id, int year, int month);
}
