package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
