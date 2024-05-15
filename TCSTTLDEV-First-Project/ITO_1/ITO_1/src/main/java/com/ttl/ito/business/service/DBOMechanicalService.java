package com.ttl.ito.business.service;

import org.springframework.stereotype.Service;

import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;

@Service
public interface DBOMechanicalService {

	public DBOForm getDBOMechanicalData(DBOForm dboForm);

	public DBOForm getDBOMechPanel(DBOForm dboForm);

	public DBOForm getDboMechanicalPrice(DBOForm dboForm);

	public DBOForm getDBOElectricalData(DBOForm dboForm);

	public DBOForm getDBOElectricalPanel(DBOForm dboForm);

	public DBOForm getDboElectricalPrice(DBOForm dboForm);

	public DBOForm saveDBOMechanicalData(DBOForm dboForm);

	public DBOForm saveDBOElectricalData(DBOForm dboForm);

	public DBOForm getDboMechanicalItems(DBOForm dboForm);

	public DBOForm resetDboMechData(DBOForm dboForm);

	public DBOForm resetDboEleData(DBOForm dboForm);

	public DBOForm getDboElectricalAddInstrPrice(DBOForm DBOForm);

	public DBOForm getDboEleAddOn(DBOForm DBOForm);

	public DBOForm getDboElectricalPriceAddOn(DBOForm DBOForm);

	public DBOForm getDboEleTotal(Integer quotId);

	public DBOForm removeDboMechItem(Integer quotId, Integer itemId, Integer subItemId);

	public DBOForm getDboMechTotal(Integer quotId);

	public QuotationForm getDboEditData(Integer quotId);
	
	public QuotationForm changeGeneralInput(String typeOfQuotation,String typeofPanel,String make,String dutySync);
	
	public DBOForm getF2fItems(DBOForm DBOForm);
	
	public DBOForm getComercial(DBOForm DBOForm);

	public DBOForm getF2fPanels(DBOForm DBOForm);

	public DBOForm getF2fTechPrice(DBOForm DBOForm);

	public DBOForm saveF2fItem(DBOForm DBOForm);
	
	public DBOForm saveComercial(DBOForm DBOForm);
	
	public DBOForm saveF2fSubItem(DBOForm DBOForm);
	
	public DBOForm saveF2fSubItemType(DBOForm DBOForm);
	
	public DBOForm getF2fAddOn(DBOForm DBOForm);

	public DBOForm getF2fPriceAddOn(DBOForm DBOForm);

	public DBOForm getGeneralInput(DBOForm DBOForm);
	
	public DBOForm saveGeneralInput(DBOForm DBOForm);
	
	public DBOForm getMechItems(DBOForm DBOForm);

	public DBOForm getMechPanels(DBOForm DBOForm);
	
	public DBOForm getMechTechPrice(DBOForm DBOForm);
	
	public DBOForm saveMechItem(DBOForm DBOForm);
	
	public DBOForm saveMechSubItem(DBOForm DBOForm);
	
	public DBOForm getMechAuxItems(DBOForm DBOForm);

	public DBOForm getMechAuxPanels(DBOForm DBOForm);
	
	public DBOForm getMechAuxTechPrice(DBOForm DBOForm);
	
	public DBOForm saveMechAuxItem(DBOForm DBOForm);
	
	public DBOForm saveMechAuxExtScope(DBOForm DBOForm);

	public DBOForm removeDboMechAuxItem(Integer quotId, Integer itemId, Integer subItemId);
	
	public DBOForm getEleFilter(DBOForm DBOForm);
	
	public DBOForm saveEleFilter(DBOForm DBOForm);
	
	public DBOForm getEleItems(DBOForm DBOForm);
	
	public DBOForm getUpdateGeneralInput(DBOForm DBOForm);
	
public DBOForm getUpdateCreateF2fColVal(DBOForm dboForm);
	
	public DBOForm getUpdateCreateF2fPrice(DBOForm dboForm);
	
	public DBOForm createUpdateF2fShopConv(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechPrice(DBOForm dboForm);
	
	public DBOForm getUpdateCreateF2fFrameSpecData(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechAuxFrameSpecData(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechAddOnCost(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechAuxColVal(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechAuxPrice(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechOverTank(DBOForm dboForm);

	
	public DBOForm getElePanels(DBOForm DBOForm);
	
	public DBOForm getEleTechPrice(DBOForm DBOForm);
	
	public DBOForm getEleRefreshPanel(DBOForm DBOForm);
	
	public DBOForm saveEleItem(DBOForm DBOForm);
	
	public DBOForm getEleSpecialFilter(DBOForm DBOForm);
	
	public DBOForm saveEleSpecialFilter(DBOForm DBOForm);
	
	public DBOForm saveCIExtScope(DBOForm DBOForm);
	
	public DBOForm saveEleExtScope(DBOForm DBOForm);
	
	public DBOForm getEleInstr(DBOForm DBOForm);

	public DBOForm saveAdditionalInstrumentation(DBOForm DBOForm);
	
	public DBOForm saveEleSubItem(DBOForm DBOForm);
	
	public DBOForm getEleVms(DBOForm DBOForm);
	
	public DBOForm getAvr(DBOForm DBOForm);
	
	public DBOForm getGoverner(DBOForm DBOForm);
	
	public DBOForm getVmsCache(DBOForm DBOForm);
	
	public DBOForm saveEleVms(DBOForm DBOForm);
	
	public DBOForm savePerformance(DBOForm DBOForm);
	
	public DBOForm getPerformance(DBOForm DBOForm);
	
	public DBOForm getPerformanceParam(DBOForm DBOForm);
	
	public DBOForm getOtherChapter(DBOForm DBOForm);
	
	
	public DBOForm saveOtherChapter(DBOForm DBOForm);
	
	public DBOForm saveAttachment(DBOForm DBOForm);
}
