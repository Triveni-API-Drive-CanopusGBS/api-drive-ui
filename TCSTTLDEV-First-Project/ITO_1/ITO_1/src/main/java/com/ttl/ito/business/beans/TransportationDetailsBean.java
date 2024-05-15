package com.ttl.ito.business.beans;

import org.springframework.stereotype.Component;

import com.ttl.ito.internal.beans.ItoConstants;


/*
created by Basavesh B R
*/
@Component
public class TransportationDetailsBean {
	private String subItemName;
	public String getSubItemName() {
		return subItemName;
	}
	public void setSubItemName(String subItemName) {
		this.subItemName = subItemName;
	}
	private int eleItemId;
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
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	private String bgmType;
	public String getBgmType() {
		return bgmType;
	}
	public void setBgmType(String bgmType) {
		this.bgmType = bgmType;
	}
	public float getBgmRating() {
		return bgmRating;
	}
	public void setBgmRating(float bgmRating) {
		this.bgmRating = bgmRating;
	}
	private float bgmRating;
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getGroupCd() {
		return groupCd;
	}
	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getNoOfMonths() {
		return noOfMonths;
	}
	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
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
	public String getScopeCode() {
		return scopeCode;
	}
	public void setScopeCode(String scopeCode) {
		this.scopeCode = scopeCode;
	}
	public String getUnItemCode() {
		return unItemCode;
	}
	public void setUnItemCode(String unItemCode) {
		this.unItemCode = unItemCode;
	}
	public String getUnItemName() {
		return unItemName;
	}
	public void setUnItemName(String unItemName) {
		this.unItemName = unItemName;
	}
	public int getF2fAddOn() {
		return f2fAddOn;
	}
	public void setF2fAddOn(int f2fAddOn) {
		this.f2fAddOn = f2fAddOn;
	}
	private String scopeCode;
	private String unItemCode;
	private String unItemName;
	private int f2fAddOn;
	private String itemCode;
	private String groupCd;
	private String groupName;
	private int noOfMonths;
	private int regionId;
	private float percent;
	private float eopMotorRating;
	private float cost;
	private String pumpType;
	private int bgHrRate;
	private float dcOutputVol;
	private int defaultFlag;
	private String typeOfPanel;
	private String eleType;
	private int itemId;
	private String itemName;
	private int subItemId;
	private String headerText;
	private String footerText;
	private String headerNm;
	private String exclusionNm;

	private int frameId;
	private int categoryDetId;
	private String categoryDetCode;

	private String categoryDetDesc;
	private String saveBasicDetails;
	

