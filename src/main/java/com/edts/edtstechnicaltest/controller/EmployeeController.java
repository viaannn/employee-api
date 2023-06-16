package com.edts.edtstechnicaltest.controller;

import com.edts.edtstechnicaltest.model.request.EmployeeRequest;
import com.edts.edtstechnicaltest.model.response.BaseResponse;
import com.edts.edtstechnicaltest.model.response.EmployeeResponse;
import com.edts.edtstechnicaltest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public BaseResponse<EmployeeResponse> getEmployeeDetail(@PathVariable Long id) {
        EmployeeResponse response = employeeService.getEmployeeDetail(id);
        return new BaseResponse<>(HttpStatus.OK.value(), "Employee Detail", response);
    }

    @GetMapping("/list")
    public BaseResponse<List<EmployeeResponse>> getEmployeeList() {
        List<EmployeeResponse> response = employeeService.getEmployeeList();
        return new BaseResponse<>(HttpStatus.OK.value(), "Employee List", response);
    }

    @PostMapping("/create")
    public BaseResponse<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        return new BaseResponse<>(HttpStatus.CREATED.value(), "Employee Created", response);
    }

    @PutMapping("/update/{id}")
    public BaseResponse<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.updateEmployee(id, request);
        return new BaseResponse<>(HttpStatus.OK.value(), "Employee Updated", response);
    }
}
