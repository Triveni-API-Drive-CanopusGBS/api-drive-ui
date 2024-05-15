package com.ttl.ito.business.service;

import org.springframework.stereotype.Service;

import com.ttl.ito.business.beans.ConsultantDetails;
import com.ttl.ito.business.beans.CustomerDetails;
import com.ttl.ito.business.beans.EndUserDetails;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.internal.beans.LoginBO;

@Service
public interface CustomerProfileService {

	public CustomerDetails createCustomerDetails(QuotationForm quotationForm,CustomerDetails customerDetails);
	
	public EndUserDetails createEndUserDetails(QuotationForm quotationForm,EndUserDetails endUserDetails);
	
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails);
	
	public EndUserDetails updateEndUserDetails(EndUserDetails endUserDetails);
	
	public CustomerDetails getCustomerDetails(QuotationForm quotationForm,CustomerDetails customerDetails);
	
	public ConsultantDetails getConsultantDetails(QuotationForm quotationForm,ConsultantDetails consultantDetails);
	
	public EndUserDetails getEndUserDetails(QuotationForm quotationForm,EndUserDetails endUserDetails);
	
	public ConsultantDetails createConsultantDetails(ConsultantDetails consultantDetails);
	
	public ConsultantDetails updateConsultantDetails(ConsultantDetails consultantDetails);

	public CustomerDetails getCustomerData(String oppSeqNo, CustomerDetails customerDetails);
	
}
