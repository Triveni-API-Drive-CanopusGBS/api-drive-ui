package com.ttl.ito.business.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerProfileForm {

	private List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
	private List<ConsultantDetails> consultantList = new ArrayList<ConsultantDetails>();
	private List<EndUserDetails> endUserList = new ArrayList<EndUserDetails>();
	Map<Integer, String> msgToUser = new HashMap<Integer, String>();
	
	/**
	 * @return the customerList
	 */
	public List<CustomerDetails> getCustomerList() {
		return customerList;
	}
	/**
	 * @param customerList the customerList to set
	 */
	public void setCustomerList(List<CustomerDetails> customerList) {
		this.customerList = customerList;
	}
	/**
	 * @return the consultantList
	 */
	public List<ConsultantDetails> getConsultantList() {
		return consultantList;
	} 
	/**
	 * @param consultantList the consultantList to set
	 */
	public void setConsultantList(List<ConsultantDetails> consultantList) {
		this.consultantList = consultantList;
	}
	/**
	 * @return the endUserList
	 */
	public List<EndUserDetails> getEndUserList() {
		return endUserList;
	}
	/**
	 * @param endUserList the endUserList to set
	 */
	public void setEndUserList(List<EndUserDetails> endUserList) {
		this.endUserList = endUserList;
	}
	/**
	 * @return the msgToUser
	 */
	public Map<Integer, String> getMsgToUser() {
		return msgToUser;
	}
	/**
	 * @param msgToUser the msgToUser to set
	 */
	public void setMsgToUser(Map<Integer, String> msgToUser) {
		this.msgToUser = msgToUser;
	}
}
