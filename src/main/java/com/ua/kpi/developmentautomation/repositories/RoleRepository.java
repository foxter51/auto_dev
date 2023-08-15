package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
