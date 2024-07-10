package com.ttl.ITOapidrive.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttl.ITOapidrive.entities.Role;
import com.ttl.ITOapidrive.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        role.setCreatedDt(LocalDateTime.now());
        role.setModifiedDt(LocalDateTime.now());
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(int roleId) {
        return roleRepository.findById(roleId);
    }

	public List<String> getAllRoleName() {
		 return roleRepository.findAllRoleNames();
	}
}
