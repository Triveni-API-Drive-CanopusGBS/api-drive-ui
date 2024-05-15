package com.ttl.ito.business.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ttl.ito.business.beans.ConsultantDetails;
import com.ttl.ito.business.beans.CustomerDetails;

import com.ttl.ito.business.beans.EndUserDetails;
import com.ttl.ito.business.beans.FirmDetails;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.service.CustomerProfileService;

/**
 * 
 * Created by Basavesh B R
 * Class Name: CustomerProfileManageController 
 * This class is used to handle customer profile related requests such as 
 * to create customer, update customer, create consultant, update consultant,
 * create enduser , update enduser
 *  
 * 
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "custprofile")
@RestController
public class CustomerProfileManageController {

	private Logger logger = Logger.getLogger(CustomerProfileManageController.class);

	@Autowired
	CustomerProfileService customerProfileService;

	@RequestMapping(value = "/createCust", method = RequestMethod.POST, produces = "application/json")
	public CustomerDetails createCustomerDeatils(@RequestBody CustomerDetails customerDetails, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		
		try {

			customerDetails = customerProfileService.createCustomerDetails(quotationForm, customerDetails);
			
			return customerDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return customerDetails;
		}
	}

	@RequestMapping(value = "/createConsultant", method = RequestMethod.POST, produces = "application/json")
	public ConsultantDetails createConsultantDetails(@RequestBody ConsultantDetails consultantDetails, HttpServletRequest request) {

		Integer errorkey = 0;
		QuotationForm quotationForm = new QuotationForm();
		Map<Integer, String> firmMap = new HashMap<>();
		try {
		
			consultantDetails = customerProfileService.createConsultantDetails(consultantDetails);
			
			if (consultantDetails.getMsgToUser().containsKey(errorkey)) {
				quotationForm.getCustomerProfileForm().getConsultantList().add(consultantDetails);
				quotationForm.getFirmDetails().put(consultantDetails.getFirmId(), consultantDetails.getFirmName());

				firmMap = quotationForm.getFirmDetails();
				List<FirmDetails> firmList = new ArrayList<>();
				for (int firmId : firmMap.keySet()) {
					FirmDetails firmDetails = new FirmDetails();
					firmDetails.setFirmId(firmId);
					firmDetails.setFirmName(firmMap.get(firmId));
					firmList.add(firmDetails);
				}

				quotationForm.setFirmDetails(firmMap);
			}
	
			return consultantDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return consultantDetails;
		}
	}

	@RequestMapping(value = "/createEnduser", method = RequestMethod.POST, produces = "application/json")
	public EndUserDetails createEndUserDetails(@RequestBody EndUserDetails endUserDetails, HttpServletRequest request) {

		QuotationForm quotationForm = new QuotationForm();
		try {
			
			endUserDetails = customerProfileService.createEndUserDetails(quotationForm, endUserDetails);

			return endUserDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return endUserDetails;
		}
	}

	@RequestMapping(value = "/updateCust", method = RequestMethod.POST, produces = "application/json")
	public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails customerDetails, HttpServletRequest request) {

		try {
			
			customerDetails = customerProfileService.updateCustomerDetails(customerDetails);
			
			return customerDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return customerDetails;
		}
	}

	@RequestMapping(value = "/updateConsultant", method = RequestMethod.POST, produces = "application/json")
	public ConsultantDetails updateConsultantDetails(@RequestBody ConsultantDetails consultantDetails, HttpServletRequest request) {

		try {
			
			consultantDetails = customerProfileService.updateConsultantDetails(consultantDetails);

			return consultantDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return consultantDetails;
		}
	}

	@RequestMapping(value = "/updateEnduser", method = RequestMethod.POST, produces = "application/json")
	public EndUserDetails updateEnduserDetails(@RequestBody EndUserDetails endUserDetails, HttpServletRequest request) {
		
		try {
			
			endUserDetails = customerProfileService.updateEndUserDetails(endUserDetails);

			return endUserDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return endUserDetails;
		}
	}
	
	@RequestMapping(value = "/getCustomerData", method = RequestMethod.POST, produces = "application/json")
	public CustomerDetails getCustomerData(@RequestBody String oppSeqNo, HttpServletRequest request) {
		
		CustomerDetails customerDetails = new CustomerDetails();
		try {
			
			customerDetails = customerProfileService.getCustomerData(oppSeqNo, customerDetails);

			return customerDetails;

		} catch (Exception e) {
			logger.error("Exception :" + e);
			return customerDetails;
		}
	}
	
	
}
