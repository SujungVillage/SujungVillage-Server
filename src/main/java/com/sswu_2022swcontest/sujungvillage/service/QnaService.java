package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.repository.qna.AnswerRepository;
import com.sswu_2022swcontest.sujungvillage.repository.qna.FaqRepository;
import com.sswu_2022swcontest.sujungvillage.repository.qna.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final FaqRepository faqRepo;
    private final QuestionRepository queRepo;
    private final AnswerRepository ansRepo;

}
