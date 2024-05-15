package com.ttl.ito.business.dao;

import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.QuotationHomeGrid;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.beans.SaveBasicDetails;

/**
 * 
 * Created by Basavesh B R Interface Name: QuotationDao This class is used to
 * handle all Quotation related requests such as to fetch & save basic Details,
 * scope of supply etc., create quotation , get quotation details from DB, edit
 * quotation,
 * 
 * this interface has declarations for methods which interacts with Database
 */

public interface QuotationDao {

	public QuotationForm getQuotationHomeGrid(QuotationForm quotationForm);

	public QuotationForm fetchCacheData(QuotationForm quotationForm);

	public QuotationForm saveBasicDetails(QuotationForm quotationForm);
	
	public QuotationForm saveAs(QuotationForm quotationForm);

	public QuotationForm getQuestionsPage(QuotationForm quotationForm);

	public QuotationForm saveQuesDetails(QuotationForm quotationForm);

	public QuotationForm saveScopeOfSupplyDetails(QuotationForm quotationForm);
	
	public QuotationForm getF2fCache(QuotationForm quotationForm);

	public QuotationForm saveCProject(QuotationForm quotationForm);

	public QuotationForm getF2FTreeStructure(QuotationForm quotationForm);

	public QuotationForm editQuotationDetails(QuotationForm quotationForm);

	public QuotationForm getF2FOverHead(QuotationForm quotationForm);

	public QuotationForm getF2FShopCon(QuotationForm quotationForm);

	public QuotationForm getTurbuineClone(QuotationForm quotationForm);

	public QuotationForm fetchQuotCacheData(QuotationForm quotationForm);

//	public QuotationForm getAddOnPrice(QuotationForm quotationForm);

//	public QuotationForm saveAddOnPrice(QuotationForm quotationForm);

	public QuotationHomeGrid assignToOthers(QuotationHomeGrid quotationHomeGrid);

	public QuotationForm getSelectedQuestionsPage(QuotationForm quotationForm);

	public QuotationForm getOneLineBom(QuotationForm quotationForm);

	public QuotationForm getCompleteCostSheetData(QuotationForm quotationForm);

	public QuotationForm getQuotation(QuotationForm quotationForm);

	public QuotationForm editOneLineBom(QuotationForm quotationForm);

	public QuotationForm getQuotOneLineBom(QuotationForm quotationForm);

	public QuotationForm getTransportationCache(QuotationForm quotationForm);

	public QuotationForm getTransportDataBasedOnFrame(QuotationForm quotationForm);

	public SaveBasicDetails saveTransportationData(SaveBasicDetails saveBasicDetails);

	public QuotationForm getErrectionCommCache(QuotationForm quotationForm);

	public QuotationForm getErecCommData(QuotationForm quotationForm);

	public QuotationForm saveErecCommission(QuotationForm quotationForm);

	public QuotationForm getPackageData(QuotationForm quotationForm);

	public QuotationForm savePackageData(QuotationForm quotationForm);

	public QuotationForm getExcelCostSheetData(QuotationForm quotationForm);
	
	public QuotationForm getValidateFinalCost(QuotationForm quotationForm);

	public QuotationForm getNewFramesForExport(QuotationForm quotationForm);
	
	public QuotationForm getDocument();

	public QuotationForm getTransportPrice(QuotationForm quotationForm);

	public QuotationForm saveProjectCost(QuotationForm quotationForm);

	public QuotationForm saveVariableCost(QuotationForm quotationForm);

	public QuotationForm saveSparesCost(QuotationForm quotationForm);

	public QuotationForm getProjectCost(QuotationForm quotationForm);

	public QuotationForm getSparesCost(QuotationForm quotationForm);

	public QuotationForm getVariableCost(QuotationForm quotationForm);

	public SaveBasicDetails quotWorkFlow(SaveBasicDetails saveBasicDetails);

	public SaveBasicDetails scopeOfSupplyStatus(SaveBasicDetails saveBasicDetails);

	public SaveBasicDetails saveF2FSap(SaveBasicDetails saveBasicDetails);

	public SaveBasicDetails getF2FSapData(SaveBasicDetails saveBasicDetails);
	
	public SaveBasicDetails saveRemarks(SaveBasicDetails saveBasicDetails);


	public SaveBasicDetails getQuotRemarks(SaveBasicDetails saveBasicDetails);

	public SaveBasicDetails saveQuotRemarks(SaveBasicDetails saveBasicDetails, String groupCode, String scopeCode, float overwrittenCost, String remarks);

	public SaveBasicDetails getF2FUboData(SaveBasicDetails saveBasicDetails);

	public SaveBasicDetails saveF2FUboData(SaveBasicDetails saveBasicDetails);

	public QuotationForm createQuotRev(QuotationForm quotationForm);

	public QuotationForm getQuotRevData(QuotationForm quotationForm);

	public QuotationForm getQuotRevNo(Integer quotId);
	
	public QuotationForm getScopeOfSupStatus(QuotationForm quotationForm);

	public QuotationForm getPackageCache(QuotationForm quotationForm);

	public QuotationForm getQuotTransCache(QuotationForm quotationForm);

	public QuotationForm fetchUserData();

	public ReportBean getReportData(QuotationForm quotationForm,ReportBean reportBean);
	
	public ReportBean getAddOnReportData(QuotationForm quotationForm,ReportBean reportBean);
	
	public ReportBean getWordData(QuotationForm quotationForm,ReportBean reportBean);
	
	public ReportBean getComercialWordData(QuotationForm quotationForm,ReportBean reportBean);
	
public ReportBean getReportDataRev(QuotationForm quotationForm,ReportBean reportBean);
	
	public ReportBean getAddOnReportDataRev(QuotationForm quotationForm,ReportBean reportBean);
	
	public ReportBean getWordDataRev(QuotationForm quotationForm,ReportBean reportBean);
	
	public ReportBean getComercialWordDataRev(QuotationForm quotationForm,ReportBean reportBean);
	
	public ReportBean getComercialWordDataNew(QuotationForm quotationForm,ReportBean reportBean);
	
	public ReportBean getComercialWordDataNewRev(QuotationForm quotationForm,ReportBean reportBean);

	public SaveBasicDetails quotStatusComplete(SaveBasicDetails saveBasicDetails);

	public QuotationForm getVarCostDet(Integer quotId);

	public	QuotationForm getQuestionInfo(Integer framePowerId);
	
	public QuotationForm saveScopeOfSupplyDetailsNew(Integer quotId);
	

	public ReportBean getTechReportData(QuotationForm quotationForm,ReportBean reportBean);
	
	
	public QuotationForm downloadPdf(Integer quotId);

}
