package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.ExeatDTO;
import com.sswu_2022swcontest.sujungvillage.entity.home.Exeat;
import com.sswu_2022swcontest.sujungvillage.repository.home.ExeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExeatService {

    private final ExeatRepository exeatRepo;
    private final UserService userService;

    // 외박신청
    public ExeatDTO applyExeat(String destination, String reason, String emergencyPhoneNumber, LocalDate date) {

        return ExeatDTO.entityToDTO(
                exeatRepo.save(
                        new Exeat(
                                null,
                                userService.getUser(),
                                destination,
                                reason,
                                emergencyPhoneNumber,
                                date
                        )
                )
        );
    }
}
