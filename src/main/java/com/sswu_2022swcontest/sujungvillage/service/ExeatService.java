package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.AppliedExeatDayDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.AppliedLongTermExeatDayDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.ExeatDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.LongTermExeatDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.AppliedRollcallDayDTO;
import com.sswu_2022swcontest.sujungvillage.entity.home.Exeat;
import com.sswu_2022swcontest.sujungvillage.entity.home.LongTermExeat;
import com.sswu_2022swcontest.sujungvillage.repository.home.ExeatRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.LongTermExeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExeatService {

    private final ExeatRepository exeatRepo;
    private final LongTermExeatRepository ltexeatRepo;
    private final UserService userService;

    // 외박신청
    public ExeatDTO applyExeat(String destination, String reason, String emergencyPhoneNumber, LocalDate date) {

        if (exeatRepo.alreadyExists(userService.getUser().getId(), date) > 0) {
            return null;
        }

        if(ltexeatRepo.checkOverlap(userService.getUser().getId(), date) > 0){
            return null;
        }

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

    // 장기외박 신청
    @Transactional
    public LongTermExeatDTO applyLongTermExeat(String destination, String reason, String emergencyPhoneNumber, LocalDate startDate, LocalDate endDate) {

        for (LocalDate d = startDate; d.isBefore(endDate) || d.isEqual(endDate); d = d.plusDays(1)){
            if(exeatRepo.alreadyExists(userService.getUser().getId(), d) > 0){
                return null;
            }

            if(ltexeatRepo.checkOverlap(userService.getUser().getId(), d) > 0){
                return null;
            }

        }

        return LongTermExeatDTO.entityToDTO(
                    ltexeatRepo.save(new LongTermExeat(
                        null,
                        userService.getUser(),
                        destination,
                        reason,
                        emergencyPhoneNumber,
                        startDate,
                        endDate
                    )
                )
        );
    }

    // 외박신청 조회
    public ExeatDTO getExeat(Long exeatId) {
        return ExeatDTO.entityToDTO(
                exeatRepo.findById(exeatId).orElseThrow(
                        () -> new IllegalArgumentException("해당 exeat이 존재하지 않습니다. id="+exeatId))
        );
    }

    // 장기외박 신청 조회
    public LongTermExeatDTO getApplieLongTermExeat(Long exeatId) {

        return LongTermExeatDTO.entityToDTO(
                ltexeatRepo.findById(exeatId).orElseThrow(
                        () -> new IllegalArgumentException("해당 exeat이 존재하지 않습니다. id="+exeatId))
        );

    }

    // 외박신청 취소
    public void deleteExeat(Long exeatId) {
        exeatRepo.deleteById(exeatId);
    }

    public void deleteLongTermExeat(Long exeatId) {ltexeatRepo.deleteById(exeatId);
    }

    // 외박신청일 조회
    public List<AppliedExeatDayDTO> getAppliedExeatDays(int year, int month) {

        return exeatRepo.getAppliedExeatlDays(userService.getUser().getId(), year, month)
                .stream()
                .map(e -> {
                    return new AppliedExeatDayDTO(
                            e.getId(),
                            e.getDateToUse().getDayOfMonth()
                    );
                }).collect(Collectors.toList());

    }

    public List<AppliedLongTermExeatDayDTO> getLongTermExeats(int year, int month) {
        return ltexeatRepo.getLongTermExeats(userService.getUser().getId(), year, month)
                .stream()
                .map(e -> {
                    return new AppliedLongTermExeatDayDTO(
                            e.getId(),
                            e.getStartDate(),
                            e.getEndDate()
                    );
                }).collect(Collectors.toList());
    }

    public Integer numOfExeats() {
        return exeatRepo.numOfExeats(userService.getUser().getId());
    }
}
