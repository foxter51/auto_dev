package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.Sprint;
import com.ua.kpi.developmentautomation.entities.SprintBacklog;
import com.ua.kpi.developmentautomation.services.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprint(@PathVariable("id") Long id) {
        Optional<Sprint> sprint = sprintService.getSprintById(id);
        return sprint.isPresent() ? ResponseEntity.ok(sprint.get()) : ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Sprint> updateSprint(@PathVariable Long id, @RequestBody Sprint updatedSprint) {
        return ResponseEntity.ok(sprintService.updateSprint(updatedSprint, id));
    }

    @PatchMapping("{id}/sprint_backlog")
    public ResponseEntity<Sprint> updateSprintSprintBacklog(@PathVariable("id") Long id, @RequestBody SprintBacklog sprintBacklog) {
        return ResponseEntity.ok(sprintService.updateSprintSprintBacklog(id, sprintBacklog));
    }
}
