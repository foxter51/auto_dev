package com.ua.kpi.developmentautomation.controllers;

import com.ua.kpi.developmentautomation.dto.TeamMemberRequest;
import com.ua.kpi.developmentautomation.entities.Team;
import com.ua.kpi.developmentautomation.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return teams.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable Long id) {
        Optional<Team> team = teamService.getTeamById(id);
        return team.isPresent() ? ResponseEntity.ok(team.get()) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.saveTeam(team));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") Long id, @RequestBody Team updatedTeam) {
        return ResponseEntity.ok(teamService.updateTeam(updatedTeam, id));
    }

    @PatchMapping("/{id}/add_member")
    public ResponseEntity<Team> updateTeamMembers(@PathVariable("id") Long id, @RequestBody TeamMemberRequest teamMemberRequest) {
        return ResponseEntity.ok(teamService.updateTeamMembers(id, teamMemberRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") Long id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }
}
