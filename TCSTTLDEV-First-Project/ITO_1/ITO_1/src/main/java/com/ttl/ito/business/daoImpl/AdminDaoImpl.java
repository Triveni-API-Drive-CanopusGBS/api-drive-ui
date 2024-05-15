/**
 * 
 * Created by Kavya
 * Class Name: AdminDaoImpl 
 * This class is used to handle admin screens related requests
 *  
 * 
 */
package com.ttl.ito.business.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.CProjectList;
import com.ttl.ito.business.beans.CommentBean;
import com.ttl.ito.business.beans.CurrencyBean;
import com.ttl.ito.business.beans.DBOBean;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.F2FUBOBean;
import com.ttl.ito.business.beans.LocationBean;
import com.ttl.ito.business.beans.QuestionsBean;
import com.ttl.ito.business.beans.QuestionsEntity;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.SaveQuesDetails;
import com.ttl.ito.business.beans.SelectBox;
import com.ttl.ito.business.beans.TransportationDetailsBean;
import com.ttl.ito.business.beans.TurbineAnswers;
import com.ttl.ito.business.beans.TurbineDetails;
import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.business.dao.AdminDao;
import com.ttl.ito.common.Utility.UtilityMethods;
import com.ttl.ito.internal.beans.ItoConstants;

import sun.misc.BASE64Decoder;

@Repository
public class AdminDaoImpl implements AdminDao {

	private Logger logger = Logger.getLogger(AdminDaoImpl.class);

