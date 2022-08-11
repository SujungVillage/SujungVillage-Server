package com.sswu_2022swcontest.sujungvillage.dto.dto.qna;

import com.sswu_2022swcontest.sujungvillage.entity.qna.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class QuestionDTO {

    private Long id;
    private String writerId;
    private String writerName;
    private String title;
    private String content;
    private Boolean anonymous;
    private LocalDateTime reqDate;
    private LocalDateTime modDate;

    public static QuestionDTO entityToDTO(Question question){
        return new QuestionDTO(
                question.getId(),
                question.getWriter().getId(),
                question.getWriter().getName(),
                question.getTitle(),
                question.getContent(),
                question.getAnonymous(),
                question.getRegDate(),
                question.getModDate()
        );
    }

}
