package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.repository.ResidentRepository;
import com.sswu_2022swcontest.sujungvillage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final UserRepository userRepository;
    private final ResidentRepository residentRepository;

    // TODO: addResident메서드 구현
}
