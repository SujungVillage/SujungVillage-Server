package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.LmpDTO;
import com.sswu_2022swcontest.sujungvillage.dto.request.exeat.AddLmpRequest;
import com.sswu_2022swcontest.sujungvillage.service.LmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LmpController {

    private final LmpService lmpService;

    // 생활태도점수 부여
    @PostMapping("/api/admin/lmp/addLMP")
    public LmpDTO addLMP(
            @RequestBody AddLmpRequest body
    ){
        return lmpService.addLmp(body.getResidentId(), body.getScore(), body.getReason());
    }

}
