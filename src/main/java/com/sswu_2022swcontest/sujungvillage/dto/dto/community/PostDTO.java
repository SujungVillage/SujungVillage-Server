package com.sswu_2022swcontest.sujungvillage.dto.dto.community;

import com.sswu_2022swcontest.sujungvillage.entity.community.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String writerId;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static PostDTO entityToDTO(Post e){
        return new PostDTO(
                e.getId(),
                e.getWriter().getId(),
                e.getTitle(),
                e.getContent(),
                e.getRegDate(),
                e.getModDate()
        );
    }

}
