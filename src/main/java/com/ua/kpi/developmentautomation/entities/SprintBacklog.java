package com.ua.kpi.developmentautomation.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class SprintBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer storyPointsTotal;

    @OneToOne
    private Sprint sprint;

    @OneToMany
    private List<UserStory> userStories;
}
