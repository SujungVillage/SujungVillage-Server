package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.community.CommentDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.community.DetailedPostDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.community.PostDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.community.SimplePostDTO;
import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.entity.community.Comment;
import com.sswu_2022swcontest.sujungvillage.entity.community.Post;
import com.sswu_2022swcontest.sujungvillage.repository.DormitoryRepository;
import com.sswu_2022swcontest.sujungvillage.repository.community.CommentRepository;
import com.sswu_2022swcontest.sujungvillage.repository.community.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final PostRepository postRepo;
    private final CommentRepository commentRepo;
    private final UserService userService;
    private final DormitoryRepository dormitoryRepo;

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

    // 게시물 리스트 조회
    public List<SimplePostDTO> getAllPost(String dormitoryName) {

        Dormitory dormitory = dormitoryRepo.findByDormitoryName(dormitoryName)
                .orElseThrow(() -> new IllegalArgumentException("해당 기숙사가 존재하지 않습니다 dormitoryName="+dormitoryName));

        if (dormitory.getId() == 0) {
            return postRepo.findAll()
                    .stream()
                    .map(p -> {
                        return new SimplePostDTO(
                                p.getId(),
                                p.getTitle(),
                                p.getContent(),
                                p.getRegDate()
                        );
                    }).collect(Collectors.toList());
        }

        return postRepo.findAllByDormityId(dormitory.getId())
                .stream()
                .map(p -> {
                    return new SimplePostDTO(
                            p.getId(),
                            p.getTitle(),
                            p.getContent(),
                            p.getRegDate()
                    );
                }).collect(Collectors.toList());

    }

    // 게시물 검색
    public List<SimplePostDTO> getSearchedPosts(String dormitoryName, String keyword) {
        Dormitory dormitory = dormitoryRepo.findByDormitoryName(dormitoryName)
                .orElseThrow(() -> new IllegalArgumentException("해당 기숙사가 존재하지 않습니다 dormitoryName="+dormitoryName));

        if (dormitory.getId() == 0) {
            return postRepo.searchByKeyword(keyword)
                    .stream()
                    .map(p -> {
                        return new SimplePostDTO(
                                p.getId(),
                                p.getTitle(),
                                p.getContent(),
                                p.getRegDate()
                        );
                    }).collect(Collectors.toList());
        }

        return postRepo.searchByKeywordAndDormitory(keyword, dormitory.getId())
                .stream()
                .map(p -> {
                    return new SimplePostDTO(
                            p.getId(),
                            p.getTitle(),
                            p.getContent(),
                            p.getRegDate()
                    );
                }).collect(Collectors.toList());
    }

    // 게시물 상세조회
    public DetailedPostDTO getPost(Long postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게시물이 존재하지 않습니다 postId="+postId));

        List<CommentDTO> comments = this.getComments(postId);

        return DetailedPostDTO.entityToDTO(post, comments);

    }

    // 댓글 조회
    private List<CommentDTO> getComments(Long postId) {
        return commentRepo.findByPostId(postId)
                .stream()
                .map(c -> {
                    return CommentDTO.entityToDTO(c);
                }).collect(Collectors.toList());
    }

    // 게시물 삭제
    @Transactional
    public Boolean deletePost(Long postId) {

        User user = userService.getUser();
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게시물이 존재하지 않습니다 postId="+postId));

        if (!post.getWriter().getId().equals(user.getId())) {
            return false;
        }

        this.deleteAllCommentByPostId(postId);
        postRepo.deleteById(postId);

        return true;

    }

    // 게시물의 모든 댓글 삭제
    @Transactional
    void deleteAllCommentByPostId(Long postId) {
        commentRepo.deleteAllByPostId(postId);
    }

    // 게시물 댓글 삭제
    @Transactional
    public Boolean deleteComment(Long commentId) {

        User user = userService.getUser();
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 댓글이 존재하지 않습니다 commentId="+commentId));

        if (!comment.getWriter().getId().equals(user.getId())) {
            return false;
        }

        commentRepo.deleteById(commentId);

        return true;

    }


}
