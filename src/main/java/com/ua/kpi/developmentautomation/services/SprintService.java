package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.Sprint;
import com.ua.kpi.developmentautomation.entities.SprintBacklog;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprintService {

    private final SprintRepository sprintRepository;
    private final ModelMapper modelMapper;

    public Optional<Sprint> getSprintById(Long sprintId) {
        return sprintRepository.findById(sprintId);
    }

    public Sprint updateSprint(Sprint updatedSprint, Long sprintId) {
        Optional<Sprint> sprint = sprintRepository.findById(sprintId);

        if (sprint.isPresent()) {
            Sprint oldSprint = sprint.get();
            modelMapper.map(updatedSprint, oldSprint);
            return oldSprint;
        }
        throw new AppException("Sprint not found", HttpStatus.BAD_REQUEST);
    }

    public Sprint updateSprintSprintBacklog(Long sprintId, SprintBacklog sprintBacklog) {
        Optional<Sprint> oldSprint = getSprintById(sprintId);

        if(oldSprint.isPresent()) {
            Sprint sprint = oldSprint.get();
            sprint.setSprintBacklog(sprintBacklog);
            return sprintRepository.save(sprint);
        }

        throw new AppException("Sprint not found", HttpStatus.BAD_REQUEST);
    }
}
