package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.Task;
import com.ua.kpi.developmentautomation.entities.UserStory;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.UserStoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final ModelMapper modelMapper;

    public Optional<UserStory> getUserStoryById(Long id) {
        return userStoryRepository.findById(id);
    }

    public UserStory updateUserStory(Long id, UserStory updatedUserStory) {
        Optional<UserStory> userStory = userStoryRepository.findById(id);

        if(userStory.isPresent()) {
            UserStory oldUserStory = userStory.get();
            modelMapper.map(updatedUserStory, oldUserStory);
            return userStoryRepository.save(oldUserStory);
        }

        throw new AppException("UserStory not found", HttpStatus.BAD_REQUEST);
    }

    public UserStory updateUserStoryTasks(Long id, Task task) {
        Optional<UserStory> oldUserStory = userStoryRepository.findById(id);

        if(oldUserStory.isPresent()) {
            UserStory userStory = oldUserStory.get();
            userStory.addTask(task);
            return userStoryRepository.save(userStory);
        }

        throw new AppException("UserStory not found", HttpStatus.BAD_REQUEST);
    }

    public void deleteUserStoryById(Long id) {
        userStoryRepository.deleteById(id);
    }

}
