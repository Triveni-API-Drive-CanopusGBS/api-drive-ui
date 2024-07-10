package com.ttl.ITOapidrive.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionId;

    private String regionName;
    private String regionKey;
    private LocalDateTime createdDt;
    private LocalDateTime modifiedDt;
    private int createdBy;
    private int modifiedBy;
    private boolean isActive;

    // Getters and Setters
}
