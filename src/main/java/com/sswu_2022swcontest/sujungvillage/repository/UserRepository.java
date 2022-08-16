package com.sswu_2022swcontest.sujungvillage.repository;

import com.sswu_2022swcontest.sujungvillage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByDormitoryId(Integer id);
}
