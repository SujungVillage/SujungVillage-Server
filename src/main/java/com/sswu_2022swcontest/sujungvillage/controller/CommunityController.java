package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.community.CommentDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.community.DetailedPostDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.community.PostDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.community.SimplePostDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.community.WriteCommentRequest;
import com.sswu_2022swcontest.sujungvillage.dto.request.community.WritePostRequest;
import com.sswu_2022swcontest.sujungvillage.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 댓글 작성
    @PostMapping("/api/common/community/writeComment")
    public CommentDTO writeComment(
            @RequestBody WriteCommentRequest body
    ){
        return communityService.writeComment(body.getPostId(), body.getContent());
    }

    // 게시글 제목 리스트 조회
    @GetMapping("/api/common/community/getAllPost")
    public List<SimplePostDTO> getAllPost(
            @RequestParam String dormitoryName
    ){
        return communityService.getAllPost(dormitoryName);
    }

    @GetMapping("/api/common/community/getPost")
    public DetailedPostDTO getPost(
            @RequestParam Long postId
    ){
        return communityService.getPost(postId);
    }

    @DeleteMapping("/api/common/community/deletePost")
    public String deletePost(
            @RequestParam Long postId
    ){
        if (communityService.deletePost(postId)) {
            return "게시물 삭제 완료";
        }

        return "게시물 삭제 실패";
    }



    @DeleteMapping("/api/common/community/deleteComment")
    public String deleteComment(
            @RequestParam Long commentId
    ){
        if (communityService.deleteComment(commentId)) {
            return "댓글 삭제 완료";
        }

        return "댓글 삭제 실패";
    }
}
