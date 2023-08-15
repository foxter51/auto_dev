package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
