package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.Project;
import com.ua.kpi.developmentautomation.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects(@PathVariable("id") Long id) {
        List<Project> projects = projectService.getAllProjects();
        return projects.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.isPresent() ? ResponseEntity.ok(project.get()) : ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        return ResponseEntity.ok(projectService.saveProject(project));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long id, @RequestBody Project updatedProject){
        return ResponseEntity.ok(projectService.updateProject(updatedProject, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") Long id){
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }
}
