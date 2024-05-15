package com.ttl.ito.business.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ttl.ito.business.service.DBOMechanicalService;
import com.ttl.ito.business.service.QuotationService;
import com.ttl.ito.common.Utility.PdfGeneratorUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("DBO")
public class DBOMechanicalController {

	private Logger logger = Logger.getLogger(DBOMechanicalController.class);

	@Autowired
	DBOMechanicalService dBOMechanicalService;
	
	@Autowired
	private QuotationService quotationService;
	
	@Autowired
	private PdfGeneratorUtil pdfGeneratorUtil;

	@RequestMapping(value = "/DBOMechanicalCache", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDBOMechanicalData(HttpServletRequest request) {

		DBOForm dboForm = new DBOForm();

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getDBOMechanicalData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDBOMechanicalItems", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboMechanicalItems(@RequestBody String turbineType, HttpServletRequest request) {

		DBOForm dboForm = new DBOForm();

		try {
			clearMessageFrom(dboForm);
			dboForm.setTurbineCode(turbineType);
			dboForm = dBOMechanicalService.getDboMechanicalItems(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDBOMechPanel", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDBOMechPanel(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getDBOMechPanel(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDboMechanicalPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboMechanicalPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getDboMechanicalPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDBOElectricalData", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDBOElectricalData(@RequestBody String voltageGenType, HttpServletRequest request) {

		DBOForm dboForm = new DBOForm();
		try {
			clearMessageFrom(dboForm);
			dboForm.setDboEleType(voltageGenType);
			dboForm = dBOMechanicalService.getDBOElectricalData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDboElectricalAddInstrPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboElectricalAddInstrPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getDboElectricalAddInstrPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDBOElectricalPanel", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDBOElectricalPanel(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getDBOElectricalPanel(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}

	}

	@RequestMapping(value = "/getDboElectricalPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboElectricalPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getDboElectricalPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/saveDBOElectricalData", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveDBOElectricalData(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveDBOElectricalData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/saveDBOMechanicalData", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveDBOMechanicalData(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveDBOMechanicalData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/resetDboMechData", method = RequestMethod.POST, produces = "application/json")
	public DBOForm resetDboMechData(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.resetDboMechData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/resetDboEleData", method = RequestMethod.POST, produces = "application/json")
	public DBOForm resetDboEleData(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.resetDboEleData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDboEleAddOn", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboEleAddOn(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getDboEleAddOn(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDboElectricalPriceAddOn", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboElectricalPriceAddOn(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getDboElectricalPriceAddOn(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/getDboEleTotal", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboEleTotal(@RequestBody Integer quotId, HttpServletRequest request) {

		try {

			return dBOMechanicalService.getDboEleTotal(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/removeDboMechItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm removeDboMechItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {

			return dBOMechanicalService.removeDboMechItem(dboForm.getQuotId(), dboForm.getItemId(), dboForm.getSubItemId());
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/getDboMechTotal", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboMechTotal(@RequestBody Integer quotId, HttpServletRequest request) {

		try {

			return dBOMechanicalService.getDboMechTotal(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/getDboEditData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getDboEditData(@RequestBody Integer quotId, HttpServletRequest request) {

		try {

			return dBOMechanicalService.getDboEditData(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/getDboFormData", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getDboFormData(HttpServletRequest request) {

		DBOForm dboForm = new DBOForm();
		try {

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	//this end point is used to get the F2FItems 

	@RequestMapping(value = "/getF2fItems", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getF2fItems(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getF2fItems(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	//this end point is used to get the F2F Panels
	
	@RequestMapping(value = "/getF2fPanels", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getF2fPanels(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getF2fPanels(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	//this end point is used to get the F2F items technical price
	
	@RequestMapping(value = "/getF2fTechPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getF2fTechPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getF2fTechPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	
	//this end point is used to save the F2F items 
	
	@RequestMapping(value = "/saveF2fItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveF2fItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveF2fItem(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	@RequestMapping(value = "/saveComercial", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveComercial(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveComercial(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	//this end point is used to save the F2F sub items 
	
	@RequestMapping(value = "/saveF2fSubItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveF2fSubItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm); 
			dboForm = dBOMechanicalService.saveF2fSubItem(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to save the F2F sub item type
	
	@RequestMapping(value = "/saveF2fSubItemType", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveF2fSubItemType(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveF2fSubItemType(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to get F2F AddOn of the additional components added for the UI purpose
	
	@RequestMapping(value = "/getF2fAddOn", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getF2fAddOn(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getF2fAddOn(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to get F2F Price AddOn of the additional components price for the UI purpose
	
	@RequestMapping(value = "/getF2fPriceAddOn", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getF2fPriceAddOn(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getF2fPriceAddOn(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	//this end point is used to get F2F cache for the UI purpose
	
	@RequestMapping(value = "/getGeneralInput", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getGeneralInput(@RequestBody Integer quotId ,HttpServletRequest request) {

	
		DBOForm dboForm = new DBOForm();
		dboForm.setQuotId(quotId);

		try {

			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getGeneralInput(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to save General inputs for the standard quotation and non standard quotation for the UI purpose
	
	@RequestMapping(value = "/saveGeneralInput", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveGeneralInput(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveGeneralInput(dboForm);
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	//this end point is used to get Mechanical items for the UI Purpose
	
	@RequestMapping(value = "/getMechItems", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getMechItems(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getMechItems(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	//this end point is used to get Mechanical panels for the UI purpose
	
	@RequestMapping(value = "/getMechPanels", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getMechPanels(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getMechPanels(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to get Mechanical price for the UI purpose

	@RequestMapping(value = "/getMechTechPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getMechTechPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getMechTechPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	//this end point is used to save mechanical item for the UI purpose
	
	@RequestMapping(value = "/saveMechItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveMechItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveMechItem(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to save mechanical sub item for the UI purpose
	
	@RequestMapping(value = "/saveMechSubItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveMechSubItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveMechSubItem(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to get mechanical Aux item for the UI purpose
	
	@RequestMapping(value = "/getMechAuxItems", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getMechAuxItems(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getMechAuxItems(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to get mechanical Aux panels for the UI purpose
	
	@RequestMapping(value = "/getMechAuxPanels", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getMechAuxPanels(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getMechAuxPanels(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to get mechanical Aux tech price for the UI purpose
	
	@RequestMapping(value = "/getMechAuxTechPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getMechAuxTechPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getMechAuxTechPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	//this end point is used to save mechanical Aux item for the UI purpose
	
	@RequestMapping(value = "/saveMechAuxItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveMechAuxItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveMechAuxItem(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	//this end point is used to save mechanical Aux extended scope for the UI purpose
	
	@RequestMapping(value = "/saveMechAuxExtScope", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveMechAuxExtScope(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveMechAuxExtScope(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/saveCIExtScope", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveCIExtScope(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveCIExtScope(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/saveEleExtScope", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveEleExtScope(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveEleExtScope(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/removeDboMechAuxItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm removeDboMechAuxItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {

			return dBOMechanicalService.removeDboMechAuxItem(dboForm.getQuotId(), dboForm.getItemId(), dboForm.getSubItemId());
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}
	
	@RequestMapping(value = "/getEleFilter", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getEleFilter(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getEleFilter(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/saveEleFilter", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveEleFilter(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveEleFilter(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getElePanels", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getElePanels(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getElePanels(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getEleItems", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getEleItems(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getEleItems(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	@RequestMapping(value = "/getEleTechPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getEleTechPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getEleTechPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	@RequestMapping(value = "/getEleRefreshPanel", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getEleRefreshPanel(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getEleRefreshPanel(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/saveEleItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveEleItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveEleItem(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getEleSpecialFilter", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getEleSpecialFilter(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getEleSpecialFilter(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/saveEleSpecialFilter", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveEleSpecialFilter(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveEleSpecialFilter(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	private void clearMessageFrom(DBOForm dboForm) {
		if (null != dboForm) {
			dboForm.getMsgToUser().clear();
		}
	}
	
	@RequestMapping(value = "/getEleInstr", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getEleInstr(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getEleInstr(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	@RequestMapping(value = "/saveAdditionalInstrumentation", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveAdditionalInstrumentation(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveAdditionalInstrumentation(dboForm);
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/saveEleSubItem", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveEleSubItem(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveEleSubItem(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getEleVms", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getEleVms(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getEleVms(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	

	
	@RequestMapping(value = "/getAvr", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getAvr(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getAvr(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	@RequestMapping(value = "/getGoverner", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getGoverner(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getGoverner(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	
	
	
	@RequestMapping(value = "/getVmsCache", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getVmsCache(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getVmsCache(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	@RequestMapping(value = "/getUpdateCreateF2fColVal", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateF2fColVal(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateF2fColVal(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	@RequestMapping(value = "/getUpdateCreateF2fPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateF2fPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateF2fPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	@RequestMapping(value = "/createUpdateF2fShopConv", method = RequestMethod.POST, produces = "application/json")
	public DBOForm createUpdateF2fShopConv(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.createUpdateF2fShopConv(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getUpdateCreateMechPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateMechPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateMechPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getUpdateCreateF2fFrameSpecData", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateF2fFrameSpecData(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateF2fFrameSpecData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getUpdateCreateMechAuxFrameSpecData", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateMechAuxFrameSpecData(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateMechAuxFrameSpecData(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getUpdateCreateMechAddOnCost", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateMechAddOnCost(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateMechAddOnCost(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getUpdateCreateMechAuxColVal", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateMechAuxColVal(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateMechAuxColVal(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	
	@RequestMapping(value = "/getUpdateCreateMechAuxPrice", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateMechAuxPrice(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateMechAuxPrice(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getUpdateCreateMechOverTank", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateCreateMechOverTank(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateCreateMechOverTank(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	
	@RequestMapping(value = "/saveEleVms", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveEleVms(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveEleVms(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/savePerformance", method = RequestMethod.POST, produces = "application/json")
	public DBOForm savePerformance(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.savePerformance(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getPerformance", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getPerformance(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getPerformance(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getPerformanceParam", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getPerformanceParam(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getPerformanceParam(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getOtherChapter", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getOtherChapter(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getOtherChapter(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/saveOtherChapter", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveOtherChapter(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.saveOtherChapter(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/getUpdateGeneralInput", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getUpdateGeneralInput(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getUpdateGeneralInput(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	
	@RequestMapping(value = "/changeGeneralInput", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm changeGeneralInput( @RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {

			return dBOMechanicalService.changeGeneralInput(quotationForm.getTypeOfQuotation(),quotationForm.getTypeOfPanel(),quotationForm.getMake(),quotationForm.getDutySync());
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	@RequestMapping(value = "/getTechReport", method = RequestMethod.POST)
	public void getTechReport(@RequestBody QuotationForm quotationForm, HttpServletRequest request, HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getTechReportData(quotationForm, reportBean);

		Document document = null;

		try {
			document = new Document(PageSize.A4,45,36,90,48);

			response.setContentType("application/pdf");
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			pdfGeneratorUtil.generateTechReport(reportBean, document, writer);
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			if (null != document) {
				document.close();
			}

		}
	}

	@RequestMapping(value = "/getComercial", method = RequestMethod.POST, produces = "application/json")
	public DBOForm getComercial(@RequestBody DBOForm dboForm, HttpServletRequest request) {

		try {
			clearMessageFrom(dboForm);
			dboForm = dBOMechanicalService.getComercial(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}

	
	@RequestMapping(value = "/saveAttachment", method = RequestMethod.POST, produces = "application/json")
	public DBOForm saveAttachment(@RequestBody DBOForm dboForm, HttpServletRequest request) {
 {

		try {

			dboForm = dBOMechanicalService.saveAttachment(dboForm);

			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
 
 
 
 
	}
}
