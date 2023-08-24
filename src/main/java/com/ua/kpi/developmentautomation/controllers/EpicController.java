package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.entities.Epic;
import com.ua.kpi.developmentautomation.entities.UserStory;
import com.ua.kpi.developmentautomation.services.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/epics")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService epicService;

    @GetMapping("/{id}")
    public ResponseEntity<Epic> getEpic(@PathVariable Long id) {
        Optional<Epic> epic = epicService.getEpicById(id);
        return epic.isPresent() ? ResponseEntity.ok(epic.get()) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Epic> createEpic(@RequestBody Epic epic) {
        return ResponseEntity.ok(epicService.saveEpic(epic));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Epic> updateEpic(@PathVariable Long id, @RequestBody Epic updatedEpic) {
        return ResponseEntity.ok(epicService.updateEpic(updatedEpic, id));
    }

    @PatchMapping("/{id}/add_user_story")
    public ResponseEntity<Epic> updateEpicUserStories(@PathVariable("id") Long id,
                                                         @RequestBody UserStory userStory,
                                                         @RequestParam("owner_id") Long ownerId) {
        return ResponseEntity.ok(epicService.addUserStory(id, ownerId, userStory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEpic(@PathVariable Long id) {
        epicService.deleteEpicById(id);
        return ResponseEntity.noContent().build();
    }
}
