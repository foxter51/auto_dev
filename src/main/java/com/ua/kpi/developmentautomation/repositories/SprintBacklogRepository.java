package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {
}
