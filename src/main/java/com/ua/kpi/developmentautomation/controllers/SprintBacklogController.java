package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.SprintBacklog;
import com.ua.kpi.developmentautomation.entities.UserStory;
import com.ua.kpi.developmentautomation.services.SprintBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sprint_backlogs")
@RequiredArgsConstructor
public class SprintBacklogController {

    private final SprintBacklogService sprintBacklogService;

    @GetMapping("/{id}")
    public ResponseEntity<SprintBacklog> getSprintBacklog(@PathVariable Long id) {
        Optional<SprintBacklog> sprintBacklog = sprintBacklogService.getSprintBacklogById(id);
        return sprintBacklog.isPresent() ? ResponseEntity.ok(sprintBacklog.get()) : ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SprintBacklog> updateSprintBacklog(@PathVariable Long id, @RequestBody SprintBacklog updatedSprintBacklog) {
        return ResponseEntity.ok(sprintBacklogService.updateSprintBacklog(id, updatedSprintBacklog));
    }

    @PatchMapping("/{id}/add_user_story")
    public ResponseEntity<SprintBacklog> updateSprintBacklogUserStories(@PathVariable Long id, @RequestBody UserStory userStory) {
        return ResponseEntity.ok(sprintBacklogService.updateSprintBacklogUserStories(id, userStory));
    }
}
