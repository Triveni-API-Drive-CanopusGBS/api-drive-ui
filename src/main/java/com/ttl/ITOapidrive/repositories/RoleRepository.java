package com.ttl.ITOapidrive.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ttl.ITOapidrive.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query(value = "SELECT role_name FROM roles", nativeQuery = true)
	List<String> findAllRoleNames();
}
