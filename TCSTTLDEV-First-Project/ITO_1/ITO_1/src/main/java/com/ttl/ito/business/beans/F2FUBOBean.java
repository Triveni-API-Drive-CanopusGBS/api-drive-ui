package com.ttl.ito.business.beans;

import org.springframework.stereotype.Component;

@Component
public class F2FUBOBean {

	private boolean iscategoryActive;
	public boolean isIscategoryActive() {
		return iscategoryActive;
	}
	public void setIscategoryActive(boolean iscategoryActive) {
		this.iscategoryActive = iscategoryActive;
	}
	private int F2F_DET_ID;								
	private int F2F_ID;
	private int MTRL_ID;
	private int FRM_POW_ID;
	private int BLEED_TYP_ID;
	private int CAT_ID;
	private int condensingTypeId;
	private int updateRequestNumber;
	private int assingedToId;
	private int statusId;
	private int createdById;
	private int isActive=1;
	
	private String CAT_NM;
	private String CAT_CD;
	private String MTRL_NM;
	private String MTRL_CD;
	private String FRAME_NAME;
	private String condensingTypeName;
	private String status;
	private String assingedToName;
	private String modifiedDate;
	private String createdBy;
	
	private float PRICE;
	private float TOTAL_PRICE;
	private float PER_MWP_RICE;
	private float totalPreviousPrice;
	private float previousPrice;
	private float matPrice;
	
	
	/**
	 * 
	 * @return the f2F_ID
	 */
	public int getF2F_ID() {
		return F2F_ID;
	}
	/**
	 * @param f2f_ID the f2F_ID to set
	 */
	public void setF2F_ID(int f2f_ID) {
		F2F_ID = f2f_ID;
	}
	/**
	 * @return the mTRL_ID
	 */
	public int getMTRL_ID() {
		return MTRL_ID;
	}
	/**
	 * @param mTRL_ID the mTRL_ID to set
	 */
	public void setMTRL_ID(int mTRL_ID) {
		MTRL_ID = mTRL_ID;
	}
	/**
	 * @return the mTRL_NM
	 */
	public String getMTRL_NM() {
		return MTRL_NM;
	}
	/**
	 * @param mTRL_NM the mTRL_NM to set
	 */
	public void setMTRL_NM(String mTRL_NM) {
		MTRL_NM = mTRL_NM;
	}
	/**
	 * @return the cAT_ID
	 */
	public int getCAT_ID() {
		return CAT_ID;
	}
	/**
	 * @param cAT_ID the cAT_ID to set
	 */
	public void setCAT_ID(int cAT_ID) {
		CAT_ID = cAT_ID;
	}
	/**
	 * @return the cAT_NM
	 */
	public String getCAT_NM() {
		return CAT_NM;
	}
	/**
	 * @param cAT_NM the cAT_NM to set
	 */
	public void setCAT_NM(String cAT_NM) {
		CAT_NM = cAT_NM;
	}
	/**
	 * @return the fRM_POW_ID
	 */
	public int getFRM_POW_ID() {
		return FRM_POW_ID;
	}
	/**
	 * @param fRM_POW_ID the fRM_POW_ID to set
	 */
	public void setFRM_POW_ID(int fRM_POW_ID) {
		FRM_POW_ID = fRM_POW_ID;
	}
	/**
	 * @return the fRAME_NAME
	 */
	public String getFRAME_NAME() {
		return FRAME_NAME;
	}
	/**
	 * @param fRAME_NAME the fRAME_NAME to set
	 */
	public void setFRAME_NAME(String fRAME_NAME) {
		FRAME_NAME = fRAME_NAME;
	}
	/**
	 * @return the bLEED_TYP_ID
	 */
	public int getBLEED_TYP_ID() {
		return BLEED_TYP_ID;
	}
	/**
	 * @param bLEED_TYP_ID the bLEED_TYP_ID to set
	 */
	public void setBLEED_TYP_ID(int bLEED_TYP_ID) {
		BLEED_TYP_ID = bLEED_TYP_ID;
	}
	/**
	 * @return the pRICE
	 */
	public float getPRICE() {
		return PRICE;
	}
	/**
	 * @param pRICE the pRICE to set
	 */
	public void setPRICE(float pRICE) {
		PRICE = pRICE;
	}
	/**
	 * @return the tOTAL_PRICE
	 */
	public float getTOTAL_PRICE() {
		return TOTAL_PRICE;
	}
	/**
	 * @param tOTAL_PRICE the tOTAL_PRICE to set
	 */
	public void setTOTAL_PRICE(float tOTAL_PRICE) {
		TOTAL_PRICE = tOTAL_PRICE;
	}
	/**
	 * @return the pER_MWP_RICE
	 */
	public float getPER_MWP_RICE() {
		return PER_MWP_RICE;
	}
	/**
	 * @param pER_MWP_RICE the pER_MWP_RICE to set
	 */
	public void setPER_MWP_RICE(float pER_MWP_RICE) {
		PER_MWP_RICE = pER_MWP_RICE;
	}
	/**
	 * @return the f2F_DET_ID
	 */
	public int getF2F_DET_ID() {
		return F2F_DET_ID;
	}
	/**
	 * @param f2f_DET_ID the f2F_DET_ID to set
	 */
	public void setF2F_DET_ID(int f2f_DET_ID) {
		F2F_DET_ID = f2f_DET_ID;
	}
	/**
	 * @return the cAT_CD
	 */
	public String getCAT_CD() {
		return CAT_CD;
	}
	/**
	 * @param cAT_CD the cAT_CD to set
	 */
	public void setCAT_CD(String cAT_CD) {
		CAT_CD = cAT_CD;
	}
	/**
	 * @return the mTRL_CD
	 */
	public String getMTRL_CD() {
		return MTRL_CD;
	}
	/**
	 * @param mTRL_CD the mTRL_CD to set
	 */
	public void setMTRL_CD(String mTRL_CD) {
		MTRL_CD = mTRL_CD;
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
	 * @return the matPrice
	 */
	public float getMatPrice() {
		return matPrice;
	}
	/**
	 * @param matPrice the matPrice to set
	 */
	public void setMatPrice(float matPrice) {
		this.matPrice = matPrice;
	}
	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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
	
	private String updateRequestName;
	public String getUpdateRequestName() {
		return updateRequestName;
	}
	public void setUpdateRequestName(String updateRequestName) {
		this.updateRequestName = updateRequestName;
	}
	
	
}
