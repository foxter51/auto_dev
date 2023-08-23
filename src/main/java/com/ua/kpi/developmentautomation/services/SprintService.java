package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.Project;
import com.ua.kpi.developmentautomation.entities.Sprint;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private final ModelMapper modelMapper;

    private final ProjectService projectService;

    public List<Sprint> getAllSprintsByProjectId(Long projectId) {
        return sprintRepository.findAllByProjectId(projectId);
    }

    public Optional<Sprint> getSprintById(Long id) {
        return sprintRepository.findById(id);
    }

    public Sprint saveSprint(Long projectId, Sprint sprint) {
        Optional<Project> project = projectService.getProjectById(projectId);

        if(project.isPresent()){
            sprint.setProject(project.get());
            return sprintRepository.save(sprint);
        }

        throw new AppException("Parent project not found", HttpStatus.BAD_REQUEST);
    }

    public Sprint updateSprint(Sprint updatedSprint, Long id) {
        Optional<Sprint> sprint = sprintRepository.findById(id);

        if (sprint.isPresent()) {
            Sprint oldSprint = sprint.get();
            modelMapper.map(updatedSprint, oldSprint);
            return oldSprint;
        }
        throw new AppException("Sprint not found", HttpStatus.BAD_REQUEST);
    }
}