	public int getFrameId() {
		return frameId;
	}
	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}
	private int prevFOBId;
	private int fobId;
	private int unitId;
	private int vehicleId;
	private int compoId;
	private int length;
	private int placeId;
	private int prevPlaceId;
	private int numberOfVehicle;
	private int prevNoOfvehicles;
	private int transId;
	private int transMastId;
	private int transTypeId;
	private int condensingTypeId;
	private int compPriceFlag;
	private int totalPriceFlag;
	private int overwrittenPriceFlag;
	private int updateRequestNumber;
	private int assingedToId;
	private int ssId; // scope of supply ID
	private int framePowerId;
	private int packageTypeId;
	private int createdById;
	private int  f2fMastId;
	private int f2fDetId;
	private int mtrlId;
	private int catId;
	private int statusId;
	private int bleedTypeID;
	private int portId;
	private int prevVehicleId;
	
	private float minDistance;
	private float cifDomPrice;
	private float cifExPrice;
	private float fobPrice;
	private float maxDistance;
	private float unitPrice;
	private float previousUnitPrice;
	private float price;
	private float previousPrice;
	private float totalPrice;
	private float overwrittenPrice;
	private float compPrice;
	private float maxPower;
	private float totalPreviousPrice;
	private float distance;
	
	private String FOBPlaceCode;
	private String FOBPlace;
	private String prevFOBPlace;
	private String compoName;
	private String prevCompoName;
	private int prevCompoId;
	private String CompoCode;
	private String custCode;
	private String turbineDesignCode;
	private String turbineDesign;
	private String turbineCode;
	private String turbineType;
	private String custType;
	private String vehicleName;
	private String prevVehicleName;
	private String countryName;
	private String portName;
	private String transTypeCode;
	

	private String VehicleCode;
	private String dimension;
	private String transType;
	private String scopeOfSupply;
	private String condensingTypeName;
	private String address;
	private String remarks;
	private String status;
	private String assingedToName;
	private String modifiedDate;
	private String createdBy;
	private String ssName; //scope of supply name
	private String packageTypeName;
	private String packageTypeCode;
	private String toPlace;
	private String mtrlName;
	private String frameName;
	private String catName;
	
	private boolean isActive; 
	private boolean defaultVal;
	private boolean isupdatedflag;
	private String fobName;
	private String transTypeNm;
	private int variantId;
	private String varientCd;
	private int custId;
	private String statusName;
	private String cNum;
	
	
	/*private List placeIds;
	private List places;*/
	
	
	/**
	 * @return the unitId
	 */
	public int getUnitId() {
		return unitId;
	}
	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	/**
	 * @return the vehicleName
	 */
	public String getVehicleName() {
		return vehicleName;
	}
	/**
	 * @param vehicleName the vehicleName to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	/**
	 * @return the vehicleCode
	 */
	public String getVehicleCode() {
		return VehicleCode;
	}
	/**
	 * @param vehicleCode the vehicleCode to set
	 */
	public void setVehicleCode(String vehicleCode) {
		VehicleCode = vehicleCode;
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
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * @return the minDistance
	 */
	public float getMinDistance() {
		return minDistance;
	}
	/**
	 * @param minDistance the minDistance to set
	 */
	public void setMinDistance(float minDistance) {
		this.minDistance = minDistance;
	}
	/**
	 * @return the maxDistance
	 */
	public float getMaxDistance() {
		return maxDistance;
	}
	/**
	 * @param maxDistance the maxDistance to set
	 */
	public void setMaxDistance(float maxDistance) {
		this.maxDistance = maxDistance;
	}
	/**
	 * @return the unitPrice
	 */
	public float getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the fOBPlaceCode
	 */
	public String getFOBPlaceCode() {
		return FOBPlaceCode;
	}
	/**
	 * @param fOBPlaceCode the fOBPlaceCode to set
	 */
	public void setFOBPlaceCode(String fOBPlaceCode) {
		FOBPlaceCode = fOBPlaceCode;
	}
	/**
	 * @return the fOBPlace
	 */
	public String getFOBPlace() {
		return FOBPlace;
	}
	/**
	 * @param fOBPlace the fOBPlace to set
	 */
	public void setFOBPlace(String fOBPlace) {
		FOBPlace = fOBPlace;
	}
	/**
	 * @return the vehicleId
	 */
	public int getVehicleId() {
		return vehicleId;
	}
	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	/**
	 * @return the compoId
	 */
	public int getCompoId() {
		return compoId;
	}
	/**
	 * @param compoId the compoId to set
	 */
	public void setCompoId(int compoId) {
		this.compoId = compoId;
	}
	/**
	 * @return the compoName
	 */
	public String getCompoName() {
		return compoName;
	}
	/**
	 * @param compoName the compoName to set
	 */
	public void setCompoName(String compoName) {
		this.compoName = compoName;
	}
	/**
	 * @return the numberOfVehicle
	 */
	public int getNumberOfVehicle() {
		return numberOfVehicle;
	}
	/**
	 * @param numberOfVehicle the numberOfVehicle to set
	 */
	public void setNumberOfVehicle(int numberOfVehicle) {
		this.numberOfVehicle = numberOfVehicle;
	}
	/**
	 * @return the placeId
	 */
	public int getPlaceId() {
		return placeId;
	}
	/**
	 * @param placeId the placeId to set
	 */
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	/**
	 * @return the transId
	 */
	public int getTransId() {
		return transId;
	}
	/**
	 * @param transId the transId to set
	 */
	public void setTransId(int transId) {
		this.transId = transId;
	}
	/**
	 * @return the scopeOfSupply
	 */
	public String getScopeOfSupply() {
		return scopeOfSupply;
	}
	/**
	 * @param scopeOfSupply the scopeOfSupply to set
	 */
	public void setScopeOfSupply(String scopeOfSupply) {
		this.scopeOfSupply = scopeOfSupply;
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
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
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
	 * @return the totalPriceFlag
	 */
	public int getTotalPriceFlag() {
		return totalPriceFlag;
	}
	/**
	 * @param totalPriceFlag the totalPriceFlag to set
	 */
	public void setTotalPriceFlag(int totalPriceFlag) {
		this.totalPriceFlag = totalPriceFlag;
	}
	/**
	 * @return the overwrittenPriceFlag
	 */
	public int getOverwrittenPriceFlag() {
		return overwrittenPriceFlag;
	}
	/**
	 * @param overwrittenPriceFlag the overwrittenPriceFlag to set
	 */
	public void setOverwrittenPriceFlag(int overwrittenPriceFlag) {
		this.overwrittenPriceFlag = overwrittenPriceFlag;
	}
	/**
	 * @return the compPrice
	 */
	public float getCompPrice() {
		return compPrice;
	}
	/**
	 * @param compPrice the compPrice to set
	 */
	public void setCompPrice(float compPrice) {
		this.compPrice = compPrice;
	}
	/**
	 * @return the compPriceFlag
	 */
	public int getCompPriceFlag() {
		return compPriceFlag;
	}
	/**
	 * @param compPriceFlag the compPriceFlag to set
	 */
	public void setCompPriceFlag(int compPriceFlag) {
		this.compPriceFlag = compPriceFlag;
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
	 * @return the assingedToName
	 */
	public String getAssingedToName() {
		return assingedToName;
	}
	/**
	 * @param assingedToName the assingedToName to set
	 */
	public void setAssingedToName(String assingedToName) {
		this.assingedToName = assingedToName;
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
	 * @return the compoCode
	 */
	public String getCompoCode() {
		return CompoCode;
	}
	/**
	 * @param compoCode the compoCode to set
	 */
	public void setCompoCode(String compoCode) {
		CompoCode = compoCode;
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
	 * @return the previousUnitPrice
	 */
	public float getPreviousUnitPrice() {
		return previousUnitPrice;
	}
	/**
	 * @param previousUnitPrice the previousUnitPrice to set
	 */
	public void setPreviousUnitPrice(float previousUnitPrice) {
		this.previousUnitPrice = previousUnitPrice;
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
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
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
	 * @return the distance
	 */
	public float getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(float distance) {
		this.distance = distance;
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
	 * @return the isupdatedflag
	 */
	public boolean isIsupdatedflag() {
		return isupdatedflag;
	}
	/**
	 * @param isupdatedflag the isupdatedflag to set
	 */
	public void setIsupdatedflag(boolean isupdatedflag) {
		this.isupdatedflag = isupdatedflag;
	}
	/**
	
	/**
	 * @return the packageTypeName
	 */
	public String getPackageTypeName() {
		return packageTypeName;
	}
	/**
	 * @param packageTypeName the packageTypeName to set
	 */
	public void setPackageTypeName(String packageTypeName) {
		this.packageTypeName = packageTypeName;
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
	 * @return the transTypeId
	 */
	public int getTransTypeId() {
		return transTypeId;
	}
	/**
	 * @param transTypeId the transTypeId to set
	 */
	public void setTransTypeId(int transTypeId) {
		this.transTypeId = transTypeId;
	}
	/**
	 * @return the prevNoOfvehicles
	 */
	public int getPrevNoOfvehicles() {
		return prevNoOfvehicles;
	}
	/**
	 * @param prevNoOfvehicles the prevNoOfvehicles to set
	 */
	public void setPrevNoOfvehicles(int prevNoOfvehicles) {
		this.prevNoOfvehicles = prevNoOfvehicles;
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
	 * @return the toPlace
	 */
	public String getToPlace() {
		return toPlace;
	}
	/**
	 * @param toPlace the toPlace to set
	 */
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
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
	 * @return the f2fMastId
	 */
	public int getF2fMastId() {
		return f2fMastId;
	}
	/**
	 * @param f2fMastId the f2fMastId to set
	 */
	public void setF2fMastId(int f2fMastId) {
		this.f2fMastId = f2fMastId;
	}
	/**
	 * @return the f2fDetId
	 */
	public int getF2fDetId() {
		return f2fDetId;
	}
	/**
	 * @param f2fDetId the f2fDetId to set
	 */
	public void setF2fDetId(int f2fDetId) {
		this.f2fDetId = f2fDetId;
	}
	/**
	 * @return the mtrlId
	 */
	public int getMtrlId() {
		return mtrlId;
	}
	/**
	 * @param mtrlId the mtrlId to set
	 */
	public void setMtrlId(int mtrlId) {
		this.mtrlId = mtrlId;
	}
	/**
	 * @return the mtrlName
	 */
	public String getMtrlName() {
		return mtrlName;
	}
	/**
	 * @param mtrlName the mtrlName to set
	 */
	public void setMtrlName(String mtrlName) {
		this.mtrlName = mtrlName;
	}
	/**
	 * @return the catId
	 */
	public int getCatId() {
		return catId;
	}
	/**
	 * @param catId the catId to set
	 */
	public void setCatId(int catId) {
		this.catId = catId;
	}
	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}
	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * @return the bleedTypeID
	 */
	public int getBleedTypeID() {
		return bleedTypeID;
	}
	/**
	 * @param bleedTypeID the bleedTypeID to set
	 */
	public void setBleedTypeID(int bleedTypeID) {
		this.bleedTypeID = bleedTypeID;
	}
	/**
	 * @return the totalPreviousPrice
	 */
	public float getTotalPreviousPrice() {
		return totalPreviousPrice;
	}
	/**
	 * @param totalPreviousPrice the totalPreviousPrice to set
	 */
	public void setTotalPreviousPrice(float totalPreviousPrice) {
		this.totalPreviousPrice = totalPreviousPrice;
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
	 * @return the transMastId
	 */
	public int getTransMastId() {
		return transMastId;
	}
	/**
	 * @param transMastId the transMastId to set
	 */
	public void setTransMastId(int transMastId) {
		this.transMastId = transMastId;
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
	 * @return the prevPlaceId
	 */
	public int getPrevPlaceId() {
		return prevPlaceId;
	}
	/**
	 * @param prevPlaceId the prevPlaceId to set
	 */
	public void setPrevPlaceId(int prevPlaceId) {
		this.prevPlaceId = prevPlaceId;
	}
	/**
	 * @return the prevFOBPlace
	 */
	public String getPrevFOBPlace() {
		return prevFOBPlace;
	}
	/**
	 * @param prevFOBPlace the prevFOBPlace to set
	 */
	public void setPrevFOBPlace(String prevFOBPlace) {
		this.prevFOBPlace = prevFOBPlace;
	}
	/**
	 * @return the prevCompoName
	 */
	public String getPrevCompoName() {
		return prevCompoName;
	}
	/**
	 * @param prevCompoName the prevCompoName to set
	 */
	public void setPrevCompoName(String prevCompoName) {
		this.prevCompoName = prevCompoName;
	}
	/**
	 * @return the prevCompoId
	 */
	public int getPrevCompoId() {
		return prevCompoId;
	}
	/**
	 * @param prevCompoId the prevCompoId to set
	 */
	public void setPrevCompoId(int prevCompoId) {
		this.prevCompoId = prevCompoId;
	}
	/**
	 * @return the prevVehicleName
	 */
	public String getPrevVehicleName() {
		return prevVehicleName;
	}
	/**
	 * @param prevVehicleName the prevVehicleName to set
	 */
	public void setPrevVehicleName(String prevVehicleName) {
		this.prevVehicleName = prevVehicleName;
	}
	/**
	 * @return the prevVehicleId
	 */
	public int getPrevVehicleId() {
		return prevVehicleId;
	}
	/**
	 * @param prevVehicleId the prevVehicleId to set
	 */
	public void setPrevVehicleId(int prevVehicleId) {
		this.prevVehicleId = prevVehicleId;
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
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the portName
	 */
	public String getPortName() {
		return portName;
	}
	/**
	 * @param portName the portName to set
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}
	/**
	 * @return the cifDomPrice
	 */
	public float getCifDomPrice() {
		return cifDomPrice;
	}
	/**
	 * @param cifDomPrice the cifDomPrice to set
	 */
	public void setCifDomPrice(float cifDomPrice) {
		this.cifDomPrice = cifDomPrice;
	}
	/**
	 * @return the cifExPrice
	 */
	public float getCifExPrice() {
		return cifExPrice;
	}
	/**
	 * @param cifExPrice the cifExPrice to set
	 */
	public void setCifExPrice(float cifExPrice) {
		this.cifExPrice = cifExPrice;
	}
	/**
	 * @return the fobPrice
	 */
	public float getFobPrice() {
		return fobPrice;
	}
	/**
	 * @param fobPrice the fobPrice to set
	 */
	public void setFobPrice(float fobPrice) {
		this.fobPrice = fobPrice;
	}
	/**
	 * @return the packageTypeCode
	 */
	public String getPackageTypeCode() {
		return packageTypeCode;
	}
	/**
	 * @param packageTypeCode the packageTypeCode to set
	 */
	public void setPackageTypeCode(String packageTypeCode) {
		this.packageTypeCode = packageTypeCode;
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
	 * @return the fobName
	 */
	public String getFobName() {
		return fobName;
	}
	/**
	 * @param fobName the fobName to set
	 */
	public void setFobName(String fobName) {
		this.fobName = fobName;
	}
	/**
	 * @return the fobId
	 */
	public int getFobId() {
		return fobId;
	}
	/**
	 * @param fobId the fobId to set
	 */
	public void setFobId(int fobId) {
		this.fobId = fobId;
	}
	/**
	 * @return the prevFOBId
	 */
	public int getPrevFOBId() {
		return prevFOBId;
	}
	/**
	 * @param prevFOBId the prevFOBId to set
	 */
	public void setPrevFOBId(int prevFOBId) {
		this.prevFOBId = prevFOBId;
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
	 * @return the saveBasicDetails
	 */
	public String getSaveBasicDetails() {
		return saveBasicDetails;
	}
	/**
	 * @param saveBasicDetails the saveBasicDetails to set
	 */
	public void setSaveBasicDetails(String saveBasicDetails) {
		this.saveBasicDetails = saveBasicDetails;
	}
	/**
	 * @return the transTypeNm
	 */
	public String getTransTypeNm() {
		return transTypeNm;
	}
	/**
	 * @param transTypeNm the transTypeNm to set
	 */
	public void setTransTypeNm(String transTypeNm) {
		this.transTypeNm = transTypeNm;
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
	
	public int getVariant_id() {
		return variantId;
	}
	
	public void setVariant_id(int variantId) {
		this.variantId=variantId;	
	}
	
	public String getVariant_cd() {
		return varientCd;
	}
	
	public void setVariant_cd(String varientCd) {
		this.varientCd=varientCd;
	}
	
	public String getC_num() {
		return cNum;
	}
	
	public void setC_num(String cNum) {	
		this.cNum=cNum;
	}
	
	public int getCust_id() {
		return custId;
	}
	
	public void setCust_id(int custId) {
		this.custId=custId;
	}
	
	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		this.statusName=statusName;
	}
	private int auxId;
	private int condTypeId;
	private int frmId;
	private String condTypeName;
	private String consId;
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
	public String getCondTypeName() {
		return condTypeName;
	}

	public void setCondTypeName(String condTypeName) {
		this.condTypeName = condTypeName;
	}
	public int getFrmId() {
		return frmId;
	}
	
	public void setFrmId(int frmId) {
		this.frmId = frmId;
	}
	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
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
	private String itemCd;
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
	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
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
	private String frmName;
	public float getMinPower() {
		return minPower;
	}
	
	public void setMinPower(float minPower) {
		this.minPower = minPower;
	}
	public String getFrmName() {
		return frmName;
	}

	public void setFrmName(String frmName) {
		this.frmName = frmName;
	}
	
	private int instrId;
	private int bleedTypeId;
	private String bleedTypeName;
	private float estimationCost;
	private float turbInstrCost;
	private float condInstrCost;
	private float subContrCost;
	private float shopConvCost;
	private float totalFTFCost;
	
	public int getInstrId() {
		return instrId;
	}

	public void setInstrId(int instrId) {
		this.instrId = instrId;
	}
	public int getBleedTypeId() {
		return bleedTypeId;
	}
	public void setBleedTypeId(int bleedTypeId) {
		this.bleedTypeId = bleedTypeId;
	}
	public String getBleedTypeName() {
		return bleedTypeName;
	}

	public void setBleedTypeName(String bleedTypeName) {
		this.bleedTypeName = bleedTypeName;
	}
	
	public float getEstimationCost() {
		return estimationCost;
	}
	
	public void setEstimationCost(float estimationCost) {
		this.estimationCost = estimationCost;
	}
	
	public float getTurbInstrCost() {
		return turbInstrCost;
	}
	
	public void setTurbInstrCost(float turbInstrCost) {
		this.turbInstrCost = turbInstrCost;
	}
	
	public float getCondInstrCost() {
		return condInstrCost;
	}
	
	public void setCondInstrCost(float condInstrCost) {
		this.condInstrCost = condInstrCost;
	}
	
	public float getSubContrCost() {
		return subContrCost;
	}
	
	public void setSubContrCost(float subContrCost) {
		this.subContrCost = subContrCost;
	}
	
	public float getShopConvCost() {
		return shopConvCost;
	}
	
	public void setShopConvCost(float shopConvCost) {
		this.shopConvCost = shopConvCost;
	}
	
	public float getTotalFTFCost() {
		return totalFTFCost;
	}
	
	public void setTotalFTFCost(float totalFTFCost) {
		this.totalFTFCost = totalFTFCost;
	}
	private float prevEstimationCost;
	private float prevTurbInstrCost;
	private float prevCondInstrCost;
	private float prevSubContrCost;
	private float prevShopConvCost;
	private String updateRequestName;
	private int activeNew;
	public float getPrevEstimationCost() {
		return prevEstimationCost;
	}
	
	public void setPrevEstimationCost(float prevEstimationCost) {
		this.prevEstimationCost = prevEstimationCost;
	}
	public float getPrevTurbInstrCost() {
		return prevTurbInstrCost;
	}
	
	public void setPrevTurbInstrCost(float prevTurbInstrCost) {
		this.prevTurbInstrCost = prevTurbInstrCost;
	}
	public float getPrevCondInstrCost() {
		return prevCondInstrCost;
	}
	
	public void setPrevCondInstrCost(float prevCondInstrCost) {
		this.prevCondInstrCost = prevCondInstrCost;
	}
	public float getPrevSubContrCost() {
		return prevSubContrCost;
	}
	
	public void setPrevSubContrCost(float prevSubContrCost) {
		this.prevSubContrCost = prevSubContrCost;
	}
	public float getPrevShopConvCost() {
		return prevShopConvCost;
	}
	
	public void setPrevShopConvCost(float prevShopConvCost) {
		this.prevShopConvCost = prevShopConvCost;
	}
	public String getUpdateRequestName() {
		return updateRequestName;
	}

	public void setUpdateRequestName(String updateRequestName) {
		this.updateRequestName = updateRequestName;
	}
	public int getActiveNew() {
		return activeNew;
	}
	public void setActiveNew(int activeNew) {
		this.activeNew = activeNew;
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
	private String test;
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	private int variantType;
	public int getVariantType() {
		return variantType;
	}
	public void setVariantType(int variantType) {
		this.variantType = variantType;
	}
	private String variantTypeName;
	public String getVariantTypeName() {
		return variantTypeName;
	}

	public void setVariantTypeName(String variantTypeName) {
		this.variantTypeName = variantTypeName;
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
	private int improvedImpId;
	public int getImprovedImpId() {
		return improvedImpId;
	}

	public void setImprovedImpId(int improvedImpId) {
		this.improvedImpId = improvedImpId;
	}
	
	private int variantTypeId;
	public int getVariantTypeId() {
		return variantTypeId;
	}

	public void setVariantTypeId(int variantTypeId) {
		this.variantTypeId = variantTypeId;
	}
	private String regionName;
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	private String serviceRemarks;
	public String getServiceRemarks() {
		return serviceRemarks;
	}

	public void setServiceRemarks(String serviceRemarks) {
		this.serviceRemarks = serviceRemarks;
	}
	
	private float priceChennai;
	public float getPriceChennai() {
		return priceChennai;
	}
	public void setPriceChennai(float priceChennai) {
		this.priceChennai = priceChennai;
	}
	
	private float priceFob;
	public float getPriceFob() {
		return priceFob;
	}
	public void setPriceFob(float priceFob) {
		this.priceFob = priceFob;
	}
	
	private float prevChennaiPrice;
	public float getPrevChennaiPrice() {
		return prevChennaiPrice;
	}
	public void setPrevChennaiPrice(float prevChennaiPrice) {
		this.prevChennaiPrice = prevChennaiPrice;
	}
	
	private float prevPriceFob;
	public float getPrevPriceFob() {
		return prevPriceFob;
	}
	public void setPrevPriceFob(float prevPriceFob) {
		this.prevPriceFob = prevPriceFob;
	}
	
	private float chennaiPrice;
	public float getChennaiPrice() {
		return chennaiPrice;
	}
	public void setChennaiPrice(float chennaiPrice) {
		this.chennaiPrice = chennaiPrice;
	}
}
