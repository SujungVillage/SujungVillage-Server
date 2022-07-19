package com.sswu_2022swcontest.sujungvillage.repository;

import com.sswu_2022swcontest.sujungvillage.entity.Domitory;
import com.sswu_2022swcontest.sujungvillage.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomitoryRepository extends JpaRepository<Domitory, Long> {
    Domitory findByDomitoryName(String domitoryName);
}
