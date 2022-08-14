package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.entity.FCM;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.repository.FcmRepository;
import com.sswu_2022swcontest.sujungvillage.repository.UserRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.LmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final FcmRepository fcmRepo;
    private final LmpRepository lmpRepo;

    public User getUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다 userId="+userId));

        return user;
    }

    public Boolean isResident(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다 userId="+userId));

        if (user.getAuthority().equals("ROLE_RESIDENT")) {
            return true;
        }

        return false;

    }

    public Boolean adminLogin(String id, String password){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다 userId="+id));

        if (user.getPassword().equals(password)) {
            return true;
        }

        return false;

    }

    public void setFcmToken(String userId, String fcmToken){

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다 userId="+userId));

        fcmRepo.save(new FCM(userId, fcmToken));

    }

    public String getFcmToken(){

        FCM fcm = fcmRepo.findById(this.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다 userId="+this.getUser().getId()));

        return fcm.getFcmToken();

    }

}
