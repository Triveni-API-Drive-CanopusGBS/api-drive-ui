package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ttl.ito.internal.beans.ItoConstants;

@Component
public class ReportBean {
	private String sectionCd;
	public String getSectionCd() {
		return sectionCd;
	}
	public void setSectionCd(String sectionCd) {
		this.sectionCd = sectionCd;
	}
	public String getSubItemTypeCd() {
		return subItemTypeCd;
	}
	public void setSubItemTypeCd(String subItemTypeCd) {
		this.subItemTypeCd = subItemTypeCd;
	}
	private String turbGeneratorMw;
	public String getTurbGeneratorMw() {
		return turbGeneratorMw;
	}
	public void setTurbGeneratorMw(String turbGeneratorMw) {
		this.turbGeneratorMw = turbGeneratorMw;
	}
	private String delivery;
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int  slNum;
	public int getSlNum() {
		return slNum;
	}
	public void setSlNum(int slNum) {
		this.slNum = slNum;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	private String fileName;
	private String subItemTypeCd;
	private String deptName;
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEnquiryReference() {
		return enquiryReference;
	}
	public void setEnquiryReference(String enquiryReference) {
		this.enquiryReference = enquiryReference;
	}
	public String getOppContactAddress() {
		return oppContactAddress;
	}
	public void setOppContactAddress(String oppContactAddress) {
		this.oppContactAddress = oppContactAddress;
	}
	private String itemDesc;
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	private String  colIdNew;
	public String getColIdNew() {
		return colIdNew;
	}
	public void setColIdNew(String colIdNew) {
		this.colIdNew = colIdNew;
	}
	private String  slNo;
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	private String designation;
	private String userName;
	private String enquiryReference;
	private String oppContactAddress;
	private String typeOfChargeName;
	public String getTypeOfChargeName() {
		return typeOfChargeName;
	}
	public void setTypeOfChargeName(String typeOfChargeName) {
		this.typeOfChargeName = typeOfChargeName;
	}
	public int getLoadgingId() {
		return loadgingId;
	}
	public void setLoadgingId(int loadgingId) {
		this.loadgingId = loadgingId;
	}
	public String getLoadgingName() {
		return loadgingName;
	}
	public void setLoadgingName(String loadgingName) {
		this.loadgingName = loadgingName;
	}
	public String getNumberOfMandays() {
		return numberOfMandays;
	}
	public void setNumberOfMandays(String numberOfMandays) {
		this.numberOfMandays = numberOfMandays;
	}
	private int loadgingId;
	private String loadgingName;
	private String numberOfMandays;
	
	private String typeOfOff;
	public String getTypeOfOff() {
		return typeOfOff;
	}
	public void setTypeOfOff(String typeOfOff) {
		this.typeOfOff = typeOfOff;
	}
	private String custSpace;
	public String getCustSpace() {
		return custSpace;
	}
	public void setCustSpace(String custSpace) {
		this.custSpace = custSpace;
	}
	private String item;
	private String fixedText6;
	
	public String getFixedText6() {
		return fixedText6;
	}
	public void setFixedText6(String fixedText6) {
		this.fixedText6 = fixedText6;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	private int desItemOrderId;
	public int getDesItemOrderId() {
		return desItemOrderId;
	}
	public void setDesItemOrderId(int desItemOrderId) {
		this.desItemOrderId = desItemOrderId;
	}
	private String filepath;
	List<ReportBean> itemList = new ArrayList<>();
	private String itemNewName;
	public String getItemNewName() {
		return itemNewName;
	}
	public void setItemNewName(String itemNewName) {
		this.itemNewName = itemNewName;
	}
	public List<ReportBean> getItemList() {
		return itemList;
	}
	public void setItemList(List<ReportBean> itemList) {
		this.itemList = itemList;
	}

	private int newId;
	public int getNewId() {
		return newId;
	}
	public void setNewId(int newId) {
		this.newId = newId;
	}
	public String getNewNm() {
		return newNm;
	}
	public void setNewNm(String newNm) {
		this.newNm = newNm;
	}

	private String newNm;
	private String itemNm;
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public String getSubItemNm() {
		return subItemNm;
	}
	public void setSubItemNm(String subItemNm) {
		this.subItemNm = subItemNm;
	}
	public String getConsumerId1() {
		return consumerId1;
	}
	public void setConsumerId1(String consumerId1) {
		this.consumerId1 = consumerId1;
	}
	public String getStartUp() {
		return startUp;
	}
	public void setStartUp(String startUp) {
		this.startUp = startUp;
	}
	public String getContinuous() {
		return continuous;
	}
	public void setContinuous(String continuous) {
		this.continuous = continuous;
	}
	public int getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(int editFlag) {
		this.editFlag = editFlag;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemCd() {
		return itemCd;
	}
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getFeeder() {
		return feeder;
	}
	public void setFeeder(String feeder) {
		this.feeder = feeder;
	}

	private String subItemNm;
	private String consumerId1;
	private String startUp;
	private String continuous;
	private int editFlag;
	private String itemType;
	private String itemCd;
	private String speed;
	private String voltage;
	private String feeder;
	private int paramId;
	public int getParamId() {
		return paramId;
	}
	public void setParamId(int paramId) {
		this.paramId = paramId;
	}
	public String getParamNm() {
		return paramNm;
	}
	public void setParamNm(String paramNm) {
		this.paramNm = paramNm;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getUnitNm() {
		return unitNm;
	}
	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}
	public String getGuaranteed() {
		return guaranteed;
	}
	public void setGuaranteed(String guaranteed) {
		this.guaranteed = guaranteed;
	}
	public int getCond1() {
		return cond1;
	}
	public void setCond1(int cond1) {
		this.cond1 = cond1;
	}
	public int getCond2() {
		return cond2;
	}
	public void setCond2(int cond2) {
		this.cond2 = cond2;
	}
	public int getCond3() {
		return cond3;
	}
	public void setCond3(int cond3) {
		this.cond3 = cond3;
	}
	public int getCond4() {
		return cond4;
	}
	public void setCond4(int cond4) {
		this.cond4 = cond4;
	}
	public int getCond5() {
		return cond5;
	}
	public void setCond5(int cond5) {
		this.cond5 = cond5;
	}
	public int getCond6() {
		return cond6;
	}
	public void setCond6(int cond6) {
		this.cond6 = cond6;
	}
	public int getCond7() {
		return cond7;
	}
	public void setCond7(int cond7) {
		this.cond7 = cond7;
	}
	public int getCond8() {
		return cond8;
	}
	public void setCond8(int cond8) {
		this.cond8 = cond8;
	}
	public int getCond9() {
		return cond9;
	}
	public void setCond9(int cond9) {
		this.cond9 = cond9;
	}
	public int getCond10() {
		return cond10;
	}
	public void setCond10(int cond10) {
		this.cond10 = cond10;
	}
	private String parameter;
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getCirWater() {
		return cirWater;
	}
	public void setCirWater(String cirWater) {
		this.cirWater = cirWater;
	}
	private int seqNo;
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public int getSsId() {
		return ssId;
	}
	public void setSsId(int ssId) {
		this.ssId = ssId;
	}
	public String getScopeCd() {
		return scopeCd;
	}
	public void setScopeCd(String scopeCd) {
		this.scopeCd = scopeCd;
	}
	public String getScopeNm() {
		return scopeNm;
	}
	public void setScopeNm(String scopeNm) {
		this.scopeNm = scopeNm;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getFinalts() {
		return finalts;
	}
	public void setFinalts(String finalts) {
		this.finalts = finalts;
	}
	public String getSubScopeCd() {
		return subScopeCd;
	}
	public void setSubScopeCd(String subScopeCd) {
		this.subScopeCd = subScopeCd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEquivalent() {
		return equivalent;
	}
	public void setEquivalent(String equivalent) {
		this.equivalent = equivalent;
	}
	public String getPanelType() {
		return panelType;
	}
	public void setPanelType(String panelType) {
		this.panelType = panelType;
	}

	private int ssId;
	private String test;
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	private int hmbdTableInput;
	public int getHmbdTableInput() {
		return hmbdTableInput;
	}
	public void setHmbdTableInput(int hmbdTableInput) {
		this.hmbdTableInput = hmbdTableInput;
	}
	public int getNoOfCondition() {
		return noOfCondition;
	}
	public void setNoOfCondition(int noOfCondition) {
		this.noOfCondition = noOfCondition;
	}
	private int conditiontableinput;
	public int getConditiontableinput() {
		return conditiontableinput;
	}
	public void setConditiontableinput(int conditiontableinput) {
		this.conditiontableinput = conditiontableinput;
	}
	
	private int noOfCondition;
	private String scopeCd;
	private String scopeNm;
	private String information;
	private String finalts;
	private String subScopeCd;
	private String description;
	private String equivalent;
	private String panelType;
	private String cirWater;
	private String paramNm;
	private int unitId;
	private String unitNm;
	private String rhsCostNew;
	public String getRhsCostNew() {
		return rhsCostNew;
	}
	public void setRhsCostNew(String rhsCostNew) {
		this.rhsCostNew = rhsCostNew;
	}
	public String getRhsComrComments() {
		return rhsComrComments;
	}
	public void setRhsComrComments(String rhsComrComments) {
		this.rhsComrComments = rhsComrComments;
	}
	public String getBasicCostNew() {
		return basicCostNew;
	}
	public void setBasicCostNew(String basicCostNew) {
		this.basicCostNew = basicCostNew;
	}
	public String getAddOnCostNew() {
		return addOnCostNew;
	}
	public void setAddOnCostNew(String addOnCostNew) {
		this.addOnCostNew = addOnCostNew;
	}
	public String getItemCostNew() {
		return itemCostNew;
	}
	public void setItemCostNew(String itemCostNew) {
		this.itemCostNew = itemCostNew;
	}
	public String getTotalCostNew() {
		return totalCostNew;
	}
	public void setTotalCostNew(String totalCostNew) {
		this.totalCostNew = totalCostNew;
	}
	private String discountPer;
	public String getDiscountPer() {
		return discountPer;
	}
	public void setDiscountPer(String discountPer) {
		this.discountPer = discountPer;
	}
	private String rhsComrComments;
	private String basicCostNew;
	private String addOnCostNew;
	private String itemCostNew;
	private String totalCostNew;
	private String guaranteed;
	private String subGrpCd;
	public String getSubGrpCd() {
		return subGrpCd;
	}
	public void setSubGrpCd(String subGrpCd) {
		this.subGrpCd = subGrpCd;
	}
	private int cond1;
	private int cond2;
	private int cond3;
	private int cond4;
	private int cond5;
	private int cond6;
	private int  cond7;
	private int  cond8;
	private int cond9;
	private int  cond10;
	
	private String identifier;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getRecmd() {
		return recmd;
	}
	public void setRecmd(String recmd) {
		this.recmd = recmd;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	private String conductivity;
	public String getConductivity() {
		return conductivity;
	}
	public void setConductivity(String conductivity) {
		this.conductivity = conductivity;
	}

	private String units;
	private String recmd;
	private String limit;
	private float addInstrCost;
	public float getAddInstrCost() {
		return addInstrCost;
	}
	public void setAddInstrCost(float addInstrCost) {
		this.addInstrCost = addInstrCost;
	}
	public float getTotalAddInstrCost() {
		return totalAddInstrCost;
	}
	public void setTotalAddInstrCost(float totalAddInstrCost) {
		this.totalAddInstrCost = totalAddInstrCost;
	}
	
	public boolean isItemFlag() {
		return itemFlag;
	}
	public boolean isAddInstrFlag() {
		return addInstrFlag;
	}

	private float totalAddInstrCost;
	private boolean itemFlag;
	public void setItemFlag(boolean itemFlag) {
		this.itemFlag = itemFlag;
	}
	public void setAddInstrFlag(boolean addInstrFlag) {
		this.addInstrFlag = addInstrFlag;
	}
	private boolean addInstrFlag;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAddPrbFlag() {
		return addPrbFlag;
	}
	public void setAddPrbFlag(int addPrbFlag) {
		this.addPrbFlag = addPrbFlag;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public String getTagNum() {
		return tagNum;
	}
	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getCost2() {
		return cost2;
	}
	public void setCost2(float cost2) {
		this.cost2 = cost2;
	}
	public int getNewColValFlag() {
		return newColValFlag;
	}
	public void setNewColValFlag(int newColValFlag) {
		this.newColValFlag = newColValFlag;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	private int addPrbFlag;
	private int orderBy;
	private String offerType;
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
	private String tagNum;
	private String equipment;
	private String application;
	private String location;
	private float cost2;
	private int newColValFlag;
	private String note;
	private int linkFlag;
	public int getLinkFlag() {
		return linkFlag;
	}
	public void setLinkFlag(int linkFlag) {
		this.linkFlag = linkFlag;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getInstrNm() {
		return instrNm;
	}
	public void setInstrNm(String instrNm) {
		this.instrNm = instrNm;
	}
	public String getInstrTypeNm() {
		return instrTypeNm;
	}
	public void setInstrTypeNm(String instrTypeNm) {
		this.instrTypeNm = instrTypeNm;
	}
	public String getInstrDesc() {
		return instrDesc;
	}
	public void setInstrDesc(String instrDesc) {
		this.instrDesc = instrDesc;
	}
	public String getInstrMounting() {
		return instrMounting;
	}
	public void setInstrMounting(String instrMounting) {
		this.instrMounting = instrMounting;
	}
	public int getQuantityLogic() {
		return quantityLogic;
	}
	public void setQuantityLogic(int quantityLogic) {
		this.quantityLogic = quantityLogic;
	}
	public float getCost1() {
		return cost1;
	}
	public void setCost1(float cost1) {
		this.cost1 = cost1;
	}
	public int getNoOfTable() {
		return noOfTable;
	}
	public void setNoOfTable(int noOfTable) {
		this.noOfTable = noOfTable;
	}
	public int getAddInstrId() {
		return addInstrId;
	}
	public void setAddInstrId(int addInstrId) {
		this.addInstrId = addInstrId;
	}
	public String getAddInstrCd() {
		return addInstrCd;
	}
	public void setAddInstrCd(String addInstrCd) {
		this.addInstrCd = addInstrCd;
	}
	public String getAddInstrNm() {
		return addInstrNm;
	}
	public void setAddInstrNm(String addInstrNm) {
		this.addInstrNm = addInstrNm;
	}
	private String instrCode;
	private String instrNm;
	private String instrTypeNm;
	private String instrDesc;
	private String instrMounting;
	private int quantityLogic;
	private float cost1;
	private int noOfTable;
	private int addInstrId;
	private String addInstrCd;
	private String addInstrNm;
	private String quatity;
	private int successCode;
	private String revNum; 
	private String quotNum; 
	private String quotStatus; 
	private String custName;
	private String orderDate;
	private String scopeCode;
	private String itemCode;
	private String subItemCode;
	private String techRemarks;
	private String techComments;
	private String headerText;
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	public String getFooterText() {
		return footerText;
	}
	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}
	private String footerText;
	private String category;
	private String desItemName;
	private int desSubItemOrderId;
	private String subColValCode;
	private String desColOrderId;
	public void setDesColOrderId(String desColOrderId) {
		this.desColOrderId = desColOrderId;
	}
	private String itemName;
	private String subCatName;
	private String varientTypeName;
	private String successMsg;
	private String oppName;
	private String opportunitySeqNum;
	private String date;
	private String accountName;
	private String custContactPersonName;
	private String subject;
	private String fixedText1;
	private String fixedText2;
	private String fixedText3;
	private String fixedText4;
	private String fixedText5;
	private String categoryName;
	private String turbineCode;
	private String SubCategoryName;
	private int detQuotId;
	private int itemId;
	private int subItemId;
	private String subItemName;
	private int subItemTypeId;
	private String subItemTypeName;
	private int colId;
	private int numberOfSubItems;
	private String colNm;
	private String colValCd;
	private float rhsCost;
	private float rhsColQuantity;
	private String rhsColcomments;
	private float itemCost;
	private float totalPrice;
//	private boolean techFlag;
//	private boolean comrFlag;
	private int qty;
	private int orderId;
	private int quant;
	private int numberOfRows;
	private String remarks ;
	private int scopeofsupplysize;
	private int scopeofsupply;
	private int quantityAux;
	private String frameDesc;
	private String condensingType;
	private int typeOfChargeId;
	private String typeOfCharge;
	private String typeOfChargeCd;
	private int loadingId;
	private String loadingName;
	private String loadingCd;
	private int lodgingId;
	private String lodgingName;
	private String  lodgingCd;
	private int noOfManDays;
	private String rhsColTechcomments;
	private String rhsColComrcomments;
	private float setBasicCost;
	private String dboMechItem;
	private int mechItemId;
	private boolean defaultVal;
	private int techFlag;
	private int comrFlag;
	private int quantity;
	private String comrRemarks;
	private int addOnNewColFlag;
	private String subItemTechRemarks;
	private String subItemComrRemarks;
	private boolean othersFlag;
	private int f2fItemId;
	private int  quotId;
	private boolean lhsFlag;
	private boolean rhsFlag;
	private int dispInd;
	private String f2fItem;
	private float totalCost;
	private float basicCost;
	private float subItemCost;
	private float addOnCost;
	private float overwrittenPrice;
	private String comrComments;
	private float othersCost;
	private boolean  addOnFlag;
	private float subItemTypeCost;
	private String subItemTypeTechRemarks;
	private String subItemTypeComrRemarks;
	private int otherItemId;
	private float insurance;
	private int inTravelExpensesReq;
	private int inNoOfVisit;
	private boolean itemOverwrittenPriceFlag;
	public boolean isItemOverwrittenPriceFlag() {
		return itemOverwrittenPriceFlag;
	}
	public void setItemOverwrittenPriceFlag(boolean itemOverwrittenPriceFlag) {
		this.itemOverwrittenPriceFlag = itemOverwrittenPriceFlag;
	}
	public boolean isAddonOverwrittenPriceFlag() {
		return addonOverwrittenPriceFlag;
	}
	public void setAddonOverwrittenPriceFlag(boolean addonOverwrittenPriceFlag) {
		this.addonOverwrittenPriceFlag = addonOverwrittenPriceFlag;
	}
	private boolean overwrittenPriceFlag;
	private boolean addonOverwrittenPriceFlag;
	private int inCostForEachVisit;
	private float splProvision;
	private float travelExpenses;
	private float turbineContigency;
	private float dboContigency;
	private float inpAgencyCommission;
	private float salesExpenses;
	private float engineCharges;
	private String cost;
	private float extScopeRhsCost;
	private float extScopeCost;
	private float totalExtCost;
	private int  pkgQuotId;
	private int packageTypeId;
	private String packageTypeName;
	private String packageTypeCode;
	private int framePowerId;
	private String frameName;
	private int condensingTypeId;
	private String condensingTypeName;
	private int price;
	private int transMastId;
	private String custCode;
	private int transTypeId;
	private String transType;
	private String fOBPlace;
	private int compoId;
	private String compoName;
	private int numberOfVehicle;
	private String toPlace;
	private int distance;
	private int portId;
	private String countryName;
	
	private String portName;
	private float compPrice;
	private float fobPrice;
	private float intrestNoOfMonths;
	private float others;
	private String othersRemarks;
	private float warrantyPeriod;
	private float varProfit;
	private float  inpLdforLateDelivery;
	private float inpLdPerformance;
	private float intrestPercentage;
	private float inpBG1;
	private float inpBG2;
	private float totOrderCost;
	private float contigencyGeneral;
	private float agencyCommCharges;
	private float  ldPerformance;
	private float ldforLateDelivery;
	private float bankingCharges1;
	private float bankingCharges2;
	private float ovrheadSaleCost;
	private float overRawMaterialCost;
	private float warrantyCost;
	private float intrestCharges;
	private float totalVariableCost;
	private boolean varNewFlag;
	private float varNewCost;
	private float projSupply;
	private float projServices;
	private float projTransport;
	private float totalProjectCost;
	private float supplyCost;
	private float serviceCost;
	private float transCost;
	private float  totOrderCostNetProfit;
	private float  percentProfit;
	private float turbineOrderBookCost;
	private float totalFtfCost;
	private float packageCost;
	private float dboMechCost;
	private float dboEleCost;
	private float projectTotalCost;
	private boolean projectNewFlag;
	private float  projectNewCost;
	private String custType;
	private String custContactNumber;
	private String  custEmailId;
	private String  custAddress;
	private String  custPincode;
	private String   consultantName;
	private String consultantSpocName;
	private String  consultantEmail;
	private String  consultantAddress;
	private String endUserName;
	private String endUserContactNo;
	private String endUserEmail;
	private String endUserAddress;
	private String  regionCode;
	private String region;
	private String  custStatus;
	private String sfdcLink;
	private  int frameId;
	private float  capacity;
	private String quotNumber;
	private String variantCode;
	private String statusName;
	private String statusCode;
	private String COldNumber;
	private String CNewNumber;
	private String orientationType;
	private int isNewProject;
	private float percentageVariation;
	private String bleedType;
	private String createdDate;
	private String modifiedDate;
	private String assignedToName;
	private String assignedEmpId;
	private String assignedPhoneNumber;
	private String assignedEmail;
	private String  createdBy;
	private String creatorEmail;
	private String creatorEmpId;
	private String creatorPhoneNumber;
	private String creatorDeptName;
	private String userRoleName;
	private  int userRoleId;
	private  int  isEndUserReq;
	private  int  typeOfOffer;
	private  int typeOfQuot;
	private  int  custId;
	private String custCodeNm;
	private String typeOfOfferNm;
	private String typeOfQuotNm;
	private  int typeOfInjection;
	private  int typeOfVarient;
	private String typeOfInjectionNm;
	private String  typeOfVarientNm;
	private  int stateId;
	private String stateNm;
	private  int  statusId;
	private  int regionId;
	private  int orientationTypeId;
	private  int bleedTypeId;
	private String desSubItemName;
	private  int quantityEle;

	
	private List<ReportBean> comercialWordData  = new ArrayList<>();
	private List<ReportBean> technicalReportData = new ArrayList<>();
	private List<ReportBean> comercialWordDataNew  = new ArrayList<>();
	public List<ReportBean> getComercialWordDataNew() {
		return comercialWordDataNew;
	}
	public void setComercialWordDataNew(List<ReportBean> comercialWordDataNew) {
		this.comercialWordDataNew = comercialWordDataNew;
	}
	public List<ReportBean> getComercialWordData() {
		return comercialWordData;
	}
	public void setComercialWordData(List<ReportBean> comercialWordData) {
		this.comercialWordData = comercialWordData;
	}
	
	
	public List<ReportBean> getAddOnReportData() {
		return addOnReportData;
	}
	public void setAddOnReportData(List<ReportBean> addOnReportData) {
		this.addOnReportData = addOnReportData;
	}
	private List<ReportBean> addOnReportData = new ArrayList<>();
	private List<ReportBean> reportData = new ArrayList<>();
	private List<ReportBean> wordData = new ArrayList<>();
	private List<ReportBean> wordDataAux = new ArrayList<>();
	private List<ReportBean> detailedReportData = new ArrayList<>();
	SaveBasicDetails saveBasicDetails = new SaveBasicDetails();
	CustomerDetails customerDetailsForm = new CustomerDetails();
	
	
	List<ScopeOfSupply> scopeOfSupplyListDet = new ArrayList<>();
	List<DBOBean> mechDetList =  new ArrayList<>();
	List<DBOBean> pkgDetList =  new ArrayList<>();
	List<DBOBean> ecDetList  =  new ArrayList<>();
	List<DBOBean> trnsDetList = new ArrayList<>();
	List<DBOBean> varDetList  = new ArrayList<>();
	List<DBOBean> proDetList = new ArrayList<>();
	List<ScopeOfSupply>  scopeOfSupplyListF2fDet = new ArrayList<>();
	List<ReportBean> indexList = new ArrayList<>();
	List<ReportBean> scopeList = new ArrayList<>();
	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the overwrittenPriceFlag
	 */
	public boolean isOverwrittenPriceFlag() {
		return overwrittenPriceFlag;
	}
	/**
	 * @param overwrittenPriceFlag the overwrittenPriceFlag to set
	 */
	public void setOverwrittenPriceFlag(boolean overwrittenPriceFlag) {
		this.overwrittenPriceFlag = overwrittenPriceFlag;
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
	 * @return the reportData
	 */
	public List<ReportBean> getReportData() {
		return reportData;
	}
	/**
	 * @param reportData the reportData to set
	 */
	public void setReportData(List<ReportBean> reportData) {
		this.reportData = reportData;
	}
	/**
	 * @return the revNum
	 */
	public String getRevNum() {
		return revNum;
	}
	/**
	 * @param revNum the revNum to set
	 */
	public void setRevNum(String revNum) {
		this.revNum = revNum;
	}
	/**
	 * @return the quantity
	 */
//	public int getQuantity() {
//		return quantity;
//	}
//	/**
//	 * @param quantity the quantity to set
//	 */
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
	/**
	 * @return the cost
	 */
	public String getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}
	/**
	 * @return the quotNum
	 */
	public String getQuotNum() {
		return quotNum;
	}
	/**
	 * @param quotNum the quotNum to set
	 */
	public void setQuotNum(String quotNum) {
		this.quotNum = quotNum;
	}
	/**
	 * @return the quotStatus
	 */
	public String getQuotStatus() {
		return quotStatus;
	}
	/**
	 * @param quotStatus the quotStatus to set
	 */
	public void setQuotStatus(String quotStatus) {
		this.quotStatus = quotStatus;
	}
	/**
	 * @return the oppName
	 */
	public String getOppName() {
		return oppName;
	}
	/**
	 * @param oppName the oppName to set
	 */
	public void setOppName(String oppName) {
		this.oppName = oppName;
	}
	/**
	 * @return the opportunitySeqNum
	 */
	public String getOpportunitySeqNum() {
		return opportunitySeqNum;
	}
	/**
	 * @param opportunitySeqNum the opportunitySeqNum to set
	 */
	public void setOpportunitySeqNum(String opportunitySeqNum) {
		this.opportunitySeqNum = opportunitySeqNum;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the custContactPersonName
	 */
	public String getCustContactPersonName() {
		return custContactPersonName;
	}
	/**
	 * @param custContactPersonName the custContactPersonName to set
	 */
	public void setCustContactPersonName(String custContactPersonName) {
		this.custContactPersonName = custContactPersonName;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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
	 * @return the subCategoryName
	 */
	public String getSubCategoryName() {
		return SubCategoryName;
	}
	/**
	 * @param subCategoryName the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		SubCategoryName = subCategoryName;
	}
	/**
	 * @return the wordData
	 */
	public List<ReportBean> getWordData() {
		return wordData;
	}
	/**
	 * @param wordData the wordData to set
	 */
	public void setWordData(List<ReportBean> wordData) {
		this.wordData = wordData;
	}
	/**
	 * @return the detQuotId
	 */
	public int getDetQuotId() {
		return detQuotId;
	}
	/**
	 * @param detQuotId the detQuotId to set
	 */
	public void setDetQuotId(int detQuotId) {
		this.detQuotId = detQuotId;
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
	 * @return the subItemId
	 */
	public int getSubItemId() {
		return subItemId;
	}
	/**
	 * @param subItemId the subItemId to set
	 */
	public void setSubItemId(int subItemId) {
		this.subItemId = subItemId;
	}
	/**
	 * @return the subItemName
	 */
	public String getSubItemName() {
		return subItemName;
	}
	/**
	 * @param subItemName the subItemName to set
	 */
	public void setSubItemName(String subItemName) {
		this.subItemName = subItemName;
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
	/**
	 * @return the subItemTypeName
	 */
	public String getSubItemTypeName() {
		return subItemTypeName;
	}
	/**
	 * @param subItemTypeName the subItemTypeName to set
	 */
	public void setSubItemTypeName(String subItemTypeName) {
		this.subItemTypeName = subItemTypeName;
	}
	/**
	 * @return the colId
	 */
	public int getColId() {
		return colId;
	}
	/**
	 * @param colId the colId to set
	 */
	public void setColId(int colId) {
		this.colId = colId;
	}
	/**
	 * @return the colNm
	 */
	public String getColNm() {
		return colNm;
	}
	/**
	 * @param colNm the colNm to set
	 */
	public void setColNm(String colNm) {
		this.colNm = colNm;
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
	 * @return the rhsCost
	 */
	public float getRhsCost() {
		return rhsCost;
	}
	/**
	 * @param rhsCost the rhsCost to set
	 */
	public void setRhsCost(float rhsCost) {
		this.rhsCost = rhsCost;
	}
	/**
	 * @return the rhsColQuantity
	 */
	public float getRhsColQuantity() {
		return rhsColQuantity;
	}
	/**
	 * @param rhsColQuantity the rhsColQuantity to set
	 */
	public void setRhsColQuantity(float rhsColQuantity) {
		this.rhsColQuantity = rhsColQuantity;
	}
	/**
	 * @return the rhsColcomments
	 */
	public String getRhsColcomments() {
		return rhsColcomments;
	}
	/**
	 * @param rhsColcomments the rhsColcomments to set
	 */
	public void setRhsColcomments(String rhsColcomments) {
		this.rhsColcomments = rhsColcomments;
	}
	/**
	 * @return the itemCost
	 */
	public float getItemCost() {
		return itemCost;
	}
	/**
	 * @param itemCost the itemCost to set
	 */
	public void setItemCost(float itemCost) {
		this.itemCost = itemCost;
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
	 * @return the techFlag
//	 */
//	public boolean isTechFlag() {
//		return techFlag;
//	}
//	/**
//	 * @param techFlag the techFlag to set
//	 */
//	public void setTechFlag(boolean techFlag) {
//		this.techFlag = techFlag;
//	}
//	/**
//	 * @return the comrFlag
//	 */
//	public boolean isComrFlag() {
//		return comrFlag;
//	}
//	/**
//	 * @param comrFlag the comrFlag to set
//	 */
//	public void setComrFlag(boolean comrFlag) {
//		this.comrFlag = comrFlag;
//	}
	/**
	 * @return the overWrittenPrice
	 */
	public float getOverWrittenPrice() {
		return overwrittenPrice;
	}
	/**
	 * @param overWrittenPrice the overWrittenPrice to set
	 */
	public void setOverWrittenPrice(float overWrittenPrice) {
		this.overwrittenPrice = overWrittenPrice;
	}
	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
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
	 * @return the scopeofsupplysize
	 */
	public int getScopeofsupplysize() {
		return scopeofsupplysize;
	}
	/**
	 * @param scopeofsupplysize the scopeofsupplysize to set
	 */
	public void setScopeofsupplysize(int scopeofsupplysize) {
		this.scopeofsupplysize = scopeofsupplysize;
	}
	/**
	 * @return the scopeofsupply
	 */
	public int getScopeofsupply() {
		return scopeofsupply;
	}
	/**
	 * @param scopeofsupply the scopeofsupply to set
	 */
	public void setScopeofsupply(int scopeofsupply) {
		this.scopeofsupply = scopeofsupply;
	}
	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the varientTypeName
	 */
	public String getVarientTypeName() {
		return varientTypeName;
	}
	/**
	 * @param varientTypeName the varientTypeName to set
	 */
	public void setVarientTypeName(String varientTypeName) {
		this.varientTypeName = varientTypeName;
	}
	/**
	 * @return the fixedText5
	 */
	public String getFixedText5() {
		return fixedText5;
	}
	/**
	 * @param fixedText5 the fixedText5 to set
	 */
	public void setFixedText5(String fixedText5) {
		this.fixedText5 = fixedText5;
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
	 * @return the numberOfRows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}
	/**
	 * @param numberOfRows the numberOfRows to set
	 */
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	/**
	 * @return the subCatName
	 */
	public String spaceF2f;
	public String getSpaceF2f() {
		return spaceF2f;
	}
	public void setSpaceF2f(String spaceF2f) {
		this.spaceF2f = spaceF2f;
	}
	public String getSubCatName() {
		return subCatName;
	}
	/**
	 * @param subCatName the subCatName to set
	 */
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
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
	 * @return the numberOfSubItems
	 */
	public int getNumberOfSubItems() {
		return numberOfSubItems;
	}
	/**
	 * @param numberOfSubItems the numberOfSubItems to set
	 */
	public void setNumberOfSubItems(int numberOfSubItems) {
		this.numberOfSubItems = numberOfSubItems;
	}
	/**
	 * @return the quant
	 * 
	 * 
	 */
	public String auxSpace;
	public String getAuxSpace() {
		return auxSpace;
	}
	public void setAuxSpace(String auxSpace) {
		this.auxSpace = auxSpace;
	}
	public String getAuxNewline() {
		return auxNewline;
	}
	public void setAuxNewline(String auxNewline) {
		this.auxNewline = auxNewline;
	}
	public String doubleQuot;
	public String getDoubleQuot() {
		return doubleQuot;
	}
	public void setDoubleQuot(String doubleQuot) {
		this.doubleQuot = doubleQuot;
	}
	
	public int itemQuantity;
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String auxNewline;
	public int getQuant() {
		return quant;
	}
	/**
	 * @param quant the quant to set
	 */
	public void setQuant(int quant) {
		this.quant = quant;
	}
	/**
	 * @return the wordDataAux
	 */
	public List<ReportBean> getWordDataAux() {
		return wordDataAux;
	}
	/**
	 * @param wordDataAux the wordDataAux to set
	 */
	public void setWordDataAux(List<ReportBean> wordDataAux) {
		this.wordDataAux = wordDataAux;
	}
	/**
	 * @return the quantityAux
	 */
	public int getQuantityAux() {
		return quantityAux;
	}
	/**
	 * @param quantityAux the quantityAux to set
	 */
	public void setQuantityAux(int quantityAux) {
		this.quantityAux = quantityAux;
	}
	/**
	 * @return the subItemCode
	 */
	public String getSubItemCode() {
		return subItemCode;
	}
	/**
	 * @param subItemCode the subItemCode to set
	 */
	public void setSubItemCode(String subItemCode) {
		this.subItemCode = subItemCode;
	}
	/**
	 * @return the rhsColTechcomments
	 */
	public String getRhsColTechcomments() {
		return rhsColTechcomments;
	}
	/**
	 * @param rhsColTechcomments the rhsColTechcomments to set
	 */
	public void setRhsColTechcomments(String rhsColTechcomments) {
		this.rhsColTechcomments = rhsColTechcomments;
	}
	/**
	 * @return the rhsColComrcomments
	 */
	public String getRhsColComrcomments() {
		return rhsColComrcomments;
	}
	/**
	 * @param rhsColComrcomments the rhsColComrcomments to set
	 */
	public void setRhsColComrcomments(String rhsColComrcomments) {
		this.rhsColComrcomments = rhsColComrcomments;
	}
	/**
	 * @return the setBasicCost
	 */
	public float getSetBasicCost() {
		return setBasicCost;
	}
	/**
	 * @param setBasicCost the setBasicCost to set
	 */
	public void setSetBasicCost(float setBasicCost) {
		this.setBasicCost = setBasicCost;
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
	 * @return the f2fItem
	 */
	public String getF2fItem() {
		return f2fItem;
	}
	/**
	 * @param f2fItem the f2fItem to set
	 */
	public void setF2fItem(String f2fItem) {
		this.f2fItem = f2fItem;
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
	 * @return the f2fItemId
	 */
	public int getF2fItemId() {
		return f2fItemId;
	}
	/**
	 * @param f2fItemId the f2fItemId to set
	 */
	public void setF2fItemId(int f2fItemId) {
		this.f2fItemId = f2fItemId;
	}
	/**
	 * @return the lhsFlag
	 */
	public boolean isLhsFlag() {
		return lhsFlag;
	}
	/**
	 * @param lhsFlag the lhsFlag to set
	 */
	public void setLhsFlag(boolean lhsFlag) {
		this.lhsFlag = lhsFlag;
	}
	/**
	 * @return the rhsFlag
	 */
	public boolean isRhsFlag() {
		return rhsFlag;
	}
	/**
	 * @param rhsFlag the rhsFlag to set
	 */
	public void setRhsFlag(boolean rhsFlag) {
		this.rhsFlag = rhsFlag;
	}
	/**
	 * @return the dboMechItem
	 */
	public String getDboMechItem() {
		return dboMechItem;
	}
	/**
	 * @param dboMechItem the dboMechItem to set
	 */
	public void setDboMechItem(String dboMechItem) {
		this.dboMechItem = dboMechItem;
	}
	/**
	 * @return the mechItemId
	 */
	public int getMechItemId() {
		return mechItemId;
	}
	/**
	 * @param mechItemId the mechItemId to set
	 */
	public void setMechItemId(int mechItemId) {
		this.mechItemId = mechItemId;
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
	/**
	 * @return the subItemCost
	 */
	public float getSubItemCost() {
		return subItemCost;
	}
	/**
	 * @param subItemCost the subItemCost to set
	 */
	public void setSubItemCost(float subItemCost) {
		this.subItemCost = subItemCost;
	}
	/**
	 * @return the addOnCost
	 */
	public float getAddOnCost() {
		return addOnCost;
	}
	/**
	 * @param addOnCost the addOnCost to set
	 */
	public void setAddOnCost(float addOnCost) {
		this.addOnCost = addOnCost;
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
	/**
	 * @return the comrFlag
	 */
	public int getComrFlag() {
		return comrFlag;
	}
	/**
	 * @param comrFlag the comrFlag to set
	 */
	public void setComrFlag(int comrFlag) {
		this.comrFlag = comrFlag;
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
	 * @return the addOnNewColFlag
	 */
	public int getAddOnNewColFlag() {
		return addOnNewColFlag;
	}
	/**
	 * @param addOnNewColFlag the addOnNewColFlag to set
	 */
	public void setAddOnNewColFlag(int addOnNewColFlag) {
		this.addOnNewColFlag = addOnNewColFlag;
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
	 * @return the othersFlag
	 */
	public boolean isOthersFlag() {
		return othersFlag;
	}
	/**
	 * @param othersFlag the othersFlag to set
	 */
	public void setOthersFlag(boolean othersFlag) {
		this.othersFlag = othersFlag;
	}
	/**
	 * @return the dispInd
	 */
	public int getDispInd() {
		return dispInd;
	}
	/**
	 * @param dispInd the dispInd to set
	 */
	public void setDispInd(int dispInd) {
		this.dispInd = dispInd;
	}
	/**
	 * @return the othersCost
	 */
	public float getOthersCost() {
		return othersCost;
	}
	/**
	 * @param othersCost the othersCost to set
	 */
	public void setOthersCost(float othersCost) {
		this.othersCost = othersCost;
	}
	/**
	 * @return the addOnFlag
	 */
	public boolean isAddOnFlag() {
		return addOnFlag;
	}
	/**
	 * @param addOnFlag the addOnFlag to set
	 */
	public void setAddOnFlag(boolean addOnFlag) {
		this.addOnFlag = addOnFlag;
	}
	/**
	 * @return the subItemTypeCost
	 */
	public float getSubItemTypeCost() {
		return subItemTypeCost;
	}
	/**
	 * @param subItemTypeCost the subItemTypeCost to set
	 */
	public void setSubItemTypeCost(float subItemTypeCost) {
		this.subItemTypeCost = subItemTypeCost;
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
	 * @return the otherItemId
	 */
	public int getOtherItemId() {
		return otherItemId;
	}
	/**
	 * @param otherItemId the otherItemId to set
	 */
	public void setOtherItemId(int otherItemId) {
		this.otherItemId = otherItemId;
	}
	/**
	 * @return the insurance
	 */
	public float getInsurance() {
		return insurance;
	}
	/**
	 * @param insurance the insurance to set
	 */
	public void setInsurance(float insurance) {
		this.insurance = insurance;
	}
	/**
	 * @return the inTravelExpensesReq
	 */
	public int getInTravelExpensesReq() {
		return inTravelExpensesReq;
	}
	/**
	 * @param inTravelExpensesReq the inTravelExpensesReq to set
	 */
	public void setInTravelExpensesReq(int inTravelExpensesReq) {
		this.inTravelExpensesReq = inTravelExpensesReq;
	}
	/**
	 * @return the inNoOfVisit
	 */
	public int getInNoOfVisit() {
		return inNoOfVisit;
	}
	/**
	 * @param inNoOfVisit the inNoOfVisit to set
	 */
	public void setInNoOfVisit(int inNoOfVisit) {
		this.inNoOfVisit = inNoOfVisit;
	}
	/**
	 * @return the inCostForEachVisit
	 */
	public int getInCostForEachVisit() {
		return inCostForEachVisit;
	}
	/**
	 * @param inCostForEachVisit the inCostForEachVisit to set
	 */
	public void setInCostForEachVisit(int inCostForEachVisit) {
		this.inCostForEachVisit = inCostForEachVisit;
	}
	/**
	 * @return the splProvision
	 */
	public float getSplProvision() {
		return splProvision;
	}
	/**
	 * @param splProvision the splProvision to set
	 */
	public void setSplProvision(float splProvision) {
		this.splProvision = splProvision;
	}
	/**
	 * @return the travelExpenses
	 */
	public float getTravelExpenses() {
		return travelExpenses;
	}
	/**
	 * @param travelExpenses the travelExpenses to set
	 */
	public void setTravelExpenses(float travelExpenses) {
		this.travelExpenses = travelExpenses;
	}
	/**
	 * @return the turbineContigency
	 */
	public float getTurbineContigency() {
		return turbineContigency;
	}
	/**
	 * @param turbineContigency the turbineContigency to set
	 */
	public void setTurbineContigency(float turbineContigency) {
		this.turbineContigency = turbineContigency;
	}
	/**
	 * @return the dboContigency
	 */
	public float getDboContigency() {
		return dboContigency;
	}
	/**
	 * @param dboContigency the dboContigency to set
	 */
	public void setDboContigency(float dboContigency) {
		this.dboContigency = dboContigency;
	}
	/**
	 * @return the inpAgencyCommission
	 */
	public float getInpAgencyCommission() {
		return inpAgencyCommission;
	}
	/**
	 * @param inpAgencyCommission the inpAgencyCommission to set
	 */
	public void setInpAgencyCommission(float inpAgencyCommission) {
		this.inpAgencyCommission = inpAgencyCommission;
	}
	/**
	 * @return the salesExpenses
	 */
	public float getSalesExpenses() {
		return salesExpenses;
	}
	/**
	 * @param salesExpenses the salesExpenses to set
	 */
	public void setSalesExpenses(float salesExpenses) {
		this.salesExpenses = salesExpenses;
	}
	/**
	 * @return the engineCharges
	 */
	public float getEngineCharges() {
		return engineCharges;
	}
	/**
	 * @param engineCharges the engineCharges to set
	 */
	public void setEngineCharges(float engineCharges) {
		this.engineCharges = engineCharges;
	}
	/**
	 * @return the extScopeRhsCost
	 */
	public float getExtScopeRhsCost() {
		return extScopeRhsCost;
	}
	/**
	 * @param extScopeRhsCost the extScopeRhsCost to set
	 */
	public void setExtScopeRhsCost(float extScopeRhsCost) {
		this.extScopeRhsCost = extScopeRhsCost;
	}
	/**
	 * @return the extScopeCost
	 */
	public float getExtScopeCost() {
		return extScopeCost;
	}
	/**
	 * @param extScopeCost the extScopeCost to set
	 */
	public void setExtScopeCost(float extScopeCost) {
		this.extScopeCost = extScopeCost;
	}
	/**
	 * @return the totalExtCost
	 */
	public float getTotalExtCost() {
		return totalExtCost;
	}
	/**
	 * @param totalExtCost the totalExtCost to set
	 */
	public void setTotalExtCost(float totalExtCost) {
		this.totalExtCost = totalExtCost;
	}
	/**
	 * @return the pkgQuotId
	 */
	public int getPkgQuotId() {
		return pkgQuotId;
	}
	/**
	 * @param pkgQuotId the pkgQuotId to set
	 */
	public void setPkgQuotId(int pkgQuotId) {
		this.pkgQuotId = pkgQuotId;
	}
	/**
	 * @return the packageTypeId
	 */
	public int getPackageTypeId() {
		return packageTypeId;
	}
	/**
	 * @param packageTypeId the packageTypeId to set
	 */
	public void setPackageTypeId(int packageTypeId) {
		this.packageTypeId = packageTypeId;
	}
	/**
	 * @return the packageTypeName
	 */
	public String getPackageTypeName() {
		return packageTypeName;
	}
	/**
	 * @param packageTypeName the packageTypeName to set
	 */
	public void setPackageTypeName(String packageTypeName) {
		this.packageTypeName = packageTypeName;
	}
	/**
	 * @return the packageTypeCode
	 */
	public String getPackageTypeCode() {
		return packageTypeCode;
	}
	/**
	 * @param packageTypeCode the packageTypeCode to set
	 */
	public void setPackageTypeCode(String packageTypeCode) {
		this.packageTypeCode = packageTypeCode;
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
	 * @return the frameDesc
	 */
	public String getFrameDesc() {
		return frameDesc;
	}
	/**
	 * @param frameDesc the frameDesc to set
	 */
	public void setFrameDesc(String frameDesc) {
		this.frameDesc = frameDesc;
	}
	/**
	 * @return the condensingType
	 */
	public String getCondensingType() {
		return condensingType;
	}
	/**
	 * @param condensingType the condensingType to set
	 */
	public void setCondensingType(String condensingType) {
		this.condensingType = condensingType;
	}
	/**
	 * @return the typeOfChargeId
	 */
	public int getTypeOfChargeId() {
		return typeOfChargeId;
	}
	/**
	 * @param typeOfChargeId the typeOfChargeId to set
	 */
	public void setTypeOfChargeId(int typeOfChargeId) {
		this.typeOfChargeId = typeOfChargeId;
	}
	/**
	 * @return the typeOfCharge
	 */
	public String getTypeOfCharge() {
		return typeOfCharge;
	}
	/**
	 * @param typeOfCharge the typeOfCharge to set
	 */
	public void setTypeOfCharge(String typeOfCharge) {
		this.typeOfCharge = typeOfCharge;
	}
	/**
	 * @return the typeOfChargeCd
	 */
	public String getTypeOfChargeCd() {
		return typeOfChargeCd;
	}
	/**
	 * @param typeOfChargeCd the typeOfChargeCd to set
	 */
	public void setTypeOfChargeCd(String typeOfChargeCd) {
		this.typeOfChargeCd = typeOfChargeCd;
	}
	/**
	 * @return the loadingId
	 */
	public int getLoadingId() {
		return loadingId;
	}
	/**
	 * @param loadingId the loadingId to set
	 */
	public void setLoadingId(int loadingId) {
		this.loadingId = loadingId;
	}
	/**
	 * @return the loadingName
	 */
	public String getLoadingName() {
		return loadingName;
	}
	/**
	 * @param loadingName the loadingName to set
	 */
	public void setLoadingName(String loadingName) {
		this.loadingName = loadingName;
	}
	/**
	 * @return the loadingCd
	 */
	public String getLoadingCd() {
		return loadingCd;
	}
	/**
	 * @param loadingCd the loadingCd to set
	 */
	public void setLoadingCd(String loadingCd) {
		this.loadingCd = loadingCd;
	}
	/**
	 * @return the lodgingId
	 */
	public int getLodgingId() {
		return lodgingId;
	}
	/**
	 * @param lodgingId the lodgingId to set
	 */
	public void setLodgingId(int lodgingId) {
		this.lodgingId = lodgingId;
	}
	/**
	 * @return the lodgingName
	 */
	public String getLodgingName() {
		return lodgingName;
	}
	/**
	 * @param lodgingName the lodgingName to set
	 */
	public void setLodgingName(String lodgingName) {
		this.lodgingName = lodgingName;
	}
	/**
	 * @return the lodgingCd
	 */
	public String getLodgingCd() {
		return lodgingCd;
	}
	/**
	 * @param lodgingCd the lodgingCd to set
	 */
	public void setLodgingCd(String lodgingCd) {
		this.lodgingCd = lodgingCd;
	}
	/**
	 * @return the noOfManDays
	 */
	public int getNoOfManDays() {
		return noOfManDays;
	}
	/**
	 * @param noOfManDays the noOfManDays to set
	 */
	public void setNoOfManDays(int noOfManDays) {
		this.noOfManDays = noOfManDays;
	}
	/**
	 * @return the transMastId
	 */
	public int getTransMastId() {
		return transMastId;
	}
	/**
	 * @param transMastId the transMastId to set
	 */
	public void setTransMastId(int transMastId) {
		this.transMastId = transMastId;
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
	 * @return the transTypeId
	 */
	public int getTransTypeId() {
		return transTypeId;
	}
	/**
	 * @param transTypeId the transTypeId to set
	 */
	public void setTransTypeId(int transTypeId) {
		this.transTypeId = transTypeId;
	}
	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * @return the fOBPlace
	 */
	public String getfOBPlace() {
		return fOBPlace;
	}
	/**
	 * @param fOBPlace the fOBPlace to set
	 */
	public void setfOBPlace(String fOBPlace) {
		this.fOBPlace = fOBPlace;
	}
	/**
	 * @return the compoId
	 */
	public int getCompoId() {
		return compoId;
	}
	/**
	 * @param compoId the compoId to set
	 */
	public void setCompoId(int compoId) {
		this.compoId = compoId;
	}
	/**
	 * @return the compoName
	 */
	public String getCompoName() {
		return compoName;
	}
	/**
	 * @param compoName the compoName to set
	 */
	public void setCompoName(String compoName) {
		this.compoName = compoName;
	}
	/**
	 * @return the numberOfVehicle
	 */
	public int getNumberOfVehicle() {
		return numberOfVehicle;
	}
	/**
	 * @param numberOfVehicle the numberOfVehicle to set
	 */
	public void setNumberOfVehicle(int numberOfVehicle) {
		this.numberOfVehicle = numberOfVehicle;
	}
	/**
	 * @return the toPlace
	 */
	public String getToPlace() {
		return toPlace;
	}
	/**
	 * @param toPlace the toPlace to set
	 */
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
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
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the portName
	 */
	public String getPortName() {
		return portName;
	}
	/**
	 * @param portName the portName to set
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}
	/**
	 * @return the compPrice
	 */
	public float getCompPrice() {
		return compPrice;
	}
	/**
	 * @param compPrice the compPrice to set
	 */
	public void setCompPrice(float compPrice) {
		this.compPrice = compPrice;
	}
	/**
	 * @return the fobPrice
	 */
	public float getFobPrice() {
		return fobPrice;
	}
	/**
	 * @param fobPrice the fobPrice to set
	 */
	public void setFobPrice(float fobPrice) {
		this.fobPrice = fobPrice;
	}
	/**
	 * @return the intrestNoOfMonths
	 */
	public float getIntrestNoOfMonths() {
		return intrestNoOfMonths;
	}
	/**
	 * @param intrestNoOfMonths the intrestNoOfMonths to set
	 */
	public void setIntrestNoOfMonths(float intrestNoOfMonths) {
		this.intrestNoOfMonths = intrestNoOfMonths;
	}
	/**
	 * @return the others
	 */
	public float getOthers() {
		return others;
	}
	/**
	 * @param others the others to set
	 */
	public void setOthers(float others) {
		this.others = others;
	}
	/**
	 * @return the othersRemarks
	 */
	public String getOthersRemarks() {
		return othersRemarks;
	}
	/**
	 * @param othersRemarks the othersRemarks to set
	 */
	public void setOthersRemarks(String othersRemarks) {
		this.othersRemarks = othersRemarks;
	}
	/**
	 * @return the warrantyPeriod
	 */
	public float getWarrantyPeriod() {
		return warrantyPeriod;
	}
	/**
	 * @param warrantyPeriod the warrantyPeriod to set
	 */
	public void setWarrantyPeriod(float warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
	/**
	 * @return the varProfit
	 */
	public float getVarProfit() {
		return varProfit;
	}
	/**
	 * @param varProfit the varProfit to set
	 */
	public void setVarProfit(float varProfit) {
		this.varProfit = varProfit;
	}
	/**
	 * @return the inpLdforLateDelivery
	 */
	public float getInpLdforLateDelivery() {
		return inpLdforLateDelivery;
	}
	/**
	 * @param inpLdforLateDelivery the inpLdforLateDelivery to set
	 */
	public void setInpLdforLateDelivery(float inpLdforLateDelivery) {
		this.inpLdforLateDelivery = inpLdforLateDelivery;
	}
	/**
	 * @return the inpLdPerformance
	 */
	public float getInpLdPerformance() {
		return inpLdPerformance;
	}
	/**
	 * @param inpLdPerformance the inpLdPerformance to set
	 */
	public void setInpLdPerformance(float inpLdPerformance) {
		this.inpLdPerformance = inpLdPerformance;
	}
	/**
	 * @return the intrestPercentage
	 */
	public float getIntrestPercentage() {
		return intrestPercentage;
	}
	/**
	 * @param intrestPercentage the intrestPercentage to set
	 */
	public void setIntrestPercentage(float intrestPercentage) {
		this.intrestPercentage = intrestPercentage;
	}
	/**
	 * @return the inpBG1
	 */
	public float getInpBG1() {
		return inpBG1;
	}
	/**
	 * @param inpBG1 the inpBG1 to set
	 */
	public void setInpBG1(float inpBG1) {
		this.inpBG1 = inpBG1;
	}
	/**
	 * @return the inpBG2
	 */
	public float getInpBG2() {
		return inpBG2;
	}
	/**
	 * @param inpBG2 the inpBG2 to set
	 */
	public void setInpBG2(float inpBG2) {
		this.inpBG2 = inpBG2;
	}
	/**
	 * @return the totOrderCost
	 */
	public float getTotOrderCost() {
		return totOrderCost;
	}
	/**
	 * @param totOrderCost the totOrderCost to set
	 */
	public void setTotOrderCost(float totOrderCost) {
		this.totOrderCost = totOrderCost;
	}
	/**
	 * @return the contigencyGeneral
	 */
	public float getContigencyGeneral() {
		return contigencyGeneral;
	}
	/**
	 * @param contigencyGeneral the contigencyGeneral to set
	 */
	public void setContigencyGeneral(float contigencyGeneral) {
		this.contigencyGeneral = contigencyGeneral;
	}
	/**
	 * @return the agencyCommCharges
	 */
	public float getAgencyCommCharges() {
		return agencyCommCharges;
	}
	/**
	 * @param agencyCommCharges the agencyCommCharges to set
	 */
	public void setAgencyCommCharges(float agencyCommCharges) {
		this.agencyCommCharges = agencyCommCharges;
	}
	/**
	 * @return the ldPerformance
	 */
	public float getLdPerformance() {
		return ldPerformance;
	}
	/**
	 * @param ldPerformance the ldPerformance to set
	 */
	public void setLdPerformance(float ldPerformance) {
		this.ldPerformance = ldPerformance;
	}
	/**
	 * @return the ldforLateDelivery
	 */
	public float getLdforLateDelivery() {
		return ldforLateDelivery;
	}
	/**
	 * @param ldforLateDelivery the ldforLateDelivery to set
	 */
	public void setLdforLateDelivery(float ldforLateDelivery) {
		this.ldforLateDelivery = ldforLateDelivery;
	}
	/**
	 * @return the bankingCharges1
	 */
	public float getBankingCharges1() {
		return bankingCharges1;
	}
	/**
	 * @param bankingCharges1 the bankingCharges1 to set
	 */
	public void setBankingCharges1(float bankingCharges1) {
		this.bankingCharges1 = bankingCharges1;
	}
	/**
	 * @return the bankingCharges2
	 */
	public float getBankingCharges2() {
		return bankingCharges2;
	}
	/**
	 * @param bankingCharges2 the bankingCharges2 to set
	 */
	public void setBankingCharges2(float bankingCharges2) {
		this.bankingCharges2 = bankingCharges2;
	}
	/**
	 * @return the ovrheadSaleCost
	 */
	public float getOvrheadSaleCost() {
		return ovrheadSaleCost;
	}
	/**
	 * @param ovrheadSaleCost the ovrheadSaleCost to set
	 */
	public void setOvrheadSaleCost(float ovrheadSaleCost) {
		this.ovrheadSaleCost = ovrheadSaleCost;
	}
	/**
	 * @return the overRawMaterialCost
	 */
	public float getOverRawMaterialCost() {
		return overRawMaterialCost;
	}
	/**
	 * @param overRawMaterialCost the overRawMaterialCost to set
	 */
	public void setOverRawMaterialCost(float overRawMaterialCost) {
		this.overRawMaterialCost = overRawMaterialCost;
	}
	/**
	 * @return the warrantyCost
	 */
	public float getWarrantyCost() {
		return warrantyCost;
	}
	/**
	 * @param warrantyCost the warrantyCost to set
	 */
	public void setWarrantyCost(float warrantyCost) {
		this.warrantyCost = warrantyCost;
	}
	/**
	 * @return the intrestCharges
	 */
	public float getIntrestCharges() {
		return intrestCharges;
	}
	/**
	 * @param intrestCharges the intrestCharges to set
	 */
	public void setIntrestCharges(float intrestCharges) {
		this.intrestCharges = intrestCharges;
	}
	/**
	 * @return the totalVariableCost
	 */
	public float getTotalVariableCost() {
		return totalVariableCost;
	}
	/**
	 * @param totalVariableCost the totalVariableCost to set
	 */
	public void setTotalVariableCost(float totalVariableCost) {
		this.totalVariableCost = totalVariableCost;
	}
	/**
	 * @return the varNewFlag
	 */
	public boolean isVarNewFlag() {
		return varNewFlag;
	}
	/**
	 * @param varNewFlag the varNewFlag to set
	 */
	public void setVarNewFlag(boolean varNewFlag) {
		this.varNewFlag = varNewFlag;
	}
	/**
	 * @return the varNewCost
	 */
	public float getVarNewCost() {
		return varNewCost;
	}
	/**
	 * @param varNewCost the varNewCost to set
	 */
	public void setVarNewCost(float varNewCost) {
		this.varNewCost = varNewCost;
	}
	/**
	 * @return the detailedReportData
	 */
	public List<ReportBean> getDetailedReportData() {
		return detailedReportData;
	}
	/**
	 * @param detailedReportData the detailedReportData to set
	 */
	public void setDetailedReportData(List<ReportBean> detailedReportData) {
		this.detailedReportData = detailedReportData;
	}
	/**
	 * @return the projSupply
	 */
	public float getProjSupply() {
		return projSupply;
	}
	/**
	 * @param projSupply the projSupply to set
	 */
	public void setProjSupply(float projSupply) {
		this.projSupply = projSupply;
	}
	/**
	 * @return the projServices
	 */
	public float getProjServices() {
		return projServices;
	}
	/**
	 * @param projServices the projServices to set
	 */
	public void setProjServices(float projServices) {
		this.projServices = projServices;
	}
	/**
	 * @return the projTransport
	 */
	public float getProjTransport() {
		return projTransport;
	}
	/**
	 * @param projTransport the projTransport to set
	 */
	public void setProjTransport(float projTransport) {
		this.projTransport = projTransport;
	}
	/**
	 * @return the totalProjectCost
	 */
	public float getTotalProjectCost() {
		return totalProjectCost;
	}
	/**
	 * @param totalProjectCost the totalProjectCost to set
	 */
	public void setTotalProjectCost(float totalProjectCost) {
		this.totalProjectCost = totalProjectCost;
	}
	/**
	 * @return the supplyCost
	 */
	public float getSupplyCost() {
		return supplyCost;
	}
	/**
	 * @param supplyCost the supplyCost to set
	 */
	public void setSupplyCost(float supplyCost) {
		this.supplyCost = supplyCost;
	}
	/**
	 * @return the serviceCost
	 */
	public float getServiceCost() {
		return serviceCost;
	}
	/**
	 * @param serviceCost the serviceCost to set
	 */
	public void setServiceCost(float serviceCost) {
		this.serviceCost = serviceCost;
	}
	/**
	 * @return the transCost
	 */
	public float getTransCost() {
		return transCost;
	}
	/**
	 * @param transCost the transCost to set
	 */
	public void setTransCost(float transCost) {
		this.transCost = transCost;
	}
	/**
	 * @return the totOrderCostNetProfit
	 */
	public float getTotOrderCostNetProfit() {
		return totOrderCostNetProfit;
	}
	/**
	 * @param totOrderCostNetProfit the totOrderCostNetProfit to set
	 */
	public void setTotOrderCostNetProfit(float totOrderCostNetProfit) {
		this.totOrderCostNetProfit = totOrderCostNetProfit;
	}
	/**
	 * @return the percentProfit
	 */
	public float getPercentProfit() {
		return percentProfit;
	}
	/**
	 * @param percentProfit the percentProfit to set
	 */
	public void setPercentProfit(float percentProfit) {
		this.percentProfit = percentProfit;
	}
	/**
	 * @return the turbineOrderBookCost
	 */
	public float getTurbineOrderBookCost() {
		return turbineOrderBookCost;
	}
	/**
	 * @param turbineOrderBookCost the turbineOrderBookCost to set
	 */
	public void setTurbineOrderBookCost(float turbineOrderBookCost) {
		this.turbineOrderBookCost = turbineOrderBookCost;
	}
	/**
	 * @return the totalFtfCost
	 */
	public float getTotalFtfCost() {
		return totalFtfCost;
	}
	/**
	 * @param totalFtfCost the totalFtfCost to set
	 */
	public void setTotalFtfCost(float totalFtfCost) {
		this.totalFtfCost = totalFtfCost;
	}
	/**
	 * @return the packageCost
	 */
	public float getPackageCost() {
		return packageCost;
	}
	/**
	 * @param packageCost the packageCost to set
	 */
	public void setPackageCost(float packageCost) {
		this.packageCost = packageCost;
	}
	/**
	 * @return the dboMechCost
	 */
	public float getDboMechCost() {
		return dboMechCost;
	}
	/**
	 * @param dboMechCost the dboMechCost to set
	 */
	public void setDboMechCost(float dboMechCost) {
		this.dboMechCost = dboMechCost;
	}
	/**
	 * @return the dboEleCost
	 */
	public float getDboEleCost() {
		return dboEleCost;
	}
	/**
	 * @param dboEleCost the dboEleCost to set
	 */
	public void setDboEleCost(float dboEleCost) {
		this.dboEleCost = dboEleCost;
	}
	/**
	 * @return the projectTotalCost
	 */
	public float getProjectTotalCost() {
		return projectTotalCost;
	}
	/**
	 * @param projectTotalCost the projectTotalCost to set
	 */
	public void setProjectTotalCost(float projectTotalCost) {
		this.projectTotalCost = projectTotalCost;
	}
	/**
	 * @return the projectNewFlag
	 */
	public boolean isProjectNewFlag() {
		return projectNewFlag;
	}
	/**
	 * @param projectNewFlag the projectNewFlag to set
	 */
	public void setProjectNewFlag(boolean projectNewFlag) {
		this.projectNewFlag = projectNewFlag;
	}
	/**
	 * @return the projectNewCost
	 */
	public float getProjectNewCost() {
		return projectNewCost;
	}
	/**
	 * @param projectNewCost the projectNewCost to set
	 */
	public void setProjectNewCost(float projectNewCost) {
		this.projectNewCost = projectNewCost;
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
	/**i
	 * @return the custContactNumber
	 */
	public String quanti;
	public String getQuanti() {
		return quanti;
	}
	public void setQuanti(String quanti) {
		this.quanti = quanti;
	}
	public String getCustContactNumber() {
		return custContactNumber;
	}
	/**
	 * @param custContactNumber the custContactNumber to set
	 */
	public void setCustContactNumber(String custContactNumber) {
		this.custContactNumber = custContactNumber;
	}
	/**
	 * @return the custEmailId
	 */
	public String getCustEmailId() {
		return custEmailId;
	}
	/**
	 * @param custEmailId the custEmailId to set
	 */
	public void setCustEmailId(String custEmailId) {
		this.custEmailId = custEmailId;
	}
	/**
	 * @return the custAddress
	 */
	public String getCustAddress() {
		return custAddress;
	}
	/**
	 * @param custAddress the custAddress to set
	 */
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	/**
	 * @return the custPincode
	 */
	public String getCustPincode() {
		return custPincode;
	}
	/**
	 * @param custPincode the custPincode to set
	 */
	public void setCustPincode(String custPincode) {
		this.custPincode = custPincode;
	}
	/**
	 * @return the consultantName
	 */
	public String getConsultantName() {
		return consultantName;
	}
	/**
	 * @param consultantName the consultantName to set
	 */
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	/**
	 * @return the consultantSpocName
	 */
	public String getConsultantSpocName() {
		return consultantSpocName;
	}
	/**
	 * @param consultantSpocName the consultantSpocName to set
	 */
	public void setConsultantSpocName(String consultantSpocName) {
		this.consultantSpocName = consultantSpocName;
	}
	/**
	 * @return the consultantEmail
	 */
	public String getConsultantEmail() {
		return consultantEmail;
	}
	/**
	 * @param consultantEmail the consultantEmail to set
	 */
	public void setConsultantEmail(String consultantEmail) {
		this.consultantEmail = consultantEmail;
	}
	/**
	 * @return the consultantAddress
	 */
	public String getConsultantAddress() {
		return consultantAddress;
	}
	/**
	 * @param consultantAddress the consultantAddress to set
	 */
	public void setConsultantAddress(String consultantAddress) {
		this.consultantAddress = consultantAddress;
	}
	/**
	 * @return the endUserName
	 */
	public String getEndUserName() {
		return endUserName;
	}
	/**
	 * @param endUserName the endUserName to set
	 */
	public void setEndUserName(String endUserName) {
		this.endUserName = endUserName;
	}
	/**
	 * @return the endUserContactNo
	 */
	public String getEndUserContactNo() {
		return endUserContactNo;
	}
	/**
	 * @param endUserContactNo the endUserContactNo to set
	 */
	public void setEndUserContactNo(String endUserContactNo) {
		this.endUserContactNo = endUserContactNo;
	}
	/**
	 * @return the endUserEmail
	 */
	public String getEndUserEmail() {
		return endUserEmail;
	}
	/**
	 * @param endUserEmail the endUserEmail to set
	 */
	public void setEndUserEmail(String endUserEmail) {
		this.endUserEmail = endUserEmail;
	}
	/**
	 * @return the endUserAddress
	 */
	public String getEndUserAddress() {
		return endUserAddress;
	}
	/**
	 * @param endUserAddress the endUserAddress to set
	 */
	public void setEndUserAddress(String endUserAddress) {
		this.endUserAddress = endUserAddress;
	}
	/**
	 * @return the regionCode
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the custStatus
	 */
	public String getCustStatus() {
		return custStatus;
	}
	/**
	 * @param custStatus the custStatus to set
	 */
	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}
	/**
	 * @return the sfdcLink
	 */
	public String getSfdcLink() {
		return sfdcLink;
	}
	/**
	 * @param sfdcLink the sfdcLink to set
	 */
	public void setSfdcLink(String sfdcLink) {
		this.sfdcLink = sfdcLink;
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
	 * @return the capacity
	 */
	public float getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}
	/**
	 * @return the quotNumber
	 */
	public String getQuotNumber() {
		return quotNumber;
	}
	/**
	 * @param quotNumber the quotNumber to set
	 */
	public void setQuotNumber(String quotNumber) {
		this.quotNumber = quotNumber;
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
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the cOldNumber
	 */
	public String getCOldNumber() {
		return COldNumber;
	}
	/**
	 * @param cOldNumber the cOldNumber to set
	 */
	public void setCOldNumber(String cOldNumber) {
		COldNumber = cOldNumber;
	}
	/**
	 * @return the cNewNumber
	 */
	public String getCNewNumber() {
		return CNewNumber;
	}
	/**
	 * @param cNewNumber the cNewNumber to set
	 */
	public void setCNewNumber(String cNewNumber) {
		CNewNumber = cNewNumber;
	}
	/**
	 * @return the orientationType
	 */
	public String getOrientationType() {
		return orientationType;
	}
	/**
	 * @param orientationType the orientationType to set
	 */
	public void setOrientationType(String orientationType) {
		this.orientationType = orientationType;
	}
	/**
	 * @return the isNewProject
	 */
	public int getIsNewProject() {
		return isNewProject;
	}
	/**
	 * @param isNewProject the isNewProject to set
	 */
	public void setIsNewProject(int isNewProject) {
		this.isNewProject = isNewProject;
	}
	/**
	 * @return the percentageVariation
	 */
	public float getPercentageVariation() {
		return percentageVariation;
	}
	/**
	 * @param percentageVariation the percentageVariation to set
	 */
	public void setPercentageVariation(float percentageVariation) {
		this.percentageVariation = percentageVariation;
	}
	/**
	 * @return the bleedType
	 */
	public String getBleedType() {
		return bleedType;
	}
	/**
	 * @param bleedType the bleedType to set
	 */
	public void setBleedType(String bleedType) {
		this.bleedType = bleedType;
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
	 * @return the assignedToName
	 */
	public String getAssignedToName() {
		return assignedToName;
	}
	/**
	 * @param assignedToName the assignedToName to set
	 */
	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}
	/**
	 * @return the assignedEmpId
	 */
	public String getAssignedEmpId() {
		return assignedEmpId;
	}
	/**
	 * @param assignedEmpId the assignedEmpId to set
	 */
	public void setAssignedEmpId(String assignedEmpId) {
		this.assignedEmpId = assignedEmpId;
	}
	/**
	 * @return the assignedPhoneNumber
	 */
	public String getAssignedPhoneNumber() {
		return assignedPhoneNumber;
	}
	/**
	 * @param assignedPhoneNumber the assignedPhoneNumber to set
	 */
	public void setAssignedPhoneNumber(String assignedPhoneNumber) {
		this.assignedPhoneNumber = assignedPhoneNumber;
	}
	/**
	 * @return the assignedEmail
	 */
	public String getAssignedEmail() {
		return assignedEmail;
	}
	/**
	 * @param assignedEmail the assignedEmail to set
	 */
	public void setAssignedEmail(String assignedEmail) {
		this.assignedEmail = assignedEmail;
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
	 * @return the creatorEmail
	 */
	public String getCreatorEmail() {
		return creatorEmail;
	}
	/**
	 * @param creatorEmail the creatorEmail to set
	 */
	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}
	/**
	 * @return the creatorEmpId
	 */
	public String getCreatorEmpId() {
		return creatorEmpId;
	}
	/**
	 * @param creatorEmpId the creatorEmpId to set
	 */
	public void setCreatorEmpId(String creatorEmpId) {
		this.creatorEmpId = creatorEmpId;
	}
	/**
	 * @return the creatorPhoneNumber
	 */
	public String getCreatorPhoneNumber() {
		return creatorPhoneNumber;
	}
	/**
	 * @param creatorPhoneNumber the creatorPhoneNumber to set
	 */
	public void setCreatorPhoneNumber(String creatorPhoneNumber) {
		this.creatorPhoneNumber = creatorPhoneNumber;
	}
	/**
	 * @return the creatorDeptName
	 */
	public String getCreatorDeptName() {
		return creatorDeptName;
	}
	/**
	 * @param creatorDeptName the creatorDeptName to set
	 */
	public void setCreatorDeptName(String creatorDeptName) {
		this.creatorDeptName = creatorDeptName;
	}
	/**
	 * @return the userRoleName
	 */
	public String getUserRoleName() {
		return userRoleName;
	}
	/**
	 * @param userRoleName the userRoleName to set
	 */
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	/**
	 * @return the quatity
	 */
	public String getQuatity() {
		return quatity;
	}
	/**
	 * @param quatity the quatity to set
	 */
	public void setQuatity(String quatity) {
		this.quatity = quatity;
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
	 * @return the isEndUserReq
	 */
	public int getIsEndUserReq() {
		return isEndUserReq;
	}
	/**
	 * @param isEndUserReq the isEndUserReq to set
	 */
	public void setIsEndUserReq(int isEndUserReq) {
		this.isEndUserReq = isEndUserReq;
	}
	/**
	 * @return the typeOfOffer
	 */
	public int getTypeOfOffer() {
		return typeOfOffer;
	}
	/**
	 * @param typeOfOffer the typeOfOffer to set
	 */
	public void setTypeOfOffer(int typeOfOffer) {
		this.typeOfOffer = typeOfOffer;
	}
	/**
	 * @return the typeOfQuot
	 */
	public int getTypeOfQuot() {
		return typeOfQuot;
	}
	/**
	 * @param typeOfQuot the typeOfQuot to set
	 */
	public void setTypeOfQuot(int typeOfQuot) {
		this.typeOfQuot = typeOfQuot;
	}
	/**
	 * @return the typeOfOfferNm
	 */
	public String getTypeOfOfferNm() {
		return typeOfOfferNm;
	}
	/**
	 * @param typeOfOfferNm the typeOfOfferNm to set
	 */
	public void setTypeOfOfferNm(String typeOfOfferNm) {
		this.typeOfOfferNm = typeOfOfferNm;
	}
	/**
	 * @return the typeOfQuotNm
	 */
	public String getTypeOfQuotNm() {
		return typeOfQuotNm;
	}
	/**
	 * @param typeOfQuotNm the typeOfQuotNm to set
	 */
	public void setTypeOfQuotNm(String typeOfQuotNm) {
		this.typeOfQuotNm = typeOfQuotNm;
	}
	/**
	 * @return the typeOfInjection
	 */
	public int getTypeOfInjection() {
		return typeOfInjection;
	}
	/**
	 * @param typeOfInjection the typeOfInjection to set
	 */
	public void setTypeOfInjection(int typeOfInjection) {
		this.typeOfInjection = typeOfInjection;
	}
	/**
	 * @return the typeOfVarient
	 */
	public int getTypeOfVarient() {
		return typeOfVarient;
	}
	/**
	 * @param typeOfVarient the typeOfVarient to set
	 */
	public void setTypeOfVarient(int typeOfVarient) {
		this.typeOfVarient = typeOfVarient;
	}
	/**
	 * @return the typeOfInjectionNm
	 */
	public String getTypeOfInjectionNm() {
		return typeOfInjectionNm;
	}
	/**
	 * @param typeOfInjectionNm the typeOfInjectionNm to set
	 */
	public void setTypeOfInjectionNm(String typeOfInjectionNm) {
		this.typeOfInjectionNm = typeOfInjectionNm;
	}
	/**
	 * @return the typeOfVarientNm
	 */
	public String getTypeOfVarientNm() {
		return typeOfVarientNm;
	}
	/**
	 * @param typeOfVarientNm the typeOfVarientNm to set
	 */
	public void setTypeOfVarientNm(String typeOfVarientNm) {
		this.typeOfVarientNm = typeOfVarientNm;
	}
	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the stateNm
	 */
	public String getStateNm() {
		return stateNm;
	}
	/**
	 * @param stateNm the stateNm to set
	 */
	public void setStateNm(String stateNm) {
		this.stateNm = stateNm;
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
	 * @return the orientationTypeId
	 */
	public int getOrientationTypeId() {
		return orientationTypeId;
	}
	/**
	 * @param orientationTypeId the orientationTypeId to set
	 */
	public void setOrientationTypeId(int orientationTypeId) {
		this.orientationTypeId = orientationTypeId;
	}
	/**
	 * @return the bleedTypeId
	 */
	public int getBleedTypeId() {
		return bleedTypeId;
	}
	/**
	 * @param bleedTypeId the bleedTypeId to set
	 */
	public void setBleedTypeId(int bleedTypeId) {
		this.bleedTypeId = bleedTypeId;
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
	 * @return the custCodeNm
	 */
	public String getCustCodeNm() {
		return custCodeNm;
	}
	/**
	 * @param custCodeNm the custCodeNm to set
	 */
	public void setCustCodeNm(String custCodeNm) {
		this.custCodeNm = custCodeNm;
	}
	/**
	 * @return the scopeOfSupplyListDet
	 */
	public List<ScopeOfSupply> getScopeOfSupplyListDet() {
		return scopeOfSupplyListDet;
	}
	/**
	 * @param scopeOfSupplyListDet the scopeOfSupplyListDet to set
	 */
	public void setScopeOfSupplyListDet(List<ScopeOfSupply> scopeOfSupplyListDet) {
		this.scopeOfSupplyListDet = scopeOfSupplyListDet;
	}
	/**
	 * @return the scopeOfSupplyListF2fDet
	 */
	public List<ScopeOfSupply> getScopeOfSupplyListF2fDet() {
		return scopeOfSupplyListF2fDet;
	}
	/**
	 * @param scopeOfSupplyListF2fDet the scopeOfSupplyListF2fDet to set
	 */
	public void setScopeOfSupplyListF2fDet(List<ScopeOfSupply> scopeOfSupplyListF2fDet) {
		this.scopeOfSupplyListF2fDet = scopeOfSupplyListF2fDet;
	}
	/**
	 * @return the mechDetList
	 */
	public List<DBOBean> getMechDetList() {
		return mechDetList;
	}
	/**
	 * @param mechDetList the mechDetList to set
	 */
	public void setMechDetList(List<DBOBean> mechDetList) {
		this.mechDetList = mechDetList;
	}
	/**
	 * @return the pkgDetList
	 */
	public List<DBOBean> getPkgDetList() {
		return pkgDetList;
	}
	/**
	 * @param pkgDetList the pkgDetList to set
	 */
	public void setPkgDetList(List<DBOBean> pkgDetList) {
		this.pkgDetList = pkgDetList;
	}
	/**
	 * @return the ecDetList
	 */
	public List<DBOBean> getEcDetList() {
		return ecDetList;
	}
	/**
	 * @param ecDetList the ecDetList to set
	 */
	public void setEcDetList(List<DBOBean> ecDetList) {
		this.ecDetList = ecDetList;
	}
	/**
	 * @return the trnsDetList
	 */
	public List<DBOBean> getTrnsDetList() {
		return trnsDetList;
	}
	/**
	 * @param trnsDetList the trnsDetList to set
	 */
	public void setTrnsDetList(List<DBOBean> trnsDetList) {
		this.trnsDetList = trnsDetList;
	}
	/**
	 * @return the varDetList
	 */
	public List<DBOBean> getVarDetList() {
		return varDetList;
	}
	/**
	 * @param varDetList the varDetList to set
	 */
	public void setVarDetList(List<DBOBean> varDetList) {
		this.varDetList = varDetList;
	}
	/**
	 * @return the proDetList
	 */
	public List<DBOBean> getProDetList() {
		return proDetList;
	}
	/**
	 * @param proDetList the proDetList to set
	 */
	public void setProDetList(List<DBOBean> proDetList) {
		this.proDetList = proDetList;
	}
	/**
	 * @return the desSubItemName
	 */
	public String getDesSubItemName() {
		return desSubItemName;
	}
	/**
	 * @param desSubItemName the desSubItemName to set
	 */
	public void setDesSubItemName(String desSubItemName) {
		this.desSubItemName = desSubItemName;
	}
	/**
	 * @return the quantityEle
	 */
	public int getQuantityEle() {
		return quantityEle;
	}
	/**
	 * @param quantityEle the quantityEle to set
	 */
	public void setQuantityEle(int quantityEle) {
		this.quantityEle = quantityEle;
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
	public List<ReportBean> getTechnicalReportData() {
		return technicalReportData;
	}
	public void setTechnicalReportData(List<ReportBean> technicalReportData) {
		this.technicalReportData = technicalReportData;
	}
	public String getDesItemName() {
		return desItemName;
	}
	public String getDesColOrderId() {
		return desColOrderId;
	}
	public void setDesItemName(String desItemName) {
		this.desItemName = desItemName;
	}
	public int getDesSubItemOrderId() {
		return desSubItemOrderId;
	}
	public void setDesSubItemOrderId(int desSubItemOrderId) {
		this.desSubItemOrderId = desSubItemOrderId;
	}
	public List<ReportBean> getIndexList() {
		return indexList;
	}
	public void setIndexList(List<ReportBean> indexList) {
		this.indexList = indexList;
	}
	public List<ReportBean> getScopeList() {
		return scopeList;
	}
	public void setScopeList(List<ReportBean> scopeList) {
		this.scopeList = scopeList;
	}
	public String getSubColValCode() {
		return subColValCode;
	}
	public void setSubColValCode(String subColValCode) {
		this.subColValCode = subColValCode;
	}
	
}
