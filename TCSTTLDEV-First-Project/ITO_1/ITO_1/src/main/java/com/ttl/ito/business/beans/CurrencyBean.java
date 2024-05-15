
package com.ttl.ito.business.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CurrencyBean {
	
	private boolean isActive;
	
	private int successCode;
	private int CUR_ID;
	
	private String CUR_NM;
	private String CUR_CD;
	private String RUPEE;
	private String CREAT_BY;
	private String MOD_BY;
	private String successMsg;
	private float convertionRate;
	
	public float getConvertionRate() {
		return convertionRate;
	}

	public void setConvertionRate(float convertionRate) {
		this.convertionRate = convertionRate;
	}

	private Date CREAT_DT;
	private Date MOD_DT;
	private Date EFF_FROM;
	private Date EFF_TO;

	Map<Integer, String> msgToUser = new HashMap<Integer, String>();

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
	 * @return the successCode
	 */
	public int getSuccessCode() {
		return successCode;
	}

	/**
	 * @param successCode the successCode to set
	 */
	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}

	/**
	 * @return the successMsg
	 */
	public String getSuccessMsg() {
		return successMsg;
	}

	/**
	 * @param successMsg the successMsg to set
	 */
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	/**
	 * @return the cUR_ID
	 */
	public int getCUR_ID() {
		return CUR_ID;
	}

	/**
	 * @param cUR_ID the cUR_ID to set
	 */
	public void setCUR_ID(int cUR_ID) {
		CUR_ID = cUR_ID;
	}

	

	/**
	 * @return the cUR_CD
	 */
	public String getCUR_CD() {
		return CUR_CD;
	}

	/**
	 * @param cUR_CD the cUR_CD to set
	 */
	public void setCUR_CD(String cUR_CD) {
		CUR_CD = cUR_CD;
	}

	/**
	 * @return the rUPEE
	 */
	public String getRUPEE() {
		return RUPEE;
	}

	/**
	 * @param rUPEE the rUPEE to set
	 */
	public void setRUPEE(String rUPEE) {
		RUPEE = rUPEE;
	}

	/**
	 * @return the cREAT_BY
	 */
	public String getCREAT_BY() {
		return CREAT_BY;
	}

	/**
	 * @param cREAT_BY the cREAT_BY to set
	 */
	public void setCREAT_BY(String cREAT_BY) {
		CREAT_BY = cREAT_BY;
	}

	/**
	 * @return the mOD_BY
	 */
	public String getMOD_BY() {
		return MOD_BY;
	}

	/**
	 * @param mOD_BY the mOD_BY to set
	 */
	public void setMOD_BY(String mOD_BY) {
		MOD_BY = mOD_BY;
	}

	/**
	 * @return the cREAT_DT
	 */
	public Date getCREAT_DT() {
		return CREAT_DT;
	}

	/**
	 * @param cREAT_DT the cREAT_DT to set
	 */
	public void setCREAT_DT(Date cREAT_DT) {
		CREAT_DT = cREAT_DT;
	}

	/**
	 * @return the mOD_DT
	 */
	public Date getMOD_DT() {
		return MOD_DT;
	}

	/**
	 * @param mOD_DT the mOD_DT to set
	 */
	public void setMOD_DT(Date mOD_DT) {
		MOD_DT = mOD_DT;
	}

	/**
	 * @return the eFF_FROM
	 */
	public Date getEFF_FROM() {
		return EFF_FROM;
	}

	/**
	 * @param eFF_FROM the eFF_FROM to set
	 */
	public void setEFF_FROM(Date eFF_FROM) {
		EFF_FROM = eFF_FROM;
	}

	/**
	 * @return the eFF_TO
	 */
	public Date getEFF_TO() {
		return EFF_TO;
	}

	/**
	 * @param eFF_TO the eFF_TO to set
	 */
	public void setEFF_TO(Date eFF_TO) {
		EFF_TO = eFF_TO;
	}

	/**
	 * @return the msgToUser
	 */
	public Map<Integer, String> getMsgToUser() {
		return msgToUser;
	}

	/**
	 * @param msgToUser the msgToUser to set
	 */
	public void setMsgToUser(Map<Integer, String> msgToUser) {
		this.msgToUser = msgToUser;
	}

	/**
	 * @return the cUR_NM
	 */
	public String getCUR_NM() {
		return CUR_NM;
	}

	/**
	 * @param cUR_NM the cUR_NM to set
	 */
	public void setCUR_NM(String cUR_NM) {
		CUR_NM = cUR_NM;
	}

    

}
