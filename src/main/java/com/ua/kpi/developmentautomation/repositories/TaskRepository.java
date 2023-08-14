package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.Task;
import com.ua.kpi.developmentautomation.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByAssigneeIdAndUserStory_Epic_ProductBacklog_ProjectId(Long assigneeId, Long projectId);
    List<Task> findTasksByUserStory_Epic_ProductBacklog_ProjectId(Long projectId);
    List<Task> findTasksByStatusAndUserStory_Epic_ProductBacklog_ProjectId(Status status, Long projectId);
}
