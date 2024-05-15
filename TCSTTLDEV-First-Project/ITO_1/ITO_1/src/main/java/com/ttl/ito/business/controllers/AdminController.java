/**
 * 
 * Created by Kavya
 * Class Name: AdminController 
 * This class is used to handle admin screens related requests
 *  
 * 
 */

package com.ttl.ito.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.beans.TurbineAnswers;
import com.ttl.ito.business.service.AdminService;
import com.ttl.ito.business.service.QuotationService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "admin")
@RestController
public class AdminController {
	private Logger logger = Logger.getLogger(QuotationController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private QuotationService quotationService;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	/**
	 * @param request
	 * @return admin form used to get empty admin form for UI purpose
	 */
	@RequestMapping(value = "/getAdminForm", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminForm(HttpServletRequest request) {

		AdminForm adminForm = new AdminForm();

		try {
			TurbineAnswers turbineAnswers = new TurbineAnswers();
			List<TurbineAnswers> ansList = new ArrayList<>();
			ansList.add(turbineAnswers);
			adminForm.setAnswersList(ansList);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAdminCache", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getAdminCache(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		try {

			quotationForm = quotationService.fetchCacheData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getAdminCacheWithAIList", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getAdminCacheWithAIList(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {

			quotationForm = adminService.getAdminCacheWithAIList(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/addOrEditQuesAnswer", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditQuesAnswer(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.addOrEditQuesAnswer(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/addOrEditDepartment", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditDepartment(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.addOrEditDepartment(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	// PROC_ADMIN_BGM_CALC

	@RequestMapping(value = "/getAdminBgmCalc", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminBgmCalc(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminBgmCalc(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPerfAux", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPerfAux(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPerfAux(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPerfOther", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPerfOther(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPerfOther(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPerfAcDcMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPerfAcDcMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPerfAcDcMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPerfAcDcFrmInput", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPerfAcDcFrmInput(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPerfAcDcFrmInput(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminSparesMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminSparesMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminSparesMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminSubSupplierMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminSubSupplierMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminSubSupplierMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminQualityMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminQualityMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminQualityMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminTerminalMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminTerminalMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminTerminalMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminExclusionMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminExclusionMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminExclusionMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminTenderDrawingMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminTenderDrawingMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminTenderDrawingMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminServiceMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminServiceMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminServiceMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminAcwr", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminAcwr(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminAcwr(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminComrMonths", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminComrMonths(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminComrMonths(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}
	// PROC_ADMIN_ELE_ITEMS

	@RequestMapping(value = "/getAdminEleItems", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminEleItems(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminEleItems(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	// PROC_ADMIN_DCM_POWER_CALC

	@RequestMapping(value = "/getAdminDcmPowerCalc", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminDcmPowerCalc(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminDcmPowerCalc(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	// PROC_ADMIN_ITEM_MAST

	@RequestMapping(value = "/getAdminItemMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminItemMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminItemMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	// PROC_ADMIN_OTHERS_MAST

	@RequestMapping(value = "/getAdminOthersMast", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminOthersMast(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminOthersMast(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/addOrEditRegion", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditRegion(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.addOrEditRegion(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/addOrEditRoles", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditRole(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.addOrEditRole(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/addOrEditFrame", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditFrame(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.addOrEditFrame(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAllF2FQues", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAllF2FQues(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			clearMessageFrom(adminForm);
			adminForm = adminService.getAllF2FQues(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/addOrEditVehicle", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditVehicle(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			clearMessageFrom(adminForm);
			adminForm = adminService.addOrEditVehicle(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/addOrEditTransportPlace", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditTransportPlace(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			clearMessageFrom(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/addOrEditTransComponentwithPlace", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditTransComponentwithPlace(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			clearMessageFrom(adminForm);
			adminForm = adminService.addOrEditTransComponentwithPlace(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/addOrEditUSD", method = RequestMethod.POST, produces = "application/json")
	public AdminForm addOrEditUSD(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			clearMessageFrom(adminForm);
			adminForm = adminService.addOrEditUSD(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}

	}

	@RequestMapping(value = "/getUSDValue", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getUSDValue(HttpServletRequest request) {

		AdminForm adminForm = new AdminForm();
		try {
			adminForm = adminService.getUSDValue(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getQuestionsPage", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getQuestionsPage(@RequestBody Integer framePowId, HttpServletRequest request) {

		AdminForm adminForm = new AdminForm();
		try {

			adminForm.setFramePowerId(framePowId);
			clearMessageFrom(adminForm);
			adminForm = adminService.getQuestionsPage(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}

	}

	private void clearMessageFrom(AdminForm adminForm) {
		if (null != adminForm) {
			adminForm.getMsgToUser().clear();
		}
	}

	@RequestMapping(value = "/addOrEditVariantCode", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm addOrEditVariantCode(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			quotationForm = adminService.addOrEditVariantCode(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getNewFramesForQuestions", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getNewFramesForQuestions(HttpServletRequest request) {

		AdminForm adminForm = new AdminForm();

		try {

			adminForm = adminService.getNewFramesForQuestions(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getOnlyOldFramesForQuestions", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getOnlyFramesForQuestions(HttpServletRequest request) {

		AdminForm adminForm = new AdminForm();

		try {
			adminForm = adminService.getOnlyOldFramesForQuestions(adminForm);

			return adminForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	/*
	 * @RequestMapping(value = "/editF2FTurbineInstruments", method =
	 * RequestMethod.POST, produces = "application/json") public AdminForm
	 * editF2FTurbineInstruments(@RequestBody AdminForm adminForm,
	 * HttpServletRequest request) {
	 * 
	 * try {
	 * 
	 * adminForm = adminService.editF2FTurbineInstruments(adminForm);
	 * 
	 * return adminForm; } catch (Exception e) { logger.error("Exception :" +
	 * e); return adminForm; } }
	 */

	@RequestMapping(value = "/getStatesList", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getStatesList(HttpServletRequest request) {
		try {

			return adminService.getStatesList();
		} catch (Exception e) {
			QuotationForm quotationForm = new QuotationForm();
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getUserManual", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getUserManual(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			adminForm = adminService.getUserManual(adminForm);

			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAuditXml", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAuditXml(@RequestBody String tableName, HttpServletRequest request) {

		try {

			return adminService.getAuditXml(tableName);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/getAuditDate", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAuditDate(@RequestBody String tableName, HttpServletRequest request) {

		try {

			return adminService.getAuditDate(tableName);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/getAuditHistory1", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAuditHistory1(HttpServletRequest request) {

		AdminForm adminForm = new AdminForm();

		try {
			adminForm = adminService.getAuditHistory1();
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAuditHistoryDetails", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAuditHistoryDetails(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAuditHistoryDetails(adminForm);
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getUpload", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getUpload(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			adminForm = adminService.getUpload(adminForm);

			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPercentTrnsDm", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPercentTrnsDm(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPercentTrnsDm(adminForm);
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPercentEc", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPercentEc(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPercentEc(adminForm);
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPercentPkg", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPercentPkg(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPercentPkg(adminForm);
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPercentTrnsEx", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPercentTrnsEx(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPercentTrnsEx(adminForm);
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPercentEle", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPercentEle(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPercentEle(adminForm);
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPercentMech", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPercentMech(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPercentMech(adminForm);
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getAdminPercentF2F", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getAdminPercentF2F(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {
			adminForm = adminService.getAdminPercentF2F(adminForm);
			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST, produces = "application/json")
	public AdminForm downloadFile(@RequestBody Integer itemId, HttpServletRequest request) {

		try {

			return adminService.downloadFile(itemId);

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@RequestMapping(value = "/getGrid", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getGrid(HttpServletRequest request) {
		try {
			return adminService.getGrid();

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@RequestMapping(value = "/getImage", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getImage(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			adminForm = adminService.getImage(adminForm);

			return adminForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return adminForm;
		}
	}

	@RequestMapping(value = "/getStdOffer", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getStdOffer(@RequestBody AdminForm adminForm, HttpServletRequest request) {

		try {

			return adminService.getStdOffer(adminForm);

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

	@RequestMapping(value = "/getValidateFinalCostNew", method = RequestMethod.POST, produces = "application/json")
	public AdminForm getValidateFinalCostNew(@RequestBody Integer quotId, HttpServletRequest request) {
		AdminForm adminForm = new AdminForm();
		adminForm.getSaveBasicDetails().setQuotId(quotId);
		try {

			return adminService.getValidateFinalCostNew(adminForm);

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}

}
