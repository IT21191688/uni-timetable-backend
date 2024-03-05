package com.UniTimetableSysBackend.service.impl;

import com.UniTimetableSysBackend.dto.request.UserDto;
import com.UniTimetableSysBackend.model.User;
import com.UniTimetableSysBackend.repository.UserRepository;
import com.UniTimetableSysBackend.security.CustomPasswordEncoder;
import com.UniTimetableSysBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomPasswordEncoder customPasswordEncoder) {
        this.userRepository = userRepository;
        this.customPasswordEncoder=customPasswordEncoder;
    }


    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setContactNo(userDto.getContactNo());
        user.setPassword(customPasswordEncoder.encodePassword(userDto.getPassword()));
        user.setRole(userDto.getRole());
        return userRepository.save(user);
    }
}
