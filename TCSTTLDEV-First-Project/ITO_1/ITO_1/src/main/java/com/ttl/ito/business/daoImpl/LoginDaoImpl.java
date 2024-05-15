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
import com.ttl.ito.business.dao.LoginDao;
import com.ttl.ito.common.Utility.UtilityMethods;
import com.ttl.ito.internal.beans.LoginBO;

@Repository
public class LoginDaoImpl implements LoginDao {

	private Logger logger = Logger.getLogger(LoginDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Override
	public LoginBO loginUserDetailsDao(LoginBO loginBO) {

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetUser = null;
		ResultSet resultSetRoles = null;
		ResultSet resultSetRegions = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_USER(?, ?) }");
			callableStatement.setString(1, loginBO.getEmailId().toLowerCase());
			callableStatement.setString(2, loginBO.getPassword());

			callableStatement.execute();

			loginBO.setSuccessCode(resultOutParameterInt);
			loginBO.setSuccessMsg(resultOutParameterString);

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				loginBO.setSuccessCode(resultSetMsg.getInt(1));
				loginBO.setSuccessMsg(resultSetMsg.getString(2));
			}

			if (loginBO.getSuccessCode() == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetUser = callableStatement.getResultSet();
					while (resultSetUser.next()) {
						loginBO.setUserId(resultSetUser.getInt("USER_ID"));
						loginBO.setEmpId(resultSetUser.getInt("EMP_ID"));
						loginBO.setName(resultSetUser.getString("NAME"));
						loginBO.setEmailId(resultSetUser.getString("EMAIL"));
						loginBO.setDeptId(resultSetUser.getInt("DEPT_ID"));
						loginBO.setDept(resultSetUser.getString("DEPT_NAME"));
						loginBO.setPhoneNumber(resultSetUser.getString("PH_NUM"));
						// loginBO.setLastLoggedInDate(resultSetUser.getDate("LAST_LOGIN_DT"));
						loginBO.setCreatedBy(resultSetUser.getString("CREAT_BY"));
						loginBO.setCreatedDate(resultSetUser.getString("CREAT_DT"));
						loginBO.setModifyBy(resultSetUser.getString("MOD_BY"));
						loginBO.setModifyDate(resultSetUser.getString("MOD_DT"));
						loginBO.setPassword("");
						if (resultSetUser.getString("IS_ACTIVE").equals("1")) {
							loginBO.setActive(true);
						} else {
							loginBO.setActive(false);
						}
						// setting Image
						if (!(resultSetUser.getBytes("IMAGE") == null)) {
							String encoded = Base64.getEncoder().encodeToString(resultSetUser.getBytes("IMAGE")); // convert
																													// array
																													// of
																													// bytes
																													// back
																													// to
							// base 64 format to display in UI

							loginBO.setImage(encoded); // setting encoded base
														// 64
						} // format to LoginBO bean
						loginBO.setDesignation(resultSetUser.getString("DESIGNATION"));
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
						selectBox.setCode(resultSetRegions.getString("REGION_CD"));
						selectBoxRegionsList.add(selectBox);
					}
					if (!selectBoxRegionsList.isEmpty()) {
						loginBO.setUserRegionsList(selectBoxRegionsList);
					}
				}
				// if (callableStatement.getMoreResults()) {
				// resultSet = callableStatement.getResultSet();
				//
				// List<SelectBox> selectBoxRegionsList1 = new
				// ArrayList<SelectBox>();
				// while (resultSetRegions.next()) {
				// SelectBox selectBox = new SelectBox();
				// selectBox.setKey(resultSet.getInt("REGION_ID"));
				// selectBox.setValue(resultSet.getString("REGION"));
				// selectBox.setCode(resultSet.getString("FLAG"));
				// selectBoxRegionsList1.add(selectBox);
				// }
				// if (!selectBoxRegionsList1.isEmpty()) {
				// loginBO.setUserRegionsList(selectBoxRegionsList1);
				// }
				// }

