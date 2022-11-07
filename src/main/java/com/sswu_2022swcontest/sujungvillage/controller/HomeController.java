package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.admin.AdminHomeInfoDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.admin.AdminInfoDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.ResidentHomeInfoDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.ResidentInfoDTO;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.service.ExeatService;
import com.sswu_2022swcontest.sujungvillage.service.LmpService;
import com.sswu_2022swcontest.sujungvillage.service.RollcallService;
import com.sswu_2022swcontest.sujungvillage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ExeatService exeatService;
    private final RollcallService rollcallService;
    private final LmpService lmpService;

    // 학생 홈화면 정보 조회
    @GetMapping("/api/student/home/getInfo")
    public ResidentHomeInfoDTO getResidentHomeInfo(
            @RequestParam int year,
            @RequestParam int month
    ){
        ResidentHomeInfoDTO response = new ResidentHomeInfoDTO();

        // 사용자 정보 조회
        User user = userService.getUser();
        response.setResidentInfo(new ResidentInfoDTO(
                user.getName(),
                user.getDormitory().getDormitoryName(),
                user.getDetailedAddress(),
                lmpService.getPlusLmp(),
                lmpService.getMinusLmp()
        ));

        // 점호일 리스트 조회
        response.setRollcallDays(rollcallService.getRollcallDays(year, month));

        // 점호신청일 리스트 조회
        response.setAppliedRollcallDays(rollcallService.getAppliedRolcallDays(year, month));

        // 외박신청일 리스트 조회
        response.setAppliedExeatDays(exeatService.getAppliedExeatDays(year, month));

        // 장기외박 신청 리스트 조회
        response.setAppliedLongTermExeatDays(exeatService.getLongTermExeats(year, month));

        return response;
    }

    // 관리자 홈화면 정보 조회
    @GetMapping("/api/admin/home/getInfo")
    public AdminHomeInfoDTO getAdminHomeInfo(
            @RequestParam int year,
            @RequestParam int month
    ){
        AdminHomeInfoDTO response = new AdminHomeInfoDTO();

        // 사용자 정보 조회
        User user = userService.getUser();
        response.setAdminInfoDTO(new AdminInfoDTO(
                user.getName(),
                user.getDormitory().getDormitoryName(),
                user.getDetailedAddress()
        ));

        // 점호일 리스트 조회
        response.setRollcallDays(rollcallService.getRollcallDays(year, month));

        return response;
    }

}
