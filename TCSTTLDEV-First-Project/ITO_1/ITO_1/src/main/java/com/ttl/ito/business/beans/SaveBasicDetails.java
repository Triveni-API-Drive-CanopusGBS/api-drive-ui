package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ttl.ito.internal.beans.ItoConstants;

@Component
public class SaveBasicDetails {
	
	private float overwrittenCost;
	public float getOverwrittenCost() {
		return overwrittenCost;
	}
	public void setOverwrittenCost(float overwrittenCost) {
		this.overwrittenCost = overwrittenCost;
	}
	private int editFlagNew;
	private int stateId;
	public int getEditFlagNew() {
		return editFlagNew;
	}
	public void setEditFlagNew(int editFlagNew) {
		this.editFlagNew = editFlagNew;
	}
	private int custId;
	private int quotId;
	private int assignedTo;
	private int regionId;
	private int firmId;
	private int scopeId;
	private int numberOfBleeds;
	private int numberOfextractors;
	private int modelTypeId;
	private int orientationTypeId;
	private int statusId;
	private int createdById;
	private int modifiedById;
	private int isFrameUpdated;
	private int bleedTypeId;
	private int bladeTypeId;
	private int FramePowerId;
	private int spocId;
	private int transportationType;
	private int packageTypeId;
	private int loggedInUserCode;
	private int endUserId;
	private int allDataFlag;
	private int transportTotalPrice;
	private int frameId;
	private int successCode;
	private int condensingTypeId;
	private int userRoleId;
	private int inQuotId;
	public int getInQuotId() {
		return inQuotId;
	}
	public void setInQuotId(int inQuotId) {
		this.inQuotId = inQuotId;
	}
	private int isNewProject; 
	private int isEndUserReq;
	private int improvedImpulse;
	public int getImprovedImpulse() {
		return improvedImpulse;
	}
	public void setImprovedImpulse(int improvedImpulse) {
		this.improvedImpulse = improvedImpulse;
	}
	private String  isEndUserAvailable;
	private String enquiryReference;
	
	public String getEnquiryReference() {
		return enquiryReference;
	}
	public void setEnquiryReference(String enquiryReference) {
		this.enquiryReference = enquiryReference;
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
	public String getOppContactStatName() {
		return oppContactStatName;
	}
	public void setOppContactStatName(String oppContactStatName) {
		this.oppContactStatName = oppContactStatName;
	}
	private String endUserCustType;
	private String oppContactName;
	private String oppContactEmail;
	private String oppContactPhone;
	private String oppContactAddress;
	private String oppContactStatName;

	private int revNum; 
	private int portId;
	private int scopeStatusFlg;
	private int quotStatusFlg;
	private int updateRequestNumber;
	private String sfdcLink;
	private String typeOfCustomer;
	private String typeOfOfferCode;
	private String TypeOfQuotCode;
	private String updateCode;
	private String typOfBladeCode;
	private String typOfBlade;
	private String successMsg;
	private String typeOfTurbine;
	private String condensingTypeName;
	private String opportunitySeqNum;
	private String oppName;
	private String custName;
	private String stateNm;
	private String custType;
	private String custCode;
	private String custSapCode;
	private String scopeCode;
	private String endUserType;
	private String EndUserCode;
	private String hasConsultant;
	private String firmName;
	private String spocName;
	private String hasEndUser;
	private String endUserName;
	private String frameName;
	private String quotNumber;
	private String region;
	private String modelType;
	private String orientationType;
	private String statusCode;
	private String groupCode;
	private String statusName;
	private String variantCode;
	private String cNewNumber;
	private String cOldNumber;
	private String createdDate;
	private String modifiedDate;
	private String modifiedBy;
	private String createdBy;
	private String selectedCProjectValue;
	private String selectedCProjectKey;
	private String bleedType;
	private String turbineCode;
	private String latestCNum;
	private String creatorEmail;
	private String creatorEmpId;
	private String creatorPhoneNumber;
	private String creatorDeptName;
	private String assignedToName;
	private String assignedEmail;
	private String assignedEmpId;
	private String assignedPhoneNumber;
	private String displayReqNumber;
	private String remarks;
	private String custStatus;
	private String regionCode;
	private String transTypeCode;
	private String userRoleName;
	
	
	private String custContactPersonName;
	private String custContactNumber;
	private String custEmailId;
	private String custAddress;
	private String custPincode;
	private String consultantName;
	private String accountName;
	private String consultantSpocName;
	private String consultantContactNo;
	private String consultantEmail;
	private String consultantAddress;
	private String endUserContactNo;
	private String endUserEmail;
	private String endUserAddress;
	private String custCodeNm;
	
	private boolean isActive;
	private boolean latestCNumFlag;
	private boolean editFlag;
	private boolean completeFlag;
	private boolean submitFlag;
	private boolean statusFlag;
	
	private int typeOfOffer;
	private int typeOfQuot;
	private int typeOfInjection;
	private int typeOfVarient;
	private String typeOfOfferNm;
	private String typeOfQuotNm;
	private String typeOfInjectionNm;
	private String typeOfVarientNm;
	private String endUserStateNm;
	public String getEndUserStateNm() {
		return endUserStateNm;
	}
	public void setEndUserStateNm(String endUserStateNm) {
		this.endUserStateNm = endUserStateNm;
	}
	private float capacity;
	private float power;
	private float percentageVariation;
	
	

	private int categoryDetId;
	private String categoryDetCode;
	private String CategoryDetDesc;
	private Date categoryCreatedDate;
	private Date categoryModifiedDate;
	private int categoryCreatedBy;
	private int categoryModifiedBy;
	private boolean isCategoryActive;
	
	private String  designation;
	
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	private List<Integer> zCount = new ArrayList<>();
	private List<Integer> revList = new ArrayList<>();
	private List<Integer> scopeOfSupply = new ArrayList<>();
	
	private List<CommentBean> remarksList = new ArrayList<CommentBean>();
	
	private List<ScopeOfSupply> scopeOfSupplyList = new ArrayList<>();
	
	private List<TransportationDetailsBean> transportVehicalCostList = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> transDetailList = new ArrayList<TransportationDetailsBean>();
	
	private Map<Integer, String> msgToUser = new HashMap<Integer, String>();
	private Map<String, String> cProjectList = new HashMap<String, String>();	
	
	F2FForm sapData = new F2FForm();
	F2FForm uboData = new F2FForm();
	
	CommentBean commentBean = new CommentBean();
	TransportationDetailsBean transportBean= new TransportationDetailsBean();
	
	
	public SaveBasicDetails() {
		super();
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
	 * @return the endUserId
	 */
	public int getEndUserId() {
		return endUserId;
	}
	/**
	 * @param endUserId the endUserId to set
	 */
	public void setEndUserId(int endUserId) {
		this.endUserId = endUserId;
	}
	/**
	 * @return the frameId
	 */
	public int getFrameId() {
		return frameId;
	}
	/**
	 * @param frameId the frameId to set
	 */
	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}
	/**
	 * @return the frameName
	 */
	public String getFrameName() {
		return frameName;
	}
	/**
	 * @param frameName the frameName to set
	 */
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}
	
