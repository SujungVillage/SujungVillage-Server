package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.entity.Domitory;
import com.sswu_2022swcontest.sujungvillage.entity.Resident;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.repository.ResidentRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;

    // TODO: addResident메서드 구현
    public Resident addResident(@NotNull User user, @NotNull Domitory domitory, @NotNull String detailedAddress) {

        return residentRepository.save(new Resident(null, user, domitory, detailedAddress));
    }
}
