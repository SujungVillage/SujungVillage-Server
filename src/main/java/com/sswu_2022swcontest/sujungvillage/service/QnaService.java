package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.qna.FaqDTO;
import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.qna.Faq;
import com.sswu_2022swcontest.sujungvillage.repository.DormitoryRepository;
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
    private final DormitoryRepository dormitoryRepo;
    private final UserService userService;

    // FAQ 작성
    public FaqDTO writeFaq(String question, String answer, String dormitoryName) {

        Dormitory dormitory = dormitoryRepo.findByDormitoryName(dormitoryName)
                .orElseThrow(() -> new IllegalArgumentException("해당 기숙사갸 존재하지 않음 dormitoryName="+dormitoryName));

        return FaqDTO.entityToDto(
                faqRepo.save(
                    new Faq(
                        null,
                        userService.getUser(),
                        dormitory,
                        question,
                        answer,
                        null,
                        null
                    )
                )
        );

    }
}
