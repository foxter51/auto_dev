package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
