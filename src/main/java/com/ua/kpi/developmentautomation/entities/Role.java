package com.ua.kpi.developmentautomation.entities;

import com.ua.kpi.developmentautomation.entities.enums.ScrumRole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ScrumRole scrumRole;
}
