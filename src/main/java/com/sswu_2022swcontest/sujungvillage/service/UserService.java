package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.repository.UserRepository;
import com.sswu_2022swcontest.sujungvillage.repository.home.LmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final LmpRepository lmpRepo;

    public Boolean isResident(String userId) {
        Optional<User> user = userRepo.findById(userId);

        if (user.isEmpty() || !user.get().getAuthority().equals("ROLE_RESIDENT")) {
            return false;
        }

        return true;

    }

}
