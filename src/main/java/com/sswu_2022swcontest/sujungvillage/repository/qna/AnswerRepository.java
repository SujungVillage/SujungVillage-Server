package com.sswu_2022swcontest.sujungvillage.repository.qna;

import com.sswu_2022swcontest.sujungvillage.entity.qna.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT EXISTS(SELECT * FROM answer WHERE question_id = ?1 limit 1)"
            , nativeQuery = true)
    int isAnswered(Long questionId);

    Optional<Answer> findByQuestionId(Long questionId);
}
