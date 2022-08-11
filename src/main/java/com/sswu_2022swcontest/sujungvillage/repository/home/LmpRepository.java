package com.sswu_2022swcontest.sujungvillage.repository.home;

import com.sswu_2022swcontest.sujungvillage.entity.home.LivingMannerPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LmpRepository extends JpaRepository<LivingMannerPoint, Long> {

    List<LivingMannerPoint> findAllByUserId(String id);
}
