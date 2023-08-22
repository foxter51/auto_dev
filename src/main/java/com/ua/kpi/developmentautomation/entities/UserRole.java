package com.ua.kpi.developmentautomation.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Role role;
}
