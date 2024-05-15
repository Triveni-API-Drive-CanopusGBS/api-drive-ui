package com.ttl.ito.business.beans;

public class SaveQuesDetails {

	private int quotId;
	private int scopeOfSupplyId;
	private int quesTypeId;
	
	private String quesTypeName;
	
	private int answerId;
	private int quesAnswerkey;
	
	private String answerValue;
	private String  answerValueCode;

	public int getScopeOfSupplyId() {
		return scopeOfSupplyId;
	}

	public void setScopeOfSupplyId(int scopeOfSupplyId) {
		this.scopeOfSupplyId = scopeOfSupplyId;
	}

	/**
	 * @return the quesTypeId
	 */
	public int getQuesTypeId() {
		return quesTypeId;
	}

	/**
	 * @param quesTypeId the quesTypeId to set
	 */
	public void setQuesTypeId(int quesTypeId) {
		this.quesTypeId = quesTypeId;
	}

	/**
	 * @return the quesTypeName
	 */
	public String getQuesTypeName() {
		return quesTypeName;
	}

	/**
	 * @param quesTypeName the quesTypeName to set
	 */
	public void setQuesTypeName(String quesTypeName) {
		this.quesTypeName = quesTypeName;
	}

	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return the answerValue
	 */
	public String getAnswerValue() {
		return answerValue;
	}

	/**
	 * @param answerValue the answerValue to set
	 */
	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}

	/**
	 * @return the answerValueCode
	 */
	public String getAnswerValueCode() {
		return answerValueCode;
	}

	/**
	 * @param answerValueCode the answerValueCode to set
	 */
	public void setAnswerValueCode(String answerValueCode) {
		this.answerValueCode = answerValueCode;
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


}
