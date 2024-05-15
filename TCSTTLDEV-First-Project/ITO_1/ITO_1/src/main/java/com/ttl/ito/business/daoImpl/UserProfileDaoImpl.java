/*
 * 
 * Created on : 17-July-2018
 * Author     : Kavya
 *
 *-----------------------------------------------------------------------------
 * VERSION  DATE       AUTHOR      DESCRIPTION OF CHANGE
 * 17-July-2018        Kavya       Created for User Profile Management screen
 *-----------------------------------------------------------------------------
 *
 */

package com.ttl.ito.business.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ttl.ito.business.beans.SelectBox;
import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.business.dao.UserProfileDao;
import com.ttl.ito.common.Utility.UtilityMethods;
import com.ttl.ito.internal.beans.LoginBO;

import sun.misc.BASE64Decoder;

@Repository
public class UserProfileDaoImpl implements UserProfileDao {

	private Logger logger = Logger.getLogger(UserProfileDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;
	
	
	@Override
	public UserProfileDetails createUser(UserProfileDetails userDetails) {

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetUserDet = null;
		Connection connection = null;
		ResultSet resultSetRoles = null;
		ResultSet resultSetRegions = null;
		
		try {
			List<String> regionsList = new ArrayList<String>();
			List<String> rolesList = new ArrayList<String>();
			for (Integer myInt : userDetails.getRegionsList()) {
				regionsList.add(String.valueOf(myInt));
			}
			
			for (Integer myInt : userDetails.getRolesList()) {
				rolesList.add(String.valueOf(myInt));
			}
			
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_CREATE_USER(?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setInt("EMP_ID", userDetails.getEmpId());
			callableStatement.setString("USER_NAME", userDetails.getEmpName());
			if(userDetails.getImage()!=null){
				 BASE64Decoder decoder = new BASE64Decoder();
		         byte[]  imageByte = decoder.decodeBuffer(userDetails.getImage());  // convert the base 64 format image to array of bytes while storing to DB
		         callableStatement.setBytes("PHOTO", imageByte);
			}else{
				 callableStatement.setBinaryStream("PHOTO", null);
			}
			callableStatement.setString("EMAIL", userDetails.getEmailId().toLowerCase());
			if(userDetails.getContactNumber()!=null){
				callableStatement.setString("PH_NUM", userDetails.getContactNumber());
			}else{
				 callableStatement.setBinaryStream("PH_NUM", null);
			}
			if(userDetails.getGroupId()!=null){
				callableStatement.setInt("DEPT_ID", userDetails.getGroupId());
			}else{
				 callableStatement.setBinaryStream("DEPT_ID", null);
			}
			callableStatement.setString("REGION_ID", String.join(",", regionsList));
			callableStatement.setString("ROLES_ID", String.join(",", rolesList));
			callableStatement.setInt("MOD_BY", userDetails.getModifiedById());
			if(userDetails.getDesignation()!=null){
				callableStatement.setString("DESIGNATION", userDetails.getDesignation());
			}else{
				 callableStatement.setBinaryStream("DESIGNATION", null);
			}
			
			callableStatement.execute();
			
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				userDetails.setSuccessCode(resultOutParameterInt);
				userDetails.setSuccessMsg(resultOutParameterString);
				userDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			
			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetUserDet = callableStatement.getResultSet();
					while (resultSetUserDet.next()) {
						userDetails.setUserId(resultSetUserDet.getInt("USER_ID"));
						userDetails.setEmpId(resultSetUserDet.getInt("EMP_ID"));
						userDetails.setEmpName(resultSetUserDet.getString("NAME"));
						userDetails.setContactNumber(resultSetUserDet.getString("PH_NUM"));
						userDetails.setEmailId(resultSetUserDet.getString("EMAIL"));
						userDetails.setGroupId(resultSetUserDet.getInt("DEPT_ID"));
						userDetails.setGroup(resultSetUserDet.getString("DEPT_NAME"));
						// setting Image
						if(!(resultSetUserDet.getBytes("IMAGE")==null)){
						String encoded = Base64.getEncoder().encodeToString(resultSetUserDet.getBytes("IMAGE"));  // convert array of bytes back to base 64 format to display in UI
						userDetails.setImage(encoded);  // setting encoded base 64 format to userdetails bean
						}
						userDetails.setCreatedDate(resultSetUserDet.getString("CREAT_DT"));
						userDetails.setModifiedDate(resultSetUserDet.getString("MOD_DT"));
						userDetails.setCreatedBy(resultSetUserDet.getString("CREAT_BY"));
						userDetails.setModifiedBy(resultSetUserDet.getString("MOD_BY"));
						if(resultSetUserDet.getString("IS_ACTIVE").equals("1")){
							userDetails.setActive(true);
						}else{
							userDetails.setActive(false);
						}
						userDetails.setDesignation(resultSetUserDet.getString("DESIGNATION"));
						logger.info(resultSetUserDet.getString("CREAT_DT"));
						logger.info(resultSetUserDet.getString("MOD_DT"));
						logger.info(resultSetUserDet.getString("CREAT_BY"));
						logger.info(resultSetUserDet.getString("NAME"));
						logger.info(resultSetUserDet.getString("PH_NUM"));
						logger.info(resultSetUserDet.getString("EMAIL"));
						logger.info(resultSetUserDet.getString("DEPT_NAME"));
						
					}
				}
				
				// fetch users RegionList and set it to bean
				if (callableStatement.getMoreResults()) {
					resultSetRegions = callableStatement.getResultSet();
					
					List<SelectBox> selectBoxRegionsList = new ArrayList<SelectBox>();
					while (resultSetRegions.next()) {
						SelectBox selectBox = new SelectBox();
						selectBox.setKey(resultSetRegions.getInt("REGION_ID"));
						selectBox.setValue(resultSetRegions.getString("REGION"));
						selectBoxRegionsList.add(selectBox);
					}
					if (!selectBoxRegionsList.isEmpty()) {
						userDetails.setUserRegionsList(selectBoxRegionsList);
					}
				}
				
				// fetch users Roles List and set it to bean
				if (callableStatement.getMoreResults()) {
					resultSetRoles = callableStatement.getResultSet();
				List<SelectBox> selectBoxRolesList = new ArrayList<SelectBox>();
				while (resultSetRoles.next()) {
					SelectBox selectBox = new SelectBox();
					selectBox.setKey(resultSetRoles.getInt("ROLES_ID"));
					selectBox.setValue(resultSetRoles.getString("ROLES"));
					selectBoxRolesList.add(selectBox);
				}
				if (!selectBoxRolesList.isEmpty()) {
					userDetails.setUserRolesList(selectBoxRolesList);
				}
			}
			
			}
		} catch (Exception e) {
			userDetails.setSuccessCode(-1);
			userDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			userDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUserDet);
		}
		return userDetails;
	}

	@Override
	public UserProfileDetails editUserProfileDetails(UserProfileDetails userDetails) {
	
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetUserDet = null;
		Connection connection = null;
		ResultSet resultSetRoles = null;
		ResultSet resultSetRegions = null;
		ResultSet resultSet = null;
		
		try {
			
			List<String> regionsList = new ArrayList<String>();
			List<String> rolesList = new ArrayList<String>();
			for (Integer myInt : userDetails.getRegionsList()) {
				regionsList.add(String.valueOf(myInt));
			}
			
			for (Integer myInt : userDetails.getRolesList()) {
				rolesList.add(String.valueOf(myInt));
			}
			
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_EDIT_USER(?,?,?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setInt("USER_ID", userDetails.getUserId());
			callableStatement.setInt("EMP_ID", userDetails.getEmpId());
			callableStatement.setString("USER_NAME", userDetails.getEmpName());
			if(userDetails.getImage()!=null){
				 BASE64Decoder decoder = new BASE64Decoder();
		         byte[]  imageByte = decoder.decodeBuffer(userDetails.getImage());  // convert the base 64 format image to array of bytes while storing to DB
		    callableStatement.setBytes("PHOTO", imageByte);
			}else{
				callableStatement.setBinaryStream("PHOTO", null);
			}
			callableStatement.setString("EMAIL", userDetails.getEmailId().toLowerCase());
			
			if(userDetails.getContactNumber()!=null){
				callableStatement.setString("PH_NUM", userDetails.getContactNumber());
			}
			else{
				callableStatement.setBinaryStream("PH_NUM", null);
			}
			
			if(userDetails.getGroupId()!=null){
				callableStatement.setInt("DEPT_ID", userDetails.getGroupId());
			}
			else{
				callableStatement.setBinaryStream("DEPT_ID", null);
			}
			
			callableStatement.setString("REGION_ID", String.join(",", regionsList));
			callableStatement.setString("ROLES_ID", String.join(",", rolesList));
			callableStatement.setInt("MOD_BY", userDetails.getModifiedById());
			if(userDetails.isActive()){
				callableStatement.setInt("IS_ACTIVE", 1);
			}else{
				callableStatement.setInt("IS_ACTIVE", 0);
			}
			
			if(userDetails.getDesignation()!=null){
				callableStatement.setString("DESIGNATION", userDetails.getDesignation());
			}
			else{
				callableStatement.setBinaryStream("DESIGNATION", null);
			}
			
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				userDetails.setSuccessCode(resultOutParameterInt);
				userDetails.setSuccessMsg(resultOutParameterString);
				userDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetUserDet = callableStatement.getResultSet();
					while (resultSetUserDet.next()) {
						userDetails.setUserId(resultSetUserDet.getInt("USER_ID"));
						userDetails.setEmpId(resultSetUserDet.getInt("EMP_ID"));
						userDetails.setEmpName(resultSetUserDet.getString("NAME"));
						userDetails.setContactNumber(resultSetUserDet.getString("PH_NUM"));
						userDetails.setEmailId(resultSetUserDet.getString("EMAIL"));
						userDetails.setGroupId(resultSetUserDet.getInt("DEPT_ID"));
						userDetails.setGroup(resultSetUserDet.getString("DEPT_NAME"));
						// setting Image
						if (!(resultSetUserDet.getBytes("IMAGE") == null)) {
							String encoded = Base64.getEncoder().encodeToString(resultSetUserDet.getBytes("IMAGE"));
							userDetails.setImage(encoded);
						}	
						userDetails.setCreatedDate(resultSetUserDet.getString("CREAT_DT"));
						userDetails.setModifiedDate(resultSetUserDet.getString("MOD_DT"));
						userDetails.setCreatedBy(resultSetUserDet.getString("CREAT_BY"));
						userDetails.setModifiedBy(resultSetUserDet.getString("MOD_BY"));
						if (resultSetUserDet.getString("IS_ACTIVE").equals("1")) {
							userDetails.setActive(true);
						} else {
							userDetails.setActive(false);
						}
						userDetails.setDesignation(resultSetUserDet.getString("DESIGNATION"));
	
					}
				}
				// fetch users RegionList and set it to bean
				if (callableStatement.getMoreResults()) {
					resultSetRegions = callableStatement.getResultSet();

					List<SelectBox> selectBoxRegionsList = new ArrayList<SelectBox>();
					while (resultSetRegions.next()) {
						SelectBox selectBox = new SelectBox();
						selectBox.setKey(resultSetRegions.getInt("REGION_ID"));
						selectBox.setValue(resultSetRegions.getString("REGION"));
						selectBoxRegionsList.add(selectBox);
					}
					if (!selectBoxRegionsList.isEmpty()) {
						userDetails.setUserRegionsList(selectBoxRegionsList);
					}
				} 
				
//				if (callableStatement.getMoreResults()) {
//					resultSet = callableStatement.getResultSet();
//
//					List<SelectBox> selectBoxRegionsList1 = new ArrayList<SelectBox>();
//					while (resultSetRegions.next()) {
//						SelectBox selectBox = new SelectBox();
//						selectBox.setKey(resultSet.getInt("REGION_ID"));
//						selectBox.setValue(resultSet.getString("REGION"));
//						selectBox.setCode(resultSet.getString("FLAG"));
//						selectBoxRegionsList1.add(selectBox);
//					}
//					if (!selectBoxRegionsList1.isEmpty()) {
//						userDetails.setUserRegionsList(selectBoxRegionsList1);
//					}
//				} 

				// fetch users Roles List and set it to bean
				if (callableStatement.getMoreResults()) {
					resultSetRoles = callableStatement.getResultSet();
					List<SelectBox> selectBoxRolesList = new ArrayList<SelectBox>();
					while (resultSetRoles.next()) {
						SelectBox selectBox = new SelectBox();
						selectBox.setKey(resultSetRoles.getInt("ROLES_ID"));
						selectBox.setValue(resultSetRoles.getString("ROLES"));
						selectBoxRolesList.add(selectBox);
					}
					if (!selectBoxRolesList.isEmpty()) {
						userDetails.setUserRolesList(selectBoxRolesList);
					}
				}
			}
	
		} catch (Exception e) {
			userDetails.setSuccessCode(-1);
			userDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			userDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUserDet);
		}
		return userDetails;
	}


}
