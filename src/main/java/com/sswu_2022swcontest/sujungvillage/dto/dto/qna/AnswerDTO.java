package com.sswu_2022swcontest.sujungvillage.dto.dto.qna;


import com.sswu_2022swcontest.sujungvillage.entity.qna.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AnswerDTO {

    private Long id;
    private String writerName;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static AnswerDTO entityToDTO(Answer answer){
        return new AnswerDTO(
                answer.getId(),
                answer.getWriter().getName(),
                answer.getContent(),
                answer.getRegDate(),
                answer.getModDate()
        );
    }

}
