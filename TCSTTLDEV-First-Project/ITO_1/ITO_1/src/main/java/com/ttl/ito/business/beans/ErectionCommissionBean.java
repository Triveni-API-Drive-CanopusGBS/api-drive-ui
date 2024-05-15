package com.ttl.ito.business.beans;

import org.springframework.stereotype.Component;

@Component
public class ErectionCommissionBean {
	private String updateCode;
	public String getUpdateCode() {
		return updateCode;
	}
	public void setUpdateCode(String updateCode) {
		this.updateCode = updateCode;
	}
	private int updateRequestNo;
	private int framePowerId;
	private int currencyId;
	private int stateId;
	private int frameId;
	public int getFrameId() {
		return frameId;
	}
	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}
	public void setCurPrice(float curPrice) {
		this.curPrice = curPrice;
	}
	private int typeOfChargeId;
	private int ecTypeId;
	private int ecId;
	private int quotId;
	private int condensingTypeId;
	private int lodgingId;
	private int loadingId;
	private int NoOfManDays;
	private int assignedToId;
	private int createdById;
	
	private String stateName;
	private String currency;
	private String frameDesc;
	private String typeOfCharge;
	private String typeOfChargeCd;
	private String chargeName;
	private String loadingName;
	private String loadingCd;
	private String lodgingName;
	private String lodgingCd;
	private String turbineDesignCode;
	private String turbineDesign;
	private String turbineCode;
	private String turbineType;
	private String ecType;
	private String ecTypeCd;
	private String condensingType;
	private String remarks;
	private String assignedToname; 
	private String modifiedDate;
	private String createdDate;
	private String createdBy;
	
	private float curPrice;
	private float price;
	private float previousPrice;
	private float overwrittenPrice;
	private float maxPower;
	
	private boolean overwrittenPriceFlag;
	
	
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
	 * @return the frameDesc
	 */
	public String getFrameDesc() {
		return frameDesc;
	}
	/**
	 * @param frameDesc the frameDesc to set
	 */
	public void setFrameDesc(String frameDesc) {
		this.frameDesc = frameDesc;
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
	 * @return the lodgingId
	 */
	public int getLodgingId() {
		return lodgingId;
	}
	/**
	 * @param lodgingId the lodgingId to set
	 */
	public void setLodgingId(int lodgingId) {
		this.lodgingId = lodgingId;
	}
	/**
	 * @return the loadingId
	 */
	public int getLoadingId() {
		return loadingId;
	}
	/**
	 * @param loadingId the loadingId to set
	 */
	public void setLoadingId(int loadingId) {
		this.loadingId = loadingId;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return the overwrittenPrice
	 */
	public float getOverwrittenPrice() {
		return overwrittenPrice;
	}
	/**
	 * @param overwrittenPrice the overwrittenPrice to set
	 */
	public void setOverwrittenPrice(float overwrittenPrice) {
		this.overwrittenPrice = overwrittenPrice;
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
	 * @return the ecId
	 */
	public int getEcId() {
		return ecId;
	}
	/**
	 * @param ecId the ecId to set
	 */
	public void setEcId(int ecId) {
		this.ecId = ecId;
	}
	/**
	 * @return the chargeName
	 */
	public String getChargeName() {
		return chargeName;
	}
	/**
	 * @param chargeName the chargeName to set
	 */
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	/**
	 * @return the loadingName
	 */
	public String getLoadingName() {
		return loadingName;
	}
	/**
	 * @param loadingName the loadingName to set
	 */
	public void setLoadingName(String loadingName) {
		this.loadingName = loadingName;
	}
	/**
	 * @return the lodgingName
	 */
	public String getLodgingName() {
		return lodgingName;
	}
	/**
	 * @param lodgingName the lodgingName to set
	 */
	public void setLodgingName(String lodgingName) {
		this.lodgingName = lodgingName;
	}
	/**
	 * @return the turbineDesign
	 */
	public String getTurbineDesign() {
		return turbineDesign;
	}
	/**
	 * @param turbineDesign the turbineDesign to set
	 */
	public void setTurbineDesign(String turbineDesign) {
		this.turbineDesign = turbineDesign;
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
	 * @return the condensingType
	 */
	public String getCondensingType() {
		return condensingType;
	}
	/**
	 * @param condensingType the condensingType to set
	 */
	public void setCondensingType(String condensingType) {
		this.condensingType = condensingType;
	}
	/**
	 * @return the ecTypeId
	 */
	public int getEcTypeId() {
		return ecTypeId;
	}
	/**
	 * @param ecTypeId the ecTypeId to set
	 */
	public void setEcTypeId(int ecTypeId) {
		this.ecTypeId = ecTypeId;
	}
	/**
	 * @return the ecType
	 */
	public String getEcType() {
		return ecType;
	}
	/**
	 * @param ecType the ecType to set
	 */
	public void setEcType(String ecType) {
		this.ecType = ecType;
	}
	/**
	 * @return the typeOfChargeId
	 */
	public int getTypeOfChargeId() {
		return typeOfChargeId;
	}
	/**
	 * @param typeOfChargeId the typeOfChargeId to set
	 */
	public void setTypeOfChargeId(int typeOfChargeId) {
		this.typeOfChargeId = typeOfChargeId;
	}
	/**
	 * @return the typeOfCharge
	 */
	public String getTypeOfCharge() {
		return typeOfCharge;
	}
	/**
	 * @param typeOfCharge the typeOfCharge to set
	 */
	public void setTypeOfCharge(String typeOfCharge) {
		this.typeOfCharge = typeOfCharge;
	}
	/**
	 * @return the maxPower
	 */
	public float getMaxPower() {
		return maxPower;
	}
	/**
	 * @param maxPower the maxPower to set
	 */
	public void setMaxPower(float maxPower) {
		this.maxPower = maxPower;
	}
	/**
	 * @return the turbineType
	 */
	public String getTurbineType() {
		return turbineType;
	}
	/**
	 * @param turbineType the turbineType to set
	 */
	public void setTurbineType(String turbineType) {
		this.turbineType = turbineType;
	}
	/**
	 * @return the turbineDesignCode
	 */
	public String getTurbineDesignCode() {
		return turbineDesignCode;
	}
	/**
	 * @param turbineDesignCode the turbineDesignCode to set
	 */
	public void setTurbineDesignCode(String turbineDesignCode) {
		this.turbineDesignCode = turbineDesignCode;
	}
	/**
	 * @return the noOfManDays
	 */
	public int getNoOfManDays() {
		return NoOfManDays;
	}
	/**
	 * @param noOfManDays the noOfManDays to set
	 */
	public void setNoOfManDays(int noOfManDays) {
		NoOfManDays = noOfManDays;
	}
	/**
	 * @return the updateRequestNo
	 */
	public int getUpdateRequestNo() {
		return updateRequestNo;
	}
	/**
	 * @param updateRequestNo the updateRequestNo to set
	 */
	public void setUpdateRequestNo(int updateRequestNo) {
		this.updateRequestNo = updateRequestNo;
	}
	/**
	 * @return the previousPrice
	 */
	public float getPreviousPrice() {
		return previousPrice;
	}
	/**
	 * @param previousPrice the previousPrice to set
	 */
	public void setPreviousPrice(float previousPrice) {
		this.previousPrice = previousPrice;
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
	 * @return the assignedToname
	 */
	public String getAssignedToname() {
		return assignedToname;
	}
	/**
	 * @param assignedToname the assignedToname to set
	 */
	public void setAssignedToname(String assignedToname) {
		this.assignedToname = assignedToname;
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
	 * @return the overwrittenPriceFlag
	 */
	public boolean isOverwrittenPriceFlag() {
		return overwrittenPriceFlag;
	}
	/**
	 * @param overwrittenPriceFlag the overwrittenPriceFlag to set
	 */
	public void setOverwrittenPriceFlag(boolean overwrittenPriceFlag) {
		this.overwrittenPriceFlag = overwrittenPriceFlag;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the currencyId
	 */
	public int getCurrencyId() {
		return currencyId;
	}
	/**
	 * @param currencyId the currencyId to set
	 */
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	/**
	 * @return the curPrice
	 */
	public Float getCurPrice() {
		return curPrice;
	}
	/**
	 * @param curPrice the curPrice to set
	 */
	public void setCurPrice(Float curPrice) {
		this.curPrice = curPrice;
	}
	/**
	 * @return the ecTypeCd
	 */
	public String getEcTypeCd() {
		return ecTypeCd;
	}
	/**
	 * @param ecTypeCd the ecTypeCd to set
	 */
	public void setEcTypeCd(String ecTypeCd) {
		this.ecTypeCd = ecTypeCd;
	}
	/**
	 * @return the typeOfChargeCd
	 */
	public String getTypeOfChargeCd() {
		return typeOfChargeCd;
	}
	/**
	 * @param typeOfChargeCd the typeOfChargeCd to set
	 */
	public void setTypeOfChargeCd(String typeOfChargeCd) {
		this.typeOfChargeCd = typeOfChargeCd;
	}
	/**
	 * @return the loadingCd
	 */
	public String getLoadingCd() {
		return loadingCd;
	}
	/**
	 * @param loadingCd the loadingCd to set
	 */
	public void setLoadingCd(String loadingCd) {
		this.loadingCd = loadingCd;
	}
	/**
	 * @return the lodgingCd
	 */
	public String getLodgingCd() {
		return lodgingCd;
	}
	/**
	 * @param lodgingCd the lodgingCd to set
	 */
	public void setLodgingCd(String lodgingCd) {
		this.lodgingCd = lodgingCd;
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
	private String serviceRemarks;
	public String getServiceRemarks() {
		return serviceRemarks;
	}
	public void setServiceRemarks(String serviceRemarks) {
		this.serviceRemarks = serviceRemarks;
	}
	
}
