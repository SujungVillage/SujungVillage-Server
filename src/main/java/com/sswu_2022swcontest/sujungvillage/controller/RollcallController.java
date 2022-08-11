package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDateDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.rollcall.AddRollcallDateRequest;
import com.sswu_2022swcontest.sujungvillage.dto.request.rollcall.ApplyRollcallRequest;
import com.sswu_2022swcontest.sujungvillage.entity.home.Rollcall;
import com.sswu_2022swcontest.sujungvillage.service.RollcallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class RollcallController {

    private final RollcallService rollcallService;

    // 점호일 추가
    @PostMapping("/api/admin/rollcall/addRollcallDate")
    public RollcallDateDTO addRollcallDate(
            @RequestBody AddRollcallDateRequest body
    ){

        LocalDateTime start = LocalDateTime.of(
                Integer.parseInt(body.date.substring(0, 4)),
                Integer.parseInt(body.date.substring(5, 7)),
                Integer.parseInt(body.date.substring(8)),
                Integer.parseInt(body.startTime.substring(0, 2)),
                Integer.parseInt(body.startTime.substring(3))
        );

        LocalDateTime end = LocalDateTime.of(
                Integer.parseInt(body.date.substring(0, 4)),
                Integer.parseInt(body.date.substring(5, 7)),
                Integer.parseInt(body.date.substring(8)),
                Integer.parseInt(body.endTime.substring(0, 2)),
                Integer.parseInt(body.endTime.substring(3))
        );

        return rollcallService.addRollcallDate(
                start,
                end,
                body.dormitoryName);
    }

    // 점호일 조회
    @GetMapping("/api/common/rollcall/getRollcallDateInfo")
    public RollcallDateDTO getRollcallDateInfo(
            @RequestParam Long rollcallDateId
    ){
        return rollcallService.getRollcallDate(rollcallDateId);
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

}
