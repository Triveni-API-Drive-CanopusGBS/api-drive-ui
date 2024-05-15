package com.ttl.ito.business.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.Entry;
import com.ttl.ito.business.beans.F2FForm;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.QuotationHomeGrid;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.beans.ScopeOfSupply;
import com.ttl.ito.business.beans.TurbineDetails;
import com.ttl.ito.business.dao.QuotationDao;
import com.ttl.ito.business.service.QuotationService;
import com.ttl.ito.common.Utility.SendMail;

@Service
public class QuotationServiceImpl implements QuotationService {

	private Logger logger = Logger.getLogger(QuotationServiceImpl.class);

	@Autowired
	private QuotationDao quotationDao;

	@Autowired
	private SendMail mail;
	
	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Override
	public QuotationForm getQuotationHomeGrid(QuotationForm quotationForm) {

		try {
			quotationForm = quotationDao.getQuotationHomeGrid(quotationForm);
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return quotationForm;
	}

	
	@Override
	public QuotationForm fetchCacheData(QuotationForm quotationForm) {

		try {
			quotationForm = quotationDao.fetchCacheData(quotationForm);
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return quotationForm;
	}
	
	@Override
	public QuotationForm saveBasicDetails(QuotationForm quotationForm ) {

		try {

		quotationForm = quotationDao.saveBasicDetails(quotationForm);
		
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}

	@Override
	public QuotationForm saveAs(QuotationForm quotationForm ) {

		try {

		quotationForm = quotationDao.saveAs(quotationForm);
		
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}

	
	@Override
	public QuotationForm getQuestionsPage(QuotationForm quotationForm ) {

		try {
			quotationForm = quotationDao.getQuestionsPage(quotationForm);
			
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}

	@Override
	public QuotationForm saveQuesDetails(QuotationForm quotationForm ) {

		try {
			quotationForm = quotationDao.saveQuesDetails(quotationForm);
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}

	

	@Override
	public SaveBasicDetails quotWorkFlow(SaveBasicDetails saveBasicDetails) {

		try {
			saveBasicDetails = quotationDao.quotWorkFlow(saveBasicDetails);
			java.io.File dir = new java.io.File("");
			java.io.File[] attachments= dir.listFiles();
			StringBuffer mailBody = new StringBuffer();
			String toAddress = saveBasicDetails.getEmail();
			mailBody.append("Hi "+saveBasicDetails.getName()+",");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Quotation Number : "+ saveBasicDetails.getQuotId()+ " is pending with you, kindly please approve it.");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Thanks and Regards, ");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("ITO Team");
			String mailText=mailBody.toString();
			logger.info("Level1");
			logger.info(saveBasicDetails.getQuotId());
			logger.info(toAddress);
			logger.info(saveBasicDetails.getName());
			mail.send(new String[] {toAddress}, null, null, "Quotation Number : "+ saveBasicDetails.getQuotId()+ " Pending Approval" , mailText,attachments);
			logger.info("Level2");			
		} catch (Exception e) {
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return saveBasicDetails;
	}

	@Override
	public QuotationForm saveCProject(QuotationForm quotationForm) {
		try {
			quotationForm = quotationDao.saveCProject(quotationForm);
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getF2FTreeStructure(QuotationForm quotationForm ) {
		
		List<F2FForm> treeObjectList=null;
		
		quotationForm = quotationDao.getF2FTreeStructure(quotationForm);
		treeObjectList=new ArrayList<F2FForm>();		
		treeObjectList=quotationForm.getF2FDataList();
		 Entry children[] = new Entry[treeObjectList.size()];
	      String childId[] = new String[treeObjectList.size()];
		try{
			 Entry rootObject = null;
			 String rootParentCode=null,subParentCode=null; 
			 rootObject=new Entry(treeObjectList.get(0));
			 rootParentCode=treeObjectList.get(0).getParentMaterialCode();
			 for (int i=1;i<treeObjectList.size();i++) {
				 children[i]=new Entry(treeObjectList.get(i));
				 subParentCode=treeObjectList.get(i).getParentMaterialCode();
				 childId[i]=treeObjectList.get(i).getChildMaterialCode();
				 if(subParentCode!=null && rootParentCode.equals(subParentCode)){
					 rootObject.add(children[i]);
				 }else{
					 for(int j=i-1;j>0;j--){
						 if(subParentCode!=null && childId[j].equals(subParentCode) )
						 {
							 children[j].add(children[i]);
						 }
					 }
				 }	
			 }
			 List<Entry> f2fTree = new ArrayList<>(); 
		     f2fTree.add(rootObject);
		      Map<String,List<Entry>> treeMap = new HashMap<String,List<Entry>>();
		      treeMap.put("data",f2fTree );
		      quotationForm.getTree().add(treeMap);
		        
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return quotationForm;
	}

	@Override
	public QuotationForm editQuotationDetails(QuotationForm quotationForm ) {
		
		try {

			quotationForm = quotationDao.editQuotationDetails(quotationForm);
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}
	
	@Override
	public QuotationForm getF2FOverHead(QuotationForm quotationForm ) {
		
		try {

		quotationForm = quotationDao.getF2FOverHead(quotationForm);
		
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}
	
	@Override
	public QuotationForm getF2FShopCon(QuotationForm quotationForm ) {
		
		try {

		quotationForm = quotationDao.getF2FShopCon(quotationForm);
		
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}
	
	@Override
	public QuotationForm getTurbuineClone(QuotationForm quotationForm ) {
		
		try {

		quotationForm = quotationDao.getTurbuineClone(quotationForm);
		
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}

	@Override
	public QuotationForm saveScopeOfSupplyDetails(QuotationForm quotationForm ) {

		try {
			if (!quotationForm.getSaveBasicDetails().getScopeOfSupply().isEmpty()) {
				quotationForm = quotationDao.saveScopeOfSupplyDetails(quotationForm);
			}
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}
	
	@Override
	public QuotationForm saveScopeOfSupplyDetailsNew(Integer quotId) {
		try {
			return quotationDao.saveScopeOfSupplyDetailsNew(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public QuotationForm getF2fCache(QuotationForm quotationForm ) {

		try {
			
				quotationForm = quotationDao.getF2fCache(quotationForm);
			
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		
		return quotationForm;
	}

	@Override
	public QuotationForm fetchQuotCacheData(QuotationForm quotationForm) {
		
		try {
			//quotationDao.fetchQuotCacheData(quotationForm);
			quotationForm = quotationDao.fetchQuotCacheData(quotationForm);
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return quotationForm;
	}
	
//	@Override
//	public QuotationForm getAddOnPrice(QuotationForm quotationForm) {
//
//		try {
//			 quotationForm = quotationDao.getAddOnPrice(quotationForm);
//
//			return quotationForm;
//		} catch (Exception e) {
//			logger.error("Exception :" + e);
//			return quotationForm;
//		}
//	}

//	@Override
//	public QuotationForm saveAddOnPrice(QuotationForm quotationForm) {
//		try {
//			quotationForm = quotationDao.saveAddOnPrice(quotationForm);
//			if (quotationForm.getAddOnComponent().getSelectedCostFlag() == 1) {
//				quotationDao.saveQuotRemarks(quotationForm.getSaveBasicDetails(),"OVERWRITE", "ADD_ON", quotationForm.getAddOnComponent().getSelectedCost(),
//						quotationForm.getAddOnComponent().getOverwrittenRemarks());
//			}
//
//			return quotationForm;
//		} catch (Exception e) {
//			logger.error("Exception :" + e);
//			return quotationForm;
//		}
//	}
//	
	@Override
	public QuotationHomeGrid assignToOthers(QuotationHomeGrid quotationHomeGrid) {
		
		QuotationForm quotationForm = new QuotationForm();
		try {
			quotationHomeGrid = quotationDao.assignToOthers(quotationHomeGrid);
			//Sending mail to notify the user
			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String toAddress = quotationHomeGrid.getEmail();
			mailBody.append("Hi ," +quotationHomeGrid.getAssignedTo());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("You have been assigned with a quotation");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Quotation details:");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("===============");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Quotation Number : "+ quotationHomeGrid.getQuotNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(" You can view it in your My-Quotations List");
			
			mailBody.append(System.getProperty("line.separator"));
			
			String mailText=mailBody.toString();
			
			//mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, "Quotation Assigned" , mailText,files );
			mail.sendMail(new String[] {toAddress}, null, null, "Quotation Assigned" , mailText,files );
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return quotationHomeGrid;
	}

	@Override
	public QuotationForm getSelectedQuestionsPage(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getSelectedQuestionsPage(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm getOneLineBom(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getOneLineBom(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm getQuotation(QuotationForm quotationForm) {

		try {
			quotationForm = quotationDao.getQuotation(quotationForm);
			quotationForm = setDefaultValues(quotationForm);
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public QuotationForm editOneLineBom(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.editOneLineBom(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public QuotationForm setDefaultValues(QuotationForm quotationForm) {
		
		try {
			quotationForm = quotationDao.fetchQuotCacheData(quotationForm);
			//quotationForm = quotationDao.fetchCacheData(quotationForm);
			

			for (List<TurbineDetails> frames : quotationForm.getDropDownColumnvalues().getFrames().values()) {
				for (TurbineDetails frame : frames) {
					if (frame.getFrameId() == quotationForm.getSaveBasicDetails().getFrameId()) {
						frame.setDefaultVal(true);
						for (List<TurbineDetails> turbineTypes : quotationForm.getDropDownColumnvalues().getTypesOfTurbine().values()) {
							for (TurbineDetails turbineType : turbineTypes) {
								if (frame.getTurbineCode().equalsIgnoreCase(turbineType.getCategoryDetCode())) {
									turbineType.setDefaultVal(true);
									quotationForm.getSaveBasicDetails().setTypeOfTurbine(turbineType.getCategoryDetDesc());
									quotationForm.getSaveBasicDetails().setTurbineCode(turbineType.getCategoryDetCode());
								}
							}
						}
						for (List<TurbineDetails> turbineTypes : quotationForm.getDropDownColumnvalues().getTypesOfNewTurbine().values()) {
							for (TurbineDetails turbineType : turbineTypes) {
								if (frame.getTurbineDesignCd().equalsIgnoreCase(turbineType.getCategoryDetCode())) {
									turbineType.setDefaultVal(true);
									quotationForm.getSaveBasicDetails().setTypOfBlade(turbineType.getCategoryDetDesc());
									quotationForm.getSaveBasicDetails().setTypOfBladeCode(turbineType.getCategoryDetCode());
								}
							}
						}
					}
				}
			}

			/*for (List<TurbineDetails> orientationTypes : quotationForm.getDropDownColumnvalues().getOrientationTypes().values()) {
				for (TurbineDetails orientationType : orientationTypes) {
					if (orientationType.getCategoryDetId() == quotationForm.getSaveBasicDetails().getOrientationTypeId()) {
						orientationType.setDefaultVal(true);
					}
				}
			}

			for (List<TurbineDetails> condensingTypes : quotationForm.getDropDownColumnvalues().getTypesOfCondensor().values()) {
				for (TurbineDetails condensingType : condensingTypes) {
					if (condensingType.getCategoryDetId() == quotationForm.getSaveBasicDetails().getCondensingType()) {
						condensingType.setDefaultVal(true);
					}
				}
			}*/

			for (List<ScopeOfSupply> scopeOfSupply : quotationForm.getDropDownColumnvalues().getScopeOfSupply().values()) {
				for (ScopeOfSupply quotScopes : quotationForm.getScopeOfSupplyList()) {
					for (ScopeOfSupply dropdownScopes : scopeOfSupply) {

						if (dropdownScopes.getSsId() == quotScopes.getSsId()) {
							dropdownScopes.setDefaultValue(true);
						}
					}
				}
			}

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm getTransportationCache(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getTransportationCache(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm getQuotTransCache(QuotationForm quotationForm) {
		try {
			 quotationForm = quotationDao.getQuotTransCache(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public QuotationForm getTransportDataBasedOnFrame(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getTransportDataBasedOnFrame(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	
	@Override
	public SaveBasicDetails saveTransportationData(SaveBasicDetails saveBasicDetails) {
		
		try {
			saveBasicDetails = quotationDao.saveTransportationData(saveBasicDetails);
			if(saveBasicDetails.getTransportBean().getOverwrittenPriceFlag()==1){
				quotationDao.saveQuotRemarks(saveBasicDetails,"OVERWRITE","TRNS",saveBasicDetails.getTransportBean().getOverwrittenPrice(),saveBasicDetails.getTransportBean().getRemarks());
			}
			 

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	@Override
	public QuotationForm getErrectionCommCache(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getErrectionCommCache(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public QuotationForm getErecCommData(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getErecCommData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm saveErecCommission(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.saveErecCommission(quotationForm);
			 if(quotationForm.getErectionCommissionBean().isOverwrittenPriceFlag()){
				 quotationDao.saveQuotRemarks(quotationForm.getSaveBasicDetails(),"OVERWRITE","EREC_COMM",quotationForm.getErectionCommissionBean().getOverwrittenPrice(),quotationForm.getErectionCommissionBean().getRemarks()); 
			 }
			 

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm getPackageData(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getPackageData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm savePackageData(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.savePackageData(quotationForm);
			 if(quotationForm.getPackageBean().getOverwrittenPriceFlag()==1){
				 quotationDao.saveQuotRemarks(quotationForm.getSaveBasicDetails(),"OVERWRITE","PKG",quotationForm.getPackageBean().getOverwrittenPrice(),quotationForm.getPackageBean().getRemarks());
			 }
			 

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm getExcelCostSheetData(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getExcelCostSheetData(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public QuotationForm getValidateFinalCost(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getValidateFinalCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public ReportBean getReportData(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getReportData(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	@Override
	public ReportBean getAddOnReportData(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getAddOnReportData(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	@Override
	public ReportBean getReportDataRev(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getReportDataRev(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	@Override
	public ReportBean getAddOnReportDataRev(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getAddOnReportDataRev(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	
	@Override
	public ReportBean getTechReportData(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getTechReportData(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	
	@Override
	public ReportBean getWordData(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getWordData(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}

	@Override
	public ReportBean getComercialWordData(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getComercialWordData(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	@Override
	public ReportBean getWordDataRev(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getWordDataRev(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}

	@Override
	public ReportBean getComercialWordDataRev(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getComercialWordDataRev(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	@Override
	public ReportBean getComercialWordDataNew(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getComercialWordDataNew(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	@Override
	public ReportBean getComercialWordDataNewRev(QuotationForm quotationForm,ReportBean reportBean) {
		
		try {
			reportBean = quotationDao.getComercialWordDataNewRev(quotationForm,reportBean);

			return reportBean;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return reportBean;
		}
	}
	
	
	@Override
	public QuotationForm getTransportPrice(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getTransportPrice(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm saveProjectCost(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.saveProjectCost(quotationForm);
			 if(quotationForm.getOtherCostsBean().isProjectNewFlag()){
			 quotationDao.saveQuotRemarks(quotationForm.getSaveBasicDetails(),"OVERWRITE","PRJ_COST",quotationForm.getOtherCostsBean().getProjectNewCost(),quotationForm.getOtherCostsBean().getProjectRemarks());
			 }
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm saveVariableCost(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.saveVariableCost(quotationForm);
			 if(quotationForm.getOtherCostsBean().isVarNewFlag()){
			 quotationDao.saveQuotRemarks(quotationForm.getSaveBasicDetails(),"OVERWRITE","VAR_COST",quotationForm.getOtherCostsBean().getVarNewCost(),quotationForm.getOtherCostsBean().getVarRemarks());
			 }
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm saveSparesCost(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.saveSparesCost(quotationForm);
			 if(quotationForm.getOtherCostsBean().isSparesNewFlag()){
			 quotationDao.saveQuotRemarks(quotationForm.getSaveBasicDetails(),"OVERWRITE","SPARES_COST",quotationForm.getOtherCostsBean().getSparesNewCost(),quotationForm.getOtherCostsBean().getSparesRemarks());
			 }
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm getProjectCost(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getProjectCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm getSparesCost(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getSparesCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}


	@Override
	public QuotationForm getVariableCost(QuotationForm quotationForm) {
		
		try {
			 quotationForm = quotationDao.getVariableCost(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}
	
	@Override
	public SaveBasicDetails scopeOfSupplyStatus(SaveBasicDetails saveBasicDetails) {
		
		try {
			saveBasicDetails = quotationDao.scopeOfSupplyStatus(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}


	@Override
	public SaveBasicDetails getQuotRemarks(SaveBasicDetails saveBasicDetails) {
		try {
			saveBasicDetails = quotationDao.getQuotRemarks(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}


	@Override
	public SaveBasicDetails getF2FUboData(SaveBasicDetails saveBasicDetails) {
		try {
			saveBasicDetails = quotationDao.getF2FUboData(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}


	@Override
	public SaveBasicDetails saveF2FUboData(SaveBasicDetails saveBasicDetails) {
		try {
			saveBasicDetails = quotationDao.saveF2FUboData(saveBasicDetails);
			 if(saveBasicDetails.getUboData().isOverwrittenPriceFlg()){
				 quotationDao.saveQuotRemarks(saveBasicDetails,"OVERWRITE","F2F",saveBasicDetails.getUboData().getOverwrittenPrice(),saveBasicDetails.getUboData().getRemarks());
			 }
			
			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}


	@Override
	public SaveBasicDetails saveF2FSap(SaveBasicDetails saveBasicDetails) {
		try {
			saveBasicDetails = quotationDao.saveF2FSap(saveBasicDetails);
			
			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}
	
	@Override
	public SaveBasicDetails saveRemarks(SaveBasicDetails saveBasicDetails) {
		try {
			saveBasicDetails = quotationDao.saveRemarks(saveBasicDetails);
			
			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}


	@Override
	public SaveBasicDetails getF2FSapData(SaveBasicDetails saveBasicDetails) {
		try {
			saveBasicDetails = quotationDao.getF2FSapData(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}


	@Override
	public QuotationForm createQuotRev(QuotationForm quotationForm) {
		try {
			 quotationForm = quotationDao.createQuotRev(quotationForm);

			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm getQuotRevData(QuotationForm quotationForm) {
		try {
			 quotationForm = quotationDao.getQuotRevData(quotationForm);
			 quotationForm = setDefaultValues(quotationForm);
			return quotationForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return quotationForm;
		}
	}

	@Override
	public QuotationForm getQuotRevNo(Integer quotId){
		try {
			return quotationDao.getQuotRevNo(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public QuotationForm getQuestionInfo(Integer framePowerId){
		try {
			return quotationDao.getQuestionInfo(framePowerId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	

	@Override
	public QuotationForm getScopeOfSupStatus(QuotationForm quotationForm){
		try {
			return quotationDao.getScopeOfSupStatus(quotationForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public QuotationForm getPackageCache(QuotationForm quotationForm) {
		try {
			return quotationDao.getPackageCache(quotationForm);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}


	@Override
	public QuotationForm fetchUserData() {
		try {
			return quotationDao.fetchUserData();
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}


	@Override
	public SaveBasicDetails quotStatusComplete(SaveBasicDetails saveBasicDetails) {
		try {
			saveBasicDetails = quotationDao.quotStatusComplete(saveBasicDetails);

			return saveBasicDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return saveBasicDetails;
		}
	}

	

	@Override
	public QuotationForm getVarCostDet(Integer quotId) {
		try {
			return quotationDao.getVarCostDet(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public QuotationForm downloadPdf(Integer quotId) {
		try {
			return quotationDao.downloadPdf(quotId);
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
	@Override
	public QuotationForm getDocument() {
		try {
			return quotationDao.getDocument();
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return null;
		}
	}
	
}
