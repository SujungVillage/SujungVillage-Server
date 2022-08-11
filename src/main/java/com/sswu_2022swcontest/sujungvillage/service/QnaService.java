package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.qna.*;
import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.qna.Answer;
import com.sswu_2022swcontest.sujungvillage.entity.qna.Faq;
import com.sswu_2022swcontest.sujungvillage.entity.qna.Question;
import com.sswu_2022swcontest.sujungvillage.repository.DormitoryRepository;
import com.sswu_2022swcontest.sujungvillage.repository.qna.AnswerRepository;
import com.sswu_2022swcontest.sujungvillage.repository.qna.FaqRepository;
import com.sswu_2022swcontest.sujungvillage.repository.qna.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    // 답변 작성
    public AnswerDTO writeAnswer(Long questionId, String content) {

        Question question = queRepo.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 질문이 존재하지 않습니다. questionId="+questionId));

        return AnswerDTO.entityToDTO(
                ansRepo.save(new Answer(
                        null,
                        userService.getUser(),
                        question,
                        content,
                        null,
                        null
                ))
        );

    }

    // 답변 여부 반환
    Boolean isAnswered(Long questionId){
        return ansRepo.isAnswered(questionId) > 0;
    }

    // 사용자의 QnA리스트 조회
    public List<SimpleQnaDTO> getMyQnas() {
        return queRepo.findByWriterId(userService.getUser().getId())
                .stream()
                .map(q -> {
                    return new SimpleQnaDTO(
                        q.getId(),
                        q.getTitle(),
                        q.getRegDate(),
                        isAnswered(q.getId())
                    );
                })
                .collect(Collectors.toList());
    }

    // 아직 답변되지 않은 QnA리스트 조회
    public List<SimpleQnaDTO> getUnansweredQnas() {

        return queRepo.getUnansweredQuestion()
                .stream()
                .map(q -> {
                    return new SimpleQnaDTO(
                            q.getId(),
                            q.getTitle(),
                            q.getRegDate(),
                            isAnswered(q.getId())
                    );
                })
                .collect(Collectors.toList());

    }

    // QnA 조회
    public QnaDTO getQna(Long questionId) {

        Question question = queRepo.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 질문이 존재하지 않습니다. questionId="+questionId));

        Optional<Answer> answer = ansRepo.findByQuestionId(questionId);

        QnaDTO result = new QnaDTO();

        result.setQuestion(QuestionDTO.entityToDTO(question));
        if (answer.isPresent()) result.setAnswer(AnswerDTO.entityToDTO(answer.get()));

        return result;

    }
}
