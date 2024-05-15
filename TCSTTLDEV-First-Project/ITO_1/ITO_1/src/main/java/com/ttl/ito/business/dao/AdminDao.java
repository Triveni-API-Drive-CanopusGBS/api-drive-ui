
package com.ttl.ito.business.dao;

import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;

public interface AdminDao {

	public AdminForm addOrEditQuesAnswer(AdminForm adminForm);

	public AdminForm addOrEditDepartment(AdminForm adminForm);
	
	public AdminForm getAdminBgmCalc(AdminForm adminForm);

	public AdminForm getAdminPerfAux(AdminForm adminForm);

	public AdminForm getAdminPerfOther(AdminForm adminForm);
	
	public AdminForm getAdminPerfAcDcMast(AdminForm adminForm);
	
	public AdminForm getAdminPerfAcDcFrmInput(AdminForm adminForm);
	
	public AdminForm getAdminSparesMast(AdminForm adminForm);
	
	public AdminForm getAdminSubSupplierMast(AdminForm adminForm);
	
	public AdminForm getAdminQualityMast(AdminForm adminForm);
	
	public AdminForm getAdminTerminalMast(AdminForm adminForm);
	
	public AdminForm getAdminExclusionMast(AdminForm adminForm);
	
	public AdminForm getAdminTenderDrawingMast(AdminForm adminForm);
	
	public AdminForm getAdminServiceMast(AdminForm adminForm);
	
	public AdminForm getAdminComrMonths(AdminForm adminForm);
	
	public AdminForm getAdminAcwr(AdminForm adminForm);
	
	public AdminForm getAdminEleItems(AdminForm adminForm);
	
	public AdminForm getAdminDcmPowerCalc(AdminForm adminForm);
	
	public AdminForm getAdminItemMast(AdminForm adminForm);
	
	public AdminForm getAdminOthersMast(AdminForm adminForm);

	public AdminForm addOrEditRegion(AdminForm adminForm);

	public AdminForm addOrEditRole(AdminForm adminForm);

	public AdminForm AddOrEditFrame(AdminForm adminForm);

	public AdminForm getAllF2FQues(AdminForm adminForm);

	public AdminForm addOrEditVehicle(AdminForm adminForm);

	public AdminForm addOrEditTransportPlace(AdminForm adminForm);

	public AdminForm addOrEditTransComponentwithPlace(AdminForm adminForm);

	public QuotationForm addOrEditVariantCode(QuotationForm quotationForm);

	public AdminForm addOrEditUSD(AdminForm adminForm);

	public AdminForm getQuestionsPage(AdminForm adminForm);

	public AdminForm getNewFramesForQuestions(AdminForm adminForm);

	public AdminForm getOnlyOldFramesForQuestions(AdminForm adminForm);

	public AdminForm getUSDValue(AdminForm adminForm);

	//public AdminForm editF2FTurbineInstruments(AdminForm adminForm);

	public QuotationForm getAdminCacheWithAIList(QuotationForm quotationForm);

	public QuotationForm getStatesList();

	public AdminForm getUserManual(AdminForm adminForm);

	public AdminForm downloadFile(Integer itemId);

	public AdminForm getAuditXml(String tableName);

	public AdminForm getAuditDate(String tableName);

	public AdminForm getAuditHistory1();
	
	public AdminForm getAuditHistoryDetails(AdminForm adminForm);
	
	public AdminForm getAdminPercentTrnsDm(AdminForm adminForm);
	
	public AdminForm getAdminPercentEc(AdminForm adminForm);
	
	public AdminForm getAdminPercentPkg(AdminForm adminForm);
	
	public AdminForm getAdminPercentTrnsEx(AdminForm adminForm);
	
	public AdminForm getAdminPercentEle(AdminForm adminForm);
	
	public AdminForm getAdminPercentMech(AdminForm adminForm);
	
	public AdminForm getAdminPercentF2F(AdminForm adminForm);
	
	public AdminForm getUpload(AdminForm adminForm);
	
	public AdminForm getGrid();

	public AdminForm getImage(AdminForm adminForm);
	
	public AdminForm getStdOffer(AdminForm adminForm);
	
	public AdminForm getValidateFinalCostNew(AdminForm adminForm);
	
	
}
