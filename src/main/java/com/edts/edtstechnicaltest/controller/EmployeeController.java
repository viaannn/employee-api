package com.edts.edtstechnicaltest.controller;

import com.edts.edtstechnicaltest.model.response.BaseResponse;
import com.edts.edtstechnicaltest.model.response.EmployeeListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    @GetMapping("/list")
    public BaseResponse<EmployeeListResponse> getEmployeeList() {
        return new BaseResponse<>(HttpStatus.CREATED.value(), "User Created", null);
    }
}
