package com.sswu_2022swcontest.sujungvillage.repository.community;

import com.sswu_2022swcontest.sujungvillage.dto.dto.community.CommentDTO;
import com.sswu_2022swcontest.sujungvillage.entity.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    void deleteAllByPostId(Long postId);

    @Query(value = "SELECT COUNT(comment_id) FROM comment " +
            "WHERE post_id = ?1 ;"
        , nativeQuery = true)
    int getNumOfComments(Long postId);
}
