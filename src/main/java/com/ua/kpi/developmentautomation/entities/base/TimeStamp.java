package com.ua.kpi.developmentautomation.entities.base;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.util.Date;

@MappedSuperclass
public abstract class TimeStamp {
    private Date createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = new Date();
    }
}
