package com.ttl.ito.business.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


/*
*
*
*created by Basavesh B R
*AddOnComponent class is used for creating bean which stores the AddonComponent Details
*
*/

public class AddOnComponent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	private int categoryDetId;
	public int getCategoryDetId() {
		return categoryDetId;
	}
	public void setCategoryDetId(int categoryDetId) {
		this.categoryDetId = categoryDetId;
	}
	public String getCategoryDetDesc() {
		return categoryDetDesc;
	}
	public void setCategoryDetDesc(String categoryDetDesc) {
		this.categoryDetDesc = categoryDetDesc;
	}
	public Object getFrameNm() {
		return frameNm;
	}
	public void setFrameNm(Object frameNm) {
		this.frameNm = frameNm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String categoryDetDesc;
	private String addOnCompo_cd;
	private String addOnCompoName;
	private String overwrittenRemarks;
	private String columnName;
	private String columnType;
	private String ColDataType;
	private String colValueCode;
	private String colValueDesc;
	private String categoryDetCode;
	private String createdDate;
	private String modifiedDate;
	private String effFromDate;
	private String effToDate;
	private int framePowId;
	private String frameName;
	

	private String remarks;
	private String make;
	private String subtype1;
	private String subtype2;
	private String makeCode;
	private String subtype1Code;
	private String subtype2Code;
	private String message;
	private String name;
	private String newCompRemark;
	private String newCompName;
	private String quantityName;
	private String statusName;
	private String assignedTo;
	private String createdByName;
	private String updReqName;
	
	private float sapCost;
	private float excelCost;
	private float selectedCost;
	private float quantity;
	private float newCompPrice;
	private float cost;
	private float price;
	private float prevPrice;
	
	private int excelCostFlag;
	private int AddOnTotal;
	private int sapCostFlag;
	private int selectedCostFlag;
	private int newCompQty;
	private int makeId;
	private int subtype1Id;
	private int subtype2Id;
	private int createdBy;
	private int modifiedBy;
	private int comp_detail_id;
	private int compoId; //generated from DB
	private int quotId;
	private int addOnCompoMastId;
	private int addOnCompoId;
	private int dependentParentId;
	private int statusId;
	private int assingedToId;
	
	private boolean flag;
	private boolean isActive;
	private boolean isDone;
	private boolean defaultCostFlag;
	private boolean addOnCompFlag;
	
	private String turbineCode;
	private String turbineType;
	private String turbineDesign;
	private int maxPower;


	private Set<String> subType1List=new HashSet<String>();
	private Set<String> subType2List=new HashSet<String>();
	private Set<String> makeList=new HashSet<String>();
	private Set<Float> quantityList=new HashSet<Float>();
	private Set<String> qtyNameList=new HashSet<String>();

	private Date modDt;


	private Object frameNm;
	
	/**
	 * @return the addOnCompoId
	 */
	public int getAddOnCompoId() {
		return addOnCompoId;
	}
	/**
	 * @param addOnCompoId the addOnCompoId to set
	 */
	public void setAddOnCompoId(int addOnCompoId) {
		this.addOnCompoId = addOnCompoId;
	}
	/**
	 * @return the addOnCompo_cd
	 */
	public String getAddOnCompo_cd() {
		return addOnCompo_cd;
	}
	/**
	 * @param addOnCompo_cd the addOnCompo_cd to set
	 */
	public void setAddOnCompo_cd(String addOnCompo_cd) {
		this.addOnCompo_cd = addOnCompo_cd;
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
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the modifiedBy
	 */
	public int getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(int modifiedBy) {
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
	 * @return the effFromDate
	 */
	public String getEffFromDate() {
		return effFromDate;
	}
	/**
	 * @param effFromDate the effFromDate to set
	 */
	public void setEffFromDate(String effFromDate) {
		this.effFromDate = effFromDate;
	}
	/**
	 * @return the effToDate
	 */
	public String getEffToDate() {
		return effToDate;
	}
	/**
	 * @param effToDate the effToDate to set
	 */
	public void setEffToDate(String effToDate) {
		this.effToDate = effToDate;
	}
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return the columnType
	 */
	public String getColumnType() {
		return columnType;
	}
	/**
	 * @param columnType the columnType to set
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	/**
	 * @return the colDataType
	 */
	public String getColDataType() {
		return ColDataType;
	}
	/**
	 * @param colDataType the colDataType to set
	 */
	public void setColDataType(String colDataType) {
		ColDataType = colDataType;
	}
	/**
	 * @return the comp_detail_id
	 */
	public int getComp_detail_id() {
		return comp_detail_id;
	}
	/**
	 * @param comp_detail_id the comp_detail_id to set
	 */
	public void setComp_detail_id(int comp_detail_id) {
		this.comp_detail_id = comp_detail_id;
	}
	/**
	 * @return the colValueCode
	 */
	public String getColValueCode() {
		return colValueCode;
	}
	/**
	 * @param colValueCode the colValueCode to set
	 */
	public void setColValueCode(String colValueCode) {
		this.colValueCode = colValueCode;
	}
	/**
	 * @return the colValueDesc
	 */
	public String getColValueDesc() {
		return colValueDesc;
	}
	/**
	 * @param colValueDesc the colValueDesc to set
	 */
	public void setColValueDesc(String colValueDesc) {
		this.colValueDesc = colValueDesc;
	}
	
	/**
	 * @return the dependentParentId
	 */
	public int getDependentParentId() {
		return dependentParentId;
	}
	/**
	 * @param dependentParentId the dependentParentId to set
	 */
	public void setDependentParentId(int dependentParentId) {
		this.dependentParentId = dependentParentId;
	}
	/**
	 * @return the makeId
	 */
	public int getMakeId() {
		return makeId;
	}
	/**
	 * @param makeId the makeId to set
	 */
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	/**
	 * @return the subtype1Id
	 */
	public int getSubtype1Id() {
		return subtype1Id;
	}
	/**
	 * @param subtype1Id the subtype1Id to set
	 */
	public void setSubtype1Id(int subtype1Id) {
		this.subtype1Id = subtype1Id;
	}
	/**
	 * @return the subtype2Id
	 */
	public int getSubtype2Id() {
		return subtype2Id;
	}
	/**
	 * @param subtype2Id the subtype2Id to set
	 */
	public void setSubtype2Id(int subtype2Id) {
		this.subtype2Id = subtype2Id;
	}
	
	/**
	 * @return the quantity
	 */
	public float getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the excelCostFlag
	 */
	public int getExcelCostFlag() {
		return excelCostFlag;
	}
	/**
	 * @param excelCostFlag the excelCostFlag to set
	 */
	public void setExcelCostFlag(int excelCostFlag) {
		this.excelCostFlag = excelCostFlag;
	}
	/**
	 * @return the sapCostFlag
	 */
	public int getSapCostFlag() {
		return sapCostFlag;
	}
	/**
	 * @param sapCostFlag the sapCostFlag to set
	 */
	public void setSapCostFlag(int sapCostFlag) {
		this.sapCostFlag = sapCostFlag;
	}
	/**
	 * @return the selectedCostFlag
	 */
	public int getSelectedCostFlag() {
		return selectedCostFlag;
	}
	/**
	 * @param selectedCostFlag the selectedCostFlag to set
	 */
	public void setSelectedCostFlag(int selectedCostFlag) {
		this.selectedCostFlag = selectedCostFlag;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the subtype1
	 */
	public String getSubtype1() {
		return subtype1;
	}
	/**
	 * @param subtype1 the subtype1 to set
	 */
	public void setSubtype1(String subtype1) {
		this.subtype1 = subtype1;
	}
	/**
	 * @return the subtype2
	 */
	public String getSubtype2() {
		return subtype2;
	}
	/**
	 * @param subtype2 the subtype2 to set
	 */
	public void setSubtype2(String subtype2) {
		this.subtype2 = subtype2;
	}
	
	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * @return the defaultCostFlag
	 */
	public boolean isDefaultCostFlag() {
		return defaultCostFlag;
	}
	/**
	 * @param defaultCostFlag the defaultCostFlag to set
	 */
	public void setDefaultCostFlag(boolean defaultCostFlag) {
		this.defaultCostFlag = defaultCostFlag;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddOnCompoName() {
		return addOnCompoName;
	}
	/**
	 * @param addOnCompoName the addOnCompoName to set
	 */
	public void setAddOnCompoName(String addOnCompoName) {
		this.addOnCompoName = addOnCompoName;
	}
	/**
	 * @return the overwrittenRemarks
	 */
	public String getOverwrittenRemarks() {
		return overwrittenRemarks;
	}
	/**
	 * @param overwrittenRemarks the overwrittenRemarks to set
	 */
	public void setOverwrittenRemarks(String overwrittenRemarks) {
		this.overwrittenRemarks = overwrittenRemarks;
	}
	/**
	 * @return the newCompName
	 */
	public String getNewCompName() {
		return newCompName;
	}
	/**
	 * @param newCompName the newCompName to set
	 */
	public void setNewCompName(String newCompName) {
		this.newCompName = newCompName;
	}
	/**
	 * @return the newCompQty
	 */
	public int getNewCompQty() {
		return newCompQty;
	}
	/**
	 * @param newCompQty the newCompQty to set
	 */
	public void setNewCompQty(int newCompQty) {
		this.newCompQty = newCompQty;
	}
	/**
	 * @return the newCompPrice
	 */
	public float getNewCompPrice() {
		return newCompPrice;
	}
	/**
	 * @param newCompPrice the newCompPrice to set
	 */
	public void setNewCompPrice(float newCompPrice) {
		this.newCompPrice = newCompPrice;
	}
	/**
	 * @return the newCompRemark
	 */
	public String getNewCompRemark() {
		return newCompRemark;
	}
	/**
	 * @param newCompRemark the newCompRemark to set
	 */
	public void setNewCompRemark(String newCompRemark) {
		this.newCompRemark = newCompRemark;
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
	 * @return the subType1List
	 */
	public Set<String> getSubType1List() {
		return subType1List;
	}
	/**
	 * @param subType1List the subType1List to set
	 */
	public void setSubType1List(Set<String> subType1List) {
		this.subType1List = subType1List;
	}
	/**
	 * @return the subType2List
	 */
	public Set<String> getSubType2List() {
		return subType2List;
	}
	/**
	 * @param subType2List the subType2List to set
	 */
	public void setSubType2List(Set<String> subType2List) {
		this.subType2List = subType2List;
	}
	/**
	 * @return the makeList
	 */
	public Set<String> getMakeList() {
		return makeList;
	}
	/**
	 * @param makeList the makeList to set
	 */
	public void setMakeList(Set<String> makeList) {
		this.makeList = makeList;
	}
	/**
	 * @return the quantityList
	 */
	public Set<Float> getQuantityList() {
		return quantityList;
	}
	/**
	 * @param quantityList the quantityList to set
	 */
	public void setQuantityList(Set<Float> quantityList) {
		this.quantityList = quantityList;
	}
	/**
	 * @return the makeCode
	 */
	public String getMakeCode() {
		return makeCode;
	}
	/**
	 * @param makeCode the makeCode to set
	 */
	public void setMakeCode(String makeCode) {
		this.makeCode = makeCode;
	}
	/**
	 * @return the subtype1Code
	 */
	public String getSubtype1Code() {
		return subtype1Code;
	}
	/**
	 * @param subtype1Code the subtype1Code to set
	 */
	public void setSubtype1Code(String subtype1Code) {
		this.subtype1Code = subtype1Code;
	}
	/**
	 * @return the subtype2Code
	 */
	public String getSubtype2Code() {
		return subtype2Code;
	}
	/**
	 * @param subtype2Code the subtype2Code to set
	 */
	public void setSubtype2Code(String subtype2Code) {
		this.subtype2Code = subtype2Code;
	}
	/**
	 * @return the sapCost
	 */
	public float getSapCost() {
		return sapCost;
	}
	/**
	 * @param sapCost the sapCost to set
	 */
	public void setSapCost(float sapCost) {
		this.sapCost = sapCost;
	}
	/**
	 * @return the excelCost
	 */
	public float getExcelCost() {
		return excelCost;
	}
	/**
	 * @param excelCost the excelCost to set
	 */
	public void setExcelCost(float excelCost) {
		this.excelCost = excelCost;
	}
	/**
	 * @return the selectedCost
	 */
	public float getSelectedCost() {
		return selectedCost;
	}
	/**
	 * @param selectedCost the selectedCost to set
	 */
	public void setSelectedCost(float selectedCost) {
		this.selectedCost = selectedCost;
	}
	/**
	 * @return the addOnTotal
	 */
	public int getAddOnTotal() {
		return AddOnTotal;
	}
	/**
	 * @param addOnTotal the addOnTotal to set
	 */
	public void setAddOnTotal(int addOnTotal) {
		AddOnTotal = addOnTotal;
	}
	/**
	 * @return the addOnCompoMastId
	 */
	public int getAddOnCompoMastId() {
		return addOnCompoMastId;
	}
	/**
	 * @param addOnCompoMastId the addOnCompoMastId to set
	 */
	public void setAddOnCompoMastId(int addOnCompoMastId) {
		this.addOnCompoMastId = addOnCompoMastId;
	}
	public void setQuantityName(String quantityName) {
		this.quantityName = quantityName;
		
	}
	public String getQuantityName() {
		return quantityName;
	}
	/**
	 * @return the qtyNameList
	 */
	public Set<String> getQtyNameList() {
		return qtyNameList;
	}
	/**
	 * @param qtyNameList the qtyNameList to set
	 */
	public void setQtyNameList(Set<String> qtyNameList) {
		this.qtyNameList = qtyNameList;
	}
	/**
	 * @return the framePowId
	 */
	public int getFramePowId() {
		return framePowId;
	}
	/**
	 * @param framePowId the framePowId to set
	 */
	public void setFramePowId(int framePowId) {
		this.framePowId = framePowId;
	}
	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}
	/**
	 * @return the isDone
	 */
	public boolean isDone() {
		return isDone;
	}
	/**
	 * @param isDone the isDone to set
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	public void setUpdateRequestNumber(int int1) {
		// TODO Auto-generated method stub
		
	}
	public void setFrameNm(String string) {
		this.frameNm = frameNm;
		
		
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
	 * @return the prevPrice
	 */
	public float getPrevPrice() {
		return prevPrice;
	}
	/**
	 * @param prevPrice the prevPrice to set
	 */
	public void setPrevPrice(float prevPrice) {
		this.prevPrice = prevPrice;
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
	 * @return the createdByName
	 */
	public String getCreatedByName() {
		return createdByName;
	}
	/**
	 * @param createdByName the createdByName to set
	 */
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
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
	 * @return the maxPower
	 */
	public int getMaxPower() {
		return maxPower;
	}
	/**
	 * @param maxPower the maxPower to set
	 */
	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
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
	 * @return the addOnCompFlag
	 */
	public boolean isAddOnCompFlag() {
		return addOnCompFlag;
	}
	/**
	 * @param addOnCompFlag the addOnCompFlag to set
	 */
	public void setAddOnCompFlag(boolean addOnCompFlag) {
		this.addOnCompFlag = addOnCompFlag;
	}
	

}