	public static int size;
	public static int size1;
	public static int size2;
	public static int size3;
	public static int size4;
	public static int size5;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @param adminForm
	 * @return form F2F object for Database purpose reusing this method multiple
	 *         times
	 */
	public SQLServerDataTable getF2FQuesTable(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable F2F_QUST_ANS = new SQLServerDataTable();
			F2F_QUST_ANS.addColumnMetadata("FRM_POW_ID", java.sql.Types.NUMERIC);
			F2F_QUST_ANS.addColumnMetadata("QUST_ID", java.sql.Types.NUMERIC);
			F2F_QUST_ANS.addColumnMetadata("QUST_NM", java.sql.Types.VARCHAR);
			F2F_QUST_ANS.addColumnMetadata("QUST_CD", java.sql.Types.VARCHAR);
			F2F_QUST_ANS.addColumnMetadata("ANS_ID", java.sql.Types.NUMERIC);
			F2F_QUST_ANS.addColumnMetadata("ANS_NM", java.sql.Types.VARCHAR);
			F2F_QUST_ANS.addColumnMetadata("ANS_CD", java.sql.Types.VARCHAR);
			F2F_QUST_ANS.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			F2F_QUST_ANS.addColumnMetadata("IS_ACTIVE_QUST", java.sql.Types.NUMERIC); // make
																						// active
																						// always
			F2F_QUST_ANS.addColumnMetadata("IS_ACTIVE_ANS", java.sql.Types.NUMERIC); // make
																						// active
																						// always

			if (adminForm.getAnswersList().size() > 0) {
				for (TurbineAnswers ansList : adminForm.getAnswersList()) {
					if (ansList.getFramePowerList().size() > 0) {
						for (Integer frameId : ansList.getFramePowerList()) {

							F2F_QUST_ANS.addRow(frameId, ansList.getQuesKey(), ansList.getQuesDesc(),
									ansList.getQuesCode(), ansList.getAnsKey(), ansList.getAnsDesc(),
									ansList.getAnsCode(), (ansList.isDefaultFlag() ? 1 : 0), 1, 1);
						}
					} else {
						F2F_QUST_ANS.addRow(0, ansList.getQuesKey(), ansList.getQuesDesc(), ansList.getQuesCode(),
								ansList.getAnsKey(), ansList.getAnsDesc(), ansList.getAnsCode(),
								(ansList.isDefaultFlag() ? 1 : 0), 1, 1);
					}

				}

			}

			return F2F_QUST_ANS;
		} catch (Exception e) {
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}

	}

	/**
	 * @param adminForm
	 * @return AdminForm with successCode and successMessage this method is used
	 *         to edit or add new department
	 */
	@Override
	public AdminForm addOrEditDepartment(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			// creating object for sending multiple data to database
			SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
			QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getUserProfileDetailsList()) {

				QUOT_ADMIN.addRow(bean.getGroupId(), // null for new department
														// and ID for editing
														// department
						bean.getGroup(), // department
						bean.getGroupCd(), // departmentCode
						null, null, null, (bean.isActive() ? 1 : 0)); // making
																		// active
																		// or
																		// inactive
																		// a
																		// department

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_DEPARTMENT (?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminBgmCalc(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_BGM_CALC = new SQLServerDataTable();
			TYP_BGM_CALC.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			TYP_BGM_CALC.addColumnMetadata("FRM_POW_ID", java.sql.Types.NUMERIC);
			TYP_BGM_CALC.addColumnMetadata("BGM_TYPE", java.sql.Types.VARCHAR);
			TYP_BGM_CALC.addColumnMetadata("BGM_RATING", java.sql.Types.REAL);
			TYP_BGM_CALC.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_BGM_CALC.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminBgmCalcList()) {

				TYP_BGM_CALC.addRow(bean.getId(), bean.getFramePowerId(), bean.getBgmType(), bean.getBgmRating(),
						bean.getDefaultFlagNew(),

						bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_BGM_CALC (?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_BGM_CALC ", TYP_BGM_CALC);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPerfAux(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable PERF_AUX = new SQLServerDataTable();
			PERF_AUX.addColumnMetadata("AUX_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("FRM_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("CONSUMER_ID", java.sql.Types.VARCHAR);
			PERF_AUX.addColumnMetadata("STARTUP", java.sql.Types.VARCHAR);
			PERF_AUX.addColumnMetadata("CONTINUOUS", java.sql.Types.VARCHAR);
			PERF_AUX.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PERF_AUX.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminPerfAuxList()) {

				PERF_AUX.addRow(bean.getAuxId(), bean.getItemId(), bean.getSubItemId(), bean.getCondTypeId(),
						bean.getFrmId(), bean.getCustId(), bean.getStartup(), bean.getContinuous(), bean.getColValCd(),
						bean.getEditFlag(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERF_AUX (?,?)}");
			callableStatement.setStructured(1, "dbo.PERF_AUX ", PERF_AUX);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPerfOther(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable PERF_AUX = new SQLServerDataTable();
			PERF_AUX.addColumnMetadata("AUX_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("FRM_ID", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("CONSUMER_ID", java.sql.Types.VARCHAR);
			PERF_AUX.addColumnMetadata("STARTUP", java.sql.Types.VARCHAR);
			PERF_AUX.addColumnMetadata("CONTINUOUS", java.sql.Types.VARCHAR);
			PERF_AUX.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PERF_AUX.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			PERF_AUX.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminPerfOtherList()) {

				PERF_AUX.addRow(bean.getAuxId(), bean.getItemId(), bean.getSubItemId(), bean.getCondTypeId(), null,
						bean.getCustId(), null, bean.getContinuous(), bean.getColValCd(), bean.getEditFlag(),
						bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERF_OTHER (?,?)}");
			callableStatement.setStructured(1, "dbo.PERF_AUX ", PERF_AUX);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPerfAcDcMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable PERF_AC_DC_MAST = new SQLServerDataTable();
			PERF_AC_DC_MAST.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			PERF_AC_DC_MAST.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PERF_AC_DC_MAST.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PERF_AC_DC_MAST.addColumnMetadata("CATEGORY", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			PERF_AC_DC_MAST.addColumnMetadata("ITEM_TYPE", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("ITEM_CD", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("SPEED", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("VOLTAGE", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("FEEDER", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("STARTUP", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("CONTINUOUS", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			PERF_AC_DC_MAST.addColumnMetadata("PANEL_TYPE", java.sql.Types.VARCHAR);
			PERF_AC_DC_MAST.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminPerfAcDcMastList()) {

				PERF_AC_DC_MAST.addRow(bean.getId(), bean.getItemId(), bean.getSubItemId(), bean.getCategory(),
						bean.getCondTypeId(), bean.getItemType(), bean.getItemCd(), bean.getSpeed(), bean.getVoltage(),
						bean.getFeeder(), bean.getStartup(), bean.getContinuous(), bean.getColValCd(),
						bean.getEditFlag(), bean.getPanelType(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERF_AC_DC_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.PERF_AC_DC_MAST ", PERF_AC_DC_MAST);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPerfAcDcFrmInput(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable PERF_AC_DC_FRM_INPUT = new SQLServerDataTable();
			PERF_AC_DC_FRM_INPUT.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			PERF_AC_DC_FRM_INPUT.addColumnMetadata("FRM_ID", java.sql.Types.NUMERIC);
			PERF_AC_DC_FRM_INPUT.addColumnMetadata("ITEM_CD", java.sql.Types.VARCHAR);
			PERF_AC_DC_FRM_INPUT.addColumnMetadata("MIN_POWER", java.sql.Types.REAL);
			PERF_AC_DC_FRM_INPUT.addColumnMetadata("MAX_POWER", java.sql.Types.REAL);
			PERF_AC_DC_FRM_INPUT.addColumnMetadata("CONTINUOUS", java.sql.Types.VARCHAR);
			PERF_AC_DC_FRM_INPUT.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminPerfAcDcFrmInputList()) {

				PERF_AC_DC_FRM_INPUT.addRow(bean.getId(), bean.getFrmId(), bean.getItemCd(), bean.getMinPower(),
						bean.getMaxPower(), bean.getContinuous(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERF_AC_DC_FRM_INPUT (?,?)}");
			callableStatement.setStructured(1, "dbo.PERF_AC_DC_FRM_INPUT ", PERF_AC_DC_FRM_INPUT);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminSparesMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable SPARES_MAST_OBJ = new SQLServerDataTable();
			SPARES_MAST_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			SPARES_MAST_OBJ.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			SPARES_MAST_OBJ.addColumnMetadata("FRM_ID", java.sql.Types.NUMERIC);
			SPARES_MAST_OBJ.addColumnMetadata("CUST_TYPE", java.sql.Types.VARCHAR);
			SPARES_MAST_OBJ.addColumnMetadata("DESCRIPTION", java.sql.Types.VARCHAR);
			SPARES_MAST_OBJ.addColumnMetadata("QTY", java.sql.Types.VARCHAR);
			SPARES_MAST_OBJ.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			SPARES_MAST_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getSparesMastList()) {

				SPARES_MAST_OBJ.addRow(bean.getId(), bean.getItemId(), bean.getFrmId(), bean.getCustType(),
						bean.getDescription(), bean.getQty(), bean.getEditFlag(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_SPARES_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.SPARES_MAST_OBJ ", SPARES_MAST_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminSubSupplierMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable SUB_SUPPLIER_MAST_OBJ = new SQLServerDataTable();
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("SUB_SCOPE_CD", java.sql.Types.VARCHAR);
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("PANEL_TYPE", java.sql.Types.VARCHAR);
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("EQUIPMENT", java.sql.Types.VARCHAR);
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("EQUIVALENT", java.sql.Types.VARCHAR);
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			SUB_SUPPLIER_MAST_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getSubSupplierMastList()) {

				SUB_SUPPLIER_MAST_OBJ.addRow(bean.getId(), bean.getItemId(), bean.getSubScopeCd(), bean.getCondTypeId(),
						bean.getPanelType(), bean.getEquipment(), bean.getEquivalent(), bean.getEditFlag(),
						bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_SUB_SUPPLIER_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.SUB_SUPPLIER_MAST_OBJ ", SUB_SUPPLIER_MAST_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminQualityMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUALITY_MAST_OBJ = new SQLServerDataTable();
			QUALITY_MAST_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUALITY_MAST_OBJ.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			QUALITY_MAST_OBJ.addColumnMetadata("SUB_SCOPE_CD", java.sql.Types.VARCHAR);
			QUALITY_MAST_OBJ.addColumnMetadata("PANEL_TYPE", java.sql.Types.VARCHAR);
			QUALITY_MAST_OBJ.addColumnMetadata("EQUIPMENT", java.sql.Types.VARCHAR);
			QUALITY_MAST_OBJ.addColumnMetadata("TEST", java.sql.Types.VARCHAR);
			QUALITY_MAST_OBJ.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			QUALITY_MAST_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminQualityMastList()) {

				QUALITY_MAST_OBJ.addRow(bean.getId(), bean.getItemId(), bean.getSubScopeCd(), bean.getPanelType(),
						bean.getEquipment(), bean.getTest(), bean.getEditFlag(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_QUALITY_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.QUALITY_MAST_OBJ ", QUALITY_MAST_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminTerminalMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TERMINAL_MAST_OBJ = new SQLServerDataTable();
			TERMINAL_MAST_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			TERMINAL_MAST_OBJ.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TERMINAL_MAST_OBJ.addColumnMetadata("SUB_SCOPE_CD", java.sql.Types.VARCHAR);
			TERMINAL_MAST_OBJ.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			TERMINAL_MAST_OBJ.addColumnMetadata("PANEL_TYPE", java.sql.Types.VARCHAR);
			TERMINAL_MAST_OBJ.addColumnMetadata("BLEED_TYP_ID", java.sql.Types.NUMERIC);
			TERMINAL_MAST_OBJ.addColumnMetadata("VARIANT_TYP", java.sql.Types.NUMERIC);
			TERMINAL_MAST_OBJ.addColumnMetadata("DESCRIPTION", java.sql.Types.VARCHAR);
			TERMINAL_MAST_OBJ.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);

			TERMINAL_MAST_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminTerminalMastList()) {

				TERMINAL_MAST_OBJ.addRow(bean.getId(), bean.getItemId(), bean.getSubScopeCd(), bean.getCondTypeId(),
						bean.getPanelType(), bean.getBleedTypeId(), bean.getVariantType(), bean.getDescription(),
						bean.getEditFlag(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_TERMINAL_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.TERMINAL_MAST_OBJ ", TERMINAL_MAST_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminExclusionMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable EXCLUSION_MAST_OBJ = new SQLServerDataTable();
			EXCLUSION_MAST_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			EXCLUSION_MAST_OBJ.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			EXCLUSION_MAST_OBJ.addColumnMetadata("SUB_SCOPE_CD", java.sql.Types.VARCHAR);
			EXCLUSION_MAST_OBJ.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			EXCLUSION_MAST_OBJ.addColumnMetadata("PANEL_TYPE", java.sql.Types.VARCHAR);
			EXCLUSION_MAST_OBJ.addColumnMetadata("VARIANT_TYP", java.sql.Types.NUMERIC);
			EXCLUSION_MAST_OBJ.addColumnMetadata("DESCRIPTION", java.sql.Types.VARCHAR);
			EXCLUSION_MAST_OBJ.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			EXCLUSION_MAST_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminExclusionMastList()) {

				EXCLUSION_MAST_OBJ.addRow(bean.getId(), bean.getItemId(), bean.getSubScopeCd(), bean.getCondTypeId(),
						bean.getPanelType(), bean.getVariantType(), bean.getDescription(), bean.getEditFlag(),
						bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_EXCLUSION_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.EXCLUSION_MAST_OBJ ", EXCLUSION_MAST_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminTenderDrawingMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TENDER_DRAWING_MAST_OBJ = new SQLServerDataTable();
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("SUB_SCOPE_CD", java.sql.Types.VARCHAR);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("PANEL_TYPE", java.sql.Types.VARCHAR);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("DESCRIPTION", java.sql.Types.VARCHAR);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("INFORMATION", java.sql.Types.VARCHAR);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("FINAL", java.sql.Types.VARCHAR);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			TENDER_DRAWING_MAST_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminTenderDrawingMastList()) {

				TENDER_DRAWING_MAST_OBJ.addRow(bean.getId(), bean.getSubScopeCd(), bean.getItemId(),
						bean.getCondTypeId(), bean.getPanelType(), bean.getDescription(), bean.getInformation(),
						bean.getFinal(), bean.getEditFlag(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_TENDER_DRAWING_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.TENDER_DRAWING_MAST_OBJ ", TENDER_DRAWING_MAST_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminServiceMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable SERVICE_MAST_OBJ = new SQLServerDataTable();
			SERVICE_MAST_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			SERVICE_MAST_OBJ.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			SERVICE_MAST_OBJ.addColumnMetadata("SUB_SCOPE_CD", java.sql.Types.VARCHAR);
			SERVICE_MAST_OBJ.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			SERVICE_MAST_OBJ.addColumnMetadata("MIN_POWER", java.sql.Types.REAL);
			SERVICE_MAST_OBJ.addColumnMetadata("MAX_POWER", java.sql.Types.REAL);
			SERVICE_MAST_OBJ.addColumnMetadata("NO_OF_MANDAYS", java.sql.Types.NUMERIC);
			SERVICE_MAST_OBJ.addColumnMetadata("SERVICE_REMARKS", java.sql.Types.VARCHAR);
			SERVICE_MAST_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminServiceMastList()) {

				SERVICE_MAST_OBJ.addRow(bean.getId(), bean.getSsId(), bean.getSubScopeCd(), bean.getTurbineCode(),
						bean.getMinPower(), bean.getMaxPower(), bean.getNoOfMandays(), bean.getServiceRemarks(),
						bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_SERVICE_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.SERVICE_MAST_OBJ ", SERVICE_MAST_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminAcwr(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable ACWR_OBJ = new SQLServerDataTable();
			ACWR_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			ACWR_OBJ.addColumnMetadata("ITEM_CD", java.sql.Types.VARCHAR);
			ACWR_OBJ.addColumnMetadata("COOLING_METHOD", java.sql.Types.VARCHAR);
			ACWR_OBJ.addColumnMetadata("TEMP_RAISE", java.sql.Types.VARCHAR);
			ACWR_OBJ.addColumnMetadata("MIN_POWER", java.sql.Types.REAL);
			ACWR_OBJ.addColumnMetadata("MAX_POWER", java.sql.Types.REAL);
			ACWR_OBJ.addColumnMetadata("TURB_OIL_COOLER", java.sql.Types.VARCHAR);
			ACWR_OBJ.addColumnMetadata("GENERATOR_COOLER", java.sql.Types.VARCHAR);
			ACWR_OBJ.addColumnMetadata("GLAND_VEND_COND", java.sql.Types.VARCHAR);
			ACWR_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminAcwrList()) {

				ACWR_OBJ.addRow(bean.getId(), bean.getItemCd(), bean.getCoolingMethod(), bean.getTempRaise(),
						bean.getMinPower(), bean.getMaxPower(), bean.getTurbOilCooler(), bean.getGeneratorCooler(),
						bean.getGlandVendCond(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_ACWR (?,?)}");
			callableStatement.setStructured(1, "dbo.ACWR_OBJ ", ACWR_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminComrMonths(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable COMR_MONTHS_OBJ = new SQLServerDataTable();
			COMR_MONTHS_OBJ.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			COMR_MONTHS_OBJ.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			COMR_MONTHS_OBJ.addColumnMetadata("TURB_DESN", java.sql.Types.VARCHAR);
			COMR_MONTHS_OBJ.addColumnMetadata("MIN_POWER", java.sql.Types.REAL);
			COMR_MONTHS_OBJ.addColumnMetadata("MAX_POWER", java.sql.Types.REAL);
			COMR_MONTHS_OBJ.addColumnMetadata("NO_OF_MONTHS", java.sql.Types.NUMERIC);
			COMR_MONTHS_OBJ.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminComrMonthsList()) {

				COMR_MONTHS_OBJ.addRow(bean.getId(), bean.getTurbineCode(), bean.getTurbDesign(), bean.getMinPower(),
						bean.getMaxPower(), bean.getNoOfMonths(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_COMR_MONTHS (?,?)}");
			callableStatement.setStructured(1, "dbo.COMR_MONTHS_OBJ ", COMR_MONTHS_OBJ);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminEleItems(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_ELE_ITEMS = new SQLServerDataTable();
			TYP_ELE_ITEMS.addColumnMetadata("ELE_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ELE_ITEMS.addColumnMetadata("TYPE_OF_PANEL", java.sql.Types.VARCHAR);
			TYP_ELE_ITEMS.addColumnMetadata("ELE_TYPE", java.sql.Types.VARCHAR);
			TYP_ELE_ITEMS.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ELE_ITEMS.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ELE_ITEMS.addColumnMetadata("HEADER_TEXT", java.sql.Types.VARCHAR);
			TYP_ELE_ITEMS.addColumnMetadata("FOOTER_TEXT", java.sql.Types.VARCHAR);
			TYP_ELE_ITEMS.addColumnMetadata("HEADER_NM", java.sql.Types.VARCHAR);
			TYP_ELE_ITEMS.addColumnMetadata("EXCLUSION_NM", java.sql.Types.VARCHAR);
			TYP_ELE_ITEMS.addColumnMetadata("DT_FRM_FLG", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminEleItemList()) {

				TYP_ELE_ITEMS.addRow(bean.getEleItemId(), bean.getTypeOfPanel(), bean.getEleType(), bean.getItemId(),
						bean.getSubItemId(), bean.getHeaderText(), bean.getFooterText(), bean.getHeaderNm(),
						bean.getExclusionNm(), bean.getDtFrameFlag()

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_ELE_ITEMS (?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_ELE_ITEMS ", TYP_ELE_ITEMS);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPercentTrnsEx(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERCNT_TRNS_EX (?,?)}");
			callableStatement.setFloat(1, adminForm.getPercent());
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPercentTrnsDm(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERCNT_TRNS_DM (?,?)}");
			callableStatement.setFloat(1, adminForm.getPercent());
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPercentEc(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERCNT_EC (?,?)}");
			callableStatement.setFloat(1, adminForm.getPercent());
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPercentPkg(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERCNT_PKG (?,?)}");
			callableStatement.setFloat(1, adminForm.getPercent());
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPercentEle(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERCNT_ELE (?,?,?,?,?,?,?)}");
			callableStatement.setFloat(1, adminForm.getPercent());
			callableStatement.setInt(2, adminForm.getItemId());
			callableStatement.setInt(3, adminForm.getSubItemId());
			callableStatement.setString(4, adminForm.getTypeOfPanel());
			callableStatement.setString(5, adminForm.getMake());
			callableStatement.setString(6, adminForm.getTableName());
			callableStatement.setInt(7, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPercentMech(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERCNT_MECH (?,?,?)}");
			callableStatement.setFloat(1, adminForm.getPercent());
			callableStatement.setString(2, adminForm.getTableName());
			callableStatement.setInt(3, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminPercentF2F(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PERCNT_F2F (?,?,?,?)}");
			callableStatement.setFloat(1, adminForm.getPercent());
			callableStatement.setFloat(2, adminForm.getSubContrPercent());
			callableStatement.setFloat(3, adminForm.getShopConvPercent());
			callableStatement.setInt(4, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminDcmPowerCalc(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_DCM_POWER_CALC = new SQLServerDataTable();
			TYP_DCM_POWER_CALC.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			TYP_DCM_POWER_CALC.addColumnMetadata("FRM_POW_ID", java.sql.Types.NUMERIC);
			TYP_DCM_POWER_CALC.addColumnMetadata("EOP_MOTOR_RATING", java.sql.Types.REAL);
			TYP_DCM_POWER_CALC.addColumnMetadata("COST", java.sql.Types.REAL);
			TYP_DCM_POWER_CALC.addColumnMetadata("PUMP_TYPE", java.sql.Types.VARCHAR);
			TYP_DCM_POWER_CALC.addColumnMetadata("BG_HR_RATE", java.sql.Types.NUMERIC);
			TYP_DCM_POWER_CALC.addColumnMetadata("DC_OUTPUT_VOL", java.sql.Types.REAL);
			TYP_DCM_POWER_CALC.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_DCM_POWER_CALC.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminDcmPowerCalcList()) {

				TYP_DCM_POWER_CALC.addRow(bean.getId(), bean.getFramePowerId(), bean.getEopMotorRating(),
						bean.getCost(), bean.getPumpType(), bean.getBgHrRate(), bean.getDcOutputVol(),
						bean.getDefaultFlagNew(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_DCM_POWER_CALC (?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_DCM_POWER_CALC ", TYP_DCM_POWER_CALC);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminItemMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_ITEM_MAST = new SQLServerDataTable();
			TYP_ITEM_MAST.addColumnMetadata("UN_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ITEM_MAST.addColumnMetadata("SCOP_CD", java.sql.Types.VARCHAR);
			TYP_ITEM_MAST.addColumnMetadata("UN_ITEM_CD", java.sql.Types.VARCHAR);
			TYP_ITEM_MAST.addColumnMetadata("UN_ITEM_NM", java.sql.Types.VARCHAR);

			TYP_ITEM_MAST.addColumnMetadata("EXCLUSION_NM", java.sql.Types.VARCHAR);
			TYP_ITEM_MAST.addColumnMetadata("HEADER_TEXT", java.sql.Types.VARCHAR);
			TYP_ITEM_MAST.addColumnMetadata("FOOTER_TEXT", java.sql.Types.VARCHAR);
			TYP_ITEM_MAST.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getAdminItemMastList()) {

				TYP_ITEM_MAST.addRow(bean.getUnItemId(), bean.getScopeCd(), bean.getUnItemCd(), bean.getUnItemNm(),
						bean.getExclusionNm(), bean.getHeaderText(), bean.getFooterText(), bean.getActiveNew());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_ITEM_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_ITEM_MAST ", TYP_ITEM_MAST);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAdminOthersMast(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_OTHERS_MAST = new SQLServerDataTable();
			TYP_OTHERS_MAST.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_OTHERS_MAST.addColumnMetadata("ITEM_CD", java.sql.Types.VARCHAR);
			TYP_OTHERS_MAST.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			TYP_OTHERS_MAST.addColumnMetadata("GROUP_CD", java.sql.Types.VARCHAR);
			TYP_OTHERS_MAST.addColumnMetadata("NO_OF_MONTHS", java.sql.Types.NUMERIC);
			TYP_OTHERS_MAST.addColumnMetadata("REGION_ID", java.sql.Types.NUMERIC);
			TYP_OTHERS_MAST.addColumnMetadata("CUST_TYPE", java.sql.Types.VARCHAR);
			TYP_OTHERS_MAST.addColumnMetadata("PERCNT", java.sql.Types.REAL);
			TYP_OTHERS_MAST.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);
			for (UserProfileDetails bean : adminForm.getAdminOthersMastList()) {

				TYP_OTHERS_MAST.addRow(bean.getItemId(), bean.getItemCd(), bean.getItemNm(), bean.getGroupCd(),
						bean.getNoOfMonths(), bean.getRegionId(), bean.getCustType(),

						bean.getPercent(), bean.getActiveNew()

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_OTHERS_MAST (?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_OTHERS_MAST ", TYP_OTHERS_MAST);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public QuotationForm addOrEditVariantCode(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
			QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (CProjectList bean : quotationForm.getCProjectWithVariantCodeList()) {
				// if (bean.getId() == 0) {
				// bean.setActive(true);
				// }

				QUOT_ADMIN.addRow(bean.getId(), bean.getVariantCode(), bean.getcNum(), null, bean.getFrameId(),
						bean.getCustId(), bean.getVariantActive());
				logger.info(bean.getVariantActive());
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_F2F_TURB_VARIANT (?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return quotationForm;
	}

	@Override
	public AdminForm getUSDValue(AdminForm adminForm) {

		CallableStatement callableStatement = null;

		ResultSet resultSetCurrency = null;
		ResultSet resultSetCurrency1 = null;
		ResultSet resultSetCurrency2 = null;

		List<CurrencyBean> userList = new ArrayList<CurrencyBean>();
		// UserList

		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_EC_CACHE() }");
			callableStatement.execute();

			resultSetCurrency2 = callableStatement.getResultSet();

			if (callableStatement.getMoreResults()) {
				resultSetCurrency1 = callableStatement.getResultSet();
			}
			if (callableStatement.getMoreResults()) {
				resultSetCurrency1 = callableStatement.getResultSet();
			}
			if (callableStatement.getMoreResults()) {
				resultSetCurrency1 = callableStatement.getResultSet();
			}
			if (callableStatement.getMoreResults()) {
				resultSetCurrency = callableStatement.getResultSet();
				while (resultSetCurrency.next()) {
					CurrencyBean currencyBean = new CurrencyBean();
					currencyBean.setCUR_ID(resultSetCurrency.getInt("CUR_ID"));
					currencyBean.setCUR_NM(resultSetCurrency.getString("CUR_NM"));
					currencyBean.setCUR_CD(resultSetCurrency.getString("CUR_CD"));
					currencyBean.setRUPEE(resultSetCurrency.getString("RUPEE"));
					currencyBean.setCREAT_DT(resultSetCurrency.getDate("CREAT_DT"));
					currencyBean.setMOD_DT(resultSetCurrency.getDate("MOD_DT"));
					currencyBean.setCREAT_BY(resultSetCurrency.getString("CREAT_BY"));
					currencyBean.setMOD_BY(resultSetCurrency.getString("MOD_BY"));
					if (resultSetCurrency.getInt("IS_ACTIVE") == 1) {
						currencyBean.setActive(true);
					} else {
						currencyBean.setActive(false);
					}
					userList.add(currencyBean);
				}
			}
			adminForm.setCurrencyBean(userList);

		} catch (Exception e) {
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetCurrency);

		}
		return adminForm;
	}

	@Override
	public AdminForm addOrEditUSD(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
			QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (CurrencyBean bean : adminForm.getCurrencyBean()) {

				QUOT_ADMIN.addRow(bean.getCUR_ID(), bean.getCUR_NM(), bean.getCUR_CD(), bean.getRUPEE(), null, null,
						(bean.isActive() ? 1 : 0));

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_USD(?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
			callableStatement.setInt(2, adminForm.getModifiedBy());
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;

	}

	@Override
	public AdminForm addOrEditRegion(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
			QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (UserProfileDetails bean : adminForm.getUserProfileDetailsList()) {

				QUOT_ADMIN.addRow(bean.getRegionId(), bean.getRegion(), bean.getRegionCode(), null, null, null,
						(bean.isActive() ? 1 : 0));

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_REGION (?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm addOrEditRole(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
			QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);
			// list of regions

			for (UserProfileDetails bean : adminForm.getUserProfileDetailsList()) {

				QUOT_ADMIN.addRow(bean.getRoleId(), bean.getRole(), bean.getRoleCode(), null, null, null,
						(bean.isActive() ? 1 : 0));

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_ROLES (?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public QuotationForm getAdminCacheWithAIList(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;

		ResultSet resultSetDepartments = null;
		ResultSet resultSetRegions = null;
		ResultSet resultSetRoles = null;
		ResultSet resultSetUserInfo = null;
		ResultSet resultSetFramepower = null;
		ResultSet resultSetOrientationList = null;
		ResultSet resultsetCategory = null;
		ResultSet resultsetMaterial = null;
		ResultSet resultSetCurrency = null;
		ResultSet resultSetDBOMechBean = null;
		ResultSet resultSetDBOElecBean = null;
		ResultSet resultSetVehiclesList = null;
		ResultSet resultSetcompType = null;
		ResultSet resultSetPlace = null;
		ResultSet resultSetPlace1 = null;
		ResultSet resultSetPlace2 = null;
		ResultSet resultSetPlace3 = null;
		ResultSet resultSetPlace4 = null;
		ResultSet resultSetPlace5 = null;
		ResultSet resultSetPlace6 = null;
		ResultSet resultSetPlace7 = null;
		ResultSet resultSetPlace8 = null;
		ResultSet resultSetPlace9 = null;
		ResultSet resultSetPlace10 = null;
		ResultSet resultSetPlace11 = null;
		ResultSet resultSetPlace12 = null;
		ResultSet resultSetPlace13 = null;
		ResultSet resultSetPlace14 = null;
		ResultSet resultSetPlace15 = null;
		ResultSet resultSetPlace16 = null;
		ResultSet resultSetPlace17 = null;
		ResultSet resultSetPlace18 = null;
		ResultSet resultSetPlace19 = null;
		ResultSet resultSetPlace20 = null;
		ResultSet resultSetPlace21 = null;
		ResultSet resultSetPlace22 = null;
		ResultSet resultSetFrame = null;

		Map<String, List<SelectBox>> department = new HashMap<>();
		Map<String, List<SelectBox>> regions = new HashMap<>();
		Map<String, List<SelectBox>> roles = new HashMap<>();
		Map<String, List<TurbineDetails>> frames = new HashMap<>();
		Map<String, List<TurbineDetails>> frames1 = new HashMap<>();

		Map<String, List<TurbineDetails>> orientationType = new HashMap<>();
		Map<String, List<F2FUBOBean>> categoryList = new HashMap<>();
		Map<String, List<F2FUBOBean>> materialList = new HashMap<>();
		Map<String, List<DBOBean>> dboMechVal = new HashMap<>();
		Map<String, List<DBOBean>> dboElectVal = new HashMap<>();
		Map<String, List<CurrencyBean>> currencyVal = new HashMap<>();
		Map<String, List<TurbineDetails>> vehiclesMap = new HashMap<>();
		Map<String, List<TurbineDetails>> componentTypes = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places1 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places2 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places3 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places4 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places5 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places6 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places7 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places8 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places9 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places10 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places11 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places12 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places13 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places14 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places15 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places16 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places17 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places18 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places19 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places20 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places21 = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places22 = new HashMap<>();

		// UserList
		Map<Integer, UserProfileDetails> userMap = new HashMap<>();
		List<UserProfileDetails> userDetailsList = new ArrayList<>();
		List<UserProfileDetails> userList = new ArrayList<>();
		// UserList

		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_AMIN_CACHE() }");
			callableStatement.execute();

			// ====================================Department List Start
			// ======================================================

			resultSetDepartments = callableStatement.getResultSet();
			List<SelectBox> selectBoxDepartmentList = new ArrayList<>();
			while (resultSetDepartments.next()) {
				SelectBox selectBox = new SelectBox();
				selectBox.setKey(resultSetDepartments.getInt("DEPT_ID"));
				selectBox.setValue(resultSetDepartments.getString("DEPT_NAME"));
				selectBox.setCode(resultSetDepartments.getString("DEPT_CD"));
				selectBox.setActive((resultSetDepartments.getInt("IS_ACTIVE") == 1 ? true : false));
				selectBoxDepartmentList.add(selectBox);
			}
			if (!selectBoxDepartmentList.isEmpty()) {
				department.put("DEPARTMENTS", selectBoxDepartmentList);
				quotationForm.getDropDownColumnvalues().setDepartmentsList(department);
			}

			// ====================================Regions List Start
			// ======================================================

			if (callableStatement.getMoreResults()) {
				resultSetRegions = callableStatement.getResultSet();
				List<SelectBox> selectBoxRegionsList = new ArrayList<>();
				while (resultSetRegions.next()) {
					SelectBox selectBox = new SelectBox();
					selectBox.setKey(resultSetRegions.getInt("REGION_ID"));
					selectBox.setValue(resultSetRegions.getString("NAME"));
					selectBox.setCode(resultSetRegions.getString("REGION_KEY"));
					selectBox.setActive((resultSetRegions.getInt("IS_ACTIVE") == 1 ? true : false));
					selectBoxRegionsList.add(selectBox);
				}
				if (!selectBoxRegionsList.isEmpty()) {
					regions.put("REGIONS", selectBoxRegionsList);
					quotationForm.getDropDownColumnvalues().setRegionsList(regions);
				}
			}

			// =======================Regions List End ======

			// =======================Roles List Start ======

			if (callableStatement.getMoreResults()) {
				resultSetRoles = callableStatement.getResultSet();
				List<SelectBox> selectBoxRolesList = new ArrayList<>();
				while (resultSetRoles.next()) {
					SelectBox selectBox = new SelectBox();
					selectBox.setKey(resultSetRoles.getInt("ROLES_ID"));
					selectBox.setValue(resultSetRoles.getString("ROLE_NAME"));
					selectBox.setCode(resultSetRoles.getString("GROUP_CD"));
					selectBox.setActive((resultSetRoles.getInt("IS_ACTIVE") == 1 ? true : false));
					selectBoxRolesList.add(selectBox);
				}
				if (!selectBoxRolesList.isEmpty()) {
					roles.put("ROLES", selectBoxRolesList);
					quotationForm.getDropDownColumnvalues().setRolesList(roles);
				}
			}

			// ===============Regions List End ============

			/// ================ User List Dtarts
			if (callableStatement.getMoreResults()) {
				resultSetUserInfo = callableStatement.getResultSet();
				while (resultSetUserInfo.next()) {
					UserProfileDetails userDetails = new UserProfileDetails();

					userDetails.setUserId(resultSetUserInfo.getInt("USER_ID"));
					userDetails.setEmpId(resultSetUserInfo.getInt("EMP_ID"));
					userDetails.setEmpName(resultSetUserInfo.getString("NAME"));
					userDetails.setContactNumber(resultSetUserInfo.getString("PH_NUM"));
					userDetails.setEmailId(resultSetUserInfo.getString("EMAIL"));
					userDetails.setGroupId(resultSetUserInfo.getInt("DEPT_ID"));
					userDetails.setGroup(resultSetUserInfo.getString("DEPT_NAME"));
					if (resultSetUserInfo.getBytes("IMAGE") != null) {

						String encoded = Base64.getEncoder().encodeToString(resultSetUserInfo.getBytes("IMAGE"));
						userDetails.setImage(encoded);
					}
					userDetails.setRegion(resultSetUserInfo.getString("REGION"));
					userDetails.setRole(resultSetUserInfo.getString("ROLES"));
					userDetails.setGroupCd(resultSetUserInfo.getString("GROUP_CD"));
					userDetails.setCreatedDate(resultSetUserInfo.getString("CREAT_DT"));
					userDetails.setModifiedDate(resultSetUserInfo.getString("MOD_DT"));
					userDetails.setCreatedById(resultSetUserInfo.getInt("CREAT_BY_ID"));
					userDetails.setModifiedById(resultSetUserInfo.getInt("MOD_BY_ID"));
					if (resultSetUserInfo.getInt("IS_ACTIVE") == 1) {
						userDetails.setActive(true);
						userDetails.setUserActiveStatus("Active");
					} else {
						userDetails.setActive(false);
						userDetails.setUserActiveStatus("In-Active");
					}
					userDetails.setLastLoggedInDate(resultSetUserInfo.getDate("LAST_LOGIN_DT"));
					userDetails.setRegionId(resultSetUserInfo.getInt("REGION_ID"));
					userDetails.setRoleId(resultSetUserInfo.getInt("ROLES_ID"));
					userDetails.setCreatedBy(resultSetUserInfo.getString("CREAT_BY"));
					userDetails.setModifiedBy(resultSetUserInfo.getString("MOD_BY"));
					if (resultSetUserInfo.getInt("IS_ACTIVE") == 1) {
						userDetails.setActive(true);
						userDetails.setUserActiveStatus("Active");
					} else {
						userDetails.setActive(false);
						userDetails.setUserActiveStatus("In-Active");
					}
					userDetails.setDesignation(resultSetUserInfo.getString("DESIGNATION"));
					userDetailsList.add(userDetails);
				}

				for (UserProfileDetails user : userDetailsList) {
					userMap.put(user.getUserId(), user);
				}

				for (Integer uniqUser : userMap.keySet()) {
					Map<Integer, String> region = new HashMap<>();
					Map<Integer, String> role = new HashMap<>();
					for (UserProfileDetails user : userDetailsList) {
						if (uniqUser == user.getUserId()) {
							region.put(user.getRegionId(), user.getRegion());
							role.put(user.getRoleId(), user.getRole());
						}
					}
					List<String> regionVal = new ArrayList<>(region.values());
					List<String> roleVal = new ArrayList<>(role.values());

					List<SelectBox> selectBoxRegionsList = new ArrayList<>();
					for (Map.Entry reg : region.entrySet()) {
						SelectBox selectBox = new SelectBox();
						selectBox.setKey((int) reg.getKey());
						selectBox.setValue((String) reg.getValue());
						selectBox.setDefaultVal(true);
						selectBoxRegionsList.add(selectBox);
					}
					List<SelectBox> selectBoxRolesList = new ArrayList<>();
					for (Map.Entry rol : role.entrySet()) {
						SelectBox selectBox = new SelectBox();
						selectBox.setKey((int) rol.getKey());
						selectBox.setValue((String) rol.getValue());
						selectBox.setDefaultVal(true);
						selectBoxRolesList.add(selectBox);
					}

					userMap.get(uniqUser).setUserRegionsList(selectBoxRegionsList);
					userMap.get(uniqUser).setUserRolesList(selectBoxRolesList);
					userMap.get(uniqUser).setRegionsVal(regionVal);
					userMap.get(uniqUser).setRolesVal(roleVal);
					userMap.get(uniqUser).setRegion(null);
					userMap.get(uniqUser).setRole(null);
				}
				for (Map.Entry userDetails : userMap.entrySet()) {

					userList.add((UserProfileDetails) userDetails.getValue());
				}
				quotationForm.setUserDetailsList(userList);

			}
			// ==================================== Users List End
			// ==================================== Frame List starts

			if (callableStatement.getMoreResults()) {
				resultSetFrame = callableStatement.getResultSet();
				List<TurbineDetails> frameList = new ArrayList<>();
				while (resultSetFrame.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setFrameId(resultSetFrame.getInt("FRM_ID"));
					turbineDetails.setTurbineCode(resultSetFrame.getString("TURB_CD"));
					turbineDetails.setTurbineDesignCd(resultSetFrame.getString("TURB_DESN"));
					turbineDetails.setFrameDesc(resultSetFrame.getString("FRM_NM"));
					turbineDetails.setPower(resultSetFrame.getFloat("POWER"));

					if (turbineDetails.getTurbineCode().equals(ItoConstants.BP)) {
						turbineDetails.setTurbType(ItoConstants.BACKPRESSURE);
					} else if (turbineDetails.getTurbineCode().equals(ItoConstants.CD)) {
						turbineDetails.setTurbType(ItoConstants.CONDENSING);
					}
					if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase(ItoConstants.IM)) {
						turbineDetails.setTurbdesignName(ItoConstants.IMPULSE);
					} else if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase(ItoConstants.RN)) {
						turbineDetails.setTurbdesignName(ItoConstants.REACTION);
					}

					if (resultSetFrame.getInt("IS_ACTIVE") == 1) {
						turbineDetails.setFrameActive(true);
					} else {
						turbineDetails.setFrameActive(false);
					}

					frameList.add(turbineDetails);
				}
				if (!frameList.isEmpty()) {
					frames1.put("FRAMES_LIST", frameList);
					quotationForm.getDropDownColumnvalues().setFrames(frames1);
				}
			}

			// =========================Frames List ends
			// =============================

			// =========================Frames with power List starts
			// =============================
			if (callableStatement.getMoreResults()) {
				resultSetFramepower = callableStatement.getResultSet();
				List<TurbineDetails> frameWithPowerList = new ArrayList<>();
				while (resultSetFramepower.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setFramePowerId(resultSetFramepower.getInt("FRAME_POW_ID"));
					turbineDetails.setFrameId(resultSetFramepower.getInt("FRM_ID"));
					turbineDetails.setTurbineCode(resultSetFramepower.getString("TURB_CD"));
					turbineDetails.setTurbineDesignCd(resultSetFramepower.getString("TURB_DESN"));
					turbineDetails.setFrameDesc(resultSetFramepower.getString("FRM_NM"));
					turbineDetails.setMinPower(resultSetFramepower.getFloat("MIN_POWER"));
					turbineDetails.setMaxPower(resultSetFramepower.getFloat("MAX_POWER"));

					if (turbineDetails.getTurbineCode().equals(ItoConstants.BP)) {
						turbineDetails.setTurbType(ItoConstants.BACKPRESSURE);
					} else if (turbineDetails.getTurbineCode().equals(ItoConstants.CD)) {
						turbineDetails.setTurbType(ItoConstants.CONDENSING);
					}
					if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase(ItoConstants.IM)) {
						turbineDetails.setTurbdesignName(ItoConstants.IMPULSE);
					} else if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase(ItoConstants.RN)) {
						turbineDetails.setTurbdesignName(ItoConstants.REACTION);
					}
					turbineDetails.setCreatedDate(resultSetFramepower.getString("CREAT_DT"));
					turbineDetails.setModifiedDate(resultSetFramepower.getString("MOD_DT"));
					turbineDetails.setCreatedBy(resultSetFramepower.getString("CREAT_BY"));
					turbineDetails.setModifiedBy(resultSetFramepower.getString("MOD_BY"));
					if (resultSetFramepower.getInt("IS_ACTIVE") == 1) {
						turbineDetails.setFramePowerActive(true);
					} else {
						turbineDetails.setFramePowerActive(false);
					}

					frameWithPowerList.add(turbineDetails);
				}
				if (!frameWithPowerList.isEmpty()) {
					frames.put("FRAMES_WITH_POWER", frameWithPowerList);
					quotationForm.getDropDownColumnvalues().setFrameWithPowerList(frames);
				}
			}

			// =========================Frames List ends
			// =============================

			// ====================Orientation type List Start==============

			if (callableStatement.getMoreResults()) {
				resultSetOrientationList = callableStatement.getResultSet();
				List<TurbineDetails> orientationTypeList = new ArrayList<>();
				while (resultSetOrientationList.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetOrientationList.getInt("CAT_DET_ID"));
					turbineDetails.setCategoryDetCode(resultSetOrientationList.getString("CAT_DET_CD"));
					turbineDetails.setCategoryDetDesc(resultSetOrientationList.getString("CAT_DET_DESC"));
					turbineDetails.setTurbineCode((resultSetOrientationList.getString("TURB_CD")));

					if (resultSetOrientationList.getInt("IS_ACTIVE") == 1) {
						turbineDetails.setIscategoryActive(true);
					} else {
						turbineDetails.setIscategoryActive(false);
					}

					orientationTypeList.add(turbineDetails);
				}
				if (!orientationTypeList.isEmpty()) {
					orientationType.put("ORIENTATION_TYPE", orientationTypeList);
					quotationForm.getDropDownColumnvalues().setOrientationTypes(orientationType);
				}
			}

			// ===================Orientation Type List End===================

			// =============Category List starts====
			List<F2FUBOBean> f2fCategoryList = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsetCategory = callableStatement.getResultSet();

				while (resultsetCategory.next()) {
					F2FUBOBean adBean = new F2FUBOBean();

					adBean.setCAT_ID(resultsetCategory.getInt("CAT_ID"));
					adBean.setCAT_NM(resultsetCategory.getString("CAT_NM"));
					adBean.setCAT_CD(resultsetCategory.getString("CAT_CD"));
					if (resultsetCategory.getInt("IS_ACTIVE") == 1) {
						adBean.setIscategoryActive(true);
					} else {
						adBean.setIscategoryActive(false);
					}
					f2fCategoryList.add(adBean);
				}
				if (!f2fCategoryList.isEmpty()) {
					categoryList.put("CAT_DET_LIST", f2fCategoryList);
					quotationForm.getDropDownColumnvalues().setCategoryList(categoryList);
				}
			}
			/// =====Material starts

			List<F2FUBOBean> f2fMaterialList = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsetMaterial = callableStatement.getResultSet();

				while (resultsetMaterial.next()) {
					F2FUBOBean adBean = new F2FUBOBean();
					adBean.setMTRL_ID(resultsetMaterial.getInt("MTRL_ID"));
					adBean.setMTRL_NM(resultsetMaterial.getString("MTRL_NM"));
					adBean.setMTRL_CD(resultsetMaterial.getString("MTRL_CD"));
					adBean.setCAT_ID(resultsetMaterial.getInt("CAT_ID"));
					adBean.setCAT_NM(resultsetMaterial.getString("CAT_NM"));
					adBean.setCAT_CD(resultsetMaterial.getString("CAT_CD"));
					if (resultsetMaterial.getInt("IS_ACTIVE") == 1) {
						adBean.setIscategoryActive(true);
					} else {
						adBean.setIscategoryActive(false);
					}
					f2fMaterialList.add(adBean);
				}
				if (!f2fMaterialList.isEmpty()) {
					materialList.put("MAT_DET_LIST", f2fMaterialList);
					quotationForm.getDropDownColumnvalues().setMaterialList(materialList);
				}
			}
			// ==== materialist ends
			// // === DBO Mech Item starts
			// List<DBOBean> dboMechList = new ArrayList<>();
			//
			// if (callableStatement.getMoreResults()) {
			// resultSetDBOMechBean = callableStatement.getResultSet();
			// while (resultSetDBOMechBean.next()) {
			// DBOBean dboBean = new DBOBean();
			// dboBean.setCatDetId(resultSetDBOMechBean.getInt("CAT_DET_ID"));
			// dboBean.setCatDetCd(resultSetDBOMechBean.getString("CAT_DET_CD"));
			// dboBean.setCatDetDesc(resultSetDBOMechBean.getString("CAT_DET_DESC"));
			//
			// if (resultSetDBOMechBean.getInt("IS_ACTIVE") == 1) {
			// dboBean.setActive(true);
			// } else {
			// dboBean.setActive(false);
			// }
			// dboMechList.add(dboBean);
			// }
			// if (!dboMechList.isEmpty()) {
			// dboMechVal.put("DBO_MECH_LIST", dboMechList);
			// quotationForm.getDropDownColumnvalues().setDboMechanicalList(dboMechVal);
			// }
			// }
			// // == DBO Mech Items ends
			// ==DBO Elect Items Starts
			// List<DBOBean> DBOelechList = new ArrayList<>();
			//
			// if (callableStatement.getMoreResults()) {
			// resultSetDBOElecBean = callableStatement.getResultSet();
			// while (resultSetDBOElecBean.next()) {
			// DBOBean dboBean = new DBOBean();
			// dboBean.setCatDetId(resultSetDBOElecBean.getInt("CAT_DET_ID"));
			// dboBean.setCatDetCd(resultSetDBOElecBean.getString("CAT_DET_CD"));
			// dboBean.setCatDetDesc(resultSetDBOElecBean.getString("CAT_DET_DESC"));
			//
			// if (resultSetDBOElecBean.getInt("IS_ACTIVE") == 1) {
			// dboBean.setActive(true);
			// } else {
			// dboBean.setActive(false);
			// }
			// DBOelechList.add(dboBean);
			// }
			// if (!DBOelechList.isEmpty()) {
			// dboElectVal.put("DBO_MECH_LIST", DBOelechList);
			// quotationForm.getDropDownColumnvalues().setDboElectricalList(dboElectVal);
			// }
			// }
			//
			// // ==DBO Elect Item ends
			// ======== currency start
			List<CurrencyBean> currencyList = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultSetCurrency = callableStatement.getResultSet();
				while (resultSetCurrency.next()) {
					CurrencyBean currencyBean = new CurrencyBean();
					currencyBean.setCUR_ID(resultSetCurrency.getInt("CUR_ID"));
					currencyBean.setCUR_NM(resultSetCurrency.getString("CUR_NM"));
					currencyBean.setCUR_CD(resultSetCurrency.getString("CUR_CD"));
					currencyBean.setConvertionRate(resultSetCurrency.getFloat("CONVERTION_RATE"));

					if (resultSetCurrency.getInt("IS_ACTIVE") == 1) {
						currencyBean.setActive(true);
					} else {
						currencyBean.setActive(false);
					}
					currencyList.add(currencyBean);
				}
				if (!currencyList.isEmpty()) {
					currencyVal.put("CURR_LIST", currencyList);
					quotationForm.getDropDownColumnvalues().setCurrencyList(currencyVal);
				}
			}
			// ================Currency End
			// Vehicles List
			List<TurbineDetails> vehiclesList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetVehiclesList = callableStatement.getResultSet();

				while (resultSetVehiclesList.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetVehiclesList.getInt("VEHICLE_ID"));
					turbineDetails.setCategoryDetCode(resultSetVehiclesList.getString("VH_CD"));
					turbineDetails.setCategoryDetDesc(resultSetVehiclesList.getString("VH_NAME"));
					turbineDetails.setLength(resultSetVehiclesList.getString("VH_LEN_MT"));
					turbineDetails.setDimension(resultSetVehiclesList.getString("VH_LEN_FT"));

					if (resultSetVehiclesList.getInt("IS_ACTIVE") == 1) {
						turbineDetails.setIscategoryActive(true);
					} else {
						turbineDetails.setIscategoryActive(false);
					}

					vehiclesList.add(turbineDetails);
				}
				if (!vehiclesList.isEmpty()) {
					vehiclesMap.put("VEHICLE_LIST", vehiclesList);
					quotationForm.getDropDownColumnvalues().setVehiclesList(vehiclesMap);
				}
			}
			// TransportComponentTypeList
			if (callableStatement.getMoreResults()) {
				resultSetcompType = callableStatement.getResultSet();
				List<TurbineDetails> componentTypeList = new ArrayList<>();
				while (resultSetcompType.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetcompType.getInt("COMP_ID"));
					turbineDetails.setCategoryDetCode(resultSetcompType.getString("COMP_CD"));
					turbineDetails.setTurbineCode(resultSetcompType.getString("TURB_CD"));
					turbineDetails.setCategoryDetDesc(resultSetcompType.getString("COMP_NM"));
					turbineDetails.setPlace(resultSetcompType.getString("FOB_PLACE"));
					turbineDetails.setPlaceId(resultSetcompType.getString("FOB_ID"));
					turbineDetails.setSsId(resultSetcompType.getInt("SS_ID"));
					turbineDetails.setSsName(resultSetcompType.getString("SCOPE_OF_SUPPLY"));
					if (resultSetcompType.getInt("IS_ACTIVE") == 1) {
						turbineDetails.setIscategoryActive(true);
					} else {
						turbineDetails.setIscategoryActive(false);
					}

					componentTypeList.add(turbineDetails);
				}
				if (!componentTypeList.isEmpty()) {
					componentTypes.put("TransportComponentTypeList", componentTypeList);
					quotationForm.getDropDownColumnvalues().setComponentTypes(componentTypes);
				}
			}
			// placeList
			if (callableStatement.getMoreResults()) {
				resultSetPlace = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList = new ArrayList<>();
				while (resultSetPlace.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setPlaceId(resultSetPlace.getInt("FOB_ID"));
					bean.setFOBPlace(resultSetPlace.getString("FOB_PLACE"));
					bean.setFOBPlaceCode(resultSetPlace.getString("FOB_PLACE_CD"));
					bean.setDistance(resultSetPlace.getFloat("FOB_DISTANCE"));
					if (resultSetPlace.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList.add(bean);
				}
				if (!placeList.isEmpty()) {
					places.put("placeList", placeList);
					quotationForm.getDropDownColumnvalues().setPlaceList(places);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetPlace1 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList1 = new ArrayList<>();
				while (resultSetPlace1.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setUnItemId(resultSetPlace1.getInt("UN_ITEM_ID"));
					bean.setScopeCode(resultSetPlace1.getString("SCOP_CD"));
					bean.setUnItemCode(resultSetPlace1.getString("UN_ITEM_CD"));
					bean.setUnItemName(resultSetPlace1.getString("UN_ITEM_NM"));
					bean.setF2fAddOn(resultSetPlace1.getInt("F2F_ADD_ON"));
					bean.setExclusionNm(resultSetPlace1.getString("EXCLUSION_NM"));
					bean.setHeaderText(resultSetPlace1.getString("HEADER_TEXT"));
					bean.setFooterText(resultSetPlace1.getString("FOOTER_TEXT"));
					if (resultSetPlace1.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList1.add(bean);
				}
				if (!placeList1.isEmpty()) {
					places1.put("placeList1", placeList1);
					quotationForm.getDropDownColumnvalues().setPlaceList1(places1);
				}
			}

			// if (callableStatement.getMoreResults()) {
			// resultSetPlace2= callableStatement.getResultSet();
			// List<TransportationDetailsBean> placeList2 = new ArrayList<>();
			// while (resultSetPlace2.next()) {
			// TransportationDetailsBean bean = new TransportationDetailsBean();
			// bean.setId(resultSetPlace2.getInt("ID"));
			// bean.setFramePowerId(resultSetPlace2.getInt("FRM_POW_ID"));
			// bean.setFrameName(resultSetPlace2.getString("FRM_NM"));
			// bean.setMaxPower(resultSetPlace2.getFloat("MAX_POWER"));
			// bean.setBgmType(resultSetPlace2.getString("BGM_TYPE"));
			// bean.setBgmRating(resultSetPlace2.getFloat("BGM_RATING"));
			// bean.setDefaultFlag(resultSetPlace2.getInt("DEFLT_FLG"));
			//
			// placeList2.add(bean);
			// }
			// if (!placeList2.isEmpty()) {
			// places2.put("placeList2", placeList2);
			// quotationForm.getDropDownColumnvalues().setPlaceList2(places2);
			// }
			// }

			if (callableStatement.getMoreResults()) {
				resultSetPlace3 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList3 = new ArrayList<>();
				while (resultSetPlace3.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setItemId(resultSetPlace3.getInt("ITEM_ID"));
					bean.setItemCode(resultSetPlace3.getString("ITEM_CD"));
					bean.setItemName(resultSetPlace3.getString("ITEM_NM"));
					bean.setGroupCd(resultSetPlace3.getString("GROUP_CD"));
					bean.setGroupName(resultSetPlace3.getString("GROUP_NAME"));
					bean.setNoOfMonths(resultSetPlace3.getInt("NO_OF_MONTHS"));
					bean.setRegionId(resultSetPlace3.getInt("REGION_ID"));
					bean.setRegionName(resultSetPlace3.getString("REGION_NM"));
					bean.setCustType(resultSetPlace3.getString("CUST_TYPE"));
					bean.setPercent(resultSetPlace3.getFloat("PERCNT"));

					placeList3.add(bean);
				}
				if (!placeList3.isEmpty()) {
					places3.put("placeList3", placeList3);
					quotationForm.getDropDownColumnvalues().setPlaceList3(places3);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace4 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList4 = new ArrayList<>();
				while (resultSetPlace4.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace4.getInt("ID"));
					bean.setFramePowerId(resultSetPlace4.getInt("FRM_POW_ID"));
					bean.setFrameName(resultSetPlace4.getString("FRM_NM"));
					bean.setMaxPower(resultSetPlace4.getInt("MAX_POWER"));
					bean.setBgmType(resultSetPlace4.getString("BGM_TYPE"));
					bean.setBgmRating(resultSetPlace4.getFloat("BGM_RATING"));
					bean.setDefaultFlag(resultSetPlace4.getInt("DEFLT_FLG"));
					if (resultSetPlace4.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList4.add(bean);
				}
				if (!placeList4.isEmpty()) {
					places4.put("placeList4", placeList4);
					quotationForm.getDropDownColumnvalues().setPlaceList4(places4);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetPlace5 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList5 = new ArrayList<>();
				while (resultSetPlace5.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace5.getInt("ID"));
					bean.setFramePowerId(resultSetPlace5.getInt("FRM_POW_ID"));
					bean.setFrameName(resultSetPlace5.getString("FRM_NM"));
					bean.setMaxPower(resultSetPlace5.getInt("MAX_POWER"));
					bean.setEopMotorRating(resultSetPlace5.getFloat("EOP_MOTOR_RATING"));
					bean.setCost(resultSetPlace5.getFloat("COST"));
					bean.setPumpType(resultSetPlace5.getString("PUMP_TYPE"));
					bean.setBgHrRate(resultSetPlace5.getInt("BG_HR_RATE"));
					bean.setDcOutputVol(resultSetPlace5.getFloat("DC_OUTPUT_VOL"));
					bean.setDefaultFlag(resultSetPlace5.getInt("DEFLT_FLG"));
					if (resultSetPlace5.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList5.add(bean);
				}
				if (!placeList5.isEmpty()) {
					places5.put("placeList5", placeList5);
					quotationForm.getDropDownColumnvalues().setPlaceList5(places5);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetPlace6 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList6 = new ArrayList<>();
				while (resultSetPlace6.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setEleItemId(resultSetPlace6.getInt("ELE_ITEM_ID"));
					bean.setTypeOfPanel(resultSetPlace6.getString("TYPE_OF_PANEL"));
					bean.setEleType(resultSetPlace6.getString("ELE_TYPE"));
					bean.setItemId(resultSetPlace6.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace6.getString("ITEM_NM"));
					bean.setSubItemId(resultSetPlace6.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetPlace6.getString("ITEM_NM"));
					bean.setHeaderText(resultSetPlace6.getString("HEADER_TEXT"));
					bean.setFooterText(resultSetPlace6.getString("FOOTER_TEXT"));
					bean.setHeaderNm(resultSetPlace6.getString("HEARER_NM"));
					bean.setExclusionNm(resultSetPlace6.getString("EXCLUSION_NM"));
					if (resultSetPlace6.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList6.add(bean);
				}
				if (!placeList6.isEmpty()) {
					places6.put("placeList6", placeList6);
					quotationForm.getDropDownColumnvalues().setPlaceList6(places6);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace7 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList7 = new ArrayList<>();
				while (resultSetPlace7.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setVariant_id(resultSetPlace7.getInt("VARIANT_ID"));
					bean.setFramePowerId(resultSetPlace7.getInt("FRM_POW_ID"));
					bean.setFrameName(resultSetPlace7.getString("FRM_NM"));
					bean.setMaxPower(resultSetPlace7.getFloat("MAX_POWER"));
					bean.setVariant_cd(resultSetPlace7.getString("VARIANT_CD"));
					bean.setC_num(resultSetPlace7.getString("C_NUM"));
					bean.setCust_id(resultSetPlace7.getInt("CUST_ID"));
					bean.setStatusId(resultSetPlace7.getInt("STATUS_ID"));
					bean.setStatusName(resultSetPlace7.getString("STATUS_NAME"));
					if (resultSetPlace7.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList7.add(bean);
				}
				if (!placeList7.isEmpty()) {
					places7.put("placeList7", placeList7);
					quotationForm.getDropDownColumnvalues().setPlaceList7(places7);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace8 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList8 = new ArrayList<>();
				while (resultSetPlace8.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setAuxId(resultSetPlace8.getInt("AUX_ID"));
					bean.setItemId(resultSetPlace8.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace8.getString("ITEM_NM"));
					bean.setSubItemId(resultSetPlace8.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetPlace8.getString("SUB_ITEM_NM"));
					bean.setCondTypeId(resultSetPlace8.getInt("COND_TYP_ID"));
					bean.setCondTypeName(resultSetPlace8.getString("COND_TYP_NAME"));
					bean.setFrmId(resultSetPlace8.getInt("FRM_ID"));
					bean.setConsId(resultSetPlace8.getString("CONSUMER_ID"));
					bean.setStartup(resultSetPlace8.getString("STARTUP"));
					bean.setContinuous(resultSetPlace8.getString("CONTINUOUS"));
					bean.setColValCd(resultSetPlace8.getString("COL_VAL_CD"));
					bean.setEditFlag(resultSetPlace8.getInt("EDIT_FLG"));
					if (resultSetPlace8.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList8.add(bean);
				}
				if (!placeList8.isEmpty()) {
					places8.put("placeList8", placeList8);
					quotationForm.getDropDownColumnvalues().setPlaceList8(places8);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace9 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList9 = new ArrayList<>();
				while (resultSetPlace9.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setAuxId(resultSetPlace9.getInt("ID"));
					bean.setItemId(resultSetPlace9.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace9.getString("ITEM_NM"));
					bean.setSubItemId(resultSetPlace9.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetPlace9.getString("SUB_ITEM_NM"));
					bean.setCondTypeId(resultSetPlace9.getInt("COND_TYP_ID"));
					bean.setCondTypeName(resultSetPlace9.getString("COND_TYP_NAME"));
					bean.setConsId(resultSetPlace9.getString("CONSUMER_ID"));
					bean.setContinuous(resultSetPlace9.getString("CONTINUOUS"));
					bean.setColValCd(resultSetPlace9.getString("COL_VAL_CD"));
					bean.setEditFlag(resultSetPlace9.getInt("EDIT_FLG"));
					if (resultSetPlace9.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList9.add(bean);
				}
				if (!placeList9.isEmpty()) {
					places9.put("placeList9", placeList9);
					quotationForm.getDropDownColumnvalues().setPlaceList9(places9);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace10 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList10 = new ArrayList<>();
				while (resultSetPlace10.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace10.getInt("ID"));
					bean.setItemId(resultSetPlace10.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace10.getString("ITEM_NM"));
					bean.setSubItemId(resultSetPlace10.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetPlace10.getString("SUB_ITEM_NM"));
					bean.setCategory(resultSetPlace10.getString("CATEGORY"));
					bean.setCondTypeId(resultSetPlace10.getInt("COND_TYP_ID"));
					bean.setItemType(resultSetPlace10.getString("ITEM_TYPE"));
					bean.setItemCd(resultSetPlace10.getString("ITEM_CD"));
					bean.setSpeed(resultSetPlace10.getString("SPEED"));
					bean.setVoltage(resultSetPlace10.getString("VOLTAGE"));
					bean.setFeeder(resultSetPlace10.getString("FEEDER"));
					bean.setStartup(resultSetPlace10.getString("STARTUP"));
					bean.setContinuous(resultSetPlace10.getString("CONTINUOUS"));
					bean.setColValCd(resultSetPlace10.getString("COL_VAL_CD"));
					bean.setEditFlag(resultSetPlace10.getInt("EDIT_FLG"));
					bean.setPanelType(resultSetPlace10.getString("PANEL_TYPE"));
					logger.info(resultSetPlace10.getInt("ID"));
					if (resultSetPlace10.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList10.add(bean);
				}
				if (!placeList10.isEmpty()) {
					places10.put("placeList10", placeList10);
					quotationForm.getDropDownColumnvalues().setPlaceList10(places10);
					logger.info("placeList10");
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace11 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList11 = new ArrayList<>();
				while (resultSetPlace11.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace11.getInt("ID"));
					bean.setFrmId(resultSetPlace11.getInt("FRM_ID"));
					bean.setFrmName(resultSetPlace11.getString("FRM_NM"));
					bean.setItemCd(resultSetPlace11.getString("ITEM_CD"));
					bean.setMinPower(resultSetPlace11.getFloat("MIN_POWER"));
					bean.setMaxPower(resultSetPlace11.getFloat("MAX_POWER"));
					bean.setContinuous(resultSetPlace11.getString("CONTINUOUS"));
					if (resultSetPlace11.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList11.add(bean);
				}
				if (!placeList11.isEmpty()) {
					places11.put("placeList11", placeList11);
					quotationForm.getDropDownColumnvalues().setPlaceList11(places11);
				}
			}
			/*
			 * if (callableStatement.getMoreResults()) { resultSetPlace12 =
			 * callableStatement.getResultSet(); List<TransportationDetailsBean>
			 * placeList12 = new ArrayList<>(); while (resultSetPlace12.next())
			 * { TransportationDetailsBean bean = new
			 * TransportationDetailsBean();
			 * bean.setInstrId(resultSetPlace12.getInt("INSTR_ID"));
			 * bean.setFramePowerId(resultSetPlace12.getInt("FRM_POW_ID"));
			 * bean.setFrmName(resultSetPlace12.getString("FRM_NM"));
			 * bean.setCondTypeId(resultSetPlace12.getInt("COND_TYP_ID"));
			 * bean.setCondTypeName(resultSetPlace12.getString("COND_TYP_NAME"))
			 * ; bean.setBleedTypeId(resultSetPlace12.getInt("BLEED_TYP_ID"));
			 * bean.setBleedTypeName(resultSetPlace12.getString("BLEED_TYP_NM"))
			 * ;
			 * bean.setEstimationCost(resultSetPlace12.getFloat("ESTIMTN_COST"))
			 * ;
			 * bean.setTurbInstrCost(resultSetPlace12.getFloat("TURB_INSTR_COST"
			 * ));
			 * bean.setCondInstrCost(resultSetPlace12.getFloat("COND_INSTR_COST"
			 * ));
			 * bean.setSubContrCost(resultSetPlace12.getFloat("SUB_CONTR_COST"))
			 * ;
			 * bean.setShopConvCost(resultSetPlace12.getFloat("SHOP_CONV_COST"))
			 * ;
			 * bean.setTotalFTFCost(resultSetPlace12.getFloat("TOTAL_FTF_COST"))
			 * ; if (resultSetPlace12.getInt("IS_ACTIVE") == 1) {
			 * bean.setActive(true); } else { bean.setActive(false); }
			 * placeList12.add(bean); } if (!placeList12.isEmpty()) {
			 * places12.put("placeList12", placeList12);
			 * quotationForm.getDropDownColumnvalues().setPlaceList12(places12);
			 * } }
			 */
			if (callableStatement.getMoreResults()) {
				resultSetPlace13 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList13 = new ArrayList<>();
				while (resultSetPlace13.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace13.getInt("ID"));
					bean.setItemId(resultSetPlace13.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace13.getString("ITEM_NM"));
					bean.setFrmId(resultSetPlace13.getInt("FRM_ID"));
					bean.setFrmName(resultSetPlace13.getString("FRM_NM"));
					bean.setCustType(resultSetPlace13.getString("CUST_TYPE"));
					bean.setDescription(resultSetPlace13.getString("DESCRIPTION"));
					bean.setQty(resultSetPlace13.getString("QTY"));
					bean.setEditFlag(resultSetPlace13.getInt("EDIT_FLG"));
					if (resultSetPlace13.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList13.add(bean);
				}
				if (!placeList13.isEmpty()) {
					places13.put("placeList13", placeList13);
					quotationForm.getDropDownColumnvalues().setPlaceList13(places13);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace14 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList14 = new ArrayList<>();
				while (resultSetPlace14.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace14.getInt("ID"));
					bean.setItemId(resultSetPlace14.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace14.getString("ITEM_NM"));
					bean.setSubScopeCd(resultSetPlace14.getString("SUB_SCOPE_CD"));
					bean.setCondTypeId(resultSetPlace14.getInt("COND_TYP_ID"));
					bean.setCondTypeName(resultSetPlace14.getString("COND_TYP_NAME"));
					bean.setPanelType(resultSetPlace14.getString("PANEL_TYPE"));
					bean.setEquipment(resultSetPlace14.getString("EQUIPMENT"));
					bean.setEquivalent(resultSetPlace14.getString("EQUIVALENT"));
					bean.setEditFlag(resultSetPlace14.getInt("EDIT_FLG"));
					if (resultSetPlace14.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList14.add(bean);
				}
				if (!placeList14.isEmpty()) {
					places14.put("placeList14", placeList14);
					quotationForm.getDropDownColumnvalues().setPlaceList14(places14);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace15 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList15 = new ArrayList<>();
				while (resultSetPlace15.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace15.getInt("ID"));
					bean.setItemId(resultSetPlace15.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace15.getString("ITEM_NM"));
					bean.setSubScopeCd(resultSetPlace15.getString("SUB_SCOPE_CD"));
					bean.setPanelType(resultSetPlace15.getString("PANEL_TYPE"));
					bean.setEquipment(resultSetPlace15.getString("EQUIPMENT"));
					bean.setTest(resultSetPlace15.getString("TEST"));
					bean.setEditFlag(resultSetPlace15.getInt("EDIT_FLG"));
					if (resultSetPlace15.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList15.add(bean);
				}
				if (!placeList15.isEmpty()) {
					places15.put("placeList15", placeList15);
					quotationForm.getDropDownColumnvalues().setPlaceList15(places15);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace16 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList16 = new ArrayList<>();
				while (resultSetPlace16.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace16.getInt("ID"));
					bean.setItemId(resultSetPlace16.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace16.getString("ITEM_NM"));
					bean.setSubScopeCd(resultSetPlace16.getString("SUB_SCOPE_CD"));
					bean.setCondTypeId(resultSetPlace16.getInt("COND_TYP_ID"));
					bean.setCondTypeName(resultSetPlace16.getString("COND_TYP_NAME"));
					bean.setPanelType(resultSetPlace16.getString("PANEL_TYPE"));
					bean.setBleedTypeId(resultSetPlace16.getInt("BLEED_TYP_ID"));
					bean.setBleedTypeName(resultSetPlace16.getString("BLEED_TYP_NM"));
					bean.setVariantType(resultSetPlace16.getInt("VARIANT_TYP"));
					bean.setVariantTypeName(resultSetPlace16.getString("VARIANT_TYP_NM"));
					bean.setDescription(resultSetPlace16.getString("DESCRIPTION"));
					bean.setEditFlag(resultSetPlace16.getInt("EDIT_FLG"));
					if (resultSetPlace16.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList16.add(bean);
				}
				if (!placeList16.isEmpty()) {
					places16.put("placeList16", placeList16);
					quotationForm.getDropDownColumnvalues().setPlaceList16(places16);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace17 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList17 = new ArrayList<>();
				while (resultSetPlace17.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace17.getInt("ID"));
					bean.setItemId(resultSetPlace17.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace17.getString("ITEM_NM"));
					bean.setSubScopeCd(resultSetPlace17.getString("SUB_SCOPE_CD"));
					bean.setCondTypeId(resultSetPlace17.getInt("COND_TYP_ID"));
					bean.setCondTypeName(resultSetPlace17.getString("COND_TYP_NAME"));
					bean.setPanelType(resultSetPlace17.getString("PANEL_TYPE"));
					bean.setVariantType(resultSetPlace17.getInt("VARIANT_TYP"));
					bean.setVariantTypeName(resultSetPlace17.getString("VARIANT_TYP_NM"));
					bean.setDescription(resultSetPlace17.getString("DESCRIPTION"));
					bean.setEditFlag(resultSetPlace17.getInt("EDIT_FLG"));
					if (resultSetPlace17.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList17.add(bean);
				}
				if (!placeList17.isEmpty()) {
					places17.put("placeList17", placeList17);
					quotationForm.getDropDownColumnvalues().setPlaceList17(places17);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace18 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList18 = new ArrayList<>();
				while (resultSetPlace18.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace18.getInt("ID"));
					bean.setSubScopeCd(resultSetPlace18.getString("SUB_SCOPE_CD"));
					bean.setItemId(resultSetPlace18.getInt("ITEM_ID"));
					bean.setItemName(resultSetPlace18.getString("ITEM_NM"));
					bean.setCondTypeId(resultSetPlace18.getInt("COND_TYP_ID"));
					bean.setCondTypeName(resultSetPlace18.getString("COND_TYP_NAME"));
					bean.setPanelType(resultSetPlace18.getString("PANEL_TYPE"));
					bean.setDescription(resultSetPlace18.getString("DESCRIPTION"));
					bean.setInformation(resultSetPlace18.getString("INFORMATION"));
					bean.setFinal(resultSetPlace18.getString("FINAL"));
					bean.setEditFlag(resultSetPlace18.getInt("EDIT_FLG"));
					placeList18.add(bean);
				}
				if (!placeList18.isEmpty()) {
					places18.put("placeList18", placeList18);
					quotationForm.getDropDownColumnvalues().setPlaceList18(places18);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace19 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList19 = new ArrayList<>();
				while (resultSetPlace19.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace19.getInt("ID"));
					bean.setSsId(resultSetPlace19.getInt("SS_ID"));
					bean.setSubScopeCd(resultSetPlace19.getString("SUB_SCOPE_CD"));
					bean.setTurbineCode(resultSetPlace19.getString("TURB_CD"));
					bean.setMinPower(resultSetPlace19.getFloat("MIN_POWER"));
					bean.setMaxPower(resultSetPlace19.getFloat("MAX_POWER"));
					bean.setNoOfMandays(resultSetPlace19.getInt("NO_OF_MANDAYS"));
					bean.setServiceRemarks(resultSetPlace19.getString("SERVICE_REMARKS"));
					placeList19.add(bean);
					/*
					 * logger.info("placeList19 start");
					 * logger.info(resultSetPlace19.getInt("ID"));
					 * logger.info(resultSetPlace19.getInt("SS_ID"));
					 * logger.info(resultSetPlace19.getString("TURB_CD"));
					 * logger.info(resultSetPlace19.getFloat("MIN_POWER"));
					 * logger.info(resultSetPlace19.getFloat("MAX_POWER"));
					 * logger.info(resultSetPlace19.getInt("NO_OF_MANDAYS"));
					 * logger.info("placeList19 end");
					 */
				}
				if (!placeList19.isEmpty()) {
					logger.info("placeList19 is not empty");
					places19.put("placeList19", placeList19);
					quotationForm.getDropDownColumnvalues().setPlaceList19(places19);
				}
			}
			if (callableStatement.getMoreResults()) {

				resultSetPlace20 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList20 = new ArrayList<>();
				while (resultSetPlace20.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace20.getInt("ID"));
					bean.setItemCd(resultSetPlace20.getString("ITEM_CD"));
					bean.setCoolingMethod(resultSetPlace20.getString("COOLING_METHOD"));
					bean.setTempRaise(resultSetPlace20.getString("TEMP_RAISE"));
					bean.setMinPower(resultSetPlace20.getFloat("MIN_POWER"));
					bean.setMaxPower(resultSetPlace20.getFloat("MAX_POWER"));
					bean.setTurbOilCooler(resultSetPlace20.getString("TURB_OIL_COOLER"));
					bean.setGeneratorCooler(resultSetPlace20.getString("GENERATOR_COOLER"));
					bean.setGlandVendCond(resultSetPlace20.getString("GLAND_VEND_COND"));
					if (resultSetPlace20.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList20.add(bean);
					/*
					 * logger.info("placeList20 start");
					 * logger.info(resultSetPlace20.getInt("ID"));
					 * logger.info(resultSetPlace20.getInt("ITEM_CD"));
					 * logger.info(resultSetPlace20.getInt("COOLING_METHOD"));
					 * logger.info(resultSetPlace20.getInt("TEMP_RAISE"));
					 * logger.info(resultSetPlace20.getInt("MIN_POWER"));
					 * logger.info(resultSetPlace20.getInt("MAX_POWER"));
					 * logger.info(resultSetPlace20.getInt("TURB_OIL_COOLER"));
					 * logger.info(resultSetPlace20.getInt("GENERATOR_COOLER"));
					 * logger.info(resultSetPlace20.getInt("GLAND_VEND_COND"));
					 * logger.info("placeList20 end"); for(int
					 * i=0;i<placeList20.size();i++){
					 * logger.info(placeList20.get(i)); }
					 */
				}
				if (!placeList20.isEmpty()) {
					logger.info("placeList20 is not empty");
					places20.put("placeList20", placeList20);
					quotationForm.getDropDownColumnvalues().setPlaceList20(places20);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace21 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList21 = new ArrayList<>();
				while (resultSetPlace21.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setId(resultSetPlace21.getInt("ID"));
					bean.setTurbineCode(resultSetPlace21.getString("TURB_CD"));
					bean.setTurbineDesign(resultSetPlace21.getString("TURB_DESN"));
					bean.setMinPower(resultSetPlace21.getFloat("MIN_POWER"));
					bean.setMaxPower(resultSetPlace21.getFloat("MAX_POWER"));
					bean.setNoOfMonths(resultSetPlace21.getInt("NO_OF_MONTHS"));
					if (resultSetPlace21.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList21.add(bean);

				}
				if (!placeList21.isEmpty()) {
					places21.put("placeList21", placeList21);
					quotationForm.getDropDownColumnvalues().setPlaceList21(places21);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace12 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList12 = new ArrayList<>();
				while (resultSetPlace12.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setFramePowerId(resultSetPlace12.getInt("FRM_POW_ID"));
					bean.setFrmName(resultSetPlace12.getString("FRM_NM"));
					bean.setF2fMastId(resultSetPlace12.getInt("F2F_MAST_ID"));
					bean.setBleedTypeId(resultSetPlace12.getInt("BLEED_TYP_ID"));
					bean.setBleedTypeName(resultSetPlace12.getString("BLEED_TYP_NM"));
					// bean.setPrice(resultSetPlace12.getFloat("PRICE"));
					bean.setMaxPower(resultSetPlace12.getFloat("MAX_POWER"));
					bean.setTotalPrice(resultSetPlace12.getFloat("TOTAL_PRICE"));
					bean.setSubContrCost(resultSetPlace12.getFloat("SUB_CONTR_COST"));
					bean.setShopConvCost(resultSetPlace12.getFloat("SHOP_CONV_COST"));
					bean.setTotalFTFCost(resultSetPlace12.getFloat("TOTAL_FTF_COST"));
					if (resultSetPlace12.getInt("IS_ACTIVE") == 1) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}
					placeList12.add(bean);
					/*
					 * logger.info("placelist 12 start");
					 * logger.info(resultSetPlace12.getInt("FRM_POW_ID"));
					 * logger.info(resultSetPlace12.getString("FRM_NM"));
					 * logger.info(resultSetPlace12.getInt("BLEED_TYP_ID"));
					 * logger.info(resultSetPlace12.getString("BLEED_TYP_NM"));
					 * logger.info(resultSetPlace12.getFloat("TOTAL_PRICE"));
					 * logger.info(resultSetPlace12.getFloat("SUB_CONTR_COST"));
					 * logger.info(resultSetPlace12.getFloat("SHOP_CONV_COST"));
					 * logger.info(resultSetPlace12.getFloat("TOTAL_FTF_COST"));
					 * logger.info(resultSetPlace12.getInt("IS_ACTIVE"));
					 * logger.info("placelist 12 end");
					 */
				}
				if (!placeList12.isEmpty()) {
					places12.put("placeList12", placeList12);
					quotationForm.getDropDownColumnvalues().setPlaceList12(places12);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetPlace22 = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList22 = new ArrayList<>();
				while (resultSetPlace22.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setFrmName(resultSetPlace22.getString("FRM_NM"));
					bean.setFrameId(resultSetPlace22.getInt("FRM_ID"));
					bean.setTurbineDesign(resultSetPlace22.getString("TURB_DESN"));
					bean.setTurbineCode(resultSetPlace22.getString("TURB_CD"));
					bean.setMaxPower(resultSetPlace22.getFloat("MAXPOWER"));
					bean.setImprovedImpId(resultSetPlace22.getInt("IMPROVED_IMP_ID"));
					bean.setCondTypeId(resultSetPlace22.getInt("COND_TYP_ID"));
					bean.setCondTypeName(resultSetPlace22.getString("COND_TYP_NM"));
					// bean.setBleedTypeId(resultSetPlace22.getInt("BLEED_TYP_ID"));
					// bean.setBleedTypeName(resultSetPlace22.getString("BLEED_TYP_NM"));
					bean.setVariantTypeId(resultSetPlace22.getInt("VARIANT_TYP_ID"));
					bean.setVariantTypeName(resultSetPlace22.getString("VARIANT_TYP_NM"));

					placeList22.add(bean);
				}
				if (!placeList22.isEmpty()) {
					places22.put("placeList22", placeList22);
					quotationForm.getDropDownColumnvalues().setPlaceList22(places22);
				}
			}

		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetDepartments);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRegions);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRoles);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUserInfo);

			UtilityMethods.closeResource(connection, callableStatement, resultSetFrame);
			UtilityMethods.closeResource(connection, callableStatement, resultSetFramepower);
			UtilityMethods.closeResource(connection, callableStatement, resultSetOrientationList);
			UtilityMethods.closeResource(connection, callableStatement, resultsetCategory);
			UtilityMethods.closeResource(connection, callableStatement, resultsetMaterial);

			UtilityMethods.closeResource(connection, callableStatement, resultSetCurrency);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOMechBean);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOElecBean);
			UtilityMethods.closeResource(connection, callableStatement, resultSetVehiclesList);

			UtilityMethods.closeResource(connection, callableStatement, resultSetcompType);
			UtilityMethods.closeResource(connection, callableStatement, resultSetPlace);

		}

		return quotationForm;

	}

	@Override
	public AdminForm AddOrEditFrame(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_ADMIN_FRAMES_POWER(?,?,?,?,?,?,?,?,?,?) }");

			callableStatement.setInt("FRM_ID", adminForm.getFrameWithPowerDetails().getFrameId());
			callableStatement.setInt("FRM_POW_ID", adminForm.getFrameWithPowerDetails().getFramePowerId());
			callableStatement.setString("FRM_NM", adminForm.getFrameWithPowerDetails().getFrameDesc());
			callableStatement.setString("TURB_DESN", adminForm.getFrameWithPowerDetails().getTurbineDesignCd());
			callableStatement.setString("TURB_CD", adminForm.getFrameWithPowerDetails().getTurbineCode());
			callableStatement.setFloat("MIN_POWER", adminForm.getFrameWithPowerDetails().getMinPower());
			callableStatement.setFloat("MAX_POWER", adminForm.getFrameWithPowerDetails().getMaxPower());
			callableStatement.setInt("IS_ACTIVE", adminForm.getFrameWithPowerDetails().isFrameActive() ? 1 : 0);
			callableStatement.setInt("IS_POW_ACTIVE",
					adminForm.getFrameWithPowerDetails().isFramePowerActive() ? 1 : 0);
			callableStatement.setInt("MOD_BY", adminForm.getModifiedBy());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return adminForm;
	}

	@Override
	public AdminForm getAllF2FQues(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuestions = null;
		List<QuestionsEntity> questionsEntityList = new ArrayList<>();

		// UserList
		Map<Integer, QuestionsEntity> quesMap = new HashMap<>();
		Map<Integer, QuestionsEntity> questions = new HashMap<>();
		List<QuestionsEntity> quesVal = new ArrayList<>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ALL_F2F_QUST(?,?)}");
			callableStatement.setString("TURB_DESN", adminForm.getTurbineDesign());
			callableStatement.setString("TURB_CD", adminForm.getTypeOfTurbine());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetQuestions = callableStatement.getResultSet();
					while (resultSetQuestions.next()) {
						QuestionsEntity bean = new QuestionsEntity();

						bean.setQuestionId(resultSetQuestions.getInt("QUST_ID"));
						bean.setQuestionDesc(resultSetQuestions.getString("QUST_DESC"));
						bean.setQuestionCode(resultSetQuestions.getString("QUST_CD"));
						bean.setAnswerId(resultSetQuestions.getInt("ANS_ID"));
						bean.setAnswerCd(resultSetQuestions.getString("ANS_CD"));
						bean.setAnswerDesc(resultSetQuestions.getString("DEFAULT_ANSWER"));
						bean.setQuestionAnswerId(resultSetQuestions.getInt("QUST_ANS_ID"));
						bean.setFrameName(resultSetQuestions.getString("FRAME_NAME"));
						bean.setFramePowerId(resultSetQuestions.getInt("FRAME_POW_ID"));
						if (resultSetQuestions.getString("DEFLT_VALUE").equalsIgnoreCase("TRUE")) {
							bean.setDefaultVal(true);
						} else {
							bean.setDefaultVal(false);
						}
						questionsEntityList.add(bean);
					}
				}
			}
			for (QuestionsEntity ques : questionsEntityList) {
				quesMap.put(ques.getQuestionId(), ques);
			}

			for (int uniqUser : quesMap.keySet()) {
				for (QuestionsEntity user : questionsEntityList) {
					if (uniqUser == user.getQuestionId()) {
						questions.put(user.getAnswerId(), user);

					}
				}
			}
			for (int uniqUser1 : questions.keySet()) {

				for (QuestionsEntity user : questions.values()) {
					if (uniqUser1 == user.getAnswerId()) {
						quesVal.add(user);

					}
				}

			}

			adminForm.setQuestionsEntityList(quesVal);

			if (!questionsEntityList.isEmpty()) {
				createQuestionsJson(questionsEntityList, adminForm);

			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuestions);
		}
		return adminForm;
	}

	private void createQuestionsJson(List<QuestionsEntity> questionsEntityList, AdminForm adminForm) {

		// unqiue Drop down Name
		Map<Integer, String> dropdownNameMap = new HashMap<>();
		for (QuestionsEntity bean : questionsEntityList) {
			dropdownNameMap.put(bean.getQuestionId(), bean.getQuestionDesc());
		}

		List<QuestionsBean> questionsBeanList = new ArrayList<>();
		for (Entry<Integer, String> dropDownType : dropdownNameMap.entrySet()) {

			QuestionsBean questionsBean = new QuestionsBean();
			questionsBean.getDropDownType().setKey(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());
			for (QuestionsEntity ques : questionsEntityList) {
				if (dropDownType.getKey() == ques.getQuestionId()) {
					questionsBean.getDropDownType().setCode(ques.getQuestionCode());
				}
			}
			adminForm.getQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());

			questionsBeanList.add(questionsBean);
		}

		adminForm.setQuestionsBean(questionsBeanList);

		for (QuestionsBean dropDownType : adminForm.getQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			selectBoxList.clear();
			Map<Integer, SelectBox> selectBoxMap = new HashMap<>();
			selectBoxMap.clear();
			for (QuestionsEntity bean : questionsEntityList) {

				if (dropDownType.getDropDownType().getKey() == bean.getQuestionId()) {
					SelectBox box = new SelectBox();
					box.setKey(bean.getAnswerId());
					box.setValue(bean.getAnswerDesc());
					box.setCode(bean.getAnswerCd());
					box.setQuesAnswerkey(bean.getQuestionAnswerId());
					box.setDefaultVal(bean.isDefaultVal());
					box.setQuesKey(bean.getQuestionId());
					box.setQuesDesc(bean.getQuestionDesc());
					selectBoxMap.put(box.getKey(), box);
				}

				dropDownType.setDropDownAnsValueList(selectBoxMap);
			}
		}

		adminForm.setQuestionAnswerSet(questionsEntityList);
	}

	@Override
	public AdminForm addOrEditQuesAnswer(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable F2F_QUST_ANS = new SQLServerDataTable();
			F2F_QUST_ANS = getF2FQuesTable(adminForm);

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_F2F_QUST_ADMIN(?,?,?,?,?)}");

			callableStatement.setStructured(1, "dbo.F2F_QUST_ANS", F2F_QUST_ANS);
			callableStatement.setString(2, adminForm.getQuesDesc()); // EDIT_QUST_NM
			callableStatement.setString(3, adminForm.getQuesCode()); // EDIT_QUST_CD
			callableStatement.setInt(4, adminForm.getQuesKey()); // EDIT_QUST_ID
			callableStatement.setInt(5, adminForm.getModifiedBy()); // MOD_BY

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return adminForm;
	}

	@Override
	public AdminForm addOrEditVehicle(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
			QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (TransportationDetailsBean bean : adminForm.getTransportDetailsList()) {

				QUOT_ADMIN.addRow(bean.getVehicleId(), bean.getVehicleName(), bean.getVehicleCode(),
						bean.getDimension(), bean.getLength(), null, bean.isActive() ? 1 : 0);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_VEHICLE (?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return adminForm;
	}

	@Override
	public AdminForm addOrEditTransportPlace(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
			QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (TransportationDetailsBean bean : adminForm.getTransportDetailsList()) {

				QUOT_ADMIN.addRow(bean.getPlaceId(), bean.getFOBPlace(), bean.getFOBPlaceCode(), null, null, null,
						bean.isActive() ? 1 : 0);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_PLACE (?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return adminForm;
	}

	@Override
	public AdminForm addOrEditTransComponentwithPlace(AdminForm adminForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
			QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (TransportationDetailsBean bean : adminForm.getTransportDetailsList()) {

				QUOT_ADMIN.addRow(bean.getCompoId(), bean.getCompoName(), bean.getCompoCode(), bean.getTurbineCode(),
						bean.getPlaceId(), bean.getSsId(), bean.isActive() ? 1 : 0);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_ADMIN_COMP (?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
			callableStatement.setInt(2, adminForm.getModifiedBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return adminForm;
	}

	@Override
	public AdminForm getQuestionsPage(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		List<QuestionsEntity> questionsEntityList = new ArrayList<>();
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuestions = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_QUST (?,?) }");
			callableStatement.setInt(1, adminForm.getFramePowerId()); // Frame

			callableStatement.setInt(2, adminForm.getSaveBasicDetails().getQuotId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetQuestions = callableStatement.getResultSet();
					while (resultSetQuestions.next()) {
						QuestionsEntity bean = new QuestionsEntity();

						bean.setQuestionId(resultSetQuestions.getInt("QUST_ID"));
						bean.setQuestionDesc(resultSetQuestions.getString("QUST_DESC"));
						bean.setQuestionCode(resultSetQuestions.getString("QUST_CD"));
						bean.setAnswerId(resultSetQuestions.getInt("ANS_ID"));
						bean.setAnswerCd(resultSetQuestions.getString("ANS_CD"));
						bean.setAnswerDesc(resultSetQuestions.getString("DEFAULT_ANSWER"));
						bean.setQuestionAnswerId(resultSetQuestions.getInt("QUST_ANS_ID"));
						bean.setFrameName(resultSetQuestions.getString("FRAME_NAME"));
						if (resultSetQuestions.getString("DEFLT_VALUE").equalsIgnoreCase("TRUE")) {
							bean.setDefaultVal(true);
						} else {
							bean.setDefaultVal(false);
						}
						questionsEntityList.add(bean);
					}
				}
			}

			adminForm.setQuestionsEntityList(questionsEntityList);
			if (!questionsEntityList.isEmpty()) {
				createQuestionsJson(questionsEntityList, adminForm);
				// Added for UI
				List<SaveQuesDetails> tempList = new ArrayList<SaveQuesDetails>();
				for (int i = 1; i <= 10; i++) {
					SaveQuesDetails saveQues = new SaveQuesDetails();
					tempList.add(saveQues);
				}
				adminForm.setSaveQuesDetailsList(tempList);

			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuestions);

		}
		return adminForm;
	}

	@Override
	public AdminForm getNewFramesForQuestions(AdminForm adminForm) {

		try {

			String query = "select a.frm_id,b.FRAME_POW_ID, a.FRM_NM, a.TURB_CD, a.TURB_DESN,a.IS_ACTIVE,b.MAX_POWER from (SELECT *  from FRAMES ) a , (SELECT * FROM FRAMES_MAST A WHERE FRAME_POW_ID NOT IN(SELECT FRM_ID FROM F2F_TURB_QUST_ANS))  b where a.FRM_ID=b.FRM_ID";

			List<TurbineDetails> newFramePowerList = jdbcTemplate.query(query, new RowMapper<TurbineDetails>() {

				public TurbineDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setFramePowerId(rs.getInt("FRAME_POW_ID"));
					turbineDetails.setFrameId(rs.getInt("FRM_ID"));
					turbineDetails.setTurbineCode(rs.getString("TURB_CD"));
					turbineDetails.setTurbineDesignCd(rs.getString("TURB_DESN"));
					if (turbineDetails.getTurbineCode().equals(ItoConstants.BP)) {
						turbineDetails.setTurbType(ItoConstants.BACKPRESSURE);
					} else if (turbineDetails.getTurbineCode().equals(ItoConstants.CD)) {
						turbineDetails.setTurbType(ItoConstants.CONDENSING);
					}
					if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase(ItoConstants.IM)) {
						turbineDetails.setTurbdesignName(ItoConstants.IMPULSE);
					} else if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase(ItoConstants.RN)) {
						turbineDetails.setTurbdesignName(ItoConstants.REACTION);
					}
					turbineDetails.setFrameDesc(rs.getString("FRM_NM"));
					turbineDetails.setPower(rs.getFloat("MAX_POWER"));

					turbineDetails.setFramePowerDesc(rs.getString("FRM_NM") + " - " + rs.getFloat("MAX_POWER") + " MW");

					if (rs.getInt("IS_ACTIVE") == 1) {
						turbineDetails.setFrameActive(true);
					} else {
						turbineDetails.setFrameActive(false);
					}
					return turbineDetails;
				}
			});

			adminForm.setNewFrameWithPowersList(newFramePowerList);

		} catch (Exception e) {
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);

		}
		return adminForm;
	}

	@Override
	public AdminForm getOnlyOldFramesForQuestions(AdminForm adminForm) {

		try {

			String query = "SELECT A.FRM_ID,B.FRAME_POW_ID, A.FRM_NM, A.TURB_CD, A.TURB_DESN,A.IS_ACTIVE,B.MAX_POWER FROM (SELECT *  FROM FRAMES ) A , ((SELECT * FROM FRAMES_MAST A WHERE FRAME_POW_ID IN(SELECT FRM_ID FROM F2F_TURB_QUST_ANS)))  B WHERE A.FRM_ID=B.FRM_ID";

			List<TurbineDetails> onlyOldFramePowerList = jdbcTemplate.query(query, new RowMapper<TurbineDetails>() {

				public TurbineDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setFramePowerId(rs.getInt("FRAME_POW_ID"));
					turbineDetails.setFrameId(rs.getInt("FRM_ID"));
					turbineDetails.setTurbineCode(rs.getString("TURB_CD"));
					turbineDetails.setTurbineDesignCd(rs.getString("TURB_DESN"));
					if (turbineDetails.getTurbineCode().equals(ItoConstants.BP)) {
						turbineDetails.setTurbType(ItoConstants.BACKPRESSURE);
					} else if (turbineDetails.getTurbineCode().equals(ItoConstants.CD)) {
						turbineDetails.setTurbType(ItoConstants.CONDENSING);
					}
					if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase(ItoConstants.IM)) {
						turbineDetails.setTurbdesignName(ItoConstants.IMPULSE);
					} else if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase(ItoConstants.RN)) {
						turbineDetails.setTurbdesignName(ItoConstants.REACTION);
					}
					turbineDetails.setFrameDesc(rs.getString("FRM_NM"));
					turbineDetails.setPower(rs.getFloat("MAX_POWER"));
					turbineDetails.setFramePowerDesc(rs.getString("FRM_NM") + " - " + rs.getFloat("MAX_POWER") + " MW");

					if (rs.getInt("IS_ACTIVE") == 1) {
						turbineDetails.setFrameActive(true);
					} else {
						turbineDetails.setFrameActive(false);
					}
					return turbineDetails;
				}
			});

			adminForm.setFrameWithPowersList(onlyOldFramePowerList);

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}

		return adminForm;
	}

	/*
	 * @Override public AdminForm editF2FTurbineInstruments(AdminForm adminForm)
	 * {
	 * 
	 * SQLServerPreparedStatement callableStatement = null; Connection
	 * connection = null; int resultOutParameterInt = -1; String
	 * resultOutParameterString = null; ResultSet resultSetMsg = null; ResultSet
	 * resultSetAssignedUser = null;
	 * 
	 * try { connection = jdbcTemplate.getDataSource().getConnection();
	 * 
	 * SQLServerDataTable QUOT_ADMIN = new SQLServerDataTable();
	 * QUOT_ADMIN.addColumnMetadata("ID", java.sql.Types.NUMERIC);
	 * QUOT_ADMIN.addColumnMetadata("NAME", java.sql.Types.VARCHAR);
	 * QUOT_ADMIN.addColumnMetadata("CD", java.sql.Types.NUMERIC);
	 * QUOT_ADMIN.addColumnMetadata("TURB_CD", java.sql.Types.NUMERIC);
	 * QUOT_ADMIN.addColumnMetadata("FOB_ID", java.sql.Types.NUMERIC);
	 * QUOT_ADMIN.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
	 * QUOT_ADMIN.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);
	 * 
	 * for (TurbineDetails bean : adminForm.getTurbineInstrumentsList()) {
	 * 
	 * QUOT_ADMIN.addRow(bean.getInstrId(), null, bean.getFramePowerId(),
	 * bean.getPercentIncr(), bean.getBleedTypId(), bean.getCondTypId(),
	 * (bean.isActive() ? 1 : 0));
	 * 
	 * }
	 * 
	 * callableStatement = (SQLServerPreparedStatement) connection
	 * .prepareStatement("{ call dbo.PROC_ADMIN_TI_PERCNT_INCR (?,?)}");
	 * callableStatement.setStructured(1, "dbo.QUOT_ADMIN ", QUOT_ADMIN);
	 * callableStatement.setInt(2, adminForm.getModifiedBy());
	 * 
	 * callableStatement.execute(); resultSetMsg =
	 * callableStatement.getResultSet();
	 * 
	 * while (resultSetMsg.next()) { resultOutParameterInt =
	 * resultSetMsg.getInt(1); resultOutParameterString =
	 * resultSetMsg.getString(2);
	 * adminForm.setSuccessCode(resultOutParameterInt);
	 * adminForm.setSuccessMsg(resultOutParameterString);
	 * adminForm.getMsgToUser().put(resultOutParameterInt,
	 * resultOutParameterString); }
	 * 
	 * } catch (Exception e) { adminForm.getMsgToUser().put(-1,
	 * TECHNICAL_EXCEPTION); logger.error("Exception : ", e); } finally {
	 * UtilityMethods.closeResource(connection, callableStatement,
	 * resultSetMsg); UtilityMethods.closeResource(connection,
	 * callableStatement, resultSetAssignedUser); } return adminForm; }
	 */
	@Override
	public QuotationForm getStatesList() {
		QuotationForm quotationForm = new QuotationForm();
		List<LocationBean> stateList = new ArrayList<>();
		Map<String, List<LocationBean>> stateMap = new HashMap<>();
		try {

			String query = "SELECT STATE_ID,STATE_NM FROM STATES WHERE IS_ACTIVE = 1 ORDER BY  STATE_NM;";

			stateList = jdbcTemplate.query(query, new RowMapper<LocationBean>() {

				public LocationBean mapRow(ResultSet resultSetStateList, int rowNum) throws SQLException {
					UserProfileDetails userDetails = new UserProfileDetails();

					LocationBean bean = new LocationBean();
					bean.setLocationId(resultSetStateList.getInt(ItoConstants.STATE_ID));
					bean.setLocationName(resultSetStateList.getString("STATE_NM"));

					return bean;
				}
			});
			stateMap.put("stateList", stateList);
			quotationForm.getDropDownColumnvalues().setStateList(stateMap);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}

		return quotationForm;

	}

	@Override
	public AdminForm getUserManual(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;
		ResultSet resultSetdata = null;
		List<AdminForm> AdminFormlist = new ArrayList<AdminForm>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_CREATE_UTILITIES(?,?,?,?,?,?) }");

			callableStatement.setInt("ITEM_ID", adminForm.getItemId());
			callableStatement.setString("ITEM_CD", adminForm.getItemCd());
			callableStatement.setString("ITEM_NM", adminForm.getItemNm());
			callableStatement.setString("ITEM_DESC", adminForm.getItemDesc());
			if (adminForm.getItem() != null) {
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] imageByte = decoder.decodeBuffer(adminForm.getItem());
				callableStatement.setBytes("ITEM", imageByte);
			} else {
				callableStatement.setBinaryStream("ITEM", null);
			}
			callableStatement.setInt("MOD_BY", adminForm.getModifiedBy());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetdata = callableStatement.getResultSet();

				while (resultSetdata.next()) {
					AdminForm bean = new AdminForm();
					bean.setItemId(resultSetdata.getInt("ITEM_ID"));
					bean.setItemCd(resultSetdata.getString("ITEM_CD"));
					bean.setItemNm(resultSetdata.getString("ITEM_NM"));
					bean.setItemDesc(resultSetdata.getString("ITEM_DESC"));
					if (!(resultSetdata.getBytes("ITEM") == null)) {
						String encoded = Base64.getEncoder().encodeToString(resultSetdata.getBytes("ITEM"));
						bean.setItem(encoded);
					}
					bean.setCreatedDate(resultSetdata.getString("CREAT_DT"));
					bean.setModifyDate(resultSetdata.getString("MOD_DT"));
					bean.setCreatedBy(resultSetdata.getString("CREAT_BY"));
					bean.setModifyByNm(resultSetdata.getString("MOD_BY_NM"));
					bean.setModifyBy(resultSetdata.getInt("MOD_BY"));

					if (resultSetdata.getString("IS_ACTIVE").equals("1")) {
						bean.setActive(true);
					} else {
						bean.setActive(false);
					}

					AdminFormlist.add(bean);
				}
			}
			adminForm.setAdminFormlist(AdminFormlist);

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetdata);
		}
		return adminForm;
	}

	@Override
	public AdminForm downloadFile(Integer itemId) {
		AdminForm adminForm = new AdminForm();
		try {

			// UAT & PROD
			String query = "SELECT ITEM,SUBSTRING(RIGHT(FILE_PATH,5),CHARINDEX( '.',RIGHT(FILE_PATH,5)) + 1,4) AS EXTN  FROM TEST..QUOT_ATTACHMENTS WHERE ITEM_ID ='" 
					+ itemId + "';";
			// DEV
			 // String query = "SELECT ITEM,SUBSTRING(RIGHT(FILE_PATH,5),CHARINDEX( '.',RIGHT(FILE_PATH,5)) + 1,4) AS EXTN FROM TEST..QUOT_ATTACHMENTS_DEV WHERE ITEM_ID ='" + itemId + "';";
			List<AdminForm> data = jdbcTemplate.query(query, new RowMapper<AdminForm>() {
				public AdminForm mapRow(ResultSet rs, int rowNum) throws SQLException {
					AdminForm bean = new AdminForm();
					if (!(rs.getBytes("ITEM") == null)) {
						String encoded = Base64.getEncoder().encodeToString(rs.getBytes("ITEM"));
						bean.setItem(encoded);
					}
					bean.setExtension(rs.getString("EXTN"));

					return bean;
				}
			});

			logger.info("data " + data);
			data.get(0).setSuccessCode(0);
			data.get(0).setSuccessMsg("SUCCESS");
			return data.get(0);

		} catch (Exception e) {

			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return adminForm;
		}

	}

	@Override
	public AdminForm getAuditXml(String tableName) {
		AdminForm adminForm = new AdminForm();
		try {

			String query = "SELECT AUDITID,AUDITENTRY FROM AUDITALL WHERE TABLENAME  ='" + tableName + "';";
			List<AdminForm> auditXmlList = jdbcTemplate.query(query, new RowMapper<AdminForm>() {
				public AdminForm mapRow(ResultSet rs, int rowNum) throws SQLException {

					AdminForm bean = new AdminForm();

					bean.setAuditId(rs.getInt("AUDITID"));
					java.sql.SQLXML xml = rs.getSQLXML("AUDITENTRY");
					bean.setAuditEntry((xml.getString()));

					return bean;
				}
			});

			adminForm.setAuditXmllist(auditXmlList);

			return adminForm;

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return adminForm;
		}

	}

	@Override
	public AdminForm getAuditDate(String tableName) {
		AdminForm adminForm = new AdminForm();
		try {

			String query = "SELECT AUDITID,CREATE_DT,MOD_BY FROM AUDITALL WHERE TABLENAME ='" + tableName + "';";

			List<AdminForm> auditDateList = jdbcTemplate.query(query, new RowMapper<AdminForm>() {
				public AdminForm mapRow(ResultSet rs, int rowNum) throws SQLException {

					AdminForm bean = new AdminForm();
					bean.setAuditId(rs.getInt("AUDITID"));
					bean.setCreatedDate(rs.getString("CREATE_DT"));
					bean.setModifyBy(rs.getInt("MOD_BY"));

					return bean;
				}
			});

			adminForm.setAuditDatelist(auditDateList);

			return adminForm;

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return adminForm;
		}

	}

	@Override
	public AdminForm getAuditHistoryDetails(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsg1 = null;
		ResultSet resultSetDatam = null;
		ResultSet resultSetData = null;
		List<DBOBean> auditHistoryList = new ArrayList<DBOBean>();
		List<DBOBean> nameList = new ArrayList<DBOBean>();
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_AUDIT_HISTORY_DETAILS (?) }");
			callableStatement.setString("TABLE_NM", adminForm.getTableName());
			logger.info(adminForm.getTableName());
			callableStatement.execute();
			logger.info("audit");
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {

				resultSetMsg1 = callableStatement.getResultSet();
				logger.info("outside while");
				while (resultSetMsg1.next()) {
					logger.info("inside while");
					if (adminForm.getTableName().equalsIgnoreCase("CURRENCY_BKP")) {
						logger.info("inside while if");
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setCur_id(resultSetMsg1.getInt("CUR_ID"));
						dboBean.setCur_nm(resultSetMsg1.getString("CUR_NM"));
						dboBean.setCur_cd(resultSetMsg1.getString("CUR_CD"));
						logger.info(dboBean.getCurCd());
						dboBean.setComr_text_a(resultSetMsg1.getString("COMR_TEXT_A"));
						dboBean.setComr_text_d(resultSetMsg1.getString("COMR_TEXT_D"));
						dboBean.setComr_text_e(resultSetMsg1.getString("COMR_TEXT_E"));
						dboBean.setConversion_rate(resultSetMsg1.getString("CONVERTION_RATE"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);

					}
					if (adminForm.getTableName().equalsIgnoreCase("ELE_ADDON_COST_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setPrice_id(resultSetMsg1.getInt("PRICE_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setType_of_panel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setCust_type(resultSetMsg1.getString("CUST_TYPE"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setCol_id(resultSetMsg1.getInt("COL_ID"));
						dboBean.setCol_val_cd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setSub_col_val_cd(resultSetMsg1.getString("SUB_COL_VAL_CD"));
						dboBean.setAddon_cost_col(resultSetMsg1.getString("ADDON_COST_COL"));
						dboBean.setMin_val(resultSetMsg1.getFloat("MIN_VAL"));
						dboBean.setMax_val(resultSetMsg1.getFloat("MAX_VAL"));
						dboBean.setAddon_dir_cost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
						dboBean.setAddon_cost_per(resultSetMsg1.getFloat("ADDON_COST_PER"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setError_msg(resultSetMsg1.getString("ERROR_MSG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("ELE_PRICE_BKP")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setPrice_id(resultSetMsg1.getInt("PRICE_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setType_of_panel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setCust_type(resultSetMsg1.getString("CUST_TYPE"));
						dboBean.setPrice_code(resultSetMsg1.getString("PRICE_CODE"));
						dboBean.setPrice_15(resultSetMsg1.getFloat("PRICE_15"));
						dboBean.setPrice_30(resultSetMsg1.getFloat("PRICE_30"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setItem_error_msg(resultSetMsg1.getString("ITEM_ERROR_MSG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));

						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("ELE_ITEMS_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setPrice_id(resultSetMsg1.getInt("ELE_ITEM_ID"));
						dboBean.setType_of_panel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setEle_type(resultSetMsg1.getString("ELE_TYPE"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setItem_order(resultSetMsg1.getInt("ITEM_ORDER"));
						dboBean.setItem_appl_ind(resultSetMsg1.getInt("ITEM_APPL_IND"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setSub_item_flg(resultSetMsg1.getInt("SUB_ITEM_FLG"));
						dboBean.setPanel_depend_flg(resultSetMsg1.getInt("PANEL_DEPEND_FLG"));
						dboBean.setLink_flg(resultSetMsg1.getInt("LINK_FLG"));
						dboBean.setDepend_flg(resultSetMsg1.getInt("DEPEND_FLG"));
						dboBean.setTech_flg(resultSetMsg1.getInt("TECH_FLG"));
						dboBean.setComr_flg(resultSetMsg1.getInt("COMR_FLG"));
						dboBean.setIs_enable(resultSetMsg1.getInt("IS_ENABLE"));
						dboBean.setHeader_text(resultSetMsg1.getString("HEADER_TEXT"));
						dboBean.setFooter_text(resultSetMsg1.getString("FOOTER_TEXT"));
						dboBean.setHearer_nm(resultSetMsg1.getString("HEARER_NM"));
						dboBean.setExclusion_nm(resultSetMsg1.getString("EXCLUSION_NM"));
						dboBean.setCust_typ_depend_flg(resultSetMsg1.getInt("CUST_TYP_DEPEND_FLG"));
						dboBean.setDt_frm_flg(resultSetMsg1.getInt("DT_FRM_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("ELE_COL_VAL_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setCol_val_id(resultSetMsg1.getInt("COL_VAL_ID"));
						dboBean.setType_of_panel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setCol_id(resultSetMsg1.getInt("COL_ID"));
						dboBean.setCol_val_cd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setCol_val_nm(resultSetMsg1.getString("COL_VAL_NM"));
						dboBean.setCol_val_cd_sym(resultSetMsg1.getString("COL_VAL_CD_SYM"));
						dboBean.setSub_col_val_flg(resultSetMsg1.getInt("SUB_COL_VAL_FLG"));
						dboBean.setSub_col_val_nm(resultSetMsg1.getString("SUB_COL_VAL_NM"));
						dboBean.setMin_val(resultSetMsg1.getFloat("MIN_VAL"));
						dboBean.setMax_val(resultSetMsg1.getFloat("MAX_VAL"));
						dboBean.setDefault_flg_15(resultSetMsg1.getInt("DEFLT_FLG_15"));
						dboBean.setDefault_flg_30(resultSetMsg1.getInt("DEFLT_FLG_30"));
						dboBean.setOrder_id(resultSetMsg1.getInt("ORDER_ID"));
						dboBean.setAdd_on_flg(resultSetMsg1.getInt("ADD_ON_FLG"));
						dboBean.setAdd_on_diff_ds(resultSetMsg1.getInt("ADD_ON_DIFF_DS"));
						dboBean.setAddon_cost_per(resultSetMsg1.getFloat("ADDON_COST_PER"));
						dboBean.setAddon_dir_cost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setCalc_link_flg(resultSetMsg1.getInt("CALC_LINK_FLG"));
						dboBean.setTech_flg(resultSetMsg1.getInt("TECH_FLG"));
						dboBean.setComr_flg(resultSetMsg1.getInt("COMR_FLG"));
						dboBean.setDisp_ind(resultSetMsg1.getInt("DISP_IND"));
						dboBean.setDisable_col_va_cd(resultSetMsg1.getString("DISABLE_COL_VA_CD"));
						dboBean.setDel_col_flg(resultSetMsg1.getInt("DEL_COL_FLG"));
						dboBean.setInput_cost_flg(resultSetMsg1.getInt("INPUT_COST_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("INSTRUMENT_LIST_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setInstr_id(resultSetMsg1.getInt("INSTR_ID"));
						dboBean.setCategory(resultSetMsg1.getString("CATEGORY"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setType_of_gov(resultSetMsg1.getString("TYPE_OF_GOV"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setTurb_cd(resultSetMsg1.getString("TURB_CD"));
						dboBean.setCond_type_id(resultSetMsg1.getInt("COND_TYP_ID"));
						dboBean.setType_of_instr(resultSetMsg1.getString("TYPE_OF_INSTR"));
						dboBean.setInstr_type_nm(resultSetMsg1.getString("INSTR_TYPE_NM"));
						dboBean.setInstr_desc(resultSetMsg1.getString("INSTR_DESC"));
						dboBean.setInstr_mounting(resultSetMsg1.getString("INSTR_MOUNTING"));
						dboBean.setQty_1001_logic(resultSetMsg1.getInt("QTY_1001_LOGIC"));
						dboBean.setQty_1002_logic(resultSetMsg1.getInt("QTY_1002_LOGIC"));
						dboBean.setQty_2003_logic(resultSetMsg1.getInt("QTY_2003_LOGIC"));
						dboBean.setCost_1001(Math.round(resultSetMsg1.getInt("COST_1001")));
						dboBean.setApprox_1001_flg(resultSetMsg1.getInt("APPROX_1001_FLG"));
						dboBean.setCost_1002(Math.round(resultSetMsg1.getInt("COST_1002")));
						dboBean.setApprox_1002_flg(resultSetMsg1.getInt("APPROX_1002_FLG"));
						dboBean.setCost_2003(Math.round(resultSetMsg1.getInt("COST_2003")));
						dboBean.setApprox_2003_flg(resultSetMsg1.getInt("APPROX_2003_FLG"));
						dboBean.setCost_ce_1001(Math.round(resultSetMsg1.getInt("COST_CE_1001")));
						dboBean.setApprox_1001_ce_flg(resultSetMsg1.getInt("APPROX_1001_CE_FLG"));
						dboBean.setCost_ce_1002(resultSetMsg1.getInt("COST_CE_1002"));
						dboBean.setApprox_1002_ce_flg(resultSetMsg1.getInt("APPROX_1002_CE_FLG"));
						dboBean.setCost_ce_2003(Math.round(resultSetMsg1.getInt("COST_CE_2003")));
						dboBean.setApprox_2003_ce_flg(resultSetMsg1.getInt("APPROX_2003_CE_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));

						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("FRAMES_CAT_BKP")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setId(resultSetMsg1.getInt("ID"));
						dboBean.setFrm_id(resultSetMsg1.getInt("FRM_ID"));
						dboBean.setCategory(resultSetMsg1.getString("CAT"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setType_of_instr(resultSetMsg1.getString("TYPE_OF_INSTR"));
						dboBean.setCost_1001(Math.round(resultSetMsg1.getInt("COST_1001")));
						dboBean.setApprox_1001_flg(resultSetMsg1.getInt("APPROX_1001_FLG"));
						dboBean.setCost_1002(Math.round(resultSetMsg1.getInt("COST_1002")));
						dboBean.setApprox_1002_flg(resultSetMsg1.getInt("APPROX_1002_FLG"));
						dboBean.setCost_2003(Math.round(resultSetMsg1.getInt("COST_2003")));
						dboBean.setApprox_2003_flg(resultSetMsg1.getInt("APPROX_2003_FLG"));
						dboBean.setCost_ce_1001(Math.round(resultSetMsg1.getInt("COST_CE_1001")));
						dboBean.setApprox_1001_ce_flg(resultSetMsg1.getInt("APPROX_1001_CE_FLG"));
						dboBean.setCost_ce_1002(resultSetMsg1.getInt("COST_CE_1002"));
						dboBean.setApprox_1002_ce_flg(resultSetMsg1.getInt("APPROX_1002_CE_FLG"));
						dboBean.setCost_ce_2003(Math.round(resultSetMsg1.getInt("COST_CE_2003")));
						dboBean.setApprox_2003_ce_flg(resultSetMsg1.getInt("APPROX_2003_CE_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						dboBean.setEff_from(resultSetMsg1.getString("EFF_FROM"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("ADD_INSTR_LIST_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setAdd_instr_id(resultSetMsg1.getInt("ADD_INSTR_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setAdd_instr_cd(resultSetMsg1.getString("ADD_INSTR_CD"));
						dboBean.setAdd_instr_nm(resultSetMsg1.getString("ADD_INSTR_NM"));
						dboBean.setCost(Math.round(resultSetMsg1.getInt("COST")));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("VMS_FRM_LIST_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setVms_id(resultSetMsg1.getInt("VMS_ID"));
						dboBean.setType_of_panel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setFrm_id(resultSetMsg1.getInt("FRM_ID"));
						dboBean.setAdd_prb_flg(resultSetMsg1.getInt("ADD_PRB_FLG"));
						dboBean.setType(resultSetMsg1.getString("TYPE"));
						dboBean.setType_deflt_flg(resultSetMsg1.getInt("TYPE_DEFLT_FLG"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setMake_deflt_flg(resultSetMsg1.getInt("MAKE_DEFLT_FLG"));
						dboBean.setAlt_make(resultSetMsg1.getString("ALT_MAKE"));
						dboBean.setAlt_make_deflt_flg(resultSetMsg1.getInt("ALT_MAKE_DEFLT_FLG"));
						dboBean.setGear_box(resultSetMsg1.getString("GEAR_BOX"));
						dboBean.setGear_box_deflt_flg(resultSetMsg1.getInt("GEAR_BOX_DEFLT_FLG"));

						dboBean.setCost(Math.round(resultSetMsg1.getFloat("COST")));
						dboBean.setNote(resultSetMsg1.getString("NOTE"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));

						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("BGM_CALC_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setId(resultSetMsg1.getInt("ID"));
						dboBean.setFrm_pow_id(resultSetMsg1.getInt("FRM_POW_ID"));
						dboBean.setBgm_type(resultSetMsg1.getString("BGM_TYPE"));
						dboBean.setBgm_rating(resultSetMsg1.getFloat("BGM_RATING"));
						dboBean.setDeflt_flg(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));

						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("DCM_POWER_CALC_BKP")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setId(resultSetMsg1.getInt("ID"));
						dboBean.setFrm_pow_id(resultSetMsg1.getInt("FRM_POW_ID"));
						dboBean.setEop_motor_rating(resultSetMsg1.getFloat("EOP_MOTOR_RATING"));
						dboBean.setCost(resultSetMsg1.getFloat("COST"));
						dboBean.setPump_type(resultSetMsg1.getString("PUMP_TYPE"));
						dboBean.setBg_hr_rate(resultSetMsg1.getInt("BG_HR_RATE"));
						dboBean.setDc_output_vol(resultSetMsg1.getFloat("DC_OUTPUT_VOL"));
						dboBean.setDefault_flag(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));

						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("MECH_ADDON_COST_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setPrice_id(resultSetMsg1.getInt("PRICE_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setCol_id(resultSetMsg1.getInt("COL_ID"));
						dboBean.setCol_val_cd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setAddon_cost_col(resultSetMsg1.getString("ADDON_COST_COL"));
						dboBean.setAddon_cost_per(resultSetMsg1.getFloat("ADDON_COST_PER"));
						dboBean.setAddon_dir_cost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("MECH_COL_VAL_BKP")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setCol_val_id(resultSetMsg1.getInt("COL_VAL_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setCol_id(resultSetMsg1.getInt("COL_ID"));
						dboBean.setCol_val_cd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setCol_val_nm(resultSetMsg1.getString("COL_VAL_NM"));
						dboBean.setDeflt_flg(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setDisp_ind(resultSetMsg1.getInt("DISP_IND"));
						dboBean.setOrder_id(resultSetMsg1.getInt("ORDER_ID"));
						dboBean.setMin_val(resultSetMsg1.getFloat("MIN_VAL"));
						dboBean.setMax_val(resultSetMsg1.getFloat("MAX_VAL"));
						dboBean.setComr_flg(resultSetMsg1.getInt("COMR_FLG"));
						dboBean.setTech_flg(resultSetMsg1.getInt("TECH_FLG"));
						dboBean.setCalc_link_flg(resultSetMsg1.getInt("CALC_LINK_FLG"));
						dboBean.setAdd_on_flg(resultSetMsg1.getInt("ADD_ON_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("MECH_PRICE_BKP")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setPrice_id(resultSetMsg1.getInt("PRICE_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setTurb_cd(resultSetMsg1.getString("TURB_CD"));
						dboBean.setPrice_code(resultSetMsg1.getString("PRICE_CODE"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setDeflt_flg(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setTotal_price(resultSetMsg1.getFloat("TOTAL_PRICE"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("MECH_AUX_COL_VAL_BKP")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setCol_val_id(resultSetMsg1.getInt("COL_VAL_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setCol_id(resultSetMsg1.getInt("COL_ID"));
						dboBean.setCol_val_cd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setCol_val_nm(resultSetMsg1.getString("COL_VAL_NM"));
						dboBean.setDeflt_flg(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setDisp_ind(resultSetMsg1.getInt("DISP_IND"));
						dboBean.setOrder_id(resultSetMsg1.getInt("ORDER_ID"));
						dboBean.setComr_flg(resultSetMsg1.getInt("COMR_FLG"));
						dboBean.setTech_flg(resultSetMsg1.getInt("TECH_FLG"));
						dboBean.setAdd_on_flg(resultSetMsg1.getInt("ADD_ON_FLG"));
						dboBean.setAddon_cost_per(resultSetMsg1.getFloat("ADDON_COST_PER"));
						dboBean.setAddon_dir_cost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));

						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("MECH_AUX_PRICE_BKP")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setPrice_id(resultSetMsg1.getInt("PRICE_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setTurb_cd(resultSetMsg1.getString("TURB_CD"));
						dboBean.setPrice_code(resultSetMsg1.getString("PRICE_CODE"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setDeflt_flg(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));

						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("MECH_OVER_TANK_BKP")) {

						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setId(resultSetMsg1.getInt("ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setPower(resultSetMsg1.getString("POWER"));
						dboBean.setMin_val(resultSetMsg1.getFloat("MIN_VAL"));
						dboBean.setMax_val(resultSetMsg1.getFloat("MAX_VAL"));
						dboBean.setQty(resultSetMsg1.getInt("QTY"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setDeflt_flg(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("DEPARTMENT_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setDept_id(resultSetMsg1.getInt("DEPT_ID"));
						dboBean.setDept_cd(resultSetMsg1.getString("DEPT_CD"));
						dboBean.setDept_name(resultSetMsg1.getString("DEPT_NAME"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("EC_MAST_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setEc_id(resultSetMsg1.getInt("EC_ID"));
						dboBean.setFrm_pow_id(resultSetMsg1.getInt("FRM_POW_ID"));
						dboBean.setCond_typ_id(resultSetMsg1.getInt("COND_TYP_ID"));
						dboBean.setTyp_of_charge(resultSetMsg1.getString("TYP_OF_CHARGE"));
						dboBean.setLoading_id(resultSetMsg1.getInt("LOADING_ID"));
						dboBean.setLoadging_id(resultSetMsg1.getInt("LOADGING_ID"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setAssigned_to(resultSetMsg1.getString("ASSIGNED_TO"));
						dboBean.setStatus_id(resultSetMsg1.getInt("STATUS_ID"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						dboBean.setPrev_percent(resultSetMsg1.getFloat("PREV_PERCENT"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("F2F_COL_VAL_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setCol_val_id(resultSetMsg1.getInt("COL_VAL_ID"));
						dboBean.setCategory(resultSetMsg1.getString("CATEGORY"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setSub_item_typ_id(resultSetMsg1.getInt("SUB_ITEM_TYP_ID"));
						dboBean.setCol_id(resultSetMsg1.getInt("COL_ID"));
						dboBean.setCol_val_cd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setCol_val_nm(resultSetMsg1.getString("COL_VAL_NM"));
						dboBean.setCalc_link_flg(resultSetMsg1.getInt("CALC_LINK_FLG"));
						dboBean.setDeflt_flg(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setDisp_ind(resultSetMsg1.getInt("DISP_IND"));
						dboBean.setOrder_id(resultSetMsg1.getInt("ORDER_ID"));
						dboBean.setAdd_on_flg(resultSetMsg1.getInt("ADD_ON_FLG"));
						dboBean.setAddon_cost_per(resultSetMsg1.getFloat("ADDON_COST_PER"));
						dboBean.setAddon_dir_cost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
						dboBean.setApprox_cost_flg(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setTech_flg(resultSetMsg1.getInt("TECH_FLG"));
						dboBean.setComr_flg(resultSetMsg1.getInt("COMR_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("F2F_FRM_SPEC_DATA_BKP")) {

						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setId(resultSetMsg1.getInt("ID"));
						dboBean.setFrm_id(resultSetMsg1.getInt("FRM_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setSub_item_typ_id(resultSetMsg1.getInt("SUB_ITEM_TYP_ID"));
						dboBean.setCol_id(resultSetMsg1.getInt("COL_ID"));
						dboBean.setCol_val_cd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setMin_val(resultSetMsg1.getFloat("MIN_VAL"));
						dboBean.setMax_val(resultSetMsg1.getFloat("MAX_VAL"));
						dboBean.setCost(resultSetMsg1.getFloat("COST"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("F2F_PRICE_BKP")) {

						DBOBean dboBean = new DBOBean();

						/*
						 * dboBean.setAction_type(resultSetMsg1.getString(
						 * "ACTION_TYPE"));
						 * dboBean.setAction_row(resultSetMsg1.getString(
						 * "ACTION_ROW"));
						 * dboBean.setId(resultSetMsg1.getInt("ID"));
						 * dboBean.setFrm_id(resultSetMsg1.getInt("FRM_ID"));
						 * dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						 * dboBean.setSub_item_id(resultSetMsg1.getInt(
						 * "SUB_ITEM_ID"));
						 * dboBean.setSub_item_typ_id(resultSetMsg1.getInt(
						 * "SUB_ITEM_TYP_ID"));
						 * dboBean.setCol_id(resultSetMsg1.getInt("COL_ID"));
						 * dboBean.setCol_val_cd(resultSetMsg1.getString(
						 * "COL_VAL_CD"));
						 * dboBean.setMin_val(resultSetMsg1.getFloat("MIN_VAL"))
						 * ;
						 * dboBean.setMax_val(resultSetMsg1.getFloat("MAX_VAL"))
						 * ; dboBean.setCreat_dt(resultSetMsg1.getDate(
						 * ItoConstants.CREAT_DT));
						 * dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.
						 * MOD_DT));
						 * dboBean.setCreat_by(resultSetMsg1.getString(
						 * "CREAT_BY"));
						 * dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						 * dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"
						 * ));
						 */
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setSub_item_id(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setSub_item_typ_id(resultSetMsg1.getInt("SUB_ITEM_TYP_ID"));
						dboBean.setFramePowerId(resultSetMsg1.getInt("FRM_POW_ID"));
						dboBean.setBleedTypeId(resultSetMsg1.getInt("BLEED_TYP_ID"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setTotalPrice(resultSetMsg1.getFloat("TOTAL_PRICE"));
						dboBean.setDefault_flag(resultSetMsg1.getInt("DEFLT_FLG"));
						dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("F2F_UBO_DET_BKP")) {

						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setId(resultSetMsg1.getInt("F2F_DET_ID"));
						dboBean.setFrm_id(resultSetMsg1.getInt("F2F_MAST_ID"));
						dboBean.setMtrl_id(resultSetMsg1.getInt("MTRL_ID"));
						dboBean.setCat_id(resultSetMsg1.getInt("CAT_ID"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setType_of_struct(resultSetMsg1.getString("TYPE_OF_STRUCT"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("F2F_UBO_MAST_BKP")) {

						DBOBean dboBean = new DBOBean();

						/*
						 * dboBean.setAction_type(resultSetMsg1.getString(
						 * "ACTION_TYPE"));
						 * dboBean.setAction_row(resultSetMsg1.getString(
						 * "ACTION_ROW"));
						 * dboBean.setF2f_mast_id(resultSetMsg1.getInt(
						 * "F2F_MAST_ID"));
						 * dboBean.setFrm_pow_id(resultSetMsg1.getInt(
						 * "FRM_POW_ID"));
						 * dboBean.setBleed_typ_id(resultSetMsg1.getInt(
						 * "BLEED_TYP_ID"));
						 * dboBean.setCond_typ_id(resultSetMsg1.getInt(
						 * "COND_TYP_ID"));
						 * dboBean.setTotal_price(resultSetMsg1.getFloat(
						 * "TOTAL_PRICE"));
						 * dboBean.setPer_mwp_rice(resultSetMsg1.getFloat(
						 * "PER_MWP_RICE"));
						 * dboBean.setCreat_dt(resultSetMsg1.getDate(
						 * ItoConstants.CREAT_DT));
						 * dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.
						 * MOD_DT));
						 * dboBean.setCreat_by(resultSetMsg1.getString(
						 * "CREAT_BY"));
						 * dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						 * dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"
						 * ));
						 */
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setF2f_mast_id(resultSetMsg1.getInt("F2F_MAST_ID"));
						dboBean.setFrm_pow_id(resultSetMsg1.getInt("FRM_POW_ID"));
						dboBean.setBleed_typ_id(resultSetMsg1.getInt("BLEED_TYP_ID"));
						dboBean.setTotal_price(resultSetMsg1.getFloat("TOTAL_PRICE"));
						dboBean.setSubContrCost(resultSetMsg1.getFloat("SUB_CONTR_COST"));
						dboBean.setShopConvCost(resultSetMsg1.getFloat("SHOP_CONV_COST"));
						dboBean.setTotalF2fCost(resultSetMsg1.getFloat("TOTAL_FTF_COST"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("ITEM_MAST_BKP")) {

						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setUn_item_id(resultSetMsg1.getInt("UN_ITEM_ID"));
						dboBean.setScop_cd(resultSetMsg1.getString("SCOP_CD"));
						dboBean.setUn_item_cd(resultSetMsg1.getString("UN_ITEM_CD"));
						dboBean.setUn_item_nm(resultSetMsg1.getString("UN_ITEM_NM"));
						dboBean.setUn_parent_cd(resultSetMsg1.getString("UN_PARENT_CD"));
						dboBean.setItem_flg(resultSetMsg1.getInt("ITEM_FLG"));
						dboBean.setSub_item_flg(resultSetMsg1.getInt("SUB_ITEM_FLG"));
						dboBean.setSub_item_typ_flg(resultSetMsg1.getInt("SUB_ITEM_TYP_FLG"));
						dboBean.setF2f_add_on(resultSetMsg1.getInt("F2F_ADD_ON"));
						dboBean.setExclusion_nm(resultSetMsg1.getString("EXCLUSION_NM"));
						dboBean.setHeader_text(resultSetMsg1.getString("HEADER_TEXT"));
						dboBean.setFooter_text(resultSetMsg1.getString("FOOTER_TEXT"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("PKG_MAST_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setPkg_id(resultSetMsg1.getInt("PKG_ID"));
						dboBean.setCust_typ(resultSetMsg1.getString("CUST_TYP"));
						dboBean.setPkg_typ(resultSetMsg1.getString("PKG_TYP"));
						dboBean.setFrm_id(resultSetMsg1.getInt("FRM_ID"));
						dboBean.setCond_typ_id(resultSetMsg1.getInt("COND_TYP_ID"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setAssigned_to(resultSetMsg1.getString("ASSIGNED_TO"));
						dboBean.setStatus_id(resultSetMsg1.getInt("STATUS_ID"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						dboBean.setPrev_percent(resultSetMsg1.getFloat("PREV_PERCENT"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("REGION_BKP")) {

						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setId(resultSetMsg1.getInt("ID"));
						dboBean.setRegion_id(resultSetMsg1.getInt("REGION_ID"));
						dboBean.setUser_id(resultSetMsg1.getInt("USER_ID"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
					if (adminForm.getTableName().equalsIgnoreCase("REGION_MAST_BKP")) {

						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setRegion_id(resultSetMsg1.getInt("REGION_ID"));
						dboBean.setName(resultSetMsg1.getString("NAME"));
						dboBean.setRegion_key(resultSetMsg1.getString("REGION_KEY"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("ROLES_MAST_BKP")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setRoles_id(resultSetMsg1.getInt("ROLES_ID"));
						dboBean.setRole_name(resultSetMsg1.getString("ROLE_NAME"));
						dboBean.setRole_cd(resultSetMsg1.getString("ROLE_CD"));
						dboBean.setGroup_cd(resultSetMsg1.getString("GROUP_CD"));
						dboBean.setDescription(resultSetMsg1.getString("DESCRIPTION"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("ROLES_BKP")) {

						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setId(resultSetMsg1.getInt("ID"));
						dboBean.setRoles_id(resultSetMsg1.getInt("ROLES_ID"));
						dboBean.setUser_id(resultSetMsg1.getInt("USER_ID"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("TRNS_UNIT_PRICE_BKP")) {

						DBOBean dboBean = new DBOBean();

						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setUnit_id(resultSetMsg1.getInt("UNIT_ID"));
						dboBean.setVehicle_id(resultSetMsg1.getInt("VEHICLE_ID"));
						dboBean.setMin_dist(resultSetMsg1.getFloat("MIN_DIST"));
						dboBean.setMax_dist(resultSetMsg1.getFloat("MAX_DIST"));
						dboBean.setUnit_price(resultSetMsg1.getFloat("UNIT_PRICE"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						dboBean.setPrev_percent(resultSetMsg1.getFloat("PREV_PERCENT"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("TRNSPORT_DM_BKP")) {

						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setTrns_id(resultSetMsg1.getInt("TRNS_ID"));
						dboBean.setTrns_typ(resultSetMsg1.getString("TRNS_TYP"));
						dboBean.setFrm_id(resultSetMsg1.getInt("FRM_ID"));
						dboBean.setCond_typ_id(resultSetMsg1.getInt("COND_TYP_ID"));
						dboBean.setFob_id(resultSetMsg1.getInt("FOB_ID"));
						dboBean.setVehicle_id(resultSetMsg1.getInt("VEHICLE_ID"));
						dboBean.setComp_id(resultSetMsg1.getInt("COMP_ID"));
						dboBean.setNo_of_vehicle(resultSetMsg1.getInt("NO_OF_VEHICLE"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setAssigned_to(resultSetMsg1.getString("ASSIGNED_TO"));
						dboBean.setStatus_id(resultSetMsg1.getInt("STATUS_ID"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("TRNSPORT_EX_BKP")) {

						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setTrns_id(resultSetMsg1.getInt("TRNS_ID"));
						dboBean.setTrns_typ(resultSetMsg1.getString("TRNS_TYP"));
						dboBean.setFrm_id(resultSetMsg1.getInt("FRM_ID"));
						dboBean.setCond_typ_id(resultSetMsg1.getInt("COND_TYP_ID"));
						dboBean.setPort_id(resultSetMsg1.getInt("PORT_ID"));
						dboBean.setPriceChennai(resultSetMsg1.getFloat("PRICE_CHENNAI"));
						dboBean.setPriceFob(resultSetMsg1.getFloat("PRICE_FOB"));
						dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
						dboBean.setAssigned_to(resultSetMsg1.getString("ASSIGNED_TO"));
						dboBean.setStatus_id(resultSetMsg1.getInt("STATUS_ID"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						dboBean.setPrevPercent(resultSetMsg1.getFloat("PREV_PERCENT"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("F2F_TURB_VARIANT_BKP")) {

						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setVariant_id(resultSetMsg1.getInt("VARIANT_ID"));
						dboBean.setFrm_pow_id(resultSetMsg1.getInt("FRM_POW_ID"));
						dboBean.setVariant_cd(resultSetMsg1.getString("VARIANT_CD"));
						dboBean.setC_num(resultSetMsg1.getString("C_NUM"));
						dboBean.setCust_id(resultSetMsg1.getInt("CUST_ID"));
						dboBean.setStatus_id(resultSetMsg1.getInt("STATUS_ID"));
						dboBean.setC_proj_dt(resultSetMsg1.getString("C_PROJ_DT"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("USERS_BKP")) {

						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setUser_id(resultSetMsg1.getInt("USER_ID"));
						dboBean.setEmp_id(resultSetMsg1.getInt("EMP_ID"));
						dboBean.setName(resultSetMsg1.getString("NAME"));
						dboBean.setPh_num(resultSetMsg1.getString("PH_NUM"));
						dboBean.setEmail(resultSetMsg1.getString("EMAIL"));
						dboBean.setPassword(resultSetMsg1.getString("PASSWORD"));
						dboBean.setDept_id(resultSetMsg1.getInt("DEPT_ID"));
						dboBean.setDesignation(resultSetMsg1.getString("DESIGNATION"));
						dboBean.setLast_login_dt(resultSetMsg1.getDate("LAST_LOGIN_DT"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}

					if (adminForm.getTableName().equalsIgnoreCase("OTHERS_MAST_BKP")) {

						DBOBean dboBean = new DBOBean();
						dboBean.setAction_type(resultSetMsg1.getString("ACTION_TYPE"));
						dboBean.setAction_row(resultSetMsg1.getString("ACTION_ROW"));
						dboBean.setItem_id(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setItem_cd(resultSetMsg1.getString("ITEM_CD"));
						dboBean.setItem_nm(resultSetMsg1.getString("ITEM_NM"));
						dboBean.setGroup_cd(resultSetMsg1.getString("GROUP_CD"));
						dboBean.setNo_of_months(resultSetMsg1.getInt("NO_OF_MONTHS"));
						dboBean.setRegion_id(resultSetMsg1.getInt("REGION_ID"));
						dboBean.setCust_type(resultSetMsg1.getString("CUST_TYPE"));
						dboBean.setPercent_flg(resultSetMsg1.getInt("PERCNT_FLG"));
						dboBean.setPercent(resultSetMsg1.getFloat("PERCNT"));
						dboBean.setCreat_dt(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
						dboBean.setMod_dt(resultSetMsg1.getDate(ItoConstants.MOD_DT));
						dboBean.setCreat_by(resultSetMsg1.getString("CREAT_BY"));
						dboBean.setMod_by(resultSetMsg1.getString("MOD_BY"));
						dboBean.setIs_active(resultSetMsg1.getInt("IS_ACTIVE"));
						auditHistoryList.add(dboBean);
					}
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();
				while (resultSetData.next()) {

					DBOBean dboBean = new DBOBean();

					dboBean.setName(resultSetData.getString("NAME"));

					nameList.add(dboBean);
				}
				if (!auditHistoryList.isEmpty()) {
					logger.info("inside list if");
					adminForm.setAuditHistoryList(auditHistoryList);
				}
				if (!nameList.isEmpty()) {
					adminForm.setNameList(nameList);
				}

				// } else if
				// (adminForm.getTableName().equalsIgnoreCase("ELE_ADDON_COST_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setTypeOfPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
				// dboBean.setCustType(resultSetMsg1.getString("CUST_TYPE"));
				// dboBean.setMake(resultSetMsg1.getString("MAKE"));
				// dboBean.setColId(resultSetMsg1.getInt("COL_ID"));
				// dboBean.setColValCd(resultSetMsg1.getString("COL_VAL_CD"));
				// dboBean.setSubColValCd(resultSetMsg1.getString("SUB_COL_VAL_CD"));
				// dboBean.setAddOnCostCol(resultSetMsg1.getString("ADDON_COST_COL"));
				// dboBean.setMinVal(resultSetMsg1.getFloat("MIN_VAL"));
				// dboBean.setMaxVal(resultSetMsg1.getFloat("MAX_VAL"));
				// dboBean.setAddOnDirCost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
				// dboBean.setAddOnCostPer(resultSetMsg1.getFloat("ADDON_COST_PER"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setErrorMsg(resultSetMsg1.getString("ERROR_MSG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }
				// } else if
				// (adminForm.getTableName().equalsIgnoreCase("ELE_PRICE_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setSubItemId(resultSetMsg1.getInt("SUB_ITEM_ID"));
				// dboBean.setTypeOfPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
				// dboBean.setMake(resultSetMsg1.getString("MAKE"));
				// dboBean.setCustType(resultSetMsg1.getString("CUST_TYPE"));
				// dboBean.setPriceCode(resultSetMsg1.getString("PRICE_CODE"));
				// dboBean.setPrice15(resultSetMsg1.getFloat("PRICE_15"));
				// dboBean.setPrice30(resultSetMsg1.getFloat("PRICE_30"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setItemErrMessage(resultSetMsg1.getString("ITEM_ERROR_MSG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				//
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }
				// } else if
				// (adminForm.getTableName().equalsIgnoreCase("ELE_ITEMS_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setPriceId(resultSetMsg1.getInt("ELE_ITEM_ID"));
				// dboBean.setTypeOfPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
				// dboBean.setEleType(resultSetMsg1.getString("ELE_TYPE"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setItemOrder(resultSetMsg1.getInt("ITEM_ORDER"));
				// dboBean.setItemApplInd(resultSetMsg1.getInt("ITEM_APPL_IND"));
				// dboBean.setSubItemId(resultSetMsg1.getInt("SUB_ITEM_ID"));
				// dboBean.setSubItemFlag(resultSetMsg1.getInt("SUB_ITEM_FLG")
				// == 1 ? true : false);
				// dboBean.setPanelDependFlag(resultSetMsg1.getInt("PANEL_DEPEND_FLG"));
				// dboBean.setLinkFlag(resultSetMsg1.getInt("LINK_FLG") == 1 ?
				// true : false);
				// dboBean.setDependFlag(resultSetMsg1.getInt("DEPEND_FLG") == 1
				// ? true : false);
				// dboBean.setTechFlag(resultSetMsg1.getInt("TECH_FLG"));
				// dboBean.setComrFlag(resultSetMsg1.getInt("COMR_FLG"));
				// dboBean.setEnabled(resultSetMsg1.getInt("IS_ENABLE") == 1 ?
				// true : false);
				// dboBean.setHeaderText(resultSetMsg1.getString("HEADER_TEXT"));
				// dboBean.setFooterText(resultSetMsg1.getString("FOOTER_TEXT"));
				// dboBean.setHearerNm(resultSetMsg1.getString("HEARER_NM"));
				// dboBean.setExclusionNm(resultSetMsg1.getString("EXCLUSION_NM"));
				// dboBean.setCustTypeDependFlag(resultSetMsg1.getInt("CUST_TYP_DEPEND_FLG"));
				// dboBean.setDtFrmFlag(resultSetMsg1.getInt("DT_FRM_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }
				// } else if
				// (adminForm.getTableName().equalsIgnoreCase("ELE_COL_VAL_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setColValId(resultSetMsg1.getInt("COL_VAL_ID"));
				// dboBean.setTypeOfPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setSubItemId(resultSetMsg1.getInt("SUB_ITEM_ID"));
				// dboBean.setMake(resultSetMsg1.getString("MAKE"));
				// dboBean.setColId(resultSetMsg1.getInt("COL_ID"));
				// dboBean.setColValCd(resultSetMsg1.getString("COL_VAL_CD"));
				// dboBean.setColValNm(resultSetMsg1.getString("COL_VAL_NM"));
				// dboBean.setColValCdSym(resultSetMsg1.getString("COL_VAL_CD_SYM"));
				// dboBean.setSubColValFlag(resultSetMsg1.getInt("SUB_COL_VAL_FLG")==
				// 1 ? true : false);
				// dboBean.setSubColValNm(resultSetMsg1.getString("SUB_COL_VAL_NM"));
				// dboBean.setMinVal(resultSetMsg1.getFloat("MIN_VAL"));
				// dboBean.setMaxVal(resultSetMsg1.getFloat("MAX_VAL"));
				// dboBean.setDefaultFlag15(resultSetMsg1.getInt("DEFLT_FLG_15"));
				// dboBean.setDefaultFlag30(resultSetMsg1.getInt("DEFLT_FLG_30"));
				// dboBean.setOrderId(resultSetMsg1.getInt("ORDER_ID"));
				// dboBean.setAddOnFlg(resultSetMsg1.getInt("ADD_ON_FLG"));
				// dboBean.setAddOnDiffDs(resultSetMsg1.getInt("ADD_ON_DIFF_DS"));
				// dboBean.setAddOnCostPer(resultSetMsg1.getFloat("ADDON_COST_PER"));
				// dboBean.setAddOnDirCost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setCalcLinkFlag(resultSetMsg1.getInt("CALC_LINK_FLG")==
				// 1 ? true : false);
				// dboBean.setTechFlag(resultSetMsg1.getInt("TECH_FLG"));
				// dboBean.setComrFlag(resultSetMsg1.getInt("COMR_FLG"));
				// dboBean.setDispInd(resultSetMsg1.getInt("DISP_IND"));
				// dboBean.setDisableColValCd(resultSetMsg1.getString("DISABLE_COL_VA_CD"));
				// dboBean.setDelColFlag(resultSetMsg1.getInt("DEL_COL_FLG"));
				// dboBean.setInputCostFlag(resultSetMsg1.getInt("INPUT_COST_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }
				//
				//
				// } else if
				// (adminForm.getTableName().equalsIgnoreCase("INSTRUMENT_LIST_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setInstrId(resultSetMsg1.getInt("INSTR_ID"));
				// dboBean.setCategory(resultSetMsg1.getString("CATEGORY"));
				// dboBean.setMake(resultSetMsg1.getString("MAKE"));
				// dboBean.setTypeOfGov(resultSetMsg1.getString("TYPE_OF_GOV"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setTurbCode(resultSetMsg1.getString("TURB_CD"));
				// dboBean.setCondTypeId(resultSetMsg1.getInt("COND_TYP_ID"));
				// dboBean.setTypeOfInstr(resultSetMsg1.getString("TYPE_OF_INSTR"));
				// dboBean.setInstrTypeNm(resultSetMsg1.getString("INSTR_TYPE_NM"));
				// dboBean.setInstrDesc(resultSetMsg1.getString("INSTR_DESC"));
				// dboBean.setInstrMounting(resultSetMsg1.getString("INSTR_MOUNTING"));
				// dboBean.setQuantity1001Logic(resultSetMsg1.getInt("QTY_1001_LOGIC"));
				// dboBean.setQuantity1002Logic(resultSetMsg1.getInt("QTY_1002_LOGIC"));
				// dboBean.setQuantity2003Logic(resultSetMsg1.getInt("QTY_2003_LOGIC"));
				// dboBean.setCost1001(Math.round(resultSetMsg1.getInt("COST_1001")));
				// dboBean.setApprox1001Flag(resultSetMsg1.getInt("APPROX_1001_FLG"));
				// dboBean.setCost1002(Math.round(resultSetMsg1.getInt("COST_1002")));
				// dboBean.setApprox1002Flag(resultSetMsg1.getInt("APPROX_1002_FLG"));
				// dboBean.setCost2003(Math.round(resultSetMsg1.getInt("COST_2003")));
				// dboBean.setApprox20003Flag(resultSetMsg1.getInt("APPROX_2003_FLG"));
				// dboBean.setCostCe1001(Math.round(resultSetMsg1.getInt("COST_CE_1001")));
				// dboBean.setApprox1001CeFlag(resultSetMsg1.getInt("APPROX_1001_CE_FLG"));
				// dboBean.setCostCe1002(resultSetMsg1.getInt("COST_CE_1002"));
				// dboBean.setApprox1002CeFlag(resultSetMsg1.getInt("APPROX_1002_CE_FLG"));
				// dboBean.setCostCe2003(Math.round(resultSetMsg1.getInt("COST_CE_2003")));
				// dboBean.setApprox20003CeFlag(resultSetMsg1.getInt("APPROX_2003_CE_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				//
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("FRAMES_CAT_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setId(resultSetMsg1.getInt("ID"));
				// dboBean.setFrameId(resultSetMsg1.getInt("FRM_ID"));
				// dboBean.setCategory(resultSetMsg1.getString("CAT"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setTypeOfInstr(resultSetMsg1.getString("TYPE_OF_INSTR"));
				// dboBean.setCost1001(Math.round(resultSetMsg1.getInt("COST_1001")));
				// dboBean.setApprox1001Flag(resultSetMsg1.getInt("APPROX_1001_FLG"));
				// dboBean.setCost1002(Math.round(resultSetMsg1.getInt("COST_1002")));
				// dboBean.setApprox1002Flag(resultSetMsg1.getInt("APPROX_1002_FLG"));
				// dboBean.setCost2003(Math.round(resultSetMsg1.getInt("COST_2003")));
				// dboBean.setApprox20003Flag(resultSetMsg1.getInt("APPROX_2003_FLG"));
				// dboBean.setCostCe1001(Math.round(resultSetMsg1.getInt("COST_CE_1001")));
				// dboBean.setApprox1001CeFlag(resultSetMsg1.getInt("APPROX_1001_CE_FLG"));
				// dboBean.setCostCe1002(resultSetMsg1.getInt("COST_CE_1002"));
				// dboBean.setApprox1002CeFlag(resultSetMsg1.getInt("APPROX_1002_CE_FLG"));
				// dboBean.setCostCe2003(Math.round(resultSetMsg1.getInt("COST_CE_2003")));
				// dboBean.setApprox20003CeFlag(resultSetMsg1.getInt("APPROX_2003_CE_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }}else if
				// (adminForm.getTableName().equalsIgnoreCase("ADD_INSTR_LIST_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setAddInstrId(resultSetMsg1.getInt("ADD_INSTR_ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setAddInstrCd(resultSetMsg1.getString("ADD_INSTR_CD"));
				// dboBean.setAddInstrNm(resultSetMsg1.getString("ADD_INSTR_NM"));
				// dboBean.setCost(Math.round(resultSetMsg1.getInt("COST")));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("VMS_FRM_LIST_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setVmsId(resultSetMsg1.getInt("VMS_ID"));
				// dboBean.setTypeOfPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
				// dboBean.setFrameId(resultSetMsg1.getInt("FRM_ID"));
				// dboBean.setAddPrbFlag(resultSetMsg1.getInt("ADD_PRB_FLG"));
				// dboBean.setType(resultSetMsg1.getString("TYPE"));
				// dboBean.setTypeDefaultFlag(resultSetMsg1.getInt("TYPE_DEFLT_FLG"));
				// dboBean.setMake(resultSetMsg1.getString("MAKE"));
				// dboBean.setMakeDefaultFlag(resultSetMsg1.getInt("MAKE_DEFLT_FLG"));
				// dboBean.setAltMake(resultSetMsg1.getString("ALT_MAKE"));
				// dboBean.setAltMakeDefaultFlag(resultSetMsg1.getInt("ALT_MAKE_DEFLT_FLG"));
				// dboBean.setGearbox(resultSetMsg1.getString("GEAR_BOX"));
				// dboBean.setGearBoxDefaultFlag(resultSetMsg1.getInt("GEAR_BOX_DEFLT_FLG"));
				//
				// dboBean.setCost(Math.round(resultSetMsg1.getFloat("COST")));
				// dboBean.setNote(resultSetMsg1.getString("NOTE"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				//
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("BGM_CALC_BKP")) {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setId(resultSetMsg1.getInt("ID"));
				// dboBean.setFramePowerId(resultSetMsg1.getInt("FRM_POW_ID"));
				// dboBean.setBgmType(resultSetMsg1.getString("BGM_TYPE"));
				// dboBean.setBgmRating(resultSetMsg1.getFloat("BGM_RATING"));
				// dboBean.setDefaultFlagNew(resultSetMsg1.getInt("DEFLT_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				//
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("DCM_POWER_CALC_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setId(resultSetMsg1.getInt("ID"));
				// dboBean.setFramePowerId(resultSetMsg1.getInt("FRM_POW_ID"));
				// dboBean.setEopMotarRating(resultSetMsg1.getFloat("EOP_MOTOR_RATING"));
				// dboBean.setCost(resultSetMsg1.getFloat("COST"));
				// dboBean.setPumpType(resultSetMsg1.getString("PUMP_TYPE"));
				// dboBean.setBgHrRate(resultSetMsg1.getInt("BG_HR_RATE"));
				// dboBean.setDcOutputVol(resultSetMsg1.getFloat("DC_OUTPUT_VOL"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				//
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("CURRENCY_BKP")) {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setSubItemId(resultSetMsg1.getInt("SUB_ITEM_ID"));
				// dboBean.setColId(resultSetMsg1.getInt("COL_ID"));
				// dboBean.setColValCd(resultSetMsg1.getString("COL_VAL_CD"));
				// dboBean.setAddOnCostCol(resultSetMsg1.getString("ADDON_COST_COL"));
				// dboBean.setAddOnCostPer(resultSetMsg1.getFloat("ADDON_COST_PER"));
				// dboBean.setAddOnDirCost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("MECH_COL_VAL_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setColValId(resultSetMsg1.getInt("COL_VAL_ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setSubItemId(resultSetMsg1.getInt("SUB_ITEM_ID"));
				// dboBean.setColId(resultSetMsg1.getInt("COL_ID"));
				// dboBean.setColValCd(resultSetMsg1.getString("COL_VAL_CD"));
				// dboBean.setColValNm(resultSetMsg1.getString("COL_VAL_NM"));
				// dboBean.setDefaultFlagNew(resultSetMsg1.getInt("DEFLT_FLG"));
				// dboBean.setDispInd(resultSetMsg1.getInt("DISP_IND"));
				// dboBean.setOrderId(resultSetMsg1.getInt("ORDER_ID"));
				// dboBean.setMinVal(resultSetMsg1.getFloat("MIN_VAL"));
				// dboBean.setMaxVal(resultSetMsg1.getFloat("MAX_VAL"));
				// dboBean.setComrFlag(resultSetMsg1.getInt("COMR_FLG"));
				// dboBean.setTechFlag(resultSetMsg1.getInt("TECH_FLG"));
				// dboBean.setCalcLinkFlag(resultSetMsg1.getInt("CALC_LINK_FLG")==
				// 1 ? true : false);
				// dboBean.setAddOnFlag(resultSetMsg1.getInt("ADD_ON_FLG")== 1 ?
				// true : false);
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("MECH_PRICE_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setSubItemId(resultSetMsg1.getInt("SUB_ITEM_ID"));
				// dboBean.setTurbCode(resultSetMsg1.getString("TURB_CD"));
				// dboBean.setPriceCode(resultSetMsg1.getString("PRICE_CODE"));
				// dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setDefaultFlagNew(resultSetMsg1.getInt("DEFLT_FLG"));
				// dboBean.setTotalPrice(resultSetMsg1.getFloat("TOTAL_PRICE"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("MECH_AUX_COL_VAL_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setColValId(resultSetMsg1.getInt("COL_VAL_ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setColId(resultSetMsg1.getInt("COL_ID"));
				// dboBean.setColValCd(resultSetMsg1.getString("COL_VAL_CD"));
				// dboBean.setColValNm(resultSetMsg1.getString("COL_VAL_NM"));
				// dboBean.setDefaultFlagNew(resultSetMsg1.getInt("DEFLT_FLG"));
				// dboBean.setDispInd(resultSetMsg1.getInt("DISP_IND"));
				// dboBean.setOrderId(resultSetMsg1.getInt("ORDER_ID"));
				// dboBean.setComrFlag(resultSetMsg1.getInt("COMR_FLG"));
				// dboBean.setTechFlag(resultSetMsg1.getInt("TECH_FLG"));
				// dboBean.setAddOnFlag(resultSetMsg1.getInt("ADD_ON_FLG")== 1 ?
				// true : false);
				// dboBean.setAddOnCostPer(resultSetMsg1.getFloat("ADDON_COST_PER"));
				// dboBean.setAddOnDirCost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				//
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }} else if
				// (adminForm.getTableName().equalsIgnoreCase("MECH_AUX_PRICE_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setTurbCode(resultSetMsg1.getString("TURB_CD"));
				// dboBean.setPriceCode(resultSetMsg1.getString("PRICE_CODE"));
				// dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setDefaultFlagNew(resultSetMsg1.getInt("DEFLT_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				//
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
				// }
				// }
				// } else if
				// (adminForm.getTableName().equalsIgnoreCase("MECH_OVER_TANK_BKP"))
				// {
				// if (callableStatement.getMoreResults()) {
				// resultSetMsg1 = callableStatement.getResultSet();
				// while (resultSetMsg1.next()) {
				// DBOBean dboBean = new DBOBean();
				// dboBean.setActionType(resultSetMsg1.getString("ACTION_TYPE"));
				// dboBean.setActionRow(resultSetMsg1.getString("ACTION_ROW"));
				// dboBean.setId(resultSetMsg1.getInt("ID"));
				// dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
				// dboBean.setPower(resultSetMsg1.getString("POWER"));
				// dboBean.setMinVal(resultSetMsg1.getFloat("MIN_VAL"));
				// dboBean.setMaxVal(resultSetMsg1.getFloat("MAX_VAL"));
				// dboBean.setQuantity(resultSetMsg1.getInt("QTY"));
				// dboBean.setPrice(resultSetMsg1.getFloat("PRICE"));
				// dboBean.setDefaultFlagNew(resultSetMsg1.getInt("DEFLT_FLG"));
				// dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
				// dboBean.setCategoryCreatedDate(resultSetMsg1.getDate(ItoConstants.CREAT_DT));
				// dboBean.setCategoryModifiedDate(resultSetMsg1.getDate(ItoConstants.MOD_DT));
				// dboBean.setCategoryCreatedBy(resultSetMsg1.getInt(ItoConstants.CREAT_BY));
				// dboBean.setCategoryModifiedBy(resultSetMsg1.getInt(ItoConstants.MOD_BY));
				// dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
				// adminForm.getAuditHistoryList().add(dboBean);
				// }
				// }
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetData = callableStatement.getResultSet();
				// while (resultSetData.next()) {
				//
				// DBOBean dboBean = new DBOBean();
				//
				// dboBean.setName(resultSetData.getString("NAME"));
				//
				//
				// adminForm.getNameList().add(dboBean);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return adminForm;
	}

	@Override
	public AdminForm getAuditHistory1() {

		AdminForm adminForm = new AdminForm();
		CallableStatement callableStatement = null;
		Connection connection = null;
		List<AdminForm> auditFormlist = new ArrayList<AdminForm>();
		ResultSet resultSetdata = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_AUDIT_HISTORY() }");
			callableStatement.execute();

			resultSetdata = callableStatement.getResultSet();

			while (resultSetdata.next()) {
				AdminForm bean = new AdminForm();
				bean.setScopeCode(resultSetdata.getString("SCOPE_CD"));
				bean.setTableName(resultSetdata.getString("TABLE_NM"));
				bean.setScopeDesc(resultSetdata.getString("SCOPE_DESC"));

				auditFormlist.add(bean);
			}
			adminForm.setAuditFormlist(auditFormlist);

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}

		return adminForm;
	}

	@Override
	public AdminForm getUpload(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;
		ResultSet resultSetdata = null;
		List<AdminForm> Uploadlist = new ArrayList<AdminForm>();
		// String img1;
		// img1 = adminForm.getItem();

		int len;

		try {
			// File file = new File(img1);
			// FileInputStream fis = new FileInputStream(file);
			len = 0;

			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_CREATE_UTILITIES(?,?,?,?,?,?,?) }");
			callableStatement.setInt("ITEM_ID", adminForm.getItemId());
			callableStatement.setString("ITEM_CD", adminForm.getItemCd());
			callableStatement.setString("ITEM_NM", adminForm.getItemNm());
			callableStatement.setString("ITEM_DESC", adminForm.getItemDesc());
			callableStatement.setString("FILE_PATH", adminForm.getFilePath());
			callableStatement.setInt("MOD_BY", adminForm.getModifiedBy());
			// callableStatement.setBinaryStream("ITEM", adminForm.getItem());
			// String filePath = adminForm.getFilePath();
			// String arrofStr[] = filePath.split("\\");
			// int iLength = arrofStr.length;
			// String fileName = arrofStr[iLength - 1];
			// String[] arrofFileName = fileName.split(".");
			// iLength = arrofFileName.length;
			// string fileExt = arrofFileName[iLength - 1];

			if (adminForm.getItem() != null) {
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] imageByte = decoder.decodeBuffer(adminForm.getItem());
				callableStatement.setBytes("ITEM", imageByte);
			} else {
				callableStatement.setBinaryStream("ITEM", null);
			}

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			// if (callableStatement.getMoreResults()) {
			// resultSetdata = callableStatement.getResultSet();
			//
			// while (resultSetdata.next()) {
			// AdminForm bean = new AdminForm();
			// bean.setItemId(resultSetdata.getInt("ITEM_ID"));
			// bean.setItemCd(resultSetdata.getString("ITEM_CD"));
			// bean.setItemNm(resultSetdata.getString("ITEM_NM"));
			// bean.setItemDesc(resultSetdata.getString("ITEM_DESC"));
			//
			//
			// bean.setCreatedDate(resultSetdata.getString("CREAT_DT"));
			// bean.setModifyDate(resultSetdata.getString("MOD_DT"));
			// bean.setCreatedBy(resultSetdata.getString("CREAT_BY"));
			// bean.setModifyByNm(resultSetdata.getString("MOD_BY_NM"));
			// bean.setModifyBy(resultSetdata.getInt("MOD_BY"));
			//
			// if (resultSetdata.getString("IS_ACTIVE").equals("1")) {
			// bean.setActive(true);
			// } else {
			// bean.setActive(false);
			// }
			//
			// Uploadlist.add(bean);
			// }
			// }
			// adminForm.setUploadlist(Uploadlist);

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetdata);
		}
		return adminForm;
	}

	@Override
	public AdminForm getGrid() {
		AdminForm adminForm = new AdminForm();
		try {

			// UAT & PROD
			String query = "SELECT ITEM_ID, ITEM_CD , ITEM_NM, ITEM, ITEM_DESC, A.CREAT_DT,A.MOD_DT, (SELECT C.NAME FROM USERS C WHERE C.USER_ID = A.CREAT_BY) AS CREAT_BY,(SELECT D.NAME FROM USERS D WHERE D.USER_ID = A.MOD_BY) AS MOD_BY_NM,A.MOD_BY,A.IS_ACTIVE, SUBSTRING(RIGHT(FILE_PATH,5),CHARINDEX( '.',RIGHT(FILE_PATH,5)) + 1,4) AS EXTN  FROM TEST..UTILITIES A WHERE  A.IS_ACTIVE = 1";

			// DEV
			//String query = "SELECT ITEM_ID, ITEM_CD , ITEM_NM, ITEM, ITEM_DESC, A.CREAT_DT,A.MOD_DT, (SELECT C.NAME FROM USERS C WHERE C.USER_ID = A.CREAT_BY) AS CREAT_BY,(SELECT D.NAME FROM USERS D WHERE D.USER_ID = A.MOD_BY) AS MOD_BY_NM,A.MOD_BY,A.IS_ACTIVE, SUBSTRING(RIGHT(FILE_PATH,5),CHARINDEX( '.',RIGHT(FILE_PATH,5)) + 1,4) AS EXTN FROM TEST..UTILITIES_DEV A WHERE A.IS_ACTIVE = 1";

			List<AdminForm> gridList = jdbcTemplate.query(query, new RowMapper<AdminForm>() {
				public AdminForm mapRow(ResultSet rs, int rowNum) throws SQLException {

					AdminForm bean = new AdminForm();
					bean.setItemId(rs.getInt("ITEM_ID"));
					bean.setItemCd(rs.getString("ITEM_CD"));
					bean.setItemNm(rs.getString("ITEM_NM"));
					if (!(rs.getBytes("ITEM") == null)) {
						String encoded = Base64.getEncoder().encodeToString(rs.getBytes("ITEM"));
						bean.setItem(encoded);
					}
					bean.setExtension(rs.getString("EXTN"));
					bean.setItemDesc(rs.getString("ITEM_DESC"));
					bean.setCreatedDate(rs.getString("CREAT_DT"));
					bean.setModifyDate(rs.getString("MOD_DT"));

					return bean;
				}
			});

			adminForm.setGridlist(gridList);

			return adminForm;

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return adminForm;
		}

	}

	@Override
	public AdminForm getImage(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		ResultSet resultSetdata = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_RPT_TECH_OFFER(?) }");
			callableStatement.setInt("QUOT_NO", adminForm.getQuotNo());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetdata);
		}
		return adminForm;
	}

	@Override
	public AdminForm getStdOffer(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		ResultSet resultSetdata = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_CREATE_OFFER(?,?) }");
			callableStatement.setInt("QUOT_ID", adminForm.getQuotId());
			callableStatement.setInt("MOD_BY", adminForm.getModBy());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetdata);
		}
		return adminForm;
	}

	@Override
	public AdminForm getValidateFinalCostNew(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetValidationMsg = null;
		ResultSet resultSetF2fCache = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		String resultOutParameterStringNew = null;
		Map<String, List<DBOBean>> f2fCacheType = new HashMap<>();

		Connection connection = null;
		Connection connection1 = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_VALIDATE_COST(?) }");

			callableStatement.setInt("QUOT_ID", adminForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			logger.info(adminForm.getSaveBasicDetails().getQuotId());
			while (resultSetMsg.next()) {
				logger.info("Level1gen");
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				adminForm.setSuccessCode(resultOutParameterInt);
				adminForm.setSuccessMsg(resultOutParameterString);
				adminForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			logger.info(resultOutParameterInt);
			logger.info(resultOutParameterString);
			List<DBOBean> f2fCacheList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetF2fCache = callableStatement.getResultSet();
				logger.info("Level2gen");
				int i = 0;
				while (resultSetF2fCache.next()) {
					logger.info(i);
					i++;
					DBOBean bean = new DBOBean();
					bean.setGenType(resultSetF2fCache.getString("ERROR_MSG"));
					/*
					 * bean.setGenInId(resultSetF2fCache.getInt("GEN_IN_ID"));
					 * bean.setGroupCode(resultSetF2fCache.getString("GRP_CD"));
					 * bean.setGenType(resultSetF2fCache.getString("GEN_TYPE"));
					 * bean.setGenInNo(resultSetF2fCache.getInt("GEN_IN_NO"));
					 * bean.setGenInCd(resultSetF2fCache.getString("GEN_IN_CD"))
					 * ;
					 * bean.setGenInNm(resultSetF2fCache.getString("GEN_IN_NM"))
					 * ; bean.setCategoryValCode(resultSetF2fCache.getString(
					 * "CAT_VAL_CD"));
					 * bean.setCategoryValName(resultSetF2fCache.getString(
					 * "CAT_VAL_NM"));
					 * bean.setDefaultFlagNew(resultSetF2fCache.getInt(
					 * "DEFLT_FLG"));
					 * bean.setGroupDescription(resultSetF2fCache.getString(
					 * "GRP_DESC"));
					 * bean.setDispInd(resultSetF2fCache.getInt("DISP_IND"));
					 * logger.info(resultSetF2fCache.getInt("GEN_IN_ID"));
					 * logger.info(resultSetF2fCache.getString("GRP_CD"));
					 * logger.info(resultSetF2fCache.getString("GEN_TYPE"));
					 * logger.info(resultSetF2fCache.getString("GEN_IN_NO"));
					 * logger.info(resultSetF2fCache.getString("GEN_IN_CD"));
					 */ f2fCacheList.add(bean);
					logger.info(resultSetF2fCache.getString("ERROR_MSG"));
					logger.info(f2fCacheList);
				}

				if (!f2fCacheList.isEmpty()) {
					for (int i1 = 0; i1 < f2fCacheList.size(); i1++) {
						logger.info(f2fCacheList.get(i1));
					}
					f2fCacheType.put("CompleteAndExit", f2fCacheList);
					adminForm.getDropDownColumnvalues().setF2fCacheList(f2fCacheType);
				} else {
					logger.info("list is empty");
				}
			}

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}

		return adminForm;
	}

}