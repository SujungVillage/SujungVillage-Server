package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.community.CommentDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.community.PostDTO;
import com.sswu_2022swcontest.sujungvillage.entity.community.Comment;
import com.sswu_2022swcontest.sujungvillage.entity.community.Post;
import com.sswu_2022swcontest.sujungvillage.repository.community.CommentRepository;
import com.sswu_2022swcontest.sujungvillage.repository.community.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final PostRepository postRepo;
    private final CommentRepository commentRepo;
    private final UserService userService;

    // 게시물 작성
    public PostDTO writePost(String title, String content) {

        return PostDTO.entityToDTO(
                postRepo.save(new Post(
                        null,
                        userService.getUser(),
                        title,
                        content,
                        null,
                        null
                ))
        );

    }

    // 댓글 작성
    public CommentDTO writeComment(Long postId, String content) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다 postId="+postId));

        return CommentDTO.entityToDTO(
                commentRepo.save(new Comment(
                        null,
                        post,
                        userService.getUser(),
                        content,
                        null,
                        null
                ))
        );

    }
}
