package com.sswu_2022swcontest.sujungvillage.dto.dto.community;

import com.sswu_2022swcontest.sujungvillage.entity.community.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SimplePostDTO {

    private Long id;
    private String title;
    private String content;
    private String writerId;
    private LocalDateTime regDate;
    private Integer numOfComments;

    public static SimplePostDTO entityToDTO(Post e, Integer numOfComments){
        return new SimplePostDTO(
                e.getId(),
                e.getTitle(),
                e.getContent(),
                e.getWriter().getId(),
                e.getRegDate(),
                numOfComments
        );
    }
}
