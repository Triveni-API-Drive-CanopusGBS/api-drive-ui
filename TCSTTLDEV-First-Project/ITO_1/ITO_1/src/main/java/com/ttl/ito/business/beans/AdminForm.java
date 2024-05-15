package com.ttl.ito.business.beans;

import java.awt.Image;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminForm {
	private String tableName;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getScopeDesc() {
		return scopeDesc;
	}
	public void setScopeDesc(String scopeDesc) {
		this.scopeDesc = scopeDesc;
	}
	private String scopeDesc;
	private int frameId;
	private int framePowerId;
	private int modifiedBy;
	private int successCode;
	private int quesKey;
	private int quotNo;
	private String auditEntry;
	private String scopeCode;
	private String fileName;
	private String filePath;
	private String extension;
	private String successMsg;
	private String quesDesc;
	private String quesCode;
	private String turbineDesign;
	private String typeOfTurbine;
	private int transType;
	private int portId;
	private int Id;
	private int itemId;
	private String itemCd;
	private String itemNm;
	private String itemDesc;
	private String item;
	private String image;
	private int quotId;
	private int modBy;
	
	private String createdDate;
	private String modifyDate;
	private String createdBy;
	private int modifyBy;
	private String modifyByNm;
	
	
	private List<TurbineDetails> newFrameWithPowersList = new ArrayList<>();
	private List<TurbineDetails> frameWithPowersList = new ArrayList<>();
	private List<TurbineAnswers> AnswersList = new ArrayList<>();
	private List<TurbineDetails> USDList = new ArrayList<>();
	
	private List<QuestionsBean> questionsBean = new ArrayList<QuestionsBean>();

	private List<AdminForm> Gridlist = new ArrayList<AdminForm>();
	private List<AdminForm> AdminFormlist = new ArrayList<AdminForm>();
	private List<AdminForm> Uploadlist = new ArrayList<AdminForm>();
	private List<AdminForm> AuditFormlist = new ArrayList<AdminForm>();
	private List<AdminForm> AuditDatelist = new ArrayList<AdminForm>();
	private List<AdminForm> AuditXmllist = new ArrayList<AdminForm>();
	private List<QuestionsEntity> questionAnswerSet = new ArrayList<>();
	private List<QuestionsEntity> questionsEntityList = new ArrayList<QuestionsEntity>();
	private List<UserProfileDetails> adminBgmCalcList=   new ArrayList<>();
	private List<UserProfileDetails> adminPerfAuxList=   new ArrayList<>();
	private List<UserProfileDetails> adminPerfOtherList=   new ArrayList<>();
	private List<UserProfileDetails> adminPerfAcDcMastList=   new ArrayList<>();
	private List<UserProfileDetails> adminPerfAcDcFrmInputList=   new ArrayList<>();
	private List<UserProfileDetails> sparesMastList=   new ArrayList<>();
	private List<UserProfileDetails> subSupplierMastList=   new ArrayList<>();
	private List<UserProfileDetails> adminQualityMastList=   new ArrayList<>();
	private List<UserProfileDetails> adminTerminalMastList=   new ArrayList<>();
	private List<UserProfileDetails> adminExclusionMastList=   new ArrayList<>();
	private List<UserProfileDetails> adminTenderDrawingMastList=   new ArrayList<>();
	private List<UserProfileDetails> adminServiceMastList=   new ArrayList<>();
	private List<UserProfileDetails> adminAcwrList=   new ArrayList<>();
	private List<UserProfileDetails> adminComrMonthsList=   new ArrayList<>();
	
	public List<UserProfileDetails> getAdminBgmCalcList() {
		return adminBgmCalcList;
	}
	public void setAdminBgmCalcList(List<UserProfileDetails> adminBgmCalcList) {
		this.adminBgmCalcList = adminBgmCalcList;
	}
	
	public List<UserProfileDetails> getAdminPerfAuxList() {
		return adminPerfAuxList;
	}
	public void setAdminPerfAuxList(List<UserProfileDetails> adminPerfAuxList) {
		this.adminPerfAuxList = adminPerfAuxList;
	}
	
	public List<UserProfileDetails> getAdminPerfOtherList() {
		return adminPerfOtherList;
	}
	public void setAdminPerfOtherList(List<UserProfileDetails> adminPerfOtherList) {
		this.adminPerfOtherList = adminPerfOtherList;
	}
	public List<UserProfileDetails> getAdminPerfAcDcMastList() {
		return adminPerfAcDcMastList;
	}
	public void setAdminPerfAcDcMastList(List<UserProfileDetails> adminPerfAcDcMastList) {
		this.adminPerfAcDcMastList = adminPerfAcDcMastList;
	}
	public List<UserProfileDetails> getAdminPerfAcDcFrmInputList() {
		return adminPerfAcDcFrmInputList;
	}
	public void setAdminPerfAcDcFrmInputList(List<UserProfileDetails> adminPerfAcDcFrmInputList) {
		this.adminPerfAcDcFrmInputList = adminPerfAcDcFrmInputList;
	}
	public List<UserProfileDetails> getSparesMastList() {
		return sparesMastList;
	}
	public void setSparesMastList(List<UserProfileDetails> sparesMastList) {
		this.sparesMastList = sparesMastList;
	}
	public List<UserProfileDetails> getSubSupplierMastList() {
		return subSupplierMastList;
	}
	public void setSubSupplierMastList(List<UserProfileDetails> subSupplierMastList) {
		this.subSupplierMastList = subSupplierMastList;
	}
	public List<UserProfileDetails> getAdminQualityMastList() {
		return adminQualityMastList;
	}
	public void setAdminQualityMastList(List<UserProfileDetails> adminQualityMastList) {
		this.adminQualityMastList = adminQualityMastList;
	}
	public List<UserProfileDetails> getAdminTerminalMastList() {
		return adminTerminalMastList;
	}
	public void setAdminTerminalMastList(List<UserProfileDetails> adminTerminalMastList) {
		this.adminTerminalMastList = adminTerminalMastList;
	}
	public List<UserProfileDetails> getAdminExclusionMastList() {
		return adminExclusionMastList;
	}
	public void setAdminExclusionMastList(List<UserProfileDetails> adminExclusionMastList) {
		this.adminExclusionMastList = adminExclusionMastList;
	}
	public List<UserProfileDetails> getAdminTenderDrawingMastList() {
		return adminTenderDrawingMastList;
	}
	public void setAdminTenderDrawingMastList(List<UserProfileDetails> adminTenderDrawingMastList) {
		this.adminTenderDrawingMastList = adminTenderDrawingMastList;
	}
	public List<UserProfileDetails> getAdminServiceMastList() {
		return adminServiceMastList;
	}
	public void setAdminServiceMastList(List<UserProfileDetails> adminServiceMastList) {
		this.adminServiceMastList = adminServiceMastList;
	}
	public List<UserProfileDetails> getAdminAcwrList() {
		return adminAcwrList;
	}
	public void setAdminAcwrList(List<UserProfileDetails> adminAcwrList) {
		this.adminAcwrList = adminAcwrList;
	}
	public List<UserProfileDetails> getAdminComrMonthsList() {
		return adminComrMonthsList;
	}
	public void setAdminComrMonthsList(List<UserProfileDetails> adminComrMonthsList) {
		this.adminComrMonthsList = adminComrMonthsList;
	}
	
	public List<UserProfileDetails> getAdminEleItemList() {
		return adminEleItemList;
	}
	public void setAdminEleItemList(List<UserProfileDetails> adminEleItemList) {
		this.adminEleItemList = adminEleItemList;
	}
	public List<UserProfileDetails> getAdminDcmPowerCalcList() {
		return adminDcmPowerCalcList;
	}
	public void setAdminDcmPowerCalcList(List<UserProfileDetails> adminDcmPowerCalcList) {
		this.adminDcmPowerCalcList = adminDcmPowerCalcList;
	}
	public List<UserProfileDetails> getAdminItemMastList() {
		return adminItemMastList;
	}
	public void setAdminItemMastList(List<UserProfileDetails> adminItemMastList) {
		this.adminItemMastList = adminItemMastList;
	}
	public List<UserProfileDetails> getAdminOthersMastList() {
		return adminOthersMastList;
	}
	public void setAdminOthersMastList(List<UserProfileDetails> adminOthersMastList) {
		this.adminOthersMastList = adminOthersMastList;
	}
	private List<UserProfileDetails> adminEleItemList=   new ArrayList<>();
	private List<UserProfileDetails> adminDcmPowerCalcList=   new ArrayList<>();
	private List<UserProfileDetails> adminItemMastList=   new ArrayList<>();
	private List<UserProfileDetails> adminOthersMastList=   new ArrayList<>();
	private List<UserProfileDetails> UserProfileDetailsList = new ArrayList<>();
	private List<TransportationDetailsBean> TransportDetailsList = new ArrayList<>();
	private List<ErectionCommissionBean> ecUpdatePriceList = new ArrayList<ErectionCommissionBean>();
	private List<CProjectList> CProjectWithVariantCodeList = new ArrayList<CProjectList>();
	private List<TurbineDetails> turbineInstrumentsList = new ArrayList<TurbineDetails>();

	
	
	private List<SaveQuesDetails> saveQuesDetailsList = new ArrayList<SaveQuesDetails>(10);
	
	private List<CurrencyBean> currencyBean=new ArrayList<>();
	private List<DBOBean> auditHistoryData=new ArrayList<>();
	private List<DBOBean> auditHistoryList= new ArrayList<DBOBean>();
	private List<DBOBean> nameList= new ArrayList<DBOBean>();
	
	public List<DBOBean> getNameList() {
		return nameList;
	}
	public void setNameList(List<DBOBean> nameList) {
		this.nameList = nameList;
	}
	public List<DBOBean> getAuditHistoryList() {
		return auditHistoryList;
	}
	public void setAuditHistoryList(List<DBOBean> auditHistoryList) {
		this.auditHistoryList = auditHistoryList;
	}
	public List<DBOBean> getAuditHistoryData() {
		return auditHistoryData;
	}
	public void setAuditHistoryData(List<DBOBean> auditHistoryData) {
		this.auditHistoryData = auditHistoryData;
	}
	private Map<Integer, String> msgToUser = new HashMap<Integer, String>();
	 
	DropDownColumnvalues dropDownColumnvalues = new DropDownColumnvalues();
	private QuestionsEntity questionsEntity = new QuestionsEntity();
	private SaveBasicDetails saveBasicDetails = new SaveBasicDetails();
	private TurbineDetails frameWithPowerDetails = new TurbineDetails();
	private boolean active;
	private int auditId;
	private int roleId;
	private float percent;
	
	
	
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public List<CurrencyBean> getCurrencyBean() {
		return currencyBean;
	}
	public void setCurrencyBean(List<CurrencyBean> currencyBean) {
		this.currencyBean = currencyBean;
	}
	/**
	 * @return the questionsEntity
	 */
	public QuestionsEntity getQuestionsEntity() {
		return questionsEntity;
	}
	/**
	 * @param questionsEntity the questionsEntity to set
	 */
	public void setQuestionsEntity(QuestionsEntity questionsEntity) {
		this.questionsEntity = questionsEntity;
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
	 * @return the frameWithPowersList
	 */
	public List<TurbineDetails> getFrameWithPowersList() {
		return frameWithPowersList;
	}
	/**
	 * @param frameWithPowersList the frameWithPowersList to set
	 */
	public void setFrameWithPowersList(List<TurbineDetails> frameWithPowersList) {
		this.frameWithPowersList = frameWithPowersList;
	}
	/**
	 * @return the frameWithPowerDetails
	 */
	public TurbineDetails getFrameWithPowerDetails() {
		return frameWithPowerDetails;
	}
	/**
	 * @param frameWithPowerDetails the frameWithPowerDetails to set
	 */
	public void setFrameWithPowerDetails(TurbineDetails frameWithPowerDetails) {
		this.frameWithPowerDetails = frameWithPowerDetails;
	}
	/**
	 * @return the dropDownColumnvalues
	 */
	public DropDownColumnvalues getDropDownColumnvalues() {
		return dropDownColumnvalues;
	}
	/**
	 * @param dropDownColumnvalues the dropDownColumnvalues to set
	 */
	public void setDropDownColumnvalues(DropDownColumnvalues dropDownColumnvalues) {
		this.dropDownColumnvalues = dropDownColumnvalues;
	}
	/**
	 * @return the answersList
	 */
	public List<TurbineAnswers> getAnswersList() {
		return AnswersList;
	}
	/**
	 * @param answersList the answersList to set
	 */
	public void setAnswersList(List<TurbineAnswers> answersList) {
		AnswersList = answersList;
	}
	/**
	 * @return the userProfileDetailsList
	 */
	public List<UserProfileDetails> getUserProfileDetailsList() {
		return UserProfileDetailsList;
	}
	/**
	 * @param userProfileDetailsList the userProfileDetailsList to set
	 */
	public void setUserProfileDetailsList(List<UserProfileDetails> userProfileDetailsList) {
		UserProfileDetailsList = userProfileDetailsList;
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
	 * @return the typeOfTurbine
	 */
	public String getTypeOfTurbine() {
		return typeOfTurbine;
	}
	/**
	 * @param typeOfTurbine the typeOfTurbine to set
	 */
	public void setTypeOfTurbine(String typeOfTurbine) {
		this.typeOfTurbine = typeOfTurbine;
	}
	/**
	 * @return the questionsBean
	 */
	public List<QuestionsBean> getQuestionsBean() {
		return questionsBean;
	}
	/**
	 * @param questionsBean the questionsBean to set
	 */
	public void setQuestionsBean(List<QuestionsBean> questionsBean) {
		this.questionsBean = questionsBean;
	}
	/**
	 * @return the questionAnswerSet
	 */
	public List<QuestionsEntity> getQuestionAnswerSet() {
		return questionAnswerSet;
	}
	/**
	 * @param questionAnswerSet the questionAnswerSet to set
	 */
	public void setQuestionAnswerSet(List<QuestionsEntity> questionAnswerSet) {
		this.questionAnswerSet = questionAnswerSet;
	}
	/**
	 * @return the questionsEntityList
	 */
	public List<QuestionsEntity> getQuestionsEntityList() {
		return questionsEntityList;
	}
	/**
	 * @param questionsEntityList the questionsEntityList to set
	 */
	public void setQuestionsEntityList(List<QuestionsEntity> questionsEntityList) {
		this.questionsEntityList = questionsEntityList;
	}
	/**
	 * @return the transportDetailsList
	 */
	public List<TransportationDetailsBean> getTransportDetailsList() {
		return TransportDetailsList;
	}
	/**
	 * @param transportDetailsList the transportDetailsList to set
	 */
	public void setTransportDetailsList(List<TransportationDetailsBean> transportDetailsList) {
		TransportDetailsList = transportDetailsList;
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
	 * @return the ecUpdatePriceList
	 */
	public List<ErectionCommissionBean> getEcUpdatePriceList() {
		return ecUpdatePriceList;
	}
	/**
	 * @param ecUpdatePriceList the ecUpdatePriceList to set
	 */
	public void setEcUpdatePriceList(List<ErectionCommissionBean> ecUpdatePriceList) {
		this.ecUpdatePriceList = ecUpdatePriceList;
	}
	/**
	 * @return the newFrameWithPowersList
	 */
	public List<TurbineDetails> getNewFrameWithPowersList() {
		return newFrameWithPowersList;
	}
	/**
	 * @param newFrameWithPowersList the newFrameWithPowersList to set
	 */
	public void setNewFrameWithPowersList(List<TurbineDetails> newFrameWithPowersList) {
		this.newFrameWithPowersList = newFrameWithPowersList;
	}
	/**
	 * @return the cProjectWithVariantCodeList
	 */
	public List<CProjectList> getCProjectWithVariantCodeList() {
		return CProjectWithVariantCodeList;
	}
	/**
	 * @param cProjectWithVariantCodeList the cProjectWithVariantCodeList to set
	 */
	public void setCProjectWithVariantCodeList(List<CProjectList> cProjectWithVariantCodeList) {
		CProjectWithVariantCodeList = cProjectWithVariantCodeList;
	}
	/**
	 * @return the uSDList
	 */
	public List<TurbineDetails> getUSDList() {
		return USDList;
	}
	/**
	 * @param uSDList the uSDList to set
	 */
	public void setUSDList(List<TurbineDetails> uSDList) {
		USDList = uSDList;
	}
	/**
	 * @return the saveQuesDetailsList
	 */
	public List<SaveQuesDetails> getSaveQuesDetailsList() {
		return saveQuesDetailsList;
	}
	/**
	 * @param saveQuesDetailsList the saveQuesDetailsList to set
	 */
	public void setSaveQuesDetailsList(List<SaveQuesDetails> saveQuesDetailsList) {
		this.saveQuesDetailsList = saveQuesDetailsList;
	}
	/**
	 * @return the saveBasicDetails
	 */
	public SaveBasicDetails getSaveBasicDetails() {
		return saveBasicDetails;
	}
	/**
	 * @param saveBasicDetails the saveBasicDetails to set
	 */
	public void setSaveBasicDetails(SaveBasicDetails saveBasicDetails) {
		this.saveBasicDetails = saveBasicDetails;
	}
	/**
	 * @return the turbineInstrumentsList
	 */
	public List<TurbineDetails> getTurbineInstrumentsList() {
		return turbineInstrumentsList;
	}
	/**
	 * @param turbineInstrumentsList the turbineInstrumentsList to set
	 */
	public void setTurbineInstrumentsList(List<TurbineDetails> turbineInstrumentsList) {
		this.turbineInstrumentsList = turbineInstrumentsList;
	}
	/**
	 * @return the transType
	 */
	public int getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(int transType) {
		this.transType = transType;
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
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}
	/**
	 * @param itemCd the itemCd to set
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	/**
	 * @return the itemNm
	 */
	public String getItemNm() {
		return itemNm;
	}
	/**
	 * @param itemNm the itemNm to set
	 */
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	/**
	 * @return the itemDesc
	 */
	public String getItemDesc() {
		return itemDesc;
	}
	/**
	 * @param itemDesc the itemDesc to set
	 */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	/**
	 * @return the image
	 */
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * @return the adminFormlist
	 */
	public List<AdminForm> getAdminFormlist() {
		return AdminFormlist;
	}
	/**
	 * @param adminFormlist the adminFormlist to set
	 */
	public void setAdminFormlist(List<AdminForm> adminFormlist) {
		AdminFormlist = adminFormlist;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean getActive() {
		return active;
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
	 * @return the modifyDate
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
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
	 * @return the modifyBy
	 */
	public int getModifyBy() {
		return modifyBy;
	}
	/**
	 * @param modifyBy the modifyBy to set
	 */
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the modifyByNm
	 */
	public String getModifyByNm() {
		return modifyByNm;
	}
	/**
	 * @param modifyByNm the modifyByNm to set
	 */
	public void setModifyByNm(String modifyByNm) {
		this.modifyByNm = modifyByNm;
	}
	
	/**
	 * @return the auditEntry
	 */
	public String getAuditEntry() {
		return auditEntry;
	}
	/**
	 * @param sqlxml the auditEntry to set
	 */
	public void setAuditEntry(String auditEntry) {
		this.auditEntry = auditEntry;
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
	 * @return the auditFormlist
	 */
	public List<AdminForm> getAuditFormlist() {
		return AuditFormlist;
	}
	/**
	 * @param auditFormlist the auditFormlist to set
	 */
	public void setAuditFormlist(List<AdminForm> auditFormlist) {
		AuditFormlist = auditFormlist;
	}
	/**
	 * @return the auditDatelist
	 */
	public List<AdminForm> getAuditDatelist() {
		return AuditDatelist;
	}
	/**
	 * @param auditDatelist the auditDatelist to set
	 */
	public void setAuditDatelist(List<AdminForm> auditDatelist) {
		AuditDatelist = auditDatelist;
	}
	/**
	 * @return the auditXmllist
	 */
	public List<AdminForm> getAuditXmllist() {
		return AuditXmllist;
	}
	/**
	 * @param auditXmllist the auditXmllist to set
	 */
	public void setAuditXmllist(List<AdminForm> auditXmllist) {
		AuditXmllist = auditXmllist;
	}
	/**
	 * @return the auditId
	 */
	public int getAuditId() {
		return auditId;
	}
	/**
	 * @param auditId the auditId to set
	 */
	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the uploadlist
	 */
	public List<AdminForm> getUploadlist() {
		return Uploadlist;
	}
	/**
	 * @param uploadlist the uploadlist to set
	 */
	public void setUploadlist(List<AdminForm> uploadlist) {
		Uploadlist = uploadlist;
	}
	/**
	 * @return the gridlist
	 */
	public List<AdminForm> getGridlist() {
		return Gridlist;
	}
	/**
	 * @param gridlist the gridlist to set
	 */
	public void setGridlist(List<AdminForm> gridlist) {
		Gridlist = gridlist;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * @return the quotNo
	 */
	public int getQuotNo() {
		return quotNo;
	}
	/**
	 * @param quotNo the quotNo to set
	 */
	public void setQuotNo(int quotNo) {
		this.quotNo = quotNo;
	}
	/**
	 * @return the quotID
	 */
	
	/**
	 * @return the modBy
	 */
	public int getModBy() {
		return modBy;
	}
	/**
	 * @param modBy the modBy to set
	 */
	public void setModBy(int modBy) {
		this.modBy = modBy;
	}
	/**
	 * @return the quotID
	 */
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
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	private int subItemId;
	private String typeOfPanel;
	private String make;
	
	public int getSubItemId() {
		return subItemId;
	}
	public void setSubItemId(int subItemId) {
		this.subItemId = subItemId;
	}
	public String getTypeOfPanel() {
		return typeOfPanel;
	}
	public void setTypeOfPanel(String typeOfPanel) {
		this.typeOfPanel = typeOfPanel;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	private float govPercent;
	private float subContrPercent;
	private float shopConvPercent;
	public float getGovPercent() {
		return govPercent;
	}
	public void setGovPercent(float govPercent) {
		this.govPercent = govPercent;
	}
	public float getSubContrPercent() {
		return subContrPercent;
	}
	public void setSubContrPercent(float subContrPercent) {
		this.subContrPercent = subContrPercent;
	}
	public float getShopConvPercent() {
		return shopConvPercent;
	}
	public void setShopConvPercent(float shopConvPercent) {
		this.shopConvPercent = shopConvPercent;
	}
	
	private String validationMsg;
	public String getValidationMsg() {
		return validationMsg;
	}
	public void setValidationMsg(String validationMsg) {
		this.validationMsg = validationMsg;
	}
}
	