	/**
	 * @return the typeOfTurbine
	 */
	public String getTypeOfTurbine() {
		return typeOfTurbine;
	}
	/**
	 * @param typeOfTurbine the typeOfTurbine to set
	 */
	public void setTypeOfTurbine(String typeOfTurbine) {
		this.typeOfTurbine = typeOfTurbine;
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
	 * @return the assignedTo
	 */
	public int getAssignedTo() {
		return assignedTo;
	}
	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}
	/**
	 * @return the regionId
	 */
	public int getRegionId() {
		return regionId;
	}
	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	/**
	 * @return the cProjectList
	 */
	public Map<String, String> getcProjectList() {
		return cProjectList;
	}
	/**
	 * @param cProjectList the cProjectList to set
	 */
	public void setcProjectList(Map<String, String> cProjectList) {
		this.cProjectList = cProjectList;
	}
	/**
	 * @return the selectedCProjectValue
	 */
	public String getSelectedCProjectValue() {
		return selectedCProjectValue;
	}
	/**
	 * @param selectedCProjectValue the selectedCProjectValue to set
	 */
	public void setSelectedCProjectValue(String selectedCProjectValue) {
		this.selectedCProjectValue = selectedCProjectValue;
	}
	/**
	 * @return the selectedCProjectKey
	 */
	public String getSelectedCProjectKey() {
		return selectedCProjectKey;
	}
	/**
	 * @param selectedCProjectKey the selectedCProjectKey to set
	 */
	public void setSelectedCProjectKey(String selectedCProjectKey) {
		this.selectedCProjectKey = selectedCProjectKey;
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
	 * @return the hasConsultant
	 */
	public String getHasConsultant() {
		return hasConsultant;
	}
	/**
	 * @param hasConsultant the hasConsultant to set
	 */
	public void setHasConsultant(String hasConsultant) {
		this.hasConsultant = hasConsultant;
	}
	/**
	 * @return the firmId
	 */
	public int getFirmId() {
		return firmId;
	}
	/**
	 * @param firmId the firmId to set
	 */
	public void setFirmId(int firmId) {
		this.firmId = firmId;
	}
	/**
	 * @return the spocId
	 */
	public int getSpocId() {
		return spocId;
	}
	/**
	 * @param spocId the spocId to set
	 */
	public void setSpocId(int spocId) {
		this.spocId = spocId;
	}
	/**
	 * @return the hasEndUser
	 */
	public String getHasEndUser() {
		return hasEndUser;
	}
	/**
	 * @param hasEndUser the hasEndUser to set
	 */
	public void setHasEndUser(String hasEndUser) {
		this.hasEndUser = hasEndUser;
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
	 * @return the scopeId
	 */
	public int getScopeId() {
		return scopeId;
	}
	/**
	 * @param scopeId the scopeId to set
	 */
	public void setScopeId(int scopeId) {
		this.scopeId = scopeId;
	}
	/**
	 * @return the numberOfBleeds
	 */
	public int getNumberOfBleeds() {
		return numberOfBleeds;
	}
	/**
	 * @param numberOfBleeds the numberOfBleeds to set
	 */
	public void setNumberOfBleeds(int numberOfBleeds) {
		this.numberOfBleeds = numberOfBleeds;
	}
	/**
	 * @return the numberOfextractors
	 */
	public int getNumberOfextractors() {
		return numberOfextractors;
	}
	/**
	 * @param numberOfextractors the numberOfextractors to set
	 */
	public void setNumberOfextractors(int numberOfextractors) {
		this.numberOfextractors = numberOfextractors;
	}
	/**
	 * @return the modelTypeId
	 */
	public int getModelTypeId() {
		return modelTypeId;
	}
	/**
	 * @param modelTypeId the modelTypeId to set
	 */
	public void setModelTypeId(int modelTypeId) {
		this.modelTypeId = modelTypeId;
	}
	/**
	 * @return the orientationTypeId
	 */
	public int getOrientationTypeId() {
		return orientationTypeId;
	}
	/**
	 * @param orientationTypeId the orientationTypeId to set
	 */
	public void setOrientationTypeId(int orientationTypeId) {
		this.orientationTypeId = orientationTypeId;
	}
	/**
	 * @return the spocName
	 */
	public String getSpocName() {
		return spocName;
	}
	/**``
	 * @param spocName the spocName to set
	 */
	public void setSpocName(String spocName) {
		this.spocName = spocName;
	}
	
	/**
	 * @return the condensingTypeName
	 */
	public String getCondensingTypeName() {
		return condensingTypeName;
	}
	/**
	 * @param condensingTypeName the condensingTypeName to set
	 */
	public void setCondensingTypeName(String condensingTypeName) {
		this.condensingTypeName = condensingTypeName;
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
	 * @return the orientationType
	 */
	public String getOrientationType() {
		return orientationType;
	}
	/**
	 * @param orientationType the orientationType to set
	 */
	public void setOrientationType(String orientationType) {
		this.orientationType = orientationType;
	}
	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * @return the cNewNumber
	 */
	public String getcNewNumber() {
		return cNewNumber;
	}
	/**
	 * @param cNewNumber the cNewNumber to set
	 */
	public void setcNewNumber(String cNewNumber) {
		this.cNewNumber = cNewNumber;
	}
	/**
	 * @return the cOldNumber
	 */
	public String getcOldNumber() {
		return cOldNumber;
	}
	/**
	 * @param cOldNumber the cOldNumber to set
	 */
	public void setcOldNumber(String cOldNumber) {
		this.cOldNumber = cOldNumber;
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
	 * @return the scopeOfSupply
	 */
	public List<Integer> getScopeOfSupply() {
		return scopeOfSupply;
	}
	/**
	 * @param scopeOfSupply the scopeOfSupply to set
	 */
	public void setScopeOfSupply(List<Integer> scopeOfSupply) {
		this.scopeOfSupply = scopeOfSupply;
	}
	/**
	 * @return the modelType
	 */
	public String getModelType() {
		return modelType;
	}
	/**
	 * @param modelType the modelType to set
	 */
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	/**
	 * @return the capacity
	 */
	public float getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}
	/**
	 * @return the isFrameUpdated
	 */
	public int getIsFrameUpdated() {
		return isFrameUpdated;
	}
	/**
	 * @param isFrameUpdated the isFrameUpdated to set
	 */
	public void setIsFrameUpdated(int isFrameUpdated) {
		this.isFrameUpdated = isFrameUpdated;
	}
	/**
	 * @return the quotId
	 */
	public int getQuotId() {
		return quotId;
	}
	/**
	 * @param quotId the quotId to set
	 */
	public void setQuotId(int quotId) {
		this.quotId = quotId;
	}
	/**
	 * @return the quotNumber
	 */
	public String getQuotNumber() {
		return quotNumber;
	}
	/**
	 * @param quotNumber the quotNumber to set
	 */
	public void setQuotNumber(String quotNumber) {
		this.quotNumber = quotNumber;
	}
	/**
	 * @return the scopeOfSupplyList
	 */
	public List<ScopeOfSupply> getScopeOfSupplyList() {
		return scopeOfSupplyList;
	}
	/**
	 * @param scopeOfSupplyList the scopeOfSupplyList to set
	 */
	public void setScopeOfSupplyList(List<ScopeOfSupply> scopeOfSupplyList) {
		this.scopeOfSupplyList = scopeOfSupplyList;
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
	 * @return the framePowerId
	 */
	public int getFramePowerId() {
		return FramePowerId;
	}
	/**
	 * @param framePowerId the framePowerId to set
	 */
	public void setFramePowerId(int framePowerId) {
		FramePowerId = framePowerId;
	}
	/**
	 * @return the turbineCode
	 */
	public String getTurbineCode() {
		return turbineCode;
	}
	/**
	 * @param turbineCode the turbineCode to set
	 */
	public void setTurbineCode(String turbineCode) {
		this.turbineCode = turbineCode;
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
	 * @return the assignedToName
	 */
	public String getAssignedToName() {
		return assignedToName;
	}
	/**
	 * @param assignedToName the assignedToName to set
	 */
	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}
	/**
	 * @return the assignedEmail
	 */
	public String getAssignedEmail() {
		return assignedEmail;
	}
	/**
	 * @param assignedEmail the assignedEmail to set
	 */
	public void setAssignedEmail(String assignedEmail) {
		this.assignedEmail = assignedEmail;
	}
	/**
	 * @return the assignedEmpId
	 */
	public String getAssignedEmpId() {
		return assignedEmpId;
	}
	/**
	 * @param assignedEmpId the assignedEmpId to set
	 */
	public void setAssignedEmpId(String assignedEmpId) {
		this.assignedEmpId = assignedEmpId;
	}
	/**
	 * @return the assignedPhoneNumber
	 */
	public String getAssignedPhoneNumber() {
		return assignedPhoneNumber;
	}
	/**
	 * @param assignedPhoneNumber the assignedPhoneNumber to set
	 */
	public void setAssignedPhoneNumber(String assignedPhoneNumber) {
		this.assignedPhoneNumber = assignedPhoneNumber;
	}
	/**
	 * @return the variantCode
	 */
	public String getVariantCode() {
		return variantCode;
	}
	/**
	 * @param variantCode the variantCode to set
	 */
	public void setVariantCode(String variantCode) {
		this.variantCode = variantCode;
	}
	/**
	 * @return the creatorEmail
	 */
	public String getCreatorEmail() {
		return creatorEmail;
	}
	/**
	 * @param creatorEmail the creatorEmail to set
	 */
	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}
	/**
	 * @return the creatorEmpId
	 */
	public String getCreatorEmpId() {
		return creatorEmpId;
	}
	/**
	 * @param creatorEmpId the creatorEmpId to set
	 */
	public void setCreatorEmpId(String creatorEmpId) {
		this.creatorEmpId = creatorEmpId;
	}
	/**
	 * @return the creatorPhoneNumber
	 */
	public String getCreatorPhoneNumber() {
		return creatorPhoneNumber;
	}
	/**
	 * @param creatorPhoneNumber the creatorPhoneNumber to set
	 */
	public void setCreatorPhoneNumber(String creatorPhoneNumber) {
		this.creatorPhoneNumber = creatorPhoneNumber;
	}
	/**
	 * @return the creatorDeptName
	 */
	public String getCreatorDeptName() {
		return creatorDeptName;
	}
	/**
	 * @param creatorDeptName the creatorDeptName to set
	 */
	public void setCreatorDeptName(String creatorDeptName) {
		this.creatorDeptName = creatorDeptName;
	}
	/**
	 * @return the latestCNum
	 */
	public String getLatestCNum() {
		return latestCNum;
	}
	/**
	 * @param latestCNum the latestCNum to set
	 */
	public void setLatestCNum(String latestCNum) {
		this.latestCNum = latestCNum;
	}
	/**
	 * @return the latestCNumFlag
	 */
	public boolean isLatestCNumFlag() {
		return latestCNumFlag;
	}
	/**
	 * @param latestCNumFlag the latestCNumFlag to set
	 */
	public void setLatestCNumFlag(boolean latestCNumFlag) {
		this.latestCNumFlag = latestCNumFlag;
	}
	/**
	 * @return the power
	 */
	public float getPower() {
		return power;
	}
	/**
	 * @param power the power to set
	 */
	public void setPower(float power) {
		this.power = power;
	}
	/**
	 * @return the zCount
	 */
	public List<Integer> getzCount() {
		return zCount;
	}
	/**
	 * @param zCount the zCount to set
	 */
	public void setzCount(List<Integer> zCount) {
		this.zCount = zCount;
	}
	/**
	 * @return the allDataFlag
	 */
	public int getAllDataFlag() {
		return allDataFlag;
	}
	/**
	 * @param allDataFlag the allDataFlag to set
	 */
	public void setAllDataFlag(int allDataFlag) {
		this.allDataFlag = allDataFlag;
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
	 * @return the transportVehicalCostList
	 */
	public List<TransportationDetailsBean> getTransportVehicalCostList() {
		return transportVehicalCostList;
	}
	/**
	 * @param transportVehicalCostList the transportVehicalCostList to set
	 */
	public void setTransportVehicalCostList(List<TransportationDetailsBean> transportVehicalCostList) {
		this.transportVehicalCostList = transportVehicalCostList;
	}
	
	/**
	 * @return the condensingTypeId
	 */
	public int getCondensingTypeId() {
		return condensingTypeId;
	}
	/**
	 * @param condensingTypeId the condensingTypeId to set
	 */
	public void setCondensingTypeId(int condensingTypeId) {
		this.condensingTypeId = condensingTypeId;
	}
	/**
	 * @return the transportTotalPrice
	 */
	public int getTransportTotalPrice() {
		return transportTotalPrice;
	}
	/**
	 * @param transportTotalPrice the transportTotalPrice to set
	 */
	public void setTransportTotalPrice(int transportTotalPrice) {
		this.transportTotalPrice = transportTotalPrice;
	}
	/**
	 * @return the updateCode
	 */
	public String getUpdateCode() {
		return updateCode;
	}
	/**
	 * @param updateCode the updateCode to set
	 */
	public void setUpdateCode(String updateCode) {
		this.updateCode = updateCode;
	}
	/**
	 * @return the updateRequestNumber
	 */
	public int getUpdateRequestNumber() {
		return updateRequestNumber;
	}
	/**
	 * @param updateRequestNumber the updateRequestNumber to set
	 */
	public void setUpdateRequestNumber(int updateRequestNumber) {
		this.updateRequestNumber = updateRequestNumber;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the loggedInUserCode
	 */
	public int getLoggedInUserCode() {
		return loggedInUserCode;
	}
	/**
	 * @param loggedInUserCode the loggedInUserCode to set
	 */
	public void setLoggedInUserCode(int loggedInUserCode) {
		this.loggedInUserCode = loggedInUserCode;
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
	 * @return the packageTypeId
	 */
	public int getPackageTypeId() {
		return packageTypeId;
	}
	/**
	 * @param packageTypeId the packageTypeId to set
	 */
	public void setPackageTypeId(int packageTypeId) {
		this.packageTypeId = packageTypeId;
	}
	/**
	 * @return the transportationType
	 */
	public int getTransportationType() {
		return transportationType;
	}
	/**
	 * @param transportationType the transportationType to set
	 */
	public void setTransportationType(int transportationType) {
		this.transportationType = transportationType;
	}
	/**
	 * @return the displayReqNumber
	 */
	public String getDisplayReqNumber() {
		return displayReqNumber;
	}
	/**
	 * @param displayReqNumber the displayReqNumber to set
	 */
	public void setDisplayReqNumber(String displayReqNumber) {
		this.displayReqNumber = displayReqNumber;
	}
	/**
	 * @return the bleedType
	 */
	public String getBleedType() {
		return bleedType;
	}
	/**
	 * @param bleedType the bleedType to set
	 */
	public void setBleedType(String bleedType) {
		this.bleedType = bleedType;
	}
	/**
	 * @return the bleedTypeId
	 */
	public int getBleedTypeId() {
		return bleedTypeId;
	}
	/**
	 * @param bleedTypeId the bleedTypeId to set
	 */
	public void setBleedTypeId(int bleedTypeId) {
		this.bleedTypeId = bleedTypeId;
	}
	/**
	 * @return the transDetailList
	 */
	public List<TransportationDetailsBean> getTransDetailList() {
		return transDetailList;
	}
	/**
	 * @param transDetailList the transDetailList to set
	 */
	public void setTransDetailList(List<TransportationDetailsBean> transDetailList) {
		this.transDetailList = transDetailList;
	}
	/**
	 * @return the typOfBladeCode
	 */
	public String getTypOfBladeCode() {
		return typOfBladeCode;
	}
	/**
	 * @param typOfBladeCode the typOfBladeCode to set
	 */
	public void setTypOfBladeCode(String typOfBladeCode) {
		this.typOfBladeCode = typOfBladeCode;
	}
	/**
	 * @return the typOfBlade
	 */
	public String getTypOfBlade() {
		return typOfBlade;
	}
	/**
	 * @param typOfBlade the typOfBlade to set
	 */
	public void setTypOfBlade(String typOfBlade) {
		this.typOfBlade = typOfBlade;
	}
	/**
	 * @return the bladeTypeId
	 */
	public int getBladeTypeId() {
		return bladeTypeId;
	}
	/**
	 * @param bladeTypeId the bladeTypeId to set
	 */
	public void setBladeTypeId(int bladeTypeId) {
		this.bladeTypeId = bladeTypeId;
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
	 * @return the endUserCode
	 */
	public String getEndUserCode() {
		return EndUserCode;
	}
	/**
	 * @param endUserCode the endUserCode to set
	 */
	public void setEndUserCode(String endUserCode) {
		EndUserCode = endUserCode;
	}
	/**
	 * @return the custContactPersonName
	 */
	public String getCustContactPersonName() {
		return custContactPersonName;
	}
	/**
	 * @param custContactPersonName the custContactPersonName to set
	 */
	public void setCustContactPersonName(String custContactPersonName) {
		this.custContactPersonName = custContactPersonName;
	}
	/**
	 * @return the custContactNumber
	 */
	public String getCustContactNumber() {
		return custContactNumber;
	}
	/**
	 * @param custContactNumber the custContactNumber to set
	 */
	public void setCustContactNumber(String custContactNumber) {
		this.custContactNumber = custContactNumber;
	}
	/**
	 * @return the custEmailId
	 */
	public String getCustEmailId() {
		return custEmailId;
	}
	/**
	 * @param custEmailId the custEmailId to set
	 */
	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}
	/**
	 * @return the custAddress
	 */
	public String getCustAddress() {
		return custAddress;
	}
	/**
	 * @param custAddress the custAddress to set
	 */
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	/**
	 * @return the custPincode
	 */
	public String getCustPincode() {
		return custPincode;
	}
	/**
	 * @param custPincode the custPincode to set
	 */
	public void setCustPincode(String custPincode) {
		this.custPincode = custPincode;
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
	 * @return the consultantContactNo
	 */
	public String getConsultantContactNo() {
		return consultantContactNo;
	}
	/**
	 * @param consultantContactNo the consultantContactNo to set
	 */
	public void setConsultantContactNo(String consultantContactNo) {
		this.consultantContactNo = consultantContactNo;
	}
	/**
	 * @return the consultantEmail
	 */
	public String getConsultantEmail() {
		return consultantEmail;
	}
	/**
	 * @param consultantEmail the consultantEmail to set
	 */
	public void setConsultantEmail(String consultantEmail) {
		this.consultantEmail = consultantEmail;
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
	 * @return the endUserEmail
	 */
	public String getEndUserEmail() {
		return endUserEmail;
	}
	/**
	 * @param endUserEmail the endUserEmail to set
	 */
	public void setEndUserEmail(String endUserEmail) {
		this.endUserEmail = endUserEmail;
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
	 * @return the endUserContactNo
	 */
	public String getEndUserContactNo() {
		return endUserContactNo;
	}
	/**
	 * @param endUserContactNo the endUserContactNo to set
	 */
	public void setEndUserContactNo(String endUserContactNo) {
		this.endUserContactNo = endUserContactNo;
	}
	/**
	 * @return the editFlag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}
	/**
	 * @param editFlag the editFlag to set
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	/**
	 * @return the completeFlag
	 */
	public boolean isCompleteFlag() {
		return completeFlag;
	}
	/**
	 * @param completeFlag the completeFlag to set
	 */
	public void setCompleteFlag(boolean completeFlag) {
		this.completeFlag = completeFlag;
	}
	/**
	 * @return the submitFlag
	 */
	public boolean isSubmitFlag() {
		return submitFlag;
	}
	/**
	 * @param submitFlag the submitFlag to set
	 */
	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}
	/**
	 * @return the statusFlag
	 */
	public boolean isStatusFlag() {
		return statusFlag;
	}
	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(boolean statusFlag) {
		this.statusFlag = statusFlag;
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
	 * @return the custStatus
	 */
	public String getCustStatus() {
		return custStatus;
	}
	/**
	 * @param custStatus the custStatus to set
	 */
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	/**
	 * @return the regionCode
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * @return the isNewProject
	 */
	public int getIsNewProject() {
		return isNewProject;
	}
	/**
	 * @param isNewProject the isNewProject to set
	 */
	public void setIsNewProject(int isNewProject) {
		this.isNewProject = isNewProject;
	}
	/**
	 * @return the percentageVariation
	 */
	public float getPercentageVariation() {
		return percentageVariation;
	}
	/**
	 * @param percentageVariation the percentageVariation to set
	 */
	public void setPercentageVariation(float percentageVariation) {
		this.percentageVariation = percentageVariation;
	}
	/**
	 * @return the scopeStatusFlg
	 */
	public int getScopeStatusFlg() {
		return scopeStatusFlg;
	}
	/**
	 * @param scopeStatusFlg the scopeStatusFlg to set
	 */
	public void setScopeStatusFlg(int scopeStatusFlg) {
		this.scopeStatusFlg = scopeStatusFlg;
	}
	/**
	 * @return the sapData
	 */
	public F2FForm getSapData() {
		return sapData;
	}
	/**
	 * @param sapData the sapData to set
	 */
	public void setSapData(F2FForm sapData) {
		this.sapData = sapData;
	}
	/**
	 * @return the scopeCode
	 */
	public String getScopeCode() {
		return scopeCode;
	}
	/**
	 * @param scopeCode the scopeCode to set
	 */
	public void setScopeCode(String scopeCode) {
		this.scopeCode = scopeCode;
	}
	/**
	 * @return the remarksList
	 */
	public List<CommentBean> getRemarksList() {
		return remarksList;
	}
	/**
	 * @param remarksList the remarksList to set
	 */
	public void setRemarksList(List<CommentBean> remarksList) {
		this.remarksList = remarksList;
	}
	/**
	 * @return the commentBean
	 */
	public CommentBean getCommentBean() {
		return commentBean;
	}
	/**
	 * @param commentBean the commentBean to set
	 */
	public void setCommentBean(CommentBean commentBean) {
		this.commentBean = commentBean;
	}
	/**
	 * @return the transportBean
	 */
	public TransportationDetailsBean getTransportBean() {
		return transportBean;
	}
	/**
	 * @param transportBean the transportBean to set
	 */
	public void setTransportBean(TransportationDetailsBean transportBean) {
		this.transportBean = transportBean;
	}
	/**
	 * @return the revNum
	 */
	public int getRevNum() {
		return revNum;
	}
	/**
	 * @param revNum the revNum to set
	 */
	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}
	/**
	 * @return the uboData
	 */
	public F2FForm getUboData() {
		return uboData;
	}
	/**
	 * @param uboData the uboData to set
	 */
	public void setUboData(F2FForm uboData) {
		this.uboData = uboData;
	}
	/**
	 * @return the portId
	 */
	public int getPortId() {
		return portId;
	}
	/**
	 * @param portId the portId to set
	 */
	public void setPortId(int portId) {
		this.portId = portId;
	}
	/**
	 * @return the transTypeCode
	 */
	public String getTransTypeCode() {
		return transTypeCode;
	}
	/**
	 * @param transTypeCode the transTypeCode to set
	 */
	public void setTransTypeCode(String transTypeCode) {
		this.transTypeCode = transTypeCode;
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
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}
	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 * @return the revList
	 */
	public List<Integer> getRevList() {
		return revList;
	}
	/**
	 * @param revList the revList to set
	 */
	public void setRevList(List<Integer> revList) {
		this.revList = revList;
	}
	/**
	 * @return the quotStatusFlg
	 */
	public int getQuotStatusFlg() {
		return quotStatusFlg;
	}
	/**
	 * @param quotStatusFlg the quotStatusFlg to set
	 */
	public void setQuotStatusFlg(int quotStatusFlg) {
		this.quotStatusFlg = quotStatusFlg;
	}
	/**
	 * @return the userRoleName
	 */
	public String getUserRoleName() {
		return userRoleName;
	}
	/**
	 * @param userRoleName the userRoleName to set
	 */
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
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
	 * @return the isEndUserReq
	 */
//	public int getIsEndUserReq() {
//		return isEndUserReq;
//	}
//	/**
//	 * @param isEndUserReq the isEndUserReq to set
//	 */
//	public void setIsEndUserReq(int isEndUserReq) {
//		this.isEndUserReq = isEndUserReq;
//	}
	/**
	 * @return the typeOfOffer
	 */
//	public int getTypeOfOffer() {
//		return typeOfOffer;
//	}
//	/**
//	 * @param typeOfOffer the typeOfOffer to set
//	 */
//	public void setTypeOfOffer(int typeOfOffer) {
//		this.typeOfOffer = typeOfOffer;
//	}
	/**
	 * @return the isEndUserReq
	 */
	public int isEndUserReq() {
		return isEndUserReq;
	}
	/**
	 * @param isEndUserReq the isEndUserReq to set
	 */
	public void setEndUserReq(int isEndUserReq) {
		this.isEndUserReq = isEndUserReq;
	}
	/**
	 * @return the typeOfOffer
	 */
	/**
	 * @return the typeOfOffer
	 */
	public int getTypeOfOffer() {
		return typeOfOffer;
	}
	/**
	 * @param typeOfOffer the typeOfOffer to set
	 */
	public void setTypeOfOffer(int typeOfOffer) {
		this.typeOfOffer = typeOfOffer;
	}
	/**
	 * @return the typeOfQuot
	 */
	public int getTypeOfQuot() {
		return typeOfQuot;
	}
	/**
	 * @param typeOfQuot the typeOfQuot to set
	 */
	public void setTypeOfQuot(int typeOfQuot) {
		this.typeOfQuot = typeOfQuot;
	}
	/**
	 * @return the typeOfOfferCode
	 */
	public String  getTypeOfOfferCode() {
		return typeOfOfferCode;
	}
	/**
	 * @param typeOfOfferCode the typeOfOfferCode to set
	 */
	public void setTypeOfOfferCode(String typeOfOfferCode) {
		this.typeOfOfferCode = typeOfOfferCode;
	}
	/**
	 * @return the typeOfQuotCode
	 */
	public String getTypeOfQuotCode() {
		return TypeOfQuotCode;
	}
	/**
	 * @param typeOfQuotCode the typeOfQuotCode to set
	 */
	public void setTypeOfQuotCode(String typeOfQuotCode) {
		TypeOfQuotCode = typeOfQuotCode;
	}
	/**
	 * @return the categoryDetId
	 */
	public int getCategoryDetId() {
		return categoryDetId;
	}
	/**
	 * @param categoryDetId the categoryDetId to set
	 */
	public void setCategoryDetId(int categoryDetId) {
		this.categoryDetId = categoryDetId;
	}
	/**
	 * @return the categoryDetCode
	 */
	public String getCategoryDetCode() {
		return categoryDetCode;
	}
	/**
	 * @param categoryDetCode the categoryDetCode to set
	 */
	public void setCategoryDetCode(String categoryDetCode) {
		this.categoryDetCode = categoryDetCode;
	}
	/**
	 * @return the categoryDetDesc
	 */
	public String getCategoryDetDesc() {
		return CategoryDetDesc;
	}
	/**
	 * @param categoryDetDesc the categoryDetDesc to set
	 */
	public void setCategoryDetDesc(String categoryDetDesc) {
		CategoryDetDesc = categoryDetDesc;
	}
	/**
	 * @return the categoryCreatedDate
	 */
	public Date getCategoryCreatedDate() {
		return categoryCreatedDate;
	}
	/**
	 * @param categoryCreatedDate the categoryCreatedDate to set
	 */
	public void setCategoryCreatedDate(Date categoryCreatedDate) {
		this.categoryCreatedDate = categoryCreatedDate;
	}
	/**
	 * @return the categoryModifiedDate
	 */
	public Date getCategoryModifiedDate() {
		return categoryModifiedDate;
	}
	/**
	 * @param categoryModifiedDate the categoryModifiedDate to set
	 */
	public void setCategoryModifiedDate(Date categoryModifiedDate) {
		this.categoryModifiedDate = categoryModifiedDate;
	}
	/**
	 * @return the categoryCreatedBy
	 */
	public int getCategoryCreatedBy() {
		return categoryCreatedBy;
	}
	/**
	 * @param categoryCreatedBy the categoryCreatedBy to set
	 */
	public void setCategoryCreatedBy(int categoryCreatedBy) {
		this.categoryCreatedBy = categoryCreatedBy;
	}
	/**
	 * @return the categoryModifiedBy
	 */
	public int getCategoryModifiedBy() {
		return categoryModifiedBy;
	}
	/**
	 * @param categoryModifiedBy the categoryModifiedBy to set
	 */
	public void setCategoryModifiedBy(int categoryModifiedBy) {
		this.categoryModifiedBy = categoryModifiedBy;
	}
	/**
	 * @return the isCategoryActive
	 */
	public boolean isCategoryActive() {
		return isCategoryActive;
	}
	/**
	 * @param isCategoryActive the isCategoryActive to set
	 */
	public void setCategoryActive(boolean isCategoryActive) {
		this.isCategoryActive = isCategoryActive;
	}
	/**
	 * @return the typeOfOfferNm
	 */
	public String getTypeOfOfferNm() {
		return typeOfOfferNm;
	}
	/**
	 * @param typeOfOfferNm the typeOfOfferNm to set
	 */
	public void setTypeOfOfferNm(String typeOfOfferNm) {
		this.typeOfOfferNm = typeOfOfferNm;
	}
	/**
	 * @return the typeOfQuotNm
	 */
	public String getTypeOfQuotNm() {
		return typeOfQuotNm;
	}
	/**
	 * @param typeOfQuotNm the typeOfQuotNm to set
	 */
	public void setTypeOfQuotNm(String typeOfQuotNm) {
		this.typeOfQuotNm = typeOfQuotNm;
	}
	/**
	 * @return the isEndUserReq
	 */
	public int getIsEndUserReq() {
		return isEndUserReq;
	}
	/**
	 * @param isEndUserReq the isEndUserReq to set
	 */
	public void setIsEndUserReq(int isEndUserReq) {
		this.isEndUserReq = isEndUserReq;
	}
	/**
	 * @return the typeOfInjection
	 */
	public int getTypeOfInjection() {
		return typeOfInjection;
	}
	/**
	 * @param typeOfInjection the typeOfInjection to set
	 */
	public void setTypeOfInjection(int typeOfInjection) {
		this.typeOfInjection = typeOfInjection;
	}
	/**
	 * @return the typeOfVarient
	 */
	public int getTypeOfVarient() {
		return typeOfVarient;
	}
	/**
	 * @param typeOfVarient the typeOfVarient to set
	 */
	public void setTypeOfVarient(int typeOfVarient) {
		this.typeOfVarient = typeOfVarient;
	}
	/**
	 * @return the typeOfInjectionNm
	 */
	public String getTypeOfInjectionNm() {
		return typeOfInjectionNm;
	}
	/**
	 * @param typeOfInjectionNm the typeOfInjectionNm to set
	 */
	public void setTypeOfInjectionNm(String typeOfInjectionNm) {
		this.typeOfInjectionNm = typeOfInjectionNm;
	}
	/**
	 * @return the typeOfVarientNm
	 */
	public String getTypeOfVarientNm() {
		return typeOfVarientNm;
	}
	/**
	 * @param typeOfVarientNm the typeOfVarientNm to set
	 */
	public void setTypeOfVarientNm(String typeOfVarientNm) {
		this.typeOfVarientNm = typeOfVarientNm;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	/**
	 * @return the stateNm
	 */
	public String getStateNm() {
		return stateNm;
	}
	/**
	 * @param stateNm the stateNm to set
	 */
	public void setStateNm(String stateNm) {
		this.stateNm = stateNm;
	}
	/**
	 * @return the typeOFCustomer
	 */

	/**
	 * @return the typeOfCustomer
	 */
	public String getTypeOfCustomer() {
		return typeOfCustomer;
	}
	/**
	 * @param typeOfCustomer the typeOfCustomer to set
	 */
	public void setTypeOfCustomer(String typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}
	/**
	 * @return the custCodeNm
	 */
	public String getCustCodeNm() {
		return custCodeNm;
	}
	/**
	 * @param custCodeNm the custCodeNm to set
	 */
	public void setCustCodeNm(String custCodeNm) {
		this.custCodeNm = custCodeNm;
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
