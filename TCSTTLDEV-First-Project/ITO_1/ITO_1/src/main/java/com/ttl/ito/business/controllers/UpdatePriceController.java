
package com.ttl.ito.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.service.UpdatePriceService;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 
 * Created by Basavesh B R
 * Class Name: UpdatePriceController 
 * This class is used to handle update price related requests such as 
 * to update frame price, transportation unit price, package & forwarding price, erection And commission and number of vehicles 
 * for each vehicle for a given frame and methods for 'work flow' of all update requests.  
 *   
 * 
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "update")
@RestController
public class UpdatePriceController {
		private Logger logger = Logger.getLogger(QuotationController.class);

		@Autowired
		private UpdatePriceService updatePriceService;
	
		@RequestMapping(value = "/updateTransportPrice", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm updatePriceTransport(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.updatePriceTransport(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 

		@RequestMapping(value = "/saveTransportPrice", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm saveTransportPrice(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.saveTransportPrice(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		@RequestMapping(value = "/saveECUpdatedPrice", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm saveECUpdatedPrice(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.saveECUpdatedPrice(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		
		@RequestMapping(value = "/getUpdatePriceReqGrid", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm getUpdatePriceReqGrid(@RequestBody Integer userId, HttpServletRequest request) {

			QuotationForm quotationForm = new QuotationForm();
			quotationForm.setLoggedInUserId(userId);
			try {
				quotationForm = updatePriceService.getUpdatePriceReqGrid(quotationForm);

				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		
		@RequestMapping(value = "/updateStatusAndSubmit", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm updateStatus(@RequestBody QuotationForm quotationForm, HttpServletRequest request) {

			try {
				quotationForm = updatePriceService.updateStatus(quotationForm);

				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		
		@RequestMapping(value = "/getSavedUpdatedPriceData", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm editUpdatePriceData(@RequestBody SaveBasicDetails saveBasicDetails, HttpServletRequest request) {

			QuotationForm quotationForm = new QuotationForm();
			quotationForm.setSaveBasicDetails(saveBasicDetails);
			
			try {
				quotationForm = updatePriceService.editUpdatePriceData(quotationForm);

				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		
		private void clearMessageFrom(QuotationForm quotationForm) {
			if (null != quotationForm) {
				quotationForm.getMsgToUser().clear();
			}
		}
		
		@RequestMapping(value = "/getECUpdatePriceData", method = RequestMethod.POST, produces = "application/json")
		public AdminForm getECUpdatePriceData(@RequestBody AdminForm adminForm,HttpServletRequest request) {

			try {
				adminForm = updatePriceService.getECUpdatePriceData(adminForm);
				
				return adminForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return adminForm;
			}
		} 
		
		
		@RequestMapping(value = "/createECPriceUpdateRequest", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createECPriceUpdateRequest(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				quotationForm = updatePriceService.createECPriceUpdateRequest(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		/*@RequestMapping(value = "/createTurbInstrUpdateRequest", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createTurbInstrUpdateRequest(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				quotationForm = updatePriceService.createTurbInstrUpdateRequest(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
*/

		@RequestMapping(value = "/getTransportUpdatedPriceListDomestic", method = RequestMethod.POST, produces = "application/json")
		public AdminForm getTransportUpdatedPriceListDomestic(@RequestBody AdminForm adminForm,HttpServletRequest request) {

			try {
				adminForm = updatePriceService.getTransportUpdatedPriceListDomestic(adminForm);
				
				return adminForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return adminForm;
			}
		} 
		
		@RequestMapping(value = "/getTransportUpdatedPriceListExport", method = RequestMethod.POST, produces = "application/json")
		public AdminForm getTransportUpdatedPriceListExport(@RequestBody AdminForm adminForm,HttpServletRequest request) {

			try {
				adminForm = updatePriceService.getTransportUpdatedPriceListExport(adminForm);
				
				return adminForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return adminForm;
			}
		} 
		
		@RequestMapping(value = "/updatePackagePrice", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm updatePackagePrice(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				quotationForm = updatePriceService.updatePackagePrice(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		
		
		@RequestMapping(value = "/getPackageWithPriceList", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm getPackageWithPriceList(HttpServletRequest request) {

			QuotationForm quotationForm = new QuotationForm();
			try {
				quotationForm = updatePriceService.getPackageWithPriceList(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		@RequestMapping(value = "/updatePriceTransportDomestic", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm updatePriceTransportDomestic(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				quotationForm = updatePriceService.updatePriceTransportDomestic(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		@RequestMapping(value = "/saveUpdatedNoOfVehicles", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm saveUpdatedNoOfVehicles(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.saveUpdatedNoOfVehicles(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		@RequestMapping(value = "/saveUpdatedPackagePrice", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm saveUpdatedPackagePrice(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.savePkgUpdatedPrice(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		@RequestMapping(value = "/updateTransportPriceExport", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm updatePriceTransportExport(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.updatePriceTransportExport(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		@RequestMapping(value = "/getNewFramesForNoOfVehicles", method = RequestMethod.POST, produces = "application/json")
		public AdminForm getNewFramesForNoOfVehicles(HttpServletRequest request) {

			AdminForm adminForm = new AdminForm();
			try {
				adminForm = updatePriceService.getNewFramesForNoOfVehicles(adminForm);
				
				return adminForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return adminForm;
			}
		}
		
		@RequestMapping(value = "/getF2FUBOMast", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm getF2FUBOMast(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.getF2FUBOMast(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		@RequestMapping(value = "/createUBOSheet", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createUBOSheet(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {

			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.createUBOSheet(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		} 
		
		/*@RequestMapping(value = "/createOverheadSheet", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createOverheadSheet(@RequestBody QuotationForm quotationForm,HttpServletRequest request){
			
			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.createOverheadSheet(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		*/
		/*@RequestMapping(value = "/createTurbineInstruments", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createTurbineInstruments(@RequestBody QuotationForm quotationForm,HttpServletRequest request){
		
			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.createTurbineInstruments(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		*/
		/*@RequestMapping(value = "/createSubContracting", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createSubContracting(@RequestBody QuotationForm quotationForm,HttpServletRequest request){
			
			try {
				 clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.createSubContracting(quotationForm);
			
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		*/
		
		@RequestMapping(value = "/getNewFramesForUBO", method = RequestMethod.POST, produces = "application/json")
		public AdminForm getNewFramesForUBO(HttpServletRequest request){
			
			AdminForm adminForm = new AdminForm();
			try {
				adminForm = updatePriceService.getNewFramesForUBO(adminForm);
			
				return adminForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return adminForm;
			}
		}
		
		@RequestMapping(value = "/createDboEleUpdateRequestPrice", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createDboEleUpdateRequestPrice(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {
			try {
				clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.createDboEleUpdateRequestPrice(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		
		@RequestMapping(value = "/createDboEleUpdateRequestPriceAddInstr", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createDboEleUpdateRequestAddInstrPrice(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {
			try {
				clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.createDboEleUpdateRequestPriceAddInstr(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		
		@RequestMapping(value = "/createDboEleUpdateRequestPriceSplAddOn", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createDboEleUpdateRequestPriceSplAddOn(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {
			try {
				clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.createDboEleUpdateRequestPriceSplAddOn(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}
		
		@RequestMapping(value = "/createDboEleUpdateRequestPriceAddOn", method = RequestMethod.POST, produces = "application/json")
		public DBOForm createDboEleUpdateRequestPriceAddOn(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.createDboEleUpdateRequestPriceAddOn(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}
	@RequestMapping(value = "/createUpdateAddon", method = RequestMethod.POST, produces = "application/json")
		public DBOForm createUpdateAddon(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.createUpdateAddon(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}
	@RequestMapping(value = "/createUpdateProbes", method = RequestMethod.POST, produces = "application/json")
	public DBOForm createUpdateProbes(@RequestBody DBOForm dboForm,HttpServletRequest request) {
		try {
			dboForm = updatePriceService.createUpdateProbes(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
	
	@RequestMapping(value = "/createUpdateAddon1", method = RequestMethod.POST, produces = "application/json")
	public DBOForm createUpdateAddon1(@RequestBody DBOForm dboForm,HttpServletRequest request) {
		try {
			dboForm = updatePriceService.createUpdateAddon1(dboForm);
			
			return dboForm;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return dboForm;
		}
	}
		@RequestMapping(value = "/createDboMechUpdateRequestPrice", method = RequestMethod.POST, produces = "application/json")
		public DBOForm createDboMechUpdateRequestPrice(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.createDboMechUpdateRequestPrice(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}

		@RequestMapping(value = "/createDboMechUpdateRequestCol", method = RequestMethod.POST, produces = "application/json")
		public DBOForm createDboMechUpdateRequestCol(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.createDboMechUpdateRequestCol(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}
		@RequestMapping(value = "/createDboEleUpdateRequestCol", method = RequestMethod.POST, produces = "application/json")
		public QuotationForm createDboEleUpdateRequestCol(@RequestBody QuotationForm quotationForm,HttpServletRequest request) {
			try {
				clearMessageFrom(quotationForm);
				quotationForm = updatePriceService.createDboEleUpdateRequestCol(quotationForm);
				
				return quotationForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return quotationForm;
			}
		}


		@RequestMapping(value = "/getDBOEleUpdatePriceData", method = RequestMethod.POST, produces = "application/json")
		public DBOForm getDBOEleUpdatePriceData(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.getDBOEleUpdatePriceData(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}
		
		
		
		


		@RequestMapping(value = "/getDBOMechUpdatePriceData", method = RequestMethod.POST, produces = "application/json")
		public DBOForm getDBOMechUpdatePriceData(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.getDBOMechUpdatePriceData(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}


		@RequestMapping(value = "/getDBOEleUpdateColData", method = RequestMethod.POST, produces = "application/json")
		public DBOForm getDBOEleUpdateColData(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.getDBOEleUpdateColData(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}


		@RequestMapping(value = "/getDBOMechUpdateColData", method = RequestMethod.POST, produces = "application/json")
		public DBOForm getDBOMechUpdateColData(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.getDBOMechUpdateColData(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}
		
	@RequestMapping(value = "/getDBOEleUpdateColData1", method = RequestMethod.POST, produces = "application/json")
		public DBOForm getDBOMechUpdateColData1(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			
			Integer itemId=dboForm.getItemId();
			String panelType=dboForm.getPanelType();
			try {
				dboForm = updatePriceService.getDBOEleUpdateColData1(itemId,panelType,dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}

		
		
		@RequestMapping(value = "/getDBOMechUpdateColData1", method = RequestMethod.POST, produces = "application/json")
		public DBOForm getDBOMechUpdateColData1(@RequestBody Integer itemId , HttpServletRequest request){
			
			try {

				return updatePriceService.getDBOMechUpdateColData1(itemId);
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return null;
			}
			
		}
		@RequestMapping(value = "/updateGetAddOn", method = RequestMethod.POST, produces = "application/json")
		public DBOForm updateGetAddOn(@RequestBody DBOForm dboForm,HttpServletRequest request) {
			try {
				dboForm = updatePriceService.updateGetAddOn(dboForm);
				
				return dboForm;
			} catch (Exception e) {
				logger.error("Exception :" + e);
				return dboForm;
			}
		}
		
}
