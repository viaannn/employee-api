package com.edts.edtstechnicaltest.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private int errorStatus;
    private String errorMessage;
}
