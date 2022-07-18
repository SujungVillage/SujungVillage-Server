package com.sswu_2022swcontest.sujungvillage.service;

import com.sswu_2022swcontest.sujungvillage.entity.User;
import com.sswu_2022swcontest.sujungvillage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // TODO: addUser메서드 구현
    public User addUser(String userId, String username, String phonenumber, String password, String authority) {
        if ((userRepository.findById(userId)).isPresent()) {
            System.out.println("이미 존재하는 유저");
            return null;
        }

        if (authority.equals("ROLE_ADMIN") || authority.equals("ROLE_STUDENTADMIN")){
            return userRepository.save(new User(userId, username, phonenumber, passwordEncoder.encode(password), authority));
        }

        return userRepository.save(new User(userId, username, phonenumber, null, authority));
    }

}
