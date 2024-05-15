package com.ttl.ito.business.beans;

import java.util.HashMap;
import java.util.Map;

public class ConsultantDetails {

	private int firmId;
	private int createdById;
	private int modifiedById;
	private int spocId;
	private int successCode;
	
	private String firmName;
	private String spocName;
	private String emailId;
	private String address;
	private String contactNumber;
	private String city;
	private String district;
	private String state;
	private String country;
	private String pincode;
	
	private String createdDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private String successMsg;
	private String oppurtunitySeqNo;
	
	private boolean isSpocActive;
	private boolean isFirmActive;
	private boolean defaultValFirm;
	private boolean defaultValSpoc;
	
	private Map<Integer, String> msgToUser = new HashMap<Integer, String>();

	/**
	 * @return the firmId
	 */
	public int getFirmId() {
		return firmId;
	}

	/**
	 * @param firmId
	 *            the firmId to set
	 */
	public void setFirmId(int firmId) {
		this.firmId = firmId;
	}

	/**
	 * @return the firmName
	 */
	public String getFirmName() {
		return firmName;
	}

	/**
	 * @param firmName
	 *            the firmName to set
	 */
	public void setFirmName(String firmName) {
		this.firmName = firmName;
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
	 * @return the spocId
	 */
	public int getSpocId() {
		return spocId;
	}

	/**
	 * @param spocId
	 *            the spocId to set
	 */
	public void setSpocId(int spocId) {
		this.spocId = spocId;
	}

	/**
	 * @return the spocName
	 */
	public String getSpocName() {
		return spocName;
	}

	/**
	 * @param spocName
	 *            the spocName to set
	 */
	public void setSpocName(String spocName) {
		this.spocName = spocName;
	}

	/**
	 * @return the isFirmActive
	 */
	public boolean isFirmActive() {
		return isFirmActive;
	}

	/**
	 * @param isFirmActive
	 *            the isFirmActive to set
	 */
	public void setFirmActive(boolean isFirmActive) {
		this.isFirmActive = isFirmActive;
	}

	/**
	 * @return the isSpocActive
	 */
	public boolean isSpocActive() {
		return isSpocActive;
	}

	/**
	 * @param isSpocActive
	 *            the isSpocActive to set
	 */
	public void setSpocActive(boolean isSpocActive) {
		this.isSpocActive = isSpocActive;
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
	 * @return the defaultValFirm
	 */
	public boolean isDefaultValFirm() {
		return defaultValFirm;
	}

	/**
	 * @param defaultValFirm the defaultValFirm to set
	 */
	public void setDefaultValFirm(boolean defaultValFirm) {
		this.defaultValFirm = defaultValFirm;
	}

	/**
	 * @return the defaultValSpoc
	 */
	public boolean isDefaultValSpoc() {
		return defaultValSpoc;
	}

	/**
	 * @param defaultValSpoc the defaultValSpoc to set
	 */
	public void setDefaultValSpoc(boolean defaultValSpoc) {
		this.defaultValSpoc = defaultValSpoc;
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
