package com.ttl.ito.business.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ttl.ito.business.dao.LoginDao;
import com.ttl.ito.business.service.LoginService;
import com.ttl.ito.common.Utility.SendMail;
import com.ttl.ito.internal.beans.LoginBO;

@Service
public class LoginServiceImpl implements LoginService {

	private Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDao loginDao;

	@Autowired
	private SendMail sendMail;
	
	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Override
	public LoginBO loginUserDetails(LoginBO loginBO) {

		try {
			loginBO = loginDao.loginUserDetailsDao(loginBO);
			loginBO.getMsgToUser().put(String.valueOf(loginBO.getSuccessCode()), loginBO.getSuccessMsg());
		} catch (Exception e) {
			loginBO.getMsgToUser().put("-1", TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		

		return loginBO;
	}

	//Added by Kavya - Start
	
	@Override
	public LoginBO forgotPassword(LoginBO loginBO) {
		
		try {
			loginBO = loginDao.forgotPassword(loginBO);
			String toAddress = loginBO.getEmailId();
			java.io.File dir = new java.io.File("");
			java.io.File[] attachments= dir.listFiles();
			
			StringBuffer mailBody = new StringBuffer();
		
			mailBody.append("Hi "+loginBO.getName()+",");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Please find your credentials below, and reset the password immediately.");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Password : "+loginBO.getPassword());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Thanks and Regards, ");
			mailBody.append("ITO Team");
			String mailText=mailBody.toString();
			List<String> files= new ArrayList<String>();
			//files.add("C:/Users/Public/Pictures/Sample Pictures/Tulips.jpg");
			files.add("C:/Users/Public/Pictures/TTL_Brand.png");
			logger.info("Level1");
			sendMail.send(new String[] {toAddress}, null, null, "Password Management" , mailText,attachments );
			logger.info("Level2");
			sendMail.send(new String[] {"itoapplication@triveniturbines.com"}, null, null, "Password Management" , mailText,attachments );
			logger.info("Level3");
			sendMail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, "Password Management" , mailText,files );
			
			loginBO.setPassword(""); // setting password to null
			loginBO.getMsgToUser().put(String.valueOf(loginBO.getSuccessCode()), loginBO.getSuccessMsg());
		} catch (Exception e) {
			loginBO.getMsgToUser().put("-1", TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		
		return loginBO;
	}

	@Override
	public LoginBO resetPassword(LoginBO loginBO) {
		
		try {
			loginBO = loginDao.resetPassword(loginBO);
			String toAddress = loginBO.getEmailId();

	        StringBuffer mailBody = new StringBuffer();

	        mailBody.append("Hi "+loginBO.getName()+",");
	        mailBody.append(System.getProperty("line.separator"));
	        mailBody.append("Your Password has been changed");
	        mailBody.append(System.getProperty("line.separator"));
	        mailBody.append("Modified by : "+loginBO.getModifyBy());
	        mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Thanks and Regards, ");
			mailBody.append("ITO Team");
	        String mailText=mailBody.toString();
	        List<String> files= new ArrayList<String>();
	       sendMail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, "Password Management" , mailText,files );

	        loginBO.setPassword(""); // setting password to null
	        loginBO.setNewPassword(""); // setting password to null
	        loginBO.getMsgToUser().put(String.valueOf(loginBO.getSuccessCode()), loginBO.getSuccessMsg());

		} catch (Exception e) {
			loginBO.getMsgToUser().put("-1", TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return loginBO;
	}
	//Added by Kavya - End
	
}
