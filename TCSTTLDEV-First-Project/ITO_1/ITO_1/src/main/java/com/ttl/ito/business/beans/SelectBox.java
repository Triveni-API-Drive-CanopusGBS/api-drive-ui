package com.ttl.ito.business.beans;

import org.springframework.stereotype.Component;

@Component
public class SelectBox {

	private int key;
	private int quesKey;
	private int quesAnswerkey;
	private int regionId;
	private int orderId;
	private int techFlag;
	private int inputCostFlag;
	private boolean edit;
	private boolean active;
	private boolean defaultVal;
	private boolean stdPriceFlag;
	private boolean percentageFlag;
	private boolean directPriceFlag;
	private boolean defaultFlag;
	
	private String quesDesc;
	private String value;
	private String dependKey;
	private String code;
	private String custCode;
	
	private float minVal;
	private float maxVal;
	private float directPrice;
	private float percentage;
	private float noOfMonths;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDependKey() {
		return dependKey;
	}

	public void setDependKey(String dependKey) {
		this.dependKey = dependKey;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the quesAnswerkey
	 */
	public int getQuesAnswerkey() {
		return quesAnswerkey;
	}

	/**
	 * @param quesAnswerkey the quesAnswerkey to set
	 */
	public void setQuesAnswerkey(int quesAnswerkey) {
		this.quesAnswerkey = quesAnswerkey;
	}

	/**
	 * @return the edit
	 */
	public boolean isEdit() {
		return edit;
	}

	/**
	 * @param edit the edit to set
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the quesKey
	 */
	public int getQuesKey() {
		return quesKey;
	}

	/**
	 * @param quesKey the quesKey to set
	 */
	public void setQuesKey(int quesKey) {
		this.quesKey = quesKey;
	}

	/**
	 * @return the quesDesc
	 */
	public String getQuesDesc() {
		return quesDesc;
	}

	/**
	 * @param quesDesc the quesDesc to set
	 */
	public void setQuesDesc(String quesDesc) {
		this.quesDesc = quesDesc;
	}
	

	/**
	 * @return the minVal
	 */
	public float getMinVal() {
		return minVal;
	}

	/**
	 * @param minVal the minVal to set
	 */
	public void setMinVal(float minVal) {
		this.minVal = minVal;
	}

	/**
	 * @return the maxVal
	 */
	public float getMaxVal() {
		return maxVal;
	}

	/**
	 * @param maxVal the maxVal to set
	 */
	public void setMaxVal(float maxVal) {
		this.maxVal = maxVal;
	}

	/**
	 * @return the stdPriceFlag
	 */
	public boolean isStdPriceFlag() {
		return stdPriceFlag;
	}

	/**
	 * @param stdPriceFlag the stdPriceFlag to set
	 */
	public void setStdPriceFlag(boolean stdPriceFlag) {
		this.stdPriceFlag = stdPriceFlag;
	}

	/**
	 * @return the percentageFlag
	 */
	public boolean isPercentageFlag() {
		return percentageFlag;
	}

	/**
	 * @param percentageFlag the percentageFlag to set
	 */
	public void setPercentageFlag(boolean percentageFlag) {
		this.percentageFlag = percentageFlag;
	}

	/**
	 * @return the directPrice
	 */
	public float getDirectPrice() {
		return directPrice;
	}

	/**
	 * @param directPrice the directPrice to set
	 */
	public void setDirectPrice(float directPrice) {
		this.directPrice = directPrice;
	}

	/**
	 * @return the percentage
	 */
	public float getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the directPriceFlag
	 */
	public boolean isDirectPriceFlag() {
		return directPriceFlag;
	}

	/**
	 * @param directPriceFlag the directPriceFlag to set
	 */
	public void setDirectPriceFlag(boolean directPriceFlag) {
		this.directPriceFlag = directPriceFlag;
	}

	/**
	 * @return the noOfMonths
	 */
	public float getNoOfMonths() {
		return noOfMonths;
	}

	/**
	 * @param noOfMonths the noOfMonths to set
	 */
	public void setNoOfMonths(float noOfMonths) {
		this.noOfMonths = noOfMonths;
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
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	/**
	 * @return the techFlag
	 */
	public int getTechFlag() {
		return techFlag;
	}

	/**
	 * @param techFlag the techFlag to set
	 */
	public void setTechFlag(int techFlag) {
		this.techFlag = techFlag;
	}

	public int getInputCostFlag() {
		return inputCostFlag;
	}

	public void setInputCostFlag(int inputCostFlag) {
		this.inputCostFlag = inputCostFlag;
	}
}
