package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.DetailedRollcallDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDateDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.rollcall.AddRollcallDateRequest;
import com.sswu_2022swcontest.sujungvillage.dto.request.rollcall.ApplyRollcallRequest;
import com.sswu_2022swcontest.sujungvillage.entity.home.Rollcall;
import com.sswu_2022swcontest.sujungvillage.service.RollcallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RollcallController {

    private final RollcallService rollcallService;

    // 점호일 추가
    @PostMapping("/api/admin/rollcall/addRollcallDate")
    public RollcallDateDTO addRollcallDate(
            @RequestBody AddRollcallDateRequest body
    ){

        return rollcallService.addRollcallDate(
                body.getStartDateTime(),
                body.getEndDateTime(),
                body.getDormitoryName());
    }

    // 점호일 조회
    @GetMapping("/api/common/rollcall/getRollcallDateInfo")
    public RollcallDateDTO getRollcallDateInfo(
            @RequestParam Long rollcallDateId
    ){
        return rollcallService.getRollcallDate(rollcallDateId);
    }

    // 현재 점호 가능 여부 조회
    @GetMapping("/api/common/rollcall/isRollcallAvailableNow")
    public Boolean isRollcallAvailableNow(){
        return rollcallService.isRollcallAvailableNow();
    }

    // 점호일 삭제
    @DeleteMapping("/api/admin/rollcall/deleteRollcallDate")
    public String deleteRollcallDate(
            @RequestParam Long rollcallDateId
    ){
        rollcallService.deleteRollcallDate(rollcallDateId);

        return "점호일 삭제 완료";
    }


    // 점호 신청
    @PostMapping("/api/student/rollcall/applyRollcall")
    public RollcallDTO applyRollCall(
            @RequestBody ApplyRollcallRequest body
    ){
        return rollcallService.applyRollcall(body.getImageURL(), body.getLocation());
    }

    @GetMapping("/api/common/rollcall/getAppliedRollcallInfo")
    public RollcallDTO getAppliedRollcallInfo(
            @RequestParam Long rollcallId
    ){
        return rollcallService.getRollcallDto(rollcallId);
    }

    @GetMapping("/api/admin/rollcall/getRollcallList")
    public List<DetailedRollcallDTO> getRollcallList(
            @RequestParam String date,
            @RequestParam String state
    ){
        LocalDate localDate = LocalDate.parse(date);

        return rollcallService.getRollcallListByState(localDate, state);
    }

    @PatchMapping("/api/admin/rollcall/changeRollcallState")
    public String changeRollcallState(
            @RequestParam Long rollcallId,
            @RequestParam String state
    ){
        rollcallService.changeRollcallState(rollcallId, state);

        return "상태 변경 완료";
    }

}
