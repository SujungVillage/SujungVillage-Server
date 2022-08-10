package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.repository.community.CommentRepository;
import com.sswu_2022swcontest.sujungvillage.repository.community.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final PostRepository postRepoy;
    private final CommentRepository commentRepo;

}
