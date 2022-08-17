package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.*;
import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.entity.home.Rollcall;
import com.sswu_2022swcontest.sujungvillage.entity.home.RollcallDate;
import com.sswu_2022swcontest.sujungvillage.repository.DormitoryRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.RollcallDateRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.RollcallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RollcallService {

    private final RollcallRepository rollcallRepo;
    private final RollcallDateRepository rollcallDateRepo;
    private final DormitoryRepository dormitoryRepo;
    private final UserService userService;
    private final FcmService fcmService;


    // 점호일 추가
    @Transactional
    public RollcallDateDTO addRollcallDate(LocalDateTime start, LocalDateTime end, String dormitoryName) {

        Optional<Dormitory> dormitory = dormitoryRepo.findByDormitoryName(dormitoryName);

        if (dormitory.isEmpty()) {
            return null;
        }

        return RollcallDateDTO.entityToDTO(
                rollcallDateRepo.save(new RollcallDate(
                        null,
                        start,
                        end,
                        dormitory.get()
                ))
        );

    }

    // 점호일 조회
    public RollcallDateDTO getRollcallDate(Long rollcallDateId) {
        Optional<RollcallDate> rcd = rollcallDateRepo.findById(rollcallDateId);

        if (rcd.isPresent()) {
            return RollcallDateDTO.entityToDTO(rcd.get());
        }

        return null;
    }

    // 점호신청
    @Transactional
    public RollcallDTO applyRollcall(Byte[] image, String location) {

        User user = userService.getUser();
        if (user == null) return null;

        // TODO: 점호 save하고 RollcallDTO 반환
        return RollcallDTO.entityToDTO(
                rollcallRepo.save(new Rollcall(
                        null,
                        user,
                        image,
                        location,
                        null,
                        "대기"
                ))
        );

    }

    // 점호 조회
    public RollcallDTO getRollcallDto(Long rollcallId) {
        Optional<Rollcall> rollcall = rollcallRepo.findById(rollcallId);

        if (rollcall.isPresent()) {
            return RollcallDTO.entityToDTO(rollcall.get());
        }

        return null;
    }

    // 대기중인 점호 리스트 조회
    public List<DetailedRollcallDTO> getWaitingRollcallList() {

        Integer dormitoryId = userService.getUser().getDormitory().getId();

        List<Rollcall> rollcalls = rollcallRepo.getWaitingRollcallsByDormitoryId(dormitoryId);

        if (dormitoryId == 0) {
            return rollcallRepo.getWaitingRollcalls()
                    .stream()
                    .map(rollcall -> {
                        return DetailedRollcallDTO.entityToDTO(rollcall);
                    }).collect(Collectors.toList());
        }

        return rollcallRepo.getWaitingRollcallsByDormitoryId(dormitoryId)
                .stream()
                .map(rollcall -> {
                    return DetailedRollcallDTO.entityToDTO(rollcall);
                }).collect(Collectors.toList());
    }

    // 점호신청 상태 변경
    @Transactional
    public void changeRollcallState(Long rollcallId, String state) {

        Rollcall rollcall = rollcallRepo.findById(rollcallId)
                .orElseThrow(() -> new IllegalArgumentException("해당 rollcall이 존재하지 않습니다. id="+rollcallId));

        // 알람 보내기
        fcmService.sendMessageTo(
                fcmService.getDeviceToken(rollcall.getUser().getId()),
                "점호가 "+state+"되었습니다.",
                "점호일시 : "+rollcall.getRollcallTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
        );

        rollcallRepo.changeState(rollcallId, state);

    }

    // 점호일 조회
    public List<RollcallDayDTO> getRollcallDays(int year, int month) {
        return rollcallDateRepo.getRollCallDays(year, month, userService.getUser().getDormitory().getId())
                .stream()
                .map(rcd -> {
                    return new RollcallDayDTO(
                            rcd.getId(),
                            rcd.getStartDateTime().getDayOfMonth()
                    );
                }).collect(Collectors.toList());

    }

    public List<AppliedRollcallDayDTO> getAppliedRolcallDays(int year, int month) {
        return rollcallRepo.getAppliedRollcallDays(userService.getUser().getId(), year, month)
                .stream()
                .map(rc -> {
                    return new AppliedRollcallDayDTO(
                            rc.getId(),
                            rc.getRollcallTime().minusDays(1).getDayOfMonth()
                    );
                }).collect(Collectors.toList());
    }

    public Boolean isRollcallAvailableNow() {
        Integer dormitoryId = userService.getUser().getDormitory().getId();

        RollcallDate rollcallDate = rollcallDateRepo.getTodayRollcall(dormitoryId, LocalDateTime.now());

        if (rollcallDate != null) {
            return (LocalDateTime.now().isAfter(rollcallDate.getStartDateTime()) &&
                    LocalDateTime.now().isBefore(rollcallDate.getEndDateTime()));
        }

        return false;
    }

    // 점호일 삭제
    @Transactional
    public void deleteRollcallDate(Long rollcallDateId) {

        RollcallDate rcd = rollcallDateRepo.findById(rollcallDateId)
                .orElseThrow(() -> new IllegalArgumentException("해당 점호일이 존재하지 않습니다. rollcallDateId="+rollcallDateId));

        rollcallDateRepo.deleteById(rollcallDateId);
    }

    public List<DetailedRollcallDTO> getRollcallListByState(LocalDate date, String state) {

        Integer dormitoryId = userService.getUser().getDormitory().getId();

        if (dormitoryId == 0) {
            return rollcallRepo.getAllRollcallsByDate(date)
                    .stream().map(rc -> {
                        return DetailedRollcallDTO.entityToDTO(rc);
                    }).collect(Collectors.toList());
        }

        if (state.equals("전체")) {
            return rollcallRepo.getAllRollcallsByDateAndDormitoryId(date, dormitoryId)
                    .stream().map(rc -> {
                        return DetailedRollcallDTO.entityToDTO(rc);
                    }).collect(Collectors.toList());
        }

        return rollcallRepo.getAllRollcallsByDateAndDormitoryIdAndState(date, dormitoryId, state)
                .stream().map(rc -> {
                    return DetailedRollcallDTO.entityToDTO(rc);
                }).collect(Collectors.toList());
    }
}
