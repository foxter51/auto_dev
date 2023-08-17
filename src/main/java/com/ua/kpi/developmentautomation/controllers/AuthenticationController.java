package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.dto.CredentialsDto;
import com.ua.kpi.developmentautomation.dto.RegisterCredentialsDto;
import com.ua.kpi.developmentautomation.dto.UserDto;
import com.ua.kpi.developmentautomation.security.UserAuthProvider;
import com.ua.kpi.developmentautomation.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final CustomUserDetailsService userDetailsService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        UserDto user = userDetailsService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterCredentialsDto registerCredentialsDto){
        UserDto user = userDetailsService.register(registerCredentialsDto);
        user.setToken(userAuthProvider.createToken(user.getUsername()));
        return ResponseEntity.created(URI.create("/users/" +user.getId()))
                .body(user);
    }

}
