package com.sswu_2022swcontest.sujungvillage.repository.qna;

import com.sswu_2022swcontest.sujungvillage.entity.qna.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    @Query(value = " SELECT * FROM faq ORDER BY reg_date DESC; "
            , nativeQuery = true)
    List<Faq> getAllFaq();
}
