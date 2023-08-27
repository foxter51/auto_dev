package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.Task;
import com.ua.kpi.developmentautomation.exceptions.AppException;
import com.ua.kpi.developmentautomation.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task updateTask(Task updatedTask, Long taskId) {
        Optional<Task> oldTask = taskRepository.findById(taskId);

        if(oldTask.isPresent()) {
            Task task = oldTask.get();
            modelMapper.map(updatedTask, task);
            return taskRepository.save(task);
        }

        throw new AppException("Task not found", HttpStatus.BAD_REQUEST);
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
