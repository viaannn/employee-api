package com.edts.edtstechnicaltest.service;

import com.edts.edtstechnicaltest.exception.DataNotFoundException;
import com.edts.edtstechnicaltest.model.entity.Employees;
import com.edts.edtstechnicaltest.model.request.EmployeeRequest;
import com.edts.edtstechnicaltest.model.response.EmployeeResponse;
import com.edts.edtstechnicaltest.repository.EmployeeRepository;
import com.edts.edtstechnicaltest.util.EmployeeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employees employees = new Employees();
        employees.setName(request.getName());
        employees.setGrade(request.getGradeCode());
        employees.setSalary(request.getSalary());

        Employees createdEmployee = employeeRepository.save(employees);
        return EmployeeResponse.builder()
                .name(createdEmployee.getName())
                .gradeCode(createdEmployee.getGrade())
                .createdDate(createdEmployee.getCreatedDate())
                .build();
    }

    public EmployeeResponse getEmployeeDetail(Long id) {
        Employees employee = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Employee is not found!"));

        Long totalBonus = EmployeeUtil.calculateTotalBonus(employee.getGrade(), employee.getSalary());
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .gradeCode(employee.getGrade())
                .salary(employee.getSalary())
                .totalBonus(totalBonus)
                .build();
    }

    public List<EmployeeResponse> getEmployeeList() {
        List<Employees> employeesList = employeeRepository.findAll();
        List<EmployeeResponse> response = new ArrayList<>();

        for (Employees employee : employeesList) {
            Long totalBonus = EmployeeUtil.calculateTotalBonus(employee.getGrade(), employee.getSalary());
            EmployeeResponse employeeRes = EmployeeResponse.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .gradeCode(employee.getGrade())
                    .salary(employee.getSalary())
                    .totalBonus(totalBonus)
                    .build();

            response.add(employeeRes);
        }

        return response;
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employees existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Employee is not found!"));

        existingEmployee.setName(request.getName());
        existingEmployee.setGrade(request.getGradeCode());
        existingEmployee.setSalary(request.getSalary());
        employeeRepository.save(existingEmployee);

        return EmployeeResponse.builder()
                .name(existingEmployee.getName())
                .gradeCode(existingEmployee.getGrade())
                .updatedDate(existingEmployee.getUpdatedDate())
                .build();
    }
}
