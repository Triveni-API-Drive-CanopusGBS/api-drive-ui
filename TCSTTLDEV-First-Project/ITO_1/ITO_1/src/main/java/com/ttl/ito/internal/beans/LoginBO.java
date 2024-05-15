package com.ttl.ito.internal.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ttl.ito.business.beans.SelectBox;



@Component
public class LoginBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String emailId;
	private String password;
	private String newPassword;
	private String name;
	private String phoneNumber;
	private String dept;
	private String createdDate;
	private String createdBy;
	private String ModifyDate;
	private String ModifyBy;
	private String successMsg;
	private String image;
	private String designation;
	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int userId;
	private int empId;
	private int userRoleId;
	private int successCode;
	private int deptId;
	
	private boolean flag;
	
	private byte[] img;
	private boolean isActive;
	
	private Date lastLoggedInDate;
	
	List<SelectBox> userRegionsList = new ArrayList<SelectBox>(); // Regions List
	List<SelectBox> userRolesList = new ArrayList<SelectBox>(); // Roles List
	
	Map<String,String> msgToUser = new HashMap<String,String>();


	public LoginBO() {
		super();
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifyDate() {
		return ModifyDate;
	}

	public void setModifyDate(String modifyDate) {
		ModifyDate = modifyDate;
	}

	public String getModifyBy() {
		return ModifyBy;
	}

	public void setModifyBy(String modifyBy) {
		ModifyBy = modifyBy;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getSuccessCode() {
		return successCode;
	}

	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public Map<String, String> getMsgToUser() {
		return msgToUser;
	}

	public void setMsgToUser(Map<String, String> msgToUser) {
		this.msgToUser = msgToUser;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the img
	 */
	public byte[] getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(byte[] img) {
		this.img = img;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the userRegionsList
	 */
	public List<SelectBox> getUserRegionsList() {
		return userRegionsList;
	}

	/**
	 * @param userRegionsList the userRegionsList to set
	 */
	public void setUserRegionsList(List<SelectBox> userRegionsList) {
		this.userRegionsList = userRegionsList;
	}

	/**
	 * @return the userRolesList
	 */
	public List<SelectBox> getUserRolesList() {
		return userRolesList;
	}

	/**
	 * @param userRolesList the userRolesList to set
	 */
	public void setUserRolesList(List<SelectBox> userRolesList) {
		this.userRolesList = userRolesList;
	}

	/**
	 * @return the lastLoggedInDate
	 */
	public Date getLastLoggedInDate() {
		return lastLoggedInDate;
	}

	/**
	 * @param lastLoggedInDate the lastLoggedInDate to set
	 */
	public void setLastLoggedInDate(Date lastLoggedInDate) {
		this.lastLoggedInDate = lastLoggedInDate;
	}

	/**
	 * @return the deptId
	 */
	public int getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the userRoleId
	 */
	public int getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

}
