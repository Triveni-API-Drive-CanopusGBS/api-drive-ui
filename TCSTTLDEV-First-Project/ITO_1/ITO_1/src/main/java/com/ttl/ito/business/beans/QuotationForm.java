package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ttl.ito.internal.beans.ItoConstants;
import com.ttl.ito.internal.beans.LoginBO;

@Component
public class QuotationForm {
	
	public String typeOfQuotation;
	public String getTypeOfQuotation() {
		return typeOfQuotation;
	}

	public void setTypeOfQuotation(String typeOfQuotation) {
		this.typeOfQuotation = typeOfQuotation;
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

	public String getDutySync() {
		return dutySync;
	}

	public void setDutySync(String dutySync) {
		this.dutySync = dutySync;
	}

	public String typeOfPanel;
	public String make;
	public String dutySync;
	

	
	List<DBOBean> generalInputList = new ArrayList<DBOBean>();
	
	public List<DBOBean> getGeneralInputList() {
		return generalInputList;
	}

	public void setGeneralInputList(List<DBOBean> generalInputList) {
		this.generalInputList = generalInputList;
	}

	List<DBOBean> attachmentDataList = new ArrayList<>();
	List<DBOBean> comercialDataList = new ArrayList<>();
	public List<DBOBean> getComercialDataList() {
		return comercialDataList;
	}

	public void setComercialDataList(List<DBOBean> comercialDataList) {
		this.comercialDataList = comercialDataList;
	}

	public List<DBOBean> getSavedComercialDataList2() {
		return savedComercialDataList2;
	}

	public void setSavedComercialDataList2(List<DBOBean> savedComercialDataList2) {
		this.savedComercialDataList2 = savedComercialDataList2;
	}

	public List<DBOBean> getSavedComercialDataList1() {
		return savedComercialDataList1;
	}

	public void setSavedComercialDataList1(List<DBOBean> savedComercialDataList1) {
		this.savedComercialDataList1 = savedComercialDataList1;
	}

	List<DBOBean>  savedComercialDataList2 = new ArrayList<>();
	List<DBOBean>  savedComercialDataList1 = new ArrayList<>();
	
public List<DBOBean> getAttachmentDataList() {
		return attachmentDataList;
	}

	public void setAttachmentDataList(List<DBOBean> attachmentDataList) {
		this.attachmentDataList = attachmentDataList;
	}

private int itemId;
	public int getItemId() {
	return itemId;
}

public void setItemId(int itemId) {
	this.itemId = itemId;
}

	private int noOfMandays;
	public int getNoOfMandays() {
		return noOfMandays;
	}

	public void setNoOfMandays(int noOfMandays) {
		this.noOfMandays = noOfMandays;
	}

	private String hmbdImage;

	public String getHmbdImage() {
		return hmbdImage;
	}

	public void setHmbdImage(String hmbdImage) {
		this.hmbdImage = hmbdImage;
	}

	private int successCode;
	private int loggedInUserId;
	private String Date;
	private String Subject;
	private String fixedText1;
	private String fixedText2;
	private String fixedText3;
	private String fixedText4;
	private String subCategoryName;
	private String categoryName;
	private List<DBOBean> savedUpdateF2fColValList= new ArrayList<>();
	private List<DBOBean>  savedUpdateMechPriceList= new ArrayList<>();
	private List<DBOBean>  savedUpdateMechAuxPriceList= new ArrayList<>();
	private List<DBOBean> savedUpdateMechAuxColValList= new ArrayList<>();
	private List<DBOBean> savedUpdateMechOverTankList= new ArrayList<>();
	private List<DBOBean> savedUpdateMechAddOnList= new ArrayList<>();
	public List<DBOBean> getSavedUpdateMechAddOnList() {
		return savedUpdateMechAddOnList;
	}

	public void setSavedUpdateMechAddOnList(List<DBOBean> savedUpdateMechAddOnList) {
		this.savedUpdateMechAddOnList = savedUpdateMechAddOnList;
	}

	public List<DBOBean> getUnsavedUpdateMechAddOnList() {
		return unsavedUpdateMechAddOnList;
	}

	public void setUnsavedUpdateMechAddOnList(List<DBOBean> unsavedUpdateMechAddOnList) {
		this.unsavedUpdateMechAddOnList = unsavedUpdateMechAddOnList;
	}

	private List<DBOBean> unsavedUpdateMechAddOnList= new ArrayList<>();
	public List<DBOBean> getSavedUpdateMechOverTankList() {
		return savedUpdateMechOverTankList;
	}

	public void setSavedUpdateMechOverTankList(List<DBOBean> savedUpdateMechOverTankList) {
		this.savedUpdateMechOverTankList = savedUpdateMechOverTankList;
	}

	public List<DBOBean> getUnsavedUpdateMechOverTankList() {
		return unsavedUpdateMechOverTankList;
	}

	public void setUnsavedUpdateMechOverTankList(List<DBOBean> unsavedUpdateMechOverTankList) {
		this.unsavedUpdateMechOverTankList = unsavedUpdateMechOverTankList;
	}

	private List<DBOBean> unsavedUpdateMechOverTankList= new ArrayList<>();
	public List<DBOBean> getSavedUpdateMechAuxColValList() {
		return savedUpdateMechAuxColValList;
	}

	public void setSavedUpdateMechAuxColValList(List<DBOBean> savedUpdateMechAuxColValList) {
		this.savedUpdateMechAuxColValList = savedUpdateMechAuxColValList;
	}

	public List<DBOBean> getUnsavedUpdateMechAuxColValList() {
		return unsavedUpdateMechAuxColValList;
	}

	public void setUnsavedUpdateMechAuxColValList(List<DBOBean> unsavedUpdateMechAuxColValList) {
		this.unsavedUpdateMechAuxColValList = unsavedUpdateMechAuxColValList;
	}

	private List<DBOBean> unsavedUpdateMechAuxColValList= new ArrayList<>();
	public List<DBOBean> getSavedUpdateMechAuxPriceList() {
		return savedUpdateMechAuxPriceList;
	}

	public void setSavedUpdateMechAuxPriceList(List<DBOBean> savedUpdateMechAuxPriceList) {
		this.savedUpdateMechAuxPriceList = savedUpdateMechAuxPriceList;
	}

	public List<DBOBean> getUnsavedUpdateMechAuxPriceList() {
		return unsavedUpdateMechAuxPriceList;
	}

	public void setUnsavedUpdateMechAuxPriceList(List<DBOBean> unsavedUpdateMechAuxPriceList) {
		this.unsavedUpdateMechAuxPriceList = unsavedUpdateMechAuxPriceList;
	}

	private List<DBOBean> unsavedUpdateMechAuxPriceList= new ArrayList<>();
	public List<DBOBean> getSavedUpdateMechPriceList() {
		return savedUpdateMechPriceList;
	}

	public void setSavedUpdateMechPriceList(List<DBOBean> savedUpdateMechPriceList) {
		this.savedUpdateMechPriceList = savedUpdateMechPriceList;
	}

	public List<DBOBean> getUnsavedUpdateMechPriceList() {
		return unsavedUpdateMechPriceList;
	}

	public void setUnsavedUpdateMechPriceList(List<DBOBean> unsavedUpdateMechPriceList) {
		this.unsavedUpdateMechPriceList = unsavedUpdateMechPriceList;
	}

	private List<DBOBean> unsavedUpdateMechPriceList= new ArrayList<>();
	public List<DBOBean> getSavedUpdateF2fColValList() {
		return savedUpdateF2fColValList;
	}

	public void setSavedUpdateF2fColValList(List<DBOBean> savedUpdateF2fColValList) {
		this.savedUpdateF2fColValList = savedUpdateF2fColValList;
	}

	public List<DBOBean> getUnsavedUpdateF2fColValList() {
		return unsavedUpdateF2fColValList;
	}

	public void setUnsavedUpdateF2fColValList(List<DBOBean> unsavedUpdateF2fColValList) {
		this.unsavedUpdateF2fColValList = unsavedUpdateF2fColValList;
	}
	private List<DBOBean> savedUpdateF2fFrameSpecificDataList= new ArrayList<>();
	public List<DBOBean> getSavedUpdateF2fFrameSpecificDataList() {
		return savedUpdateF2fFrameSpecificDataList;
	}

	public void setSavedUpdateF2fFrameSpecificDataList(List<DBOBean> savedUpdateF2fFrameSpecificDataList) {
		this.savedUpdateF2fFrameSpecificDataList = savedUpdateF2fFrameSpecificDataList;
	}

	public List<DBOBean> getUnsavedUpdateF2fFrameSpecificDataList() {
		return unsavedUpdateF2fFrameSpecificDataList;
	}

	public void setUnsavedUpdateF2fFrameSpecificDataList(List<DBOBean> unsavedUpdateF2fFrameSpecificDataList) {
		this.unsavedUpdateF2fFrameSpecificDataList = unsavedUpdateF2fFrameSpecificDataList;
	}

	private List<DBOBean> unsavedUpdateF2fFrameSpecificDataList= new ArrayList<>();
	private List<DBOBean>unsavedUpdateF2fColValList= new ArrayList<>();
	private List<DBOBean> unsavedUpdateF2fPriceList= new ArrayList<>();
	public List<DBOBean> getUnsavedUpdateF2fPriceList() {
		return unsavedUpdateF2fPriceList;
	}

	public void setUnsavedUpdateF2fPriceList(List<DBOBean> unsavedUpdateF2fPriceList) {
		this.unsavedUpdateF2fPriceList = unsavedUpdateF2fPriceList;
	}

	public List<DBOBean> getSavedUpdateF2fPriceList() {
		return savedUpdateF2fPriceList;
	}

	public void setSavedUpdateF2fPriceList(List<DBOBean> savedUpdateF2fPriceList) {
		this.savedUpdateF2fPriceList = savedUpdateF2fPriceList;
	}
	private List<DBOBean> unsavedUpdateMechAuxFrameSpecificDataList= new ArrayList<>();
	public List<DBOBean> getUnsavedUpdateMechAuxFrameSpecificDataList() {
		return unsavedUpdateMechAuxFrameSpecificDataList;
	}

	public void setUnsavedUpdateMechAuxFrameSpecificDataList(List<DBOBean> unsavedUpdateMechAuxFrameSpecificDataList) {
		this.unsavedUpdateMechAuxFrameSpecificDataList = unsavedUpdateMechAuxFrameSpecificDataList;
	}

	public List<DBOBean> getSavedUpdateMechAuxFrameSpecificDataList() {
		return savedUpdateMechAuxFrameSpecificDataList;
	}

	public void setSavedUpdateMechAuxFrameSpecificDataList(List<DBOBean> savedUpdateMechAuxFrameSpecificDataList) {
		this.savedUpdateMechAuxFrameSpecificDataList = savedUpdateMechAuxFrameSpecificDataList;
	}

	private List<DBOBean> savedUpdateMechAuxFrameSpecificDataList= new ArrayList<>();
	private List<DBOBean> savedUpdateF2fPriceList= new ArrayList<>();
	private List<DBOBean>  savedUpdatePriceElectricalList= new ArrayList<>();
	public List<DBOBean> getSavedUpdatePriceElectricalList() {
		return savedUpdatePriceElectricalList;
	}

	public void setSavedUpdatePriceElectricalList(List<DBOBean> savedUpdatePriceElectricalList) {
		this.savedUpdatePriceElectricalList = savedUpdatePriceElectricalList;
	}

	public List<DBOBean> getUnsavedUpdatePriceElectricalList() {
		return unsavedUpdatePriceElectricalList;
	}

	public void setUnsavedUpdatePriceElectricalList(List<DBOBean> unsavedUpdatePriceElectricalList) {
		this.unsavedUpdatePriceElectricalList = unsavedUpdatePriceElectricalList;
	}

	private List<DBOBean> unsavedUpdatePriceElectricalList= new ArrayList<>();
	private List<DBOBean> ecDataList= new ArrayList<>();
	public List<DBOBean> getEcDataList() {
		return ecDataList;
	}

	public void setEcDataList(List<DBOBean> ecDataList) {
		this.ecDataList = ecDataList;
	}

	private List<DBOBean> identListData= new ArrayList<>();
	
	public List<DBOBean> getIdentListData() {
		return identListData;
	}

	public void setIdentListData(List<DBOBean> identListData) {
		this.identListData = identListData;
	}

	private List<DBOBean> fixedListData= new ArrayList<>();
	public List<DBOBean> getFixedListData() {
		return fixedListData;
	}

	public void setFixedListData(List<DBOBean> fixedListData) {
		this.fixedListData = fixedListData;
	}

	private List<DBOBean> itemListData= new ArrayList<>();
	public List<DBOBean> getItemListData() {
		return itemListData;
	}

	public void setItemListData(List<DBOBean> itemListData) {
		this.itemListData = itemListData;
	}

	private List<DBOBean> cirListData= new ArrayList<>();
	public List<DBOBean> getCirListData() {
		return cirListData;
	}

	public void setCirListData(List<DBOBean> cirListData) {
		this.cirListData = cirListData;
	}

	private List<DBOBean> eleInstrList= new ArrayList<>();
	public List<DBOBean> getEleInstrList() {
		return eleInstrList;
	}

	public void setEleInstrList(List<DBOBean> eleInstrList) {
		this.eleInstrList = eleInstrList;
	}

	private List<DBOBean> otherGetDataList = new ArrayList<>();
	public List<DBOBean> getOtherGetDataList() {
		return otherGetDataList;
	}

	public void setOtherGetDataList(List<DBOBean> otherGetDataList) {
		this.otherGetDataList = otherGetDataList;
	}

	private List<DBOBean> savePerformanceList3 = new ArrayList<>();
	public List<DBOBean> getSavePerformanceList3() {
		return savePerformanceList3;
	}

	public void setSavePerformanceList3(List<DBOBean> savePerformanceList3) {
		this.savePerformanceList3 = savePerformanceList3;
	}

	private List<DBOBean> saveOtherChapterList = new ArrayList<>();
	public List<DBOBean> getSaveOtherChapterList() {
		return saveOtherChapterList;
	}

	public void setSaveOtherChapterList(List<DBOBean> saveOtherChapterList) {
		this.saveOtherChapterList = saveOtherChapterList;
	}

	private List<DBOBean> varCostData = new ArrayList<>();
	private List<DBOBean> sparesCostData = new ArrayList<>();
	private List<DBOBean> otherCostData = new ArrayList<>();
	private List<DBOBean> savePerformanceDataList1 = new ArrayList<>();
	public List<DBOBean> getSavePerformanceDataList1() {
		return savePerformanceDataList1;
	}

	public void setSavePerformanceDataList1(List<DBOBean> savePerformanceDataList1) {
		this.savePerformanceDataList1 = savePerformanceDataList1;
	}

	public List<DBOBean> getSavePerformanceDataList2() {
		return savePerformanceDataList2;
	}

	public void setSavePerformanceDataList2(List<DBOBean> savePerformanceDataList2) {
		this.savePerformanceDataList2 = savePerformanceDataList2;
	}

	private List<DBOBean> savePerformanceDataList2 = new ArrayList<>();
	private List<DBOBean> varQuestionsBean =new ArrayList<>();
	private List<DBOBean> sparesQuestionsBean =new ArrayList<>();
//	getVarQuestionsBean
//	setVarQuestionsBean
//	getSparesQuestionsBean
//	setSparesQuestionsBean
//	
	
	private int scopeId;
	private int price;
	private int quotId;
	private int ssId;
	private String scopeName;
	private String scopeCode;
    private String createdDate;
     private String modifiedDate;
     private String createdBy;
     private String modifiedBy;
     private int createdById;
     private int modifiedById;
     private String ecType;
	
	
	
	
	private String successMsg;
	
	private boolean editQuotFlow=false;

	List<QuotationHomeGrid> quotationHomeGrid = new ArrayList<QuotationHomeGrid>();
	List<QuotationHomeGrid> myQuotations = new ArrayList<QuotationHomeGrid>();
	
    List<TurbineDetails> frameWithPowersList = new ArrayList<>();
	
	List<SaveQuesDetails> saveQuesDetailsList = new ArrayList<SaveQuesDetails>(10);
	
	List<CProjectList> matchedCProjectList = new ArrayList<CProjectList>();
	List<CProjectList> cProjectList = new ArrayList<CProjectList>();
	List<CProjectList> CProjectWithVariantCodeList = new ArrayList<CProjectList>();
	
	List<UserProfileDetails> userDetailsList = new ArrayList<UserProfileDetails>();
	
	List<FirmDetails> firmList = new ArrayList<FirmDetails>();
	
	List<QuestionsBean> questionsBean = new ArrayList<QuestionsBean>();
	
	List<QuestionsEntity> SelectedQuestionAnswerSet = new ArrayList<>();
	List<QuestionsEntity> questionsEntityList = new ArrayList<QuestionsEntity>();
	
	List<F2FForm> f2FDataList = new ArrayList<F2FForm>();
	
	List<F2FCostBean> f2fCostBeanList = new ArrayList<F2FCostBean>();
	List<DBOBean>  eleSubList = new ArrayList<>();
	public List<DBOBean> getEleSubList() {
		return eleSubList;
	}

	public void setEleSubList(List<DBOBean> eleSubList) {
		this.eleSubList = eleSubList;
	}

	List<ScopeOfSupply> scopeOfSupplyList = new ArrayList<>();
	List<DBOBean> performanceParameterList  = new ArrayList<>();
	public List<DBOBean> getPerformanceParameterList() {
		return performanceParameterList;
	}

	public void setPerformanceParameterList(List<DBOBean> performanceParameterList) {
		this.performanceParameterList = performanceParameterList;
	}

	public List<DBOBean> getPerformanceUnitList() {
		return performanceUnitList;
	}

	public void setPerformanceUnitList(List<DBOBean> performanceUnitList) {
		this.performanceUnitList = performanceUnitList;
	}

	List<DBOBean> performanceUnitList= new ArrayList<>();
	List<DBOBean> savedList = new ArrayList<>();
	List<DBOBean> f2fGeneralList = new ArrayList<>();
	List<DBOBean> mechGeneralList = new ArrayList<>();
	List<DBOBean> mechAuxGeneralList = new ArrayList<>();
	List<DBOBean> eleGeneralList = new ArrayList<>();
	List<DBOBean> mechAuxGeneralListNew = new ArrayList<>();
	List<DBOBean> f2fOthersItemList = new ArrayList<>();
	List<DBOBean> f2fOthersSubItemList = new ArrayList<>();
	List<DBOBean> f2fOthersSubItemTypeList = new ArrayList<>();
	List<DBOBean> f2fAddOnList = new ArrayList<>();
	List<DBOBean> f2fAddOnList1 = new ArrayList<>();
	List<DBOBean> saveEleFilterList = new ArrayList<>();
	List<DBOBean> eleExtScopeList1= new ArrayList<>();
	public List<DBOBean> getEleExtScopeList1() {
		return eleExtScopeList1;
	}

	public void setEleExtScopeList1(List<DBOBean> eleExtScopeList1) {
		this.eleExtScopeList1 = eleExtScopeList1;
	}

	List<DBOBean> mechAddOnList1 = new ArrayList<>();
	List<DBOBean> eleOtherList  = new ArrayList<>();
	List<DBOBean> eleGeneralSpecialList  = new ArrayList<>();
	List<DBOBean> saveEleFilterList1 = new ArrayList<>();
	List<DBOBean> eleExtScopeList = new ArrayList<>();
	List<DBOBean> eleCIExtScopeList = new ArrayList<>();
	List<DBOBean> saveVmsDataList = new ArrayList<>();
	public List<DBOBean> getSaveVmsDataList() {
		return saveVmsDataList;
	}

	public void setSaveVmsDataList(List<DBOBean> saveVmsDataList) {
		this.saveVmsDataList = saveVmsDataList;
	}

	public List<DBOBean> getEleCIExtScopeList() {
		return eleCIExtScopeList;
	}

	public void setEleCIExtScopeList(List<DBOBean> eleCIExtScopeList) {
		this.eleCIExtScopeList = eleCIExtScopeList;
	}

	public List<DBOBean> getEleExtScopeList() {
		return eleExtScopeList;
	}

	public void setEleExtScopeList(List<DBOBean> eleExtScopeList) {
		this.eleExtScopeList = eleExtScopeList;
	}

	public List<DBOBean> getSaveEleFilterList1() {
		return saveEleFilterList1;
	}

	public void setSaveEleFilterList1(List<DBOBean> saveEleFilterList1) {
		this.saveEleFilterList1 = saveEleFilterList1;
	}

	public List<DBOBean> getEleInstrFilterList() {
		return eleInstrFilterList;
	}

	public void setEleInstrFilterList(List<DBOBean> eleInstrFilterList) {
		this.eleInstrFilterList = eleInstrFilterList;
	}

	List<DBOBean>  eleInstrFilterList = new ArrayList<>();
	List<ScopeOfSupply> scopeOfSupplyStatusList = new ArrayList<>();
	
	List<AddOnComponent> submittedAddOnList = new ArrayList<AddOnComponent>();
	List<AddOnComponent> submittedAddOnListCost = new ArrayList<AddOnComponent>();
	List<AddOnComponent> addonDefaultList = new ArrayList<>();
	List<AddOnComponent> addOnComponentCostSheetList = new ArrayList<>();
	List<AddOnComponent> addOnComponentsList = new ArrayList<>();
	List<AddOnComponent> createUpdateAddonList = new ArrayList<AddOnComponent>();
	List<AddOnComponent> savedAddonList  = new ArrayList<AddOnComponent>();
	List<AddOnComponent> unsavedAddonList = new ArrayList<AddOnComponent>();
	
	
	List<TransportationDetailsBean> transportationDetailList = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> packageDetailList = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> UpdatePriceTransportList = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> savedUpdatePriceTransportList = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> unsavedUpdatePriceTransportList = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> packageDetailsListData = new ArrayList<TransportationDetailsBean>();
	
	List<TransportationDetailsBean> savedUpdatePriceTransportListDomestic = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> unsavedUpdatePriceTransportListDomestic = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> savedUpdatePriceTransportListExport = new ArrayList<TransportationDetailsBean>();
	List<TransportationDetailsBean> unsavedUpdatePriceTransportListExport= new ArrayList<TransportationDetailsBean>();
	
	List<ErectionCommissionBean> savedUpdateECPriceList = new ArrayList<ErectionCommissionBean>();
	List<ErectionCommissionBean> unsavedUpdateECPriceList = new ArrayList<ErectionCommissionBean>();
	List<ErectionCommissionBean> errectionCommList = new ArrayList<ErectionCommissionBean>();
	
	List<PackageBean> savedUpdatePKGPriceList = new ArrayList<PackageBean>();
	List<PackageBean> unsavedUpdatePKGPriceList = new ArrayList<PackageBean>();
	List<PackageBean> packageupdatedPriceList = new ArrayList<>();
	List<PackageBean> packageDetailsWithPriceList = new ArrayList<PackageBean>();
	
	List<SaveBasicDetails>  updatePriceMyRequestGrid = new ArrayList<>();
	List<SaveBasicDetails>  updatePriceOthersRequestGrid = new ArrayList<>();
	
	List<TurbineDetails> newFrameWithPowersList = new ArrayList<>();
	List<TurbineDetails> costSheetList = new ArrayList<>();
	List<TurbineDetails> overHeadSheetList = new ArrayList<>();
	List<TurbineDetails> savedCostList= new ArrayList<>();
	List<TurbineDetails> unSavedCostList= new ArrayList<>();
	List<TurbineDetails> turbineInstrumentList = new ArrayList<>();
	List<TurbineDetails> selectedScopeList = new ArrayList<>();
	List<DBOBean> f2fList = new ArrayList<>();
	
	List<CommentBean> commentList = new ArrayList<CommentBean>();
	
	List<F2FUBOBean> f2FUBOList= new ArrayList<>();
	List<F2FUBOBean> f2FUBOsetterList= new ArrayList<>();
	List<F2FUBOBean> f2FUboSavedList= new ArrayList<>();
	List<F2FUBOBean> f2FUboUnsavedList= new ArrayList<>();

	List<Map<String,List<Entry>>> tree = new ArrayList<>();
	
	List<DBOBean> quotDboMechList = new ArrayList<DBOBean>();
	List<DBOBean> quotDboMechOthersList = new ArrayList<DBOBean>();
	List<AddOnComponent> quotAddOnOthersList = new ArrayList<AddOnComponent>();
	List<DBOBean> quotDboEleOthersList = new ArrayList<DBOBean>();
	List<DBOBean> quotDboElectricalList = new ArrayList<DBOBean>();
	List<DBOBean> quotDboEleSplAddOnList = new ArrayList<DBOBean>();
	List<DBOBean> savedDboMechList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedDboMechList = new ArrayList<DBOBean>();
	List<DBOBean> savedDboList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedDboList = new ArrayList<DBOBean>();
	List<DBOBean> savedDboEleList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedDboEleList = new ArrayList<DBOBean>();
	List<DBOBean> savedDboEleAddOnList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedDboEleAddOnList = new ArrayList<DBOBean>();
	List<DBOBean> savedDboEleAddInstrList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedDboEleAddInstrList = new ArrayList<DBOBean>();
	List<DBOBean> savedDboEleSplAddOnList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedDboEleSplAddOnList = new ArrayList<DBOBean>();
	List<DBOBean> savedDboMechColList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedDboMechColList = new ArrayList<DBOBean>();
	List<DBOBean> savedDboEleColList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedDboEleColList = new ArrayList<DBOBean>();
	List<DBOBean> AddInstrList = new ArrayList<DBOBean>();
	List<DBOBean> dboEleAddOnList = new ArrayList<DBOBean>();
	List<DBOBean> mechExtScpList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedUpdateEleVms = new ArrayList<DBOBean>();
	public List<DBOBean> getUnsavedUpdateEleVms() {
		return unsavedUpdateEleVms;
	}

	public void setUnsavedUpdateEleVms(List<DBOBean> unsavedUpdateEleVms) {
		this.unsavedUpdateEleVms = unsavedUpdateEleVms;
	}

	public List<DBOBean> getSavedUpdateEleVms() {
		return savedUpdateEleVms;
	}

	public void setSavedUpdateEleVms(List<DBOBean> savedUpdateEleVms) {
		this.savedUpdateEleVms = savedUpdateEleVms;
	}

	public List<DBOBean> getUnsavedUpdateEleInstrList() {
		return unsavedUpdateEleInstrList;
	}

	public void setUnsavedUpdateEleInstrList(List<DBOBean> unsavedUpdateEleInstrList) {
		this.unsavedUpdateEleInstrList = unsavedUpdateEleInstrList;
	}

	public List<DBOBean> getSavedUpdateEleInstrList() {
		return savedUpdateEleInstrList;
	}

	public void setSavedUpdateEleInstrList(List<DBOBean> savedUpdateEleInstrList) {
		this.savedUpdateEleInstrList = savedUpdateEleInstrList;
	}

	public List<DBOBean> getUnsavedUpdateEleColValList() {
		return unsavedUpdateEleColValList;
	}

	public void setUnsavedUpdateEleColValList(List<DBOBean> unsavedUpdateEleColValList) {
		this.unsavedUpdateEleColValList = unsavedUpdateEleColValList;
	}

	public List<DBOBean> getSavedUpdateEleColValList() {
		return savedUpdateEleColValList;
	}

	public void setSavedUpdateEleColValList(List<DBOBean> savedUpdateEleColValList) {
		this.savedUpdateEleColValList = savedUpdateEleColValList;
	}

	public List<DBOBean> getUnsavedUpdateAddOnList() {
		return unsavedUpdateAddOnList;
	}

	public void setUnsavedUpdateAddOnList(List<DBOBean> unsavedUpdateAddOnList) {
		this.unsavedUpdateAddOnList = unsavedUpdateAddOnList;
	}

	public List<DBOBean> getSavedUpdateAddOnList() {
		return savedUpdateAddOnList;
	}

	public void setSavedUpdateAddOnList(List<DBOBean> savedUpdateAddOnList) {
		this.savedUpdateAddOnList = savedUpdateAddOnList;
	}

	List<DBOBean> savedUpdateEleVms = new ArrayList<DBOBean>();
	List<DBOBean> unsavedUpdateEleInstrList = new ArrayList<DBOBean>();
	List<DBOBean> savedUpdateEleInstrList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedUpdateEleColValList = new ArrayList<DBOBean>();
	List<DBOBean> savedUpdateEleColValList = new ArrayList<DBOBean>();
	List<DBOBean> unsavedUpdateAddOnList = new ArrayList<DBOBean>();
	List<DBOBean> savedUpdateAddOnList = new ArrayList<DBOBean>();
	List<QuotationForm> ScopeList = new ArrayList<QuotationForm>();
	
	Map<Integer, String> msgToUser = new HashMap<Integer, String>();
	
	Map<String,List<TurbineDetails>> addOnMap = new HashMap<>();
	
	Map<Integer, String> FirmDetails = new HashMap<>();
	
	List<OtherCostsBean> projectCostList = new ArrayList<OtherCostsBean>();
	List<OtherCostsBean> variableCostList = new ArrayList<OtherCostsBean>();
	List<OtherCostsBean> sparesCostList = new ArrayList<OtherCostsBean>();
	
	LoginBO userDetails = new LoginBO();
	
	F2FCostBean f2fCostBean = new F2FCostBean();
	
	F2FForm latestCProjectData = new F2FForm();
	F2FForm f2fExcel = new F2FForm();
	F2FForm oneLineBom = new F2FForm();
	OneLineBomData oneLineBomExcel = new OneLineBomData();
	
	CustomerProfileForm customerProfileForm = new CustomerProfileForm();
	
	CustomerDetails customerDetailsForm = new CustomerDetails();
	
	DropDownColumnvalues dropDownColumnvalues = new DropDownColumnvalues();
	
	OtherCostsBean otherCostsBean = new OtherCostsBean();
	
	SaveBasicDetails saveBasicDetails = new SaveBasicDetails();
	
	ScopeOfSupply scopeOfSupply = new ScopeOfSupply();
	
	AddOnComponent addOnComponent = new AddOnComponent();
	
	ErectionCommissionBean erectionCommissionBean = new ErectionCommissionBean();
	
	TransportationDetailsBean packageBean = new TransportationDetailsBean();
	TransportationDetailsBean transBean = new TransportationDetailsBean();	
	
	private TurbineDetails UBOFrameList = new TurbineDetails();
	
	

	public List<QuestionsBean> getQuestionsBean() {
		return questionsBean;
	}

	public void setQuestionsBean(List<QuestionsBean> questionsBean) {
		this.questionsBean = questionsBean;
	}

	public List<QuotationHomeGrid> getQuotationHomeGrid() {
		return quotationHomeGrid;
	}

	public void setQuotationHomeGrid(List<QuotationHomeGrid> quotationHomeGrid) {
		this.quotationHomeGrid = quotationHomeGrid;
	}

	public DropDownColumnvalues getDropDownColumnvalues() {
		return dropDownColumnvalues;
	}

	public void setDropDownColumnvalues(DropDownColumnvalues dropDownColumnvalues) {
		this.dropDownColumnvalues = dropDownColumnvalues;
	}

	public SaveBasicDetails getSaveBasicDetails() {
		return saveBasicDetails;
	}

	public void setSaveBasicDetails(SaveBasicDetails saveBasicDetails) {
		this.saveBasicDetails = saveBasicDetails;
	}

	public Map<Integer, String> getMsgToUser() {
		return msgToUser;
	}

	public void setMsgToUser(Map<Integer, String> msgToUser) {
		this.msgToUser = msgToUser;
	}

	public List<SaveQuesDetails> getSaveQuesDetailsList() {
		return saveQuesDetailsList;
	}

	public void setSaveQuesDetailsList(List<SaveQuesDetails> saveQuesDetailsList) {
		this.saveQuesDetailsList = saveQuesDetailsList;
	}

	/**
	 * @return the f2fCostBean
	 */
	public F2FCostBean getF2fCostBean() {
		return f2fCostBean;
	}

	/**
	 * @param f2fCostBean
	 *            the f2fCostBean to set
	 */
	public void setF2fCostBean(F2FCostBean f2fCostBean) {
		this.f2fCostBean = f2fCostBean;
	}

	/**
	 * @return the f2fCostBeanList
	 */
	public List<F2FCostBean> getF2fCostBeanList() {
		return f2fCostBeanList;
	}

	/**
	 * @param f2fCostBeanList
	 *            the f2fCostBeanList to set
	 */
	public void setF2fCostBeanList(List<F2FCostBean> f2fCostBeanList) {
		this.f2fCostBeanList = f2fCostBeanList;
	}

	/**
	 * @return the cProjectList
	 */
	public List<CProjectList> getcProjectList() {
		return cProjectList;
	}

	/**
	 * @param cProjectList
	 *            the cProjectList to set
	 */
	public void setcProjectList(List<CProjectList> cProjectList) {
		this.cProjectList = cProjectList;
	}

	/**
	 * @return the f2FDataList
	 */
	public List<F2FForm> getF2FDataList() {
		return f2FDataList;
	}

	/**
	 * @param f2fDataList
	 *            the f2FDataList to set
	 */
	public void setF2FDataList(List<F2FForm> f2fDataList) {
		f2FDataList = f2fDataList;
	}

	/**
	 * @return the userDetailsList
	 */
	public List<UserProfileDetails> getUserDetailsList() {
		return userDetailsList;
	}

	/**
	 * @param userDetailsList
	 *            the userDetailsList to set
	 */
	public void setUserDetailsList(List<UserProfileDetails> userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

	/**
	 * @return the customerProfileForm
	 */
	public CustomerProfileForm getCustomerProfileForm() {
		return customerProfileForm;
	}

	/**
	 * @param customerProfileForm
	 *            the customerProfileForm to set
	 */
	public void setCustomerProfileForm(CustomerProfileForm customerProfileForm) {
		this.customerProfileForm = customerProfileForm;
	}

	/**
	 * @return the myQuotations
	 */
	public List<QuotationHomeGrid> getMyQuotations() {
		return myQuotations;
	}

	/**
	 * @param myQuotations
	 *            the myQuotations to set
	 */
	public void setMyQuotations(List<QuotationHomeGrid> myQuotations) {
		this.myQuotations = myQuotations;
	}

	/**
	 * @return the firmDetails
	 */
	public Map<Integer, String> getFirmDetails() {
		return FirmDetails;
	}

	/**
	 * @param firmDetails
	 *            the firmDetails to set
	 */
	public void setFirmDetails(Map<Integer, String> firmDetails) {
		FirmDetails = firmDetails;
	}

	/**
	 * @return the firmList
	 */
	public List<FirmDetails> getFirmList() {
		return firmList;
	}

	/**
	 * @param firmList
	 *            the firmList to set
	 */
	public void setFirmList(List<FirmDetails> firmList) {
		this.firmList = firmList;
	}

	/**
	 * @return the scopeOfSupplyList
	 */
	public List<ScopeOfSupply> getScopeOfSupplyList() {
		return scopeOfSupplyList;
	}

	/**
	 * @param scopeOfSupplyList
	 *            the scopeOfSupplyList to set
	 */
	public void setScopeOfSupplyList(List<ScopeOfSupply> scopeOfSupplyList) {
		this.scopeOfSupplyList = scopeOfSupplyList;
	}

	/**
	 * @return the userDetails
	 */
	public LoginBO getUserDetails() {
		return userDetails;
	}

	/**
	 * @param userDetails
	 *            the userDetails to set
	 */
	public void setUserDetails(LoginBO userDetails) {
		this.userDetails = userDetails;
	}

	/**
	 * @return the successCode
	 */
	public int getSuccessCode() {
		return successCode;
	}

	/**
	 * @param successCode
	 *            the successCode to set
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
	 * @param successMsg
	 *            the successMsg to set
	 */
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	/**
	 * @return the matchedCProjectList
	 */
	public List<CProjectList> getMatchedCProjectList() {
		return matchedCProjectList;
	}

	/**
	 * @param matchedCProjectList
	 *            the matchedCProjectList to set
	 */
	public void setMatchedCProjectList(List<CProjectList> matchedCProjectList) {
		this.matchedCProjectList = matchedCProjectList;
	}

	/**
	 * @return the f2FTreeRoot
	 */

	/**
	 * @return the loggedInUserId
	 */
	public int getLoggedInUserId() {
		return loggedInUserId;
	}

	/**
	 * @param loggedInUserId
	 *            the loggedInUserId to set
	 */
	public void setLoggedInUserId(int loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	/**
	 * @return the scopeOfSupply
	 */
	public ScopeOfSupply getScopeOfSupply() {
		return scopeOfSupply;
	}

	/**
	 * @param scopeOfSupply
	 *            the scopeOfSupply to set
	 */
	public void setScopeOfSupply(ScopeOfSupply scopeOfSupply) {
		this.scopeOfSupply = scopeOfSupply;
	}

	/**
	 * @return the tree
	 *//*
	public List<Map<String, List<Entry>>> getTree() {
		return tree;
	}

	*//**
	 * @param tree the tree to set
	 *//*
	public void setTree(List<Map<String, List<Entry>>> tree) {
		this.tree = tree;
	}*/

	
	
	
	/**
	 * @return the addOnMap
	 */
	public Map<String, List<TurbineDetails>> getAddOnMap() {
		return addOnMap;
	}

	/**
	 * @param addOnMap the addOnMap to set
	 */
	public void setAddOnMap(Map<String, List<TurbineDetails>> addOnMap) {
		this.addOnMap = addOnMap;
	}

	/**
	 * @return the addOnComponent
	 */
	public AddOnComponent getAddOnComponent() {
		return addOnComponent;
	}

	/**
	 * @param addOnComponent the addOnComponent to set
	 */
	public void setAddOnComponent(AddOnComponent addOnComponent) {
		this.addOnComponent = addOnComponent;
	}

	/**
	 * @return the submittedAddOnList
	 */
	public List<AddOnComponent> getSubmittedAddOnList() {
		return submittedAddOnList;
	}

	/**
	 * @param submittedAddOnList the submittedAddOnList to set
	 */
	public void setSubmittedAddOnList(List<AddOnComponent> submittedAddOnList) {
		this.submittedAddOnList = submittedAddOnList;
	}

	/**
	 * @return the tree
	 */
	/*public List<Map<String, List<TreeObject>>> getTree() {
		return tree;
	}

	*//**
	 * @param tree the tree to set
	 *//*
	public void setTree(List<Map<String, List<TreeObject>>> tree) {
		this.tree = tree;
	}*/

	
	public List<Map<String, List<Entry>>> getTree() {
		return tree;
	}

	
	public void setTree(List<Map<String, List<Entry>>> tree) {
		this.tree = tree;
	}

	/**
	 * @return the selectedQuestionAnswerSet
	 */
	public List<QuestionsEntity> getSelectedQuestionAnswerSet() {
		return SelectedQuestionAnswerSet;
	}

	/**
	 * @param selectedQuestionAnswerSet the selectedQuestionAnswerSet to set
	 */
	public void setSelectedQuestionAnswerSet(List<QuestionsEntity> selectedQuestionAnswerSet) {
		SelectedQuestionAnswerSet = selectedQuestionAnswerSet;
	}

	/**
	 * @return the oneLineBom
	 */
	public F2FForm getOneLineBom() {
		return oneLineBom;
	}

	/**
	 * @param oneLineBom the oneLineBom to set
	 */
	public void setOneLineBom(F2FForm oneLineBom) {
		this.oneLineBom = oneLineBom;
	}

	/**
	 * @return the addonDefaultList
	 */
	public List<AddOnComponent> getAddonDefaultList() {
		return addonDefaultList;
	}

	/**
	 * @param addonDefaultList the addonDefaultList to set
	 */
	public void setAddonDefaultList(List<AddOnComponent> addonDefaultList) {
		this.addonDefaultList = addonDefaultList;
	}

	/**
	 * @return the addOnComponentCostSheetList
	 */
	public List<AddOnComponent> getAddOnComponentCostSheetList() {
		return addOnComponentCostSheetList;
	}

	/**
	 * @param addOnComponentCostSheetList the addOnComponentCostSheetList to set
	 */
	public void setAddOnComponentCostSheetList(List<AddOnComponent> addOnComponentCostSheetList) {
		this.addOnComponentCostSheetList = addOnComponentCostSheetList;
	}

	/**
	 * @return the latestCProjectData
	 */
	public F2FForm getLatestCProjectData() {
		return latestCProjectData;
	}

	/**
	 * @param latestCProjectData the latestCProjectData to set
	 */
	public void setLatestCProjectData(F2FForm latestCProjectData) {
		this.latestCProjectData = latestCProjectData;
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
	 * @return the transportationDetailList
	 */
	public List<TransportationDetailsBean> getTransportationDetailList() {
		return transportationDetailList;
	}

	/**
	 * @param transportationDetailList the transportationDetailList to set
	 */
	public void setTransportationDetailList(List<TransportationDetailsBean> transportationDetailList) {
		this.transportationDetailList = transportationDetailList;
	}

	/**
	 * @return the erectionCommissionBean
	 */
	public ErectionCommissionBean getErectionCommissionBean() {
		return erectionCommissionBean;
	}

	/**
	 * @param erectionCommissionBean the erectionCommissionBean to set
	 */
	public void setErectionCommissionBean(ErectionCommissionBean erectionCommissionBean) {
		this.erectionCommissionBean = erectionCommissionBean;
	}

	/**
	 * @return the updatePriceTransportList
	 */
	public List<TransportationDetailsBean> getUpdatePriceTransportList() {
		return UpdatePriceTransportList;
	}

	/**
	 * @param updatePriceTransportList the updatePriceTransportList to set
	 */
	public void setUpdatePriceTransportList(List<TransportationDetailsBean> updatePriceTransportList) {
		UpdatePriceTransportList = updatePriceTransportList;
	}

	/**
	 * @return the updatePriceMyRequestGrid
	 */
	public List<SaveBasicDetails> getUpdatePriceMyRequestGrid() {
		return updatePriceMyRequestGrid;
	}

	/**
	 * @param updatePriceMyRequestGrid the updatePriceMyRequestGrid to set
	 */
	public void setUpdatePriceMyRequestGrid(List<SaveBasicDetails> updatePriceMyRequestGrid) {
		this.updatePriceMyRequestGrid = updatePriceMyRequestGrid;
	}

	/**
	 * @return the updatePriceOthersRequestGrid
	 */
	public List<SaveBasicDetails> getUpdatePriceOthersRequestGrid() {
		return updatePriceOthersRequestGrid;
	}

	/**
	 * @param updatePriceOthersRequestGrid the updatePriceOthersRequestGrid to set
	 */
	public void setUpdatePriceOthersRequestGrid(List<SaveBasicDetails> updatePriceOthersRequestGrid) {
		this.updatePriceOthersRequestGrid = updatePriceOthersRequestGrid;
	}

	/**
	 * @return the commentList
	 */
	public List<CommentBean> getCommentList() {
		return commentList;
	}

	/**
	 * @param commentList the commentList to set
	 */
	public void setCommentList(List<CommentBean> commentList) {
		this.commentList = commentList;
	}

	/**
	 * @return the errectionCommList
	 */
	public List<ErectionCommissionBean> getErrectionCommList() {
		return errectionCommList;
	}

	/**
	 * @param errectionCommList the errectionCommList to set
	 */
	public void setErrectionCommList(List<ErectionCommissionBean> errectionCommList) {
		this.errectionCommList = errectionCommList;
	}

	/**
	 * @return the packageDetailList
	 */
	public List<TransportationDetailsBean> getPackageDetailList() {
		return packageDetailList;
	}

	/**
	 * @param packageDetailList the packageDetailList to set
	 */
	public void setPackageDetailList(List<TransportationDetailsBean> packageDetailList) {
		this.packageDetailList = packageDetailList;
	}

	/**
	 * @return the packageBean
	 */
	public TransportationDetailsBean getPackageBean() {
		return packageBean;
	}

	/**
	 * @param packageBean the packageBean to set
	 */
	public void setPackageBean(TransportationDetailsBean packageBean) {
		this.packageBean = packageBean;
	}

	/**
	 * @return the savedUpdatePriceTransportList
	 */
	public List<TransportationDetailsBean> getSavedUpdatePriceTransportList() {
		return savedUpdatePriceTransportList;
	}

	/**
	 * @param savedUpdatePriceTransportList the savedUpdatePriceTransportList to set
	 */
	public void setSavedUpdatePriceTransportList(List<TransportationDetailsBean> savedUpdatePriceTransportList) {
		this.savedUpdatePriceTransportList = savedUpdatePriceTransportList;
	}

	/**
	 * @return the unsavedUpdatePriceTransportList
	 */
	public List<TransportationDetailsBean> getUnsavedUpdatePriceTransportList() {
		return unsavedUpdatePriceTransportList;
	}

	/**
	 * @param unsavedUpdatePriceTransportList the unsavedUpdatePriceTransportList to set
	 */
	public void setUnsavedUpdatePriceTransportList(List<TransportationDetailsBean> unsavedUpdatePriceTransportList) {
		this.unsavedUpdatePriceTransportList = unsavedUpdatePriceTransportList;
	}

	/**
	 * @return the savedUpdateECPriceList
	 */
	public List<ErectionCommissionBean> getSavedUpdateECPriceList() {
		return savedUpdateECPriceList;
	}

	/**
	 * @param savedUpdateECPriceList the savedUpdateECPriceList to set
	 */
	public void setSavedUpdateECPriceList(List<ErectionCommissionBean> savedUpdateECPriceList) {
		this.savedUpdateECPriceList = savedUpdateECPriceList;
	}

	/**
	 * @return the unsavedUpdateECPriceList
	 */
	public List<ErectionCommissionBean> getUnsavedUpdateECPriceList() {
		return unsavedUpdateECPriceList;
	}

	/**
	 * @param unsavedUpdateECPriceList the unsavedUpdateECPriceList to set
	 */
	public void setUnsavedUpdateECPriceList(List<ErectionCommissionBean> unsavedUpdateECPriceList) {
		this.unsavedUpdateECPriceList = unsavedUpdateECPriceList;
	}

	/**
	 * @return the packageupdatedPriceList
	 */
	public List<PackageBean> getPackageupdatedPriceList() {
		return packageupdatedPriceList;
	}

	/**
	 * @param packageupdatedPriceList the packageupdatedPriceList to set
	 */
	public void setPackageupdatedPriceList(List<PackageBean> packageupdatedPriceList) {
		this.packageupdatedPriceList = packageupdatedPriceList;
	}

	/**
	 * @return the savedUpdatePKGPriceList
	 */
	public List<PackageBean> getSavedUpdatePKGPriceList() {
		return savedUpdatePKGPriceList;
	}

	/**
	 * @param savedUpdatePKGPriceList the savedUpdatePKGPriceList to set
	 */
	public void setSavedUpdatePKGPriceList(List<PackageBean> savedUpdatePKGPriceList) {
		this.savedUpdatePKGPriceList = savedUpdatePKGPriceList;
	}

	/**
	 * @return the unsavedUpdatePKGPriceList
	 */
	public List<PackageBean> getUnsavedUpdatePKGPriceList() {
		return unsavedUpdatePKGPriceList;
	}

	/**
	 * @param unsavedUpdatePKGPriceList the unsavedUpdatePKGPriceList to set
	 */
	public void setUnsavedUpdatePKGPriceList(List<PackageBean> unsavedUpdatePKGPriceList) {
		this.unsavedUpdatePKGPriceList = unsavedUpdatePKGPriceList;
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
	 * @return the selectedScopeList
	 */
	public List<TurbineDetails> getSelectedScopeList() {
		return selectedScopeList;
	}

	/**
	 * @param selectedScopeList the selectedScopeList to set
	 */
	public void setSelectedScopeList(List<TurbineDetails> selectedScopeList) {
		this.selectedScopeList = selectedScopeList;
	}

	/**
	 * @return the costSheetList
	 */
	public List<TurbineDetails> getCostSheetList() {
		return costSheetList;
	}

	/**
	 * @param costSheetList the costSheetList to set
	 */
	public void setCostSheetList(List<TurbineDetails> costSheetList) {
		this.costSheetList = costSheetList;
	}

	/**
	 * @return the f2FUBOList
	 */
	public List<F2FUBOBean> getF2FUBOList() {
		return f2FUBOList;
	}

	/**
	 * @param f2fuboList the f2FUBOList to set
	 */
	public void setF2FUBOList(List<F2FUBOBean> f2fuboList) {
		f2FUBOList = f2fuboList;
	}

	/**
	 * @return the uBOFrameList
	 */
	public TurbineDetails getUBOFrameList() {
		return UBOFrameList;
	}

	/**
	 * @param uBOFrameList the uBOFrameList to set
	 */
	public void setUBOFrameList(TurbineDetails uBOFrameList) {
		UBOFrameList = uBOFrameList;
	}

	

	/**
	 * @return the packageDetailsWithPriceList
	 */
	public List<PackageBean> getPackageDetailsWithPriceList() {
		return packageDetailsWithPriceList;
	}

	/**
	 * @param packageDetailsWithPriceList the packageDetailsWithPriceList to set
	 */
	public void setPackageDetailsWithPriceList(List<PackageBean> packageDetailsWithPriceList) {
		this.packageDetailsWithPriceList = packageDetailsWithPriceList;
	}

	/**
	 * @return the packageDetailsListData
	 */
	public List<TransportationDetailsBean> getPackageDetailsListData() {
		return packageDetailsListData;
	}

	/**
	 * @param packageDetailsListData the packageDetailsListData to set
	 */
	public void setPackageDetailsListData(List<TransportationDetailsBean> packageDetailsListData) {
		this.packageDetailsListData = packageDetailsListData;
	}

	/**
	 * @return the submittedAddOnListCost
	 */
	public List<AddOnComponent> getSubmittedAddOnListCost() {
		return submittedAddOnListCost;
	}

	/**
	 * @param submittedAddOnListCost the submittedAddOnListCost to set
	 */
	public void setSubmittedAddOnListCost(List<AddOnComponent> submittedAddOnListCost) {
		this.submittedAddOnListCost = submittedAddOnListCost;
	}

	/**
	 * @return the quotDboMechList
	 */
	public List<DBOBean> getQuotDboMechList() {
		return quotDboMechList;
	}

	/**
	 * @param quotDboMechList the quotDboMechList to set
	 */
	public void setQuotDboMechList(List<DBOBean> quotDboMechList) {
		this.quotDboMechList = quotDboMechList;
	}

	/**
	 * @return the quotDboElectricalList
	 */
	public List<DBOBean> getQuotDboElectricalList() {
		return quotDboElectricalList;
	}

	/**
	 * @param quotDboElectricalList the quotDboElectricalList to set
	 */
	public void setQuotDboElectricalList(List<DBOBean> quotDboElectricalList) {
		this.quotDboElectricalList = quotDboElectricalList;
	}
/**
	 * @return the f2FUBOsetterList
	 */
	public List<F2FUBOBean> getF2FUBOsetterList() {
		return f2FUBOsetterList;
	}

	/**
	 * @param f2fubOsetterList the f2FUBOsetterList to set
	 */
	public void setF2FUBOsetterList(List<F2FUBOBean> f2fubOsetterList) {
		f2FUBOsetterList = f2fubOsetterList;
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
	 * @return the f2FUboSavedList
	 */
	public List<F2FUBOBean> getF2FUboSavedList() {
		return f2FUboSavedList;
	}

	/**
	 * @param f2fUboSavedList the f2FUboSavedList to set
	 */
	public void setF2FUboSavedList(List<F2FUBOBean> f2fUboSavedList) {
		f2FUboSavedList = f2fUboSavedList;
	}

	/**
	 * @return the f2FUboUnsavedList
	 */
	public List<F2FUBOBean> getF2FUboUnsavedList() {
		return f2FUboUnsavedList;
	}

	/**
	 * @param f2fUboUnsavedList the f2FUboUnsavedList to set
	 */
	public void setF2FUboUnsavedList(List<F2FUBOBean> f2fUboUnsavedList) {
		f2FUboUnsavedList = f2fUboUnsavedList;
	}

	/**
	 * @return the overHeadSheetList
	 */
	public List<TurbineDetails> getOverHeadSheetList() {
		return overHeadSheetList;
	}

	/**
	 * @param overHeadSheetList the overHeadSheetList to set
	 */
	public void setOverHeadSheetList(List<TurbineDetails> overHeadSheetList) {
		this.overHeadSheetList = overHeadSheetList;
	}

	/**
	 * @return the turbineInstrumentList
	 */
	public List<TurbineDetails> getTurbineInstrumentList() {
		return turbineInstrumentList;
	}

	/**
	 * @param turbineInstrumentList the turbineInstrumentList to set
	 */
	public void setTurbineInstrumentList(List<TurbineDetails> turbineInstrumentList) {
		this.turbineInstrumentList = turbineInstrumentList;
	}

	/**
	 * @return the addOnComponentsList
	 */
	public List<AddOnComponent> getAddOnComponentsList() {
		return addOnComponentsList;
	}

	/**
	 * @param addOnComponentsList the addOnComponentsList to set
	 */
	public void setAddOnComponentsList(List<AddOnComponent> addOnComponentsList) {
		this.addOnComponentsList = addOnComponentsList;
	}

	/**
	 * @return the transBean
	 */
	public TransportationDetailsBean getTransBean() {
		return transBean;
	}

	/**
	 * @param transBean the transBean to set
	 */
	public void setTransBean(TransportationDetailsBean transBean) {
		this.transBean = transBean;
	}

	/**
	 * @return the savedCostList
	 */
	public List<TurbineDetails> getSavedCostList() {
		return savedCostList;
	}

	/**
	 * @param savedCostList the savedCostList to set
	 */
	public void setSavedCostList(List<TurbineDetails> savedCostList) {
		this.savedCostList = savedCostList;
	}

	/**
	 * @return the unSavedCostList
	 */
	public List<TurbineDetails> getUnSavedCostList() {
		return unSavedCostList;
	}

	/**
	 * @param unSavedCostList the unSavedCostList to set
	 */
	public void setUnSavedCostList(List<TurbineDetails> unSavedCostList) {
		this.unSavedCostList = unSavedCostList;
	}

	/**
	 * @return the customerDetailsForm
	 */
	public CustomerDetails getCustomerDetailsForm() {
		return customerDetailsForm;
	}

	/**
	 * @param customerDetailsForm the customerDetailsForm to set
	 */
	public void setCustomerDetailsForm(CustomerDetails customerDetailsForm) {
		this.customerDetailsForm = customerDetailsForm;
	}

	/**
	 * @return the otherCostsBean
	 */
	public OtherCostsBean getOtherCostsBean() {
		return otherCostsBean;
	}

	/**
	 * @param otherCostsBean the otherCostsBean to set
	 */
	public void setOtherCostsBean(OtherCostsBean otherCostsBean) {
		this.otherCostsBean = otherCostsBean;
	}

	/**
	 * @return the projectCostList
	 */
	public List<OtherCostsBean> getProjectCostList() {
		return projectCostList;
	}

	/**
	 * @param projectCostList the projectCostList to set
	 */
	public void setProjectCostList(List<OtherCostsBean> projectCostList) {
		this.projectCostList = projectCostList;
	}

	/**
	 * @return the variableCostList
	 */
	public List<OtherCostsBean> getVariableCostList() {
		return variableCostList;
	}

	/**
	 * @param variableCostList the variableCostList to set
	 */
	public void setVariableCostList(List<OtherCostsBean> variableCostList) {
		this.variableCostList = variableCostList;
	}

	/**
	 * @return the sparesCostList
	 */
	public List<OtherCostsBean> getSparesCostList() {
		return sparesCostList;
	}

	/**
	 * @param sparesCostList the sparesCostList to set
	 */
	public void setSparesCostList(List<OtherCostsBean> sparesCostList) {
		this.sparesCostList = sparesCostList;
	}

	/**
	 * @return the f2fExcel
	 */
	public F2FForm getF2fExcel() {
		return f2fExcel;
	}

	/**
	 * @param f2fExcel the f2fExcel to set
	 */
	public void setF2fExcel(F2FForm f2fExcel) {
		this.f2fExcel = f2fExcel;
	}

	/**
	 * @return the quotDboMechOthersList
	 */
	public List<DBOBean> getQuotDboMechOthersList() {
		return quotDboMechOthersList;
	}

	/**
	 * @param quotDboMechOthersList the quotDboMechOthersList to set
	 */
	public void setQuotDboMechOthersList(List<DBOBean> quotDboMechOthersList) {
		this.quotDboMechOthersList = quotDboMechOthersList;
	}

	/**
	 * @return the quotDboEleOthersList
	 */
	public List<DBOBean> getQuotDboEleOthersList() {
		return quotDboEleOthersList;
	}

	/**
	 * @param quotDboEleOthersList the quotDboEleOthersList to set
	 */
	public void setQuotDboEleOthersList(List<DBOBean> quotDboEleOthersList) {
		this.quotDboEleOthersList = quotDboEleOthersList;
	}

	/**
	 * @return the addInstrList
	 */
	public List<DBOBean> getAddInstrList() {
		return AddInstrList;
	}

	/**
	 * @param addInstrList the addInstrList to set
	 */
	public void setAddInstrList(List<DBOBean> addInstrList) {
		AddInstrList = addInstrList;
	}

	/**
	 * @return the editQuotFlow
	 */
	public boolean isEditQuotFlow() {
		return editQuotFlow;
	}

	/**
	 * @param editQuotFlow the editQuotFlow to set
	 */
	public void setEditQuotFlow(boolean editQuotFlow) {
		this.editQuotFlow = editQuotFlow;
	}

	/**
	 * @return the scopeOfSupplyStatusList
	 */
	public List<ScopeOfSupply> getScopeOfSupplyStatusList() {
		return scopeOfSupplyStatusList;
	}

	/**
	 * @param scopeOfSupplyStatusList the scopeOfSupplyStatusList to set
	 */
	public void setScopeOfSupplyStatusList(List<ScopeOfSupply> scopeOfSupplyStatusList) {
		this.scopeOfSupplyStatusList = scopeOfSupplyStatusList;
	}

	/**
	 * @return the quotDboEleSplAddOnList
	 */
	public List<DBOBean> getQuotDboEleSplAddOnList() {
		return quotDboEleSplAddOnList;
	}

	/**
	 * @param quotDboEleSplAddOnList the quotDboEleSplAddOnList to set
	 */
	public void setQuotDboEleSplAddOnList(List<DBOBean> quotDboEleSplAddOnList) {
		this.quotDboEleSplAddOnList = quotDboEleSplAddOnList;
	}

	/**
	 * @return the dboEleAddOnList
	 */
	public List<DBOBean> getDboEleAddOnList() {
		return dboEleAddOnList;
	}

	/**
	 * @param dboEleAddOnList the dboEleAddOnList to set
	 */
	public void setDboEleAddOnList(List<DBOBean> dboEleAddOnList) {
		this.dboEleAddOnList = dboEleAddOnList;
	}

	/**
	 * @return the oneLineBomExcel
	 */
	public OneLineBomData getOneLineBomExcel() {
		return oneLineBomExcel;
	}

	/**
	 * @param oneLineBomExcel the oneLineBomExcel to set
	 */
	public void setOneLineBomExcel(OneLineBomData oneLineBomExcel) {
		this.oneLineBomExcel = oneLineBomExcel;
	}

	/**
	 * @return the savedDboMechList
	 */
	public List<DBOBean> getSavedDboMechList() {
		return savedDboMechList;
	}

	/**
	 * @param savedDboMechList the savedDboMechList to set
	 */
	public void setSavedDboMechList(List<DBOBean> savedDboMechList) {
		this.savedDboMechList = savedDboMechList;
	}

	/**
	 * @return the unsavedDboMechList
	 */
	public List<DBOBean> getUnsavedDboMechList() {
		return unsavedDboMechList;
	}

	/**
	 * @param unsavedDboMechList the unsavedDboMechList to set
	 */
	public void setUnsavedDboMechList(List<DBOBean> unsavedDboMechList) {
		this.unsavedDboMechList = unsavedDboMechList;
	}

	/**
	 * @return the savedDboEleList
	 */
	
	/**
	 * @return the savedDboMechColList
	 */
	public List<DBOBean> getSavedDboMechColList() {
		return savedDboMechColList;
	}

	/**
	 * @param savedDboMechColList the savedDboMechColList to set
	 */
	public void setSavedDboMechColList(List<DBOBean> savedDboMechColList) {
		this.savedDboMechColList = savedDboMechColList;
	}

	/**
	 * @return the unsavedDboMechColList
	 */
	public List<DBOBean> getUnsavedDboMechColList() {
		return unsavedDboMechColList;
	}

	/**
	 * @param unsavedDboMechColList the unsavedDboMechColList to set
	 */
	public void setUnsavedDboMechColList(List<DBOBean> unsavedDboMechColList) {
		this.unsavedDboMechColList = unsavedDboMechColList;
	}

	/**
	 * @return the savedDboEleColList
	 */
	public List<DBOBean> getSavedDboEleColList() {
		return savedDboEleColList;
	}

	/**
	 * @param savedDboEleColList the savedDboEleColList to set
	 */
	public void setSavedDboEleColList(List<DBOBean> savedDboEleColList) {
		this.savedDboEleColList = savedDboEleColList;
	}

	/**
	 * @return the unsavedDboEleColList
	 */
	public List<DBOBean> getUnsavedDboEleColList() {
		return unsavedDboEleColList;
	}

	/**
	 * @param unsavedDboEleColList the unsavedDboEleColList to set
	 */
	public void setUnsavedDboEleColList(List<DBOBean> unsavedDboEleColList) {
		this.unsavedDboEleColList = unsavedDboEleColList;
	}

	/**
	 * @return the savedDboList
	 */
	public List<DBOBean> getSavedDboList() {
		return savedDboList;
	}

	/**
	 * @param savedDboList the savedDboList to set
	 */
	public void setSavedDboList(List<DBOBean> savedDboList) {
		this.savedDboList = savedDboList;
	}

	/**
	 * @return the unsavedDboList
	 */
	public List<DBOBean> getUnsavedDboList() {
		return unsavedDboList;
	}

	/**
	 * @param unsavedDboList the unsavedDboList to set
	 */
	public void setUnsavedDboList(List<DBOBean> unsavedDboList) {
		this.unsavedDboList = unsavedDboList;
	}

	/**
	 * @return the savedDboEleAddOnList
	 */
	public List<DBOBean> getSavedDboEleAddOnList() {
		return savedDboEleAddOnList;
	}

	/**
	 * @param savedDboEleAddOnList the savedDboEleAddOnList to set
	 */
	public void setSavedDboEleAddOnList(List<DBOBean> savedDboEleAddOnList) {
		this.savedDboEleAddOnList = savedDboEleAddOnList;
	}

	/**
	 * @return the unsavedDboEleAddOnList
	 */
	public List<DBOBean> getUnsavedDboEleAddOnList() {
		return unsavedDboEleAddOnList;
	}

	/**
	 * @param unsavedDboEleAddOnList the unsavedDboEleAddOnList to set
	 */
	public void setUnsavedDboEleAddOnList(List<DBOBean> unsavedDboEleAddOnList) {
		this.unsavedDboEleAddOnList = unsavedDboEleAddOnList;
	}

	/**
	 * @return the savedDboEleAddInstrList
	 */
	public List<DBOBean> getSavedDboEleAddInstrList() {
		return savedDboEleAddInstrList;
	}

	/**
	 * @param savedDboEleAddInstrList the savedDboEleAddInstrList to set
	 */
	public void setSavedDboEleAddInstrList(List<DBOBean> savedDboEleAddInstrList) {
		this.savedDboEleAddInstrList = savedDboEleAddInstrList;
	}

	/**
	 * @return the unsavedDboEleAddInstrList
	 */
	public List<DBOBean> getUnsavedDboEleAddInstrList() {
		return unsavedDboEleAddInstrList;
	}

	/**
	 * @param unsavedDboEleAddInstrList the unsavedDboEleAddInstrList to set
	 */
	public void setUnsavedDboEleAddInstrList(List<DBOBean> unsavedDboEleAddInstrList) {
		this.unsavedDboEleAddInstrList = unsavedDboEleAddInstrList;
	}

	/**
	 * @return the savedDboEleSplAddOnList
	 */
	public List<DBOBean> getSavedDboEleSplAddOnList() {
		return savedDboEleSplAddOnList;
	}

	/**
	 * @param savedDboEleSplAddOnList the savedDboEleSplAddOnList to set
	 */
	public void setSavedDboEleSplAddOnList(List<DBOBean> savedDboEleSplAddOnList) {
		this.savedDboEleSplAddOnList = savedDboEleSplAddOnList;
	}

	/**
	 * @return the unsavedDboEleSplAddOnList
	 */
	public List<DBOBean> getUnsavedDboEleSplAddOnList() {
		return unsavedDboEleSplAddOnList;
	}

	/**
	 * @param unsavedDboEleSplAddOnList the unsavedDboEleSplAddOnList to set
	 */
	public void setUnsavedDboEleSplAddOnList(List<DBOBean> unsavedDboEleSplAddOnList) {
		this.unsavedDboEleSplAddOnList = unsavedDboEleSplAddOnList;
	}

	/**
	 * @return the savedDboEleList
	 */
	public List<DBOBean> getSavedDboEleList() {
		return savedDboEleList;
	}

	/**
	 * @param savedDboEleList the savedDboEleList to set
	 */
	public void setSavedDboEleList(List<DBOBean> savedDboEleList) {
		this.savedDboEleList = savedDboEleList;
	}

	/**
	 * @return the unsavedDboEleList
	 */
	public List<DBOBean> getUnsavedDboEleList() {
		return unsavedDboEleList;
	}

	/**
	 * @param unsavedDboEleList the unsavedDboEleList to set
	 */
	public void setUnsavedDboEleList(List<DBOBean> unsavedDboEleList) {
		this.unsavedDboEleList = unsavedDboEleList;
	}

	/**
	 * @return the createUpdateAddonList
	 */
	public List<AddOnComponent> getCreateUpdateAddonList() {
		return createUpdateAddonList;
	}

	/**
	 * @param createUpdateAddonList the createUpdateAddonList to set
	 */
	public void setCreateUpdateAddonList(List<AddOnComponent> createUpdateAddonList) {
		this.createUpdateAddonList = createUpdateAddonList;
	}

	/**
	 * @return the savedAddonList
	 */
	public List<AddOnComponent> getSavedAddonList() {
		return savedAddonList;
	}

	/**
	 * @param savedAddonList the savedAddonList to set
	 */
	public void setSavedAddonList(List<AddOnComponent> savedAddonList) {
		this.savedAddonList = savedAddonList;
	}

	/**
	 * @return the unsavedAddonList
	 */
	public List<AddOnComponent> getUnsavedAddonList() {
		return unsavedAddonList;
	}

	/**
	 * @param unsavedAddonList the unsavedAddonList to set
	 */
	public void setUnsavedAddonList(List<AddOnComponent> unsavedAddonList) {
		this.unsavedAddonList = unsavedAddonList;
	}

	/**
	 * @return the savedUpdatePriceTransportListDomestic
	 */
	public List<TransportationDetailsBean> getSavedUpdatePriceTransportListDomestic() {
		return savedUpdatePriceTransportListDomestic;
	}

	/**
	 * @param savedUpdatePriceTransportListDomestic the savedUpdatePriceTransportListDomestic to set
	 */
	public void setSavedUpdatePriceTransportListDomestic(List<TransportationDetailsBean> savedUpdatePriceTransportListDomestic) {
		this.savedUpdatePriceTransportListDomestic = savedUpdatePriceTransportListDomestic;
	}

	/**
	 * @return the unsavedUpdatePriceTransportListDomestic
	 */
	public List<TransportationDetailsBean> getUnsavedUpdatePriceTransportListDomestic() {
		return unsavedUpdatePriceTransportListDomestic;
	}

	/**
	 * @param unsavedUpdatePriceTransportListDomestic the unsavedUpdatePriceTransportListDomestic to set
	 */
	public void setUnsavedUpdatePriceTransportListDomestic(List<TransportationDetailsBean> unsavedUpdatePriceTransportListDomestic) {
		this.unsavedUpdatePriceTransportListDomestic = unsavedUpdatePriceTransportListDomestic;
	}

	/**
	 * @return the savedUpdatePriceTransportListExport
	 */
	public List<TransportationDetailsBean> getSavedUpdatePriceTransportListExport() {
		return savedUpdatePriceTransportListExport;
	}

	/**
	 * @param savedUpdatePriceTransportListExport the savedUpdatePriceTransportListExport to set
	 */
	public void setSavedUpdatePriceTransportListExport(List<TransportationDetailsBean> savedUpdatePriceTransportListExport) {
		this.savedUpdatePriceTransportListExport = savedUpdatePriceTransportListExport;
	}

	/**
	 * @return the unsavedUpdatePriceTransportListExport
	 */
	public List<TransportationDetailsBean> getUnsavedUpdatePriceTransportListExport() {
		return unsavedUpdatePriceTransportListExport;
	}

	/**
	 * @param unsavedUpdatePriceTransportListExport the unsavedUpdatePriceTransportListExport to set
	 */
	public void setUnsavedUpdatePriceTransportListExport(List<TransportationDetailsBean> unsavedUpdatePriceTransportListExport) {
		this.unsavedUpdatePriceTransportListExport = unsavedUpdatePriceTransportListExport;
	}

	/**
	 * @return the quotAddOnOthersList
	 */
	public List<AddOnComponent> getQuotAddOnOthersList() {
		return quotAddOnOthersList;
	}

	/**
	 * @param quotAddOnOthersList the quotAddOnOthersList to set
	 */
	public void setQuotAddOnOthersList(List<AddOnComponent> quotAddOnOthersList) {
		this.quotAddOnOthersList = quotAddOnOthersList;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return Date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		Date = date;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return Subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		Subject = subject;
	}

	/**
	 * @return the fixedText1
	 */
	public String getFixedText1() {
		return fixedText1;
	}

	/**
	 * @param fixedText1 the fixedText1 to set
	 */
	public void setFixedText1(String fixedText1) {
		this.fixedText1 = fixedText1;
	}

	/**
	 * @return the fixedText2
	 */
	public String getFixedText2() {
		return fixedText2;
	}

	/**
	 * @param fixedText2 the fixedText2 to set
	 */
	public void setFixedText2(String fixedText2) {
		this.fixedText2 = fixedText2;
	}

	/**
	 * @return the fixedText3
	 */
	public String getFixedText3() {
		return fixedText3;
	}

	/**
	 * @param fixedText3 the fixedText3 to set
	 */
	public void setFixedText3(String fixedText3) {
		this.fixedText3 = fixedText3;
	}

	/**
	 * @return the fixedText4
	 */
	public String getFixedText4() {
		return fixedText4;
	}

	/**
	 * @param fixedText4 the fixedText4 to set
	 */
	public void setFixedText4(String fixedText4) {
		this.fixedText4 = fixedText4;
	}

	/**
	 * @return the subCategoryName
	 */
	public String getSubCategoryName() {
		return subCategoryName;
	}

	/**
	 * @param subCategoryName the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the f2fList
	 */
	public List<DBOBean> getF2fList() {
		return f2fList;
	}

	/**
	 * @param f2fList the f2fList to set
	 */
	public void setF2fList(List<DBOBean> f2fList) {
		this.f2fList = f2fList;
	}

	/**
	 * @return the savedList
	 */
	public List<DBOBean> getSavedList() {
		return savedList;
	}

	/**
	 * @param savedList the savedList to set
	 */
	public void setSavedList(List<DBOBean> savedList) {
		this.savedList = savedList;
	}

	/**
	 * @return the f2fGeneralList
	 */
	public List<DBOBean> getF2fGeneralList() {
		return f2fGeneralList;
	}

	/**
	 * @param f2fGeneralList the f2fGeneralList to set
	 */
	public void setF2fGeneralList(List<DBOBean> f2fGeneralList) {
		this.f2fGeneralList = f2fGeneralList;
	}

	

	/**
	 * @return the f3fAddOnList
	 */
	public List<DBOBean> getF2fAddOnList() {
		return f2fAddOnList;
	}

	/**
	 * @param f3fAddOnList the f3fAddOnList to set
	 */
	public void setF2fAddOnList(List<DBOBean> f3fAddOnList) {
		this.f2fAddOnList = f2fAddOnList;
	}

	/**
	 * @return the f2fOthersItemList
	 */
	public List<DBOBean> getF2fOthersItemList() {
		return f2fOthersItemList;
	}

	/**
	 * @param f2fOthersItemList the f2fOthersItemList to set
	 */
	public void setF2fOthersItemList(List<DBOBean> f2fOthersItemList) {
		this.f2fOthersItemList = f2fOthersItemList;
	}

	/**
	 * @return the f2fOthersSubItemList
	 */
	public List<DBOBean> getF2fOthersSubItemList() {
		return f2fOthersSubItemList;
	}

	/**
	 * @param f2fOthersSubItemList the f2fOthersSubItemList to set
	 */
	public void setF2fOthersSubItemList(List<DBOBean> f2fOthersSubItemList) {
		this.f2fOthersSubItemList = f2fOthersSubItemList;
	}

	/**
	 * @return the f2fOthersSubItemTypeList
	 */
	public List<DBOBean> getF2fOthersSubItemTypeList() {
		return f2fOthersSubItemTypeList;
	}

	/**
	 * @param f2fOthersSubItemTypeList the f2fOthersSubItemTypeList to set
	 */
	public void setF2fOthersSubItemTypeList(List<DBOBean> f2fOthersSubItemTypeList) {
		this.f2fOthersSubItemTypeList = f2fOthersSubItemTypeList;
	}

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
	 * @return the modfiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modfiedBy the modfiedBy to set
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
	 * @return the scopeList
	 */
	public List<QuotationForm> getScopeList() {
		return ScopeList;
	}

	/**
	 * @param scopeList the scopeList to set
	 */
	public void setScopeList(List<QuotationForm> scopeList) {
		ScopeList = scopeList;
	}

	/**
	 * @return the f2fAddOnList1
	 */
	public List<DBOBean> getF2fAddOnList1() {
		return f2fAddOnList1;
	}

	/**
	 * @param f2fAddOnList1 the f2fAddOnList1 to set
	 */
	public void setF2fAddOnList1(List<DBOBean> f2fAddOnList1) {
		this.f2fAddOnList1 = f2fAddOnList1;
	}

	/**
	 * @return the mechGeneralList
	 */
	public List<DBOBean> getMechGeneralList() {
		return mechGeneralList;
	}

	/**
	 * @param mechGeneralList the mechGeneralList to set
	 */
	public void setMechGeneralList(List<DBOBean> mechGeneralList) {
		this.mechGeneralList = mechGeneralList;
	}

	/**
	 * @return the mechAddOnList1
	 */
	public List<DBOBean> getMechAddOnList1() {
		return mechAddOnList1;
	}

	/**
	 * @param mechAddOnList1 the mechAddOnList1 to set
	 */
	public void setMechAddOnList1(List<DBOBean> mechAddOnList1) {
		this.mechAddOnList1 = mechAddOnList1;
	}

	/**
	 * @return the mechAuxGeneralList
	 */
	public List<DBOBean> getMechAuxGeneralList() {
		return mechAuxGeneralList;
	}

	/**
	 * @param mechAuxGeneralList the mechAuxGeneralList to set
	 */
	public void setMechAuxGeneralList(List<DBOBean> mechAuxGeneralList) {
		this.mechAuxGeneralList = mechAuxGeneralList;
	}

	/**
	 * @return the mechExtScpList
	 */
	public List<DBOBean> getMechExtScpList() {
		return mechExtScpList;
	}

	/**
	 * @param mechExtScpList the mechExtScpList to set
	 */
	public void setMechExtScpList(List<DBOBean> mechExtScpList) {
		this.mechExtScpList = mechExtScpList;
	}

	/**
	 * @return the ecType
	 */
	public String getEcType() {
		return ecType;
	}

	/**
	 * @param ecType the ecType to set
	 */
	public void setEcType(String ecType) {
		this.ecType = ecType;
	}

	/**
	 * @return the varCostData
	 */
	public List<DBOBean> getVarCostData() {
		return varCostData;
	}

	/**
	 * @param varCostData the varCostData to set
	 */
	public void setVarCostData(List<DBOBean> varCostData) {
		this.varCostData = varCostData;
	}

	/**
	 * @return the sparesCostData
	 */
	public List<DBOBean> getSparesCostData() {
		return sparesCostData;
	}

	/**
	 * @param sparesCostData the sparesCostData to set
	 */
	public void setSparesCostData(List<DBOBean> sparesCostData) {
		this.sparesCostData = sparesCostData;
	}

	/**
	 * @return the otherCostData
	 */
	public List<DBOBean> getOtherCostData() {
		return otherCostData;
	}

	/**
	 * @param otherCostData the otherCostData to set
	 */
	public void setOtherCostData(List<DBOBean> otherCostData) {
		this.otherCostData = otherCostData;
	}

	/**
	 * @return the varQuestionsBean
	 */
	public List<DBOBean> getVarQuestionsBean() {
		return varQuestionsBean;
	}

	/**
	 * @param varQuestionsBean the varQuestionsBean to set
	 */
	public void setVarQuestionsBean(List<DBOBean> varQuestionsBean) {
		this.varQuestionsBean = varQuestionsBean;
	}

	/**
	 * @return the sparesQuestionsBean
	 */
	public List<DBOBean> getSparesQuestionsBean() {
		return sparesQuestionsBean;
	}

	/**
	 * @param sparesQuestionsBean the sparesQuestionsBean to set
	 */
	public void setSparesQuestionsBean(List<DBOBean> sparesQuestionsBean) {
		this.sparesQuestionsBean = sparesQuestionsBean;
	}

	/**
	 * @return the saveEleFilterList
	 */
	public List<DBOBean> getSaveEleFilterList() {
		return saveEleFilterList;
	}

	/**
	 * @param saveEleFilterList the saveEleFilterList to set
	 */
	public void setSaveEleFilterList(List<DBOBean> saveEleFilterList) {
		this.saveEleFilterList = saveEleFilterList;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the mechAuxGeneralListNew
	 */
	public List<DBOBean> getMechAuxGeneralListNew() {
		return mechAuxGeneralListNew;
	}

	/**
	 * @param mechAuxGeneralListNew the mechAuxGeneralListNew to set
	 */
	public void setMechAuxGeneralListNew(List<DBOBean> mechAuxGeneralListNew) {
		this.mechAuxGeneralListNew = mechAuxGeneralListNew;
	}

	/**
	 * @return the eleGeneralList
	 */
	public List<DBOBean> getEleGeneralList() {
		return eleGeneralList;
	}

	/**
	 * @param eleGeneralList the eleGeneralList to set
	 */
	public void setEleGeneralList(List<DBOBean> eleGeneralList) {
		this.eleGeneralList = eleGeneralList;
	}

	/**
	 * @return the eleOtherList
	 */
	public List<DBOBean> getEleOtherList() {
		return eleOtherList;
	}

	/**
	 * @param eleOtherList the eleOtherList to set
	 */
	public void setEleOtherList(List<DBOBean> eleOtherList) {
		this.eleOtherList = eleOtherList;
	}

	/**
	 * @return the eleGeneralSpecialList
	 */
	public List<DBOBean> getEleGeneralSpecialList() {
		return eleGeneralSpecialList;
	}

	/**
	 * @param eleGeneralSpecialList the eleGeneralSpecialList to set
	 */
	public void setEleGeneralSpecialList(List<DBOBean> eleGeneralSpecialList) {
		this.eleGeneralSpecialList = eleGeneralSpecialList;
	}
	
	List<TransportationDetailsBean> savedUpdateLoginUserEngineerTransportList1 = new ArrayList<>();
	public List<TransportationDetailsBean> getSavedUpdateLoginUserEngineerTransportList1() {
		return savedUpdateLoginUserEngineerTransportList1;
	}

	public void setSavedUpdateLoginUserEngineerTransportList1(List<TransportationDetailsBean> savedUpdateLoginUserEngineerTransportList1) {
		this.savedUpdateLoginUserEngineerTransportList1 = savedUpdateLoginUserEngineerTransportList1;
	}
	
	List<TransportationDetailsBean> savedUpdateLoginUserEngineerTransportList2 = new ArrayList<>();
	public List<TransportationDetailsBean> getSavedUpdateLoginUserEngineerTransportList2() {
		return savedUpdateLoginUserEngineerTransportList2;
	}

	public void setSavedUpdateLoginUserEngineerTransportList2(List<TransportationDetailsBean> savedUpdateLoginUserEngineerTransportList2) {
		this.savedUpdateLoginUserEngineerTransportList2 = savedUpdateLoginUserEngineerTransportList2;
	}
	
	List<TransportationDetailsBean> savedUpdateLoginUserReviewerTransportList1 = new ArrayList<>();
	public List<TransportationDetailsBean> getSavedUpdateLoginUserReviewerTransportList1() {
		return savedUpdateLoginUserReviewerTransportList1;
	}

	public void setSavedUpdateLoginUserReviewerTransportList1(List<TransportationDetailsBean> savedUpdateLoginUserReviewerTransportList1) {
		this.savedUpdateLoginUserReviewerTransportList1 = savedUpdateLoginUserReviewerTransportList1;
	}
	
	List<TransportationDetailsBean> savedUpdateLoginUserApproverTransportList1 = new ArrayList<>();
	public List<TransportationDetailsBean> getSavedUpdateLoginUserApproverTransportList1() {
		return savedUpdateLoginUserApproverTransportList1;
	}

	public void setSavedUpdateLoginUserApproverTransportList1(List<TransportationDetailsBean> savedUpdateLoginUserApproverTransportList1) {
		this.savedUpdateLoginUserApproverTransportList1 = savedUpdateLoginUserApproverTransportList1;
	}
	List<TransportationDetailsBean> instrTurbineList = new ArrayList<>();
	public List<TransportationDetailsBean> getInstrTurbineList() {
		return instrTurbineList;
	}

	public void setInstrTurbineList(List<TransportationDetailsBean> instrTurbineList) {
		this.instrTurbineList = instrTurbineList;
	}
	private String name;
	private String email;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	List<List> userDetailsEmailList = new ArrayList<List>();
	public List<List> getUserDetailsEmailList() {
		return userDetailsEmailList;
	}

	public void setUserDetailsEmailList(List<List> userDetailsEmailList) {
		this.userDetailsEmailList = userDetailsEmailList;
	}
	
	private String validateErrorMsg;
	public String getValidateErrorMsg() {
		return validateErrorMsg;
	}

	public void setValidateErrorMsg(String validateErrorMsg) {
		this.validateErrorMsg = validateErrorMsg;
	}
	
	private int successCodeNew;
	private String successMsgNew;
	public int getSuccessCodeNew() {
		return successCodeNew;
	}
	public void setSuccessCodeNew(int successCodeNew) {
		this.successCodeNew = successCodeNew;
	}

	public String getSuccessMsgNew() {
		return successMsgNew;
	}

	public void setSuccessMsgNew(String successMsgNew) {
		this.successMsgNew = successMsgNew;
	}
	List<QuotationForm> validationList = new ArrayList<QuotationForm>();
	public List<QuotationForm> getValidationList() {
		return validationList;
	}

	public void setValidationList(List<QuotationForm> validationList) {
		this.validationList = validationList;
	}
	private int validationCode;
	public int getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(int validationCode) {
		this.validationCode = validationCode;
	}
	
	public String validationMsg;
	public String getValidationMsg() {
		return validationMsg;
	}

	public void setValidationMsg(String validationMsg) {
		this.validationMsg = validationMsg;
	}
	
	public String item;
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	public String itemNm;
	public String getItemNm() {
		return itemNm;
	}

	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String filePath;
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String fileName;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	List <QuotationForm> documentList = new ArrayList<QuotationForm>();
	public List<QuotationForm> getDocumentlist() {
		return documentList;
	}
	public void setDocumentlist(List<QuotationForm> documentList) {
		this.documentList = documentList;
	}
	
	private List<DBOBean> savedUpdateF2fShopConvPrcieList= new ArrayList<>();
	public List<DBOBean> getSavedUpdateF2fShopConvPrcieList() {
		return savedUpdateF2fShopConvPrcieList;
	}
	
	public void setSavedUpdateF2fShopConvPrcieList(List<DBOBean> savedUpdateF2fShopConvPrcieList) {
		this.savedUpdateF2fShopConvPrcieList = savedUpdateF2fShopConvPrcieList;
	}
	
	private List<DBOBean> unsavedUpdateF2fShopConvPriceList= new ArrayList<>();
	public List<DBOBean> getUnsavedUpdateF2fShopConvPriceList() {
		return unsavedUpdateF2fShopConvPriceList;
	}

	public void setUnsavedUpdateF2fShopConvPriceList(List<DBOBean> unsavedUpdateF2fShopConvPriceList) {
		this.unsavedUpdateF2fShopConvPriceList = unsavedUpdateF2fShopConvPriceList;
	}
}
