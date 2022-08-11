package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.qna.FaqDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.qna.WriteFaqRequest;
import com.sswu_2022swcontest.sujungvillage.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    @PostMapping("/api/admin/qna/writeFaq")
    public FaqDTO writeFaq(
            @RequestBody WriteFaqRequest body
    ){
        return qnaService.writeFaq(body.getQuestion(), body.getAnswer(), body.getDormitoryName());
    }

}
