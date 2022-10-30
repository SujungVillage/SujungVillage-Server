package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.ExeatDTO;
import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.LongTermExeatDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.exeat.ApplyExeatRequest;
import com.sswu_2022swcontest.sujungvillage.dto.request.exeat.ApplyLongTermExeatRequest;
import com.sswu_2022swcontest.sujungvillage.service.ExeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExeatController {

    private final ExeatService exeatService;

    // 외박 신청
    @PostMapping("/api/student/exeat/applyExeat")
    public List<ExeatDTO> applyExeat(
            @RequestBody ApplyExeatRequest body
    ){

        ArrayList<ExeatDTO> exeats = new ArrayList<>();

        LocalDate dateToStart = LocalDate.parse(body.getDateToStart(), DateTimeFormatter.ISO_DATE);
        LocalDate dateToEnd = LocalDate.parse(body.getDateToEnd(), DateTimeFormatter.ISO_DATE);

        for (LocalDate d = dateToStart; d.isBefore(dateToEnd) || d.isEqual(dateToEnd); d = d.plusDays(1)){
            exeats.add(exeatService.applyExeat(
                    body.getDestination(),
                    body.getReason(),
                    body.getEmergencyPhoneNumber(),
                    d
            ));
        }

        return exeats;

    }

    @PostMapping("/api/student/exeat/applyLongTermExeat")
    public LongTermExeatDTO applyLongTermExeat(
            @RequestBody ApplyLongTermExeatRequest body
    ){

        return exeatService.applyLongTermExeat(
                body.getDestination(),
                body.getReason(),
                body.getEmergencyPhoneNumber(),
                body.getStartDate(),
                body.getEndDate());

    }
    @GetMapping("/api/student/exeat/getAppliedExeat")
    public ExeatDTO getAppliedExeat(
            @RequestParam Long exeatId
    ){
        return exeatService.getExeat(exeatId);
    }

    @DeleteMapping("/api/student/exeat/cancelExeat")
    public String cancelExeat(
            @RequestParam Long exeatId
    ){
        exeatService.deleteExeat(exeatId);

        return "외박신청 취소 완료";
    }

    @GetMapping("/api/student/exeat/numOfExeats")
    public Integer numOfExeats(){
        return exeatService.numOfExeats();
    }

}
