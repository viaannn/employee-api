package com.edts.edtstechnicaltest.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserRegisterResponse {
    private String username;
    private Date createdDate;
}
