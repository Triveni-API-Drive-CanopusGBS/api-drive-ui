
package com.ttl.ito.business.serviceImpl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.dao.UpdatePriceDao;
import com.ttl.ito.business.service.UpdatePriceService;
import com.ttl.ito.common.Utility.SendMail;


@Service
public class UpdatePriceServiceImpl implements UpdatePriceService {

	private Logger logger = Logger.getLogger(UpdatePriceServiceImpl.class);

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Autowired
	private UpdatePriceDao updatePriceDao;

	@Autowired
	private SendMail sendMail;
	
	@Override
	public QuotationForm updatePriceTransport(QuotationForm quotationForm) {

		try {
			quotationForm = updatePriceDao.updatePriceTransport(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm saveTransportPrice(QuotationForm quotationForm) {

		try {
			quotationForm = updatePriceDao.saveUpdatedPrice(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public QuotationForm saveECUpdatedPrice(QuotationForm quotationForm) {
	
		try {
			quotationForm = updatePriceDao.saveUpdatedPrice(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	

	@Override
	public QuotationForm getUpdatePriceReqGrid(QuotationForm quotationForm) {

		try {
			quotationForm = updatePriceDao.getUpdatePriceReqGrid(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm updateStatus(QuotationForm quotationForm) {
		
		try {
			quotationForm = updatePriceDao.updateStatus(quotationForm);
			java.io.File dir = new java.io.File("");
			java.io.File[] attachments= dir.listFiles();
			StringBuffer mailBody = new StringBuffer();
			String toAddress = quotationForm.getEmail();
			mailBody.append("Hi "+quotationForm.getName()+",");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Request Number : "+ quotationForm.getSaveBasicDetails().getUpdateRequestNumber()+ " is pending with you, kindly please approve it.");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Thanks and Regards, ");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("ITO Team");
			String mailText=mailBody.toString();
			logger.info("Level1");
			logger.info(quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
			logger.info(toAddress);
			logger.info(quotationForm.getName());
			sendMail.send(new String[] {toAddress}, null, null, "Request Number : "+ quotationForm.getSaveBasicDetails().getUpdateRequestNumber()+ " Pending Approval" , mailText,attachments);
			logger.info("Level2");
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public AdminForm getECUpdatePriceData(AdminForm adminForm) {
	
		try {
			adminForm = updatePriceDao.getECUpdatePriceData(adminForm);
			
			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public QuotationForm createECPriceUpdateRequest(QuotationForm quotationForm) {
	
		try {
			quotationForm = updatePriceDao.createECPriceUpdateRequest(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	/*@Override
	public QuotationForm createTurbInstrUpdateRequest(QuotationForm quotationForm) {
	
		try {
			quotationForm = updatePriceDao.createTurbInstrUpdateRequest(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}*/

	@Override
	public QuotationForm editUpdatePriceData(QuotationForm quotationForm) {
		
		try {
			quotationForm = updatePriceDao.getEditUpdatePriceData(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm savePkgUpdatedPrice(QuotationForm quotationForm) {
		
		try {
			quotationForm = updatePriceDao.saveUpdatedPrice(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public AdminForm getTransportUpdatedPriceListDomestic(AdminForm adminForm) {
		
		try {
			adminForm = updatePriceDao.getTransportUpdatedPriceListDomestic(adminForm);
			
			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getTransportUpdatedPriceListExport(AdminForm adminForm) {
		
		try {
			adminForm = updatePriceDao.getTransportUpdatedPriceListExport(adminForm);
			
			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}


	@Override
	public QuotationForm updatePackagePrice(QuotationForm quotationForm) {
		
		try {
			quotationForm = updatePriceDao.updatePackagePrice(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm updatePriceTransportDomestic(QuotationForm quotationForm) {
	
		try {
			quotationForm = updatePriceDao.updatePriceTransportDomestic(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm saveUpdatedNoOfVehicles(QuotationForm quotationForm) {
		
		try {
			quotationForm = updatePriceDao.saveUpdatedPrice(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm getPackageWithPriceList(QuotationForm quotationForm) {
	
		try {
			quotationForm = updatePriceDao.getPackageWithPriceList(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm updatePriceTransportExport(QuotationForm quotationForm) {

		try {
			quotationForm = updatePriceDao.updatePriceTransportExport(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public AdminForm getNewFramesForNoOfVehicles(AdminForm adminForm) {
		
		try {
			adminForm = updatePriceDao.getNewFramesForNoOfVehicles(adminForm);
			
			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public QuotationForm getF2FUBOMast(QuotationForm quotationForm) {
	
		try {
			quotationForm = updatePriceDao.getF2FUBOMast(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm createUBOSheet(QuotationForm quotationForm) {

		try {
			quotationForm = updatePriceDao.createUBOSheet(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	/*@Override
	public QuotationForm createOverheadSheet(QuotationForm quotationForm){
		
		try {
			quotationForm = updatePriceDao.createOverheadSheet(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}*/

	/*@Override
	public QuotationForm createTurbineInstruments(QuotationForm quotationForm){
		
		try {
			quotationForm = updatePriceDao.createTurbineInstruments(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}*/
	/*@Override
	public QuotationForm createSubContracting(QuotationForm quotationForm) {
		
		try {
			quotationForm = updatePriceDao.createSubContracting(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	
	}*/
	
	@Override
	public AdminForm getNewFramesForUBO(AdminForm adminForm){
		
		try {
			adminForm = updatePriceDao.getNewFramesForUBO(adminForm);
			
			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	
	}


	@Override
	public QuotationForm createDboEleUpdateRequestPrice(QuotationForm quotationForm) {
		try {
			quotationForm = updatePriceDao.createDboEleUpdateRequestPrice(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public QuotationForm createDboEleUpdateRequestPriceAddInstr(QuotationForm quotationForm) {
		try {
			quotationForm = updatePriceDao.createDboEleUpdateRequestPriceAddInstr(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public QuotationForm createDboEleUpdateRequestPriceSplAddOn(QuotationForm quotationForm) {
		try {
			quotationForm = updatePriceDao.createDboEleUpdateRequestPriceSplAddOn(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public DBOForm createDboEleUpdateRequestPriceAddOn(DBOForm dboForm) {
		try {
			dboForm = updatePriceDao.createDboEleUpdateRequestPriceAddOn(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm createDboMechUpdateRequestPrice(DBOForm dboForm) {
		try {
			dboForm = updatePriceDao.createDboMechUpdateRequestPrice(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}


	@Override
	public DBOForm createDboMechUpdateRequestCol(DBOForm dboForm) {
		try {
			dboForm = updatePriceDao.createDboMechUpdateRequestCol(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@Override
	public QuotationForm createDboEleUpdateRequestCol(QuotationForm quotationForm) {
		try {
			quotationForm = updatePriceDao.createDboEleUpdateRequestCol(quotationForm);
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public DBOForm getDBOEleUpdatePriceData(DBOForm dboForm) {
		try {
			dboForm = updatePriceDao.getDBOEleUpdatePriceData(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}


	@Override
	public DBOForm getDBOMechUpdatePriceData(DBOForm dboForm) {
		try {
			dboForm = updatePriceDao.getDBOMechUpdatePriceData(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}


	@Override
	public DBOForm getDBOEleUpdateColData(DBOForm dboForm) {
		try {
			dboForm = updatePriceDao.getDBOEleUpdateColData(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}


	@Override
	public DBOForm getDBOMechUpdateColData(DBOForm dboForm) {
		try {
			dboForm = updatePriceDao.getDBOMechUpdateColData(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
@Override
	public DBOForm getDBOEleUpdateColData1(Integer itemId,String panelCd,DBOForm dboForm) {
		
		try {
			dboForm = updatePriceDao.getDBOEleUpdateColData1(itemId, panelCd,dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getDBOMechUpdateColData1(Integer itemId) {
		try {
			return updatePriceDao.getDBOMechUpdateColData1(itemId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	

	@Override
	public DBOForm createUpdateProbes(DBOForm dboForm) {
		try {
			return updatePriceDao.createUpdateProbes(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public DBOForm createUpdateAddon(DBOForm dboForm) {
		try {
			return updatePriceDao.createUpdateAddon(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public DBOForm createUpdateAddon1(DBOForm dboForm) {
		try {
			return updatePriceDao.createUpdateAddon1(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}


	@Override
	public DBOForm updateGetAddOn(DBOForm dboForm) {
		try {
			return updatePriceDao.updateGetAddOn(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
}
