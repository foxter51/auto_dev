package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.AppUser;
import com.ua.kpi.developmentautomation.entities.enums.Status;
import com.ua.kpi.developmentautomation.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser newUser){
        try{
            AppUser createdUser = userDetailsService.registerUser(newUser);
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("/users/" + createdUser.getId())
                    .build()
                    .toUri();
            return ResponseEntity.created(location).body(createdUser);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            boolean isAuthenticated = userDetailsService.login(username, password);

            return isAuthenticated ? ResponseEntity.ok("Login successful!")
                    : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
