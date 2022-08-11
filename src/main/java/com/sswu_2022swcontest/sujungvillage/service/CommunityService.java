package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.community.PostDTO;
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
}
