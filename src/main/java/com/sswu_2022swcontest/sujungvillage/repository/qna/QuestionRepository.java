package com.sswu_2022swcontest.sujungvillage.repository.qna;

import com.sswu_2022swcontest.sujungvillage.dto.dto.qna.SimpleQnaDTO;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.entity.qna.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * from question where writer_id = ?1 order by reg_date desc ;"
        , nativeQuery = true)
    List<Question> findByWriterId(String userId);

    @Query(value = "SELECT question.question_id, question.writer_id, question.title, " +
            "question.content, question.anonymous, question.reg_date, question.mod_date " +
            "FROM question LEFT JOIN answer ON question.question_id = answer.question_id " +
            "WHERE answer_id IS NULL; "
        ,nativeQuery = true)
    List<Question> getUnansweredQuestion();

    @Query(value = "SELECT * from question order by reg_date desc ;"
            , nativeQuery = true)
    List<Question> getAllQuestions();
}
