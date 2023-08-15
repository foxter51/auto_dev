package com.ua.kpi.developmentautomation.entities;

import com.ua.kpi.developmentautomation.entities.base.TimeStamp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class AppUser extends TimeStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String lastname;

    private String firstname;

    @ManyToOne
    private Team team;

    @OneToMany
    private Set<Role> roles;
}
