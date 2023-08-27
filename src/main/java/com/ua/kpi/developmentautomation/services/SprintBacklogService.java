package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.SprintBacklog;
import com.ua.kpi.developmentautomation.entities.UserStory;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.SprintBacklogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SprintBacklogService {

    private final SprintBacklogRepository sprintBacklogRepository;
    private final ModelMapper modelMapper;

    public Optional<SprintBacklog> getSprintBacklogById(Long sprintBacklogId) {
        return sprintBacklogRepository.findById(sprintBacklogId);
    }

    public SprintBacklog updateSprintBacklog(Long sprintBacklogId, SprintBacklog updatedSprintBacklog) {
        Optional<SprintBacklog> sprintBacklog = sprintBacklogRepository.findById(sprintBacklogId);

        if(sprintBacklog.isPresent()) {
            modelMapper.map(updatedSprintBacklog, sprintBacklog.get());
            return sprintBacklogRepository.save(sprintBacklog.get());
        }

        throw new AppException("SprintBacklog not found", HttpStatus.BAD_REQUEST);
    }

    public SprintBacklog updateSprintBacklogUserStories(Long sprintBacklogId, UserStory userStory) {
        Optional<SprintBacklog> oldSprintBacklog = sprintBacklogRepository.findById(sprintBacklogId);

        if(oldSprintBacklog.isPresent()) {
            SprintBacklog sprintBacklog = oldSprintBacklog.get();
            sprintBacklog.addUserStory(userStory);
            return sprintBacklogRepository.save(sprintBacklog);
        }

        throw new AppException("SprintBacklog not found", HttpStatus.BAD_REQUEST);
    }
}
