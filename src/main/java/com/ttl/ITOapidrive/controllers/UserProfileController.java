package com.ttl.ITOapidrive.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ttl.ITOapidrive.entities.Department;
import com.ttl.ITOapidrive.entities.Region;
import com.ttl.ITOapidrive.entities.Role;
import com.ttl.ITOapidrive.entities.UserProfile;
import com.ttl.ITOapidrive.services.DepartmentService;
import com.ttl.ITOapidrive.services.RegionService;
import com.ttl.ITOapidrive.services.RoleService;
import com.ttl.ITOapidrive.services.UserProfileService;

@RestController
@RequestMapping("/api/userprofiles")
@CrossOrigin
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;
    
    @Autowired
    private RegionService regionService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private DepartmentService departmentService;
    
 
    // User profile --> Add/edit/delete/get all users
    @PostMapping
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile createdUserProfile = userProfileService.createUserProfile(userProfile);
        return new ResponseEntity<>(createdUserProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile userProfile) {
        UserProfile updatedUserProfile = userProfileService.updateUserProfile(userId, userProfile);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long userId) {
        userProfileService.deleteUserProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserProfile>> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadUserProfiles(@RequestParam("file") MultipartFile file) {
        try {
            userProfileService.createUsersFromExcel(file);
            return new ResponseEntity<>("User profiles uploaded successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload user profiles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
  //Regions Controller part - 

    @PostMapping("/regions")
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        Region createdRegion = regionService.createRegion(region);
        return ResponseEntity.ok(createdRegion);
    }

    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getAllRegions();
        return ResponseEntity.ok(regions);
    }
    
    @GetMapping("/regionnames")
    public ResponseEntity<List<String>> getAllRegionsByName() {
        List<String> roles = regionService.getAllRegionsByName();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/regions/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable int id) {
        return regionService.getRegionById(id)
                .map(region -> ResponseEntity.ok(region))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Roles api end points - 
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
    
    @GetMapping("/rolenames")
    public ResponseEntity<List<String>> getAllRoleNames() {
        List<String> roles = roleService.getAllRoleName();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        return roleService.getRoleById(id)
                .map(role -> ResponseEntity.ok(role))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Get All department names API 

    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department newDepartment = departmentService.createDepartment(department);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/departments/{deptId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long deptId) {
        Optional<Department> department = departmentService.getDepartmentById(deptId);
        return department.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/departmentnames")
    public ResponseEntity<List<String>> getDepartmentByName() {
        List<String> departments = departmentService.getDepartmentByName();
        return new ResponseEntity<>(departments,HttpStatus.OK);
    }

}

