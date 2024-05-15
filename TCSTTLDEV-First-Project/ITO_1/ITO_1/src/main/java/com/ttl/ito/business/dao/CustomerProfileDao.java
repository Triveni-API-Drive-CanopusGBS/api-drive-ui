package com.ttl.ito.business.dao;

import org.springframework.stereotype.Repository;

import com.ttl.ito.business.beans.ConsultantDetails;
import com.ttl.ito.business.beans.CustomerDetails;
import com.ttl.ito.business.beans.EndUserDetails;
import com.ttl.ito.internal.beans.LoginBO;

@Repository
public interface CustomerProfileDao {

	public CustomerDetails createCustomerDetails(CustomerDetails customerDetails);
	
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails);
	
	public EndUserDetails createEndUserDetails(EndUserDetails endUserDetails);
	
	public EndUserDetails updateEndUserDetails(EndUserDetails endUserDetails);
	
	public ConsultantDetails createConsultantDetails(ConsultantDetails consultantDetails); //new
	
	public ConsultantDetails updateConsultantDetails(ConsultantDetails consultantDetails); //new
	
	public CustomerDetails getCustomerData(String oppSeqNo, CustomerDetails customerDetails);
	
}
