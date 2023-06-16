package com.edts.edtstechnicaltest.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
    private Long id;
    private String name;
    private Integer gradeCode;
    private Long salary;
    private Long totalBonus;
    private Date createdDate;
    private Date updatedDate;
}
