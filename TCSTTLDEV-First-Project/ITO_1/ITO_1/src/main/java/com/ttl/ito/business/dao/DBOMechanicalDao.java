package com.ttl.ito.business.dao;

import org.springframework.stereotype.Repository;

import com.ttl.ito.business.beans.DBOBean;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;

@Repository
public interface DBOMechanicalDao {

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
	

	public DBOForm getF2fItems(DBOForm dboForm);
	
	public DBOForm getComercial(DBOForm dboForm);

	public DBOForm getF2fPanels(DBOForm dboForm);

	public DBOForm getF2fTechPrice(DBOForm dboForm);

	public DBOForm saveF2fItem(DBOForm dboForm);
	
	public DBOForm saveComercial(DBOForm dboForm);
	
	public DBOForm saveF2fSubItem(DBOForm dboForm);
	
	public DBOForm saveF2fSubItemType(DBOForm dboForm);
	
	public DBOForm getF2fAddOn(DBOForm DBOForm);

	public DBOForm getF2fPriceAddOn(DBOForm DBOForm);
	
	public DBOForm getGeneralInput(DBOForm DBOForm);
	
	public DBOForm saveGeneralInput(DBOForm dboForm);
	
	public DBOForm getMechItems(DBOForm dboForm);

	public DBOForm getMechPanels(DBOForm dboForm);
	
	public DBOForm getMechTechPrice(DBOForm dboForm);
	
	public DBOForm saveMechItem(DBOForm dboForm);
	
	public DBOForm saveMechSubItem(DBOForm dboForm);
	
	public DBOForm getMechAuxItems(DBOForm dboForm);

	public DBOForm getMechAuxPanels(DBOForm dboForm);
	
	public DBOForm getMechAuxTechPrice(DBOForm dboForm);
	
	public DBOForm saveMechAuxItem(DBOForm dboForm);
	
	public DBOForm saveMechAuxExtScope(DBOForm dboForm);
	
	public DBOForm removeDboMechAuxItem(Integer quotId, Integer itemId, Integer subItemId);
	
	public DBOForm getEleFilter(DBOForm dboForm);

	public DBOForm saveEleFilter(DBOForm dboForm);
	
	public DBOForm getEleItems(DBOForm dboForm);
	
	public DBOForm getUpdateGeneralInput(DBOForm dboForm);
	
	
	
	public DBOForm getElePanels(DBOForm dboForm);
	
	public DBOForm getEleTechPrice(DBOForm dboForm);
	
	public DBOForm getEleRefreshPanel(DBOForm dboForm);
	
	public DBOForm saveEleItem(DBOForm dboForm);
	
	public DBOForm getEleSpecialFilter(DBOForm dboForm);

	public DBOForm saveEleSpecialFilter(DBOForm dboForm);
	
	public DBOForm saveCIExtScope(DBOForm dboForm);
	
public DBOForm getUpdateCreateF2fColVal(DBOForm dboForm);
	
	public DBOForm getUpdateCreateF2fPrice(DBOForm dboForm);
	
	public DBOForm createUpdateF2fShopConv(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechPrice(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechAddOnCost(DBOForm dboForm);
	
	public DBOForm getUpdateCreateF2fFrameSpecData(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechAuxFrameSpecData(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechAuxColVal(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechAuxPrice(DBOForm dboForm);
	
	public DBOForm getUpdateCreateMechOverTank(DBOForm dboForm);
	
	
	
	public DBOForm saveEleExtScope(DBOForm dboForm);
	
	public DBOForm getEleInstr(DBOForm dboForm);
	
	public DBOForm saveEleSubItem(DBOForm dboForm);
	
	public DBOForm saveAdditionalInstrumentation(DBOForm dboForm);
	
	public DBOForm getEleVms(DBOForm dboForm);
	
	public DBOForm getAvr(DBOForm dboForm);
	
	public DBOForm getGoverner(DBOForm dboForm);
	public DBOForm getVmsCache(DBOForm dboForm);
	public DBOForm saveEleVms(DBOForm dboForm);
	
	public DBOForm savePerformance(DBOForm dboForm);
	
	public DBOForm getPerformance(DBOForm dboForm);
	
	public DBOForm getPerformanceParam(DBOForm dboForm);
	
	public DBOForm getOtherChapter(DBOForm dboForm);
	
	public DBOForm saveOtherChapter(DBOForm dboForm);
	
	public DBOForm saveAttachment(DBOForm dboForm);
}
