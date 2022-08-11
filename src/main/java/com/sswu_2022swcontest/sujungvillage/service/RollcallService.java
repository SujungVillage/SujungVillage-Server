package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDateDTO;
import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.entity.home.Rollcall;
import com.sswu_2022swcontest.sujungvillage.entity.home.RollcallDate;
import com.sswu_2022swcontest.sujungvillage.repository.DormitoryRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.RollcallDateRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.RollcallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RollcallService {

    private final RollcallRepository rollcallRepo;
    private final RollcallDateRepository rollcallDateRepo;
    private final DormitoryRepository dormitoryRepo;
    private final UserService userService;


    // 점호일 추가
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
}
