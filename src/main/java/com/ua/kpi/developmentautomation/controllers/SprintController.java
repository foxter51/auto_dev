package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.Sprint;
import com.ua.kpi.developmentautomation.services.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    @GetMapping("/projects/{project_id}/sprints")
    public ResponseEntity<List<Sprint>> getAllSprintsByProjectId(@PathVariable("project_id") Long projectId) {
        List<Sprint> sprints = sprintService.getAllSprintsByProjectId(projectId);
        return sprints.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(sprints);
    }


    @GetMapping("/sprints/{id}")
    public ResponseEntity<Sprint> getSprint(@PathVariable("id") Long id) {
        Optional<Sprint> sprint = sprintService.getSprintById(id);
        return sprint.isPresent() ? ResponseEntity.ok(sprint.get()) : ResponseEntity.noContent().build();
    }

    @PostMapping("/projects/{project_id}/sprints")
    public ResponseEntity<Sprint> createProjectSprint(@PathVariable("project_id") Long projectId, @RequestBody Sprint sprint) {
        return ResponseEntity.ok(sprintService.saveSprint(projectId, sprint));
    }

    @PatchMapping("/sprints/{id}")
    public ResponseEntity<Sprint> updateSprint(@PathVariable Long id, @RequestBody Sprint updatedSprint) {
        return ResponseEntity.ok(sprintService.updateSprint(updatedSprint, id));
    }
}
