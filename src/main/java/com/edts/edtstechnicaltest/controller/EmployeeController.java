package com.edts.edtstechnicaltest.controller;

import com.edts.edtstechnicaltest.model.request.EmployeeRequest;
import com.edts.edtstechnicaltest.model.response.BaseResponse;
import com.edts.edtstechnicaltest.model.response.EmployeeResponse;
import com.edts.edtstechnicaltest.model.response.PageResponse;
import com.edts.edtstechnicaltest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public PageResponse<List<EmployeeResponse>> getEmployeeList(@RequestParam int size, @RequestParam int page) {
        Page<EmployeeResponse> response = employeeService.getEmployeeList(size, page);
        return new PageResponse<>(HttpStatus.OK.value(), "Employee List", response.getContent(), response.getTotalElements(), response.getTotalPages());
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
