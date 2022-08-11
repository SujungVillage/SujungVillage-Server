package com.sswu_2022swcontest.sujungvillage.dto.dto.qna;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SimpleQnaDTO {

    private Long questionId;
    private String title;
    private LocalDateTime redDate;
    private Boolean answered;

}
