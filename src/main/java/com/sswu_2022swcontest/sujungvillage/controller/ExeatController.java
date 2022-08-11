package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.exeat.ExeatDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.exeat.ApplyExeatRequest;
import com.sswu_2022swcontest.sujungvillage.service.ExeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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



}
