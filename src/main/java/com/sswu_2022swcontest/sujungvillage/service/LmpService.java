package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.LmpDTO;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.entity.home.LivingMannerPoint;
import com.sswu_2022swcontest.sujungvillage.repository.UserRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.LmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LmpService {

    private final LmpRepository lmpRepo;
    private final UserService userService;
    private final UserRepository userRepo;
    private final FcmService fcmService;

    // 생활태도점수 부여
    public LmpDTO addLmp(String residentId, Short score, String reason) {

        User user = userRepo.findById(residentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 재사생, residentId="+residentId));

        // 알람 보내기
        fcmService.sendMessageTo(
                fcmService.getDeviceToken(user.getId()),
                "생활태도점수가 부여되었습니다.",
                "점수 : "+score.toString()+", 사유 : "+reason
        );

        return LmpDTO.entityToDTO(
                lmpRepo.save(new LivingMannerPoint(
                        null,
                        user,
                        score,
                        reason,
                        null
                ))
        );

    }

    // 생활태도점수 내역 조회
    public List<LmpDTO> getLmpHistory() {

        return lmpRepo.findAllByUserId(userService.getUser().getId())
                .stream()
                .map(lmp -> {
                    return LmpDTO.entityToDTO(lmp);
                })
                .collect(Collectors.toList());

    }

    // 상벌점 조회
    public int getLmp() {
        return lmpRepo.getLmp(userService.getUser().getId());
    }

    // 상점 조회
    public int getPlusLmp() {
        return lmpRepo.getPlusLmp(userService.getUser().getId());
    }

    // 벌점 조회
    public int getMinusLmp() {
        return lmpRepo.getMinusLmp(userService.getUser().getId());
    }
}
