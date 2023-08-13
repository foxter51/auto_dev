package com.ua.kpi.developmentautomation.entities;

import com.ua.kpi.developmentautomation.entities.base.TimeStamp;
import com.ua.kpi.developmentautomation.entities.enums.Priority;
import com.ua.kpi.developmentautomation.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Task extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Integer storyPoints;

    @ManyToOne
    private UserStory userStory;

    @ManyToOne
    private AppUser assignee;

    @Enumerated(EnumType.STRING)
    private Status status;
}
