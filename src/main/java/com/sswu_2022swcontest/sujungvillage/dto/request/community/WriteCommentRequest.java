package com.sswu_2022swcontest.sujungvillage.dto.request.community;

import lombok.Getter;

@Getter
public class WriteCommentRequest {

    private Long postId;
    private String content;

}
