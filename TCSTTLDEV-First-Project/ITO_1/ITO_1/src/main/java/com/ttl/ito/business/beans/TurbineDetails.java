package com.ttl.ito.business.beans;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class TurbineDetails {
	private String improvedImpId;
	
	public String getImprovedImpId() {
		return improvedImpId;
	}
	public void setImprovedImpId(String improvedImpId) {
		this.improvedImpId = improvedImpId;
	}
	private int frameVariantId;
	public int getFrameVariantId() {
		return frameVariantId;
	}
	public void setFrameVariantId(int frameVariantId) {
		this.frameVariantId = frameVariantId;
	}
	public String getFrameNm() {
		return frameNm;
	}
	public void setFrameNm(String frameNm) {
		this.frameNm = frameNm;
	}
	public int getVariantTypeId() {
		return variantTypeId;
	}
	public void setVariantTypeId(int variantTypeId) {
		this.variantTypeId = variantTypeId;
	}
	public String getVariantTypeNm() {
		return variantTypeNm;
	}
	public void setVariantTypeNm(String variantTypeNm) {
		this.variantTypeNm = variantTypeNm;
	}
	private String frameNm;
	private int variantTypeId;
	private String variantTypeNm;
	private int frameId;
	private int QuotId;
	private int frameCreatedBy;
	private int frameModifiedBy;
	private int categoryDetId;
	private int categoryCreatedBy;
	private int categoryModifiedBy;
	private int framePowerId;
	private int ssId; //scopeOfsupplyId
	private int statusId;
	private int instrId;
	private int condTypId;
	private int bleedTypId;
	private int updateRequestNumber;
	private int assingedToId;
	private int createdById;
	private String grpCd;
	
	private float power;
	private float maxPower;
	private float minPower;
	private float price;
	private float percentIncr;
	private float turbInstrCost;
	private float prevTurbInstrCost;
	private float prevCondInstrCost;
	private float condInstrCost;
	private float subContrCost;
	private float overHeads;
	private float totalFTFCost;
	private float prevOverHeads;
	private float prevSubContrCost;
	private float suppChainCost;
	private float estimateCost;
	
	private Date frameCreatedDate;
	private Date frameModifiedDate;
	private Date categoryCreatedDate;
	private Date categoryModifiedDate;
	
	private boolean isFrameActive;
	private boolean isFramePowerActive;
	private boolean iscategoryActive;
	private boolean extractFlag;
	private boolean bleedsFlag;
	private boolean defaultVal;
	private boolean isActive;
	
	private String categoryDetCode;
	private String categoryDetDesc;
	private String frameCode;
	private String turbineDesignCd;
	private String frameDesc;
	private String turbineCode;
	private String turbType;
	private String turbdesignName;
	private String framePowerDesc;
	private String length;
	private String dimension;
	private String dependentCode;
	private String subDependentCode;
	private String ssName;
	private String placeId;
	private String place;
	private String effectiveFrom;
	private String effectiveTo;
	private String status;
	private String statusCode;
	private String createdBy;
	private String updReqName;
	private String condensingtypes;
	private String bleedType;
	private String statusName;
	private String assignedTo;
	private Date modDt;
		
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
	 * @return the frameCode
	 */
	public String getFrameCode() {
		return frameCode;
	}
	/**
	 * @param frameCode the frameCode to set
	 */
	public void setFrameCode(String frameCode) {
		this.frameCode = frameCode;
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
	 * @return the frameCreatedDate
	 */
	public Date getFrameCreatedDate() {
		return frameCreatedDate;
	}
	/**
	 * @param frameCreatedDate the frameCreatedDate to set
	 */
	public void setFrameCreatedDate(Date frameCreatedDate) {
		this.frameCreatedDate = frameCreatedDate;
	}
	/**
	 * @return the frameModifiedDate
	 */
	public Date getFrameModifiedDate() {
		return frameModifiedDate;
	}
	/**
	 * @param frameModifiedDate the frameModifiedDate to set
	 */
	public void setFrameModifiedDate(Date frameModifiedDate) {
		this.frameModifiedDate = frameModifiedDate;
	}
	/**
	 * @return the frameCreatedBy
	 */
	public int getFrameCreatedBy() {
		return frameCreatedBy;
	}
	/**
	 * @param frameCreatedBy the frameCreatedBy to set
	 */
	public void setFrameCreatedBy(int frameCreatedBy) {
		this.frameCreatedBy = frameCreatedBy;
	}
	/**
	 * @return the frameModifiedBy
	 */
	public int getFrameModifiedBy() {
		return frameModifiedBy;
	}
	/**
	 * @param frameModifiedBy the frameModifiedBy to set
	 */
	public void setFrameModifiedBy(int frameModifiedBy) {
		this.frameModifiedBy = frameModifiedBy;
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
		return categoryDetDesc;
	}
	/**
	 * @param categoryDetDesc the categoryDetDesc to set
	 */
	public void setCategoryDetDesc(String categoryDetDesc) {
		this.categoryDetDesc = categoryDetDesc;
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
	 * @return the isFrameActive
	 */
	public boolean isFrameActive() {
		return isFrameActive;
	}
	/**
	 * @param isFrameActive the isFrameActive to set
	 */
	public void setFrameActive(boolean isFrameActive) {
		this.isFrameActive = isFrameActive;
	}
	/**
	 * @return the iscategoryActive
	 */
	public boolean isIscategoryActive() {
		return iscategoryActive;
	}
	/**
	 * @param iscategoryActive the iscategoryActive to set
	 */
	public void setIscategoryActive(boolean iscategoryActive) {
		this.iscategoryActive = iscategoryActive;
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
	 * @return the extractFlag
	 */
	public boolean isExtractFlag() {
		return extractFlag;
	}
	/**
	 * @param extractFlag the extractFlag to set
	 */
	public void setExtractFlag(boolean extractFlag) {
		this.extractFlag = extractFlag;
	}
	/**
	 * @return the bleedsFlag
	 */
	public boolean isBleedsFlag() {
		return bleedsFlag;
	}
	/**
	 * @param bleedsFlag the bleedsFlag to set
	 */
	public void setBleedsFlag(boolean bleedsFlag) {
		this.bleedsFlag = bleedsFlag;
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
	 * @return the minPower
	 */
	public float getMinPower() {
		return minPower;
	}
	/**
	 * @param minPower the minPower to set
	 */
	public void setMinPower(float minPower) {
		this.minPower = minPower;
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
	 * @return the turbineDesignCd
	 */
	public String getTurbineDesignCd() {
		return turbineDesignCd;
	}
	/**
	 * @param turbineDesignCd the turbineDesignCd to set
	 */
	public void setTurbineDesignCd(String turbineDesignCd) {
		this.turbineDesignCd = turbineDesignCd;
	}
	/**
	 * @return the isFramePowerActive
	 */
	public boolean isFramePowerActive() {
		return isFramePowerActive;
	}
	/**
	 * @param isFramePowerActive the isFramePowerActive to set
	 */
	public void setFramePowerActive(boolean isFramePowerActive) {
		this.isFramePowerActive = isFramePowerActive;
	}
	/**
	 * @return the dependentCode
	 */
	public String getDependentCode() {
		return dependentCode;
	}
	/**
	 * @param dependentCode the dependentCode to set
	 */
	public void setDependentCode(String dependentCode) {
		this.dependentCode = dependentCode;
	}
	
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return the placeId
	 */
	public String getPlaceId() {
		return placeId;
	}
	/**
	 * @param placeId the placeId to set
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	/**
	 * @return the effectiveFrom
	 */
	public String getEffectiveFrom() {
		return effectiveFrom;
	}
	/**
	 * @param effectiveFrom the effectiveFrom to set
	 */
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	/**
	 * @return the effectiveTo
	 */
	public String getEffectiveTo() {
		return effectiveTo;
	}
	/**
	 * @param effectiveTo the effectiveTo to set
	 */
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	/**
	 * @return the framePowerDesc
	 */
	public String getFramePowerDesc() {
		return framePowerDesc;
	}
	/**
	 * @param framePowerDesc the framePowerDesc to set
	 */
	public void setFramePowerDesc(String framePowerDesc) {
		this.framePowerDesc = framePowerDesc;
	}
	/**
	 * @return the ssId
	 */
	public int getSsId() {
		return ssId;
	}
	/**
	 * @param ssId the ssId to set
	 */
	public void setSsId(int ssId) {
		this.ssId = ssId;
	}
	/**
	 * @return the ssName
	 */
	public String getSsName() {
		return ssName;
	}
	/**
	 * @param ssName the ssName to set
	 */
	public void setSsName(String ssName) {
		this.ssName = ssName;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the turbType
	 */
	public String getTurbType() {
		return turbType;
	}
	/**
	 * @param turbType the turbType to set
	 */
	public void setTurbType(String turbType) {
		this.turbType = turbType;
	}
	/**
	 * @return the turbdesignName
	 */
	public String getTurbdesignName() {
		return turbdesignName;
	}
	/**
	 * @param turbdesignName the turbdesignName to set
	 */
	public void setTurbdesignName(String turbdesignName) {
		this.turbdesignName = turbdesignName;
	}
	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}
	/**
	 * @return the dimension
	 */
	public String getDimension() {
		return dimension;
	}
	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(String dimension) {
		this.dimension = dimension;
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
	 * @return the instrId
	 */
	public int getInstrId() {
		return instrId;
	}
	/**
	 * @param instrId the instrId to set
	 */
	public void setInstrId(int instrId) {
		this.instrId = instrId;
	}
	/**
	 * @return the condTypId
	 */
	public int getCondTypId() {
		return condTypId;
	}
	/**
	 * @param condTypId the condTypId to set
	 */
	public void setCondTypId(int condTypId) {
		this.condTypId = condTypId;
	}
	/**
	 * @return the condensingtypes
	 */
	public String getCondensingtypes() {
		return condensingtypes;
	}
	/**
	 * @param condensingtypes the condensingtypes to set
	 */
	public void setCondensingtypes(String condensingtypes) {
		this.condensingtypes = condensingtypes;
	}
	/**
	 * @return the bleedTypId
	 */
	public int getBleedTypId() {
		return bleedTypId;
	}
	/**
	 * @param bleedTypId the bleedTypId to set
	 */
	public void setBleedTypId(int bleedTypId) {
		this.bleedTypId = bleedTypId;
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
	 * @return the percentIncr
	 */
	public float getPercentIncr() {
		return percentIncr;
	}
	/**
	 * @param percentIncr the percentIncr to set
	 */
	public void setPercentIncr(float percentIncr) {
		this.percentIncr = percentIncr;
	}
	/**
	 * @return the turbInstrCost
	 */
	public float getTurbInstrCost() {
		return turbInstrCost;
	}
	/**
	 * @param turbInstrCost the turbInstrCost to set
	 */
	public void setTurbInstrCost(float turbInstrCost) {
		this.turbInstrCost = turbInstrCost;
	}
	/**
	 * @return the condInstrCost
	 */
	public float getCondInstrCost() {
		return condInstrCost;
	}
	/**
	 * @param condInstrCost the condInstrCost to set
	 */
	public void setCondInstrCost(float condInstrCost) {
		this.condInstrCost = condInstrCost;
	}
	/**
	 * @return the subContrCost
	 */
	public float getSubContrCost() {
		return subContrCost;
	}
	/**
	 * @param subContrCost the subContrCost to set
	 */
	public void setSubContrCost(float subContrCost) {
		this.subContrCost = subContrCost;
	}
	/**
	 * @return the overHeads
	 */
	public float getOverHeads() {
		return overHeads;
	}
	/**
	 * @param overHeads the overHeads to set
	 */
	public void setOverHeads(float overHeads) {
		this.overHeads = overHeads;
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
	 * @return the assingedToId
	 */
	public int getAssingedToId() {
		return assingedToId;
	}
	/**
	 * @param assingedToId the assingedToId to set
	 */
	public void setAssingedToId(int assingedToId) {
		this.assingedToId = assingedToId;
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
	 * @return the modDt
	 */
	public Date getModDt() {
		return modDt;
	}
	/**
	 * @param modDt the modDt to set
	 */
	public void setModDt(Date modDt) {
		this.modDt = modDt;
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
	 * @return the updReqName
	 */
	public String getUpdReqName() {
		return updReqName;
	}
	/**
	 * @param updReqName the updReqName to set
	 */
	public void setUpdReqName(String updReqName) {
		this.updReqName = updReqName;
	}
	/**
	 * @return the prevOverHeads
	 */
	public float getPrevOverHeads() {
		return prevOverHeads;
	}
	/**
	 * @param prevOverHeads the prevOverHeads to set
	 */
	public void setPrevOverHeads(float prevOverHeads) {
		this.prevOverHeads = prevOverHeads;
	}
	/**
	 * @return the prevSubContrCost
	 */
	public float getPrevSubContrCost() {
		return prevSubContrCost;
	}
	/**
	 * @param prevSubContrCost the prevSubContrCost to set
	 */
	public void setPrevSubContrCost(float prevSubContrCost) {
		this.prevSubContrCost = prevSubContrCost;
	}
	/**
	 * @return the prevTurbInstrCost
	 */
	public float getPrevTurbInstrCost() {
		return prevTurbInstrCost;
	}
	/**
	 * @param prevTurbInstrCost the prevTurbInstrCost to set
	 */
	public void setPrevTurbInstrCost(float prevTurbInstrCost) {
		this.prevTurbInstrCost = prevTurbInstrCost;
	}
	/**
	 * @return the prevCondInstrCost
	 */
	public float getPrevCondInstrCost() {
		return prevCondInstrCost;
	}
	/**
	 * @param prevCondInstrCost the prevCondInstrCost to set
	 */
	public void setPrevCondInstrCost(float prevCondInstrCost) {
		this.prevCondInstrCost = prevCondInstrCost;
	}
	/**
	 * @return the suppChainCost
	 */
	public float getSuppChainCost() {
		return suppChainCost;
	}
	/**
	 * @param suppChainCost the suppChainCost to set
	 */
	public void setSuppChainCost(float suppChainCost) {
		this.suppChainCost = suppChainCost;
	}
	/**
	 * @return the estimateCost
	 */
	public float getEstimateCost() {
		return estimateCost;
	}
	/**
	 * @param estimateCost the estimateCost to set
	 */
	public void setEstimateCost(float estimateCost) {
		this.estimateCost = estimateCost;
	}
	/**
	 * @return the totalFTFCost
	 */
	public float getTotalFTFCost() {
		return totalFTFCost;
	}
	/**
	 * @param totalFTFCost the totalFTFCost to set
	 */
	public void setTotalFTFCost(float totalFTFCost) {
		this.totalFTFCost = totalFTFCost;
	}
	/**
	 * @return the grpCd
	 */
	public String getGrpCd() {
		return grpCd;
	}
	/**
	 * @param grpCd the grpCd to set
	 */
	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}
	/**
	 * @return the subDependentCode
	 */
	public String getSubDependentCode() {
		return subDependentCode;
	}
	/**
	 * @param subDependentCode the subDependentCode to set
	 */
	public void setSubDependentCode(String subDependentCode) {
		this.subDependentCode = subDependentCode;
	}
	public int getQuotId() {
		return QuotId;
	}
	public void setQuotId(int quotId) {
		QuotId = quotId;
	}
	private String condTypName;
	private String bleedTypName;
	private String variantTypeName;
	private float maximumPower;
	private String bleedTypCode;
	public String getCondTypName() {
		return condTypName;
	}
	public void setCondTypName(String condTypName) {
		this.condTypName = condTypName;
	}
	public String getBleedTypName() {
		return bleedTypName;
	}
	public void setBleedTypName(String bleedTypName) {
		this.bleedTypName = bleedTypName;
	}
	public String getVariantTypeName() {
		return variantTypeName;
	}
	public void setVariantTypeName(String variantTypeName) {
		this.variantTypeName = variantTypeName;
	}
	public float getMaximumPower() {
		return maximumPower;
	}
	public void setMaximumPower(float maximumPower) {
		this.maximumPower = maximumPower;
	}
	public String getBleedTypCode() {
		return bleedTypCode;
	}
	public void setBleedTypCode(String bleedTypCode) {
		this.bleedTypCode = bleedTypCode;
	}
	private String createdDate;
	private String modifiedDate;
	private String modifiedBy;
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
