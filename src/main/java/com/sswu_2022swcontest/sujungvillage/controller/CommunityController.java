package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.community.PostDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.community.WritePostRequest;
import com.sswu_2022swcontest.sujungvillage.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    // 게시글 작성
    @PostMapping("/api/common/community/writePost")
    public PostDTO writePost(
            @RequestBody WritePostRequest body
    ){
        return communityService.writePost(body.getTitle(), body.getContent());
    }

}
