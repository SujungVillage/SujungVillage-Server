package com.sswu_2022swcontest.sujungvillage.repository.qna;

import com.sswu_2022swcontest.sujungvillage.entity.qna.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
