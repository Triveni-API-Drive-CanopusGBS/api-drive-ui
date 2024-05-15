package com.ttl.ito.business.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ttl.ito.business.service.LoginService;
import com.ttl.ito.internal.beans.LoginBO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "login")
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	/**
	 * This method is used to login user
	 * @param loginBO
	 * @return LoginBO
	 */
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST, produces = "application/json")
	public LoginBO loginUser(@RequestBody LoginBO loginBO, HttpServletRequest request) {

		try {
			if (null != loginBO.getEmailId() && null != loginBO.getPassword()) {
				loginBO = loginService.loginUserDetails(loginBO);  	//Storing logged in user details to track his modifications throughout the application
				
				loginBO.setFlag(true);
			} else {
				loginBO.setFlag(false);
			}

			return loginBO;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			loginBO.setFlag(false);
			return loginBO;
		}
	}
	
	//Added by Kavya - Start
	
	
	//Forgot password
	/**
	 * This method is used to send the existing password to user via mail when he clicks on forgot Password
	 * @param loginBO
	 * @param request
	 * @return LoginBO
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, produces = "application/json")
	public LoginBO forgotPassword(@RequestBody LoginBO loginBO, HttpServletRequest request) {

		try {
			if (null != loginBO.getEmailId()) {
				loginBO = loginService.forgotPassword(loginBO);
				loginBO.setFlag(true);
			} else {
				loginBO.setFlag(false);
			}

			return loginBO;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			loginBO.setFlag(false);
			return loginBO;
		}
	}

	//Reset password
		/** This method is used to reset the user password of the user
		 * @param loginBO
		 * @param request
		 * @return LoginBO
		 */
		@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = "application/json")
		public LoginBO resetPassword(@RequestBody LoginBO loginBO, HttpServletRequest request) {

			try {
			
				loginBO = loginService.resetPassword(loginBO);
				
				return loginBO;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				loginBO.setFlag(false);
				return loginBO;
			}
		}

		//Added by Kavya - End
	
	@RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
	public boolean logoutUser(HttpServletRequest request) {

		boolean flag = false;

		try {

			HttpSession session = request.getSession(false);
			if (session != null || request.isRequestedSessionIdValid()) {
				request.getSession().removeAttribute("loginBO");
				if (session != null) {
					session.invalidate();
					flag = true;
				}
			}
			
			return flag;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return flag;
		}
	}

}
