package com.edts.edtstechnicaltest.controller;

import com.edts.edtstechnicaltest.model.request.AuthRequest;
import com.edts.edtstechnicaltest.model.response.BaseResponse;
import com.edts.edtstechnicaltest.model.response.LoginResponse;
import com.edts.edtstechnicaltest.model.response.UserRegisterResponse;
import com.edts.edtstechnicaltest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody AuthRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return new BaseResponse<>(HttpStatus.OK.value(), "Login Success", response);
    }

    @PostMapping("/register")
    public BaseResponse<UserRegisterResponse> registerUser(@RequestBody AuthRequest userRegisterRequest) {
        UserRegisterResponse response = userService.userRegister(userRegisterRequest);
        return new BaseResponse<>(HttpStatus.CREATED.value(), "User Created", response);
    }

}
