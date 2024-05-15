package com.ttl.ito.business.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ttl.ito.business.beans.ConsultantDetails;
import com.ttl.ito.business.beans.CustomerDetails;

import com.ttl.ito.business.beans.EndUserDetails;
import com.ttl.ito.business.dao.CustomerProfileDao;
import com.ttl.ito.common.Utility.RunTimeExec;
import com.ttl.ito.common.Utility.UtilityMethods;

@Repository
public class CustomerProfileDaoImpl implements CustomerProfileDao {
	private Logger logger = Logger.getLogger(CustomerProfileDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RunTimeExec runTimeExec;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Override
	public CustomerDetails createCustomerDetails(CustomerDetails customerDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuot = null;
		ResultSet resultSetCustomerDetails = null;
		
		
		
		CustomerDetails custDetails = new CustomerDetails();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_CUSTOMER (?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			/* set values to procedure inputs */

			callableStatement.setString("CUST_NAME", customerDetails.getCustName());
			callableStatement.setString("CUST_TYP", customerDetails.getCustType());
			callableStatement.setString("CUST_CONT_PERSON", customerDetails.getContactPersonName());
			callableStatement.setString("CUST_CONT_NO", customerDetails.getContactNumber());
			callableStatement.setString("EMAIL", customerDetails.getEmailId());
			callableStatement.setString("ADDRESS", customerDetails.getAddress());
			callableStatement.setString("CITY", customerDetails.getCity());
			callableStatement.setString("DISTRICT", customerDetails.getDistrict());
			callableStatement.setString("STATE", customerDetails.getState());
			callableStatement.setString("COUNTRY", customerDetails.getCountry());
			callableStatement.setString("PIN_CD", customerDetails.getPincode());
			callableStatement.setInt("MOD_BY", customerDetails.getModifiedById());
			callableStatement.setInt("CUST_ID", 0);
			callableStatement.setInt("IS_ACTIVE",1);
			
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				custDetails.setSuccessCode(resultOutParameterInt);
				custDetails.setSuccessMsg(resultOutParameterString);
				custDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetCustomerDetails = callableStatement.getResultSet();
					while (resultSetCustomerDetails.next()) {

						custDetails.setCustId(resultSetCustomerDetails.getInt("CUST_ID"));
						custDetails.setCustName(resultSetCustomerDetails.getString("CUST_NAME"));
						custDetails.setCustCode(resultSetCustomerDetails.getString("CUST_CODE"));
						custDetails.setCustType(resultSetCustomerDetails.getString("CUST_TYP"));
						custDetails.setContactPersonName(resultSetCustomerDetails.getString("CUST_CONT_PERSON"));
						custDetails.setContactNumber(resultSetCustomerDetails.getString("CUST_CONT_NO"));
						custDetails.setEmailId(resultSetCustomerDetails.getString("EMAIL"));
						custDetails.setAddress(resultSetCustomerDetails.getString("ADDRESS"));
						custDetails.setCity(resultSetCustomerDetails.getString("CITY"));
						custDetails.setDistrict(resultSetCustomerDetails.getString("DISTRICT"));
						custDetails.setState(resultSetCustomerDetails.getString("STATE"));
						custDetails.setCountry(resultSetCustomerDetails.getString("COUNTRY"));
						custDetails.setPincode(resultSetCustomerDetails.getString("PIN_CD"));
						custDetails.setCreatedDate(resultSetCustomerDetails.getString("CREAT_DT"));
						custDetails.setModifiedDate(resultSetCustomerDetails.getString("MOD_DT"));
						custDetails.setCreatedById(resultSetCustomerDetails.getInt("CREAT_BY_ID"));
						custDetails.setModifiedById(resultSetCustomerDetails.getInt("MOD_BY_ID"));
						custDetails.setCreatedBy(resultSetCustomerDetails.getString("CREAT_BY"));
						custDetails.setModifiedBy(resultSetCustomerDetails.getString("MOD_BY"));
						if(resultSetCustomerDetails.getInt("IS_ACTIVE")==1){
							custDetails.setActive(true);
						}else{
							custDetails.setActive(false);
						}
						
					}
				}
			}

		} catch (Exception e) {
			custDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : " + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
			UtilityMethods.closeResource(connection, callableStatement, resultSetCustomerDetails);
		}
		return custDetails;
	}

	
	@Override
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails) {
		
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuot = null;
		ResultSet resultSetCustomerDetails = null;

		CustomerDetails custDetails = new CustomerDetails();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_CUSTOMER (?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			/* set values to procedure inputs */
			callableStatement.setString("CUST_NAME", customerDetails.getCustName());
			callableStatement.setString("CUST_TYP", customerDetails.getCustType());
			callableStatement.setString("CUST_CONT_PERSON", customerDetails.getContactPersonName());
			callableStatement.setString("CUST_CONT_NO", customerDetails.getContactNumber());
			callableStatement.setString("EMAIL", customerDetails.getEmailId());
			callableStatement.setString("ADDRESS", customerDetails.getAddress());
			callableStatement.setString("CITY", customerDetails.getCity());
			callableStatement.setString("DISTRICT", customerDetails.getDistrict());
			callableStatement.setString("STATE", customerDetails.getState());
			callableStatement.setString("COUNTRY", customerDetails.getCountry());
			callableStatement.setString("PIN_CD", customerDetails.getPincode());
			callableStatement.setInt("MOD_BY", customerDetails.getModifiedById());
			callableStatement.setInt("CUST_ID", customerDetails.getCustId());
			if(customerDetails.isActive()){
				callableStatement.setInt("IS_ACTIVE",1);	
			}else{
				callableStatement.setInt("IS_ACTIVE",0);	
			}
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				custDetails.setSuccessCode(resultOutParameterInt);
				custDetails.setSuccessMsg(resultOutParameterString);
				custDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetCustomerDetails = callableStatement.getResultSet();
					while (resultSetCustomerDetails.next()) {

						custDetails.setCustId(resultSetCustomerDetails.getInt("CUST_ID"));
						custDetails.setCustName(resultSetCustomerDetails.getString("CUST_NAME"));
						custDetails.setCustCode(resultSetCustomerDetails.getString("CUST_CODE"));
						custDetails.setCustType(resultSetCustomerDetails.getString("CUST_TYP"));
						custDetails.setContactPersonName(resultSetCustomerDetails.getString("CUST_CONT_PERSON"));
						custDetails.setContactNumber(resultSetCustomerDetails.getString("CUST_CONT_NO"));
						custDetails.setEmailId(resultSetCustomerDetails.getString("EMAIL"));
						custDetails.setAddress(resultSetCustomerDetails.getString("ADDRESS"));
						custDetails.setCity(resultSetCustomerDetails.getString("CITY"));
						custDetails.setState(resultSetCustomerDetails.getString("STATE"));
						custDetails.setCountry(resultSetCustomerDetails.getString("COUNTRY"));
						custDetails.setPincode(resultSetCustomerDetails.getString("PIN_CD"));
						custDetails.setCreatedDate(resultSetCustomerDetails.getString("CREAT_DT"));
						custDetails.setModifiedDate(resultSetCustomerDetails.getString("MOD_DT"));
						custDetails.setCreatedById(resultSetCustomerDetails.getInt("CREAT_BY_ID"));
						custDetails.setModifiedById(resultSetCustomerDetails.getInt("MOD_BY_ID"));
						custDetails.setCreatedBy(resultSetCustomerDetails.getString("CREAT_BY"));
						custDetails.setModifiedBy(resultSetCustomerDetails.getString("MOD_BY"));
						if(resultSetCustomerDetails.getInt("IS_ACTIVE")==1){
							custDetails.setActive(true);
						}else{
							custDetails.setActive(false);
						}

					}
				}
			}

		} catch (Exception e) {
			custDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : " + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
			UtilityMethods.closeResource(connection, callableStatement, resultSetCustomerDetails);
		}
		return custDetails;
	}
	  
	
	// ==============================create Enduser	 DAO===========================================
	@Override
	public EndUserDetails createEndUserDetails(EndUserDetails endUserDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuot = null;
		ResultSet resultSetEnduserDetails = null;

		
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_END_USER (?,?,?,?,?,?,?,?,?,?,?,?) }");
			/* set values to procedure inputs */

			callableStatement.setString(1, endUserDetails.getEndUserName());
			callableStatement.setString(2, endUserDetails.getContactNumber());
			callableStatement.setString(3, endUserDetails.getEmailId());
			callableStatement.setString(4, endUserDetails.getAddress());
			callableStatement.setString(5, endUserDetails.getCity());
			callableStatement.setString(6, endUserDetails.getDistrict());
			callableStatement.setString(7, endUserDetails.getState());
			callableStatement.setString(8, endUserDetails.getCountry());
			callableStatement.setString(9, endUserDetails.getPincode());
			callableStatement.setInt(10, endUserDetails.getModifiedById());
			callableStatement.setInt(11, 0);
			callableStatement.setInt(12,1);
			
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				endUserDetails.setSuccessCode(resultOutParameterInt);
				endUserDetails.setSuccessMsg(resultOutParameterString);
				endUserDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetEnduserDetails = callableStatement.getResultSet();
					while (resultSetEnduserDetails.next()) {

						endUserDetails.setEndUserId(resultSetEnduserDetails.getInt("END_USER_ID"));
						endUserDetails.setEndUserName(resultSetEnduserDetails.getString("END_USER_NAME"));
						endUserDetails.setContactNumber(resultSetEnduserDetails.getString("END_USER_CONT_NO"));
						endUserDetails.setEmailId(resultSetEnduserDetails.getString("END_USER_EMAIL"));
						endUserDetails.setAddress(resultSetEnduserDetails.getString("END_USER_ADDRESS"));
						endUserDetails.setCity(resultSetEnduserDetails.getString("END_USER_CITY"));
						endUserDetails.setState(resultSetEnduserDetails.getString("END_USER_STATE"));
						endUserDetails.setCountry(resultSetEnduserDetails.getString("END_USER_COUNTRY"));
						endUserDetails.setPincode(resultSetEnduserDetails.getString("END_USER_PIN_CD"));
						endUserDetails.setCreatedDate(resultSetEnduserDetails.getString("CREAT_DT"));
						endUserDetails.setModifiedDate(resultSetEnduserDetails.getString("MOD_DT"));
						endUserDetails.setCreatedById(resultSetEnduserDetails.getInt("CREAT_BY_ID"));
						endUserDetails.setModifiedById(resultSetEnduserDetails.getInt("MOD_BY_ID"));
						endUserDetails.setCreatedBy(resultSetEnduserDetails.getString("CREAT_BY"));
						endUserDetails.setModifiedBy(resultSetEnduserDetails.getString("MOD_BY"));
						endUserDetails.setSapCode(resultSetEnduserDetails.getString("END_USER_SAP_CODE"));
						if(resultSetEnduserDetails.getInt("IS_ACTIVE")==1){
							endUserDetails.setActive(true);
						}else{
							endUserDetails.setActive(false);
						}
					}
				}
			}

		} catch (Exception e) {
			endUserDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : " + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEnduserDetails);
		}
		return endUserDetails;
	}

	// =============== update EndUser DAO=============================
	@Override
	public EndUserDetails updateEndUserDetails(EndUserDetails endUserDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuot = null;
		ResultSet resultSetEnduserDetails = null;

		
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_END_USER (?,?,?,?,?,?,?,?,?,?,?,?) }");
			/* set values to procedure inputs */

			callableStatement.setString(1, endUserDetails.getEndUserName());
			callableStatement.setString(2, endUserDetails.getContactNumber());
			callableStatement.setString(3, endUserDetails.getEmailId());
			callableStatement.setString(4, endUserDetails.getAddress());
			callableStatement.setString(5, endUserDetails.getCity());
			callableStatement.setString(6, endUserDetails.getDistrict());
			callableStatement.setString(7, endUserDetails.getState());
			callableStatement.setString(8, endUserDetails.getCountry());
			callableStatement.setString(9, endUserDetails.getPincode());
			callableStatement.setInt(10, endUserDetails.getModifiedById());
			callableStatement.setInt(11, endUserDetails.getEndUserId());
			if(endUserDetails.isActive()){
				callableStatement.setInt(12,1);	
			}else{
				callableStatement.setInt(12,0);	
			}
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				endUserDetails.setSuccessCode(resultOutParameterInt);
				endUserDetails.setSuccessMsg(resultOutParameterString);
				endUserDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetEnduserDetails = callableStatement.getResultSet();
					while (resultSetEnduserDetails.next()) {

						endUserDetails.setEndUserId(resultSetEnduserDetails.getInt("END_USER_ID"));
						endUserDetails.setEndUserName(resultSetEnduserDetails.getString("END_USER_NAME"));
						endUserDetails.setContactNumber(resultSetEnduserDetails.getString("END_USER_CONT_NO"));
						endUserDetails.setEmailId(resultSetEnduserDetails.getString("END_USER_EMAIL"));
						endUserDetails.setAddress(resultSetEnduserDetails.getString("END_USER_ADDRESS"));
						endUserDetails.setCity(resultSetEnduserDetails.getString("END_USER_CITY"));
						endUserDetails.setState(resultSetEnduserDetails.getString("END_USER_STATE"));
						endUserDetails.setCountry(resultSetEnduserDetails.getString("END_USER_COUNTRY"));
						endUserDetails.setPincode(resultSetEnduserDetails.getString("END_USER_PIN_CD"));
						endUserDetails.setCreatedDate(resultSetEnduserDetails.getString("CREAT_DT"));
						endUserDetails.setModifiedDate(resultSetEnduserDetails.getString("MOD_DT"));
						endUserDetails.setCreatedById(resultSetEnduserDetails.getInt("CREAT_BY_ID"));
						endUserDetails.setModifiedById(resultSetEnduserDetails.getInt("MOD_BY_ID"));
						endUserDetails.setCreatedBy(resultSetEnduserDetails.getString("CREAT_BY"));
						endUserDetails.setSapCode(resultSetEnduserDetails.getString("END_USER_SAP_CODE"));
						endUserDetails.setModifiedBy(resultSetEnduserDetails.getString("MOD_BY"));
						if(resultSetEnduserDetails.getInt("IS_ACTIVE")==1){
							endUserDetails.setActive(true);
						}else{
							endUserDetails.setActive(false);
						}
					}
				}
			}

		} catch (Exception e) {
			endUserDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : " + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEnduserDetails);
		}
		return endUserDetails;
	}
	
	//===================================================================================
	
	@Override
	public ConsultantDetails createConsultantDetails(ConsultantDetails consultantDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetConsultantDetails = null;
		
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_CONSULTANT (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"); 
			
			/* set values to procedure inputs */
			
			callableStatement.setString(1, consultantDetails.getFirmName());
			callableStatement.setString(2, consultantDetails.getSpocName());
			callableStatement.setString(3, consultantDetails.getContactNumber());
			callableStatement.setString(4, consultantDetails.getEmailId());
			callableStatement.setString(5, consultantDetails.getAddress());
			callableStatement.setString(6, consultantDetails.getCity());
			callableStatement.setString(7, consultantDetails.getDistrict());
			callableStatement.setString(8, consultantDetails.getState());
			callableStatement.setString(9, consultantDetails.getCountry());
			callableStatement.setString(10, consultantDetails.getPincode());
			callableStatement.setInt(11, consultantDetails.getModifiedById());
			callableStatement.setInt(12,1);	
			
			callableStatement.setInt(13, consultantDetails.getFirmId());
			callableStatement.setInt(14, consultantDetails.getSpocId());
			callableStatement.setInt(15,1);	
			
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				consultantDetails.setSuccessCode(resultOutParameterInt);
				consultantDetails.setSuccessMsg(resultOutParameterString);
				consultantDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			
			if (callableStatement.getMoreResults()) {
				resultSetConsultantDetails = callableStatement.getResultSet();
				while (resultSetConsultantDetails.next()) {
					
					consultantDetails.setFirmId(resultSetConsultantDetails.getInt("FIRM_ID"));
					consultantDetails.setFirmName(resultSetConsultantDetails.getString("FIRM_NAME"));
					consultantDetails.setSpocId(resultSetConsultantDetails.getInt("CONST_ID"));
					consultantDetails.setSpocName(resultSetConsultantDetails.getString("CONST_NAME"));
					consultantDetails.setContactNumber(resultSetConsultantDetails.getString("CONST_CONT_NO"));
					consultantDetails.setEmailId(resultSetConsultantDetails.getString("CONST_EMAIL"));
					consultantDetails.setAddress(resultSetConsultantDetails.getString("CONST_ADDRESS"));
					consultantDetails.setCity(resultSetConsultantDetails.getString("CONST_CITY"));
					consultantDetails.setState(resultSetConsultantDetails.getString("CONST_STATE"));
					consultantDetails.setCountry(resultSetConsultantDetails.getString("CONST_COUNTRY"));
					consultantDetails.setPincode(resultSetConsultantDetails.getString("CONST_PIN_CD"));
					consultantDetails.setCreatedDate(resultSetConsultantDetails.getString("CREAT_DT"));
					consultantDetails.setModifiedDate(resultSetConsultantDetails.getString("MOD_DT"));
					consultantDetails.setCreatedById(resultSetConsultantDetails.getInt("CREAT_BY_ID"));
					consultantDetails.setModifiedById(resultSetConsultantDetails.getInt("MOD_BY_ID"));
					consultantDetails.setCreatedBy(resultSetConsultantDetails.getString("CREAT_BY"));
					consultantDetails.setModifiedBy(resultSetConsultantDetails.getString("MOD_BY"));
					if(resultSetConsultantDetails.getInt("IS_ACTIVE")==1){
						consultantDetails.setFirmActive(true);
					}else{
						consultantDetails.setFirmActive(false);
					}
				}
			}

		} catch (Exception e) {
			consultantDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : " + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetConsultantDetails);
		}
		return consultantDetails;
	}
	
	@Override
	public ConsultantDetails updateConsultantDetails(ConsultantDetails consultantDetails) {
		
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuot = null;
		ResultSet resultSetConsultantDetails = null;
		
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_CONSULTANT (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"); 
			
			/* set values to procedure inputs */

			callableStatement.setString(1, consultantDetails.getFirmName());
			callableStatement.setString(2, consultantDetails.getSpocName());
			callableStatement.setString(3, consultantDetails.getContactNumber());
			callableStatement.setString(4, consultantDetails.getEmailId());
			callableStatement.setString(5, consultantDetails.getAddress());
			callableStatement.setString(6, consultantDetails.getCity());
			callableStatement.setString(7, consultantDetails.getDistrict());
			callableStatement.setString(8, consultantDetails.getState());
			callableStatement.setString(9, consultantDetails.getCountry());
			callableStatement.setString(10, consultantDetails.getPincode());
			callableStatement.setInt(11, consultantDetails.getModifiedById());
			
			if(consultantDetails.isFirmActive()){
				callableStatement.setInt(12,1);	
			}else{
				callableStatement.setInt(12,0);	
			}
			
			callableStatement.setInt(13, consultantDetails.getFirmId());
			callableStatement.setInt(14, consultantDetails.getSpocId());
			
			if(consultantDetails.isSpocActive()){
				callableStatement.setInt(15,1);	
			}else{
				callableStatement.setInt(15,0);	
			}

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				consultantDetails.setSuccessCode(resultOutParameterInt);
				consultantDetails.setSuccessMsg(resultOutParameterString);
				consultantDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetConsultantDetails = callableStatement.getResultSet();
				while (resultSetConsultantDetails.next()) {
			
					consultantDetails.setFirmId(resultSetConsultantDetails.getInt("FIRM_ID"));
					consultantDetails.setFirmName(resultSetConsultantDetails.getString("FIRM_NAME"));
					consultantDetails.setSpocId(resultSetConsultantDetails.getInt("CONST_ID"));
					consultantDetails.setSpocName(resultSetConsultantDetails.getString("CONST_NAME"));
					consultantDetails.setContactNumber(resultSetConsultantDetails.getString("CONST_CONT_NO"));
					consultantDetails.setEmailId(resultSetConsultantDetails.getString("CONST_EMAIL"));
					consultantDetails.setAddress(resultSetConsultantDetails.getString("CONST_ADDRESS"));
					consultantDetails.setCity(resultSetConsultantDetails.getString("CONST_CITY"));
					consultantDetails.setState(resultSetConsultantDetails.getString("CONST_STATE"));
					consultantDetails.setCountry(resultSetConsultantDetails.getString("CONST_COUNTRY"));
					consultantDetails.setPincode(resultSetConsultantDetails.getString("CONST_PIN_CD"));
					consultantDetails.setCreatedDate(resultSetConsultantDetails.getString("CREAT_DT"));
					consultantDetails.setModifiedDate(resultSetConsultantDetails.getString("MOD_DT"));
					consultantDetails.setCreatedById(resultSetConsultantDetails.getInt("CREAT_BY_ID"));
					consultantDetails.setModifiedById(resultSetConsultantDetails.getInt("MOD_BY_ID"));
					consultantDetails.setCreatedBy(resultSetConsultantDetails.getString("CREAT_BY"));
					consultantDetails.setModifiedBy(resultSetConsultantDetails.getString("MOD_BY"));
					if(resultSetConsultantDetails.getInt("IS_ACTIVE")==1){
						consultantDetails.setFirmActive(true);
					}else{
						consultantDetails.setFirmActive(false);
					}
				}
			}

		} catch (Exception e) {
			consultantDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : " + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
			UtilityMethods.closeResource(connection, callableStatement, resultSetConsultantDetails);
		}
		return consultantDetails;
	}


	@SuppressWarnings("unchecked")
	@Override
	public CustomerDetails getCustomerData(String oppSeqNo, CustomerDetails customerDetails) {
		
		try {
			String custData = null;
			String error = null;
			int i = 0;
			List<String> outputList = new ArrayList<>();
			List<String> newlist = new ArrayList<>();

			outputList = runTimeExec.runCmd(oppSeqNo);
			if (outputList.get(0).length() > 0 && outputList.get(0).contains(oppSeqNo)) {
				custData = outputList.get(0);

				newlist = Arrays.asList(custData.split("[|]"));
				customerDetails.setSuccessCode(0);
				customerDetails.setSuccessMsg("SUCCESS");
			} else if (outputList.get(1).length() > 0) {
				error = outputList.get(1);
				customerDetails.setSuccessCode(-1);
				customerDetails.setSuccessMsg(error);
				logger.info("Error" + " : " + error);
			} else {
				customerDetails.setSuccessCode(-1);
				customerDetails.setSuccessMsg(outputList.get(0));
				logger.info("Error" + " : " + outputList.get(0) + "Error" + " : " + error);
			}

			logger.info("custData" + " : " + custData);

			
			
			if (!newlist.isEmpty()) {
				customerDetails.setOppurtunitySeqNo(newlist.get(i));
				customerDetails.setOppName(newlist.get(i + 1));
				customerDetails.setIsEndUserAvailable(newlist.get(i + 2));
//				if(newlist.get(i + 2)!="null" || newlist.get(i + 2)!=null){
					customerDetails.setEndUserStateName(newlist.get(i + 3));
				//}
				if(!(newlist.get(i + 4).equalsIgnoreCase("null"))){
					customerDetails.setOppContactName(newlist.get(i + 4));
				}
				if(!(newlist.get(i + 5).equalsIgnoreCase("null"))){
					customerDetails.setOppContactEmail(newlist.get(i + 5));
				}
				if(!(newlist.get(i + 6).equalsIgnoreCase("null"))){
					customerDetails.setOppContactPhone(newlist.get(i + 6));
				}
				if(!(newlist.get(i + 7).equalsIgnoreCase("null"))){
					customerDetails.setOppContactAddress(newlist.get(i + 7));
				}
				
				
				
				if(!(newlist.get(i + 9).equalsIgnoreCase("null"))){
					customerDetails.setCustType(newlist.get(i + 9));
					logger.info("check input");
					logger.info(customerDetails.getCustType());
				}
				
				
				if(!(newlist.get(i + 10).equalsIgnoreCase("null"))){
					customerDetails.setOppContactStateName(newlist.get(i + 10));
				}
				
				if(!(newlist.get(i + 11).equalsIgnoreCase("null"))){
					customerDetails.setEndUserName(newlist.get(i + 11));
				}
				if(!(newlist.get(i + 12).equalsIgnoreCase("null"))){
					customerDetails.setEndUserCustType(newlist.get(i + 12));
				}
				
				if(!(newlist.get(i + 14).equalsIgnoreCase("null"))){
					customerDetails.setCustName(newlist.get(i + 14));
				}
			
				
			}

			return customerDetails;
		} catch (Exception e) {
			customerDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return customerDetails;
		}
			
		
	}


}
