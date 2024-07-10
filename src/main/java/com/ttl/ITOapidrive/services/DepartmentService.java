package com.ttl.ITOapidrive.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttl.ITOapidrive.entities.Department;
import com.ttl.ITOapidrive.repositories.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        // Logic to save or update department
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        // Logic to fetch all departments
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long deptId) {
        // Logic to fetch department by ID
        return departmentRepository.findById(deptId);
    }
    
    public List<String> getDepartmentByName() {
        // Logic to fetch department by ID
        return departmentRepository.getDepartmentByName();
    }

	

    // Other methods as needed
}
