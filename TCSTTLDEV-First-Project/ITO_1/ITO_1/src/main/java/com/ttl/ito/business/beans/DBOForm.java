package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DBOForm {
	
	List<DBOBean> attachmentList = new ArrayList<>();
	public List<DBOBean> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<DBOBean> attachmentList) {
		this.attachmentList = attachmentList;
	}

	private String newItem;
	private int deleteFlag;
	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	private int slNo;
	public int getSlNo() {
		return slNo;
	}

	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String fileName;
	public String getNewItem() {
		return newItem;
	}

	public void setNewItem(String newItem) {
		this.newItem = newItem;
	}
	
	public List<DBOBean> getSavedComercialDataList() {
		return savedComercialDataList;
	}

	public void setSavedComercialDataList(List<DBOBean> savedComercialDataList) {
		this.savedComercialDataList = savedComercialDataList;
	}

	List<DBOBean> savedComercialDataList = new ArrayList<>(); 
	
	List<DBOBean> dboComercialItemList = new ArrayList<>(); 
	public List<DBOBean> getDboComercialItemList() {
		return dboComercialItemList;
	}

	public void setDboComercialItemList(List<DBOBean> dboComercialItemList) {
		this.dboComercialItemList = dboComercialItemList;
	}

	List<DBOBean> pdfData = new ArrayList<>();
	public List<DBOBean> getPdfData() {
		return pdfData;
	}

	public void setPdfData(List<DBOBean> pdfData) {
		this.pdfData = pdfData;
	}
	private String altMake;
	public String getAltMake() {
		return altMake;
	}

	public void setAltMake(String altMake) {
		this.altMake = altMake;
	}

	public String getGearbox() {
		return gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String gearbox;
	private String type;
	private String filePath;
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	private String item;
	private int  conditionTableInput;
	public int getConditionTableInput() {
		return conditionTableInput;
	}

	public void setConditionTableInput(int conditionTableInput) {
		this.conditionTableInput = conditionTableInput;
	}

	private int SsId;
	public int getSsId() {
		return SsId;
	}

	public void setSsId(int ssId) {
		SsId = ssId;
	}

	private List<DBOBean>  savePerformanceList3 =  new ArrayList<>();
	public List<DBOBean> getSavePerformanceList3() {
		return savePerformanceList3;
	}

	public void setSavePerformanceList3(List<DBOBean> savePerformanceList3) {
		this.savePerformanceList3 = savePerformanceList3;
	}

	private List<DBOBean> newPerformanceList =  new ArrayList<>();
	public List<DBOBean> getNewPerformanceList() {
		return newPerformanceList;
	}

	public void setNewPerformanceList(List<DBOBean> newPerformanceList) {
		this.newPerformanceList = newPerformanceList;
	}

	private List<DBOBean> otherChapterData =  new ArrayList<>();
	public List<DBOBean> getOtherChapterData() {
		return otherChapterData;
	}

	public void setOtherChapterData(List<DBOBean> otherChapterData) {
		this.otherChapterData = otherChapterData;
	}

	private List<DBOBean> saveOtherChapterList =  new ArrayList<>();
	public List<DBOBean> getSaveOtherChapterList() {
		return saveOtherChapterList;
	}

	public void setSaveOtherChapterList(List<DBOBean> saveOtherChapterList) {
		this.saveOtherChapterList = saveOtherChapterList;
	}

	private List<DBOBean> otherDataList =  new ArrayList<>();
	
	public List<DBOBean> getOtherDataList() {
		return otherDataList;
	}

	public void setOtherDataList(List<DBOBean> otherDataList) {
		this.otherDataList = otherDataList;
	}

	private List<DBOBean> performanceData1 =  new ArrayList<>();
	public List<DBOBean> getPerformanceData1() {
		return performanceData1;
	}

	public void setPerformanceData1(List<DBOBean> performanceData1) {
		this.performanceData1 = performanceData1;
	}

	private String instrCntrl;
	private String acdc;
	public String getAcdc() {
		return acdc;
	}

	public void setAcdc(String acdc) {
		this.acdc = acdc;
	}

	private String scfm;
	public String getScfm() {
		return scfm;
	}

	public void setScfm(String scfm) {
		this.scfm = scfm;
	}

	public String getInstrCntrl() {
		return instrCntrl;
	}

	public void setInstrCntrl(String instrCntrl) {
		this.instrCntrl = instrCntrl;
	}

	private String auxSteam;
	public String getAuxSteam() {
		return auxSteam;
	}

	public void setAuxSteam(String auxSteam) {
		this.auxSteam = auxSteam;
	}

	private int noOfCondition;
	public int getNoOfCondition() {
		return noOfCondition;
	}

	public void setNoOfCondition(int noOfCondition) {
		this.noOfCondition = noOfCondition;
	}

	private int successCode;
	private int itemId;
	//private Object itemName;
	
	private float mechAuxTotalPrice;
	private int loggedInUserId;
	private int modifiedById;
	private int quotId;
	private int userRoleId;
	private int overwrittenPriceFlag;
	private int quantity;
	private int framePowId;
	private int glandSelType;
	private int lubeOilType;
	private int lubeOilSubItem;
	private float discountPer;
	
	public float getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(float discountPer) {
		this.discountPer = discountPer;
	}

	private float f2FAddOnCost;
	private String f2fItemTechRemarks;
	private String f2fItemComrRemarks;
	private float f2FBasicCost;
	private float price;
	private float flowRate;
	private float additionalCost;
	private float mechAdditionalCost;
	private float eleAdditionalCost;
	private float sapPrice;
	private float overwrittenPrice;
	private float power;
	private float totalCost;
	private float basicCost;
	private float eleAddOnCost;
	private float addInstrTotCost;
	private float totalPrice;
	private float f2fAddOnCost;
	private float mechAuxAddOnCost;
	
	private float condensorFlowCapacity;
	private String auxSteamValue;
	public String getAuxSteamValue() {
		return auxSteamValue;
	}

	public void setAuxSteamValue(String auxSteamValue) {
		this.auxSteamValue = auxSteamValue;
	}
	private String scfmValue;
	public String getScfmValue() {
		return scfmValue;
	}

	public void setScfmValue(String scfmValue) {
		this.scfmValue = scfmValue;
	}

	private String custType;
	private String successMsg;
	private String panelType;
	private String dboEleType;
	private String remarks;
	private String techRemarks;
	private String itemTechRemarks;
	private String itemComrRemarks;
	private String mechItemTechRemarks;
	private String mechItemComrRemarks;
	
	private String mechAuxItemTechRemarks;
	private String mechAuxItemComrRemarks;
	private String f2fItemTechComments;
	private String f2fItemComrComments;
	private String subItemTechRemarks;
	private String subItemComrRemarks;
	private String subItemTypeTechRemarks;
	private String subItemTypeComrRemarks;
	private String eleItemTechRemarks;
	private String eleItemComrRemarks;
	private String comrRemarks;
	private String techComments;
	private String comrComments;
	private String turbineCode;
	private String custCode;
	private String costCode;
	private String compRemarks;
	private String panelCode;
	private String groupCode;
	private String eleItemTechComments;
	private String eleItemComrComments;
	private int eleSpecialId;
	private String colValCd;
	private int itemApplInd;
	private String typeOfPanel;
	private String typeOfQuotation;
	private String typeOfTurbineStart;
	 public String getTypeOfTurbineStart() {
		return typeOfTurbineStart;
	}

	public void setTypeOfTurbineStart(String typeOfTurbineStart) {
		this.typeOfTurbineStart = typeOfTurbineStart;
	}

	SaveBasicDetails saveBasicDetails = new SaveBasicDetails();
	public SaveBasicDetails getSaveBasicDetails() {
		return saveBasicDetails;
	}

	public void setSaveBasicDetails(SaveBasicDetails saveBasicDetails) {
		this.saveBasicDetails = saveBasicDetails;
	}

	public String getTypeOfQuotation() {
		return typeOfQuotation;
	}

	public void setTypeOfQuotation(String typeOfQuotation) {
		this.typeOfQuotation = typeOfQuotation;
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
	List<DBOBean> initialFillList= new ArrayList<>();
	public List<DBOBean> getInitialFillList() {
		return initialFillList;
	}

	public void setInitialFillList(List<DBOBean> initialFillList) {
		this.initialFillList = initialFillList;
	}

	List<DBOBean> priceList= new ArrayList<>();
	public List<DBOBean> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<DBOBean> priceList) {
		this.priceList = priceList;
	}
	public List<DBOBean> getAddonPriceList() {
		return addonPriceList;
	}
	public void setAddonPriceList(List<DBOBean> addonPriceList) {
		this.addonPriceList = addonPriceList;
	}
	public List<DBOBean> getOverTankPriceList() {
		return overTankPriceList;
	}
	public void setOverTankPriceList(List<DBOBean> overTankPriceList) {
		this.overTankPriceList = overTankPriceList;
	}
	List<DBOBean> frameOilList= new ArrayList<>();
	public List<DBOBean> getFrameOilList() {
		return frameOilList;
	}

	public void setFrameOilList(List<DBOBean> frameOilList) {
		this.frameOilList = frameOilList;
	}

	List<DBOBean> addonPriceList= new ArrayList<>();
	List<DBOBean> overTankPriceList= new ArrayList<>();
	private String make;
	private String dutySync;
	private String  eleType;
	public String getEleType() {
		return eleType;
	}

	public void setEleType(String eleType) {
		this.eleType = eleType;
	}

	public String getTypeOfPanel() {
		return typeOfPanel;
	}

	public void setTypeOfPanel(String typeOfPanel) {
		this.typeOfPanel = typeOfPanel;
	}

	private Boolean selectedPriceFlag;
	private int mechAddOnCost;
	private Float selectedCost;
	private int subItemTypeId;
	private int noOfConditions;
	private String	hmbdImage;
	public String getHmbdImage() {
		return hmbdImage;
	}

	public void setHmbdImage(String hmbdImage) {
		this.hmbdImage = hmbdImage;
	}

	public int getNoOfConditions() {
		return noOfConditions;
	}

	public void setNoOfConditions(int noOfConditions) {
		this.noOfConditions = noOfConditions;
	}

	private int hmbdTableInput;
	public int getHmbdTableInput() {
		return hmbdTableInput;
	}

	public void setHmbdTableInput(int hmbdTableInput) {
		this.hmbdTableInput = hmbdTableInput;
	}
	List<DBOBean> updatePriceElectricalVms= new ArrayList<>();
	public List<DBOBean> getUpdatePriceElectricalVms() {
		return updatePriceElectricalVms;
	}

	public void setUpdatePriceElectricalVms(List<DBOBean> updatePriceElectricalVms) {
		this.updatePriceElectricalVms = updatePriceElectricalVms;
	}
	
	List<DBOBean> updateF2fFrameSpecData= new ArrayList<>();
	public List<DBOBean> getUpdateF2fFrameSpecData() {
		return updateF2fFrameSpecData;
	}

	public void setUpdateF2fFrameSpecData(List<DBOBean> updateF2fFrameSpecData) {
		this.updateF2fFrameSpecData = updateF2fFrameSpecData;
	}

	List<DBOBean> updatePriceF2fColVal= new ArrayList<>();
	public List<DBOBean> getUpdatePriceF2fColVal() {
		return updatePriceF2fColVal;
	}

	public void setUpdatePriceF2fColVal(List<DBOBean> updatePriceF2fColVal) {
		this.updatePriceF2fColVal = updatePriceF2fColVal;
	}

	public List<DBOBean> getUpdateF2fPrice() {
		return updateF2fPrice;
	}

	public void setUpdateF2fPrice(List<DBOBean> updateF2fPrice) {
		this.updateF2fPrice = updateF2fPrice;
	}

	public List<DBOBean> getUpdateMechPrice() {
		return updateMechPrice;
	}

	public void setUpdateMechPrice(List<DBOBean> updateMechPrice) {
		this.updateMechPrice = updateMechPrice;
	}

	public List<DBOBean> getUpdateMechAddOnCost() {
		return updateMechAddOnCost;
	}

	public void setUpdateMechAddOnCost(List<DBOBean> updateMechAddOnCost) {
		this.updateMechAddOnCost = updateMechAddOnCost;
	}

	public List<DBOBean> getUpdateMechAuxColVal() {
		return updateMechAuxColVal;
	}

	public void setUpdateMechAuxColVal(List<DBOBean> updateMechAuxColVal) {
		this.updateMechAuxColVal = updateMechAuxColVal;
	}

	public List<DBOBean> getUpdateMechOverTank() {
		return updateMechOverTank;
	}

	public void setUpdateMechOverTank(List<DBOBean> updateMechOverTank) {
		this.updateMechOverTank = updateMechOverTank;
	}
	List<DBOBean> updateMechAuxPrice= new ArrayList<>();
	public List<DBOBean> getUpdateMechAuxPrice() {
		return updateMechAuxPrice;
	}

	public void setUpdateMechAuxPrice(List<DBOBean> updateMechAuxPrice) {
		this.updateMechAuxPrice = updateMechAuxPrice;
	}

	List<DBOBean> updateF2fPrice= new ArrayList<>();
	List<DBOBean> updateMechPrice= new ArrayList<>();
	List<DBOBean> updateMechAddOnCost= new ArrayList<>();
	List<DBOBean> updateMechAuxColVal= new ArrayList<>();
	List<DBOBean> updateMechOverTank= new ArrayList<>();

	List<DBOBean>updatePriceElectricalInstr= new ArrayList<>();
	List<DBOBean>updatePriceElectricalInstr3= new ArrayList<>();
	
	public List<DBOBean> getUpdatePriceElectricalInstr3() {
		return updatePriceElectricalInstr3;
	}

	public void setUpdatePriceElectricalInstr3(List<DBOBean> updatePriceElectricalInstr3) {
		this.updatePriceElectricalInstr3 = updatePriceElectricalInstr3;
	}

	public List<DBOBean> getUpdatePriceElectricalInstr() {
		return updatePriceElectricalInstr;
	}

	public void setUpdatePriceElectricalInstr(List<DBOBean> updatePriceElectricalInstr) {
		this.updatePriceElectricalInstr = updatePriceElectricalInstr;
	}

	List<DBOBean> updatePriceElectricalColVal= new ArrayList<>();
	public List<DBOBean> getUpdatePriceElectricalColVal() {
		return updatePriceElectricalColVal;
	}

	public void setUpdatePriceElectricalColVal(List<DBOBean> updatePriceElectricalColVal) {
		this.updatePriceElectricalColVal = updatePriceElectricalColVal;
	}

	List<DBOBean> updatePriceElectricalAddOnList= new ArrayList<>();
	public List<DBOBean> getUpdatePriceElectricalAddOnList() {
		return updatePriceElectricalAddOnList;
	}

	public void setUpdatePriceElectricalAddOnList(List<DBOBean> updatePriceElectricalAddOnList) {
		this.updatePriceElectricalAddOnList = updatePriceElectricalAddOnList;
	}

	List<DBOBean> updatePriceElectricalList= new ArrayList<>();
	public List<DBOBean> getUpdatePriceElectricalList() {
		return updatePriceElectricalList;
	}

	public void setUpdatePriceElectricalList(List<DBOBean> updatePriceElectricalList) {
		this.updatePriceElectricalList = updatePriceElectricalList;
	}

	List<DBOBean> unitList= new ArrayList<>();
	public List<DBOBean> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<DBOBean> unitList) {
		this.unitList = unitList;
	}
	
	List<DBOBean> cirList  = new ArrayList<>();
	public List<DBOBean> getCirList() {
		return cirList;
	}

	public void setCirList(List<DBOBean> cirList) {
		this.cirList = cirList;
	}

	List<DBOBean> itemList  = new ArrayList<>();
	List<DBOBean>  itemPurityList  = new ArrayList<>();
	public List<DBOBean> getItemPurityList() {
		return itemPurityList;
	}

	public void setItemPurityList(List<DBOBean> itemPurityList) {
		this.itemPurityList = itemPurityList;
	}

	public List<DBOBean> getItemList() {
		return itemList;
	}

	public void setItemList(List<DBOBean> itemList) {
		this.itemList = itemList;
	}

	List<DBOBean> identList  = new ArrayList<>();
	public List<DBOBean> getIdentList() {
		return identList;
	}

	public void setIdentList(List<DBOBean> identList) {
		this.identList = identList;
	}

	List<DBOBean> acList  = new ArrayList<>();
	List<DBOBean> purityList  = new ArrayList<>();
	List<DBOBean> fixedList = new ArrayList<>();
	public List<DBOBean> getFixedList() {
		return fixedList;
	}

	public void setFixedList(List<DBOBean> fixedList) {
		this.fixedList = fixedList;
	}

	public List<DBOBean> getPurityList() {
		return purityList;
	}

	public void setPurityList(List<DBOBean> purityList) {
		this.purityList = purityList;
	}

	public List<DBOBean> getAcList() {
		return acList;
	}

	public void setAcList(List<DBOBean> acList) {
		this.acList = acList;
	}

	List<DBOBean> scfmList  = new ArrayList<>();
	public List<DBOBean> getScfmList() {
		return scfmList;
	}

	public void setScfmList(List<DBOBean> scfmList) {
		this.scfmList = scfmList;
	}

	List<DBOBean> controlList  = new ArrayList<>();
	public List<DBOBean> getControlList() {
		return controlList;
	}

	public void setControlList(List<DBOBean> controlList) {
		this.controlList = controlList;
	}

	List<DBOBean> lubeOilList = new ArrayList<>();
	public List<DBOBean> getLubeOilList() {
		return lubeOilList;
	}

	public void setLubeOilList(List<DBOBean> lubeOilList) {
		this.lubeOilList = lubeOilList;
	}

	List<DBOBean> freshList = new ArrayList<>();
	public List<DBOBean> getFreshList() {
		return freshList;
	}

	public void setFreshList(List<DBOBean> freshList) {
		this.freshList = freshList;
	}

	List<DBOBean> auxSteamList = new ArrayList<>();
	public List<DBOBean> getAuxSteamList() {
		return auxSteamList;
	}

	public void setAuxSteamList(List<DBOBean> auxSteamList) {
		this.auxSteamList = auxSteamList;
	}

	List<DBOBean> hmbdList= new ArrayList<>();
	List<DBOBean> auxList= new ArrayList<>(); 
	public List<DBOBean> getAuxList() {
		return auxList;
	}

	public void setAuxList(List<DBOBean> auxList) {
		this.auxList = auxList;
	}

	public List<DBOBean> getHmbdList() {
		return hmbdList;
	}

	public void setHmbdList(List<DBOBean> hmbdList) {
		this.hmbdList = hmbdList;
	}

	List<DBOBean> dboPerformanceList1= new ArrayList<>();
	List<DBOBean> dboPerformanceList2= new ArrayList<>();
	public List<DBOBean> getDboPerformanceList2() {
		return dboPerformanceList2;
	}

	public void setDboPerformanceList2(List<DBOBean> dboPerformanceList2) {
		this.dboPerformanceList2 = dboPerformanceList2;
	}

	public List<DBOBean> getDboPerformanceList3() {
		return dboPerformanceList3;
	}

	public void setDboPerformanceList3(List<DBOBean> dboPerformanceList3) {
		this.dboPerformanceList3 = dboPerformanceList3;
	}

	List<DBOBean> dboPerformanceList3= new ArrayList<>();
	public List<DBOBean> getDboPerformanceList1() {
		return dboPerformanceList1;
	}

	public void setDboPerformanceList1(List<DBOBean> dboPerformanceList1) {
		this.dboPerformanceList1 = dboPerformanceList1;
	}

	List<DBOBean> savePerformanceList1= new ArrayList<>();
	List<DBOBean> performanceData= new ArrayList<>();
	public List<DBOBean> getPerformanceData() {
		return performanceData;
	}

	public void setPerformanceData(List<DBOBean> performanceData) {
		this.performanceData = performanceData;
	}

	public List<DBOBean> getSavePerformanceList1() {
		return savePerformanceList1;
	}

	public void setSavePerformanceList1(List<DBOBean> savePerformanceList1) {
		this.savePerformanceList1 = savePerformanceList1;
	}

	public List<DBOBean> getSavePerformanceList2() {
		return savePerformanceList2;
	}

	public void setSavePerformanceList2(List<DBOBean> savePerformanceList2) {
		this.savePerformanceList2 = savePerformanceList2;
	}
	List<DBOBean> dboPerformanceList= new ArrayList<>();
	public List<DBOBean> getDboPerformanceList() {
		return dboPerformanceList;
	}

	public void setDboPerformanceList(List<DBOBean> dboPerformanceList) {
		this.dboPerformanceList = dboPerformanceList;
	}

	List<DBOBean> savePerformanceList2= new ArrayList<>();


	public List<DBOBean> getSaveEleVmsList() {
		return saveEleVmsList;
	}

	public void setSaveEleVmsList(List<DBOBean> saveEleVmsList) {
		this.saveEleVmsList = saveEleVmsList;
	}

	private Map<Integer, String> msgToUser = new HashMap<>();
	private List<DBOBean> saveEleVmsList = new ArrayList<>();
	private List<Integer> eleSubItemIdList  = new ArrayList<>();
	public List<Integer> getEleSubItemIdList() {
		return eleSubItemIdList;
	}

	public void setEleSubItemIdList(List<Integer> eleSubItemIdList) {
		this.eleSubItemIdList = eleSubItemIdList;
	}

	private List<Integer> itemIdList = new ArrayList<>();
	private List<Integer> mechItemIdList = new ArrayList<>();
	private List<Integer> mechAuxItemIdList = new ArrayList<>();
	private List<Integer> mechSubItemIdList = new ArrayList<>();
	private List<Integer> subItemIdList = new ArrayList<>();
	private List<Integer> subItemTypeIdList = new ArrayList<>();
	private List<Integer> subTypeItemIdList = new ArrayList<>();
	private List<Integer> generalInputIdList = new ArrayList<>();
	private List<String> itemIdList1 = new ArrayList<>();
	private List<Integer> eleItemIdList =  new ArrayList<>();
	private List<DBOBean> dboEleVmsList=  new ArrayList<>();
	private List<DBOBean> eleVmsData =  new ArrayList<>();
	public List<DBOBean> getEleVmsData() {
		return eleVmsData;
	}

	public void setEleVmsData(List<DBOBean> eleVmsData) {
		this.eleVmsData = eleVmsData;
	}

	public List<DBOBean> getDboEleVmsList() {
		return dboEleVmsList;
	}

	public void setDboEleVmsList(List<DBOBean> dboEleVmsList) {
		this.dboEleVmsList = dboEleVmsList;
	}

	// Used for DBO Mechanical
	private List<DBOBean> savedEleSubDataList=new ArrayList<>();
	public List<DBOBean> getSavedEleSubDataList() {
		return savedEleSubDataList;
	}

	public void setSavedEleSubDataList(List<DBOBean> savedEleSubDataList) {
		this.savedEleSubDataList = savedEleSubDataList;
	}
	private List<DBOBean> saveEleSubList=new ArrayList<>();
	public List<DBOBean> getSaveEleSubList() {
		return saveEleSubList;
	}

	public void setSaveEleSubList(List<DBOBean> saveEleSubList) {
		this.saveEleSubList = saveEleSubList;
	}

	private List<DBOBean> saveAddInstrList=new ArrayList<>();
	public List<DBOBean> getSaveAddInstrList() {
		return saveAddInstrList;
	}

	public void setSaveAddInstrList(List<DBOBean> saveAddInstrList) {
		this.saveAddInstrList = saveAddInstrList;
	}
	private List<DBOBean> savedAddInstrList=new ArrayList<>();
	public List<DBOBean> getSavedAddInstrList() {
		return savedAddInstrList;
	}

	public void setSavedAddInstrList(List<DBOBean> savedAddInstrList) {
		this.savedAddInstrList = savedAddInstrList;
	}

	private List<DBOBean> dboEleInstrList=new ArrayList<>();
	private List<DBOBean> dboEleInstrList1=new ArrayList<>();
	public List<DBOBean> getDboEleInstrList1() {
		return dboEleInstrList1;
	}

	public void setDboEleInstrList1(List<DBOBean> dboEleInstrList1) {
		this.dboEleInstrList1 = dboEleInstrList1;
	}

	public List<DBOBean> getDboEleInstrList() {
		return dboEleInstrList;
	}

	public void setDboEleInstrList(List<DBOBean> dboEleInstrList) {
		this.dboEleInstrList = dboEleInstrList;
	}
	
	private List<DBOBean> DboVmsList=new ArrayList<>();
	public List<DBOBean> getDboVmsList() {
		return DboVmsList;
	}

	public void setDboVmsList(List<DBOBean> dboVmsList) {
		DboVmsList = dboVmsList;
	}

	private List<DBOBean> DboAvrList=new ArrayList<>();
	private List<DBOBean> DboGovernerList=new ArrayList<>();
	public List<DBOBean> getDboGovernerList() {
		return DboGovernerList;
	}

	public void setDboGovernerList(List<DBOBean> dboGovernerList) {
		DboGovernerList = dboGovernerList;
	}

	public List<DBOBean> getDboAvrList() {
		return DboAvrList;
	}

	public void setDboAvrList(List<DBOBean> dboAvrList) {
		DboAvrList = dboAvrList;
	}

	private List<DBOBean> dBOMechanicalList=new ArrayList<>();
	private List<DBOBean> mechAuxTechList=new ArrayList<>();
	private List<AddOnComponent> addOnList=new ArrayList<>();
	private List<DBOBean>  eleRefreshTechList = new ArrayList<>();
	private List<DBOBean> dBOColList=new ArrayList<>();
	private List<DBOBean> dBOColWithPrice=new ArrayList<>();
	private List<DBOBean> dBOColListSAP=new ArrayList<>();
	private List<DBOBean> dBOColWithPriceSAP=new ArrayList<>();
	private List<DBOBean> dBOMechanicalListExcel=new ArrayList<>();
	private List<DBOBean> dBOMechanicalListSap=new ArrayList<>();
	private List<DBOBean> dboEleItemList=new ArrayList<>();
	private List<DBOBean> questionsBean =new ArrayList<>();
	private List<DBOBean> questionsBean1 =new ArrayList<>();
	public List<DBOBean> getQuestionsBean1() {
		return questionsBean1;
	}

	public void setQuestionsBean1(List<DBOBean> questionsBean1) {
		this.questionsBean1 = questionsBean1;
	}

	private List<DBOBean> varQuestionsBean =new ArrayList<>();
	private List<DBOBean> sparesQuestionsBean =new ArrayList<>();
	private List<DBOBean> sapQuestionsBean = new ArrayList<>();
	private List<DBOBean> eleRefreshTechData = new ArrayList<>();
	private List<DBOBean> nominalOutputList = new ArrayList<>();
	private List<DBOBean> generalAddOnList = new ArrayList<>();
	private List<DBOBean> selGeneralAddOnList = new ArrayList<>();
	private List<DBOBean> addOnInstrList = new ArrayList<>();
	private List<DBOBean> selAddOnInstrList = new ArrayList<>();
	private List<DBOBean> f2fTechList = new ArrayList<>();
	private List<DBOBean> MechTechList = new ArrayList<>();
	private List<DBOBean> f2fList = new ArrayList<>();
	private List<DBOBean> f2fAddOnList = new ArrayList<>();
	private List<DBOBean> mechAddOnList = new ArrayList<>();
	private List<DBOBean> saveMechAuxList = new ArrayList<>();
	private List<DBOBean> mechSelectedPriceData = new ArrayList<>();
	private List<DBOBean> selectedExcelData = new ArrayList<>();
	private List<DBOBean> saveMechExtScopeList=new ArrayList<>();
	private List<DBOBean> excelPriceData = new ArrayList<>();
	private List<DBOBean> saveEleSpecialFilterList = new ArrayList<>();
	private List<DBOBean> dboEleSpecialFilterList = new ArrayList<>();
	private List<DBOBean> dboEleAddOnColumns = new ArrayList<>();
	private List<DBOBean> dboEleAddOnColData = new ArrayList<>();
	
	private List<DBOBean> selectedDboEleAddOnColData = new ArrayList<>();
	private List<DBOBean> f2fSelectedPriceData = new ArrayList<>();
	private List<DBOBean> eleTechList=new ArrayList<>();
	private List<DBOBean> dboMechOthersList=new ArrayList<>();
	private List<DBOBean> dboEleOthersList=new ArrayList<>();
	private List<DBOBean> dboEleCompOthersList=new ArrayList<>();
	private List<DBOBean> dboEleFilterList=new ArrayList<>();
	private List<DBOBean> savedF2fDataList=new ArrayList<>();
	private List<DBOBean> savedGeneralInputList=new ArrayList<>();
	private List<DBOBean> saveGeneralInputList=new ArrayList<>();
	private List<DBOBean> saveEleFilterList =new ArrayList<>();
	private List<DBOBean> savedMechList=new ArrayList<>();
	private List<DBOBean> savedF2fSubDataList=new ArrayList<>();
	private List<DBOBean> savedF2fSubTypeDataList=new ArrayList<>();
	private List<DBOBean> dboElePanelList1 = new ArrayList<>();
	private List<DBOBean> dboElePanelList2 = new ArrayList<>();
	private List<DBOBean> saveEleList = new ArrayList<>();
	private List<DBOBean>  savedEleDataList=new ArrayList<>();
	// Used for DBO Electrical
	private List<DBOBean> dBOElectricalList=new ArrayList<>();
	private List<DBOBean> dBOElectricalData=new ArrayList<>();
	private List<DBOBean> dBOElectricalColList=new ArrayList<>();
	private List<DBOBean> dBOElectricalColWithPrice=new ArrayList<>();

	private List<DBOBean> dBOElectricalColListSAP=new ArrayList<>();
	private List<DBOBean> dBOElectricalColWithPriceSAP=new ArrayList<>();
	
	private List<DBOBean> dBOElectricalQuestionsBean =new ArrayList<>();
	private List<DBOBean> dBOElectricalSapQuestionsBean = new ArrayList<>();
	
	private List<DBOBean> dBOEleSelectedExcelData = new ArrayList<>();
	private List<DBOBean> dBOEleExcelPriceData = new ArrayList<>(); 
	private List<DBOBean> dboEleAddonsPrice = new ArrayList<>(); 
	private List<DBOBean> dboEleSplAddonsPrice = new ArrayList<>(); 
	private List<DBOBean> dboEleSplAddonsList = new ArrayList<>();
	List<DBOBean> dboEleAddOnList = new ArrayList<DBOBean>();
	
	List<DBOBean> quotDboElectricalList = new ArrayList<DBOBean>();
	List<DBOBean> quotDboEleSplAddOnList = new ArrayList<DBOBean>();
	List<DBOBean> dboF2fItemList = new ArrayList<DBOBean>();
	List<DBOBean> dboMechItemList = new ArrayList<DBOBean>();
	List<DBOBean> savedGeneralEleFliterInputList =  new ArrayList<DBOBean>();
	List<DBOBean> dboMechAuxItemList = new ArrayList<DBOBean>();
	List<DBOBean> saveF2fList  = new ArrayList<DBOBean>();
	List<DBOBean> saveF2fSubList  = new ArrayList<DBOBean>();
	List<DBOBean> saveF2fSubTypeList  = new ArrayList<DBOBean>();
	List<DBOBean> dboMechAuxPanelList1 = new ArrayList<DBOBean>();
	List<DBOBean> f2fCacheListNew = new ArrayList<DBOBean>();
	List<DBOBean> dboMechAuxPanelList2 = new ArrayList<DBOBean>();
	List<DBOBean> saveMechSubItemList = new ArrayList<DBOBean>();
	List<DBOBean> dboF2fCacheList = new ArrayList<DBOBean>();
	List<DBOBean> dboF2fPanelList1 = new ArrayList<DBOBean>();
	List<DBOBean> dboF2fPanelList2 = new ArrayList<DBOBean>();
	List<DBOBean> dboMechPanelList1 = new ArrayList<DBOBean>();
	List<DBOBean> dboMechPanelList2 = new ArrayList<DBOBean>();
	List<DBOBean> dboF2fAddOnList1 = new ArrayList<DBOBean>();
	List<DBOBean> saveComrList1 = new ArrayList<DBOBean>();
	public List<DBOBean> getSaveComrList1() {
		return saveComrList1;
	}

	public void setSaveComrList1(List<DBOBean> saveComrList1) {
		this.saveComrList1 = saveComrList1;
	}

	public List<DBOBean> getSaveComrList2() {
		return saveComrList2;
	}

	public void setSaveComrList2(List<DBOBean> saveComrList2) {
		this.saveComrList2 = saveComrList2;
	}

	List<DBOBean> saveComrList2 = new ArrayList<DBOBean>();
	
	List<DBOBean> dboF2fAddOnList2 = new ArrayList<DBOBean>();
	List<DBOBean> f2fAddOnColumns = new ArrayList<DBOBean>();
	List<DBOBean> f2fAddOnColData = new ArrayList<DBOBean>();
	List<DBOBean> mechAddOnColumns = new ArrayList<DBOBean>();
	List<DBOBean> mechAddOnColData = new ArrayList<DBOBean>();
	private List<DBOBean>MechAuxilaryTechData = new ArrayList<>();
	private List<DBOBean>F2fTechData = new ArrayList<>();
	private List<DBOBean>MechAuxTechData = new ArrayList<>();
	private List<DBOBean>MechTechData = new ArrayList<>();
	private List<DBOBean>EleTechData = new ArrayList<>();
	private List<DBOBean>MechExtScopeData = new ArrayList<>();
	private List<DBOBean>EleExtScopeData = new ArrayList<>();
	private String eleExtScopeTechComments;
	private String eleExtScopeComrComments;
	private List<DBOBean> saveEleExtScopeList = new ArrayList<>();
	private List<DBOBean> cIExtScopeData = new ArrayList<>();
	public List<DBOBean> getcIExtScopeData() {
		return cIExtScopeData;
	}

	public void setcIExtScopeData(List<DBOBean> cIExtScopeData) {
		this.cIExtScopeData = cIExtScopeData;
	}

	public String getcIExtScopeTechComments() {
		return cIExtScopeTechComments;
	}

	public void setcIExtScopeTechComments(String cIExtScopeTechComments) {
		this.cIExtScopeTechComments = cIExtScopeTechComments;
	}

	public String getcIExtScopeComrComments() {
		return cIExtScopeComrComments;
	}

	public void setcIExtScopeComrComments(String cIExtScopeComrComments) {
		this.cIExtScopeComrComments = cIExtScopeComrComments;
	}

	public List<DBOBean> getSaveCIExtScopeList() {
		return saveCIExtScopeList;
	}

	public void setSaveCIExtScopeList(List<DBOBean> saveCIExtScopeList) {
		this.saveCIExtScopeList = saveCIExtScopeList;
	}

	private String cIExtScopeTechComments;
	private String  cIExtScopeComrComments;
	private List<DBOBean> saveCIExtScopeList = new ArrayList<>();
	private List<TurbineDetails>  turbineInstrumentsWithFramelist =new ArrayList<TurbineDetails>(); 
	DropDownColumnvalues dropDownColumnvalues = new DropDownColumnvalues();
	
	private List<DBOBean> varCostData = new ArrayList<>();
	private List<DBOBean> sparesCostData = new ArrayList<>();
	private List<DBOBean> otherCostData = new ArrayList<>();
	
	private String itemNm;
	private int subItemId;
	private int dboQuotDetId;
	private int dboQuotId;
	private String tableName;
	private String scopeCd;
	public String getScopeCd() {
		return scopeCd;
	}

	public void setScopeCd(String scopeCd) {
		this.scopeCd = scopeCd;
	}

	private String MechExtScopeTechComments;
	private String MechExtScopeComrComments;
	
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
	 * @return the loggedInUserId
	 */
	public int getLoggedInUserId() {
		return loggedInUserId;
	}

	/**
	 * @param loggedInUserId the loggedInUserId to set
	 */
	public void setLoggedInUserId(int loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	/**
	 * @return the dBOMechanicalList
	 */
	public List<DBOBean> getdBOMechanicalList() {
		return dBOMechanicalList;
	}

	/**
	 * @param dBOMechanicalList the dBOMechanicalList to set
	 */
	public void setdBOMechanicalList(List<DBOBean> dBOMechanicalList) {
		this.dBOMechanicalList = dBOMechanicalList;
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
	 * @return the dBOColList
	 */
	public List<DBOBean> getdBOColList() {
		return dBOColList;
	}

	/**
	 * @param dBOColList the dBOColList to set
	 */
	public void setdBOColList(List<DBOBean> dBOColList) {
		this.dBOColList = dBOColList;
	}

	/**
	 * @return the dBOColWithPrice
	 */
	public List<DBOBean> getdBOColWithPrice() {
		return dBOColWithPrice;
	}

	/**
	 * @param dBOColWithPrice the dBOColWithPrice to set
	 */
	public void setdBOColWithPrice(List<DBOBean> dBOColWithPrice) {
		this.dBOColWithPrice = dBOColWithPrice;
	}

	/**
	 * @return the dBOColListSAP
	 */
	public List<DBOBean> getdBOColListSAP() {
		return dBOColListSAP;
	}

	/**
	 * @param dBOColListSAP the dBOColListSAP to set
	 */
	public void setdBOColListSAP(List<DBOBean> dBOColListSAP) {
		this.dBOColListSAP = dBOColListSAP;
	}

	/**
	 * @return the dBOColWithPriceSAP
	 */
	public List<DBOBean> getdBOColWithPriceSAP() {
		return dBOColWithPriceSAP;
	}

	/**
	 * @param dBOColWithPriceSAP the dBOColWithPriceSAP to set
	 */
	public void setdBOColWithPriceSAP(List<DBOBean> dBOColWithPriceSAP) {
		this.dBOColWithPriceSAP = dBOColWithPriceSAP;
	}

	/**
	 * @return the questionsBean
	 */
	public List<DBOBean> getQuestionsBean() {
		return questionsBean;
	}

	/**
	 * @param questionsBean the questionsBean to set
	 */
	public void setQuestionsBean(List<DBOBean> questionsBean) {
		this.questionsBean = questionsBean;
	}

	/**
	 * @return the sapQuestionsBean
	 */
	public List<DBOBean> getSapQuestionsBean() {
		return sapQuestionsBean;
	}

	/**
	 * @param sapQuestionsBean the sapQuestionsBean to set
	 */
	public void setSapQuestionsBean(List<DBOBean> sapQuestionsBean) {
		this.sapQuestionsBean = sapQuestionsBean;
	}

	/**
	 * @return the selectedExcelData
	 */
	public List<DBOBean> getSelectedExcelData() {
		return selectedExcelData;
	}

	/**
	 * @param selectedExcelData the selectedExcelData to set
	 */
	public void setSelectedExcelData(List<DBOBean> selectedExcelData) {
		this.selectedExcelData = selectedExcelData;
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
	 * @return the panelType
	 */
	public String getPanelType() {
		return panelType;
	}
	/**
	 * @param panelType the panelType to set
	 */
	public void setPanelType(String panelType) {
		this.panelType = panelType;
	}

	/**
	 * @return the excelPriceData
	 */
	public List<DBOBean> getExcelPriceData() {
		return excelPriceData;
	}

	/**
	 * @param excelPriceData the excelPriceData to set
	 */
	public void setExcelPriceData(List<DBOBean> excelPriceData) {
		this.excelPriceData = excelPriceData;
	}

	/**
	 * @return the dBOElectricalList
	 */
	public List<DBOBean> getdBOElectricalList() {
		return dBOElectricalList;
	}

	/**
	 * @param dBOElectricalList the dBOElectricalList to set
	 */
	public void setdBOElectricalList(List<DBOBean> dBOElectricalList) {
		this.dBOElectricalList = dBOElectricalList;
	}

	/**
	 * @return the dBOElectricalColList
	 */
	public List<DBOBean> getdBOElectricalColList() {
		return dBOElectricalColList;
	}

	/**
	 * @param dBOElectricalColList the dBOElectricalColList to set
	 */
	public void setdBOElectricalColList(List<DBOBean> dBOElectricalColList) {
		this.dBOElectricalColList = dBOElectricalColList;
	}

	/**
	 * @return the dBOElectricalColWithPrice
	 */
	public List<DBOBean> getdBOElectricalColWithPrice() {
		return dBOElectricalColWithPrice;
	}

	/**
	 * @param dBOElectricalColWithPrice the dBOElectricalColWithPrice to set
	 */
	public void setdBOElectricalColWithPrice(List<DBOBean> dBOElectricalColWithPrice) {
		this.dBOElectricalColWithPrice = dBOElectricalColWithPrice;
	}

	/**
	 * @return the dBOElectricalColListSAP
	 */
	public List<DBOBean> getdBOElectricalColListSAP() {
		return dBOElectricalColListSAP;
	}

	/**
	 * @param dBOElectricalColListSAP the dBOElectricalColListSAP to set
	 */
	public void setdBOElectricalColListSAP(List<DBOBean> dBOElectricalColListSAP) {
		this.dBOElectricalColListSAP = dBOElectricalColListSAP;
	}

	/**
	 * @return the dBOElectricalColWithPriceSAP
	 */
	public List<DBOBean> getdBOElectricalColWithPriceSAP() {
		return dBOElectricalColWithPriceSAP;
	}

	/**
	 * @param dBOElectricalColWithPriceSAP the dBOElectricalColWithPriceSAP to set
	 */
	public void setdBOElectricalColWithPriceSAP(List<DBOBean> dBOElectricalColWithPriceSAP) {
		this.dBOElectricalColWithPriceSAP = dBOElectricalColWithPriceSAP;
	}

	/**
	 * @return the dBOElectricalQuestionsBean
	 */
	public List<DBOBean> getdBOElectricalQuestionsBean() {
		return dBOElectricalQuestionsBean;
	}

	/**
	 * @param dBOElectricalQuestionsBean the dBOElectricalQuestionsBean to set
	 */
	public void setdBOElectricalQuestionsBean(List<DBOBean> dBOElectricalQuestionsBean) {
		this.dBOElectricalQuestionsBean = dBOElectricalQuestionsBean;
	}

	/**
	 * @return the dBOElectricalSapQuestionsBean
	 */
	public List<DBOBean> getdBOElectricalSapQuestionsBean() {
		return dBOElectricalSapQuestionsBean;
	}

	/**
	 * @param dBOElectricalSapQuestionsBean the dBOElectricalSapQuestionsBean to set
	 */
	public void setdBOElectricalSapQuestionsBean(List<DBOBean> dBOElectricalSapQuestionsBean) {
		this.dBOElectricalSapQuestionsBean = dBOElectricalSapQuestionsBean;
	}

	/**
	 * @return the dBOEleSelectedExcelData
	 */
	public List<DBOBean> getdBOEleSelectedExcelData() {
		return dBOEleSelectedExcelData;
	}

	/**
	 * @param dBOEleSelectedExcelData the dBOEleSelectedExcelData to set
	 */
	public void setdBOEleSelectedExcelData(List<DBOBean> dBOEleSelectedExcelData) {
		this.dBOEleSelectedExcelData = dBOEleSelectedExcelData;
	}

	/**
	 * @return the dBOEleExcelPriceData
	 */
	public List<DBOBean> getdBOEleExcelPriceData() {
		return dBOEleExcelPriceData;
	}

	/**
	 * @param dBOEleExcelPriceData the dBOEleExcelPriceData to set
	 */
	public void setdBOEleExcelPriceData(List<DBOBean> dBOEleExcelPriceData) {
		this.dBOEleExcelPriceData = dBOEleExcelPriceData;
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
	 * @return the overwrittenPriceFlag
	 */
	public int getOverwrittenPriceFlag() {
		return overwrittenPriceFlag;
	}

	/**
	 * @param overwrittenPriceFlag the overwrittenPriceFlag to set
	 */
	public void setOverwrittenPriceFlag(int overwrittenPriceFlag) {
		this.overwrittenPriceFlag = overwrittenPriceFlag;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
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
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the sapPrice
	 */
	public float getSapPrice() {
		return sapPrice;
	}

	/**
	 * @param sapPrice the sapPrice to set
	 */
	public void setSapPrice(float sapPrice) {
		this.sapPrice = sapPrice;
	}

	/**
	 * @return the turbineInstrumentsWithFramelist
	 */
	public List<TurbineDetails> getTurbineInstrumentsWithFramelist() {
		return turbineInstrumentsWithFramelist;
	}

	/**
	 * @param turbineInstrumentsWithFramelist the turbineInstrumentsWithFramelist to set
	 */
	public void setTurbineInstrumentsWithFramelist(List<TurbineDetails> turbineInstrumentsWithFramelist) {
		this.turbineInstrumentsWithFramelist = turbineInstrumentsWithFramelist;
	}

	/**
	 * @return the turbineCode
	 */
	public String getTurbineCode() {
		return turbineCode;
	}

	/**
	 * @param turbineCode the turbineCode to set
	 */
	public void setTurbineCode(String turbineCode) {
		this.turbineCode = turbineCode;
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
	 * @return the dBOMechanicalListExcel
	 */
	public List<DBOBean> getdBOMechanicalListExcel() {
		return dBOMechanicalListExcel;
	}

	/**
	 * @param dBOMechanicalListExcel the dBOMechanicalListExcel to set
	 */
	public void setdBOMechanicalListExcel(List<DBOBean> dBOMechanicalListExcel) {
		this.dBOMechanicalListExcel = dBOMechanicalListExcel;
	}

	/**
	 * @return the dBOMechanicalListSap
	 */
	public List<DBOBean> getdBOMechanicalListSap() {
		return dBOMechanicalListSap;
	}

	/**
	 * @param dBOMechanicalListSap the dBOMechanicalListSap to set
	 */
	public void setdBOMechanicalListSap(List<DBOBean> dBOMechanicalListSap) {
		this.dBOMechanicalListSap = dBOMechanicalListSap;
	}

	/**
	 * @return the dboEleAddonsPrice
	 */
	public List<DBOBean> getDboEleAddonsPrice() {
		return dboEleAddonsPrice;
	}

	/**
	 * @param dboEleAddonsPrice the dboEleAddonsPrice to set
	 */
	public void setDboEleAddonsPrice(List<DBOBean> dboEleAddonsPrice) {
		this.dboEleAddonsPrice = dboEleAddonsPrice;
	}

	/**
	 * @return the dboEleSplAddonsPrice
	 */
	public List<DBOBean> getDboEleSplAddonsPrice() {
		return dboEleSplAddonsPrice;
	}

	/**
	 * @param dboEleSplAddonsPrice the dboEleSplAddonsPrice to set
	 */
	public void setDboEleSplAddonsPrice(List<DBOBean> dboEleSplAddonsPrice) {
		this.dboEleSplAddonsPrice = dboEleSplAddonsPrice;
	}

	/**
	 * @return the costCode
	 */
	public String getCostCode() {
		return costCode;
	}

	/**
	 * @param costCode the costCode to set
	 */
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	/**
	 * @return the dboEleType
	 */
	public String getDboEleType() {
		return dboEleType;
	}

	/**
	 * @param dboEleType the dboEleType to set
	 */
	public void setDboEleType(String dboEleType) {
		this.dboEleType = dboEleType;
	}

	/**
	 * @return the power
	 */
	public float getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(float power) {
		this.power = power;
	}

	/**
	 * @return the condensorFlowCapacity
	 */
	public float getCondensorFlowCapacity() {
		return condensorFlowCapacity;
	}

	/**
	 * @param condensorFlowCapacity the condensorFlowCapacity to set
	 */
	public void setCondensorFlowCapacity(float condensorFlowCapacity) {
		this.condensorFlowCapacity = condensorFlowCapacity;
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
	 * @return the dBOElectricalData
	 */
	public List<DBOBean> getdBOElectricalData() {
		return dBOElectricalData;
	}

	/**
	 * @param dBOElectricalData the dBOElectricalData to set
	 */
	public void setdBOElectricalData(List<DBOBean> dBOElectricalData) {
		this.dBOElectricalData = dBOElectricalData;
	}

	/**
	 * @return the nominalOutputList
	 */
	public List<DBOBean> getNominalOutputList() {
		return nominalOutputList;
	}

	/**
	 * @param nominalOutputList the nominalOutputList to set
	 */
	public void setNominalOutputList(List<DBOBean> nominalOutputList) {
		this.nominalOutputList = nominalOutputList;
	}

	/**
	 * @return the userRoleId
	 */
	public int getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
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
	 * @return the itemIdList
	 */
	public List<Integer> getItemIdList() {
		return itemIdList;
	}

	/**
	 * @param itemIdList the itemIdList to set
	 */
	public void setItemIdList(List<Integer> itemIdList) {
		this.itemIdList = itemIdList;
	}

	/**
	 * @return the dboEleSplAddonsList
	 */
	public List<DBOBean> getDboEleSplAddonsList() {
		return dboEleSplAddonsList;
	}

	/**
	 * @param dboEleSplAddonsList the dboEleSplAddonsList to set
	 */
	public void setDboEleSplAddonsList(List<DBOBean> dboEleSplAddonsList) {
		this.dboEleSplAddonsList = dboEleSplAddonsList;
	}

	/**
	 * @return the generalAddOnList
	 */
	public List<DBOBean> getGeneralAddOnList() {
		return generalAddOnList;
	}

	/**
	 * @param generalAddOnList the generalAddOnList to set
	 */
	public void setGeneralAddOnList(List<DBOBean> generalAddOnList) {
		this.generalAddOnList = generalAddOnList;
	}

	/**
	 * @return the selGeneralAddOnList
	 */
	public List<DBOBean> getSelGeneralAddOnList() {
		return selGeneralAddOnList;
	}

	/**
	 * @param selGeneralAddOnList the selGeneralAddOnList to set
	 */
	public void setSelGeneralAddOnList(List<DBOBean> selGeneralAddOnList) {
		this.selGeneralAddOnList = selGeneralAddOnList;
	}

	/**
	 * @return the additionalCost
	 */
	public float getAdditionalCost() {
		return additionalCost;
	}

	/**
	 * @param additionalCost the additionalCost to set
	 */
	public void setAdditionalCost(float additionalCost) {
		this.additionalCost = additionalCost;
	}

	/**
	 * @return the compRemarks
	 */
	public String getCompRemarks() {
		return compRemarks;
	}

	/**
	 * @param compRemarks the compRemarks to set
	 */
	public void setCompRemarks(String compRemarks) {
		this.compRemarks = compRemarks;
	}

	/**
	 * @return the dboMechOthersList
	 */
	public List<DBOBean> getDboMechOthersList() {
		return dboMechOthersList;
	}

	/**
	 * @param dboMechOthersList the dboMechOthersList to set
	 */
	public void setDboMechOthersList(List<DBOBean> dboMechOthersList) {
		this.dboMechOthersList = dboMechOthersList;
	}

	/**
	 * @return the dboEleOthersList
	 */
	public List<DBOBean> getDboEleOthersList() {
		return dboEleOthersList;
	}

	/**
	 * @param dboEleOthersList the dboEleOthersList to set
	 */
	public void setDboEleOthersList(List<DBOBean> dboEleOthersList) {
		this.dboEleOthersList = dboEleOthersList;
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
	 * @return the addOnInstrList
	 */
	public List<DBOBean> getAddOnInstrList() {
		return addOnInstrList;
	}

	/**
	 * @param addOnInstrList the addOnInstrList to set
	 */
	public void setAddOnInstrList(List<DBOBean> addOnInstrList) {
		this.addOnInstrList = addOnInstrList;
	}

	/**
	 * @return the selAddOnInstrList
	 */
	public List<DBOBean> getSelAddOnInstrList() {
		return selAddOnInstrList;
	}

	/**
	 * @param selAddOnInstrList the selAddOnInstrList to set
	 */
	public void setSelAddOnInstrList(List<DBOBean> selAddOnInstrList) {
		this.selAddOnInstrList = selAddOnInstrList;
	}

	/**
	 * @return the addInstrTotCost
	 */
	public float getAddInstrTotCost() {
		return addInstrTotCost;
	}

	/**
	 * @param addInstrTotCost the addInstrTotCost to set
	 */
	public void setAddInstrTotCost(float addInstrTotCost) {
		this.addInstrTotCost = addInstrTotCost;
	}

	/**
	 * @return the mechAdditionalCost
	 */
	public float getMechAdditionalCost() {
		return mechAdditionalCost;
	}

	/**
	 * @param mechAdditionalCost the mechAdditionalCost to set
	 */
	public void setMechAdditionalCost(float mechAdditionalCost) {
		this.mechAdditionalCost = mechAdditionalCost;
	}

	/**
	 * @return the eleAdditionalCost
	 */
	public float getEleAdditionalCost() {
		return eleAdditionalCost;
	}

	/**
	 * @param eleAdditionalCost the eleAdditionalCost to set
	 */
	public void setEleAdditionalCost(float eleAdditionalCost) {
		this.eleAdditionalCost = eleAdditionalCost;
	}

	/**
	 * @return the dboEleCompOthersList
	 */
	public List<DBOBean> getDboEleCompOthersList() {
		return dboEleCompOthersList;
	}

	/**
	 * @param dboEleCompOthersList the dboEleCompOthersList to set
	 */
	public void setDboEleCompOthersList(List<DBOBean> dboEleCompOthersList) {
		this.dboEleCompOthersList = dboEleCompOthersList;
	}

	/**
	 * @return the dboEleAddOnColumns
	 */
	public List<DBOBean> getDboEleAddOnColumns() {
		return dboEleAddOnColumns;
	}

	/**
	 * @param dboEleAddOnColumns the dboEleAddOnColumns to set
	 */
	public void setDboEleAddOnColumns(List<DBOBean> dboEleAddOnColumns) {
		this.dboEleAddOnColumns = dboEleAddOnColumns;
	}

	/**
	 * @return the dboEleAddOnColData
	 */
	public List<DBOBean> getDboEleAddOnColData() {
		return dboEleAddOnColData;
	}

	/**
	 * @param dboEleAddOnColData the dboEleAddOnColData to set
	 */
	public void setDboEleAddOnColData(List<DBOBean> dboEleAddOnColData) {
		this.dboEleAddOnColData = dboEleAddOnColData;
	}

	/**
	 * @return the selectedDboEleAddOnColData
	 */
	public List<DBOBean> getSelectedDboEleAddOnColData() {
		return selectedDboEleAddOnColData;
	}

	/**
	 * @param selectedDboEleAddOnColData the selectedDboEleAddOnColData to set
	 */
	public void setSelectedDboEleAddOnColData(List<DBOBean> selectedDboEleAddOnColData) {
		this.selectedDboEleAddOnColData = selectedDboEleAddOnColData;
	}

	/**
	 * @return the basicCost
	 */
	public float getBasicCost() {
		return basicCost;
	}

	/**
	 * @param basicCost the basicCost to set
	 */
	public void setBasicCost(float basicCost) {
		this.basicCost = basicCost;
	}

	 
		public String getItemName() {
			return itemNm;
		}

		
		public void setItemName(String itemName) {
			this. itemNm=  itemName;
		}
		public void setSubItemId(int subItemId) {
			this.subItemId = subItemId;
		}
		public int getSubItemId() {
			return subItemId;
		}
		
		public void setDboQuotDetId(int dboQuotDetId) {
			this.dboQuotDetId = dboQuotDetId;
		}

		
		public int getDboQuotDetId() {
			
			return dboQuotDetId;
		}
		
		public void setDboQuotId(int dboQuotId) {
			this.dboQuotId = dboQuotId;
		}
		

		public int getDboQuotId() {
			
			return dboQuotId ;
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

		public String getTableName() {
			
			return tableName;
		}
		public void setTableName(String tableName) {
			this. tableName=  tableName;
		}

		/**
		 * @return the framePowId
		 */
		public int getFramePowId() {
			return framePowId;
		}

		/**
		 * @param framePowId the framePowId to set
		 */
		public void setFramePowId(int framePowId) {
			this.framePowId = framePowId;
		}

		/**
		 * @return the addOnList
		 */
		public List<AddOnComponent> getAddOnList() {
			return addOnList;
		}

		/**
		 * @param addOnList the addOnList to set
		 */
		public void setAddOnList(List<AddOnComponent> addOnList) {
			this.addOnList = addOnList;
		}

		

		/**
		 * @return the dboF2fItemList
		 */
		public List<DBOBean> getDboF2fItemList() {
			return dboF2fItemList;
		}

		/**
		 * @param dboF2fItemList the dboF2fItemList to set
		 */
		public void setDboF2fItemList(List<DBOBean> dboF2fItemList) {
			this.dboF2fItemList = dboF2fItemList;
		}

		/**
		 * @return the dboF2fPanelList1
		 */
		public List<DBOBean> getDboF2fPanelList1() {
			return dboF2fPanelList1;
		}

		/**
		 * @param dboF2fPanelList1 the dboF2fPanelList1 to set
		 */
		public void setDboF2fPanelList1(List<DBOBean> dboF2fPanelList1) {
			this.dboF2fPanelList1 = dboF2fPanelList1;
		}

		/**
		 * @return the dboF2fPanelList2
		 */
		public List<DBOBean> getDboF2fPanelList2() {
			return dboF2fPanelList2;
		}

		/**
		 * @param dboF2fPanelList2 the dboF2fPanelList2 to set
		 */
		public void setDboF2fPanelList2(List<DBOBean> dboF2fPanelList2) {
			this.dboF2fPanelList2 = dboF2fPanelList2;
		}

		/**
		 * @return the subItemTypeId
		 */
		public int getSubItemTypeId() {
			return subItemTypeId;
		}

		/**
		 * @param subItemTypeId the subItemTypeId to set
		 */
		public void setSubItemTypeId(int subItemTypeId) {
			this.subItemTypeId = subItemTypeId;
		}

		public List<DBOBean> getF2fTechData() {
			return F2fTechData;
		}

		public void setF2fTechData(List<DBOBean> f2fTechData) {
			F2fTechData = f2fTechData;
		}

		/**
		 * @return the f2fTechList
		 */
		public List<DBOBean> getF2fTechList() {
			return f2fTechList;
		}

		/**
		 * @param f2fTechList the f2fTechList to set
		 */
		public void setF2fTechList(List<DBOBean> f2fTechList) {
			this.f2fTechList = f2fTechList;
		}

		/**
		 * @return the panelCode
		 */
		public String getPanelCode() {
			return panelCode;
		}

		/**
		 * @param panelCode the panelCode to set
		 */
		public void setPanelCode(String panelCode) {
			this.panelCode = panelCode;
		}

		/**
		 * @return the selectedPriceFlag
		 */
		public Boolean getSelectedPriceFlag() {
			return selectedPriceFlag;
		}

		/**
		 * @param selectedPriceFlag the selectedPriceFlag to set
		 */
		public void setSelectedPriceFlag(Boolean selectedPriceFlag) {
			this.selectedPriceFlag = selectedPriceFlag;
		}

		/**
		 * @return the selectedCost
		 */
		public Float getSelectedCost() {
			return selectedCost;
		}

		/**
		 * @param selectedCost the selectedCost to set
		 */
		public void setSelectedCost(Float selectedCost) {
			this.selectedCost = selectedCost;
		}

		/**
		 * @return the savedF2fDataList
		 */
		public List<DBOBean> getSavedF2fDataList() {
			return savedF2fDataList;
		}

		/**
		 * @param savedF2fDataList the savedF2fDataList to set
		 */
		public void setSavedF2fDataList(List<DBOBean> savedF2fDataList) {
			this.savedF2fDataList = savedF2fDataList;
		}

		/**
		 * @return the itemIdList1
		 */
		public List<String> getItemIdList1() {
			return itemIdList1;
		}

		/**
		 * @param itemIdList1 the itemIdList1 to set
		 */
		public void setItemIdList1(List<String> itemIdList1) {
			this.itemIdList1 = itemIdList1;
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
		 * @return the dboF2fAddOnList1
		 */
		public List<DBOBean> getDboF2fAddOnList1() {
			return dboF2fAddOnList1;
		}

		/**
		 * @param dboF2fAddOnList1 the dboF2fAddOnList1 to set
		 */
		public void setDboF2fAddOnList1(List<DBOBean> dboF2fAddOnList1) {
			this.dboF2fAddOnList1 = dboF2fAddOnList1;
		}

		/**
		 * @return the dboF2fAddOnList2
		 */
		public List<DBOBean> getDboF2fAddOnList2() {
			return dboF2fAddOnList2;
		}

		/**
		 * @param dboF2fAddOnList2 the dboF2fAddOnList2 to set
		 */
		public void setDboF2fAddOnList2(List<DBOBean> dboF2fAddOnList2) {
			this.dboF2fAddOnList2 = dboF2fAddOnList2;
		}

		/**
		 * @return the f2fAddOnCost
		 */
		public float getF2fAddOnCost() {
			return f2fAddOnCost;
		}

		/**
		 * @param f2fAddOnCost the f2fAddOnCost to set
		 */
		public void setF2fAddOnCost(float f2fAddOnCost) {
			this.f2fAddOnCost = f2fAddOnCost;
		}

		/**
		 * @return the f2fAddOnList
		 */
		public List<DBOBean> getF2fAddOnList() {
			return f2fAddOnList;
		}

		/**
		 * @param f2fAddOnList the f2fAddOnList to set
		 */
		public void setF2fAddOnList(List<DBOBean> f2fAddOnList) {
			this.f2fAddOnList = f2fAddOnList;
		}

		/**
		 * @return the f2fSelectedPriceData
		 */
		public List<DBOBean> getF2fSelectedPriceData() {
			return f2fSelectedPriceData;
		}

		/**
		 * @param f2fSelectedPriceData the f2fSelectedPriceData to set
		 */
		public void setF2fSelectedPriceData(List<DBOBean> f2fSelectedPriceData) {
			this.f2fSelectedPriceData = f2fSelectedPriceData;
		}

		/**
		 * @return the f2fAddOnColumns
		 */
		public List<DBOBean> getF2fAddOnColumns() {
			return f2fAddOnColumns;
		}

		/**
		 * @param f2fAddOnColumns the f2fAddOnColumns to set
		 */
		public void setF2fAddOnColumns(List<DBOBean> f2fAddOnColumns) {
			this.f2fAddOnColumns = f2fAddOnColumns;
		}

		/**
		 * @return the f2fAddOnColData
		 */
		public List<DBOBean> getF2fAddOnColData() {
			return f2fAddOnColData;
		}

		/**
		 * @param f2fAddOnColData the f2fAddOnColData to set
		 */
		public void setF2fAddOnColData(List<DBOBean> f2fAddOnColData) {
			this.f2fAddOnColData = f2fAddOnColData;
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
		 * @return the glandSelType
		 */
		public int getGlandSelType() {
			return glandSelType;
		}

		/**
		 * @param glandSelType the glandSelType to set
		 */
		public void setGlandSelType(int glandSelType) {
			this.glandSelType = glandSelType;
		}

		/**
		 * @return the lubeOilType
		 */
		public int getLubeOilType() {
			return lubeOilType;
		}

		/**
		 * @param lubeOilType the lubeOilType to set
		 */
		public void setLubeOilType(int lubeOilType) {
			this.lubeOilType = lubeOilType;
		}

		/**
		 * @return the lubeOilSubItem
		 */
		public int getLubeOilSubItem() {
			return lubeOilSubItem;
		}

		/**
		 * @param lubeOilSubItem the lubeOilSubItem to set
		 */
		public void setLubeOilSubItem(int lubeOilSubItem) {
			this.lubeOilSubItem = lubeOilSubItem;
		}

		/**
		 * @return the groupCode
		 */
		public String getGroupCode() {
			return groupCode;
		}

		/**
		 * @param groupCode the groupCode to set
		 */
		public void setGroupCode(String groupCode) {
			this.groupCode = groupCode;
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
		 * @return the generalInputIdList
		 */
		public List<Integer> getGeneralInputIdList() {
			return generalInputIdList;
		}

		/**
		 * @param generalInputIdList the generalInputIdList to set
		 */
		public void setGeneralInputIdList(List<Integer> generalInputIdList) {
			this.generalInputIdList = generalInputIdList;
		}

		/**
		 * @return the dboF2fCacheList
		 */
		public List<DBOBean> getDboF2fCacheList() {
			return dboF2fCacheList;
		}

		/**
		 * @param dboF2fCacheList the dboF2fCacheList to set
		 */
		public void setDboF2fCacheList(List<DBOBean> dboF2fCacheList) {
			this.dboF2fCacheList = dboF2fCacheList;
		}

		/**
		 * @return the techRemarks
		 */
		public String getTechRemarks() {
			return techRemarks;
		}

		/**
		 * @param techRemarks the techRemarks to set
		 */
		public void setTechRemarks(String techRemarks) {
			this.techRemarks = techRemarks;
		}

		/**
		 * @return the comrRemarks
		 */
		public String getComrRemarks() {
			return comrRemarks;
		}

		/**
		 * @param comrRemarks the comrRemarks to set
		 */
		public void setComrRemarks(String comrRemarks) {
			this.comrRemarks = comrRemarks;
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
		 * @return the subItemIdList
		 */
		public List<Integer> getSubItemIdList() {
			return subItemIdList;
		}

		/**
		 * @param subItemIdList the subItemIdList to set
		 */
		public void setSubItemIdList(List<Integer> subItemIdList) {
			this.subItemIdList = subItemIdList;
		}

		/**
		 * @return the savedF2fSubDataList
		 */
		public List<DBOBean> getSavedF2fSubDataList() {
			return savedF2fSubDataList;
		}

		/**
		 * @param savedF2fSubDataList the savedF2fSubDataList to set
		 */
		public void setSavedF2fSubDataList(List<DBOBean> savedF2fSubDataList) {
			this.savedF2fSubDataList = savedF2fSubDataList;
		}

		/**
		 * @return the subItemTypeIdList
		 */
		public List<Integer> getSubItemTypeIdList() {
			return subItemTypeIdList;
		}

		/**
		 * @param subItemTypeIdList the subItemTypeIdList to set
		 */
		public void setSubItemTypeIdList(List<Integer> subItemTypeIdList) {
			this.subItemTypeIdList = subItemTypeIdList;
		}

		/**
		 * @return the subTypeItemIdList
		 */
		public List<Integer> getSubTypeItemIdList() {
			return subTypeItemIdList;
		}

		/**
		 * @param subTypeItemIdList the subTypeItemIdList to set
		 */
		public void setSubTypeItemIdList(List<Integer> subTypeItemIdList) {
			this.subTypeItemIdList = subTypeItemIdList;
		}

		/**
		 * @return the savedF2fSubTypeDataList
		 */
		public List<DBOBean> getSavedF2fSubTypeDataList() {
			return savedF2fSubTypeDataList;
		}

		/**
		 * @param savedF2fSubTypeDataList the savedF2fSubTypeDataList to set
		 */
		public void setSavedF2fSubTypeDataList(List<DBOBean> savedF2fSubTypeDataList) {
			this.savedF2fSubTypeDataList = savedF2fSubTypeDataList;
		}

		/**
		 * @return the itemTechRemarks
		 */
		public String getItemTechRemarks() {
			return itemTechRemarks;
		}

		/**
		 * @param itemTechRemarks the itemTechRemarks to set
		 */
		public void setItemTechRemarks(String itemTechRemarks) {
			this.itemTechRemarks = itemTechRemarks;
		}

		/**
		 * @return the itemComrRemarks
		 */
		public String getItemComrRemarks() {
			return itemComrRemarks;
		}

		/**
		 * @param itemComrRemarks the itemComrRemarks to set
		 */
		public void setItemComrRemarks(String itemComrRemarks) {
			this.itemComrRemarks = itemComrRemarks;
		}

		/**
		 * @return the subItemTechRemarks
		 */
		public String getSubItemTechRemarks() {
			return subItemTechRemarks;
		}

		/**
		 * @param subItemTechRemarks the subItemTechRemarks to set
		 */
		public void setSubItemTechRemarks(String subItemTechRemarks) {
			this.subItemTechRemarks = subItemTechRemarks;
		}

		/**
		 * @return the subItemComrRemarks
		 */
		public String getSubItemComrRemarks() {
			return subItemComrRemarks;
		}

		/**
		 * @param subItemComrRemarks the subItemComrRemarks to set
		 */
		public void setSubItemComrRemarks(String subItemComrRemarks) {
			this.subItemComrRemarks = subItemComrRemarks;
		}

		/**
		 * @return the subItemTypeTechRemarks
		 */
		public String getSubItemTypeTechRemarks() {
			return subItemTypeTechRemarks;
		}

		/**
		 * @param subItemTypeTechRemarks the subItemTypeTechRemarks to set
		 */
		public void setSubItemTypeTechRemarks(String subItemTypeTechRemarks) {
			this.subItemTypeTechRemarks = subItemTypeTechRemarks;
		}

		/**
		 * @return the subItemTypeComrRemarks
		 */
		public String getSubItemTypeComrRemarks() {
			return subItemTypeComrRemarks;
		}

		/**
		 * @param subItemTypeComrRemarks the subItemTypeComrRemarks to set
		 */
		public void setSubItemTypeComrRemarks(String subItemTypeComrRemarks) {
			this.subItemTypeComrRemarks = subItemTypeComrRemarks;
		}

		/**
		 * @return the dboMechItemList
		 */
		public List<DBOBean> getDboMechItemList() {
			return dboMechItemList;
		}

		/**
		 * @param dboMechItemList the dboMechItemList to set
		 */
		public void setDboMechItemList(List<DBOBean> dboMechItemList) {
			this.dboMechItemList = dboMechItemList;
		}

		/**
		 * @return the dboMechPanelList1
		 */
		public List<DBOBean> getDboMechPanelList1() {
			return dboMechPanelList1;
		}

		/**
		 * @param dboMechPanelList1 the dboMechPanelList1 to set
		 */
		public void setDboMechPanelList1(List<DBOBean> dboMechPanelList1) {
			this.dboMechPanelList1 = dboMechPanelList1;
		}

		/**
		 * @return the dboMechPanelList2
		 */
		public List<DBOBean> getDboMechPanelList2() {
			return dboMechPanelList2;
		}

		/**
		 * @param dboMechPanelList2 the dboMechPanelList2 to set
		 */
		public void setDboMechPanelList2(List<DBOBean> dboMechPanelList2) {
			this.dboMechPanelList2 = dboMechPanelList2;
		}

		/**
		 * @return the flowRate
		 */
		public float getFlowRate() {
			return flowRate;
		}

		/**
		 * @param flowRate the flowRate to set
		 */
		public void setFlowRate(float flowRate) {
			this.flowRate = flowRate;
		}

		/**
		 * @return the mechTechList
		 */
		public List<DBOBean> getMechTechList() {
			return MechTechList;
		}

		/**
		 * @param mechTechList the mechTechList to set
		 */
		public void setMechTechList(List<DBOBean> mechTechList) {
			MechTechList = mechTechList;
		}

		/**
		 * @return the mechItemTechRemarks
		 */
		public String getMechItemTechRemarks() {
			return mechItemTechRemarks;
		}

		/**
		 * @param mechItemTechRemarks the mechItemTechRemarks to set
		 */
		public void setMechItemTechRemarks(String mechItemTechRemarks) {
			this.mechItemTechRemarks = mechItemTechRemarks;
		}

		/**
		 * @return the mechItemComrRemarks
		 */
		public String getMechItemComrRemarks() {
			return mechItemComrRemarks;
		}

		/**
		 * @param mechItemComrRemarks the mechItemComrRemarks to set
		 */
		public void setMechItemComrRemarks(String mechItemComrRemarks) {
			this.mechItemComrRemarks = mechItemComrRemarks;
		}

		/**
		 * @return the mechTechData
		 */
		public List<DBOBean> getMechTechData() {
			return MechTechData;
		}

		/**
		 * @param mechTechData the mechTechData to set
		 */
		public void setMechTechData(List<DBOBean> mechTechData) {
			MechTechData = mechTechData;
		}

		/**
		 * @return the mechAddOnColumns
		 */
		public List<DBOBean> getMechAddOnColumns() {
			return mechAddOnColumns;
		}

		/**
		 * @param mechAddOnColumns the mechAddOnColumns to set
		 */
		public void setMechAddOnColumns(List<DBOBean> mechAddOnColumns) {
			this.mechAddOnColumns = mechAddOnColumns;
		}

		/**
		 * @return the mechAddOnColData
		 */
		public List<DBOBean> getMechAddOnColData() {
			return mechAddOnColData;
		}

		/**
		 * @param mechAddOnColData the mechAddOnColData to set
		 */
		public void setMechAddOnColData(List<DBOBean> mechAddOnColData) {
			this.mechAddOnColData = mechAddOnColData;
		}

		/**
		 * @return the mechAddOnList
		 */
		public List<DBOBean> getMechAddOnList() {
			return mechAddOnList;
		}

		/**
		 * @param mechAddOnList the mechAddOnList to set
		 */
		public void setMechAddOnList(List<DBOBean> mechAddOnList) {
			this.mechAddOnList = mechAddOnList;
		}

		/**
		 * @return the mechSelectedPriceData
		 */
		public List<DBOBean> getMechSelectedPriceData() {
			return mechSelectedPriceData;
		}

		/**
		 * @param mechSelectedPriceData the mechSelectedPriceData to set
		 */
		public void setMechSelectedPriceData(List<DBOBean> mechSelectedPriceData) {
			this.mechSelectedPriceData = mechSelectedPriceData;
		}

		/**
		 * @return the mechAddOnCost
		 */
		public int getMechAddOnCost() {
			return mechAddOnCost;
		}

		/**
		 * @param mechAddOnCost the mechAddOnCost to set
		 */
		public void setMechAddOnCost(int mechAddOnCost) {
			this.mechAddOnCost = mechAddOnCost;
		}

		/**
		 * @return the custType
		 */
		public String getCustType() {
			return custType;
		}

		/**
		 * @param custType the custType to set
		 */
		public void setCustType(String custType) {
			this.custType = custType;
		}

		/**
		 * @return the mechItemIdList
		 */
		public List<Integer> getMechItemIdList() {
			return mechItemIdList;
		}

		/**
		 * @param mechItemIdList the mechItemIdList to set
		 */
		public void setMechItemIdList(List<Integer> mechItemIdList) {
			this.mechItemIdList = mechItemIdList;
		}

		/**
		 * @return the mechSubItemIdList
		 */
		public List<Integer> getMechSubItemIdList() {
			return mechSubItemIdList;
		}

		/**
		 * @param mechSubItemIdList the mechSubItemIdList to set
		 */
		public void setMechSubItemIdList(List<Integer> mechSubItemIdList) {
			this.mechSubItemIdList = mechSubItemIdList;
		}

		/**
		 * @return the dboMechAuxItemList
		 */
		public List<DBOBean> getDboMechAuxItemList() {
			return dboMechAuxItemList;
		}

		/**
		 * @param dboMechAuxItemList the dboMechAuxItemList to set
		 */
		public void setDboMechAuxItemList(List<DBOBean> dboMechAuxItemList) {
			this.dboMechAuxItemList = dboMechAuxItemList;
		}

		/**
		 * @return the dboMechAuxPanelList1
		 */
		public List<DBOBean> getDboMechAuxPanelList1() {
			return dboMechAuxPanelList1;
		}

		/**
		 * @param dboMechAuxPanelList1 the dboMechAuxPanelList1 to set
		 */
		public void setDboMechAuxPanelList1(List<DBOBean> dboMechAuxPanelList1) {
			this.dboMechAuxPanelList1 = dboMechAuxPanelList1;
		}

		/**
		 * @return the dboMechAuxPanelList2
		 */
		public List<DBOBean> getDboMechAuxPanelList2() {
			return dboMechAuxPanelList2;
		}

		/**
		 * @param dboMechAuxPanelList2 the dboMechAuxPanelList2 to set
		 */
		public void setDboMechAuxPanelList2(List<DBOBean> dboMechAuxPanelList2) {
			this.dboMechAuxPanelList2 = dboMechAuxPanelList2;
		}

		/**
		 * @return the mechAuxTechData
		 */
		public List<DBOBean> getMechAuxTechData() {
			return MechAuxTechData;
		}

		/**
		 * @param mechAuxTechData the mechAuxTechData to set
		 */
		public void setMechAuxTechData(List<DBOBean> mechAuxTechData) {
			MechAuxTechData = mechAuxTechData;
		}

		
		/**
		 * @return the mechAuxAddOnCost
		 */
		public float getMechAuxAddOnCost() {
			return mechAuxAddOnCost;
		}

		/**
		 * @param mechAuxAddOnCost the mechAuxAddOnCost to set
		 */
		public void setMechAuxAddOnCost(float mechAuxAddOnCost) {
			this.mechAuxAddOnCost = mechAuxAddOnCost;
		}

		/**
		 * @return the mechAuxTechList
		 */
		public List<DBOBean> getMechAuxTechList() {
			return mechAuxTechList;
		}

		/**
		 * @param mechAuxTechList the mechAuxTechList to set
		 */
		public void setMechAuxTechList(List<DBOBean> mechAuxTechList) {
			this.mechAuxTechList = mechAuxTechList;
		}

		/**
		 * @return the mechAuxItemTechRemarks
		 */
		public String getMechAuxItemTechRemarks() {
			return mechAuxItemTechRemarks;
		}

		/**
		 * @param mechAuxItemTechRemarks the mechAuxItemTechRemarks to set
		 */
		public void setMechAuxItemTechRemarks(String mechAuxItemTechRemarks) {
			this.mechAuxItemTechRemarks = mechAuxItemTechRemarks;
		}

		/**
		 * @return the mechAuxItemComrRemarks
		 */
		public String getMechAuxItemComrRemarks() {
			return mechAuxItemComrRemarks;
		}

		/**
		 * @param mechAuxItemComrRemarks the mechAuxItemComrRemarks to set
		 */
		public void setMechAuxItemComrRemarks(String mechAuxItemComrRemarks) {
			this.mechAuxItemComrRemarks = mechAuxItemComrRemarks;
		}

		/**
		 * @return the mechAuxItemIdList
		 */
		public List<Integer> getMechAuxItemIdList() {
			return mechAuxItemIdList;
		}

		/**
		 * @param mechAuxItemIdList the mechAuxItemIdList to set
		 */
		public void setMechAuxItemIdList(List<Integer> mechAuxItemIdList) {
			this.mechAuxItemIdList = mechAuxItemIdList;
		}

		/**
		 * @return the mechAuxilaryTechData
		 */
		public List<DBOBean> getMechAuxilaryTechData() {
			return MechAuxilaryTechData;
		}

		/**
		 * @param mechAuxilaryTechData the mechAuxilaryTechData to set
		 */
		public void setMechAuxilaryTechData(List<DBOBean> mechAuxilaryTechData) {
			MechAuxilaryTechData = mechAuxilaryTechData;
		}

		/**
		 * @return the savedMechList
		 */
		public List<DBOBean> getSavedMechList() {
			return savedMechList;
		}

		/**
		 * @param savedMechList the savedMechList to set
		 */
		public void setSavedMechList(List<DBOBean> savedMechList) {
			this.savedMechList = savedMechList;
		}

		/**
		 * @return the saveMechSubItemList
		 */
		public List<DBOBean> getSaveMechSubItemList() {
			return saveMechSubItemList;
		}

		/**
		 * @param saveMechSubItemList the saveMechSubItemList to set
		 */
		public void setSaveMechSubItemList(List<DBOBean> saveMechSubItemList) {
			this.saveMechSubItemList = saveMechSubItemList;
		}

		/**
		 * @return the mechExtScopeData
		 */
		public List<DBOBean> getMechExtScopeData() {
			return MechExtScopeData;
		}

		/**
		 * @param mechExtScopeData the mechExtScopeData to set
		 */
		public void setMechExtScopeData(List<DBOBean> mechExtScopeData) {
			MechExtScopeData = mechExtScopeData;
		}

		/**
		 * @return the mechExtScopeTechComments
		 */
		public String getMechExtScopeTechComments() {
			return MechExtScopeTechComments;
		}

		/**
		 * @param mechExtScopeTechComments the mechExtScopeTechComments to set
		 */
		public void setMechExtScopeTechComments(String mechExtScopeTechComments) {
			MechExtScopeTechComments = mechExtScopeTechComments;
		}

		/**
		 * @return the mechExtScopeComrComments
		 */
		public String getMechExtScopeComrComments() {
			return MechExtScopeComrComments;
		}

		/**
		 * @param mechExtScopeComrComments the mechExtScopeComrComments to set
		 */
		public void setMechExtScopeComrComments(String mechExtScopeComrComments) {
			MechExtScopeComrComments = mechExtScopeComrComments;
		}

		/**
		 * @return the f2fCacheListNew
		 */
		public List<DBOBean> getF2fCacheListNew() {
			return f2fCacheListNew;
		}

		/**
		 * @param f2fCacheListNew the f2fCacheListNew to set
		 */
		public void setF2fCacheListNew(List<DBOBean> f2fCacheListNew) {
			this.f2fCacheListNew = f2fCacheListNew;
		}

		/**
		 * @return the savedGeneralInputList
		 */
		public List<DBOBean> getSavedGeneralInputList() {
			return savedGeneralInputList;
		}

		/**
		 * @param savedGeneralInputList the savedGeneralInputList to set
		 */
		public void setSavedGeneralInputList(List<DBOBean> savedGeneralInputList) {
			this.savedGeneralInputList = savedGeneralInputList;
		}

		/**
		 * @return the saveGeneralInputList
		 */
		public List<DBOBean> getSaveGeneralInputList() {
			return saveGeneralInputList;
		}

		/**
		 * @param saveGeneralInputList the saveGeneralInputList to set
		 */
		public void setSaveGeneralInputList(List<DBOBean> saveGeneralInputList) {
			this.saveGeneralInputList = saveGeneralInputList;
		}

		/**
		 * @return the dboEleFilterList
		 */
		public List<DBOBean> getDboEleFilterList() {
			return dboEleFilterList;
		}

		/**
		 * @param dboEleFilterList the dboEleFilterList to set
		 */
		public void setDboEleFilterList(List<DBOBean> dboEleFilterList) {
			this.dboEleFilterList = dboEleFilterList;
		}

		/**
		 * @return the dboEleItemList
		 */
		public List<DBOBean> getDboEleItemList() {
			return dboEleItemList;
		}

		/**
		 * @param dboEleItemList the dboEleItemList to set
		 */
		public void setDboEleItemList(List<DBOBean> dboEleItemList) {
			this.dboEleItemList = dboEleItemList;
		}

		/**
		 * @return the dboElePanelList1
		 */
		public List<DBOBean> getDboElePanelList1() {
			return dboElePanelList1;
		}

		/**
		 * @param dboElePanelList1 the dboElePanelList1 to set
		 */
		public void setDboElePanelList1(List<DBOBean> dboElePanelList1) {
			this.dboElePanelList1 = dboElePanelList1;
		}

		/**
		 * @return the dboElePanelList2
		 */
		public List<DBOBean> getDboElePanelList2() {
			return dboElePanelList2;
		}

		/**
		 * @param dboElePanelList2 the dboElePanelList2 to set
		 */
		public void setDboElePanelList2(List<DBOBean> dboElePanelList2) {
			this.dboElePanelList2 = dboElePanelList2;
		}

		/**
		 * @return the eleTechList
		 */
		public List<DBOBean> getEleTechList() {
			return eleTechList;
		}

		/**
		 * @param eleTechList the eleTechList to set
		 */
		public void setEleTechList(List<DBOBean> eleTechList) {
			this.eleTechList = eleTechList;
		}

		/**
		 * @return the eleAddOnCost
		 */
		public float getEleAddOnCost() {
			return eleAddOnCost;
		}

		/**
		 * @param eleAddOnCost the eleAddOnCost to set
		 */
		public void setEleAddOnCost(float eleAddOnCost) {
			this.eleAddOnCost = eleAddOnCost;
		}

		/**
		 * @return the eleItemTechRemarks
		 */
		public String getEleItemTechRemarks() {
			return eleItemTechRemarks;
		}

		/**
		 * @param eleItemTechRemarks the eleItemTechRemarks to set
		 */
		public void setEleItemTechRemarks(String eleItemTechRemarks) {
			this.eleItemTechRemarks = eleItemTechRemarks;
		}

		/**
		 * @return the eleItemComrRemarks
		 */
		public String getEleItemComrRemarks() {
			return eleItemComrRemarks;
		}

		/**
		 * @param eleItemComrRemarks the eleItemComrRemarks to set
		 */
		public void setEleItemComrRemarks(String eleItemComrRemarks) {
			this.eleItemComrRemarks = eleItemComrRemarks;
		}

		/**
		 * @return the eleTechData
		 */
		public List<DBOBean> getEleTechData() {
			return EleTechData;
		}

		/**
		 * @param eleTechData the eleTechData to set
		 */
		public void setEleTechData(List<DBOBean> eleTechData) {
			EleTechData = eleTechData;
		}

		/**
		 * @return the savedGeneralEleFliterInputList
		 */
		public List<DBOBean> getSavedGeneralEleFliterInputList() {
			return savedGeneralEleFliterInputList;
		}

		/**
		 * @param savedGeneralEleFliterInputList the savedGeneralEleFliterInputList to set
		 */
		public void setSavedGeneralEleFliterInputList(List<DBOBean> savedGeneralEleFliterInputList) {
			this.savedGeneralEleFliterInputList = savedGeneralEleFliterInputList;
		}

		/**
		 * @return the f2FAddOnCost
		 */
		public float getF2FAddOnCost() {
			return f2FAddOnCost;
		}

		/**
		 * @param f2fAddOnCost the f2FAddOnCost to set
		 */
		public void setF2FAddOnCost(float f2fAddOnCost) {
			f2FAddOnCost = f2fAddOnCost;
		}

		/**
		 * @return the f2fItemTechRemarks
		 */
		public String getF2fItemTechRemarks() {
			return f2fItemTechRemarks;
		}

		/**
		 * @param f2fItemTechRemarks the f2fItemTechRemarks to set
		 */
		public void setF2fItemTechRemarks(String f2fItemTechRemarks) {
			this.f2fItemTechRemarks = f2fItemTechRemarks;
		}

		/**
		 * @return the f2fItemComrRemarks
		 */
		public String getF2fItemComrRemarks() {
			return f2fItemComrRemarks;
		}

		/**
		 * @param f2fItemComrRemarks the f2fItemComrRemarks to set
		 */
		public void setF2fItemComrRemarks(String f2fItemComrRemarks) {
			this.f2fItemComrRemarks = f2fItemComrRemarks;
		}

		/**
		 * @return the f2FBasicCost
		 */
		public float getF2FBasicCost() {
			return f2FBasicCost;
		}

		/**
		 * @param f2fBasicCost the f2FBasicCost to set
		 */
		public void setF2FBasicCost(float f2fBasicCost) {
			f2FBasicCost = f2fBasicCost;
		}

		/**
		 * @return the f2fItemTechComments
		 */
		public String getF2fItemTechComments() {
			return f2fItemTechComments;
		}

		/**
		 * @param f2fItemTechComments the f2fItemTechComments to set
		 */
		public void setF2fItemTechComments(String f2fItemTechComments) {
			this.f2fItemTechComments = f2fItemTechComments;
		}

		/**
		 * @return the f2fItemComrComments
		 */
		public String getF2fItemComrComments() {
			return f2fItemComrComments;
		}

		/**
		 * @param f2fItemComrComments the f2fItemComrComments to set
		 */
		public void setF2fItemComrComments(String f2fItemComrComments) {
			this.f2fItemComrComments = f2fItemComrComments;
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
		 * @return the saveF2fList
		 */
		public List<DBOBean> getSaveF2fList() {
			return saveF2fList;
		}

		/**
		 * @param saveF2fList the saveF2fList to set
		 */
		public void setSaveF2fList(List<DBOBean> saveF2fList) {
			this.saveF2fList = saveF2fList;
		}

		/**
		 * @return the saveF2fSubList
		 */
		public List<DBOBean> getSaveF2fSubList() {
			return saveF2fSubList;
		}

		/**
		 * @param saveF2fSubList the saveF2fSubList to set
		 */
		public void setSaveF2fSubList(List<DBOBean> saveF2fSubList) {
			this.saveF2fSubList = saveF2fSubList;
		}

		/**
		 * @return the saveF2fSubTypeList
		 */
		public List<DBOBean> getSaveF2fSubTypeList() {
			return saveF2fSubTypeList;
		}

		/**
		 * @param saveF2fSubTypeList the saveF2fSubTypeList to set
		 */
		public void setSaveF2fSubTypeList(List<DBOBean> saveF2fSubTypeList) {
			this.saveF2fSubTypeList = saveF2fSubTypeList;
		}

		/**
		 * @return the mechAuxTotalPrice
		 */
		public float getMechAuxTotalPrice() {
			return mechAuxTotalPrice;
		}

		/**
		 * @param mechAuxTotalPrice the mechAuxTotalPrice to set
		 */
		public void setMechAuxTotalPrice(float mechAuxTotalPrice) {
			this.mechAuxTotalPrice = mechAuxTotalPrice;
		}

		/**
		 * @return the saveMechAuxList
		 */
		public List<DBOBean> getSaveMechAuxList() {
			return saveMechAuxList;
		}

		/**
		 * @param saveMechAuxList the saveMechAuxList to set
		 */
		public void setSaveMechAuxList(List<DBOBean> saveMechAuxList) {
			this.saveMechAuxList = saveMechAuxList;
		}

		/**
		 * @return the saveMechExtScopeList
		 */
		public List<DBOBean> getSaveMechExtScopeList() {
			return saveMechExtScopeList;
		}

		/**
		 * @param saveMechExtScopeList the saveMechExtScopeList to set
		 */
		public void setSaveMechExtScopeList(List<DBOBean> saveMechExtScopeList) {
			this.saveMechExtScopeList = saveMechExtScopeList;
		}

		/**
		 * @return the totalPrice
		 */
		public float getTotalPrice() {
			return totalPrice;
		}

		/**
		 * @param totalPrice the totalPrice to set
		 */
		public void setTotalPrice(float totalPrice) {
			this.totalPrice = totalPrice;
		}

		/**
		 * @return the eleRefreshTechList
		 */
		public List<DBOBean> getEleRefreshTechList() {
			return eleRefreshTechList;
		}

		/**
		 * @param eleRefreshTechList the eleRefreshTechList to set
		 */
		public void setEleRefreshTechList(List<DBOBean> eleRefreshTechList) {
			this.eleRefreshTechList = eleRefreshTechList;
		}

		/**
		 * @return the eleRefreshTechData
		 */
		public List<DBOBean> getEleRefreshTechData() {
			return eleRefreshTechData;
		}

		/**
		 * @param eleRefreshTechData the eleRefreshTechData to set
		 */
		public void setEleRefreshTechData(List<DBOBean> eleRefreshTechData) {
			this.eleRefreshTechData = eleRefreshTechData;
		}

		/**
		 * @return the saveEleList
		 */
		public List<DBOBean> getSaveEleList() {
			return saveEleList;
		}

		/**
		 * @param saveEleList the saveEleList to set
		 */
		public void setSaveEleList(List<DBOBean> saveEleList) {
			this.saveEleList = saveEleList;
		}

		/**
		 * @return the eleItemTechComments
		 */
		public String getEleItemTechComments() {
			return eleItemTechComments;
		}

		/**
		 * @param eleItemTechComments the eleItemTechComments to set
		 */
		public void setEleItemTechComments(String eleItemTechComments) {
			this.eleItemTechComments = eleItemTechComments;
		}

		/**
		 * @return the eleItemComrComments
		 */
		public String getEleItemComrComments() {
			return eleItemComrComments;
		}

		/**
		 * @param eleItemComrComments the eleItemComrComments to set
		 */
		public void setEleItemComrComments(String eleItemComrComments) {
			this.eleItemComrComments = eleItemComrComments;
		}

		/**
		 * @return the savedEleDataList
		 */
		public List<DBOBean> getSavedEleDataList() {
			return savedEleDataList;
		}

		/**
		 * @param savedEleDataList the savedEleDataList to set
		 */
		public void setSavedEleDataList(List<DBOBean> savedEleDataList) {
			this.savedEleDataList = savedEleDataList;
		}

		/**
		 * @return the eleItemIdList
		 */
		public List<Integer> getEleItemIdList() {
			return eleItemIdList;
		}

		/**
		 * @param eleItemIdList the eleItemIdList to set
		 */
		public void setEleItemIdList(List<Integer> eleItemIdList) {
			this.eleItemIdList = eleItemIdList;
		}

		/**
		 * @return the saveEleSpecialFilterList
		 */
		public List<DBOBean> getSaveEleSpecialFilterList() {
			return saveEleSpecialFilterList;
		}

		/**
		 * @param saveEleSpecialFilterList the saveEleSpecialFilterList to set
		 */
		public void setSaveEleSpecialFilterList(List<DBOBean> saveEleSpecialFilterList) {
			this.saveEleSpecialFilterList = saveEleSpecialFilterList;
		}

		/**
		 * @return the eleSpecialId
		 */
		public int getEleSpecialId() {
			return eleSpecialId;
		}

		/**
		 * @param eleSpecialId the eleSpecialId to set
		 */
		public void setEleSpecialId(int eleSpecialId) {
			this.eleSpecialId = eleSpecialId;
		}

		/**
		 * @return the colValCd
		 */
		public String getColValCd() {
			return colValCd;
		}

		/**
		 * @param colValCd the colValCd to set
		 */
		public void setColValCd(String colValCd) {
			this.colValCd = colValCd;
		}

		/**
		 * @return the itemApplInd
		 */
		public int getItemApplInd() {
			return itemApplInd;
		}

		/**
		 * @param itemApplInd the itemApplInd to set
		 */
		public void setItemApplInd(int itemApplInd) {
			this.itemApplInd = itemApplInd;
		}

		/**
		 * @return the dboEleSpecialFilterList
		 */
		public List<DBOBean> getDboEleSpecialFilterList() {
			return dboEleSpecialFilterList;
		}

		/**
		 * @param dboEleSpecialFilterList the dboEleSpecialFilterList to set
		 */
		public void setDboEleSpecialFilterList(List<DBOBean> dboEleSpecialFilterList) {
			this.dboEleSpecialFilterList = dboEleSpecialFilterList;
		}

		public List<DBOBean> getEleExtScopeData() {
			return EleExtScopeData;
		}

		public void setEleExtScopeData(List<DBOBean> eleExtScopeData) {
			EleExtScopeData = eleExtScopeData;
		}

		public String getEleExtScopeTechComments() {
			return eleExtScopeTechComments;
		}

		public void setEleExtScopeTechComments(String eleExtScopeTechComments) {
			this.eleExtScopeTechComments = eleExtScopeTechComments;
		}

		public String getEleExtScopeComrComments() {
			return eleExtScopeComrComments;
		}

		public void setEleExtScopeComrComments(String eleExtScopeComrComments) {
			this.eleExtScopeComrComments = eleExtScopeComrComments;
		}

		public List<DBOBean> getSaveEleExtScopeList() {
			return saveEleExtScopeList;
		}

		public void setSaveEleExtScopeList(List<DBOBean> saveEleExtScopeList) {
			this.saveEleExtScopeList = saveEleExtScopeList;
		}

		/**
		 * @return the grpCd
		 */
		List<DBOBean> updateF2fShopConv= new ArrayList<>();
		public List<DBOBean> getUpdateF2fShopConv() {
			return updateF2fShopConv;
		}

		public void setUpdateF2fShopConv(List<DBOBean> updateF2fShopConv) {
			this.updateF2fShopConv = updateF2fShopConv;
		}

	

		
	
}
