package com.ttl.ito.business.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.ttl.ito.business.beans.AddOnComponent;
import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.CommentBean;
import com.ttl.ito.business.beans.DBOBean;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.ErectionCommissionBean;
import com.ttl.ito.business.beans.F2FUBOBean;
import com.ttl.ito.business.beans.PackageBean;
import com.ttl.ito.business.beans.QuestionsEntity;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.beans.SelectBox;
import com.ttl.ito.business.beans.TransportationDetailsBean;
import com.ttl.ito.business.beans.TurbineDetails;
import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.business.dao.QuotationDao;
import com.ttl.ito.business.dao.UpdatePriceDao;
import com.ttl.ito.common.Utility.UtilityMethods;
import com.ttl.ito.internal.beans.ItoConstants;

@Repository
public class UpdatePriceDaoImpl implements UpdatePriceDao {

	@Autowired
	QuotationDao quotationDao;
	private Logger logger = Logger.getLogger(UpdatePriceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Override
	public QuotationForm updatePriceTransport(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR); // update
																				// screen
																				// code
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR); // unit
																		// id
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC); // vehicleId
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC); // dimension
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC); // length
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (TransportationDetailsBean bean : quotationForm.getUpdatePriceTransportList()) {

				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null, bean.getUnitId(),
						bean.getVehicleId(), null, null, null, null, bean.getMinDistance(), bean.getMaxDistance(),
						bean.getUnitPrice());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_TRNS_UNIT_PRICE (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm updatePriceTransportExport(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.REAL);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.REAL);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (TransportationDetailsBean bean : quotationForm.getUpdatePriceTransportList()) {

				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null, bean.getTransId(),
						bean.getTransTypeId(), bean.getFrameId(), bean.getCondensingTypeId(), bean.getPortId(), null,
						bean.getChennaiPrice(), bean.getPriceFob(), bean.getPrice());
				logger.info(bean.getFrameId());
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_TR_EX (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getUpdatePriceReqGrid(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMyReq = null;
		ResultSet resultSetOtherReq = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_MY_ACTION (?)}");
			callableStatement.setInt(1, quotationForm.getLoggedInUserId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetMyReq = callableStatement.getResultSet();
					while (resultSetMyReq.next()) {
						SaveBasicDetails bean = new SaveBasicDetails();
						bean.setUpdateRequestNumber(resultSetMyReq.getInt("UPD_REQ_NO"));
						bean.setUpdateCode(resultSetMyReq.getString("UPD_REQ_NAME"));
						bean.setDisplayReqNumber(bean.getUpdateCode() + "-" + bean.getUpdateRequestNumber());
						bean.setStatusId(resultSetMyReq.getInt("STATUS_ID"));
						bean.setStatusName(resultSetMyReq.getString("STATUS_NAME"));
						bean.setAssignedTo(resultSetMyReq.getInt("ASSIGHNED_TO_ID"));
						bean.setAssignedToName(resultSetMyReq.getString("ASSIGNED_TO"));
						bean.setModifiedDate(resultSetMyReq.getString("MOD_DT"));
						bean.setModifiedById(resultSetMyReq.getInt("MOD_BY_ID"));
						bean.setModifiedBy(resultSetMyReq.getString("MOD_BY"));
						bean.setCreatedById(resultSetMyReq.getInt("CREATED_BY_ID"));
						bean.setCreatedBy(resultSetMyReq.getString("CREATED_BY"));
						quotationForm.getUpdatePriceOthersRequestGrid().add(bean);
					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetOtherReq = callableStatement.getResultSet();
					while (resultSetOtherReq.next()) {
						SaveBasicDetails bean = new SaveBasicDetails();
						bean.setUpdateRequestNumber(resultSetOtherReq.getInt("UPD_REQ_NO"));
						bean.setUpdateCode(resultSetOtherReq.getString("UPD_REQ_NAME"));
						bean.setDisplayReqNumber(bean.getUpdateCode() + "-" + bean.getUpdateRequestNumber());
						bean.setStatusId(resultSetOtherReq.getInt("STATUS_ID"));
						bean.setStatusName(resultSetOtherReq.getString("STATUS_NAME"));
						bean.setAssignedTo(resultSetOtherReq.getInt("ASSIGHNED_TO_ID"));
						bean.setAssignedToName(resultSetOtherReq.getString("ASSIGNED_TO"));
						bean.setModifiedDate(resultSetOtherReq.getString("MOD_DT"));
						bean.setModifiedById(resultSetOtherReq.getInt("MOD_BY_ID"));
						bean.setModifiedBy(resultSetOtherReq.getString("MOD_BY"));
						bean.setCreatedById(resultSetOtherReq.getInt("CREATED_BY_ID"));
						bean.setCreatedBy(resultSetOtherReq.getString("CREATED_BY"));
						quotationForm.getUpdatePriceMyRequestGrid().add(bean);
					}
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getEditUpdatePriceData(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMyReq = null;
		ResultSet resultSetOtherReq = null;
		ResultSet resultSetOtherData = null;
		ResultSet resultSetData = null;
		ResultSet resultEcData = null;
		ResultSet resultSetData1 = null;
		ResultSet resultSetData2 = null;
		ResultSet resultSetOtherData1 = null;
		ResultSet resultSetOtherData2 = null;
		ResultSet resultSetExportData = null;
		ResultSet resultSetUBODate = null;
		ResultSet resultSetUpdateCost = null;
		ResultSet resultSetUpdateCostunsaved = null;
		ResultSet resultSetUpdateSubCost = null;
		ResultSet resultSetUpdateSubCostunsaved = null;
		ResultSet resultSetUpdateTurbCost = null;
		ResultSet resultSetUpdateTurbCostunsaved = null;
		ResultSet resultSeDboMechCol = null;
		ResultSet resultSeDboEleCol = null;
		ResultSet resultSeDboMechColUnsaved = null;
		ResultSet resultSeDboEleColUnsaved = null;
		ResultSet resultSeDboMechPrice = null;
		ResultSet resultSeDboElePrice = null;
		ResultSet resultSeDboElePriceAddOn = null;
		ResultSet resultSeDboElePriceSplAddOn = null;
		ResultSet resultSeDboElePriceAddInstr = null;
		ResultSet resultSeAddOn = null;

		ResultSet resultSeDboMechPriceUnsaved = null;
		ResultSet resultSeDboElePriceUnsaved = null;
		ResultSet resultSeDboElePriceAddOnUnsaved = null;
		ResultSet resultSeDboElePriceSplAddOnUnsaved = null;
		ResultSet resultSeDboElePriceAddInstrUnsaved = null;
		ResultSet resultSeAddOnUnsaved = null;
		ResultSet resultSetOtherDatam = null;
		ResultSet resultSetDatam = null;
		ResultSet resultSetLoginUserEngineer1=null;
		ResultSet resultSetLoginUserEngineer2=null;
		ResultSet resultSetLoginUserReviewer1=null;
		ResultSet resultSetLoginUserApprover1=null;
		ResultSet resultSetDatam1 = null;
		ResultSet resultSetOtherDatam1 = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_UPD_EDIT_DATA (?,?,?)}");
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(3, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetMyReq = callableStatement.getResultSet();
					while (resultSetMyReq.next()) {
						CommentBean bean = new CommentBean();
						bean.setCommentId(resultSetMyReq.getInt("COMMENT_ID"));
						bean.setComment(resultSetMyReq.getString("COMMENT_TX"));
						bean.setAssingedToId(resultSetMyReq.getInt("ASSIGNED_TO_ID"));
						bean.setAssingedToname(resultSetMyReq.getString("ASSIGNED_TO"));
						bean.setCommentType(resultSetMyReq.getString("PROC_NM"));
						quotationForm.getCommentList().add(bean);

					}
				}

				if (!quotationForm.getCommentList().isEmpty()) {
					if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_TRNS_UNIT_PRICE")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherData = callableStatement.getResultSet();
							while (resultSetOtherData.next()) {

								TransportationDetailsBean bean = new TransportationDetailsBean();

								bean.setUpdateRequestNumber(resultSetOtherData.getInt("UPD_REQ_NO"));
								bean.setUnitId(resultSetOtherData.getInt("UNIT_ID"));
								bean.setVehicleId(resultSetOtherData.getInt("VEHICLE_ID"));
								bean.setVehicleName(resultSetOtherData.getString("VEHICLE_NM"));
								bean.setDimension(resultSetOtherData.getString("DIMN"));
								bean.setLength(resultSetOtherData.getInt("LENT"));
								bean.setMinDistance(resultSetOtherData.getFloat("MIN_DIST"));
								bean.setMaxDistance(resultSetOtherData.getFloat("MAX_DIST"));
								bean.setUnitPrice(Math.round(resultSetOtherData.getFloat("UNIT_PRICE")));
								bean.setPreviousUnitPrice(Math.round(resultSetOtherData.getFloat("PREV_UNIT_PRICE")));
								bean.setStatusId(resultSetOtherData.getInt("STATUS_ID"));
								bean.setStatus(resultSetOtherData.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherData.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetOtherData.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherData.getString("MOD_DT"));

								bean.setCreatedById(resultSetOtherData.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherData.getString("CREATED_BY"));

								quotationForm.getSavedUpdatePriceTransportList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetData = callableStatement.getResultSet();
							while (resultSetData.next()) {

								TransportationDetailsBean bean = new TransportationDetailsBean();
								// bean.setIsupdatedflag(resultSetData.getInt("FLG")
								// == 1 ? true : false);
								bean.setUpdateRequestNumber(resultSetData.getInt("UPD_REQ_NO"));
								bean.setUnitId(resultSetData.getInt("UNIT_ID"));
								bean.setVehicleId(resultSetData.getInt("VEHICLE_ID"));
								bean.setVehicleName(resultSetData.getString("VEHICLE_NM"));
								bean.setDimension(resultSetData.getString("DIMN"));
								bean.setLength(resultSetData.getInt("LENT"));
								bean.setMinDistance(resultSetData.getFloat("MIN_DIST"));
								bean.setMaxDistance(resultSetData.getFloat("MAX_DIST"));
								bean.setUnitPrice(Math.round(resultSetData.getFloat("UNIT_PRICE")));
								bean.setPreviousUnitPrice(Math.round(resultSetData.getFloat("PREV_UNIT_PRICE")));
								bean.setStatusId(resultSetData.getInt("STATUS_ID"));
								bean.setStatus(resultSetData.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetData.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetData.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetData.getString("MOD_DT"));
								bean.setCreatedById(resultSetData.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetData.getString("CREATED_BY"));

								quotationForm.getUnsavedUpdatePriceTransportList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_ELE_ADDON_COST")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {

								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetOtherDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setTypeOfPanel(resultSetOtherDatam.getString("TYPE_OF_PANEL"));

								bean.setCustType(resultSetOtherDatam.getString("CUST_TYPE"));
								bean.setMake(resultSetOtherDatam.getString("MAKE"));
								bean.setColId(resultSetOtherDatam.getInt("COL_ID"));
								bean.setColNm(resultSetOtherDatam.getString("COL_NM"));
								bean.setColValCd(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setSubColValCd(resultSetOtherDatam.getString("SUB_COL_VAL_CD"));
								bean.setAddOnCostCol(resultSetOtherDatam.getString("ADDON_COST_COL"));
								bean.setMinVal(resultSetOtherDatam.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSetOtherDatam.getFloat("MAX_VAL"));
								bean.setAddOnCostPer(resultSetOtherDatam.getFloat("ADDON_COST_PER"));
								bean.setAddOnDirCost(resultSetOtherDatam.getFloat("ADDON_DIR_COST"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setErrorMsg(resultSetOtherDatam.getString("ERROR_MSG"));
								bean.setPrevAddOnCostPer(resultSetOtherDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetOtherDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setPrevErrMessage(resultSetOtherDatam.getString("PREV_ERROR_MSG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdatePriceElectricalList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setTypeOfPanel(resultSetDatam.getString("TYPE_OF_PANEL"));

								bean.setCustType(resultSetDatam.getString("CUST_TYPE"));
								bean.setMake(resultSetDatam.getString("MAKE"));
								bean.setColId(resultSetDatam.getInt("COL_ID"));
								bean.setColNm(resultSetDatam.getString("COL_NM"));
								bean.setColValCd(resultSetDatam.getString("COL_VAL_CD"));
								bean.setSubColValCd(resultSetDatam.getString("SUB_COL_VAL_CD"));
								bean.setAddOnCostCol(resultSetDatam.getString("ADDON_COST_COL"));
								bean.setMinVal(resultSetDatam.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSetDatam.getFloat("MAX_VAL"));
								bean.setPrevAddOnCostPer(resultSetDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setPrevErrMessage(resultSetDatam.getString("PREV_ERROR_MSG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdatePriceElectricalList().add(bean);
							}
						}
						// f2f price
					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_F2F_PRICE")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetOtherDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setFramePowerId(resultSetOtherDatam.getInt("FRM_POW_ID"));
								bean.setFrameName(resultSetOtherDatam.getString("FRM_NM"));
								bean.setMaxPower(resultSetOtherDatam.getFloat("MAX_POWER"));
								bean.setTurbCode(resultSetOtherDatam.getString("TURB_CD"));
								bean.setTurbCodeNm(resultSetOtherDatam.getString("TURB_CD_NM"));
								bean.setBleedTypeId(resultSetOtherDatam.getInt("BLEED_TYP_ID"));
								bean.setBleedTypeName(resultSetOtherDatam.getString("BLEED_TYPE_NM"));
								bean.setPrice(resultSetOtherDatam.getFloat("PRICE"));
								bean.setTotalPrice(resultSetOtherDatam.getFloat("TOTAL_PRICE"));
								//bean.setDefaultFlagNew(resultSetOtherDatam.getInt("DEFLT_FLG"));
								//bean.setSubContrCost(resultSetOtherDatam.getFloat("SUB_CONTR_COST"));
								//bean.setShopConvCost(resultSetOtherDatam.getFloat("SHOP_CONV_COST"));
								//bean.setTotalF2fCost(resultSetOtherDatam.getFloat("TOTAL_FTF_COST"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevPrice(resultSetOtherDatam.getFloat("PREV_PRICE"));
								bean.setPrevTotalPrice(resultSetOtherDatam.getFloat("PREV_TOTAL_PRICE"));
								//bean.setPrevDefaultFlag(resultSetOtherDatam.getInt("PREV_DEFLT_FLG"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateF2fPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {

								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setFramePowerId(resultSetDatam.getInt("FRM_POW_ID"));
								bean.setFrameName(resultSetDatam.getString("FRM_NM"));
								bean.setMaxPower(resultSetDatam.getFloat("MAX_POWER"));
								bean.setTurbCode(resultSetDatam.getString("TURB_CD"));
								bean.setTurbCodeNm(resultSetDatam.getString("TURB_CD_NM"));
								bean.setBleedTypeId(resultSetDatam.getInt("BLEED_TYP_ID"));
								bean.setBleedTypeName(resultSetDatam.getString("BLEED_TYPE_NM"));
								bean.setPrevPrice(resultSetDatam.getFloat("PREV_PRICE"));
								bean.setPrevTotalPrice(resultSetDatam.getFloat("PREV_TOTAL_PRICE"));
								//bean.setPrevDefaultFlag(resultSetDatam.getInt("PREV_DEFLT_FLG"));
								//bean.setSubContrCost(resultSetDatam.getFloat("PREV_SUB_CONTR_COST"));
								//bean.setShopConvCost(resultSetDatam.getFloat("SHOP_CONV_COST"));
								//bean.setTotalF2fCost(resultSetDatam.getFloat("TOTAL_FTF_COST"));
								//bean.setPrevTotalF2fCost(resultSetDatam.getFloat("PREV_TOTAL_FTF_COST"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdateF2fPriceList().add(bean);
							}
						}

					}
						else if (quotationForm.getCommentList().get(0).getCommentType()
								.equals("PROC_UPD_CREATE_F2F_SHOP_CONV")) {
							if (callableStatement.getMoreResults()) {
								resultSetOtherDatam1 = callableStatement.getResultSet();
								while (resultSetOtherDatam1.next()) {

									DBOBean bean = new DBOBean();
									bean.setUpdateRequestNumber(resultSetOtherDatam1.getInt("UPD_REQ_NO"));
									bean.setF2fMastId(resultSetOtherDatam1.getInt("F2F_MAST_ID"));
									bean.setFramePowerId(resultSetOtherDatam1.getInt("FRM_POW_ID"));
									bean.setFrmName(resultSetOtherDatam1.getString("FRM_NM"));
									bean.setMaxPower(resultSetOtherDatam1.getFloat("MAX_POWER"));
									bean.setTurbCode(resultSetOtherDatam1.getString("TURB_CD"));
									bean.setTurbCodeNm(resultSetOtherDatam1.getString("TURB_CD_NM"));
									bean.setBleedTypeId(resultSetOtherDatam1.getInt("BLEED_TYP_ID"));
									bean.setBleedTypeName(resultSetOtherDatam1.getString("BLEED_TYPE_NM"));
									bean.setTotalPrice(resultSetOtherDatam1.getFloat("TOTAL_PRICE"));
									bean.setSubContrCost(resultSetOtherDatam1.getFloat("SUB_CONTR_COST"));
									bean.setShopConvCost(resultSetOtherDatam1.getFloat("SHOP_CONV_COST"));
									bean.setTotalF2fCost(resultSetOtherDatam1.getFloat("TOTAL_FTF_COST"));
									bean.setPrevTotalPrice(resultSetOtherDatam1.getFloat("PREV_TOTAL_PRICE"));
									bean.setPrevSubContrCost(resultSetOtherDatam1.getFloat("OREV_SUB_CONTR_COST"));
									bean.setPrevShopConvCost(resultSetOtherDatam1.getFloat("PREV_SHOP_CONV_COST"));
									bean.setPrevTotalF2fCost(resultSetOtherDatam1.getFloat("PREV_TOTAL_FTF_COST"));
									bean.setStatusId(resultSetOtherDatam1.getInt("STATUS_ID"));
									bean.setStatusName(resultSetOtherDatam1.getString("STATUS_NAME"));
									bean.setAssingedToId(resultSetOtherDatam1.getInt("ASSIGHNED_TO_ID"));
									bean.setAssingedTo(resultSetOtherDatam1.getString("ASSIGNED_TO"));
									bean.setModifiedDate(resultSetOtherDatam1.getString("MOD_DT"));
									bean.setCreatedById(resultSetOtherDatam1.getInt("CREATED_BY_ID"));
									bean.setCreatedBy(resultSetOtherDatam1.getString("CREATED_BY"));
									bean.setActiveNew(resultSetOtherDatam1.getInt("IS_ACTIVE"));
//									logger.info("Shop conv start1");
//									logger.info(resultSetOtherDatam1.getInt("UPD_REQ_NO"));
//									logger.info(resultSetOtherDatam1.getInt("F2F_MAST_ID"));
//									logger.info(resultSetOtherDatam1.getInt("FRM_POW_ID"));
//									logger.info(resultSetOtherDatam1.getString("FRM_NM"));
//									logger.info(resultSetOtherDatam1.getFloat("MAX_POWER"));
//									logger.info(resultSetOtherDatam1.getString("TURB_CD"));
//									logger.info(resultSetOtherDatam1.getString("TURB_CD_NM"));
//									logger.info(resultSetOtherDatam1.getInt("BLEED_TYP_ID"));
//									logger.info(resultSetOtherDatam1.getString("BLEED_TYPE_NM"));
//									logger.info(resultSetOtherDatam1.getFloat("TOTAL_PRICE"));
//									logger.info(resultSetOtherDatam1.getFloat("SUB_CONTR_COST"));
//									logger.info(resultSetOtherDatam1.getFloat("SHOP_CONV_COST"));
//									logger.info(resultSetOtherDatam1.getFloat("TOTAL_FTF_COST"));
//									logger.info(resultSetOtherDatam1.getFloat("OREV_SUB_CONTR_COST"));
//									logger.info(resultSetOtherDatam1.getFloat("PREV_SHOP_CONV_COST"));
//                                    logger.info(resultSetOtherDatam1.getFloat("PREV_TOTAL_FTF_COST"));
//									logger.info("Shop conv end1");
									quotationForm.getSavedUpdateF2fShopConvPrcieList().add(bean);
								}
							}

							if (callableStatement.getMoreResults()) {
								resultSetDatam1 = callableStatement.getResultSet();
								while (resultSetDatam1.next()) {

									DBOBean bean = new DBOBean();
									bean.setUpdateRequestNumber(resultSetDatam1.getInt("UPD_REQ_NO"));
									bean.setF2fMastId(resultSetDatam1.getInt("F2F_MAST_ID"));
									bean.setFramePowerId(resultSetDatam1.getInt("FRM_POW_ID"));
									bean.setFrmName(resultSetDatam1.getString("FRM_NM"));
									bean.setMaxPower(resultSetDatam1.getFloat("MAX_POWER"));
									bean.setTurbCode(resultSetDatam1.getString("TURB_CD"));
									bean.setTurbCodeNm(resultSetDatam1.getString("TURB_CD_NM"));
									bean.setBleedTypeId(resultSetDatam1.getInt("BLEED_TYP_ID"));
									bean.setBleedTypeName(resultSetDatam1.getString("BLEED_TYPE_NM"));
									bean.setPrevTotalPrice(resultSetDatam1.getFloat("PREV_TOTAL_PRICE"));
									bean.setPrevSubContrCost(resultSetDatam1.getFloat("OREV_SUB_CONTR_COST"));
									bean.setPrevShopConvCost(resultSetDatam1.getFloat("PREV_SHOP_CONV_COST"));
									bean.setPrevTotalF2fCost(resultSetDatam1.getFloat("PREV_TOTAL_FTF_COST"));
									bean.setStatusId(resultSetDatam1.getInt("STATUS_ID"));
									bean.setStatusName(resultSetDatam1.getString("STATUS_NAME"));
									bean.setAssingedToId(resultSetDatam1.getInt("ASSIGHNED_TO_ID"));
									bean.setAssingedTo(resultSetDatam1.getString("ASSIGNED_TO"));
									bean.setModifiedDate(resultSetDatam1.getString("MOD_DT"));
									bean.setCreatedById(resultSetDatam1.getInt("CREATED_BY_ID"));
									bean.setCreatedBy(resultSetDatam1.getString("CREATED_BY"));
									bean.setActiveNew(resultSetDatam1.getInt("IS_ACTIVE"));
//									logger.info("Shop conv unsaved start");
//									logger.info(resultSetDatam1.getInt("UPD_REQ_NO"));
//									logger.info(resultSetDatam1.getInt("F2F_MAST_ID"));
//									logger.info(resultSetDatam1.getInt("FRM_POW_ID"));
//									logger.info(resultSetDatam1.getString("FRM_NM"));
//									logger.info(resultSetDatam1.getFloat("MAX_POWER"));
//									logger.info(resultSetDatam1.getString("TURB_CD"));
//									logger.info(resultSetDatam1.getString("TURB_CD_NM"));
//									logger.info(resultSetDatam1.getInt("BLEED_TYP_ID"));
//									logger.info(resultSetDatam1.getString("BLEED_TYPE_NM"));
//									logger.info(resultSetDatam1.getFloat("TOTAL_PRICE"));
//									logger.info(resultSetDatam1.getFloat("SUB_CONTR_COST"));
//									logger.info(resultSetDatam1.getFloat("SHOP_CONV_COST"));
//									logger.info(resultSetDatam1.getFloat("TOTAL_FTF_COST"));
//									logger.info(resultSetDatam1.getFloat("OREV_SUB_CONTR_COST"));
//									logger.info(resultSetDatam1.getFloat("PREV_SHOP_CONV_COST"));
//                                    logger.info(resultSetDatam1.getFloat("PREV_TOTAL_FTF_COST"));
//									logger.info("Shop conv unsaved end");
									quotationForm.getUnsavedUpdateF2fShopConvPriceList().add(bean);
								}
							}
			
					// f2f col val
					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_F2F_COL_VAL")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setColValId(resultSetOtherDatam.getInt("COL_VAL_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
								bean.setSubItemTypeId(resultSetOtherDatam.getInt("SUB_ITEM_TYP_ID"));
								bean.setSubItemTypeName(resultSetOtherDatam.getString("SUB_ITEM_TYP_NAME"));
								bean.setCategory(resultSetOtherDatam.getString("CATEGORY"));
								bean.setColId(resultSetOtherDatam.getInt("COL_ID"));
								bean.setColNm(resultSetOtherDatam.getString("COL_NM"));
								bean.setFrameName(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setBleedTypeName(resultSetOtherDatam.getString("COL_VAL_NM"));
								bean.setCalculateLinkFlag(resultSetOtherDatam.getInt("CALC_LINK_FLG"));
								bean.setDefaultFlagNew(resultSetOtherDatam.getInt("DEFLT_FLG"));
								bean.setDispInd(resultSetOtherDatam.getInt("DISP_IND"));
								bean.setOrderId(resultSetOtherDatam.getInt("ORDER_ID"));
								bean.setAddOnFlg(resultSetOtherDatam.getInt("ADD_ON_FLG"));
								bean.setAddOnCostPer(resultSetOtherDatam.getFloat("ADDON_COST_PER"));
								bean.setAddOnDirCost(resultSetOtherDatam.getFloat("ADDON_DIR_COST"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevAddOnFlag(resultSetOtherDatam.getInt("PREV_ADD_ON_FLG"));
								bean.setPrevAddOnCostPer(resultSetOtherDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetOtherDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateF2fPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setColValId(resultSetDatam.getInt("COL_VAL_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetDatam.getString("SUB_ITEM_NAME"));
								bean.setSubItemTypeId(resultSetDatam.getInt("SUB_ITEM_TYP_ID"));
								bean.setSubItemTypeName(resultSetDatam.getString("SUB_ITEM_TYP_NAME"));
								bean.setCategory(resultSetDatam.getString("CATEGORY"));
								bean.setColId(resultSetDatam.getInt("COL_ID"));
								bean.setColNm(resultSetDatam.getString("COL_NM"));
								bean.setFrameName(resultSetDatam.getString("COL_VAL_CD"));
								bean.setBleedTypeName(resultSetDatam.getString("COL_VAL_NM"));
								bean.setCalculateLinkFlag(resultSetDatam.getInt("CALC_LINK_FLG"));
								bean.setDefaultFlagNew(resultSetDatam.getInt("DEFLT_FLG"));
								bean.setDispInd(resultSetDatam.getInt("DISP_IND"));
								bean.setOrderId(resultSetDatam.getInt("ORDER_ID"));
								bean.setPrevAddOnFlag(resultSetDatam.getInt("PREV_ADD_ON_FLG"));
								bean.setPrevAddOnCostPer(resultSetDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdateF2fPriceList().add(bean);
							}
						}
						// mech price
					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_MECH_PRICE")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {
								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetOtherDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
								bean.setTurbCode(resultSetOtherDatam.getString("TURB_CD"));
								bean.setTurbCodeNm(resultSetOtherDatam.getString("TURB_CD_NM"));
								bean.setPriceCode(resultSetOtherDatam.getString("PRICE_CODE"));
								bean.setPrice(resultSetOtherDatam.getFloat("PRICE"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setDefaultFlagNew(resultSetOtherDatam.getInt("DEFLT_FLG"));
								bean.setPrevPrice(resultSetOtherDatam.getFloat("PREV_PRICE"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setPrevDefaultFlag(resultSetOtherDatam.getInt("PREV_DEFLT_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));
								logger.info("Mech price start1");
								logger.info(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								logger.info(resultSetOtherDatam.getInt("PRICE_ID"));
								logger.info(resultSetOtherDatam.getInt("ITEM_ID"));
								logger.info(resultSetOtherDatam.getString("ITEM_NAME"));
								logger.info(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
								logger.info(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
								logger.info(resultSetOtherDatam.getString("TURB_CD"));
								logger.info(resultSetOtherDatam.getString("TURB_CD_NM"));
								logger.info(resultSetOtherDatam.getString("PRICE_CODE"));
								logger.info(resultSetOtherDatam.getFloat("PRICE"));
								logger.info(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								logger.info(resultSetOtherDatam.getFloat("PREV_PRICE"));
								logger.info("Mech price end1");
								quotationForm.getSavedUpdateMechPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetDatam.getInt("PRICE_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetDatam.getString("SUB_ITEM_NAME"));
								bean.setTurbCode(resultSetDatam.getString("TURB_CD"));
								bean.setTurbCodeNm(resultSetDatam.getString("TURB_CD_NM"));
								bean.setPriceCode(resultSetDatam.getString("PRICE_CODE"));
								bean.setPrevPrice(resultSetDatam.getFloat("PREV_PRICE"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setPrevDefaultFlag(resultSetDatam.getInt("PREV_DEFLT_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));
								logger.info("Mech price start2");
								logger.info(resultSetDatam.getInt("UPD_REQ_NO"));
								logger.info(resultSetDatam.getInt("PRICE_ID"));
								logger.info(resultSetDatam.getInt("ITEM_ID"));
								logger.info(resultSetDatam.getString("ITEM_NAME"));
								logger.info(resultSetDatam.getInt("SUB_ITEM_ID"));
								logger.info(resultSetDatam.getString("SUB_ITEM_NAME"));
								logger.info(resultSetDatam.getString("TURB_CD"));
								logger.info(resultSetDatam.getString("TURB_CD_NM"));
								logger.info(resultSetDatam.getString("PRICE_CODE"));
								//logger.info(resultSetDatam.getFloat("PRICE"));
								//logger.info(resultSetDatam.getInt("APPROX_COST_FLG"));
								logger.info(resultSetDatam.getFloat("PREV_PRICE"));
								logger.info("Mech price end2");
								quotationForm.getUnsavedUpdateMechPriceList().add(bean);
							}
						}

						// mech aux price
					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_MECH_AUX_PRICE")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {
								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetOtherDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setTurbCode(resultSetOtherDatam.getString("TURB_CD"));
								bean.setTurbCodeNm(resultSetOtherDatam.getString("TURB_CD_NM"));
								bean.setPriceCode(resultSetOtherDatam.getString("PRICE_CODE"));
								bean.setPrice(resultSetOtherDatam.getFloat("PRICE"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevPrice(resultSetOtherDatam.getFloat("PREV_PRICE"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateMechPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setTurbCode(resultSetDatam.getString("TURB_CD"));
								bean.setTurbCodeNm(resultSetDatam.getString("TURB_CD_NM"));
								bean.setPriceCode(resultSetDatam.getString("PRICE_CODE"));
								bean.setPrevPrice(resultSetDatam.getFloat("PREV_PRICE"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdateMechPriceList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_MECH_ADDON_COST")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetOtherDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
								bean.setColId(resultSetOtherDatam.getInt("COL_ID"));
								bean.setColNm(resultSetOtherDatam.getString("COL_NM"));
								bean.setColValCd(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setAddOnCostCol(resultSetOtherDatam.getString("ADDON_COST_COL"));
								bean.setAddOnCostPer(resultSetOtherDatam.getFloat("ADDON_COST_PER"));
								bean.setAddOnDirCost(resultSetOtherDatam.getFloat("ADDON_DIR_COST"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevAddOnCostCol(resultSetOtherDatam.getString("PREV_ADDON_COST_COL"));
								bean.setPrevAddOnCostPer(resultSetOtherDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetOtherDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateMechPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetDatam.getString("SUB_ITEM_NAME"));
								bean.setColId(resultSetDatam.getInt("COL_ID"));
								bean.setColNm(resultSetDatam.getString("COL_NM"));
								bean.setColValCd(resultSetDatam.getString("COL_VAL_CD"));
								bean.setPrevAddOnCostCol(resultSetDatam.getString("PREV_ADDON_COST_COL"));
								bean.setPrevAddOnCostPer(resultSetDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));
								quotationForm.getUnsavedUpdateMechPriceList().add(bean);
							}
						}

						// mech aux colval
					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_MECH_AUX_COL_VAL")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setColValId(resultSetOtherDatam.getInt("COL_VAL_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setColId(resultSetOtherDatam.getInt("COL_ID"));
								bean.setColNm(resultSetOtherDatam.getString("COL_NM"));
								bean.setColValCd(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setColValNm(resultSetOtherDatam.getString("COL_VAL_NM"));
								bean.setDefaultFlagNew(resultSetOtherDatam.getInt("DEFLT_FLG"));
								bean.setDispInd(resultSetOtherDatam.getInt("DISP_IND"));
								bean.setOrderId(resultSetOtherDatam.getInt("ORDER_ID"));
								bean.setComrFlag(resultSetOtherDatam.getInt("COMR_FLG"));
								bean.setTechFlag(resultSetOtherDatam.getInt("TECH_FLG"));
								bean.setAddOnCostPer(resultSetOtherDatam.getFloat("ADDON_COST_PER"));
								bean.setAddOnDirCost(resultSetOtherDatam.getFloat("ADDON_DIR_COST"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevAddOnCostPer(resultSetOtherDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetOtherDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateMechPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setColValId(resultSetDatam.getInt("COL_VAL_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setColValId(resultSetDatam.getInt("COL_VAL_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setColId(resultSetDatam.getInt("COL_ID"));
								bean.setColNm(resultSetDatam.getString("COL_NM"));
								bean.setColValCd(resultSetDatam.getString("COL_VAL_CD"));
								bean.setColValNm(resultSetDatam.getString("COL_VAL_NM"));
								bean.setDefaultFlagNew(resultSetDatam.getInt("DEFLT_FLG"));
								bean.setDispInd(resultSetDatam.getInt("DISP_IND"));
								bean.setOrderId(resultSetDatam.getInt("ORDER_ID"));
								bean.setComrFlag(resultSetDatam.getInt("COMR_FLG"));
								bean.setTechFlag(resultSetDatam.getInt("TECH_FLG"));
								bean.setPrevAddOnCostPer(resultSetDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdateMechPriceList().add(bean);
							}
						}
						// mech over tank

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_MECH_OVER_TANK")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setId(resultSetOtherDatam.getInt("ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setPower(resultSetOtherDatam.getString("POWER"));
								bean.setMinVal(resultSetOtherDatam.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSetOtherDatam.getFloat("MAX_VAL"));
								bean.setDefaultFlagNew(resultSetOtherDatam.getInt("DEFLT_FLG"));
								bean.setQuantity(resultSetOtherDatam.getInt("QTY"));
								bean.setPrice(Math.round(resultSetOtherDatam.getFloat("PRICE")));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevQuantity(resultSetOtherDatam.getInt("PREV_QTY"));
								bean.setPrevPrice(resultSetOtherDatam.getFloat("PREV_PRICE"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateMechPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setId(resultSetDatam.getInt("ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setPower(resultSetDatam.getString("POWER"));
								bean.setMinVal(resultSetDatam.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSetDatam.getFloat("MAX_VAL"));
								bean.setDefaultFlagNew(resultSetDatam.getInt("DEFLT_FLG"));
								bean.setQuantity(resultSetDatam.getInt("QTY"));
								bean.setPrice(Math.round(resultSetDatam.getFloat("PRICE")));
								bean.setApproxCostFlag(resultSetDatam.getInt("APPROX_COST_FLG"));
								bean.setQuantity(resultSetDatam.getInt("PREV_QTY"));
								bean.setPrevPrice(resultSetDatam.getFloat("PREV_PRICE"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));
								quotationForm.getUnsavedUpdateMechPriceList().add(bean);
							}
						}

						// mech addon

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_MECH_ADDON_COST")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetOtherDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
								bean.setColId(resultSetOtherDatam.getInt("COL_ID"));
								bean.setColNm(resultSetOtherDatam.getString("COL_NM"));
								bean.setColValCd(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setAddOnCostCol(resultSetOtherDatam.getString("ADDON_COST_COL"));
								bean.setAddOnCostPer(resultSetOtherDatam.getFloat("ADDON_COST_PER"));
								bean.setAddOnDirCost(resultSetOtherDatam.getFloat("ADDON_DIR_COST"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevAddOnCostCol(resultSetOtherDatam.getString("PREV_ADDON_COST_COL"));
								bean.setPrevAddOnCostPer(resultSetOtherDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetOtherDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateMechPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetDatam.getString("SUB_ITEM_NAME"));
								bean.setColId(resultSetDatam.getInt("COL_ID"));
								bean.setColNm(resultSetDatam.getString("COL_NM"));
								bean.setColValCd(resultSetDatam.getString("COL_VAL_CD"));
								bean.setPrevAddOnCostCol(resultSetDatam.getString("PREV_ADDON_COST_COL"));
								bean.setPrevAddOnCostPer(resultSetDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));
								quotationForm.getUnsavedUpdateMechPriceList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_ELE_COL_VAL")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setColValCd(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
								bean.setColValId(resultSetOtherDatam.getInt("COL_VAL_ID"));
								bean.setTypeOfPanel(resultSetOtherDatam.getString("TYPE_OF_PANEL"));
								bean.setMake(resultSetOtherDatam.getString("MAKE"));
								bean.setColId(resultSetOtherDatam.getInt("COL_ID"));
								bean.setColNm(resultSetOtherDatam.getString("COL_NM"));
								bean.setColValCd(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setColValNm(resultSetOtherDatam.getString("COL_VAL_NM"));
								bean.setColValCdSym(resultSetOtherDatam.getString("COL_VAL_CD_SYM"));
								bean.setSubColValFlg(resultSetOtherDatam.getInt("SUB_COL_VAL_FLG"));
								bean.setSubColValNm(resultSetOtherDatam.getString("SUB_COL_VAL_NM"));
								bean.setMinVal(resultSetOtherDatam.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSetOtherDatam.getFloat("MAX_VAL"));
								bean.setDefaultFlag15(resultSetOtherDatam.getInt("DEFLT_FLG_15"));
								bean.setDefaultFlag30(resultSetOtherDatam.getInt("DEFLT_FLG_30"));
								bean.setOrderId(resultSetOtherDatam.getInt("ORDER_ID"));
								bean.setCalculateLinkFlag(resultSetOtherDatam.getInt("CALC_LINK_FLG"));
								bean.setComrFlag(resultSetOtherDatam.getInt("COMR_FLG"));
								bean.setDispInd(resultSetOtherDatam.getInt("DISP_IND"));
								bean.setDisableColValCd(resultSetOtherDatam.getString("DISABLE_COL_VA_CD"));
								bean.setDelColFlag(resultSetOtherDatam.getInt("DEL_COL_FLG"));
								bean.setInputCostFlag(resultSetOtherDatam.getInt("INPUT_COST_FLG"));
								bean.setAddOnCostPer(resultSetOtherDatam.getFloat("ADDON_COST_PER"));
								bean.setAddOnDirCost(resultSetOtherDatam.getFloat("ADDON_DIR_COST"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setTechFlag(resultSetOtherDatam.getInt("TECH_FLG"));
								bean.setAddOnFlg(resultSetOtherDatam.getInt("ADD_ON_FLG"));
								bean.setAddOnDiffDs(resultSetOtherDatam.getInt("ADD_ON_DIFF_DS"));
								bean.setPrevAddOnCostPer(resultSetOtherDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetOtherDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setPrevTechFlag(resultSetOtherDatam.getInt("PREV_TECH_FLG"));
								bean.setPrevAddOnFlag(resultSetOtherDatam.getInt("PREV_ADD_ON_FLG"));
								bean.setPrevtAddOnDiffDs(resultSetOtherDatam.getInt("PREV_ADD_ON_DIFF_DS"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdatePriceElectricalList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {

								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setColValCd(resultSetDatam.getString("COL_VAL_CD"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetDatam.getString("SUB_ITEM_NAME"));
								bean.setColValId(resultSetDatam.getInt("COL_VAL_ID"));
								bean.setTypeOfPanel(resultSetDatam.getString("TYPE_OF_PANEL"));
								bean.setMake(resultSetDatam.getString("MAKE"));
								bean.setColId(resultSetDatam.getInt("COL_ID"));
								bean.setColNm(resultSetDatam.getString("COL_NM"));
								bean.setColValCd(resultSetDatam.getString("COL_VAL_CD"));
								bean.setColValNm(resultSetDatam.getString("COL_VAL_NM"));
								bean.setColValCdSym(resultSetDatam.getString("COL_VAL_CD_SYM"));
								bean.setSubColValFlg(resultSetDatam.getInt("SUB_COL_VAL_FLG"));
								bean.setSubColValNm(resultSetDatam.getString("SUB_COL_VAL_NM"));
								bean.setMinVal(resultSetDatam.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSetDatam.getFloat("MAX_VAL"));
								bean.setDefaultFlag15(resultSetDatam.getInt("DEFLT_FLG_15"));
								bean.setDefaultFlag30(resultSetDatam.getInt("DEFLT_FLG_30"));
								bean.setOrderId(resultSetDatam.getInt("ORDER_ID"));
								bean.setCalculateLinkFlag(resultSetDatam.getInt("CALC_LINK_FLG"));
								bean.setComrFlag(resultSetDatam.getInt("COMR_FLG"));
								bean.setDispInd(resultSetDatam.getInt("DISP_IND"));
								bean.setDisableColValCd(resultSetDatam.getString("DISABLE_COL_VA_CD"));
								bean.setDelColFlag(resultSetDatam.getInt("DEL_COL_FLG"));
								bean.setInputCostFlag(resultSetDatam.getInt("INPUT_COST_FLG"));

								bean.setPrevAddOnCostPer(resultSetDatam.getFloat("PREV_ADDON_COST_PER"));
								bean.setPrevAddOnDirCost(resultSetDatam.getFloat("PREV_ADDON_DIR_COST"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setPrevTechFlag(resultSetDatam.getInt("PREV_TECH_FLG"));
								bean.setPrevAddOnFlag(resultSetDatam.getInt("PREV_ADD_ON_FLG"));
								bean.setPrevtAddOnDiffDs(resultSetDatam.getInt("PREV_ADD_ON_DIFF_DS"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));
								quotationForm.getUnsavedUpdatePriceElectricalList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_INSTRUMENT_LIST")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setInstrId(resultSetOtherDatam.getInt("INSTR_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setCategory(resultSetOtherDatam.getString("CATEGORY"));

								bean.setMake(resultSetOtherDatam.getString("MAKE"));

								bean.setTypeOfGov(resultSetOtherDatam.getString("TYPE_OF_GOV"));

								bean.setTurbCode(resultSetOtherDatam.getString("TURB_CD"));
								bean.setTurbCodeNm(resultSetOtherDatam.getString("TURB_CD_NM"));

								bean.setCondensingTypeId(resultSetOtherDatam.getInt("COND_TYP_ID"));
								bean.setCondensingType(resultSetOtherDatam.getString("CONDENSING_TYPES"));
								bean.setTypeOfInstr(resultSetOtherDatam.getString("TYPE_OF_INSTR"));

								bean.setInstrMounting(resultSetOtherDatam.getString("INSTR_MOUNTING"));
								bean.setQuantity1001Logic(resultSetOtherDatam.getInt("QTY_1001_LOGIC"));
								bean.setQuantity1002Logic(resultSetOtherDatam.getInt("QTY_1002_LOGIC"));
								bean.setQuantity2003Logic(resultSetOtherDatam.getInt("QTY_2003_LOGIC"));
								bean.setCost1001(Math.round(resultSetOtherDatam.getInt("COST_1001")));
								bean.setApprox1001Flag(resultSetOtherDatam.getInt("APPROX_1001_FLG"));
								bean.setCost1002(Math.round(resultSetOtherDatam.getInt("COST_1002")));
								bean.setApprox1002Flag(resultSetOtherDatam.getInt("APPROX_1002_FLG"));
								bean.setCost2003(Math.round(resultSetOtherDatam.getInt("COST_2003")));
								bean.setApprox20003Flag(resultSetOtherDatam.getInt("APPROX_2003_FLG"));
								bean.setCostCe1001(Math.round(resultSetOtherDatam.getInt("COST_CE_1001")));
								bean.setApprox1001CeFlag(resultSetOtherDatam.getInt("APPROX_1001_CE_FLG"));
								bean.setCostCe1002(resultSetOtherDatam.getInt("COST_CE_1002"));
								bean.setApprox1002CeFlag(resultSetOtherDatam.getInt("APPROX_1002_CE_FLG"));
								bean.setCostCe2003(Math.round(resultSetOtherDatam.getInt("COST_CE_2003")));
								bean.setApprox20003CeFlag(resultSetOtherDatam.getInt("APPROX_2003_CE_FLG"));

								bean.setPrevInstrDesc(resultSetOtherDatam.getString("PREV_INSTR_DESC"));
								bean.setPrevInstrMounting(resultSetOtherDatam.getString("PREV_INSTR_MOUNTING"));
								bean.setPrevQuantity1001Logic(resultSetOtherDatam.getInt("PREV_QTY_1001_LOGIC"));
								bean.setPrevQuantity1002Logic(resultSetOtherDatam.getInt("PREV_QTY_1002_LOGIC"));
								bean.setPrevQuantity2003Logic(resultSetOtherDatam.getInt("PREV_QTY_2003_LOGIC"));
								bean.setPrevCost1001(Math.round(resultSetOtherDatam.getInt("PREV_COST_1001")));
								bean.setPrevApprox1001Flag(resultSetOtherDatam.getInt("PREV_APPROX_1001_FLG"));
								bean.setPrevCost1002(Math.round(resultSetOtherDatam.getInt("PREV_COST_1002")));
								bean.setPrevApprox1002Flag(resultSetOtherDatam.getInt("PREV_APPROX_1002_FLG"));
								bean.setPrevCost2003(Math.round(resultSetOtherDatam.getInt("PREV_COST_2003")));
								bean.setPrevApprox20003Flag(resultSetOtherDatam.getInt("PREV_APPROX_2003_FLG"));
								bean.setPrevCostCe1001(Math.round(resultSetOtherDatam.getInt("PREV_COST_CE_1001")));
								bean.setPrevApprox1001CeFlag(resultSetOtherDatam.getInt("PREV_APPROX_1001_CE_FLG"));
								bean.setPrevCostCe1002(resultSetOtherDatam.getInt("PREV_COST_CE_1002"));
								bean.setPrevApprox1002CeFlag(resultSetOtherDatam.getInt("PREV_APPROX_1002_CE_FLG"));
								bean.setPrevCostCe2003(Math.round(resultSetOtherDatam.getInt("PREV_COST_CE_2003")));
								bean.setPrevApprox20003CeFlag(resultSetOtherDatam.getInt("PREV_APPROX_2003_CE_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdatePriceElectricalList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setInstrId(resultSetDatam.getInt("INSTR_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setCategory(resultSetDatam.getString("CATEGORY"));

								bean.setMake(resultSetDatam.getString("MAKE"));

								bean.setTypeOfGov(resultSetDatam.getString("TYPE_OF_GOV"));

								bean.setTurbCode(resultSetDatam.getString("TURB_CD"));
								bean.setTurbCodeNm(resultSetDatam.getString("TURB_CD_NM"));
								bean.setCondensingTypeId(resultSetDatam.getInt("COND_TYP_ID"));
								bean.setCondensingType(resultSetDatam.getString("CONDENSING_TYPES"));
								bean.setTypeOfInstr(resultSetDatam.getString("TYPE_OF_INSTR"));
								bean.setInstrTypeNm(resultSetDatam.getString("INSTR_TYPE_NM"));
								bean.setPrevInstrDesc(resultSetDatam.getString("PREV_INSTR_DESC"));
								bean.setPrevInstrMounting(resultSetDatam.getString("PREV_INSTR_MOUNTING"));
								bean.setPrevQuantity1001Logic(resultSetDatam.getInt("PREV_QTY_1001_LOGIC"));
								bean.setPrevQuantity1002Logic(resultSetDatam.getInt("PREV_QTY_1002_LOGIC"));
								bean.setPrevQuantity2003Logic(resultSetDatam.getInt("PREV_QTY_2003_LOGIC"));
								bean.setPrevCost1001(Math.round(resultSetDatam.getInt("PREV_COST_1001")));
								bean.setPrevApprox1001Flag(resultSetDatam.getInt("PREV_APPROX_1001_FLG"));
								bean.setPrevCost1002(Math.round(resultSetDatam.getInt("PREV_COST_1002")));
								bean.setPrevApprox1002Flag(resultSetDatam.getInt("PREV_APPROX_1002_FLG"));
								bean.setPrevCost2003(Math.round(resultSetDatam.getInt("PREV_COST_2003")));
								bean.setPrevApprox20003Flag(resultSetDatam.getInt("PREV_APPROX_2003_FLG"));
								bean.setPrevCostCe1001(Math.round(resultSetDatam.getInt("PREV_COST_CE_1001")));
								bean.setPrevApprox1001CeFlag(resultSetDatam.getInt("PREV_APPROX_1001_CE_FLG"));
								bean.setPrevCostCe1002(resultSetDatam.getInt("PREV_COST_CE_1002"));
								bean.setPrevApprox1002CeFlag(resultSetDatam.getInt("PREV_APPROX_1002_CE_FLG"));
								bean.setPrevCostCe2003(Math.round(resultSetDatam.getInt("PREV_COST_CE_2003")));
								bean.setPrevApprox20003CeFlag(resultSetDatam.getInt("PREV_APPROX_2003_CE_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdatePriceElectricalList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_INSTRUMENT_LIST_ADDITIONAL")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {

	
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setAddInstrId(resultSetOtherDatam.getInt("ADD_INSTR_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setAddInstrCd(resultSetOtherDatam.getString("ADD_INSTR_CD"));
								bean.setAddInstrNm(resultSetOtherDatam.getString("ADD_INSTR_NM"));

								bean.setCost(resultSetOtherDatam.getFloat("COST"));
								bean.setPrevCost(resultSetOtherDatam.getFloat("PREV_COST"));
								
								
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdatePriceElectricalList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();
								
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setAddInstrId(resultSetOtherDatam.getInt("ADD_INSTR_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setAddInstrCd(resultSetOtherDatam.getString("ADD_INSTR_CD"));
								bean.setAddInstrNm(resultSetOtherDatam.getString("ADD_INSTR_NM"));
								bean.setPrevCost(resultSetOtherDatam.getFloat("PREV_COST"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdatePriceElectricalList().add(bean);
							}
						}

					
					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_VMS_FRM_LIST")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {

								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setVmsId(resultSetOtherDatam.getInt("VMS_ID"));
								bean.setTypeOfPanel(resultSetOtherDatam.getString("TYPE_OF_PANEL"));
								bean.setFrameId(resultSetOtherDatam.getInt("FRM_ID"));
								bean.setFrameName(resultSetOtherDatam.getString("FRM_NM"));
								bean.setAddPrbFlag(resultSetOtherDatam.getInt("ADD_PRB_FLG"));
								bean.setAltMake(resultSetOtherDatam.getString("ALT_MAKE"));
								bean.setAltMakeDefaultFlag(resultSetOtherDatam.getInt("ALT_MAKE_DEFLT_FLG"));
								bean.setGearbox(resultSetOtherDatam.getString("GEAR_BOX"));
								bean.setGearBoxDefaultFlag(resultSetOtherDatam.getInt("GEAR_BOX_DEFLT_FLG"));

								bean.setType(resultSetOtherDatam.getString("TYPE"));
								bean.setTypeDefaultFlag(resultSetOtherDatam.getInt("TYPE_DEFLT_FLG"));

								bean.setMake(resultSetOtherDatam.getString("MAKE"));
								bean.setMakeDefaultFlag(resultSetOtherDatam.getInt("MAKE_DEFLT_FLG"));

								bean.setNote(resultSetOtherDatam.getString("NOTE"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevCost(Math.round(resultSetOtherDatam.getFloat("PREV_COST")));
								bean.setPrevNote(resultSetOtherDatam.getString("PREV_NOTE"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdatePriceElectricalList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setVmsId(resultSetDatam.getInt("VMS_ID"));
								bean.setTypeOfPanel(resultSetDatam.getString("TYPE_OF_PANEL"));
								bean.setFrameId(resultSetDatam.getInt("FRM_ID"));
								bean.setFrameName(resultSetDatam.getString("FRM_NM"));
								bean.setAddPrbFlag(resultSetDatam.getInt("ADD_PRB_FLG"));
								bean.setAltMakeDefaultFlag(resultSetDatam.getInt("ALT_MAKE_DEFLT_FLG"));
								bean.setGearbox(resultSetDatam.getString("GEAR_BOX"));
								bean.setGearBoxDefaultFlag(resultSetDatam.getInt("GEAR_BOX_DEFLT_FLG"));
								bean.setType(resultSetDatam.getString("TYPE"));
								bean.setTypeDefaultFlag(resultSetDatam.getInt("TYPE_DEFLT_FLG"));
								bean.setMake(resultSetDatam.getString("MAKE"));
								bean.setMakeDefaultFlag(resultSetDatam.getInt("MAKE_DEFLT_FLG"));
								bean.setNote(resultSetDatam.getString("NOTE"));
								bean.setApproxCostFlag(resultSetDatam.getInt("APPROX_COST_FLG"));
								bean.setPrevCost(Math.round(resultSetDatam.getFloat("PREV_COST")));
								bean.setPrevNote(resultSetDatam.getString("PREV_NOTE"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdatePriceElectricalList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_INSTRUMENT_LIST_AUTO_SPARY")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {

								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setId(resultSetOtherDatam.getInt("ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setFrameId(resultSetOtherDatam.getInt("FRM_ID"));
								bean.setFrameName(resultSetOtherDatam.getString("FRM_NM"));
								bean.setTypeOfInstr(resultSetOtherDatam.getString("TYPE_OF_INSTR"));
								bean.setCost1001(Math.round(resultSetOtherDatam.getInt("COST_1001")));
								bean.setApprox1001Flag(resultSetOtherDatam.getInt("APPROX_1001_FLG"));
								bean.setCost1002(Math.round(resultSetOtherDatam.getInt("COST_1002")));
								bean.setApprox1002Flag(resultSetOtherDatam.getInt("APPROX_1002_FLG"));
								bean.setCost2003(Math.round(resultSetOtherDatam.getInt("COST_2003")));
								bean.setApprox20003Flag(resultSetOtherDatam.getInt("APPROX_2003_FLG"));
								bean.setCostCe1001(Math.round(resultSetOtherDatam.getInt("COST_CE_1001")));
								bean.setApprox1001CeFlag(resultSetOtherDatam.getInt("APPROX_1001_CE_FLG"));
								bean.setCostCe1002(resultSetOtherDatam.getInt("COST_CE_1002"));
								bean.setApprox1002CeFlag(resultSetOtherDatam.getInt("APPROX_1002_CE_FLG"));
								bean.setCostCe2003(Math.round(resultSetOtherDatam.getInt("COST_CE_2003")));
								bean.setApprox20003CeFlag(resultSetOtherDatam.getInt("APPROX_2003_CE_FLG"));

								bean.setPrevCost1001(Math.round(resultSetOtherDatam.getInt("PREV_COST_1001")));
								bean.setPrevApprox1001Flag(resultSetOtherDatam.getInt("PREV_APPROX_1001_FLG"));
								bean.setPrevCost1002(Math.round(resultSetOtherDatam.getInt("PREV_COST_1002")));
								bean.setPrevApprox1002Flag(resultSetOtherDatam.getInt("PREV_APPROX_1002_FLG"));
								bean.setPrevCost2003(Math.round(resultSetOtherDatam.getInt("PREV_COST_2003")));
								bean.setPrevApprox20003Flag(resultSetOtherDatam.getInt("PREV_APPROX_2003_FLG"));
								bean.setPrevCostCe1001(Math.round(resultSetOtherDatam.getInt("PREV_COST_CE_1001")));
								bean.setPrevApprox1001CeFlag(resultSetOtherDatam.getInt("PREV_APPROX_1001_CE_FLG"));
								bean.setPrevCostCe1002(resultSetOtherDatam.getInt("PREV_COST_CE_1002"));
								bean.setPrevApprox1002CeFlag(resultSetOtherDatam.getInt("PREV_APPROX_1002_CE_FLG"));
								bean.setPrevCostCe2003(Math.round(resultSetOtherDatam.getInt("PREV_COST_CE_2003")));
								bean.setPrevApprox20003CeFlag(resultSetOtherDatam.getInt("PREV_APPROX_2003_CE_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdatePriceElectricalList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setId(resultSetDatam.getInt("ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setFrameId(resultSetDatam.getInt("FRM_ID"));
								bean.setFrameName(resultSetDatam.getString("FRM_NM"));
								bean.setTypeOfInstr(resultSetDatam.getString("TYPE_OF_INSTR"));

								bean.setPrevCost1001(Math.round(resultSetDatam.getInt("PREV_COST_1001")));
								bean.setPrevApprox1001Flag(resultSetDatam.getInt("PREV_APPROX_1001_FLG"));
								bean.setPrevCost1002(Math.round(resultSetDatam.getInt("PREV_COST_1002")));
								bean.setPrevApprox1002Flag(resultSetDatam.getInt("PREV_APPROX_1002_FLG"));
								bean.setPrevCost2003(Math.round(resultSetDatam.getInt("PREV_COST_2003")));
								bean.setPrevApprox20003Flag(resultSetDatam.getInt("PREV_APPROX_2003_FLG"));
								bean.setPrevCostCe1001(Math.round(resultSetDatam.getInt("PREV_COST_CE_1001")));
								bean.setPrevApprox1001CeFlag(resultSetDatam.getInt("PREV_APPROX_1001_CE_FLG"));
								bean.setPrevCostCe1002(resultSetDatam.getInt("PREV_COST_CE_1002"));
								bean.setPrevApprox1002CeFlag(resultSetDatam.getInt("PREV_APPROX_1002_CE_FLG"));
								bean.setPrevCostCe2003(Math.round(resultSetDatam.getInt("PREV_COST_CE_2003")));
								bean.setPrevApprox20003CeFlag(resultSetDatam.getInt("PREV_APPROX_2003_CE_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdatePriceElectricalList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_CREATE_EC")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherReq = callableStatement.getResultSet();
							while (resultSetOtherReq.next()) {
								ErectionCommissionBean bean = new ErectionCommissionBean();
								bean.setUpdateRequestNo(resultSetOtherReq.getInt("UPD_REQ_NO"));
								bean.setEcId(resultSetOtherReq.getInt("EC_ID"));
								bean.setFramePowerId(resultSetOtherReq.getInt("FRM_POW_ID"));
								bean.setFrameDesc(resultSetOtherReq.getString("FRM_NM"));
								bean.setCondensingTypeId(resultSetOtherReq.getInt("COND_TYP_ID"));
								bean.setCondensingType(resultSetOtherReq.getString("CONDENSING_TYPES"));

								bean.setTypeOfChargeId(resultSetOtherReq.getInt("TYP_OF_CHARGE_ID"));
								bean.setTypeOfCharge(resultSetOtherReq.getString("TYP_OF_CHARGE"));
								bean.setLoadingId(resultSetOtherReq.getInt("LOADING_ID"));
								bean.setLoadingName(resultSetOtherReq.getString("LOADING_TYPE"));
								bean.setLodgingId(resultSetOtherReq.getInt("LOADGING_ID"));
								bean.setLodgingName(resultSetOtherReq.getString("LOADGING_TYPE"));

								bean.setPrice(resultSetOtherReq.getInt("PRICE"));
								bean.setPreviousPrice(resultSetOtherReq.getInt("PREV_PRICE"));
								bean.setTurbineCode(resultSetOtherReq.getString("TURB_CD"));
								bean.setTurbineDesignCode(resultSetOtherReq.getString("TURB_DESN"));

								if (bean.getTurbineCode().equals("BP")) {
									bean.setTurbineType("Back Pressure");
								} else if (bean.getTurbineCode().equals("CD")) {
									bean.setTurbineType("Condensing");
								}
								if (bean.getTurbineDesignCode().equalsIgnoreCase("IM")) {
									bean.setTurbineDesign("Impulse");
								} else if (bean.getTurbineDesignCode().equalsIgnoreCase("RN")) {
									bean.setTurbineDesign("Reaction");
								}

								bean.setMaxPower(resultSetOtherReq.getInt("MAX_POWER"));
								bean.setStateId(resultSetOtherReq.getInt("STATUS_ID"));
								bean.setStateName(resultSetOtherReq.getString("STATUS_NAME"));
								bean.setAssignedToId(resultSetOtherReq.getInt("ASSIGHNED_TO_ID"));

								bean.setAssignedToname(resultSetOtherReq.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherReq.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherReq.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherReq.getString("CREATED_BY"));
								//
								quotationForm.getSavedUpdateECPriceList().add(bean);

							}
						}

						if (callableStatement.getMoreResults()) {
							resultEcData = callableStatement.getResultSet();
							while (resultEcData.next()) {

								ErectionCommissionBean bean = new ErectionCommissionBean();
								bean.setUpdateRequestNo(resultEcData.getInt("UPD_REQ_NO"));
								bean.setEcId(resultEcData.getInt("EC_ID"));
								bean.setFramePowerId(resultEcData.getInt("FRM_POW_ID"));
								bean.setFrameDesc(resultEcData.getString("FRM_NM"));
								bean.setCondensingTypeId(resultEcData.getInt("COND_TYP_ID"));
								bean.setCondensingType(resultEcData.getString("CONDENSING_TYPES"));
								bean.setEcTypeId(resultEcData.getInt("EC_TYP_ID"));
								// bean.setEcType(resultEcData.getString("EC_TYP"));
								bean.setTypeOfChargeId(resultEcData.getInt("TYP_OF_CHARGE_ID"));
								bean.setTypeOfCharge(resultEcData.getString("TYP_OF_CHARGE"));
								bean.setLoadingId(resultEcData.getInt("LOADING_ID"));
								bean.setLoadingName(resultEcData.getString("LOADING_TYPE"));
								bean.setLodgingId(resultEcData.getInt("LOADGING_ID"));
								bean.setLodgingName(resultEcData.getString("LOADGING_TYPE"));
								// bean.setPrice(resultEcData.getInt("PRICE"));
								bean.setPreviousPrice(resultEcData.getInt("PREV_PRICE"));
								bean.setTurbineCode(resultEcData.getString("TURB_CD"));
								bean.setTurbineDesignCode(resultEcData.getString("TURB_DESN"));
								if (bean.getTurbineCode().equals("BP")) {
									bean.setTurbineType("Back Pressure");
								} else if (bean.getTurbineCode().equals("CD")) {
									bean.setTurbineType("Condensing");
								}
								if (bean.getTurbineDesignCode().equalsIgnoreCase("IM")) {
									bean.setTurbineDesign("Impulse");
								} else if (bean.getTurbineDesignCode().equalsIgnoreCase("RN")) {
									bean.setTurbineDesign("Reaction");
								}

								bean.setMaxPower(resultEcData.getInt("MAX_POWER"));
								bean.setStateId(resultEcData.getInt("STATUS_ID"));
								bean.setStateName(resultEcData.getString("STATUS_NAME"));
								bean.setAssignedToId(resultEcData.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedToname(resultEcData.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultEcData.getString("MOD_DT"));
								bean.setCreatedById(resultEcData.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultEcData.getString("CREATED_BY"));

								quotationForm.getUnsavedUpdateECPriceList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_MECH_AUX_FRM_SPEC_DATA")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {
								DBOBean bean = new DBOBean();
								
	
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setId(resultSetOtherDatam.getInt("ID"));
								bean.setFrameId(resultSetOtherDatam.getInt("FRM_ID"));
								bean.setFrameName(resultSetOtherDatam.getString("FRM_NM"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setColId(resultSetOtherDatam.getInt("COL_ID"));
								bean.setColNm(resultSetOtherDatam.getString("COL_NM"));
								bean.setColValCd(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setMinVal(resultSetOtherDatam.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSetOtherDatam.getFloat("MAX_VAL"));
								bean.setPrevColValCd(resultSetOtherDatam.getString("PREV_COL_VAL_CD"));
								bean.setPrevMinVal(resultSetOtherDatam.getFloat("PREV_MIN_VAL"));
								bean.setPrevMaxVal(resultSetOtherDatam.getFloat("PREV_MAX_VAL"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateMechPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setId(resultSetDatam.getInt("ID"));
								bean.setFrameId(resultSetDatam.getInt("FRM_ID"));
								bean.setFrameName(resultSetDatam.getString("FRM_NM"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setColId(resultSetDatam.getInt("COL_ID"));
								bean.setColNm(resultSetDatam.getString("COL_NM"));
								bean.setColValCd(resultSetDatam.getString("COL_VAL_CD"));
								bean.setPrevColValCd(resultSetDatam.getString("PREV_COL_VAL_CD"));
								bean.setPrevMinVal(resultSetDatam.getFloat("PREV_MIN_VAL"));
								bean.setPrevMaxVal(resultSetDatam.getFloat("PREV_MAX_VAL"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdateMechPriceList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_F2F_FRM_SPEC_DATA")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {


								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setId(resultSetOtherDatam.getInt("ID"));
								bean.setFrameId(resultSetOtherDatam.getInt("FRM_ID"));
								bean.setFrameName(resultSetOtherDatam.getString("FRM_NM"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
								bean.setSubItemTypeId(resultSetOtherDatam.getInt("SUB_ITEM_TYP_ID"));
								bean.setSubItemTypeName(resultSetOtherDatam.getString("SUB_ITEM_TYP_NAME"));
								bean.setColId(resultSetOtherDatam.getInt("COL_ID"));
								bean.setColNm(resultSetOtherDatam.getString("COL_NM"));
								bean.setColValCd(resultSetOtherDatam.getString("COL_VAL_CD"));
								bean.setMinVal(resultSetOtherDatam.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSetOtherDatam.getFloat("MAX_VAL"));
								bean.setPrevColValCd(resultSetOtherDatam.getString("PREV_COL_VAL_CD"));
								bean.setPrevMinVal(resultSetOtherDatam.getFloat("PREV_MIN_VAL"));
								bean.setPrevMaxVal(resultSetOtherDatam.getFloat("PREV_MAX_VAL"));
								bean.setPrevCost(resultSetOtherDatam.getFloat("PREV_COST"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdateF2fPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {
								DBOBean bean = new DBOBean();
	

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setId(resultSetDatam.getInt("ID"));
								bean.setFrameId(resultSetDatam.getInt("FRM_ID"));
								bean.setFrameName(resultSetDatam.getString("FRM_NM"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetDatam.getString("SUB_ITEM_NAME"));
								bean.setSubItemTypeId(resultSetDatam.getInt("SUB_ITEM_TYP_ID"));
								bean.setSubItemTypeName(resultSetDatam.getString("SUB_ITEM_TYP_NAME"));
								bean.setColId(resultSetDatam.getInt("COL_ID"));
								bean.setColNm(resultSetDatam.getString("COL_NM"));
								bean.setColValCd(resultSetDatam.getString("COL_VAL_CD"));
								bean.setPrevColValCd(resultSetDatam.getString("PREV_COL_VAL_CD"));
								bean.setPrevMinVal(resultSetDatam.getFloat("PREV_MIN_VAL"));
								bean.setPrevMaxVal(resultSetDatam.getFloat("PREV_MAX_VAL"));
								bean.setPrevCost(resultSetDatam.getFloat("PREV_COST"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdateF2fPriceList().add(bean);
							}
						}
						// edit mode ele price
					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_ELE_PRICE")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherDatam = callableStatement.getResultSet();
							while (resultSetOtherDatam.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSetOtherDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetOtherDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetOtherDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetOtherDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetOtherDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetOtherDatam.getString("SUB_ITEM_NAME"));
								bean.setTypeOfPanel(resultSetOtherDatam.getString("TYPE_OF_PANEL"));
								bean.setMake(resultSetOtherDatam.getString("MAKE"));
								bean.setCustType(resultSetOtherDatam.getString("CUST_TYPE"));
								bean.setPriceCode(resultSetOtherDatam.getString("PRICE_CODE"));
								bean.setApproxCostFlag(resultSetOtherDatam.getInt("APPROX_COST_FLG"));
								bean.setItemErrMessage(resultSetOtherDatam.getString("ITEM_ERROR_MSG"));
								bean.setPrice15(resultSetOtherDatam.getInt("PRICE_15"));
								bean.setPrevPrice(resultSetOtherDatam.getInt("PREV_PRICE"));
								bean.setPrevApproxCostFlag(resultSetOtherDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetOtherDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetOtherDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetOtherDatam.getInt("IS_ACTIVE"));

								quotationForm.getSavedUpdatePriceElectricalList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetDatam = callableStatement.getResultSet();
							while (resultSetDatam.next()) {

								DBOBean bean = new DBOBean();

								bean.setUpdateRequestNumber(resultSetDatam.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSetDatam.getInt("PRICE_ID"));
								bean.setItemId(resultSetDatam.getInt("ITEM_ID"));
								bean.setItemName(resultSetDatam.getString("ITEM_NAME"));
								bean.setSubItemId(resultSetDatam.getInt("SUB_ITEM_ID"));
								bean.setSubItemName(resultSetDatam.getString("SUB_ITEM_NAME"));
								bean.setTypeOfPanel(resultSetDatam.getString("TYPE_OF_PANEL"));
								bean.setMake(resultSetDatam.getString("MAKE"));
								bean.setCustType(resultSetDatam.getString("CUST_TYPE"));
								bean.setPriceCode(resultSetDatam.getString("PRICE_CODE"));
								bean.setItemErrMessage(resultSetDatam.getString("ITEM_ERROR_MSG"));
								bean.setPrevPrice(resultSetDatam.getInt("PREV_PRICE"));
								bean.setPrevApproxCostFlag(resultSetDatam.getInt("PREV_APPROX_COST_FLG"));
								bean.setStatusId(resultSetDatam.getInt("STATUS_ID"));
								bean.setStatusName(resultSetDatam.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDatam.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedTo(resultSetDatam.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDatam.getString("MOD_DT"));
								bean.setCreatedById(resultSetDatam.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDatam.getString("CREATED_BY"));
								bean.setActiveNew(resultSetDatam.getInt("IS_ACTIVE"));

								quotationForm.getUnsavedUpdatePriceElectricalList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_CREATE_PKG")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherData1 = callableStatement.getResultSet();
							while (resultSetOtherData1.next()) {

								PackageBean bean = new PackageBean();
								quotationForm.getSaveBasicDetails()
										.setUpdateRequestNumber(resultSetOtherData1.getInt("UPD_REQ_NO"));
								bean.setPkgId(resultSetOtherData1.getInt("PKG_ID"));
								bean.setCustCode(resultSetOtherData1.getString("CUST_TYP"));
								bean.setFrameId(resultSetOtherData1.getInt("FRM_ID"));
								bean.setFrameName(resultSetOtherData1.getString("FRAME_NAME"));
								bean.setCondenTypeId(resultSetOtherData1.getInt("COND_TYP_ID"));
								bean.setCondensingType(resultSetOtherData1.getString("COND_TYP_NAME"));
								bean.setPkgTypeId(resultSetOtherData1.getInt("PKG_TYP"));
								bean.setPkgType(resultSetOtherData1.getString("PKG_TYP_NAME"));
								bean.setPrice(Math.round(resultSetOtherData1.getFloat("PRICE")));
								bean.setPrevPrice(Math.round(resultSetOtherData1.getFloat("PREV_PRICE")));
								bean.setTurbCode(resultSetOtherData1.getString("TURB_CD"));
								// bean.setTurbDesgnCode(resultSetOtherData1.getString("TURB_DESN"));
								bean.setTurbDesgnCode(resultSetOtherData1.getString("TURB_DESN"));
								if (bean.getTurbCode().equals("BP")) {
									bean.setTurbTypeName("Back Pressure");
								} else if (bean.getTurbCode().equals("CD")) {
									bean.setTurbTypeName("Condensing");
								}
								if (bean.getTurbDesgnCode().equalsIgnoreCase("IM")) {
									bean.setTurbDesgnName("Impulse");
								} else if (bean.getTurbDesgnCode().equalsIgnoreCase("RN")) {
									bean.setTurbDesgnName("Reaction");
								}

								// bean.setMaxPower(resultSetOtherData1.getFloat("MAX_POWER"));
								bean.setStatusId(resultSetOtherData1.getInt("STATUS_ID"));
								bean.setStatusName(resultSetOtherData1.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherData1.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetOtherData1.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherData1.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherData1.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherData1.getString("CREATED_BY"));

								quotationForm.getSavedUpdatePKGPriceList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetData1 = callableStatement.getResultSet();
							while (resultSetData1.next()) {

								PackageBean bean = new PackageBean();
								quotationForm.getSaveBasicDetails()
										.setUpdateRequestNumber(resultSetData1.getInt("UPD_REQ_NO"));
								bean.setPkgId(resultSetData1.getInt("PKG_ID"));
								bean.setCustCode(resultSetData1.getString("CUST_TYP"));

								bean.setFrameId(resultSetData1.getInt("FRM_ID"));
								bean.setFrameName(resultSetData1.getString("FRAME_NAME"));
								bean.setCondenTypeId(resultSetData1.getInt("COND_TYP_ID"));
								bean.setCondensingType(resultSetData1.getString("COND_TYP_NAME"));
								bean.setPkgTypeId(resultSetData1.getInt("PKG_TYP"));
								bean.setPkgType(resultSetData1.getString("PKG_TYP_NAME"));
								bean.setPrice(Math.round(resultSetData1.getFloat("PRICE")));
								bean.setPrevPrice(Math.round(resultSetData1.getFloat("PREV_PRICE")));
								bean.setTurbCode(resultSetData1.getString("TURB_CD"));
								bean.setTurbDesgnCode(resultSetData1.getString("TURB_DESN"));
								bean.setStatusId(resultSetData1.getInt("STATUS_ID"));
								bean.setStatusName(resultSetData1.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetData1.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetData1.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetData1.getString("MOD_DT"));
								bean.setCreatedById(resultSetData1.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetData1.getString("CREATED_BY"));

								quotationForm.getUnsavedUpdatePKGPriceList().add(bean);
							}
						}

					} else if (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_CREATE_TR_DM")) {
						if (callableStatement.getMoreResults()) {
							resultSetOtherData2 = callableStatement.getResultSet();
							while (resultSetOtherData2.next()) {
								TransportationDetailsBean bean = new TransportationDetailsBean();
								bean.setUpdateRequestNumber(resultSetOtherData2.getInt("UPD_REQ_NO"));
								bean.setTransId(resultSetOtherData2.getInt("TRNS_ID"));

								bean.setTransTypeId(resultSetOtherData2.getInt("TRNS_TYP_ID"));
								bean.setTransType(resultSetOtherData2.getString("TRNS_TYP"));
								bean.setFrameId(resultSetOtherData2.getInt("FRM_ID"));
								bean.setFrameName(resultSetOtherData2.getString("FRM_NM"));
								bean.setCondensingTypeId(resultSetOtherData2.getInt("COND_TYP_ID"));
								bean.setCondensingTypeName(resultSetOtherData2.getString("CONDENSING_TYPES"));
								bean.setFobId(resultSetOtherData2.getInt("FOB_ID"));
								bean.setFOBPlace(resultSetOtherData2.getString("FOB_PLACE"));
								bean.setPrevPlaceId(resultSetOtherData2.getInt("PREV_FOB_ID"));
								bean.setPrevFOBPlace(resultSetOtherData2.getString("PREV_FOB_PLACE"));
								bean.setVehicleId(resultSetOtherData2.getInt("VEHICLE_ID"));
								bean.setVehicleName(resultSetOtherData2.getString("VEHICLE_NAME"));
								bean.setPrevVehicleId(resultSetOtherData2.getInt("PREV_VEHICLE_ID"));
								bean.setPrevVehicleName(resultSetOtherData2.getString("PREV_VEHICLE_NAME"));
								bean.setCompoId(resultSetOtherData2.getInt("COMP_ID"));
								bean.setCompoName(resultSetOtherData2.getString("COMP_NAME"));
								bean.setPrevCompoId(resultSetOtherData2.getInt("PREV_COMP_ID"));
								bean.setPrevCompoName(resultSetOtherData2.getString("PREV_COMP_NAME"));
								bean.setNumberOfVehicle(resultSetOtherData2.getInt("NO_OF_VEHICLE"));
								bean.setPrevNoOfvehicles(resultSetOtherData2.getInt("PREV_NO_OF_VEHICLE"));
								bean.setTurbineCode(resultSetOtherData2.getString("TURB_CD"));

								if (bean.getTurbineCode().equals("CD")) {
									bean.setTurbineType("Condensing");
								} else if (bean.getTurbineCode().equals("BP")) {
									bean.setTurbineType("Back Pressure");
								}
								bean.setTurbineDesignCode(resultSetOtherData2.getString("TURB_DESN"));
								if (bean.getTurbineDesignCode().equals("IM")) {
									bean.setTurbineDesign("Impulse");
								} else if (bean.getTurbineDesignCode().equals("RN")) {
									bean.setTurbineDesign("Reaction");
								}
								// bean.setMaxPower(resultSetOtherData2.getInt("MAX_POWER"));
								bean.setStatusId(resultSetOtherData2.getInt("STATUS_ID"));
								bean.setStatus(resultSetOtherData2.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetOtherData2.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetOtherData2.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetOtherData2.getString("MOD_DT"));
								bean.setCreatedById(resultSetOtherData2.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetOtherData2.getString("CREATED_BY"));

								quotationForm.getSavedUpdatePriceTransportListDomestic().add(bean);

							}
						}

						if (callableStatement.getMoreResults()) {
							resultSetData2 = callableStatement.getResultSet();
							while (resultSetData2.next()) {
								TransportationDetailsBean bean = new TransportationDetailsBean();
								bean.setUpdateRequestNumber(resultSetData2.getInt("UPD_REQ_NO"));
								bean.setTransId(resultSetData2.getInt("TRNS_ID"));
								bean.setTransTypeId(resultSetData2.getInt("TRNS_TYP_ID"));
								bean.setTransType(resultSetData2.getString("TRNS_TYP"));
								bean.setFrameId(resultSetData2.getInt("FRM_ID"));
								bean.setFrameName(resultSetData2.getString("FRM_NM"));
								bean.setCondensingTypeId(resultSetData2.getInt("COND_TYP_ID"));
								bean.setCondensingTypeName(resultSetData2.getString("CONDENSING_TYPES"));
								bean.setPrevPlaceId(resultSetData2.getInt("PREV_FOB_ID"));
								bean.setPrevFOBPlace(resultSetData2.getString("PREV_FOB_PLACE"));
								bean.setPrevVehicleId(resultSetData2.getInt("PREV_VEHICLE_ID"));
								bean.setPrevVehicleName(resultSetData2.getString("PREV_VEHICLE_NAME"));
								bean.setPrevCompoId(resultSetData2.getInt("PREV_COMP_ID"));
								bean.setPrevCompoName(resultSetData2.getString("PREV_COMP_NAME"));
								bean.setPrevNoOfvehicles(resultSetData2.getInt("PREV_NO_OF_VEHICLE"));
								bean.setTurbineCode(resultSetData2.getString("TURB_CD"));
								if (bean.getTurbineCode().equals("CD")) {
									bean.setTurbineType("Condensing");
								} else if (bean.getTurbineCode().equals("BP")) {
									bean.setTurbineType("Back Pressure");
								}
								bean.setTurbineDesignCode(resultSetData2.getString("TURB_DESN"));
								if (bean.getTurbineDesignCode().equals("IM")) {
									bean.setTurbineDesign("Impulse");
								} else if (bean.getTurbineDesignCode().equals("RN")) {
									bean.setTurbineDesign("Reaction");
								}

								bean.setStatusId(resultSetData2.getInt("STATUS_ID"));
								bean.setStatus(resultSetData2.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetData2.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetData2.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetData2.getString("MOD_DT"));
								bean.setCreatedById(resultSetData2.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetData2.getString("CREATED_BY"));

								quotationForm.getUnsavedUpdatePriceTransportListDomestic().add(bean);
							}
						}
					} else if (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_CREATE_TR_EX")) {
						if (callableStatement.getMoreResults()) {
							resultSetExportData = callableStatement.getResultSet();

							while (resultSetExportData.next()) {

								TransportationDetailsBean bean = new TransportationDetailsBean();

								bean.setUpdateRequestNumber(resultSetExportData.getInt("UPD_REQ_NO"));
								bean.setTransId(resultSetExportData.getInt("TRNS_ID"));
								bean.setTransTypeId(resultSetExportData.getInt("TRNS_TYP_ID"));
								bean.setTransType(resultSetExportData.getString("TRNS_TYP"));
								bean.setFrameId(resultSetExportData.getInt("FRM_ID"));
								bean.setFrameName(resultSetExportData.getString("FRM_NM"));
								bean.setCondensingTypeId(resultSetExportData.getInt("COND_TYP_ID"));
								bean.setCondensingTypeName(resultSetExportData.getString("CONDENSING_TYPES"));
								bean.setPortId(resultSetExportData.getInt("PORT_ID"));
								bean.setCountryName(resultSetExportData.getString("COUNTRY_NM"));
								bean.setPortName(resultSetExportData.getString("PORT_NM"));
								bean.setPrevChennaiPrice(Math.round(resultSetExportData.getFloat("PREV_PRICE_CHENNAI")));
								bean.setChennaiPrice(Math.round(resultSetExportData.getFloat("PRICE_CHENNAI")));
								bean.setPrevPriceFob(Math.round(resultSetExportData.getFloat("PREV_PRICE_FOB")));
								bean.setPriceFob(Math.round(resultSetExportData.getFloat("PRICE_FOB")));
								bean.setPrice(Math.round(resultSetExportData.getFloat("PREV_PRICE")));
								bean.setPreviousPrice(Math.round(resultSetExportData.getFloat("PRICE")));
								bean.setTurbineCode(resultSetExportData.getString("TURB_CD"));

								if (bean.getTurbineCode().equals("CD")) {
									bean.setTurbineType("Condensing");
								} else if (bean.getTurbineCode().equals("BP")) {
									bean.setTurbineType("Back Pressure");
								}
								bean.setTurbineDesignCode(resultSetExportData.getString("TURB_DESN"));
								if (bean.getTurbineDesignCode().equals("IM")) {
									bean.setTurbineDesign("Impulse");
								} else if (bean.getTurbineDesignCode().equals("RN")) {
									bean.setTurbineDesign("Reaction");
								}

								bean.setStatusId(resultSetExportData.getInt("STATUS_ID"));
								bean.setStatus(resultSetExportData.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetExportData.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetExportData.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetExportData.getString("MOD_DT"));
								bean.setCreatedById(resultSetExportData.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetExportData.getString("CREATED_BY"));
								quotationForm.getSavedUpdatePriceTransportListExport().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							ResultSet resultSetDataEx = callableStatement.getResultSet();
							while (resultSetDataEx.next()) {

								TransportationDetailsBean bean = new TransportationDetailsBean();

								bean.setUpdateRequestNumber(resultSetDataEx.getInt("UPD_REQ_NO"));
								bean.setTransId(resultSetDataEx.getInt("TRNS_ID"));
								bean.setTransTypeId(resultSetDataEx.getInt("TRNS_TYP_ID"));
								bean.setTransType(resultSetDataEx.getString("TRNS_TYP"));
								bean.setFrameId(resultSetDataEx.getInt("FRM_ID"));
								bean.setFrameName(resultSetDataEx.getString("FRM_NM"));
								bean.setCondensingTypeId(resultSetDataEx.getInt("COND_TYP_ID"));
								bean.setCondensingTypeName(resultSetDataEx.getString("CONDENSING_TYPES"));
								bean.setPrevChennaiPrice(Math.round(resultSetExportData.getFloat("PREV_PRICE_CHENNAI")));
								bean.setPrevPriceFob(Math.round(resultSetExportData.getFloat("PREV_PRICE_FOB")));
								bean.setPreviousPrice(Math.round(resultSetDataEx.getFloat("PREV_PRICE")));
								bean.setTurbineCode(resultSetDataEx.getString("TURB_CD"));

								if (bean.getTurbineCode().equals("CD")) {
									bean.setTurbineType("Condensing");
								} else if (bean.getTurbineCode().equals("BP")) {
									bean.setTurbineType("Back Pressure");
								}
								bean.setTurbineDesignCode(resultSetDataEx.getString("TURB_DESN"));
								if (bean.getTurbineDesignCode().equals("IM")) {
									bean.setTurbineDesign("Impulse");
								} else if (bean.getTurbineDesignCode().equals("RN")) {
									bean.setTurbineDesign("Reaction");
								}
								bean.setPortId(resultSetDataEx.getInt("PORT_ID"));
								bean.setCountryName(resultSetDataEx.getString("COUNTRY_NM"));
								bean.setPortName(resultSetDataEx.getString("PORT_NM"));

								bean.setStatusId(resultSetDataEx.getInt("STATUS_ID"));
								bean.setStatus(resultSetDataEx.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetDataEx.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetDataEx.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetDataEx.getString("MOD_DT"));
								bean.setCreatedById(resultSetDataEx.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetDataEx.getString("CREATED_BY"));
								quotationForm.getUnsavedUpdatePriceTransportListExport().add(bean);
							}
						}
					} else if (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_CREATE_UBO_SHEET")) {
						if (callableStatement.getMoreResults()) {
							resultSetUBODate = callableStatement.getResultSet();
							while (resultSetUBODate.next()) {

								F2FUBOBean bean = new F2FUBOBean();

								bean.setUpdateRequestNumber(resultSetUBODate.getInt("UPD_REQ_NO"));
								bean.setF2F_ID(resultSetUBODate.getInt("F2F_MAST_ID"));
								bean.setF2F_DET_ID(resultSetUBODate.getInt("F2F_DET_ID"));
								bean.setMTRL_ID(resultSetUBODate.getInt("MTRL_ID"));
								bean.setMTRL_NM(resultSetUBODate.getString("MTRL_NM"));
								bean.setCAT_ID(resultSetUBODate.getInt("CAT_ID"));
								bean.setCAT_NM(resultSetUBODate.getString("CAT_NM"));
								bean.setFRM_POW_ID(resultSetUBODate.getInt("FRM_POW_ID"));
								bean.setFRAME_NAME(resultSetUBODate.getString("FRAME_NAME"));
								bean.setBLEED_TYP_ID(resultSetUBODate.getInt("BLEED_TYP_ID"));
								//bean.setCondensingTypeId(resultSetUBODate.getInt("COND_TYP_ID"));
								//bean.setCondensingTypeName(resultSetUBODate.getString("CONDENSING_TYPES"));
								bean.setTOTAL_PRICE(Math.round(resultSetUBODate.getFloat("TOTAL")));
								bean.setTotalPreviousPrice(Math.round(resultSetUBODate.getFloat("PREV_TOTAL")));
								bean.setPRICE(Math.round(resultSetUBODate.getFloat("PRICE")));
								bean.setPreviousPrice(Math.round(resultSetUBODate.getFloat("PREV_PRICE")));
								bean.setStatusId(resultSetUBODate.getInt("STATUS_ID"));
								bean.setStatus(resultSetUBODate.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetUBODate.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetUBODate.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetUBODate.getString("MOD_DT"));
								bean.setCreatedBy(resultSetUBODate.getString("CREATED_BY"));
								bean.setCreatedById(resultSetUBODate.getInt("CREATED_BY_ID"));
								bean.setUpdateRequestName(resultSetUBODate.getString("UPD_REQ_NAME"));

								quotationForm.getF2FUboSavedList().add(bean);
							}
						}
						if (callableStatement.getMoreResults()) {
							ResultSet resultSetUBODateunsave = callableStatement.getResultSet();
							while (resultSetUBODateunsave.next()) {

								F2FUBOBean bean = new F2FUBOBean();

								bean.setUpdateRequestNumber(resultSetUBODateunsave.getInt("UPD_REQ_NO"));
								bean.setF2F_ID(resultSetUBODateunsave.getInt("F2F_MAST_ID"));
								bean.setF2F_DET_ID(resultSetUBODateunsave.getInt("F2F_DET_ID"));
								bean.setMTRL_ID(resultSetUBODateunsave.getInt("MTRL_ID"));
								bean.setMTRL_NM(resultSetUBODateunsave.getString("MTRL_NM"));
								bean.setCAT_ID(resultSetUBODateunsave.getInt("CAT_ID"));
								bean.setCAT_NM(resultSetUBODateunsave.getString("CAT_NM"));
								bean.setFRM_POW_ID(resultSetUBODateunsave.getInt("FRM_POW_ID"));
								bean.setFRAME_NAME(resultSetUBODateunsave.getString("FRAME_NAME"));
								bean.setBLEED_TYP_ID(resultSetUBODateunsave.getInt("BLEED_TYP_ID"));
								bean.setTotalPreviousPrice(Math.round(resultSetUBODateunsave.getFloat("PREV_TOTAL")));
								bean.setPreviousPrice(Math.round(resultSetUBODateunsave.getFloat("PREV_PRICE")));
								bean.setStatusId(resultSetUBODateunsave.getInt("STATUS_ID"));
								bean.setStatus(resultSetUBODateunsave.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetUBODateunsave.getInt("ASSIGHNED_TO_ID"));
								bean.setAssingedToName(resultSetUBODateunsave.getString("ASSIGNED_TO"));
								bean.setModifiedDate(resultSetUBODateunsave.getString("MOD_DT"));
								bean.setCreatedBy(resultSetUBODateunsave.getString("CREATED_BY"));
								bean.setCreatedById(resultSetUBODateunsave.getInt("CREATED_BY_ID"));
								bean.setUpdateRequestName(resultSetUBODateunsave.getString("UPD_REQ_NAME"));
								quotationForm.getF2FUboUnsavedList().add(bean);
							}
						}
					} /*else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_TI_SHOP_CONV_COST")) {
						if (callableStatement.getMoreResults()) {
							resultSetUpdateCost = callableStatement.getResultSet();
							while (resultSetUpdateCost.next()) {

								TurbineDetails bean = new TurbineDetails();
								bean.setUpdateRequestNumber(resultSetUpdateCost.getInt("UPD_REQ_NO"));
								bean.setInstrId(resultSetUpdateCost.getInt("INSTR_ID"));
								bean.setFramePowerId(resultSetUpdateCost.getInt("FRM_POW_ID"));
								bean.setFrameDesc(resultSetUpdateCost.getString("FRM_NM"));
								bean.setCondTypId(resultSetUpdateCost.getInt("COND_TYP_ID"));
								bean.setCondensingtypes(resultSetUpdateCost.getString("CONDENSING_TYPES"));
								bean.setBleedTypId(resultSetUpdateCost.getInt("BLEED_TYP_ID"));
								bean.setBleedType(resultSetUpdateCost.getString("BLEED_TYP"));
								bean.setOverHeads(Math.round(resultSetUpdateCost.getFloat("SHOP_CONV_COST")));
								bean.setPrevOverHeads(Math.round(resultSetUpdateCost.getFloat("PREV_SHOP_CONV_COST")));
								bean.setTurbineCode(resultSetUpdateCost.getString("TURB_CD"));
								bean.setTurbineDesignCd(resultSetUpdateCost.getString("TURB_DESN"));
								bean.setMaxPower(resultSetUpdateCost.getFloat("MAX_POWER"));
								bean.setStatusId(resultSetUpdateCost.getInt("STATUS_ID"));
								bean.setStatusName(resultSetUpdateCost.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetUpdateCost.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSetUpdateCost.getString("ASSIGNED_TO"));
								bean.setModDt(resultSetUpdateCost.getDate("MOD_DT"));
								bean.setCreatedById(resultSetUpdateCost.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetUpdateCost.getString("CREATED_BY"));
								bean.setUpdReqName(resultSetUpdateCost.getString("UPD_REQ_NAME"));

								quotationForm.getSavedCostList().add(bean);
							}
						}
						if (callableStatement.getMoreResults()) {
							resultSetUpdateCostunsaved = callableStatement.getResultSet();
							while (resultSetUpdateCostunsaved.next()) {

								TurbineDetails bean = new TurbineDetails();
								bean.setUpdateRequestNumber(resultSetUpdateCostunsaved.getInt("UPD_REQ_NO"));
								bean.setInstrId(resultSetUpdateCostunsaved.getInt("INSTR_ID"));
								bean.setFramePowerId(resultSetUpdateCostunsaved.getInt("FRM_POW_ID"));
								bean.setFrameDesc(resultSetUpdateCostunsaved.getString("FRM_NM"));
								bean.setCondTypId(resultSetUpdateCostunsaved.getInt("COND_TYP_ID"));
								bean.setCondensingtypes(resultSetUpdateCostunsaved.getString("CONDENSING_TYPES"));
								bean.setBleedTypId(resultSetUpdateCostunsaved.getInt("BLEED_TYP_ID"));
								bean.setBleedType(resultSetUpdateCostunsaved.getString("BLEED_TYP"));
								bean.setPrevOverHeads(
										Math.round(resultSetUpdateCostunsaved.getFloat("PREV_SHOP_CONV_COST")));
								bean.setTurbineCode(resultSetUpdateCostunsaved.getString("TURB_CD"));
								bean.setTurbineDesignCd(resultSetUpdateCostunsaved.getString("TURB_DESN"));
								bean.setMaxPower(resultSetUpdateCostunsaved.getFloat("MAX_POWER"));
								bean.setStatusId(resultSetUpdateCostunsaved.getInt("STATUS_ID"));
								bean.setStatusName(resultSetUpdateCostunsaved.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetUpdateCostunsaved.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSetUpdateCostunsaved.getString("ASSIGNED_TO"));
								bean.setModDt(resultSetUpdateCostunsaved.getDate("MOD_DT"));
								bean.setCreatedById(resultSetUpdateCostunsaved.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetUpdateCostunsaved.getString("CREATED_BY"));
								bean.setUpdReqName(resultSetUpdateCostunsaved.getString("UPD_REQ_NAME"));

								quotationForm.getUnSavedCostList().add(bean);
							}
						}
					}*//* else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_TI_SUB_CONTR")) {
						if (callableStatement.getMoreResults()) {
							resultSetUpdateSubCost = callableStatement.getResultSet();
							while (resultSetUpdateSubCost.next()) {

								TurbineDetails bean = new TurbineDetails();
								bean.setUpdateRequestNumber(resultSetUpdateSubCost.getInt("UPD_REQ_NO"));
								bean.setInstrId(resultSetUpdateSubCost.getInt("INSTR_ID"));
								bean.setFramePowerId(resultSetUpdateSubCost.getInt("FRM_POW_ID"));
								bean.setFrameDesc(resultSetUpdateSubCost.getString("FRM_NM"));
								bean.setCondTypId(resultSetUpdateSubCost.getInt("COND_TYP_ID"));
								bean.setCondensingtypes(resultSetUpdateSubCost.getString("CONDENSING_TYPES"));
								bean.setBleedTypId(resultSetUpdateSubCost.getInt("BLEED_TYP_ID"));
								bean.setBleedType(resultSetUpdateSubCost.getString("BLEED_TYP"));
								bean.setSubContrCost(Math.round(resultSetUpdateSubCost.getFloat("SUB_CONTR_COST")));
								bean.setPrevSubContrCost(
										Math.round(resultSetUpdateSubCost.getFloat("PREV_SUB_CONTR_COST")));
								bean.setTurbineCode(resultSetUpdateSubCost.getString("TURB_CD"));
								bean.setTurbineDesignCd(resultSetUpdateSubCost.getString("TURB_DESN"));
								bean.setMaxPower(resultSetUpdateSubCost.getFloat("MAX_POWER"));
								bean.setStatusId(resultSetUpdateSubCost.getInt("STATUS_ID"));
								bean.setStatusName(resultSetUpdateSubCost.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetUpdateSubCost.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSetUpdateSubCost.getString("ASSIGNED_TO"));
								bean.setModDt(resultSetUpdateSubCost.getDate("MOD_DT"));
								bean.setCreatedById(resultSetUpdateSubCost.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetUpdateSubCost.getString("CREATED_BY"));
								bean.setUpdReqName(resultSetUpdateSubCost.getString("UPD_REQ_NAME"));

								quotationForm.getSavedCostList().add(bean);
							}
						}
						if (callableStatement.getMoreResults()) {
							resultSetUpdateSubCostunsaved = callableStatement.getResultSet();
							while (resultSetUpdateSubCostunsaved.next()) {

								TurbineDetails bean = new TurbineDetails();
								bean.setUpdateRequestNumber(resultSetUpdateSubCostunsaved.getInt("UPD_REQ_NO"));
								bean.setInstrId(resultSetUpdateSubCostunsaved.getInt("INSTR_ID"));
								bean.setFramePowerId(resultSetUpdateSubCostunsaved.getInt("FRM_POW_ID"));
								bean.setFrameDesc(resultSetUpdateSubCostunsaved.getString("FRM_NM"));
								bean.setCondTypId(resultSetUpdateSubCostunsaved.getInt("COND_TYP_ID"));
								bean.setCondensingtypes(resultSetUpdateSubCostunsaved.getString("CONDENSING_TYPES"));
								bean.setBleedTypId(resultSetUpdateSubCostunsaved.getInt("BLEED_TYP_ID"));
								bean.setBleedType(resultSetUpdateSubCostunsaved.getString("BLEED_TYP"));
								bean.setPrevSubContrCost(
										Math.round(resultSetUpdateSubCostunsaved.getFloat("PREV_SUB_CONTR_COST")));
								bean.setTurbineCode(resultSetUpdateSubCostunsaved.getString("TURB_CD"));
								bean.setTurbineDesignCd(resultSetUpdateSubCostunsaved.getString("TURB_DESN"));
								bean.setMaxPower(resultSetUpdateSubCostunsaved.getFloat("MAX_POWER"));
								bean.setStatusId(resultSetUpdateSubCostunsaved.getInt("STATUS_ID"));
								bean.setStatusName(resultSetUpdateSubCostunsaved.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetUpdateSubCostunsaved.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSetUpdateSubCostunsaved.getString("ASSIGNED_TO"));
								bean.setModDt(resultSetUpdateSubCostunsaved.getDate("MOD_DT"));
								bean.setCreatedById(resultSetUpdateSubCostunsaved.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetUpdateSubCostunsaved.getString("CREATED_BY"));
								bean.setUpdReqName(resultSetUpdateSubCostunsaved.getString("UPD_REQ_NAME"));

								quotationForm.getUnSavedCostList().add(bean);
							}
						}
					} *//*else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_CREATE_TI_TURB_INSTR")) {
						if (callableStatement.getMoreResults()) {
							resultSetUpdateTurbCost = callableStatement.getResultSet();
							while (resultSetUpdateTurbCost.next()) {

								TurbineDetails bean = new TurbineDetails();
								bean.setUpdateRequestNumber(resultSetUpdateTurbCost.getInt("UPD_REQ_NO"));
								bean.setInstrId(resultSetUpdateTurbCost.getInt("INSTR_ID"));
								bean.setFramePowerId(resultSetUpdateTurbCost.getInt("FRM_POW_ID"));
								bean.setFrameDesc(resultSetUpdateTurbCost.getString("FRM_NM"));
								bean.setCondTypId(resultSetUpdateTurbCost.getInt("COND_TYP_ID"));
								bean.setCondensingtypes(resultSetUpdateTurbCost.getString("CONDENSING_TYPES"));
								bean.setBleedTypId(resultSetUpdateTurbCost.getInt("BLEED_TYP_ID"));
								bean.setBleedType(resultSetUpdateTurbCost.getString("BLEED_TYP"));
								bean.setTurbInstrCost(Math.round(resultSetUpdateTurbCost.getFloat("TURB_INSTR_COST")));
								bean.setPrevTurbInstrCost(
										Math.round(resultSetUpdateTurbCost.getFloat("PREV_TURB_INSTR_COST")));
								bean.setCondInstrCost(Math.round(resultSetUpdateTurbCost.getFloat("COND_INSTR_COST")));
								bean.setPrevCondInstrCost(
										Math.round(resultSetUpdateTurbCost.getFloat("PREV_COND_INSTR_COST")));
								bean.setTurbineCode(resultSetUpdateTurbCost.getString("TURB_CD"));
								bean.setTurbineDesignCd(resultSetUpdateTurbCost.getString("TURB_DESN"));
								bean.setMaxPower(resultSetUpdateTurbCost.getFloat("MAX_POWER"));
								bean.setStatusId(resultSetUpdateTurbCost.getInt("STATUS_ID"));
								bean.setStatusName(resultSetUpdateTurbCost.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetUpdateTurbCost.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSetUpdateTurbCost.getString("ASSIGNED_TO"));
								bean.setModDt(resultSetUpdateTurbCost.getDate("MOD_DT"));
								bean.setCreatedById(resultSetUpdateTurbCost.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetUpdateTurbCost.getString("CREATED_BY"));
								bean.setUpdReqName(resultSetUpdateTurbCost.getString("UPD_REQ_NAME"));

								quotationForm.getSavedCostList().add(bean);
							}
						}
						if (callableStatement.getMoreResults()) {
							resultSetUpdateTurbCostunsaved = callableStatement.getResultSet();
							while (resultSetUpdateTurbCostunsaved.next()) {

								TurbineDetails bean = new TurbineDetails();
								bean.setUpdateRequestNumber(resultSetUpdateTurbCostunsaved.getInt("UPD_REQ_NO"));
								bean.setInstrId(resultSetUpdateTurbCostunsaved.getInt("INSTR_ID"));
								bean.setFramePowerId(resultSetUpdateTurbCostunsaved.getInt("FRM_POW_ID"));
								bean.setFrameDesc(resultSetUpdateTurbCostunsaved.getString("FRM_NM"));
								bean.setCondTypId(resultSetUpdateTurbCostunsaved.getInt("COND_TYP_ID"));
								bean.setCondensingtypes(resultSetUpdateTurbCostunsaved.getString("CONDENSING_TYPES"));
								bean.setBleedTypId(resultSetUpdateTurbCostunsaved.getInt("BLEED_TYP_ID"));
								bean.setBleedType(resultSetUpdateTurbCostunsaved.getString("BLEED_TYP"));
								bean.setTurbInstrCost(
										Math.round(resultSetUpdateTurbCostunsaved.getFloat("TURB_INSTR_COST")));
								bean.setPrevTurbInstrCost(
										Math.round(resultSetUpdateTurbCostunsaved.getFloat("PREV_TURB_INSTR_COST")));
								bean.setCondInstrCost(
										Math.round(resultSetUpdateTurbCostunsaved.getFloat("COND_INSTR_COST")));
								bean.setPrevCondInstrCost(
										Math.round(resultSetUpdateTurbCostunsaved.getFloat("PREV_COND_INSTR_COST")));
								bean.setTurbineCode(resultSetUpdateTurbCostunsaved.getString("TURB_CD"));
								bean.setTurbineDesignCd(resultSetUpdateTurbCostunsaved.getString("TURB_DESN"));
								bean.setMaxPower(resultSetUpdateTurbCostunsaved.getFloat("MAX_POWER"));
								bean.setStatusId(resultSetUpdateTurbCostunsaved.getInt("STATUS_ID"));
								bean.setStatusName(resultSetUpdateTurbCostunsaved.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSetUpdateTurbCostunsaved.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSetUpdateTurbCostunsaved.getString("ASSIGNED_TO"));
								bean.setModDt(resultSetUpdateTurbCostunsaved.getDate("MOD_DT"));
								bean.setCreatedById(resultSetUpdateTurbCostunsaved.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSetUpdateTurbCostunsaved.getString("CREATED_BY"));
								bean.setUpdReqName(resultSetUpdateTurbCostunsaved.getString("UPD_REQ_NAME"));

								quotationForm.getUnSavedCostList().add(bean);
							}
						}
					}*/ else if (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_DBO_MECH_COL")) {
						if (callableStatement.getMoreResults()) {
							resultSeDboMechCol = callableStatement.getResultSet();
							while (resultSeDboMechCol.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSeDboMechCol.getInt("UPD_REQ_NO"));
								bean.setColValId(resultSeDboMechCol.getInt("COL_VAL_ID"));
								bean.setItemId(resultSeDboMechCol.getInt("ITEM_ID"));
								bean.setItemName(resultSeDboMechCol.getString("ITEM_NAME"));
								bean.setColId(resultSeDboMechCol.getInt("COL_ID"));
								bean.setColNm(resultSeDboMechCol.getString("COL_NM"));
								bean.setPercentage(resultSeDboMechCol.getFloat("PERCNT"));
								bean.setPrevPercent(resultSeDboMechCol.getFloat("PREV_PERCNT"));
								bean.setDirectPrice(Math.round(resultSeDboMechCol.getFloat("DIR_PRICE")));
								bean.setPrevDirectPrice(Math.round(resultSeDboMechCol.getFloat("PREV_DIR_PRICE")));
								bean.setStatusId(resultSeDboMechCol.getInt("STATUS_ID"));
								bean.setStatusName(resultSeDboMechCol.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSeDboMechCol.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSeDboMechCol.getString("ASSIGNED_TO"));
								bean.setModDt(resultSeDboMechCol.getDate("MOD_DT"));
								bean.setCreatedById(resultSeDboMechCol.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSeDboMechCol.getString("CREATED_BY"));
								bean.setActive(resultSeDboMechCol.getInt("IS_ACTIVE") == 1 ? true : false);

								quotationForm.getSavedDboMechColList().add(bean);
							}
						}
						if (callableStatement.getMoreResults()) {
							resultSeDboMechColUnsaved = callableStatement.getResultSet();
							while (resultSeDboMechColUnsaved.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSeDboMechColUnsaved.getInt("UPD_REQ_NO"));
								bean.setColValId(resultSeDboMechColUnsaved.getInt("COL_VAL_ID"));
								bean.setItemId(resultSeDboMechColUnsaved.getInt("ITEM_ID"));
								bean.setItemName(resultSeDboMechColUnsaved.getString("ITEM_NAME"));
								bean.setColId(resultSeDboMechColUnsaved.getInt("COL_ID"));
								bean.setColNm(resultSeDboMechColUnsaved.getString("COL_NM"));
								bean.setPrevPercent(resultSeDboMechColUnsaved.getFloat("PREV_PERCNT"));
								bean.setPrevDirectPrice(
										Math.round(resultSeDboMechColUnsaved.getFloat("PREV_DIR_PRICE")));
								bean.setStatusId(resultSeDboMechColUnsaved.getInt("STATUS_ID"));
								bean.setStatusName(resultSeDboMechColUnsaved.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSeDboMechColUnsaved.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSeDboMechColUnsaved.getString("ASSIGNED_TO"));
								bean.setModDt(resultSeDboMechColUnsaved.getDate("MOD_DT"));
								bean.setCreatedById(resultSeDboMechColUnsaved.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSeDboMechColUnsaved.getString("CREATED_BY"));
								bean.setActive(resultSeDboMechColUnsaved.getInt("IS_ACTIVE") == 1 ? true : false);

								quotationForm.getUnsavedDboMechColList().add(bean);
							}
						}
						// } else if
						// (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_DBO_ELE_COL"))
						// {
						// if (callableStatement.getMoreResults()) {
						// resultSeDboEleCol = callableStatement.getResultSet();
						// while (resultSeDboEleCol.next()) {
						//
						// DBOBean bean = new DBOBean();
						// bean.setUpdateRequestNumber(resultSeDboEleCol.getInt("UPD_REQ_NO"));
						// bean.setColValId(resultSeDboEleCol.getInt("COL_VAL_ID"));
						// bean.setItemId(resultSeDboEleCol.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboEleCol.getString("ITEM_NAME"));
						// bean.setPanelCode(resultSeDboEleCol.getString("PANEL_CD"));
						// bean.setColId(resultSeDboEleCol.getInt("COL_ID"));
						// bean.setColNm(resultSeDboEleCol.getString("COL_NM"));
						// bean.setColValCd(resultSeDboEleCol.getString("COL_VAL_CD"));
						// bean.setPrevColValCd(resultSeDboEleCol.getString("PREV_COL_VAL_CD"));
						// bean.setPercentage(resultSeDboEleCol.getFloat("PERCNT"));
						// bean.setPrevPercent(resultSeDboEleCol.getFloat("PREV_PERCNT"));
						// bean.setDirectPrice(Math.round(resultSeDboEleCol.getFloat("DIR_PRICE")));
						// bean.setPrevDirectPrice(Math.round(resultSeDboEleCol.getFloat("PREV_DIR_PRICE")));
						// bean.setDefaultVal(resultSeDboEleCol.getInt("DEFLT_FLG")
						// == 1 ? true : false);
						// bean.setPrevDefaultVal(resultSeDboEleCol.getInt("PREV_DEFLT_FLG")
						// == 1 ? true : false);
						// bean.setStatusId(resultSeDboEleCol.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboEleCol.getString("STATUS_NAME"));
						// bean.setAssingedToId(resultSeDboEleCol.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboEleCol.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboEleCol.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboEleCol.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboEleCol.getString("CREATED_BY"));
						// bean.setActive(resultSeDboEleCol.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getSavedDboEleColList().add(bean);
						// }
						// }
						// if (callableStatement.getMoreResults()) {
						// resultSeDboEleColUnsaved =
						// callableStatement.getResultSet();
						// while (resultSeDboEleColUnsaved.next()) {
						// DBOBean bean = new DBOBean();
						// bean.setUpdateRequestNumber(resultSeDboEleColUnsaved.getInt("UPD_REQ_NO"));
						// bean.setColValId(resultSeDboEleColUnsaved.getInt("COL_VAL_ID"));
						// bean.setItemId(resultSeDboEleColUnsaved.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboEleColUnsaved.getString("ITEM_NAME"));
						// bean.setColId(resultSeDboEleColUnsaved.getInt("COL_ID"));
						// bean.setColNm(resultSeDboEleColUnsaved.getString("COL_NM"));
						// bean.setPrevPercent(resultSeDboEleColUnsaved.getFloat("PREV_PERCNT"));
						// bean.setPrevDirectPrice(Math.round(resultSeDboEleColUnsaved.getFloat("PREV_DIR_PRICE")));
						// bean.setStatusId(resultSeDboEleColUnsaved.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboEleColUnsaved.getString("STATUS_NAME"));
						// bean.setAssingedToId(resultSeDboEleColUnsaved.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboEleColUnsaved.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboEleColUnsaved.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboEleColUnsaved.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboEleColUnsaved.getString("CREATED_BY"));
						// bean.setActive(resultSeDboEleColUnsaved.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getUnsavedDboEleColList().add(bean);
						// }
						// }
						// } else if
						// (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_DBO_MECH_PRICE"))
						// {
						// if (callableStatement.getMoreResults()) {
						// resultSeDboMechPrice =
						// callableStatement.getResultSet();
						// while (resultSeDboMechPrice.next()) {
						//
						// DBOBean bean = new DBOBean();
						// bean.setUpdateRequestNumber(resultSeDboMechPrice.getInt("UPD_REQ_NO"));
						// bean.setPriceId(resultSeDboMechPrice.getInt("PRICE_ID"));
						// bean.setItemId(resultSeDboMechPrice.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboMechPrice.getString("ITEM_NAME"));
						// bean.setPrice(Math.round(resultSeDboMechPrice.getFloat("PRICE")));
						// bean.setPrevPrice(Math.round(resultSeDboMechPrice.getFloat("PREV_PRICE")));
						// bean.setStatusId(resultSeDboMechPrice.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboMechPrice.getString("STATUS_NAME"));
						// bean.setAssingedToId(resultSeDboMechPrice.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboMechPrice.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboMechPrice.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboMechPrice.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboMechPrice.getString("CREATED_BY"));
						// bean.setActive(resultSeDboMechPrice.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getSavedDboMechList().add(bean);
						// }
						// }
						//
						// if (callableStatement.getMoreResults()) {
						// resultSeDboMechPriceUnsaved =
						// callableStatement.getResultSet();
						// while (resultSeDboMechPriceUnsaved.next()) {
						// DBOBean bean = new DBOBean();
						// bean.setUpdateRequestNumber(resultSeDboMechPriceUnsaved.getInt("UPD_REQ_NO"));
						// bean.setPriceId(resultSeDboMechPriceUnsaved.getInt("PRICE_ID"));
						// bean.setItemId(resultSeDboMechPriceUnsaved.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboMechPriceUnsaved.getString("ITEM_NAME"));
						// //
						// bean.setPrice(Math.round(resultSeDboMechPriceUnsaved.getFloat("PRICE")));
						// bean.setPrevPrice(Math.round(resultSeDboMechPriceUnsaved.getFloat("PREV_PRICE")));
						// bean.setStatusId(resultSeDboMechPriceUnsaved.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboMechPriceUnsaved.getString("STATUS_NAME"));
						// bean.setAssingedToId(resultSeDboMechPriceUnsaved.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboMechPriceUnsaved.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboMechPriceUnsaved.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboMechPriceUnsaved.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboMechPriceUnsaved.getString("CREATED_BY"));
						// bean.setActive(resultSeDboMechPriceUnsaved.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getUnsavedDboMechList().add(bean);
						// }
						// }
						// } else if
						// (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_DBO_ELE_PRICE"))
						// {
						// if (callableStatement.getMoreResults()) {
						// resultSeDboElePrice =
						// callableStatement.getResultSet();
						// while (resultSeDboElePrice.next()) {
						//
						// DBOBean bean = new DBOBean();
						// bean.setUpdateRequestNumber(resultSeDboElePrice.getInt("UPD_REQ_NO"));
						// bean.setPriceId(resultSeDboElePrice.getInt("PRICE_ID"));
						// bean.setItemId(resultSeDboElePrice.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboElePrice.getString("ITEM_NAME"));
						// bean.setPanelCode(resultSeDboElePrice.getString("PANEL_CD"));
						// bean.setPriceCode(resultSeDboElePrice.getString("PRICE_CODE"));
						// bean.setPrice(Math.round(resultSeDboElePrice.getFloat("PRICE")));
						// bean.setPrevPrice(Math.round(resultSeDboElePrice.getFloat("PREV_PRICE")));
						// bean.setStatusId(resultSeDboElePrice.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboElePrice.getString("STATUS_NAME"));
						// bean.setCustCode(resultSeDboElePrice.getString("CUST_TYP"));
						// if (null !=
						// resultSeDboElePrice.getString("CUST_TYP")) {
						// if (bean.getCustCode().equalsIgnoreCase("DM")) {
						// bean.setCustType("Domestic");
						// } else if (bean.getCustCode().equalsIgnoreCase("EX"))
						// {
						// bean.setCustType("Export");
						// }
						// }
						// bean.setAssingedToId(resultSeDboElePrice.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboElePrice.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboElePrice.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboElePrice.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboElePrice.getString("CREATED_BY"));
						// bean.setActive(resultSeDboElePrice.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getSavedDboEleList().add(bean);
						// }
						// }
						//
						// if (callableStatement.getMoreResults()) {
						// resultSeDboElePriceUnsaved =
						// callableStatement.getResultSet();
						// while (resultSeDboElePriceUnsaved.next()) {
						// DBOBean bean = new DBOBean();
						// bean.setUpdateRequestNumber(resultSeDboElePriceUnsaved.getInt("UPD_REQ_NO"));
						// bean.setPriceId(resultSeDboElePriceUnsaved.getInt("PRICE_ID"));
						// bean.setItemId(resultSeDboElePriceUnsaved.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboElePriceUnsaved.getString("ITEM_NAME"));
						// bean.setPanelCode(resultSeDboElePriceUnsaved.getString("PANEL_CD"));
						// //
						// bean.setPrice(Math.round(resultSeDboElePriceUnsaved.getFloat("PRICE")));
						// bean.setPrevPrice(Math.round(resultSeDboElePriceUnsaved.getFloat("PREV_PRICE")));
						// bean.setPriceCode(resultSeDboElePriceUnsaved.getString("PRICE_CODE"));
						// bean.setStatusId(resultSeDboElePriceUnsaved.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboElePriceUnsaved.getString("STATUS_NAME"));
						// bean.setAssingedToId(resultSeDboElePriceUnsaved.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboElePriceUnsaved.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboElePriceUnsaved.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboElePriceUnsaved.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboElePriceUnsaved.getString("CREATED_BY"));
						// bean.setActive(resultSeDboElePriceUnsaved.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getUnsavedDboEleList().add(bean);
						// }
						// }

					} else if (quotationForm.getCommentList().get(0).getCommentType()
							.equals("PROC_UPD_DBO_ELE_PRICE_ADDON")) {
						if (callableStatement.getMoreResults()) {
							resultSeDboElePriceAddOn = callableStatement.getResultSet();
							while (resultSeDboElePriceAddOn.next()) {

								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSeDboElePriceAddOn.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSeDboElePriceAddOn.getInt("PRICE_ID"));
								bean.setItemId(resultSeDboElePriceAddOn.getInt("ITEM_ID"));
								bean.setItemName(resultSeDboElePriceAddOn.getString("ITEM_NAME"));
								bean.setPanelCode(resultSeDboElePriceAddOn.getString("PANEL_CD"));
								bean.setPrice(Math.round(resultSeDboElePriceAddOn.getFloat("COST")));
								bean.setMinVal(resultSeDboElePriceAddOn.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSeDboElePriceAddOn.getFloat("MAX_VAL"));
								bean.setColId(resultSeDboElePriceAddOn.getInt("COL_ID"));

								bean.setColValCd(resultSeDboElePriceAddOn.getString("COL_VAL_CD"));
								bean.setPrevPrice(Math.round(resultSeDboElePriceAddOn.getFloat("PREV_COST")));
								bean.setStatusId(resultSeDboElePriceAddOn.getInt("STATUS_ID"));
								bean.setStatusName(resultSeDboElePriceAddOn.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSeDboElePriceAddOn.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSeDboElePriceAddOn.getString("ASSIGNED_TO"));
								bean.setModDt(resultSeDboElePriceAddOn.getDate("MOD_DT"));
								bean.setCreatedById(resultSeDboElePriceAddOn.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSeDboElePriceAddOn.getString("CREATED_BY"));
								bean.setActive(resultSeDboElePriceAddOn.getInt("IS_ACTIVE") == 1 ? true : false);

								quotationForm.getSavedDboEleAddOnList().add(bean);

							}
						}

						if (callableStatement.getMoreResults()) {
							resultSeDboElePriceAddOnUnsaved = callableStatement.getResultSet();
							while (resultSeDboElePriceAddOnUnsaved.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSeDboElePriceAddOnUnsaved.getInt("UPD_REQ_NO"));
								bean.setPriceId(resultSeDboElePriceAddOnUnsaved.getInt("PRICE_ID"));
								bean.setItemId(resultSeDboElePriceAddOnUnsaved.getInt("ITEM_ID"));
								bean.setItemName(resultSeDboElePriceAddOnUnsaved.getString("ITEM_NAME"));
								bean.setPanelCode(resultSeDboElePriceAddOnUnsaved.getString("PANEL_CD"));
								bean.setMinVal(resultSeDboElePriceAddOnUnsaved.getFloat("MIN_VAL"));
								bean.setMaxVal(resultSeDboElePriceAddOnUnsaved.getFloat("MAX_VAL"));
								bean.setColId(resultSeDboElePriceAddOnUnsaved.getInt("COL_ID"));
								bean.setColValCd(resultSeDboElePriceAddOnUnsaved.getString("COL_VAL_CD"));
								bean.setPrevPrice(Math.round(resultSeDboElePriceAddOnUnsaved.getFloat("PREV_COST")));
								bean.setStatusId(resultSeDboElePriceAddOnUnsaved.getInt("STATUS_ID"));
								bean.setStatusName(resultSeDboElePriceAddOnUnsaved.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSeDboElePriceAddOnUnsaved.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSeDboElePriceAddOnUnsaved.getString("ASSIGNED_TO"));
								bean.setModDt(resultSeDboElePriceAddOnUnsaved.getDate("MOD_DT"));
								bean.setCreatedById(resultSeDboElePriceAddOnUnsaved.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSeDboElePriceAddOnUnsaved.getString("CREATED_BY"));
								bean.setActive(resultSeDboElePriceAddOnUnsaved.getInt("IS_ACTIVE") == 1 ? true : false);

								quotationForm.getUnsavedDboEleAddOnList().add(bean);
							}
						}
						// } else if
						// (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_DBO_ELE_PRICE_SPLADDON"))
						// {
						// if (callableStatement.getMoreResults()) {
						// resultSeDboElePriceSplAddOn =
						// callableStatement.getResultSet();
						// while (resultSeDboElePriceSplAddOn.next()) {
						//
						// DBOBean bean = new DBOBean();
						//
						// bean.setUpdateRequestNumber(resultSeDboElePriceSplAddOn.getInt("UPD_REQ_NO"));
						// bean.setPriceId(resultSeDboElePriceSplAddOn.getInt("PRICE_ID"));
						// bean.setItemId(resultSeDboElePriceSplAddOn.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboElePriceSplAddOn.getString("ITEM_NAME"));
						// bean.setColCd(resultSeDboElePriceSplAddOn.getString("COL_CD"));
						// bean.setColNm(resultSeDboElePriceSplAddOn.getString("COL_NM"));
						// bean.setPrice(Math.round(resultSeDboElePriceSplAddOn.getFloat("COST")));
						// bean.setPrevPrice(Math.round(resultSeDboElePriceSplAddOn.getFloat("PREV_COST")));
						// bean.setPrevColCd(resultSeDboElePriceSplAddOn.getString("PREV_COL_CD"));
						// bean.setPrevColNm(resultSeDboElePriceSplAddOn.getString("PREV_COL_NM"));
						// bean.setStatusId(resultSeDboElePriceSplAddOn.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboElePriceSplAddOn.getString("STATUS_NAME"));
						// bean.setAssingedToId(resultSeDboElePriceSplAddOn.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboElePriceSplAddOn.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboElePriceSplAddOn.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboElePriceSplAddOn.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboElePriceSplAddOn.getString("CREATED_BY"));
						// bean.setActive(resultSeDboElePriceSplAddOn.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getSavedDboEleSplAddOnList().add(bean);
						// }
						// }
						//
						// if (callableStatement.getMoreResults()) {
						// resultSeDboElePriceSplAddOnUnsaved =
						// callableStatement.getResultSet();
						// while (resultSeDboElePriceSplAddOnUnsaved.next()) {
						// DBOBean bean = new DBOBean();
						//
						// bean.setUpdateRequestNumber(resultSeDboElePriceSplAddOnUnsaved.getInt("UPD_REQ_NO"));
						// bean.setPriceId(resultSeDboElePriceSplAddOnUnsaved.getInt("PRICE_ID"));
						// bean.setItemId(resultSeDboElePriceSplAddOnUnsaved.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboElePriceSplAddOnUnsaved.getString("ITEM_NAME"));
						// bean.setPrevPrice(Math.round(resultSeDboElePriceSplAddOnUnsaved.getFloat("PREV_COST")));
						// bean.setPrevColCd(resultSeDboElePriceSplAddOnUnsaved.getString("PREV_COL_CD"));
						// bean.setPrevColNm(resultSeDboElePriceSplAddOnUnsaved.getString("PREV_COL_NM"));
						// bean.setStatusId(resultSeDboElePriceSplAddOnUnsaved.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboElePriceSplAddOnUnsaved.getString("STATUS_NAME"));
						// bean.setAssingedToId(resultSeDboElePriceSplAddOnUnsaved.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboElePriceSplAddOnUnsaved.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboElePriceSplAddOnUnsaved.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboElePriceSplAddOnUnsaved.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboElePriceSplAddOnUnsaved.getString("CREATED_BY"));
						// bean.setActive(resultSeDboElePriceSplAddOnUnsaved.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getUnsavedDboEleSplAddOnList().add(bean);
						// }
						// }
					} else if (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_CREATE_ADDON")) {
						if (callableStatement.getMoreResults()) {
							resultSeAddOn = callableStatement.getResultSet();
							while (resultSeAddOn.next()) {

								AddOnComponent bean = new AddOnComponent();
								bean.setUpdateRequestNumber(resultSeAddOn.getInt("UPD_REQ_NO"));
								bean.setCompoId(resultSeAddOn.getInt("COMP_ID"));
								bean.setFramePowId(resultSeAddOn.getInt("FRAME_POW_ID"));
								bean.setFrameNm(resultSeAddOn.getString("FRM_NM"));
								bean.setAddOnCompoId(resultSeAddOn.getInt("ADD_ON_COMP_ID"));
								bean.setAddOnCompoName(resultSeAddOn.getString("ADDONNAME"));
								bean.setAddOnCompo_cd(resultSeAddOn.getString("ADDON"));
								bean.setSubtype1(resultSeAddOn.getString("SUB_TYPE1"));
								bean.setSubtype2(resultSeAddOn.getString("SUB_TYPE2"));
								bean.setMake(resultSeAddOn.getString("MAKE"));
								bean.setSubtype1Code(resultSeAddOn.getString("SUB_TYPE1_NM"));
								bean.setSubtype2Code(resultSeAddOn.getString("SUB_TYPE2_NM"));
								bean.setMakeCode(resultSeAddOn.getString("MAKE_NM"));
								bean.setQuantity(resultSeAddOn.getInt("QTY"));
								bean.setQuantityName(resultSeAddOn.getString("QTY_NM"));
								bean.setSubtype1Id(resultSeAddOn.getInt("SUB_TYPE1_ID"));
								bean.setSubtype2Id(resultSeAddOn.getInt("SUB_TYPE2_ID"));
								bean.setMakeId(resultSeAddOn.getInt("MAKE_ID"));
								bean.setPrice(Math.round(resultSeAddOn.getFloat("COST")));
								bean.setPrevPrice(Math.round(resultSeAddOn.getFloat("PREV_COST")));
								bean.setStatusId(resultSeAddOn.getInt("STATUS_ID"));
								bean.setStatusName(resultSeAddOn.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSeAddOn.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSeAddOn.getString("ASSIGNED_TO"));
								bean.setModDt(resultSeAddOn.getDate("MOD_DT"));
								bean.setCreatedBy(resultSeAddOn.getInt("CREATED_BY_ID"));
								bean.setCreatedByName(resultSeAddOn.getString("CREATED_BY"));
								bean.setUpdReqName(resultSeAddOn.getString("UPD_REQ_NAME"));

								quotationForm.getSavedAddonList().add(bean);
							}
						}

						if (callableStatement.getMoreResults()) {
							resultSeAddOnUnsaved = callableStatement.getResultSet();
							while (resultSeAddOnUnsaved.next()) {
								AddOnComponent bean = new AddOnComponent();

								bean.setUpdateRequestNumber(resultSeAddOnUnsaved.getInt("UPD_REQ_NO"));
								bean.setCompoId(resultSeAddOnUnsaved.getInt("COMP_ID"));
								bean.setFramePowId(resultSeAddOnUnsaved.getInt("FRAME_POW_ID"));
								bean.setFrameNm(resultSeAddOnUnsaved.getString("FRM_NM"));
								bean.setAddOnCompoId(resultSeAddOnUnsaved.getInt("ADD_ON_COMP_ID"));
								bean.setAddOnCompoName(resultSeAddOnUnsaved.getString("ADDONNAME"));
								bean.setAddOnCompo_cd(resultSeAddOnUnsaved.getString("ADDON"));
								bean.setSubtype1(resultSeAddOnUnsaved.getString("SUB_TYPE1"));
								bean.setSubtype2(resultSeAddOnUnsaved.getString("SUB_TYPE2"));
								bean.setMake(resultSeAddOnUnsaved.getString("MAKE"));
								bean.setSubtype1Code(resultSeAddOnUnsaved.getString("SUB_TYPE1_NM"));
								bean.setSubtype2Code(resultSeAddOnUnsaved.getString("SUB_TYPE2_NM"));
								bean.setMakeCode(resultSeAddOnUnsaved.getString("MAKE_NM"));
								bean.setQuantity(resultSeAddOnUnsaved.getInt("QTY"));
								bean.setQuantityName(resultSeAddOnUnsaved.getString("QTY_NM"));
								bean.setSubtype1Id(resultSeAddOnUnsaved.getInt("SUB_TYPE1_ID"));
								bean.setSubtype2Id(resultSeAddOnUnsaved.getInt("SUB_TYPE2_ID"));
								bean.setMakeId(resultSeAddOnUnsaved.getInt("MAKE_ID"));
								// bean.setPrice(Math.round(resultSeAddOnUnsaved.getFloat("COST")));
								bean.setPrevPrice(Math.round(resultSeAddOnUnsaved.getFloat("PREV_COST")));
								bean.setStatusId(resultSeAddOnUnsaved.getInt("STATUS_ID"));
								bean.setStatusName(resultSeAddOnUnsaved.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSeAddOnUnsaved.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSeAddOnUnsaved.getString("ASSIGNED_TO"));
								bean.setModDt(resultSeAddOnUnsaved.getDate("MOD_DT"));
								bean.setCreatedBy(resultSeAddOnUnsaved.getInt("CREATED_BY_ID"));
								bean.setCreatedByName(resultSeAddOnUnsaved.getString("CREATED_BY"));
								bean.setUpdReqName(resultSeAddOnUnsaved.getString("UPD_REQ_NAME"));

								quotationForm.getUnsavedAddonList().add(bean);
							}
						}

						// } else if
						// (quotationForm.getCommentList().get(0).getCommentType().equals("PROC_UPD_DBO_ELE_PRICE_ADDINSTR"))
						// {
						// if (callableStatement.getMoreResults()) {
						// resultSeDboElePriceAddInstr =
						// callableStatement.getResultSet();
						// while (resultSeDboElePriceAddInstr.next()) {
						//
						// DBOBean bean = new DBOBean();
						// bean.setUpdateRequestNumber(resultSeDboElePriceAddInstr.getInt("UPD_REQ_NO"));
						// bean.setAddInstrId(resultSeDboElePriceAddInstr.getInt("ADD_INSTR_ID"));
						// bean.setItemId(resultSeDboElePriceAddInstr.getInt("ITEM_ID"));
						// bean.setItemName(resultSeDboElePriceAddInstr.getString("ITEM_NAME"));
						// bean.setPrice(resultSeDboElePriceAddInstr.getFloat("COST"));
						// bean.setAddInstrCd(resultSeDboElePriceAddInstr.getString("ADD_INSTR_CD"));
						// bean.setAddInstrName(resultSeDboElePriceAddInstr.getString("ADD_INSTR_NM"));
						// bean.setPrevPrice(resultSeDboElePriceAddInstr.getFloat("PREV_COST"));
						// bean.setPrevAddInstrCd(resultSeDboElePriceAddInstr.getString("PREV_ADD_INSTR_CD"));
						// bean.setStatusId(resultSeDboElePriceAddInstr.getInt("STATUS_ID"));
						// bean.setStatusName(resultSeDboElePriceAddInstr.getString("STATUS_NAME"));
						// bean.setAssingedToId(resultSeDboElePriceAddInstr.getInt("ASSIGHNED_TO_ID"));
						// bean.setAssignedTo(resultSeDboElePriceAddInstr.getString("ASSIGNED_TO"));
						// bean.setModDt(resultSeDboElePriceAddInstr.getDate("MOD_DT"));
						// bean.setCreatedById(resultSeDboElePriceAddInstr.getInt("CREATED_BY_ID"));
						// bean.setCreatedBy(resultSeDboElePriceAddInstr.getString("CREATED_BY"));
						// bean.setActive(resultSeDboElePriceAddInstr.getInt("IS_ACTIVE")
						// == 1 ? true : false);
						//
						// quotationForm.getSavedDboEleAddInstrList().add(bean);
						// }
						// }

						if (callableStatement.getMoreResults()) {
							resultSeDboElePriceAddInstrUnsaved = callableStatement.getResultSet();
							while (resultSeDboElePriceAddInstrUnsaved.next()) {
								DBOBean bean = new DBOBean();
								bean.setUpdateRequestNumber(resultSeDboElePriceAddInstrUnsaved.getInt("UPD_REQ_NO"));
								bean.setAddInstrId(resultSeDboElePriceAddInstrUnsaved.getInt("ADD_INSTR_ID"));
								bean.setItemId(resultSeDboElePriceAddInstrUnsaved.getInt("ITEM_ID"));
								bean.setItemName(resultSeDboElePriceAddInstrUnsaved.getString("ITEM_NAME"));
								bean.setPrice(resultSeDboElePriceAddInstrUnsaved.getFloat("COST"));
								bean.setAddInstrCd(resultSeDboElePriceAddInstrUnsaved.getString("ADD_INSTR_CD"));
								bean.setAddInstrName(resultSeDboElePriceAddInstrUnsaved.getString("ADD_INSTR_NM"));
								bean.setPrevPrice(resultSeDboElePriceAddInstrUnsaved.getFloat("PREV_COST"));
								bean.setPrevAddInstrCd(
										resultSeDboElePriceAddInstrUnsaved.getString("PREV_ADD_INSTR_CD"));
								bean.setStatusId(resultSeDboElePriceAddInstrUnsaved.getInt("STATUS_ID"));
								bean.setStatusName(resultSeDboElePriceAddInstrUnsaved.getString("STATUS_NAME"));
								bean.setAssingedToId(resultSeDboElePriceAddInstrUnsaved.getInt("ASSIGHNED_TO_ID"));
								bean.setAssignedTo(resultSeDboElePriceAddInstrUnsaved.getString("ASSIGNED_TO"));
								bean.setModDt(resultSeDboElePriceAddInstrUnsaved.getDate("MOD_DT"));
								bean.setCreatedById(resultSeDboElePriceAddInstrUnsaved.getInt("CREATED_BY_ID"));
								bean.setCreatedBy(resultSeDboElePriceAddInstrUnsaved.getString("CREATED_BY"));
								bean.setActive(
										resultSeDboElePriceAddInstrUnsaved.getInt("IS_ACTIVE") == 1 ? true : false);

								quotationForm.getUnsavedDboEleAddInstrList().add(bean);
							}
						}
					}
				}
				
			}
			}
		
		 	catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMyReq);
			UtilityMethods.closeResource(connection, callableStatement, resultSetOtherReq);
			UtilityMethods.closeResource(connection, callableStatement, resultSetOtherData);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData);
			UtilityMethods.closeResource(connection, callableStatement, resultEcData);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData2);
			UtilityMethods.closeResource(connection, callableStatement, resultSetExportData);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUBODate);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUpdateCost);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUpdateCostunsaved);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUpdateSubCost);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUpdateTurbCost);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUpdateTurbCostunsaved);

		}
		return quotationForm;
    }
	@Override
	public QuotationForm updateStatus(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_UPDATE_STATUS (?,?,?,?,?)}");
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getStatusId());
			callableStatement.setInt(3, quotationForm.getSaveBasicDetails().getAssignedTo());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getModifiedById());
			
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			logger.info("updateStatus inputs");
			logger.info(quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
			logger.info(quotationForm.getSaveBasicDetails().getStatusId());
			logger.info(quotationForm.getSaveBasicDetails().getAssignedTo());
			logger.info(quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
				logger.info("updateStatus result");
				logger.info(resultSetMsg.getInt(1));
				logger.info(resultSetMsg.getString(2));
				if (resultOutParameterInt == 0){
					List<List> list1 = new ArrayList<List>();
					//QuotationForm quotationForm1 = new QuotationForm();
					quotationDao.fetchCacheData(quotationForm);
					//QuotationForm email = userDetails.fetchCacheData(quotationForm);
					list1 = quotationForm.getUserDetailsEmailList();
					//logger.info("Testlevel1");
					//logger.info(list1.size());
					//logger.info(list1);
					//logger.info(QuotationDaoImpl.test);
					//logger.info(quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
					//logger.info(userDetails.getModifiedBy());
					//logger.info(userDetails.getUserId());
					logger.info(quotationForm.getSaveBasicDetails().getAssignedTo());
					for (int i = 0; i<list1.size();i++){
						/*logger.info("User Details Start");
						logger.info((int)list1.get(i).get(0));
						logger.info((String)list1.get(i).get(1));
						logger.info((String)list1.get(i).get(2));
						logger.info("User Details End");*/
						if ((int)list1.get(i).get(0) == quotationForm.getSaveBasicDetails().getAssignedTo()){
							/*logger.info("Test Email");
							logger.info(quotationForm.getSaveBasicDetails().getAssignedTo());
							logger.info((String)list1.get(i).get(1));
							logger.info((String)list1.get(i).get(2));*/
							quotationForm.setName((String)list1.get(i).get(1));
							quotationForm.setEmail((String)list1.get(i).get(2));
						}
					}
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public AdminForm getECUpdatePriceData(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		List<ErectionCommissionBean> ecDataList = new ArrayList<ErectionCommissionBean>();
		List<TurbineDetails> newFramePowerList = new ArrayList<TurbineDetails>();
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_UPD_GET_EC_DATA (?) }");
			callableStatement.setInt("FRM_POW_ID", adminForm.getFramePowerId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {

				ErectionCommissionBean erectionCommissionBean = new ErectionCommissionBean();
				erectionCommissionBean.setEcId(resultSetMsg.getInt("EC_ID"));
				erectionCommissionBean.setFramePowerId(resultSetMsg.getInt("FRM_POW_ID"));
				erectionCommissionBean.setFrameDesc(resultSetMsg.getString("FRM_NM"));
				erectionCommissionBean.setCondensingTypeId(resultSetMsg.getInt("COND_TYP_ID"));
				erectionCommissionBean.setCondensingType(resultSetMsg.getString("CONDENSING_TYPES"));
				erectionCommissionBean.setTypeOfChargeId(resultSetMsg.getInt("TYP_OF_CHARGE_ID"));
				erectionCommissionBean.setChargeName(resultSetMsg.getString("TYP_OF_CHARGE"));

				erectionCommissionBean.setLoadingId(resultSetMsg.getInt("LOADING_ID"));
				erectionCommissionBean.setLoadingName(resultSetMsg.getString("LOADING_TYPE"));
				erectionCommissionBean.setLodgingId(resultSetMsg.getInt("LOADGING_ID"));
				erectionCommissionBean.setLodgingName(resultSetMsg.getString("LOADGING_TYPE"));
				erectionCommissionBean.setPrice(resultSetMsg.getInt("PRICE"));

				erectionCommissionBean.setTurbineCode(resultSetMsg.getString("TURB_CD"));
				erectionCommissionBean.setTurbineDesignCode(resultSetMsg.getString("TURB_DESN"));
				if (erectionCommissionBean.getTurbineCode().equals("BP")) {
					erectionCommissionBean.setTurbineType("Back Pressure");
				} else if (erectionCommissionBean.getTurbineCode().equals("CD")) {
					erectionCommissionBean.setTurbineType("Condensing");
				}
				if (erectionCommissionBean.getTurbineDesignCode().equalsIgnoreCase("IM")) {
					erectionCommissionBean.setTurbineDesign("Impulse");
				} else if (erectionCommissionBean.getTurbineDesignCode().equalsIgnoreCase("RN")) {
					erectionCommissionBean.setTurbineDesign("Reaction");
				}
				erectionCommissionBean.setMaxPower(resultSetMsg.getFloat("MAX_POWER"));

				ecDataList.add(erectionCommissionBean);
			}
			if (!ecDataList.isEmpty()) {
				adminForm.setEcUpdatePriceList(ecDataList);
				;
			}

			QuotationForm quotationForm = new QuotationForm();
			quotationDao.fetchQuotCacheData(quotationForm); // fetch frame
															// related details
			quotationDao.fetchCacheData(quotationForm); // fetch user related
														// details

			adminForm.setDropDownColumnvalues(quotationForm.getDropDownColumnvalues()); // setting
																						// frame
																						// data
																						// to
																						// AdminForm
			adminForm.setUserProfileDetailsList(quotationForm.getUserDetailsList()); // setting
																						// Users
																						// data
																						// to
																						// AdminForm

			boolean contains = false;

			for (TurbineDetails bean1 : quotationForm.getDropDownColumnvalues().getFrameWithPowerList()
					.get("FRAMES_WITH_POWER")) {
				for (ErectionCommissionBean bean2 : ecDataList) {
					if (bean1.getFramePowerId() == bean2.getFramePowerId()) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					newFramePowerList.add(bean1);
				} else {
					contains = false;
				}
			}
			adminForm.setNewFrameWithPowersList(newFramePowerList);

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
	public QuotationForm createECPriceUpdateRequest(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR); // update
																				// screen
																				// code
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR); // unit
																		// id
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC); // vehicleId
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC); // dimension
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC); // length
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (ErectionCommissionBean bean : quotationForm.getErrectionCommList()) {

				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null, bean.getEcId(),
						bean.getFramePowerId(), bean.getCondensingTypeId(), bean.getEcTypeId(),
						bean.getTypeOfChargeId(), bean.getLoadingId(), bean.getLodgingId(), null, bean.getPrice());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_EC (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}
	/*@Override
	public QuotationForm createTurbInstrUpdateRequest(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TURB_INSTRMNT = new SQLServerDataTable();
			TURB_INSTRMNT.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TURB_INSTRMNT.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR); 
			TURB_INSTRMNT.addColumnMetadata("INSTR_ID", java.sql.Types.NUMERIC); 
			TURB_INSTRMNT.addColumnMetadata("FRM_POW_ID", java.sql.Types.NUMERIC); 
			TURB_INSTRMNT.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC); 
			TURB_INSTRMNT.addColumnMetadata("BLEED_TYP_ID", java.sql.Types.NUMERIC); 
			TURB_INSTRMNT.addColumnMetadata("ESTIMTN_COST", java.sql.Types.REAL);
			TURB_INSTRMNT.addColumnMetadata("TURB_INSTR_COST", java.sql.Types.REAL);
			TURB_INSTRMNT.addColumnMetadata("COND_INSTR_COST", java.sql.Types.REAL);
			TURB_INSTRMNT.addColumnMetadata("SUB_CONTR_COST", java.sql.Types.REAL);
			TURB_INSTRMNT.addColumnMetadata("SHOP_CONV_COST", java.sql.Types.REAL);
			TURB_INSTRMNT.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (TransportationDetailsBean bean : quotationForm.getInstrTurbineList()) {

				TURB_INSTRMNT.addRow(bean.getUpdateRequestNumber(), bean.getUpdateRequestName(), bean.getInstrId(), bean.getFramePowerId(), 
				bean.getCondTypeId(), bean.getBleedTypeId(), bean.getEstimationCost(),bean.getTurbInstrCost(), bean.getCondInstrCost(), 
				bean.getSubContrCost(), bean.getShopConvCost(), bean.getActiveNew());
				logger.info("TURB_INSTRMNT Start");
				logger.info(bean.getUpdateRequestNumber());
				logger.info(bean.getUpdateRequestName());
				logger.info(bean.getInstrId());
				logger.info(bean.getFramePowerId());
				logger.info(bean.getCondTypeId());
				logger.info(bean.getBleedTypeId());
				logger.info(bean.getEstimationCost());
				logger.info(bean.getTurbInstrCost());
				logger.info(bean.getCondInstrCost());
				logger.info(bean.getSubContrCost());
				logger.info(bean.getShopConvCost());
				logger.info(bean.getActiveNew());
				logger.info("TURB_INSTRMNT End");
				
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_TURB_INSTR (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TURB_INSTRMNT ", TURB_INSTRMNT);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			logger.info("dbo.TURB_INSTRMNT");
			logger.info(TURB_INSTRMNT);
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info(quotationForm.getSaveBasicDetails().getRemarks());
			logger.info(quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			logger.info(quotationForm.getSaveBasicDetails().getUserRoleId());
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
				logger.info("Inside while1");
				logger.info(resultOutParameterInt);
				logger.info(resultOutParameterString);
			}
			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
						logger.info("Inside while2");
						logger.info(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
			logger.info("UpdateCode");
			logger.info(quotationForm.getSaveBasicDetails().getUpdateCode());
			logger.info("UpdateRequestNumber");
			logger.info(quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}
*/
	@Override
	public QuotationForm saveUpdatedPrice(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_SAVE (?,?,?)}");
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setInt(3, quotationForm.getSaveBasicDetails().getStatusId());

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
		}
		return quotationForm;

	}

	@Override
	public QuotationForm updatePackagePrice(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR); // update
																				// screen
																				// code
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR); // unit
																		// id
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC); // vehicleId
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC); // dimension
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC); // length
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (PackageBean bean : quotationForm.getPackageupdatedPriceList()) {

				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null, bean.getPkgId(), bean.getFrameId(),
						bean.getCondenTypeId(), bean.getPkgTypeId(), null, null, null, null, bean.getPrice());
				logger.info(bean.getPkgId());
				logger.info(bean.getFrameId());
				logger.info(bean.getCondenTypeId());
				logger.info(bean.getPkgTypeId());
				logger.info(bean.getPrice());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_PKG (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	/*@Override
	public QuotationForm createOverheadSheet(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (TurbineDetails bean : quotationForm.getOverHeadSheetList()) {

				// ID2 INT NULL,--F2F_DET_ID

				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null, bean.getInstrId(),
						bean.getFramePowerId(), bean.getCondTypId(), bean.getBleedTypId(), null, // -id5
						null, // -id6
						bean.getOverHeads(), null, // send zero if making a
													// component inactive 0r 1
													// if just updating the
													// price
						bean.getTotalFTFCost());

			}
			// [0, UP_TR, DM, 1, 1, 1, 0, 1, 4, 1, null, 12]
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_TI_SHOP_CONV_COST (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}
*/
	/*@Override
	public QuotationForm createTurbineInstruments(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (TurbineDetails bean : quotationForm.getTurbineInstrumentList()) {
				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null, bean.getInstrId(),
						bean.getFramePowerId(), bean.getCondTypId(), bean.getBleedTypId(), null, // -id5
						null, // -id6
						bean.getTurbInstrCost(), bean.getCondInstrCost(), bean.getTotalFTFCost());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_TI_TURB_INSTR (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}
*/
	/*@Override
	public QuotationForm createSubContracting(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (TurbineDetails bean : quotationForm.getTurbineInstrumentList()) {
				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null, bean.getInstrId(),
						bean.getFramePowerId(), bean.getCondTypId(), bean.getBleedTypId(), null, // -id5
						null, // -id6
						bean.getSubContrCost(), null, // send zero if making a
														// component inactive 0r
														// 1 if just updating
														// the price
						bean.getTotalFTFCost());

			}
			// [0, UP_TR, DM, 1, 1, 1, 0, 1, 4, 1, null, 12]
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_TI_SUB_CONTR (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}
*/
	@Override
	public QuotationForm createUBOSheet(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (F2FUBOBean bean : quotationForm.getF2FUBOsetterList()) {

				// ID2 INT NULL,--F2F_DET_ID

				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null,
						bean.getF2F_ID(),bean.getF2F_DET_ID(), bean.getFRM_POW_ID(), bean.getBLEED_TYP_ID(), 
						bean.getMTRL_ID(), // -id5
						null, // -id6
						(int)bean.getTOTAL_PRICE(),null,(int)bean.getPRICE());
				logger.info("createUBOSheet");
				logger.info(quotationForm.getSaveBasicDetails().getUpdateRequestNumber());
				logger.info(quotationForm.getSaveBasicDetails().getUpdateCode());
				logger.info(null);
				logger.info(bean.getF2F_ID());
				logger.info(bean.getF2F_DET_ID());
				logger.info(bean.getFRM_POW_ID());
				logger.info(bean.getBLEED_TYP_ID());
				logger.info(bean.getMTRL_ID());
				logger.info(null);
				logger.info(bean.getTOTAL_PRICE());
				logger.info(null);
				logger.info(bean.getPRICE());
			}
			
			// [0, UP_TR, DM, 1, 1, 1, 0, 1, 4, 1, null, 12]
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_UBO_SHEET (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public AdminForm getNewFramesForNoOfVehicles(AdminForm adminForm) {

		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			String query = "select  c.FRM_ID,c.FRM_NM,d.FRAME_POW_ID,d.IS_ACTIVE,d.MAX_POWER,d.MIN_POWER,d.TURB_CD,d.TURB_DESN,d.MOD_BY,d.MOD_DT,d.CREAT_BY,d.CREAT_DT from   (select * from FRAMES) c, (select * from FRAMES_MAST where FRAME_POW_ID NOT in (SELECT FRM_POW_ID FROM TRNSPORT_DM A,FRAMES_MAST B WHERE A.FRM_POW_ID = B.FRAME_POW_ID)) D where c.FRM_ID=d.FRM_ID";
			Statement stmt = null;
			// select
			// c.FRM_ID,c.FRM_NM,d.FRAME_POW_ID,d.IS_ACTIVE,d.MAX_POWER,d.MIN_POWER,d.TURB_CD,d.TURB_DESN,d.MOD_BY,d.MOD_DT,d.CREAT_BY,d.CREAT_DT
			// from
			/*
			 * (select * from FRAMES) c, (select * from FRAMES_MAST where
			 * FRAME_POW_ID NOT in (SELECT FRM_POW_ID FROM F2F_UBO_MAST
			 * A,FRAMES_MAST B WHERE A.FRM_POW_ID = B.FRAME_POW_ID)) D where
			 * c.FRM_ID=d.FRM_ID
			 */
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			List<TurbineDetails> newFramePowerList = new ArrayList<>();
			while (rs.next()) {
				TurbineDetails turbineDetails = new TurbineDetails();
				turbineDetails.setFramePowerId(rs.getInt("FRAME_POW_ID"));
				turbineDetails.setFrameId(rs.getInt("FRM_ID"));
				turbineDetails.setTurbineCode(rs.getString("TURB_CD"));
				turbineDetails.setTurbineDesignCd(rs.getString("TURB_DESN"));
				if (turbineDetails.getTurbineCode().equals("BP")) {
					turbineDetails.setTurbType("Back Pressure");
				} else if (turbineDetails.getTurbineCode().equals("CD")) {
					turbineDetails.setTurbType("Condensing");
				}
				if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase("IM")) {
					turbineDetails.setTurbdesignName("Impulse");
				} else if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase("RN")) {
					turbineDetails.setTurbdesignName("Reaction");
				}
				turbineDetails.setFrameDesc(rs.getString("FRM_NM"));
				turbineDetails.setPower(rs.getFloat("MAX_POWER"));
				// turbineDetails.setMinPower(rs.getFloat("MIN_POWER"));
				// turbineDetails.setMaxPower(rs.getFloat("MAX_POWER"));

				turbineDetails.setFramePowerDesc(rs.getString("FRM_NM") + " - " + rs.getFloat("MAX_POWER") + " MW");

				if (rs.getInt("IS_ACTIVE") == 1) {
					turbineDetails.setFrameActive(true);
				} else {
					turbineDetails.setFrameActive(false);
				}

				newFramePowerList.add(turbineDetails);
			}

			adminForm.setNewFrameWithPowersList(newFramePowerList);

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		}

		return adminForm;
	}

	@Override
	public QuotationForm getPackageWithPriceList(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMyReq = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_GET_PKG_DATA (?)}");
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getFrameId());

			callableStatement.execute();

			resultSetMyReq = callableStatement.getResultSet();
			while (resultSetMyReq.next()) {
				PackageBean bean = new PackageBean();
				bean.setPkgId(resultSetMyReq.getInt("PKG_ID"));
				bean.setCustCode(resultSetMyReq.getString("CUST_TYP"));

				bean.setFrameId(resultSetMyReq.getInt("FRM_ID"));
				bean.setFrameName(resultSetMyReq.getString("FRAME_NAME"));
				bean.setCondenTypeId(resultSetMyReq.getInt("COND_TYP_ID"));
				bean.setCondensingType(resultSetMyReq.getString("COND_TYP_NAME"));
				bean.setPkgTypeId(resultSetMyReq.getInt("PKG_TYP"));
				bean.setPkgType(resultSetMyReq.getString("PKG_TYP_NAME"));
				bean.setPrice(Math.round(resultSetMyReq.getFloat("PRICE")));

				bean.setTurbCode(resultSetMyReq.getString("TURB_CD"));
				bean.setTurbDesgnCode(resultSetMyReq.getString("TURB_DESN"));
				if (bean.getTurbCode().equals("BP")) {
					bean.setTurbTypeName("Back Pressure");
				} else if (bean.getTurbCode().equals("CD")) {
					bean.setTurbTypeName("Condensing");
				}
				if (bean.getTurbDesgnCode().equalsIgnoreCase("IM")) {
					bean.setTurbDesgnName("Impulse");
				} else if (bean.getTurbDesgnCode().equalsIgnoreCase("RN")) {
					bean.setTurbDesgnName("Reaction");
				}
				// bean.setMaxPower(resultSetMyReq.getFloat("MAX_POWER"));

				quotationForm.getPackageDetailsWithPriceList().add(bean);

			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public AdminForm getTransportUpdatedPriceListDomestic(AdminForm adminForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetdata = null;
		List<TransportationDetailsBean> transDataList = new ArrayList<TransportationDetailsBean>();
		List<TurbineDetails> newFramePowerList = new ArrayList<TurbineDetails>();
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_UPD_GET_TRNS_DATA_DM (?,?)}");
			callableStatement.setInt("FRM_ID", adminForm.getFrameId());
			callableStatement.setInt("COND_TYP_ID", adminForm.getSaveBasicDetails().getCondensingTypeId());
			callableStatement.execute();
			resultSetdata = callableStatement.getResultSet();

			while (resultSetdata.next()) {
				TransportationDetailsBean bean = new TransportationDetailsBean();

				bean.setTransId(resultSetdata.getInt("TRNS_ID"));
				bean.setFrameId(resultSetdata.getInt("FRM_ID"));
				bean.setFrameName(resultSetdata.getString("FRM_NM"));
				bean.setCondensingTypeId(resultSetdata.getInt("COND_TYP_ID"));
				bean.setCondensingTypeName(resultSetdata.getString("CONDENSING_TYPES"));
				bean.setFobId(resultSetdata.getInt("FOB_ID"));
				bean.setFOBPlace(resultSetdata.getString("FOB_PLACE"));
				bean.setVehicleId(resultSetdata.getInt("VEHICLE_ID"));
				bean.setVehicleName(resultSetdata.getString("VEHICLE_NAME"));
				bean.setCompoId(resultSetdata.getInt("COMP_ID"));
				bean.setCompoName(resultSetdata.getString("COMP_NAME"));
				bean.setNumberOfVehicle(resultSetdata.getInt("NO_OF_VEHICLE"));
				bean.setTurbineCode(resultSetdata.getString("TURB_CD"));
				if (bean.getTurbineCode().equals("BP")) {
					bean.setTurbineType("Back Pressure");
				} else if (bean.getTurbineCode().equals("CD")) {
					bean.setTurbineType("Condensing");
				}
				bean.setTurbineDesignCode(resultSetdata.getString("TURB_DESN"));
				if (bean.getTurbineDesignCode().equalsIgnoreCase("IM")) {
					bean.setTurbineDesign("Impulse");
				} else if (bean.getTurbineDesignCode().equalsIgnoreCase("RN")) {
					bean.setTurbineDesign("Reaction");
				}
				// bean.setMaxPower(resultSetdata.getFloat("MAX_POWER"));

				transDataList.add(bean);
			}

			if (!transDataList.isEmpty()) {
				adminForm.setTransportDetailsList(transDataList);
			}

			QuotationForm quotationForm = new QuotationForm();
			quotationDao.fetchQuotCacheData(quotationForm);

			adminForm.setDropDownColumnvalues(quotationForm.getDropDownColumnvalues());

			boolean contains = false;

			for (TurbineDetails bean1 : quotationForm.getDropDownColumnvalues().getFrameWithPowerList()
					.get("FRAMES_WITH_POWER")) {
				for (TransportationDetailsBean bean2 : transDataList) {
					if (bean1.getFramePowerId() == bean2.getFramePowerId()) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					newFramePowerList.add(bean1);
				} else {
					contains = false;
				}
			}
			adminForm.setNewFrameWithPowersList(newFramePowerList);

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
	public AdminForm getTransportUpdatedPriceListExport(AdminForm adminForm) {

		CallableStatement callableStatement = null;

		Connection connection = null;
		ResultSet resultSetdata = null;
		List<TransportationDetailsBean> transDataList = new ArrayList<TransportationDetailsBean>();
		List<TurbineDetails> newFramePowerList = new ArrayList<TurbineDetails>();
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_UPD_GET_TRNS_DATA_EX () }");
			// callableStatement.setInt("TRNS_TYP", adminForm.getTransType());
			//// callableStatement.setInt("PORT_ID", adminForm.getPortId());
			//// callableStatement.setInt("FRM_POW_ID",
			// adminForm.getFramePowerId());
			//// callableStatement.setInt("COND_TYP_ID",
			// adminForm.getSaveBasicDetails().getCondensingTypeId());
			callableStatement.execute();
			resultSetdata = callableStatement.getResultSet();

			while (resultSetdata.next()) {
				TransportationDetailsBean bean = new TransportationDetailsBean();
				//
				// if (bean.getTransTypeCode().equals("FOB")) {
				bean.setTransId(resultSetdata.getInt("TRNS_ID"));
				bean.setFrameId(resultSetdata.getInt("FRM_ID"));
				bean.setFrameName(resultSetdata.getString("FRM_NM"));
				bean.setCondensingTypeId(resultSetdata.getInt("COND_TYP_ID"));
				bean.setCondensingTypeName(resultSetdata.getString("COND_TYP_NAME"));
				bean.setTransTypeId(resultSetdata.getInt("TRNS_TYP_ID"));
				bean.setTransType(resultSetdata.getString("TRANS_TYP"));

				bean.setTransTypeCode(resultSetdata.getString("TRANS_TYPE_CD"));
				bean.setPortId(resultSetdata.getInt("PORT_ID"));
				bean.setCountryName(resultSetdata.getString("COUNTRY_NM"));
				bean.setPortName(resultSetdata.getString("PORT_NM"));
				bean.setChennaiPrice(resultSetdata.getFloat("PRICE_CHENNAI"));
				bean.setPriceFob(resultSetdata.getFloat("PRICE_FOB"));
				bean.setPrice(resultSetdata.getInt("PRICE"));
				bean.setTurbineCode(resultSetdata.getString("TURB_CD"));
				if (bean.getTurbineCode().equals("BP")) {
					bean.setTurbineType("Back Pressure");
				} else if (bean.getTurbineCode().equals("CD")) {
					bean.setTurbineType("Condensing");
				}
				bean.setTurbineDesignCode(resultSetdata.getString("TURB_DESN"));
				if (bean.getTurbineDesignCode().equalsIgnoreCase("IM")) {
					bean.setTurbineDesign("Impulse");
				} else if (bean.getTurbineDesignCode().equalsIgnoreCase("RN")) {
					bean.setTurbineDesign("Reaction");
				}
				// bean.setMaxPower(resultSetdata.getFloat("MAX_POWER"));

				// else if (bean.getTransTypeCode().equals("CIF")) {
				// bean.setTransId(resultSetdata.getInt("TRNS_ID"));
				// bean.setFramePowerId(resultSetdata.getInt("FRM_POW_ID"));
				// bean.setFrameName(resultSetdata.getString("FRM_NM"));
				// bean.setCondensingTypeId(resultSetdata.getInt("COND_TYP_ID"));
				// bean.setCondensingTypeName(resultSetdata.getString("COND_TYP_NAME"));
				// bean.setTransType(resultSetdata.getString("TRNS_TYP"));
				// bean.setTransTypeNm(resultSetdata.getString("TRANS_TYPE_NM"));
				// bean.setTransTypeCode(resultSetdata.getString("TRANS_TYPE_CD"));
				//
				// bean.setPrice(resultSetdata.getInt("PRICE"));
				// bean.setTurbineCode(resultSetdata.getString("TURB_CD"));
				// if (bean.getTurbineCode().equals("BP")) {
				// bean.setTurbineType("Back Pressure");
				// } else if (bean.getTurbineCode().equals("CD")) {
				// bean.setTurbineType("Condensing");
				// }
				// bean.setTurbineDesignCode(resultSetdata.getString("TURB_DESN"));
				// if (bean.getTurbineDesignCode().equalsIgnoreCase("IM")) {
				// bean.setTurbineDesign("Impulse");
				// } else if
				// (bean.getTurbineDesignCode().equalsIgnoreCase("RN")) {
				// bean.setTurbineDesign("Reaction");
				// }
				// bean.setMaxPower(resultSetdata.getFloat("MAX_POWER"));
				// }

				transDataList.add(bean);
			}
			if (!transDataList.isEmpty()) {
				adminForm.setTransportDetailsList(transDataList);
			}

			QuotationForm quotationForm = new QuotationForm();
			quotationDao.fetchQuotCacheData(quotationForm);

			adminForm.setDropDownColumnvalues(quotationForm.getDropDownColumnvalues());

			boolean contains = false;

			for (TurbineDetails bean1 : quotationForm.getDropDownColumnvalues().getFrameWithPowerList()
					.get("FRAMES_WITH_POWER")) {
				for (TransportationDetailsBean bean2 : transDataList) {
					if (bean1.getFramePowerId() == bean2.getFramePowerId()) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					newFramePowerList.add(bean1);
				} else {
					contains = false;
				}
			}
			adminForm.setNewFrameWithPowersList(newFramePowerList);

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
	public AdminForm getNewFramesForUBO(AdminForm adminForm) {

		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			String query = "SELECT  C.FRM_ID,C.FRM_NM,D.FRAME_POW_ID,D.IS_ACTIVE,D.MAX_POWER,D.MIN_POWER,D.TURB_CD,D.TURB_DESN,D.MOD_BY,D.MOD_DT,D.CREAT_BY,D.CREAT_DT FROM (SELECT * FROM FRAMES) C,(SELECT * FROM FRAMES_MAST WHERE FRAME_POW_ID NOT IN(SELECT FRM_POW_ID FROM F2F_UBO_MAST A,FRAMES_MAST B WHERE A.FRM_POW_ID = B.FRAME_POW_ID)) D WHERE C.FRM_ID=D.FRM_ID";
			Statement stmt = null;

			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			List<TurbineDetails> newFramePowerList = new ArrayList<>();
			while (rs.next()) {
				TurbineDetails turbineDetails = new TurbineDetails();
				turbineDetails.setFramePowerId(rs.getInt("FRAME_POW_ID"));
				turbineDetails.setFrameId(rs.getInt("FRM_ID"));
				turbineDetails.setTurbineCode(rs.getString("TURB_CD"));
				turbineDetails.setTurbineDesignCd(rs.getString("TURB_DESN"));
				if (turbineDetails.getTurbineCode().equals("BP")) {
					turbineDetails.setTurbType("Back Pressure");
				} else if (turbineDetails.getTurbineCode().equals("CD")) {
					turbineDetails.setTurbType("Condensing");
				}
				if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase("IM")) {
					turbineDetails.setTurbdesignName("Impulse");
				} else if (turbineDetails.getTurbineDesignCd().equalsIgnoreCase("RN")) {
					turbineDetails.setTurbdesignName("Reaction");
				}
				turbineDetails.setFrameDesc(rs.getString("FRM_NM"));
				turbineDetails.setPower(rs.getFloat("MAX_POWER"));
				// turbineDetails.setMinPower(rs.getFloat("MIN_POWER"));
				// turbineDetails.setMaxPower(rs.getFloat("MAX_POWER"));

				turbineDetails.setFramePowerDesc(rs.getString("FRM_NM") + " - " + rs.getFloat("MAX_POWER") + " MW");

				if (rs.getInt("IS_ACTIVE") == 1) {
					turbineDetails.setFrameActive(true);
				} else {
					turbineDetails.setFrameActive(false);
				}

				newFramePowerList.add(turbineDetails);
			}

			adminForm.setNewFrameWithPowersList(newFramePowerList);

		} catch (Exception e) {
			adminForm.setSuccessCode(-1);
			adminForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			adminForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		}

		return adminForm;
	}

	@Override
	public QuotationForm createDboEleUpdateRequestPrice(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String custCode = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable DBO_PRICE_UPD = new SQLServerDataTable();
			DBO_PRICE_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			DBO_PRICE_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR); // update
																						// screen
																						// code
			DBO_PRICE_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);
			DBO_PRICE_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_PRICE_UPD.addColumnMetadata("CUST_TYP", java.sql.Types.VARCHAR);
			DBO_PRICE_UPD.addColumnMetadata("PANEL_CD", java.sql.Types.VARCHAR);
			DBO_PRICE_UPD.addColumnMetadata("PRICE_CODE", java.sql.Types.VARCHAR);
			DBO_PRICE_UPD.addColumnMetadata("PRICE", java.sql.Types.NUMERIC);
			DBO_PRICE_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : quotationForm.getQuotDboElectricalList()) {

				if (quotationForm.getSaveBasicDetails().getUpdateCode().equalsIgnoreCase("UPD_DBO_ELE_PRICE")) {
					custCode = quotationForm.getSaveBasicDetails().getCustCode();
				} else if (quotationForm.getSaveBasicDetails().getUpdateCode()
						.equalsIgnoreCase("UPD_DBO_ELE_PRICE_NEW")) {
					custCode = bean.getCustCode();
				}
				DBO_PRICE_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), bean.getPriceId(), bean.getItemId(),
						custCode, bean.getPanelCode(), bean.getPriceCode(), bean.getPrice(), bean.isActive() ? 1 : 0);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_DBO_ELE_PRICE (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.DBO_PRICE_UPD ", DBO_PRICE_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm createDboEleUpdateRequestPriceAddInstr(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String custCode = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable DBO_ELE_UPD = new SQLServerDataTable();
			DBO_ELE_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			DBO_ELE_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			DBO_ELE_UPD.addColumnMetadata("ADD_INSTR_ID", java.sql.Types.NUMERIC);
			DBO_ELE_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_ELE_UPD.addColumnMetadata("PANEL_CD", java.sql.Types.VARCHAR);
			DBO_ELE_UPD.addColumnMetadata("MIN_VAL", java.sql.Types.FLOAT);
			DBO_ELE_UPD.addColumnMetadata("MAX_VAL", java.sql.Types.FLOAT);

			DBO_ELE_UPD.addColumnMetadata("QTY_FLG", java.sql.Types.NUMERIC);
			DBO_ELE_UPD.addColumnMetadata("ADD_INSTR_CD", java.sql.Types.VARCHAR);
			DBO_ELE_UPD.addColumnMetadata("ADD_INSTR_NM", java.sql.Types.VARCHAR);
			DBO_ELE_UPD.addColumnMetadata("COST", java.sql.Types.FLOAT);
			DBO_ELE_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : quotationForm.getQuotDboElectricalList()) {
				if (quotationForm.getSaveBasicDetails().getUpdateCode()
						.equalsIgnoreCase("UPD_DBO_ELE_PRICE_ADDINSTR")) {
					custCode = quotationForm.getSaveBasicDetails().getCustCode();

				} else if (quotationForm.getSaveBasicDetails().getUpdateCode()
						.equalsIgnoreCase("UPD_DBO_ELE_PRICE_ADDINSTR_NEW")) {

					custCode = bean.getCustCode();
				}

				DBO_ELE_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), bean.getAddInstrId(), bean.getItemId(),
						null, null, null, bean.isQuantityFlag() ? 1 : 0, bean.getAddInstrCd(), bean.getAddInstrName(),
						bean.getPrice(), bean.isActive() ? 1 : 0);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_DBO_ELE_PRICE_ADDINSTR (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.DBO_ELE_UPD ", DBO_ELE_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm createDboEleUpdateRequestPriceSplAddOn(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String custCode = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable DBO_ELE_UPD = new SQLServerDataTable();
			DBO_ELE_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			DBO_ELE_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR); // update
																					// screen
																					// code
			DBO_ELE_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);
			DBO_ELE_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_ELE_UPD.addColumnMetadata("PANEL_CD", java.sql.Types.VARCHAR);
			DBO_ELE_UPD.addColumnMetadata("MIN_VAL", java.sql.Types.FLOAT);
			DBO_ELE_UPD.addColumnMetadata("MAX_VAL", java.sql.Types.FLOAT);
			DBO_ELE_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			DBO_ELE_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			DBO_ELE_UPD.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			DBO_ELE_UPD.addColumnMetadata("COST", java.sql.Types.FLOAT);
			DBO_ELE_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : quotationForm.getQuotDboElectricalList()) {

				if (quotationForm.getSaveBasicDetails().getUpdateCode()
						.equalsIgnoreCase("UPD_DBO_ELE_PRICE_SPLADDON")) {
					custCode = quotationForm.getSaveBasicDetails().getCustCode();

				} else if (quotationForm.getSaveBasicDetails().getUpdateCode()
						.equalsIgnoreCase("UPD_DBO_ELE_PRICE_SPLADDON_NEW")) {

					custCode = bean.getCustCode();
				}
				DBO_ELE_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), bean.getPriceId(), bean.getItemId(), null,
						null, null, null, bean.getColCd(), bean.getColNm(), bean.getPrice(), bean.isActive() ? 1 : 0

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_DBO_ELE_PRICE_SPLADDON (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.DBO_ELE_UPD ", DBO_ELE_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public DBOForm createDboEleUpdateRequestPriceAddOn(DBOForm dboForm) {
		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_VMS_FRM_LIST_UPD = new SQLServerDataTable();

			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("VMS_ID", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("TYPE_OF_PANEL", java.sql.Types.VARCHAR);

			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("FRM_ID", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("ADD_PRB_FLG", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("TYPE", java.sql.Types.VARCHAR);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("TYPE_DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("MAKE", java.sql.Types.VARCHAR);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("MAKE_DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("ALT_MAKE", java.sql.Types.VARCHAR);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("ALT_MAKE_DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("GEAR_BOX", java.sql.Types.VARCHAR);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("GEAR_BOX_DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("COST", java.sql.Types.REAL);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("NOTE", java.sql.Types.VARCHAR);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_VMS_FRM_LIST_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdatePriceElectricalVms()) {

				TYP_VMS_FRM_LIST_UPD.addRow(dboForm.getSaveBasicDetails().getUpdateRequestNumber(),
						bean.getUpdateCode(), bean.getVmsId(), bean.getTypeOfPanel(), bean.getFrameId(),
						bean.getAddPrbFlag(), bean.getType(), bean.getTypeDefaultFlag(), bean.getMake(),
						bean.getMakeDefaultFlag(), bean.getAltMake(), bean.getAltMakeDefaultFlag(), bean.getGearbox(),
						bean.getGearBoxDefaultFlag(), bean.getCost(), bean.getNote(), bean.getApproxCostFlag(),
						bean.getActiveNew())

				;
				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_VMS_FRM_LIST (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_VMS_FRM_LIST_UPD ", TYP_VMS_FRM_LIST_UPD);
			callableStatement.setInt(2, dboForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, dboForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, dboForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, dboForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						dboForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			dboForm.getSaveBasicDetails()
					.setDisplayReqNumber(code + "-" + dboForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}

	@Override
	public DBOForm createDboMechUpdateRequestPrice(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_ELE_COL_VAL_UPD = new SQLServerDataTable();

			TYP_ELE_COL_VAL_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("COL_VAL_ID", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("TYPE_OF_PANEL", java.sql.Types.VARCHAR);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("MAKE", java.sql.Types.VARCHAR);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("COL_VAL_NM", java.sql.Types.VARCHAR);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("COL_VAL_CD_SYM", java.sql.Types.VARCHAR);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("SUB_COL_VAL_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("SUB_COL_VAL_NM", java.sql.Types.VARCHAR);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("MIN_VAL", java.sql.Types.REAL);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("MAX_VAL", java.sql.Types.REAL);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("DEFLT_FLG_15", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("DEFLT_FLG_30", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("ORDER_ID", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("ADD_ON_DIFF_DS", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("ADDON_COST_PER", java.sql.Types.REAL);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("ADDON_DIR_COST", java.sql.Types.REAL);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("CALC_LINK_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("DISABLE_COL_VA_CD", java.sql.Types.VARCHAR);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("DEL_COL_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("INPUT_COST_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_COL_VAL_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdatePriceElectricalColVal()) {

				TYP_ELE_COL_VAL_UPD.addRow
				// `(dboForm.getSaveBasicDetails().getUpdateRequestNumber(),"ELE_COL_VAL",599,"HT",45,0,"BHEL",291,"500","NULL",0,"NULL",1,500,1,1,1,0,0,0,0,0,0,1,1,1,null,0,0,1);

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getColValId(),
						bean.getTypeOfPanel(), bean.getItemId(), bean.getSubItemId(), bean.getMake(), bean.getColId(),
						bean.getColValCd(), bean.getColValNm(), bean.getColValCdSym(), bean.getSubColValFlg(),
						bean.getSubColValNm(), bean.getMinVal(), bean.getMaxVal(), bean.getDefaultFlag15(),
						bean.getDefaultFlag30(), bean.getOrderId(), bean.getAddOnFlg(), bean.getAddOnDiffDs(),
						bean.getAddOnCostPer(), bean.getAddOnDirCost(), bean.getApproxCostFlag(),
						bean.getCalculateLinkFlag(), bean.getTechFlag(), bean.getComrFlag(), bean.getDispInd(),
						bean.getDisableColValCd(), bean.getDelColFlag(), bean.getInputCostFlag(), bean.getActiveNew())

				;
				logger.info(bean.getUpdateCode());

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_ELE_COL_VAL (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_ELE_COL_VAL_UPD ", TYP_ELE_COL_VAL_UPD);
			callableStatement.setInt(2, dboForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, dboForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, dboForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, dboForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						dboForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			dboForm.getSaveBasicDetails()
					.setDisplayReqNumber(code + "-" + dboForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}

	@Override
	public QuotationForm createDboEleUpdateRequestCol(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable DBO_ELE_COL_UPD = new SQLServerDataTable();
			DBO_ELE_COL_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			DBO_ELE_COL_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			DBO_ELE_COL_UPD.addColumnMetadata("COL_VAL_ID", java.sql.Types.NUMERIC);
			DBO_ELE_COL_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR); // null
			DBO_ELE_COL_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC); //
			DBO_ELE_COL_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC); //
			DBO_ELE_COL_UPD.addColumnMetadata("PANEL_CD", java.sql.Types.VARCHAR); // null
			DBO_ELE_COL_UPD.addColumnMetadata("MIN_VAL", java.sql.Types.FLOAT);
			DBO_ELE_COL_UPD.addColumnMetadata("MAX_VAL", java.sql.Types.FLOAT);
			DBO_ELE_COL_UPD.addColumnMetadata("PERCNT", java.sql.Types.FLOAT); // null
			DBO_ELE_COL_UPD.addColumnMetadata("DIR_PRICE", java.sql.Types.FLOAT); // null
			DBO_ELE_COL_UPD.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);// null
			DBO_ELE_COL_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : quotationForm.getQuotDboElectricalList()) {

				DBO_ELE_COL_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), bean.getColValId(), bean.getColValCd(),
						bean.getItemId(), bean.getColId(), bean.getPanelCode(), null, null, bean.getPercentage(),
						bean.getDirectPrice(), bean.isDefaultVal() ? 1 : 0, bean.isActive() ? 1 : 0

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_DBO_ELE_COL (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.DBO_ELE_COL_UPD ", DBO_ELE_COL_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public DBOForm createDboMechUpdateRequestCol(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {
			logger.info("inside try");
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_ELE_ADDON_UPD = new SQLServerDataTable();

			TYP_ELE_ADDON_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_ELE_ADDON_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_ELE_ADDON_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);
			TYP_ELE_ADDON_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ELE_ADDON_UPD.addColumnMetadata("TYPE_OF_PANEL", java.sql.Types.VARCHAR);
			TYP_ELE_ADDON_UPD.addColumnMetadata("CUST_TYPE", java.sql.Types.VARCHAR);
			TYP_ELE_ADDON_UPD.addColumnMetadata("MAKE", java.sql.Types.VARCHAR);
			TYP_ELE_ADDON_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			TYP_ELE_ADDON_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			TYP_ELE_ADDON_UPD.addColumnMetadata("SUB_COL_VAL_CD", java.sql.Types.VARCHAR);
			TYP_ELE_ADDON_UPD.addColumnMetadata("ADDON_COST_COL", java.sql.Types.VARCHAR);
			TYP_ELE_ADDON_UPD.addColumnMetadata("MIN_VAL", java.sql.Types.REAL);
			TYP_ELE_ADDON_UPD.addColumnMetadata("MAX_VAL", java.sql.Types.REAL);
			TYP_ELE_ADDON_UPD.addColumnMetadata("ADDON_DIR_COST", java.sql.Types.REAL);
			TYP_ELE_ADDON_UPD.addColumnMetadata("ADDON_COST_PER", java.sql.Types.REAL);
			TYP_ELE_ADDON_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_ADDON_UPD.addColumnMetadata("ERROR_MSG", java.sql.Types.VARCHAR);
			TYP_ELE_ADDON_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdatePriceElectricalAddOnList()) {

				TYP_ELE_ADDON_UPD.addRow(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(),
						bean.getPriceId(), bean.getItemId(), bean.getTypeOfPanel(), bean.getCustType(), bean.getMake(),
						bean.getColId(), bean.getColValCd(), bean.getSubColValCd(), bean.getAddOnCostCol(),
						bean.getMinVal(), bean.getMaxVal(), bean.getAddOnDirCost(), bean.getAddOnCostPer(),
						bean.getApproxCostFlag(), bean.getErrorMsg(), bean.getActiveNew());

				code = bean.getUpdateCode();
				logger.info(dboForm.getSaveBasicDetails().getUpdateRequestNumber());
				logger.info(bean.getUpdateCode());
				logger.info(bean.getUpdateCode());
				logger.info(bean.getPriceId());
				logger.info(bean.getItemId());
				logger.info(bean.getTypeOfPanel());
				logger.info(bean.getCustType());
				logger.info(bean.getMake());
				logger.info(bean.getColId());
				logger.info(bean.getColValCd());
				logger.info(bean.getSubColValCd());
				logger.info(bean.getAddOnCostCol());
				logger.info(bean.getMinVal());
				logger.info(bean.getMaxVal());
				logger.info(bean.getAddOnDirCost());
				logger.info(bean.getAddOnCostPer());
				logger.info(bean.getApproxCostFlag());
				logger.info(bean.getErrorMsg());
				logger.info(bean.getActiveNew());
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_ELE_ADDON_COST (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_ELE_ADDON_UPD ", TYP_ELE_ADDON_UPD);
			callableStatement.setInt(2, dboForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, dboForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, dboForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, dboForm.getSaveBasicDetails().getUserRoleId());
			logger.info(dboForm.getSaveBasicDetails().getModifiedById());
			logger.info(dboForm.getSaveBasicDetails().getRemarks());
			logger.info(dboForm.getSaveBasicDetails().getLoggedInUserCode());
			logger.info(dboForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						dboForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			dboForm.getSaveBasicDetails()
					.setDisplayReqNumber(code + "-" + dboForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}

	@Override
	public DBOForm getDBOEleUpdatePriceData(DBOForm dboForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsg1 = null;
		List<DBOBean> eleDataList = new ArrayList<DBOBean>();
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_UPD_GET_ELE_PRICE_DATA (?,?,?,?,?) }");
			callableStatement.setInt("ITEM_ID", dboForm.getItemId());
			callableStatement.setInt("SUB_ITEM_ID", dboForm.getSubItemId());
			callableStatement.setString("TYPE_OF_PANEL", dboForm.getTypeOfPanel());
			callableStatement.setString("MAKE", dboForm.getMake());
			callableStatement.setString("TABLE_NM", dboForm.getTableName());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetMsg1 = callableStatement.getResultSet();

				while (resultSetMsg1.next()) {
					if (dboForm.getTableName().equalsIgnoreCase("ELE_PRICE")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
						dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setItemName(resultSetMsg1.getString("ITEM_NAME"));
						dboBean.setSubItemId(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setSubItemName(resultSetMsg1.getString("SUB_ITEM_NAME"));
						dboBean.setTypeOfPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setCustType(resultSetMsg1.getString("CUST_TYPE"));
						dboBean.setPriceCode(resultSetMsg1.getString("PRICE_CODE"));
						dboBean.setPrice15(Math.round(resultSetMsg1.getFloat("PRICE_15")));
						dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
						
						dboBean.setItemErrMessage(resultSetMsg1.getString("ITEM_ERROR_MSG"));
						dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));

						eleDataList.add(dboBean);
					}

					if (dboForm.getTableName().equalsIgnoreCase("ELE_COL_VAL")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setColValId(resultSetMsg1.getInt("COL_VAL_ID"));
						dboBean.setTypeOfPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setItemName(resultSetMsg1.getString("ITEM_NAME"));
						dboBean.setSubItemId(resultSetMsg1.getInt("SUB_ITEM_ID"));
						dboBean.setSubItemName(resultSetMsg1.getString("SUB_ITEM_NAME"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setColId(resultSetMsg1.getInt("COL_ID"));
						dboBean.setColNm(resultSetMsg1.getString("COL_NM"));
						dboBean.setColValCd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setColValNm(resultSetMsg1.getString("COL_VAL_NM"));
						dboBean.setColValCdSym(resultSetMsg1.getString("COL_VAL_CD_SYM"));
						dboBean.setSubColValFlg(resultSetMsg1.getInt("SUB_COL_VAL_FLG"));
						dboBean.setSubColValNm(resultSetMsg1.getString("SUB_COL_VAL_NM"));
						dboBean.setMinVal(resultSetMsg1.getFloat("MIN_VAL"));
						dboBean.setMaxVal(resultSetMsg1.getFloat("MAX_VAL"));
						dboBean.setDefaultFlag15(resultSetMsg1.getInt("DEFLT_FLG_15"));
						dboBean.setDefaultFlag30(resultSetMsg1.getInt("DEFLT_FLG_30"));
						dboBean.setOrderId(resultSetMsg1.getInt("ORDER_ID"));
						dboBean.setAddOnFlg(resultSetMsg1.getInt("ADD_ON_FLG"));
						dboBean.setAddOnDiffDs(resultSetMsg1.getInt("ADD_ON_DIFF_DS"));
						dboBean.setAddOnCostPer(resultSetMsg1.getFloat("ADDON_COST_PER"));
						dboBean.setAddOnDirCost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
						dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setCalculateLinkFlag(resultSetMsg1.getInt("CALC_LINK_FLG"));
						dboBean.setTechFlag(resultSetMsg1.getInt("TECH_FLG"));
						dboBean.setComrFlag(resultSetMsg1.getInt("COMR_FLG"));
						dboBean.setDispInd(resultSetMsg1.getInt("DISP_IND"));
						dboBean.setDisableColValCd(resultSetMsg1.getString("DISABLE_COL_VA_CD"));
						dboBean.setDelColFlag(resultSetMsg1.getInt("DEL_COL_FLG"));
						dboBean.setInputCostFlag(resultSetMsg1.getInt("INPUT_COST_FLG"));
						dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));

						eleDataList.add(dboBean);
					}
					if (dboForm.getTableName().equalsIgnoreCase("VMS_FRM_LIST")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setVmsId(resultSetMsg1.getInt("VMS_ID"));
						dboBean.setTypeOfPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setFrameId(resultSetMsg1.getInt("FRM_ID"));
						dboBean.setFrameName(resultSetMsg1.getString("FRM_NM"));
						dboBean.setAddPrbFlag(resultSetMsg1.getInt("ADD_PRB_FLG"));

						dboBean.setAltMake(resultSetMsg1.getString("ALT_MAKE"));
						dboBean.setAltMakeDefaultFlag(resultSetMsg1.getInt("ALT_MAKE_DEFLT_FLG"));
						dboBean.setGearbox(resultSetMsg1.getString("GEAR_BOX"));
						dboBean.setGearBoxDefaultFlag(resultSetMsg1.getInt("GEAR_BOX_DEFLT_FLG"));
						dboBean.setType(resultSetMsg1.getString("TYPE"));
						dboBean.setTypeDefaultFlag(resultSetMsg1.getInt("TYPE_DEFLT_FLG"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setMakeDefaultFlag(resultSetMsg1.getInt("MAKE_DEFLT_FLG"));
						dboBean.setCost(Math.round(resultSetMsg1.getFloat("COST")));
						dboBean.setNote(resultSetMsg1.getString("NOTE"));
						dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
						eleDataList.add(dboBean);
					}

					if (dboForm.getTableName().equalsIgnoreCase("INSTRUMENT_LIST1")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setInstrId(resultSetMsg1.getInt("INSTR_ID"));
						dboBean.setCategoryNm(resultSetMsg1.getString("CATEGORY"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setTypeOfGov(resultSetMsg1.getString("TYPE_OF_GOV"));
						dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setItemName(resultSetMsg1.getString("ITEM_NAME"));
						dboBean.setTurbCode(resultSetMsg1.getString("TURB_CD"));
						dboBean.setTurbCodeNm(resultSetMsg1.getString("TURB_CD_NAME"));
						dboBean.setCondensingTypeId(resultSetMsg1.getInt("COND_TYP_ID"));
						dboBean.setCondensingTypeName(resultSetMsg1.getString("COND_TYP_NAME"));
						dboBean.setTypeOfInstr(resultSetMsg1.getString("TYPE_OF_INSTR"));
						dboBean.setInstrTypeNm(resultSetMsg1.getString("INSTR_TYPE_NM"));
						dboBean.setInstrDesc(resultSetMsg1.getString("INSTR_DESC"));
						dboBean.setInstrMounting(resultSetMsg1.getString("INSTR_MOUNTING"));
						dboBean.setQuantity1001Logic(resultSetMsg1.getInt("QTY_1001_LOGIC"));
						dboBean.setQuantity1002Logic(resultSetMsg1.getInt("QTY_1002_LOGIC"));
						dboBean.setQuantity2003Logic(resultSetMsg1.getInt("QTY_2003_LOGIC"));
						dboBean.setCost1001(Math.round(resultSetMsg1.getInt("COST_1001")));
						dboBean.setApprox1001Flag(resultSetMsg1.getInt("APPROX_1001_FLG"));
						dboBean.setCost1002(Math.round(resultSetMsg1.getInt("COST_1002")));
						dboBean.setApprox1002Flag(resultSetMsg1.getInt("APPROX_1002_FLG"));
						dboBean.setCost2003(Math.round(resultSetMsg1.getInt("COST_2003")));
						dboBean.setApprox20003Flag(resultSetMsg1.getInt("APPROX_2003_FLG"));
						dboBean.setCostCe1001(Math.round(resultSetMsg1.getInt("COST_CE_1001")));
						dboBean.setApprox1001CeFlag(resultSetMsg1.getInt("APPROX_1001_CE_FLG"));
						dboBean.setCostCe1002(resultSetMsg1.getInt("COST_CE_1002"));
						dboBean.setApprox1002CeFlag(resultSetMsg1.getInt("APPROX_1002_CE_FLG"));
						dboBean.setCostCe2003(Math.round(resultSetMsg1.getInt("COST_CE_2003")));
						dboBean.setApprox20003CeFlag(resultSetMsg1.getInt("APPROX_2003_CE_FLG"));
						dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
						eleDataList.add(dboBean);
					}

					
					if (dboForm.getTableName().equalsIgnoreCase("INSTRUMENT_LIST3")) {
						DBOBean dboBean = new DBOBean();
						dboBean.setAddInstrId(resultSetMsg1.getInt("ADD_INSTR_ID"));
						dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setAddInstrCd(resultSetMsg1.getString("ADD_INSTR_CD"));
						dboBean.setAddInstrNm(resultSetMsg1.getString("ADD_INSTR_NM"));
						dboBean.setCost(resultSetMsg1.getFloat("COST"));
						dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
						
						eleDataList.add(dboBean);
					}
					if (dboForm.getTableName().equalsIgnoreCase("INSTRUMENT_LIST2")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setId(resultSetMsg1.getInt("ID"));
						dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setItemName(resultSetMsg1.getString("ITEM_NAME"));
						dboBean.setFrameId(resultSetMsg1.getInt("FRM_ID"));
						dboBean.setFrameName(resultSetMsg1.getString("FRM_NM"));
						dboBean.setTypeOfInstr(resultSetMsg1.getString("TYPE_OF_INSTR"));
						dboBean.setCost1001(Math.round(resultSetMsg1.getInt("COST_1001")));
						dboBean.setApprox1001Flag(resultSetMsg1.getInt("APPROX_1001_FLG"));
						dboBean.setCost1002(Math.round(resultSetMsg1.getInt("COST_1002")));
						dboBean.setApprox1002Flag(resultSetMsg1.getInt("APPROX_1002_FLG"));
						dboBean.setCost2003(Math.round(resultSetMsg1.getInt("COST_2003")));
						dboBean.setApprox20003Flag(resultSetMsg1.getInt("APPROX_2003_FLG"));
						dboBean.setCostCe1001(Math.round(resultSetMsg1.getInt("COST_CE_1001")));
						dboBean.setApprox1001CeFlag(resultSetMsg1.getInt("APPROX_1001_CE_FLG"));
						dboBean.setCostCe1002(resultSetMsg1.getInt("COST_CE_1002"));
						dboBean.setApprox1002CeFlag(resultSetMsg1.getInt("APPROX_1002_CE_FLG"));
						dboBean.setCostCe2003(Math.round(resultSetMsg1.getInt("COST_CE_2003")));
						dboBean.setApprox20003CeFlag(resultSetMsg1.getInt("APPROX_2003_CE_FLG"));
						dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
						eleDataList.add(dboBean);
					}
					if (dboForm.getTableName().equalsIgnoreCase("ELE_ADDON_COST")) {
						DBOBean dboBean = new DBOBean();

						dboBean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
						dboBean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
						dboBean.setItemName(resultSetMsg1.getString("ITEM_NAME"));
						dboBean.setTypeOfOPanel(resultSetMsg1.getString("TYPE_OF_PANEL"));
						dboBean.setCustType(resultSetMsg1.getString("CUST_TYPE"));
						dboBean.setMake(resultSetMsg1.getString("MAKE"));
						dboBean.setColId(resultSetMsg1.getInt("COL_ID"));
						dboBean.setColNm(resultSetMsg1.getString("COL_NM"));
						dboBean.setColValCd(resultSetMsg1.getString("COL_VAL_CD"));
						dboBean.setSubColValCd(resultSetMsg1.getString("SUB_COL_VAL_CD"));
						dboBean.setAddOnCostCol(resultSetMsg1.getString("ADDON_COST_COL"));
						dboBean.setMinVal(resultSetMsg1.getFloat("MIN_VAL"));
						dboBean.setMaxVal(resultSetMsg1.getFloat("MAX_VAL"));

						dboBean.setAddOnDirCost(resultSetMsg1.getFloat("ADDON_DIR_COST"));
						dboBean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
						dboBean.setErrorMsg(resultSetMsg1.getString("ERROR_MSG"));
						dboBean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));
						eleDataList.add(dboBean);
					}

				}
				if (!eleDataList.isEmpty()) {
					dboForm.setdBOElectricalData(eleDataList);
				}
			}

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}

	@Override
	public DBOForm getDBOMechUpdatePriceData(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_ELE_PRICE_UPD = new SQLServerDataTable();

			TYP_ELE_PRICE_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_ELE_PRICE_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_ELE_PRICE_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);
			TYP_ELE_PRICE_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ELE_PRICE_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_ELE_PRICE_UPD.addColumnMetadata("TYPE_OF_PANEL", java.sql.Types.VARCHAR);
			TYP_ELE_PRICE_UPD.addColumnMetadata("MAKE", java.sql.Types.VARCHAR);
			TYP_ELE_PRICE_UPD.addColumnMetadata("CUST_TYPE", java.sql.Types.VARCHAR);
			TYP_ELE_PRICE_UPD.addColumnMetadata("PRICE_CODE", java.sql.Types.VARCHAR);
			TYP_ELE_PRICE_UPD.addColumnMetadata("PRICE", java.sql.Types.REAL);
			TYP_ELE_PRICE_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_ELE_PRICE_UPD.addColumnMetadata("ITEM_ERROR_MSG", java.sql.Types.VARCHAR);
			TYP_ELE_PRICE_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdatePriceElectricalList()) {
				logger.info(dboForm.getSaveBasicDetails().getUpdateRequestNumber());
				logger.info(bean.getUpdateCode());
				logger.info(bean.getPriceId());
				logger.info(bean.getItemId());
				logger.info(bean.getSubItemId());
				logger.info(bean.getTypeOfPanel());
				logger.info(bean.getMake());
				logger.info(bean.getCustType());
				logger.info(bean.getPriceCode());
				logger.info(bean.getPrice());
				logger.info(bean.getApproxCostFlag());
				logger.info(bean.getItemErrMessage());
				logger.info(bean.getActiveNew());
				TYP_ELE_PRICE_UPD.addRow(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(),
						bean.getPriceId(), bean.getItemId(), bean.getSubItemId(), bean.getTypeOfPanel(), bean.getMake(),
						bean.getCustType(), bean.getPriceCode(), bean.getPrice(), bean.getApproxCostFlag(),
						bean.getItemErrMessage(), bean.getActiveNew())

				;

				code = bean.getUpdateCode();

			}
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_ELE_PRICE (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_ELE_PRICE_UPD ", TYP_ELE_PRICE_UPD);
			callableStatement.setInt(2, dboForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, dboForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, dboForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, dboForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						dboForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			dboForm.getSaveBasicDetails()
					.setDisplayReqNumber(code + "-" + dboForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}

	@Override
	public DBOForm getDBOEleUpdateColData(DBOForm dboForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		List<DBOBean> eleDataList = new ArrayList<DBOBean>();

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_UPD_GET_ELE_TYPE (?,?) }");

			callableStatement.setString("TYPE_OF_PANEL", dboForm.getTypeOfPanel());
			callableStatement.setString("ELE_TYPE", dboForm.getEleType());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {

				DBOBean dboBean = new DBOBean();
				dboBean.setTypeOfPanel(resultSetMsg.getString("TYPE_OF_PANEL"));
				dboBean.setEleType(resultSetMsg.getString("ELE_TYPE"));
				dboBean.setEleTypeName(resultSetMsg.getString("ELE_TYPE_NM"));
				dboBean.setItemId(resultSetMsg.getInt("ITEM_ID"));
				dboBean.setItemName(resultSetMsg.getString("ITEM_NAME"));
				dboBean.setSubItemId(resultSetMsg.getInt("SUB_ITEM_ID"));
				dboBean.setSubItemName(resultSetMsg.getString("SUB_ITEM_NAME"));
				dboBean.setPanelDependFlag(resultSetMsg.getInt("PANEL_DEPEND_FLG"));
				dboBean.setIsEnable(resultSetMsg.getInt("IS_ENABLE"));

				eleDataList.add(dboBean);
			}
			if (!eleDataList.isEmpty()) {
				dboForm.setdBOElectricalData(eleDataList);
			}

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}

	@Override
	public DBOForm getDBOMechUpdateColData(DBOForm dboForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		List<DBOBean> mechDataList = new ArrayList<DBOBean>();

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_UPD_GET_ELE_MAKE (?,?,?) }");
			callableStatement.setInt("ITEM_ID", dboForm.getItemId());
			callableStatement.setInt("SUB_ITEM_ID", dboForm.getSubItemId());
			callableStatement.setString("TYPE_OF_PANEL", dboForm.getTypeOfPanel());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				DBOBean dboBean = new DBOBean();
				dboBean.setMake(resultSetMsg.getString("MAKE"));

				mechDataList.add(dboBean);
			}
			if (!mechDataList.isEmpty()) {
				dboForm.setdBOMechanicalList(mechDataList);
			}

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}

	@Override
	public DBOForm getDBOEleUpdateColData1(Integer itemId, String panelCd, DBOForm dboForm) {

		try {

			String query = "SELECT ITEM_ID,(SELECT CAT_DET_DESC FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'DBO' AND CAT_CD = 'ELE' ) AND IS_ACTIVE = 1 AND CAT_DET_ID = ITEM_ID) AS ITEM_NAME,COL_VAL_ID,COL_VAL_CD,COL_ID,(SELECT COL_NM FROM DBO_ELE_COL WHERE COL_ID = A.COL_ID) AS COL_NM FROM DBO_ELE_COL_VAL A WHERE ITEM_ID = '"
					+ itemId + "' AND PANEL_CD ='" + panelCd + "'AND STD_PRICE_FLG = 1 AND EXCEL_FLG = 1;";

			List<DBOBean> data = jdbcTemplate.query(query, new RowMapper<DBOBean>() {
				public DBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
					DBOBean dboBean = new DBOBean();
					dboBean.setItemId(rs.getInt("ITEM_ID"));
					dboBean.setItemName(rs.getString("ITEM_NAME"));
					dboBean.setColValId(rs.getInt("COL_VAL_ID"));
					dboBean.setColValCd(rs.getString("COL_VAL_CD"));
					dboBean.setColId(rs.getInt("COL_ID"));
					dboBean.setColNm(rs.getString("COL_NM"));
					return dboBean;
				}
			});

			logger.info("data " + data);

			dboForm.setdBOElectricalColList(data);
			if (!data.isEmpty()) {
				createDBOEleJson(data, dboForm);
			}

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}
		return dboForm;
	}

	@Override
	public DBOForm getDBOMechUpdateColData1(Integer itemId) {

		DBOForm dboForm = new DBOForm();

		try {

			String query = " SELECT ITEM_ID,(SELECT CAT_DET_DESC FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'DBO' AND CAT_CD = 'MECH' ) AND IS_ACTIVE = 1 AND CAT_DET_ID = ITEM_ID) AS ITEM_NAME,COL_ID,(SELECT COL_NM FROM DBO_MECH_COL WHERE COL_ID = A.COL_ID) AS COL_NM,COL_VAL_ID,COL_VAL_CD FROM DBO_MECH_COL_VAL A WHERE ITEM_ID = '"
					+ itemId + "'AND STD_PRICE_FLG = 1 AND EXCEL_FLG = 1;";
			List<DBOBean> data = jdbcTemplate.query(query, new RowMapper<DBOBean>() {
				public DBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
					DBOBean dboBean = new DBOBean();
					dboBean.setItemId(rs.getInt("ITEM_ID"));
					dboBean.setItemName(rs.getString("ITEM_NAME"));
					// dboBean.setColValId(rs.getInt("COL_VAL_ID"));
					dboBean.setColValCd(rs.getString("COL_VAL_CD"));
					dboBean.setColId(rs.getInt("COL_ID"));
					dboBean.setColNm(rs.getString("COL_NM"));
					return dboBean;
				}
			});

			logger.info("data " + data);

			dboForm.setdBOMechanicalList(data);
			if (!data.isEmpty()) {
				createDBOMechJson(data, dboForm);
			}

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}
		return dboForm;

	}

	private void createDBOMechJson(List<DBOBean> dataList, DBOForm DBOForm) {
		Map<Integer, String> dropdownNameMap = new HashMap<>();
		for (DBOBean bean : dataList) {
			dropdownNameMap.put(bean.getColId(), bean.getColNm());
		}
		Map<Integer, String> treeMap = new TreeMap<>(dropdownNameMap);
		List<DBOBean> questionsBeanList = new ArrayList<>();
		for (Entry<Integer, String> dropDownType : treeMap.entrySet()) {
			DBOBean questionsBean = new DBOBean();
			questionsBean.getDropDownType().setKey(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());

			DBOForm.getQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);
		}
		DBOForm.setQuestionsBean(questionsBeanList);
		for (DBOBean dropDownType : DBOForm.getQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			for (DBOBean bean : dataList) {
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					SelectBox box = new SelectBox();

					box.setValue(bean.getColValCd());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());

					selectBoxList.add(box);

				}

				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	private void createDBOEleJson(List<DBOBean> dataList, DBOForm DBOForm) {
		Map<Integer, String> dropdownNameMap = new HashMap<>();
		for (DBOBean bean : dataList) {
			dropdownNameMap.put(bean.getColId(), bean.getColNm());
		}
		Map<Integer, String> treeMap = new TreeMap<>(dropdownNameMap);
		List<DBOBean> questionsBeanList = new ArrayList<>();
		for (Entry<Integer, String> dropDownType : treeMap.entrySet()) {
			DBOBean questionsBean = new DBOBean();
			questionsBean.getDropDownType().setKey(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());

			DBOForm.getQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);
		}
		DBOForm.setQuestionsBean(questionsBeanList);
		for (DBOBean dropDownType : DBOForm.getQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			for (DBOBean bean : dataList) {
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					SelectBox box = new SelectBox();

					box.setValue(bean.getColValCd());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());

					selectBoxList.add(box);

				}

				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	@Override
	public DBOForm createUpdateAddon(DBOForm dboForm) {
		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_INSTRUMENT_LIST_UPD = new SQLServerDataTable();

			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("CATEGORY", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("MAKE", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TYPE_OF_GOV", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TYPE_OF_INSTR", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_TYPE_NM", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_DESC", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_MOUNTING", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_1001_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_1002_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_2003_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_1001", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1001_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_1002", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1002_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_2003", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_2003_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_1001", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1001_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_1002", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1002_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_2003", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_2003_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdatePriceElectricalInstr()) {

				TYP_INSTRUMENT_LIST_UPD.addRow(dboForm.getSaveBasicDetails().getUpdateRequestNumber(),

						bean.getUpdateCode(), bean.getInstrId(), bean.getCategory(), bean.getMake(),
						bean.getTypeOfGov(), bean.getItemId(), bean.getTurbCode(), bean.getCondensingTypeId(),
						bean.getTypeOfInstr(), bean.getInstrTypeNm(), bean.getInstrDesc(), bean.getInstrMounting(),
						bean.getQuantity1001Logic(), bean.getQuantity1002Logic(), bean.getQuantity2003Logic(),
						bean.getCost1001(), bean.getApprox1001Flag(), bean.getCost1002(), bean.getApprox1002Flag(),
						bean.getCost2003(), bean.getApprox20003Flag(), bean.getCostCe1001(), bean.getApprox1001CeFlag(),
						bean.getCostCe1002(), bean.getApprox1002CeFlag(), bean.getCostCe2003(),
						bean.getApprox20003CeFlag(), bean.getActiveNew())

				;
				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_INSTRUMENT_LIST (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_INSTRUMENT_LIST_UPD ", TYP_INSTRUMENT_LIST_UPD);
			callableStatement.setInt(2, dboForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, dboForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, dboForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, dboForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						dboForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			dboForm.getSaveBasicDetails()
					.setDisplayReqNumber(code + "-" + dboForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}
	
	@Override
	public DBOForm createUpdateProbes(DBOForm dboForm) {
		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_INSTRUMENT_LIST_UPD = new SQLServerDataTable();

			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("CATEGORY", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("MAKE", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TYPE_OF_GOV", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TYPE_OF_INSTR", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_TYPE_NM", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_DESC", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_MOUNTING", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_1001_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_1002_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_2003_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_1001", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1001_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_1002", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1002_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_2003", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_2003_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_1001", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1001_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_1002", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1002_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_2003", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_2003_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdatePriceElectricalInstr3()) {

				TYP_INSTRUMENT_LIST_UPD.addRow(dboForm.getSaveBasicDetails().getUpdateRequestNumber(),

						bean.getUpdateCode(), bean.getInstrId(), null, null,
						null, bean.getItemId(), null, null,
						bean.getTypeOfInstr(), bean.getInstrTypeNm(), null, null,
						null,null, null,
						bean.getCost1001(), null, null, null,
						null, null, null, null,
						null, null, null,
						null, null)

				;
				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_INSTRUMENT_LIST_ADDITIONAL (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_INSTRUMENT_LIST_UPD ", TYP_INSTRUMENT_LIST_UPD);
			callableStatement.setInt(2, dboForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, dboForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, dboForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, dboForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						dboForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			dboForm.getSaveBasicDetails()
					.setDisplayReqNumber(code + "-" + dboForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}


	@Override
	public DBOForm createUpdateAddon1(DBOForm dboForm) {
		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_INSTRUMENT_LIST_UPD = new SQLServerDataTable();

			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("CATEGORY", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("MAKE", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TYPE_OF_GOV", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COND_TYP_ID", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("TYPE_OF_INSTR", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_TYPE_NM", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_DESC", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("INSTR_MOUNTING", java.sql.Types.VARCHAR);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_1001_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_1002_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("QTY_2003_LOGIC", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_1001", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1001_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_1002", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1002_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_2003", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_2003_FLG", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_1001", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1001_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_1002", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_1002_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("COST_CE_2003", java.sql.Types.NUMERIC);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("APPROX_2003_CE_FLG", java.sql.Types.REAL);
			TYP_INSTRUMENT_LIST_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdatePriceElectricalInstr()) {

				TYP_INSTRUMENT_LIST_UPD.addRow(dboForm.getSaveBasicDetails().getUpdateRequestNumber(),

						bean.getUpdateCode(), bean.getInstrId(), bean.getCategory(), bean.getMake(),
						bean.getTypeOfGov(), bean.getItemId(), bean.getTurbCode(), bean.getCondensingTypeId(),
						bean.getTypeOfInstr(), bean.getInstrTypeNm(), bean.getInstrDesc(), bean.getInstrMounting(),
						bean.getQuantity1001Logic(), bean.getQuantity1002Logic(), bean.getQuantity2003Logic(),
						bean.getCost1001(), bean.getApprox1001Flag(), bean.getCost1002(), bean.getApprox1002Flag(),
						bean.getCost2003(), bean.getApprox20003Flag(), bean.getCostCe1001(), bean.getApprox1001CeFlag(),
						bean.getCostCe1002(), bean.getApprox1002CeFlag(), bean.getCostCe2003(),
						bean.getApprox20003CeFlag(), bean.getActiveNew())

				;
				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_INSTRUMENT_LIST_AUTO_SPARY (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_INSTRUMENT_LIST_UPD ", TYP_INSTRUMENT_LIST_UPD);
			callableStatement.setInt(2, dboForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, dboForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, dboForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, dboForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						dboForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));

					}
				}
			}
			dboForm.getSaveBasicDetails()
					.setDisplayReqNumber(code + "-" + dboForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}



@Override
	public DBOForm updateGetAddOn(DBOForm dboForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsg1 = null;
		ResultSet resultSetMsg2 = null;
		ResultSet resultSetMsg3 = null;
		ResultSet resultSetMsg4 = null;
		ResultSet resultSetMsg5 = null;
		ResultSet resultSetMsg6 = null;
		ResultSet resultSetMsg7 = null;
		ResultSet resultSetMsg8 = null;
		List<DBOBean> priceList = new ArrayList<DBOBean>();
		List<DBOBean> addonPriceList = new ArrayList<DBOBean>();
		List<DBOBean> overTankPriceList = new ArrayList<DBOBean>();
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_UPD_GET_F2F_MECH_DATA (?) }");
			callableStatement.setString("SCOPE_CD", dboForm.getScopeCd());
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}




			if (dboForm.getScopeCd().equalsIgnoreCase("F2F")) {
				if (callableStatement.getMoreResults()) {
					resultSetMsg1 = callableStatement.getResultSet();
					while (resultSetMsg1.next()) {
						DBOBean bean = new DBOBean();
						
						bean.setPriceId(resultSetMsg1.getInt("PRICE_ID"));
						bean.setItemId(resultSetMsg1.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg1.getString("ITEM_NAME"));
						bean.setFramePowerId(resultSetMsg1.getInt("FRM_POW_ID"));
						bean.setFrameName(resultSetMsg1.getString("FRM_NM"));
						bean.setMaxPower(resultSetMsg1.getFloat("MAX_POWER"));
						bean.setTurbCode(resultSetMsg1.getString("TURB_CD"));
						bean.setTurbCodeNm(resultSetMsg1.getString("TURB_CD_NM"));
						bean.setBleedTypeId(resultSetMsg1.getInt("BLEED_TYP_ID"));
						bean.setBleedTypeName(resultSetMsg1.getString("BLEED_TYPE_NM"));
						//bean.setCondensingTypeId(resultSetMsg1.getInt("COND_TYP_ID"));
						//bean.setCondensingType(resultSetMsg1.getString("CONDENSING_TYPES"));
						bean.setPrice(Math.round(resultSetMsg1.getFloat("PRICE")));
						bean.setTotalPrice(Math.round(resultSetMsg1.getFloat("TOTAL_PRICE")));
						//bean.setGovPrice(Math.round(resultSetMsg1.getFloat("GOV_PRICE")));
						//bean.setTotalGovPrice(Math.round(resultSetMsg1.getFloat("TOTAL_GOV_PRICE")));
						//bean.setSubContrCost(Math.round(resultSetMsg1.getFloat("SUB_CONTR_COST")));
						//bean.setShopConvCost(Math.round(resultSetMsg1.getFloat("SHOP_CONV_COST")));
						//bean.setTotalFtfCost(Math.round(resultSetMsg1.getFloat("TOTAL_FTF_COST")));
						bean.setDefaultFlagNew(resultSetMsg1.getInt("DEFLT_FLG"));
						bean.setApproxCostFlag(resultSetMsg1.getInt("APPROX_COST_FLG"));
						bean.setActiveNew(resultSetMsg1.getInt("IS_ACTIVE"));

						dboForm.getPriceList().add(bean);

					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetMsg2 = callableStatement.getResultSet();
					while (resultSetMsg2.next()) {

					
						DBOBean bean = new DBOBean();
						bean.setColValId(resultSetMsg2.getInt("COL_VAL_ID"));
						bean.setItemId(resultSetMsg2.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg2.getString("ITEM_NAME"));
						bean.setSubItemId(resultSetMsg2.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetMsg2.getString("SUB_ITEM_NAME"));
						bean.setSubItemTypeId(resultSetMsg2.getInt("SUB_ITEM_TYP_ID"));
						bean.setSubItemTypeName(resultSetMsg2.getString("SUB_ITEM_TYP_NAME"));
						bean.setCategory(resultSetMsg2.getString("CATEGORY"));
						bean.setColId(resultSetMsg2.getInt("COL_ID"));
						bean.setColNm(resultSetMsg2.getString("COL_NM"));
						bean.setColValCd(resultSetMsg2.getString("COL_VAL_CD"));
						bean.setColValNm(resultSetMsg2.getString("COL_VAL_NM"));
						bean.setCalculateLinkFlag(resultSetMsg2.getInt("CALC_LINK_FLG"));
						bean.setDefaultFlagNew(resultSetMsg2.getInt("DEFLT_FLG"));
						bean.setDispInd(resultSetMsg2.getInt("DISP_IND"));
						bean.setOrderId(resultSetMsg2.getInt("ORDER_ID"));
						

						bean.setAddOnFlg(resultSetMsg2.getInt("ADD_ON_FLG"));
						bean.setAddOnCostPer(resultSetMsg2.getFloat("ADDON_COST_PER"));
						bean.setAddOnDirCost(resultSetMsg2.getFloat("ADDON_DIR_COST"));
						bean.setApproxCostFlag(resultSetMsg2.getInt("APPROX_COST_FLG"));
						bean.setActiveNew(resultSetMsg2.getInt("IS_ACTIVE"));

						dboForm.getAddonPriceList().add(bean);
					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetMsg3 = callableStatement.getResultSet();
					while (resultSetMsg3.next()) {

						DBOBean bean = new DBOBean();
						
						bean.setId(resultSetMsg3.getInt("ID"));
						bean.setFrameId(resultSetMsg3.getInt("FRM_ID"));
						bean.setFrameName(resultSetMsg3.getString("FRM_NM"));
						bean.setTurbCodeNm(resultSetMsg3.getString("TURB_CD_NM"));
						bean.setItemId(resultSetMsg3.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg3.getString("ITEM_NAME"));
						bean.setSubItemId(resultSetMsg3.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetMsg3.getString("SUB_ITEM_NAME"));
						bean.setSubItemTypeId(resultSetMsg3.getInt("SUB_ITEM_TYP_ID"));
						bean.setSubItemTypeName(resultSetMsg3.getString("SUB_ITEM_TYP_NAME"));
						bean.setColId(resultSetMsg3.getInt("COL_ID"));
						bean.setColNm(resultSetMsg3.getString("COL_NM"));
						bean.setColValCd(resultSetMsg3.getString("COL_VAL_CD"));
						bean.setMinVal(resultSetMsg3.getFloat("MIN_VAL"));
						bean.setMaxVal(resultSetMsg3.getFloat("MAX_VAL"));
						bean.setCost(resultSetMsg3.getFloat("COST"));
						dboForm.getFrameOilList().add(bean);
					}
				}

			}

			if (dboForm.getScopeCd().equalsIgnoreCase("MECH")) {
				if (callableStatement.getMoreResults()) {
					resultSetMsg3 = callableStatement.getResultSet();
					while (resultSetMsg3.next()) {
						DBOBean bean = new DBOBean();

						bean.setPriceId(resultSetMsg3.getInt("PRICE_ID"));
						bean.setItemId(resultSetMsg3.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg3.getString("ITEM_NAME"));
						bean.setSubItemId(resultSetMsg3.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetMsg3.getString("SUB_ITEM_NAME"));
						bean.setTurbCode(resultSetMsg3.getString("TURB_CD"));
						bean.setTurbCodeNm(resultSetMsg3.getString("TURB_CD_NM"));
						bean.setPriceCode(resultSetMsg3.getString("PRICE_CODE"));
						bean.setPrice(Math.round(resultSetMsg3.getFloat("PRICE")));
						bean.setApproxCostFlag(resultSetMsg3.getInt("APPROX_COST_FLG"));
						bean.setDefaultFlagNew(resultSetMsg3.getInt("DEFLT_FLG"));
						bean.setActiveNew(resultSetMsg3.getInt("IS_ACTIVE"));

						dboForm.getPriceList().add(bean);

					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetMsg4 = callableStatement.getResultSet();
					while (resultSetMsg4.next()) {
						DBOBean bean = new DBOBean();

						bean.setPriceId(resultSetMsg4.getInt("PRICE_ID"));
						bean.setItemId(resultSetMsg4.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg4.getString("ITEM_NAME"));
						bean.setSubItemId(resultSetMsg4.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetMsg4.getString("SUB_ITEM_NAME"));
						bean.setColId(resultSetMsg4.getInt("COL_ID"));
						bean.setColNm(resultSetMsg4.getString("COL_NM"));
						bean.setColValCd(resultSetMsg4.getString("COL_VAL_CD"));
						bean.setAddOnCostCol(resultSetMsg4.getString("ADDON_COST_COL"));
						bean.setAddOnCostPer(resultSetMsg4.getFloat("ADDON_COST_PER"));
						bean.setAddOnDirCost(resultSetMsg4.getFloat("ADDON_DIR_COST"));
						bean.setApproxCostFlag(resultSetMsg4.getInt("APPROX_COST_FLG"));
						bean.setActiveNew(resultSetMsg4.getInt("IS_ACTIVE"));
						dboForm.getAddonPriceList().add(bean);
					}
				}

			}
			if (dboForm.getScopeCd().equalsIgnoreCase("MECH_AUX")) {
				if (callableStatement.getMoreResults()) {
					resultSetMsg5 = callableStatement.getResultSet();
					while (resultSetMsg5.next()) {
						DBOBean bean = new DBOBean();

						bean.setPriceId(resultSetMsg5.getInt("PRICE_ID"));
						bean.setItemId(resultSetMsg5.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg5.getString("ITEM_NAME"));
						bean.setTurbCode(resultSetMsg5.getString("TURB_CD"));
						bean.setTurbCodeNm(resultSetMsg5.getString("TURB_CD_NM"));
						bean.setPriceCode(resultSetMsg5.getString("PRICE_CODE"));
						bean.setPrice(Math.round(resultSetMsg5.getFloat("PRICE")));
						bean.setApproxCostFlag(resultSetMsg5.getInt("APPROX_COST_FLG"));
						bean.setActiveNew(resultSetMsg5.getInt("IS_ACTIVE"));

						dboForm.getPriceList().add(bean);

					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetMsg6 = callableStatement.getResultSet();
					while (resultSetMsg6.next()) {

						DBOBean bean = new DBOBean();

						bean.setColValId(resultSetMsg6.getInt("COL_VAL_ID"));
						bean.setItemId(resultSetMsg6.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg6.getString("ITEM_NAME"));
						bean.setColId(resultSetMsg6.getInt("COL_ID"));
						bean.setColNm(resultSetMsg6.getString("COL_NM"));
						bean.setColValCd(resultSetMsg6.getString("COL_VAL_CD"));
						bean.setColValNm(resultSetMsg6.getString("COL_VAL_NM"));
						bean.setDefaultFlagNew(resultSetMsg6.getInt("DEFLT_FLG"));
						bean.setDispInd(resultSetMsg6.getInt("DISP_IND"));
						bean.setOrderId(resultSetMsg6.getInt("ORDER_ID"));
						bean.setComrFlag(resultSetMsg6.getInt("COMR_FLG"));
						bean.setTechFlag(resultSetMsg6.getInt("TECH_FLG"));
						// bean.setAddOnFlg(resultSetMsg6.getInt("ADD_ON_FLG"));
						bean.setAddOnCostPer(resultSetMsg6.getFloat("ADDON_COST_PER"));
						bean.setAddOnDirCost(resultSetMsg6.getFloat("ADDON_DIR_COST"));
						bean.setApproxCostFlag(resultSetMsg6.getInt("APPROX_COST_FLG"));
						bean.setActiveNew(resultSetMsg6.getInt("IS_ACTIVE"));

						dboForm.getAddonPriceList().add(bean);
					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetMsg7 = callableStatement.getResultSet();
					while (resultSetMsg7.next()) {
						DBOBean bean = new DBOBean();

						bean.setId(resultSetMsg7.getInt("ID"));
						bean.setItemId(resultSetMsg7.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg7.getString("ITEM_NAME"));
						bean.setPower(resultSetMsg7.getString("POWER"));
						bean.setMinVal(resultSetMsg7.getFloat("MIN_VAL"));
						bean.setMaxVal(resultSetMsg7.getFloat("MAX_VAL"));
						bean.setDefaultFlagNew(resultSetMsg7.getInt("DEFLT_FLG"));
						bean.setQuantity(resultSetMsg7.getInt("QTY"));
						bean.setPrice(Math.round(resultSetMsg7.getFloat("PRICE")));
						bean.setApproxCostFlag(resultSetMsg7.getInt("APPROX_COST_FLG"));
						bean.setActiveNew(resultSetMsg7.getInt("IS_ACTIVE"));

						dboForm.getOverTankPriceList().add(bean);
					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetMsg8 = callableStatement.getResultSet();
					while (resultSetMsg8.next()) {
						DBOBean bean = new DBOBean();
						bean.setId(resultSetMsg8.getInt("ID"));
						bean.setFrameId(resultSetMsg8.getInt("FRM_ID"));
						bean.setFrameName(resultSetMsg8.getString("FRM_NM"));
						bean.setTurbCodeNm(resultSetMsg8.getString("TURB_CD_NM"));
						bean.setItemId(resultSetMsg8.getInt("ITEM_ID"));
						bean.setItemName(resultSetMsg8.getString("ITEM_NAME"));
						bean.setColId(resultSetMsg8.getInt("COL_ID"));
						bean.setColNm(resultSetMsg8.getString("COL_NM"));
						bean.setColValCd(resultSetMsg8.getString("COL_VAL_CD"));
						bean.setMinVal(resultSetMsg8.getFloat("MIN_VAL"));
						bean.setMaxVal(resultSetMsg8.getFloat("MAX_VAL"));

						dboForm.getInitialFillList().add(bean);
					}
				}

			}

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return dboForm;
	}


	@Override
	public QuotationForm updatePriceTransportDomestic(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			QUOT_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("NM1", java.sql.Types.VARCHAR);
			QUOT_UPD.addColumnMetadata("ID1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID3", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID4", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID5", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("ID6", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS1", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("DIS2", java.sql.Types.NUMERIC);
			QUOT_UPD.addColumnMetadata("PRICE1", java.sql.Types.NUMERIC);

			for (TransportationDetailsBean bean : quotationForm.getUpdatePriceTransportList()) {

				QUOT_UPD.addRow(quotationForm.getSaveBasicDetails().getUpdateRequestNumber(),
						quotationForm.getSaveBasicDetails().getUpdateCode(), null, bean.getTransId(),
						bean.getTransTypeId(), bean.getFrameId(),

						bean.getCondensingTypeId(), bean.getFobId(), bean.getVehicleId(), bean.getCompoId(),
						bean.getNumberOfVehicle(), null);
				logger.info(bean.getFrameId());
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_TR_DM (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.QUOT_UPD ", QUOT_UPD);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getRemarks());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getLoggedInUserCode());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						quotationForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
					}
				}
			}
			quotationForm.getSaveBasicDetails().setDisplayReqNumber(quotationForm.getSaveBasicDetails().getUpdateCode()
					+ "-" + quotationForm.getSaveBasicDetails().getUpdateRequestNumber());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getF2FUBOMast(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetdata = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		List<F2FUBOBean> F2FUBOBeanlist = new ArrayList<F2FUBOBean>();

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_UPD_F2F_UBO_DET (?,?) }");
			callableStatement.setInt("FRM_POW_ID", quotationForm.getUBOFrameList().getFramePowerId());
			callableStatement.setInt("BLEED_TYP_ID", quotationForm.getUBOFrameList().getCategoryDetId());
			//callableStatement.setInt("COND_TYP_ID", quotationForm.getSaveBasicDetails().getCondensingTypeId());
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetdata = callableStatement.getResultSet();

				while (resultSetdata.next()) {
					F2FUBOBean bean = new F2FUBOBean();
					bean.setF2F_ID(resultSetdata.getInt("F2F_MAST_ID"));
					bean.setF2F_DET_ID(resultSetdata.getInt("F2F_DET_ID"));
					bean.setMTRL_ID(resultSetdata.getInt("MTRL_ID"));
					bean.setMTRL_NM(resultSetdata.getString("MTRL_NM"));
					bean.setCAT_ID(resultSetdata.getInt("CAT_ID"));
					bean.setCAT_NM(resultSetdata.getString("CAT_NM"));
					bean.setFRM_POW_ID(resultSetdata.getInt("FRM_POW_ID"));
					bean.setFRAME_NAME(resultSetdata.getString("FRAME_NAME"));
					bean.setBLEED_TYP_ID(resultSetdata.getInt("BLEED_TYP_ID"));
					bean.setPRICE(Math.round(resultSetdata.getFloat("PRICE")));
					bean.setTOTAL_PRICE(Math.round(resultSetdata.getFloat("TOTAL_PRICE")));
					//bean.setPER_MWP_RICE(Math.round(resultSetdata.getFloat("PER_MWP_RICE")));

					F2FUBOBeanlist.add(bean);
				}
			}
			quotationForm.setF2FUBOList(F2FUBOBeanlist);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetdata);
		}
		return quotationForm;
	}

}
