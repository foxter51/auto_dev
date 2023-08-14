package com.ua.kpi.developmentautomation.entities;

import com.ua.kpi.developmentautomation.entities.base.TimeStamp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Project extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne
    private ProductBacklog productBacklog;

    @OneToMany
    private List<Sprint> sprints;

    @OneToOne
    private Sprint currentSprint;

    @ManyToOne
    private Team team;
}
