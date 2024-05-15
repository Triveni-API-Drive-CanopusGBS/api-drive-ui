package com.ttl.ito.business.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

//import org.apache.commons.jexl3.internal.introspection.AbstractExecutor.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.ttl.ito.business.beans.DBOBean;
import com.ttl.ito.business.beans.DBOForm;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.SelectBox;
import com.ttl.ito.business.beans.TurbineDetails;
import com.ttl.ito.business.dao.DBOMechanicalDao;
import com.ttl.ito.common.Utility.UtilityMethods;
import com.ttl.ito.internal.beans.ItoConstants;

import sun.misc.BASE64Decoder;

@Repository
public class DBOMechanicalDaoImpl implements DBOMechanicalDao {

	private Logger logger = Logger.getLogger(DBOMechanicalDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Override
	public DBOForm getDBOMechanicalData(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetDBOMec = null;
		ResultSet resultSetTurbineinstF2F = null;
		ResultSet resultSetVarCost = null;

		Connection connection = null;
		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_DBO_MECH_CACHE() }");
			callableStatement.execute();
			resultSetDBOMec = callableStatement.getResultSet();

			List<DBOBean> dboMechList = new ArrayList<>();
			while (resultSetDBOMec.next()) {
				DBOBean DBOBean = new DBOBean();
				DBOBean.setCatDetId(resultSetDBOMec.getInt("CAT_DET_ID"));
				DBOBean.setCatDetCd(resultSetDBOMec.getString("CAT_DET_CD"));
				DBOBean.setCatDetDesc(resultSetDBOMec.getString("CAT_DET_DESC"));

				dboMechList.add(DBOBean);

			}
			if (!dboMechList.isEmpty()) {
				DBOForm.setdBOMechanicalList(dboMechList);
			}

			if (callableStatement.getMoreResults()) {
				resultSetTurbineinstF2F = callableStatement.getResultSet();
				List<TurbineDetails> f2FturbList = new ArrayList<>();
				while (resultSetTurbineinstF2F.next()) {
					TurbineDetails bean = new TurbineDetails();
					bean.setInstrId(resultSetTurbineinstF2F.getInt("INSTR_ID"));
					bean.setFramePowerId(resultSetTurbineinstF2F.getInt("FRM_POW_ID"));
					bean.setFrameDesc(resultSetTurbineinstF2F.getString("FRM_NM"));
					bean.setCondTypId(resultSetTurbineinstF2F.getInt("COND_TYP_ID"));
					bean.setCondensingtypes(resultSetTurbineinstF2F.getString("CONDENSING_TYPES"));
					bean.setBleedTypId(resultSetTurbineinstF2F.getInt("BLEED_TYP_ID"));
					bean.setBleedType(resultSetTurbineinstF2F.getString("BLEED_TYP"));
					bean.setPercentIncr(resultSetTurbineinstF2F.getFloat("PERCNT_INCR"));

					bean.setTurbInstrCost(Math.round(resultSetTurbineinstF2F.getFloat("TURB_INSTR_COST")));
					bean.setCondInstrCost(Math.round(resultSetTurbineinstF2F.getFloat("COND_INSTR_COST")));
					bean.setSubContrCost(Math.round(resultSetTurbineinstF2F.getFloat("SUB_CONTR_COST")));
					bean.setOverHeads(Math.round(resultSetTurbineinstF2F.getFloat("SHOP_CONV_COST")));
					bean.setTotalFTFCost(Math.round(resultSetTurbineinstF2F.getFloat("TOTAL_FTF_COST")));
					bean.setTurbineCode(resultSetTurbineinstF2F.getString("TURB_CD"));
					bean.setTurbineDesignCd(resultSetTurbineinstF2F.getString("TURB_DESN"));
					bean.setMaxPower(resultSetTurbineinstF2F.getFloat("MAX_POWER"));
					bean.setSuppChainCost(Math.round(resultSetTurbineinstF2F.getFloat("SUPP_CHAIN_COST")));
					bean.setEstimateCost(Math.round(resultSetTurbineinstF2F.getFloat("ESTIMTN_COST")));

					f2FturbList.add(bean);
				}
				if (!f2FturbList.isEmpty()) {
					DBOForm.setTurbineInstrumentsWithFramelist(f2FturbList);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetVarCost = callableStatement.getResultSet();
				List<DBOBean> varCostList = new ArrayList<>();
				List<DBOBean> sparesCostList = new ArrayList<>();
				List<DBOBean> otherCostList = new ArrayList<>();
				while (resultSetVarCost.next()) {
					DBOBean bean = new DBOBean();
					bean.setCatDetCd(resultSetVarCost.getString("GROUP_CD"));
					bean.setItemCd(resultSetVarCost.getString("ITEM_CD"));
					bean.setItemName(resultSetVarCost.getString("ITEM_NM"));
					bean.setNoOfMonths(resultSetVarCost.getInt("NO_OF_MONTHS"));
					bean.setPercentageFlag(resultSetVarCost.getInt("PERCNT_FLG") == 1 ? true : false);
					bean.setPercentage(resultSetVarCost.getFloat("PERCNT"));
					bean.setRegionId(resultSetVarCost.getInt("REGION_ID"));
					bean.setCustCode(resultSetVarCost.getString("CUST_TYPE"));
					if (bean.getCatDetCd() != null && bean.getCatDetCd().equalsIgnoreCase("VR")) {
						varCostList.add(bean);
					} else if (bean.getCatDetCd() != null && bean.getCatDetCd().equalsIgnoreCase("SP")) {
						sparesCostList.add(bean);
					} else {
						otherCostList.add(bean);
					}

				}
				if (!varCostList.isEmpty()) {
					DBOForm.setVarCostData(varCostList);
					createVarCostsJson(varCostList, DBOForm);
				}
				if (!sparesCostList.isEmpty()) {
					DBOForm.setSparesCostData(sparesCostList);
					createSparesCostsJson(sparesCostList, DBOForm);
				}
				if (!otherCostList.isEmpty()) {
					DBOForm.setOtherCostData(otherCostList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOMec);
			UtilityMethods.closeResource(connection, callableStatement, resultSetTurbineinstF2F);

		}
		return DBOForm;
	}

	// not using as of now
	@Override
	public DBOForm getDBOMechPanel(DBOForm DBOForm) {

		CallableStatement callableStatement = null;

		ResultSet resultSetDBOMecpanel = null;
		ResultSet resultSetDBOMecpanel1 = null;
		ResultSet resultSetDBOMecpanel2 = null;
		ResultSet resultSetDBOMecpanel3 = null;
		ResultSet resultSetDBOMecpanel4 = null;
		ResultSet resultSetDBOMecpanel5 = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		Connection connection = null;
		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_DBO_MECH_PANEL(?,?) }");
			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());
			callableStatement.setString("CUST_TYP", DBOForm.getCustCode());

			callableStatement.execute();
			resultSetDBOMecpanel = callableStatement.getResultSet();

			while (resultSetDBOMecpanel.next()) {
				resultOutParameterInt = resultSetDBOMecpanel.getInt(1);
				resultOutParameterString = resultSetDBOMecpanel.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOMecpanel1 = callableStatement.getResultSet();
				List<DBOBean> excelQuesList = new ArrayList<>();
				while (resultSetDBOMecpanel1.next()) {
					DBOBean bean = new DBOBean();
					bean.setColCd(resultSetDBOMecpanel1.getString("COL_CD"));
					bean.setColNm(resultSetDBOMecpanel1.getString("COL_NM"));
					bean.setStdPriceFlag(resultSetDBOMecpanel1.getInt("STD_PRICE_FLG") == 1 ? true : false);
					bean.setOthersFlag(resultSetDBOMecpanel1.getInt("OTH_FLG") == 1 ? true : false);
					excelQuesList.add(bean);
				}
				if (!excelQuesList.isEmpty()) {
					DBOForm.setdBOColList(excelQuesList);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetDBOMecpanel3 = callableStatement.getResultSet();
				List<DBOBean> sapDataList = new ArrayList<>();
				while (resultSetDBOMecpanel3.next()) {
					DBOBean bean = new DBOBean();
					bean.setColCd(resultSetDBOMecpanel3.getString("COL_CD"));
					bean.setColNm(resultSetDBOMecpanel3.getString("COL_NM"));
					sapDataList.add(bean);
				}
				if (!sapDataList.isEmpty()) {
					DBOForm.setdBOColListSAP(sapDataList);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOMecpanel2 = callableStatement.getResultSet();
				List<DBOBean> excelPriceList = new ArrayList<>();
				while (resultSetDBOMecpanel2.next()) {
					DBOBean bean = new DBOBean();
					bean.setColValCd(resultSetDBOMecpanel2.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetDBOMecpanel2.getString("COL_VAL_NM"));
					bean.setColId(resultSetDBOMecpanel2.getInt("COL_ID"));
					bean.setColNm(resultSetDBOMecpanel2.getString("COL_NM"));
					bean.setMinVal(resultSetDBOMecpanel2.getFloat("MIN_VAL"));
					bean.setMaxVal(resultSetDBOMecpanel2.getFloat("MAX_VAL"));
					bean.setStdPriceFlag(resultSetDBOMecpanel2.getInt("STD_PRICE_FLG") == 1 ? true : false);
					bean.setDefaultVal(resultSetDBOMecpanel2.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setPercentage(resultSetDBOMecpanel2.getFloat("PERCNT"));
					bean.setPercentageFlag(resultSetDBOMecpanel2.getInt("PERCNT_FLG") == 1 ? true : false);
					bean.setDirectPriceFlag(resultSetDBOMecpanel2.getFloat("DIR_PRICE_FLG") == 1 ? true : false);
					bean.setDirectPrice(Math.round(resultSetDBOMecpanel2.getFloat("DIR_PRICE")));
					if (bean.isDefaultVal()) {
						bean.setColValNm(bean.getColValNm() + " (TTL STD)");
					}
					excelPriceList.add(bean);
				}
				if (!excelPriceList.isEmpty()) {
					DBOForm.setdBOColWithPrice(excelPriceList);
					createDBOExcelJson(excelPriceList, DBOForm);
				}
			}
			if (callableStatement.getMoreResults()) {
				resultSetDBOMecpanel4 = callableStatement.getResultSet();
				List<DBOBean> sapPriceList = new ArrayList<>();
				while (resultSetDBOMecpanel4.next()) {
					DBOBean bean = new DBOBean();
					bean.setColValCd(resultSetDBOMecpanel4.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetDBOMecpanel4.getString("COL_VAL_NM"));
					bean.setColId(resultSetDBOMecpanel4.getInt("COL_ID"));
					bean.setColNm(resultSetDBOMecpanel4.getString("COL_NM"));
					bean.setMinVal(resultSetDBOMecpanel4.getInt("MIN_VAL"));
					bean.setMaxVal(resultSetDBOMecpanel4.getInt("MAX_VAL"));
					bean.setDefaultVal(resultSetDBOMecpanel4.getInt("DEFLT_FLG") == 1 ? true : false);

					sapPriceList.add(bean);
				}
				if (!sapPriceList.isEmpty()) {
					DBOForm.setdBOColWithPriceSAP(sapPriceList);
					createDBOSapJson(sapPriceList, DBOForm);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOMecpanel);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOMecpanel1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOMecpanel2);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOMecpanel3);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOMecpanel4);

		}
		return DBOForm;

	}

