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

    @DeleteMapping("/api/admin/qna/deleteFaq")
    public String deleteFaq(
            @RequestParam Long faqId
    ){

        return qnaService.deleteFaq(faqId);
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

    @GetMapping("/api/admin/qna/getAllQuestions")
    public List<SimpleQnaDTO> getAllQuestions(){
        return qnaService.getAllQuestions();
    }

    @GetMapping("/api/common/qna/getQna")
    public QnaDTO getQna(
            @RequestParam Long questionId
    ){
        return qnaService.getQna(questionId);
    }

    @DeleteMapping("/api/common/qna/deleteQuestion")
    public String deleteQuestion(
            @RequestParam Long questionId
    ){

        qnaService.deleteQuestion(questionId);

        return "질문삭제 성공";

    }

}
