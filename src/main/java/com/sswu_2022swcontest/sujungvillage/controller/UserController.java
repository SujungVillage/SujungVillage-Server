package com.sswu_2022swcontest.sujungvillage.controller;

import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.UserDTO;
import com.sswu_2022swcontest.sujungvillage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/admin/getResidentList")
    public List<UserDTO> getResidentList(
            @RequestParam String dormitoryName
    ){
        return userService.getResidentList(dormitoryName);
    }

}
