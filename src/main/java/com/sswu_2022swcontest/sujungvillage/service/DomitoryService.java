package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.entity.Domitory;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.repository.DomitoryRepository;
import com.sswu_2022swcontest.sujungvillage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomitoryService {
    private final DomitoryRepository domitoryRepository;

    // TODO: addDomitory메서드 구현
    public Domitory addDomitory(String domitoryName, String address) {
        if (domitoryRepository.findByDomitoryName(domitoryName) != null){
            System.out.println("이미 존재하는 기숙사");
        }

        return domitoryRepository.save(new Domitory(null, domitoryName, address));
    }
}
