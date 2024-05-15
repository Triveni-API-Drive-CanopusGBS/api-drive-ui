package com.ttl.ito.business.beans;

import java.util.HashMap;
import java.util.Map;

public class CustomerDetails {

	private int custId;
	private int createdById;
	private int modifiedById;
	private int successCode;
	private int custRegionId;
	private int stateId;
	
	private String oppName;
	private String custName;
	private String custCode;
	private String custType;
	private String region;
	private String statusCode;
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
	private String consultantName;
	private String consultantSpocName;
	private String firmName;
	private String endUserName;
	private String consultantAddress;
	private String endUserAddress;
	private String endUserType;
	private String oppurtunitySeqNo;
	private String consultantEmailId;
	private String consultantContactNumber;
	private String endUserEmailId;
	private String endUserContactNumber;
	private String sapAddress;
	private String customerStatus;
	private String custRegion;
	private String custRegionCode;
	private String sfdcLink;
	private String custSapCode;
	
	private String oppContactName;
	public String getOppContactName() {
		return oppContactName;
	}
	public void setOppContactName(String oppContactName) {
		this.oppContactName = oppContactName;
	}
	public String getOppContactEmail() {
		return oppContactEmail;
	}
	public void setOppContactEmail(String oppContactEmail) {
		this.oppContactEmail = oppContactEmail;
	}
	public String getOppContactPhone() {
		return oppContactPhone;
	}
	public void setOppContactPhone(String oppContactPhone) {
		this.oppContactPhone = oppContactPhone;
	}
	public String getOppContactAddress() {
		return oppContactAddress;
	}
	public void setOppContactAddress(String oppContactAddress) {
		this.oppContactAddress = oppContactAddress;
	}
	public String getOppContactStateName() {
		return oppContactStateName;
	}
	public void setOppContactStateName(String oppContactStateName) {
		this.oppContactStateName = oppContactStateName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getIsEndUserAvailable() {
		return isEndUserAvailable;
	}
	public void setIsEndUserAvailable(String isEndUserAvailable) {
		this.isEndUserAvailable = isEndUserAvailable;
	}
	public String getEndUserCustType() {
		return endUserCustType;
	}
	public void setEndUserCustType(String endUserCustType) {
		this.endUserCustType = endUserCustType;
	}
	public String getEndUserStateName() {
		return endUserStateName;
	}
	public void setEndUserStateName(String endUserStateName) {
		this.endUserStateName = endUserStateName;
	}
	private String oppContactEmail;
	private String oppContactPhone;
	private String oppContactAddress;
	private String oppContactStateName;
	private String stateName;
	private String isEndUserAvailable;
	private String endUserCustType;
	private String endUserStateName;


	
	
	private boolean defaultVal;
	private boolean isActive;
	
	
	private Map<Integer, String> msgToUser = new HashMap<Integer, String>();
	
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
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
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}
	/**
	 * @param custCode the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	/**
	 * @return the contactPersonName
	 */
	public String getContactPersonName() {
		return contactPersonName;
	}
	/**
	 * @param contactPersonName the contactPersonName to set
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
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
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
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/**
	 * @return the custId
	 */
	public int getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}
	/**
	 * @return the msgToUser
	 */
	public Map<Integer, String> getMsgToUser() {
		return msgToUser;
	}
	/**
	 * @param msgToUser the msgToUser to set
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
	 * @param createdById the createdById to set
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
	 * @param modifiedById the modifiedById to set
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
	 * @param createdBy the createdBy to set
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
	 * @param modifiedBy the modifiedBy to set
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
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
	/**
	 * @return the consultantName
	 */
	public String getConsultantName() {
		return consultantName;
	}
	/**
	 * @param consultantName the consultantName to set
	 */
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	/**
	 * @return the endUserName
	 */
	public String getEndUserName() {
		return endUserName;
	}
	/**
	 * @param endUserName the endUserName to set
	 */
	public void setEndUserName(String endUserName) {
		this.endUserName = endUserName;
	}
	/**
	 * @return the consultantAddress
	 */
	public String getConsultantAddress() {
		return consultantAddress;
	}
	/**
	 * @param consultantAddress the consultantAddress to set
	 */
	public void setConsultantAddress(String consultantAddress) {
		this.consultantAddress = consultantAddress;
	}
	/**
	 * @return the endUserAddress
	 */
	public String getEndUserAddress() {
		return endUserAddress;
	}
	/**
	 * @param endUserAddress the endUserAddress to set
	 */
	public void setEndUserAddress(String endUserAddress) {
		this.endUserAddress = endUserAddress;
	}
	/**
	 * @return the endUserType
	 */
	public String getEndUserType() {
		return endUserType;
	}
	/**
	 * @param endUserType the endUserType to set
	 */
	public void setEndUserType(String endUserType) {
		this.endUserType = endUserType;
	}
	/**
	 * @return the firmName
	 */
	public String getFirmName() {
		return firmName;
	}
	/**
	 * @param firmName the firmName to set
	 */
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	/**
	 * @return the consultantEmailId
	 */
	public String getConsultantEmailId() {
		return consultantEmailId;
	}
	/**
	 * @param consultantEmailId the consultantEmailId to set
	 */
	public void setConsultantEmailId(String consultantEmailId) {
		this.consultantEmailId = consultantEmailId;
	}
	/**
	 * @return the consultantContactNumber
	 */
	public String getConsultantContactNumber() {
		return consultantContactNumber;
	}
	/**
	 * @param consultantContactNumber the consultantContactNumber to set
	 */
	public void setConsultantContactNumber(String consultantContactNumber) {
		this.consultantContactNumber = consultantContactNumber;
	}
	/**
	 * @return the endUserEmailId
	 */
	public String getEndUserEmailId() {
		return endUserEmailId;
	}
	/**
	 * @param endUserEmailId the endUserEmailId to set
	 */
	public void setEndUserEmailId(String endUserEmailId) {
		this.endUserEmailId = endUserEmailId;
	}
	/**
	 * @return the endUserContactNumber
	 */
	public String getEndUserContactNumber() {
		return endUserContactNumber;
	}
	/**
	 * @param endUserContactNumber the endUserContactNumber to set
	 */
	public void setEndUserContactNumber(String endUserContactNumber) {
		this.endUserContactNumber = endUserContactNumber;
	}
	/**
	 * @return the consultantSpocName
	 */
	public String getConsultantSpocName() {
		return consultantSpocName;
	}
	/**
	 * @param consultantSpocName the consultantSpocName to set
	 */
	public void setConsultantSpocName(String consultantSpocName) {
		this.consultantSpocName = consultantSpocName;
	}
	/**
	 * @return the sapAddress
	 */
	public String getSapAddress() {
		return sapAddress;
	}
	/**
	 * @param sapAddress the sapAddress to set
	 */
	public void setSapAddress(String sapAddress) {
		this.sapAddress = sapAddress;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the customerStatus
	 */
	public String getCustomerStatus() {
		return customerStatus;
	}
	/**
	 * @param customerStatus the customerStatus to set
	 */
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	/**
	 * @return the custRegion
	 */
	public String getCustRegion() {
		return custRegion;
	}
	/**
	 * @param custRegion the custRegion to set
	 */
	public void setCustRegion(String custRegion) {
		this.custRegion = custRegion;
	}
	/**
	 * @return the custRegionCode
	 */
	public String getCustRegionCode() {
		return custRegionCode;
	}
	/**
	 * @param custRegionCode the custRegionCode to set
	 */
	public void setCustRegionCode(String custRegionCode) {
		this.custRegionCode = custRegionCode;
	}
	/**
	 * @return the custRegionId
	 */
	public int getCustRegionId() {
		return custRegionId;
	}
	/**
	 * @param custRegionId the custRegionId to set
	 */
	public void setCustRegionId(int custRegionId) {
		this.custRegionId = custRegionId;
	}
	/**
	 * @return the sfdcLink
	 */
	public String getSfdcLink() {
		return sfdcLink;
	}
	/**
	 * @param sfdcLink the sfdcLink to set
	 */
	public void setSfdcLink(String sfdcLink) {
		this.sfdcLink = sfdcLink;
	}
	/**
	 * @return the custSapCode
	 */
	public String getCustSapCode() {
		return custSapCode;
	}
	/**
	 * @param custSapCode the custSapCode to set
	 */
	public void setCustSapCode(String custSapCode) {
		this.custSapCode = custSapCode;
	}
	/**
	 * @return the oppName
	 */
	public String getOppName() {
		return oppName;
	}
	/**
	 * @param oppName the oppName to set
	 */
	public void setOppName(String oppName) {
		this.oppName = oppName;
	}
	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	
}
