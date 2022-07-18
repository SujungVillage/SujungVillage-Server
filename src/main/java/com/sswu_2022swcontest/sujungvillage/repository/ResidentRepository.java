package com.sswu_2022swcontest.sujungvillage.repository;

import com.sswu_2022swcontest.sujungvillage.entity.Resident;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResidentRepository extends JpaRepository<Resident, String> {
    // 기숙사별 학생들 리스트 반환 메서드
}
