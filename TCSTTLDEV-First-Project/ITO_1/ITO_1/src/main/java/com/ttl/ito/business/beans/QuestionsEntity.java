package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.List;

public class QuestionsEntity implements Comparable<QuestionsEntity>{

	private int questionId;
	private int answerId;
	private int questionAnswerId;
	private int framePowerId;
	
	private String questionDesc;
	private String questionCode;
	private String frameName;
	private String answerCd;
	private String answerDesc;
	
	private boolean defaultVal;
	
	List<QuestionsEntity> questionsList = new ArrayList<QuestionsEntity>();

	/**
	 * @return the questionDesc
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}

	/**
	 * @param questionDesc
	 *            the questionDesc to set
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId
	 *            the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return the answerCd
	 */
	public String getAnswerCd() {
		return answerCd;
	}

	/**
	 * @param answerCd
	 *            the answerCd to set
	 */
	public void setAnswerCd(String answerCd) {
		this.answerCd = answerCd;
	}

	/**
	 * @return the defaultVal
	 */
	public boolean isDefaultVal() {
		return defaultVal;
	}

	/**
	 * @param defaultVal
	 *            the defaultVal to set
	 */
	public void setDefaultVal(boolean defaultVal) {
		this.defaultVal = defaultVal;
	}

	/**
	 * @return the questionAnswerId
	 */
	public int getQuestionAnswerId() {
		return questionAnswerId;
	}

	/**
	 * @param questionAnswerId
	 *            the questionAnswerId to set
	 */
	public void setQuestionAnswerId(int questionAnswerId) {
		this.questionAnswerId = questionAnswerId;
	}

	/**
	 * @return the frameName
	 */
	public String getFrameName() {
		return frameName;
	}

	/**
	 * @param frameName
	 *            the frameName to set
	 */
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}


	/**
	 * @return the answerDesc
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}

	/**
	 * @param answerDesc
	 *            the answerDesc to set
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}


	@Override
	public int compareTo(QuestionsEntity o) {
		if(this.getQuestionId()==o.getQuestionId()){
			return 0;
		}
		else if (this.getQuestionId()>o.getQuestionId()){
			return 1;
		}
		else{
			return -1;
		}
		
	}

	/**
	 * @return the questionCode
	 */
	public String getQuestionCode() {
		return questionCode;
	}

	/**
	 * @param questionCode the questionCode to set
	 */
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
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
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the questionsList
	 */
	public List<QuestionsEntity> getQuestionsList() {
		return questionsList;
	}

	/**
	 * @param questionsList the questionsList to set
	 */
	public void setQuestionsList(List<QuestionsEntity> questionsList) {
		this.questionsList = questionsList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionsEntity [questionId=" + questionId + ", questionDesc=" + questionDesc + ", questionCode=" + questionCode + ", answerId=" + answerId + ", answerCd=" + answerCd + ", answerDesc="
				+ answerDesc + ", defaultVal=" + defaultVal + ", questionAnswerId=" + questionAnswerId + ", framePowerId=" + framePowerId + ", frameName=" + frameName + ", questionsList="
				+ questionsList + "]";
	}

}
