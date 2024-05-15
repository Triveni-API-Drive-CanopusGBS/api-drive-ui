package com.ttl.ito.business.beans;

import org.springframework.stereotype.Component;

@Component
public class QuotationHomeGrid {

	private int quotId;
	private int custId;
	private String custName;
	private int frameId;
	private int framePowerId;
	private String frameName;
	private String capacity;
	private String quotNumber;
	private String cNewNumber;
	private String cOldNumber;
	private String opportunitySeqNum;
	private String oppName;
	private String custType;
	private String region;
	private String isActive;
	private String statusCode;
	private int statusId;
	private String statusName;
	private String custPlace;
	private String createdDate;
	private String createdBy;
	private String modifyDate;
	private String modifyBy;
	private int modifyById;
	private int createdById;
	private int assignedToId;
	private int userRoleId;
	private String assignedTo;
	private int successCode;
	private String successMsg;

	public QuotationHomeGrid() {
		super();
	}

	public int getQuotId() {
		return quotId;
	}

	public void setQuotId(int quotId) {
		this.quotId = quotId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getFrameId() {
		return frameId;
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}

	public String getFrameName() {
		return frameName;
	}

	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getQuotNumber() {
		return quotNumber;
	}

	public void setQuotNumber(String quotNumber) {
		this.quotNumber = quotNumber;
	}

	public String getcNewNumber() {
		return cNewNumber;
	}

	public void setcNewNumber(String cNewNumber) {
		this.cNewNumber = cNewNumber;
	}

	public String getcOldNumber() {
		return cOldNumber;
	}

	public void setcOldNumber(String cOldNumber) {
		this.cOldNumber = cOldNumber;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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


	public String getCustPlace() {
		return custPlace;
	}

	public void setCustPlace(String custPlace) {
		this.custPlace = custPlace;
	}

	/**
	 * @return the opportunitySeqNum
	 */
	public String getOpportunitySeqNum() {
		return opportunitySeqNum;
	}

	/**
	 * @param opportunitySeqNum the opportunitySeqNum to set
	 */
	public void setOpportunitySeqNum(String opportunitySeqNum) {
		this.opportunitySeqNum = opportunitySeqNum;
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
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the modifyDate
	 */
	public String getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the modifyBy
	 */
	public String getModifyBy() {
		return modifyBy;
	}

	/**
	 * @param modifyBy the modifyBy to set
	 */
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	/**
	 * @return the modifyById
	 */
	public int getModifyById() {
		return modifyById;
	}

	/**
	 * @param modifyById the modifyById to set
	 */
	public void setModifyById(int modifyById) {
		this.modifyById = modifyById;
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
	 * @return the assignedToId
	 */
	public int getAssignedToId() {
		return assignedToId;
	}

	/**
	 * @param assignedToId the assignedToId to set
	 */
	public void setAssignedToId(int assignedToId) {
		this.assignedToId = assignedToId;
	}

	/**
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
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
	 * @return the framePowerId
	 */
	public int getFramePowerId() {
		return framePowerId;
	}

	/**
	 * @param framePowerId the framePowerId to set
	 */
	public void setFramePowerId(int framePowerId) {
		this.framePowerId = framePowerId;
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
	private String name;
	private String email;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
