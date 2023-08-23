package com.ua.kpi.developmentautomation.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Project> projects;

    private String name;

    @OneToMany
    private List<UserRole> usersAndRoles;

    public void addUserAndRole(UserRole userAndRole) {
        usersAndRoles.add(userAndRole);
    }
}
