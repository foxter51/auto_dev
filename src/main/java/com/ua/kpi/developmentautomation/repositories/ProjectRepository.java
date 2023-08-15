package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
