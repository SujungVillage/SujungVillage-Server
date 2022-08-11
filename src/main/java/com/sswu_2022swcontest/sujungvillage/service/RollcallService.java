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
import java.time.LocalDateTime;
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
    public RollcallDTO applyRollcall(String imageURL, String location) {

        User user = userService.getUser();
        if (user == null) return null;

        // TODO: 점호 save하고 RollcallDTO 반환
        return RollcallDTO.entityToDTO(
                rollcallRepo.save(new Rollcall(
                        null,
                        user,
                        imageURL,
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

        for(int i = 0; i < rollcalls.size(); i++){
            System.out.println(rollcalls.get(i).getUser().getUsername()+" " + rollcalls.get(i).getRollcallTime());
        }

        return rollcallRepo.getWaitingRollcallsByDormitoryId(dormitoryId)
                .stream()
                .map(rollcall -> {
                    return new DetailedRollcallDTO(
                            rollcall.getId(),
                            rollcall.getUser().getId(),
                            rollcall.getUser().getName(),
                            rollcall.getUser().getDormitory().getDormitoryName(),
                            rollcall.getUser().getDetailedAddress(),
                            rollcall.getImageURL(),
                            rollcall.getLocation(),
                            rollcall.getRollcallTime(),
                            rollcall.getState()
                    );
                }).collect(Collectors.toList());
    }

    // 점호신청 상태 변경
    @Transactional
    public void changeRollcallState(Long rollcallId, String state) {

        rollcallRepo.findById(rollcallId)
                .orElseThrow(() -> new IllegalArgumentException("해당 rollcall이 존재하지 않습니다. id="+rollcallId));

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
                            rc.getRollcallTime().getDayOfMonth()
                    );
                }).collect(Collectors.toList());
    }
}
