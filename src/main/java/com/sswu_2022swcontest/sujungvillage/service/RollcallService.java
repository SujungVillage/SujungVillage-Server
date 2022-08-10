package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.repository.home.RollcallDateRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.RollcallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RollcallService {

    private final RollcallRepository rcRepo;
    private final RollcallDateRepository dateRepo;

}
