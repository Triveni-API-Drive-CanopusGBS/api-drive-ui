
package com.ttl.ito.business.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.dao.AdminDao;
import com.ttl.ito.business.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private Logger logger = Logger.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminDao adminDao;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Override
	public AdminForm addOrEditQuesAnswer(AdminForm adminForm) {

		try {
			adminForm = adminDao.addOrEditQuesAnswer(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm addOrEditDepartment(AdminForm adminForm) {

		try {
			adminForm = adminDao.addOrEditDepartment(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminBgmCalc(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminBgmCalc(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminPerfAux(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminPerfAux(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminPerfOther(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminPerfOther(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminPerfAcDcMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminPerfAcDcMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminPerfAcDcFrmInput(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminPerfAcDcFrmInput(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminSparesMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminSparesMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminSubSupplierMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminSubSupplierMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	@Override
	public AdminForm getAdminQualityMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminQualityMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	@Override
	public AdminForm getAdminTerminalMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminTerminalMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	@Override
	public AdminForm getAdminExclusionMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminExclusionMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	@Override
	public AdminForm getAdminTenderDrawingMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminTenderDrawingMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	@Override
	public AdminForm getAdminServiceMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminServiceMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	@Override
	public AdminForm getAdminAcwr(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminAcwr(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	@Override
	public AdminForm getAdminComrMonths(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminComrMonths(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	@Override
	public AdminForm getAdminEleItems(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminEleItems(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminDcmPowerCalc(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminDcmPowerCalc(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminItemMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminItemMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getAdminOthersMast(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAdminOthersMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm addOrEditRegion(AdminForm adminForm) {

		try {
			adminForm = adminDao.addOrEditRegion(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm addOrEditRole(AdminForm adminForm) {

		try {
			adminForm = adminDao.addOrEditRole(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm addOrEditFrame(AdminForm adminForm) {

		try {
			adminForm = adminDao.AddOrEditFrame(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm getAllF2FQues(AdminForm adminForm) {

		try {
			adminForm = adminDao.getAllF2FQues(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm addOrEditVehicle(AdminForm adminForm) {

		try {
			adminForm = adminDao.addOrEditVehicle(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}

	}

	@Override
	public AdminForm addOrEditTransportPlace(AdminForm adminForm) {

		try {
			adminForm = adminDao.addOrEditTransportPlace(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm addOrEditTransComponentwithPlace(AdminForm adminForm) {

		try {
			adminForm = adminDao.addOrEditTransComponentwithPlace(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm addOrEditUSD(AdminForm adminForm) {

		try {
			adminForm = adminDao.addOrEditUSD(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm getUSDValue(AdminForm adminForm) {

		try {
			adminForm = adminDao.getUSDValue(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm getQuestionsPage(AdminForm adminForm) {

		try {
			adminForm = adminDao.getQuestionsPage(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm getNewFramesForQuestions(AdminForm adminForm) {

		try {
			adminForm = adminDao.getNewFramesForQuestions(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public QuotationForm addOrEditVariantCode(QuotationForm quotationForm) {

		try {
			quotationForm = adminDao.addOrEditVariantCode(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public AdminForm getOnlyOldFramesForQuestions(AdminForm adminForm) {

		try {
			adminForm = adminDao.getOnlyOldFramesForQuestions(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	/*@Override
	public AdminForm editF2FTurbineInstruments(AdminForm adminForm) {

		try {
			adminForm = adminDao.editF2FTurbineInstruments(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}*/

	@Override
	public QuotationForm getAdminCacheWithAIList(QuotationForm quotationForm) {

		try {
			quotationForm = adminDao.getAdminCacheWithAIList(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	

	@Override
	public QuotationForm getStatesList() {
		try {
			return adminDao.getStatesList();
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public AdminForm getUserManual(AdminForm adminForm) {
		try {
			adminForm = adminDao.getUserManual(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm downloadFile(Integer itemId) {
		try {
			return adminDao.downloadFile(itemId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public AdminForm getAuditXml(String tableName) {
		try {
			return adminDao.getAuditXml(tableName);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public AdminForm getAuditDate(String tableName) {
		try {
			return adminDao.getAuditDate(tableName);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@Override
	public AdminForm getAuditHistory1() {
		try {
			return adminDao.getAuditHistory1();
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getAuditHistoryDetails(AdminForm adminForm) {
		try {
			return adminDao.getAuditHistoryDetails(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getAdminPercentTrnsDm(AdminForm adminForm) {
		try {
			return adminDao.getAdminPercentTrnsDm(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getAdminPercentTrnsEx(AdminForm adminForm) {
		try {
			return adminDao.getAdminPercentTrnsEx(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getAdminPercentEc(AdminForm adminForm) {
		try {
			return adminDao.getAdminPercentEc(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getAdminPercentPkg(AdminForm adminForm) {
		try {
			return adminDao.getAdminPercentPkg(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getAdminPercentEle(AdminForm adminForm) {
		try {
			return adminDao.getAdminPercentEle(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getAdminPercentMech(AdminForm adminForm) {
		try {
			return adminDao.getAdminPercentMech(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getAdminPercentF2F(AdminForm adminForm) {
		try {
			return adminDao.getAdminPercentF2F(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getUpload(AdminForm adminForm) {
		try {
			adminForm = adminDao.getUpload(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	
	@Override
	public AdminForm getGrid() {
		try {
			return adminDao.getGrid();
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public AdminForm getImage(AdminForm adminForm) {
		try {
			adminForm = adminDao.getImage(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@Override
	public AdminForm getStdOffer(AdminForm adminForm) {
		try {
			adminForm = adminDao.getStdOffer(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	@Override
	public AdminForm getValidateFinalCostNew(AdminForm adminForm) {
		try {
			return adminDao.getValidateFinalCostNew(adminForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
}
