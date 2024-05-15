package com.ttl.ito.business.service;

import org.springframework.stereotype.Service;

import com.ttl.ito.internal.beans.LoginBO;

@Service
public interface LoginService {

	/**
	 * This method is used to login user
	 * @param loginBO
	 * @return LoginBO
	 */
	//Added by Kavya - Start
	public LoginBO loginUserDetails(LoginBO loginBO);
	/**
	 * This method is used to send the existing password to user via mail when he clicks on forgot Password
	 * @param loginBO
	 * @return LoginBO
	 */
	public LoginBO forgotPassword(LoginBO loginBO);

	/**
	 * This method is used to reset the user password of the user
	 * @param loginBO
	 * @return LoginBO
	 */
	public LoginBO resetPassword(LoginBO loginBO);
	//Added by Kavya - End
}
