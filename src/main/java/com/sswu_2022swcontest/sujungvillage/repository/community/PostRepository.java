package com.sswu_2022swcontest.sujungvillage.repository.community;

import com.sswu_2022swcontest.sujungvillage.dto.dto.community.SimplePostDTO;
import com.sswu_2022swcontest.sujungvillage.entity.community.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post ORDER BY reg_date DESC; "
        , nativeQuery = true)
    List<Post> findAll();

    @Query(value = "SELECT post_id, content, reg_date, title, mod_date, post.writer_id FROM post " +
            "LEFT JOIN users ON post.writer_id = users.user_id " +
            "WHERE users.dormitory_id = ?1 " +
            "ORDER BY reg_date DESC;"
        , nativeQuery = true)
    List<Post> findAllByDormityId(Integer dormitoryId);


}
