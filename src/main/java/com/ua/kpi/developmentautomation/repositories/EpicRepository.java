package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.Epic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpicRepository extends JpaRepository<Epic, Long> {
}
