package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.User;
import com.ua.kpi.developmentautomation.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CustomUserDetailsService userDetailsService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            List<User> users = userDetailsService.getAllUsers();
            return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        try{
            Optional<User> user = userDetailsService.getUserById(id);
            return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser){
        Optional<User> oldUser = userDetailsService.getUserById(id);
        if(oldUser.isPresent()){
            User user = oldUser.get();
            return ResponseEntity.ok(userDetailsService.updateUser(updatedUser, user));
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeUser(@PathVariable("id") Long id){
        try{
            userDetailsService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
