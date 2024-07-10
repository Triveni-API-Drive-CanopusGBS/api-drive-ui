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
@Table(name = "departments")
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;
    
    private String deptCd;
    private String deptName;
    private LocalDateTime createdDt;
    private LocalDateTime modifiedDt;
    private Integer createdBy;
    private Integer modifiedBy;
    private Boolean isActive;

    // Constructors, getters, and setters
    

    // Getters and setters (omitted for brevity)

}
