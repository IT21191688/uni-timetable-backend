package com.UniTimetableSysBackend.controller;



import com.UniTimetableSysBackend.dto.request.LoginDto;
import com.UniTimetableSysBackend.dto.response.CustomResponse;
import com.UniTimetableSysBackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //logging.level.org.springframework=DEBUG
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {

        Object data = authService.login(loginDto.getEmail(), loginDto.getPassword());

        CustomResponse<String> response = new CustomResponse<>(data, 201, "Success", true);
        return ResponseEntity.ok(response);

    }
}
