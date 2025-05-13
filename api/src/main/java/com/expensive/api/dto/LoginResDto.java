package com.expensive.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResDto {
    private String accessToken;
    private String Username;
    private String email;
    private String msg;
}
