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
package com.ttl.ito.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.business.service.UserProfileService;
import com.ttl.ito.internal.beans.LoginBO;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;



@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "user")
@RestController
public class UserProfileController {
		private Logger logger = Logger.getLogger(QuotationController.class);

		@Autowired
		private UserProfileService userProfileService;
	
		@RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = "application/json")
		public UserProfileDetails createUser(@RequestBody UserProfileDetails userDetails,HttpServletRequest request) {
			
			try {
				clearMessageFrom(userDetails);
				userDetails = userProfileService.createUser(userDetails);

				return userDetails;
				
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return userDetails;
			}
		}

		/**
		 * This method is used to edit the existing user
		 * @param userDetails
		 * @param request
		 * @param response
		 * @return UserProfileDetails
		 */
		@RequestMapping(value = "/editUserProfileDetails", method = RequestMethod.POST, produces = "application/json")
		public UserProfileDetails editUserProfileDetails(@RequestBody UserProfileDetails userDetails,HttpServletRequest request) {
		
			try {
				clearMessageFrom(userDetails);
				userDetails = userProfileService.editUserProfileDetails(userDetails);
			
				return userDetails;

			} catch (Exception e) {
				logger.error("Exception :" + e);
				return userDetails;
			}
		}
		
		
		public LoginBO updateLoginUser(LoginBO loginBO,UserProfileDetails userDetails) {
		
			loginBO.setUserId(userDetails.getUserId());
			loginBO.setEmpId(userDetails.getEmpId());
			loginBO.setName(userDetails.getEmpName());
			loginBO.setEmailId(userDetails.getEmailId());
			loginBO.setDept(userDetails.getGroup());
			loginBO.setPhoneNumber(userDetails.getContactNumber());
			loginBO.setCreatedBy(userDetails.getCreatedBy());
			loginBO.setCreatedDate(userDetails.getCreatedDate());
			loginBO.setModifyBy(userDetails.getModifiedBy());
			loginBO.setModifyDate(userDetails.getModifiedDate());
			loginBO.setActive(userDetails.isActive());
			loginBO.setPassword("");
			loginBO.setImage(userDetails.getImage()); 
			loginBO.setUserRegionsList(userDetails.getUserRegionsList());
			loginBO.setUserRolesList(userDetails.getUserRolesList());
			return loginBO;
			
			}
		
		private void clearMessageFrom(UserProfileDetails userDetails) {
			if (null != userDetails) {
				userDetails.getMsgToUser().clear();
			}
		}
		
		
}
