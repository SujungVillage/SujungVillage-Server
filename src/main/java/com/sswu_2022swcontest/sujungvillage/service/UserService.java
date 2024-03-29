package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.dto.dto.resident.UserDTO;
import com.sswu_2022swcontest.sujungvillage.entity.Dormitory;
import com.sswu_2022swcontest.sujungvillage.entity.FCM;
import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.repository.DormitoryRepository;
import com.sswu_2022swcontest.sujungvillage.repository.FcmRepository;
import com.sswu_2022swcontest.sujungvillage.repository.UserRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.LmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final FcmRepository fcmRepo;
    private final LmpRepository lmpRepo;
    private final DormitoryRepository dormitoryRepo;

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

    public Boolean userLogin(String id, String password){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다 userId="+id));

        if (user.getPassword().equals(password)) {
            return true;
        }

        return false;

    }

    @Transactional
    public String userSignup(String id, String password, String name, String dormitoryName, String detailedAddress, String phoneNumber){

        if (userRepo.findById(id).isPresent()){
            return "이미 사용중인 id입니다.";
        }

        if (dormitoryRepo.findByDormitoryName(dormitoryName).isEmpty()){
            return "존재하지 않는 기숙사입니다.";
        }

        Dormitory dormitory = dormitoryRepo.findByDormitoryName(dormitoryName).get();

        userRepo.save(new User(
                id,
                name,
                phoneNumber,
                dormitory,
                detailedAddress,
                password,
                "ROLE_RESIDENT"
        ));

        return "회원가입 성공";
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

    public List<UserDTO> getResidentList(String dormitoryName) {
        if (dormitoryName.equals("전체")) {
            return userRepo.getAllResident().stream()
                    .map(u -> {
                        return UserDTO.entityToDTO(u);
                    }).collect(Collectors.toList());
        }

        Dormitory d = dormitoryRepo.findByDormitoryName(dormitoryName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기숙사입니다 dormitoryName=" + dormitoryName));

        return userRepo.getAllResidentByDormitoryId(d.getId()).stream()
                .map(u -> {
                    return UserDTO.entityToDTO(u);
                }).collect(Collectors.toList());

    }

    public Boolean isAvailableId(String id) {
        return userRepo.findById(id).isEmpty();
    }
}
