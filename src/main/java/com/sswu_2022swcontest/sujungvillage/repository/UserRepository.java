package com.sswu_2022swcontest.sujungvillage.repository;

import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.UserDTO;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByDormitoryId(Integer id);

    @Query(value = "SELECT * FROM users WHERE authority = 'ROLE_RESIDENT' ORDER BY user_id ; "
        ,nativeQuery = true)
    List<User> getAllResident();

    @Query(value = "SELECT * FROM users WHERE authority = 'ROLE_RESIDENT' AND dormitory_id = ?1 ORDER BY user_id ; "
        , nativeQuery = true)
    List<User> getAllResidentByDormitoryId(Integer dormitoryId);
}
