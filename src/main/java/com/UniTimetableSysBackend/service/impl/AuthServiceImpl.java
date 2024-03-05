package com.UniTimetableSysBackend.service.impl;

import com.UniTimetableSysBackend.model.User;
import com.UniTimetableSysBackend.repository.UserRepository;
import com.UniTimetableSysBackend.security.CustomPasswordEncoder;
import com.UniTimetableSysBackend.security.JwtTokenProvider;
import com.UniTimetableSysBackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    private UserRepository userRepository;


    private CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    public AuthServiceImpl(CustomPasswordEncoder customPasswordEncoder) {
        this.customPasswordEncoder = customPasswordEncoder;
    }

    @Override
    public Object login(String email, String password) {


        User user = userRepository.findByEmail(email);

        if (user != null && customPasswordEncoder.matchPassword(password,user.getPassword())) {

            String role = user.getRole();

            String token = jwtTokenProvider.generateToken(user.getId(),email, role);

            Object object = Map.of("token", token, "role", role);

            return object;

        } else {

            throw new RuntimeException("Invalid credentials");
        }
    }
}
