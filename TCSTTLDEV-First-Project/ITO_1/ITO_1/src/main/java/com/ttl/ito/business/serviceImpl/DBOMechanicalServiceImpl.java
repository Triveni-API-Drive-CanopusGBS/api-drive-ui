package com.ttl.ito.business.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.dao.DBOMechanicalDao;
import com.ttl.ito.business.dao.QuotationDao;
import com.ttl.ito.business.service.DBOMechanicalService;

@Service
public class DBOMechanicalServiceImpl implements DBOMechanicalService {

	private Logger logger = Logger.getLogger(DBOMechanicalServiceImpl.class);

	@Autowired
	DBOMechanicalDao dBOMechanicalDao;

	@Autowired
	QuotationDao quotationDao;

	@Override
	public DBOForm getDBOMechanicalData(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getDBOMechanicalData(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm getDBOMechPanel(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getDBOMechPanel(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm getDboMechanicalPrice(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getDboMechanicalPrice(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm getDBOElectricalData(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getDBOElectricalData(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm getDBOElectricalPanel(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getDBOElectricalPanel(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm getDboElectricalPrice(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getDboElectricalPrice(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm saveDBOMechanicalData(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveDBOMechanicalData(DBOForm);
			SaveBasicDetails saveBasicDetails = new SaveBasicDetails();
			saveBasicDetails.setQuotId(DBOForm.getQuotId());
			saveBasicDetails.setModifiedById(DBOForm.getModifiedById());
			saveBasicDetails.setUserRoleId(DBOForm.getUserRoleId());
			saveBasicDetails = quotationDao.saveQuotRemarks(saveBasicDetails, "OVERWRITE", "DBO_MECH", DBOForm.getOverwrittenPrice(), DBOForm.getRemarks());

			DBOForm.setSuccessCode(saveBasicDetails.getSuccessCode());
			DBOForm.setSuccessMsg(saveBasicDetails.getSuccessMsg());

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm saveDBOElectricalData(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveDBOElectricalData(DBOForm);

			SaveBasicDetails saveBasicDetails = new SaveBasicDetails();
			saveBasicDetails.setQuotId(DBOForm.getQuotId());
			saveBasicDetails.setModifiedById(DBOForm.getModifiedById());
			saveBasicDetails.setUserRoleId(DBOForm.getUserRoleId());
			saveBasicDetails = quotationDao.saveQuotRemarks(saveBasicDetails, "OVERWRITE", "DBO_ELE", DBOForm.getOverwrittenPrice(), DBOForm.getRemarks());

			// DBOForm.setSuccessCode(saveBasicDetails.getSuccessCode());
			// DBOForm.setSuccessMsg(saveBasicDetails.getSuccessMsg());

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm getDboMechanicalItems(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getDboMechanicalItems(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm getDboElectricalAddInstrPrice(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getDboElectricalAddInstrPrice(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm resetDboMechData(DBOForm DBOForm) {
		try {
			DBOForm = dBOMechanicalDao.resetDboMechData(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm resetDboEleData(DBOForm dboForm) {
		try {
			dboForm = dBOMechanicalDao.resetDboEleData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@Override
	public DBOForm getDboEleAddOn(DBOForm dboForm) {
		try {
			dboForm = dBOMechanicalDao.getDboEleAddOn(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@Override
	public DBOForm getDboElectricalPriceAddOn(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getDboElectricalPriceAddOn(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@Override
	public DBOForm getDboEleTotal(Integer quotId) {
		try {
			return dBOMechanicalDao.getDboEleTotal(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public DBOForm removeDboMechItem(Integer quotId, Integer itemId, Integer subItemId) {
		try {
			return dBOMechanicalDao.removeDboMechItem(quotId, itemId, subItemId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public DBOForm getDboMechTotal(Integer quotId) {
		try {
			return dBOMechanicalDao.getDboMechTotal(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public QuotationForm getDboEditData(Integer quotId) {
		try {
			return dBOMechanicalDao.getDboEditData(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public QuotationForm changeGeneralInput(String typeOfQuotation,String typeofPanel,String make,String dutySync){
		try {
			return dBOMechanicalDao.changeGeneralInput(typeOfQuotation,typeofPanel,make,dutySync);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	

	@Override
	public DBOForm getF2fItems(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getF2fItems(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	@Override
	public DBOForm getComercial(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getComercial(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getF2fPanels(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getF2fPanels(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@Override
	public DBOForm getF2fTechPrice(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getF2fTechPrice(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}

	@Override
	public DBOForm saveF2fItem(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveF2fItem(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveComercial(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveComercial(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	
	@Override
	public DBOForm saveF2fSubItem(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveF2fSubItem(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveF2fSubItemType(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveF2fSubItemType(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm getF2fAddOn(DBOForm dboForm) {
		try {
			dboForm = dBOMechanicalDao.getF2fAddOn(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@Override
	public DBOForm getF2fPriceAddOn(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getF2fPriceAddOn(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getGeneralInput(DBOForm dboForm) {
		
		try {
			return dBOMechanicalDao.getGeneralInput(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	
	@Override
	public DBOForm saveGeneralInput(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveGeneralInput(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm getMechItems(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getMechItems(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@Override
	public DBOForm getMechPanels(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getMechPanels(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getMechTechPrice(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getMechTechPrice(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveMechItem(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveMechItem(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveMechSubItem(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveMechSubItem(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	

	@Override
	public DBOForm getMechAuxItems(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getMechAuxItems(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}


	@Override
	public DBOForm getMechAuxPanels(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getMechAuxPanels(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@Override
	public DBOForm getMechAuxTechPrice(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getMechAuxTechPrice(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveMechAuxItem(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveMechAuxItem(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveMechAuxExtScope(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveMechAuxExtScope(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	

	@Override
	public DBOForm removeDboMechAuxItem(Integer quotId, Integer itemId, Integer subItemId) {
		try {
			return dBOMechanicalDao.removeDboMechAuxItem(quotId, itemId, subItemId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	
	@Override
	public DBOForm getEleFilter(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getEleFilter(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getEleInstr(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getEleInstr(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm saveEleFilter(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveEleFilter(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm getEleItems(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getEleItems(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getUpdateGeneralInput(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateGeneralInput(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	
	@Override
	public DBOForm getElePanels(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getElePanels(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getEleTechPrice(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getEleTechPrice(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm getEleRefreshPanel(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.getEleRefreshPanel(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveEleItem(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveEleItem(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm getEleSpecialFilter(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getEleSpecialFilter(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm saveEleSpecialFilter(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveEleSpecialFilter(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	
	@Override
	public DBOForm getUpdateCreateF2fColVal(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateF2fColVal(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getUpdateCreateF2fPrice(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateF2fPrice(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm createUpdateF2fShopConv(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.createUpdateF2fShopConv(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getUpdateCreateMechPrice(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateMechPrice(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	@Override
	public DBOForm getUpdateCreateMechAddOnCost(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateMechAddOnCost(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	@Override
	public DBOForm getUpdateCreateMechAuxColVal(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateMechAuxColVal(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	@Override
	public DBOForm getUpdateCreateMechAuxPrice(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateMechAuxPrice(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	@Override
	public DBOForm getUpdateCreateMechOverTank(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateMechOverTank(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getUpdateCreateF2fFrameSpecData(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateF2fFrameSpecData(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	@Override
	public DBOForm getUpdateCreateMechAuxFrameSpecData(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getUpdateCreateMechAuxFrameSpecData(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	
	
	
	@Override
	public DBOForm saveEleExtScope(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveEleExtScope(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveCIExtScope(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveCIExtScope(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	
	@Override
	public DBOForm saveAdditionalInstrumentation(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveAdditionalInstrumentation(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm saveEleSubItem(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveEleSubItem(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm getEleVms(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getEleVms(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getAvr(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getAvr(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getGoverner(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getGoverner(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getVmsCache(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getVmsCache(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm saveEleVms(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.saveEleVms(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm savePerformance(DBOForm DBOForm) {

		try {
			DBOForm = dBOMechanicalDao.savePerformance(DBOForm);

			return DBOForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return DBOForm;
		}
	}
	
	@Override
	public DBOForm getPerformance(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getPerformance(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getPerformanceParam(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getPerformanceParam(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm getOtherChapter(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.getOtherChapter(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm saveOtherChapter(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.saveOtherChapter(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@Override
	public DBOForm saveAttachment(DBOForm dboForm) {
		try {
			return dBOMechanicalDao.saveAttachment(dboForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
}
