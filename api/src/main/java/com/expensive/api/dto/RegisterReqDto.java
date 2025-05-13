package com.expensive.api.dto;

import lombok.Data;

@Data
public class RegisterReqDto {
    private String username;
    private String email;
    private String password;
}
