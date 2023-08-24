package com.ua.kpi.developmentautomation.entities;

import com.ua.kpi.developmentautomation.entities.base.TimeStamp;
import com.ua.kpi.developmentautomation.entities.enums.Priority;
import com.ua.kpi.developmentautomation.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class UserStory extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Integer storyPoints;

    @ManyToOne
    private Epic epic;

    @ManyToOne
    private User owner;

    @OneToMany
    private List<Task> tasks;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void addTask(Task task) {
        tasks.add(task);
    }
}
