package com.ua.kpi.developmentautomation.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ProductBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne
    private Project project;

    @ManyToOne
    private AppUser owner;

    @OneToMany
    private List<Epic> epics;
}
