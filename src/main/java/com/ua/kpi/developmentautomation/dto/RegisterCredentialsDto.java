package com.ua.kpi.developmentautomation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterCredentialsDto {

    private String username;
    private String email;
    private char[] password;
    private String lastname;
    private String firstname;
}
