package com.UniTimetableSysBackend.service;


import com.UniTimetableSysBackend.dto.request.UserDto;
import com.UniTimetableSysBackend.model.User;

public interface UserService {
    User createUser(UserDto userDto);
}
