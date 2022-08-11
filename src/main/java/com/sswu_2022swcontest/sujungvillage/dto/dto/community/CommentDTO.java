package com.sswu_2022swcontest.sujungvillage.dto.dto.community;

import com.sswu_2022swcontest.sujungvillage.entity.community.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private Long postId;
    private String writerId;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static CommentDTO entityToDTO(Comment e){
        return new CommentDTO(
                e.getId(),
                e.getPost().getId(),
                e.getWriter().getId(),
                e.getContent(),
                e.getRegDate(),
                e.getModDate()
        );
    }

}
