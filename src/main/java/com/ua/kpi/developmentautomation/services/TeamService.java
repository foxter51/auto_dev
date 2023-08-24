package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.dto.TeamMemberRequest;
import com.ua.kpi.developmentautomation.entities.Role;
import com.ua.kpi.developmentautomation.entities.Team;
import com.ua.kpi.developmentautomation.entities.User;
import com.ua.kpi.developmentautomation.entities.UserRole;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    private final CustomUserDetailsService userDetailsService;
    private final RoleService roleService;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long teamId) {
        return teamRepository.findById(teamId);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Team updatedTeam, Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            Team oldTeam = team.get();
            modelMapper.map(updatedTeam, oldTeam);
            return oldTeam;
        }
        throw new AppException("Team not found", HttpStatus.BAD_REQUEST);
    }

    public void deleteTeamById(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    public Team updateTeamMembers(Long teamId, TeamMemberRequest teamMemberRequest) {
        Optional<Team> team = teamRepository.findById(teamId);
        Optional<User> user = userDetailsService.getUserById(teamMemberRequest.getMemberId());
        Optional<Role> role = roleService.getRoleById(teamMemberRequest.getMemberId());

        if(team.isPresent() && user.isPresent() && role.isPresent()) {
            UserRole userRole = UserRole.builder().team(team.get()).user(user.get()).role(role.get()).build();

            Team oldTeam = team.get();
            oldTeam.addUserAndRole(userRole);
            return teamRepository.save(oldTeam);
        }

        throw new AppException("Team or user or role not found", HttpStatus.BAD_REQUEST);
    }
}
