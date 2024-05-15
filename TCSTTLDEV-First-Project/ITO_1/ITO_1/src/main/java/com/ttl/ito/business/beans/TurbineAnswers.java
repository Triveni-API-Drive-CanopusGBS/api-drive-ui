package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TurbineAnswers {
	private int ansKey;
	private String ansCode;
	private String ansDesc;
	
	private boolean defaultFlag;
	
	private int quesKey;
	private String quesCode;
	private String quesDesc;
	private List<Integer> framePowerList = new ArrayList<>();
	/**
	 * @return the ansKey
	 */
	public int getAnsKey() {
		return ansKey;
	}
	/**
	 * @param ansKey the ansKey to set
	 */
	public void setAnsKey(int ansKey) {
		this.ansKey = ansKey;
	}
	/**
	 * @return the ansCode
	 */
	public String getAnsCode() {
		return ansCode;
	}
	/**
	 * @param ansCode the ansCode to set
	 */
	public void setAnsCode(String ansCode) {
		this.ansCode = ansCode;
	}
	/**
	 * @return the ansDesc
	 */
	public String getAnsDesc() {
		return ansDesc;
	}
	/**
	 * @param ansDesc the ansDesc to set
	 */
	public void setAnsDesc(String ansDesc) {
		this.ansDesc = ansDesc;
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
	 * @return the quesCode
	 */
	public String getQuesCode() {
		return quesCode;
	}
	/**
	 * @param quesCode the quesCode to set
	 */
	public void setQuesCode(String quesCode) {
		this.quesCode = quesCode;
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
	 * @return the framePowerList
	 */
	public List<Integer> getFramePowerList() {
		return framePowerList;
	}
	/**
	 * @param framePowerList the framePowerList to set
	 */
	public void setFramePowerList(List<Integer> framePowerList) {
		this.framePowerList = framePowerList;
	}
	
		}
