package com.ua.kpi.developmentautomation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String token;
}
