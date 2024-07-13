package com.ttl.ITOapidrive.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ttl.ITOapidrive.DTO.UserProfileDTO;
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

//	@Autowired
//	ModelMapper modelMapper;

	// User profile --> Add/edit/delete/get all users
	@PostMapping
	public ResponseEntity<UserProfile> createUserProfile(@ModelAttribute UserProfileDTO userProfileDTO)
			throws IOException {

		UserProfile userProfile = new UserProfile();
		userProfile.setEmployeeId(userProfileDTO.getEmployeeId());
		userProfile.setEmployeeName(userProfileDTO.getEmployeeName());
		userProfile.setEmailId(userProfileDTO.getEmailId());
		userProfile.setContactNumber(userProfileDTO.getContactNumber());
		userProfile.setGroupId(userProfileDTO.getGroupId());
		userProfile.setModifiedById(userProfileDTO.getModifiedById());
		userProfile.setDepartment(userProfileDTO.getDepartment());
		userProfile.setDesignation(userProfileDTO.getDesignation());
		userProfile.setActiveStatus(userProfileDTO.getActiveStatus());
		userProfile.setRoleNames(userProfileDTO.getRoleNames());
		userProfile.setRegionNames(userProfileDTO.getRegionNames());
		
		userProfile.setImage(userProfileDTO.getImage().getBytes()); 
		
		UserProfile savedUserProfile = userProfileService.createUserProfile(userProfile);
		return ResponseEntity.ok(savedUserProfile);
		
		// throws exception, need to handle it.
		// Files.readAllBytes(Paths.get("/c/IdeaProjects/Triveni-Turbines/api-drive-backend/image.png"))
		// UserProfile userProfile = modelMapper.map(userProfileDTO, UserProfile.class);

		/* 
		 * UserProfile savedUserProfile = userProfileService.createUserProfile(userProfile);
		

		UserProfileDTO savedUserProfileDTO = new UserProfileDTO();
		savedUserProfileDTO.setUserId(savedUserProfile.getUserId());
		savedUserProfileDTO.setEmployeeId(savedUserProfile.getEmployeeId());
		savedUserProfileDTO.setEmployeeName(savedUserProfile.getEmployeeName());
		savedUserProfileDTO.setEmailId(savedUserProfile.getEmailId());
		savedUserProfileDTO.setContactNumber(savedUserProfile.getContactNumber());
		savedUserProfileDTO.setGroupId(savedUserProfile.getGroupId());
		savedUserProfileDTO.setModifiedById(savedUserProfile.getModifiedById());
		savedUserProfileDTO.setDepartment(savedUserProfile.getDepartment());
		savedUserProfileDTO.setDesignation(savedUserProfile.getDesignation());
		savedUserProfileDTO.setActiveStatus(savedUserProfile.getActiveStatus());
		savedUserProfileDTO.setRoleNames(savedUserProfile.getRoleNames());
		savedUserProfileDTO.setRegionNames(savedUserProfile.getRegionNames());
		
		//byte[] to write into an image file.
		// Path write = Files.write(Paths.get("/c/IdeaProjects/Triveni-Turbines/api-drive-backend/image.png"), savedUserProfile.getImage());
		
		String base64String = "data:image/png;base64,savedUserProfile.getImage()"; // Your Base64 string
        byte[] bytes = Base64.getDecoder().decode(base64String.split(",")[1]); // Decode, removing data URL prefix

        //MultipartFile multipartFile = new MockMultipartFile(bytes);
		savedUserProfileDTO.setImage(multipartFile);
		
		 */
		

	}
	@CrossOrigin
	@PostMapping("/profile")
	public ResponseEntity<List<UserProfile>> getUserProfilesByEmailIdAndUserId(
	        @RequestParam(required = true) String emailId,
	        @RequestParam(required = true) Long userId) {

	    List<UserProfile> userProfiles = userProfileService.getUserProfilesByEmailIdAndUserId(emailId, userId);

	    if (userProfiles.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(userProfiles);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Long userId,
			@RequestBody UserProfile userProfile) {
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
			return new ResponseEntity<>("Failed to upload user profiles: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Regions Controller part -

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
		return regionService.getRegionById(id).map(region -> ResponseEntity.ok(region))
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
		return roleService.getRoleById(id).map(role -> ResponseEntity.ok(role))
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
		return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/departmentnames")
	public ResponseEntity<List<String>> getDepartmentByName() {
		List<String> departments = departmentService.getDepartmentByName();
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}

}
