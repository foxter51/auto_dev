package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.Task;
import com.ua.kpi.developmentautomation.entities.UserStory;
import com.ua.kpi.developmentautomation.services.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user_stories")
@RequiredArgsConstructor
public class UserStoryController {

    private final UserStoryService userStoryService;

    @GetMapping("/{id}")
    public ResponseEntity<UserStory> getUserStory(@PathVariable Long id) {
        Optional<UserStory> userStory = userStoryService.getUserStoryById(id);
        return userStory.isPresent() ? ResponseEntity.ok(userStory.get()) : ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserStory> updateUserStory(@PathVariable Long id, @RequestBody UserStory updatedUserStory) {
        return ResponseEntity.ok(userStoryService.updateUserStory(id, updatedUserStory));
    }

    @PatchMapping("/{id}/add_task")
    public ResponseEntity<UserStory> updateUserStoryTasks(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(userStoryService.updateUserStoryTasks(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUserStory(@PathVariable Long id) {
        userStoryService.deleteUserStoryById(id);
        return ResponseEntity.noContent().build();
    }
}
