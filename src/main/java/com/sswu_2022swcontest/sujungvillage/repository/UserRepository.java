package com.sswu_2022swcontest.sujungvillage.repository;

import com.sswu_2022swcontest.sujungvillage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
