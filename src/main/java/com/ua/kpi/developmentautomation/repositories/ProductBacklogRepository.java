package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {

    Optional<ProductBacklog> findByProjectId(Long projectId);
}
