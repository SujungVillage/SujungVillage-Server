package com.sswu_2022swcontest.sujungvillage.repository.qna;

import com.sswu_2022swcontest.sujungvillage.dto.dto.qna.SimpleQnaDTO;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.entity.qna.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * from question where writer_id = ?1 order by reg_date desc ;"
        , nativeQuery = true)
    List<Question> findByWriterId(String userId);
}
