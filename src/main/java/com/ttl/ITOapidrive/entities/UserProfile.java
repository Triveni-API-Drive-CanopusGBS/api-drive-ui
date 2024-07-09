package com.ttl.ITOapidrive.entities;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private double empId;
	private String empName;
	private String emailId;
	private double contactNumber;
	private double groupId;
	private String image;
	private double modifiedById;
	private String department;
	private String designation;
	private Boolean activeStatus;

	@ElementCollection
	private List<String> roleNames;

	@ElementCollection
	private List<String> regionNames;

		
}
