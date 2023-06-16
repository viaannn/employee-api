package com.edts.edtstechnicaltest.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeRequest {
    private String name;
    private int gradeCode;
    private long salary;
}