				// fetch users Roles List and set it to bean
				if (callableStatement.getMoreResults()) {
					resultSetRoles = callableStatement.getResultSet();
					List<SelectBox> selectBoxRolesList = new ArrayList<SelectBox>();
					while (resultSetRoles.next()) {
						SelectBox selectBox = new SelectBox();
						selectBox.setKey(resultSetRoles.getInt("ROLES_ID"));
						selectBox.setValue(resultSetRoles.getString("ROLES"));
						selectBox.setCode(resultSetRoles.getString("GROUP_CD"));
						selectBoxRolesList.add(selectBox);
					}
					if (!selectBoxRolesList.isEmpty()) {
						loginBO.setUserRolesList(selectBoxRolesList);
					}
				}

			} else if (loginBO.getSuccessCode() == 1) {
				loginBO.setSuccessCode(1);
				loginBO.setSuccessMsg(loginBO.getSuccessMsg());

			} else if (loginBO.getSuccessCode() == 2) {
				loginBO.setSuccessCode(2);
				loginBO.setSuccessMsg(loginBO.getSuccessMsg());
			}

			logger.info("User logged in successfully...!!" + "-" + loginBO.getName() + "--" + loginBO.getUserId());

		} catch (Exception e) {
			loginBO.setSuccessCode(-1);
			loginBO.setSuccessMsg(TECHNICAL_EXCEPTION);
			loginBO.getMsgToUser().put("-1", TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUser);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRoles);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRegions);
		}
		return loginBO;
	}

	// Added by Kavya -Forgot password Start

	@Override
	public LoginBO forgotPassword(LoginBO loginBO) {

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetUser = null;
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_FORGOT_PASSWORD(?) }");

			callableStatement.setString(1, loginBO.getEmailId().toLowerCase());

			callableStatement.execute();

			loginBO.setSuccessCode(resultOutParameterInt);
			loginBO.setSuccessMsg(resultOutParameterString);

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				loginBO.setSuccessCode(resultSetMsg.getInt(1));
				loginBO.setSuccessMsg(resultSetMsg.getString(2));
			}

			if (loginBO.getSuccessCode() == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetUser = callableStatement.getResultSet();
					while (resultSetUser.next()) {
						loginBO.setUserId(resultSetUser.getInt("USER_ID"));
						loginBO.setEmpId(resultSetUser.getInt("EMP_ID"));
						loginBO.setName(resultSetUser.getString("NAME"));
						loginBO.setPassword(resultSetUser.getString("PASSWORD"));
					}
				}

				logger.info(" Success...!!" + "-" + loginBO.getName() + "--" + loginBO.getUserId());

			} else if (loginBO.getSuccessCode() == 1) {
				loginBO.getMsgToUser().put("1", loginBO.getSuccessMsg());
				logger.info("User Not found...!!" + "--" + loginBO.getSuccessCode());
				logger.info("User Not found...!!" + "--" + loginBO.getSuccessMsg());

			}

		} catch (Exception e) {
			loginBO.setSuccessCode(-1);
			loginBO.setSuccessMsg(TECHNICAL_EXCEPTION);
			loginBO.getMsgToUser().put("-1", TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUser);
		}
		return loginBO;
	}

	// Reset password
	@Override
	public LoginBO resetPassword(LoginBO loginBO) {

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetUser = null;
		ResultSet resultSetRoles = null;
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RESET_PASSWORD(?,?,?) }");

			callableStatement.setInt(1, loginBO.getUserId());
			callableStatement.setString(2, loginBO.getPassword());
			callableStatement.setString(3, loginBO.getNewPassword());

			callableStatement.execute();

			loginBO.setSuccessCode(resultOutParameterInt);
			loginBO.setSuccessMsg(resultOutParameterString);

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				loginBO.setSuccessCode(resultSetMsg.getInt(1));
				loginBO.setSuccessMsg(resultSetMsg.getString(2));
				loginBO.setPassword(null);
			}

			if (loginBO.getSuccessCode() == 0) {
				loginBO.getMsgToUser().put("0", loginBO.getSuccessMsg());
			}

			else if (loginBO.getSuccessCode() == 1) {
				loginBO.getMsgToUser().put("1", loginBO.getSuccessMsg());
				logger.info("User Not found...!!" + "--" + loginBO.getSuccessCode());
				logger.info("User Not found...!!" + "--" + loginBO.getSuccessMsg());
			}

		} catch (Exception e) {
			loginBO.setSuccessCode(-1);
			loginBO.setSuccessMsg(TECHNICAL_EXCEPTION);
			loginBO.getMsgToUser().put("-1", TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUser);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRoles);
		}
		return loginBO;
	}
	// Added by Kavya - End
}
