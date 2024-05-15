package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserProfileDetails {
	private int userId;
	private int empId;
	private int regionId;
	private int status;
	private int successCode;
	private int roleId;
	private int createdById;
	private int imageId;
	private int modifiedById;
	private String roleCode;
	
	private Integer groupId;
	
	private byte[] img;
	
	private boolean isActive;
	
	private Date lastLoggedInDate;
	private int itemId;
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public String getItemNm() {
		return itemNm;
	}

	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}

	public int getNoOfMonths() {
		return noOfMonths;
	}

	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public int getPercentFlag() {
		return percentFlag;
	}

	public void setPercentFlag(int percentFlag) {
		this.percentFlag = percentFlag;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}
	private int unItemId;
	public int getUnItemId() {
		return unItemId;
	}

	public void setUnItemId(int unItemId) {
		this.unItemId = unItemId;
	}

	public String getScopeCd() {
		return scopeCd;
	}

	public void setScopeCd(String scopeCd) {
		this.scopeCd = scopeCd;
	}

	public String getUnItemCd() {
		return unItemCd;
	}

	public void setUnItemCd(String unItemCd) {
		this.unItemCd = unItemCd;
	}

	public String getUnItemNm() {
		return unItemNm;
	}

	public void setUnItemNm(String unItemNm) {
		this.unItemNm = unItemNm;
	}

	public String getUnParentCd() {
		return unParentCd;
	}

	public void setUnParentCd(String unParentCd) {
		this.unParentCd = unParentCd;
	}

	public int getItemFlag() {
		return itemFlag;
	}

	public void setItemFlag(int itemFlag) {
		this.itemFlag = itemFlag;
	}

	public int getSubItemFlag() {
		return subItemFlag;
	}

	public void setSubItemFlag(int subItemFlag) {
		this.subItemFlag = subItemFlag;
	}

	public int getSubItemTypeFlag() {
		return subItemTypeFlag;
	}

	public void setSubItemTypeFlag(int subItemTypeFlag) {
		this.subItemTypeFlag = subItemTypeFlag;
	}

	public int getF2fAddOn() {
		return f2fAddOn;
	}

	public void setF2fAddOn(int f2fAddOn) {
		this.f2fAddOn = f2fAddOn;
	}

	public int getActiveNew() {
		return activeNew;
	}

	public void setActiveNew(int activeNew) {
		this.activeNew = activeNew;
	}
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFramePowerId() {
		return framePowerId;
	}

	public void setFramePowerId(int framePowerId) {
		this.framePowerId = framePowerId;
	}

	public float getEopMotorRating() {
		return eopMotorRating;
	}

	public void setEopMotorRating(float eopMotorRating) {
		this.eopMotorRating = eopMotorRating;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getPumpType() {
		return pumpType;
	}

	public void setPumpType(String pumpType) {
		this.pumpType = pumpType;
	}

	public int getBgHrRate() {
		return bgHrRate;
	}

	public void setBgHrRate(int bgHrRate) {
		this.bgHrRate = bgHrRate;
	}

	public float getDcOutputVol() {
		return dcOutputVol;
	}

	public void setDcOutputVol(float dcOutputVol) {
		this.dcOutputVol = dcOutputVol;
	}

	public int getDefaultFlagNew() {
		return defaultFlagNew;
	}

	public void setDefaultFlagNew(int defaultFlagNew) {
		this.defaultFlagNew = defaultFlagNew;
	}
	private int   eleItemId;
	public int getEleItemId() {
		return eleItemId;
	}

	public void setEleItemId(int eleItemId) {
		this.eleItemId = eleItemId;
	}

	public String getTypeOfPanel() {
		return typeOfPanel;
	}

	public void setTypeOfPanel(String typeOfPanel) {
		this.typeOfPanel = typeOfPanel;
	}

	public String getEleType() {
		return eleType;
	}

	public void setEleType(String eleType) {
		this.eleType = eleType;
	}

	public int getSubItemId() {
		return subItemId;
	}

	public void setSubItemId(int subItemId) {
		this.subItemId = subItemId;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public String getFooterText() {
		return footerText;
	}

	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}

	public String getHeaderNm() {
		return headerNm;
	}

	public void setHeaderNm(String headerNm) {
		this.headerNm = headerNm;
	}

	public String getExclusionNm() {
		return exclusionNm;
	}

	public void setExclusionNm(String exclusionNm) {
		this.exclusionNm = exclusionNm;
	}

	public int getCustTypeDependFlag() {
		return custTypeDependFlag;
	}

	public void setCustTypeDependFlag(int custTypeDependFlag) {
		this.custTypeDependFlag = custTypeDependFlag;
	}

	public int getDtFrameFlag() {
		return dtFrameFlag;
	}

	public void setDtFrameFlag(int dtFrameFlag) {
		this.dtFrameFlag = dtFrameFlag;
	}
	private String  bgmType;
	public String getBgmType() {
		return bgmType;
	}

	public void setBgmType(String bgmType) {
		this.bgmType = bgmType;
	}

	
	public int getApproxCostFlag() {
		return approxCostFlag;
	}

	public void setApproxCostFlag(int approxCostFlag) {
		this.approxCostFlag = approxCostFlag;
	}
	private float bgmRating;
	public float getBgmRating() {
		return bgmRating;
	}

	public void setBgmRating(float bgmRating) {
		this.bgmRating = bgmRating;
	}
	private int auxId;
	private int condTypeId;
	private int frmId;
	private String custId;
	private String continuous;
	private String startup;
	private String colValCd;
	private int editFlag;
	public int getAuxId() {
		return auxId;
	}

	public void setAuxId(int auxId) {
		this.auxId = auxId;
	}
	public int getCondTypeId() {
		return condTypeId;
	}

	public void setCondTypeId(int condTypeId) {
		this.condTypeId = condTypeId;
	}
	public int getFrmId() {
		return frmId;
	}

	public void setFrmId(int frmId) {
		this.frmId = frmId;
	}
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getStartup() {
		return startup;
	}

	public void setStartup(String startup) {
		this.startup = startup;
	}
	public String getContinuous() {
		return continuous;
	}

	public void setContinuous(String continuous) {
		this.continuous = continuous;
	}
	public String getColValCd() {
		return colValCd;
	}

	public void setColValCd(String colValCd) {
		this.colValCd = colValCd;
	}
	public int getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(int editFlag) {
		this.editFlag = editFlag;
	}
	private String category;
	private String itemType;
	private String speed;
	private String voltage;
	private String feeder;
	private String panelType;
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSpeed() {
		return speed;
	}

	public void getSpeed(String speed) {
		this.speed = speed;
	}
	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getFeeder() {
		return feeder;
	}

	public void setFeeder(String feeder) {
		this.feeder = feeder;
	}
	public String getPanelType() {
		return panelType;
	}

	public void setPanelType(String panelType) {
		this.panelType = panelType;
	}
	private float minPower;
	private float maxPower;
	public float getMinPower() {
		return minPower;
	}

	public void setMinPower(float minPower) {
		this.minPower = minPower;
	}
	public float getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(float maxPower) {
		this.maxPower = maxPower;
	}
	private int approxCostFlag;
	private String typeOfPanel;
	private String eleType;
	private int   subItemId;
	private String headerText;
	private String footerText;
	private String headerNm;
	private String exclusionNm;
	private int   custTypeDependFlag;
	private int   dtFrameFlag;
	private int  framePowerId;
	private float eopMotorRating;
	private float cost;
	private String pumpType;
	private int bgHrRate;
	private float dcOutputVol;
	private int  defaultFlagNew;
	private String scopeCd;
	private String unItemCd;
	private String unItemNm;
	private String unParentCd;
	private int itemFlag;
	private int subItemFlag;
	private int subItemTypeFlag;
	private int f2fAddOn;
	private int activeNew;
	private String itemCd;
	private String itemNm;
	private int noOfMonths;
	private String custType;
	private int percentFlag;
	private float percent;
	private String createdDate;
	private String role;
	private String userActiveStatus;
	private String image;
	private String successMsg;
	private String groupCd;
	private String empName;
	private String emailId;
	private String contactNumber;
	private String group;
	private String modifiedBy;
	private String region;
	private String regionCode;
	private String createdBy;
	private String modifiedDate;
	private String  designation;
	private int DeptId;
	
	public int getDeptId() {
		return DeptId;
	}

	public void setDeptId(int deptId) {
		DeptId = deptId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	List<SelectBox> userRegionsList = new ArrayList<SelectBox>(); // Regions List
	List<SelectBox> userRolesList = new ArrayList<SelectBox>(); // Roles List
	List<SelectBox> userDeptList = new ArrayList<SelectBox>(); // Roles List
	
	List<Integer> regionsList = new ArrayList<Integer>();
	List<Integer> rolesList = new ArrayList<Integer>();
	
	List<String> regionsVal = new ArrayList<String>();
	List<String> rolesVal = new ArrayList<String>();
	List<String> rolesCodeVal = new ArrayList<String>();

	Map<Integer, String> msgToUser = new HashMap<Integer, String>();

	
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the empId
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/**
	 * @return the imageId
	 */
	public int getImageId() {
		return imageId;
	}

	/**
	 * @param imageId
	 *            the imageId to set
	 */
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the img
	 */
	public byte[] getImg() {
		return img;
	}

	/**
	 * @param img
	 *            the img to set
	 */
	public void setImg(byte[] img) {
		this.img = img;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
	 * @return the regionsList
	 */
	public List<Integer> getRegionsList() {
		return regionsList;
	}

	/**
	 * @param regionsList
	 *            the regionsList to set
	 */
	public void setRegionsList(List<Integer> regionsList) {
		this.regionsList = regionsList;
	}

	/**
	 * @return the rolesList
	 */
	public List<Integer> getRolesList() {
		return rolesList;
	}

	/**
	 * @param rolesList
	 *            the rolesList to set
	 */
	public void setRolesList(List<Integer> rolesList) {
		this.rolesList = rolesList;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the regionId
	 */
	public int getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId
	 *            the regionId to set
	 */
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the userRegionsList
	 */
	public List<SelectBox> getUserRegionsList() {
		return userRegionsList;
	}

	/**
	 * @param userRegionsList
	 *            the userRegionsList to set
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
	 * @param userRolesList
	 *            the userRolesList to set
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
	 * @param lastLoggedInDate
	 *            the lastLoggedInDate to set
	 */
	public void setLastLoggedInDate(Date lastLoggedInDate) {
		this.lastLoggedInDate = lastLoggedInDate;
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
	 * @return the userDeptList
	 */
	public List<SelectBox> getUserDeptList() {
		return userDeptList;
	}

	/**
	 * @param userDeptList
	 *            the userDeptList to set
	 */
	public void setUserDeptList(List<SelectBox> userDeptList) {
		this.userDeptList = userDeptList;
	}

	/**
	 * @return the regionsVal
	 */
	public List<String> getRegionsVal() {
		return regionsVal;
	}

	/**
	 * @param regionsVal
	 *            the regionsVal to set
	 */
	public void setRegionsVal(List<String> regionsVal) {
		this.regionsVal = regionsVal;
	}

	/**
	 * @return the rolesVal
	 */
	public List<String> getRolesVal() {
		return rolesVal;
	}

	/**
	 * @param rolesVal
	 *            the rolesVal to set
	 */
	public void setRolesVal(List<String> rolesVal) {
		this.rolesVal = rolesVal;
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
	 * @return the successCode
	 */
	public int getSuccessCode() {
		return successCode;
	}

	/**
	 * @param successCode
	 *            the successCode to set
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
	 * @param successMsg
	 *            the successMsg to set
	 */
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	/**
	 * @return the groupCd
	 */
	public String getGroupCd() {
		return groupCd;
	}

	/**
	 * @param groupCd
	 *            the groupCd to set
	 */
	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
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
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return the rolesCodeVal
	 */
	public List<String> getRolesCodeVal() {
		return rolesCodeVal;
	}

	/**
	 * @param rolesCodeVal the rolesCodeVal to set
	 */
	public void setRolesCodeVal(List<String> rolesCodeVal) {
		this.rolesCodeVal = rolesCodeVal;
	}

	/**
	 * @return the userActiveStatus
	 */
	public String getUserActiveStatus() {
		return userActiveStatus;
	}

	/**
	 * @param userActiveStatus the userActiveStatus to set
	 */
	public void setUserActiveStatus(String userActiveStatus) {
		this.userActiveStatus = userActiveStatus;
	}
	private String description;
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	private String qty;
	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	private String subScopeCd;
	public String getSubScopeCd() {
		return subScopeCd;
	}

	public void setSubScopeCd(String subScopeCd) {
		this.subScopeCd = subScopeCd;
	}
	private String equipment;
	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	private String equivalent;
	public String getEquivalent() {
		return equivalent;
	}

	public void setEquivalent(String equivalent) {
		this.equivalent = equivalent;
	}
	private String actionType;
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	private String actionRow;
	public String getActionRow() {
		return actionRow;
	}

	public void setActionRow(String actionRow) {
		this.actionRow = actionRow;
	}
	private String test;
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	private int bleedTypeId;
	public int getBleedTypeId() {
		return bleedTypeId;
	}

	public void setBleedTypeId(int bleedTypeId) {
		this.bleedTypeId = bleedTypeId;
	}
	private int variantType;
	public int getVariantType() {
		return variantType;
	}

	public void setVariantType(int variantType) {
		this.variantType = variantType;
	}
	private String information;
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	private String fine;
	public String getFinal() {
		return fine;
	}

	public void setFinal(String fine) {
		this.fine = fine;
	}
	private int noOfMandays;
	public int getNoOfMandays() {
		return noOfMandays;
	}
	public void setNoOfMandays(int noOfMandays) {
		this.noOfMandays = noOfMandays;
	}
	private int ssId;
	public int getSsId() {
		return ssId;
	}
	public void setSsId(int ssId) {
		this.ssId = ssId;
	}
	private String turbCd;
	public String getTurbCd() {
		return turbCd;
	}

	public void setTurbCd(String turbCd) {
		this.turbCd = turbCd;
	}
	private String coolingMethod;
	public String getCoolingMethod() {
		return coolingMethod;
	}

	public void setCoolingMethod(String coolingMethod) {
		this.coolingMethod = coolingMethod;
	}
	private String tempRaise;
	public String getTempRaise() {
		return tempRaise;
	}

	public void setTempRaise(String tempRaise) {
		this.tempRaise = tempRaise;
	}
	private String turbOilCooler;
	public String getTurbOilCooler() {
		return turbOilCooler;
	}

	public void setTurbOilCooler(String turbOilCooler) {
		this.turbOilCooler = turbOilCooler;
	}
	private String generatorCooler;
	public String getGeneratorCooler() {
		return generatorCooler;
	}

	public void setGeneratorCooler(String generatorCooler) {
		this.generatorCooler = generatorCooler;
	}
	private String glandVendCond;
	public String getGlandVendCond() {
		return glandVendCond;
	}

	public void setGlandVendCond(String glandVendCond) {
		this.glandVendCond = glandVendCond;
	}
	private String turbDesign;
	public String getTurbDesign() {
		return turbDesign;
	}

	public void setTurbDesign(String turbDesign) {
		this.turbDesign = turbDesign;
	}
	private String turbineCode;
	public String getTurbineCode() {
		return turbineCode;
	}

	public void setTurbineCode(String turbineCode) {
		this.turbineCode = turbineCode;
	}
	
	List<List> userDetailsEmailList = new ArrayList<List>();
	public List<List> getUserDetailsEmailList() {
		return userDetailsEmailList;
	}

	public void setUserDetailsEmailList(List<List> userDetailsEmailList) {
		this.userDetailsEmailList = userDetailsEmailList;
	}
	private String serviceRemarks;
	public String getServiceRemarks() {
		return serviceRemarks;
	}

	public void setServiceRemarks(String serviceRemarks) {
		this.serviceRemarks = serviceRemarks;
	}
}
