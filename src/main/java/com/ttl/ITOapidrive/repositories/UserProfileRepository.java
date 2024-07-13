package com.ttl.ITOapidrive.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttl.ITOapidrive.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	List<UserProfile> findByEmailIdAndUserId(String emailId, Long userId);

}
