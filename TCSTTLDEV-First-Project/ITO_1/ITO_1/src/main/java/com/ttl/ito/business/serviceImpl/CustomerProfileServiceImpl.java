package com.ttl.ito.business.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ttl.ito.business.beans.ConsultantDetails;
import com.ttl.ito.business.beans.CustomerDetails;
import com.ttl.ito.business.beans.EndUserDetails;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.dao.CustomerProfileDao;
import com.ttl.ito.business.service.CustomerProfileService;
import com.ttl.ito.common.Utility.SendMail;
import com.ttl.ito.internal.beans.LoginBO;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Value("${EMAIL_ID}")
	public String EMAIL_ID;
	
	@Autowired
	private SendMail mail;
	
	Logger logger = Logger.getLogger(CustomerProfileServiceImpl.class);
	@Autowired
	private CustomerProfileDao customerProfileDao;

	@Override
	public CustomerDetails createCustomerDetails(QuotationForm quotationForm, CustomerDetails customerDetails) {
		
		try {
			customerDetails = customerProfileDao.createCustomerDetails(customerDetails);
			//mail.sendMail(String[] to, String[] cc, String[] bcc, String subject, String htmlMessage, List<String> filePath)
			
			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String subject="New Customer has been created successfully.";
			
			mailBody.append("Hi  "+customerDetails.getCreatedBy()+",");
			//mailBody.append("Hi User");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Customer details:");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("===============");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Name : "+ customerDetails.getCustName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("SAP code : "+ customerDetails.getCustCode());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Customer Type : "+ customerDetails.getCustType());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact person : "+ customerDetails.getContactPersonName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact number : "+ customerDetails.getContactNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Email : "+ customerDetails.getEmailId());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Created by : "+ customerDetails.getCreatedBy() );
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Created date : "+ customerDetails.getCreatedDate());
			mailBody.append(System.getProperty("line.separator"));
			
			String mailText=mailBody.toString();
			
			//mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, subject , mailText,files );

		} catch (Exception e) {
			customerDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return customerDetails;
	}

	@Override
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails) {

		try {

			customerDetails = customerProfileDao.updateCustomerDetails(customerDetails);
			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String subject="Customer details updated successfully.";
			
			mailBody.append("Hi  "+customerDetails.getModifiedBy()+",");
			//mailBody.append("Hi User");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Updated Customer details:");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("===============");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Name : "+ customerDetails.getCustName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("SAP code : "+ customerDetails.getCustCode());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Customer Type : "+ customerDetails.getCustType());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact person : "+ customerDetails.getContactPersonName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact number : "+ customerDetails.getContactNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Email : "+ customerDetails.getEmailId());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Created by : "+ customerDetails.getCreatedBy() );
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Created date : "+ customerDetails.getCreatedDate());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified By : "+ customerDetails.getModifiedBy());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified date : "+ customerDetails.getModifiedDate());
			mailBody.append(System.getProperty("line.separator"));
			
			String mailText=mailBody.toString();
			
			//mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, subject , mailText,files );
		} catch (Exception e) {
			customerDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return customerDetails;
	}

	

	@Override
	public EndUserDetails createEndUserDetails(QuotationForm quotationForm, EndUserDetails endUserDetails) {
		
		try {
			endUserDetails = customerProfileDao.createEndUserDetails(endUserDetails);
			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String subject="New Enduser has been created successfully.";
			
			//mailBody.append("Hi ," +endUserDetails.getModifiedBy());
			mailBody.append("Hi "+endUserDetails.getCreatedBy()+",");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("EndUser Details :");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("================");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Name : "+ endUserDetails.getEndUserName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact number : "+ endUserDetails.getContactNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Email : "+ endUserDetails.getEmailId());
			mailBody.append(System.getProperty("line.separator"));		
			mailBody.append("Created by : "+ endUserDetails.getCreatedBy() );
			mailBody.append(System.getProperty("line.separator"));	
			mailBody.append("Created date : "+ endUserDetails.getCreatedDate());
			mailBody.append(System.getProperty("line.separator"));
			String mailText=mailBody.toString();
			
			//mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, subject , mailText,files );
		} catch (Exception e) {
			endUserDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return endUserDetails;
	}

	@Override
	public ConsultantDetails updateConsultantDetails(ConsultantDetails consultantDetails) {
	
		try {
			consultantDetails = customerProfileDao.updateConsultantDetails(consultantDetails);
			
			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String subject="Consultant has been updated successfully.";
			
			mailBody.append("Hi "+consultantDetails.getModifiedBy()+",");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("================");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Consultant Name : "+ consultantDetails.getFirmName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact Person Name : "+ consultantDetails.getSpocName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact number : "+ consultantDetails.getContactNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Email : "+ consultantDetails.getEmailId());
			mailBody.append(System.getProperty("line.separator"));		
			mailBody.append("Created by : "+ consultantDetails.getCreatedBy() );
			mailBody.append(System.getProperty("line.separator"));	
			mailBody.append("Created date : "+ consultantDetails.getCreatedDate());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified By : "+ consultantDetails.getModifiedBy());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified date : "+ consultantDetails.getModifiedDate());
			mailBody.append(System.getProperty("line.separator"));
			String mailText=mailBody.toString();
			
			//mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, subject , mailText,files );

			
			
			
		} catch (Exception e) {
			consultantDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return consultantDetails;
	}

	@Override
	public EndUserDetails updateEndUserDetails(EndUserDetails endUserDetails) {
		
		try {

			endUserDetails = customerProfileDao.updateEndUserDetails(endUserDetails);
			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String subject="Enduser Details has been updated successfully.";
			
			mailBody.append("Hi "+endUserDetails.getModifiedBy()+",");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Name : "+ endUserDetails.getEndUserName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact number : "+ endUserDetails.getContactNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Email : "+ endUserDetails.getEmailId());
			mailBody.append(System.getProperty("line.separator"));		
			mailBody.append("Created by : "+ endUserDetails.getCreatedBy() );
			mailBody.append(System.getProperty("line.separator"));	
			mailBody.append("Created date : "+ endUserDetails.getCreatedDate());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified By : "+ endUserDetails.getModifiedBy());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified date : "+ endUserDetails.getModifiedDate());
			mailBody.append(System.getProperty("line.separator"));
			String mailText=mailBody.toString();
			
			//mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, subject , mailText,files );

		} catch (Exception e) {
			endUserDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return endUserDetails;
	}

	@Override
	public CustomerDetails getCustomerDetails(QuotationForm quotationForm,CustomerDetails customerDetails) {
	
		List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
		CustomerDetails resultCustDetails = new CustomerDetails();
		
		try {

			customerList = quotationForm.getCustomerProfileForm().getCustomerList();
			for(CustomerDetails custDetails :customerList){
				if(customerDetails.getCustId()==custDetails.getCustId() && customerDetails.getCustCode()==custDetails.getCustCode()){
					resultCustDetails = custDetails;
					break;
				}
			}
			
		} catch (Exception e) {
			resultCustDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return resultCustDetails;
	}

	@Override
	public ConsultantDetails getConsultantDetails(QuotationForm quotationForm,ConsultantDetails consultantDetails) {
	
		List<ConsultantDetails> consltList = new ArrayList<ConsultantDetails>();
		ConsultantDetails resultConsltDetails = new ConsultantDetails();
		
		try {

			consltList = quotationForm.getCustomerProfileForm().getConsultantList();
		/*	for(ConsultantDetails consltDetails :consltList){
				if(consltDetails.getConsultantId()==consultantDetails.getConsultantId()){
					resultConsltDetails = consltDetails;
					break;
				}
			}*/
			
		} catch (Exception e) {
			resultConsltDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}

		return resultConsltDetails;
	}

	@Override
	public EndUserDetails getEndUserDetails(QuotationForm quotationForm,EndUserDetails endUserDetails) {
		
		List<EndUserDetails> enduserList = new ArrayList<EndUserDetails>();
		EndUserDetails resultEnduserDetails = new EndUserDetails();
		
		try {

			enduserList = quotationForm.getCustomerProfileForm().getEndUserList();
			for(EndUserDetails endusrDetails :enduserList){
				if(endusrDetails.getEndUserId()==endUserDetails.getEndUserId()){
					resultEnduserDetails = endUserDetails;
					break;
				}
			}
			
		} catch (Exception e) {
			resultEnduserDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
		 
		return resultEnduserDetails;
		
	}

	
	@Override
	public ConsultantDetails createConsultantDetails(ConsultantDetails consultantDetails) {
		
		try {
			consultantDetails = customerProfileDao.createConsultantDetails(consultantDetails);

			List<String> files= new ArrayList<String>();
			StringBuffer mailBody = new StringBuffer();
			String subject="New Consultant has been Created successfully.";
			
			//mailBody.append("Hi  ,"+consultantDetails.getModifiedBy());
			mailBody.append("Hi "+consultantDetails.getCreatedBy() +",");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Consultant Details :");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("================");
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Consultant Name : "+ consultantDetails.getFirmName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact Person Name : "+ consultantDetails.getSpocName());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Contact number : "+ consultantDetails.getContactNumber());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Email : "+ consultantDetails.getEmailId());
			mailBody.append(System.getProperty("line.separator"));		
			mailBody.append("Created by : "+ consultantDetails.getCreatedBy() );
			mailBody.append(System.getProperty("line.separator"));	
			mailBody.append("Created date : "+ consultantDetails.getCreatedDate());
			/*mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified By : "+ consultantDetails.getModifiedBy());
			mailBody.append(System.getProperty("line.separator"));
			mailBody.append("Modified date : "+ consultantDetails.getModifiedDate());*/
			mailBody.append(System.getProperty("line.separator"));
			String mailText=mailBody.toString();
			
			//mail.sendMail(new String[] {"itoapplication@triveniturbines.com"}, null, null, subject , mailText,files );


		} catch (Exception e) {
			consultantDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(e);
		}
	
		return consultantDetails;
	}

	@Override
	public CustomerDetails getCustomerData(String oppSeqNo, CustomerDetails customerDetails) {
		
		try {
			customerDetails = customerProfileDao.getCustomerData(oppSeqNo,customerDetails);

			return customerDetails;
		} catch (Exception e) {
			logger.error("Exception :" + e);
			return customerDetails;
		}
	}

}
