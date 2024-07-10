package com.ttl.ITOapidrive.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ttl.ITOapidrive.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	@Query(value = "SELECT dept_name FROM departments", nativeQuery = true)
	List<String> getDepartmentByName();
}
