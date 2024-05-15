
package com.ttl.ito.business.dao;
import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;



public interface UpdatePriceDao {
	
	public QuotationForm updatePriceTransport(QuotationForm quotationForm);

	public QuotationForm getUpdatePriceReqGrid(QuotationForm quotationForm);

	public QuotationForm updateStatus(QuotationForm quotationForm);

	public AdminForm getECUpdatePriceData(AdminForm adminForm);

	public QuotationForm getEditUpdatePriceData(QuotationForm quotationForm);

	public QuotationForm createECPriceUpdateRequest(QuotationForm quotationForm);
	
	//public QuotationForm createTurbInstrUpdateRequest(QuotationForm quotationForm);

	public QuotationForm updatePackagePrice(QuotationForm quotationForm);

	public QuotationForm saveUpdatedPrice(QuotationForm quotationForm);

	

	

	public QuotationForm getPackageWithPriceList(QuotationForm quotationForm);

	public QuotationForm updatePriceTransportExport(QuotationForm quotationForm);
	
	public QuotationForm updatePriceTransportDomestic(QuotationForm quotationForm);

	public AdminForm getNewFramesForNoOfVehicles(AdminForm adminForm);

	public QuotationForm getF2FUBOMast(QuotationForm quotationForm);

	public QuotationForm createUBOSheet(QuotationForm quotationForm);

	//public QuotationForm createOverheadSheet(QuotationForm quotationForm);

	//public QuotationForm createTurbineInstruments(QuotationForm quotationForm);

	//public QuotationForm createSubContracting(QuotationForm quotationForm);

	public AdminForm getNewFramesForUBO(AdminForm adminForm);

	public QuotationForm createDboEleUpdateRequestPrice(QuotationForm quotationForm);
	
	public QuotationForm createDboEleUpdateRequestPriceAddInstr(QuotationForm quotationForm);
	
	public QuotationForm createDboEleUpdateRequestPriceSplAddOn(QuotationForm quotationForm);
	
public DBOForm createDboEleUpdateRequestPriceAddOn(DBOForm dboForm);
	


	public DBOForm createDboMechUpdateRequestPrice(DBOForm dboForm);

public DBOForm createDboMechUpdateRequestCol(DBOForm dboForm);

	public QuotationForm createDboEleUpdateRequestCol(QuotationForm quotationForm);

	public DBOForm getDBOEleUpdatePriceData(DBOForm dboForm);

	public DBOForm getDBOMechUpdatePriceData(DBOForm dboForm);

	public DBOForm getDBOEleUpdateColData(DBOForm dboForm);

	public DBOForm getDBOMechUpdateColData(DBOForm dboForm);
	
	public DBOForm getDBOMechUpdateColData1(Integer itemId);

	public DBOForm getDBOEleUpdateColData1(Integer itemId, String panelCd, DBOForm dboform);

	public DBOForm createUpdateProbes(DBOForm dboForm);

	 public DBOForm createUpdateAddon(DBOForm dboForm);
	 public DBOForm createUpdateAddon1(DBOForm dboForm);
	 
	public DBOForm updateGetAddOn(DBOForm dboForm);
	

	public AdminForm getTransportUpdatedPriceListDomestic(AdminForm adminForm);

	public AdminForm getTransportUpdatedPriceListExport(AdminForm adminForm);
	
	
	
}
