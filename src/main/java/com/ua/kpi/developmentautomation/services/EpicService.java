package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.Epic;
import com.ua.kpi.developmentautomation.entities.User;
import com.ua.kpi.developmentautomation.entities.UserStory;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.EpicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpicService {

    private final EpicRepository epicRepository;
    private final ModelMapper modelMapper;

    private final CustomUserDetailsService userDetailsService;

    public Optional<Epic> getEpicById(Long epicId) {
        return epicRepository.findById(epicId);
    }

    public Epic saveEpic(Epic epic) {
        return epicRepository.save(epic);
    }

    public Epic updateEpic(Epic updatedEpic, Long epicId) {
        Optional<Epic> epic = epicRepository.findById(epicId);
        if(epic.isPresent()) {
            Epic oldEpic = epic.get();
            modelMapper.map(updatedEpic, oldEpic);
            return epicRepository.save(oldEpic);
        }
        throw new AppException("Epic not found", HttpStatus.BAD_REQUEST);
    }

    public Epic addUserStory(Long epicId, Long ownerId, UserStory userStory) {
        Optional<Epic> oldEpic = epicRepository.findById(epicId);
        Optional<User> owner = userDetailsService.getUserById(ownerId);

        if(oldEpic.isPresent() && owner.isPresent()) {
            userStory.setOwner(owner.get());
            Epic epic = oldEpic.get();
            epic.addUserStory(userStory);
            return epicRepository.save(epic);
        }

        throw new AppException("Epic or owner not found", HttpStatus.BAD_REQUEST);
    }

    public void deleteEpicById(Long epicId) {
        epicRepository.deleteById(epicId);
    }
}
