package com.UniTimetableSysBackend.controller;


import com.UniTimetableSysBackend.dto.request.UserDto;
import com.UniTimetableSysBackend.dto.response.CustomResponse;
import com.UniTimetableSysBackend.model.User;
import com.UniTimetableSysBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomResponse> signup(@RequestBody UserDto userDto) {

        User user = userService.createUser(userDto);

        //System.out.println("Details Recieved");

        CustomResponse<String> response = new CustomResponse<>(user, 201, "Success", true);
        return ResponseEntity.ok(response);
       // System.out.println("Details Recieved");
    }
}
