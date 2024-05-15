package com.ttl.ito.business.beans;

public class CProjectList {
	private int id;
	private int custId;
	private int frameId;
	private int statusId;
	private int quesDropDownValueId;
	
	private String variantCode;
	private String custName;
	private String cNum;
	private String frameName;
	private String cProjectDate;
	private int variantActive;
	private boolean defaultFlag;
	private boolean isActive;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the cNum
	 */
	public String getcNum() {
		return cNum;
	}
	/**
	 * @param cNum the cNum to set
	 */
	public void setcNum(String cNum) {
		this.cNum = cNum;
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
	 * @return the quesDropDownValueId
	 */
	public int getQuesDropDownValueId() {
		return quesDropDownValueId;
	}
	/**
	 * @param quesDropDownValueId the quesDropDownValueId to set
	 */
	public void setQuesDropDownValueId(int quesDropDownValueId) {
		this.quesDropDownValueId = quesDropDownValueId;
	}
	public int getVariantActive() {
		return variantActive;
	}
	public void setVariantActive(int variantActive ) {
		this.variantActive=variantActive;
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
	 * @return the cProjectDate
	 */
	public String getcProjectDate() {
		return cProjectDate;
	}
	/**
	 * @param cProjectDate the cProjectDate to set
	 */
	public void setcProjectDate(String cProjectDate) {
		this.cProjectDate = cProjectDate;
	}
	/**
	 * @return the defaultFlag
	 */
	public boolean isDefaultFlag() {
		return defaultFlag;
	}
	/**
	 * @param defaultFlag the defaultFlag to set
	 */
	public void setDefaultFlag(boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
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
	

}
