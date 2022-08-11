package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.entity.home.LivingMannerPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LmpRepository extends JpaRepository<LivingMannerPoint, Long> {

    List<LivingMannerPoint> findAllByUserId(String id);

    @Query(value = "SELECT IFNULL(SUM(score), 0) from living_manner_point where user_id = ?1 and score > 0"
            ,nativeQuery = true)
    int getPlusLmp(String userId);

    @Query(value = "SELECT IFNULL(SUM(score), 0) from living_manner_point where user_id = ?1 and score < 0"
            , nativeQuery = true)
    int getMinusLmp(String userId);

    @Query(value = "SELECT IFNULL(SUM(score), 0) from living_manner_point where user_id = ?1 "
            , nativeQuery = true)
    int getLmp(String userId);
}
