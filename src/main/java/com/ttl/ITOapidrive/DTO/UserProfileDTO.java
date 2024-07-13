package com.ttl.ITOapidrive.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
	private Long userId=null;
	private Double employeeId;
	private String employeeName;
	private String emailId;
	private Double contactNumber;
	private Double groupId;
	private Double modifiedById;
	private String department;
	private String designation;
	private Boolean activeStatus;
	private List<String> roleNames;
	private List<String> regionNames;
	private MultipartFile image;
	/*
	 * @Lob
	 * 
	 * @Column(name = "image", columnDefinition = "LONGBLOB") private byte[] image;
	 */

	// Add getters and setters
}
