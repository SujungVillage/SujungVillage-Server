package com.sswu_2022swcontest.sujungvillage.dto.dto.community;

import com.sswu_2022swcontest.sujungvillage.entity.community.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class DetailedPostDTO {

    private Long id;
    private String writerId;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private List<CommentDTO> comments;

    public static DetailedPostDTO entityToDTO(Post e, List<CommentDTO> comments){
        return new DetailedPostDTO(
                e.getId(),
                e.getWriter().getId(),
                e.getTitle(),
                e.getContent(),
                e.getRegDate(),
                e.getModDate(),
                comments
        );
    }

}
