package com.edts.edtstechnicaltest.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeListResponse {
    private String name;
    private String gradeCode;
    private String salary;
    private String totalBonus;
}
