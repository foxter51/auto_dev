package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    List<Sprint> findAllByProjectId(Long projectId);
}
