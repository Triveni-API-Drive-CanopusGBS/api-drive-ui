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

package com.ttl.ito.business.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.business.dao.UserProfileDao;
import com.ttl.ito.business.service.UserProfileService;
import com.ttl.ito.common.Utility.SendMail;
import com.ttl.ito.internal.beans.LoginBO;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private Logger logger = Logger.getLogger(UserProfileServiceImpl.class);

	@Autowired
	private UserProfileDao userProfileDao;

	@Autowired
	private SendMail mail;
	
	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Override
	public UserProfileDetails createUser(UserProfileDetails userDetails) {

		try {
			userDetails = userProfileDao.createUser(userDetails);
			
			//Sending mail to notify the user
			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String  subject="New User has been created successfully.";
			
			mailBody.append("Hi "+userDetails.getModifiedBy()+",");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("User details:");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("===============");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Name : "+ userDetails.getEmpName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact number : "+ userDetails.getContactNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Email : "+ userDetails.getEmailId());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Created by : "+ userDetails.getCreatedBy() );
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Created date : "+ userDetails.getCreatedDate());
			mailBody.append(System.getProperty("line.separator"));
			
			String mailText=mailBody.toString();
			
			mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, subject , mailText,files );
			mail.sendMail(new String[] {"jayaseelan@triveniturbines.com"}, null, null, " New User has been created" , mailText,files );

		} catch (Exception e) {
			userDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return userDetails;
	}

	@Override
	public UserProfileDetails editUserProfileDetails(UserProfileDetails userDetails) {
		
		try {
		
			userDetails = userProfileDao.editUserProfileDetails(userDetails);
			
			//Sending mail to notify the user
			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String  subject=" User has been updated successfully.";
			
			//mailBody.append("Hi " +userDetails.getModifiedBy()+",");
			mailBody.append("Hi User");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Please find updated User details:");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("===============");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Name : "+ userDetails.getEmpName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact number : "+ userDetails.getContactNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Email : "+ userDetails.getEmailId());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified by : "+ userDetails.getModifiedBy() );
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified date : "+ userDetails.getModifiedDate());
			mailBody.append(System.getProperty("line.separator"));
			
			String mailText=mailBody.toString();
			
			mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, subject , mailText,files );
			mail.sendMail(new String[] {"jayaseelan@triveniturbines.com"}, null, null, " User has been updated" , mailText,files );
		} catch (Exception e) {
			userDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return userDetails;
	}


}
