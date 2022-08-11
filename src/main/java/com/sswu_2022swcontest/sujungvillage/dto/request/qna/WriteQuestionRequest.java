package com.sswu_2022swcontest.sujungvillage.dto.request.qna;

import lombok.Getter;

@Getter
public class WriteQuestionRequest {

    private String title;
    private String content;
    private Boolean anonymous;

}
