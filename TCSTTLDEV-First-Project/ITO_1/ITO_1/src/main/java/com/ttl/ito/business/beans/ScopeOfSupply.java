package com.ttl.ito.business.beans;



public class ScopeOfSupply {
	private int scopeId;
	private int quotID;
	private int createdById;
	private int modifiedById;
	private int ssId;
	
	private String scopeCode;
	private String scopeName;
	private String createdBy;
	private String modifiedBy;
	private String createdDate;
	private String modifiedDate;
	private String status;
	private String grpCd;
	
	private String f2fItem;
	private int quantity;
	private float cost;
	private float totalCost;
	private boolean overwrittenPriceFlag;
	private float overwrittenPrice;
	private String techComments;
	private String comrComments;
	
	private boolean isActive;
	private boolean defaultValue;
	
	
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
	 * @return the quotID
	 */
	public int getQuotID() {
		return quotID;
	}
	/**
	 * @param quotID the quotID to set
	 */
	public void setQuotID(int quotID) {
		this.quotID = quotID;
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
	 * @return the scopeName
	 */
	public String getScopeName() {
		return scopeName;
	}
	/**
	 * @param scopeName the scopeName to set
	 */
	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}
	/**
	 * @return the defaultValue
	 */
	public boolean isDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
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
	 * @return the f2fItem
	 */
	public String getF2fItem() {
		return f2fItem;
	}
	/**
	 * @param f2fItem the f2fItem to set
	 */
	public void setF2fItem(String f2fItem) {
		this.f2fItem = f2fItem;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the cost
	 */
	
	/**
	 * @return the totalCost
	 */
	public float getTotalCost() {
		return totalCost;
	}
	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
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
	 * @return the techComments
	 */
	public String getTechComments() {
		return techComments;
	}
	/**
	 * @param techComments the techComments to set
	 */
	public void setTechComments(String techComments) {
		this.techComments = techComments;
	}
	/**
	 * @return the comrComments
	 */
	public String getComrComments() {
		return comrComments;
	}
	/**
	 * @param comrComments the comrComments to set
	 */
	public void setComrComments(String comrComments) {
		this.comrComments = comrComments;
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
	
}
