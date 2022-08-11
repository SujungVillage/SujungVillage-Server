package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.rollcall.RollcallDateDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.rollcall.AddRollcallDateRequest;
import com.sswu_2022swcontest.sujungvillage.service.RollcallService;
import lombok.RequiredArgsConstructor;
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

}
