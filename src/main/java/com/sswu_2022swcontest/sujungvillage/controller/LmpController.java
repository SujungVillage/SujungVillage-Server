package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.LmpDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.exeat.AddLmpRequest;
import com.sswu_2022swcontest.sujungvillage.service.LmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LmpController {

    private final LmpService lmpService;

    // 생활태도점수 부여
    @PostMapping("/api/admin/lmp/addLMP")
    public List<LmpDTO> addLMP(
            @RequestBody AddLmpRequest body
    ){
        return lmpService.addLmp(body.getResidentList(), body.getScore(), body.getReason());
    }

    // 생활태도점수 내역 조회
    @GetMapping("/api/student/lmp/getLmpHistory")
    public List<LmpDTO> getLmpHistory(){
        return lmpService.getLmpHistory();
    }


}
