package com.ttl.ito.business.beans;

import java.util.HashMap;
import java.util.Map;

public class EndUserDetails {

	private int endUserId;
	private int successCode;
	private int createdById;
	private int modifiedById;
	
	private String endUserName;
	private String contactPersonName;
	private String emailId;
	private String contactNumber;
	private String city;
	private String district;
	private String state;
	private String country;
	private String pincode;
	private String address;
	private String createdDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private String successMsg;
	private String custType;
	private String oppurtunitySeqNo;
	private String sapCode;
	
	private boolean defaultVal;
	private boolean isActive;

	private Map<Integer, String> msgToUser = new HashMap<Integer, String>();

	/**
	 * @return the endUserId
	 */
	public int getEndUserId() {
		return endUserId;
	}

	/**
	 * @param endUserId
	 *            the endUserId to set
	 */
	public void setEndUserId(int endUserId) {
		this.endUserId = endUserId;
	}

	/**
	 * @return the endUserName
	 */
	public String getEndUserName() {
		return endUserName;
	}

	/**
	 * @param endUserName
	 *            the endUserName to set
	 */
	public void setEndUserName(String endUserName) {
		this.endUserName = endUserName;
	}

	/**
	 * @return the contactPersonName
	 */
	public String getContactPersonName() {
		return contactPersonName;
	}

	/**
	 * @param contactPersonName
	 *            the contactPersonName to set
	 */
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the msgToUser
	 */
	public Map<Integer, String> getMsgToUser() {
		return msgToUser;
	}

	/**
	 * @param msgToUser
	 *            the msgToUser to set
	 */
	public void setMsgToUser(Map<Integer, String> msgToUser) {
		this.msgToUser = msgToUser;
	}

	/**
	 * @return the createdById
	 */
	public int getCreatedById() {
		return createdById;
	}

	/**
	 * @param createdById
	 *            the createdById to set
	 */
	public void setCreatedById(int createdById) {
		this.createdById = createdById;
	}

	/**
	 * @return the modifiedById
	 */
	public int getModifiedById() {
		return modifiedById;
	}

	/**
	 * @param modifiedById
	 *            the modifiedById to set
	 */
	public void setModifiedById(int modifiedById) {
		this.modifiedById = modifiedById;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the sapCode
	 */
	public String getSapCode() {
		return sapCode;
	}

	/**
	 * @param sapCode
	 *            the sapCode to set
	 */
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the successCode
	 */
	public int getSuccessCode() {
		return successCode;
	}

	/**
	 * @param successCode the successCode to set
	 */
	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}

	/**
	 * @return the successMsg
	 */
	public String getSuccessMsg() {
		return successMsg;
	}

	/**
	 * @param successMsg the successMsg to set
	 */
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	/**
	 * @return the defaultVal
	 */
	public boolean isDefaultVal() {
		return defaultVal;
	}

	/**
	 * @param defaultVal the defaultVal to set
	 */
	public void setDefaultVal(boolean defaultVal) {
		this.defaultVal = defaultVal;
	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the oppurtunitySeqNo
	 */
	public String getOppurtunitySeqNo() {
		return oppurtunitySeqNo;
	}

	/**
	 * @param oppurtunitySeqNo the oppurtunitySeqNo to set
	 */
	public void setOppurtunitySeqNo(String oppurtunitySeqNo) {
		this.oppurtunitySeqNo = oppurtunitySeqNo;
	}

}
