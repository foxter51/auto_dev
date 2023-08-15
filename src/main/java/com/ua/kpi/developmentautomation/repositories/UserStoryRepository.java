package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
}
