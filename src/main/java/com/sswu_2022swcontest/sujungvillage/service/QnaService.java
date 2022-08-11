package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.qna.FaqDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.qna.QuestionDTO;
import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.qna.Faq;
import com.sswu_2022swcontest.sujungvillage.entity.qna.Question;
import com.sswu_2022swcontest.sujungvillage.repository.DormitoryRepository;
import com.sswu_2022swcontest.sujungvillage.repository.qna.AnswerRepository;
import com.sswu_2022swcontest.sujungvillage.repository.qna.FaqRepository;
import com.sswu_2022swcontest.sujungvillage.repository.qna.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // 모든 FAQ 조회
    public List<FaqDTO> getAllFaq() {

        return faqRepo.findAll().stream()
                .map(faq -> {
                    return FaqDTO.entityToDto(faq);
                }).collect(Collectors.toList());

    }

    // 질문 작성
    public QuestionDTO writeQuestion(String title, String content, Boolean anonymous) {

        return QuestionDTO.entityToDTO(
                queRepo.save(new Question(
                        null,
                        userService.getUser(),
                        title,
                        content,
                        anonymous,
                        null,
                        null
                ))
        );

    }

}
