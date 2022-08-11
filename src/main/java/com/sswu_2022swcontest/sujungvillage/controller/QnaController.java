package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.qna.*;
import com.sswu_2022swcontest.sujungvillage.dto.request.qna.WriteAnswerRequest;
import com.sswu_2022swcontest.sujungvillage.dto.request.qna.WriteFaqRequest;
import com.sswu_2022swcontest.sujungvillage.dto.request.qna.WriteQuestionRequest;
import com.sswu_2022swcontest.sujungvillage.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    // FAQ 작성
    @PostMapping("/api/admin/qna/writeFaq")
    public FaqDTO writeFaq(
            @RequestBody WriteFaqRequest body
    ){
        return qnaService.writeFaq(body.getQuestion(), body.getAnswer(), body.getDormitoryName());
    }

    // FAQ 리스트 조회
    @GetMapping("/api/common/qna/getAllFaq")
    public List<FaqDTO> getAllFaq(){
        return qnaService.getAllFaq();
    }

    // 질문 작성
    @PostMapping("/api/student/qna/writeQuestion")
    public QuestionDTO writeQuestion(
            @RequestBody WriteQuestionRequest body
    ){
        return qnaService.writeQuestion(body.getTitle(), body.getContent(), body.getAnonymous());
    }

    // 답변 작성
    @PostMapping("/api/admin/qna/writeAnswer")
    public AnswerDTO writeAnswer(
            @RequestBody WriteAnswerRequest body
    ){
        return qnaService.writeAnswer(body.getQuestionId(), body.getContent());
    }

    @GetMapping("/api/student/qna/getMyQnas")
    public List<SimpleQnaDTO> getMyQnas(){
        return qnaService.getMyQnas();
    }

    @GetMapping("/api/admin/qna/getUnansweredQnas")
    public List<SimpleQnaDTO> getUnansweredQnas(){
        return qnaService.getUnansweredQnas();
    }

    @GetMapping("/api/common/qna/getQna")
    public QnaDTO getQna(
            @RequestParam Long questionId
    ){
        return qnaService.getQna(questionId);
    }


}
