/*
 * 
 * Created on : 17-July-2018
 * Author     : Kavya
 *
 *-----------------------------------------------------------------------------
 * VERSION  DATE       AUTHOR      DESCRIPTION OF CHANGE
 * 17-July-2018        Kavya        Created for User Profile Management screen
 *-----------------------------------------------------------------------------
 *
 */

package com.ttl.ito.business.service;

import org.springframework.stereotype.Service;

import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.internal.beans.LoginBO;

@Service
public interface UserProfileService {

	/**
	 * This method is used to create the user
	 * @param userDetails
	 * @param request
	 * @param response
	 * @return UserProfileDetails
	 */
	public UserProfileDetails createUser(UserProfileDetails userDetails);
	/**
	 * This method is used to edit the existing user
	 * @param userDetails
	 * @param request
	 * @param response
	 * @return UserProfileDetails
	 */
	public UserProfileDetails editUserProfileDetails(UserProfileDetails userDetails);
	
	}