	private void createDBOExcelJson(List<DBOBean> dataList, DBOForm DBOForm) {
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
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());
					box.setDefaultVal(bean.isDefaultVal());
					box.setStdPriceFlag(bean.isStdPriceFlag());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setDirectPriceFlag(bean.isDirectPriceFlag());
					box.setDirectPrice(bean.getDirectPrice());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());
					selectBoxList.add(box);
				}

				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	private void createDBOSapJson(List<DBOBean> dataList, DBOForm DBOForm) {
		Map<Integer, String> dropdownNameMap = new HashMap<>();
		for (DBOBean bean : dataList) {
			dropdownNameMap.put(bean.getColId(), bean.getColNm());
		}
		List<DBOBean> questionsBeanList = new ArrayList<>();
		for (Entry<Integer, String> dropDownType : dropdownNameMap.entrySet()) {
			DBOBean questionsBean = new DBOBean();
			questionsBean.getDropDownType().setKey(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());
			DBOForm.getSapQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);
		}
		DBOForm.setSapQuestionsBean(questionsBeanList);
		for (DBOBean dropDownType : DBOForm.getSapQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			for (DBOBean bean : dataList) {
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					SelectBox box = new SelectBox();
					// box.setKey(bean.getAnswerId());
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());

					box.setDefaultVal(bean.isDefaultVal());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());
					selectBoxList.add(box);
				}
				dropDownType.setDropDownValueList(selectBoxList);
			}
		}

	}

	@Override
	public DBOForm getDboMechanicalPrice(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;
		ResultSet resultSetMsgNew = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable DBO_MECH_CONFIG = new SQLServerDataTable();
			DBO_MECH_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			DBO_MECH_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_MECH_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			DBO_MECH_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			DBO_MECH_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			DBO_MECH_CONFIG.addColumnMetadata("ME_COL_VAL", java.sql.Types.REAL);

			for (DBOBean bean : DBOForm.getSelectedExcelData()) {
				DBO_MECH_CONFIG.addRow(DBOForm.getQuotId(), DBOForm.getItemId(), bean.getSubItemId(), bean.getColId(),
						bean.getColValCd(), bean.getUserVal());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_DBO_MECH_PRICE(?,?,?,?,?,?,?)}");

			callableStatement.setStructured(1, "dbo.DBO_MECH_CONFIG", DBO_MECH_CONFIG);
			callableStatement.setString(2, DBOForm.getPanelType()); // PANEL_TYP(EXCEL/SAP)
			callableStatement.setString(3, DBOForm.getCustCode()); // CUST_TYP(Domestic
																	// /Export)
			callableStatement.setInt(4, DBOForm.getQuantity()); // QTY
			callableStatement.setFloat(5, DBOForm.getAdditionalCost()); // ADD_ON_COST
			callableStatement.setString(6, DBOForm.getCompRemarks()); // COMMENTS
			callableStatement.setInt(7, DBOForm.getModifiedById()); // MOD_BY
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();
				while (resultSetData.next()) {
					DBOForm.setPrice(Math.round(resultSetData.getFloat("COST")));
					DBOForm.setMechAdditionalCost(Math.round(resultSetData.getFloat("ADD_ON_COST")));

				}

			}

			if (callableStatement.getMoreResults()) {
				resultSetMsgNew = callableStatement.getResultSet();
				while (resultSetMsgNew.next()) {
					resultOutParameterInt = resultSetMsgNew.getInt(1);
					resultOutParameterString = resultSetMsgNew.getString(2);
					DBOForm.setSuccessCode(resultOutParameterInt);
					DBOForm.setSuccessMsg(resultOutParameterString);
					DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveDBOMechanicalData(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		try {

			clearMessageFrom(DBOForm);
			List<String> itemsList = new ArrayList<>();
			for (Integer myInt : DBOForm.getItemIdList()) {
				itemsList.add(String.valueOf(myInt));
			}

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable DBO_CONFIG_DET = new SQLServerDataTable();
			DBO_CONFIG_DET.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG_DET.addColumnMetadata("ITEM_NAME", java.sql.Types.VARCHAR);
			DBO_CONFIG_DET.addColumnMetadata("QTY", java.sql.Types.REAL);
			DBO_CONFIG_DET.addColumnMetadata("COST", java.sql.Types.REAL);
			DBO_CONFIG_DET.addColumnMetadata("COMMENTS", java.sql.Types.VARCHAR);

			for (DBOBean bean : DBOForm.getDboMechOthersList()) {
				DBO_CONFIG_DET.addRow(bean.getOtherCompId(), bean.getOthCompName(), bean.getOthQuantity(),
						bean.getOthPrice(), bean.getOthRemarks());

			}
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_DBO_MECH_PRICE(?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId()); // QUOT_ID
			callableStatement.setString(2, String.join(",", itemsList)); // ITEM_ID_LIST
			callableStatement.setStructured(3, "dbo.DBO_CONFIG_DET", DBO_CONFIG_DET);
			callableStatement.setFloat(4, DBOForm.getCondensorFlowCapacity()); // FLOW_RATE
			callableStatement.setInt(5, DBOForm.getOverwrittenPriceFlag()); // COST_ME_FLG
			callableStatement.setFloat(6, DBOForm.getOverwrittenPrice()); // COST_ME
			// callableStatement.setFloat("SAP_PRICE",DBOForm.getSapPrice());

			callableStatement.setInt(7, DBOForm.getModifiedById()); // MOD_BY

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getDBOElectricalData(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetDBOEle = null;
		ResultSet resultSetDBOData = null;
		ResultSet resultSetDBOData1 = null;

		Connection connection = null;
		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_DBO_ELE_ITEMS(?) }");
			callableStatement.setString("PANEL_CD", DBOForm.getDboEleType()); // LT
																				// or
																				// HT
			callableStatement.execute();
			resultSetDBOEle = callableStatement.getResultSet();

			List<DBOBean> dboEleList = new ArrayList<DBOBean>();
			while (resultSetDBOEle.next()) {
				DBOBean DBOBean = new DBOBean();
				DBOBean.setCatDetId(resultSetDBOEle.getInt("ITEM_ID"));
				DBOBean.setCatDetCd(resultSetDBOEle.getString("ITEM_CD"));
				DBOBean.setCatDetDesc(resultSetDBOEle.getString("ITEM_NAME"));
				DBOBean.setSplAddonFlag(resultSetDBOEle.getInt("SPL_ADDON_FLG") == 1 ? true : false);
				DBOBean.setSplAddonNm(resultSetDBOEle.getString("SPL_ADDON_NM"));

				dboEleList.add(DBOBean);

			}
			if (!dboEleList.isEmpty()) {
				DBOForm.setdBOElectricalList(dboEleList);
			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOData = callableStatement.getResultSet();

				List<DBOBean> dboEleData = new ArrayList<>();
				while (resultSetDBOData.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setPanelCode(resultSetDBOData.getString("PANEL_CD"));
					DBOBean.setColValCd(resultSetDBOData.getString("COL_VAL_CD"));
					DBOBean.setColValNm(resultSetDBOData.getString("COL_VAL_NM"));
					DBOBean.setColId(resultSetDBOData.getInt("COL_ID"));
					DBOBean.setColNm(resultSetDBOData.getString("COL_NM"));

					dboEleData.add(DBOBean);

				}
				if (!dboEleList.isEmpty()) {
					DBOForm.setdBOElectricalData(dboEleData);
				}

			}
			List<DBOBean> genaddOnPriceList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetDBOData1 = callableStatement.getResultSet();
				while (resultSetDBOData1.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetDBOData1.getInt("ITEM_ID"));
					bean.setItemName(resultSetDBOData1.getString("ITEM_NAME"));

					genaddOnPriceList.add(bean);
				}
				DBOForm.setGeneralAddOnList(genaddOnPriceList);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOEle);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getDBOElectricalPanel(DBOForm DBOForm) {

		CallableStatement callableStatement = null;

		ResultSet resultSetDBOElepanel = null;
		ResultSet resultSetDBOElepanel1 = null;
		ResultSet resultSetDBOElepanel2 = null;
		ResultSet resultSetDBOElepanel3 = null;
		ResultSet resultSetDBOElepanel4 = null;
		ResultSet resultSetDBOElepanel5 = null;
		ResultSet resultSetDBOElepanel6 = null;
		ResultSet resultSetDBOElepanel7 = null;
		ResultSet resultSetDBOElepanel8 = null;
		ResultSet resultSetDBOElepanel9 = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		Connection connection = null;
		try {
			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_DBO_ELE_PANEL(?,?,?) }");
			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());
			callableStatement.setString("CUST_TYP", DBOForm.getCustCode());
			callableStatement.setString("PANEL_CD", DBOForm.getDboEleType());
			callableStatement.execute();
			resultSetDBOElepanel = callableStatement.getResultSet();

			while (resultSetDBOElepanel.next()) {
				resultOutParameterInt = resultSetDBOElepanel.getInt(1);
				resultOutParameterString = resultSetDBOElepanel.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOElepanel1 = callableStatement.getResultSet();
				List<DBOBean> excelQuesList = new ArrayList<>();
				while (resultSetDBOElepanel1.next()) {
					DBOBean bean = new DBOBean();
					bean.setColCd(resultSetDBOElepanel1.getString("COL_CD"));
					bean.setColNm(resultSetDBOElepanel1.getString("COL_NM"));
					bean.setStdPriceFlag(resultSetDBOElepanel1.getInt("STD_PRICE_FLG") == 1 ? true : false);
					bean.setOthersFlag(resultSetDBOElepanel1.getInt("OTH_FLG") == 1 ? true : false);
					bean.setOrderId(resultSetDBOElepanel1.getInt("ORDER_ID"));

					excelQuesList.add(bean);
				}
				if (!excelQuesList.isEmpty()) {
					DBOForm.setdBOElectricalColList(excelQuesList);
				}

			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOElepanel2 = callableStatement.getResultSet();
				List<DBOBean> sapDataList = new ArrayList<>();
				while (resultSetDBOElepanel2.next()) {
					DBOBean bean = new DBOBean();
					bean.setColCd(resultSetDBOElepanel2.getString("COL_CD"));
					bean.setColNm(resultSetDBOElepanel2.getString("COL_NM"));
					sapDataList.add(bean);
				}
				if (!sapDataList.isEmpty()) {
					DBOForm.setdBOElectricalColListSAP(sapDataList);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOElepanel3 = callableStatement.getResultSet();
				List<DBOBean> excelPriceList = new ArrayList<>();
				while (resultSetDBOElepanel3.next()) {
					DBOBean bean = new DBOBean();
					bean.setColValCd(resultSetDBOElepanel3.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetDBOElepanel3.getString("COL_VAL_NM"));
					bean.setColId(resultSetDBOElepanel3.getInt("COL_ID"));
					bean.setColNm(resultSetDBOElepanel3.getString("COL_NM"));
					bean.setMinVal(resultSetDBOElepanel3.getInt("MIN_VAL"));
					bean.setMaxVal(resultSetDBOElepanel3.getInt("MAX_VAL"));
					bean.setStdPriceFlag(resultSetDBOElepanel3.getInt("STD_PRICE_FLG") == 1 ? true : false);
					bean.setDefaultVal(resultSetDBOElepanel3.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setPercentage(resultSetDBOElepanel3.getFloat("PERCNT"));
					bean.setPercentageFlag(resultSetDBOElepanel3.getInt("PERCNT_FLG") == 1 ? true : false);
					bean.setDirectPriceFlag(resultSetDBOElepanel3.getFloat("DIR_PRICE_FLG") == 1 ? true : false);
					bean.setDirectPrice(Math.round(resultSetDBOElepanel3.getFloat("DIR_PRICE")));
					bean.setOrderId(resultSetDBOElepanel3.getInt("ORDER_ID"));

					if (bean.isDefaultVal()) {
						bean.setColValNm(bean.getColValNm() + " (TTL STD)");
					}
					excelPriceList.add(bean);
				}
				if (!excelPriceList.isEmpty()) {
					DBOForm.setdBOElectricalColWithPrice(excelPriceList);
					createDBOEleExcelJson(excelPriceList, DBOForm);
				}

			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOElepanel4 = callableStatement.getResultSet();
				List<DBOBean> sapPriceList = new ArrayList<>();
				while (resultSetDBOElepanel4.next()) {
					DBOBean bean = new DBOBean();
					bean.setColValCd(resultSetDBOElepanel4.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetDBOElepanel4.getString("COL_VAL_NM"));
					bean.setColId(resultSetDBOElepanel4.getInt("COL_ID"));
					bean.setColNm(resultSetDBOElepanel4.getString("COL_NM"));
					bean.setMinVal(resultSetDBOElepanel4.getInt("MIN_VAL"));
					bean.setMaxVal(resultSetDBOElepanel4.getInt("MAX_VAL"));
					bean.setDefaultVal(resultSetDBOElepanel4.getInt("DEFLT_FLG") == 1 ? true : false);

					sapPriceList.add(bean);
				}
				if (!sapPriceList.isEmpty()) {
					DBOForm.setdBOElectricalColWithPriceSAP(sapPriceList);
					createDBOEleSapJson(sapPriceList, DBOForm);
				}
			}

			ResultSet resultSetDBOElepanelNom = null;
			List<DBOBean> nominalOutputList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetDBOElepanelNom = callableStatement.getResultSet();
				while (resultSetDBOElepanelNom.next()) {
					DBOBean bean = new DBOBean();

					bean.setCostId(resultSetDBOElepanelNom.getInt("PRICE_ID"));
					bean.setMinVal(resultSetDBOElepanelNom.getFloat("MIN_VAL"));
					bean.setMaxVal(resultSetDBOElepanelNom.getFloat("MAX_VAL"));
					bean.setPrice(Math.round(resultSetDBOElepanelNom.getFloat("COST")));
					bean.setColId(resultSetDBOElepanelNom.getInt("COL_ID"));
					bean.setColNm(resultSetDBOElepanelNom.getString("COL_NM"));
					bean.setColValCd(resultSetDBOElepanelNom.getString("COL_VAL_CD"));

					nominalOutputList.add(bean);
				}
				DBOForm.setNominalOutputList(nominalOutputList);
			}

			List<DBOBean> spladdOnPriceList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetDBOElepanel7 = callableStatement.getResultSet();
				while (resultSetDBOElepanel7.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetDBOElepanel7.getInt("ITEM_ID"));
					bean.setItemCd(resultSetDBOElepanel7.getString("COL_CD"));
					bean.setItemName(resultSetDBOElepanel7.getString("COL_NM"));
					bean.setPriceId(resultSetDBOElepanel7.getInt("PRICE_ID"));
					bean.setPrice(Math.round(resultSetDBOElepanel7.getFloat("COST")));
					bean.setDefaultVal(resultSetDBOElepanel7.getInt("DEFLT_FLG") == 1 ? true : false);

					spladdOnPriceList.add(bean);
				}
				DBOForm.setDboEleSplAddonsPrice(spladdOnPriceList);
			}
			// ADD_INSTR_ID ITEM_ID ITEM_NAME ADD_INSTR_CD ADD_INSTR_NM COST
			// QTY_FLG
			List<DBOBean> addOnPriceList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetDBOElepanel9 = callableStatement.getResultSet();
				while (resultSetDBOElepanel9.next()) {
					DBOBean bean = new DBOBean();
					bean.setAddInstrId(resultSetDBOElepanel9.getInt("ADD_INSTR_ID"));
					bean.setItemId(resultSetDBOElepanel9.getInt("ITEM_ID"));
					bean.setItemName(resultSetDBOElepanel9.getString("ITEM_NAME"));
					bean.setAddInstrCd(resultSetDBOElepanel9.getString("ADD_INSTR_CD"));
					bean.setAddInstrName(resultSetDBOElepanel9.getString("ADD_INSTR_NM"));
					bean.setPrice(Math.round(resultSetDBOElepanel9.getFloat("COST")));
					bean.setQuantityFlag(resultSetDBOElepanel9.getInt("QTY_FLG") == 1 ? true : false);

					addOnPriceList.add(bean);
				}
				DBOForm.setAddOnInstrList(addOnPriceList);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOElepanel);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOElepanel1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOElepanel2);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOElepanel3);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOElepanel4);

		}
		return DBOForm;

	}

	private void createDBOEleExcelJson(List<DBOBean> dataList, DBOForm DBOForm) {

		// unqiue Drop down Name
		Map<Integer, String> dropdownNameMap = new HashMap<>();
		for (DBOBean bean : dataList) {
			dropdownNameMap.put(bean.getColId(), bean.getColNm());

		}

		List<DBOBean> excelPriceList = new ArrayList<>();
		for (Entry<Integer, String> dropDownType : dropdownNameMap.entrySet()) {

			DBOBean questionsBean = new DBOBean();
			questionsBean.getDropDownType().setKey(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());

			DBOForm.getdBOElectricalQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());

			excelPriceList.add(questionsBean);
		}

		DBOForm.setdBOElectricalQuestionsBean(excelPriceList);

		for (DBOBean dropDownType : DBOForm.getdBOElectricalQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			for (DBOBean bean : dataList) {
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					dropDownType.setOrderId(bean.getOrderId());

					SelectBox box = new SelectBox();
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());
					box.setDefaultVal(bean.isDefaultVal());
					box.setStdPriceFlag(bean.isStdPriceFlag());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setDirectPriceFlag(bean.isDirectPriceFlag());
					box.setDirectPrice(bean.getDirectPrice());
					box.setQuesKey(bean.getColId());
					box.setOrderId(bean.getOrderId());
					box.setQuesDesc(bean.getColNm());
					selectBoxList.add(box);

				}

				dropDownType.setDropDownValueList(selectBoxList);

			}

		}

		// DBOForm.setQuestionAnswerSet(dataList);
	}

	private void createDBOEleSapJson(List<DBOBean> dataList, DBOForm DBOForm) {

		// unqiue Drop down Name
		Map<Integer, String> dropdownNameMap = new HashMap<>();
		for (DBOBean bean : dataList) {
			dropdownNameMap.put(bean.getColId(), bean.getColNm());
		}

		List<DBOBean> questionsBeanList = new ArrayList<>();
		for (Entry<Integer, String> dropDownType : dropdownNameMap.entrySet()) {

			DBOBean questionsBean = new DBOBean();
			questionsBean.getDropDownType().setKey(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());

			DBOForm.getdBOElectricalSapQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());

			questionsBeanList.add(questionsBean);
		}

		DBOForm.setdBOElectricalSapQuestionsBean(questionsBeanList);

		for (DBOBean dropDownType : DBOForm.getdBOElectricalSapQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			for (DBOBean bean : dataList) {
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					SelectBox box = new SelectBox();
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());
					box.setDefaultVal(bean.isDefaultVal());
					box.setQuesKey(bean.getColId());

					box.setQuesDesc(bean.getColNm());
					selectBoxList.add(box);
				}
				dropDownType.setDropDownValueList(selectBoxList);
			}
		}

		// DBOForm.setQuestionAnswerSet(dataList);
	}

	@Override
	public DBOForm getDboElectricalPrice(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsgNew = null;
		ResultSet resultSetData = null;
		ResultSet resultSetDboEle = null;
		ResultSet resultSetDboEleAddOn = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable DBO_CONFIG = new SQLServerDataTable();
			DBO_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			DBO_CONFIG.addColumnMetadata("ME_COL_VAL", java.sql.Types.REAL);

			SQLServerDataTable DBO_CONFIG_SPLADD = new SQLServerDataTable();
			DBO_CONFIG_SPLADD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG_SPLADD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG_SPLADD.addColumnMetadata("COL_CD", java.sql.Types.VARCHAR);
			DBO_CONFIG_SPLADD.addColumnMetadata("QTY", java.sql.Types.REAL);

			for (DBOBean bean : DBOForm.getdBOEleSelectedExcelData()) {
				DBO_CONFIG.addRow(DBOForm.getQuotId(), DBOForm.getItemId(), bean.getColId(), bean.getColValCd(),
						bean.getUserVal());

			}

			for (DBOBean bean : DBOForm.getDboEleSplAddonsList()) {
				DBO_CONFIG_SPLADD.addRow(bean.getItemId(), bean.getPriceId(), bean.getColCd(), bean.getQuantity());

			}

			SQLServerDataTable DBO_CONFIG_DET = new SQLServerDataTable();
			DBO_CONFIG_DET.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG_DET.addColumnMetadata("ITEM_NAME", java.sql.Types.VARCHAR);
			DBO_CONFIG_DET.addColumnMetadata("QTY", java.sql.Types.REAL);
			DBO_CONFIG_DET.addColumnMetadata("COST", java.sql.Types.REAL);
			DBO_CONFIG_DET.addColumnMetadata("COMMENTS", java.sql.Types.VARCHAR);

			for (DBOBean bean : DBOForm.getDboEleCompOthersList()) {
				DBO_CONFIG_DET.addRow(bean.getOtherCompId(), bean.getOthCompName(), bean.getOthQuantity(),
						bean.getOthPrice(), bean.getOthRemarks());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_DBO_ELE_PRICE(?,?,?,?,?,?,?,?)}");

			callableStatement.setStructured(1, "dbo.DBO_CONFIG", DBO_CONFIG);
			callableStatement.setStructured(2, "dbo.DBO_CONFIG_SPLADD", DBO_CONFIG_SPLADD);
			callableStatement.setStructured(3, "dbo.DBO_CONFIG_DET", DBO_CONFIG_DET);
			callableStatement.setString(4, DBOForm.getPanelType()); // PANEL_TYP
			callableStatement.setString(5, DBOForm.getDboEleType()); // PANEL_CD(LT/HT)
			callableStatement.setString(6, DBOForm.getCustCode()); // CUST_TYP
			callableStatement.setFloat(7, DBOForm.getQuantity());
			// callableStatement.setFloat(7, DBOForm.getAdditionalCost());
			// //ADD_ON_COST
			// callableStatement.setString(8, DBOForm.getCompRemarks());
			// //COMMENTS
			callableStatement.setInt(8, DBOForm.getModifiedById());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();
				while (resultSetData.next()) {

					DBOForm.setBasicCost(Math.round(resultSetData.getFloat("COST")));
					// DBOForm.setEleAdditionalCost(Math.round(resultSetData.getFloat("ADD_ON_COST")));
				}
			}
			List<DBOBean> quotDboEleList = new ArrayList<>();
			List<DBOBean> quotDboEleSplAddList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetDboEle = callableStatement.getResultSet();
				while (resultSetDboEle.next()) {

					DBOForm.setPrice(Math.round(resultSetDboEle.getFloat(ItoConstants.COST)));

					// get DBO ELectrical Componenets and Special Addons Data
					// Starts

					if (resultSetDboEle.getInt("SPL_ADD_FLG") == 1 ? true : false) {
						DBOBean addOnBean = new DBOBean();
						addOnBean.setDboQuotDetId(resultSetDboEle.getInt("DBO_QUOT_DET_ID"));
						addOnBean.setItemId(resultSetDboEle.getInt("ITEM_ID"));
						addOnBean.setItemName(resultSetDboEle.getString("DBO_ELE_ITEM"));
						addOnBean.setColNm(resultSetDboEle.getString("COL_NM"));
						addOnBean.setColId(resultSetDboEle.getInt("COL_ID"));
						addOnBean.setColValCd(resultSetDboEle.getString("COL_VAL_CD"));
						addOnBean.setUserVal(resultSetDboEle.getInt("ME_COL_VAL"));
						addOnBean.setDboEleType(resultSetDboEle.getString("PANEL_CD"));
						addOnBean.setQuantity(resultSetDboEle.getInt("QTY"));
						addOnBean.setPrice(Math.round(resultSetDboEle.getFloat(ItoConstants.COST)));
						addOnBean.setTotalPrice(Math.round(resultSetDboEle.getFloat(ItoConstants.TOTAL_COST)));
						addOnBean.setOverwrittenPriceFlag(
								resultSetDboEle.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						addOnBean.setOverwrittenPrice(Math.round(resultSetDboEle.getFloat(ItoConstants.COST_ME)));
						addOnBean.setAddInstrCost(Math.round(resultSetDboEle.getFloat("COST_INSTR")));
						addOnBean.setSplAddonFlag(resultSetDboEle.getInt("SPL_ADD_FLG") == 1 ? true : false);

						quotDboEleSplAddList.add(addOnBean);
					} else {
						DBOBean bean = new DBOBean();
						bean.setDboQuotDetId(resultSetDboEle.getInt("DBO_QUOT_DET_ID"));
						bean.setItemId(resultSetDboEle.getInt("ITEM_ID"));
						bean.setItemName(resultSetDboEle.getString("DBO_ELE_ITEM"));
						bean.setColNm(resultSetDboEle.getString("COL_NM"));
						bean.setColId(resultSetDboEle.getInt("COL_ID"));
						bean.setDefaultVal(resultSetDboEle.getInt("DEFLT_FLG") == 1 ? true : false);
						if (resultSetDboEle.getInt("DEFLT_FLG") == 1 ? true : false) {
							bean.setColValCd(resultSetDboEle.getString("COL_VAL_CD") + " (TTL STD)");

						} else {
							bean.setColValCd(resultSetDboEle.getString("COL_VAL_CD"));
						}
						bean.setUserVal(resultSetDboEle.getInt("ME_COL_VAL"));
						bean.setPrice(Math.round(resultSetDboEle.getFloat(ItoConstants.COST)));
						bean.setTotalPrice(Math.round(resultSetDboEle.getFloat(ItoConstants.TOTAL_COST)));

						bean.setOverwrittenPriceFlag(
								resultSetDboEle.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetDboEle.getFloat(ItoConstants.COST_ME)));
						bean.setDboEleType(resultSetDboEle.getString("PANEL_CD"));
						bean.setQuantity(resultSetDboEle.getInt("QTY"));

						bean.setAddInstrCost(Math.round(resultSetDboEle.getFloat("COST_INSTR")));

						bean.setSplAddonFlag(resultSetDboEle.getInt("SPL_ADD_FLG") == 1 ? true : false);
						// others
						bean.setOthersFlag(resultSetDboEle.getInt("OTHR_COL_FLG") == 1 ? true : false);
						bean.setOthQuantity(resultSetDboEle.getFloat("OTHR_COL_QTY"));
						bean.setOthRemarks(resultSetDboEle.getString("OTHR_COL_COMMENTS"));
						// others

						quotDboEleList.add(bean);
					}
				}
				DBOForm.setQuotDboElectricalList(quotDboEleList);
				DBOForm.setQuotDboEleSplAddOnList(quotDboEleSplAddList);
			}
			// get DBO ELectrical Addons Data Starts

			List<DBOBean> addOnDboEleList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetDboEleAddOn = callableStatement.getResultSet();
				while (resultSetDboEleAddOn.next()) {
					DBOBean bean = new DBOBean();
					bean.setColId(resultSetDboEleAddOn.getInt("COL_ID"));
					bean.setColNm(resultSetDboEleAddOn.getString("COL_NM"));
					bean.setColValCd(resultSetDboEleAddOn.getString("COL_VAL_CD"));
					bean.setOthQuantity(resultSetDboEleAddOn.getFloat("OTHR_COL_QTY"));
					bean.setOthersFlag(resultSetDboEleAddOn.getInt("OTHR_COL_VAL_FLG") == 1 ? true : false);
					bean.setAddOnOthersFlag(resultSetDboEleAddOn.getInt("OTHR_COL_VAL_FLG") == 1 ? true : false);
					bean.setAddOnOverwrittenFlag(resultSetDboEleAddOn.getInt("ADD_ON_COST_ME_FLG") == 1 ? true : false);
					bean.setPrice(Math.round(resultSetDboEleAddOn.getFloat("COST_INSTR")));
					bean.setOthRemarks(resultSetDboEleAddOn.getString("OTHR_COL_COMMENTS"));
					bean.setAddOnListFlag(resultSetDboEleAddOn.getInt("ADD_ON_FLG") == 1 ? true : false);
					bean.setItemId(resultSetDboEleAddOn.getInt("ITEM_ID"));
					bean.setItemName(resultSetDboEleAddOn.getString("ITEM_NAME"));
					addOnDboEleList.add(bean);
					// COL_ID COL_NM COL_VAL_CD COST_INSTR OTHR_COL_QTY
					// OTHR_COL_VAL_FLG ADD_ON_COST_ME_FLG OTHR_COL_COMMENTS
					// ADD_ON_FLG ITEM_ID ITEM_NAME
				}
				DBOForm.setDboEleAddOnList(addOnDboEleList);
			}
			for (DBOBean dboComp : DBOForm.getQuotDboElectricalList()) {
				for (DBOBean addon : DBOForm.getDboEleAddOnList()) {
					if (dboComp.getItemId() == addon.getItemId()) {
						dboComp.setPrice(dboComp.getPrice() - addon.getPrice());
					}

				}
				for (DBOBean dboComp1 : DBOForm.getQuotDboElectricalList()) {
					if ((dboComp1.getItemId() == DBOForm.getItemId()) && (dboComp1.isSplAddonFlag() == false)) {
						DBOForm.setPrice(dboComp1.getPrice());
					}
				}

			}
			// get DBO ELectrical Addons Data Ends
		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@SuppressWarnings("null")
	@Override
	public DBOForm getDboElectricalPriceAddOn(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;
		ResultSet resultSetMsgNew = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetDboEleAddOn = null;

		try {

			DBOForm.setQuantity(1);
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable DBO_CONFIG_ADD_ON = new SQLServerDataTable();
			DBO_CONFIG_ADD_ON.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG_ADD_ON.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			DBO_CONFIG_ADD_ON.addColumnMetadata("QTY", java.sql.Types.REAL);
			DBO_CONFIG_ADD_ON.addColumnMetadata("ADD_ON_COST", java.sql.Types.REAL);
			DBO_CONFIG_ADD_ON.addColumnMetadata("OTHR_COL_VAL_FLG", java.sql.Types.NUMERIC);
			DBO_CONFIG_ADD_ON.addColumnMetadata("ADD_ON_COST_ME_FLG", java.sql.Types.NUMERIC);
			DBO_CONFIG_ADD_ON.addColumnMetadata("COMMENTS", java.sql.Types.VARCHAR);

			for (DBOBean bean : DBOForm.getSelectedDboEleAddOnColData()) {
				DBO_CONFIG_ADD_ON.addRow(bean.getColId(), bean.getColValCd(), bean.getQuantity(),
						bean.getAdditionalCost(), bean.isAddOnOthersFlag() ? 1 : 0,
						bean.isAddOnOverwrittenFlag() ? 1 : 0, bean.getRemarks());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_DBO_ELE_PRICE_ADDON(?,?,?,?)}");

			callableStatement.setStructured(1, "dbo.DBO_CONFIG_ADD_ON", DBO_CONFIG_ADD_ON);
			callableStatement.setInt(2, DBOForm.getQuotId());
			callableStatement.setInt(3, DBOForm.getItemId());
			callableStatement.setInt(4, DBOForm.getModifiedById());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();
				while (resultSetData.next()) {
					DBOForm.setEleAdditionalCost(Math.round(resultSetData.getFloat("ADD_ON_COST")));

				}
			}

			// get DBO ELectrical Addons Data Starts

			List<DBOBean> addOnDboEleList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetDboEleAddOn = callableStatement.getResultSet();
				while (resultSetDboEleAddOn.next()) {
					DBOBean bean = new DBOBean();
					bean.setColId(resultSetDboEleAddOn.getInt("COL_ID"));
					bean.setColNm(resultSetDboEleAddOn.getString("COL_NM"));
					bean.setColValCd(resultSetDboEleAddOn.getString("COL_VAL_CD"));
					bean.setOthQuantity(resultSetDboEleAddOn.getFloat("OTHR_COL_QTY"));
					bean.setOthersFlag(resultSetDboEleAddOn.getInt("OTHR_COL_VAL_FLG") == 1 ? true : false);
					bean.setAddOnOthersFlag(resultSetDboEleAddOn.getInt("OTHR_COL_VAL_FLG") == 1 ? true : false);
					bean.setAddOnOverwrittenFlag(resultSetDboEleAddOn.getInt("ADD_ON_COST_ME_FLG") == 1 ? true : false);
					bean.setPrice(Math.round(resultSetDboEleAddOn.getFloat("COST_INSTR")));
					bean.setOthRemarks(resultSetDboEleAddOn.getString("OTHR_COL_COMMENTS"));
					bean.setAddOnListFlag(resultSetDboEleAddOn.getInt("ADD_ON_FLG") == 1 ? true : false);
					bean.setItemId(resultSetDboEleAddOn.getInt("ITEM_ID"));
					bean.setItemName(resultSetDboEleAddOn.getString("ITEM_NAME"));
					addOnDboEleList.add(bean);

				}
				DBOForm.setDboEleAddOnList(addOnDboEleList);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getDboElectricalAddInstrPrice(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;
		ResultSet resultSetMsgNew = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			DBOForm.setQuantity(1);
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable DBO_CONFIG_ADD_ON = new SQLServerDataTable();
			DBO_CONFIG_ADD_ON.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG_ADD_ON.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			DBO_CONFIG_ADD_ON.addColumnMetadata("QTY", java.sql.Types.REAL);
			DBO_CONFIG_ADD_ON.addColumnMetadata("ADD_ON_COST", java.sql.Types.REAL);
			DBO_CONFIG_ADD_ON.addColumnMetadata("OTHR_COL_VAL_FLG", java.sql.Types.NUMERIC);
			DBO_CONFIG_ADD_ON.addColumnMetadata("ADD_ON_COST_ME_FLG", java.sql.Types.NUMERIC);
			DBO_CONFIG_ADD_ON.addColumnMetadata("COMMENTS", java.sql.Types.VARCHAR);

			for (DBOBean bean : DBOForm.getSelAddOnInstrList()) {
				DBO_CONFIG_ADD_ON.addRow(bean.getAddInstrId(), bean.getAddInstrCd(), bean.getAddInstrQuantity(),
						bean.getAddInstrCost(), bean.isAddOnOthersFlag() ? 1 : 0, bean.isAddOnOverwrittenFlag() ? 1 : 0,
						bean.getAddInstrRemarks());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_DBO_ELE_PRICE_ADD_INSTR(?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setInt(2, DBOForm.getItemId());
			callableStatement.setStructured(3, "dbo.DBO_CONFIG_ADD_ON", DBO_CONFIG_ADD_ON);
			callableStatement.setFloat(4, DBOForm.getQuantity());
			callableStatement.setInt(5, DBOForm.getModifiedById());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();
				while (resultSetData.next()) {

					DBOForm.setAddInstrTotCost(Math.round(resultSetData.getFloat("COST")));
					// DBOForm.setEleAdditionalCost(Math.round(resultSetData.getFloat("ADD_ON_COST")));
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveDBOElectricalData(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		try {

			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			List<String> itemsList = new ArrayList<>();
			for (Integer myInt : DBOForm.getItemIdList()) {
				itemsList.add(String.valueOf(myInt));
			}
			SQLServerDataTable DBO_CONFIG_DET = new SQLServerDataTable();
			DBO_CONFIG_DET.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			DBO_CONFIG_DET.addColumnMetadata("ITEM_NAME", java.sql.Types.VARCHAR);
			DBO_CONFIG_DET.addColumnMetadata("QTY", java.sql.Types.REAL);
			DBO_CONFIG_DET.addColumnMetadata("COST", java.sql.Types.REAL);
			DBO_CONFIG_DET.addColumnMetadata("COMMENTS", java.sql.Types.VARCHAR);

			if (!DBOForm.getDboEleOthersList().isEmpty()) {
				for (DBOBean bean : DBOForm.getDboEleOthersList()) {
					DBO_CONFIG_DET.addRow(bean.getOtherCompId(), bean.getOthCompName(), bean.getOthQuantity(),
							bean.getOthPrice(), bean.getOthRemarks());

				}
			}
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_DBO_ELE_PRICE(?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setString(2, String.join(",", itemsList)); // ITEM_ID_LIST

			callableStatement.setStructured(3, "dbo.DBO_CONFIG_DET", DBO_CONFIG_DET);
			callableStatement.setString(4, DBOForm.getDboEleType()); // PANEL_CD
			callableStatement.setInt(5, DBOForm.getOverwrittenPriceFlag());
			callableStatement.setFloat(6, DBOForm.getOverwrittenPrice());
			callableStatement.setInt(7, DBOForm.getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getDboMechanicalItems(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetDBOMec = null;
		ResultSet resultSetTurbineinstF2F = null;

		Connection connection = null;
		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_DBO_MECH_ITEMS(?) }");
			callableStatement.setString("TURB_CD", DBOForm.getTurbineCode());
			callableStatement.execute();
			resultSetDBOMec = callableStatement.getResultSet();

			List<DBOBean> dboMechListExcel = new ArrayList<>();
			List<DBOBean> dboMechListSap = new ArrayList<>();
			while (resultSetDBOMec.next()) {
				DBOBean DBOBean = new DBOBean();
				if (resultSetDBOMec.getInt("EXCEL_FLG") == 1) {
					DBOBean.setCatDetId(resultSetDBOMec.getInt("ITEM_ID"));
					DBOBean.setCatDetCd(resultSetDBOMec.getString("ITEM_CD"));
					DBOBean.setCatDetDesc(resultSetDBOMec.getString("ITEM_NAME"));
					DBOBean.setExcelFlag(resultSetDBOMec.getInt("EXCEL_FLG") == 1 ? true : false);
					DBOBean.setSapFlag(resultSetDBOMec.getInt("SAP_FLG") == 1 ? true : false);
					dboMechListExcel.add(DBOBean);
				}
				if (resultSetDBOMec.getInt("SAP_FLG") == 1) {
					DBOBean.setCatDetId(resultSetDBOMec.getInt("ITEM_ID"));
					DBOBean.setCatDetCd(resultSetDBOMec.getString("ITEM_CD"));
					DBOBean.setCatDetDesc(resultSetDBOMec.getString("ITEM_NAME"));
					DBOBean.setExcelFlag(resultSetDBOMec.getInt("EXCEL_FLG") == 1 ? true : false);
					DBOBean.setSapFlag(resultSetDBOMec.getInt("SAP_FLG") == 1 ? true : false);
					dboMechListSap.add(DBOBean);
				}

			}
			if (!dboMechListExcel.isEmpty()) {
				DBOForm.setdBOMechanicalListExcel(dboMechListExcel);
			}
			if (!dboMechListSap.isEmpty()) {
				DBOForm.setdBOMechanicalListSap(dboMechListSap);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetDBOMec);
			UtilityMethods.closeResource(connection, callableStatement, resultSetTurbineinstF2F);

		}
		return DBOForm;
	}

	private void createVarCostsJson(List<DBOBean> dataList, DBOForm DBOForm) {
		Map<String, String> dropdownNameMap = new HashMap<>();
		for (DBOBean bean : dataList) {
			dropdownNameMap.put(bean.getItemCd(), bean.getItemName());
		}

		List<DBOBean> questionsBeanList = new ArrayList<>();
		for (Entry<String, String> dropDownType : dropdownNameMap.entrySet()) {
			DBOBean questionsBean = new DBOBean();
			questionsBean.getDropDownType().setCode(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());

			for (DBOBean ques : dataList) {
				questionsBean.getDropDownType().setDependKey(ques.getCatDetCd());
			}
			DBOForm.getVarQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);
		}
		DBOForm.setVarQuestionsBean(questionsBeanList);
		for (DBOBean dropDownType : DBOForm.getVarQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			for (DBOBean bean : dataList) {
				if (dropDownType.getDropDownType().getCode().equalsIgnoreCase(bean.getItemCd())) {
					SelectBox box = new SelectBox();
					box.setDependKey(bean.getCatDetCd());
					box.setCode(bean.getItemCd());
					box.setQuesDesc(bean.getItemName());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setNoOfMonths(bean.getNoOfMonths());
					box.setRegionId(bean.getRegionId());
					box.setCustCode(bean.getCustCode());
					selectBoxList.add(box);
				}
				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	private void createSparesCostsJson(List<DBOBean> dataList, DBOForm DBOForm) {
		Map<String, String> dropdownNameMap = new HashMap<>();
		for (DBOBean bean : dataList) {
			dropdownNameMap.put(bean.getItemCd(), bean.getItemName());
		}

		List<DBOBean> questionsBeanList = new ArrayList<>();
		for (Entry<String, String> dropDownType : dropdownNameMap.entrySet()) {
			DBOBean questionsBean = new DBOBean();
			questionsBean.getDropDownType().setCode(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());

			for (DBOBean ques : dataList) {
				// if(dropDownType.getKey().equalsIgnoreCase(ques.getItemCd())){
				questionsBean.getDropDownType().setDependKey(ques.getCatDetCd());
				// }
			}
			DBOForm.getSparesQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);
		}
		DBOForm.setSparesQuestionsBean(questionsBeanList);
		for (DBOBean dropDownType : DBOForm.getSparesQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			for (DBOBean bean : dataList) {
				if (dropDownType.getDropDownType().getCode().equalsIgnoreCase(bean.getItemCd())) {
					SelectBox box = new SelectBox();
					box.setDependKey(bean.getCatDetCd());
					box.setCode(bean.getItemCd());
					box.setQuesDesc(bean.getItemName());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setNoOfMonths(bean.getNoOfMonths());
					box.setRegionId(bean.getRegionId());
					box.setCustCode(bean.getCustCode());
					selectBoxList.add(box);
				}
				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	@Override
	public DBOForm resetDboEleData(DBOForm dboForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		try {

			clearMessageFrom(dboForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RESET_DBO_ELE_PANEL(?,?)}");

			callableStatement.setInt("QUOT_ID", dboForm.getQuotId());
			callableStatement.setInt("MOD_BY", dboForm.getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				dboForm.setSuccessCode(resultOutParameterInt);
				dboForm.setSuccessMsg(resultOutParameterString);
				dboForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			dboForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return dboForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return dboForm;
	}

	@Override
	public DBOForm resetDboMechData(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RESET_DBO_MECH_FLOWRATE(?,?)}");

			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());
			callableStatement.setInt("MOD_BY", DBOForm.getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getDboEleAddOn(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetDBOEle1 = null;
		ResultSet resultSetDBOEle2 = null;
		ResultSet resultSetData = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_DBO_ELE_ADDON(?,?,?,?)}");

			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());
			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());
			callableStatement.setString("CUST_TYP", DBOForm.getCustCode()); // CUST_TYP(Domestic
																			// /Export)
			callableStatement.setString("PANEL_CD", DBOForm.getDboEleType()); // LT
																				// or
																				// HT

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOEle1 = callableStatement.getResultSet();
				List<DBOBean> eleAddonList = new ArrayList<>();
				while (resultSetDBOEle1.next()) {
					DBOBean bean = new DBOBean();
					bean.setColCd(resultSetDBOEle1.getString("COL_CD"));
					bean.setColNm(resultSetDBOEle1.getString("COL_NM"));
					bean.setColId(resultSetDBOEle1.getInt("COL_ID"));
					bean.setItemId(DBOForm.getItemId());
					eleAddonList.add(bean);
				}
				if (!eleAddonList.isEmpty()) {
					DBOForm.setDboEleAddOnColumns(eleAddonList);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetDBOEle2 = callableStatement.getResultSet();
				List<DBOBean> addOnList = new ArrayList<>();
				while (resultSetDBOEle2.next()) {
					DBOBean bean = new DBOBean();
					bean.setColId(resultSetDBOEle2.getInt("COL_ID"));
					bean.setColNm(resultSetDBOEle2.getString("COL_NM"));
					bean.setColValCd(resultSetDBOEle2.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetDBOEle2.getString("COL_VAL_NM"));
					bean.setPrice(Math.round(resultSetDBOEle2.getFloat("COST")));
					bean.setItemId(DBOForm.getItemId());
					addOnList.add(bean);
				}
				if (!addOnList.isEmpty()) {
					DBOForm.setDboEleAddOnColData(addOnList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getDboEleTotal(Integer quotId) {
		DBOForm dboForm = new DBOForm();
		try {

			String query = "SELECT  (CASE WHEN COST_ME_FLG = 1 THEN COST_ME ELSE TOTAL_COST END ) AS TOTAL FROM  DBO_QUOT_DBO_ELE_MAST A WHERE QUOT_ID = '"
					+ quotId + "';";

			List<DBOForm> data = jdbcTemplate.query(query, new RowMapper<DBOForm>() {
				public DBOForm mapRow(ResultSet rs, int rowNum) throws SQLException {
					DBOForm bean = new DBOForm();
					bean.setTotalCost(Math.round(rs.getFloat("TOTAL")));
					return bean;
				}
			});

			logger.info("data " + data);
			data.get(0).setSuccessCode(0);
			data.get(0).setSuccessMsg("SUCCESS");
			return data.get(0);

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return dboForm;
		}

	}

	@Override
	public DBOForm getDboMechTotal(Integer quotId) {
		DBOForm dboForm = new DBOForm();
		try {

			String query = "SELECT  (CASE WHEN COST_ME_FLG = 1 THEN COST_ME ELSE TOTAL_COST END ) AS TOTAL FROM  DBO_QUOT_DBO_MECH_MAST A WHERE QUOT_ID = '"
					+ quotId + "';";

			List<DBOForm> data = jdbcTemplate.query(query, new RowMapper<DBOForm>() {
				public DBOForm mapRow(ResultSet rs, int rowNum) throws SQLException {
					DBOForm bean = new DBOForm();
					bean.setTotalCost(Math.round(rs.getFloat("TOTAL")));
					return bean;
				}
			});

			logger.info("data " + data);
			data.get(0).setSuccessCode(0);
			data.get(0).setSuccessMsg("SUCCESS");
			return data.get(0);

		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return dboForm;
		}

	}

	@Override
	public DBOForm removeDboMechItem(Integer QUOT_ID, Integer ITEM_ID, Integer SUB_ITEM_ID) {
		DBOForm dboForm = new DBOForm();
		try {

			String query = "DELETE FROM DBO_QUOT_DBO_MECH_ITEM_DET WHERE DBO_QUOT_DET_ID IN (SELECT A.DBO_QUOT_DET_ID FROM DBO_QUOT_DBO_MECH_DET A,DBO_QUOT_DBO_MECH_MAST B WHERE A.DBO_QUOT_ID = B.DBO_QUOT_ID AND DBO_QUOT_DBO_MECH_ITEM_DET.DBO_QUOT_DET_ID = A.DBO_QUOT_DET_ID AND B.QUOT_ID = ? AND ITEM_ID = ? AND SUB_ITEM_ID = ?);";

			jdbcTemplate.update(query, QUOT_ID, ITEM_ID, SUB_ITEM_ID);

			String query1 = "DELETE FROM DBO_QUOT_DBO_MECH_DET WHERE DBO_QUOT_ID IN (SELECT DBO_QUOT_ID FROM DBO_QUOT_DBO_MECH_MAST WHERE QUOT_ID = ?) AND ITEM_ID = ? AND SUB_ITEM_ID = ?;";

			jdbcTemplate.update(query1, QUOT_ID, ITEM_ID, SUB_ITEM_ID);

			dboForm.setSuccessCode(0);
			dboForm.setSuccessMsg("SUCCESS");
		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return dboForm;
		}
		return dboForm;

	}

	@Override
	public QuotationForm getDboEditData(Integer quotId) {
		QuotationForm quotationForm = new QuotationForm();
		quotationForm.getSaveBasicDetails().setQuotId(quotId);
		try {
			// get DBO ELectrical Componenets and Special Addons Data Starts
			String query = "SELECT 	A.DBO_QUOT_DET_ID, A.ITEM_ID, (CASE WHEN OTHR_FLG = 1 THEN ITEM_NAME "
					+ " ELSE (SELECT CAT_DET_DESC FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'DBO' AND CAT_CD = 'ELE') AND CAT_DET_ID = A.ITEM_ID) "
					+ " END ) AS DBO_ELE_ITEM,  COL_ID,(CASE  WHEN SPL_ADD_FLG = 1  THEN ( SELECT COL_NM FROM DBO_ELE_SPLADDON_PRICE G WHERE G.PRICE_ID = C.COL_ID ) "
					+ " WHEN OTHR_COL_FLG = 1  THEN OTHR_COL_NM  ELSE 	(SELECT COL_NM FROM DBO_ELE_COL WHERE COL_ID = C.COL_ID)END )  AS COL_NM, "
					+ " COL_VAL_CD,ME_COL_VAL,A.COST,TOTAL_COST,COST_ME_FLG,COST_ME,PANEL_CD,QTY,COMMENTS,COST_INSTR,SPL_ADD_FLG,OTHR_COL_FLG,OTHR_COL_QTY,OTHR_COL_COMMENTS, "
					+ " (SELECT D.DEFLT_FLG  FROM DBO_ELE_COL_VAL D  WHERE D.COL_ID = C.COL_ID  AND D.COL_VAL_CD = C.COL_VAL_CD  AND D.EXCEL_FLG = 1 "
					+ " AND D.ITEM_ID = A.ITEM_ID  AND D.PANEL_CD = A.PANEL_CD) AS DEFLT_FLG  FROM DBO_QUOT_DBO_ELE_ITEM_DET C, DBO_QUOT_DBO_ELE_DET A, DBO_QUOT_DBO_ELE_MAST B "
					+ " WHERE A.DBO_QUOT_ID = B.DBO_QUOT_ID  AND C.DBO_QUOT_DET_ID = A.DBO_QUOT_DET_ID  AND  ADD_INSTR_FLG IS NULL  AND  ADD_ON_FLG IS NULL  AND B.QUOT_ID ='"
					+ quotId + "';";

			List<DBOBean> quotDboEleSplAddList = jdbcTemplate.query(query, new RowMapper<DBOBean>() {
				public DBOBean mapRow(ResultSet resultSetData9, int rowNum) throws SQLException {
					DBOBean addOnBean = new DBOBean();
					if (resultSetData9.getInt("SPL_ADD_FLG") == 1 ? true : false) {
						addOnBean.setDboQuotDetId(resultSetData9.getInt("DBO_QUOT_DET_ID"));
						addOnBean.setItemId(resultSetData9.getInt("ITEM_ID"));
						addOnBean.setItemName(resultSetData9.getString("DBO_ELE_ITEM"));
						addOnBean.setColNm(resultSetData9.getString("COL_NM"));
						addOnBean.setColId(resultSetData9.getInt("COL_ID"));
						addOnBean.setColValCd(resultSetData9.getString("COL_VAL_CD"));
						addOnBean.setUserVal(resultSetData9.getInt("ME_COL_VAL"));
						addOnBean.setDboEleType(resultSetData9.getString("PANEL_CD"));
						addOnBean.setQuantity(resultSetData9.getInt("QTY"));
						addOnBean.setPrice(Math.round(resultSetData9.getFloat(ItoConstants.COST)));
						addOnBean.setTotalPrice(Math.round(resultSetData9.getFloat(ItoConstants.TOTAL_COST)));
						addOnBean.setOverwrittenPriceFlag(
								resultSetData9.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						addOnBean.setOverwrittenPrice(Math.round(resultSetData9.getFloat(ItoConstants.COST_ME)));
						addOnBean.setAddInstrCost(Math.round(resultSetData9.getFloat("COST_INSTR")));
						addOnBean.setSplAddonFlag(resultSetData9.getInt("SPL_ADD_FLG") == 1 ? true : false);

					}
					return addOnBean;
				}
			});

			List<DBOBean> quotDboEleList = jdbcTemplate.query(query, new RowMapper<DBOBean>() {
				public DBOBean mapRow(ResultSet resultSetData9, int rowNum) throws SQLException {

					DBOBean bean = new DBOBean();
					if (!(resultSetData9.getInt("SPL_ADD_FLG") == 1 ? true : false)) {
						bean.setDboQuotDetId(resultSetData9.getInt("DBO_QUOT_DET_ID"));
						bean.setItemId(resultSetData9.getInt("ITEM_ID"));
						bean.setItemName(resultSetData9.getString("DBO_ELE_ITEM"));
						bean.setColNm(resultSetData9.getString("COL_NM"));
						bean.setColId(resultSetData9.getInt("COL_ID"));
						bean.setDefaultVal(resultSetData9.getInt("DEFLT_FLG") == 1 ? true : false);
						if (resultSetData9.getInt("DEFLT_FLG") == 1 ? true : false) {
							bean.setColValCd(resultSetData9.getString("COL_VAL_CD") + " (TTL STD)");

						} else {
							bean.setColValCd(resultSetData9.getString("COL_VAL_CD"));
						}
						bean.setUserVal(resultSetData9.getInt("ME_COL_VAL"));
						bean.setPrice(Math.round(resultSetData9.getFloat(ItoConstants.COST)));
						bean.setTotalPrice(Math.round(resultSetData9.getFloat(ItoConstants.TOTAL_COST)));

						bean.setOverwrittenPriceFlag(
								resultSetData9.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetData9.getFloat(ItoConstants.COST_ME)));
						bean.setDboEleType(resultSetData9.getString("PANEL_CD"));
						bean.setQuantity(resultSetData9.getInt("QTY"));

						bean.setAddInstrCost(Math.round(resultSetData9.getFloat("COST_INSTR")));

						bean.setSplAddonFlag(resultSetData9.getInt("SPL_ADD_FLG") == 1 ? true : false);
						// others
						bean.setOthersFlag(resultSetData9.getInt("OTHR_COL_FLG") == 1 ? true : false);
						bean.setOthQuantity(resultSetData9.getFloat("OTHR_COL_QTY"));
						bean.setOthRemarks(resultSetData9.getString("OTHR_COL_COMMENTS"));
						// others
					}
					return bean;
				}
			});

			quotationForm.setQuotDboElectricalList(quotDboEleList);
			quotationForm.setQuotDboEleSplAddOnList(quotDboEleSplAddList);

			// get DBO ELectrical Componenets and Special Addons Data Ends

			// get DBO ELectrical Additional Instruments Data Starts
			String addInstrQuery = "SELECT 	A.DBO_QUOT_DET_ID,"
					+ " A.ITEM_ID, (SELECT CAT_DET_DESC FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'DBO' AND CAT_CD = 'ADDINSTR') AND CAT_DET_ID = A.ITEM_ID) AS DBO_ELE_ITEM,"
					+ " COL_ID,(SELECT ADD_INSTR_NM FROM  DBO_ELE_ADD_INSTR_PRICE F WHERE F.ADD_INSTR_ID = C.COL_ID) AS COL_NM,"
					+ " COL_VAL_CD,OTHR_COL_QTY,COST_INSTR,A.COST,TOTAL_COST,QTY,OTHR_COL_VAL_FLG,ADD_ON_COST_ME_FLG,OTHR_COL_COMMENTS,ADD_INSTR_FLG,PANEL_CD FROM DBO_QUOT_DBO_ELE_ITEM_DET C,"
					+ " DBO_QUOT_DBO_ELE_DET A,DBO_QUOT_DBO_ELE_MAST B WHERE A.DBO_QUOT_ID = B.DBO_QUOT_ID AND C.DBO_QUOT_DET_ID = A.DBO_QUOT_DET_ID AND ADD_INSTR_FLG = 1 AND B.QUOT_ID = '"
					+ quotId + "';";

			List<DBOBean> quotDboEleAddInstrList = jdbcTemplate.query(addInstrQuery, new RowMapper<DBOBean>() {
				public DBOBean mapRow(ResultSet resultSetAddIntr, int rowNum) throws SQLException {

					DBOBean bean = new DBOBean();

					bean.setDboQuotDetId(resultSetAddIntr.getInt("DBO_QUOT_DET_ID"));
					bean.setItemId(resultSetAddIntr.getInt("ITEM_ID"));
					bean.setItemName(resultSetAddIntr.getString("DBO_ELE_ITEM"));
					bean.setColNm(resultSetAddIntr.getString("COL_NM"));
					bean.setColId(resultSetAddIntr.getInt("COL_ID"));
					bean.setColValCd(resultSetAddIntr.getString("COL_VAL_CD") + " (TTL STD)");
					bean.setUserVal(resultSetAddIntr.getInt("OTHR_COL_QTY"));
					bean.setAddInstrCost(Math.round(resultSetAddIntr.getFloat("COST_INSTR")));
					bean.setPrice(Math.round(resultSetAddIntr.getFloat(ItoConstants.COST)));
					bean.setTotalPrice(Math.round(resultSetAddIntr.getFloat(ItoConstants.TOTAL_COST)));
					bean.setQuantity(resultSetAddIntr.getInt("QTY"));
					bean.setAddOnOthersFlag(resultSetAddIntr.getInt("OTHR_COL_VAL_FLG") == 1 ? true : false);
					bean.setAddOnOverwrittenFlag(resultSetAddIntr.getInt("ADD_ON_COST_ME_FLG") == 1 ? true : false);

					bean.setOverwrittenPriceFlag(resultSetAddIntr.getInt("ADD_INSTR_FLG") == 1 ? true : false);
					bean.setAddInstrRemarks(resultSetAddIntr.getString("OTHR_COL_COMMENTS"));
					bean.setDboEleType(resultSetAddIntr.getString("PANEL_CD"));

					return bean;
				}
			});

			quotationForm.setAddInstrList(quotDboEleAddInstrList);

			// get DBO ELectrical Additional Instruments Data Ends

			// get DBO ELectrical Addons Data Starts

			String addOnsQuery = "SELECT COL_ID,	(SELECT COL_NM FROM DBO_ELE_COL WHERE COL_ID = C.COL_ID)  AS COL_NM,"
					+ " COL_VAL_CD,COST_INSTR,OTHR_COL_QTY,OTHR_COL_VAL_FLG,ADD_ON_COST_ME_FLG,OTHR_COL_COMMENTS,ADD_ON_FLG,"
					+ " ITEM_ID, (CASE WHEN ADD_INSTR_FLG = 1 THEN (SELECT CAT_DET_DESC FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'DBO' AND CAT_CD = 'ADDINSTR') AND CAT_DET_ID = A.ITEM_ID)"
					+ " ELSE (SELECT CAT_DET_DESC FROM CATEGORY_DET B WHERE B.CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'DBO' AND CAT_CD = 'ELE' ) AND B.CAT_DET_ID = A.ITEM_ID ) END) AS ITEM_NAME"
					+ " FROM DBO_QUOT_DBO_ELE_ITEM_DET C, DBO_QUOT_DBO_ELE_DET A, DBO_QUOT_DBO_ELE_MAST B WHERE A.DBO_QUOT_ID = B.DBO_QUOT_ID AND C.DBO_QUOT_DET_ID = A.DBO_QUOT_DET_ID"
					+ " AND  ADD_ON_FLG = 1 AND B.QUOT_ID = '" + quotId + "';";

			List<DBOBean> addOnDboEleList = jdbcTemplate.query(addOnsQuery, new RowMapper<DBOBean>() {
				public DBOBean mapRow(ResultSet resultSetDboEleAddOn, int rowNum) throws SQLException {
					DBOBean bean = new DBOBean();
					bean.setColId(resultSetDboEleAddOn.getInt("COL_ID"));
					bean.setColNm(resultSetDboEleAddOn.getString("COL_NM"));
					bean.setColValCd(resultSetDboEleAddOn.getString("COL_VAL_CD"));
					bean.setOthQuantity(resultSetDboEleAddOn.getFloat("OTHR_COL_QTY"));
					bean.setOthersFlag(resultSetDboEleAddOn.getInt("OTHR_COL_VAL_FLG") == 1 ? true : false);
					bean.setAddOnOthersFlag(resultSetDboEleAddOn.getInt("OTHR_COL_VAL_FLG") == 1 ? true : false);
					bean.setAddOnOverwrittenFlag(resultSetDboEleAddOn.getInt("ADD_ON_COST_ME_FLG") == 1 ? true : false);
					bean.setPrice(Math.round(resultSetDboEleAddOn.getFloat("COST_INSTR")));
					bean.setOthRemarks(resultSetDboEleAddOn.getString("OTHR_COL_COMMENTS"));
					bean.setAddOnListFlag(resultSetDboEleAddOn.getInt("ADD_ON_FLG") == 1 ? true : false);
					bean.setItemId(resultSetDboEleAddOn.getInt("ITEM_ID"));
					bean.setItemName(resultSetDboEleAddOn.getString("ITEM_NAME"));
					return bean;

				}
			});

			quotationForm.setDboEleAddOnList(addOnDboEleList);

			for (DBOBean dboComp : quotationForm.getQuotDboElectricalList()) {
				for (DBOBean addon : quotationForm.getDboEleAddOnList()) {
					if (dboComp.getItemId() == addon.getItemId()) {
						dboComp.setPrice(dboComp.getPrice() - addon.getPrice());
					}
				}

			}
			// get DBO ELectrical Addons Data Ends

			// get DBO Electrical Others Starts

			String othersQuery = "SELECT A.DBO_QUOT_DET_ID,A.DBO_QUOT_ID,ITEM_ID,(CASE WHEN OTHR_FLG = 1 THEN ITEM_NAME"
					+ " ELSE (SELECT CAT_DET_DESC FROM CATEGORY_DET B WHERE B.CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'DBO' AND CAT_CD = 'ELE' ) AND B.CAT_DET_ID = A.ITEM_ID )"
					+ " END) AS ITEM_NAME, QTY,COST,COMMENTS FROM DBO_QUOT_DBO_ELE_DET A,DBO_QUOT_DBO_ELE_MAST B WHERE B.QUOT_ID = '"
					+ quotId + "'" + " AND A.DBO_QUOT_ID = B.DBO_QUOT_ID"
					+ " AND OTHR_FLG = 1 ORDER BY DBO_QUOT_DET_ID;";

			List<DBOBean> genDboEleList = jdbcTemplate.query(othersQuery, new RowMapper<DBOBean>() {
				public DBOBean mapRow(ResultSet resultSetDbo, int rowNum) throws SQLException {
					DBOBean bean = new DBOBean();
					bean.setDboQuotDetId(resultSetDbo.getInt("DBO_QUOT_DET_ID"));
					bean.setOtherCompId(resultSetDbo.getInt("ITEM_ID"));
					bean.setOthCompName(resultSetDbo.getString("ITEM_NAME"));
					bean.setOthPrice(Math.round(resultSetDbo.getFloat(ItoConstants.COST)));
					bean.setOthRemarks(resultSetDbo.getString("COMMENTS"));
					bean.setOthQuantity(resultSetDbo.getFloat("QTY"));
					return bean;
				}
			});

			quotationForm.setQuotDboEleOthersList(genDboEleList);

			return quotationForm;

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return quotationForm;
		}

	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get F2Fitem List
	 */

	@Override
	public DBOForm getF2fItems(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetF2fItems = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_ITEMS(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				List<DBOBean> dboF2fItemList = new ArrayList<DBOBean>();
				resultSetF2fItems = callableStatement.getResultSet();
				while (resultSetF2fItems.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setF2fItemId(resultSetF2fItems.getInt("F2F_ITEM_ID"));
					DBOBean.setItemId(resultSetF2fItems.getInt("ITEM_ID"));
					DBOBean.setItemCd(resultSetF2fItems.getString("ITEM_CD"));
					DBOBean.setItemName(resultSetF2fItems.getString("ITEM_NAME"));
					DBOBean.setItemApplInd(resultSetF2fItems.getInt("ITEM_APPL_IND"));
					DBOBean.setItemOrder(resultSetF2fItems.getInt("ITEM_ORDER"));
					DBOBean.setSubItemId(resultSetF2fItems.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemCd(resultSetF2fItems.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetF2fItems.getString("SUB_ITEM_NAME"));
					DBOBean.setSubItemFlag(resultSetF2fItems.getInt("SUB_ITEM_FLG") == 1 ? true : false);
					DBOBean.setSubItemApplInd(resultSetF2fItems.getInt("SUB_ITEM_APPL_IND"));
					DBOBean.setSubItemOrder(resultSetF2fItems.getInt("SUB_ITEM_ORDER"));
					DBOBean.setSubItemTypeId(resultSetF2fItems.getInt("SUB_ITEM_TYP_ID"));
					DBOBean.setSubItemTypeCd(resultSetF2fItems.getString("SUB_ITEM_TYPE_CD"));
					DBOBean.setSubItemTypeName(resultSetF2fItems.getString("SUB_ITEM_TYPE_NAME"));
					DBOBean.setSubItemTypeFlag(resultSetF2fItems.getInt("SUB_ITEM_TYP_FLG") == 1 ? true : false);
					DBOBean.setEnabled(resultSetF2fItems.getInt("IS_ENABLE") == 1 ? true : false);
					dboF2fItemList.add(DBOBean);

				}

				if (!dboF2fItemList.isEmpty()) {
					DBOForm.setDboF2fItemList(dboF2fItemList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2fItems);

		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get Drop down type and Drop Down values
	 */

	public DBOForm getF2fPanels(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetF2fPanels1 = null;
		ResultSet resultSetF2fPanels2 = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_PANEL(?,?,?,?) }");
			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());
			callableStatement.setInt("SUB_ITEM_ID", DBOForm.getSubItemId());
			callableStatement.setInt("SUB_ITEM_TYP_ID", DBOForm.getSubItemTypeId());
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			List<DBOBean> dboF2fPanelList1 = new ArrayList<DBOBean>();

			if (callableStatement.getMoreResults()) {
				resultSetF2fPanels1 = callableStatement.getResultSet();

				while (resultSetF2fPanels1.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setColId(resultSetF2fPanels1.getInt("COL_ID"));
					DBOBean.setSubItemId(resultSetF2fPanels1.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemTypeId(resultSetF2fPanels1.getInt("SUB_ITEM_TYP_ID"));
					DBOBean.setSubItemCd(resultSetF2fPanels1.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetF2fPanels1.getString("SUB_ITEM_NAME"));
					DBOBean.setColCd(resultSetF2fPanels1.getString("COL_CD"));
					DBOBean.setColNm(resultSetF2fPanels1.getString("COL_NM"));
					DBOBean.setTechFlag(resultSetF2fPanels1.getInt("TECH_FLG"));
					DBOBean.setDispInd(resultSetF2fPanels1.getInt("DISP_IND"));
					DBOBean.setOrderId(resultSetF2fPanels1.getInt("ORDER_ID"));
					DBOBean.setAddOnNewColFlag(resultSetF2fPanels1.getInt("ADD_ON_NEW_COL_FLG"));
					DBOBean.setComrFlag(resultSetF2fPanels1.getInt("COMR_FLG"));
					DBOBean.setStdPriceFlag(resultSetF2fPanels1.getInt("STD_PRICE_FLG") == 1 ? true : false);
					DBOBean.setColType(resultSetF2fPanels1.getString("COL_TYPE"));
					dboF2fPanelList1.add(DBOBean);

				}

				if (!dboF2fPanelList1.isEmpty()) {
					DBOForm.setDboF2fPanelList1(dboF2fPanelList1);

				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetF2fPanels2 = callableStatement.getResultSet();
				List<DBOBean> dboF2fPanelList2 = new ArrayList<DBOBean>();

				while (resultSetF2fPanels2.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setSubItemId(resultSetF2fPanels2.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemCd(resultSetF2fPanels2.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetF2fPanels2.getString("SUB_ITEM_NAME"));
					DBOBean.setSubItemTypeId(resultSetF2fPanels2.getInt("SUB_ITEM_TYP_ID"));
					DBOBean.setSubItemTypeCd(resultSetF2fPanels2.getString("SUB_ITEM_TYPE_CD"));
					DBOBean.setSubItemTypeName(resultSetF2fPanels2.getString("SUB_ITEM_TYPE_NAME"));
					DBOBean.setColId(resultSetF2fPanels2.getInt("COL_ID"));
					DBOBean.setColNm(resultSetF2fPanels2.getString("COL_NM"));
					DBOBean.setColValCd(resultSetF2fPanels2.getString("COL_VAL_CD"));
					DBOBean.setColValNm(resultSetF2fPanels2.getString("COL_VAL_NM"));
					DBOBean.setCalcLinkFlag(resultSetF2fPanels2.getInt("CALC_LINK_FLG") == 1 ? true : false);
					DBOBean.setDefaultFlag(resultSetF2fPanels2.getInt("DEFLT_FLG") == 1 ? true : false);
					DBOBean.setDispInd(resultSetF2fPanels2.getInt("DISP_IND"));
					DBOBean.setOrderId(resultSetF2fPanels2.getInt("ORDER_ID"));
					DBOBean.setAddOnNewColFlag(resultSetF2fPanels2.getInt("ADD_ON_NEW_COL_FLG"));
					DBOBean.setAddOnFlg(resultSetF2fPanels2.getInt("ADD_ON_FLG"));
					DBOBean.setAddOnCostPer(resultSetF2fPanels2.getFloat("ADDON_COST_PER"));
					DBOBean.setAddOnDirCost(resultSetF2fPanels2.getFloat("ADDON_DIR_COST"));
					DBOBean.setApproxCostFlag(resultSetF2fPanels2.getInt("APPROX_COST_FLG"));
					DBOBean.setTechFlag(resultSetF2fPanels2.getInt("TECH_FLG"));
					DBOBean.setComrFlag(resultSetF2fPanels2.getInt("COMR_FLG"));
					dboF2fPanelList2.add(DBOBean);
				}
				if (!dboF2fPanelList2.isEmpty()) {
					DBOForm.setDboF2fPanelList2(dboF2fPanelList2);
					createDBOF2f2Json(dboF2fPanelList2, DBOForm, dboF2fPanelList1);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2fPanels1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2fPanels2);

		}
		return DBOForm;
	}

	private void createDBOF2f2Json(List<DBOBean> dataList, DBOForm DBOForm, List<DBOBean> ColumnList) {
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
				for (DBOBean column : ColumnList) {
					if (bean.getColId() == column.getColId()) {
						bean.setOrderId(column.getOrderId());
						bean.setColType(column.getColType());
						break;
					}
				}
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					dropDownType.setOrderId(bean.getOrderId());
					dropDownType.setColType(bean.getColType());
					dropDownType.setTechFlag(bean.getTechFlag());

					SelectBox box = new SelectBox();
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());
					box.setDefaultFlag(bean.isDefaultFlag());
					box.setStdPriceFlag(bean.isStdPriceFlag());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setDirectPriceFlag(bean.isDirectPriceFlag());
					box.setDirectPrice(bean.getDirectPrice());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());
					box.setOrderId(bean.getOrderId());
					box.setTechFlag(bean.getTechFlag());
					selectBoxList.add(box);
				}

				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get F2f tech price returns the basic cost and F2F tech list
	 */

	@Override
	public DBOForm getF2fTechPrice(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsgNew = null;
		ResultSet resultSetData1 = null;
		ResultSet resultSetData2 = null;
		ResultSet resultSetData3 = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable F2F_CONFIG = new SQLServerDataTable();
			F2F_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_TYP_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_TYP_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			F2F_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			F2F_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getF2fTechData()) {
				F2F_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getSubItemTypeId(), bean.getSubItemTypeName(), bean.getColId(),
						bean.getColNm(), bean.getColValCd(), bean.getQuantity(), bean.getCost(), bean.getTechComments(),
						bean.getComrComments(), bean.getAddOnNewColFlag(), bean.getTechFlag(), bean.getComrFlag()

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_F2F_TECH(?,?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.F2F_CONFIG", F2F_CONFIG);
			callableStatement.setFloat(2, DBOForm.getQuantity());
			callableStatement.setString(3, DBOForm.getF2fItemTechRemarks());
			callableStatement.setString(4, DBOForm.getF2fItemComrRemarks());
			callableStatement.setInt(5, DBOForm.getModifiedById());
			callableStatement.setFloat(6, DBOForm.getDiscountPer());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);

			}

			if (callableStatement.getMoreResults()) {
				resultSetData1 = callableStatement.getResultSet();
				while (resultSetData1.next()) {

					DBOForm.setF2FBasicCost(Math.round(resultSetData1.getFloat("COST")));

				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetData2 = callableStatement.getResultSet();
				while (resultSetData2.next()) {

					DBOForm.setF2FAddOnCost(Math.round(resultSetData2.getFloat("ADD_ON_COST")));

				}
			}

			List<DBOBean> f2fTechList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetData3 = callableStatement.getResultSet();
				while (resultSetData3.next()) {

					DBOBean bean = new DBOBean();
					bean.setF2fItemId(resultSetData3.getInt("F2F_ITEM_ID"));
					bean.setDetQuotId(resultSetData3.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetData3.getInt("ITEM_ID"));
					bean.setItemName(resultSetData3.getString("ITEM_NM"));
					bean.setSubItemId(resultSetData3.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetData3.getString("SUB_ITEM_NM"));
					bean.setSubItemTypeId(resultSetData3.getInt("SUB_ITEM_TYP_ID"));
					bean.setSubItemTypeName(resultSetData3.getString("SUB_ITEM_TYP_NM"));
					bean.setColId(resultSetData3.getInt("COL_ID"));
					bean.setColNm(resultSetData3.getString("COL_NM"));
					bean.setColValCd(resultSetData3.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetData3.getInt("LHS_FLG"));
					bean.setRhsFlag(resultSetData3.getInt("RHS_FLG") == 1 ? true : false);
					bean.setRhsCost(Math.round(resultSetData3.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetData3.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetData3.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetData3.getString("RHS_COL_COMR_COMMENTS"));
					bean.setDefaultVal(resultSetData3.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setBasicCost(Math.round(resultSetData3.getFloat("BASIC_COST")));
					bean.setOthersCost(Math.round(resultSetData3.getFloat("OTHERS_COST")));
					bean.setAddOnCost(Math.round(resultSetData3.getFloat("ADD_ON_COST")));
					bean.setItemCost(Math.round(resultSetData3.getFloat("ITEM_COST")));
					bean.setTotalPrice(Math.round(resultSetData3.getFloat(ItoConstants.TOTAL_COST)));
					bean.setTechFlag(resultSetData3.getInt("TECH_FLG"));
					bean.setComrFlag(resultSetData3.getInt("COMR_FLG"));
					bean.setOverwrittenPriceFlag(resultSetData3.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetData3.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetData3.getInt("QTY"));
					bean.setTechRemarks(resultSetData3.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetData3.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetData3.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetData3.getString("COMR_COMMENTS"));
					bean.setAddOnNewColFlag(resultSetData3.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setAddOnFlag(resultSetData3.getInt("ADD_ON_FLG") == 1 ? true : false);
					bean.setSubItemCost(Math.round(resultSetData3.getFloat("SUB_ITEM_COST")));
					bean.setSubItemTechRemarks(resultSetData3.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetData3.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setSubItemTypeCost(Math.round(resultSetData3.getFloat("SUB_ITEM_TYP_COST")));
					bean.setSubItemTypeTechRemarks(resultSetData3.getString("SUB_ITEM_TYP_TECH_COMMENTS"));
					bean.setSubItemTypeComrRemarks(resultSetData3.getString("SUB_ITEM_TYP_COMR_COMMENTS"));
					bean.setOthersFlag(resultSetData3.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setApproxCostFlag(resultSetData3.getInt("APPROX_COST_FLG"));
					bean.setItemApproxCostFlag(resultSetData3.getInt("ITEM_APPROX_COST_FLG"));
					bean.setColType(resultSetData3.getString("COL_TYPE"));
					bean.setDiscountPer(Math.round(resultSetData3.getFloat("DISCOUNT_PER")));
					bean.setNonDiscountCost(Math.round(resultSetData3.getFloat("NON_DISCOUNT_COST")));
					f2fTechList.add(bean);

				}
				if (!f2fTechList.isEmpty()) {
					DBOForm.setF2fTechList(f2fTechList);
				}

			}
		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData2);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData3);

		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get total price and save F2F items
	 */

	@Override
	public DBOForm saveF2fItem(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetf2f1 = null;
		ResultSet resultSetf2f2 = null;
		try {

			clearMessageFrom(DBOForm);
			List<String> itemsList = new ArrayList<>();
			for (Integer myInt : DBOForm.getItemIdList()) {
				itemsList.add(String.valueOf(myInt));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable F2F_CONFIG = new SQLServerDataTable();
			F2F_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_TYP_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_TYP_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			F2F_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			F2F_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getSavedF2fDataList()) {
				F2F_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getSubItemTypeId(), bean.getSubItemTypeName(), bean.getColId(),
						bean.getColNm(), bean.getColValCd(), bean.getQuantity(), bean.getCost(), bean.getTechComments(),
						bean.getComrComments(), bean.getAddOnNewColFlag(), bean.getTechFlag(), bean.getComrFlag()

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_F2F_ITEM(?,?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setString(2, String.join(",", itemsList));
			callableStatement.setStructured(3, "dbo.F2F_CONFIG ", F2F_CONFIG);
			callableStatement.setInt(4, DBOForm.getOverwrittenPriceFlag());
			callableStatement.setFloat(5, DBOForm.getOverwrittenPrice());
			logger.info(DBOForm.getOverwrittenPrice());
			callableStatement.setString(6, DBOForm.getF2fItemTechComments());
			callableStatement.setString(7, DBOForm.getF2fItemComrComments());
			callableStatement.setInt(8, DBOForm.getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetf2f1 = callableStatement.getResultSet();
				while (resultSetf2f1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetf2f1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveF2fList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetf2f2 = callableStatement.getResultSet();
				while (resultSetf2f2.next()) {
					DBOBean bean = new DBOBean();

					bean.setDetQuotId(resultSetf2f2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetf2f2.getInt("ITEM_ID"));
					bean.setItemName(resultSetf2f2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetf2f2.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetf2f2.getString("SUB_ITEM_NM"));
					bean.setSubItemTypeId(resultSetf2f2.getInt("SUB_ITEM_TYP_ID"));
					bean.setSubItemTypeName(resultSetf2f2.getString("SUB_ITEM_TYP_NM"));
					bean.setColId(resultSetf2f2.getInt("COL_ID"));
					bean.setColNm(resultSetf2f2.getString("COL_NM"));
					bean.setColValCd(resultSetf2f2.getString("COL_VAL_CD"));
					bean.setRhsCost(Math.round(resultSetf2f2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetf2f2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetf2f2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetf2f2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setBasicCost(Math.round(resultSetf2f2.getFloat("BASIC_COST")));
					bean.setOthersCost(Math.round(resultSetf2f2.getFloat("OTHERS_COST")));
					bean.setTotalPrice(Math.round(resultSetf2f2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setOverwrittenPriceFlag(resultSetf2f2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetf2f2.getFloat(ItoConstants.COST_ME)));
					logger.info(bean.getOverwrittenPrice());
					bean.setQuantity(resultSetf2f2.getInt("QTY"));
					bean.setTechRemarks(resultSetf2f2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetf2f2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetf2f2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetf2f2.getString("COMR_COMMENTS"));
					bean.setOthersFlag(resultSetf2f2.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setAddOnFlag(resultSetf2f2.getInt("ADD_ON_FLG") == 1 ? true : false);
					saveF2fList.add(bean);

				}
				if (!saveF2fList.isEmpty()) {
					DBOForm.setSaveF2fList(saveF2fList);
				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetf2f1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetf2f2);
		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get total price and save F2F sub items
	 */

	@Override
	public DBOForm saveF2fSubItem(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetf2fSub1 = null;
		ResultSet resultSetf2fSub2 = null;
		try {

			clearMessageFrom(DBOForm);
			List<String> subItemList = new ArrayList<>();
			for (Integer myInt : DBOForm.getSubItemIdList()) {
				subItemList.add(String.valueOf(myInt));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable F2F_CONFIG = new SQLServerDataTable();
			F2F_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_TYP_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_TYP_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			F2F_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			F2F_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getSavedF2fSubDataList()) {
				F2F_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getSubItemTypeId(), bean.getSubItemTypeName(), bean.getColId(),
						bean.getColNm(), bean.getColValCd(), bean.getQuantity(), bean.getCost(), bean.getTechComments(),
						bean.getComrComments(), bean.getAddOnNewColFlag(), bean.getTechFlag(), bean.getComrFlag()

				);

			}
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_F2F_SUB_ITEM(?,?,?,?,?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setInt(2, DBOForm.getItemId());
			callableStatement.setString(3, String.join(",", subItemList));
			callableStatement.setStructured(4, "dbo.F2F_CONFIG ", F2F_CONFIG);
			callableStatement.setInt(5, DBOForm.getOverwrittenPriceFlag());
			callableStatement.setFloat(6, DBOForm.getOverwrittenPrice());
			callableStatement.setString(7, DBOForm.getSubItemTechRemarks());
			callableStatement.setString(8, DBOForm.getSubItemComrRemarks());
			callableStatement.setInt(9, DBOForm.getModifiedById());
			callableStatement.setFloat(10, DBOForm.getDiscountPer());
			callableStatement.setInt(11, DBOForm.getQuantity());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetf2fSub1 = callableStatement.getResultSet();
				while (resultSetf2fSub1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetf2fSub1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveF2fSubList = new ArrayList<>();
			if ((callableStatement.getMoreResults())) {
				resultSetf2fSub2 = callableStatement.getResultSet();
				while (resultSetf2fSub2.next()) {
					DBOBean bean = new DBOBean();

					logger.info("inside f2f sub list");
					bean.setDetQuotId(resultSetf2fSub2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetf2fSub2.getInt("ITEM_ID"));
					bean.setItemName(resultSetf2fSub2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetf2fSub2.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetf2fSub2.getString("SUB_ITEM_NM"));
					logger.info(bean.getSubItemName());
					bean.setSubItemTypeId(resultSetf2fSub2.getInt("SUB_ITEM_TYP_ID"));
					bean.setSubItemTypeName(resultSetf2fSub2.getString("SUB_ITEM_TYP_NM"));
					bean.setColId(resultSetf2fSub2.getInt("COL_ID"));
					bean.setColNm(resultSetf2fSub2.getString("COL_NM"));
					bean.setColValCd(resultSetf2fSub2.getString("COL_VAL_CD"));
					bean.setRhsCost(Math.round(resultSetf2fSub2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetf2fSub2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetf2fSub2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetf2fSub2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setBasicCost(Math.round(resultSetf2fSub2.getFloat("BASIC_COST")));
					bean.setOthersCost(Math.round(resultSetf2fSub2.getFloat("OTHERS_COST")));
					bean.setTotalPrice(Math.round(resultSetf2fSub2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setOverwrittenPriceFlag(resultSetf2fSub2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetf2fSub2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetf2fSub2.getInt("QTY"));
					bean.setTechRemarks(resultSetf2fSub2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetf2fSub2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetf2fSub2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetf2fSub2.getString("COMR_COMMENTS"));
					bean.setOthersFlag(resultSetf2fSub2.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setAddOnFlag(resultSetf2fSub2.getInt("ADD_ON_FLG") == 1 ? true : false);
					bean.setSubItemCost(Math.round(resultSetf2fSub2.getFloat("SUB_ITEM_COST")));
					bean.setSubItemTechRemarks(resultSetf2fSub2.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetf2fSub2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setItemCost(Math.round(resultSetf2fSub2.getFloat("ITEM_COST")));
					bean.setDiscountPer(Math.round(resultSetf2fSub2.getFloat("DISCOUNT_PER")));
					bean.setNonDiscountCost(Math.round(resultSetf2fSub2.getFloat("NON_DISCOUNT_COST")));

					saveF2fSubList.add(bean);
					logger.info(saveF2fSubList);

				}
				if (!saveF2fSubList.isEmpty()) {
					DBOForm.setSaveF2fSubList(saveF2fSubList);
					logger.info(DBOForm.getSaveF2fSubList());
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetf2fSub1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetf2fSub2);
		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get total price and save F2F sub item type
	 */

	@Override
	public DBOForm saveF2fSubItemType(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetf2fSubType1 = null;
		ResultSet resultSetf2fSubType2 = null;
		try {

			clearMessageFrom(DBOForm);
			List<String> subTypeItemList = new ArrayList<>();
			for (Integer myInt : DBOForm.getSubTypeItemIdList()) {
				subTypeItemList.add(String.valueOf(myInt));// 38
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable F2F_CONFIG = new SQLServerDataTable();

			F2F_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_TYP_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("SUB_ITEM_TYP_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			F2F_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			F2F_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			F2F_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			F2F_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getSavedF2fSubTypeDataList()) {
				F2F_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getSubItemTypeId(), bean.getSubItemTypeName(), bean.getColId(),
						bean.getColNm(), bean.getColValCd(), bean.getQuantity(), bean.getCost(), bean.getTechComments(),
						bean.getComrComments(), bean.getAddOnNewColFlag(), bean.getTechFlag(), bean.getComrFlag()

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_F2F_SUB_ITEM_TYP(?,?,?,?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setInt(2, DBOForm.getItemId());
			callableStatement.setInt(3, DBOForm.getSubItemId());
			callableStatement.setString(4, String.join(",", subTypeItemList));
			callableStatement.setStructured(5, "dbo.F2F_CONFIG ", F2F_CONFIG);
			callableStatement.setInt(6, DBOForm.getOverwrittenPriceFlag());
			callableStatement.setFloat(7, DBOForm.getOverwrittenPrice());
			callableStatement.setString(8, DBOForm.getSubItemTypeTechRemarks());
			callableStatement.setString(9, DBOForm.getSubItemTypeComrRemarks());
			callableStatement.setInt(10, DBOForm.getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetf2fSubType1 = callableStatement.getResultSet();
				while (resultSetf2fSubType1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetf2fSubType1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveF2fSubTypeList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetf2fSubType2 = callableStatement.getResultSet();
				while (resultSetf2fSubType2.next()) {
					DBOBean bean = new DBOBean();

					bean.setDetQuotId(resultSetf2fSubType2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetf2fSubType2.getInt("ITEM_ID"));
					bean.setItemName(resultSetf2fSubType2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetf2fSubType2.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetf2fSubType2.getString("SUB_ITEM_NM"));
					bean.setSubItemTypeId(resultSetf2fSubType2.getInt("SUB_ITEM_TYP_ID"));
					bean.setSubItemTypeName(resultSetf2fSubType2.getString("SUB_ITEM_TYP_NM"));
					bean.setColId(resultSetf2fSubType2.getInt("COL_ID"));
					bean.setColNm(resultSetf2fSubType2.getString("COL_NM"));
					bean.setColValCd(resultSetf2fSubType2.getString("COL_VAL_CD"));
					bean.setRhsCost(Math.round(resultSetf2fSubType2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetf2fSubType2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetf2fSubType2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetf2fSubType2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setBasicCost(Math.round(resultSetf2fSubType2.getFloat("BASIC_COST")));
					bean.setOthersCost(Math.round(resultSetf2fSubType2.getFloat("OTHERS_COST")));
					bean.setTotalPrice(Math.round(resultSetf2fSubType2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setOverwrittenPriceFlag(
							resultSetf2fSubType2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetf2fSubType2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetf2fSubType2.getInt("QTY"));
					bean.setTechRemarks(resultSetf2fSubType2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetf2fSubType2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetf2fSubType2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetf2fSubType2.getString("COMR_COMMENTS"));
					bean.setOthersFlag(resultSetf2fSubType2.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setAddOnFlag(resultSetf2fSubType2.getInt("ADD_ON_FLG") == 1 ? true : false);
					bean.setSubItemTypeCost(Math.round(resultSetf2fSubType2.getFloat("SUB_ITEM_TYP_COST")));
					bean.setSubItemTypeTechRemarks(resultSetf2fSubType2.getString("SUB_ITEM_TYP_TECH_COMMENTS"));
					bean.setSubItemTypeComrRemarks(resultSetf2fSubType2.getString("SUB_ITEM_TYP_COMR_COMMENTS"));
					saveF2fSubTypeList.add(bean);

				}
				if (!saveF2fSubTypeList.isEmpty()) {
					DBOForm.setSaveF2fSubTypeList(saveF2fSubTypeList);
				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetf2fSubType1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetf2fSubType2);
		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get AddOn Coloumns and Add on column data in the drop down
	 */

	@Override
	public DBOForm getF2fAddOn(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetF2fAddOn1 = null;
		ResultSet resultSetF2fAddOn2 = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_ADDON(?,?,?,?)}");

			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());// 22
			callableStatement.setInt("SUB_ITEM_ID", DBOForm.getSubItemId());// 23
			callableStatement.setInt("SUB_ITEM_TYP_ID", DBOForm.getSubItemTypeId());
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());// 7

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetF2fAddOn1 = callableStatement.getResultSet();
				List<DBOBean> f2fAddonList1 = new ArrayList<>();
				while (resultSetF2fAddOn1.next()) {
					DBOBean bean = new DBOBean();
					bean.setItemId(resultSetF2fAddOn1.getInt("ITEM_ID"));
					bean.setColId(resultSetF2fAddOn1.getInt("COL_ID"));
					bean.setSubItemId(resultSetF2fAddOn1.getInt("SUB_ITEM_ID"));
					bean.setSubItemTypeId(resultSetF2fAddOn1.getInt("SUB_ITEM_TYP_ID"));
					bean.setSubItemCd(resultSetF2fAddOn1.getString("SUB_ITEM_CD"));
					bean.setSubItemName(resultSetF2fAddOn1.getString("SUB_ITEM_NAME"));
					bean.setColCd(resultSetF2fAddOn1.getString("COL_CD"));
					bean.setColNm(resultSetF2fAddOn1.getString("COL_NM"));
					bean.setTechFlag(resultSetF2fAddOn1.getInt("TECH_FLG"));
					bean.setDispInd(resultSetF2fAddOn1.getInt("DISP_IND"));
					bean.setOrderId(resultSetF2fAddOn1.getInt("ORDER_ID"));
					bean.setOthersFlag(resultSetF2fAddOn1.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setComrFlag(resultSetF2fAddOn1.getInt("COMR_FLG"));
					bean.setStdPriceFlag(resultSetF2fAddOn1.getInt("STD_PRICE_FLG") == 1 ? true : false);
					f2fAddonList1.add(bean);
				}
				if (!f2fAddonList1.isEmpty()) {
					DBOForm.setF2fAddOnColumns(f2fAddonList1);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetF2fAddOn2 = callableStatement.getResultSet();
				List<DBOBean> f2fAddonList2 = new ArrayList<>();
				while (resultSetF2fAddOn2.next()) {
					DBOBean bean = new DBOBean();
					bean.setF2fItemId(resultSetF2fAddOn2.getInt("F2F_ITEM_ID"));
					bean.setItemId(resultSetF2fAddOn2.getInt("ITEM_ID"));
					bean.setSubItemId(resultSetF2fAddOn2.getInt("SUB_ITEM_ID"));
					bean.setSubItemCd(resultSetF2fAddOn2.getString("SUB_ITEM_CD"));
					bean.setSubItemName(resultSetF2fAddOn2.getString("SUB_ITEM_NAME"));
					bean.setSubItemTypeId(resultSetF2fAddOn2.getInt("SUB_ITEM_TYP_ID"));
					bean.setSubItemTypeCd(resultSetF2fAddOn2.getString("SUB_ITEM_TYPE_CD"));
					bean.setSubItemTypeName(resultSetF2fAddOn2.getString("SUB_ITEM_TYPE_NAME"));
					bean.setColId(resultSetF2fAddOn2.getInt("COL_ID"));
					bean.setColNm(resultSetF2fAddOn2.getString("COL_NM"));
					bean.setColValCd(resultSetF2fAddOn2.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetF2fAddOn2.getString("COL_VAL_NM"));
					bean.setPrice(Math.round(resultSetF2fAddOn2.getFloat(ItoConstants.COST)));
					f2fAddonList2.add(bean);
				}
				if (!f2fAddonList2.isEmpty()) {
					DBOForm.setF2fAddOnColData(f2fAddonList2);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get AddOn cost and Add on list
	 */

	@Override
	public DBOForm getF2fPriceAddOn(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetF2fAddOnPrice1 = null;
		ResultSet resultSetF2fAddOnPrice2 = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable CONFIG = new SQLServerDataTable();
			CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("SUB_ITEM_TYP_ID", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("SUB_ITEM_TYP_NM", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("OTHR_COL_VAL_FLG", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			CONFIG.addColumnMetadata("COL_VAL_COMMENTS", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("ADD_ON_COST_ME_FLG", java.sql.Types.NUMERIC);
			for (DBOBean bean : DBOForm.getF2fSelectedPriceData()) {
				CONFIG.addRow(

						bean.getQuotId(), // 233//243//245
						bean.getItemId(), // 17//17//17
						bean.getItemName(), // NULL//steam turbine mod//stm
						bean.getSubItemId(), // 0//0
						bean.getSubItemName(), // NULL//null//null
						bean.getSubItemTypeId(), // 0//0
						bean.getSubItemTypeName(), // null//null
						bean.getColId(), // 0 //1//1
						bean.getColNm(), // NEW COL34//stop n emergency
						bean.getColValCd(), // Gear 44y.//hydraulically
						bean.getOthColValFlag(), bean.getQuantity(), // 1//1//1
						bean.getCost(), // 2000//2341//0
						bean.getTechRemarks(), // XX1YIYI1//EC//colvalcommnets//
						bean.getComrRemarks(), // XXXXCCX//sdf
						bean.getTechFlag(), bean.getComrFlag(), bean.getAddOnCostMeFlag());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_F2F_PRICE_ADDON(?,?)}");

			callableStatement.setStructured(1, "dbo.CONFIG", CONFIG);
			callableStatement.setInt(2, DBOForm.getModifiedById());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetF2fAddOnPrice1 = callableStatement.getResultSet();
				while (resultSetF2fAddOnPrice1.next()) {
					DBOForm.setF2fAddOnCost(Math.round(resultSetF2fAddOnPrice1.getFloat("ADD_ON_COST")));

				}
			}

			// get F2f Addons Data Starts

			List<DBOBean> f2fAddOnList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetF2fAddOnPrice2 = callableStatement.getResultSet();
				while (resultSetF2fAddOnPrice2.next()) {
					DBOBean bean = new DBOBean();

					bean.setF2fItemId(resultSetF2fAddOnPrice2.getInt("F2F_ITEM_ID"));
					bean.setDetQuotId(resultSetF2fAddOnPrice2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetF2fAddOnPrice2.getInt("ITEM_ID"));
					bean.setItemName(resultSetF2fAddOnPrice2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetF2fAddOnPrice2.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetF2fAddOnPrice2.getString("SUB_ITEM_NM"));
					bean.setSubItemTypeId(resultSetF2fAddOnPrice2.getInt("SUB_ITEM_TYP_ID"));
					bean.setSubItemTypeName(resultSetF2fAddOnPrice2.getString("SUB_ITEM_TYP_NM"));
					bean.setColId(resultSetF2fAddOnPrice2.getInt("COL_ID"));
					bean.setColNm(resultSetF2fAddOnPrice2.getString("COL_NM"));
					bean.setColValCd(resultSetF2fAddOnPrice2.getString("COL_VAL_CD"));
					bean.setRhsCost(Math.round(resultSetF2fAddOnPrice2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetF2fAddOnPrice2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetF2fAddOnPrice2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetF2fAddOnPrice2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setAddOnCostMeFlag(resultSetF2fAddOnPrice2.getInt("ADD_ON_COST_ME_FLG"));
					bean.setOthColValFlag(resultSetF2fAddOnPrice2.getInt("OTHR_COL_VAL_FLG"));
					bean.setOthersCost(Math.round(resultSetF2fAddOnPrice2.getFloat("OTHERS_COST")));
					bean.setItemCost(Math.round(resultSetF2fAddOnPrice2.getFloat("ITEM_COST")));
					bean.setTotalPrice(Math.round(resultSetF2fAddOnPrice2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setTechFlag(resultSetF2fAddOnPrice2.getInt("TECH_FLG"));
					bean.setComrFlag(resultSetF2fAddOnPrice2.getInt("COMR_FLG"));
					bean.setAddOnFlag(resultSetF2fAddOnPrice2.getInt("ADD_ON_FLG") == 1 ? true : false);

					f2fAddOnList.add(bean);
				}
				if (!f2fAddOnList.isEmpty()) {
					DBOForm.setF2fAddOnList(f2fAddOnList);

				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2fAddOnPrice1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2fAddOnPrice2);
		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get drop down values of cache list
	 */

	@Override
	public DBOForm getGeneralInput(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetF2fCache = null;
		ResultSet resultSetF2fCacheNew = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		Map<String, List<DBOBean>> f2fCacheType = new HashMap<>();

		Connection connection = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_GENERAL_INPUT(?) }");

			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> f2fCacheList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetF2fCache = callableStatement.getResultSet();

				while (resultSetF2fCache.next()) {

					DBOBean bean = new DBOBean();
					bean.setGenInId(resultSetF2fCache.getInt("GEN_IN_ID"));
					bean.setGroupCode(resultSetF2fCache.getString("GRP_CD"));
					bean.setGenType(resultSetF2fCache.getString("GEN_TYPE"));
					bean.setGenInNo(resultSetF2fCache.getInt("GEN_IN_NO"));
					bean.setGenInCd(resultSetF2fCache.getString("GEN_IN_CD"));
					bean.setGenInNm(resultSetF2fCache.getString("GEN_IN_NM"));
					bean.setCategoryValName(resultSetF2fCache.getString("CAT_VAL_NM"));
					bean.setCategoryValCode(resultSetF2fCache.getString("CAT_VAL_CD"));
					bean.setDefaultFlagNew(resultSetF2fCache.getInt("DEFLT_FLG"));
					bean.setGroupDescription(resultSetF2fCache.getString("GRP_DESC"));
					bean.setDispInd(resultSetF2fCache.getInt("DISP_IND"));
					f2fCacheList.add(bean);
				}

				if (!f2fCacheList.isEmpty()) {
					f2fCacheType.put("F2fCacheType", f2fCacheList);
					DBOForm.getDropDownColumnvalues().setF2fCacheList(f2fCacheType);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2fCache);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2fCacheNew);

		}

		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to save general input
	 */

	@Override
	public DBOForm saveGeneralInput(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSaveGenIn = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable GEN_IN = new SQLServerDataTable();

			GEN_IN.addColumnMetadata("GEN_IN_ID", java.sql.Types.NUMERIC);
			GEN_IN.addColumnMetadata("CAT_VAL_CD", java.sql.Types.VARCHAR);
			GEN_IN.addColumnMetadata("QTY", java.sql.Types.NUMERIC);
			GEN_IN.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			GEN_IN.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			GEN_IN.addColumnMetadata("ERROR", java.sql.Types.VARCHAR);

			for (DBOBean bean : DBOForm.getSavedGeneralInputList()) {
				GEN_IN.addRow(

						bean.getGenInId(), bean.getCategoryValCode(), bean.getQuantity(), bean.getDefaultFlagNew(),
						bean.getDispInd(), bean.getErrorMsg()
								

				);
				logger.info("Start of General Input");
				logger.info(bean.getGenInId());
				logger.info(bean.getCategoryValCode());
				logger.info(bean.getQuantity());
				logger.info(bean.getDefaultFlagNew());
				logger.info(bean.getDispInd());
				logger.info(bean.getErrorMsg());
				logger.info("End of General Input");

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_GENERAL_INPUT(?,?,?)}");
			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID
			callableStatement.setStructured(2, "dbo.GEN_IN ", GEN_IN);// GEN_IN
			callableStatement.setInt(3, DBOForm.getModifiedById());// MOD_BY
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				//
				//
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> saveGeneralInputList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSaveGenIn = callableStatement.getResultSet();
				while (resultSaveGenIn.next()) {
					DBOBean bean = new DBOBean();

					bean.setQuotGenId(resultSaveGenIn.getInt("QUOT_GEN_ID"));
					bean.setGenInId(resultSaveGenIn.getInt("GEN_IN_ID"));
					bean.setGroupCode(resultSaveGenIn.getString("GRP_CD"));
					bean.setGenType(resultSaveGenIn.getString("GEN_TYPE"));
					bean.setGenInNo(resultSaveGenIn.getInt("GEN_IN_NO"));
					bean.setGenInCd(resultSaveGenIn.getString("GEN_IN_CD"));
					bean.setGenInNm(resultSaveGenIn.getString("GEN_IN_NM"));
					bean.setCategoryValCode(resultSaveGenIn.getString("CAT_VAL_CD"));
					bean.setCategoryValName(resultSaveGenIn.getString("CAT_VAL_NM"));
					bean.setDefaultVal(resultSaveGenIn.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setQuantity(resultSaveGenIn.getInt("QTY"));
					bean.setGroupDescription(resultSaveGenIn.getString("GRP_DESC"));
					bean.setDispInd(resultSaveGenIn.getInt("DISP_IND"));
					saveGeneralInputList.add(bean);
				}
				if (!saveGeneralInputList.isEmpty()) {
					DBOForm.setSaveGeneralInputList(saveGeneralInputList);

				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get mechanical items list
	 */

	@Override
	public DBOForm getMechItems(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMechItems = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_MECH_ITEMS(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				List<DBOBean> dboMechItemList = new ArrayList<DBOBean>();
				resultSetMechItems = callableStatement.getResultSet();

				while (resultSetMechItems.next()) {
					DBOBean DBOBean = new DBOBean();

					DBOBean.setMechItemId(resultSetMechItems.getInt("MECH_ITEM_ID"));
					DBOBean.setItemId(resultSetMechItems.getInt("ITEM_ID"));
					DBOBean.setItemCd(resultSetMechItems.getString("ITEM_CD"));
					DBOBean.setItemName(resultSetMechItems.getString("ITEM_NAME"));
					DBOBean.setItemOrder(resultSetMechItems.getInt("ITEM_ORDER"));
					DBOBean.setSubItemId(resultSetMechItems.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemCd(resultSetMechItems.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetMechItems.getString("SUB_ITEM_NAME"));
					DBOBean.setSubItemFlag(resultSetMechItems.getInt("SUB_ITEM_FLG") == 1 ? true : false);
					DBOBean.setSubItemOrder(resultSetMechItems.getInt("SUB_ITEM_ORDER"));
					DBOBean.setEnabled(resultSetMechItems.getInt("IS_ENABLE") == 1 ? true : false);

					dboMechItemList.add(DBOBean);

				}

				if (!dboMechItemList.isEmpty()) {
					DBOForm.setDboMechItemList(dboMechItemList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechItems);

		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get mechanical panel
	 */

	public DBOForm getMechPanels(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMechPanel1 = null;
		ResultSet resultSetMechPanel2 = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_MECH_PANEL(?,?,?) }");
			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());
			callableStatement.setInt("SUB_ITEM_ID", DBOForm.getSubItemId());
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			List<DBOBean> dboMechPanelList1 = new ArrayList<DBOBean>();

			if (callableStatement.getMoreResults()) {
				resultSetMechPanel1 = callableStatement.getResultSet();

				while (resultSetMechPanel1.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setColId(resultSetMechPanel1.getInt("COL_ID"));
					DBOBean.setItemId(resultSetMechPanel1.getInt("ITEM_ID"));
					DBOBean.setSubItemId(resultSetMechPanel1.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemCd(resultSetMechPanel1.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetMechPanel1.getString("SUB_ITEM_NAME"));
					DBOBean.setColCd(resultSetMechPanel1.getString("COL_CD"));
					DBOBean.setColNm(resultSetMechPanel1.getString("COL_NM"));
					DBOBean.setTechFlag(resultSetMechPanel1.getInt("TECH_FLG"));
					DBOBean.setDispInd(resultSetMechPanel1.getInt("DISP_IND"));
					DBOBean.setOrderId(resultSetMechPanel1.getInt("ORDER_ID"));
					DBOBean.setAddOnNewColFlag(resultSetMechPanel1.getInt("ADD_ON_NEW_COL_FLG"));
					DBOBean.setComrFlag(resultSetMechPanel1.getInt("COMR_FLG"));
					DBOBean.setStdPriceFlag(resultSetMechPanel1.getInt("STD_PRICE_FLG") == 1 ? true : false);
					DBOBean.setColType(resultSetMechPanel1.getString("COL_TYPE"));
					dboMechPanelList1.add(DBOBean);

				}

				if (!dboMechPanelList1.isEmpty()) {
					DBOForm.setDboMechPanelList1(dboMechPanelList1);

				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetMechPanel2 = callableStatement.getResultSet();
				List<DBOBean> dboMechPanelList2 = new ArrayList<DBOBean>();

				while (resultSetMechPanel2.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setItemId(resultSetMechPanel2.getInt("ITEM_ID"));
					DBOBean.setSubItemId(resultSetMechPanel2.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemCd(resultSetMechPanel2.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetMechPanel2.getString("SUB_ITEM_NAME"));
					DBOBean.setColId(resultSetMechPanel2.getInt("COL_ID"));
					DBOBean.setColNm(resultSetMechPanel2.getString("COL_NM"));
					DBOBean.setColValCd(resultSetMechPanel2.getString("COL_VAL_CD"));
					DBOBean.setColValNm(resultSetMechPanel2.getString("COL_VAL_NM"));
					DBOBean.setDefaultFlag(resultSetMechPanel2.getInt("DEFLT_FLG") == 1 ? true : false);
					DBOBean.setDispInd(resultSetMechPanel2.getInt("DISP_IND"));
					DBOBean.setOrderId(resultSetMechPanel2.getInt("ORDER_ID"));
					DBOBean.setMinVal(resultSetMechPanel2.getFloat("MIN_VAL"));
					DBOBean.setMaxVal(resultSetMechPanel2.getFloat("MAX_VAL"));
					DBOBean.setComrFlag(resultSetMechPanel2.getInt("COMR_FLG"));
					DBOBean.setTechFlag(resultSetMechPanel2.getInt("TECH_FLG"));
					DBOBean.setCalcLinkFlag(resultSetMechPanel2.getInt("CALC_LINK_FLG") == 1 ? true : false);
					DBOBean.setAddOnFlg(resultSetMechPanel2.getInt("ADD_ON_FLG"));
					dboMechPanelList2.add(DBOBean);
				}
				if (!dboMechPanelList2.isEmpty()) {
					DBOForm.setDboMechPanelList2(dboMechPanelList2);
					createMechJson(dboMechPanelList2, DBOForm, dboMechPanelList1);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechPanel1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechPanel2);

		}
		return DBOForm;
	}

	private void createMechJson(List<DBOBean> dataList, DBOForm DBOForm, List<DBOBean> ColumnList) {
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
				for (DBOBean column : ColumnList) {
					if (bean.getColId() == column.getColId()) {
						bean.setOrderId(column.getOrderId());
						bean.setColType(column.getColType());
						// bean.setAddOnNewColFlag(column.getAddOnNewColFlag());
						break;
					}
				}
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					dropDownType.setOrderId(bean.getOrderId());
					dropDownType.setColType(bean.getColType());
					dropDownType.setTechFlag(bean.getTechFlag());

					SelectBox box = new SelectBox();
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());
					box.setDefaultFlag(bean.isDefaultFlag());
					box.setStdPriceFlag(bean.isStdPriceFlag());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setDirectPriceFlag(bean.isDirectPriceFlag());
					box.setDirectPrice(bean.getDirectPrice());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());
					box.setOrderId(bean.getOrderId());
					box.setTechFlag(bean.getTechFlag());

					selectBoxList.add(box);
				}

				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get Mech tech price returns the basic cost and Mech tech list
	 */

	@Override
	public DBOForm getMechTechPrice(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsgNew = null;
		ResultSet resultSetMechData1 = null;
		ResultSet resultSetMechData2 = null;
		ResultSet resultSetMechData3 = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable PANEL_CONFIG = new SQLServerDataTable();
			PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getMechTechData()) {
				PANEL_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getColId(), bean.getColNm(), bean.getColValCd(), bean.getQuantity(),
						bean.getCost(), bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnFlg(),
						bean.getTechFlag(), bean.getComrFlag());

				System.out.println("START");
				System.out.println(bean.getQuotId());
				System.out.println(bean.getItemId());
				System.out.println(bean.getItemName());
				System.out.println(bean.getSubItemId());
				System.out.println(bean.getSubItemName());
				System.out.println(bean.getColId());
				System.out.println(bean.getColNm());
				System.out.println(bean.getColValCd());
				System.out.println(bean.getQuantity());
				System.out.println(bean.getCost());
				System.out.println(bean.getTechRemarks());
				System.out.println(bean.getComrRemarks());
				System.out.println(bean.getAddOnFlg());
				System.out.println(bean.getTechFlag());
				System.out.println(bean.getComrFlag());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_MECH_TECH(?,?,?,?,?,?)}");

			callableStatement.setStructured(1, "dbo.PANEL_CONFIG", PANEL_CONFIG);
			callableStatement.setFloat(2, DBOForm.getQuantity());
			callableStatement.setString(3, DBOForm.getMechItemTechRemarks());
			callableStatement.setString(4, DBOForm.getMechItemComrRemarks());
			callableStatement.setInt(5, DBOForm.getModifiedById());
			callableStatement.setFloat(6, DBOForm.getDiscountPer());
			System.out.println(PANEL_CONFIG);
			System.out.println(DBOForm.getQuantity());
			System.out.println(DBOForm.getMechItemTechRemarks());
			System.out.println(DBOForm.getMechItemComrRemarks());
			System.out.println(DBOForm.getModifiedById());

			System.out.println("END");
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);

			}

			if (callableStatement.getMoreResults()) {
				resultSetMechData1 = callableStatement.getResultSet();
				while (resultSetMechData1.next()) {

					DBOForm.setBasicCost(Math.round(resultSetMechData1.getFloat("COST")));

				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetMechData3 = callableStatement.getResultSet();
				while (resultSetMechData3.next()) {

					DBOForm.setMechAddOnCost(Math.round(resultSetMechData3.getFloat("ADD_ON_COST")));

				}
			}

			List<DBOBean> mechTechList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetMechData2 = callableStatement.getResultSet();
				while (resultSetMechData2.next()) {
					DBOBean bean = new DBOBean();
					bean.setMechItemId(resultSetMechData2.getInt("MECH_ITEM_ID"));
					bean.setDetQuotId(resultSetMechData2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetMechData2.getInt("ITEM_ID"));
					bean.setItemName(resultSetMechData2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetMechData2.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetMechData2.getString("SUB_ITEM_NM"));
					bean.setColId(resultSetMechData2.getInt("COL_ID"));
					bean.setColNm(resultSetMechData2.getString("COL_NM"));
					bean.setColValCd(resultSetMechData2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetMechData2.getInt("LHS_FLG"));
					// bean.setRhsFlag(resultSetMechData2.getInt("RHS_FLG") == 1
					// ? true : false);
					bean.setRhsCost(Math.round(resultSetMechData2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetMechData2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetMechData2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetMechData2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setDefaultVal(resultSetMechData2.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setDispInd(resultSetMechData2.getInt("DISP_IND"));
					bean.setItemCost(resultSetMechData2.getFloat("ITEM_COST"));
					bean.setBasicCost(Math.round(resultSetMechData2.getFloat("BASIC_COST")));
					bean.setSubItemCost(Math.round(resultSetMechData2.getFloat("SUB_ITEM_COST")));
					bean.setAddOnCost(Math.round(resultSetMechData2.getFloat("ADD_ON_COST")));
					bean.setTotalPrice(Math.round(resultSetMechData2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setTechFlag(resultSetMechData2.getInt("TECH_FLG"));
					bean.setComrFlag(resultSetMechData2.getInt("COMR_FLG"));
					bean.setOverwrittenPriceFlag(
							resultSetMechData2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetMechData2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetMechData2.getInt("QTY"));
					bean.setTechRemarks(resultSetMechData2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetMechData2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetMechData2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetMechData2.getString("COMR_COMMENTS"));
					bean.setAddOnNewColFlag(resultSetMechData2.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setSubItemTechRemarks(resultSetMechData2.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetMechData2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setApproxCostFlag(resultSetMechData2.getInt("APPROX_COST_FLG"));
					bean.setItemApproxCostFlag(resultSetMechData2.getInt("ITEM_APPROX_COST_FLG"));
					bean.setColType(resultSetMechData2.getString("COL_TYPE"));
					bean.setTotalMechCost(Math.round(resultSetMechData2.getFloat("TOTAL_MECH_COST")));
					bean.setDiscountPer(Math.round(resultSetMechData2.getFloat("DISCOUNT_PER")));
					bean.setNonDiscountCost(Math.round(resultSetMechData2.getFloat("NON_DISCOUNT_COST")));
					mechTechList.add(bean);

				}

				DBOForm.setMechTechList(mechTechList);
			}
		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechData1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechData2);

		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get total cost and mech item list
	 */

	@Override
	public DBOForm saveMechItem(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetMech1 = null;
		ResultSet resultSetMech2 = null;

		try {

			clearMessageFrom(DBOForm);
			List<String> mechItemsList = new ArrayList<>();
			for (Integer myInt : DBOForm.getMechItemIdList()) {
				mechItemsList.add(String.valueOf(myInt));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_MECH_ITEM(?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID//50
			callableStatement.setString(2, String.join(",", mechItemsList)); // MECH_ITEM_ID_LIST//39
			callableStatement.setInt(3, DBOForm.getOverwrittenPriceFlag()); // COST_ME_FLG//0
			callableStatement.setFloat(4, DBOForm.getOverwrittenPrice()); // COST_ME//0.0
			callableStatement.setString(5, DBOForm.getTechComments());// TECH_COMMENTS//null//adf
			callableStatement.setString(6, DBOForm.getComrComments());// COMR_COMMENTSdfdsf
			callableStatement.setInt(7, DBOForm.getModifiedById());// MOD_BY//1
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetMech1 = callableStatement.getResultSet();
				while (resultSetMech1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetMech1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> savedMechList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetMech2 = callableStatement.getResultSet();
				while (resultSetMech2.next()) {
					DBOBean bean = new DBOBean();

					bean.setMechItemId(resultSetMech2.getInt("MECH_ITEM_ID"));
					bean.setDetQuotId(resultSetMech2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetMech2.getInt("ITEM_ID"));
					bean.setItemName(resultSetMech2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetMech2.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetMech2.getString("SUB_ITEM_NM"));
					bean.setColId(resultSetMech2.getInt("COL_ID"));
					bean.setColNm(resultSetMech2.getString("COL_NM"));
					bean.setColValCd(resultSetMech2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetMech2.getInt("LHS_FLG"));

					bean.setRhsCost(Math.round(resultSetMech2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetMech2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetMech2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetMech2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setDefaultVal(resultSetMech2.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setDispInd(resultSetMech2.getInt("DISP_IND"));
					bean.setBasicCost(Math.round(resultSetMech2.getFloat("BASIC_COST")));
					bean.setSubItemCost(Math.round(resultSetMech2.getFloat("SUB_ITEM_COST")));
					bean.setAddOnCost(Math.round(resultSetMech2.getFloat("ADD_ON_COST")));
					bean.setTotalPrice(Math.round(resultSetMech2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setTechFlag(resultSetMech2.getInt("TECH_FLG"));
					bean.setComrFlag(resultSetMech2.getInt("COMR_FLG"));
					bean.setOverwrittenPriceFlag(resultSetMech2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetMech2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetMech2.getInt("QTY"));
					bean.setTechRemarks(resultSetMech2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetMech2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetMech2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetMech2.getString("COMR_COMMENTS"));
					bean.setSubItemTechRemarks(resultSetMech2.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetMech2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setAddOnNewColFlag(resultSetMech2.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setSubItemComrRemarks(resultSetMech2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setTotalMechCost(Math.round(resultSetMech2.getFloat("TOTAL_MECH_COST")));

					savedMechList.add(bean);

				}
				DBOForm.setSavedMechList(savedMechList);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMech1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMech2);
		}
		return DBOForm;
	}

	/**
	 * @param DBOForm
	 * @return DBOForm with successCode and successMessage this method is used
	 *         to get total cost cost and mech sub item list
	 */

	@Override
	public DBOForm saveMechSubItem(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetMechSubItem1 = null;
		ResultSet resultSetMechSubItem2 = null;

		try {

			clearMessageFrom(DBOForm);
			List<String> mechSubItemsList = new ArrayList<>();
			for (Integer myInt : DBOForm.getMechSubItemIdList()) {
				mechSubItemsList.add(String.valueOf(myInt));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_MECH_SUB_ITEM(?,?,?,?,?,?)}");
			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID
			callableStatement.setInt(2, DBOForm.getItemId());// ITEM_ID
			callableStatement.setString(3, String.join(",", mechSubItemsList)); // MECH__SUB_ITEM_ID_LIST

			callableStatement.setString(4, DBOForm.getTechRemarks());// TECH_COMMENTS
			callableStatement.setString(5, DBOForm.getComrRemarks());// COMR_COMMENTS
			callableStatement.setInt(6, DBOForm.getModifiedById());// MOD_BY
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetMechSubItem1 = callableStatement.getResultSet();
				while (resultSetMechSubItem1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetMechSubItem1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveMechSubItemList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetMechSubItem2 = callableStatement.getResultSet();
				while (resultSetMechSubItem2.next()) {
					DBOBean bean = new DBOBean();
					bean.setMechItemId(resultSetMechSubItem2.getInt("MECH_ITEM_ID"));
					bean.setDetQuotId(resultSetMechSubItem2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetMechSubItem2.getInt("ITEM_ID"));
					bean.setItemName(resultSetMechSubItem2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetMechSubItem2.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetMechSubItem2.getString("SUB_ITEM_NM"));
					bean.setColId(resultSetMechSubItem2.getInt("COL_ID"));
					bean.setColNm(resultSetMechSubItem2.getString("COL_NM"));
					bean.setColValCd(resultSetMechSubItem2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetMechSubItem2.getInt("LHS_FLG"));
					// bean.setRhsFlag(resultSetMechSubItem2.getInt("RHS_FLG")
					// == 1 ? true : false);
					bean.setRhsCost(Math.round(resultSetMechSubItem2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetMechSubItem2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetMechSubItem2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetMechSubItem2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setDefaultVal(resultSetMechSubItem2.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setDispInd(resultSetMechSubItem2.getInt("DISP_IND"));
					bean.setItemCost(Math.round(resultSetMechSubItem2.getFloat("ITEM_COST")));
					
					bean.setBasicCost(Math.round(resultSetMechSubItem2.getFloat("BASIC_COST")));
					bean.setSubItemCost(Math.round(resultSetMechSubItem2.getFloat("SUB_ITEM_COST")));
					bean.setAddOnCost(Math.round(resultSetMechSubItem2.getFloat("ADD_ON_COST")));
					bean.setTotalPrice(Math.round(resultSetMechSubItem2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setTechFlag(resultSetMechSubItem2.getInt("TECH_FLG"));
					bean.setComrFlag(resultSetMechSubItem2.getInt("COMR_FLG"));
					bean.setOverwrittenPriceFlag(
							resultSetMechSubItem2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetMechSubItem2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetMechSubItem2.getInt("QTY"));
					bean.setTechRemarks(resultSetMechSubItem2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetMechSubItem2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetMechSubItem2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetMechSubItem2.getString("COMR_COMMENTS"));
					bean.setAddOnNewColFlag(resultSetMechSubItem2.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setSubItemTechRemarks(resultSetMechSubItem2.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetMechSubItem2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setApproxCostFlag(resultSetMechSubItem2.getInt("APPROX_COST_FLG"));
						bean.setItemApproxCostFlag(resultSetMechSubItem2.getInt("ITEM_APPROX_COST_FLG"));
						bean.setColType(resultSetMechSubItem2.getString("COL_TYPE"));
						bean.setTotalMechCost(Math.round(resultSetMechSubItem2.getFloat("TOTAL_MECH_COST")));
						bean.setDiscountPer(Math.round(resultSetMechSubItem2.getFloat("DISCOUNT_PER")));
						bean.setNonDiscountCost(Math.round(resultSetMechSubItem2.getFloat("NON_DISCOUNT_COST")));
					saveMechSubItemList.add(bean);

				}
				DBOForm.setSaveMechSubItemList(saveMechSubItemList);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechSubItem1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechSubItem2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm getMechAuxItems(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMechAuxItems = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_MECH_AUX_ITEMS(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				List<DBOBean> dboMechAuxItemList = new ArrayList<DBOBean>();
				resultSetMechAuxItems = callableStatement.getResultSet();

				while (resultSetMechAuxItems.next()) {
					DBOBean DBOBean = new DBOBean();

					DBOBean.setMechItemId(resultSetMechAuxItems.getInt("MECH_ITEM_ID"));
					DBOBean.setItemId(resultSetMechAuxItems.getInt("ITEM_ID"));
					DBOBean.setItemCd(resultSetMechAuxItems.getString("ITEM_CD"));
					DBOBean.setItemName(resultSetMechAuxItems.getString("ITEM_NAME"));
					DBOBean.setItemOrder(resultSetMechAuxItems.getInt("ITEM_ORDER"));
					DBOBean.setEnabled(resultSetMechAuxItems.getInt("IS_ENABLE") == 1 ? true : false);
					DBOBean.setNewPanelFlag(resultSetMechAuxItems.getInt("NEW_PANEL_FLG") == 1 ? true : false);

					dboMechAuxItemList.add(DBOBean);

				}

				if (!dboMechAuxItemList.isEmpty()) {
					DBOForm.setDboMechAuxItemList(dboMechAuxItemList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechAuxItems);

		}
		return DBOForm;
	}

	public DBOForm getMechAuxPanels(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMechAuxPanel1 = null;
		ResultSet resultSetMechAuxPanel2 = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_MECH_AUX_PANEL(?,?) }");
			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());// 164
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());// 132

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			List<DBOBean> dboMechAuxPanelList1 = new ArrayList<DBOBean>();

			if (callableStatement.getMoreResults()) {
				resultSetMechAuxPanel1 = callableStatement.getResultSet();

				while (resultSetMechAuxPanel1.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setColId(resultSetMechAuxPanel1.getInt("COL_ID"));
					DBOBean.setItemId(resultSetMechAuxPanel1.getInt("ITEM_ID"));
					DBOBean.setColCd(resultSetMechAuxPanel1.getString("COL_CD"));
					DBOBean.setColNm(resultSetMechAuxPanel1.getString("COL_NM"));
					DBOBean.setOrderId(resultSetMechAuxPanel1.getInt("ORDER_ID"));
					DBOBean.setAddOnNewColFlag(resultSetMechAuxPanel1.getInt("ADD_ON_NEW_COL_FLG"));
					DBOBean.setTechFlag(resultSetMechAuxPanel1.getInt("TECH_FLG"));
					DBOBean.setComrFlag(resultSetMechAuxPanel1.getInt("COMR_FLG"));
					DBOBean.setDispInd(resultSetMechAuxPanel1.getInt("DISP_IND"));
					DBOBean.setStdPriceFlag(resultSetMechAuxPanel1.getInt("STD_PRICE_FLG") == 1 ? true : false);
					DBOBean.setColType(resultSetMechAuxPanel1.getString("COL_TYPE"));
					dboMechAuxPanelList1.add(DBOBean);

				}

				if (!dboMechAuxPanelList1.isEmpty()) {
					DBOForm.setDboMechAuxPanelList1(dboMechAuxPanelList1);

				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetMechAuxPanel2 = callableStatement.getResultSet();
				List<DBOBean> dboMechAuxPanelList2 = new ArrayList<DBOBean>();

				while (resultSetMechAuxPanel2.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setItemId(resultSetMechAuxPanel2.getInt("ITEM_ID"));
					DBOBean.setColId(resultSetMechAuxPanel2.getInt("COL_ID"));
					DBOBean.setColNm(resultSetMechAuxPanel2.getString("COL_NM"));
					DBOBean.setColValCd(resultSetMechAuxPanel2.getString("COL_VAL_CD"));
					DBOBean.setColValNm(resultSetMechAuxPanel2.getString("COL_VAL_NM"));
					DBOBean.setDefaultFlag(resultSetMechAuxPanel2.getInt("DEFLT_FLG") == 1 ? true : false);
					DBOBean.setDispInd(resultSetMechAuxPanel2.getInt("DISP_IND"));
					DBOBean.setOrderId(resultSetMechAuxPanel2.getInt("ORDER_ID"));
					DBOBean.setComrFlag(resultSetMechAuxPanel2.getInt("COMR_FLG"));
					DBOBean.setTechFlag(resultSetMechAuxPanel2.getInt("TECH_FLG"));
					DBOBean.setAddOnFlg(resultSetMechAuxPanel2.getInt("ADD_ON_FLG"));
					dboMechAuxPanelList2.add(DBOBean);
				}
				if (!dboMechAuxPanelList2.isEmpty()) {
					DBOForm.setDboMechAuxPanelList2(dboMechAuxPanelList2);
					createMechAuxJson(dboMechAuxPanelList2, DBOForm, dboMechAuxPanelList1);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechAuxPanel1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechAuxPanel2);

		}
		return DBOForm;
	}

	private void createMechAuxJson(List<DBOBean> dataList, DBOForm DBOForm, List<DBOBean> ColumnList) {
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
				for (DBOBean column : ColumnList) {
					if (bean.getColId() == column.getColId()) {
						bean.setOrderId(column.getOrderId());
						bean.setColType(column.getColType());
						break;
					}
				}
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					dropDownType.setOrderId(bean.getOrderId());
					dropDownType.setColType(bean.getColType());
					SelectBox box = new SelectBox();
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());
					box.setDefaultFlag(bean.isDefaultFlag());
					box.setStdPriceFlag(bean.isStdPriceFlag());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setDirectPriceFlag(bean.isDirectPriceFlag());
					box.setDirectPrice(bean.getDirectPrice());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());
					box.setOrderId(bean.getOrderId());
					selectBoxList.add(box);
				}

				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	@Override
	public DBOForm getMechAuxTechPrice(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsgNew = null;
		ResultSet resultSetMechAuxData1 = null;
		ResultSet resultSetMechAuxData2 = null;
		ResultSet resultSetMechAuxData3 = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable PANEL_CONFIG = new SQLServerDataTable();
			PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getMechAuxTechData()) {
				PANEL_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getColId(), bean.getColNm(), bean.getColValCd(), bean.getQuantity(),
						bean.getCost(), bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnFlg(),
						bean.getTechFlag(), bean.getComrFlag());

				System.out.println("START OF OBEJECT");
				System.out.println(bean.getQuotId());
				System.out.println(bean.getItemId());
				System.out.println(bean.getItemName());
				System.out.println(bean.getSubItemId());
				System.out.println(bean.getSubItemName());
				System.out.println(bean.getColId());
				System.out.println(bean.getColNm());
				System.out.println(bean.getColValCd());
				System.out.println(bean.getQuantity());
				System.out.println(bean.getCost());
				System.out.println(bean.getTechRemarks());
				System.out.println(bean.getComrRemarks());
				System.out.println(bean.getAddOnFlg());
				System.out.println(bean.getTechFlag());
				System.out.println(bean.getComrFlag());
				System.out.println("END OF OBEJECT");

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_MECH_AUX_TECH(?,?,?,?,?,?)}");

			callableStatement.setStructured(1, "dbo.PANEL_CONFIG", PANEL_CONFIG);
			callableStatement.setFloat(2, DBOForm.getQuantity());
			callableStatement.setString(3, DBOForm.getMechAuxItemTechRemarks());
			callableStatement.setString(4, DBOForm.getMechAuxItemComrRemarks());
			callableStatement.setInt(5, DBOForm.getModifiedById());
			callableStatement.setFloat(6, DBOForm.getDiscountPer());

			System.out.println(PANEL_CONFIG);
			System.out.println("START");

			System.out.println(DBOForm.getQuantity());
			System.out.println(DBOForm.getMechAuxItemTechRemarks());
			System.out.println(DBOForm.getMechAuxItemComrRemarks());
			System.out.println(DBOForm.getModifiedById());

			System.out.println("END");

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);

			}

			if (callableStatement.getMoreResults()) {
				resultSetMechAuxData1 = callableStatement.getResultSet();
				while (resultSetMechAuxData1.next()) {

					DBOForm.setBasicCost(Math.round(resultSetMechAuxData1.getFloat("COST")));

				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetMechAuxData3 = callableStatement.getResultSet();
				while (resultSetMechAuxData3.next()) {

					DBOForm.setMechAuxAddOnCost(Math.round(resultSetMechAuxData3.getFloat("ADD_ON_COST")));

				}
			}

			List<DBOBean> mechAuxTechList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetMechAuxData2 = callableStatement.getResultSet();

				while (resultSetMechAuxData2.next()) {
					DBOBean bean = new DBOBean();
					bean.setMechItemId(resultSetMechAuxData2.getInt("MECH_ITEM_ID"));
					bean.setDetQuotId(resultSetMechAuxData2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetMechAuxData2.getInt("ITEM_ID"));
					bean.setItemName(resultSetMechAuxData2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetMechAuxData2.getInt("SUB_ITEM_ID"));

					bean.setColId(resultSetMechAuxData2.getInt("COL_ID"));
					bean.setColNm(resultSetMechAuxData2.getString("COL_NM"));
					bean.setColValCd(resultSetMechAuxData2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetMechAuxData2.getInt("LHS_FLG"));

					bean.setRhsCost(Math.round(resultSetMechAuxData2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetMechAuxData2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetMechAuxData2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetMechAuxData2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setDefaultVal(resultSetMechAuxData2.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setItemCost(Math.round(resultSetMechAuxData2.getFloat("ITEM_COST")));
					bean.setBasicCost(Math.round(resultSetMechAuxData2.getFloat("BASIC_COST")));
					bean.setSubItemCost(Math.round(resultSetMechAuxData2.getFloat("SUB_ITEM_COST")));
					bean.setAddOnCost(Math.round(resultSetMechAuxData2.getFloat("ADD_ON_COST")));
					bean.setTotalPrice(Math.round(resultSetMechAuxData2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setTechFlag(resultSetMechAuxData2.getInt("TECH_FLG"));
					bean.setComrFlag(resultSetMechAuxData2.getInt("COMR_FLG"));
					bean.setOverwrittenPriceFlag(
							resultSetMechAuxData2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetMechAuxData2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetMechAuxData2.getInt("QTY"));
					bean.setTechRemarks(resultSetMechAuxData2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetMechAuxData2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetMechAuxData2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetMechAuxData2.getString("COMR_COMMENTS"));
					bean.setAddOnNewColFlag(resultSetMechAuxData2.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setSubItemTechRemarks(resultSetMechAuxData2.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetMechAuxData2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setOthersFlag(resultSetMechAuxData2.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setApproxCostFlag(resultSetMechAuxData2.getInt("APPROX_COST_FLG"));
					bean.setItemApproxCostFlag(resultSetMechAuxData2.getInt("ITEM_APPROX_COST_FLG"));
					bean.setColType(resultSetMechAuxData2.getString("COL_TYPE"));
					bean.setDiscountPer(Math.round(resultSetMechAuxData2.getFloat("DISCOUNT_PER")));
					bean.setNonDiscountCost(Math.round(resultSetMechAuxData2.getFloat("NON_DISCOUNT_COST")));
					mechAuxTechList.add(bean);

				}

				DBOForm.setMechAuxTechList(mechAuxTechList);
			}
		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechAuxData1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechAuxData3);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechAuxData2);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveMechAuxItem(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetMechAux1 = null;
		ResultSet resultSetMechAux2 = null;

		try {

			clearMessageFrom(DBOForm);
			List<String> mechAuxItemsList = new ArrayList<>();
			for (Integer myInt : DBOForm.getMechAuxItemIdList()) {
				mechAuxItemsList.add(String.valueOf(myInt));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable PANEL_CONFIG = new SQLServerDataTable();
			PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getMechAuxilaryTechData()) {
				PANEL_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getColId(), bean.getColNm(), bean.getColValCd(), bean.getQuantity(),
						bean.getCost(), bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnFlg(),
						bean.getTechFlag(), bean.getComrFlag());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_MECH_AUX_ITEM(?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setString(2, String.join(",", mechAuxItemsList));
			callableStatement.setStructured(3, "dbo.PANEL_CONFIG", PANEL_CONFIG);
			callableStatement.setInt(4, DBOForm.getOverwrittenPriceFlag());
			callableStatement.setFloat(5, DBOForm.getOverwrittenPrice());
			callableStatement.setString(6, DBOForm.getTechComments());
			callableStatement.setString(7, DBOForm.getComrComments());
			callableStatement.setInt(8, DBOForm.getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetMechAux1 = callableStatement.getResultSet();
				while (resultSetMechAux1.next()) {

					DBOForm.setMechAuxTotalPrice(Math.round(resultSetMechAux1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveMechAuxList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetMechAux2 = callableStatement.getResultSet();
				while (resultSetMechAux2.next()) {
					DBOBean bean = new DBOBean();

					bean.setDetQuotId(resultSetMechAux2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetMechAux2.getInt("ITEM_ID"));
					bean.setItemName(resultSetMechAux2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetMechAux2.getInt("SUB_ITEM_ID"));
					bean.setColId(resultSetMechAux2.getInt("COL_ID"));
					bean.setColNm(resultSetMechAux2.getString("COL_NM"));
					bean.setColValCd(resultSetMechAux2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetMechAux2.getInt("LHS_FLG"));
					bean.setRhsCost(Math.round(resultSetMechAux2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetMechAux2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetMechAux2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetMechAux2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setBasicCost(Math.round(resultSetMechAux2.getFloat("BASIC_COST")));
					bean.setSubItemCost(Math.round(resultSetMechAux2.getFloat("SUB_ITEM_COST")));
					bean.setAddOnCost(Math.round(resultSetMechAux2.getFloat("ADD_ON_COST")));
					bean.setTotalPrice(Math.round(resultSetMechAux2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setOverwrittenPriceFlag(
							resultSetMechAux2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetMechAux2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetMechAux2.getInt("QTY"));
					bean.setTechRemarks(resultSetMechAux2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetMechAux2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetMechAux2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetMechAux2.getString("COMR_COMMENTS"));
					bean.setAddOnNewColFlag(resultSetMechAux2.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setSubItemTechRemarks(resultSetMechAux2.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetMechAux2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setOthersFlag(resultSetMechAux2.getInt("OTHERS_FLG") == 1 ? true : false);

					saveMechAuxList.add(bean);

				}
				if (!saveMechAuxList.isEmpty()) {
					DBOForm.setSaveMechAuxList(saveMechAuxList);
				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechAux1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechAux2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm saveMechAuxExtScope(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetMechExtScope1 = null;
		ResultSet resultSetMechExtScope2 = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable PANEL_CONFIG = new SQLServerDataTable();
			PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getMechExtScopeData()) {
				PANEL_CONFIG.addRow(

						bean.getQuotId(),
						bean.getItemId(), 
						bean.getItemName(), 
						bean.getSubItemId(),
						bean.getSubItemName(),
						bean.getColId(),
						bean.getColNm(), 
						bean.getColValCd(),
						bean.getQuantity(),
						bean.getCost(),
						bean.getTechRemarks(),
						bean.getComrRemarks(),
						bean.getAddOnFlg(),
						bean.getTechFlag(),
						bean.getComrFlag()
						
						
						
				);
				logger.info("START OF OBEJECT");
				logger.info(bean.getQuotId());
				logger.info(bean.getItemId()); 
				logger.info(bean.getItemName()); 
				logger.info(bean.getSubItemId());
				logger.info(bean.getSubItemName());
				logger.info(bean.getColId());
				logger.info(bean.getColNm()); 
				logger.info(bean.getColValCd());
				logger.info(bean.getQuantity());
				logger.info(bean.getCost());
				logger.info(bean.getTechRemarks());
				logger.info(bean.getComrRemarks());
				logger.info(bean.getAddOnFlg());
				logger.info(bean.getTechFlag());
				logger.info(bean.getComrFlag());
				logger.info("END OF OBEJECT");

				

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_MECH_EXT_SCOPE(?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID
			callableStatement.setStructured(2, "dbo.PANEL_CONFIG ", PANEL_CONFIG);// PANEL_CONFIG
			callableStatement.setString(3, DBOForm.getMechExtScopeTechComments());// TECH_COMMENTS
			callableStatement.setString(4, DBOForm.getMechExtScopeComrComments());// COMR_COMMENTS
			callableStatement.setInt(5, DBOForm.getModifiedById());// MOD_BY
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetMechExtScope1 = callableStatement.getResultSet();
				while (resultSetMechExtScope1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetMechExtScope1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveMechExtScopeList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetMechExtScope2 = callableStatement.getResultSet();
				while (resultSetMechExtScope2.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetMechExtScope2.getInt("ITEM_ID"));
					bean.setItemName(resultSetMechExtScope2.getString("ITEM_NM"));

					bean.setColId(resultSetMechExtScope2.getInt("COL_ID"));
					bean.setColNm(resultSetMechExtScope2.getString("COL_NM"));
					bean.setColValCd(resultSetMechExtScope2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetMechExtScope2.getInt("LHS_FLG"));

					bean.setExtScopeCost(Math.round(resultSetMechExtScope2.getFloat("EXT_SCOPE_COST")));
					bean.setMechTotalExtCost(Math.round(resultSetMechExtScope2.getFloat("MECH_TOTAL_EXT_COST")));
					bean.setQuantity(resultSetMechExtScope2.getInt("QTY"));
					bean.setTechRemarks(resultSetMechExtScope2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetMechExtScope2.getString("COMR_REMARKS"));
					bean.setMechTechComments(resultSetMechExtScope2.getString("MECH_TECH_COMMENTS"));
					bean.setMechComrComments(resultSetMechExtScope2.getString("MECH_COMR_COMMENTS"));

					saveMechExtScopeList.add(bean);

				}
				if (!saveMechExtScopeList.isEmpty()) {
					DBOForm.setSaveMechExtScopeList(saveMechExtScopeList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechExtScope1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMechExtScope2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm removeDboMechAuxItem(Integer QUOT_ID, Integer ITEM_ID, Integer SUB_ITEM_ID) {
		DBOForm dboForm = new DBOForm();
		try {

			String query = "DELETE FROM MECH_AUX_QUOT_TECH_ITEM_DET WHERE DET_QUOT_ID IN (  SELECT A.DET_QUOT_ID FROM MECH_AUX_QUOT_TECH_DET A,MECH_AUX_QUOT_TECH_MAST B WHERE B.QUOT_ID = ? AND A.MAST_QUOT_ID = B.MAST_QUOT_ID AND ITEM_ID = ? AND A.SUB_ITEM_ID = ? );";

			jdbcTemplate.update(query, QUOT_ID, ITEM_ID, SUB_ITEM_ID);

			String query1 = "DELETE FROM MECH_AUX_QUOT_TECH_DET WHERE DET_QUOT_ID IN (  SELECT A.DET_QUOT_ID FROM MECH_AUX_QUOT_TECH_DET A,MECH_AUX_QUOT_TECH_MAST B WHERE B.QUOT_ID = ? AND A.MAST_QUOT_ID = B.MAST_QUOT_ID AND ITEM_ID = ? AND A.SUB_ITEM_ID = ? )";

			jdbcTemplate.update(query1, QUOT_ID, ITEM_ID, SUB_ITEM_ID);

			dboForm.setSuccessCode(0);
			dboForm.setSuccessMsg("SUCCESS");
		} catch (Exception e) {
			dboForm.setSuccessCode(-1);
			dboForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return dboForm;
		}
		return dboForm;

	}

	@Override
	public DBOForm getEleFilter(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetEleFilter = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ELE_FILTER(?,?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());
			callableStatement.setString("TYPE_OF_PANEL", DBOForm.getTypeOfPanel());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> dboEleFilterList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetEleFilter = callableStatement.getResultSet();
				while (resultSetEleFilter.next()) {
					DBOBean bean = new DBOBean();

					bean.setEleFilterId(resultSetEleFilter.getInt("ELE_FILTER_ID"));
					bean.setEleType(resultSetEleFilter.getString("ELE_TYPE"));
					bean.setGenType(resultSetEleFilter.getString("GEN_TYPE"));
					bean.setFilterId(resultSetEleFilter.getInt("FILTER_ID"));
					bean.setFilterCd(resultSetEleFilter.getString("FILTER_CD"));
					bean.setItemName(resultSetEleFilter.getString("FILTER_NM"));
					bean.setItemId(resultSetEleFilter.getInt("ITEM_ID"));
					bean.setColValCd(resultSetEleFilter.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetEleFilter.getString("COL_VAL_NM"));
					bean.setSubColValNm(resultSetEleFilter.getString("SUB_COL_VAL_NM"));
					bean.setDefaultFlagNew(resultSetEleFilter.getInt("DEFLT_FLG"));
					bean.setMinVal(resultSetEleFilter.getFloat("MIN_VAL"));
					bean.setMaxVal(resultSetEleFilter.getFloat("MAX_VAL"));
					bean.setDispInd(resultSetEleFilter.getInt("DISP_IND"));
					logger.info("eletest");
					logger.info(resultSetEleFilter.getString("COL_VAL_CD"));
					logger.info(resultSetEleFilter.getString("COL_VAL_NM"));
					logger.info(resultSetEleFilter.getString("MIN_VAL"));
					logger.info(resultSetEleFilter.getString("MAX_VAL"));
					dboEleFilterList.add(bean);

				}

				if (!dboEleFilterList.isEmpty()) {
					DBOForm.setDboEleFilterList(dboEleFilterList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleFilter);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveEleFilter(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetSaveEleFilter = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable GEN_IN = new SQLServerDataTable();

			GEN_IN.addColumnMetadata("GEN_IN_ID", java.sql.Types.NUMERIC);
			GEN_IN.addColumnMetadata("CAT_VAL_CD", java.sql.Types.VARCHAR);
			GEN_IN.addColumnMetadata("QTY", java.sql.Types.NUMERIC);
			GEN_IN.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			GEN_IN.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			GEN_IN.addColumnMetadata("ERROR", java.sql.Types.VARCHAR);

			for (DBOBean bean : DBOForm.getSavedGeneralEleFliterInputList()) {// 6
				GEN_IN.addRow(

						bean.getGenInId(), bean.getCategoryValCode(), null, bean.getDefaultFlagNew(), bean.getDispInd(),
						bean.getErrorMsg()

				);
				logger.info("saveelefilter object input start");
				logger.info(bean.getGenInId());
				logger.info(bean.getCategoryValCode());
				logger.info(bean.getQuantity());
				logger.info(bean.getDefaultFlagNew());
				logger.info(bean.getDispInd());
				logger.info(bean.getErrorMsg());
				logger.info("saveelefilter object input");
				
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_ELE_FILTER(?,?,?)}");
			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID//750
			callableStatement.setStructured(2, "dbo.GEN_IN ", GEN_IN);// GEN_IN
			callableStatement.setInt(3, DBOForm.getModifiedById());// MOD_BY
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				logger.info("meghatest" + resultOutParameterInt);
				logger.info("testttt" + resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> saveEleFilterList = new ArrayList<>();
			if (resultOutParameterInt >= 0 && (callableStatement.getMoreResults())) {
				resultSetSaveEleFilter = callableStatement.getResultSet();
				while (resultSetSaveEleFilter.next()) {
					DBOBean bean = new DBOBean();

					bean.setQuotFilterId(resultSetSaveEleFilter.getInt("QUOT_FILTER_ID"));
					bean.setEleFilterId(resultSetSaveEleFilter.getInt("ELE_FILTER_ID"));
					bean.setEleType(resultSetSaveEleFilter.getString("ELE_TYPE"));
					bean.setGenType(resultSetSaveEleFilter.getString("GEN_TYPE"));
					bean.setFilterId(resultSetSaveEleFilter.getInt("FILTER_ID"));
					bean.setFilterCd(resultSetSaveEleFilter.getString("FILTER_CD"));
					bean.setItemName(resultSetSaveEleFilter.getString("FILTER_NM"));
					bean.setItemId(resultSetSaveEleFilter.getInt("ITEM_ID"));
					bean.setColValCd(resultSetSaveEleFilter.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetSaveEleFilter.getString("COL_VAL_NM"));
					bean.setSubColValNm(resultSetSaveEleFilter.getString("SUB_COL_VAL_NM"));
					bean.setDefaultFlag(resultSetSaveEleFilter.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setMinVal(resultSetSaveEleFilter.getFloat("MIN_VAL"));
					bean.setMaxVal(resultSetSaveEleFilter.getFloat("MAX_VAL"));
					bean.setDispInd(resultSetSaveEleFilter.getInt("DISP_IND"));
					bean.setAltError(resultSetSaveEleFilter.getString("ALT_ERROR"));
					saveEleFilterList.add(bean);

				}
				if (!saveEleFilterList.isEmpty()) {
					DBOForm.setSaveEleFilterList(saveEleFilterList);

				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSaveEleFilter);
		}
		return DBOForm;
	}

	@Override
	public DBOForm getEleItems(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetEleItems = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ELE_ITEMS(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				List<DBOBean> dboEleItemList = new ArrayList<DBOBean>();
				resultSetEleItems = callableStatement.getResultSet();

				while (resultSetEleItems.next()) {
					DBOBean DBOBean = new DBOBean();

					DBOBean.setEleItemId(resultSetEleItems.getInt("ELE_ITEM_ID"));
					DBOBean.setEleType(resultSetEleItems.getString("ELE_TYPE"));
					DBOBean.setItemId(resultSetEleItems.getInt("ITEM_ID"));
					DBOBean.setItemCd(resultSetEleItems.getString("ITEM_CD"));
					DBOBean.setItemName(resultSetEleItems.getString("ITEM_NAME"));
					DBOBean.setItemOrder(resultSetEleItems.getInt("ITEM_ORDER"));
					DBOBean.setItemApplInd(resultSetEleItems.getInt("ITEM_APPL_IND"));
					DBOBean.setSubItemId(resultSetEleItems.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemCd(resultSetEleItems.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetEleItems.getString("SUB_ITEM_NAME"));
					DBOBean.setSubItemFlag(resultSetEleItems.getInt("SUB_ITEM_FLG") == 1 ? true : false);
					DBOBean.setLinkFlag(resultSetEleItems.getInt("LINK_FLG") == 1 ? true : false);
					DBOBean.setDependFlag(resultSetEleItems.getInt("DEPEND_FLG") == 1 ? true : false);
					DBOBean.setF2fAddOnFlag(resultSetEleItems.getInt("F2F_ADDON_FLG") == 1 ? true : false);
					DBOBean.setTechFlag(resultSetEleItems.getInt("TECH_FLG"));
					DBOBean.setComrFlag(resultSetEleItems.getInt("COMR_FLG"));
					DBOBean.setEnabled(resultSetEleItems.getInt("IS_ENABLE") == 1 ? true : false);
					DBOBean.setHeaderText(resultSetEleItems.getString("HEADER_TEXT"));
					DBOBean.setFooterText(resultSetEleItems.getString("FOOTER_TEXT"));
					DBOBean.setTypeOfPanel(resultSetEleItems.getString("TYPE_OF_PANEL"));
					// DBOBean.setEleTcpFilterFlag(resultSetEleItems.getInt("ELE_TCP_FILTER_FLG")
					// == 1 ? true : false);

					dboEleItemList.add(DBOBean);

				}

				if (!dboEleItemList.isEmpty()) {
					DBOForm.setDboEleItemList(dboEleItemList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleItems);

		}
		return DBOForm;
	}

	public DBOForm getElePanels(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetElePanel1 = null;
		ResultSet resultSetElePanel2 = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {
			clearMessageFrom(DBOForm);
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ELE_PANEL(?,?,?) }");
			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());// 43//45
			callableStatement.setInt("SUB_ITEM_ID", DBOForm.getSubItemId());// 0//0
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());// 700//1280

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			List<DBOBean> dboElePanelList1 = new ArrayList<DBOBean>();

			if (callableStatement.getMoreResults()) {
				resultSetElePanel1 = callableStatement.getResultSet();

				while (resultSetElePanel1.next()) {

					DBOBean DBOBean = new DBOBean();
					DBOBean.setColId(resultSetElePanel1.getInt("COL_ID"));
					DBOBean.setItemId(resultSetElePanel1.getInt("ITEM_ID"));
					DBOBean.setSubItemId(resultSetElePanel1.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemCd(resultSetElePanel1.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetElePanel1.getString("SUB_ITEM_NAME"));
					DBOBean.setDesItemId(resultSetElePanel1.getInt("DES_ITEM_ID"));
					DBOBean.setDesItemName(resultSetElePanel1.getString("DES_ITEM_NAME"));
					DBOBean.setDesSubItemId(resultSetElePanel1.getInt("DES_SUB_ITEM_ID"));
					DBOBean.setDesSubItemName(resultSetElePanel1.getString("DES_SUB_ITEM_NAME"));
					DBOBean.setColCd(resultSetElePanel1.getString("COL_CD"));
					DBOBean.setColNm(resultSetElePanel1.getString("COL_NM"));
					DBOBean.setTechFlag(resultSetElePanel1.getInt("TECH_FLG"));
					DBOBean.setDispInd(resultSetElePanel1.getInt("DISP_IND"));
					DBOBean.setOrderId(resultSetElePanel1.getInt("ORDER_ID"));
					DBOBean.setAddOnNewColFlag(resultSetElePanel1.getInt("ADD_ON_NEW_COL_FLG"));
					DBOBean.setComrFlag(resultSetElePanel1.getInt("COMR_FLG"));
					DBOBean.setStdPriceFlag(resultSetElePanel1.getInt("STD_PRICE_FLG") == 1 ? true : false);
					DBOBean.setDesItemOrderId(resultSetElePanel1.getInt("DES_ITEM_ORDER_ID"));
					DBOBean.setDesSubItemOrderId(resultSetElePanel1.getInt("DES_SUB_ITEM_ORDER_ID"));
					DBOBean.setDesColOrderId(resultSetElePanel1.getString("DES_COL_ORDER_ID"));
					DBOBean.setColType(resultSetElePanel1.getString("COL_TYPE"));
					DBOBean.setGenInputLink(resultSetElePanel1.getInt("GEN_INPUT_LINK") == 1 ? true : false);

					dboElePanelList1.add(DBOBean);

				}

				if (!dboElePanelList1.isEmpty()) {
					DBOForm.setDboElePanelList1(dboElePanelList1);

				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetElePanel2 = callableStatement.getResultSet();
				List<DBOBean> dboElePanelList2 = new ArrayList<DBOBean>();

				while (resultSetElePanel2.next()) {
					DBOBean DBOBean = new DBOBean();

					DBOBean.setItemId(resultSetElePanel2.getInt("ITEM_ID"));
					DBOBean.setSubItemCode(resultSetElePanel2.getString("SUB_ITEM_CD"));
					DBOBean.setSubItemName(resultSetElePanel2.getString("SUB_ITEM_NAME"));
					DBOBean.setDesItemId(resultSetElePanel2.getInt("DES_ITEM_ID"));
					DBOBean.setDesItemName(resultSetElePanel2.getString("DES_ITEM_NAME"));
					DBOBean.setDesSubItemId(resultSetElePanel2.getInt("DES_SUB_ITEM_ID"));
					DBOBean.setDesSubItemName(resultSetElePanel2.getString("DES_SUB_ITEM_NAME"));
					DBOBean.setColId(resultSetElePanel2.getInt("COL_ID"));
					DBOBean.setColNm(resultSetElePanel2.getString("COL_NM"));
					DBOBean.setColValCd(resultSetElePanel2.getString("COL_VAL_CD"));
					DBOBean.setColValNm(resultSetElePanel2.getString("COL_VAL_NM"));
					DBOBean.setSubColValFlag(resultSetElePanel2.getInt("SUB_COL_VAL_FLG") == 1 ? true : false);
					DBOBean.setSubColValName(resultSetElePanel2.getString("SUB_COL_VAL_NM"));
					DBOBean.setMinVal(resultSetElePanel2.getFloat("MIN_VAL"));
					DBOBean.setMaxVal(resultSetElePanel2.getFloat("MAX_VAL"));
					DBOBean.setMake(resultSetElePanel2.getString("MAKE"));
					DBOBean.setDefaultFlag(resultSetElePanel2.getInt("DEFLT_FLG") == 1 ? true : false);
					DBOBean.setComrFlag(resultSetElePanel2.getInt("COMR_FLG"));
					DBOBean.setTechFlag(resultSetElePanel2.getInt("TECH_FLG"));
					DBOBean.setCalcLinkFlag(resultSetElePanel2.getInt("CALC_LINK_FLG") == 1 ? true : false);
					DBOBean.setAddOnFlg(resultSetElePanel2.getInt("ADD_ON_FLG"));
					DBOBean.setRhsDispInd(resultSetElePanel2.getInt("RHS_DISP_IND"));
					DBOBean.setDispInd(resultSetElePanel2.getInt("DISP_IND"));
					DBOBean.setOrderId(resultSetElePanel2.getInt("ORDER_ID"));
					DBOBean.setAddOnNewColFlag(resultSetElePanel2.getInt("ADD_ON_NEW_COL_FLG"));
					DBOBean.setInputCostFlag(resultSetElePanel2.getInt("INPUT_COST_FLG"));

					dboElePanelList2.add(DBOBean);
				}
				if (!dboElePanelList2.isEmpty()) {
					DBOForm.setDboElePanelList2(dboElePanelList2);
					createEleJson(dboElePanelList2, DBOForm, dboElePanelList1);
					createEleJson1(dboElePanelList2, DBOForm, dboElePanelList1);

				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetElePanel1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetElePanel2);

		}
		return DBOForm;
	}

	private void createEleJson(List<DBOBean> dataList, DBOForm DBOForm, List<DBOBean> ColumnList) {
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
				for (DBOBean column : ColumnList) {
					if (bean.getColId() == column.getColId()) {
						bean.setOrderId(column.getOrderId());
						bean.setColType(column.getColType());
						bean.setDesSubItemOrderId(column.getDesSubItemOrderId());
						bean.setDesItemOrderId(column.getDesItemOrderId());
						bean.setDesColOrderId(column.getDesColOrderId());

						break;
					}
				}
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					dropDownType.setOrderId(bean.getOrderId());
					dropDownType.setDesSubItemId(bean.getDesSubItemId());
					dropDownType.setDesSubItemName(bean.getDesSubItemName());
					dropDownType.setDesItemId(bean.getDesItemId());
					dropDownType.setDesItemName(bean.getDesItemName());
					dropDownType.setColType(bean.getColType());
					dropDownType.setDesSubItemOrderId(bean.getDesSubItemOrderId());
					dropDownType.setDesItemOrderId(bean.getDesItemOrderId());
					dropDownType.setDesColOrderId(bean.getDesColOrderId());
					dropDownType.setComrFlag(bean.getComrFlag());
					dropDownType.setTechFlag(bean.getTechFlag());

					SelectBox box = new SelectBox();
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());
					box.setDefaultFlag(bean.isDefaultFlag());
					box.setStdPriceFlag(bean.isStdPriceFlag());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setDirectPriceFlag(bean.isDirectPriceFlag());
					box.setDirectPrice(bean.getDirectPrice());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());
					box.setOrderId(bean.getOrderId());
					box.setTechFlag(bean.getTechFlag());
					box.setInputCostFlag(bean.getInputCostFlag());
					selectBoxList.add(box);
				}

				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	private void createEleJson1(List<DBOBean> dataList, DBOForm DBOForm, List<DBOBean> ColumnList) {
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

			DBOForm.getQuestionsBean1().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);

		}

		DBOForm.setQuestionsBean1(questionsBeanList);

		for (DBOBean dropDownType : DBOForm.getQuestionsBean1()) {
			List<SelectBox> selectBoxList = new ArrayList<>();

			for (DBOBean bean : dataList) {
				for (DBOBean column : ColumnList) {
					if (bean.getColId() == column.getColId()) {
						bean.setOrderId(column.getOrderId());
						bean.setColType(column.getColType());
						bean.setDesSubItemOrderId(column.getDesSubItemOrderId());
						bean.setDesColOrderId(column.getDesColOrderId());

						break;
					}
				}
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					dropDownType.setOrderId(bean.getOrderId());
					dropDownType.setDesSubItemId(bean.getDesSubItemId());
					dropDownType.setDesSubItemName(bean.getDesSubItemName());
					dropDownType.setDesItemId(bean.getDesItemId());
					dropDownType.setDesItemName(bean.getDesItemName());
					dropDownType.setColType(bean.getColType());
					dropDownType.setDesSubItemOrderId(bean.getDesSubItemOrderId());
					dropDownType.setDesColOrderId(bean.getDesColOrderId());
					dropDownType.setComrFlag(bean.getComrFlag());
					dropDownType.setTechFlag(bean.getTechFlag());

					SelectBox box = new SelectBox();
					box.setValue(bean.getColValNm());
					box.setCode(bean.getColValCd());
					box.setMaxVal(bean.getMaxVal());
					box.setMinVal(bean.getMinVal());
					box.setDefaultFlag(bean.isDefaultFlag());
					box.setStdPriceFlag(bean.isStdPriceFlag());
					box.setPercentage(bean.getPercentage());
					box.setPercentageFlag(bean.isPercentageFlag());
					box.setDirectPriceFlag(bean.isDirectPriceFlag());
					box.setDirectPrice(bean.getDirectPrice());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());
					box.setOrderId(bean.getOrderId());
					box.setTechFlag(bean.getTechFlag());
					box.setInputCostFlag(bean.getInputCostFlag());
					selectBoxList.add(box);
				}

				dropDownType.setDropDownValueList(selectBoxList);
			}
		}
	}

	@Override
	public DBOForm getEleTechPrice(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsgNew = null;
		ResultSet resultSetEleData1 = null;
		ResultSet resultSetEleData2 = null;
		ResultSet resultSetEleData3 = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable ELE_PANEL_CONFIG = new SQLServerDataTable();
			ELE_PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_ITEM_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_SUB_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_SUB_ITEM_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_COL_VAL_CD", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			ELE_PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			ELE_PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ORDER_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getEleTechData()) {
				ELE_PANEL_CONFIG.addRow(

						bean.getQuotId(), bean.getItemId(), bean.getSubItemId(), bean.getDesItemId(),
						bean.getDesItemName(), bean.getDesSubItemId(), bean.getDesSubItemName(), bean.getColId(),
						bean.getColNm(), bean.getColValCd(), bean.getSubColValCode(), bean.getQuantity(),
						bean.getCost(), bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnNewColFlag(),
						bean.getOrderId(), bean.getDefaultFlagNew(), bean.getDispInd(), bean.getTechFlag(),
						bean.getComrFlag());
				logger.info("object start");
				logger.info(bean.getQuotId());
				logger.info(bean.getItemId());
				logger.info(bean.getSubItemId());
				logger.info(bean.getDesItemId());
				logger.info(bean.getDesItemName());
				logger.info(bean.getDesSubItemId());
				logger.info(bean.getDesSubItemName());
				logger.info(bean.getColId());
				logger.info(bean.getColNm());
				logger.info(bean.getColValCd());
				logger.info(bean.getSubColValCode());
				logger.info(bean.getQuantity());
				logger.info(bean.getCost());
				logger.info(bean.getTechRemarks());
				logger.info(bean.getComrRemarks());
				logger.info(bean.getAddOnNewColFlag());
				logger.info(bean.getOrderId());
				logger.info(bean.getDefaultFlagNew());
				logger.info(bean.getDispInd());
				logger.info(bean.getTechFlag());
				logger.info(bean.getComrFlag());
				logger.info("object ends");

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_ELE_TECH(?,?,?,?,?,?)}");

			callableStatement.setStructured(1, "dbo.ELE_PANEL_CONFIG", ELE_PANEL_CONFIG);
			callableStatement.setFloat(2, DBOForm.getQuantity());
			logger.info("input start");
			logger.info(DBOForm.getQuantity());
			callableStatement.setString(3, DBOForm.getEleItemTechRemarks());
			logger.info(DBOForm.getEleItemTechRemarks());
			callableStatement.setString(4, DBOForm.getEleItemComrRemarks());
			logger.info(DBOForm.getEleItemComrRemarks());
			callableStatement.setInt(5, DBOForm.getModifiedById());
			callableStatement.setFloat(6, DBOForm.getDiscountPer());
			logger.info(DBOForm.getModifiedById());
			logger.info("input ends");
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);

			}

			if (callableStatement.getMoreResults()) {
				resultSetEleData1 = callableStatement.getResultSet();
				while (resultSetEleData1.next()) {

					DBOForm.setBasicCost(Math.round(resultSetEleData1.getFloat("COST")));

				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetEleData2 = callableStatement.getResultSet();
				while (resultSetEleData2.next()) {

					DBOForm.setEleAddOnCost(Math.round(resultSetEleData2.getFloat("ADD_ON_COST")));

				}
			}

			List<DBOBean> eleTechList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetEleData3 = callableStatement.getResultSet();
				while (resultSetEleData3.next()) {
					DBOBean bean = new DBOBean();
					bean.setEleItemId(resultSetEleData3.getInt("ELE_ITEM_ID"));
					bean.setDetQuotId(resultSetEleData3.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetEleData3.getInt("ITEM_ID"));
					bean.setItemName(resultSetEleData3.getString("ITEM_NM"));
					bean.setSubItemId(resultSetEleData3.getInt("SUB_ITEM_ID"));
					bean.setSubItemCode(resultSetEleData3.getString("SUB_ITEM_CD"));
					bean.setSubItemName(resultSetEleData3.getString("SUB_ITEM_NAME"));
					bean.setDesItemId(resultSetEleData3.getInt("DES_ITEM_ID"));
					bean.setDesItemName(resultSetEleData3.getString("DES_ITEM_NAME"));
					bean.setDesSubItemId(resultSetEleData3.getInt("DES_SUB_ITEM_ID"));
					bean.setDesSubItemName(resultSetEleData3.getString("DES_SUB_ITEM_NAME"));
					bean.setColId(resultSetEleData3.getInt("COL_ID"));
					bean.setColNm(resultSetEleData3.getString("COL_NM"));
					bean.setColValCd(resultSetEleData3.getString("COL_VAL_CD"));
					bean.setSubColValCode(resultSetEleData3.getString("SUB_COL_VAL_CD"));
					bean.setLhsFlag(resultSetEleData3.getInt("LHS_FLG"));
					bean.setRhsCost(Math.round(resultSetEleData3.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetEleData3.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetEleData3.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetEleData3.getString("RHS_COL_COMR_COMMENTS"));
					bean.setDefaultVal(resultSetEleData3.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setOrderId(resultSetEleData3.getInt("ORDER_ID"));
					bean.setDispInd(resultSetEleData3.getInt("DISP_IND"));
					bean.setBasicCost(Math.round(resultSetEleData3.getFloat("BASIC_COST")));
					bean.setAddOnCost(Math.round(resultSetEleData3.getFloat("ADD_ON_COST")));
					bean.setItemCost(Math.round(resultSetEleData3.getFloat("ITEM_COST")));
					bean.setAddOnFlg(resultSetEleData3.getInt("ADD_ON_FLG"));
					bean.setTotalPrice(Math.round(resultSetEleData3.getFloat(ItoConstants.TOTAL_COST)));
					bean.setTechFlag(resultSetEleData3.getInt("TECH_FLG"));
					bean.setComrFlag(resultSetEleData3.getInt("COMR_FLG"));
					bean.setOverwrittenPriceFlag(resultSetEleData3.getInt("ELE_COST_ME_FLG") == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetEleData3.getFloat("ELE_COST_ME")));
					bean.setQuantity(resultSetEleData3.getInt("QTY"));
					bean.setTechRemarks(resultSetEleData3.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetEleData3.getString("COMR_REMARKS"));
					bean.setAddOnNewColFlag(resultSetEleData3.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setErrorMsg(resultSetEleData3.getString("ERROR_MSG"));
					bean.setItemErrMessage(resultSetEleData3.getString("ITEM_ERROR_MSG"));
					bean.setApproxCostFlag(resultSetEleData3.getInt("APPROX_COST_FLG"));
					bean.setItemApproxCostFlag(resultSetEleData3.getInt("ITEM_APPROX_COST_FLG"));
					bean.setDesItemOrderId(resultSetEleData3.getInt("DES_ITEM_ORDER_ID"));
					bean.setDesSubItemOrderId(resultSetEleData3.getInt("DES_SUB_ITEM_ORDER_ID"));
					bean.setColType(resultSetEleData3.getString("COL_TYPE"));
					bean.setDesColOrderId(resultSetEleData3.getString("DES_COL_ORDER_ID"));
					bean.setEleType(resultSetEleData3.getString("ELE_TYPE"));
					bean.setInputCostFlag(resultSetEleData3.getInt("INPUT_COST_FLG"));
					bean.setTotalEleCost(Math.round(resultSetEleData3.getFloat("TOTAL_ELE_COST")));
					bean.setTotalEle(Math.round(resultSetEleData3.getFloat("TOTAL_ELE")));
					bean.setTotalCi(Math.round(resultSetEleData3.getFloat("TOTAL_CI")));
					bean.setNewDesItemFlag(resultSetEleData3.getInt("NEW_DES_ITEM_FLG"));
					bean.setNewDesSubItemFlag(resultSetEleData3.getInt("NEW_DES_SUB_ITEM_FLG"));
					bean.setDiscountPer(Math.round(resultSetEleData3.getFloat("DISCOUNT_PER")));
					bean.setNonDiscountCost(Math.round(resultSetEleData3.getFloat("NON_DISCOUNT_COST")));

					eleTechList.add(bean);

				}
				if (!eleTechList.isEmpty()) {
					DBOForm.setEleTechList(eleTechList);

				}

			}
		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleData1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleData2);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleData3);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getEleRefreshPanel(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsgNew = null;
		ResultSet resultSetEleRefreshData = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable ELE_PANEL_CONFIG = new SQLServerDataTable();
			ELE_PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_ITEM_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_SUB_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_SUB_ITEM_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_COL_VAL_CD", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			ELE_PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			ELE_PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ORDER_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getEleRefreshTechData()) {
				ELE_PANEL_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getSubItemId(), bean.getDesItemId(),
						bean.getDesItemName(), bean.getDesSubItemId(), bean.getDesSubItemName(), bean.getColId(),
						bean.getColNm(), bean.getColValCd(), bean.getSubColValCode(), bean.getQuantity(),
						bean.getCost(), bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnNewColFlag(),
						bean.getOrderId(), bean.getDefaultFlagNew(), bean.getDispInd(), bean.getTechFlag(),
						bean.getComrFlag());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_GET_ELE_REFRESH_PANEL(?,?)}");

			callableStatement.setStructured(1, "dbo.ELE_PANEL_CONFIG", ELE_PANEL_CONFIG);

			callableStatement.setInt(2, DBOForm.getModifiedById());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);

			}

			List<DBOBean> eleRefreshTechList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetEleRefreshData = callableStatement.getResultSet();
				while (resultSetEleRefreshData.next()) {
					DBOBean bean = new DBOBean();

					bean.setEleItemId(resultSetEleRefreshData.getInt("ELE_ITEM_ID"));
					bean.setDetQuotId(resultSetEleRefreshData.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetEleRefreshData.getInt("ITEM_ID"));
					bean.setItemName(resultSetEleRefreshData.getString("ITEM_NM"));
					bean.setSubItemId(resultSetEleRefreshData.getInt("SUB_ITEM_ID"));
					bean.setSubItemCode(resultSetEleRefreshData.getString("SUB_ITEM_CD"));
					bean.setSubItemName(resultSetEleRefreshData.getString("SUB_ITEM_NAME"));
					bean.setDesItemId(resultSetEleRefreshData.getInt("DES_ITEM_ID"));
					bean.setDesItemName(resultSetEleRefreshData.getString("DES_ITEM_NAME"));
					bean.setDesSubItemId(resultSetEleRefreshData.getInt("DES_SUB_ITEM_ID"));
					bean.setDesSubItemName(resultSetEleRefreshData.getString("DES_SUB_ITEM_NAME"));
					bean.setColId(resultSetEleRefreshData.getInt("COL_ID"));
					bean.setColNm(resultSetEleRefreshData.getString("COL_NM"));
					bean.setColValCd(resultSetEleRefreshData.getString("COL_VAL_CD"));
					bean.setSubColValCode(resultSetEleRefreshData.getString("SUB_COL_VAL_CD"));
					bean.setLhsFlag(resultSetEleRefreshData.getInt("LHS_FLG"));
					bean.setRhsCost(Math.round(resultSetEleRefreshData.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetEleRefreshData.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetEleRefreshData.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetEleRefreshData.getString("RHS_COL_COMR_COMMENTS"));
					bean.setDefaultVal(resultSetEleRefreshData.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setOrderId(resultSetEleRefreshData.getInt("ORDER_ID"));
					bean.setDispInd(resultSetEleRefreshData.getInt("DISP_IND"));
					bean.setBasicCost(Math.round(resultSetEleRefreshData.getFloat("BASIC_COST")));
					bean.setAddOnCost(Math.round(resultSetEleRefreshData.getFloat("ADD_ON_COST")));
					bean.setItemCost(Math.round(resultSetEleRefreshData.getFloat("ITEM_COST")));
					bean.setAddOnFlg(resultSetEleRefreshData.getInt("ADD_ON_FLG"));
					bean.setTotalPrice(Math.round(resultSetEleRefreshData.getFloat(ItoConstants.TOTAL_COST)));
					bean.setTechFlag(resultSetEleRefreshData.getInt("TECH_FLG"));
					bean.setComrFlag(resultSetEleRefreshData.getInt("COMR_FLG"));
					bean.setOverwrittenPriceFlag(resultSetEleRefreshData.getInt("ELE_COST_ME_FLG") == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetEleRefreshData.getFloat("ELE_COST_ME")));
					bean.setQuantity(resultSetEleRefreshData.getInt("QTY"));
					bean.setTechRemarks(resultSetEleRefreshData.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetEleRefreshData.getString("COMR_REMARKS"));
					bean.setAddOnNewColFlag(resultSetEleRefreshData.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setErrorMsg(resultSetEleRefreshData.getString("ERROR_MSG"));
					bean.setItemErrMessage(resultSetEleRefreshData.getString("ITEM_ERROR_MSG"));
					bean.setApproxCostFlag(resultSetEleRefreshData.getInt("APPROX_COST_FLG"));
					bean.setItemApproxCostFlag(resultSetEleRefreshData.getInt("ITEM_APPROX_COST_FLG"));
					bean.setDesItemOrderId(resultSetEleRefreshData.getInt("DES_ITEM_ORDER_ID"));
					bean.setDesSubItemOrderId(resultSetEleRefreshData.getInt("DES_SUB_ITEM_ORDER_ID"));
					bean.setColType(resultSetEleRefreshData.getString("COL_TYPE"));
					bean.setDesColOrderId(resultSetEleRefreshData.getString("DES_COL_ORDER_ID"));
					bean.setEleType(resultSetEleRefreshData.getString("ELE_TYPE"));
					bean.setInputCostFlag(resultSetEleRefreshData.getInt("INPUT_COST_FLG"));
					bean.setTotalEleCost(Math.round(resultSetEleRefreshData.getFloat("TOTAL_ELE_COST")));
					bean.setTotalEle(Math.round(resultSetEleRefreshData.getFloat("TOTAL_ELE")));
					bean.setTotalCi(Math.round(resultSetEleRefreshData.getFloat("TOTAL_CI")));
					bean.setNewDesItemFlag(resultSetEleRefreshData.getInt("NEW_DES_ITEM_FLG"));
					bean.setNewDesSubItemFlag(resultSetEleRefreshData.getInt("NEW_DES_SUB_ITEM_FLG"));
					bean.setDiscountPer(Math.round(resultSetEleRefreshData.getFloat("DISCOUNT_PER")));
					bean.setNonDiscountCost(Math.round(resultSetEleRefreshData.getFloat("NON_DISCOUNT_COST")));
					eleRefreshTechList.add(bean);

				}
				if (!eleRefreshTechList.isEmpty()) {
					DBOForm.setEleRefreshTechList(eleRefreshTechList);

				}

			}
		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleRefreshData);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveEleItem(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetEle1 = null;
		ResultSet resultSetEle2 = null;
		try {

			clearMessageFrom(DBOForm);
			List<String> eleItemsList = new ArrayList<>();
			for (Integer myInt : DBOForm.getEleItemIdList()) {
				eleItemsList.add(String.valueOf(myInt));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable ELE_PANEL_CONFIG = new SQLServerDataTable();
			ELE_PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_SUB_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_COL_VAL_CD", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			ELE_PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			ELE_PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ORDER_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getSavedEleDataList()) {
				ELE_PANEL_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getDesItemId(),
						bean.getDesSubItemId(),

						bean.getSubItemId(), bean.getSubItemName(), bean.getColId(), bean.getColNm(),
						bean.getColValCd(), bean.getSubColValCode(), bean.getQuantity(), bean.getCost(),
						bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnNewColFlag(), bean.getOrderId(),
						bean.getDefaultFlagNew(), bean.getDispInd(), bean.getTechFlag(), bean.getComrFlag());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_ELE_ITEM(?,?,?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setString(2, String.join(",", eleItemsList));
			callableStatement.setString(3, DBOForm.getScopeCd());
			callableStatement.setStructured(4, "dbo.ELE_PANEL_CONFIG ", ELE_PANEL_CONFIG);
			callableStatement.setInt(5, DBOForm.getOverwrittenPriceFlag());
			callableStatement.setFloat(6, DBOForm.getOverwrittenPrice());
			callableStatement.setString(7, DBOForm.getEleItemTechComments());
			callableStatement.setString(8, DBOForm.getEleItemComrComments());
			callableStatement.setInt(9, DBOForm.getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetEle1 = callableStatement.getResultSet();
				while (resultSetEle1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetEle1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveEleList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetEle2 = callableStatement.getResultSet();
				while (resultSetEle2.next()) {
					DBOBean bean = new DBOBean();

					bean.setDetQuotId(resultSetEle2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetEle2.getInt("ITEM_ID"));
					bean.setItemName(resultSetEle2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetEle2.getInt("SUB_ITEM_ID"));
					bean.setColId(resultSetEle2.getInt("COL_ID"));
					bean.setColNm(resultSetEle2.getString("COL_NM"));
					bean.setColValCd(resultSetEle2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetEle2.getInt("LHS_FLG"));
					bean.setRhsCost(Math.round(resultSetEle2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetEle2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetEle2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetEle2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setItemCost(Math.round(resultSetEle2.getFloat("ITEM_COST")));
					bean.setSubItemCost(Math.round(resultSetEle2.getFloat("SUB_ITEM_COST")));
					bean.setAddOnCost(Math.round(resultSetEle2.getFloat("ADD_ON_COST")));
					bean.setTotalPrice(Math.round(resultSetEle2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setOverwrittenPriceFlag(resultSetEle2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetEle2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetEle2.getInt("QTY"));
					bean.setTechRemarks(resultSetEle2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetEle2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetEle2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetEle2.getString("COMR_COMMENTS"));
					bean.setAddOnNewColFlag(resultSetEle2.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setSubItemTechRemarks(resultSetEle2.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetEle2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setOthersFlag(resultSetEle2.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setScopeCd(resultSetEle2.getString("SCOP_CD"));
					bean.setTotalEle(Math.round(resultSetEle2.getFloat("TOTAL_ELE")));
					bean.setTotalCi(Math.round(resultSetEle2.getFloat("TOTAL_CI")));
					saveEleList.add(bean);

				}
				if (!saveEleList.isEmpty()) {
					DBOForm.setSaveEleList(saveEleList);
				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEle1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEle2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm getEleSpecialFilter(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetEleSpecialFilter = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ELE_SPL_FILTER(?,?) }");
			callableStatement.setInt("ITEM_ID", DBOForm.getItemId());
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> dboEleSpecialFilterList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetEleSpecialFilter = callableStatement.getResultSet();
				while (resultSetEleSpecialFilter.next()) {
					DBOBean bean = new DBOBean();

					bean.setEleSpecialId(resultSetEleSpecialFilter.getInt("ELE_SPL_ID"));
					bean.setColNm(resultSetEleSpecialFilter.getString("COL_NM"));
					bean.setColValCd(resultSetEleSpecialFilter.getString("COL_VAL_CD"));
					bean.setDefaultFlag(resultSetEleSpecialFilter.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setItemApplInd(resultSetEleSpecialFilter.getInt("ITEM_APPL_IND"));
					// bean.setItemId(resultSetEleSpecialFilter.getInt("ITEM_ID"));
					bean.setNote(resultSetEleSpecialFilter.getString("NOTE"));

					dboEleSpecialFilterList.add(bean);

				}

				if (!dboEleSpecialFilterList.isEmpty()) {
					DBOForm.setDboEleSpecialFilterList(dboEleSpecialFilterList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleSpecialFilter);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveEleSpecialFilter(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetSaveEleSpecialFilter = null;

		try {
			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_ELE_SPL_FILTER(?,?,?,?,?,?)}");
			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setInt(2, DBOForm.getItemId());
			callableStatement.setInt(3, DBOForm.getEleSpecialId());
			callableStatement.setString(4, DBOForm.getColValCd());
			callableStatement.setInt(5, DBOForm.getItemApplInd());
			callableStatement.setInt(6, DBOForm.getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> saveEleSpecialFilterList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetSaveEleSpecialFilter = callableStatement.getResultSet();
				while (resultSetSaveEleSpecialFilter.next()) {
					DBOBean bean = new DBOBean();

					bean.setEleSpecialId(resultSetSaveEleSpecialFilter.getInt("ELE_SPL_ID"));
					bean.setColNm(resultSetSaveEleSpecialFilter.getString("COL_NM"));
					bean.setColValCd(resultSetSaveEleSpecialFilter.getString("COL_VAL_CD"));
					bean.setDefaultFlag(resultSetSaveEleSpecialFilter.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setItemApplInd(resultSetSaveEleSpecialFilter.getInt("ITEM_APPL_IND"));
					bean.setItemId(resultSetSaveEleSpecialFilter.getInt("ITEM_ID"));
					saveEleSpecialFilterList.add(bean);

				}
				if (!saveEleSpecialFilterList.isEmpty()) {
					DBOForm.setSaveEleSpecialFilterList(saveEleSpecialFilterList);

				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSaveEleSpecialFilter);
		}
		return DBOForm;
	}

	@Override
	public DBOForm saveCIExtScope(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetCIExtScope1 = null;
		ResultSet resultSetCIExtScope2 = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable PANEL_CONFIG = new SQLServerDataTable();
			PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getcIExtScopeData()) {
				PANEL_CONFIG.addRow(

						bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getColId(), bean.getColNm(), bean.getColValCd(), bean.getQuantity(),
						bean.getCost(), bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnFlg(),
						bean.getTechFlag(), bean.getComrFlag()

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_CI_EXT_SCOPE(?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID
			callableStatement.setStructured(2, "dbo.PANEL_CONFIG ", PANEL_CONFIG);// PANEL_CONFIG
			callableStatement.setString(3, DBOForm.getcIExtScopeTechComments());// TECH_COMMENTS
			callableStatement.setString(4, DBOForm.getcIExtScopeComrComments());// COMR_COMMENTS
			callableStatement.setInt(5, DBOForm.getModifiedById());// MOD_BY
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetCIExtScope1 = callableStatement.getResultSet();
				while (resultSetCIExtScope1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetCIExtScope1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveCIExtScopeList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetCIExtScope2 = callableStatement.getResultSet();
				while (resultSetCIExtScope2.next()) {
					DBOBean bean = new DBOBean();

					bean.setDetQuotId(resultSetCIExtScope2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetCIExtScope2.getInt("ITEM_ID"));
					bean.setItemName(resultSetCIExtScope2.getString("ITEM_NM"));
					bean.setColId(resultSetCIExtScope2.getInt("COL_ID"));
					bean.setColNm(resultSetCIExtScope2.getString("COL_NM"));
					bean.setColValCd(resultSetCIExtScope2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetCIExtScope2.getInt("LHS_FLG"));
					bean.setExtScopeCost(Math.round(resultSetCIExtScope2.getFloat("EXT_SCOPE_COST")));
					bean.setcITotalExtCost(Math.round(resultSetCIExtScope2.getFloat("CI_TOTAL_EXT_COST")));
					bean.setQuantity(resultSetCIExtScope2.getInt("QTY"));
					bean.setTechRemarks(resultSetCIExtScope2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetCIExtScope2.getString("COMR_REMARKS"));
					bean.setcITechComments(resultSetCIExtScope2.getString("CI_TECH_COMMENTS"));
					bean.setcIComrComments(resultSetCIExtScope2.getString("CI_COMR_COMMENTS"));

					saveCIExtScopeList.add(bean);

				}
				if (!saveCIExtScopeList.isEmpty()) {
					DBOForm.setSaveCIExtScopeList(saveCIExtScopeList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetCIExtScope1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetCIExtScope2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm saveEleExtScope(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetEleExtScope1 = null;
		ResultSet resultSetEleExtScope2 = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable PANEL_CONFIG = new SQLServerDataTable();
			PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			PANEL_CONFIG.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getEleExtScopeData()) {
				PANEL_CONFIG.addRow(

						bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemName(), bean.getColId(), bean.getColNm(), bean.getColValCd(), bean.getQuantity(),
						bean.getCost(), bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnFlg(),
						bean.getTechFlag(), bean.getComrFlag()

				);

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_ELE_EXT_SCOPE(?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID
			callableStatement.setStructured(2, "dbo.PANEL_CONFIG ", PANEL_CONFIG);// PANEL_CONFIG
			callableStatement.setString(3, DBOForm.getEleExtScopeTechComments());// TECH_COMMENTS
			callableStatement.setString(4, DBOForm.getEleExtScopeComrComments());// COMR_COMMENTS
			callableStatement.setInt(5, DBOForm.getModifiedById());// MOD_BY
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetEleExtScope1 = callableStatement.getResultSet();
				while (resultSetEleExtScope1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetEleExtScope1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveEleExtScopeList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetEleExtScope2 = callableStatement.getResultSet();
				while (resultSetEleExtScope2.next()) {
					DBOBean bean = new DBOBean();

					bean.setDetQuotId(resultSetEleExtScope2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetEleExtScope2.getInt("ITEM_ID"));
					bean.setItemName(resultSetEleExtScope2.getString("ITEM_NM"));
					bean.setColId(resultSetEleExtScope2.getInt("COL_ID"));
					bean.setColNm(resultSetEleExtScope2.getString("COL_NM"));
					bean.setColValCd(resultSetEleExtScope2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetEleExtScope2.getInt("LHS_FLG"));
					bean.setExtScopeCost(Math.round(resultSetEleExtScope2.getFloat("EXT_SCOPE_COST")));
					bean.setEleTotalExtCost(Math.round(resultSetEleExtScope2.getFloat("ELE_TOTAL_EXT_COST")));
					bean.setQuantity(resultSetEleExtScope2.getInt("QTY"));
					bean.setTechRemarks(resultSetEleExtScope2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetEleExtScope2.getString("COMR_REMARKS"));
					bean.setEleTechComments(resultSetEleExtScope2.getString("ELE_TECH_COMMENTS"));
					bean.setEleComrComments(resultSetEleExtScope2.getString("ELE_COMR_COMMENTS"));

					saveEleExtScopeList.add(bean);

				}
				if (!saveEleExtScopeList.isEmpty()) {
					DBOForm.setSaveEleExtScopeList(saveEleExtScopeList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleExtScope1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleExtScope2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm getEleInstr(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetInstrFilter = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ELE_INSTR(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> dboEleInstrList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetInstrFilter = callableStatement.getResultSet();
				while (resultSetInstrFilter.next()) {
					DBOBean bean = new DBOBean();

					bean.setId(resultSetInstrFilter.getInt("ID"));
					bean.setInstrCode(resultSetInstrFilter.getString("INSTR_CD"));
					bean.setInstrNm(resultSetInstrFilter.getString("INSTR_NM"));
					bean.setInstrTypeNm(resultSetInstrFilter.getString("INSTR_TYPE_NM"));
					bean.setInstrDesc(resultSetInstrFilter.getString("INSTR_DESC"));
					bean.setInstrMounting(resultSetInstrFilter.getString("INSTR_MOUNTING"));
					bean.setQuantityLogic(resultSetInstrFilter.getInt("QTY_LOGIC"));
					bean.setCost(resultSetInstrFilter.getFloat("COST"));
					bean.setNoOfTable(resultSetInstrFilter.getInt("NO_OF_TABLE"));
					bean.setHeaderText(resultSetInstrFilter.getString("HEADER_TEXT"));
					bean.setFooterText(resultSetInstrFilter.getString("FOOTER_TEXT"));
					bean.setItemId(resultSetInstrFilter.getInt("ITEM_ID"));
					bean.setAddInstrId(resultSetInstrFilter.getInt("ADD_INSTR_ID"));
					bean.setAddInstrCd(resultSetInstrFilter.getString("ADD_INSTR_CD"));
					bean.setAddInstrNm(resultSetInstrFilter.getString("ADD_INSTR_NM"));
					bean.setApproxFlag(resultSetInstrFilter.getInt("APPROX_FLG"));
					dboEleInstrList.add(bean);

				}

				if (!dboEleInstrList.isEmpty()) {
					DBOForm.setDboEleInstrList(dboEleInstrList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetInstrFilter);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveAdditionalInstrumentation(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSaveAddInstr = null;
		ResultSet resultSaveAddInstr1 = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable INSTR_CONFIG = new SQLServerDataTable();

			INSTR_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			INSTR_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			INSTR_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			INSTR_CONFIG.addColumnMetadata("LHS_COL_NM", java.sql.Types.VARCHAR);
			INSTR_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			INSTR_CONFIG.addColumnMetadata("SUB_COL_VAL_CD", java.sql.Types.VARCHAR);
			INSTR_CONFIG.addColumnMetadata("QTY", java.sql.Types.NUMERIC);
			INSTR_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			INSTR_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);

			INSTR_CONFIG.addColumnMetadata("OTHER_ITEM_INSTR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getSavedAddInstrList()) {
				INSTR_CONFIG.addRow(

						bean.getItemId(), bean.getItemNm(), bean.getColId(), bean.getLhsColNm(), bean.getColValCd(),
						bean.getSubColValCode(), bean.getQuantity(), bean.getCost(), bean.getAddOnNewColFlag(),
						bean.getOtherItemInstrFlag()

				);
				logger.info("instr object start");
				logger.info(bean.getItemId());
				logger.info(bean.getItemNm());
				logger.info(bean.getColId());
				logger.info(bean.getLhsColNm());
				logger.info(bean.getColValCd());
				logger.info(bean.getSubColValCode());
				logger.info(bean.getQuantity());
				logger.info(bean.getCost());
				logger.info(bean.getAddOnNewColFlag());
				logger.info(bean.getOtherItemInstrFlag());
				logger.info("instr object ends");

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_ELE_INSTR(?,?,?)}");
			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID
			callableStatement.setStructured(2, "dbo.INSTR_CONFIG ", INSTR_CONFIG);// INSTR_CONFIG
			callableStatement.setInt(3, DBOForm.getModifiedById());// MOD_BY
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSaveAddInstr1 = callableStatement.getResultSet();
				while (resultSaveAddInstr1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSaveAddInstr1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveAddInstrList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSaveAddInstr = callableStatement.getResultSet();
				while (resultSaveAddInstr.next()) {
					DBOBean bean = new DBOBean();

					bean.setDetQuotId(resultSaveAddInstr.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSaveAddInstr.getInt("ITEM_ID"));
					bean.setItemNm(resultSaveAddInstr.getString("ITEM_NM"));
					bean.setQuantity(resultSaveAddInstr.getInt("QTY"));
					bean.setColId(resultSaveAddInstr.getInt("COL_ID"));
					bean.setColNm(resultSaveAddInstr.getString("COL_NM"));
					bean.setColValCd(resultSaveAddInstr.getString("COL_VAL_CD"));
					bean.setSubColValCode(resultSaveAddInstr.getString("SUB_COL_VAL_CD"));
					bean.setAddOnNewColFlag(resultSaveAddInstr.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setRhsFlag(resultSaveAddInstr.getInt("RHS_FLG") == 1 ? true : false);
					bean.setRhsColQuantity(resultSaveAddInstr.getFloat("RHS_COL_QTY"));
					bean.setDesSubItemOrderId(resultSaveAddInstr.getInt("DES_SUB_ITEM_ORDER_ID"));
					bean.setRhsCost(Math.round(resultSaveAddInstr.getFloat("RHS_COST")));
					bean.setAddInstrCost(Math.round(resultSaveAddInstr.getFloat("ADD_INSTR_COST")));
					bean.setTotalAddInstrCost(Math.round(resultSaveAddInstr.getFloat("TOTAL_ADD_INSTR_COST")));
					bean.setItemFlag(resultSaveAddInstr.getInt("ITEM_FLG") == 1 ? true : false);
					bean.setOthersFlag(resultSaveAddInstr.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setAddInstrFlag(resultSaveAddInstr.getInt("ADD_INSTR_FLG") == 1 ? true : false);
					bean.setBasicCost(Math.round(resultSaveAddInstr.getFloat("BASIC_COST")));
					bean.setAddOnCost(Math.round(resultSaveAddInstr.getFloat("ADD_ON_COST")));

					saveAddInstrList.add(bean);
				}
				if (!saveAddInstrList.isEmpty()) {
					DBOForm.setSaveAddInstrList(saveAddInstrList);

				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveEleSubItem(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetEleSub1 = null;
		ResultSet resultSetEleSub2 = null;
		try {

			clearMessageFrom(DBOForm);
			List<String> eleSubItemsList = new ArrayList<>();
			for (Integer myInt : DBOForm.getEleSubItemIdList()) {
				eleSubItemsList.add(String.valueOf(myInt));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable ELE_PANEL_CONFIG = new SQLServerDataTable();
			ELE_PANEL_CONFIG.addColumnMetadata("QUOT_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DES_SUB_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_ITEM_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_NM", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("SUB_COL_VAL_CD", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			ELE_PANEL_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			ELE_PANEL_CONFIG.addColumnMetadata("TECH_COMMENTS", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("COMR_COMMENTS", java.sql.Types.VARCHAR);
			ELE_PANEL_CONFIG.addColumnMetadata("ADD_ON_NEW_COL_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("ORDER_ID", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			ELE_PANEL_CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getSavedEleSubDataList()) {
				ELE_PANEL_CONFIG.addRow(bean.getQuotId(), bean.getItemId(), bean.getItemName(), bean.getDesItemId(),
						bean.getDesSubItemId(),

						bean.getSubItemId(), bean.getSubItemName(), bean.getColId(), bean.getColNm(),
						bean.getColValCd(), bean.getSubColValCode(), bean.getQuantity(), bean.getCost(),
						bean.getTechRemarks(), bean.getComrRemarks(), bean.getAddOnNewColFlag(), bean.getOrderId(),
						bean.getDefaultFlagNew(), bean.getDispInd(), bean.getTechFlag(), bean.getComrFlag());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_ELE_SUB_ITEM(?,?,?,?,?)}");

//			PROCEDURE  [dbo].[PROC_SAVE_ELE_SUB_ITEM]
//					(@QUOT_ID      INT,
//					 @ITEM_ID      INT,
//					 @SUB_ITEM_ID_LIST VARCHAR(500),
//					 @ELE_PANEL_CONFIG AS  ELE_PANEL_CONFIG  READONLY,
//					 @MOD_BY      INT
//					)
			
			callableStatement.setInt(1, DBOForm.getQuotId());
			callableStatement.setInt(2, DBOForm.getItemId());
			callableStatement.setString(3, String.join(",", eleSubItemsList));
			callableStatement.setStructured(4, "dbo.ELE_PANEL_CONFIG ", ELE_PANEL_CONFIG);
//			callableStatement.setInt(5, DBOForm.getOverwrittenPriceFlag());
//			callableStatement.setFloat(6, DBOForm.getOverwrittenPrice());
//			callableStatement.setString(7, DBOForm.getEleItemTechComments());
//			callableStatement.setString(8, DBOForm.getEleItemComrComments());
			callableStatement.setInt(5, DBOForm.getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetEleSub1 = callableStatement.getResultSet();
				while (resultSetEleSub1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetEleSub1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveEleSubList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetEleSub2 = callableStatement.getResultSet();
				while (resultSetEleSub2.next()) {
					DBOBean bean = new DBOBean();

					bean.setDetQuotId(resultSetEleSub2.getInt("DET_QUOT_ID"));
					bean.setItemId(resultSetEleSub2.getInt("ITEM_ID"));
					bean.setItemName(resultSetEleSub2.getString("ITEM_NM"));
					bean.setSubItemId(resultSetEleSub2.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetEleSub2.getString("SUB_ITEM_NM"));
					bean.setColId(resultSetEleSub2.getInt("COL_ID"));
					bean.setColNm(resultSetEleSub2.getString("COL_NM"));
					bean.setColValCd(resultSetEleSub2.getString("COL_VAL_CD"));
					bean.setLhsFlag(resultSetEleSub2.getInt("LHS_FLG"));
					bean.setRhsCost(Math.round(resultSetEleSub2.getFloat("RHS_COST")));
					bean.setRhsColQuantity(resultSetEleSub2.getFloat("RHS_COL_QTY"));
					bean.setRhsColTechcomments(resultSetEleSub2.getString("RHS_COL_TECH_COMMENTS"));
					bean.setRhsColComrcomments(resultSetEleSub2.getString("RHS_COL_COMR_COMMENTS"));
					bean.setItemCost(Math.round(resultSetEleSub2.getFloat("ITEM_COST")));
					bean.setSubItemCost(Math.round(resultSetEleSub2.getFloat("SUB_ITEM_COST")));
					bean.setAddOnCost(Math.round(resultSetEleSub2.getFloat("ADD_ON_COST")));
					bean.setTotalPrice(Math.round(resultSetEleSub2.getFloat(ItoConstants.TOTAL_COST)));
					bean.setOverwrittenPriceFlag(resultSetEleSub2.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					bean.setOverwrittenPrice(Math.round(resultSetEleSub2.getFloat(ItoConstants.COST_ME)));
					bean.setQuantity(resultSetEleSub2.getInt("QTY"));
					bean.setTechRemarks(resultSetEleSub2.getString("TECH_REMARKS"));
					bean.setComrRemarks(resultSetEleSub2.getString("COMR_REMARKS"));
					bean.setTechComments(resultSetEleSub2.getString("TECH_COMMENTS"));
					bean.setComrComments(resultSetEleSub2.getString("COMR_COMMENTS"));
					bean.setAddOnNewColFlag(resultSetEleSub2.getInt("ADD_ON_NEW_COL_FLG"));
					bean.setSubItemTechRemarks(resultSetEleSub2.getString("SUB_ITEM_TECH_COMMENTS"));
					bean.setSubItemComrRemarks(resultSetEleSub2.getString("SUB_ITEM_COMR_COMMENTS"));
					bean.setOthersFlag(resultSetEleSub2.getInt("OTHERS_FLG") == 1 ? true : false);
					bean.setScopeCd(resultSetEleSub2.getString("SCOP_CD"));
					saveEleSubList.add(bean);

				}
				if (!saveEleSubList.isEmpty()) {
					DBOForm.setSaveEleSubList(saveEleSubList);
				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleSub1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleSub2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm getUpdateCreateF2fColVal(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_F2F_COL_VAL_UPD = new SQLServerDataTable();

			TYP_F2F_COL_VAL_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("COL_VAL_ID", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("CATEGORY", java.sql.Types.VARCHAR);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("SUB_ITEM_TYPE_ID", java.sql.Types.NUMERIC);
			// TYP_F2F_COL_VAL_UPD.addColumnMetadata("MAKE",
			// java.sql.Types.VARCHAR);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("COL_VAL_NM", java.sql.Types.VARCHAR);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("CALC_LINK_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("ORDER_ID", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("ADDON_COST_PER", java.sql.Types.REAL);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("ADDON_DIR_COST", java.sql.Types.REAL);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_COL_VAL_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdatePriceF2fColVal()) {

				TYP_F2F_COL_VAL_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getColValId(),
						bean.getCategory(), bean.getItemId(), bean.getSubItemId(), bean.getSubItemTypeId(),
						// bean.getMake(),
						bean.getColId(), bean.getColValCd(), bean.getColValNm(), bean.getCalculateLinkFlag(),
						bean.getDefaultFlagNew(), bean.getDispInd(), bean.getOrderId(), bean.getAddOnFlg(),
						bean.getAddOnCostPer(), bean.getAddOnDirCost(), bean.getApproxCostFlag(), bean.getTechFlag(),
						bean.getComrFlag(), bean.getActiveNew())

				;
				logger.info(bean.getUpdateCode());

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_F2F_COL_VAL (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_F2F_COL_VAL_UPD ", TYP_F2F_COL_VAL_UPD);
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
	public DBOForm getUpdateCreateF2fPrice(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_F2F_PRICE_UPD = new SQLServerDataTable();
			TYP_F2F_PRICE_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_F2F_PRICE_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("SUB_ITEM_TYP_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("FRM_POW_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("BLEED_TYP_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("PRICE", java.sql.Types.REAL);
			//TYP_F2F_PRICE_UPD.addColumnMetadata("TOTAL_PRICE", java.sql.Types.REAL);
			TYP_F2F_PRICE_UPD.addColumnMetadata("SUB_CONTR_COST", java.sql.Types.REAL);
			TYP_F2F_PRICE_UPD.addColumnMetadata("SHOP_CONV_COST", java.sql.Types.REAL);
			//TYP_F2F_PRICE_UPD.addColumnMetadata("TOTAL_FTF_COST", java.sql.Types.REAL);
			//TYP_F2F_PRICE_UPD.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);
			
			for (DBOBean bean : dboForm.getUpdateF2fPrice()) {

				TYP_F2F_PRICE_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getPriceId(),

						bean.getItemId(), bean.getSubItemId(), bean.getSubItemTypeId(), bean.getFramePowerId(),
						bean.getBleedTypeId(), bean.getPrice(), null , null , bean.getApproxCostFlag(),
						bean.getActiveNew())

				;
				logger.info(bean.getUpdateCode());
				logger.info(bean.getPriceId());
				logger.info(bean.getItemId());
				logger.info(bean.getSubItemId());
				logger.info(bean.getSubItemTypeId());
				logger.info(bean.getFramePowerId());
				logger.info(bean.getBleedTypeId());
				//logger.info(bean.getCondTypeId());
				//logger.info(bean.getCustType());
				//logger.info(bean.getPriceCode());
				logger.info(bean.getPrice());
				//logger.info(bean.getTotalPrice());
				//logger.info(bean.getDefaultFlagNew());
				logger.info(bean.getApproxCostFlag());
				logger.info(bean.getActiveNew());

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_F2F_PRICE (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_F2F_PRICE_UPD ", TYP_F2F_PRICE_UPD);
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
	public DBOForm createUpdateF2fShopConv(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_F2F_PRICE_UPD = new SQLServerDataTable();
			TYP_F2F_PRICE_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_F2F_PRICE_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("SUB_ITEM_TYP_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("FRM_POW_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("BLEED_TYP_ID", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("PRICE", java.sql.Types.REAL);
			//TYP_F2F_PRICE_UPD.addColumnMetadata("TOTAL_PRICE", java.sql.Types.REAL);
			TYP_F2F_PRICE_UPD.addColumnMetadata("SUB_CONTR_COST", java.sql.Types.REAL);
			TYP_F2F_PRICE_UPD.addColumnMetadata("SHOP_CONV_COST", java.sql.Types.REAL);
			//TYP_F2F_PRICE_UPD.addColumnMetadata("TOTAL_FTF_COST", java.sql.Types.REAL);
			//TYP_F2F_PRICE_UPD.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_F2F_PRICE_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);
			
			for (DBOBean bean : dboForm.getUpdateF2fShopConv()) {

				TYP_F2F_PRICE_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getF2fMastId(),

						null , null , null, bean.getFramePowerId(),
						bean.getBleedTypeId(), bean.getPrice(), bean.getSubContrCost() , bean.getShopConvCost() , null,
						bean.getActiveNew());
				logger.info("createUpdateF2fShopConv object");
				logger.info(dboForm.getSaveBasicDetails().getUpdateRequestNumber());
				logger.info(bean.getUpdateCode());
				logger.info(bean.getF2fMastId());
				logger.info(bean.getFramePowerId());
				logger.info(bean.getBleedTypeId());
				logger.info(bean.getPrice());
				logger.info(bean.getSubContrCost());
				logger.info(bean.getShopConvCost());
				logger.info(bean.getActiveNew());
				
		
				code = bean.getUpdateCode();
			}
			logger.info("code"+code);
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_F2F_SHOP_CONV (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_F2F_PRICE_UPD ", TYP_F2F_PRICE_UPD);
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
				logger.info("createUpdateF2fShopConv result1");
				logger.info(resultSetMsg.getInt(1));
				logger.info(resultSetMsg.getString(2));
			}

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetReqNo = callableStatement.getResultSet();
					while (resultSetReqNo.next()) {
						dboForm.getSaveBasicDetails()
								.setUpdateRequestNumber(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
						logger.info("createUpdateF2fShopConv result2");
						logger.info(resultSetReqNo.getInt("UPDATE_REQUEST_NO"));
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
	public DBOForm getUpdateCreateMechAuxFrameSpecData(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_F2F_FRM_SPEC_DATA_UPD = new SQLServerDataTable();

			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("FRM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("SUB_ITEM_TYPE_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("MIN_VAL", java.sql.Types.REAL);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("MAX_VAL", java.sql.Types.REAL);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("COST", java.sql.Types.REAL);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdateF2fFrameSpecData()) {

				TYP_F2F_FRM_SPEC_DATA_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getId(),
						bean.getFrameId(), bean.getItemId(), bean.getSubItemId(), bean.getSubItemTypeId(),
						bean.getColId(), bean.getColValCd(), bean.getMinVal(), bean.getMaxVal(), null, bean.getActiveNew())

				;

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_MECH_AUX_FRM_SPEC_DATA (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_F2F_FRM_SPEC_DATA_UPD ", TYP_F2F_FRM_SPEC_DATA_UPD);
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
	public DBOForm getUpdateCreateF2fFrameSpecData(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_F2F_FRM_SPEC_DATA_UPD = new SQLServerDataTable();

			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("FRM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("SUB_ITEM_TYP_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("MIN_VAL", java.sql.Types.REAL);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("MAX_VAL", java.sql.Types.REAL);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("COST", java.sql.Types.REAL);
			TYP_F2F_FRM_SPEC_DATA_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdateF2fFrameSpecData()) {

				TYP_F2F_FRM_SPEC_DATA_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getId(),
						bean.getFrameId(), bean.getItemId(), bean.getSubItemId(), bean.getSubItemTypeId(),
						bean.getColId(), bean.getColValCd(), bean.getMinVal(), bean.getMaxVal(), bean.getCost(), bean.getActiveNew())

				;

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_F2F_FRM_SPEC_DATA (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_F2F_FRM_SPEC_DATA_UPD ", TYP_F2F_FRM_SPEC_DATA_UPD);
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
	public DBOForm getUpdateCreateMechPrice(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_MECH_PRICE_UPD = new SQLServerDataTable();

			TYP_MECH_PRICE_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_MECH_PRICE_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_MECH_PRICE_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);

			TYP_MECH_PRICE_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_MECH_PRICE_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);

			TYP_MECH_PRICE_UPD.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			TYP_MECH_PRICE_UPD.addColumnMetadata("PRICE_CODE", java.sql.Types.VARCHAR);
			TYP_MECH_PRICE_UPD.addColumnMetadata("PRICE", java.sql.Types.REAL);
			TYP_MECH_PRICE_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_PRICE_UPD.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_PRICE_UPD.addColumnMetadata("TOTAL_PRICE", java.sql.Types.REAL);
			TYP_MECH_PRICE_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdateMechPrice()) {

				TYP_MECH_PRICE_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getPriceId(),

						bean.getItemId(), bean.getSubItemId(), bean.getTurbCode(), bean.getPriceCode(), bean.getPrice(),
						bean.getApproxCostFlag(),

						bean.getDefaultFlagNew(), bean.getTotalPrice(), bean.getActiveNew())

				;
				logger.info(bean.getUpdateCode());

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_MECH_PRICE (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_MECH_PRICE_UPD ", TYP_MECH_PRICE_UPD);
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
	public DBOForm getUpdateCreateMechAddOnCost(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_MECH_ADDON_COST_UPD = new SQLServerDataTable();

			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);

			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("ADDON_COST_COL", java.sql.Types.VARCHAR);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("ADDON_COST_PER", java.sql.Types.REAL);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("ADDON_DIR_COST", java.sql.Types.REAL);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_ADDON_COST_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdateMechAddOnCost()) {

				TYP_MECH_ADDON_COST_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getPriceId(),

						bean.getItemId(), bean.getSubItemId(), bean.getColId(), bean.getColValCd(),
						bean.getAddOnCostCol(), bean.getAddOnCostPer(), bean.getAddOnDirCost(),
						bean.getApproxCostFlag(),

						bean.getActiveNew())

				;
				logger.info(bean.getUpdateCode());

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_MECH_ADDON_COST (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_MECH_ADDON_COST_UPD ", TYP_MECH_ADDON_COST_UPD);
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
	public DBOForm getUpdateCreateMechAuxColVal(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_MECH_AUX_COL_VAL_UPD = new SQLServerDataTable();

			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("COL_VAL_ID", java.sql.Types.NUMERIC);

			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("COL_ID", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("COL_VAL_NM", java.sql.Types.VARCHAR);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("DISP_IND", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("ORDER_ID", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("ADD_ON_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("ADDON_COST_PER", java.sql.Types.REAL);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("ADDON_DIR_COST", java.sql.Types.REAL);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_COL_VAL_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdateMechAuxColVal()) {

				TYP_MECH_AUX_COL_VAL_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getColValId(),

						bean.getItemId(),

						bean.getColId(), bean.getColValCd(), bean.getColValNm(), bean.getDefaultFlagNew(),
						bean.getDispInd(), bean.getOrderId(), bean.getComrFlag(), bean.getTechFlag(),
						bean.getAddOnFlg(), bean.getAddOnCostPer(), bean.getAddOnDirCost(),

						bean.getApproxCostFlag(),

						bean.getActiveNew())

				;
				logger.info(bean.getUpdateCode());

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_MECH_AUX_COL_VAL (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_MECH_AUX_COL_VAL_UPD ", TYP_MECH_AUX_COL_VAL_UPD);
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
	public DBOForm getUpdateCreateMechOverTank(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_MECH_OVER_TANK_UPD = new SQLServerDataTable();

			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("ID", java.sql.Types.NUMERIC);

			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("POWER", java.sql.Types.VARCHAR);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("MIN_VAL", java.sql.Types.REAL);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("MAX_VAL", java.sql.Types.REAL);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("QTY", java.sql.Types.REAL);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("PRICE", java.sql.Types.REAL);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_OVER_TANK_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdateMechOverTank()) {

				TYP_MECH_OVER_TANK_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getId(),

						bean.getItemId(),

						bean.getPower(), bean.getMinVal(), bean.getMaxVal(), bean.getQty(), bean.getPrice(),
						bean.getDefaultFlagNew(), bean.getApproxCostFlag(),

						bean.getActiveNew())

				;
				logger.info(bean.getUpdateCode());

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_MECH_OVER_TANK (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_MECH_OVER_TANK_UPD ", TYP_MECH_OVER_TANK_UPD);
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
	public DBOForm getAvr(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetAvr = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_AVR(?,?,?,?) }");
			callableStatement.setString("TYPE_OF_QUOTATION", DBOForm.getTypeOfQuotation());
			callableStatement.setString("TYPE_OF_PANEL", DBOForm.getTypeOfPanel());
			callableStatement.setString("MAKE", DBOForm.getMake());
			callableStatement.setString("DUTY_SYNC", DBOForm.getDutySync());
			logger.info("avr inputs");
			logger.info(DBOForm.getTypeOfQuotation());
			logger.info(DBOForm.getTypeOfPanel());
			logger.info(DBOForm.getMake());
			logger.info(DBOForm.getDutySync());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> dboAvrList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetAvr = callableStatement.getResultSet();
				while (resultSetAvr.next()) {
					DBOBean bean = new DBOBean();

					bean.setEleFilterId(resultSetAvr.getInt("ELE_FILTER_ID"));
					bean.setEleType(resultSetAvr.getString("ELE_TYPE"));
					bean.setGenType(resultSetAvr.getString("GEN_TYPE"));
					bean.setFilterId(resultSetAvr.getInt("FILTER_ID"));
					bean.setFilterCd(resultSetAvr.getString("FILTER_CD"));
					bean.setItemName(resultSetAvr.getString("FILTER_NM"));
					bean.setItemId(resultSetAvr.getInt("ITEM_ID"));
					bean.setColValCd(resultSetAvr.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetAvr.getString("COL_VAL_NM"));
					bean.setSubColValNm(resultSetAvr.getString("SUB_COL_VAL_NM"));

					bean.setDefaultFlagNew(resultSetAvr.getInt("DEFLT_FLG"));
					bean.setMinVal(resultSetAvr.getFloat("MIN_VAL"));
					bean.setMaxVal(resultSetAvr.getFloat("MAX_VAL"));
					bean.setDispInd(resultSetAvr.getInt("DISP_IND"));

					dboAvrList.add(bean);
					logger.info("output");
					logger.info(dboAvrList);

				}

				if (!dboAvrList.isEmpty()) {
					DBOForm.setDboAvrList(dboAvrList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAvr);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getGoverner(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetGoverner = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_GOVERNER(?,?,?,?) }");

			callableStatement.setString("DUTY_SYNC", DBOForm.getDutySync());
			callableStatement.setString("TYPE_OF_TURBINE_START", DBOForm.getTypeOfTurbineStart());
			callableStatement.setString("TYPE_OF_PANEL", DBOForm.getTypeOfPanel());
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> dboGovernerList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetGoverner = callableStatement.getResultSet();
				while (resultSetGoverner.next()) {
					DBOBean bean = new DBOBean();

					bean.setEleFilterId(resultSetGoverner.getInt("ELE_FILTER_ID"));
					bean.setEleType(resultSetGoverner.getString("ELE_TYPE"));
					bean.setGenType(resultSetGoverner.getString("GEN_TYPE"));
					bean.setFilterId(resultSetGoverner.getInt("FILTER_ID"));
					bean.setFilterCd(resultSetGoverner.getString("FILTER_CD"));
					bean.setItemName(resultSetGoverner.getString("FILTER_NM"));
					bean.setItemId(resultSetGoverner.getInt("ITEM_ID"));
					bean.setColValCd(resultSetGoverner.getString("COL_VAL_CD"));
					bean.setColValNm(resultSetGoverner.getString("COL_VAL_NM"));
					bean.setSubColValNm(resultSetGoverner.getString("SUB_COL_VAL_NM"));

					bean.setDefaultFlagNew(resultSetGoverner.getInt("DEFLT_FLG"));
					bean.setMinVal(resultSetGoverner.getFloat("MIN_VAL"));
					bean.setMaxVal(resultSetGoverner.getFloat("MAX_VAL"));
					bean.setDispInd(resultSetGoverner.getInt("DISP_IND"));

					dboGovernerList.add(bean);
					logger.info("output");
					logger.info(dboGovernerList);

				}

				if (!dboGovernerList.isEmpty()) {
					DBOForm.setDboGovernerList(dboGovernerList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetGoverner);

		}
		return DBOForm;
	}

	@Override
	public DBOForm getEleVms(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetEleVms = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ELE_VMS(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> dboEleVmsList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetEleVms = callableStatement.getResultSet();
				while (resultSetEleVms.next()) {
					DBOBean bean = new DBOBean();

					bean.setId(resultSetEleVms.getInt("ID"));
					bean.setType(resultSetEleVms.getString("TYPE"));
					bean.setAddPrbFlag(resultSetEleVms.getInt("ADD_PRB_FLG"));
					bean.setOrderBy(resultSetEleVms.getInt("ORDER_BY"));
					bean.setTagNum(resultSetEleVms.getString("TAG_NO"));

					bean.setEquipment(resultSetEleVms.getString("EQUIPMENT"));
					bean.setApplication(resultSetEleVms.getString("APPLICATION"));
					bean.setLocation(resultSetEleVms.getString("LOCATION"));

					bean.setQuantity(resultSetEleVms.getInt("QTY"));

					bean.setHeaderText(resultSetEleVms.getString("HEADER_TEXT"));
					bean.setCost(resultSetEleVms.getFloat("COST"));
					bean.setNote(resultSetEleVms.getString("NOTE"));

					dboEleVmsList.add(bean);

				}

				if (!dboEleVmsList.isEmpty()) {
					DBOForm.setDboEleVmsList(dboEleVmsList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleVms);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveEleVms(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetEleVms1 = null;
		ResultSet resultSetEleVms2 = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable VMS_CONFIG = new SQLServerDataTable();

			VMS_CONFIG.addColumnMetadata("TYPE", java.sql.Types.VARCHAR);
			VMS_CONFIG.addColumnMetadata("ADD_PRB_FLG", java.sql.Types.NUMERIC);
			VMS_CONFIG.addColumnMetadata("ORDER_BY", java.sql.Types.NUMERIC);
			VMS_CONFIG.addColumnMetadata("TAG_NO", java.sql.Types.VARCHAR);
			VMS_CONFIG.addColumnMetadata("EQUIPMENT", java.sql.Types.VARCHAR);
			VMS_CONFIG.addColumnMetadata("APPLICATION", java.sql.Types.VARCHAR);
			VMS_CONFIG.addColumnMetadata("LOCATION", java.sql.Types.VARCHAR);
			VMS_CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			VMS_CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			VMS_CONFIG.addColumnMetadata("NEW_COL_VAL_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getEleVmsData()) {
				VMS_CONFIG.addRow(

						bean.getType(), bean.getAddPrbFlag(), bean.getOrderBy(), bean.getTagNum(), bean.getEquipment(),
						bean.getApplication(), bean.getLocation(), bean.getQuantity(), bean.getCost(),
						bean.getNewColValFlag());

				logger.info("vms object start");
				logger.info(bean.getType());
				logger.info(bean.getAddPrbFlag());
				logger.info(bean.getOrderBy());
				logger.info(bean.getTagNum());
				logger.info(bean.getEquipment());
				logger.info(bean.getApplication());
				logger.info(bean.getLocation());
				logger.info(bean.getQuantity());
				logger.info(bean.getCost());
				logger.info(bean.getNewColValFlag());
				logger.info("vms object end");

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_ELE_VMS(?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID
			callableStatement.setInt(2, DBOForm.getItemId());
			callableStatement.setStructured(3, "dbo.VMS_CONFIG ", VMS_CONFIG);// VMS_CONFIG

			callableStatement.setInt(4, DBOForm.getModifiedById());// MOD_BY
			logger.info("save vms input");
			logger.info(DBOForm.getQuotId());
			logger.info(DBOForm.getItemId());
			logger.info(DBOForm.getModifiedById());
			logger.info("save vms input ends");
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				resultSetEleVms1 = callableStatement.getResultSet();
				while (resultSetEleVms1.next()) {

					DBOForm.setTotalPrice(Math.round(resultSetEleVms1.getFloat(ItoConstants.TOTAL_COST)));

				}
			}

			List<DBOBean> saveEleVmsList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetEleVms2 = callableStatement.getResultSet();
				while (resultSetEleVms2.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetEleVms2.getInt("ITEM_ID"));
					bean.setItemName(resultSetEleVms2.getString("ITEM_NM"));
					bean.setType(resultSetEleVms2.getString("TYPE"));
					bean.setAddPrbFlag(resultSetEleVms2.getInt("ADD_PRB_FLG"));
					bean.setOrderBy(resultSetEleVms2.getInt("ORDER_BY"));
					bean.setTagNum(resultSetEleVms2.getString("TAG_NO"));
					bean.setEquipment(resultSetEleVms2.getString("EQUIPMENT"));
					bean.setApplication(resultSetEleVms2.getString("APPLICATION"));
					bean.setLocation(resultSetEleVms2.getString("LOCATION"));
					bean.setQuantity(resultSetEleVms2.getInt("QTY"));
					bean.setCost(Math.round(resultSetEleVms2.getFloat("COST")));
					bean.setNewColValFlag(resultSetEleVms2.getInt("NEW_COL_VAL_FLG"));
					bean.setItemCost(Math.round(resultSetEleVms2.getFloat("ITEM_COST")));
					bean.setHeaderText(resultSetEleVms2.getString("HEADER_TEXT"));

					bean.setNote(resultSetEleVms2.getString("NOTE"));
					bean.setBasicCost(Math.round(resultSetEleVms2.getFloat("BASIC_COST")));
					bean.setAddOnCost(Math.round(resultSetEleVms2.getFloat("ADD_ON_COST")));
					saveEleVmsList.add(bean);

				}
				if (!saveEleVmsList.isEmpty()) {
					DBOForm.setSaveEleVmsList(saveEleVmsList);

				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleVms1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEleVms2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm savePerformance(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetPerform1 = null;
		ResultSet resultSetPerform3 = null;
		ResultSet resultSetPerform2 = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable PERF_COND = new SQLServerDataTable();

			PERF_COND.addColumnMetadata("PARAM_ID", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("PARAM_NM", java.sql.Types.VARCHAR);
			PERF_COND.addColumnMetadata("UNIT_ID", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("UNIT_NM", java.sql.Types.VARCHAR);
			PERF_COND.addColumnMetadata("GUARNTEED", java.sql.Types.VARCHAR);
			PERF_COND.addColumnMetadata("COND1", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND2", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND3", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND4", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND5", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND6", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND7", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND8", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND9", java.sql.Types.NUMERIC);
			PERF_COND.addColumnMetadata("COND10", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getPerformanceData()) {
				PERF_COND.addRow(

						bean.getParamId(), bean.getParamNm(), bean.getUnitId(), bean.getUnitNm(), bean.getGuaranteed(),
						bean.getCond1(), bean.getCond2(), bean.getCond3(), bean.getCond4(), bean.getCond5(),
						bean.getCond6(), bean.getCond7(), bean.getCond8(), bean.getCond9(), bean.getCond10()

				);

			}

			SQLServerDataTable PERF_DETAIL = new SQLServerDataTable();
			PERF_DETAIL.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			PERF_DETAIL.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			PERF_DETAIL.addColumnMetadata("CONSUMER_ID", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("STARTUP", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("CONTINUOUS", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("COL_VAL_CD", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			PERF_DETAIL.addColumnMetadata("ITEM_TYPE", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("ITEM_CD", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("SPEED", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("VOLTAGE", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("FEEDER", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("TECH_REMARKS", java.sql.Types.VARCHAR);
			PERF_DETAIL.addColumnMetadata("NEW_COL_VAL_FLG", java.sql.Types.NUMERIC);

			for (DBOBean bean : DBOForm.getPerformanceData1()) {
				PERF_DETAIL.addRow(bean.getItemId(), bean.getSubItemId(), bean.getConsumerId1(), bean.getStartUp(),
						bean.getContinuous(), bean.getColValCd(), bean.getEditFlag(), bean.getItemType(),
						bean.getItemCd(), bean.getSpeed(), bean.getVoltage(), bean.getFeeder(), bean.getTechRemarks(),
						bean.getNewColValFlag()

				);
				logger.info(bean.getItemId());
				logger.info(bean.getItemType());
				logger.info(bean.getItemCd());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_PERFORMANCE(?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID
			callableStatement.setInt(2, DBOForm.getHmbdTableInput());
			callableStatement.setInt(3, DBOForm.getConditionTableInput());
			// if (DBOForm.getHmbdImage() != null) {
			// BASE64Decoder decoder = new BASE64Decoder();
			// byte[] imageByte = decoder.decodeBuffer(DBOForm.getHmbdImage());
			// callableStatement.setBytes(4, imageByte);
			// } else {
			// callableStatement.setBinaryStream(4, null);
			// }
			callableStatement.setInt(4, DBOForm.getNoOfConditions());

			callableStatement.setStructured(5, "dbo.PERF_COND ", PERF_COND);// PERF_CONDT

			callableStatement.setStructured(6, "dbo.PERF_DETAIL ", PERF_DETAIL);// PERF_DETAIL

			callableStatement.setInt(7, DBOForm.getModifiedById());// MOD_BY

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> savePerformanceList3 = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetPerform3 = callableStatement.getResultSet();
				while (resultSetPerform3.next()) {
					DBOBean bean = new DBOBean();

					bean.setHmbdTableInput(resultSetPerform3.getInt("HMBD_TABLE_INPUT"));
					bean.setConditionTableInput(resultSetPerform3.getInt("CONDTION_TABLE_INPUT"));

					bean.setNoOfConditions(resultSetPerform3.getInt("NO_OF_CONDITIONS"));

					savePerformanceList3.add(bean);

				}
				if (!savePerformanceList3.isEmpty()) {
					DBOForm.setSavePerformanceList3(savePerformanceList3);
				}
			}

			List<DBOBean> savePerformanceList1 = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetPerform1 = callableStatement.getResultSet();
				while (resultSetPerform1.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetPerform1.getInt("ITEM_ID"));
					bean.setItemNm(resultSetPerform1.getString("ITEM_NM"));
					bean.setSubItemId(resultSetPerform1.getInt("SUB_ITEM_ID"));
					bean.setSubItemNm(resultSetPerform1.getString("SUB_ITEM_NM"));
					bean.setMastQuotId(resultSetPerform1.getInt("MAST_QUOT_ID"));
					bean.setConsumerId1(resultSetPerform1.getString("CONSUMER_ID"));
					bean.setStartUp(resultSetPerform1.getString("STARTUP"));
					bean.setContinuous(resultSetPerform1.getString("CONTINUOUS"));
					bean.setColValCd(resultSetPerform1.getString("COL_VAL_CD"));
					bean.setEditFlag(resultSetPerform1.getInt("EDIT_FLG"));
					bean.setItemType(resultSetPerform1.getString("ITEM_TYPE"));
					bean.setItemCd(resultSetPerform1.getString("ITEM_CD"));
					bean.setSpeed(resultSetPerform1.getString("SPEED"));
					bean.setVoltage(resultSetPerform1.getString("VOLTAGE"));
					bean.setFeeder(resultSetPerform1.getString("FEEDER"));
					bean.setTechRemarks(resultSetPerform1.getString("TECH_REMARKS"));
					bean.setNewColValFlag(resultSetPerform1.getInt("NEW_COL_VAL_FLG"));
					bean.setNote(resultSetPerform1.getString("NOTE"));
					savePerformanceList1.add(bean);

				}
				if (!savePerformanceList1.isEmpty()) {
					DBOForm.setSavePerformanceList1(savePerformanceList1);
				}
			}

			List<DBOBean> savePerformanceList2 = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetPerform2 = callableStatement.getResultSet();
				while (resultSetPerform2.next()) {
					DBOBean bean = new DBOBean();

					bean.setParamId(resultSetPerform2.getInt("PARAM_ID"));
					bean.setParamNm(resultSetPerform2.getString("PARAM_NM"));
					bean.setUnitId(resultSetPerform2.getInt("UNIT_ID"));
					bean.setUnitNm(resultSetPerform2.getString("UNIT_NM"));
					bean.setGuaranteed(resultSetPerform2.getString("GUARNTEED"));
					bean.setCond1(resultSetPerform2.getInt("COND1"));
					bean.setCond2(resultSetPerform2.getInt("COND2"));
					bean.setCond3(resultSetPerform2.getInt("COND3"));
					bean.setCond4(resultSetPerform2.getInt("COND4"));
					bean.setCond5(resultSetPerform2.getInt("COND5"));
					bean.setCond6(resultSetPerform2.getInt("COND6"));
					bean.setCond7(resultSetPerform2.getInt("COND7"));
					bean.setCond8(resultSetPerform2.getInt("COND8"));
					bean.setCond9(resultSetPerform2.getInt("COND9"));
					bean.setCond10(resultSetPerform2.getInt("COND10"));

					savePerformanceList2.add(bean);

				}
				if (!savePerformanceList2.isEmpty()) {
					DBOForm.setSavePerformanceList2(savePerformanceList2);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetPerform1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetPerform2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm getPerformance(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetperformance = null;
		ResultSet resultSetHmbdData = null;
		ResultSet resultSetAuxData = null;
		ResultSet resultSetAuxSteamData = null;
		ResultSet resultSetPerformancenew = null;

		ResultSet resultSetPerData1 = null;
		ResultSet resultSetPerData7 = null;
		ResultSet resultSetPerData8 = null;
		ResultSet resultSetPerformance1 = null;
		ResultSet resultSetFreshData = null;
		ResultSet resultSetPerformance2 = null;
		ResultSet resultSetLubeOilData = null;

		ResultSet resultSetControlData = null;
		ResultSet resultSetScfmData = null;
		ResultSet resultSetAcData = null;
		ResultSet resultSetPerformance3 = null;
		ResultSet resultSetUnitData = null;
		ResultSet resultSetPerformance10 = null;

		ResultSet resultSetPerformance4 = null;

		ResultSet resultSetPerformanc5 = null;

		ResultSet resultSetPerformance6 = null;
		ResultSet resultSetPurityData = null;
		ResultSet resultSetFixedData = null;
		ResultSet resultSetIdentData = null;
		ResultSet resultSetItemData = null;
		ResultSet resultSetItem1Data = null;
		ResultSet resultSetItemPurityData = null;
		ResultSet resultSetCirData = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_PERFORMANCE(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> dboPerformanceList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetperformance = callableStatement.getResultSet();
				while (resultSetperformance.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetperformance.getInt("ITEM_ID"));
					bean.setItemNm(resultSetperformance.getString("ITEM_NM"));
					bean.setSubItemId(resultSetperformance.getInt("SUB_ITEM_ID"));
					bean.setSubItemNm(resultSetperformance.getString("SUB_ITEM_NM"));
					bean.setConsumerId1(resultSetperformance.getString("CONSUMER_ID"));
					bean.setStartUp(resultSetperformance.getString("STARTUP"));
					bean.setContinuous(resultSetperformance.getString("CONTINUOUS"));
					bean.setColValCd(resultSetperformance.getString("COL_VAL_CD"));
					bean.setEditFlag(resultSetperformance.getInt("EDIT_FLG"));
					bean.setItemType(resultSetperformance.getString("ITEM_TYPE"));
					bean.setItemCd(resultSetperformance.getString("ITEM_CD"));
					bean.setSpeed(resultSetperformance.getString("SPEED"));
					bean.setVoltage(resultSetperformance.getString("VOLTAGE"));
					bean.setFeeder(resultSetperformance.getString("FEEDER"));
					bean.setNote(resultSetperformance.getString("NOTE"));

					dboPerformanceList.add(bean);

				}

				if (!dboPerformanceList.isEmpty()) {
					DBOForm.setDboPerformanceList(dboPerformanceList);
				}
			}

			List<DBOBean> fixedList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetFixedData = callableStatement.getResultSet();
				while (resultSetFixedData.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetFixedData.getInt("ITEM_ID"));
					bean.setItemNm(resultSetFixedData.getString("ITEM_NM"));
					bean.setFixedText1(resultSetFixedData.getString("FIXED_TEXT1"));
					bean.setFixedText2(resultSetFixedData.getString("FIXED_TEXT2"));
					bean.setFixedText3(resultSetFixedData.getString("FIXED_TEXT3"));

					fixedList.add(bean);

				}

				if (!fixedList.isEmpty()) {
					DBOForm.setFixedList(fixedList);
				}
			}

			List<DBOBean> identList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetIdentData = callableStatement.getResultSet();
				while (resultSetIdentData.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetIdentData.getInt("ITEM_ID"));
					bean.setItemNm(resultSetIdentData.getString("ITEM_NM"));
					bean.setIdentifier(resultSetIdentData.getString("IDENTIFIER"));
					bean.setUnits(resultSetIdentData.getString("UNIT"));
					bean.setRecmd(resultSetIdentData.getString("RECMD"));
					bean.setLimit(resultSetIdentData.getString("LIMIT"));

					identList.add(bean);

				}

				if (!identList.isEmpty()) {
					DBOForm.setIdentList(identList);
				}
			}

			List<DBOBean> itemList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetItem1Data = callableStatement.getResultSet();
				while (resultSetItem1Data.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetItem1Data.getInt("ITEM_ID"));
					bean.setItemNm(resultSetItem1Data.getString("ITEM_NM"));
					bean.setColNm(resultSetItem1Data.getString("COL_NM"));
					bean.setConductivity(resultSetItem1Data.getString("CONDUCTIVITY"));
					bean.setContinuous(resultSetItem1Data.getString("CONTINUOUS"));
					bean.setStartUp(resultSetItem1Data.getString("START_UP"));

					itemList.add(bean);

				}

				if (!itemList.isEmpty()) {
					DBOForm.setItemList(itemList);
				}
			}

			List<DBOBean> cirList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetCirData = callableStatement.getResultSet();
				while (resultSetCirData.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetCirData.getInt("ITEM_ID"));
					bean.setItemNm(resultSetCirData.getString("ITEM_NM"));
					bean.setParameter(resultSetCirData.getString("PARAMETERS"));
					bean.setType(resultSetCirData.getString("TYPE"));
					bean.setUnits(resultSetCirData.getString("UNITS"));
					bean.setCirWater(resultSetCirData.getString("CIR_WATER"));

					cirList.add(bean);

				}

				if (!cirList.isEmpty()) {
					DBOForm.setCirList(cirList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetperformance);

		}

		return DBOForm;
	}

	@Override
	public DBOForm getPerformanceParam(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetperformance = null;

		ResultSet resultSetUnitData = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_PERF_PARAMETER(?,?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());
			callableStatement.setInt("NO_OF_CONDITION", DBOForm.getNoOfCondition());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> unitList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetUnitData = callableStatement.getResultSet();
				while (resultSetUnitData.next()) {
					DBOBean bean = new DBOBean();

					bean.setUnitItemId(resultSetUnitData.getInt("UN_ITEM_ID"));
					bean.setParamCd(resultSetUnitData.getString("PARAM_CD"));
					bean.setParamId(resultSetUnitData.getInt("PARAM_ID"));
					bean.setParamNm(resultSetUnitData.getString("PARAM_NM"));
					bean.setUnitItemCd(resultSetUnitData.getString("UN_ITEM_CD"));
					bean.setUnitItemNm(resultSetUnitData.getString("UN_ITEM_NM"));

					unitList.add(bean);

				}

				if (!unitList.isEmpty()) {
					DBOForm.setUnitList(unitList);
				}
			}

			List<DBOBean> dboPerformanceList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetperformance = callableStatement.getResultSet();
				while (resultSetperformance.next()) {
					DBOBean bean = new DBOBean();

					bean.setId(resultSetperformance.getInt("ID"));
					bean.setNoOfCondition(resultSetperformance.getInt("NO_OF_CONDITION"));
					bean.setNoOfBleed(resultSetperformance.getInt("NO_OF_BLEED"));
					bean.setNoOfExt(resultSetperformance.getInt("NO_OF_EXT"));
					bean.setParamId(resultSetperformance.getInt("PARAM_ID"));
					bean.setParamNm(resultSetperformance.getString("PARAM_NM"));
					bean.setUnitId(resultSetperformance.getInt("UNIT_ID"));
					bean.setUnitNm(resultSetperformance.getString("UNIT_NM"));
					bean.setGuaranteed(resultSetperformance.getString("GUARNTEED"));
					bean.setCond1(resultSetperformance.getInt("COND1"));
					bean.setCond2(resultSetperformance.getInt("COND2"));
					bean.setCond3(resultSetperformance.getInt("COND3"));
					bean.setCond4(resultSetperformance.getInt("COND4"));
					bean.setCond5(resultSetperformance.getInt("COND5"));
					bean.setCond6(resultSetperformance.getInt("COND6"));
					bean.setCond7(resultSetperformance.getInt("COND7"));
					bean.setCond8(resultSetperformance.getInt("COND8"));
					bean.setCond9(resultSetperformance.getInt("COND9"));
					bean.setCond10(resultSetperformance.getInt("COND10"));
					bean.setNote(resultSetperformance.getString("NOTE"));

					dboPerformanceList.add(bean);

				}

				if (!dboPerformanceList.isEmpty()) {
					DBOForm.setDboPerformanceList(dboPerformanceList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetperformance);

		}

		return DBOForm;
	}

	@Override
	public DBOForm getOtherChapter(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetOtherData = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_OTHER_CHAPTER(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				List<DBOBean> otherDataList = new ArrayList<DBOBean>();
				resultSetOtherData = callableStatement.getResultSet();
				while (resultSetOtherData.next()) {
					DBOBean bean = new DBOBean();

					bean.setGroupCode(resultSetOtherData.getString("GRP_CD"));
					bean.setCategoryNm(resultSetOtherData.getString("CAT_NM"));
					bean.setSubCategoryNm(resultSetOtherData.getString("SUB_CAT_NM"));
					bean.setSeqNo(resultSetOtherData.getInt("SEQ_NO"));
					bean.setSsId(resultSetOtherData.getInt("SS_ID"));
					bean.setScopeCd(resultSetOtherData.getString("SCOPE_CD"));
					bean.setScopeNm(resultSetOtherData.getString("SCOPE_NM"));

					bean.setItemId(resultSetOtherData.getInt("ITEM_ID"));
					bean.setItemNm(resultSetOtherData.getString("ITEM_NM"));
					bean.setSubItemId(resultSetOtherData.getInt("SUB_ITEM_ID"));
					bean.setSubItemNm(resultSetOtherData.getString("SUB_ITEM_NM"));
					bean.setInformation(resultSetOtherData.getString("INFORMATION"));
					bean.setFinalts(resultSetOtherData.getString("FINAL"));
					bean.setSubScopeCd(resultSetOtherData.getString("SUB_SCOPE_CD"));
					bean.setDescription(resultSetOtherData.getString("DESCRIPTION"));
					bean.setEquipment(resultSetOtherData.getString("EQUIPMENT"));
					bean.setTest(resultSetOtherData.getString("TEST"));
					bean.setEquivalent(resultSetOtherData.getString("EQUIVALENT"));
					bean.setPanelType(resultSetOtherData.getString("PANEL_TYPE"));
					bean.setCustType(resultSetOtherData.getString("CUST_TYPE"));
					bean.setQuant(resultSetOtherData.getString("QTY"));
					bean.setCost(resultSetOtherData.getFloat("COST"));
					bean.setEditFlag(resultSetOtherData.getInt("EDIT_FLG"));
					bean.setNewColValFlag(resultSetOtherData.getInt("NEW_COL_VAL_FLG"));
					bean.setNote(resultSetOtherData.getString("NOTE"));

					otherDataList.add(bean);

				}

				if (!otherDataList.isEmpty()) {
					DBOForm.setOtherDataList(otherDataList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetOtherData);

		}

		return DBOForm;
	}

	@Override
	public DBOForm saveOtherChapter(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;

		ResultSet resultSetOtherChapterData = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable OTHER_CHPT = new SQLServerDataTable();

			OTHER_CHPT.addColumnMetadata("SEQ_NO", java.sql.Types.NUMERIC);
			OTHER_CHPT.addColumnMetadata("SS_ID", java.sql.Types.NUMERIC);
			OTHER_CHPT.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			OTHER_CHPT.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			OTHER_CHPT.addColumnMetadata("INFORMATION", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("FINAL", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("SUB_SCOPE_CD", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("DESCRIPTION", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("EQUIPMENT", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("TEST", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("EQUIVALENT", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("PANEL_TYPE", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("CUST_TYPE", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("QTY", java.sql.Types.VARCHAR);
			OTHER_CHPT.addColumnMetadata("COST", java.sql.Types.NUMERIC);
			OTHER_CHPT.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			OTHER_CHPT.addColumnMetadata("NEW_COL_VAL_FLG", java.sql.Types.NUMERIC);
			OTHER_CHPT.addColumnMetadata("REMARKS", java.sql.Types.VARCHAR);

			for (DBOBean bean : DBOForm.getOtherChapterData()) {
				OTHER_CHPT.addRow(bean.getSeqNo(), bean.getSsId(), bean.getItemId(), bean.getSubItemId(),
						bean.getInformation(), bean.getFinalts(), bean.getSubItemCd(), bean.getDescription(),
						bean.getEquipment(), bean.getTest(), bean.getEquivalent(), bean.getPanelType(),
						bean.getCustType(), bean.getQuant(), bean.getCost(), bean.getEditFlag(),
						bean.getNewColValFlag(), bean.getRemarks());
				logger.info("printing Information");
				logger.info(bean.getSeqNo()+ bean.getSsId()+ bean.getItemId()+ bean.getSubItemId()+
						bean.getInformation()+ bean.getFinalts()+ bean.getSubItemCd()+ bean.getDescription()+
						bean.getEquipment()+ bean.getTest()+ bean.getEquivalent()+ bean.getPanelType()+
						bean.getCustType()+ bean.getQuant()+ bean.getCost()+ bean.getEditFlag()+
						bean.getNewColValFlag()+ bean.getRemarks());
				logger.info(bean.getInformation());
				logger.info(bean.getDescription());
				logger.info(bean.getFinalts());
				

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_OTHER_CHPT(?,?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());// QUOT_ID

			callableStatement.setStructured(2, "dbo.OTHER_CHPT ", OTHER_CHPT);// OTHER_CHPT

			callableStatement.setInt(3, DBOForm.getSsId());// MOD_BY
			callableStatement.setInt(4, DBOForm.getModifiedById());// MOD_BY

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> saveOtherCapterList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetOtherChapterData = callableStatement.getResultSet();
				while (resultSetOtherChapterData.next()) {
					DBOBean bean = new DBOBean();

					bean.setSeqNo(resultSetOtherChapterData.getInt("SEQ_NO"));
					bean.setSsId(resultSetOtherChapterData.getInt("SS_ID"));
					bean.setScopeCd(resultSetOtherChapterData.getString("SCOPE_CD"));
					bean.setScopeNm(resultSetOtherChapterData.getString("SCOPE_NM"));
					bean.setItemId(resultSetOtherChapterData.getInt("ITEM_ID"));
					bean.setItemName(resultSetOtherChapterData.getString("ITEM_NM"));
					bean.setSubItemId(resultSetOtherChapterData.getInt("SUB_ITEM_ID"));
					bean.setSubItemName(resultSetOtherChapterData.getString("SUB_ITEM_NM"));
					bean.setInformation(resultSetOtherChapterData.getString("INFORMATION"));
					bean.setFinalts(resultSetOtherChapterData.getString("FINAL"));
					bean.setSubScopeCd(resultSetOtherChapterData.getString("SUB_SCOPE_CD"));
					bean.setDescription(resultSetOtherChapterData.getString("DESCRIPTION"));
					bean.setEquipment(resultSetOtherChapterData.getString("EQUIPMENT"));
					bean.setTest(resultSetOtherChapterData.getString("TEST"));
					bean.setEquivalent(resultSetOtherChapterData.getString("EQUIVALENT"));
					bean.setPanelType(resultSetOtherChapterData.getString("PANEL_TYPE"));
					bean.setCustType(resultSetOtherChapterData.getString("CUST_TYPE"));
					bean.setQuant(resultSetOtherChapterData.getString("QTY"));
					bean.setCost(resultSetOtherChapterData.getInt("COST"));
					bean.setEditFlag(resultSetOtherChapterData.getInt("EDIT_FLG"));
					bean.setNewColValFlag(resultSetOtherChapterData.getInt("NEW_COL_VAL_FLG"));
					bean.setRemarks(resultSetOtherChapterData.getString("REMARKS"));
					bean.setNote(resultSetOtherChapterData.getString("NOTE"));

					saveOtherCapterList.add(bean);

				}
				if (!saveOtherCapterList.isEmpty()) {
					DBOForm.setSaveOtherChapterList(saveOtherCapterList);

				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

			UtilityMethods.closeResource(connection, callableStatement, resultSetOtherChapterData);
		}
		return DBOForm;
	}

	@Override
	public DBOForm saveAttachment(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAttachmentData = null;
		ResultSet resultSetAssignedUser = null;
		ResultSet resultSetdata = null;
		List<DBOForm> attachmentList = new ArrayList<DBOForm>();

		int len;

		try {

			len = 0;

			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_ATTACHEMENTS(?,?,?,?,?,?,?,?,?,?) }");
			
			
			
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());
			callableStatement.setInt("SS_ID", DBOForm.getSsId());
			callableStatement.setString("ITEM_NM", DBOForm.getNewItem());
			callableStatement.setInt("SL_NO", DBOForm.getSlNo());
			callableStatement.setString("FILE_NM", DBOForm.getFileName());
			callableStatement.setString("FILE_PATH", DBOForm.getFilePath());
			callableStatement.setInt("MOD_BY", DBOForm.getModifiedById());
			callableStatement.setString("REMARKS", DBOForm.getRemarks());
			callableStatement.setInt("DELETE_FLG", DBOForm.getDeleteFlag());

			if (DBOForm.getItem() != null) {
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] imageByte = decoder.decodeBuffer(DBOForm.getItem());
				callableStatement.setBytes("ITEM", imageByte);
			} else {
				callableStatement.setBinaryStream("ITEM", null);
			}

			logger.info(DBOForm.getQuotId());
			logger.info(DBOForm.getSsId());
			logger.info(DBOForm.getNewItem());
			logger.info(DBOForm.getFilePath());
			logger.info(DBOForm.getModifiedById());
			logger.info(DBOForm.getItem());
			logger.info(DBOForm.getSlNo());
			logger.info(DBOForm.getFileName());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> saveAttachmentList = new ArrayList<>();
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetAttachmentData = callableStatement.getResultSet();
				while (resultSetAttachmentData.next()) {
					DBOBean bean = new DBOBean();

					bean.setItemId(resultSetAttachmentData.getInt("ITEM_ID"));
					bean.setItemName(resultSetAttachmentData.getString("ITEM_NM"));
					bean.setItem(resultSetAttachmentData.getString("ITEM"));
					bean.setFilePath(resultSetAttachmentData.getString("FILE_PATH"));
					bean.setSsId(resultSetAttachmentData.getInt("SS_ID"));
					bean.setScopeNm(resultSetAttachmentData.getString("SCOPE_NM"));
					bean.setRemarks(resultSetAttachmentData.getString("REMARKS"));
					bean.setSlNum(resultSetAttachmentData.getInt("SL_NO"));
					bean.setName(resultSetAttachmentData.getString("FILE_NM"));
					saveAttachmentList.add(bean);

				}
				if (!saveAttachmentList.isEmpty()) {
					DBOForm.setAttachmentList(saveAttachmentList);

				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetdata);
		}
		return DBOForm;
	}

	@Override
	public DBOForm getUpdateGeneralInput(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetInstrFilter = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_UPDATE_GENERAL_INPUT(?,?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());
			callableStatement.setInt("MOD_BY", DBOForm.getModifiedById());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return DBOForm;
	}

	@Override

	public QuotationForm changeGeneralInput(String typeOfQuotation, String typeofPanel, String make, String dutySync) {
		QuotationForm quotationForm = new QuotationForm();
		try {

			// String query = " SELECT
			// ELE_FILTER_ID,ELE_TYPE,GEN_TYPE,FILTER_ID,FILTER_NM,A.ITEM_ID,A.COL_VAL_CD,A.COL_VAL_NM,SUB_COL_VAL_NM,MAKE,B.DEFLT_FLG,MIN_VAL,MAX_VAL,DISP_IND
			// FROM ELE_FILTER A,ELE_SPL_FILTER B WHERE FILTER_NM = 'Excitation
			// Type' AND A.TYPE_OF_QUOTATION = B.TYPE_OF_QUOTATION AND
			// A.TYPE_OF_PANEL = B.TYPE_OF_PANEL AND COL_NM = 'Type of AVR'AND
			// A.COL_VAL_CD = B.EXCITATION_TYPE AND PANEL_DEPEND_FLG = 1 AND
			// A.TYPE_OF_QUOTATION = '"+ typeOfQuotation + "' AND
			// A.TYPE_OF_PANEL = '"+ typeofPanel + "'AND MAKE = '"+ make + "'
			// AND DUTY_SYNC = '"+ dutySync + "'";

			// String query1 = "SELECT
			// ELE_FILTER_ID,ELE_TYPE,GEN_TYPE,FILTER_ID,FILTER_NM,A.ITEM_ID,A.COL_VAL_CD,A.COL_VAL_NM,SUB_COL_VAL_NM,DEFLT_FLG,MIN_VAL,MAX_VAL,DISP_IND
			// FROM ELE_FILTER A WHERE FILTER_NM = 'Excitation Type' AND
			// PANEL_DEPEND_FLG = 1 AND A.TYPE_OF_QUOTATION = '"+
			// typeOfQuotation + "' AND A.TYPE_OF_PANEL = '"+ typeofPanel + "'
			// AND COL_VAL_CD IN (SELECT DISTINCT EXCITATION_TYPE FROM
			// ELE_SPL_FILTER B WHERE COL_NM = 'Type of AVR' AND
			// TYPE_OF_QUOTATION = '"+ typeOfQuotation + "' AND TYPE_OF_PANEL =
			// '"+ typeofPanel + "' AND MAKE IN ( 'Leroysomer' ,'Cummins') AND
			// MAKE = '"+ make + "' AND DUTY_SYNC = '"+ dutySync + "')UNION ALL
			// SELECT
			// ELE_FILTER_ID,ELE_TYPE,GEN_TYPE,FILTER_ID,FILTER_NM,A.ITEM_ID,A.COL_VAL_CD,A.COL_VAL_NM,SUB_COL_VAL_NM,1
			// AS DEFLT_FLG,MIN_VAL,MAX_VAL,DISP_IND FROM ELE_FILTER A WHERE
			// FILTER_NM = 'Excitation Type' AND PANEL_DEPEND_FLG = 1 AND
			// A.TYPE_OF_QUOTATION = '"+ typeOfQuotation + "' AND
			// A.TYPE_OF_PANEL = '"+ typeofPanel + "' AND COL_VAL_CD IN ( SELECT
			// DISTINCT EXCITATION_TYPE FROM ELE_SPL_FILTER B WHERE COL_NM =
			// 'Type of AVR' AND TYPE_OF_QUOTATION = '"+ typeOfQuotation + "'
			// AND TYPE_OF_PANEL = '"+ typeofPanel + "' AND MAKE = 'TDPS' AND
			// MAKE = '"+ make + "' AND DUTY_SYNC = '"+ dutySync + " ')";

			String query1 = "SELECT ELE_FILTER_ID,ELE_TYPE,GEN_TYPE,FILTER_ID,(SELECT UN_ITEM_NM FROM ITEM_MAST WHERE SCOP_CD IN ('FILTER') AND UN_ITEM_CD = FILTER_CD ) AS FILTER_NM,A.ITEM_ID,A.COL_VAL_CD,A.COL_VAL_NM,SUB_COL_VAL_NM,DEFLT_FLG,MIN_VAL,MAX_VAL,DISP_IND FROM ELE_FILTER A	WHERE  FILTER_CD = 'EX_TYP' AND PANEL_DEPEND_FLG  = 1 AND A.TYPE_OF_QUOTATION = '"
					+ typeOfQuotation + "' AND A.TYPE_OF_PANEL = '" + typeofPanel
					+ "' AND COL_VAL_CD IN (SELECT DISTINCT EXCITATION_TYPE FROM ELE_SPL_FILTER B WHERE  COL_NM = 'Type of AVR' AND TYPE_OF_QUOTATION = '"
					+ typeOfQuotation + "' AND TYPE_OF_PANEL = '" + typeofPanel
					+ "' AND MAKE IN ( 'Leroysomer' ,'Cummins') AND MAKE = '" + make + "' AND DUTY_SYNC = '" + dutySync
					+ "')UNION ALL SELECT ELE_FILTER_ID,ELE_TYPE,GEN_TYPE,FILTER_ID,(SELECT UN_ITEM_NM FROM ITEM_MAST WHERE SCOP_CD IN ('FILTER') AND UN_ITEM_CD = FILTER_CD ) AS FILTER_NM,A.ITEM_ID,A.COL_VAL_CD,A.COL_VAL_NM,SUB_COL_VAL_NM,1 AS DEFLT_FLG,MIN_VAL,MAX_VAL,DISP_IND FROM ELE_FILTER A WHERE  FILTER_CD = 'EX_TYP' AND PANEL_DEPEND_FLG  = 1 AND A.TYPE_OF_QUOTATION = '"
					+ typeOfQuotation + "' AND A.TYPE_OF_PANEL = '" + typeofPanel
					+ "' AND COL_VAL_CD IN ( SELECT DISTINCT EXCITATION_TYPE FROM ELE_SPL_FILTER B WHERE  COL_NM = 'Type of AVR' AND TYPE_OF_QUOTATION = '"
					+ typeOfQuotation + "' AND TYPE_OF_PANEL = '" + typeofPanel + "' AND MAKE = 'TDPS' AND MAKE = '"
					+ make + "' AND DUTY_SYNC = '" + dutySync + " ')";

			List<DBOBean> generalInputList = jdbcTemplate.query(query1, new RowMapper<DBOBean>() {
				public DBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
					DBOBean bean = new DBOBean();

					// ELE_FILTER_ID ELE_TYPE GEN_TYPE FILTER_ID FILTER_NM
					// ITEM_ID COL_VAL_CD COL_VAL_NM SUB_COL_VAL_NM
					// DEFLT_FLG MIN_VAL MAX_VAL DISP_IND
					bean.setEleFilterId(rs.getInt("ELE_FILTER_ID"));
					bean.setEleType(rs.getString("ELE_TYPE"));
					bean.setGenType(rs.getString("GEN_TYPE"));
					bean.setFilterId(rs.getInt("FILTER_ID"));
					bean.setItemName(rs.getString("FILTER_NM"));
					bean.setItemId(rs.getInt("ITEM_ID"));
					bean.setColValCd(rs.getString("COL_VAL_CD"));
					bean.setColValNm(rs.getString("COL_VAL_NM"));
					bean.setSubColValNm(rs.getString("SUB_COL_VAL_NM"));
					// bean.setMake(rs.getString("MAKE"));
					bean.setDefaultFlag(rs.getInt("DEFLT_FLG") == 1 ? true : false);
					bean.setMinVal(rs.getFloat("MIN_VAL"));
					bean.setMaxVal(rs.getFloat("MAX_VAL"));
					bean.setDispInd(rs.getInt("DISP_IND"));

					// bean.setEleFilterId(rs.getInt("ELE_FILTER_ID"));
					// bean.setEleType(rs.getString("ELE_TYPE"));
					// bean.setGenType(rs.getString("GEN_TYPE"));
					// bean.setFilterId(rs.getInt("FILTER_ID"));
					// bean.setItemName(rs.getString("FILTER_NM"));
					// bean.setItemId(rs.getInt("ITEM_ID"));
					// bean.setColValCd(rs.getString("COL_VAL_CD"));
					// bean.setColValNm(rs.getString("COL_VAL_NM"));
					// bean.setSubColValNm(rs.getString("SUB_COL_VAL_NM"));
					// bean.setDefaultFlagNew(rs.getInt("DEFLT_FLG"));
					// bean.setMinVal(rs.getFloat("MIN_VAL"));
					// bean.setMaxVal(rs.getFloat("MAX_VAL"));
					// bean.setDispInd(rs.getInt("DISP_IND"));
					return bean;
				}
			});

			quotationForm.setGeneralInputList(generalInputList);

			quotationForm.setSuccessCode(0);
			quotationForm.setSuccessMsg("SUCCESS");

			return quotationForm;

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return quotationForm;
		}

	}

	@Override
	public DBOForm getComercial(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetComercial = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_COMMRCIAL(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (callableStatement.getMoreResults()) {
				List<DBOBean> dboComercialItemList = new ArrayList<DBOBean>();
				resultSetComercial = callableStatement.getResultSet();
				while (resultSetComercial.next()) {
					DBOBean DBOBean = new DBOBean();
					DBOBean.setId(resultSetComercial.getInt("ID"));
					DBOBean.setSectionCd(resultSetComercial.getString("SECTION_CD"));
					DBOBean.setItemId(resultSetComercial.getInt("ITEM_ID"));
					DBOBean.setItemName(resultSetComercial.getString("ITEM_NM"));
					DBOBean.setSubItemId(resultSetComercial.getInt("SUB_ITEM_ID"));
					DBOBean.setSubItemName(resultSetComercial.getString("SUB_ITEM_NM"));
					DBOBean.setSubItemTypeCd(resultSetComercial.getString("SUB_ITEM_TYPE_CD"));
					DBOBean.setSubItemTypeName(resultSetComercial.getString("SUB_ITEM_TYPE_NM"));
					DBOBean.setFixedText1(resultSetComercial.getString("FIXED_TEXT1"));
					DBOBean.setFixedText2(resultSetComercial.getString("FIXED_TEXT2"));
					DBOBean.setFixedText3(resultSetComercial.getString("FIXED_TEXT3"));
					DBOBean.setEditFlag(resultSetComercial.getInt("EDIT_FLG"));
					DBOBean.setNewColValFlag(resultSetComercial.getInt("NEW_COL_FLG"));
					DBOBean.setNote(resultSetComercial.getString("NOTE"));

					dboComercialItemList.add(DBOBean);

				}

				if (!dboComercialItemList.isEmpty()) {
					DBOForm.setDboComercialItemList(dboComercialItemList);
					logger.info("check start");
					logger.info(dboComercialItemList);
					logger.info("check end");
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetComercial);

		}
		return DBOForm;
	}

	@Override
	public DBOForm saveComercial(DBOForm DBOForm) {

		SQLServerPreparedStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		Connection connection = null;
		ResultSet resultSetComr1 = null;
		ResultSet resultSetComr2 = null;
		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable COMR = new SQLServerDataTable();
			COMR.addColumnMetadata("SECTION_CD", java.sql.Types.VARCHAR);
			COMR.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			COMR.addColumnMetadata("ITEM_NM", java.sql.Types.VARCHAR);
			COMR.addColumnMetadata("SUB_ITEM_ID", java.sql.Types.NUMERIC);
			COMR.addColumnMetadata("SUB_ITEM_TYPE_CD", java.sql.Types.VARCHAR);
			COMR.addColumnMetadata("FIXED_TEXT1", java.sql.Types.VARCHAR);
			COMR.addColumnMetadata("FIXED_TEXT2", java.sql.Types.VARCHAR);
			COMR.addColumnMetadata("FIXED_TEXT3", java.sql.Types.VARCHAR);
			COMR.addColumnMetadata("EDIT_FLG", java.sql.Types.NUMERIC);
			COMR.addColumnMetadata("NEW_COL_FLG", java.sql.Types.NUMERIC);
			COMR.addColumnMetadata("UNCHECK_FLG", java.sql.Types.NUMERIC);
			for (DBOBean bean : DBOForm.getSavedComercialDataList()) {
				COMR.addRow(

						bean.getSectionCd(), bean.getItemId(), bean.getItemName(), bean.getSubItemId(),
						bean.getSubItemTypeCd(), bean.getFixedText1(), bean.getFixedText2(), bean.getFixedText3(),
						bean.getEditFlag(), bean.getNewColValFlag(), bean.getUncheckFlag());

			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_COMR(?,?,?)}");

			callableStatement.setInt(1, DBOForm.getQuotId());

			callableStatement.setStructured(2, "dbo.COMR ", COMR);

			callableStatement.setInt(3, DBOForm.getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> saveComrList1 = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetComr1 = callableStatement.getResultSet();
				while (resultSetComr1.next()) {
					DBOBean DBOBean = new DBOBean();

					DBOBean.setMastQuotId(resultSetComr1.getInt("MAST_QUOT_ID"));
					DBOBean.setFixedText2(resultSetComr1.getString("FIXED_TEXT2"));
					DBOBean.setEditFlag(resultSetComr1.getInt("EDIT_FLG"));
					DBOBean.setNewColValFlag(resultSetComr1.getInt("NEW_COL_FLG"));
					DBOBean.setUncheckFlag(resultSetComr1.getInt("UNCHECK_FLG"));

					saveComrList1.add(DBOBean);

				}
				if (!saveComrList1.isEmpty()) {
					DBOForm.setSaveComrList1(saveComrList1);
					logger.info("check comercial list1 start");
					logger.info(saveComrList1);
					logger.info("check comercial list1 end");
				}

			}

			List<DBOBean> saveComrList2 = new ArrayList<>();
			if (callableStatement.getMoreResults()) {

				resultSetComr2 = callableStatement.getResultSet();
				while (resultSetComr2.next()) {
					DBOBean DBOBean = new DBOBean();
					// MAST_QUOT_ID, SECTION_CD,ITEM_ID,
					// ITEM_NM,FIXED_TEXT1,FIXED_TEXT2,FIXED_TEXT3,EDIT_FLG,NEW_COL_FLG
					DBOBean.setMastQuotId(resultSetComr2.getInt("MAST_QUOT_ID"));
					DBOBean.setSectionCd(resultSetComr2.getString("SECTION_CD"));
					DBOBean.setItemId(resultSetComr2.getInt("ITEM_ID"));
					DBOBean.setItemName(resultSetComr2.getString("ITEM_NM"));

					DBOBean.setFixedText1(resultSetComr2.getString("FIXED_TEXT1"));
					DBOBean.setFixedText2(resultSetComr2.getString("FIXED_TEXT2"));
					DBOBean.setFixedText3(resultSetComr2.getString("FIXED_TEXT3"));
					DBOBean.setEditFlag(resultSetComr2.getInt("EDIT_FLG"));
					DBOBean.setNewColValFlag(resultSetComr2.getInt("NEW_COL_FLG"));
					DBOBean.setUncheckFlag(resultSetComr2.getInt("UNCHECK_FLG"));

					saveComrList2.add(DBOBean);

				}
				if (!saveComrList2.isEmpty()) {
					DBOForm.setSaveComrList2(saveComrList2);
					logger.info("check comercial list2 start");
					logger.info(saveComrList2);
					logger.info("check comercial list2 end");
				}

			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetComr1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetComr2);
		}
		return DBOForm;
	}

	@Override
	public DBOForm getVmsCache(DBOForm DBOForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetVms = null;

		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(DBOForm);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ELE_VMS_CACHE(?) }");
			callableStatement.setInt("QUOT_ID", DBOForm.getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				DBOForm.setSuccessCode(resultOutParameterInt);
				DBOForm.setSuccessMsg(resultOutParameterString);
				DBOForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			List<DBOBean> dboVmsList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetVms = callableStatement.getResultSet();
				while (resultSetVms.next()) {
					DBOBean bean = new DBOBean();

					bean.setTypeOfPanel(resultSetVms.getString("TYPE_OF_PANEL"));
					bean.setAltMake(resultSetVms.getString("ALT_MAKE"));
					bean.setAltMakeDefaultFlag(resultSetVms.getInt("ALT_MAKE_DEFLT_FLG"));
					bean.setGearbox(resultSetVms.getString("GEAR_BOX"));
					bean.setGearBoxDefaultFlag(resultSetVms.getInt("GEAR_BOX_DEFLT_FLG"));
					bean.setType(resultSetVms.getString("TYPE"));
					bean.setTypeDefaultFlag(resultSetVms.getInt("TYPE_DEFLT_FLG"));
					bean.setMake(resultSetVms.getString("MAKE"));
					bean.setMakeDefaultFlag(resultSetVms.getInt("MAKE_DEFLT_FLG"));

					dboVmsList.add(bean);
					logger.info("output");
					logger.info(dboVmsList);

				}

				if (!dboVmsList.isEmpty()) {
					DBOForm.setDboVmsList(dboVmsList);
				}
			}

		} catch (Exception e) {
			DBOForm.setSuccessCode(-1);
			DBOForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			DBOForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return DBOForm;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetVms);

		}
		return DBOForm;
	}

	private void clearMessageFrom(DBOForm DBOForm) {
		if (null != DBOForm) {
			DBOForm.getMsgToUser().clear();
		}
	}

	@Override
	public DBOForm getUpdateCreateMechAuxPrice(DBOForm dboForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetReqNo = null;
		String code = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TYP_MECH_AUX_PRICE_UPD = new SQLServerDataTable();

			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("UPD_REQ_NO", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("UPD_REQ_NAME", java.sql.Types.VARCHAR);
			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("PRICE_ID", java.sql.Types.NUMERIC);

			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("ITEM_ID", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("TURB_CD", java.sql.Types.VARCHAR);
			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("PRICE_CODE", java.sql.Types.VARCHAR);
			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("PRICE", java.sql.Types.REAL);
			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("APPROX_COST_FLG", java.sql.Types.NUMERIC);
			TYP_MECH_AUX_PRICE_UPD.addColumnMetadata("IS_ACTIVE", java.sql.Types.NUMERIC);

			for (DBOBean bean : dboForm.getUpdateMechAuxPrice()) {

				TYP_MECH_AUX_PRICE_UPD.addRow

				(dboForm.getSaveBasicDetails().getUpdateRequestNumber(), bean.getUpdateCode(), bean.getPriceId(),

						bean.getItemId(),

						bean.getTurbCode(), bean.getPriceCode(), bean.getPrice(), bean.getApproxCostFlag(),

						bean.getActiveNew())

				;
				logger.info(bean.getUpdateCode());

				code = bean.getUpdateCode();
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_UPD_CREATE_MECH_AUX_PRICE (?,?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.TYP_MECH_AUX_PRICE_UPD ", TYP_MECH_AUX_PRICE_UPD);
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
}
