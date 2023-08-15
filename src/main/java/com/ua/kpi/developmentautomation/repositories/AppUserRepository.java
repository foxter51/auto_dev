package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
