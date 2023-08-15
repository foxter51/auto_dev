package com.ua.kpi.developmentautomation.repositories;

import com.ua.kpi.developmentautomation.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);
    Boolean existsAppUserByUsername(String username);
    Boolean existsAppUserByEmail(String username);
}
