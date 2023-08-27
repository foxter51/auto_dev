package com.ua.kpi.developmentautomation.services;

import com.ua.kpi.developmentautomation.entities.Role;
import com.ua.kpi.developmentautomation.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }
}
