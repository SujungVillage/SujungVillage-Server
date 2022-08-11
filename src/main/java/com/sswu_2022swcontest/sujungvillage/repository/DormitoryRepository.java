package com.sswu_2022swcontest.sujungvillage.repository;

import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DormitoryRepository extends JpaRepository<Dormitory, Long> {
    Optional<Dormitory> findByDormitoryName(String dormitoryName);
}
