package com.edts.edtstechnicaltest.service;

import com.edts.edtstechnicaltest.exception.DataNotFoundException;
import com.edts.edtstechnicaltest.model.entity.Employees;
import com.edts.edtstechnicaltest.model.request.EmployeeRequest;
import com.edts.edtstechnicaltest.model.response.EmployeeResponse;
import com.edts.edtstechnicaltest.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;
    private Employees mockEmployee;
    private EmployeeRequest mockEmployeeRequest;

    @BeforeEach
    public void init() {
        employeeService = new EmployeeService(employeeRepository);
        mockEmployee = buildEmployee();
        mockEmployeeRequest = buildEmployeeRequest();
    }

    @Test
    public void testCreateEmployee_returnSuccess() {
        when(employeeRepository.save(any())).thenReturn(mockEmployee);
        EmployeeResponse response = employeeService.createEmployee(mockEmployeeRequest);

        assertEquals(mockEmployeeRequest.getName(), response.getName());
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    public void testGetEmployeeDetail_returnSuccess() {
        when(employeeRepository.findById(any())).thenReturn(Optional.of(mockEmployee));
        EmployeeResponse response = employeeService.getEmployeeDetail(1L);

        assertEquals(mockEmployee.getName(), response.getName());
        verify(employeeRepository, times(1)).findById(any());
    }

    @Test
    public void testGetEmployeeList_returnSuccess() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Employees> employeesList = List.of(mockEmployee);
        Page<Employees> employeesPage = new PageImpl<>(employeesList, pageable, employeesList.size());

        when(employeeRepository.findAll(pageable)).thenReturn(employeesPage);
        Page<EmployeeResponse> responses = employeeService.getEmployeeList(10, 1);

        assertEquals(employeesPage.getContent().size(), responses.getContent().size());
        verify(employeeRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testUpdateEmployee_returnSuccess() {
        when(employeeRepository.findById(any())).thenReturn(Optional.of(mockEmployee));
        EmployeeResponse response = employeeService.updateEmployee(1L, mockEmployeeRequest);

        assertEquals(mockEmployee.getName(), response.getName());
        verify(employeeRepository, times(1)).findById(any());
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateEmployee_employeeNotFound_throwDataNotFoundException() {
        when(employeeRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () -> {
            employeeService.updateEmployee(1L, mockEmployeeRequest);
        });
        verify(employeeRepository, times(1)).findById(any());
    }

    private Employees buildEmployee() {
        Employees employees = new Employees();
        employees.setId(1L);
        employees.setName("TEST_NAME");
        employees.setGrade(1);
        employees.setSalary(123456);
        return employees;
    }

    private EmployeeRequest buildEmployeeRequest() {
        return EmployeeRequest.builder()
                .name("TEST_NAME")
                .gradeCode(1)
                .salary(12342356)
                .build();
    }

}
