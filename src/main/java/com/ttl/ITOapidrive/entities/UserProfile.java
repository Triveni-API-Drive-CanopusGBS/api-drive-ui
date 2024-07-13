package com.ttl.ITOapidrive.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	
	private double employeeId;
	private String employeeName;
	private String emailId;
	private double contactNumber;
	private double groupId;
	private double modifiedById;
	private String department;
	private String designation;
	private Boolean activeStatus;

	@ElementCollection
	private List<String> roleNames;

	@ElementCollection
	private List<String> regionNames;

	@Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;
		
}
