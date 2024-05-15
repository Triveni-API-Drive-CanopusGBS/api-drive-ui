package com.ttl.ito.business.controllers;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.QuotationHomeGrid;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.service.AdminService;
import com.ttl.ito.business.service.QuotationService;
import com.ttl.ito.common.Utility.ComercialWordGeneratorUtil;
import com.ttl.ito.common.Utility.ComercialWordGeneratorUtilNew;
import com.ttl.ito.common.Utility.PdfGeneratorUtil;
import com.ttl.ito.common.Utility.WordGeneratorUtil;
import com.ttl.ito.internal.beans.LoginBO;

/**
 * 
 * Created by Basavesh B R and KavyaShree G C Class Name: QuotationController
 * This class is used to handle customer profile related requests such as to
 * Quotation create , update, display Bill Of Material , Assign Quotation to
 * other user etc.,
 * 
 * 
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "quot")
public class QuotationController {

	private Logger logger = Logger.getLogger(QuotationController.class);
String Item="";
	@Autowired
	private QuotationService quotationService;

	@Autowired
	private PdfGeneratorUtil pdfGeneratorUtil;

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/quotHome", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm quotHome(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.fetchCacheData(quotationForm);

			return quotationForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/fetchCacheData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm fetchCacheData(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);
			quotationForm = quotationService.fetchCacheData(quotationForm);

			return quotationForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/quotCache", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getQuotationCache(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.fetchQuotCacheData(quotationForm);

			return quotationForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getFrameAndUserData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getFrameAndUserData(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);
			quotationForm = quotationService.fetchQuotCacheData(quotationForm);
			quotationForm = quotationService.fetchCacheData(quotationForm);

			return quotationForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/saveBasicDetails", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm saveBasicDetails(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			if (quotationForm.getSaveBasicDetails().getQuotId() == 0) {
				quotationForm = quotationService.saveBasicDetails(quotationForm);// PROC_TURB_CONFIG//create
			} else {
				quotationForm.getSaveBasicDetails().setActive(true);
				quotationForm = quotationService.editQuotationDetails(quotationForm);// PROC_EDIT_TURB_CONFIG//edit
			}

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	
	@RequestMapping(value = "/saveAs", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm saveAs(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.saveAs(quotationForm);// PROC_TURB_CLONE//create
			
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	
	@RequestMapping(value = "/createScopeOfSupply", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm createScopeOfSupply(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {

			quotationForm = quotationService.saveScopeOfSupplyDetails(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/createScopeOfSupplyNew", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm createScopeOfSupplyNew(@RequestBody Integer quotId, HttpServletRequest request) {

		try {

			return quotationService.saveScopeOfSupplyDetailsNew(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/updateScopeOfSupply", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm updateScopeOfSupply(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.saveScopeOfSupplyDetails(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getQuesPage", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getQuestionsPage(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			if (quotationForm.getSaveBasicDetails().getcNewNumber() == null) {
				quotationForm = quotationService.getQuestionsPage(quotationForm);
			} else {
				quotationForm = quotationService.getSelectedQuestionsPage(quotationForm);
			}

			return quotationForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/saveQuesDetails", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm saveQuesDetails(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.saveQuesDetails(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/quotWorkFlow", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails quotWorkFlow(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		try {

			saveBasicDetails = quotationService.quotWorkFlow(saveBasicDetails);

			return saveBasicDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/quotStatusComplete", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails quotStatusComplete(@RequestBody SaveBasicDetails saveBasicDetails,
			HttpServletRequest request) {

		try {

			saveBasicDetails = quotationService.quotStatusComplete(saveBasicDetails);

			return saveBasicDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/saveCProject", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm saveCProject(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);
			quotationForm.setSaveBasicDetails(saveBasicDetails);
			quotationForm = quotationService.saveCProject(quotationForm);

			return quotationForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getOneLFineBom", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getOneLineBom(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);

			quotationForm.setSaveBasicDetails(saveBasicDetails);
			quotationForm = quotationService.getOneLineBom(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getF2FTreeStructure", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getF2FTreeStructure(@RequestBody SaveBasicDetails saveBasicDetails,
			HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);

			quotationForm.setSaveBasicDetails(saveBasicDetails);
			quotationForm = quotationService.getF2FTreeStructure(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/editQuotationDetails", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm editQuotationDetails(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.editQuotationDetails(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getF2FOverHeadCost", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getF2FOverHeadCost(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.getF2FOverHead(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getF2FShopCon", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getF2FShopCon(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.getF2FShopCon(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getTurbineClone", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getTurbuineClone(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);

			quotationForm = quotationService.getTurbuineClone(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getQuotFormData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getQuotFormData(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		try {

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getHomePageData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getHomePageData(@RequestBody LoginBO userDetails, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);
			quotationForm.setUserDetails(userDetails);
			quotationForm.setLoggedInUserId(quotationForm.getUserDetails().getUserId());
			// quotationForm.getSaveBasicDetails().setUserRoleId(userRoleId);
			quotationForm = quotationService.getQuotationHomeGrid(quotationForm);

			return quotationForm;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;

		}
	}

	// @RequestMapping(value = "/getAddOnExcelPrice", method =
	// RequestMethod.POST, produces = "application/json")
	// public QuotationForm getAddonPrice(@RequestBody QuotationForm
	// quotationForm, HttpServletRequest request) {
	//
	// try {
	// quotationForm = quotationService.getAddOnPrice(quotationForm);
	//
	// return quotationForm;
	// } catch (Exception e) {
	// logger.error("Exception :" + e);
	// return quotationForm;
	// }
	// }

	// @RequestMapping(value = "/saveAddOnPrice", method = RequestMethod.POST,
	// produces = "application/json")
	// public QuotationForm saveAddOnPrice(@RequestBody QuotationForm
	// quotationForm, HttpServletRequest request) {
	//
	// try {
	// quotationForm = quotationService.saveAddOnPrice(quotationForm);
	//
	// return quotationForm;
	// } catch (Exception e) {
	// logger.error("Exception :" + e);
	// return quotationForm;
	// }
	// }

	@RequestMapping(value = "/getQuotation", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getQuotation(@RequestBody Integer quotId, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		quotationForm.getSaveBasicDetails().setQuotId(quotId);
		try {
			quotationForm = quotationService.getQuotation(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/assignToOthers", method = RequestMethod.POST, produces = "application/json")
	public QuotationHomeGrid assignToOthers(@RequestBody QuotationHomeGrid quotationHomeGrid,
			HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);
			quotationHomeGrid = quotationService.assignToOthers(quotationHomeGrid);

			return quotationHomeGrid;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationHomeGrid;

		}
	}

	@RequestMapping(value = "/editOneLineBom", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm editOneLineBom(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {

			clearMessageFrom(quotationForm);

			quotationForm.setSaveBasicDetails(saveBasicDetails);
			quotationForm = quotationService.editOneLineBom(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getTransportationCache", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getTransportationCache(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {

			clearMessageFrom(quotationForm);

			quotationForm = quotationService.getTransportationCache(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getQuotTransCache", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getQuotTransCache(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {

			clearMessageFrom(quotationForm);

			quotationForm = quotationService.getQuotTransCache(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getPackageCache", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getPackageCache(HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			clearMessageFrom(quotationForm);
			return quotationService.getPackageCache(quotationForm);

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getTransportDataBasedOnFrame", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getTransportDataBasedOnFrame(@RequestBody SaveBasicDetails saveBasicDetails,
			HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {

			clearMessageFrom(quotationForm);
			quotationForm.setSaveBasicDetails(saveBasicDetails);
			quotationForm = quotationService.getTransportDataBasedOnFrame(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/saveTransportationData", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails saveTransportationData(@RequestBody SaveBasicDetails saveBasicDetails,
			HttpServletRequest request) {

		try {
			saveBasicDetails = quotationService.saveTransportationData(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/getErrectionCommCache", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getErrectionCommCache(@RequestBody Integer quotId, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		quotationForm.getSaveBasicDetails().setQuotId(quotId);

		try {

			clearMessageFrom(quotationForm);
			quotationForm = quotationService.getErrectionCommCache(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getErecCommData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getErecCommData(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			quotationForm = quotationService.getErecCommData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/saveErecCommission", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm saveErecCommission(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			quotationForm = quotationService.saveErecCommission(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getPackageData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getPackageData(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();

		try {
			quotationForm.setSaveBasicDetails(saveBasicDetails);
			quotationForm = quotationService.getPackageData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/savePackageData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm savePackageData(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			quotationForm = quotationService.savePackageData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getExcelCostSheetData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getExcelCostSheetData(@RequestBody Integer quotId, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		quotationForm.getSaveBasicDetails().setQuotId(quotId);
		try {
			quotationForm = quotationService.getExcelCostSheetData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@RequestMapping(value = "/getValidateFinalCost", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getValidateFinalCost(@RequestBody Integer quotId, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		quotationForm.getSaveBasicDetails().setQuotId(quotId);
		try {
			quotationForm = quotationService.getValidateFinalCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getTransportPrice", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getTransportPrice(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			quotationForm = quotationService.getTransportPrice(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/quotExportPdf", method = RequestMethod.POST)
	public void quotExportPdf(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getReportData(quotationForm, reportBean);

		Document document = null;

		try {
			document = new Document(PageSize.A4);

			response.setContentType("application/pdf");
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			pdfGeneratorUtil.generatePdf(reportBean, document, writer);
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			if (null != document) {
				document.close();
			}

		}
	}
	
	@RequestMapping(value = "/quotExportPdfRev", method = RequestMethod.POST)
	public void quotExportPdfRev(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getReportDataRev(quotationForm, reportBean);

		Document document = null;

		try {
			document = new Document(PageSize.A4);

			response.setContentType("application/pdf");
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			pdfGeneratorUtil.generatePdf(reportBean, document, writer);
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			if (null != document) {
				document.close();
			}

		}
	}


	@RequestMapping(value = "/quotExportAddOnPdfRev", method = RequestMethod.POST)
	public void quotExportAddOnPdfRev(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getAddOnReportDataRev(quotationForm, reportBean);

		Document document = null;

		try {
			document = new Document(PageSize.A4);

			response.setContentType("application/pdf");
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			pdfGeneratorUtil.generateAddOnPdf(reportBean, document, writer);
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			if (null != document) {
				document.close();
			}

		}
	}
	
	@RequestMapping(value = "/quotExportAddOnPdf", method = RequestMethod.POST)
	public void quotExportAddOnPdf(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getAddOnReportData(quotationForm, reportBean);

		Document document = null;

		try {
			document = new Document(PageSize.A4);

			response.setContentType("application/pdf");
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			pdfGeneratorUtil.generateAddOnPdf(reportBean, document, writer);
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			if (null != document) {
				document.close();
			}

		}
	}
	
	


	@RequestMapping(value = "/getWord", method = RequestMethod.POST)
	public void getWord(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) throws IOException, OpenXML4JException, XmlException {
		ReportBean reportBean = new ReportBean();
		byte[] bytebuffer = Base64.decodeBase64(quotationService.getDocument().getDocumentlist().get(0).getItem());
		reportBean = quotationService.getWordData(quotationForm, reportBean);
logger.info("hello");


//logger.info(quotationService.getDocument().getDocumentlist().get(0).getItem());
//logger.info(adminService.getGrid().getGridlist().get(0).getItem());


		//XWPFDocument document= new XWPFDocument(new ByteArrayInputStream(decodedBytes));

ByteArrayInputStream bis = new ByteArrayInputStream(bytebuffer);
logger.info("hello33");
//logger.info(bytebuffer.toString());

//POIXMLTextExtractor extractor = (POIXMLTextExtractor) ExtractorFactory.createExtractor(bis);
///POIXMLDocument document1 = extractor.getDocument();
XWPFDocument document= null;
 //if (document1 instanceof XWPFDocument) {
   //      document = (XWPFDocument) document1;	
 //}
logger.info("hello1");

		try {

			// for word
			document= new XWPFDocument(bis);
			logger.info("tst11");

			response.setContentType("application/docx");
			logger.info("tst12");

			WordGeneratorUtil.generateWord(reportBean, document, quotationForm);
			logger.info("tst13");

		    document.write(response.getOutputStream());
		    
		    
			logger.info("tst14");

			// for pdf

			// PdfOptions pdfOptions = PdfOptions.create();
			// //to do
//			OutputStream out = new FileOutputStream(new File("D:\\megha\\textqweas.pdf"));
//			// PdfConverter.getInstance().convert(document, out, pdfOptions);
//
//			fr.opensagres.poi.xwpf.converter.pdf.PdfOptions options = fr.opensagres.poi.xwpf.converter.pdf.PdfOptions
//					.create().fontEncoding("windows-1250");
//			fr.opensagres.poi.xwpf.converter.pdf.PdfConverter.getInstance().convert(document, out, options);

		} catch (Exception e) {
			logger.info("asdasd1");

			logger.error("Exception :" + e);
		} finally {
			logger.info("asdasd");
			try {
				if (document != null) {
					logger.info("asdasd44");
					 document.close();
					logger.info("asdasd55");

				}

			} catch (Exception ex) {
				logger.info("asdasd3");

			}
		}
	}
	
	@RequestMapping(value = "/getWordRev", method = RequestMethod.POST)
	public void getWordRev(@RequestBody QuotationForm quotationForm, HttpServletRequest request,HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getWordDataRev(quotationForm, reportBean);

		XWPFDocument document = null;

		try {

			// for word
			document = new XWPFDocument();
			response.setContentType("application/docx");
			WordGeneratorUtil.generateWord(reportBean, document, quotationForm);
			document.write(response.getOutputStream());

			// for pdf

			// PdfOptions pdfOptions = PdfOptions.create();
			// //to do
			OutputStream out = new FileOutputStream(new File("D:\\megha\\textqweas.pdf"));
			// PdfConverter.getInstance().convert(document, out, pdfOptions);

			fr.opensagres.poi.xwpf.converter.pdf.PdfOptions options = fr.opensagres.poi.xwpf.converter.pdf.PdfOptions
					.create().fontEncoding("windows-1250");
			fr.opensagres.poi.xwpf.converter.pdf.PdfConverter.getInstance().convert(document, out, options);

		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			try {
				if (document != null) {
					((Closeable) document).close();
				}

			} catch (Exception ex) {
			}
		}
	}

	@RequestMapping(value = "/getComercialWord", method = RequestMethod.POST)
	public void getComercialWord(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getComercialWordData(quotationForm, reportBean);

		XWPFDocument document = null;

		try {
			document = new XWPFDocument();

			response.setContentType("application/docx");
			ComercialWordGeneratorUtil.generateComercialWord(reportBean, document, quotationForm);
			document.write(response.getOutputStream());
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			try {
				if (document != null) {
					((Closeable) document).close();
				}

			} catch (Exception ex) {
			}
		}
	}
	
	@RequestMapping(value = "/getComercialWordRev", method = RequestMethod.POST)
	public void getComercialWordRev(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getComercialWordDataRev(quotationForm, reportBean);

		XWPFDocument document = null;

		try {
			document = new XWPFDocument();

			response.setContentType("application/docx");
			ComercialWordGeneratorUtil.generateComercialWord(reportBean, document, quotationForm);
			document.write(response.getOutputStream());
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			try {
				if (document != null) {
					((Closeable) document).close();
				}

			} catch (Exception ex) {
			}
		}
	}


	@RequestMapping(value = "/getComercialWordNew", method = RequestMethod.POST)
	public void getComercialWordNew(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getComercialWordDataNew(quotationForm, reportBean);

		XWPFDocument document = null;

		try {
			document = new XWPFDocument();

			response.setContentType("application/docx");
			ComercialWordGeneratorUtilNew.generateComercialWordNew(reportBean, document, quotationForm);
			document.write(response.getOutputStream());
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			try {
				if (document != null) {
					((Closeable) document).close();
				}

			} catch (Exception ex) {
			}
		}
	}
	
	@RequestMapping(value = "/getComercialWordNewRev", method = RequestMethod.POST)
	public void getComercialWordNewRev(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getComercialWordDataNewRev(quotationForm, reportBean);

		XWPFDocument document = null;

		try {
			document = new XWPFDocument();

			response.setContentType("application/docx");
			ComercialWordGeneratorUtilNew.generateComercialWordNew(reportBean, document, quotationForm);
			document.write(response.getOutputStream());
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			try {
				if (document != null) {
					((Closeable) document).close();
				}

			} catch (Exception ex) {
			}
		}
	}


	@RequestMapping(value = "/quotExportPdfDet", method = RequestMethod.POST)
	public void quotExportPdfDet(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		quotationForm = quotationService.getQuotation(quotationForm);
		Document document = null;

		try {
			document = new Document(PageSize.A4);

			response.setContentType("application/pdf");
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			pdfGeneratorUtil.quotExportPdfDet(quotationForm, document, writer);
		} catch (Exception e) {
			logger.error("Exception :" + e);
		} finally {
			if (null != document) {
				document.close();
			}

		}
	}

	@RequestMapping(value = "/saveProjectCost", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm saveProjectCost(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);
			quotationForm = quotationService.saveProjectCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/saveVariableCost", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm saveVariableCost(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);
			quotationForm = quotationService.saveVariableCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/saveSparesCost", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm saveSparesCost(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);
			quotationForm = quotationService.saveSparesCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getProjectCost", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getProjectCost(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {

			quotationForm = quotationService.getProjectCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getSparesCost", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getSparesCost(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);
			quotationForm = quotationService.getSparesCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getVariableCost", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getVariableCost(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			clearMessageFrom(quotationForm);
			quotationForm = quotationService.getVariableCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	// not using this
	/*
	 * @RequestMapping(value = "/createQuotRev", method = RequestMethod.POST,
	 * produces = "application/json") public QuotationForm
	 * createQuotRev(@RequestBody QuotationForm quotationForm,
	 * HttpServletRequest request) {
	 * 
	 * try { quotationForm = quotationService.createQuotRev(quotationForm);
	 * 
	 * return quotationForm; } catch (Exception e) { logger.error("Exception :"
	 * + e); return quotationForm; } }
	 */

	@RequestMapping(value = "/getQuotRevData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getQuotRevData(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

		try {
			quotationForm = quotationService.getQuotRevData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/scopeOfSupplyStatus", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails scopeOfSupplyStatus(@RequestBody SaveBasicDetails saveBasicDetails,
			HttpServletRequest request) {
		try {

			saveBasicDetails = quotationService.scopeOfSupplyStatus(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/getQuotRemarks", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails getQuotRemarks(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		try {
			saveBasicDetails = quotationService.getQuotRemarks(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/getF2FUboData", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails getF2FUboData(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		try {
			saveBasicDetails = quotationService.getF2FUboData(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/saveF2FUboData", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails saveF2FUboData(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		try {
			saveBasicDetails = quotationService.saveF2FUboData(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/getF2FSapData", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails getF2FSapData(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		try {
			saveBasicDetails = quotationService.getF2FSapData(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/saveF2FSap", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails saveF2FSap(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		try {
			saveBasicDetails = quotationService.saveF2FSap(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@RequestMapping(value = "/getQuotRevNo", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getQuotRevNo(@RequestBody Integer quotId, HttpServletRequest request) {

		try {

			return quotationService.getQuotRevNo(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/getQuestionInfo", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getQuestionInfo(@RequestBody Integer framePowerId, HttpServletRequest request) {

		try {

			return quotationService.getQuestionInfo(framePowerId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/fetchUserData", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm fetchUserData(HttpServletRequest request) {

		try {

			return quotationService.fetchUserData();
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/getScopeOfSupStatus", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getScopeOfSupStatus(@RequestBody Integer quotId, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		quotationForm.getSaveBasicDetails().setQuotId(quotId);
		try {
			quotationForm = quotationService.getScopeOfSupStatus(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@RequestMapping(value = "/getVarCostDet", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getVarCostDet(@RequestBody Integer quotId, HttpServletRequest request) {

		try {

			return quotationService.getVarCostDet(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/downloadPdf", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm downloadPdf(@RequestBody Integer quotId, HttpServletRequest request) {

		try {

			return quotationService.downloadPdf(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}

	}

	@RequestMapping(value = "/getTechReport", method = RequestMethod.POST)
	public void getTechReport(@RequestBody QuotationForm quotationForm, HttpServletRequest request,
			HttpServletResponse response) {
		ReportBean reportBean = new ReportBean();
		reportBean = quotationService.getTechReportData(quotationForm, reportBean);

		Document document = null;

		try {
			document = new Document(PageSize.A4, 45, 36, 90, 48);
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

	@RequestMapping(value = "/saveRemarks", method = RequestMethod.POST, produces = "application/json")
	public SaveBasicDetails saveRemarks(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

		try {
			saveBasicDetails = quotationService.saveRemarks(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	private void clearMessageFrom(QuotationForm quotationForm) {
		if (null != quotationForm) {
			quotationForm.getMsgToUser().clear();
		}
	}
	@RequestMapping(value = "/getDocument", method = RequestMethod.POST, produces = "application/json")
	public QuotationForm getDocument(HttpServletRequest request) {
		try {

			return quotationService.getDocument();

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
}
