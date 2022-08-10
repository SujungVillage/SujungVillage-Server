package com.sswu_2022swcontest.sujungvillage.repository.community;

import com.sswu_2022swcontest.sujungvillage.entity.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
