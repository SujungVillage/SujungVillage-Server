package com.sswu_2022swcontest.sujungvillage.dto.dto.qna;

import com.sswu_2022swcontest.sujungvillage.entity.qna.Faq;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class FaqDTO {

    private Long id;
    private String writerId;
    private String question;
    private String answer;
    private String dormitoryName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static FaqDTO entityToDto(Faq faq){
        return new FaqDTO(
                faq.getId(),
                faq.getWriter().getId(),
                faq.getQuestion(),
                faq.getAnswer(),
                faq.getDormitory().getDormitoryName(),
                faq.getRegDate(),
                faq.getModDate()
        );
    }

}
