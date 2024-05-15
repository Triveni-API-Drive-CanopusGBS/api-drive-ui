package com.ttl.ito.business.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.ttl.ito.business.beans.AddOnComponent;
import com.ttl.ito.business.beans.CProjectList;
import com.ttl.ito.business.beans.CommentBean;
import com.ttl.ito.business.beans.DBOBean;
import com.ttl.ito.business.beans.ErectionCommissionBean;
import com.ttl.ito.business.beans.F2FCostBean;
import com.ttl.ito.business.beans.F2FForm;
import com.ttl.ito.business.beans.F2FUBOBean;
import com.ttl.ito.business.beans.OneLineBomData;
import com.ttl.ito.business.beans.OtherCostsBean;
import com.ttl.ito.business.beans.QuestionsBean;
import com.ttl.ito.business.beans.QuestionsEntity;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.QuotationHomeGrid;
import com.ttl.ito.business.beans.ReportBean;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.beans.SaveQuesDetails;
import com.ttl.ito.business.beans.ScopeOfSupply;
import com.ttl.ito.business.beans.SelectBox;
import com.ttl.ito.business.beans.TransportationDetailsBean;
import com.ttl.ito.business.beans.TurbineDetails;
import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.business.dao.QuotationDao;
import com.ttl.ito.common.Utility.UtilityMethods;
import com.ttl.ito.internal.beans.ItoConstants;

/*
 * 
 * Created by Basavesh B R and Kavya class Name: QuotationDaoImpl This class is
 * used to handle all Quotation related requests such as to fetch & save basic
 * Details, scope of supply create quotation , get quotation details from DB,
 * edit quotation etc.,
 * 
 * this class has implementations of methods which interacts with Database
 */

@Repository
public class QuotationDaoImpl implements QuotationDao {
	private Logger logger = Logger.getLogger(QuotationDaoImpl.class);
	public static int size;
	public static int size1;
	public static int size2;
	public static int size3;
	public static int size4;
	public static int size5;
	public static int size6;
	public static int size7;
	public static int sizeCi;
	public static int size8;
	public static int size9;
	public static int size10;
	public static int size11;
	public static int size12;
	public static int size13;
	public static int size14;
	public static int size15;
	public static int size16;
	public static int size17;
	public static int size18;
	public static int size19;
	public static int size20;
	public static int size21;
	public static int size202;
	public static int sizeA;
	public static int sizeB;
	List<List> list = new ArrayList<List>();
	public static int test = 5;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${TECHNICAL_EXCEPTION}")
	public String TECHNICAL_EXCEPTION;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public QuotationForm getQuotationHomeGrid(QuotationForm quotationForm) {

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuotHomeGrid = null;
		ResultSet resultSetMyQuotGrid = null;
		Connection connection = null;
		List<QuotationHomeGrid> quotationHomeGridList = new ArrayList<>();

		try {
			List<String> regionsListStr = new ArrayList<>();
			for (SelectBox myRegions : quotationForm.getUserDetails().getUserRegionsList()) {
				regionsListStr.add(String.valueOf(myRegions.getKey()));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_HOMEPAGE(?,?,?) }");

			callableStatement.setInt(1, quotationForm.getLoggedInUserId()); // user
																			// Id
			callableStatement.setString(2, String.join(",", regionsListStr)); // regions
																				// Assigned
			callableStatement.setInt(3, quotationForm.getUserDetails().getUserRoleId()); // role
																							// id
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {
				// fetch Quotations on home Grid data based on Region
				if (callableStatement.getMoreResults()) {
					resultSetQuotHomeGrid = callableStatement.getResultSet();
					while (resultSetQuotHomeGrid.next()) {
						QuotationHomeGrid quotationHomeGrid = new QuotationHomeGrid();
						quotationHomeGrid.setQuotId(resultSetQuotHomeGrid.getInt(ItoConstants.QUOT_ID));
						quotationHomeGrid.setCustId(resultSetQuotHomeGrid.getInt(ItoConstants.CUST_ID));
						quotationHomeGrid.setCustName(resultSetQuotHomeGrid.getString(ItoConstants.CUST_NAME));
						// quotationHomeGrid.setOppName(resultSetQuotHomeGrid.getString(ItoConstants.OPP_NAME));
						quotationHomeGrid.setQuotNumber(resultSetQuotHomeGrid.getString(ItoConstants.QUOT_NUM));
						quotationHomeGrid
								.setOpportunitySeqNum(resultSetQuotHomeGrid.getString(ItoConstants.OPP_SEQ_NO));
						quotationHomeGrid.setCapacity(resultSetQuotHomeGrid.getString(ItoConstants.CAPACITY));
						quotationHomeGrid.setcNewNumber(resultSetQuotHomeGrid.getString(ItoConstants.C_NUM_NEW));
						quotationHomeGrid.setcOldNumber(resultSetQuotHomeGrid.getString(ItoConstants.C_NUM_OLD));
						quotationHomeGrid.setFramePowerId(resultSetQuotHomeGrid.getInt(ItoConstants.FRM_POW_ID));
						quotationHomeGrid.setFrameName(resultSetQuotHomeGrid.getString(ItoConstants.FRM_NM));
						quotationHomeGrid.setStatusId(resultSetQuotHomeGrid.getInt(ItoConstants.STATUS_ID));
						quotationHomeGrid.setStatusCode(resultSetQuotHomeGrid.getString(ItoConstants.STATUS_CD));
						quotationHomeGrid.setStatusName(resultSetQuotHomeGrid.getString(ItoConstants.STATUS_NAME));
						quotationHomeGrid.setAssignedToId(resultSetQuotHomeGrid.getInt(ItoConstants.ASSIGNED_TO_ID));
						quotationHomeGrid.setAssignedTo(resultSetQuotHomeGrid.getString(ItoConstants.ASSIGNED_TO));
						quotationHomeGrid.setCreatedDate(resultSetQuotHomeGrid.getString(ItoConstants.CREAT_DT));
						quotationHomeGrid.setCreatedBy(resultSetQuotHomeGrid.getString(ItoConstants.CREAT_BY));
						quotationHomeGrid.setCreatedById(resultSetQuotHomeGrid.getInt(ItoConstants.CREAT_BY_ID));
						quotationHomeGrid.setModifyDate(resultSetQuotHomeGrid.getString(ItoConstants.MOD_DT));
						quotationHomeGrid.setRegion(resultSetQuotHomeGrid.getString(ItoConstants.REGION));

						quotationHomeGridList.add(quotationHomeGrid);
					}
					quotationForm.setQuotationHomeGrid(quotationHomeGridList);
				}

				List<QuotationHomeGrid> myquotationList = new ArrayList<QuotationHomeGrid>();

				// fetch My Quotations Grid data based on User Id
				if (callableStatement.getMoreResults()) {
					resultSetMyQuotGrid = callableStatement.getResultSet();
					while (resultSetMyQuotGrid.next()) {
						QuotationHomeGrid quotationMyQuotGrid = new QuotationHomeGrid();
						quotationMyQuotGrid.setCustId(resultSetMyQuotGrid.getInt(ItoConstants.CUST_ID));
						quotationMyQuotGrid.setCustName(resultSetMyQuotGrid.getString(ItoConstants.CUST_NAME));

						quotationMyQuotGrid.setQuotId(resultSetMyQuotGrid.getInt(ItoConstants.QUOT_ID));
						quotationMyQuotGrid.setQuotNumber(resultSetMyQuotGrid.getString(ItoConstants.QUOT_NUM));
						quotationMyQuotGrid
								.setOpportunitySeqNum(resultSetMyQuotGrid.getString(ItoConstants.OPP_SEQ_NO));
						quotationMyQuotGrid.setCapacity(resultSetMyQuotGrid.getString(ItoConstants.CAPACITY));
						quotationMyQuotGrid.setcNewNumber(resultSetMyQuotGrid.getString(ItoConstants.C_NUM_NEW));
						quotationMyQuotGrid.setcOldNumber(resultSetMyQuotGrid.getString(ItoConstants.C_NUM_OLD));
						quotationMyQuotGrid.setFramePowerId(resultSetMyQuotGrid.getInt(ItoConstants.FRM_POW_ID));
						quotationMyQuotGrid.setFrameName(resultSetMyQuotGrid.getString(ItoConstants.FRM_NM));
						quotationMyQuotGrid.setStatusId(resultSetMyQuotGrid.getInt(ItoConstants.STATUS_ID));
						quotationMyQuotGrid.setStatusCode(resultSetMyQuotGrid.getString(ItoConstants.STATUS_CD));
						quotationMyQuotGrid.setStatusName(resultSetMyQuotGrid.getString(ItoConstants.STATUS_NAME));
						quotationMyQuotGrid.setAssignedToId(resultSetMyQuotGrid.getInt(ItoConstants.ASSIGNED_TO_ID));
						quotationMyQuotGrid.setAssignedTo(resultSetMyQuotGrid.getString(ItoConstants.ASSIGNED_TO));
						quotationMyQuotGrid.setCreatedDate(resultSetMyQuotGrid.getString(ItoConstants.CREAT_DT));
						quotationMyQuotGrid.setCreatedBy(resultSetMyQuotGrid.getString(ItoConstants.CREAT_BY));
						quotationMyQuotGrid.setCreatedById(resultSetMyQuotGrid.getInt(ItoConstants.CREAT_BY_ID));
						quotationMyQuotGrid.setModifyDate(resultSetMyQuotGrid.getString(ItoConstants.MOD_DT));
						quotationMyQuotGrid.setRegion(resultSetMyQuotGrid.getString(ItoConstants.REGION));

						myquotationList.add(quotationMyQuotGrid);
					}
					quotationForm.setMyQuotations(myquotationList);
				}

			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuotHomeGrid);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMyQuotGrid);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm fetchCacheData(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;

		ResultSet resultSetDepartments = null;
		ResultSet resultSetRegions = null;
		ResultSet resultSetRoles = null;
		ResultSet resultSetUserInfo = null;
		ResultSet resultSetCustomerDetails = null;
		ResultSet resultSetConsultantDetails = null;
		ResultSet resultSetEnduserDetails = null;
		ResultSet resultSetStateList = null;
		ResultSet resultSetTrnstype = null;
		ResultSet resultSetVarCost = null;
		ResultSet resultSetPerParam = null;
		ResultSet resultSetPerUnit = null;

		Map<String, List<SelectBox>> department = new HashMap<>();
		Map<String, List<SelectBox>> trnstype = new HashMap<>();
		Map<String, List<SelectBox>> regions = new HashMap<>();
		Map<String, List<SelectBox>> roles = new HashMap<>();

		List<UserProfileDetails> userDetailsList = new ArrayList<>();
		List<List> emailList = new ArrayList<List>();
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_CACHE() }");
			callableStatement.execute();

			// Added by Kavya
			// ================ Department List Start ====================

			resultSetDepartments = callableStatement.getResultSet();
			List<SelectBox> selectBoxDepartmentList = new ArrayList<>();
			while (resultSetDepartments.next()) {
				SelectBox selectBox = new SelectBox();
				selectBox.setKey(resultSetDepartments.getInt("DEPT_ID"));
				selectBox.setValue(resultSetDepartments.getString(ItoConstants.DEPT_NAME));
				selectBoxDepartmentList.add(selectBox);
			}
			if (!selectBoxDepartmentList.isEmpty()) {
				department.put("DEPARTMENTS", selectBoxDepartmentList);
				quotationForm.getDropDownColumnvalues().setDepartmentsList(department);
			}

			// ============== Department List End===============

			// ================ trans List Start ====================

			resultSetTrnstype = callableStatement.getResultSet();
			List<SelectBox> selectBoxTrnstypeList = new ArrayList<>();
			while (resultSetTrnstype.next()) {
				SelectBox selectBox = new SelectBox();
				selectBox.setKey(resultSetTrnstype.getInt("TRNS_TYP_ID"));
				selectBox.setValue(resultSetTrnstype.getString("TRNS_TYPE"));
				selectBoxTrnstypeList.add(selectBox);
			}
			if (!selectBoxTrnstypeList.isEmpty()) {
				trnstype.put("TRNSTYPE", selectBoxTrnstypeList);
				quotationForm.getDropDownColumnvalues().setTrnstypeList(trnstype);
			}

			// ============== Department List End===============

			// ============== Regions List Start ===============

			if (callableStatement.getMoreResults()) {
				resultSetRegions = callableStatement.getResultSet();
				List<SelectBox> selectBoxRegionsList = new ArrayList<>();
				while (resultSetRegions.next()) {
					SelectBox selectBox = new SelectBox();
					selectBox.setKey(resultSetRegions.getInt(ItoConstants.REGION_ID));
					selectBox.setValue(resultSetRegions.getString("NAME"));
					selectBox.setDependKey(resultSetRegions.getString("REGION_KEY"));
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
					selectBox.setValue(resultSetRoles.getString(ItoConstants.ROLE_NAME));
					selectBox.setCode(resultSetRoles.getString(ItoConstants.GROUP_CD));
					selectBoxRolesList.add(selectBox);
				}
				if (!selectBoxRolesList.isEmpty()) {
					roles.put("ROLES", selectBoxRolesList);
					quotationForm.getDropDownColumnvalues().setRolesList(roles);
				}
			}

			// ===============Regions List End ============

			// ============= Users List Start ===========

			if (callableStatement.getMoreResults()) {
				resultSetUserInfo = callableStatement.getResultSet();
				
				while (resultSetUserInfo.next()) {
					UserProfileDetails userDetails = new UserProfileDetails();
					List templist = new ArrayList();
					userDetails.setUserId(resultSetUserInfo.getInt("USER_ID"));
					userDetails.setEmpId(resultSetUserInfo.getInt("EMP_ID"));
					userDetails.setEmpName(resultSetUserInfo.getString("NAME"));
					userDetails.setContactNumber(resultSetUserInfo.getString("PH_NUM"));
					userDetails.setEmailId(resultSetUserInfo.getString(ItoConstants.EMAIL));
					userDetails.setGroupId(resultSetUserInfo.getInt("DEPT_ID"));
					userDetails.setGroup(resultSetUserInfo.getString(ItoConstants.DEPT_NAME));
					if (resultSetUserInfo.getBytes("IMAGE") != null) {
						// setting Image

						String encoded = Base64.getEncoder().encodeToString(resultSetUserInfo.getBytes("IMAGE"));
						// convert array of bytes back to base 64 format to
						// display in UI
						// setting encoded base 64 format to userdetails bean
						userDetails.setImage(encoded);
					}

					userDetails.setRegion(resultSetUserInfo.getString(ItoConstants.REGION));
					userDetails.setRole(resultSetUserInfo.getString("ROLES"));
					userDetails.setGroupCd(resultSetUserInfo.getString(ItoConstants.GROUP_CD));
					userDetails.setCreatedDate(resultSetUserInfo.getString(ItoConstants.CREAT_DT));
					userDetails.setModifiedDate(resultSetUserInfo.getString(ItoConstants.MOD_DT));
					userDetails.setCreatedById(resultSetUserInfo.getInt(ItoConstants.CREAT_BY_ID));
					userDetails.setModifiedById(resultSetUserInfo.getInt(ItoConstants.MOD_BY_ID));
					userDetails.setActive(resultSetUserInfo.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);
					userDetails.setLastLoggedInDate(resultSetUserInfo.getDate("LAST_LOGIN_DT"));
					userDetails.setRegionId(resultSetUserInfo.getInt(ItoConstants.REGION_ID));
					userDetails.setRoleId(resultSetUserInfo.getInt("ROLES_ID"));
					userDetails.setCreatedBy(resultSetUserInfo.getString(ItoConstants.CREAT_BY));
					userDetails.setModifiedBy(resultSetUserInfo.getString(ItoConstants.MOD_BY));
					userDetails.setDesignation(resultSetUserInfo.getString("DESIGNATION"));
					userDetailsList.add(userDetails);
					templist.add(resultSetUserInfo.getInt("USER_ID"));
					templist.add(resultSetUserInfo.getString("NAME"));
					templist.add(resultSetUserInfo.getString(ItoConstants.EMAIL));
					emailList.add(templist);
				}
				quotationForm.setUserDetailsEmailList(emailList);
				for (int i = 0; i<emailList.size();i++){
					//logger.info((int)emailList.get(i).get(0));
					//logger.info((String)emailList.get(i).get(1));
					//logger.info((String)emailList.get(i).get(2));
				}	
				List<List> temp = new ArrayList<List>();
				temp= quotationForm.getUserDetailsEmailList();
//				for (int i = 0; i<temp.size();i++){
//					logger.info("getmethod");
//					logger.info((int)temp.get(i).get(0));
//					logger.info((String)temp.get(i).get(1));
//					logger.info((String)temp.get(i).get(2));
//				}	
				/*UserProfileDetails userDetails = new UserProfileDetails();
				userDetails.setUserDetailsEmailList(emailList);
				for (int i = 0; i<userDetails.getUserDetailsEmailList().size();i++){
					logger.info((int)userDetails.getUserDetailsEmailList().get(i).get(0));
					logger.info((String)userDetails.getUserDetailsEmailList().get(i).get(1));
					logger.info((String)userDetails.getUserDetailsEmailList().get(i).get(2));
				}	*/
				list=emailList;
				setEmailList(emailList);
				getUserData(quotationForm, userDetailsList);
			}
			// ================ Users List End ====================

			// Map<String, List<LocationBean>> stateMap = new HashMap<>();
			//
			// List<LocationBean> stateList = new ArrayList<>();
			// if (callableStatement.getMoreResults()) {
			// resultSetStateList = callableStatement.getResultSet();
			// while (resultSetStateList.next()) {
			// LocationBean bean = new LocationBean();
			// bean.setLocationId(resultSetStateList.getInt(ItoConstants.STATE_ID));
			// bean.setLocationName(resultSetStateList.getString("STATE_NM"));
			// stateList.add(bean);
			// }
			// stateMap.put("stateList", stateList);
			// quotationForm.getDropDownColumnvalues().setStateList(stateMap);
			// }

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
					quotationForm.setVarCostData(varCostList);
					createVarCostsJson(varCostList, quotationForm);
				}
				if (!sparesCostList.isEmpty()) {
					quotationForm.setSparesCostData(sparesCostList);
					createSparesCostsJson(sparesCostList, quotationForm);
				}
				if (!otherCostList.isEmpty()) {
					quotationForm.setOtherCostData(otherCostList);
					logger.info("inside other cost list start");
					logger.info(otherCostList);
					logger.info("other cost list ends");
				}
			}

			// performance parameter
			List<DBOBean> performanceParameterList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetPerParam = callableStatement.getResultSet();
				while (resultSetPerParam.next()) {
					DBOBean bean = new DBOBean();

					bean.setUnitItemId(resultSetPerParam.getInt("UN_ITEM_ID"));
					bean.setScopeCd(resultSetPerParam.getString("SCOP_CD"));
					bean.setChptCd(resultSetPerParam.getString("CHPT_CD"));
					bean.setUnItemCd(resultSetPerParam.getString("UN_ITEM_CD"));
					bean.setUnItemNm(resultSetPerParam.getString("UN_ITEM_NM"));

					performanceParameterList.add(bean);

				}
				if (!performanceParameterList.isEmpty()) {
					quotationForm.setPerformanceParameterList(performanceParameterList);
				}
			}

			// performance unit
			List<DBOBean> performanceUnitList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetPerUnit = callableStatement.getResultSet();
				while (resultSetPerUnit.next()) {
					DBOBean bean = new DBOBean();

					bean.setUnitItemId(resultSetPerUnit.getInt("UN_ITEM_ID"));
					bean.setScopeCd(resultSetPerUnit.getString("SCOP_CD"));
					bean.setChptCd(resultSetPerUnit.getString("CHPT_CD"));
					bean.setUnItemCd(resultSetPerUnit.getString("UN_ITEM_CD"));
					bean.setUnItemNm(resultSetPerUnit.getString("UN_ITEM_NM"));

					performanceUnitList.add(bean);

				}
				if (!performanceUnitList.isEmpty()) {
					quotationForm.setPerformanceUnitList(performanceUnitList);
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {

			UtilityMethods.closeResource(connection, callableStatement, resultSetDepartments);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRegions);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRoles);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUserInfo);
			UtilityMethods.closeResource(connection, callableStatement, resultSetCustomerDetails);
			UtilityMethods.closeResource(connection, callableStatement, resultSetConsultantDetails);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEnduserDetails);
		}

		return quotationForm;
	}

	private void createVarCostsJson(List<DBOBean> dataList, QuotationForm quotationForm) {
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
			quotationForm.getVarQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);
		}
		quotationForm.setVarQuestionsBean(questionsBeanList);
		for (DBOBean dropDownType : quotationForm.getVarQuestionsBean()) {
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

	private void createSparesCostsJson(List<DBOBean> dataList, QuotationForm quotationForm) {
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
			quotationForm.getSparesQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);
		}
		quotationForm.setSparesQuestionsBean(questionsBeanList);
		for (DBOBean dropDownType : quotationForm.getSparesQuestionsBean()) {
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
	public QuotationForm fetchQuotCacheData(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetTypesOfTurbine = null;
		ResultSet resultSetTypesOfCondensor = null;
		ResultSet resultSetScopeOfSupply = null;
		ResultSet resultSetAddOns = null;
		ResultSet resultSetFrames = null;
		ResultSet resultSetDepartments = null;
		ResultSet resultSetRegions = null;
		ResultSet resultSetRoles = null;
		ResultSet resultSetUserInfo = null;
		ResultSet resultSetCustomerDetails = null;
		ResultSet resultSetConsultantDetails = null;
		ResultSet resultSetEnduserDetails = null;
		ResultSet resultSetExhaustList = null;
		ResultSet resultSetAddOnComps = null;
		ResultSet resultSetNewTypesOfTurbine = null;
		ResultSet resultsetCategory = null;
		ResultSet resultsetMaterial = null;
		ResultSet resultsettypeOfOffer = null;
		ResultSet resultsettypeOfQuotation = null;
		ResultSet resultsettypeOfInjection = null;
		ResultSet resultsettypeOfVarient = null;
		ResultSet resultsettypeOfCustomer = null;
		ResultSet resultsettypeOfNew = null;
		ResultSet resultsetframeVariant = null;
		ResultSet resultsettypeOfFrameVarient = null;
		ResultSet resultsettypeOfNew1 = null;
		
		Map<String, List<TurbineDetails>> frames = new HashMap<>();
		Map<String, List<TurbineDetails>> framesWithPowerNew = new HashMap<>();
		Map<String, List<TurbineDetails>> typesOfTurbine = new HashMap<>();
		Map<String, List<TurbineDetails>> typesOfNewTurbine = new HashMap<>();
		Map<String, List<TurbineDetails>> typesOfCondensor = new HashMap<>();

		Map<String, List<ScopeOfSupply>> scopeOfSupply = new HashMap<>();
		Map<String, List<TurbineDetails>> orientationType = new HashMap<>();
		Map<String, List<TurbineDetails>> addOnComponent = new HashMap<>();
		Map<String, List<AddOnComponent>> addOnComponentDetails = new HashMap<>();
		Map<String, List<F2FUBOBean>> categoryList = new HashMap<>();
		Map<String, List<F2FUBOBean>> materialList = new HashMap<>();

		Map<String, List<TurbineDetails>> framesWithPower = new HashMap<>();
		Map<String, List<TurbineDetails>> typeOfOfferList = new HashMap<>();
		Map<String, List<TurbineDetails>> typeOfQuotationList = new HashMap<>();
		Map<String, List<TurbineDetails>> typeOfInjectionList = new HashMap<>();
		Map<String, List<TurbineDetails>> typeOfVarientList = new HashMap<>();
		Map<String, List<TurbineDetails>> typeOfCustomerList = new HashMap<>();
		Map<String, List<TurbineDetails>> typeOfNewList = new HashMap<>();
		Map<String, List<TurbineDetails>> typeOfFrameVarientList = new HashMap<>();
		Map<String, List<TurbineDetails>> typeOfList1 = new HashMap<>();
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_QUOT_CACHE() }");
			callableStatement.execute();

			resultSetNewTypesOfTurbine = callableStatement.getResultSet();
			List<TurbineDetails> turbinestypeList = new ArrayList<>();
			while (resultSetNewTypesOfTurbine.next()) {
				TurbineDetails turbineDetails = new TurbineDetails();
				turbineDetails.setCategoryDetId(resultSetNewTypesOfTurbine.getInt(ItoConstants.CAT_DET_ID));
				turbineDetails.setCategoryDetCode(resultSetNewTypesOfTurbine.getString(ItoConstants.CAT_DET_CD));
				turbineDetails.setGrpCd(resultSetNewTypesOfTurbine.getString(ItoConstants.GRP_CD));
				turbineDetails.setCategoryDetDesc(resultSetNewTypesOfTurbine.getString(ItoConstants.CAT_DET_DESC));

				turbineDetails.setCategoryCreatedDate(resultSetNewTypesOfTurbine.getDate(ItoConstants.CREAT_DT));
				turbineDetails.setCategoryModifiedDate(resultSetNewTypesOfTurbine.getDate(ItoConstants.MOD_DT));
				turbineDetails.setCategoryCreatedBy(resultSetNewTypesOfTurbine.getInt(ItoConstants.CREAT_BY));
				turbineDetails.setCategoryModifiedBy(resultSetNewTypesOfTurbine.getInt(ItoConstants.MOD_BY));
				turbineDetails.setIscategoryActive(
						resultSetNewTypesOfTurbine.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

				turbinestypeList.add(turbineDetails);
			}
			if (!turbinestypeList.isEmpty()) {
				typesOfNewTurbine.put("TURBINE_NEW_TYPES", turbinestypeList);
				quotationForm.getDropDownColumnvalues().setTypesOfNewTurbine(typesOfNewTurbine);
			}

			// ================ TYPE OF TURBINE MODEL List Start ==============

			if (callableStatement.getMoreResults()) {
				resultSetTypesOfTurbine = callableStatement.getResultSet();
				List<TurbineDetails> turbinesModelList = new ArrayList<>();
				while (resultSetTypesOfTurbine.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetTypesOfTurbine.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setCategoryDetCode(resultSetTypesOfTurbine.getString(ItoConstants.CAT_DET_CD));

					turbineDetails.setCategoryDetDesc(resultSetTypesOfTurbine.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetTypesOfTurbine.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetTypesOfTurbine.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetTypesOfTurbine.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetTypesOfTurbine.getInt(ItoConstants.MOD_BY));
					turbineDetails.setIscategoryActive(
							resultSetTypesOfTurbine.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					turbinesModelList.add(turbineDetails);
				}
				if (!turbinesModelList.isEmpty()) {
					typesOfTurbine.put("TURBINE", turbinesModelList);
					quotationForm.getDropDownColumnvalues().setTypesOfTurbine(typesOfTurbine);
				}
			}
			// ============ TYPE OF TURBINE MODEL List End ==========

			
		
			// // ============== FRAME POWER List Start ===============
			//
			// ResultSet resultSetFramepower1 = null;
			//
			// if (callableStatement.getMoreResults()) {
			// List<TurbineDetails> framePowerListNew = new ArrayList<>();
			// resultSetFramepower1 = callableStatement.getResultSet();
			//
			// while (resultSetFramepower1.next()) {
			// TurbineDetails turbineDetails = new TurbineDetails();
			//
			// turbineDetails.setFramePowerId(resultSetFramepower1.getInt(ItoConstants.FRAME_POW_ID));
			// turbineDetails.setFrameId(resultSetFramepower1.getInt(ItoConstants.FRM_ID));
			// turbineDetails.setTurbineDesignCd(resultSetFramepower1.getString(ItoConstants.TURB_DESN));
			// turbineDetails.setTurbineCode(resultSetFramepower1.getString(ItoConstants.TURB_CD));
			// turbineDetails.setFrameDesc(resultSetFramepower1.getString(ItoConstants.FRM_NM));
			// //turbineDetails.setPower(resultSetFramepower.getFloat("POWER"));
			// turbineDetails.setMinPower(resultSetFramepower1.getFloat(ItoConstants.MIN_POWER));
			// turbineDetails.setMaxPower(resultSetFramepower1.getFloat(ItoConstants.MAX_POWER));
			// turbineDetails.setFrameCreatedDate(resultSetFramepower1.getDate(ItoConstants.CREAT_DT));
			// turbineDetails.setFrameModifiedDate(resultSetFramepower1.
			// getDate(ItoConstants.MOD_DT));
			// turbineDetails.setFrameCreatedBy(resultSetFramepower1.getInt(
			// ItoConstants.CREAT_BY));
			// turbineDetails.setFrameModifiedBy(resultSetFramepower1.getInt(ItoConstants.MOD_BY));
			// turbineDetails.setIscategoryActive(resultSetFramepower1.getInt(ItoConstants.IS_ACTIVE)
			// == 1 ? true : false);
			// framePowerListNew.add(turbineDetails);
			// }
			//
			// if (!framePowerListNew.isEmpty()) {
			// framesWithPowerNew.put("FRAMES_WITH_POWERNEW",
			// framePowerListNew);
			// quotationForm.getDropDownColumnvalues().setFrameWithPowerListNew(framesWithPowerNew);
			// }
			// }
			// // ============== FRAME POWER List End ======================

			// ============== Orientation type List Start ===============

			if (callableStatement.getMoreResults()) {
				resultSetExhaustList = callableStatement.getResultSet();
				List<TurbineDetails> orientationTypeList = new ArrayList<>();
				while (resultSetExhaustList.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetExhaustList.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setTurbineCode((resultSetExhaustList.getString(ItoConstants.TURB_CD)));
					turbineDetails.setCategoryDetCode(resultSetExhaustList.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setCategoryDetDesc(resultSetExhaustList.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetExhaustList.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetExhaustList.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetExhaustList.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetExhaustList.getInt(ItoConstants.MOD_BY));
					turbineDetails.setIscategoryActive(
							resultSetExhaustList.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					orientationTypeList.add(turbineDetails);
				}
				if (!orientationTypeList.isEmpty()) {
					orientationType.put("ORIENTATION_TYPE", orientationTypeList);
					quotationForm.getDropDownColumnvalues().setOrientationTypes(orientationType);
				}
			}

			// =================== Orientation Type List End =================

			// =================== CONDENSING TYPES List Start ===============

			if (callableStatement.getMoreResults()) {
				resultSetTypesOfCondensor = callableStatement.getResultSet();
				List<TurbineDetails> typesOfCondensorList = new ArrayList<>();
				while (resultSetTypesOfCondensor.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetTypesOfCondensor.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setTurbineCode((resultSetTypesOfCondensor.getString(ItoConstants.TURB_CD)));
					turbineDetails.setCategoryDetCode(resultSetTypesOfCondensor.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setCategoryDetDesc(resultSetTypesOfCondensor.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetTypesOfCondensor.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetTypesOfCondensor.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetTypesOfCondensor.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetTypesOfCondensor.getInt(ItoConstants.MOD_BY));
					turbineDetails.setIscategoryActive(
							resultSetTypesOfCondensor.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					typesOfCondensorList.add(turbineDetails);
				}
				if (!typesOfCondensorList.isEmpty()) {
					typesOfCondensor.put("CONDENSOR", typesOfCondensorList);
					quotationForm.getDropDownColumnvalues().setTypesOfCondensor(typesOfCondensor);
				}
			}

			// =================== CONDENSING TYPES List End ================

			// ================= Scope of Supply List Start ==============

			if (callableStatement.getMoreResults()) {
				resultSetScopeOfSupply = callableStatement.getResultSet();
				List<ScopeOfSupply> scopeOfSupplyList = new ArrayList<>();
				while (resultSetScopeOfSupply.next()) {
					ScopeOfSupply sos = new ScopeOfSupply();
					sos.setSsId(resultSetScopeOfSupply.getInt(ItoConstants.CAT_DET_ID));
					sos.setScopeCode(resultSetScopeOfSupply.getString(ItoConstants.CAT_DET_CD));
					sos.setScopeName(resultSetScopeOfSupply.getString(ItoConstants.CAT_DET_DESC));
					sos.setGrpCd(resultSetScopeOfSupply.getString(ItoConstants.GRP_CD));

					sos.setCreatedDate(resultSetScopeOfSupply.getString(ItoConstants.CREAT_DT));
					sos.setModifiedDate(resultSetScopeOfSupply.getString(ItoConstants.MOD_DT));
					sos.setCreatedById(resultSetScopeOfSupply.getInt(ItoConstants.CREAT_BY));
					sos.setModifiedById(resultSetScopeOfSupply.getInt(ItoConstants.MOD_BY));
					sos.setActive(resultSetScopeOfSupply.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					scopeOfSupplyList.add(sos);
				}
				if (!scopeOfSupplyList.isEmpty()) {
					scopeOfSupply.put("SCOPEOFSUPPLY", scopeOfSupplyList);
					quotationForm.getDropDownColumnvalues().setScopeOfSupply(scopeOfSupply);
				}
			}

			// =============== Scope of Supply List End =====================

			// Added by Kavya

			// ================ AddOn Components List =======================

			// if (callableStatement.getMoreResults()) {
			// resultSetAddOns = callableStatement.getResultSet();
			// List<TurbineDetails> addOnComponentList = new ArrayList<>();
			// while (resultSetAddOns.next()) {
			// TurbineDetails turbineDetails = new TurbineDetails();
			// turbineDetails.setCategoryDetId(resultSetAddOns.getInt(ItoConstants.CAT_DET_ID));
			// turbineDetails.setCategoryDetCode(resultSetAddOns.getString(ItoConstants.CAT_DET_CD));
			// turbineDetails.setCategoryDetDesc(resultSetAddOns.getString(ItoConstants.CAT_DET_DESC));
			// turbineDetails.setCategoryCreatedDate(resultSetAddOns.getDate(ItoConstants.CREAT_DT));
			// turbineDetails.setCategoryModifiedDate(resultSetAddOns.getDate(ItoConstants.MOD_DT));
			// turbineDetails.setCategoryCreatedBy(resultSetAddOns.getInt(ItoConstants.CREAT_BY));
			// turbineDetails.setCategoryModifiedBy(resultSetAddOns.getInt(ItoConstants.MOD_BY));
			// turbineDetails
			// .setIscategoryActive(resultSetAddOns.getInt(ItoConstants.IS_ACTIVE)
			// == 1 ? true : false);
			//
			// addOnComponentList.add(turbineDetails);
			// }
			// if (!addOnComponentList.isEmpty()) {
			// addOnComponent.put("ADD_ON_COMPONENTS", addOnComponentList);
			// quotationForm.getDropDownColumnvalues().setAddOnComponents(addOnComponent);
			// }
			// }
			// ==================== AddOns List end =====================
			// List<AddOnComponent> addOnComponentDetailList = new
			// ArrayList<>();
			//
			// if (callableStatement.getMoreResults()) {
			// resultSetAddOnComps = callableStatement.getResultSet();
			//
			// while (resultSetAddOnComps.next()) {
			// AddOnComponent adBean = new AddOnComponent();
			//// CAT_DET_ID CAT_DET_CD CAT_DET_DESC CREAT_DT MOD_DT CREAT_BY
			// MOD_BY IS_ACTIVE EFF_FROM EFF_TO
			// adBean.setCategoryDetId(resultSetAddOnComps.getInt(ItoConstants.CAT_DET_ID));
			// adBean.setCategoryDetCode(resultSetAddOnComps.getString(ItoConstants.CAT_DET_CD));
			// adBean.setCategoryDetDesc(resultSetAddOnComps.getString(ItoConstants.CAT_DET_DESC));
			// adBean.setCreatedDate(resultSetAddOnComps.getString(ItoConstants.CREAT_DT));
			// adBean.setModifiedDate(resultSetAddOnComps.getString(ItoConstants.MOD_DT));
			// adBean.setCreatedBy(resultSetAddOnComps.getInt(ItoConstants.CREAT_BY));
			// adBean.setModifiedBy(resultSetAddOnComps.getInt(ItoConstants.MOD_BY));
			// adBean
			// .setActive(resultSetAddOnComps.getInt(ItoConstants.IS_ACTIVE) ==
			// 1 ? true : false);
			// adBean.setEffFromDate(resultSetAddOnComps.getString(ItoConstants.EFF_FROM));
			// adBean.setEffToDate(resultSetAddOnComps.getString(ItoConstants.EFF_TO));
			//
			// addOnComponentDetailList.add(adBean);
			// }
			// if (!addOnComponentDetailList.isEmpty()) {
			// addOnComponentDetails.put("ADD_ON_COMPO_DETAILS",
			// addOnComponentDetailList);
			// quotationForm.getDropDownColumnvalues().setAddOnCompoInDetailList(addOnComponentDetails);
			// }
			// }

			ResultSet resultSetbleed = null;

			if (callableStatement.getMoreResults()) {
				resultSetbleed = callableStatement.getResultSet();
				List<TurbineDetails> bleedTypeList = new ArrayList<>();
				while (resultSetbleed.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetbleed.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setCategoryDetCode(resultSetbleed.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setCategoryDetDesc(resultSetbleed.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetbleed.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetbleed.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetbleed.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetbleed.getInt(ItoConstants.MOD_BY));
					turbineDetails
							.setIscategoryActive(resultSetbleed.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					bleedTypeList.add(turbineDetails);
				}
				if (!bleedTypeList.isEmpty()) {
					typesOfCondensor.put("BLEED_TYPE_LIST", bleedTypeList);
					quotationForm.getDropDownColumnvalues().setTypesOfCondensor(typesOfCondensor);
				}
			}

			// ================ Category List starts ===================
			List<F2FUBOBean> f2fCategoryList = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsetCategory = callableStatement.getResultSet();

				while (resultsetCategory.next()) {
					F2FUBOBean adBean = new F2FUBOBean();

					adBean.setCAT_ID(resultsetCategory.getInt("CAT_ID"));
					adBean.setCAT_NM(resultsetCategory.getString("CAT_NM"));
					adBean.setCAT_CD(resultsetCategory.getString("CAT_CD"));

					f2fCategoryList.add(adBean);
				}
				if (!f2fCategoryList.isEmpty()) {
					categoryList.put("CAT_DET_LIST", f2fCategoryList);
					quotationForm.getDropDownColumnvalues().setCategoryList(categoryList);
				}
			}
			// ================ Material starts ========================

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

					f2fMaterialList.add(adBean);
				}
				if (!f2fMaterialList.isEmpty()) {
					materialList.put("MAT_DET_LIST", f2fMaterialList);
					quotationForm.getDropDownColumnvalues().setMaterialList(materialList);
				}
			}

			
			// ================ TYPE OF OFFER starts ========================
			List<TurbineDetails> typeOfOfferList1 = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsettypeOfOffer = callableStatement.getResultSet();

				while (resultsettypeOfOffer.next()) {

					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultsettypeOfOffer.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setCategoryDetCode(resultsettypeOfOffer.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setCategoryDetDesc(resultsettypeOfOffer.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultsettypeOfOffer.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultsettypeOfOffer.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultsettypeOfOffer.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultsettypeOfOffer.getInt(ItoConstants.MOD_BY));
					turbineDetails.setIscategoryActive(
							resultsettypeOfOffer.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					typeOfOfferList1.add(turbineDetails);
				}
				if (!typeOfOfferList1.isEmpty()) {
					typeOfOfferList.put("TYPE_OF_OFFER_LIST", typeOfOfferList1);
					quotationForm.getDropDownColumnvalues().setTypeOfOfferList(typeOfOfferList);
				}
			}

			// ================ TYPE OF Quotation starts
			// ========================

			List<TurbineDetails> typeOfQuotationList1 = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsettypeOfQuotation = callableStatement.getResultSet();

				while (resultsettypeOfQuotation.next()) {

					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultsettypeOfQuotation.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setCategoryDetCode(resultsettypeOfQuotation.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setCategoryDetDesc(resultsettypeOfQuotation.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultsettypeOfQuotation.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultsettypeOfQuotation.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultsettypeOfQuotation.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultsettypeOfQuotation.getInt(ItoConstants.MOD_BY));
					turbineDetails.setIscategoryActive(
							resultsettypeOfQuotation.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					typeOfQuotationList1.add(turbineDetails);
				}
				if (!typeOfQuotationList1.isEmpty()) {
					typeOfQuotationList.put("TYPE_OF_QUOTATION_LIST", typeOfQuotationList1);
					quotationForm.getDropDownColumnvalues().setTypeOfQuotationList(typeOfQuotationList);
				}
			}

			// // ================ --TYPE OF INJECTION========================
			//
			// List<TurbineDetails> typeOfInjectionList1 = new ArrayList<>();
			//
			// if (callableStatement.getMoreResults()) {
			// resultsettypeOfInjection = callableStatement.getResultSet();
			//
			// while (resultsettypeOfInjection.next()) {
			//
			// TurbineDetails turbineDetails = new TurbineDetails();
			// turbineDetails.setCategoryDetId(resultsettypeOfInjection.getInt(ItoConstants.CAT_DET_ID));
			// turbineDetails.setCategoryDetCode(resultsettypeOfInjection.getString(ItoConstants.CAT_DET_CD));
			// turbineDetails.setCategoryDetDesc(resultsettypeOfInjection.getString(ItoConstants.CAT_DET_DESC));
			// turbineDetails.setCategoryCreatedDate(resultsettypeOfInjection.getDate(ItoConstants.CREAT_DT));
			// turbineDetails.setCategoryModifiedDate(resultsettypeOfInjection.getDate(ItoConstants.MOD_DT));
			// turbineDetails.setCategoryCreatedBy(resultsettypeOfInjection.getInt(ItoConstants.CREAT_BY));
			// turbineDetails.setCategoryModifiedBy(resultsettypeOfInjection.getInt(ItoConstants.MOD_BY));
			// turbineDetails.setIscategoryActive(resultsettypeOfInjection.getInt(ItoConstants.IS_ACTIVE)
			// == 1 ? true : false);
			//
			// typeOfInjectionList1.add(turbineDetails);
			// }
			// if (!typeOfInjectionList1.isEmpty()) {
			// typeOfInjectionList.put("TYPE_OF_INJECTION_LIST",
			// typeOfInjectionList1);
			// quotationForm.getDropDownColumnvalues().setTypeOfInjectionList(typeOfInjectionList);
			// }
			// }

			// ================ --TYPE OF VARIENT========================

			List<TurbineDetails> typeOfVarientList1 = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsettypeOfVarient = callableStatement.getResultSet();

				while (resultsettypeOfVarient.next()) {

					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultsettypeOfVarient.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setTurbineCode(resultsettypeOfVarient.getString(ItoConstants.TURB_CD));
					turbineDetails.setDependentCode(resultsettypeOfVarient.getString(ItoConstants.GRP_CD));
					turbineDetails.setSubDependentCode(resultsettypeOfVarient.getString(ItoConstants.SUB_GRP_CD));
					turbineDetails.setCategoryDetCode(resultsettypeOfVarient.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setCategoryDetDesc(resultsettypeOfVarient.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultsettypeOfVarient.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultsettypeOfVarient.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultsettypeOfVarient.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultsettypeOfVarient.getInt(ItoConstants.MOD_BY));
					turbineDetails.setIscategoryActive(
							resultsettypeOfVarient.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					typeOfVarientList1.add(turbineDetails);
				}
				if (!typeOfVarientList1.isEmpty()) {
					typeOfVarientList.put("TYPE_OF_VARIENT_LIST", typeOfVarientList1);
					quotationForm.getDropDownColumnvalues().setTypeOfVarientList(typeOfVarientList);
				}
			}

			// ================ --TYPE OF Customer========================

			List<TurbineDetails> typeOfCustomerList1 = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsettypeOfCustomer = callableStatement.getResultSet();

				while (resultsettypeOfCustomer.next()) {

					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultsettypeOfCustomer.getInt(ItoConstants.CAT_DET_ID));

					turbineDetails.setCategoryDetCode(resultsettypeOfCustomer.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setCategoryDetDesc(resultsettypeOfCustomer.getString(ItoConstants.CAT_DET_DESC));

					typeOfCustomerList1.add(turbineDetails);
				}
				if (!typeOfCustomerList1.isEmpty()) {
					typeOfCustomerList.put("TYPE_OF_CUSTOMER_LIST", typeOfCustomerList1);
					quotationForm.getDropDownColumnvalues().setTypeOfCustomerList(typeOfCustomerList);
				}
			}
			
			// ================ FRAMES List Start ====================

			if (callableStatement.getMoreResults()) {
				resultSetFrames = callableStatement.getResultSet();
				List<TurbineDetails> framesList = new ArrayList<>();
				while (resultSetFrames.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setFrameDesc(resultSetFrames.getString("FRM_NM"));
					turbineDetails.setFrameId(resultSetFrames.getInt("FRM_ID"));
					turbineDetails.setTurbineDesignCd(resultSetFrames.getString("TURB_DESN"));
					turbineDetails.setTurbineCode(resultSetFrames.getString("TURB_CD"));
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
					turbineDetails.setMaxPower(resultSetFrames.getFloat("MAXPOWER"));
					turbineDetails.setImprovedImpId(resultSetFrames.getString("IMPROVED_IMP_ID"));
					turbineDetails.setCondTypId(resultSetFrames.getInt("COND_TYP_ID"));
					turbineDetails.setCondTypName(resultSetFrames.getString("COND_TYP_NM"));
					turbineDetails.setBleedTypId(resultSetFrames.getInt("BLEED_TYP_ID"));
					turbineDetails.setBleedTypName(resultSetFrames.getString("BLEED_TYP_NM"));
					turbineDetails.setBleedTypCode(resultSetFrames.getString("BLEED_TYP_CD"));
					turbineDetails.setVariantTypeId(resultSetFrames.getInt("VARIANT_TYP_ID"));
					turbineDetails.setVariantTypeName(resultSetFrames.getString("VARIANT_TYP_NM"));
					
					framesList.add(turbineDetails);
				}
				if (!framesList.isEmpty()) {
					frames.put("FRAMES", framesList);
					quotationForm.getDropDownColumnvalues().setFrames(frames);
				}
			}
	// ================= FRAMES List End ===================
	

			// TYPE NEW
			List<TurbineDetails> typeOfNewList1 = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsettypeOfNew = callableStatement.getResultSet();

				while (resultsettypeOfNew.next()) {

					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setFrameDesc(resultsettypeOfNew.getString("FRM_NM"));
					turbineDetails.setFrameId(resultsettypeOfNew.getInt("FRM_ID"));
					turbineDetails.setFramePowerId(resultsettypeOfNew.getInt("FRAME_POW_ID"));
					turbineDetails.setTurbdesignName(resultsettypeOfNew.getString("TURB_DESN"));
					turbineDetails.setTurbineCode(resultsettypeOfNew.getString("TURB_CD"));
					turbineDetails.setMaxPower(resultsettypeOfNew.getFloat("MAXPOWER"));
					turbineDetails.setMinPower(resultsettypeOfNew.getFloat("MIN_POWER"));
					turbineDetails.setMaximumPower(resultsettypeOfNew.getFloat("MAX_POWER"));
					turbineDetails.setImprovedImpId(resultsettypeOfNew.getString("IMPROVED_IMP_ID"));
					typeOfNewList1.add(turbineDetails);
				}
				if (!typeOfNewList1.isEmpty()) {
					typeOfNewList.put("FRAMES_WITH_POWER", typeOfNewList1);
					quotationForm.getDropDownColumnvalues().setTypeOfNewList(typeOfNewList);
				}
			}
			List<TurbineDetails> typeOfNewList2 = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultsettypeOfNew1 = callableStatement.getResultSet();

				while (resultsettypeOfNew1.next()) {

					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setFrameDesc(resultsettypeOfNew1.getString("FRM_NM"));
					turbineDetails.setFrameId(resultsettypeOfNew1.getInt("FRM_ID"));
					turbineDetails.setTurbdesignName(resultsettypeOfNew1.getString("TURB_DESN"));
					turbineDetails.setTurbineCode(resultsettypeOfNew1.getString("TURB_CD"));
					turbineDetails.setMaxPower(resultsettypeOfNew1.getFloat("MAXPOWER"));
					turbineDetails.setMinPower(resultsettypeOfNew1.getFloat("MINPOWER"));
					turbineDetails.setMaximumPower(resultsettypeOfNew1.getFloat("MAXPOWER"));
					turbineDetails.setImprovedImpId(resultsettypeOfNew1.getString("IMPROVED_IMP_ID"));
					typeOfNewList2.add(turbineDetails);
				}
				if (!typeOfNewList2.isEmpty()) {
					typeOfList1.put("TYPE_OF_NEW_LIST", typeOfNewList2);
					quotationForm.getDropDownColumnvalues().setTypeOfNewList1(typeOfList1);
				}
			}
} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetTypesOfTurbine);
			UtilityMethods.closeResource(connection, callableStatement, resultSetTypesOfCondensor);
			UtilityMethods.closeResource(connection, callableStatement, resultSetScopeOfSupply);
			UtilityMethods.closeResource(connection, callableStatement, resultSetFrames);
			UtilityMethods.closeResource(connection, callableStatement, resultSetDepartments);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRegions);
			UtilityMethods.closeResource(connection, callableStatement, resultSetRoles);
			UtilityMethods.closeResource(connection, callableStatement, resultSetUserInfo);
			UtilityMethods.closeResource(connection, callableStatement, resultSetCustomerDetails);
			UtilityMethods.closeResource(connection, callableStatement, resultSetConsultantDetails);
			UtilityMethods.closeResource(connection, callableStatement, resultSetEnduserDetails);
			UtilityMethods.closeResource(connection, callableStatement, resultsetCategory);
			UtilityMethods.closeResource(connection, callableStatement, resultSetNewTypesOfTurbine);
			UtilityMethods.closeResource(connection, callableStatement, resultsetMaterial);
			UtilityMethods.closeResource(connection, callableStatement, resultsettypeOfFrameVarient);
			//

		}

		return quotationForm;
	}

	public QuotationForm getUserData(QuotationForm quotationForm, List<UserProfileDetails> userDetailsList) {
		// UserList
		Map<Integer, UserProfileDetails> userMap = new HashMap<>();
		List<UserProfileDetails> userList = new ArrayList<>();
		// UserList

		for (UserProfileDetails user : userDetailsList) {
			userMap.put(user.getUserId(), user);
		}
		for (Integer uniqUser : userMap.keySet()) {
			Map<Integer, String> region = new HashMap<>();
			Map<Integer, String> role = new HashMap<>();
			Map<Integer, String> roleCd = new HashMap<>();
			for (UserProfileDetails user : userDetailsList) {
				if (uniqUser == user.getUserId()) {
					region.put(user.getRegionId(), user.getRegion());
					role.put(user.getRoleId(), user.getRole());
					roleCd.put(user.getRoleId(), user.getGroupCd());
				}
			}
			List<String> regionVal = new ArrayList(region.values());
			List<String> roleVal = new ArrayList(role.values());
			List<String> roleGrpVal = new ArrayList(roleCd.values());

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
			userMap.get(uniqUser).setRolesCodeVal(roleGrpVal);
			userMap.get(uniqUser).setRegion(null);
			userMap.get(uniqUser).setRole(null);
		}
		for (Map.Entry userDetails : userMap.entrySet()) {

			userList.add((UserProfileDetails) userDetails.getValue());
		}
		quotationForm.setUserDetailsList(userList);
		return quotationForm;

	}
	@Override
	public QuotationForm saveAs(QuotationForm quotationForm) {

		quotationForm.setEditQuotFlow(true);
		CallableStatement callableStatement = null;
		Connection connection = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable CUSTOMER_DET = new SQLServerDataTable();
			CUSTOMER_DET = getcustObj(quotationForm);
			callableStatement = connection.prepareCall("{ call dbo.PROC_TURB_CLONE (?,?,?,?,?) }");
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getInQuotId());
			callableStatement.setInt(3, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getAssignedTo());
			((SQLServerPreparedStatement) callableStatement).setStructured(5, "dbo.CUSTOMER_DET", CUSTOMER_DET);
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info(quotationForm.getSaveBasicDetails().getInQuotId());
			logger.info(quotationForm.getSaveBasicDetails().getUserRoleId());
			logger.info( quotationForm.getSaveBasicDetails().getAssignedTo());
			callableStatement.execute();

			getQuotationData(callableStatement, quotationForm);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, e.toString());

			logger.error(ItoConstants.EXCEPTION + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement);
			UtilityMethods.closeResource(connection, callableStatement);
		}
		return quotationForm;
	}

	
//	@Override
//	public QuotationForm saveAs(QuotationForm quotationForm) {
//
//		
//		SQLServerPreparedStatement callableStatement = null;
//		Connection connection = null;
//		ResultSet resultSetQuot = null;
//		int resultOutParameterInt = -1;
//		String resultOutParameterString = null;
//		ResultSet resultSetMsg = null;
//
//		try {
//			clearMessageFrom(quotationForm.getSaveBasicDetails());
//
//			connection = jdbcTemplate.getDataSource().getConnection();
//			SQLServerDataTable CUSTOMER_DET = new SQLServerDataTable();
//			CUSTOMER_DET = getcustObj(quotationForm);
//			callableStatement = (SQLServerPreparedStatement) connection	.prepareStatement("{ call dbo.PROC_TURB_CLONE(?,?,?,?,?)}");
//
//			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getModifiedById());
//			logger.info("save as input start");
//			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
//			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getInQuotId());
//			logger.info(quotationForm.getSaveBasicDetails().getInQuotId());
//			callableStatement.setInt(3, quotationForm.getSaveBasicDetails().getUserRoleId());
//			logger.info(quotationForm.getSaveBasicDetails().getUserRoleId());
//			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getAssignedTo());
//			logger.info(quotationForm.getSaveBasicDetails().getAssignedTo());
//			callableStatement.setStructured(5, "dbo.CUSTOMER_DET", CUSTOMER_DET);
//		
//			callableStatement.execute();
//
//			resultSetMsg = callableStatement.getResultSet();
//
//			while (resultSetMsg.next()) {
//				resultOutParameterInt = resultSetMsg.getInt(1);
//				resultOutParameterString = resultSetMsg.getString(2);
//				quotationForm.getSaveBasicDetails().setSuccessCode(resultOutParameterInt);
//				quotationForm.getSaveBasicDetails().setSuccessMsg(resultOutParameterString);
//				quotationForm.getSaveBasicDetails().getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
//			}
//			
//			
//
//		} catch (Exception e) {
//			quotationForm.setSuccessCode(-1);
//			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
//			quotationForm.getMsgToUser().put(-1, e.toString());
//
//			logger.error(ItoConstants.EXCEPTION + e, e);
//		} finally {
//			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
//		}
//		return quotationForm;
//	}

	@Override
	public QuotationForm saveBasicDetails(QuotationForm quotationForm) {

		int frameIdWithPowerId = 0;

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetQuot = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			clearMessageFrom(quotationForm.getSaveBasicDetails());

			List<TurbineDetails> list = new ArrayList<>();
			list.addAll(quotationForm.getDropDownColumnvalues().getTypeOfNewList()
					.get(ItoConstants.FRAMES_WITH_POWER));

			for (TurbineDetails bean : list) {
				logger.info("new input starts");
				logger.info("save basic details frame Id");
				logger.info(quotationForm.getSaveBasicDetails().getFrameId());
				logger.info("bean frame Id");
				logger.info(bean.getFrameId());
				if (quotationForm.getSaveBasicDetails().getFrameId() == bean.getFrameId()
						&& quotationForm.getSaveBasicDetails().getFrameId() != 0) {
					logger.info(" savebasic details capacity");
					logger.info(quotationForm.getSaveBasicDetails().getCapacity());
					logger.info(" bean min power");
					logger.info(bean.getMinPower());
					logger.info("bean max power");
					logger.info(bean.getMaximumPower());
					if (quotationForm.getSaveBasicDetails().getCapacity() >= bean.getMinPower()
							&& quotationForm.getSaveBasicDetails().getCapacity() <= bean.getMaximumPower()) {
						logger.info(quotationForm.getSaveBasicDetails().getCapacity());

						logger.info(bean.getMinPower());
						logger.info(bean.getMaximumPower());
						logger.info("new input ends");
						frameIdWithPowerId = bean.getFramePowerId();
						logger.info("frame power id" + frameIdWithPowerId);
					}
				}
			}

			quotationForm.getSaveBasicDetails().setFramePowerId(frameIdWithPowerId);
			logger.info(frameIdWithPowerId);

			connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable CUSTOMER_DET = new SQLServerDataTable();
			CUSTOMER_DET = getcustObj(quotationForm);
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_TURB_CONFIG(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getFramePowerId());
			callableStatement.setFloat(2, quotationForm.getSaveBasicDetails().getCapacity());
			callableStatement.setInt(3, quotationForm.getSaveBasicDetails().getCondensingTypeId());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getBleedTypeId());
			//callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getOrientationTypeId());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getIsNewProject());
			callableStatement.setFloat(6, quotationForm.getSaveBasicDetails().getPercentageVariation());
			callableStatement.setStructured(7, "dbo.CUSTOMER_DET", CUSTOMER_DET);
			callableStatement.setInt(8, quotationForm.getSaveBasicDetails().getTypeOfOffer());
			callableStatement.setInt(9, quotationForm.getSaveBasicDetails().getTypeOfQuot());
			callableStatement.setInt(10, quotationForm.getSaveBasicDetails().getTypeOfVarient());
			callableStatement.setInt(11, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setInt(12, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.setString(13, quotationForm.getSaveBasicDetails().getEnquiryReference());
			callableStatement.setInt(14, quotationForm.getSaveBasicDetails().getImprovedImpulse());
			logger.info(" save basic details inputs starts ");
			logger.info(quotationForm.getSaveBasicDetails().getFramePowerId());
			logger.info(quotationForm.getSaveBasicDetails().getCapacity());
			logger.info(quotationForm.getSaveBasicDetails().getCondensingTypeId());
			logger.info(quotationForm.getSaveBasicDetails().getBleedTypeId());
			//logger.info(quotationForm.getSaveBasicDetails().getOrientationTypeId());
			logger.info(quotationForm.getSaveBasicDetails().getIsNewProject());
			logger.info(quotationForm.getSaveBasicDetails().getPercentageVariation());
			logger.info(quotationForm.getSaveBasicDetails().getTypeOfOffer());
			logger.info(quotationForm.getSaveBasicDetails().getTypeOfQuot());

			logger.info(quotationForm.getSaveBasicDetails().getTypeOfVarient());
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info(quotationForm.getSaveBasicDetails().getUserRoleId());
			logger.info(quotationForm.getSaveBasicDetails().getEnquiryReference());
			logger.info(" save basic details inputs ends");
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.getSaveBasicDetails().setSuccessCode(resultOutParameterInt);
				quotationForm.getSaveBasicDetails().setSuccessMsg(resultOutParameterString);
				quotationForm.getSaveBasicDetails().getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetQuot = callableStatement.getResultSet();
				while (resultSetQuot.next()) {
					quotationForm.getSaveBasicDetails().setQuotNumber(resultSetQuot.getString("QUOTAION_NUMBER"));
					quotationForm.getSaveBasicDetails().setQuotId(resultSetQuot.getInt("QUOTAION_ID"));
				}
			}
			quotationForm.setLoggedInUserId(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info("*****" + quotationForm.getSaveBasicDetails().getQuotId() + " ||"
					+ quotationForm.getSaveBasicDetails().getQuotNumber() + "****");

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, e.toString());

			logger.error(ItoConstants.EXCEPTION + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
		}
		return quotationForm;
	}

	// ===========create scope of supply==========
	@Override
	public QuotationForm saveScopeOfSupplyDetails(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultsetScope = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			List<String> scopeOfSupplyStr = new ArrayList<>();
			for (Integer myInt : quotationForm.getSaveBasicDetails().getScopeOfSupply()) {
				scopeOfSupplyStr.add(String.valueOf(myInt));
			}
			Set<String> set = new HashSet<String>(scopeOfSupplyStr);
			List<String> scopeOfSupplyList = new ArrayList<String>(set);

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_SCOPE_OF_SUPPLY (?,?,?,?,?)}");
			logger.info("Start of Save Scope");
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info(scopeOfSupplyList);
			logger.info(quotationForm.getSaveBasicDetails().getUserRoleId());
			logger.info(quotationForm.getSaveBasicDetails().getEditFlagNew());
			logger.info("End of Save Scope");
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getQuotId());//34
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());//5
			callableStatement.setString(3, String.join(",", scopeOfSupplyList));//[44, 55, 45, 46, 47, 48, 38, 49, 39, 50, 51, 41, 52, 42, 53, 43, 54]
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getUserRoleId());//1
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getEditFlagNew());//1
			
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);

			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultsetScope);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm saveScopeOfSupplyDetailsNew(Integer quotId) {
		QuotationForm quotationForm = new QuotationForm();
		try {

			String query = "SELECT SCOP_ID,QUOT_ID,SS_ID,(SELECT CAT_DET_CD FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'TB' AND CAT_CD = 'SS' ) AND IS_ACTIVE = 1 AND CAT_DET_ID = SS_ID) AS SCOPE_CD, (SELECT CAT_DET_DESC FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'TB' AND CAT_CD = 'SS' ) AND IS_ACTIVE = 1 AND CAT_DET_ID = SS_ID) AS SCOPE_OF_SUPPLY, A.CREAT_DT,A.MOD_DT, (SELECT NAME FROM USERS B WHERE B.USER_ID = A.CREAT_BY ) AS CREAT_BY, (SELECT NAME FROM USERS B WHERE B.USER_ID = A.MOD_BY ) AS MOD_BY, A.CREAT_BY AS CREAT_BY_ID,A.MOD_BY  AS MOD_BY_ID FROM TURB_CONFIG_SCOPE A WHERE QUOT_ID = '"
					+ quotId + "';";

			List<QuotationForm> scopeList = jdbcTemplate.query(query, new RowMapper<QuotationForm>() {
				public QuotationForm mapRow(ResultSet rs, int rowNum) throws SQLException {
					QuotationForm bean = new QuotationForm();

					bean.setScopeId(rs.getInt("SCOP_ID"));
					bean.setQuotId(rs.getInt(ItoConstants.QUOT_ID));
					bean.setSsId(rs.getInt(ItoConstants.SS_ID));
					bean.setScopeName(rs.getString(ItoConstants.SCOPE_OF_SUPPLY));
					bean.setScopeCode(rs.getString(ItoConstants.SCOPE_CD));
					bean.setCreatedDate(rs.getString(ItoConstants.CREAT_DT));
					bean.setModifiedDate(rs.getString(ItoConstants.MOD_DT));
					bean.setCreatedBy(rs.getString(ItoConstants.CREAT_BY));
					bean.setModifiedBy(rs.getString(ItoConstants.MOD_BY));
					bean.setCreatedById(rs.getInt(ItoConstants.CREAT_BY_ID));
					bean.setModifiedById(rs.getInt(ItoConstants.MOD_BY_ID));

					return bean;
				}
			});

			quotationForm.setScopeList(scopeList);

			return quotationForm;

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return quotationForm;
		}

	}

	@Override
	public QuotationForm getF2fCache(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetF2fCache = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		Map<String, List<DBOBean>> f2fCacheType = new HashMap<>();

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_CACHE(?)}");
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);

			}

			List<DBOBean> f2fCacheList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetF2fCache = callableStatement.getResultSet();

				while (resultSetF2fCache.next()) {

					DBOBean bean = new DBOBean();
					bean.setId(resultSetF2fCache.getInt("ID"));
					bean.setGroupCode(resultSetF2fCache.getString("GRP_CD"));
					bean.setItemNm(resultSetF2fCache.getString("ITEM_NM"));
					bean.setCategoryValCode(resultSetF2fCache.getString("CAT_VAL_CD"));
					bean.setDefaultVal(resultSetF2fCache.getInt("DEFLT_FLG") == 1 ? true : false);

					f2fCacheList.add(bean);
				}

				if (!f2fCacheList.isEmpty()) {
					f2fCacheType.put("F2fCacheType", f2fCacheList);
					quotationForm.getDropDownColumnvalues().setF2fCacheList(f2fCacheType);
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2fCache);
		}
		return quotationForm;
	}
	// ====================

	// ====================

	@Override
	public QuotationForm getSelectedQuestionsPage(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		List<QuestionsEntity> questionsEntityList = new ArrayList<>();
		List<QuestionsEntity> selectedQustList = new ArrayList<>();
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuestions = null;
		ResultSet resultSetQustSelected = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_QUST (?,?) }");

			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getFramePowerId());

			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0) {

				if (callableStatement.getMoreResults()) {
					resultSetQuestions = callableStatement.getResultSet();
					while (resultSetQuestions.next()) {
						QuestionsEntity bean = new QuestionsEntity();

						bean.setQuestionId(resultSetQuestions.getInt(ItoConstants.QUST_ID));
						bean.setQuestionDesc(resultSetQuestions.getString(ItoConstants.QUST_DESC));
						bean.setAnswerId(resultSetQuestions.getInt(ItoConstants.ANS_ID));
						bean.setAnswerCd(resultSetQuestions.getString(ItoConstants.ANS_CD));
						bean.setAnswerDesc(resultSetQuestions.getString("DEFAULT_ANSWER"));
						bean.setQuestionAnswerId(resultSetQuestions.getInt(ItoConstants.QUST_ANS_ID));
						bean.setFrameName(resultSetQuestions.getString(ItoConstants.FRAME_NAME));
						if (resultSetQuestions.getString("DEFLT_VALUE").equalsIgnoreCase("TRUE")) {
							bean.setDefaultVal(true);
						} else {
							bean.setDefaultVal(false);
						}
						questionsEntityList.add(bean);
					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetQustSelected = callableStatement.getResultSet();

					while (resultSetQustSelected.next()) {
						QuestionsEntity qustBean = new QuestionsEntity();
						qustBean.setQuestionId(resultSetQustSelected.getInt(ItoConstants.QUST_ID));
						qustBean.setQuestionDesc(resultSetQustSelected.getString("QUST_NM"));
						qustBean.setQuestionAnswerId(resultSetQustSelected.getInt(ItoConstants.ANS_ID));
						qustBean.setAnswerDesc(resultSetQustSelected.getString("ANS_NM"));
						qustBean.setAnswerCd(resultSetQustSelected.getString(ItoConstants.ANS_CD));
						selectedQustList.add(qustBean);
					}

					for (int j = 0; j < selectedQustList.size(); j++) {
						for (int i = 0; i < questionsEntityList.size(); i++) {
							if (questionsEntityList.get(i).getQuestionId() == selectedQustList.get(j).getQuestionId()
									&& questionsEntityList.get(i).getQuestionAnswerId() == selectedQustList.get(j)
											.getQuestionAnswerId()) {
								questionsEntityList.get(i).setDefaultVal(true);
							}
							if (questionsEntityList.get(i).getQuestionId() == selectedQustList.get(j).getQuestionId()
									&& questionsEntityList.get(i).getQuestionAnswerId() != selectedQustList.get(j)
											.getQuestionAnswerId()) {
								questionsEntityList.get(i).setDefaultVal(false);
							}
						}
					}

					quotationForm.setSelectedQuestionAnswerSet(questionsEntityList);
				}
			}

			if (!questionsEntityList.isEmpty()) {
				createQuestionsJson(questionsEntityList, quotationForm);
				// Added for UI
				List<SaveQuesDetails> tempList = new ArrayList<>();
				for (int i = 1; i <= 10; i++) {
					SaveQuesDetails saveQues = new SaveQuesDetails();
					tempList.add(saveQues);
				}
				quotationForm.setSaveQuesDetailsList(tempList);

			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuestions);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQustSelected);

		}
		return quotationForm;
	}

	@Override
	public QuotationForm getQuestionsPage(QuotationForm quotationForm) {

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
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getFramePowerId()); // Frame

			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			logger.info("Frame Power Id");
			logger.info(quotationForm.getSaveBasicDetails().getFramePowerId());
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetQuestions = callableStatement.getResultSet();
				while (resultSetQuestions.next()) {
					QuestionsEntity bean = new QuestionsEntity();

					bean.setQuestionId(resultSetQuestions.getInt(ItoConstants.QUST_ID));
					bean.setQuestionDesc(resultSetQuestions.getString(ItoConstants.QUST_DESC));
					bean.setQuestionCode(resultSetQuestions.getString("QUST_CD"));
					bean.setAnswerId(resultSetQuestions.getInt(ItoConstants.ANS_ID));
					bean.setAnswerCd(resultSetQuestions.getString(ItoConstants.ANS_CD));
					bean.setAnswerDesc(resultSetQuestions.getString("DEFAULT_ANSWER"));
					bean.setQuestionAnswerId(resultSetQuestions.getInt(ItoConstants.QUST_ANS_ID));
					bean.setFrameName(resultSetQuestions.getString(ItoConstants.FRAME_NAME));
					logger.info(resultSetQuestions.getInt(ItoConstants.QUST_ID));
					logger.info(resultSetQuestions.getString(ItoConstants.QUST_DESC));
					logger.info(resultSetQuestions.getString("QUST_CD"));
					logger.info(resultSetQuestions.getInt(ItoConstants.ANS_ID));
					logger.info(resultSetQuestions.getString(ItoConstants.ANS_CD));
					logger.info(resultSetQuestions.getString("DEFAULT_ANSWER"));
					logger.info(resultSetQuestions.getInt(ItoConstants.QUST_ANS_ID));
					logger.info(resultSetQuestions.getString(ItoConstants.FRAME_NAME));
					
					if (resultSetQuestions.getString("DEFLT_VALUE").equalsIgnoreCase("TRUE")) {
						bean.setDefaultVal(true);
						bean.setAnswerDesc(bean.getAnswerDesc() + " (TTL STD)");
					} else {
						bean.setDefaultVal(false);
					}
					questionsEntityList.add(bean);
				}
			}

			quotationForm.setQuestionsEntityList(questionsEntityList);
			if (!questionsEntityList.isEmpty()) {
				createQuestionsJson(questionsEntityList, quotationForm);
				// Added for UI
				List<SaveQuesDetails> tempList = new ArrayList<>();
				for (int i = 1; i <= 10; i++) {
					SaveQuesDetails saveQues = new SaveQuesDetails();
					tempList.add(saveQues);
				}
				quotationForm.setSaveQuesDetailsList(tempList);

			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuestions);

		}
		return quotationForm;
	}

	// @Override
	// public QuotationForm getAddOnPrice(QuotationForm quotationForm) {
	//
	// CallableStatement callableStatement = null;
	// Connection connection = null;
	// ResultSet resultSetAddon = null;
	//
	// try {
	// clearMessageFrom(quotationForm.getSaveBasicDetails());
	// connection = jdbcTemplate.getDataSource().getConnection();
	// callableStatement = connection.prepareCall("{ call
	// dbo.[PROC_GET_ADDON_PRICE] (?) }");
	//
	// callableStatement.setInt(ItoConstants.FRM_POW_ID,
	// quotationForm.getSaveBasicDetails().getFramePowerId());
	//
	// callableStatement.execute();
	// resultSetAddon = callableStatement.getResultSet();
	// List<AddOnComponent> addOnComponentsListTemp = new ArrayList<>();
	// while (resultSetAddon.next()) {
	// AddOnComponent addOn = new AddOnComponent();
	// addOn.setCompoId(resultSetAddon.getInt(ItoConstants.COMP_ID));
	// addOn.setAddOnCompoId(resultSetAddon.getInt(ItoConstants.ADD_ON_COMP_ID));
	// addOn.setAddOnCompoName(resultSetAddon.getString("ADDONNAME"));
	// addOn.setAddOnCompo_cd(resultSetAddon.getString("ADDON"));
	// addOn.setSubtype1(resultSetAddon.getString("SUB_TYPE1_NM"));
	// addOn.setSubtype2(resultSetAddon.getString("SUB_TYPE2_NM"));
	// addOn.setMake(resultSetAddon.getString("MAKE_NM"));
	// addOn.setSubtype1Code(resultSetAddon.getString(ItoConstants.SUB_TYPE1));
	// addOn.setSubtype2Code(resultSetAddon.getString(ItoConstants.SUB_TYPE2));
	// addOn.setMakeCode(resultSetAddon.getString("MAKE"));
	// addOn.setQuantity(resultSetAddon.getInt("QTY"));
	// addOn.setQuantityName(resultSetAddon.getString("QTY_NM"));
	// addOn.setExcelCost(Math.round(resultSetAddon.getFloat(ItoConstants.COST)));
	// addOn.setSubtype1Id(resultSetAddon.getInt("SUB_TYPE1_ID"));
	// addOn.setSubtype2Id(resultSetAddon.getInt("SUB_TYPE2_ID"));
	// addOn.setMakeId(resultSetAddon.getInt("MAKE_ID"));
	// addOn.setSubtype1Id(resultSetAddon.getInt("SUB_TYPE1_ID"));
	// addOn.setSubtype2Id(resultSetAddon.getInt("SUB_TYPE2_ID"));
	// addOn.setMakeId(resultSetAddon.getInt("MAKE_ID"));
	// addOn.setDefaultCostFlag(resultSetAddon.getInt("F2F_DEFULT_FLG") == 1 ?
	// true : false);
	//
	// addOnComponentsListTemp.add(addOn);
	// }
	// if (!addOnComponentsListTemp.isEmpty()) {
	// quotationForm.setAddOnComponentsList(addOnComponentsListTemp);
	// }
	//
	// createAddOnJson(addOnComponentsListTemp, quotationForm);
	//
	// } catch (Exception e) {
	// quotationForm.setSuccessCode(-1);
	// quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
	// quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
	// logger.error(ItoConstants.EXCEPTION, e);
	// } finally {
	// UtilityMethods.closeResource(connection, callableStatement,
	// resultSetAddon);
	// }
	// return quotationForm;
	// }

	private void createAddOnJson(List<AddOnComponent> addOnComponentsList, QuotationForm quotationForm) {

		// unqiue Drop down Name
		Map<String, String> dropdownNameMap = new HashMap<>();
		for (AddOnComponent bean : addOnComponentsList) {
			dropdownNameMap.put(bean.getAddOnCompo_cd(), bean.getAddOnCompoName());
		}

		List<QuestionsBean> addOnBeanList = new ArrayList<>();
		for (Entry<String, String> dropDownType : dropdownNameMap.entrySet()) {

			QuestionsBean questionsBean = new QuestionsBean();
			questionsBean.getDropDownType().setCode(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());
			quotationForm.getQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<>());

			addOnBeanList.add(questionsBean);
		}

		quotationForm.setQuestionsBean(addOnBeanList);

		for (QuestionsBean dropDownType : quotationForm.getQuestionsBean()) {
			List<AddOnComponent> selectBoxList = new ArrayList<>();
			Set<String> subType1 = new HashSet<>();
			Set<String> subType2 = new HashSet<>();
			Set<String> make = new HashSet<>();
			Set<Float> quantity = new HashSet<>();
			Set<String> quantityName = new HashSet<>();
			for (AddOnComponent bean : addOnComponentsList) {
				if (dropDownType.getDropDownType().getCode().equalsIgnoreCase(bean.getAddOnCompo_cd())) {
					AddOnComponent addOn = new AddOnComponent();
					addOn.setCompoId(bean.getCompoId());
					addOn.setAddOnCompoId(bean.getAddOnCompoId());
					addOn.setAddOnCompoName(bean.getAddOnCompoName());
					addOn.setNewCompName(bean.getNewCompName());
					addOn.setAddOnCompo_cd(bean.getAddOnCompo_cd());

					addOn.setSubtype1(bean.getSubtype1());
					if (bean.getSubtype1() != null) {
						subType1.add(bean.getSubtype1());
					}

					addOn.setSubtype2(bean.getSubtype2());
					if (bean.getSubtype2() != null) {
						subType2.add(bean.getSubtype2());
					}

					addOn.setMake(bean.getMake());
					if (bean.getMake() != null) {
						make.add(bean.getMake());
					}
					addOn.setQuantity(bean.getQuantity());
					addOn.setQuantityName(bean.getQuantityName());
					addOn.setNewCompQty(bean.getNewCompQty());
					if (bean.getQuantity() != 0) {
						quantity.add(bean.getQuantity());
						quantityName.add(bean.getQuantityName());
					}
					addOn.setExcelCost(bean.getExcelCost());
					addOn.setSelectedCost(bean.getSelectedCost());
					addOn.setNewCompPrice(bean.getNewCompPrice());
					addOn.setSelectedCostFlag(bean.getSelectedCostFlag());

					addOn.setOverwrittenRemarks(bean.getOverwrittenRemarks());
					addOn.setNewCompRemark(bean.getNewCompRemark());
					addOn.setRemarks(bean.getRemarks());

					addOn.setActive(bean.isActive());

					addOn.setSubtype1Id(bean.getSubtype1Id());
					addOn.setSubtype2Id(bean.getSubtype2Id());
					addOn.setMakeId(bean.getMakeId());

					addOn.getSubType1List().addAll(subType1);
					addOn.getSubType2List().addAll(subType2);
					addOn.getMakeList().addAll(make);
					addOn.getQuantityList().addAll(quantity);
					addOn.getQtyNameList().addAll(quantityName);

					selectBoxList.add(addOn);
				}
				dropDownType.setAddOnValueList(selectBoxList);
			}
		}

	}

	// @Override
	// public QuotationForm saveAddOnPrice(QuotationForm quotationForm) {
	//
	// SQLServerPreparedStatement callableStatement = null;
	// Connection connection = null;
	// int resultOutParameterInt = -1;
	// String resultOutParameterString = null;
	// ResultSet resultSetMsg = null;
	// ResultSet resultSetaddOns = null;
	//
	// try {
	// connection = jdbcTemplate.getDataSource().getConnection();
	//
	// SQLServerDataTable ADD_ON = new SQLServerDataTable();
	// ADD_ON.addColumnMetadata(ItoConstants.ADD_ON_COMP_ID,
	// java.sql.Types.NUMERIC);
	// ADD_ON.addColumnMetadata("ADD_ON_COMP_NAME", java.sql.Types.VARCHAR);
	// ADD_ON.addColumnMetadata(ItoConstants.SUB_TYPE1, java.sql.Types.NUMERIC);
	// ADD_ON.addColumnMetadata(ItoConstants.SUB_TYPE2, java.sql.Types.NUMERIC);
	// ADD_ON.addColumnMetadata("MAKE", java.sql.Types.NUMERIC);
	// ADD_ON.addColumnMetadata("QTY", java.sql.Types.NUMERIC);
	// ADD_ON.addColumnMetadata("COST_EX", java.sql.Types.DOUBLE);
	// ADD_ON.addColumnMetadata("COST_SAP", java.sql.Types.DOUBLE);
	// ADD_ON.addColumnMetadata("COMMENTS", java.sql.Types.VARCHAR);
	//
	// for (AddOnComponent bean : quotationForm.getSubmittedAddOnList()) {
	//
	// ADD_ON.addRow(bean.getAddOnCompoId(), bean.getName(),
	// bean.getSubtype1Id(), bean.getSubtype2Id(),
	// bean.getMakeId(), bean.getQuantity(), bean.getExcelCost(),
	// bean.getSapCost(),
	// bean.getNewCompRemark());
	//
	// }
	//
	// callableStatement = (SQLServerPreparedStatement) connection
	// .prepareStatement("{ call dbo.PROC_SAVE_ADDON_PRICE (?,?,?,?,?)}");
	// callableStatement.setStructured(1, "dbo.ADD_ON ", ADD_ON);
	// callableStatement.setInt(2,
	// quotationForm.getSaveBasicDetails().getQuotId());
	// callableStatement.setInt(3,
	// quotationForm.getAddOnComponent().getSelectedCostFlag());
	// callableStatement.setFloat(4,
	// quotationForm.getAddOnComponent().getSelectedCost());
	// callableStatement.setInt(5,
	// quotationForm.getSaveBasicDetails().getModifiedById());
	// callableStatement.execute();
	//
	// resultSetMsg = callableStatement.getResultSet();
	//
	// while (resultSetMsg.next()) {
	// resultOutParameterInt = resultSetMsg.getInt(1);
	// resultOutParameterString = resultSetMsg.getString(2);
	// quotationForm.getMsgToUser().put(resultOutParameterInt,
	// resultOutParameterString);
	// }
	//
	// while (resultSetMsg.next()) {
	// resultOutParameterInt = resultSetMsg.getInt(1);
	// resultOutParameterString = resultSetMsg.getString(2);
	// quotationForm.getMsgToUser().put(resultOutParameterInt,
	// resultOutParameterString);
	// }
	//
	// while (resultSetMsg.next()) {
	// resultOutParameterInt = resultSetMsg.getInt(1);
	// resultOutParameterString = resultSetMsg.getString(2);
	// quotationForm.getMsgToUser().put(resultOutParameterInt,
	// resultOutParameterString);
	// }
	//
	// List<AddOnComponent> addOnlist = new ArrayList<>();
	// if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
	// resultSetaddOns = callableStatement.getResultSet();
	// while (resultSetaddOns.next()) {
	// AddOnComponent bean = new AddOnComponent();
	// bean.setCompoId(resultSetaddOns.getInt(ItoConstants.COMP_DET_ID));
	// bean.setAddOnCompoMastId(resultSetaddOns.getInt(ItoConstants.COMP_MAST_ID));
	// bean.setAddOnCompoId(resultSetaddOns.getInt(ItoConstants.ADD_ON_COMP_ID));
	// bean.setAddOnCompo_cd(resultSetaddOns.getString(ItoConstants.ADD_ON_COMP));
	// bean.setAddOnCompoName(resultSetaddOns.getString("ADD_ON_COMP_NM"));
	// bean.setSubtype1Id(resultSetaddOns.getInt(ItoConstants.SUB_TYPE1));
	// bean.setSubtype1(resultSetaddOns.getString("SUB_TYPE1_NAME"));
	// bean.setSubtype1Code(resultSetaddOns.getString("SUB_TYPE1_CD"));
	// bean.setSubtype2Id(resultSetaddOns.getInt(ItoConstants.SUB_TYPE2));
	// bean.setSubtype2(resultSetaddOns.getString("SUB_TYPE2_NAME"));
	// bean.setSubtype2Code(resultSetaddOns.getString("SUB_TYPE2_CD"));
	// bean.setMakeId(resultSetaddOns.getInt("MAKE"));
	// bean.setMake(resultSetaddOns.getString("MAKE_NAME"));
	// bean.setMakeCode(resultSetaddOns.getString("MAKE_CD"));
	// bean.setQuantity(resultSetaddOns.getFloat("QTY"));
	// bean.setQuantityName(resultSetaddOns.getString("QTY_NM"));
	// bean.setExcelCost(Math.round(resultSetaddOns.getFloat(ItoConstants.COST)));
	// bean.setAddOnTotal(Math.round(resultSetaddOns.getFloat(ItoConstants.TOTAL_COST)));
	// bean.setSelectedCost(Math.round(resultSetaddOns.getFloat(ItoConstants.COST_ME)));
	// bean.setSelectedCostFlag(resultSetaddOns.getInt(ItoConstants.COST_ME_FLG));
	// bean.setNewCompRemark(resultSetaddOns.getString("COMMENTS"));
	// }
	// }
	//
	// quotationForm.setSubmittedAddOnList(addOnlist);
	//
	// } catch (Exception e) {
	// quotationForm.setSuccessCode(-1);
	// quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
	//
	// quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
	// logger.error(ItoConstants.EXCEPTION, e);
	// } finally {
	// UtilityMethods.closeResource(connection, callableStatement,
	// resultSetMsg);
	// UtilityMethods.closeResource(connection, callableStatement,
	// resultSetaddOns);
	// }
	// return quotationForm;
	// }

	// Added by Kavya -- Save questionaries End

	private void createQuestionsJson(List<QuestionsEntity> questionsEntityList, QuotationForm quotationForm) {

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
			quotationForm.getQuestionsBean().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<>());

			questionsBeanList.add(questionsBean);
		}

		quotationForm.setQuestionsBean(questionsBeanList);

		for (QuestionsBean dropDownType : quotationForm.getQuestionsBean()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
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
					selectBoxList.add(box);
				}
				dropDownType.setDropDownValueList(selectBoxList);
			}
		}

		quotationForm.setSelectedQuestionAnswerSet(questionsEntityList);
	}

	// saveCProject
	@Override
	public QuotationForm saveCProject(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuot = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_VARIANT_CD (?,?,?,?,?) }");

			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setString(2, quotationForm.getSaveBasicDetails().getcNewNumber());
			callableStatement.setString(3, quotationForm.getSaveBasicDetails().getSelectedCProjectKey());
			if (quotationForm.getSaveBasicDetails().getzCount().isEmpty()) {
				callableStatement.setInt(4, 0);
			} else {
				callableStatement.setInt(4, 1);
			}
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getModifiedById());

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
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getF2FTreeStructure(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetF2F = null;
		List<F2FForm> f2FDataList = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_DATA (?,?) }");

			callableStatement.setString(1, quotationForm.getSaveBasicDetails().getcNewNumber());
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
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetF2F = callableStatement.getResultSet();
				f2FDataList = new ArrayList<>();
				while (resultSetF2F.next()) {
					F2FForm f2FForm = new F2FForm();
					f2FForm.setId(resultSetF2F.getInt("ZCOUNT"));
					f2FForm.setcNum(resultSetF2F.getString("CPROJ_DB"));
					f2FForm.setWbsElementNo(resultSetF2F.getString("WBS_ELE_DB"));
					f2FForm.setParentMaterialCode(resultSetF2F.getString("MATERIAL_H"));
					f2FForm.setChildMaterialCode(resultSetF2F.getString("MATERIAL_C"));
					f2FForm.setMaterialDesc(resultSetF2F.getString("ZDESC"));
					f2FForm.setChildQuantity(resultSetF2F.getString("QTY_C"));
					f2FForm.setTurbineInstrumentCost(Math.round(resultSetF2F.getFloat(ItoConstants.TURBINE_MTRL)));
					f2FForm.setUnitMeasure(resultSetF2F.getString("UOM"));
					f2FForm.setLevelNo(resultSetF2F.getString("ZLEVEL"));
					f2FForm.setRawMaterialCost(Math.round(resultSetF2F.getFloat("MAT_COST")));
					f2FForm.setShopCoverCost(Math.round(resultSetF2F.getFloat(ItoConstants.SHOP_CONV)));
					f2FForm.setOverheadCost(Math.round(resultSetF2F.getFloat(ItoConstants.OVERHEAD)));
					f2FForm.setSubContrCost(Math.round(resultSetF2F.getFloat(ItoConstants.SUB_CONTRACT)));
					f2FForm.setSemiFinished(Math.round(resultSetF2F.getFloat("SEMI_FINISHED")));
					f2FForm.setTotal(Math.round(resultSetF2F.getFloat(ItoConstants.TOTAL)));
					f2FForm.setMisl(resultSetF2F.getString("MISL"));
					f2FForm.setUbo(Math.round(resultSetF2F.getFloat("UBO")));
					f2FForm.setProdOrder(resultSetF2F.getString(ItoConstants.PROD_ORD));
					f2FForm.setPurchaseOrder(resultSetF2F.getString("PURCH_ORD"));
					f2FForm.setParentQuantity(resultSetF2F.getString("QTY_P"));

					f2FDataList.add(f2FForm);
					quotationForm.setF2FDataList(f2FDataList);
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2F);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm saveQuesDetails(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetQuestions = null;
		ResultSet resultSetCProjects = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			int scopeOfSupply = 0;
			for (ScopeOfSupply scopes : quotationForm.getSaveBasicDetails().getScopeOfSupplyList()) {
				if (scopes.getScopeCode().equalsIgnoreCase("F2F")) {
					scopeOfSupply = scopes.getSsId();
				}
			}

			SQLServerDataTable TURB_CONFIG = new SQLServerDataTable();
			TURB_CONFIG.addColumnMetadata(ItoConstants.QUOT_ID, java.sql.Types.NUMERIC);
			TURB_CONFIG.addColumnMetadata(ItoConstants.SS_ID, java.sql.Types.NUMERIC);
			TURB_CONFIG.addColumnMetadata(ItoConstants.QUST_ID, java.sql.Types.NUMERIC);
			TURB_CONFIG.addColumnMetadata(ItoConstants.QUST_ANS_ID, java.sql.Types.NUMERIC);

			for (SaveQuesDetails b : quotationForm.getSaveQuesDetailsList()) {
				TURB_CONFIG.addRow(quotationForm.getSaveBasicDetails().getQuotId(), scopeOfSupply, b.getQuesTypeId(),
						b.getQuesAnswerkey());
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_F2F_QUST (?,?)}");
			callableStatement.setStructured(1, "dbo.TURB_CONFIG", TURB_CONFIG);
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			Map<String, String> cProjectList = new HashMap<>();
			List<CProjectList> cProjectVariantList = new ArrayList<>();
			List<CProjectList> unmatchedCProjectVariantList = new ArrayList<>();

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetQuestions = callableStatement.getResultSet();
					while (resultSetQuestions.next()) {
						CProjectList bean = new CProjectList();
						bean.setId(resultSetQuestions.getInt("VARIANT_ID"));
						bean.setcNum(resultSetQuestions.getString(ItoConstants.C_NUM));
						bean.setFrameName(resultSetQuestions.getString(ItoConstants.FRAME_NAME));
						bean.setVariantCode(resultSetQuestions.getString("VARIANT_CODE"));
						bean.setCustName(resultSetQuestions.getString(ItoConstants.CUST_NAME));
						bean.setcProjectDate(resultSetQuestions.getString("C_PROJ_DT"));
						// bean.setDefaultFlag(resultSetQuestions.getInt("FLG")==1
						// ?true:false);

						cProjectVariantList.add(bean);
					}
					quotationForm.setMatchedCProjectList(cProjectVariantList);

					for (CProjectList bean : quotationForm.getMatchedCProjectList()) {
						if (bean.isDefaultFlag()) {
							quotationForm.getSaveBasicDetails().setLatestCNum(bean.getcNum());
							quotationForm.getSaveBasicDetails().setLatestCNumFlag(true);
							break;
						}
					}

					quotationForm.getSaveBasicDetails().setcProjectList(cProjectList);
					// quotationForm = getOneLineBom(quotationForm);

					quotationForm.getSaveBasicDetails().setLatestCNumFlag(false);
					quotationForm.setLatestCProjectData(quotationForm.getOneLineBom());
				}
			} else if (resultOutParameterInt == 2) {
				if (callableStatement.getMoreResults()) {
					resultSetCProjects = callableStatement.getResultSet();
					while (resultSetCProjects.next()) {
						CProjectList bean = new CProjectList();
						bean.setId(resultSetCProjects.getInt("VARIANT_ID"));
						bean.setcNum(resultSetCProjects.getString(ItoConstants.C_NUM));
						bean.setFrameName(resultSetCProjects.getString(ItoConstants.FRAME_NAME));
						bean.setVariantCode(resultSetCProjects.getString("VARIANT_CODE"));
						bean.setCustName(resultSetCProjects.getString(ItoConstants.CUST_NAME));
						bean.setcProjectDate(resultSetCProjects.getString("C_PROJ_DT"));
						// bean.setDefaultFlag(resultSetCProjects.getInt("FLG")==1
						// ?true:false);

						unmatchedCProjectVariantList.add(bean);
					}
				}
				quotationForm.setcProjectList(unmatchedCProjectVariantList);
				for (CProjectList bean : quotationForm.getcProjectList()) {
					if (bean.isDefaultFlag()) {
						quotationForm.getSaveBasicDetails().setLatestCNum(bean.getcNum());
						quotationForm.getSaveBasicDetails().setLatestCNumFlag(true);
						break;
					}
				}
			}

			quotationForm.getSaveBasicDetails().setLatestCNumFlag(false);
			quotationForm.setLatestCProjectData(quotationForm.getOneLineBom());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuestions);
		}
		return quotationForm;
	}

	public SQLServerDataTable getcustObj(QuotationForm quotationForm) {
		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable CUSTOMER_DET = new SQLServerDataTable();
			CUSTOMER_DET.addColumnMetadata(ItoConstants.OPP_SEQ_NO, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("REGION_NAME", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.STATUS, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.CUST_NAME, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.OPP_NAME, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("OPP_CONTACT_NAME", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("OPP_CONTACT_EMAIL", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("OPP_CONTACT_PHONE", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("OPP_CONTACT_ADDRESS", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("OPP_CONTACT_STATE_NAME", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.CUST_TYP, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.CUST_CONT_PERSON, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.CUST_CONT_NO, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.EMAIL, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.ADDRESS, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.PIN_CD, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("STATE_NAME", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.FIRM_NAME, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.CONST_NAME, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.CONST_CONT_NO, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.CONST_EMAIL, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.CONST_ADDRESS, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("IS_END_USER_AVAILABLE", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("END_USER_CUST_TYPE", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.END_USER_NAME, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.END_USER_CONT_NO, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.END_USER_EMAIL, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.END_USER_ADDRESS, java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata("END_USER_STATE_NAME", java.sql.Types.VARCHAR);
			CUSTOMER_DET.addColumnMetadata(ItoConstants.SFDC_LINK, java.sql.Types.VARCHAR);

			CUSTOMER_DET.addRow(

					quotationForm.getSaveBasicDetails().getOpportunitySeqNum(),
					quotationForm.getSaveBasicDetails().getRegionCode(),
					null, // quotationForm.getCustomerDetailsForm().getCustomerStatus(),
					quotationForm.getCustomerDetailsForm().getCustName(),
					quotationForm.getCustomerDetailsForm().getOppName(),
					quotationForm.getCustomerDetailsForm().getOppContactName(),
					quotationForm.getCustomerDetailsForm().getOppContactEmail(),
					quotationForm.getCustomerDetailsForm().getOppContactPhone(),
					quotationForm.getCustomerDetailsForm().getOppContactAddress(),
					quotationForm.getCustomerDetailsForm().getOppContactStateName(),
					quotationForm.getCustomerDetailsForm().getCustType(),
					null, // quotationForm.getCustomerDetailsForm().getContactPersonName(),
					null, // quotationForm.getCustomerDetailsForm().getContactNumber(),
					null, // quotationForm.getCustomerDetailsForm().getEmailId(),
					null, // quotationForm.getCustomerDetailsForm().getAddress(),
					null, // quotationForm.getCustomerDetailsForm().getPincode(),
					null, // quotationForm.getCustomerDetailsForm().getStateName(),
					null, // quotationForm.getCustomerDetailsForm().getFirmName(),
					null, // quotationForm.getCustomerDetailsForm().getConsultantName(),
					null, // quotationForm.getCustomerDetailsForm().getConsultantContactNumber(),
					null, // quotationForm.getCustomerDetailsForm().getConsultantEmailId(),
					null, // quotationForm.getCustomerDetailsForm().getConsultantAddress(),
					quotationForm.getCustomerDetailsForm().getIsEndUserAvailable(),
					quotationForm.getCustomerDetailsForm().getEndUserCustType(),
					quotationForm.getCustomerDetailsForm().getEndUserName(),
					null, // quotationForm.getCustomerDetailsForm().getEndUserContactNumber(),
					null, // quotationForm.getCustomerDetailsForm().getEndUserEmailId(),
					null, // quotationForm.getCustomerDetailsForm().getEndUserAddress(),
					quotationForm.getCustomerDetailsForm().getEndUserStateName(), 
					null);// quotationForm.getCustomerDetailsForm().getSfdcLink());

			logger.info("cust object start");
			logger.info(quotationForm.getSaveBasicDetails().getOpportunitySeqNum());
			logger.info(quotationForm.getSaveBasicDetails().getRegionCode());
//			null, // quotationForm.getCustomerDetailsForm().getCustomerStatus(),
			logger.info(quotationForm.getCustomerDetailsForm().getCustName());
					logger.info(quotationForm.getCustomerDetailsForm().getOppName());
							logger.info(quotationForm.getCustomerDetailsForm().getOppContactName());
									logger.info(quotationForm.getCustomerDetailsForm().getOppContactEmail());
											logger.info(quotationForm.getCustomerDetailsForm().getOppContactPhone());
													logger.info(quotationForm.getCustomerDetailsForm().getOppContactAddress());
															logger.info(quotationForm.getCustomerDetailsForm().getOppContactStateName());
																	logger.info(quotationForm.getCustomerDetailsForm().getCustType());
//			null, // quotationForm.getCustomerDetailsForm().getContactPersonName(),
//			null, // quotationForm.getCustomerDetailsForm().getContactNumber(),
//			null, // quotationForm.getCustomerDetailsForm().getEmailId(),
//			null, // quotationForm.getCustomerDetailsForm().getAddress(),
//			null, // quotationForm.getCustomerDetailsForm().getPincode(),
//			null, // quotationForm.getCustomerDetailsForm().getStateName(),
//			null, // quotationForm.getCustomerDetailsForm().getFirmName(),
//			null, // quotationForm.getCustomerDetailsForm().getConsultantName(),
//			null, // quotationForm.getCustomerDetailsForm().getConsultantContactNumber(),
//			null, // quotationForm.getCustomerDetailsForm().getConsultantEmailId(),
//			null, // quotationForm.getCustomerDetailsForm().getConsultantAddress(),
																			logger.info(	quotationForm.getCustomerDetailsForm().getIsEndUserAvailable());
																					logger.info(quotationForm.getCustomerDetailsForm().getEndUserCustType());
																							logger.info(quotationForm.getCustomerDetailsForm().getEndUserName());
//			null, // quotationForm.getCustomerDetailsForm().getEndUserContactNumber(),
//			null, // quotationForm.getCustomerDetailsForm().getEndUserEmailId(),
//			null, // quotationForm.getCustomerDetailsForm().getEndUserAddress(),
			logger.info(quotationForm.getCustomerDetailsForm().getEndUserStateName()); 
//			null);// quotationForm.getCustomerDetailsForm().getSfdcLink());

			logger.info("cust object start");
			return CUSTOMER_DET;
		} catch (Exception e) {
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error("Exception : ", e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}

	}

	
	// Edit SaveBasic Details
	@Override
	public QuotationForm editQuotationDetails(QuotationForm quotationForm) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		//ResultSet res = null;
		ResultSet resultSetQuot = null;
		int frameIdWithPowerId = 0;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			List<TurbineDetails> list = new ArrayList<>();
			list.addAll(quotationForm.getDropDownColumnvalues().getTypeOfNewList()
					.get(ItoConstants.FRAMES_WITH_POWER));

			for (TurbineDetails bean : list) {
				if (quotationForm.getSaveBasicDetails().getFrameId() == bean.getFrameId()
						&& quotationForm.getSaveBasicDetails().getFrameId() != 0) {
					if (quotationForm.getSaveBasicDetails().getCapacity() >= bean.getMinPower()
							&& quotationForm.getSaveBasicDetails().getCapacity() <= bean.getMaximumPower()) {
						frameIdWithPowerId = bean.getFramePowerId();
					}
				}
			}

			quotationForm.getSaveBasicDetails().setFramePowerId(frameIdWithPowerId);
			SQLServerDataTable CUSTOMER_DET = new SQLServerDataTable();
			CUSTOMER_DET = getcustObj(quotationForm);
			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_EDIT_TURB_CONFIG(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getFramePowerId());
			callableStatement.setFloat(2, quotationForm.getSaveBasicDetails().getCapacity());
			callableStatement.setInt(3, quotationForm.getSaveBasicDetails().getCondensingTypeId());
			callableStatement.setInt(4, quotationForm.getSaveBasicDetails().getBleedTypeId());
			//callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getOrientationTypeId());
			callableStatement.setInt(5, quotationForm.getSaveBasicDetails().getQuotId());
			if (quotationForm.getSaveBasicDetails().isActive()) {
				callableStatement.setInt(6, 1);
			} else {
				callableStatement.setInt(6, 0);
			}
			callableStatement.setInt(7, quotationForm.getSaveBasicDetails().getIsFrameUpdated());
			callableStatement.setInt(8, quotationForm.getSaveBasicDetails().getIsNewProject());
			callableStatement.setFloat(9, quotationForm.getSaveBasicDetails().getPercentageVariation());
			callableStatement.setInt(10, quotationForm.getSaveBasicDetails().getRevNum());
			callableStatement.setStructured(11, "dbo.CUSTOMER_DET", CUSTOMER_DET);
			callableStatement.setInt(12, quotationForm.getSaveBasicDetails().getTypeOfOffer());
			callableStatement.setInt(13, quotationForm.getSaveBasicDetails().getTypeOfQuot());
			callableStatement.setInt(14, quotationForm.getSaveBasicDetails().getTypeOfVarient());
			callableStatement.setInt(15, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setInt(16, quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.setString(17, quotationForm.getSaveBasicDetails().getEnquiryReference());
			callableStatement.setInt(18, quotationForm.getSaveBasicDetails().getImprovedImpulse());

			callableStatement.execute();
			logger.info("EDITMODE");
			logger.info("quotNumber");
			logger.info(quotationForm.getSaveBasicDetails().getFramePowerId());
			logger.info(quotationForm.getSaveBasicDetails().getCapacity());
			logger.info(quotationForm.getSaveBasicDetails().getCondensingTypeId());
			logger.info(quotationForm.getSaveBasicDetails().getBleedTypeId());
			//logger.info(quotationForm.getSaveBasicDetails().getOrientationTypeId());
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			logger.info(quotationForm.getSaveBasicDetails().isActive());
			logger.info(quotationForm.getSaveBasicDetails().getIsFrameUpdated());
			logger.info(quotationForm.getSaveBasicDetails().getIsNewProject());
			logger.info(quotationForm.getSaveBasicDetails().getPercentageVariation());
			logger.info(quotationForm.getSaveBasicDetails().getRevNum());
			logger.info(quotationForm.getSaveBasicDetails().getTypeOfOffer());
			logger.info(quotationForm.getSaveBasicDetails().getTypeOfQuot());
			logger.info(quotationForm.getSaveBasicDetails().getTypeOfVarient());
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info(quotationForm.getSaveBasicDetails().getUserRoleId());
			logger.info(quotationForm.getSaveBasicDetails().getEnquiryReference());
			logger.info(quotationForm.getSaveBasicDetails().getImprovedImpulse());
			logger.info("END");
			resultSetMsg = callableStatement.getResultSet();
			//res=resultSetMsg;
			//ResultSetMetaData count = res.getMetaData(); 
			logger.info(resultSetMsg);
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				logger.info("Testing resultOutParameterInt");
				logger.info(resultOutParameterInt);
				logger.info(resultOutParameterString);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetQuot = callableStatement.getResultSet();
				getCustomerData(quotationForm, resultSetQuot);
				logger.info(quotationForm.getSaveBasicDetails().getQuotNumber());
			}
			logger.info("resultOutParameterInt");
			logger.info(resultOutParameterInt);
			logger.info(callableStatement.getMoreResults());

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetQuot);
		}
		
		return quotationForm;
	}

	// =====

	@Override
	public QuotationForm getF2FOverHead(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetOHCost = null;
		List<F2FCostBean> f2FCostList = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_OVERHEAD (?,?,?) }");
			callableStatement.setString(1, quotationForm.getF2fCostBean().getcNum());
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getF2fCostBean().getParentMaterialCode());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetOHCost = callableStatement.getResultSet();
				while (resultSetOHCost.next()) {
					f2FCostList = new ArrayList<>();
					F2FCostBean f2FCostBean = new F2FCostBean();
					f2FCostBean.setcNum(resultSetOHCost.getString(ItoConstants.C_NUM));
					f2FCostBean.setWbsElementNo(resultSetOHCost.getString("WBS_ELE_NO"));
					f2FCostBean.setProdOrd(resultSetOHCost.getString(ItoConstants.PROD_ORD));
					f2FCostBean.setCostElement(resultSetOHCost.getString("COST_ELE"));
					f2FCostBean.setParentMaterialCode(resultSetOHCost.getString("PARNT_MTRL_CD"));
					f2FCostBean.setMaterialDesc(resultSetOHCost.getString("MAT_DESC"));
					f2FCostBean.setAmount(resultSetOHCost.getString("AMOUNT"));
					f2FCostList.add(f2FCostBean);
				}
				quotationForm.setF2fCostBeanList(f2FCostList);
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetOHCost);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getF2FShopCon(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetShopCon = null;
		List<F2FCostBean> f2fCostList = new ArrayList<>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_SHOP_CON (?,?,?) }");
			callableStatement.setString(1, quotationForm.getF2fCostBean().getcNum());
			callableStatement.setInt(2, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(3, quotationForm.getF2fCostBean().getParentMaterialCode());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetShopCon = callableStatement.getResultSet();

				while (resultSetShopCon.next()) {
					F2FCostBean f2fCostBean = new F2FCostBean();
					f2fCostBean.setcNum(resultSetShopCon.getString(ItoConstants.C_NUM));
					f2fCostBean.setWbsElementNo(resultSetShopCon.getString("WBS_ELE_NO"));
					f2fCostBean.setProdOrd(resultSetShopCon.getString(ItoConstants.PROD_ORD));
					f2fCostBean.setCostElement(resultSetShopCon.getString("COST_ELE"));
					f2fCostBean.setParentMaterialCode(resultSetShopCon.getString("PARNT_MTRL_CD"));
					f2fCostBean.setMaterialDesc(resultSetShopCon.getString("MAT_DESC"));
					f2fCostBean.setOperation(resultSetShopCon.getString("OPERATION"));
					f2fCostBean.setSubOperation(resultSetShopCon.getString("SUB_OPERATION"));
					f2fCostBean.setzDesc(resultSetShopCon.getString("ZDESC"));
					f2fCostBean.setAplfl(resultSetShopCon.getString("APLFL"));
					f2fCostBean.setWorkCenter(resultSetShopCon.getString("WORK_CENTER"));
					f2fCostBean.setCostElementDesc(resultSetShopCon.getString("COST_ELE_DESC"));
					f2fCostBean.setTotalHours(resultSetShopCon.getString("TOT_HOURS"));
					f2fCostBean.setAssemblyCost(resultSetShopCon.getString("ASSEMBLY_COST"));
					f2fCostBean.setTotalCost(resultSetShopCon.getString(ItoConstants.TOTAL_COST));
					f2fCostList.add(f2fCostBean);
				}
				quotationForm.setF2fCostBeanList(f2fCostList);
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetShopCon);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm createQuotRev(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_TURB_REVISION (?,?) }");

			callableStatement.setInt("IN_QUOT_ID", quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.execute();

			getQuotationData(callableStatement, quotationForm);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, e.toString());

			logger.error(ItoConstants.EXCEPTION + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement);
			UtilityMethods.closeResource(connection, callableStatement);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getQuotRevData(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_REVISION (?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.REV_NO, quotationForm.getSaveBasicDetails().getRevNum());
			callableStatement.execute();

			getQuotationData(callableStatement, quotationForm);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, e.toString());

			logger.error(ItoConstants.EXCEPTION + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement);
			UtilityMethods.closeResource(connection, callableStatement);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getTurbuineClone(QuotationForm quotationForm) {

		quotationForm.setEditQuotFlow(true);
		CallableStatement callableStatement = null;
		Connection connection = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_TURB_CLONE (?,?,?) }");
			callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setInt("IN_QUOT_ID", quotationForm.getSaveBasicDetails().getQuotId());
//			callableStatement.setInt(ItoConstants.REV_NO, quotationForm.getSaveBasicDetails().getRevNum());
			callableStatement.setInt("ROLES_ID", quotationForm.getSaveBasicDetails().getUserRoleId());
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			logger.info(quotationForm.getSaveBasicDetails().getRevNum());
			logger.info( quotationForm.getSaveBasicDetails().getUserRoleId());
			callableStatement.execute();

			getQuotationData(callableStatement, quotationForm);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, e.toString());

			logger.error(ItoConstants.EXCEPTION + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement);
			UtilityMethods.closeResource(connection, callableStatement);
		}
		return quotationForm;
	}

	@Override
	public QuotationHomeGrid assignToOthers(QuotationHomeGrid quotationHomeGrid) {

		QuotationForm quotationForm = new QuotationForm();
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_ASSIGNED_TO_OTHERS (?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationHomeGrid.getQuotId());
			callableStatement.setInt(ItoConstants.ASSIGNED_TO, quotationHomeGrid.getAssignedToId());
			callableStatement.setInt(ItoConstants.MOD_BY, quotationHomeGrid.getModifyById());
			callableStatement.setInt("ROLE_ID", quotationHomeGrid.getUserRoleId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				if (resultOutParameterInt == 0){
					List<List> list1 = new ArrayList<List>();
					list1 = getEmailList();
					logger.info("Testlevel1");
					logger.info(list1.size());
					logger.info(list1);
					logger.info(QuotationDaoImpl.test);
					for (int i = 0; i<list1.size();i++){
						logger.info("User Details Start");
						logger.info((int)list1.get(i).get(0));
						logger.info((String)list1.get(i).get(1));
						logger.info((String)list1.get(i).get(2));
						logger.info("User Details End");
						if ((int)list1.get(i).get(0) == quotationHomeGrid.getAssignedToId()){
							logger.info("Test Email");
							//logger.info(quotationForm.getSaveBasicDetails().getAssignedTo());
							logger.info((String)list1.get(i).get(1));
							logger.info((String)list1.get(i).get(2));
							quotationHomeGrid.setName((String)list1.get(i).get(1));
							quotationHomeGrid.setEmail((String)list1.get(i).get(2));
						}
					}
				}	

				quotationHomeGrid.setSuccessCode(resultOutParameterInt);
				quotationHomeGrid.setSuccessMsg(resultOutParameterString);

			}
			if (callableStatement.getMoreResults()) {
				resultSetAssignedUser = callableStatement.getResultSet();
				while (resultSetAssignedUser.next()) {
					quotationHomeGrid.setAssignedTo(resultSetAssignedUser.getString(ItoConstants.ASSIGNED_USER_NAME));
					logger.info("Assigned to : " + resultSetAssignedUser.getString(ItoConstants.ASSIGNED_USER_NAME));
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return quotationHomeGrid;
	}

	public QuotationForm getExcelCostSheetData(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetTrans = null;
		ResultSet resultSetF2F = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_QUOT_COST_SHEET_SUMMARY (?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

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
				oneLineBomData(callableStatement, quotationForm);

			}
		}

		catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetTrans);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2F);
		}

		return quotationForm;
	}
	
//	@Override
//	public QuotationForm getValidateFinalCost(QuotationForm quotationForm) {
//
//		CallableStatement callableStatement = null;
//		Connection connection = null;
//		int resultOutParameterInt = -1;
//		String resultOutParameterString = null;
//		ResultSet resultSetMsg = null;
//		ResultSet resultSetErrorMsg = null;
//
//		try {
//			connection = jdbcTemplate.getDataSource().getConnection();
//			callableStatement = connection.prepareCall("{ call dbo.PROC_VALIDATE_FINAL_COST (?) }");
//
//			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
//
//		callableStatement.execute();
//
//		resultSetMsg = callableStatement.getResultSet();
//		logger.info("level1");
//		while (resultSetMsg.next()) {
//			logger.info("level2");
//			resultOutParameterInt = resultSetMsg.getInt(1);
//			resultOutParameterString = resultSetMsg.getString(2);
//			quotationForm.setSuccessCode(resultOutParameterInt);
//			quotationForm.setSuccessMsg(resultOutParameterString);
//			quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
//		}
//		logger.info("level3");
//		if (resultOutParameterInt == 1) {
//			if (callableStatement.getMoreResults()) {
//				resultSetErrorMsg = callableStatement.getResultSet();
//				while (resultSetErrorMsg.next()) {
//					logger.info("level4");
//					quotationForm.setValidateErrorMsg(resultSetErrorMsg.getString(1));
//				}
//			}
//		}
//	}
//	catch (Exception e) {
//		quotationForm.setSuccessCode(-1);
//		quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
//
//		quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
//		logger.error(ItoConstants.EXCEPTION, e);
//	} finally {
//		UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
//	}
//
//	return quotationForm;
//	}
	/*@Override
	public QuotationForm getValidateFinalCost(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		int resultOutParameterIntNew = -1;
		String resultOutParameterStringNew = null;
		ResultSet resultSetTrans = null;
		ResultSet resultSetF2F = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetMsgNew = null;
		ResultSet resultSetErrorMsg = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_VALIDATE_FINAL_COST (?) }");

			//callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			logger.info("level1");
			while (resultSetMsg.next()) {
				logger.info("level2");
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			logger.info("level3");
			
//			if (callableStatement.getMoreResults()){
//				logger.info("More Resultset Available");
//			resultSetMsgNew = callableStatement.getResultSet();
//			while (resultSetMsgNew.next()) {
//				logger.info("level4");
//				resultOutParameterIntNew = resultSetMsgNew.getInt(1);
//				resultOutParameterStringNew = resultSetMsgNew.getString(2);
//				quotationForm.setSuccessCodeNew(resultOutParameterIntNew);
//				quotationForm.setSuccessMsgNew(resultOutParameterStringNew);
//				quotationForm.getMsgToUser().put(resultOutParameterIntNew, resultOutParameterStringNew);
//			}
//			if (resultOutParameterIntNew == 1) {
//				List<QuotationForm> valitationList = new ArrayList<>();
//				if (callableStatement.getMoreResults()) {
//					resultSetErrorMsg = callableStatement.getResultSet();
//					while (resultSetErrorMsg.next()) {
//						logger.info("level5");
//						QuotationForm bean = new QuotationForm();
//						bean.setValidateErrorMsg(resultSetErrorMsg.getString(1));
//						valitationList.add(bean);
//					}
//					quotationForm.setValidationList(valitationList);
//				}
//			}
//			}
//			else{
//				logger.info("No more resultSets Found");
//			}
			resultSetMsgNew = callableStatement.getResultSet();
			logger.info(resultSetMsgNew);
			while (resultSetMsgNew.next()) {
				logger.info("level4");
				resultOutParameterStringNew = resultSetMsgNew.getString(1);
				quotationForm.setValidatinMsg(resultOutParameterStringNew);
			}
			
			logger.info(resultOutParameterStringNew);
		}	
		catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetTrans);
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2F);
		}

		return quotationForm;
	}
*/
	@Override
	public QuotationForm getValidateFinalCost(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetValidationMsg = null;

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		String resultOutParameterStringNew = null;
		Map<String, List<DBOBean>> f2fCacheType = new HashMap<>();

		Connection connection = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_VALIDATE_FINAL_COST(?) }");

			callableStatement.setInt(1, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			while (resultSetMsg.next()) {
				logger.info("level1");
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			logger.info("level2");
			List<QuotationForm> validationList = new ArrayList<>();
				resultSetValidationMsg = callableStatement.getResultSet();
				logger.info("More Resultsets available");
				logger.info(resultSetValidationMsg);
				logger.info(callableStatement.getFetchSize());
				logger.info(resultSetValidationMsg.getString(1));
				while (resultSetValidationMsg.next()) {
					logger.info("level5");
					resultOutParameterStringNew = resultSetValidationMsg.getString(1);
					quotationForm.setValidationMsg(resultOutParameterStringNew);
				}
			logger.info(resultOutParameterStringNew);
				

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}

		return quotationForm;
	}

	public QuotationForm getCompleteCostSheetData(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAddOns1 = null;
		ResultSet resultSetAddOns = null;
		ResultSet resultSetAddOns2 = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_COST_SHEET_SUMMARY (?,?) }");

			callableStatement.setInt(ItoConstants.FRM_POW_ID, quotationForm.getSaveBasicDetails().getFramePowerId());
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

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

				List<AddOnComponent> addOnList = new ArrayList<>();

				if (callableStatement.getMoreResults()) {
					resultSetAddOns = callableStatement.getResultSet();

					while (resultSetAddOns.next()) {
						AddOnComponent adBean = new AddOnComponent();
						adBean.setAddOnCompoId(resultSetAddOns.getInt(ItoConstants.ADD_ON_COMP_ID));
						adBean.setAddOnCompo_cd(resultSetAddOns.getString(ItoConstants.ADD_ON_COMP));
						adBean.setExcelCost(Math.round(resultSetAddOns.getFloat(ItoConstants.COST)));

						addOnList.add(adBean);
					}
				}

				if (callableStatement.getMoreResults()) {
					resultSetAddOns1 = callableStatement.getResultSet();

					while (resultSetAddOns1.next()) {
						AddOnComponent adBean = new AddOnComponent();
						adBean.setAddOnCompoId(resultSetAddOns1.getInt(ItoConstants.ADD_ON_COMP_ID));
						adBean.setAddOnCompo_cd(resultSetAddOns1.getString(ItoConstants.ADD_ON_COMP));
						adBean.setExcelCost(Math.round(resultSetAddOns1.getFloat(ItoConstants.COST)));

						addOnList.add(adBean);
					}
				}

				quotationForm.setAddonDefaultList(addOnList);

				List<AddOnComponent> addOnCostSheetList = new ArrayList<>();
				// additional addOn component with cost
				if (callableStatement.getMoreResults()) {
					resultSetAddOns2 = callableStatement.getResultSet();

					while (resultSetAddOns2.next()) {
						AddOnComponent adBean = new AddOnComponent();
						adBean.setAddOnCompoId(resultSetAddOns2.getInt(ItoConstants.ADD_ON_COMP_ID));
						adBean.setAddOnCompo_cd(resultSetAddOns2.getString(ItoConstants.ADD_ON_COMP));

						if (resultSetAddOns2.getInt("COST_EX_FLG") == 1) {
							adBean.setExcelCostFlag(resultSetAddOns2.getInt("COST_EX_FLG"));
							adBean.setExcelCost(Math.round(resultSetAddOns2.getFloat("COST_EX")));
						} else if (resultSetAddOns2.getInt(ItoConstants.COST_ME_FLG) == 1) {
							adBean.setSelectedCostFlag(resultSetAddOns2.getInt(ItoConstants.COST_ME_FLG));
							adBean.setExcelCost(Math.round(resultSetAddOns2.getFloat(ItoConstants.COST_ME)));
						} else {
							adBean.setExcelCost(Math.round(resultSetAddOns2.getFloat("COST_SAP")));
						}

						adBean.setAddOnTotal(Math.round(resultSetAddOns2.getFloat("ADD_ON_TOTAL")));
						addOnCostSheetList.add(adBean);
					}
				}

				quotationForm.setAddOnComponentCostSheetList(addOnCostSheetList);

			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);

			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAddOns);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAddOns2);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAddOns1);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getOneLineBom(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetF2F = null;
		F2FForm f2FForm = new F2FForm();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_ONE_LINE_BOM (?) }");

			if (quotationForm.getSaveBasicDetails().isLatestCNumFlag()) {
				callableStatement.setString(ItoConstants.C_NUM, quotationForm.getSaveBasicDetails().getLatestCNum());
				quotationForm.getOneLineBom().setcNum(quotationForm.getSaveBasicDetails().getLatestCNum());
			} else {
				callableStatement.setString(ItoConstants.C_NUM, quotationForm.getSaveBasicDetails().getcNewNumber());
			}

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetF2F = callableStatement.getResultSet();
				while (resultSetF2F.next()) {

					f2FForm.setTurbineInstrumentCost(Math.round(resultSetF2F.getFloat(ItoConstants.TURBINE_MATERIAL)));
					f2FForm.setRawMaterialCost(Math.round(resultSetF2F.getFloat(ItoConstants.RAW_MATERIAL_COST)));
					f2FForm.setShopCoverCost(Math.round(resultSetF2F.getFloat(ItoConstants.SHOP_CONVERTION)));
					f2FForm.setOverheadCost(Math.round(resultSetF2F.getFloat(ItoConstants.OVERHEAD)));
					f2FForm.setSubContrCost(Math.round(resultSetF2F.getFloat(ItoConstants.SUBCONTRACTING)));
					f2FForm.setSemiFinished(Math.round(resultSetF2F.getFloat(ItoConstants.SEMIFINISHED)));
					f2FForm.setUbo(Math.round(resultSetF2F.getFloat("UBO")));
					f2FForm.setTotal(Math.round(resultSetF2F.getFloat(ItoConstants.TOTAL)));
				}
				quotationForm.setOneLineBom(f2FForm);
			}

			quotationForm = getCompleteCostSheetData(quotationForm);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2F);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getQuotOneLineBom(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetF2F = null;
		F2FForm f2FForm = new F2FForm();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_QUOT_ONE_LINE_BOM (?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
				resultSetF2F = callableStatement.getResultSet();

				while (resultSetF2F.next()) {

					f2FForm.setTurbineInstrumentCost(Math.round(resultSetF2F.getFloat(ItoConstants.TURBINE_MATERIAL)));
					f2FForm.setRawMaterialCost(Math.round(resultSetF2F.getFloat(ItoConstants.RAW_MATERIAL_COST)));
					f2FForm.setShopCoverCost(Math.round(resultSetF2F.getFloat(ItoConstants.SHOP_CONVERTION)));
					f2FForm.setOverheadCost(Math.round(resultSetF2F.getFloat(ItoConstants.OVERHEAD)));
					f2FForm.setSubContrCost(Math.round(resultSetF2F.getFloat(ItoConstants.SUBCONTRACTING)));
					f2FForm.setSemiFinished(Math.round(resultSetF2F.getFloat(ItoConstants.SEMIFINISHED)));
					f2FForm.setUbo(Math.round(resultSetF2F.getFloat("UBO")));
					f2FForm.setTotal(Math.round(resultSetF2F.getFloat(ItoConstants.TOTAL)));
				}
				quotationForm.setOneLineBom(f2FForm);
			}

			quotationForm = getCompleteCostSheetData(quotationForm);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2F);
		}
		return quotationForm;
	}

	private void clearMessageFrom(SaveBasicDetails saveBasicDetails) {
		if (null != saveBasicDetails) {
			saveBasicDetails.getMsgToUser().clear();
		}
	}

	@Override
	public QuotationForm getQuotation(QuotationForm quotationForm) {
		quotationForm.setEditQuotFlow(true);
		CallableStatement callableStatement = null;
		Connection connection = null;

		try {
			clearMessageFrom(quotationForm.getSaveBasicDetails());
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_TURB_CONFIG (?) }");
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			logger.info("edit mode");
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.execute();

			// get Quotation data from resultsets
			getQuotationData(callableStatement, quotationForm);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);

			logger.error(ItoConstants.EXCEPTION + e, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement);
		}
		return quotationForm;
	}

	// editOneLineBom
	@Override
	public QuotationForm editOneLineBom(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetF2F = null;
		F2FForm f2FForm = new F2FForm();
		try {

			List<String> zCountStr = new ArrayList<>();

			for (Integer value : quotationForm.getSaveBasicDetails().getzCount()) {
				zCountStr.add(String.valueOf(value));
			}

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_ONE_LINE_BOM (?,?,?,?,?) }");

			callableStatement.setString(ItoConstants.C_NUM, quotationForm.getSaveBasicDetails().getcNewNumber());

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString("ID", String.join(",", zCountStr));
			callableStatement.setInt("ALL_DATA", quotationForm.getSaveBasicDetails().getAllDataFlag());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {

				resultSetF2F = callableStatement.getResultSet();
				while (resultSetF2F.next()) {
					f2FForm.setRawMaterialCost(Math.round(resultSetF2F.getFloat(ItoConstants.RAW_MATERIAL_COST)));
					f2FForm.setShopCoverCost(Math.round(resultSetF2F.getFloat(ItoConstants.SHOP_CONVERTION)));
					f2FForm.setOverheadCost(Math.round(resultSetF2F.getFloat(ItoConstants.OVERHEAD)));
					f2FForm.setSubContrCost(Math.round(resultSetF2F.getFloat(ItoConstants.SUBCONTRACTING)));
					f2FForm.setSemiFinished(Math.round(resultSetF2F.getFloat(ItoConstants.SEMIFINISHED)));
					f2FForm.setUbo(Math.round(resultSetF2F.getFloat("UBO")));
					f2FForm.setTurbineInstrumentCost(Math.round(resultSetF2F.getFloat(ItoConstants.TURBINE_MATERIAL)));
					f2FForm.setTotal(Math.round(resultSetF2F.getFloat(ItoConstants.TOTAL)));
				}
				quotationForm.setOneLineBom(f2FForm);
			}

			quotationForm = getCompleteCostSheetData(quotationForm);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetF2F);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getQuotTransCache(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;

		ResultSet resultSetTransportType = null;
		ResultSet resultSetCustomerType = null;
		ResultSet resultSetvelicle = null;
		ResultSet resultSetcompType = null;
		ResultSet resultSetPlace = null;
		ResultSet resultSetVehiclesList = null;
		ResultSet resultSetstatus = null;
		ResultSet resultSetPackage = null;

		Map<String, List<TurbineDetails>> customerType = new HashMap<>();
		Map<String, List<TurbineDetails>> transportationType = new HashMap<>();
		Map<String, List<TurbineDetails>> vehiclesMap = new HashMap<>();
		Map<String, List<TurbineDetails>> statusList = new HashMap<>();
		Map<String, List<TurbineDetails>> componentTypes = new HashMap<>();

		Map<String, List<TransportationDetailsBean>> vehicleWithUnitPrice = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> places = new HashMap<>();
		Map<String, List<TurbineDetails>> packageList = new HashMap<>();
		Map<String, List<TurbineDetails>> newVehicleList = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> exportdetailsMap = new HashMap<>();
		Map<String, List<TransportationDetailsBean>> portDetailsMap = new HashMap<>();
		List<TransportationDetailsBean> vehicleWithUnitList = new ArrayList<>();
		List<TurbineDetails> vehiclesList = new ArrayList<>();
		List<TurbineDetails> newVehiclesList = new ArrayList<>();
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_TRNS_CACHE() }");
			callableStatement.execute();

			// Customer Types
			resultSetCustomerType = callableStatement.getResultSet();
			List<TurbineDetails> customerTypeList = new ArrayList<>();
			while (resultSetCustomerType.next()) {
				TurbineDetails turbineDetails = new TurbineDetails();
				turbineDetails.setCategoryDetId(resultSetCustomerType.getInt(ItoConstants.CAT_DET_ID));
				turbineDetails.setCategoryDetCode(resultSetCustomerType.getString(ItoConstants.CAT_DET_CD));

				turbineDetails.setCategoryDetDesc(resultSetCustomerType.getString(ItoConstants.CAT_DET_DESC));
				turbineDetails.setCategoryCreatedDate(resultSetCustomerType.getDate(ItoConstants.CREAT_DT));
				turbineDetails.setCategoryModifiedDate(resultSetCustomerType.getDate(ItoConstants.MOD_DT));
				turbineDetails.setCategoryCreatedBy(resultSetCustomerType.getInt(ItoConstants.CREAT_BY));
				turbineDetails.setCategoryModifiedBy(resultSetCustomerType.getInt(ItoConstants.MOD_BY));
				turbineDetails
						.setIscategoryActive(resultSetCustomerType.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

				customerTypeList.add(turbineDetails);
			}
			if (!customerTypeList.isEmpty()) {
				customerType.put("CustomerType", customerTypeList);
				quotationForm.getDropDownColumnvalues().setCustomerType(customerType);
			}

			// Transport Types
			if (callableStatement.getMoreResults()) {
				resultSetTransportType = callableStatement.getResultSet();
				List<TurbineDetails> transportTypesList = new ArrayList<>();
				while (resultSetTransportType.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetTransportType.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setCategoryDetCode(resultSetTransportType.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setDependentCode(resultSetTransportType.getString(ItoConstants.GRP_CD));
					turbineDetails.setCategoryDetDesc(resultSetTransportType.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetTransportType.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetTransportType.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetTransportType.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetTransportType.getInt(ItoConstants.MOD_BY));
					turbineDetails.setIscategoryActive(
							resultSetTransportType.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					transportTypesList.add(turbineDetails);
				}
				if (!transportTypesList.isEmpty()) {
					transportationType.put("TransportTypes", transportTypesList);
					quotationForm.getDropDownColumnvalues().setTransportationType(transportationType);
				}
			}

			// Vehicles List
			if (callableStatement.getMoreResults()) {
				resultSetVehiclesList = callableStatement.getResultSet();

				while (resultSetVehiclesList.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetVehiclesList.getInt("VEHICLE_ID"));
					turbineDetails.setCategoryDetCode(resultSetVehiclesList.getString("VH_CD"));
					turbineDetails.setCategoryDetDesc(resultSetVehiclesList.getString("VH_NAME"));
					turbineDetails.setLength(resultSetVehiclesList.getString("VH_LEN_MT"));
					turbineDetails.setDimension(resultSetVehiclesList.getString("VH_LEN_FT"));
					turbineDetails.setCategoryCreatedDate(resultSetVehiclesList.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetVehiclesList.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetVehiclesList.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetVehiclesList.getInt(ItoConstants.MOD_BY));
					turbineDetails.setEffectiveFrom(resultSetVehiclesList.getString(ItoConstants.EFF_FROM));
					turbineDetails.setEffectiveTo(resultSetVehiclesList.getString(ItoConstants.EFF_TO));
					turbineDetails.setIscategoryActive(
							resultSetVehiclesList.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

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
					turbineDetails.setCategoryDetId(resultSetcompType.getInt(ItoConstants.COMP_ID));
					turbineDetails.setCategoryDetCode(resultSetcompType.getString("COMP_CD"));
					turbineDetails.setTurbineCode(resultSetcompType.getString(ItoConstants.TURB_CD));
					turbineDetails.setCategoryDetDesc(resultSetcompType.getString(ItoConstants.COMP_NM));
					turbineDetails.setPlace(resultSetcompType.getString(ItoConstants.FOB_PLACE));
					turbineDetails.setPlaceId(resultSetcompType.getString(ItoConstants.FOB_ID));
					turbineDetails.setSsId(resultSetcompType.getInt(ItoConstants.SS_ID));
					turbineDetails.setSsName(resultSetcompType.getString(ItoConstants.SCOPE_OF_SUPPLY));
					turbineDetails
							.setIscategoryActive(resultSetcompType.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					componentTypeList.add(turbineDetails);
				}
				if (!componentTypeList.isEmpty()) {
					componentTypes.put("TransportComponentTypeList", componentTypeList);
					quotationForm.getDropDownColumnvalues().setComponentTypes(componentTypes);
				}
			}
			// vehicleWithUnitList
			if (callableStatement.getMoreResults()) {
				resultSetvelicle = callableStatement.getResultSet();

				while (resultSetvelicle.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setUnitId(resultSetvelicle.getInt("UNIT_ID"));
					bean.setUnitPrice(resultSetvelicle.getFloat("UNIT_PRICE"));
					bean.setDimension(resultSetvelicle.getString("DIMN"));
					bean.setLength(resultSetvelicle.getInt("LENT"));
					bean.setMaxDistance(resultSetvelicle.getFloat("MAX_DIST"));
					bean.setMinDistance(resultSetvelicle.getFloat("MIN_DIST"));
					bean.setVehicleId(resultSetvelicle.getInt("VEHICLE_ID"));
					bean.setVehicleCode(resultSetvelicle.getString("VEHICLE_CD"));
					bean.setVehicleName(resultSetvelicle.getString("VEHICLE_NM"));
					bean.setActive(resultSetvelicle.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					vehicleWithUnitList.add(bean);
				}
				if (!vehicleWithUnitList.isEmpty()) {
					vehicleWithUnitPrice.put("vehicleWithUnitList", vehicleWithUnitList);
					quotationForm.getDropDownColumnvalues().setVehicleWithUnitPrice(vehicleWithUnitPrice);
				}
			}
			boolean contains = false;
			for (TurbineDetails bean1 : vehiclesList) {
				for (TransportationDetailsBean bean2 : vehicleWithUnitList) {
					if (bean1.getCategoryDetId() == bean2.getVehicleId()) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					newVehiclesList.add(bean1);
				} else {
					contains = false;
				}
			}

			newVehicleList.put("NewlyAdded_VehiclesList", newVehiclesList);
			quotationForm.getDropDownColumnvalues().setNewlyAdded_vehiclesList(newVehicleList);

			// placeList
			if (callableStatement.getMoreResults()) {
				resultSetPlace = callableStatement.getResultSet();
				List<TransportationDetailsBean> placeList = new ArrayList<>();
				while (resultSetPlace.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setPlaceId(resultSetPlace.getInt(ItoConstants.FOB_ID));
					bean.setFOBPlace(resultSetPlace.getString(ItoConstants.FOB_PLACE));
					bean.setFOBPlaceCode(resultSetPlace.getString("FOB_PLACE_CD"));
					bean.setDistance(resultSetPlace.getFloat("FOB_DISTANCE"));
					bean.setActive(resultSetPlace.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					placeList.add(bean);
				}
				if (!placeList.isEmpty()) {
					places.put("placeList", placeList);
					quotationForm.getDropDownColumnvalues().setPlaceList(places);
				}
			}

			// status List
			if (callableStatement.getMoreResults()) {
				resultSetstatus = callableStatement.getResultSet();
				List<TurbineDetails> statusArray = new ArrayList<>();
				while (resultSetstatus.next()) {
					TurbineDetails bean = new TurbineDetails();
					bean.setStatusId(resultSetstatus.getInt("ID"));
					bean.setStatus(resultSetstatus.getString("NAME"));
					bean.setStatusCode(resultSetstatus.getString("CD"));
					statusArray.add(bean);
				}
				if (!statusArray.isEmpty()) {
					statusList.put("statusList", statusArray);
					quotationForm.getDropDownColumnvalues().setStatusList(statusList);
				}
			}

			// --------------- Package List

			if (callableStatement.getMoreResults()) {
				resultSetPackage = callableStatement.getResultSet();
				List<TurbineDetails> packageArray = new ArrayList<>();
				while (resultSetPackage.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetPackage.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setCategoryDetCode(resultSetPackage.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setDependentCode(resultSetPackage.getString(ItoConstants.GRP_CD));
					turbineDetails.setCategoryDetDesc(resultSetPackage.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetPackage.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetPackage.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetPackage.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetPackage.getInt(ItoConstants.MOD_BY));
					turbineDetails
							.setIscategoryActive(resultSetPackage.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					packageArray.add(turbineDetails);
				}
				if (!packageArray.isEmpty()) {
					packageList.put("packageList", packageArray);
					quotationForm.getDropDownColumnvalues().setPackageList(packageList);
				}
			}

			// --------------export trans cache

			ResultSet resultSetexp = null;
			if (callableStatement.getMoreResults()) {
				resultSetexp = callableStatement.getResultSet();
				List<TransportationDetailsBean> exportTransDetailList = new ArrayList<>();
				while (resultSetexp.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();

					bean.setTransId(resultSetexp.getInt("TRNS_ID"));
					bean.setCustType(resultSetexp.getString(ItoConstants.CUST_TYP));
					bean.setTransTypeId(resultSetexp.getInt("TRNS_TYP_ID"));
					bean.setTransType(resultSetexp.getString(ItoConstants.TRNS_TYP));
					bean.setFrameId(resultSetexp.getInt(ItoConstants.FRM_ID));
					bean.setFrameName(resultSetexp.getString(ItoConstants.FRM_NM));

					bean.setCondensingTypeId(resultSetexp.getInt(ItoConstants.COND_TYP_ID));
					bean.setCondensingTypeName(resultSetexp.getString("CONDENSING_TYPES"));
					bean.setChennaiPrice(resultSetexp.getFloat("PRICE_CHENNAI"));
					bean.setPriceFob(resultSetexp.getFloat("PRICE_FOB"));
					bean.setPrice(resultSetexp.getInt(ItoConstants.PRICE));
					bean.setCreatedBy(resultSetexp.getString(ItoConstants.CREAT_BY));
					bean.setModifiedDate(resultSetexp.getString(ItoConstants.MOD_DT));
					bean.setActive(resultSetexp.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

					exportTransDetailList.add(bean);
				}
				if (!exportTransDetailList.isEmpty()) {
					exportdetailsMap.put("EXPORT_TRANS_DETAILS", exportTransDetailList);
					quotationForm.getDropDownColumnvalues().setExportTransportDetails(exportdetailsMap);
				}
			}

			ResultSet resultSetPort = null;
			if (callableStatement.getMoreResults()) {
				resultSetPort = callableStatement.getResultSet();
				List<TransportationDetailsBean> portDetailList = new ArrayList<>();
				while (resultSetPort.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();

					bean.setPortId(resultSetPort.getInt(ItoConstants.PORT_ID));
					bean.setCountryName(resultSetPort.getString(ItoConstants.COUNTRY_NM));
					bean.setPortName(resultSetPort.getString(ItoConstants.PORT_NM));

					portDetailList.add(bean);
				}
				if (!portDetailList.isEmpty()) {
					portDetailsMap.put("PORT_DETAILS", portDetailList);
					quotationForm.getDropDownColumnvalues().setPortDetails(portDetailsMap);
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetTransportType);
			UtilityMethods.closeResource(connection, callableStatement, resultSetCustomerType);
			UtilityMethods.closeResource(connection, callableStatement, resultSetcompType);
			UtilityMethods.closeResource(connection, callableStatement, resultSetPlace);
			UtilityMethods.closeResource(connection, callableStatement, resultSetVehiclesList);
			UtilityMethods.closeResource(connection, callableStatement, resultSetvelicle);
			UtilityMethods.closeResource(connection, callableStatement, resultSetstatus);
			UtilityMethods.closeResource(connection, callableStatement, resultSetPackage);

		}

		return quotationForm;

	}

	@Override
	public QuotationForm getTransportationCache(QuotationForm quotationForm) {
		try {
			getQuotTransCache(quotationForm);

			quotationForm = fetchQuotCacheData(quotationForm);
			quotationForm = fetchCacheData(quotationForm);

			quotationForm = getNewFramesForExport(quotationForm);
		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getNewFramesForExport(QuotationForm quotationForm) {

		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			String query = "SELECT  FRM_ID,FRM_NM,TURB_CD,TURB_DESN,IS_ACTIVE,MOD_BY,MOD_DT,CREAT_BY,CREAT_DT FROM FRAMES WHERE FRM_ID NOT IN (SELECT DISTINCT A.FRM_ID FROM TRNSPORT_EX A,FRAMES B WHERE A.FRM_ID = B.FRM_ID)";

			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			Map<String, List<TurbineDetails>> map = new HashMap<>();
			List<TurbineDetails> newFramePowerList = new ArrayList<>();
			while (rs.next()) {
				TurbineDetails turbineDetails = new TurbineDetails();
				turbineDetails.setFrameId(rs.getInt(ItoConstants.FRM_ID));
				turbineDetails.setFrameDesc(rs.getString(ItoConstants.FRM_NM));

				turbineDetails.setTurbineCode(rs.getString(ItoConstants.TURB_CD));
				turbineDetails.setTurbineDesignCd(rs.getString(ItoConstants.TURB_DESN));

				turbineDetails.setFrameActive(rs.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);

				turbineDetails.setCategoryModifiedBy(rs.getInt(ItoConstants.MOD_BY));
				turbineDetails.setCategoryModifiedDate(rs.getDate(ItoConstants.MOD_DT));
				turbineDetails.setCategoryCreatedBy(rs.getInt(ItoConstants.CREAT_BY));
				turbineDetails.setCategoryCreatedDate(rs.getDate(ItoConstants.CREAT_DT));

				newFramePowerList.add(turbineDetails);
			}

			if (!newFramePowerList.isEmpty()) {
				map.put("NEW_FRAMES_FOR_EXPORT", newFramePowerList);
				quotationForm.getDropDownColumnvalues().setNewframeWithPowerForExportTrans(map);
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, stmt, rs);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getTransportDataBasedOnFrame(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetTransport = null;
		ResultSet resultSetTransExport = null;
		List<TransportationDetailsBean> transportList = null;
		// String custType = null;

		try {
			// if
			// (quotationForm.getSaveBasicDetails().getCustType().equalsIgnoreCase(ItoConstants.DOMESTIC))
			// custType = ItoConstants.DM;
			// else if
			// (quotationForm.getSaveBasicDetails().getCustType().equalsIgnoreCase("export"))
			// custType = "EX";
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_TRNS_DATA (?,?,?,?,?,?) }");

			callableStatement.setString(ItoConstants.CUST_TYP, quotationForm.getSaveBasicDetails().getCustType());// dm//
			callableStatement.setInt(ItoConstants.FRM_ID, quotationForm.getSaveBasicDetails().getFrameId());// 83//tst-1300-h//43
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());// 120
			callableStatement.setInt(ItoConstants.COND_TYP_ID,
					quotationForm.getSaveBasicDetails().getCondensingTypeId());// 0
			callableStatement.setInt(ItoConstants.TRNS_TYP,
					quotationForm.getSaveBasicDetails().getTransportationType());// 124
			callableStatement.setInt(ItoConstants.PORT_ID, quotationForm.getSaveBasicDetails().getPortId());// 0

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
				resultSetTransport = callableStatement.getResultSet();
				transportList = new ArrayList<>();
				while (resultSetTransport.next()) {

					TransportationDetailsBean bean = new TransportationDetailsBean();
					if (quotationForm.getSaveBasicDetails().getCustType().equalsIgnoreCase("DM")
							&& quotationForm.getSaveBasicDetails().getQuotId() != 0) {
						bean.setPlaceId(resultSetTransport.getInt(ItoConstants.FOB_ID));
						bean.setFOBPlace(resultSetTransport.getString(ItoConstants.FOB_PLACE));
						bean.setSsId(resultSetTransport.getInt(ItoConstants.SS_ID));
						bean.setScopeOfSupply(resultSetTransport.getString(ItoConstants.SCOPE_OF_SUPPLY));
						bean.setCompoId(resultSetTransport.getInt(ItoConstants.COMP_ID));
						bean.setCompoName(resultSetTransport.getString(ItoConstants.COMP_NM));
						bean.setNumberOfVehicle(resultSetTransport.getInt(ItoConstants.NO_OF_VEHICLE));

					} else if (quotationForm.getSaveBasicDetails().getCustType().equalsIgnoreCase("DM")
							&& quotationForm.getSaveBasicDetails().getQuotId() == 0) {

						bean.setPlaceId(resultSetTransport.getInt(ItoConstants.FOB_ID));
						bean.setFOBPlace(resultSetTransport.getString(ItoConstants.FOB_PLACE));
						bean.setNumberOfVehicle(resultSetTransport.getInt(ItoConstants.NO_OF_VEHICLE));
						bean.setCompoId(resultSetTransport.getInt(ItoConstants.COMP_ID));
						bean.setCompoName(resultSetTransport.getString(ItoConstants.COMP_NM));

					} else if (quotationForm.getSaveBasicDetails().getCustType().equalsIgnoreCase("EX")
							&& quotationForm.getSaveBasicDetails().getTransTypeCode().equalsIgnoreCase("FOB")) {
						bean.setCondensingTypeId(resultSetTransport.getInt(ItoConstants.COND_TYP_ID));
						bean.setCondensingTypeName(resultSetTransport.getString(ItoConstants.COND_TYP_NAME));
						bean.setTotalPrice(Math.round(resultSetTransport.getFloat(ItoConstants.PRICE)));
						bean.setChennaiPrice(Math.round(resultSetTransport.getFloat("PRICE_CHENNAI")));
						bean.setPriceFob(Math.round(resultSetTransport.getFloat("PRICE_FOB")));

					} else if (quotationForm.getSaveBasicDetails().getCustType().equalsIgnoreCase("EX")
							&& quotationForm.getSaveBasicDetails().getTransTypeCode().equalsIgnoreCase("CIF")) {
						bean.setCondensingTypeId(resultSetTransport.getInt(ItoConstants.COND_TYP_ID));
						bean.setCondensingTypeName(resultSetTransport.getString(ItoConstants.COND_TYP_NAME));
						bean.setPortId(resultSetTransport.getInt(ItoConstants.PORT_ID));
						bean.setCountryName(resultSetTransport.getString(ItoConstants.COUNTRY_NM));
						bean.setPortName(resultSetTransport.getString(ItoConstants.PORT_NM));
						bean.setCifExPrice(Math.round(resultSetTransport.getFloat(ItoConstants.PRICE)));

					}

					transportList.add(bean);
				}

			}
			if (callableStatement.getMoreResults()) {
				resultSetTransExport = callableStatement.getResultSet();
				while (resultSetTransExport.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();
					bean.setCifDomPrice(Math.round(resultSetTransExport.getFloat(ItoConstants.PRICE)));

					transportList.add(bean);

				}
			}
			quotationForm.setTransportationDetailList(transportList);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetTransport);
			UtilityMethods.closeResource(connection, callableStatement, resultSetTransExport);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getTransportPrice(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetTransport = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_TRNS_DATA_PRICE (?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.FRM_ID, quotationForm.getSaveBasicDetails().getFrameId());
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.COND_TYP_ID,
					quotationForm.getSaveBasicDetails().getCondensingTypeId());
			callableStatement.setFloat(ItoConstants.DISTANCE, quotationForm.getTransBean().getDistance());
			callableStatement.setFloat(ItoConstants.COMP_ID, quotationForm.getTransBean().getCompoId());

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
				resultSetTransport = callableStatement.getResultSet();
				while (resultSetTransport.next()) {
					quotationForm.getTransBean().setPrice(Math.round(resultSetTransport.getFloat(ItoConstants.COST)));
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetTransport);
		}
		return quotationForm;
	}

	@Override
	public SaveBasicDetails saveTransportationData(SaveBasicDetails saveBasicDetails) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		try {

			clearMessageFrom(saveBasicDetails);
			connection = jdbcTemplate.getDataSource().getConnection();

			SQLServerDataTable TRNS_DET_OBJ = new SQLServerDataTable();
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.TRNS_MAST_ID, java.sql.Types.NUMERIC);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.QUOT_ID, java.sql.Types.NUMERIC);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.TRNS_TYP, java.sql.Types.NUMERIC);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.FOB_NM, java.sql.Types.VARCHAR);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.COMP_ID, java.sql.Types.NUMERIC);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.NO_OF_VEHICLE, java.sql.Types.NUMERIC);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.TO_PLACE, java.sql.Types.VARCHAR);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.DISTANCE, java.sql.Types.NUMERIC);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.PORT_ID, java.sql.Types.NUMERIC);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.COST, java.sql.Types.REAL);
			TRNS_DET_OBJ.addColumnMetadata(ItoConstants.FOB_COST, java.sql.Types.REAL);
			TRNS_DET_OBJ.addColumnMetadata("CHENNAI_COST", java.sql.Types.REAL);

			for (TransportationDetailsBean bean : saveBasicDetails.getTransportVehicalCostList()) {
				TRNS_DET_OBJ.addRow(bean.getTransMastId(), saveBasicDetails.getQuotId(),
						saveBasicDetails.getTransportationType(), bean.getFOBPlace(), bean.getCompoId(),
						bean.getNumberOfVehicle(), bean.getAddress(), bean.getMaxDistance(),
						saveBasicDetails.getPortId(), bean.getCompPrice(), bean.getFobPrice(), bean.getChennaiPrice());
			}

			callableStatement = (SQLServerPreparedStatement) connection
					.prepareStatement("{ call dbo.PROC_SAVE_TRNS_DATA(?,?,?,?,?)}");

			callableStatement.setStructured(1, "dbo.TRNS_DET_OBJ", TRNS_DET_OBJ);
			callableStatement.setFloat(2, saveBasicDetails.getTransportTotalPrice());
			callableStatement.setInt(3, saveBasicDetails.getTransportBean().getOverwrittenPriceFlag());
			callableStatement.setFloat(4, saveBasicDetails.getTransportBean().getOverwrittenPrice());
			callableStatement.setInt(5, saveBasicDetails.getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			ResultSet resultSetDataTrans = null;
			List<TransportationDetailsBean> quotTransportList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetDataTrans = callableStatement.getResultSet();
				while (resultSetDataTrans.next()) {
					TransportationDetailsBean bean = new TransportationDetailsBean();

					bean.setTransMastId(resultSetDataTrans.getInt(ItoConstants.TRNS_MAST_ID));
					bean.setCustCode(resultSetDataTrans.getString("CUST_CD"));
					bean.setTransTypeId(resultSetDataTrans.getInt(ItoConstants.TRNS_TYP));
					bean.setTransType(resultSetDataTrans.getString("TRNS_TYPES"));
					bean.setFrameId(resultSetDataTrans.getInt(ItoConstants.FRM_ID));
					bean.setFrameName(resultSetDataTrans.getString(ItoConstants.FRAME_NAME));
					bean.setCondensingTypeId(resultSetDataTrans.getInt(ItoConstants.COND_TYP_ID));
					bean.setCondensingTypeName(resultSetDataTrans.getString(ItoConstants.COND_TYP_NAME));
					// bean.setFOBName(resultSetDataTrans.getString(ItoConstants.FOB_NM));
					bean.setFOBPlace(resultSetDataTrans.getString(ItoConstants.FOB_NM));

					bean.setCompoId(resultSetDataTrans.getInt(ItoConstants.COMP_ID));
					bean.setCompoName(resultSetDataTrans.getString(ItoConstants.COMP_NM));
					bean.setNumberOfVehicle(resultSetDataTrans.getInt(ItoConstants.NO_OF_VEHICLE));
					bean.setToPlace(resultSetDataTrans.getString(ItoConstants.TO_PLACE));
					bean.setDistance(resultSetDataTrans.getInt(ItoConstants.DISTANCE));
					bean.setPortId(resultSetDataTrans.getInt(ItoConstants.PORT_ID));
					bean.setPortName(resultSetDataTrans.getString(ItoConstants.PORT_NM));
					bean.setCountryName(resultSetDataTrans.getString(ItoConstants.COUNTRY_NM));
					bean.setCompPrice(Math.round(resultSetDataTrans.getFloat(ItoConstants.COST)));
					bean.setFobPrice(Math.round(resultSetDataTrans.getFloat(ItoConstants.FOB_COST)));
					bean.setTotalPrice(Math.round(resultSetDataTrans.getFloat(ItoConstants.TOTAL_COST)));

					bean.setOverwrittenPriceFlag(resultSetDataTrans.getInt(ItoConstants.COST_ME_FLG));
					bean.setOverwrittenPrice(Math.round(resultSetDataTrans.getFloat(ItoConstants.COST_ME)));
					quotTransportList.add(bean);
				}
				saveBasicDetails.setTransDetailList(quotTransportList);
			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return saveBasicDetails;
	}

	@Override
	public QuotationForm getErrectionCommCache(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		ResultSet resultSetData = null;
		ResultSet resultSetCharges = null;
		ResultSet resultSetLodging = null;
		ResultSet resultSetData1 = null;
		ResultSet resultSetLodgingExport = null;
		ResultSet resultSetLodgingNBoarding = null;

		Map<String, List<TurbineDetails>> ecCustType = new HashMap<>();
		Map<String, List<TurbineDetails>> charges = new HashMap<>();
		Map<String, List<TurbineDetails>> lodging = new HashMap<>();
		Map<String, List<TurbineDetails>> loadingDomestic = new HashMap<>();
		Map<String, List<TurbineDetails>> USDollerList = new HashMap<>();

		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_EC_CACHE(?) }");
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();

			resultSetData = callableStatement.getResultSet(); // get cust types
			List<TurbineDetails> ecCustTypeList = new ArrayList<>();
			while (resultSetData.next()) {

				TurbineDetails turbineDetails = new TurbineDetails();
				turbineDetails.setCategoryDetId(resultSetData.getInt(ItoConstants.CAT_DET_ID));
				turbineDetails.setCategoryDetCode(resultSetData.getString(ItoConstants.CAT_DET_CD));
				turbineDetails.setCategoryDetDesc(resultSetData.getString(ItoConstants.CAT_DET_DESC));
				turbineDetails.setCategoryCreatedDate(resultSetData.getDate(ItoConstants.CREAT_DT));
				turbineDetails.setCategoryModifiedDate(resultSetData.getDate(ItoConstants.MOD_DT));
				turbineDetails.setCategoryCreatedBy(resultSetData.getInt(ItoConstants.CREAT_BY));
				turbineDetails.setCategoryModifiedBy(resultSetData.getInt(ItoConstants.MOD_BY));
				turbineDetails.setIscategoryActive(resultSetData.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);
				turbineDetails.setEffectiveFrom(resultSetData.getString(ItoConstants.EFF_FROM));
				turbineDetails.setEffectiveTo(resultSetData.getString(ItoConstants.EFF_TO));

				ecCustTypeList.add(turbineDetails);
			}

			if (!ecCustTypeList.isEmpty()) {
				ecCustType.put("ErectionCustTypes", ecCustTypeList);
				quotationForm.getDropDownColumnvalues().setEnCustTypeList(ecCustType);
			}

			List<TurbineDetails> loadingListDomestic = new ArrayList<>();

			if (callableStatement.getMoreResults()) {
				resultSetLodgingNBoarding = callableStatement.getResultSet();

				while (resultSetLodgingNBoarding.next()) {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetLodgingNBoarding.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setDependentCode(resultSetLodgingNBoarding.getString(ItoConstants.GRP_CD));
					turbineDetails.setCategoryDetCode(resultSetLodgingNBoarding.getString(ItoConstants.CAT_DET_CD));

					turbineDetails.setCategoryDetDesc(resultSetLodgingNBoarding.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetLodgingNBoarding.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetLodgingNBoarding.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetLodgingNBoarding.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetLodgingNBoarding.getInt(ItoConstants.MOD_BY));
					turbineDetails.setIscategoryActive(
							resultSetLodgingNBoarding.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);
					turbineDetails.setEffectiveFrom(resultSetLodgingNBoarding.getString(ItoConstants.EFF_FROM));
					turbineDetails.setEffectiveTo(resultSetLodgingNBoarding.getString(ItoConstants.EFF_TO));

					loadingListDomestic.add(turbineDetails);
				}

			}
			if (!loadingListDomestic.isEmpty()) {
				loadingDomestic.put("EnCLoadingListDomestic", loadingListDomestic);
				quotationForm.getDropDownColumnvalues().setEnCLoadingDomestic(loadingDomestic);
			}

			if (callableStatement.getMoreResults()) {
				resultSetLodging = callableStatement.getResultSet();
				List<TurbineDetails> lodgingList = new ArrayList<>();
				while (resultSetLodging.next()) {

					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetLodging.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setDependentCode(resultSetLodging.getString(ItoConstants.GRP_CD));
					turbineDetails.setCategoryDetCode(resultSetLodging.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setCategoryDetDesc(resultSetLodging.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetLodging.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetLodging.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetLodging.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetLodging.getInt(ItoConstants.MOD_BY));
					turbineDetails
							.setIscategoryActive(resultSetLodging.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);
					turbineDetails.setEffectiveFrom(resultSetLodging.getString(ItoConstants.EFF_FROM));
					turbineDetails.setEffectiveTo(resultSetLodging.getString(ItoConstants.EFF_TO));

					lodgingList.add(turbineDetails);
				}
				if (!lodgingList.isEmpty()) {
					lodging.put("EnCLodgingList", lodgingList);
					quotationForm.getDropDownColumnvalues().setEnCLodging(lodging);
				}
			}

			if (callableStatement.getMoreResults()) {
				resultSetData1 = callableStatement.getResultSet();
				while (resultSetData1.next()) {

					quotationForm.setNoOfMandays(resultSetData1.getInt("NO_OF_MANDAYS"));

				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetData);
			UtilityMethods.closeResource(connection, callableStatement, resultSetCharges);
			UtilityMethods.closeResource(connection, callableStatement, resultSetLodging);
			UtilityMethods.closeResource(connection, callableStatement, resultSetLodgingExport);
			UtilityMethods.closeResource(connection, callableStatement, resultSetLodgingNBoarding);
		}

		return quotationForm;
	}

	@Override
	public QuotationForm getErecCommData(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		int resultSetErecComm = -1;
		int resultSetErecComm1 = -1;
		int resultSetErecComm2 = -1;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_EC (?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getErectionCommissionBean().getQuotId());
			// callableStatement.setInt(ItoConstants.TYP_OF_CHARGE,
			// quotationForm.getErectionCommissionBean().getTypeOfChargeId());
			callableStatement.setInt(ItoConstants.LOADING_ID, quotationForm.getErectionCommissionBean().getLoadingId());
			callableStatement.setInt(ItoConstants.LOADGING_ID,
					quotationForm.getErectionCommissionBean().getLodgingId());
			callableStatement.setInt(ItoConstants.NO_OF_MANDAYS,
					quotationForm.getErectionCommissionBean().getNoOfManDays());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				resultSetErecComm = resultSetMsg.getInt(ItoConstants.PRICE);
				resultSetErecComm1 = resultSetMsg.getInt(ItoConstants.ITEM_ID);
				resultSetErecComm2 = resultSetMsg.getInt(ItoConstants.SS_ID);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.setPrice(resultSetErecComm);
				quotationForm.setItemId(resultSetErecComm1);
				quotationForm.setSsId(resultSetErecComm2);

				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

			// quotationForm.getErectionCommissionBean().setPrice(resultSetErecComm.getInt(ItoConstants.PRICE));

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

		}
		return quotationForm;
	}

	@Override
	public QuotationForm saveErecCommission(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			logger.info("Save EC start1");
			logger.info(quotationForm.getErectionCommissionBean().getTypeOfChargeId());
			logger.info(quotationForm.getErectionCommissionBean().getLoadingId());
			logger.info(quotationForm.getErectionCommissionBean().getLodgingId());
			logger.info(quotationForm.getErectionCommissionBean().getNoOfManDays());
			logger.info(quotationForm.getErectionCommissionBean().getPrice());
			logger.info(quotationForm.getErectionCommissionBean().getOverwrittenPrice());
			logger.info(quotationForm.getErectionCommissionBean().isOverwrittenPriceFlag());
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			logger.info(quotationForm.getErectionCommissionBean().getRemarks());
			logger.info(quotationForm.getErectionCommissionBean().getServiceRemarks());
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info("Save EC end1");
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_EC (?,?,?,?,?,?,?,?,?,?,?) }");
			
			callableStatement.setInt(ItoConstants.TYP_OF_CHARGE,
					quotationForm.getErectionCommissionBean().getTypeOfChargeId());

			callableStatement.setInt(ItoConstants.LOADING_ID, quotationForm.getErectionCommissionBean().getLoadingId());
			callableStatement.setInt(ItoConstants.LOADGING_ID,
					quotationForm.getErectionCommissionBean().getLodgingId());
			callableStatement.setInt(ItoConstants.NO_OF_MANDAYS,
					quotationForm.getErectionCommissionBean().getNoOfManDays());
			callableStatement.setFloat(ItoConstants.COST, quotationForm.getErectionCommissionBean().getPrice());
			//callableStatement.setFloat(ItoConstants.COST_ME,
					//quotationForm.getErectionCommissionBean().getOverwrittenPrice());
			callableStatement.setInt(ItoConstants.COST_ME,
					(int)quotationForm.getErectionCommissionBean().getOverwrittenPrice());
			callableStatement.setInt(ItoConstants.COST_ME_FLG,
					quotationForm.getErectionCommissionBean().isOverwrittenPriceFlag() ? 1 : 0);
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setString(ItoConstants.REMARKS, quotationForm.getErectionCommissionBean().getRemarks());
			callableStatement.setString("SERVICE_REMARKS", quotationForm.getErectionCommissionBean().getServiceRemarks());
			//callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());
			callableStatement.setString(ItoConstants.MOD_BY, Integer.toString(quotationForm.getSaveBasicDetails().getModifiedById()));
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			logger.info("Save EC start2");
			logger.info(quotationForm.getErectionCommissionBean().getTypeOfChargeId());
			logger.info(quotationForm.getErectionCommissionBean().getLoadingId());
			logger.info(quotationForm.getErectionCommissionBean().getLodgingId());
			logger.info(quotationForm.getErectionCommissionBean().getNoOfManDays());
			logger.info(quotationForm.getErectionCommissionBean().getPrice());
			logger.info(quotationForm.getErectionCommissionBean().getOverwrittenPrice());
			logger.info(quotationForm.getErectionCommissionBean().isOverwrittenPriceFlag());
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			logger.info(quotationForm.getErectionCommissionBean().getRemarks());
			logger.info(quotationForm.getErectionCommissionBean().getServiceRemarks());
			logger.info(quotationForm.getSaveBasicDetails().getModifiedById());
			logger.info("Save EC end2");
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}
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
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getPackageData(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetPackage = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_PKG_DATA (?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.PKG_TYP, quotationForm.getSaveBasicDetails().getPackageTypeId());

			callableStatement.execute();

			resultSetPackage = callableStatement.getResultSet();
			while (resultSetPackage.next()) {
				quotationForm.getPackageBean().setCustCode(resultSetPackage.getString(ItoConstants.CUST_TYP));
				quotationForm.getPackageBean().setFrameId(resultSetPackage.getInt(ItoConstants.FRM_ID));
				quotationForm.getPackageBean().setFrameName(resultSetPackage.getString(ItoConstants.FRAME_NAME));
				quotationForm.getPackageBean().setCondensingTypeId(resultSetPackage.getInt(ItoConstants.COND_TYP_ID));
				quotationForm.getPackageBean()
						.setCondensingTypeName(resultSetPackage.getString(ItoConstants.COND_TYP_NAME));
				quotationForm.getPackageBean().setPackageTypeId(resultSetPackage.getInt(ItoConstants.PKG_TYP));
				quotationForm.getPackageBean().setPackageTypeName(resultSetPackage.getString("PKG_TYP_NAME"));
				quotationForm.getPackageBean().setPrice(resultSetPackage.getInt(ItoConstants.PRICE));
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetPackage);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm savePackageData(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetAssignedUser = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_PKG_DATA (?,?,?,?,?,?) }");
			callableStatement.setInt(ItoConstants.PKG_TYP, quotationForm.getPackageBean().getPackageTypeId());
			callableStatement.setFloat(ItoConstants.COST, quotationForm.getPackageBean().getPrice());
			callableStatement.setFloat(ItoConstants.COST_ME, quotationForm.getPackageBean().getOverwrittenPrice());
			callableStatement.setInt(ItoConstants.COST_ME_FLG,
					quotationForm.getPackageBean().getOverwrittenPriceFlag());
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}
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
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetAssignedUser);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm saveProjectCost(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection
					.prepareCall("{ call dbo.PROC_SAVE_PROJECT_COST (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setFloat("SUPPLY_PRICE", quotationForm.getOtherCostsBean().getProjSupply());
			callableStatement.setFloat("SERVICES_PRICE", quotationForm.getOtherCostsBean().getProjSupply());
			callableStatement.setFloat("TRANSPORATION_PRICE", quotationForm.getOtherCostsBean().getProjTransport());
			callableStatement.setFloat("TOTAL_PROJECT_COST_TURBINE",
					quotationForm.getOtherCostsBean().getTotalProjectCost());
			callableStatement.setFloat("SUPPLY_COST", quotationForm.getOtherCostsBean().getSupplyCost());
			callableStatement.setFloat("SERVICES_COST", quotationForm.getOtherCostsBean().getServiceCost());
			callableStatement.setFloat("TRANSP_COST", quotationForm.getOtherCostsBean().getTransCost());
			callableStatement.setFloat("NET_PROFIT", quotationForm.getOtherCostsBean().getTotOrderCostNetProfit());
			callableStatement.setFloat("PERCENT_PROFIT", quotationForm.getOtherCostsBean().getPercentProfit());
			callableStatement.setFloat("ORDER_BOOK_COST_TURBINE",
					quotationForm.getOtherCostsBean().getTurbineOrderBookCost());
			callableStatement.setFloat("TOTAL_FTF_COST", quotationForm.getOtherCostsBean().getTotalFtfCost());
			//callableStatement.setFloat("ADDONCOST", quotationForm.getOtherCostsBean().getAddOnCost());
			callableStatement.setFloat("DBO_MECH_COST", quotationForm.getOtherCostsBean().getDboMechCost());
			callableStatement.setFloat("DBO_ELE_COST", quotationForm.getOtherCostsBean().getDboEleCost());
			callableStatement.setFloat("DBO_CI_COST", quotationForm.getOtherCostsBean().getDboCiCost());
			
			callableStatement.setFloat("PKG_COST", quotationForm.getOtherCostsBean().getPackageCost());
			
			callableStatement.setFloat("VARIABLE_COST", quotationForm.getOtherCostsBean().getTotalVariableCost());
			callableStatement.setFloat(ItoConstants.TOTAL_COST,
					quotationForm.getOtherCostsBean().getTurbineOrderBookCost());
			callableStatement.setInt(ItoConstants.COST_ME_FLG,
					quotationForm.getOtherCostsBean().isProjectNewFlag() ? 1 : 0);
			callableStatement.setFloat(ItoConstants.COST_ME, quotationForm.getOtherCostsBean().getProjectNewCost());
			callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());

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
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}


	@Override
	public QuotationForm saveVariableCost(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall(
					"{ call dbo.PROC_SAVE_VARIABLE_COST (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt("IN_TRAVEL_EXPENSES_REQ",
					quotationForm.getOtherCostsBean().getInTravelExpensesReq());
			callableStatement.setInt("IN_NO_OF_VISIT", quotationForm.getOtherCostsBean().getInNoOfVisit());
			callableStatement.setInt("IN_COST_FOR_EACH_VISIT",
					quotationForm.getOtherCostsBean().getInCostForEachVisit());
			callableStatement.setFloat("SPL_PROVISION", quotationForm.getOtherCostsBean().getSplProvision());
			callableStatement.setFloat("INSURANCE", quotationForm.getOtherCostsBean().getInsurance());
			callableStatement.setFloat("AGENCY_COMMISSION", quotationForm.getOtherCostsBean().getInpAgencyCommission());
			callableStatement.setFloat("AGENCY_COMMISSION_CHRG",
					quotationForm.getOtherCostsBean().getAgencyCommCharges());
			callableStatement.setFloat(ItoConstants.TRAVEL_EXPENSES,
					quotationForm.getOtherCostsBean().getTravelExpenses());
			callableStatement.setFloat(ItoConstants.CONT_FOR_TURBINE,
					quotationForm.getOtherCostsBean().getTurbineContigency());
			callableStatement.setFloat(ItoConstants.CONT_FOR_DBO, quotationForm.getOtherCostsBean().getDboContigency());
			callableStatement.setFloat(ItoConstants.SALES_EXPENSES,
					quotationForm.getOtherCostsBean().getSalesExpenses());
			callableStatement.setFloat(ItoConstants.ENGIN_CHARGES,
					quotationForm.getOtherCostsBean().getEngineCharges());
			callableStatement.setFloat("INTR_NO_OF_MONTHS", quotationForm.getOtherCostsBean().getIntrestNoOfMonths());
			callableStatement.setFloat("OTHR", quotationForm.getOtherCostsBean().getOthers());
			callableStatement.setString("OTHR_REMARKS", quotationForm.getOtherCostsBean().getOthersRemarks());
			callableStatement.setFloat("WR", quotationForm.getOtherCostsBean().getWarrantyPeriod());
			callableStatement.setFloat("IN_PROFIT_PER", quotationForm.getOtherCostsBean().getVarProfit());
			callableStatement.setFloat("IN_LD_DL", quotationForm.getOtherCostsBean().getInpLdforLateDelivery());
			callableStatement.setFloat("IN_LD_PER", quotationForm.getOtherCostsBean().getInpLdPerformance());
			callableStatement.setFloat("IN_INSTR_PER", quotationForm.getOtherCostsBean().getIntrestPercentage());
			callableStatement.setFloat("IN_BG_CHR1", quotationForm.getOtherCostsBean().getInpBG1());
			callableStatement.setFloat("IN_BG_CHR2", quotationForm.getOtherCostsBean().getInpBG2());
			callableStatement.setFloat("ORDER_VALVE", quotationForm.getOtherCostsBean().getTotOrderCost());
			callableStatement.setFloat(ItoConstants.CONT_FOR_GENERAL,
					quotationForm.getOtherCostsBean().getContigencyGeneral());
			callableStatement.setFloat("LD_PERORMANCE", quotationForm.getOtherCostsBean().getLdPerformance());
			callableStatement.setFloat("LD_LA_DEL", quotationForm.getOtherCostsBean().getLdforLateDelivery());
			callableStatement.setFloat("OVR_SALE", quotationForm.getOtherCostsBean().getOvrheadSaleCost());
			callableStatement.setFloat("WARREN", quotationForm.getOtherCostsBean().getWarrantyCost());
			callableStatement.setFloat("INTR_CHARG", quotationForm.getOtherCostsBean().getIntrestCharges());
			callableStatement.setFloat("BG_CHR1", quotationForm.getOtherCostsBean().getBankingCharges1());
			callableStatement.setFloat("BG_CHR2", quotationForm.getOtherCostsBean().getBankingCharges2());
			callableStatement.setFloat("OVR_RAW_MTRL_COST", quotationForm.getOtherCostsBean().getOverRawMaterialCost());
			callableStatement.setFloat(ItoConstants.TOTAL_COST,
					quotationForm.getOtherCostsBean().getTotalVariableCost());
			callableStatement.setInt(ItoConstants.COST_ME_FLG,
					quotationForm.getOtherCostsBean().isVarNewFlag() ? 1 : 0);
			callableStatement.setFloat(ItoConstants.COST_ME, quotationForm.getOtherCostsBean().getVarNewCost());
			callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());

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
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm saveSparesCost(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_SPARES_COST (?,?,?,?,?,?,?,?,?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			// SPARES_COST
			callableStatement.setFloat(ItoConstants.IN_PROFIT_PER,quotationForm.getOtherCostsBean().getInpSparesProfit());
			callableStatement.setFloat(ItoConstants.TOOLS_TACK_COST, quotationForm.getOtherCostsBean().getSparesCost());
			callableStatement.setFloat(ItoConstants.SPARES_COST, quotationForm.getOtherCostsBean().getSparesCost());
			callableStatement.setFloat("OVERD_TURB", quotationForm.getOtherCostsBean().getOvrheadSparesCost());
			callableStatement.setFloat("OVERD_TOTAL_SALE", quotationForm.getOtherCostsBean().getOvrheadTotSaleCost());
			callableStatement.setFloat("ORDER_BOOKING_SPARES",quotationForm.getOtherCostsBean().getOrderBookingSpares());
			callableStatement.setFloat("NET_PROFIT_SPARES", quotationForm.getOtherCostsBean().getSparesNetProfit());
			callableStatement.setFloat("PERCENTT_PROFIT_SPARES", quotationForm.getOtherCostsBean().getSparesProfit());
			callableStatement.setFloat(ItoConstants.TOTAL_COST, quotationForm.getOtherCostsBean().getTotalSparesCost());
			callableStatement.setInt(ItoConstants.COST_ME_FLG,quotationForm.getOtherCostsBean().isSparesNewFlag() ? 1 : 0);
			callableStatement.setFloat(ItoConstants.COST_ME, quotationForm.getOtherCostsBean().getSparesNewCost());
			callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

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
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getProjectCost(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultVarCost = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection
					.prepareCall("{ call dbo.PROC_GET_PROJECT_COST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setFloat(ItoConstants.IN_ORDER_BOOK_PRICE_TURBINE_SPARES,quotationForm.getOtherCostsBean().getInpTurbineOrderBookCost());
			callableStatement.setInt(ItoConstants.MOD_BY, quotationForm.getSaveBasicDetails().getModifiedById());

			// output parameters
			callableStatement.registerOutParameter("TOTAL_PROJECT_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("ORDER_BOOK_PRICE_TURBINE_SPARES", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("NET_PROFIT", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("PERCENT_PROFIT", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("SUPPLY_PRICE", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("SERVICES_PRICE", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("TRANSP_PRICE", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("SUPPLY_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("SERVICES_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("TRANSPORATION_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("TOTAL_FTF_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("DBO_MECH_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("DBO_ELE_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("DBO_CI_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("PKG_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("VARIABLE_COST", java.sql.Types.FLOAT);

			callableStatement.executeUpdate();

			quotationForm.getOtherCostsBean().setTotalProjectCost(callableStatement.getFloat("TOTAL_PROJECT_COST"));
			quotationForm.getOtherCostsBean().setTurbineOrderBookCost(callableStatement.getFloat("ORDER_BOOK_PRICE_TURBINE_SPARES"));
			quotationForm.getOtherCostsBean().setTotOrderCostNetProfit(callableStatement.getFloat("NET_PROFIT"));
			quotationForm.getOtherCostsBean().setPercentProfit(callableStatement.getFloat("PERCENT_PROFIT"));
			quotationForm.getOtherCostsBean().setProjSupply(callableStatement.getFloat("SUPPLY_PRICE"));
			quotationForm.getOtherCostsBean().setProjServices(callableStatement.getFloat("SERVICES_PRICE"));
			quotationForm.getOtherCostsBean().setProjTransport(callableStatement.getFloat("TRANSP_PRICE"));
			quotationForm.getOtherCostsBean().setSupplyCost(callableStatement.getFloat("SUPPLY_COST"));
			quotationForm.getOtherCostsBean().setServiceCost(callableStatement.getFloat("SERVICES_COST"));
			quotationForm.getOtherCostsBean().setTransCost(callableStatement.getFloat("TRANSPORATION_COST"));
			quotationForm.getOtherCostsBean().setTotalFtfCost(callableStatement.getFloat("TOTAL_FTF_COST"));
			quotationForm.getOtherCostsBean().setDboMechCost(callableStatement.getFloat("DBO_MECH_COST"));
			quotationForm.getOtherCostsBean().setDboEleCost(callableStatement.getFloat("DBO_ELE_COST"));
			quotationForm.getOtherCostsBean().setDboCiCost(callableStatement.getFloat("DBO_CI_COST"));
			quotationForm.getOtherCostsBean().setPackageCost(callableStatement.getFloat("PKG_COST"));
			quotationForm.getOtherCostsBean().setTotalVariableCost(callableStatement.getFloat("VARIABLE_COST"));
			
			

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultVarCost);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getSparesCost(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSparesCost1 = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_SPARES_COST (?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setFloat(ItoConstants.TOOLS_TACK_COST, quotationForm.getOtherCostsBean().getToolsTackCost());
			callableStatement.setFloat(ItoConstants.SPARES_COST, quotationForm.getOtherCostsBean().getSparesCost());
			callableStatement.setFloat(ItoConstants.IN_PROFIT_PER,	quotationForm.getOtherCostsBean().getInpSparesProfit());
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
				resultSparesCost1 = callableStatement.getResultSet();
				while (resultSparesCost1.next()) {
					quotationForm.getOtherCostsBean()
							.setOvrheadSparesCost(Math.round(resultSparesCost1.getFloat("Over Heads of Spares cost")));
					quotationForm.getOtherCostsBean().setOvrheadTotSaleCost(
							Math.round(resultSparesCost1.getFloat("Over heads on Total Sale Value")));
					quotationForm.getOtherCostsBean().setTotalSparesCost(
							Math.round(resultSparesCost1.getFloat("TOTAL PROJECT COST for Spares")));
					quotationForm.getOtherCostsBean().setOrderBookingSpares(
							Math.round(resultSparesCost1.getFloat("ORDER BOOKING PRICE for spares")));
					quotationForm.getOtherCostsBean().setSparesNetProfit(resultSparesCost1.getFloat("NET PROFIT"));
				}
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSparesCost1);
		}
		return quotationForm;
	}

	@Override
	public QuotationForm getVariableCost(QuotationForm quotationForm) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultVarCost = null;

		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall(
					"{ call dbo.PROC_GET_VARIABLE_COST (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt("IN_TRAVEL_EXPENSES_REQ",
					quotationForm.getOtherCostsBean().getInTravelExpensesReq());
			callableStatement.setInt("IN_NO_OF_VISIT", quotationForm.getOtherCostsBean().getInNoOfVisit());
			callableStatement.setInt("IN_COST_FOR_EACH_VISIT",
					quotationForm.getOtherCostsBean().getInCostForEachVisit());
			callableStatement.setFloat("SPL_PROVISION", quotationForm.getOtherCostsBean().getSplProvision());
			callableStatement.setFloat("INSURANCE", quotationForm.getOtherCostsBean().getInsurance());
			callableStatement.setFloat(ItoConstants.CONT_FOR_TURBINE,
					quotationForm.getOtherCostsBean().getTurbineContigency());
			callableStatement.setFloat(ItoConstants.CONT_FOR_DBO, quotationForm.getOtherCostsBean().getDboContigency());
			callableStatement.setFloat(ItoConstants.SALES_EXPENSES,
					quotationForm.getOtherCostsBean().getSalesExpenses());
			callableStatement.setFloat("ENGIN_CHARGES", quotationForm.getOtherCostsBean().getEngineCharges());
			callableStatement.setFloat("INTR_NO_OF_MONTHS", quotationForm.getOtherCostsBean().getIntrestNoOfMonths());
			callableStatement.setFloat("OTHR", quotationForm.getOtherCostsBean().getOthers());
			callableStatement.setFloat("WR", quotationForm.getOtherCostsBean().getWarrantyPeriod());
			callableStatement.setFloat("IN_PROFIT_PER", quotationForm.getOtherCostsBean().getVarProfit());
			callableStatement.setFloat("IN_LD_DL", quotationForm.getOtherCostsBean().getInpLdforLateDelivery());
			callableStatement.setFloat("IN_LD_PER", quotationForm.getOtherCostsBean().getInpLdPerformance());
			callableStatement.setFloat("IN_INSTR_PER", quotationForm.getOtherCostsBean().getIntrestPercentage());
			callableStatement.setFloat("IN_BG_CHR1", quotationForm.getOtherCostsBean().getInpBG1());
			callableStatement.setFloat("IN_BG_CHR2", quotationForm.getOtherCostsBean().getInpBG2());

			// output parameters
			callableStatement.registerOutParameter("ORDER_VALVE", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("CONT_FOR_GENERAL", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("LD_PERORMANCE", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("LD_LA_DEL", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("OVR_SALE", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("WARREN", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("INTR_CHARG", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("BG_CHARGE1", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("BG_CHARGE2", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("TRAVEL_EXPENSES", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("TOTAL_VARIABLE_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("OVR_RAW_MTRL_COST", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("AGENCY_COMMISSION", java.sql.Types.FLOAT);
			callableStatement.registerOutParameter("AGENCY_COMMISSION_CHRG", java.sql.Types.FLOAT);

			callableStatement.executeUpdate();

			quotationForm.getOtherCostsBean().setTotOrderCost(callableStatement.getFloat("ORDER_VALVE"));
			quotationForm.getOtherCostsBean().setContigencyGeneral(callableStatement.getFloat("CONT_FOR_GENERAL"));
			quotationForm.getOtherCostsBean().setLdPerformance(callableStatement.getFloat("LD_PERORMANCE"));
			quotationForm.getOtherCostsBean().setLdforLateDelivery(callableStatement.getFloat("LD_LA_DEL"));
			quotationForm.getOtherCostsBean().setOvrheadSaleCost(callableStatement.getFloat("OVR_SALE"));
			quotationForm.getOtherCostsBean().setWarrantyCost(callableStatement.getFloat("WARREN"));
			quotationForm.getOtherCostsBean().setIntrestCharges(callableStatement.getFloat("INTR_CHARG"));
			quotationForm.getOtherCostsBean().setBankingCharges1(callableStatement.getFloat("BG_CHARGE1"));
			quotationForm.getOtherCostsBean().setBankingCharges2(callableStatement.getFloat("BG_CHARGE2"));
			quotationForm.getOtherCostsBean().setTravelExpenses(callableStatement.getFloat("TRAVEL_EXPENSES"));
			quotationForm.getOtherCostsBean().setTotalVariableCost(callableStatement.getFloat("TOTAL_VARIABLE_COST"));
			quotationForm.getOtherCostsBean().setOverRawMaterialCost(callableStatement.getFloat("OVR_RAW_MTRL_COST"));
			quotationForm.getOtherCostsBean().setInpAgencyCommission(callableStatement.getFloat("AGENCY_COMMISSION"));
			quotationForm.getOtherCostsBean()
					.setAgencyCommCharges(callableStatement.getFloat("AGENCY_COMMISSION_CHRG"));

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultVarCost);
		}
		return quotationForm;
	}

	private QuotationForm getQuotationData(CallableStatement callableStatement, QuotationForm quotationForm) {

		int resultOutParameterInt = -1;
		String resultOutParameterString = null;

		ResultSet resultSetMsg = null;
		ResultSet resultSetQuot = null;
		ResultSet resultsetScope = null;
		ResultSet resultSetGeneral = null;
		ResultSet resultSetMechGeneral = null;
		ResultSet resultSetMechAuxGeneral = null;
		ResultSet resultSetOthersItem = null;
		ResultSet resultSetOthersSubItem = null;
		ResultSet resultSetOthersSubItemType = null;
		ResultSet resultSetF2fAddOn = null;
		ResultSet resultSetMechAddOn = null;
		ResultSet resultSetQuestions = null;
		ResultSet resultSetMsg1 = null;
		ResultSet resultSetSap = null;
		ResultSet resultSetF2FExcel = null;
		ResultSet resultSetSave = null;
		ResultSet resultSetmechExtScp = null;
		ResultSet resultSetEleGeneral = null;
		ResultSet resultSetEleOther = null;
		ResultSet resultSetEleSub2 = null;
		ResultSet resultSetEleExtScope = null;
		ResultSet resultSetEleExtScope1= null;
		
		ResultSet resultSetEleCIExtScope = null;
		ResultSet resultSetSaveEleFilter = null;
		ResultSet resultSetMechAuxGeneralNew = null;
		ResultSet resultSetEleGeneralSpecial = null;
		ResultSet resultSetEleInstrFilterList = null;
		ResultSet resultSetOtherChapterData = null;
		ResultSet resultSetAttachmentData = null;
		ResultSet resultSetWord811 = null;
		ResultSet resultSetEleInstrFilter = null;
		ResultSet resultSetEleVmsData = null;
		ResultSet resultSetPerform1 = null;
		ResultSet resultSetFixedData = null;
		ResultSet resultSetIdentData = null;
		ResultSet resultSetItem1Data = null;
		ResultSet resultSetCirData = null;
		ResultSet resultSetPerform2 = null;
		ResultSet resultSetComercialData = null;
		ResultSet resultSetSavedComercialData1 = null;
		ResultSet resultSetSavedComercialData2 = null;

		ResultSet resultSetPerformData3 = null;
		ResultSet resultSetOtherGetData = null;

		try {
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				quotationForm.setSuccessCode(resultOutParameterInt);
				quotationForm.setSuccessMsg(resultOutParameterString);
				quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
			}

			// set basic details(customer requirements in saveBasicDetailsBean)
			// and Customer Data Starts

			if (resultOutParameterInt == 0) {
				if (callableStatement.getMoreResults()) {
					resultSetQuot = callableStatement.getResultSet();
					getCustomerData(quotationForm, resultSetQuot);
				}
				// set scope of Supply Details Starts
				List<ScopeOfSupply> scopeList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultsetScope = callableStatement.getResultSet();
					while (resultsetScope.next()) {
						ScopeOfSupply supply = new ScopeOfSupply();
						supply.setScopeId(resultsetScope.getInt("SCOP_ID"));
						supply.setQuotID(resultsetScope.getInt(ItoConstants.QUOT_ID));
						supply.setSsId(resultsetScope.getInt(ItoConstants.SS_ID));
						supply.setScopeName(resultsetScope.getString(ItoConstants.SCOPE_OF_SUPPLY));
						supply.setScopeCode(resultsetScope.getString("SCOPE_OF_SUPPLY_CD"));
						supply.setStatus(resultsetScope.getString(ItoConstants.STATUS));
						supply.setCreatedDate(resultsetScope.getString(ItoConstants.CREAT_DT));
						supply.setModifiedDate(resultsetScope.getString(ItoConstants.MOD_DT));
						supply.setCreatedBy(resultsetScope.getString(ItoConstants.CREAT_BY));
						supply.setModifiedBy(resultsetScope.getString(ItoConstants.MOD_BY));
						supply.setCreatedById(resultsetScope.getInt(ItoConstants.CREAT_BY_ID));
						supply.setModifiedById(resultsetScope.getInt(ItoConstants.MOD_BY_ID));

						scopeList.add(supply);
					}
					quotationForm.setScopeOfSupplyList(scopeList);
				}

				// set scope of Supply Details Ends

				// set general Details Starts

				List<DBOBean> savedList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetSave = callableStatement.getResultSet();
					while (resultSetSave.next()) {
						DBOBean bean = new DBOBean();
						bean.setQuotGenId(resultSetSave.getInt("QUOT_GEN_ID"));
						bean.setGenInId(resultSetSave.getInt("GEN_IN_ID"));
						bean.setGroupCode(resultSetSave.getString("GRP_CD"));
						bean.setGenType(resultSetSave.getString("GEN_TYPE"));
						bean.setGenInNo(resultSetSave.getInt("GEN_IN_NO"));
						bean.setGenInCd(resultSetSave.getString("GEN_IN_CD"));
						bean.setGenInNm(resultSetSave.getString("GEN_IN_NM"));
						bean.setCategoryValName(resultSetSave.getString("CAT_VAL_NM"));
						bean.setCategoryValCode(resultSetSave.getString("CAT_VAL_CD"));
						bean.setDefaultVal(resultSetSave.getInt("DEFLT_FLG") == 1 ? true : false);
						bean.setQuantity(resultSetSave.getInt("QTY"));
						bean.setGroupDescription(resultSetSave.getString("GRP_DESC"));
						bean.setDispInd(resultSetSave.getInt("DISP_IND"));
						savedList.add(bean);
					}
					quotationForm.setSavedList(savedList);
				}

				// f2f

				List<DBOBean> f2fGeneralList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetGeneral = callableStatement.getResultSet();
					while (resultSetGeneral.next()) {
						DBOBean bean = new DBOBean();
						bean.setF2fItemId(resultSetGeneral.getInt("F2F_ITEM_ID"));
						bean.setDetQuotId(resultSetGeneral.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetGeneral.getInt("ITEM_ID"));
						bean.setItemName(resultSetGeneral.getString("ITEM_NM"));
						bean.setSubItemId(resultSetGeneral.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetGeneral.getString("SUB_ITEM_NM"));
						bean.setSubItemTypeId(resultSetGeneral.getInt("SUB_ITEM_TYP_ID"));
						bean.setSubItemTypeName(resultSetGeneral.getString("SUB_ITEM_TYP_NM"));
						bean.setColId(resultSetGeneral.getInt("COL_ID"));
						bean.setColNm(resultSetGeneral.getString("COL_NM"));
						bean.setColValCd(resultSetGeneral.getString("COL_VAL_CD"));
						bean.setLhsFlag(resultSetGeneral.getInt("LHS_FLG"));
						bean.setRhsFlag(resultSetGeneral.getInt("RHS_FLG") == 1 ? true : false);
						bean.setRhsCost(Math.round(resultSetGeneral.getFloat("RHS_COST")));
						bean.setRhsColQuantity(resultSetGeneral.getFloat("RHS_COL_QTY"));
						bean.setRhsColTechcomments(resultSetGeneral.getString("RHS_COL_TECH_COMMENTS"));
						bean.setRhsColComrcomments(resultSetGeneral.getString("RHS_COL_COMR_COMMENTS"));
						bean.setDefaultVal(resultSetGeneral.getInt("DEFLT_FLG") == 1 ? true : false);
						bean.setBasicCost(Math.round(resultSetGeneral.getFloat("BASIC_COST")));
						bean.setOthersCost(Math.round(resultSetGeneral.getFloat("OTHERS_COST")));
						bean.setAddOnCost(Math.round(resultSetGeneral.getFloat("ADD_ON_COST")));
						bean.setItemCost(Math.round(resultSetGeneral.getFloat("ITEM_COST")));
						bean.setTotalPrice(Math.round(resultSetGeneral.getFloat(ItoConstants.TOTAL_COST)));
						bean.setTechFlag(resultSetGeneral.getInt("TECH_FLG"));
						bean.setComrFlag(resultSetGeneral.getInt("COMR_FLG"));
						bean.setOverwrittenPriceFlag(
								resultSetGeneral.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetGeneral.getFloat(ItoConstants.COST_ME)));
						bean.setQuantity(resultSetGeneral.getInt("QTY"));
						bean.setTechRemarks(resultSetGeneral.getString("TECH_REMARKS"));
						bean.setComrRemarks(resultSetGeneral.getString("COMR_REMARKS"));
						bean.setTechComments(resultSetGeneral.getString("TECH_COMMENTS"));
						bean.setComrComments(resultSetGeneral.getString("COMR_COMMENTS"));
						bean.setAddOnNewColFlag(resultSetGeneral.getInt("ADD_ON_NEW_COL_FLG"));
						bean.setAddOnFlag(resultSetGeneral.getInt("ADD_ON_FLG") == 1 ? true : false);
						bean.setSubItemCost(Math.round(resultSetGeneral.getFloat("SUB_ITEM_COST")));
						bean.setSubItemTechRemarks(resultSetGeneral.getString("SUB_ITEM_TECH_COMMENTS"));
						bean.setSubItemComrRemarks(resultSetGeneral.getString("SUB_ITEM_COMR_COMMENTS"));
						bean.setSubItemTypeCost(Math.round(resultSetGeneral.getFloat("SUB_ITEM_TYP_COST")));
						bean.setSubItemTypeTechRemarks(resultSetGeneral.getString("SUB_ITEM_TYP_TECH_COMMENTS"));
						bean.setSubItemTypeComrRemarks(resultSetGeneral.getString("SUB_ITEM_TYP_COMR_COMMENTS"));
						bean.setOthersFlag(resultSetGeneral.getInt("OTHERS_FLG") == 1 ? true : false);
						bean.setApproxCostFlag(resultSetGeneral.getInt("APPROX_COST_FLG"));
						bean.setItemApproxCostFlag(resultSetGeneral.getInt("ITEM_APPROX_COST_FLG"));
						bean.setColType(resultSetGeneral.getString("COL_TYPE"));
						bean.setDiscountPer(Math.round(resultSetGeneral.getFloat("DISCOUNT_PER")));
						bean.setNonDiscountCost(Math.round(resultSetGeneral.getFloat("NON_DISCOUNT_COST")));
						f2fGeneralList.add(bean);

					}

					quotationForm.setF2fGeneralList(f2fGeneralList);
				}

				List<DBOBean> f2fOthersItemList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetOthersItem = callableStatement.getResultSet();
					while (resultSetOthersItem.next()) {
						DBOBean bean = new DBOBean();

						bean.setDetQuotId(resultSetOthersItem.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetOthersItem.getInt("ITEM_ID"));
						bean.setItemName(resultSetOthersItem.getString("ITEM_NM"));
						bean.setSubItemId(resultSetOthersItem.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetOthersItem.getString("SUB_ITEM_NM"));
						bean.setSubItemTypeId(resultSetOthersItem.getInt("SUB_ITEM_TYP_ID"));
						bean.setSubItemTypeName(resultSetOthersItem.getString("SUB_ITEM_TYP_NM"));
						bean.setColId(resultSetOthersItem.getInt("COL_ID"));
						bean.setColNm(resultSetOthersItem.getString("COL_NM"));
						bean.setColValCd(resultSetOthersItem.getString("COL_VAL_CD"));
						bean.setRhsCost(Math.round(resultSetOthersItem.getFloat("RHS_COST")));
						bean.setRhsColQuantity(resultSetOthersItem.getFloat("RHS_COL_QTY"));
						bean.setRhsColTechcomments(resultSetOthersItem.getString("RHS_COL_TECH_COMMENTS"));
						bean.setRhsColComrcomments(resultSetOthersItem.getString("RHS_COL_COMR_COMMENTS"));
						bean.setBasicCost(Math.round(resultSetOthersItem.getFloat("BASIC_COST")));
						bean.setOthersCost(Math.round(resultSetOthersItem.getFloat("OTHERS_COST")));
						bean.setTotalPrice(Math.round(resultSetOthersItem.getFloat(ItoConstants.TOTAL_COST)));
						bean.setOverwrittenPriceFlag(
								resultSetOthersItem.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetOthersItem.getFloat(ItoConstants.COST_ME)));
						bean.setQuantity(resultSetOthersItem.getInt("QTY"));
						bean.setTechComments(resultSetOthersItem.getString("TECH_REMARKS"));
						bean.setComrComments(resultSetOthersItem.getString("COMR_REMARKS"));
						bean.setTechRemarks(resultSetOthersItem.getString("TECH_COMMENTS"));
						bean.setComrRemarks(resultSetOthersItem.getString("COMR_COMMENTS"));
						bean.setOthersFlag(resultSetOthersItem.getInt("OTHERS_FLG") == 1 ? true : false);
						bean.setAddOnFlag(resultSetOthersItem.getInt("ADD_ON_FLG") == 1 ? true : false);

						f2fOthersItemList.add(bean);

					}

					quotationForm.setF2fOthersItemList(f2fOthersItemList);
				}

				List<DBOBean> f2fOthersSubItemList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetOthersSubItem = callableStatement.getResultSet();
					while (resultSetOthersSubItem.next()) {
						DBOBean bean = new DBOBean();

						bean.setDetQuotId(resultSetOthersSubItem.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetOthersSubItem.getInt("ITEM_ID"));
						bean.setItemName(resultSetOthersSubItem.getString("ITEM_NM"));
						bean.setSubItemId(resultSetOthersSubItem.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetOthersSubItem.getString("SUB_ITEM_NM"));
						bean.setSubItemTypeId(resultSetOthersSubItem.getInt("SUB_ITEM_TYP_ID"));
						bean.setSubItemTypeName(resultSetOthersSubItem.getString("SUB_ITEM_TYP_NM"));
						bean.setColId(resultSetOthersSubItem.getInt("COL_ID"));
						bean.setColNm(resultSetOthersSubItem.getString("COL_NM"));
						bean.setColValCd(resultSetOthersSubItem.getString("COL_VAL_CD"));
						bean.setRhsCost(Math.round(resultSetOthersSubItem.getFloat("RHS_COST")));
						bean.setRhsColQuantity(resultSetOthersSubItem.getFloat("RHS_COL_QTY"));
						bean.setRhsColTechcomments(resultSetOthersSubItem.getString("RHS_COL_TECH_COMMENTS"));
						bean.setRhsColComrcomments(resultSetOthersSubItem.getString("RHS_COL_COMR_COMMENTS"));
						bean.setBasicCost(Math.round(resultSetOthersSubItem.getFloat("BASIC_COST")));
						bean.setOthersCost(Math.round(resultSetOthersSubItem.getFloat("OTHERS_COST")));
						bean.setTotalPrice(Math.round(resultSetOthersSubItem.getFloat(ItoConstants.TOTAL_COST)));
						bean.setOverwrittenPriceFlag(
								resultSetOthersSubItem.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetOthersSubItem.getFloat(ItoConstants.COST_ME)));
						bean.setQuantity(resultSetOthersSubItem.getInt("QTY"));
						bean.setTechComments(resultSetOthersSubItem.getString("TECH_REMARKS"));
						bean.setComrComments(resultSetOthersSubItem.getString("COMR_REMARKS"));
						bean.setTechRemarks(resultSetOthersSubItem.getString("TECH_COMMENTS"));
						bean.setComrRemarks(resultSetOthersSubItem.getString("COMR_COMMENTS"));
						bean.setOthersFlag(resultSetOthersSubItem.getInt("OTHERS_FLG") == 1 ? true : false);
						bean.setAddOnFlag(resultSetOthersSubItem.getInt("ADD_ON_FLG") == 1 ? true : false);
						bean.setSubItemCost(Math.round(resultSetOthersSubItem.getFloat("SUB_ITEM_COST")));
						bean.setSubItemTechRemarks(resultSetOthersSubItem.getString("SUB_ITEM_TECH_COMMENTS"));
						bean.setSubItemComrRemarks(resultSetOthersSubItem.getString("SUB_ITEM_COMR_COMMENTS"));
						bean.setItemCost(Math.round(resultSetOthersSubItem.getFloat("ITEM_COST")));
						bean.setDiscountPer(Math.round(resultSetOthersSubItem.getFloat("DISCOUNT_PER")));
						bean.setNonDiscountCost(Math.round(resultSetOthersSubItem.getFloat("NON_DISCOUNT_COST")));
						f2fOthersSubItemList.add(bean);

					}

					quotationForm.setF2fOthersSubItemList(f2fOthersSubItemList);
				}

				List<DBOBean> f2fOthersSubItemTypeList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetOthersSubItemType = callableStatement.getResultSet();
					while (resultSetOthersSubItemType.next()) {
						DBOBean bean = new DBOBean();

						bean.setDetQuotId(resultSetOthersSubItemType.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetOthersSubItemType.getInt("ITEM_ID"));
						bean.setItemName(resultSetOthersSubItemType.getString("ITEM_NM"));
						bean.setSubItemId(resultSetOthersSubItemType.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetOthersSubItemType.getString("SUB_ITEM_NM"));
						bean.setSubItemTypeId(resultSetOthersSubItemType.getInt("SUB_ITEM_TYP_ID"));
						bean.setSubItemTypeName(resultSetOthersSubItemType.getString("SUB_ITEM_TYP_NM"));
						bean.setColId(resultSetOthersSubItemType.getInt("COL_ID"));
						bean.setColNm(resultSetOthersSubItemType.getString("COL_NM"));
						bean.setColValCd(resultSetOthersSubItemType.getString("COL_VAL_CD"));
						bean.setRhsCost(Math.round(resultSetOthersSubItemType.getFloat("RHS_COST")));
						bean.setRhsColQuantity(resultSetOthersSubItemType.getFloat("RHS_COL_QTY"));
						bean.setRhsColTechcomments(resultSetOthersSubItemType.getString("RHS_COL_TECH_COMMENTS"));
						bean.setRhsColComrcomments(resultSetOthersSubItemType.getString("RHS_COL_COMR_COMMENTS"));
						bean.setBasicCost(Math.round(resultSetOthersSubItemType.getFloat("BASIC_COST")));
						bean.setOthersCost(Math.round(resultSetOthersSubItemType.getFloat("OTHERS_COST")));
						bean.setTotalPrice(Math.round(resultSetOthersSubItemType.getFloat(ItoConstants.TOTAL_COST)));
						bean.setOverwrittenPriceFlag(
								resultSetOthersSubItemType.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetOthersSubItemType.getFloat(ItoConstants.COST_ME)));
						bean.setQuantity(resultSetOthersSubItemType.getInt("QTY"));
						bean.setTechComments(resultSetOthersSubItemType.getString("TECH_REMARKS"));
						bean.setComrComments(resultSetOthersSubItemType.getString("COMR_REMARKS"));
						bean.setTechRemarks(resultSetOthersSubItemType.getString("TECH_COMMENTS"));
						bean.setComrRemarks(resultSetOthersSubItemType.getString("COMR_COMMENTS"));
						bean.setOthersFlag(resultSetOthersSubItemType.getInt("OTHERS_FLG") == 1 ? true : false);
						bean.setAddOnFlag(resultSetOthersSubItemType.getInt("ADD_ON_FLG") == 1 ? true : false);
						bean.setSubItemTypeCost(Math.round(resultSetOthersSubItemType.getFloat("SUB_ITEM_TYP_COST")));
						bean.setSubItemTypeTechRemarks(
								resultSetOthersSubItemType.getString("SUB_ITEM_TYP_TECH_COMMENTS"));
						bean.setSubItemTypeComrRemarks(
								resultSetOthersSubItemType.getString("SUB_ITEM_TYP_COMR_COMMENTS"));
						f2fOthersSubItemTypeList.add(bean);

					}

					quotationForm.setF2fOthersSubItemTypeList(f2fOthersSubItemTypeList);
				}

				// mech general

				List<DBOBean> mechGeneralList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetMechGeneral = callableStatement.getResultSet();
					while (resultSetMechGeneral.next()) {
						DBOBean bean = new DBOBean();
						bean.setMechItemId(resultSetMechGeneral.getInt("MECH_ITEM_ID"));
						bean.setDetQuotId(resultSetMechGeneral.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetMechGeneral.getInt("ITEM_ID"));
						bean.setItemName(resultSetMechGeneral.getString("ITEM_NM"));
						bean.setSubItemId(resultSetMechGeneral.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetMechGeneral.getString("SUB_ITEM_NM"));
						bean.setColId(resultSetMechGeneral.getInt("COL_ID"));
						bean.setColNm(resultSetMechGeneral.getString("COL_NM"));
						bean.setColValCd(resultSetMechGeneral.getString("COL_VAL_CD"));
						bean.setLhsFlag(resultSetMechGeneral.getInt("LHS_FLG"));
						// bean.setRhsFlag(resultSetMechGeneral.getInt("RHS_FLG")
						// == 1 ? true : false);
						bean.setRhsCost(Math.round(resultSetMechGeneral.getFloat("RHS_COST")));
						bean.setRhsColQuantity(resultSetMechGeneral.getFloat("RHS_COL_QTY"));
						bean.setRhsColTechcomments(resultSetMechGeneral.getString("RHS_COL_TECH_COMMENTS"));
						bean.setRhsColComrcomments(resultSetMechGeneral.getString("RHS_COL_COMR_COMMENTS"));
						bean.setDefaultVal(resultSetMechGeneral.getInt("DEFLT_FLG") == 1 ? true : false);
						bean.setDispInd(resultSetMechGeneral.getInt("DISP_IND"));
						bean.setItemCost(Math.round(resultSetMechGeneral.getFloat("ITEM_COST")));
						bean.setBasicCost(Math.round(resultSetMechGeneral.getFloat("BASIC_COST")));
						bean.setSubItemCost(Math.round(resultSetMechGeneral.getFloat("SUB_ITEM_COST")));
						bean.setAddOnCost(Math.round(resultSetMechGeneral.getFloat("ADD_ON_COST")));
						bean.setTotalPrice(Math.round(resultSetMechGeneral.getFloat(ItoConstants.TOTAL_COST)));
						bean.setTechFlag(resultSetMechGeneral.getInt("TECH_FLG"));
						bean.setComrFlag(resultSetMechGeneral.getInt("COMR_FLG"));
						bean.setOverwrittenPriceFlag(
								resultSetMechGeneral.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetMechGeneral.getFloat(ItoConstants.COST_ME)));
						bean.setQuantity(resultSetMechGeneral.getInt("QTY"));
						bean.setTechRemarks(resultSetMechGeneral.getString("TECH_REMARKS"));
						bean.setComrRemarks(resultSetMechGeneral.getString("COMR_REMARKS"));
						bean.setTechComments(resultSetMechGeneral.getString("TECH_COMMENTS"));
						bean.setComrComments(resultSetMechGeneral.getString("COMR_COMMENTS"));
						bean.setAddOnNewColFlag(resultSetMechGeneral.getInt("ADD_ON_NEW_COL_FLG"));
						bean.setSubItemTechRemarks(resultSetMechGeneral.getString("SUB_ITEM_TECH_COMMENTS"));
						bean.setSubItemComrRemarks(resultSetMechGeneral.getString("SUB_ITEM_COMR_COMMENTS"));
						bean.setApproxCostFlag(resultSetMechGeneral.getInt("APPROX_COST_FLG"));
						bean.setItemApproxCostFlag(resultSetMechGeneral.getInt("ITEM_APPROX_COST_FLG"));
						bean.setColType(resultSetMechGeneral.getString("COL_TYPE"));
						bean.setTotalMechCost(Math.round(resultSetMechGeneral.getFloat("TOTAL_MECH_COST")));
						bean.setDiscountPer(Math.round(resultSetMechGeneral.getFloat("DISCOUNT_PER")));
						bean.setNonDiscountCost(Math.round(resultSetMechGeneral.getFloat("NON_DISCOUNT_COST")));
						mechGeneralList.add(bean);

					}

					quotationForm.setMechGeneralList(mechGeneralList);
				}

				List<DBOBean> mechAuxGeneralList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetMechAuxGeneral = callableStatement.getResultSet();

					while (resultSetMechAuxGeneral.next()) {
						DBOBean bean = new DBOBean();
						bean.setMechItemId(resultSetMechAuxGeneral.getInt("MECH_ITEM_ID"));
						bean.setDetQuotId(resultSetMechAuxGeneral.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetMechAuxGeneral.getInt("ITEM_ID"));
						bean.setItemName(resultSetMechAuxGeneral.getString("ITEM_NM"));
						bean.setSubItemId(resultSetMechAuxGeneral.getInt("SUB_ITEM_ID"));

						bean.setColId(resultSetMechAuxGeneral.getInt("COL_ID"));
						bean.setColNm(resultSetMechAuxGeneral.getString("COL_NM"));
						bean.setColValCd(resultSetMechAuxGeneral.getString("COL_VAL_CD"));
						bean.setLhsFlag(resultSetMechAuxGeneral.getInt("LHS_FLG"));

						bean.setRhsCost(Math.round(resultSetMechAuxGeneral.getFloat("RHS_COST")));
						bean.setRhsColQuantity(resultSetMechAuxGeneral.getFloat("RHS_COL_QTY"));
						bean.setRhsColTechcomments(resultSetMechAuxGeneral.getString("RHS_COL_TECH_COMMENTS"));
						bean.setRhsColComrcomments(resultSetMechAuxGeneral.getString("RHS_COL_COMR_COMMENTS"));
						bean.setDefaultVal(resultSetMechAuxGeneral.getInt("DEFLT_FLG") == 1 ? true : false);
						bean.setItemCost(Math.round(resultSetMechAuxGeneral.getFloat("ITEM_COST")));
						bean.setBasicCost(Math.round(resultSetMechAuxGeneral.getFloat("BASIC_COST")));
						bean.setSubItemCost(Math.round(resultSetMechAuxGeneral.getFloat("SUB_ITEM_COST")));
						bean.setAddOnCost(Math.round(resultSetMechAuxGeneral.getFloat("ADD_ON_COST")));
						bean.setTotalPrice(Math.round(resultSetMechAuxGeneral.getFloat(ItoConstants.TOTAL_COST)));
						bean.setTechFlag(resultSetMechAuxGeneral.getInt("TECH_FLG"));
						bean.setComrFlag(resultSetMechAuxGeneral.getInt("COMR_FLG"));
						bean.setOverwrittenPriceFlag(
								resultSetMechAuxGeneral.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetMechAuxGeneral.getFloat(ItoConstants.COST_ME)));
						bean.setQuantity(resultSetMechAuxGeneral.getInt("QTY"));
						bean.setTechRemarks(resultSetMechAuxGeneral.getString("TECH_REMARKS"));
						bean.setComrRemarks(resultSetMechAuxGeneral.getString("COMR_REMARKS"));
						bean.setTechComments(resultSetMechAuxGeneral.getString("TECH_COMMENTS"));
						bean.setComrComments(resultSetMechAuxGeneral.getString("COMR_COMMENTS"));
						bean.setAddOnNewColFlag(resultSetMechAuxGeneral.getInt("ADD_ON_NEW_COL_FLG"));
						bean.setSubItemTechRemarks(resultSetMechAuxGeneral.getString("SUB_ITEM_TECH_COMMENTS"));
						bean.setSubItemComrRemarks(resultSetMechAuxGeneral.getString("SUB_ITEM_COMR_COMMENTS"));
						bean.setOthersFlag(resultSetMechAuxGeneral.getInt("OTHERS_FLG") == 1 ? true : false);
						bean.setApproxCostFlag(resultSetMechAuxGeneral.getInt("APPROX_COST_FLG"));
						bean.setItemApproxCostFlag(resultSetMechAuxGeneral.getInt("ITEM_APPROX_COST_FLG"));
						bean.setColType(resultSetMechAuxGeneral.getString("COL_TYPE"));
						bean.setDiscountPer(Math.round(resultSetMechAuxGeneral.getFloat("DISCOUNT_PER")));
						bean.setNonDiscountCost(Math.round(resultSetMechAuxGeneral.getFloat("NON_DISCOUNT_COST")));
						mechAuxGeneralList.add(bean);

					}
					quotationForm.setMechAuxGeneralList(mechAuxGeneralList);
				}

				List<DBOBean> mechAuxGeneralListNew = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetMechAuxGeneralNew = callableStatement.getResultSet();

					while (resultSetMechAuxGeneralNew.next()) {
						DBOBean bean = new DBOBean();

						bean.setDetQuotId(resultSetMechAuxGeneralNew.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetMechAuxGeneralNew.getInt("ITEM_ID"));
						bean.setItemName(resultSetMechAuxGeneralNew.getString("ITEM_NM"));
						bean.setSubItemId(resultSetMechAuxGeneralNew.getInt("SUB_ITEM_ID"));
						bean.setColId(resultSetMechAuxGeneralNew.getInt("COL_ID"));
						bean.setColNm(resultSetMechAuxGeneralNew.getString("COL_NM"));
						bean.setColValCd(resultSetMechAuxGeneralNew.getString("COL_VAL_CD"));
						bean.setLhsFlag(resultSetMechAuxGeneralNew.getInt("LHS_FLG"));
						bean.setRhsCost(Math.round(resultSetMechAuxGeneralNew.getFloat("RHS_COST")));
						bean.setRhsColQuantity(resultSetMechAuxGeneralNew.getFloat("RHS_COL_QTY"));
						bean.setRhsColTechcomments(resultSetMechAuxGeneralNew.getString("RHS_COL_TECH_COMMENTS"));
						bean.setRhsColComrcomments(resultSetMechAuxGeneralNew.getString("RHS_COL_COMR_COMMENTS"));
						bean.setBasicCost(Math.round(resultSetMechAuxGeneralNew.getFloat("BASIC_COST")));
						bean.setSubItemCost(Math.round(resultSetMechAuxGeneralNew.getFloat("SUB_ITEM_COST")));
						bean.setAddOnCost(Math.round(resultSetMechAuxGeneralNew.getFloat("ADD_ON_COST")));
						bean.setTotalPrice(Math.round(resultSetMechAuxGeneralNew.getFloat(ItoConstants.TOTAL_COST)));
						bean.setOverwrittenPriceFlag(
								resultSetMechAuxGeneralNew.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetMechAuxGeneralNew.getFloat(ItoConstants.COST_ME)));
						bean.setQuantity(resultSetMechAuxGeneralNew.getInt("QTY"));
						bean.setTechRemarks(resultSetMechAuxGeneralNew.getString("TECH_REMARKS"));
						bean.setComrRemarks(resultSetMechAuxGeneralNew.getString("COMR_REMARKS"));
						bean.setTechComments(resultSetMechAuxGeneralNew.getString("TECH_COMMENTS"));
						bean.setComrComments(resultSetMechAuxGeneralNew.getString("COMR_COMMENTS"));
						bean.setAddOnNewColFlag(resultSetMechAuxGeneralNew.getInt("ADD_ON_NEW_COL_FLG"));
						bean.setSubItemTechRemarks(resultSetMechAuxGeneralNew.getString("SUB_ITEM_TECH_COMMENTS"));
						bean.setSubItemComrRemarks(resultSetMechAuxGeneralNew.getString("SUB_ITEM_COMR_COMMENTS"));
						bean.setOthersFlag(resultSetMechAuxGeneralNew.getInt("OTHERS_FLG") == 1 ? true : false);

						mechAuxGeneralListNew.add(bean);

					}
					quotationForm.setMechAuxGeneralListNew(mechAuxGeneralListNew);
				}

				List<DBOBean> mechExtScpList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetmechExtScp = callableStatement.getResultSet();
					while (resultSetmechExtScp.next()) {
						DBOBean bean = new DBOBean();
						bean.setItemId(resultSetmechExtScp.getInt("ITEM_ID"));
						bean.setItemName(resultSetmechExtScp.getString("ITEM_NM"));

						bean.setColId(resultSetmechExtScp.getInt("COL_ID"));
						bean.setColNm(resultSetmechExtScp.getString("COL_NM"));
						bean.setColValCd(resultSetmechExtScp.getString("COL_VAL_CD"));
						bean.setLhsFlag(resultSetmechExtScp.getInt("LHS_FLG"));

						bean.setExtScopeCost(Math.round(resultSetmechExtScp.getFloat("EXT_SCOPE_COST")));
						bean.setMechTotalExtCost(Math.round(resultSetmechExtScp.getFloat("MECH_TOTAL_EXT_COST")));
						bean.setQuantity(resultSetmechExtScp.getInt("QTY"));
						bean.setTechRemarks(resultSetmechExtScp.getString("TECH_REMARKS"));
						bean.setComrRemarks(resultSetmechExtScp.getString("COMR_REMARKS"));
						bean.setMechTechComments(resultSetmechExtScp.getString("MECH_TECH_COMMENTS"));
						bean.setMechComrComments(resultSetmechExtScp.getString("MECH_COMR_COMMENTS"));

						mechExtScpList.add(bean);
					}
					quotationForm.setMechExtScpList(mechExtScpList);
				}

				// ////mech addon
				//
				// List<DBOBean> mechAddOnList1 = new ArrayList<>();
				// if (callableStatement.getMoreResults()) {
				// resultSetMechAddOn = callableStatement.getResultSet();
				// while (resultSetMechAddOn.next()) {
				// DBOBean bean = new DBOBean();
				//
				//
				// bean.setMechItemId(resultSetMechAddOn.getInt("MECH_ITEM_ID"));
				// bean.setDetQuotId(resultSetMechAddOn.getInt("DET_QUOT_ID"));
				// bean.setItemId(resultSetMechAddOn.getInt("ITEM_ID"));
				// bean.setItemName(resultSetMechAddOn.getString("ITEM_NM"));
				// bean.setSubItemId(resultSetMechAddOn.getInt("SUB_ITEM_ID"));
				// bean.setSubItemName(resultSetMechAddOn.getString("SUB_ITEM_NM"));
				// bean.setColId(resultSetMechAddOn.getInt("COL_ID"));
				// bean.setColNm(resultSetMechAddOn.getString("COL_NM"));
				// bean.setColValCd(resultSetMechAddOn.getString("COL_VAL_CD"));
				// bean.setRhsCost(Math.round(resultSetMechAddOn.getFloat("RHS_COST")));
				// bean.setRhsColQuantity(resultSetMechAddOn.getFloat("RHS_COL_QTY"));
				// bean.setRhsColTechcomments(resultSetMechAddOn.getString("RHS_COL_TECH_COMMENTS"));
				// bean.setRhsColComrcomments(resultSetMechAddOn.getString("RHS_COL_COMR_COMMENTS"));
				// bean.setAddOnCostMeFlag(resultSetMechAddOn.getInt("ADD_ON_COST_ME_FLG"));
				// bean.setOthColValFlag(resultSetMechAddOn.getInt("OTHR_COL_VAL_FLG"));
				// bean.setOthersCost(Math.round(resultSetMechAddOn.getFloat("OTHERS_COST")));
				// bean.setItemCost(Math.round(resultSetMechAddOn.getFloat("ITEM_COST")));
				// bean.setTotalPrice(Math.round(resultSetMechAddOn.getFloat(ItoConstants.TOTAL_COST)));
				// bean.setTechFlag(resultSetMechAddOn.getInt("TECH_FLG"));
				// bean.setComrFlag(resultSetMechAddOn.getInt("COMR_FLG"));
				// bean.setAddOnFlag(resultSetMechAddOn.getInt("ADD_ON_FLG") ==
				// 1 ? true : false);
				// mechAddOnList1.add(bean);
				//
				// }
				//
				// quotationForm.setMechAddOnList1(mechAddOnList1);
				// }

				// set F2f Sap Question Details Starts
				// List<QuestionsEntity> questionsEntityList = new
				// ArrayList<>();
				//
				// if (callableStatement.getMoreResults()) {
				// resultSetQuestions = callableStatement.getResultSet();
				// while (resultSetQuestions.next()) {s
				// QuestionsEntity bean1 = new QuestionsEntity();
				//
				// bean1.setQuestionId(resultSetQuestions.getInt(ItoConstants.QUST_ID));
				// bean1.setQuestionDesc(resultSetQuestions.getString("QUST_NM"));
				// bean1.setQuestionAnswerId(resultSetQuestions.getInt(ItoConstants.ANS_ID));
				// bean1.setAnswerCd(resultSetQuestions.getString(ItoConstants.ANS_CD));
				// bean1.setAnswerDesc(resultSetQuestions.getString("ANS_NM") +
				// " (TTL STD)");
				// questionsEntityList.add(bean1);
				// }
				// quotationForm.setSelectedQuestionAnswerSet(questionsEntityList);
				// }
				// set F2f Sap Question Details Ends

				// Add On Components
				// List<AddOnComponent> selAddOnCompList = new ArrayList<>();
				// ResultSet resultSetData6 = null;
				// if (callableStatement.getMoreResults()) {
				// resultSetData6 = callableStatement.getResultSet();
				// while (resultSetData6.next()) {
				// AddOnComponent addOn = new AddOnComponent();
				//
				// addOn.setCompoId(resultSetData6.getInt(ItoConstants.COMP_DET_ID));
				// addOn.setAddOnCompoMastId(resultSetData6.getInt(ItoConstants.COMP_MAST_ID));
				// addOn.setAddOnCompoId(resultSetData6.getInt(ItoConstants.ADD_ON_COMP_ID));
				// addOn.setAddOnCompo_cd(resultSetData6.getString(ItoConstants.ADD_ON_COMP));
				// addOn.setAddOnCompoName(resultSetData6.getString("ADD_ON_COMP_NM"));
				// addOn.setNewCompName(resultSetData6.getString("ADD_ON_COMP_NM"));
				// addOn.setSubtype1Id(resultSetData6.getInt(ItoConstants.SUB_TYPE1));
				// addOn.setSubtype1(resultSetData6.getString("SUB_TYPE1_NAME"));
				// addOn.setSubtype1Code(resultSetData6.getString("SUB_TYPE1_CD"));
				// addOn.setSubtype2Id(resultSetData6.getInt(ItoConstants.SUB_TYPE2));
				// addOn.setSubtype2(resultSetData6.getString("SUB_TYPE2_NAME"));
				// addOn.setSubtype2Code(resultSetData6.getString("SUB_TYPE2_CD"));
				// addOn.setMakeId(resultSetData6.getInt("MAKE"));
				// addOn.setMake(resultSetData6.getString("MAKE_NAME"));
				// addOn.setMakeCode(resultSetData6.getString("MAKE_CD"));
				// addOn.setQuantity(resultSetData6.getInt("QTY"));
				// addOn.setQuantityName(resultSetData6.getString("QTY_NM"));
				// addOn.setNewCompQty(resultSetData6.getInt("QTY"));
				// addOn.setNewCompRemark(resultSetData6.getString("COMMENTS"));
				// addOn.setExcelCost(Math.round(resultSetData6.getFloat(ItoConstants.COST)));
				// addOn.setNewCompPrice(Math.round(resultSetData6.getFloat(ItoConstants.COST)));
				// addOn.setAddOnTotal(Math.round(resultSetData6.getFloat(ItoConstants.TOTAL_COST)));
				// addOn.setSelectedCost(Math.round(resultSetData6.getFloat(ItoConstants.COST_ME)));
				// addOn.setSelectedCostFlag(resultSetData6.getInt(ItoConstants.COST_ME_FLG));
				// //
				// addOn.setAddOnCompFlag(resultSetData6.getInt(ItoConstants.ADD_ON_COMP_FLG)
				// // == 1 ? true : false);
				// //
				// addOn.setAddOnCompFlag(resultSetData6.getInt(ItoConstants.ADD_ON_COMP_FLG)
				// // == 1 ? true : false);
				// // if(resultSetData6.getInt("ADD_ON_COMP_FLAG")==1
				// // ?true:false){
				// //
				// addOn.setAddOnCompFlag(resultSetData6.getString("COL_VAL_CD")+"
				// // (TTL STD)");
				// // }else{
				// //
				// addOn.setAddOnCompFlag(resultSetData6.getString("COL_VAL_CD"));
				// // }
				// //
				//
				// selAddOnCompList.add(addOn);
				//
				// }
				// if (!selAddOnCompList.isEmpty()) {
				//
				// quotationForm.setSubmittedAddOnList(selAddOnCompList);
				// createAddOnJson(selAddOnCompList, quotationForm);
				// }
				// // Add On Components End
				//
				// }
				//

				// electrcail special

				List<DBOBean> eleGeneralSpecialList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetEleGeneralSpecial = callableStatement.getResultSet();
					while (resultSetEleGeneralSpecial.next()) {
						DBOBean bean = new DBOBean();

						bean.setEleSpecialId(resultSetEleGeneralSpecial.getInt("ELE_SPL_ID"));
						bean.setColNm(resultSetEleGeneralSpecial.getString("COL_NM"));
						bean.setColValCd(resultSetEleGeneralSpecial.getString("COL_VAL_CD"));
						bean.setDefaultFlag(resultSetEleGeneralSpecial.getInt("DEFLT_FLG") == 1 ? true : false);
						bean.setItemApplInd(resultSetEleGeneralSpecial.getInt("ITEM_APPL_IND"));
						bean.setItemId(resultSetEleGeneralSpecial.getInt("ITEM_ID"));
						bean.setNote(resultSetEleGeneralSpecial.getString("NOTE"));

						eleGeneralSpecialList.add(bean);

					}

					quotationForm.setEleGeneralSpecialList(eleGeneralSpecialList);
				}

				// electrical general
				List<DBOBean> eleGeneralList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetEleGeneral = callableStatement.getResultSet();
					while (resultSetEleGeneral.next()) {
						DBOBean bean = new DBOBean();
						bean.setEleItemId(resultSetEleGeneral.getInt("ELE_ITEM_ID"));
						bean.setDetQuotId(resultSetEleGeneral.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetEleGeneral.getInt("ITEM_ID"));
						bean.setItemName(resultSetEleGeneral.getString("ITEM_NM"));
						bean.setSubItemId(resultSetEleGeneral.getInt("SUB_ITEM_ID"));
						bean.setSubItemCode(resultSetEleGeneral.getString("SUB_ITEM_CD"));
						bean.setSubItemName(resultSetEleGeneral.getString("SUB_ITEM_NAME"));
						bean.setDesItemId(resultSetEleGeneral.getInt("DES_ITEM_ID"));
						bean.setDesItemName(resultSetEleGeneral.getString("DES_ITEM_NAME"));
						bean.setDesSubItemId(resultSetEleGeneral.getInt("DES_SUB_ITEM_ID"));
						bean.setDesSubItemName(resultSetEleGeneral.getString("DES_SUB_ITEM_NAME"));
						bean.setColId(resultSetEleGeneral.getInt("COL_ID"));
						bean.setColNm(resultSetEleGeneral.getString("COL_NM"));
						bean.setColValCd(resultSetEleGeneral.getString("COL_VAL_CD"));
						bean.setSubColValCode(resultSetEleGeneral.getString("SUB_COL_VAL_CD"));
						bean.setLhsFlag(resultSetEleGeneral.getInt("LHS_FLG"));
						bean.setRhsCost(Math.round(resultSetEleGeneral.getFloat("RHS_COST")));
						bean.setRhsColQuantity(resultSetEleGeneral.getFloat("RHS_COL_QTY"));
						bean.setRhsColTechcomments(resultSetEleGeneral.getString("RHS_COL_TECH_COMMENTS"));
						bean.setRhsColComrcomments(resultSetEleGeneral.getString("RHS_COL_COMR_COMMENTS"));
						bean.setDefaultVal(resultSetEleGeneral.getInt("DEFLT_FLG") == 1 ? true : false);
						bean.setOrderId(resultSetEleGeneral.getInt("ORDER_ID"));
						bean.setDispInd(resultSetEleGeneral.getInt("DISP_IND"));
						bean.setBasicCost(Math.round(resultSetEleGeneral.getFloat("BASIC_COST")));
						bean.setAddOnCost(Math.round(resultSetEleGeneral.getFloat("ADD_ON_COST")));
						bean.setItemCost(Math.round(resultSetEleGeneral.getFloat("ITEM_COST")));
						bean.setAddOnFlg(resultSetEleGeneral.getInt("ADD_ON_FLG"));
						bean.setTotalPrice(Math.round(resultSetEleGeneral.getFloat(ItoConstants.TOTAL_COST)));
						bean.setTechFlag(resultSetEleGeneral.getInt("TECH_FLG"));
						bean.setComrFlag(resultSetEleGeneral.getInt("COMR_FLG"));
						bean.setOverwrittenPriceFlag(resultSetEleGeneral.getInt("ELE_COST_ME_FLG") == 1 ? true : false);
						bean.setOverwrittenPrice(Math.round(resultSetEleGeneral.getFloat("ELE_COST_ME")));
						bean.setQuantity(resultSetEleGeneral.getInt("QTY"));
						bean.setTechRemarks(resultSetEleGeneral.getString("TECH_REMARKS"));
						bean.setComrRemarks(resultSetEleGeneral.getString("COMR_REMARKS"));
						bean.setAddOnNewColFlag(resultSetEleGeneral.getInt("ADD_ON_NEW_COL_FLG"));
						bean.setErrorMsg(resultSetEleGeneral.getString("ERROR_MSG"));
						bean.setItemErrMessage(resultSetEleGeneral.getString("ITEM_ERROR_MSG"));
						bean.setApproxCostFlag(resultSetEleGeneral.getInt("APPROX_COST_FLG"));
						bean.setItemApproxCostFlag(resultSetEleGeneral.getInt("ITEM_APPROX_COST_FLG"));
						bean.setDesItemOrderId(resultSetEleGeneral.getInt("DES_ITEM_ORDER_ID"));
						bean.setDesSubItemOrderId(resultSetEleGeneral.getInt("DES_SUB_ITEM_ORDER_ID"));
						bean.setColType(resultSetEleGeneral.getString("COL_TYPE"));
						bean.setDesColOrderId(resultSetEleGeneral.getString("DES_COL_ORDER_ID"));
						bean.setEleType(resultSetEleGeneral.getString("ELE_TYPE"));
						bean.setInputCostFlag(resultSetEleGeneral.getInt("INPUT_COST_FLG"));
						bean.setTotalEleCost(Math.round(resultSetEleGeneral.getFloat("TOTAL_ELE_COST")));
						bean.setTotalEle(Math.round(resultSetEleGeneral.getFloat("TOTAL_ELE")));
						bean.setTotalCi(Math.round(resultSetEleGeneral.getFloat("TOTAL_CI")));
						bean.setNewDesItemFlag(resultSetEleGeneral.getInt("NEW_DES_ITEM_FLG"));
						bean.setNewDesSubItemFlag(resultSetEleGeneral.getInt("NEW_DES_SUB_ITEM_FLG"));
						bean.setDiscountPer(Math.round(resultSetEleGeneral.getFloat("DISCOUNT_PER")));
						bean.setNonDiscountCost(Math.round(resultSetEleGeneral.getFloat("NON_DISCOUNT_COST")));
						eleGeneralList.add(bean);

					}

					quotationForm.setEleGeneralList(eleGeneralList);
				}
				
				
				List<DBOBean> eleExtScopeList1 = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetEleExtScope1 = callableStatement.getResultSet();
					while (resultSetEleExtScope1.next()) {
						DBOBean bean = new DBOBean();

						bean.setItemId(resultSetEleExtScope1.getInt("ITEM_ID"));
						bean.setSubItemId(resultSetEleExtScope1.getInt("SUB_ITEM_ID"));
						bean.setItemCost(Math.round(resultSetEleExtScope1.getFloat("ITEM_COST")));
						
						eleExtScopeList1.add(bean);

					}

					quotationForm.setEleExtScopeList1(eleExtScopeList1);
				}


				List<DBOBean> eleExtScopeList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetEleExtScope = callableStatement.getResultSet();
					while (resultSetEleExtScope.next()) {
						DBOBean bean = new DBOBean();

						bean.setDetQuotId(resultSetEleExtScope.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetEleExtScope.getInt("ITEM_ID"));
						bean.setItemName(resultSetEleExtScope.getString("ITEM_NM"));
						bean.setColId(resultSetEleExtScope.getInt("COL_ID"));
						bean.setColNm(resultSetEleExtScope.getString("COL_NM"));
						bean.setColValCd(resultSetEleExtScope.getString("COL_VAL_CD"));
						bean.setLhsFlag(resultSetEleExtScope.getInt("LHS_FLG"));
						bean.setExtScopeCost(Math.round(resultSetEleExtScope.getFloat("EXT_SCOPE_COST")));
						bean.setEleTotalExtCost(Math.round(resultSetEleExtScope.getFloat("ELE_TOTAL_EXT_COST")));
						bean.setQuantity(resultSetEleExtScope.getInt("QTY"));
						bean.setTechRemarks(resultSetEleExtScope.getString("TECH_REMARKS"));
						bean.setComrRemarks(resultSetEleExtScope.getString("COMR_REMARKS"));
						bean.setEleTechComments(resultSetEleExtScope.getString("ELE_TECH_COMMENTS"));
						bean.setEleComrComments(resultSetEleExtScope.getString("ELE_COMR_COMMENTS"));

						eleExtScopeList.add(bean);

					}

					quotationForm.setEleExtScopeList(eleExtScopeList);
				}

				List<DBOBean> eleCIExtScopeList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetEleCIExtScope = callableStatement.getResultSet();
					while (resultSetEleCIExtScope.next()) {
						DBOBean bean = new DBOBean();

						bean.setDetQuotId(resultSetEleCIExtScope.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetEleCIExtScope.getInt("ITEM_ID"));
						bean.setItemName(resultSetEleCIExtScope.getString("ITEM_NM"));
						bean.setColId(resultSetEleCIExtScope.getInt("COL_ID"));
						bean.setColNm(resultSetEleCIExtScope.getString("COL_NM"));
						bean.setColValCd(resultSetEleCIExtScope.getString("COL_VAL_CD"));
						bean.setLhsFlag(resultSetEleCIExtScope.getInt("LHS_FLG"));
						bean.setExtScopeCost(Math.round(resultSetEleCIExtScope.getFloat("EXT_SCOPE_COST")));
						bean.setcITotalExtCost(Math.round(resultSetEleCIExtScope.getFloat("CI_TOTAL_EXT_COST")));
						bean.setQuantity(resultSetEleCIExtScope.getInt("QTY"));
						bean.setTechRemarks(resultSetEleCIExtScope.getString("TECH_REMARKS"));
						bean.setComrRemarks(resultSetEleCIExtScope.getString("COMR_REMARKS"));
						bean.setcITechComments(resultSetEleCIExtScope.getString("CI_TECH_COMMENTS"));
						bean.setcIComrComments(resultSetEleCIExtScope.getString("CI_COMR_COMMENTS"));

						eleCIExtScopeList.add(bean);

					}

					quotationForm.setEleCIExtScopeList(eleCIExtScopeList);
				}

				List<DBOBean> saveAddInstrList1 = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetWord811 = callableStatement.getResultSet();
					while (resultSetWord811.next()) {
						DBOBean bean = new DBOBean();
						bean.setDetQuotId(resultSetWord811.getInt("DET_QUOT_ID"));
						bean.setItemId(resultSetWord811.getInt("ITEM_ID"));
						bean.setItemNm(resultSetWord811.getString("ITEM_NM"));
						bean.setQuantity(resultSetWord811.getInt("QTY"));
						bean.setColId(resultSetWord811.getInt("COL_ID"));
						bean.setColNm(resultSetWord811.getString("COL_NM"));
						bean.setColValCd(resultSetWord811.getString("COL_VAL_CD"));
						bean.setSubColValCode(resultSetWord811.getString("SUB_COL_VAL_CD"));
						bean.setAddOnNewColFlag(resultSetWord811.getInt("ADD_ON_NEW_COL_FLG"));
						bean.setRhsFlag(resultSetWord811.getInt("RHS_FLG") == 1 ? true : false);
						bean.setRhsColQuantity(resultSetWord811.getFloat("RHS_COL_QTY"));
						bean.setDesSubItemOrderId(resultSetWord811.getInt("DES_SUB_ITEM_ORDER_ID"));
						bean.setRhsCost(Math.round(resultSetWord811.getFloat("RHS_COST")));
						bean.setAddInstrCost(Math.round(resultSetWord811.getFloat("ADD_INSTR_COST")));

						bean.setTotalAddInstrCost(Math.round(resultSetWord811.getFloat("TOTAL_ADD_INSTR_COST")));
						bean.setItemFlag(resultSetWord811.getInt("ITEM_FLG") == 1 ? true : false);
						bean.setOthersFlag(resultSetWord811.getInt("OTHERS_FLG") == 1 ? true : false);
						bean.setAddInstrFlag(resultSetWord811.getInt("ADD_INSTR_FLG") == 1 ? true : false);
						bean.setBasicCost(Math.round(resultSetWord811.getFloat("BASIC_COST")));
						bean.setAddOnCost(Math.round(resultSetWord811.getFloat("ADD_ON_COST")));
						saveAddInstrList1.add(bean);

					}
					quotationForm.setSaveEleFilterList1(saveAddInstrList1);
				}

				List<DBOBean> eleInstrList = new ArrayList<>();
				if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
					resultSetEleInstrFilter = callableStatement.getResultSet();
					while (resultSetEleInstrFilter.next()) {
						DBOBean bean = new DBOBean();

						bean.setId(resultSetEleInstrFilter.getInt("ID"));
						bean.setInstrCode(resultSetEleInstrFilter.getString("INSTR_CD"));
						bean.setInstrNm(resultSetEleInstrFilter.getString("INSTR_NM"));
						bean.setInstrTypeNm(resultSetEleInstrFilter.getString("INSTR_TYPE_NM"));
						bean.setInstrDesc(resultSetEleInstrFilter.getString("INSTR_DESC"));
						bean.setInstrMounting(resultSetEleInstrFilter.getString("INSTR_MOUNTING"));
						bean.setQuantityLogic(resultSetEleInstrFilter.getInt("QTY_LOGIC"));
						bean.setCost(resultSetEleInstrFilter.getFloat("COST"));
						bean.setNoOfTable(resultSetEleInstrFilter.getInt("NO_OF_TABLE"));
						bean.setHeaderText(resultSetEleInstrFilter.getString("HEADER_TEXT"));
						bean.setFooterText(resultSetEleInstrFilter.getString("FOOTER_TEXT"));
						bean.setItemId(resultSetEleInstrFilter.getInt("ITEM_ID"));
						bean.setAddInstrId(resultSetEleInstrFilter.getInt("ADD_INSTR_ID"));
						bean.setAddInstrCd(resultSetEleInstrFilter.getString("ADD_INSTR_CD"));
						bean.setAddInstrNm(resultSetEleInstrFilter.getString("ADD_INSTR_NM"));
						bean.setApproxFlag(resultSetEleInstrFilter.getInt("APPROX_FLG"));
						eleInstrList.add(bean);

					}

					quotationForm.setEleInstrList(eleInstrList);
				}

				List<DBOBean> saveVmsDataList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetEleVmsData = callableStatement.getResultSet();
					while (resultSetEleVmsData.next()) {
						DBOBean bean = new DBOBean();
						bean.setItemId(resultSetEleVmsData.getInt("ITEM_ID"));
						bean.setItemName(resultSetEleVmsData.getString("ITEM_NM"));
						bean.setType(resultSetEleVmsData.getString("TYPE"));
						bean.setAddPrbFlag(resultSetEleVmsData.getInt("ADD_PRB_FLG"));
						bean.setOrderBy(resultSetEleVmsData.getInt("ORDER_BY"));
						bean.setTagNum(resultSetEleVmsData.getString("TAG_NO"));
						bean.setEquipment(resultSetEleVmsData.getString("EQUIPMENT"));
						bean.setApplication(resultSetEleVmsData.getString("APPLICATION"));
						bean.setLocation(resultSetEleVmsData.getString("LOCATION"));
						bean.setQuantity(resultSetEleVmsData.getInt("QTY"));
						bean.setCost(Math.round(resultSetEleVmsData.getFloat("COST")));
						bean.setNewColValFlag(resultSetEleVmsData.getInt("NEW_COL_VAL_FLG"));
						bean.setItemCost(Math.round(resultSetEleVmsData.getFloat("ITEM_COST")));
						bean.setHeaderText(resultSetEleVmsData.getString("HEADER_TEXT"));
						bean.setNote(resultSetEleVmsData.getString("NOTE"));
						bean.setBasicCost(Math.round(resultSetEleVmsData.getFloat("BASIC_COST")));
						bean.setAddOnCost(Math.round(resultSetEleVmsData.getFloat("ADD_ON_COST")));
						saveVmsDataList.add(bean);

					}
					quotationForm.setSaveVmsDataList(saveVmsDataList);
				}

				List<DBOBean> savePerformanceDataList3 = new ArrayList<>();
				if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
					resultSetPerformData3 = callableStatement.getResultSet();
					while (resultSetPerformData3.next()) {
						DBOBean bean = new DBOBean();

						bean.setHmbdTableInput(resultSetPerformData3.getInt("HMBD_TABLE_INPUT"));
						bean.setConditionTableInput(resultSetPerformData3.getInt("CONDTION_TABLE_INPUT"));
						bean.setNoOfConditions(resultSetPerformData3.getInt("NO_OF_CONDITIONS"));

						savePerformanceDataList3.add(bean);

					}

					quotationForm.setSavePerformanceList3(savePerformanceDataList3);
				}

				List<DBOBean> savePerformanceDataList1 = new ArrayList<>();
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

						savePerformanceDataList1.add(bean);

					}

					quotationForm.setSavePerformanceDataList1(savePerformanceDataList1);
				}

				List<DBOBean> fixedListData = new ArrayList<>();
				if (resultOutParameterInt == 0 && (callableStatement.getMoreResults())) {
					resultSetFixedData = callableStatement.getResultSet();
					while (resultSetFixedData.next()) {
						DBOBean bean = new DBOBean();

						bean.setItemId(resultSetFixedData.getInt("ITEM_ID"));
						bean.setItemNm(resultSetFixedData.getString("ITEM_NM"));
						bean.setFixedText1(resultSetFixedData.getString("FIXED_TEXT1"));
						bean.setFixedText2(resultSetFixedData.getString("FIXED_TEXT2"));
						bean.setFixedText3(resultSetFixedData.getString("FIXED_TEXT3"));

						fixedListData.add(bean);

					}

					quotationForm.setFixedListData(fixedListData);
				}

				List<DBOBean> identListData = new ArrayList<>();
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

						identListData.add(bean);

					}

					quotationForm.setIdentListData(identListData);
				}

				List<DBOBean> itemListData = new ArrayList<>();
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

						itemListData.add(bean);

					}

					quotationForm.setItemListData(itemListData);
				}

				List<DBOBean> cirListData = new ArrayList<>();
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

						cirListData.add(bean);

					}
					quotationForm.setCirListData(cirListData);
				}

				List<DBOBean> savePerformanceDataList2 = new ArrayList<>();
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
						bean.setNote(resultSetPerform2.getString("NOTE"));
						savePerformanceDataList2.add(bean);

					}

					quotationForm.setSavePerformanceDataList2(savePerformanceDataList2);
				}

				if (callableStatement.getMoreResults()) {
					List<DBOBean> comercialDataList = new ArrayList<DBOBean>();
					resultSetComercialData = callableStatement.getResultSet();
					while (resultSetComercialData.next()) {
						DBOBean bean = new DBOBean();

						bean.setId(resultSetComercialData.getInt("ID"));
						bean.setSectionCd(resultSetComercialData.getString("SECTION_CD"));
						bean.setItemId(resultSetComercialData.getInt("ITEM_ID"));
						bean.setItemName(resultSetComercialData.getString("ITEM_NM"));
						bean.setSubItemId(resultSetComercialData.getInt("SUB_ITEM_ID"));
						bean.setSubItemName(resultSetComercialData.getString("SUB_ITEM_NM"));
						bean.setSubItemTypeCd(resultSetComercialData.getString("SUB_ITEM_TYPE_CD"));
						bean.setSubItemTypeName(resultSetComercialData.getString("SUB_ITEM_TYPE_NM"));
						bean.setFixedText1(resultSetComercialData.getString("FIXED_TEXT1"));
						bean.setFixedText2(resultSetComercialData.getString("FIXED_TEXT2"));
						bean.setFixedText3(resultSetComercialData.getString("FIXED_TEXT3"));
						bean.setEditFlag(resultSetComercialData.getInt("EDIT_FLG"));
						bean.setNewColValFlag(resultSetComercialData.getInt("NEW_COL_FLG"));
						bean.setNote(resultSetComercialData.getString("NOTE"));
						logger.info("commercialDataList start");
						logger.info(resultSetComercialData.getInt("ID"));
						logger.info(resultSetComercialData.getString("SECTION_CD"));
						logger.info(resultSetComercialData.getInt("ITEM_ID"));
						logger.info(resultSetComercialData.getString("ITEM_NM"));
						logger.info(resultSetComercialData.getInt("SUB_ITEM_ID"));
						logger.info(resultSetComercialData.getString("SUB_ITEM_NM"));
						logger.info(resultSetComercialData.getString("SUB_ITEM_TYPE_CD"));
						logger.info(resultSetComercialData.getString("SUB_ITEM_TYPE_NM"));
						logger.info(resultSetComercialData.getString("FIXED_TEXT1"));
						logger.info(resultSetComercialData.getString("FIXED_TEXT2"));
						logger.info(resultSetComercialData.getString("FIXED_TEXT3"));
						logger.info(resultSetComercialData.getInt("EDIT_FLG"));
						logger.info(resultSetComercialData.getInt("NEW_COL_FLG"));
						logger.info(resultSetComercialData.getString("NOTE"));
						logger.info("commercialDataList end");
						comercialDataList.add(bean);

					}

					quotationForm.setComercialDataList(comercialDataList);
				}

				if (callableStatement.getMoreResults()) {
					List<DBOBean> savedComercialDataList1 = new ArrayList<DBOBean>();
					resultSetSavedComercialData1 = callableStatement.getResultSet();
					while (resultSetSavedComercialData1.next()) {
						DBOBean bean = new DBOBean();

						bean.setMastQuotId(resultSetSavedComercialData1.getInt("MAST_QUOT_ID"));
						bean.setFixedText2(resultSetSavedComercialData1.getString("FIXED_TEXT2"));
						bean.setEditFlag(resultSetSavedComercialData1.getInt("EDIT_FLG"));
						bean.setNewColValFlag(resultSetSavedComercialData1.getInt("NEW_COL_FLG"));
						bean.setUncheckFlag(resultSetSavedComercialData1.getInt("UNCHECK_FLG"));
						logger.info("savedComercialDataList1 start");
						logger.info(resultSetSavedComercialData1.getInt("MAST_QUOT_ID"));
						logger.info(resultSetSavedComercialData1.getString("FIXED_TEXT2"));
						logger.info(resultSetSavedComercialData1.getInt("EDIT_FLG"));
						logger.info(resultSetSavedComercialData1.getInt("NEW_COL_FLG"));
						logger.info(resultSetSavedComercialData1.getInt("UNCHECK_FLG"));
						logger.info("savedComercialDataList1 end");
						savedComercialDataList1.add(bean);

					}

					quotationForm.setSavedComercialDataList1(savedComercialDataList1);
				}

				if (callableStatement.getMoreResults()) {
					List<DBOBean> savedComercialDataList2 = new ArrayList<DBOBean>();
					resultSetSavedComercialData2 = callableStatement.getResultSet();
					while (resultSetSavedComercialData2.next()) {
						DBOBean bean = new DBOBean();

						bean.setMastQuotId(resultSetSavedComercialData2.getInt("MAST_QUOT_ID"));
						bean.setSectionCd(resultSetSavedComercialData2.getString("SECTION_CD"));
						bean.setItemId(resultSetSavedComercialData2.getInt("ITEM_ID"));
						bean.setItemName(resultSetSavedComercialData2.getString("ITEM_NM"));

						bean.setFixedText1(resultSetSavedComercialData2.getString("FIXED_TEXT1"));
						bean.setFixedText2(resultSetSavedComercialData2.getString("FIXED_TEXT2"));
						bean.setFixedText3(resultSetSavedComercialData2.getString("FIXED_TEXT3"));
						bean.setEditFlag(resultSetSavedComercialData2.getInt("EDIT_FLG"));
						bean.setNewColValFlag(resultSetSavedComercialData2.getInt("NEW_COL_FLG"));
						bean.setUncheckFlag(resultSetSavedComercialData2.getInt("UNCHECK_FLG"));
						logger.info("savedComercialDataList2 start");
						logger.info(resultSetSavedComercialData2.getInt("MAST_QUOT_ID"));
						logger.info(resultSetSavedComercialData2.getString("SECTION_CD"));
						logger.info(resultSetSavedComercialData2.getInt("ITEM_ID"));
						logger.info(resultSetSavedComercialData2.getString("ITEM_NM"));
						logger.info(resultSetSavedComercialData2.getString("FIXED_TEXT1"));
						logger.info(resultSetSavedComercialData2.getString("FIXED_TEXT2"));
						logger.info(resultSetSavedComercialData2.getString("FIXED_TEXT3"));
						logger.info(resultSetSavedComercialData2.getInt("EDIT_FLG"));
						logger.info(resultSetSavedComercialData2.getInt("NEW_COL_FLG"));
						logger.info(resultSetSavedComercialData2.getInt("UNCHECK_FLG"));
						logger.info("savedComercialDataList2 end");
						savedComercialDataList2.add(bean);

					}

					quotationForm.setSavedComercialDataList2(savedComercialDataList2);
				}

				if (callableStatement.getMoreResults()) {
					List<DBOBean> attachmentDataList = new ArrayList<DBOBean>();
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
						bean.setSlNo(resultSetAttachmentData.getInt("SL_NO"));
						bean.setName(resultSetAttachmentData.getString("FILE_NM"));
						attachmentDataList.add(bean);

					}

					quotationForm.setAttachmentDataList(attachmentDataList);
				}

				if (callableStatement.getMoreResults()) {
					List<DBOBean> otherDataGetList = new ArrayList<DBOBean>();
					resultSetOtherGetData = callableStatement.getResultSet();
					while (resultSetOtherGetData.next()) {
						DBOBean bean = new DBOBean();

						bean.setGroupCode(resultSetOtherGetData.getString("GRP_CD"));
						bean.setCategoryNm(resultSetOtherGetData.getString("CAT_NM"));
						bean.setSubCategoryNm(resultSetOtherGetData.getString("SUB_CAT_NM"));
						bean.setSeqNo(resultSetOtherGetData.getInt("SEQ_NO"));
						bean.setSsId(resultSetOtherGetData.getInt("SS_ID"));
						bean.setScopeCd(resultSetOtherGetData.getString("SCOPE_CD"));
						bean.setScopeNm(resultSetOtherGetData.getString("SCOPE_NM"));

						bean.setItemId(resultSetOtherGetData.getInt("ITEM_ID"));
						bean.setItemNm(resultSetOtherGetData.getString("ITEM_NM"));
						bean.setSubItemId(resultSetOtherGetData.getInt("SUB_ITEM_ID"));
						bean.setSubItemNm(resultSetOtherGetData.getString("SUB_ITEM_NM"));
						bean.setInformation(resultSetOtherGetData.getString("INFORMATION"));
						bean.setFinalts(resultSetOtherGetData.getString("FINAL"));
						bean.setSubScopeCd(resultSetOtherGetData.getString("SUB_SCOPE_CD"));
						bean.setDescription(resultSetOtherGetData.getString("DESCRIPTION"));
						bean.setEquipment(resultSetOtherGetData.getString("EQUIPMENT"));
						bean.setTest(resultSetOtherGetData.getString("TEST"));
						bean.setEquivalent(resultSetOtherGetData.getString("EQUIVALENT"));
						bean.setPanelType(resultSetOtherGetData.getString("PANEL_TYPE"));
						bean.setCustType(resultSetOtherGetData.getString("CUST_TYPE"));
				 		bean.setQuant(resultSetOtherGetData.getString("QTY"));
						bean.setCost(Math.round(resultSetOtherGetData.getFloat("COST")));
						bean.setEditFlag(resultSetOtherGetData.getInt("EDIT_FLG"));
						bean.setNewColValFlag(resultSetOtherGetData.getInt("NEW_COL_VAL_FLG"));
						bean.setNote(resultSetOtherGetData.getString("NOTE"));

						otherDataGetList.add(bean);

					}

					quotationForm.setOtherGetDataList(otherDataGetList);
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
						bean.setItemNm(resultSetOtherChapterData.getString("ITEM_NM"));
						bean.setSubItemId(resultSetOtherChapterData.getInt("SUB_ITEM_ID"));
						bean.setSubItemNm(resultSetOtherChapterData.getString("SUB_ITEM_NM"));
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

					quotationForm.setSaveOtherChapterList(saveOtherCapterList);

				}

				// F2F SAP Data Starts
				if (callableStatement.getMoreResults()) {
					resultSetSap = callableStatement.getResultSet();
					while (resultSetSap.next()) {
						quotationForm.getLatestCProjectData().setId(resultSetSap.getInt("ID"));
						quotationForm.getLatestCProjectData().setcNum(resultSetSap.getString(ItoConstants.C_NUM));
						quotationForm.getLatestCProjectData().setVariantCode(resultSetSap.getString("VARIANT_CD"));
						quotationForm.getLatestCProjectData()
								.setTurbineMaterialCost(Math.round(resultSetSap.getFloat(ItoConstants.TURBINE_MTRL)));
						quotationForm.getLatestCProjectData()
								.setShopCoverCost(Math.round(resultSetSap.getFloat(ItoConstants.SHOP_CONV)));
						quotationForm.getLatestCProjectData()
								.setSubContrCost(Math.round(resultSetSap.getFloat(ItoConstants.SUB_CONTRACT)));
						quotationForm.getLatestCProjectData()
								.setTotal(Math.round(resultSetSap.getFloat(ItoConstants.TOTAL)));

					}

				}
				// F2F SAP Data Ends

				// // F2F Excel Data(SupplyChain) Starts
				// if (callableStatement.getMoreResults()) {
				// resultSetF2FExcel = callableStatement.getResultSet();
				// while (resultSetF2FExcel.next()) {
				//
				// quotationForm.getF2fExcel().setCompMastId(resultSetF2FExcel.getInt("F2F_MAST_ID"));
				// quotationForm.getF2fExcel().setTurbineMaterialCost(Math.round(resultSetF2FExcel.getFloat("TURBINE_MTRL_COST")));
				// quotationForm.getF2fExcel().setSubContrCost(Math.round(resultSetF2FExcel.getFloat("SUB_CONTR_COST")));
				// quotationForm.getF2fExcel().setShopCoverCost(Math.round(resultSetF2FExcel.getFloat("SHOP_CONV_COST")));
				// quotationForm.getF2fExcel().setTotalF2FCost(Math.round(resultSetF2FExcel.getFloat(ItoConstants.TOTAL_COST)));
				// quotationForm.getF2fExcel().setOverwrittenPrice(Math.round(resultSetF2FExcel.getFloat(ItoConstants.COST_ME)));
				// quotationForm.getF2fExcel().setOverwrittenPriceFlg(resultSetF2FExcel.getInt(ItoConstants.COST_ME_FLG)
				// == 1 ? true : false);
				// }
				//
				// }
				// // F2F Excel(SupplyChain) Data Ends
				//

				List<DBOBean> saveEleFilterList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
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
					quotationForm.setSaveEleFilterList(saveEleFilterList);
				}

				// // package Starts
				ResultSet resultSetData3 = null;
				List<TransportationDetailsBean> packageList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetData3 = callableStatement.getResultSet();

					while (resultSetData3.next()) {
						TransportationDetailsBean bean = new TransportationDetailsBean();

						bean.setCustCode(quotationForm.getSaveBasicDetails().getCustCode());
						bean.setPackageTypeName(resultSetData3.getString("PKG_TYP_NAME"));
						bean.setPackageTypeCode(resultSetData3.getString("PKG_TYP_CD"));
						bean.setPackageTypeId(resultSetData3.getInt(ItoConstants.PKG_TYP));
						bean.setFrameId(resultSetData3.getInt(ItoConstants.FRM_ID));
						bean.setFrameName(resultSetData3.getString(ItoConstants.FRAME_NAME));
						bean.setCondensingTypeId(resultSetData3.getInt(ItoConstants.COND_TYP_ID));
						bean.setCondensingTypeName(resultSetData3.getString(ItoConstants.COND_TYP_NAME));
						bean.setPrice(resultSetData3.getInt(ItoConstants.COST));
						bean.setOverwrittenPrice(resultSetData3.getInt(ItoConstants.COST_ME));
						bean.setOverwrittenPriceFlag(resultSetData3.getInt(ItoConstants.COST_ME_FLG));

						packageList.add(bean);

					}
					quotationForm.setPackageDetailsListData(packageList);
				}
				// // package Ends
				//
				// // Errection And Commision Starts
				ResultSet resultSetData4 = null;
				List<ErectionCommissionBean> ecList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetData4 = callableStatement.getResultSet();

					while (resultSetData4.next()) {
						ErectionCommissionBean bean = new ErectionCommissionBean();

						bean.setFramePowerId(resultSetData4.getInt(ItoConstants.FRM_POW_ID));
						bean.setFrameDesc(resultSetData4.getString(ItoConstants.FRAME_NAME));
						bean.setCondensingTypeId(resultSetData4.getInt(ItoConstants.COND_TYP_ID));
						bean.setCondensingType(resultSetData4.getString(ItoConstants.COND_TYP_NAME));
						bean.setTypeOfChargeId(resultSetData4.getInt(ItoConstants.TYP_OF_CHARGE));
						bean.setTypeOfCharge(resultSetData4.getString("TYP_OF_CHARGE_NAME"));
						bean.setTypeOfChargeCd(resultSetData4.getString("TYP_OF_CHARGE_CD"));
						bean.setLoadingId(resultSetData4.getInt(ItoConstants.LOADING_ID));
						bean.setLoadingCd(resultSetData4.getString("LOADING_CD"));
						bean.setLoadingName(resultSetData4.getString("LOADING_NAME"));
						bean.setLodgingId(resultSetData4.getInt(ItoConstants.LOADGING_ID));
						bean.setLodgingName(resultSetData4.getString("LOADGING_NAME"));
						bean.setLodgingCd(resultSetData4.getString("LOADGING_CD"));
						bean.setNoOfManDays(resultSetData4.getInt(ItoConstants.NO_OF_MANDAYS));
						bean.setPrice(resultSetData4.getFloat(ItoConstants.COST));
						bean.setOverwrittenPrice(resultSetData4.getFloat(ItoConstants.COST_ME));
						bean.setOverwrittenPriceFlag(
								resultSetData4.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						bean.setRemarks(resultSetData4.getString("REMARKS"));
						bean.setServiceRemarks(resultSetData4.getString("SERVICE_REMARKS"));
						bean.setEcTypeCd(quotationForm.getSaveBasicDetails().getCustCode());

						ecList.add(bean);
					}
					quotationForm.setErrectionCommList(ecList);
				}
				// // Errection And Commision Ends
				//
				// // Transportation Starts
				ResultSet resultSetData7 = null;
				List<TransportationDetailsBean> quotTransportList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetData7 = callableStatement.getResultSet();
					while (resultSetData7.next()) {
						TransportationDetailsBean bean = new TransportationDetailsBean();

						bean.setTransMastId(resultSetData7.getInt(ItoConstants.TRNS_MAST_ID));
						bean.setCustCode(resultSetData7.getString("CUST_CD"));
						bean.setTransTypeId(resultSetData7.getInt(ItoConstants.TRNS_TYP));
						quotationForm.getSaveBasicDetails()
								.setTransportationType(resultSetData7.getInt(ItoConstants.TRNS_TYP));
						bean.setTransType(resultSetData7.getString("TRNS_TYPES"));
						bean.setFrameId(resultSetData7.getInt(ItoConstants.FRM_ID));
						bean.setFrameName(resultSetData7.getString(ItoConstants.FRAME_NAME));
						bean.setCondensingTypeId(resultSetData7.getInt(ItoConstants.COND_TYP_ID));
						bean.setCondensingTypeName(resultSetData7.getString(ItoConstants.COND_TYP_NAME));
						// bean.setPlaceId(resultSetData7.getInt(ItoConstants.FOB_ID));
						bean.setFOBPlace(resultSetData7.getString(ItoConstants.FOB_NM));
						bean.setCompoId(resultSetData7.getInt(ItoConstants.COMP_ID));
						bean.setCompoName(resultSetData7.getString(ItoConstants.COMP_NM));
						bean.setNumberOfVehicle(resultSetData7.getInt(ItoConstants.NO_OF_VEHICLE));
						bean.setToPlace(resultSetData7.getString(ItoConstants.TO_PLACE));
						bean.setDistance(resultSetData7.getInt(ItoConstants.DISTANCE));
						bean.setPortId(resultSetData7.getInt(ItoConstants.PORT_ID));
						bean.setPortName(resultSetData7.getString(ItoConstants.PORT_NM));
						bean.setCountryName(resultSetData7.getString(ItoConstants.COUNTRY_NM));
						bean.setCompPrice(Math.round(resultSetData7.getFloat(ItoConstants.COST)));
						bean.setFobPrice(Math.round(resultSetData7.getFloat(ItoConstants.FOB_COST)));
						bean.setChennaiPrice(Math.round(resultSetData7.getFloat("CHENNAI_COST")));
						bean.setTotalPrice(Math.round(resultSetData7.getFloat(ItoConstants.TOTAL_COST)));
						bean.setOverwrittenPriceFlag(resultSetData7.getInt(ItoConstants.COST_ME_FLG));
						bean.setOverwrittenPrice(Math.round(resultSetData7.getFloat(ItoConstants.COST_ME)));
						bean.setTransTypeCode(resultSetData7.getString("TRNS_TYPES_CD"));
						quotationForm.getSaveBasicDetails().setTransTypeCode(resultSetData7.getString("TRNS_TYPES_CD"));

						quotTransportList.add(bean);
					}
					quotationForm.setTransportationDetailList(quotTransportList);
				}
				// // Transportation Ends
				//
				// // DBO Mechanical Starts
				// ResultSet resultSetData8 = null;
				// List<DBOBean> quotDboMechList = new ArrayList<>();
				// if (callableStatement.getMoreResults()) {
				// // DBO Mechanical Components Data Starts
				// resultSetData8 = callableStatement.getResultSet();
				// while (resultSetData8.next()) {
				// DBOBean bean = new DBOBean();
				// bean.setDboQuotDetId(resultSetData8.getInt("DBO_QUOT_DET_ID"));
				// bean.setItemId(resultSetData8.getInt("ITEM_ID"));
				// bean.setSubItemId(resultSetData8.getInt("SUB_ITEM_ID"));
				//
				// bean.setItemName(resultSetData8.getString("DBO_MECH_ITEM"));
				// // along
				// // with
				// // subitem
				// // Id
				// // bean.setCatDetDesc(resultSetData8.getString("ITEM_NAME"));
				// // //item Name without subitem Id
				// bean.setColId(resultSetData8.getInt("COL_ID"));
				// bean.setColNm(resultSetData8.getString("COL_NM"));
				// if (resultSetData8.getInt("DEFLT_FLG") == 1 ? true : false) {
				// bean.setColValCd(resultSetData8.getString("COL_VAL_CD") + "
				// (TTL STD)");
				// } else {
				// bean.setColValCd(resultSetData8.getString("COL_VAL_CD"));
				// }
				//
				// bean.setUserVal(resultSetData8.getInt("ME_COL_VAL"));
				// bean.setPrice(Math.round(resultSetData8.getFloat(ItoConstants.COST)));
				// bean.setTotalPrice(Math.round(resultSetData8.getFloat(ItoConstants.TOTAL_COST)));
				// bean.setOverwrittenPriceFlag(resultSetData8.getInt(ItoConstants.COST_ME_FLG)
				// == 1 ? true : false);
				// bean.setOverwrittenPrice(Math.round(resultSetData8.getFloat(ItoConstants.COST_ME)));
				// bean.setCondensorFlowCapacity(resultSetData8.getInt("FLOW_RATE"));
				// bean.setQuantity(resultSetData8.getInt("QTY"));
				// bean.setAdditionalCost(Math.round(resultSetData8.getFloat("ADD_ON_COST")));
				// bean.setCompRemarks(resultSetData8.getString("COMMENTS"));
				// quotDboMechList.add(bean);
				// }
				// }
				// quotationForm.setQuotDboMechList(quotDboMechList);
				// createDboMechExcelJson(quotationForm.getQuotDboMechList(),
				// quotationForm);
				// }
				// // DBO Mechanical Components Data Ends
				//
				// // DBO Mechanical Others Data Starts
				// ResultSet dboMechOthers = null;
				// List<DBOBean> quotDboMechOthList = new ArrayList<>();
				// if (callableStatement.getMoreResults()) {
				// dboMechOthers = callableStatement.getResultSet();
				// while (dboMechOthers.next()) {
				// DBOBean bean = new DBOBean();
				// bean.setDboQuotDetId(dboMechOthers.getInt("DBO_QUOT_DET_ID"));
				// bean.setOtherCompId(dboMechOthers.getInt("ITEM_ID"));
				// bean.setSubItemId(dboMechOthers.getInt("SUB_ITEM_ID"));
				// bean.setOthCompName(dboMechOthers.getString("ITEM_NAME"));
				// bean.setOthQuantity(dboMechOthers.getFloat("QTY"));
				// bean.setOthPrice(Math.round(dboMechOthers.getFloat(ItoConstants.COST)));
				// bean.setOthRemarks(dboMechOthers.getString("COMMENTS"));
				//
				// quotDboMechOthList.add(bean);
				// }
				// quotationForm.setQuotDboMechOthersList(quotDboMechOthList);
				// }
				// // DBO Mechanical Others Data Ends
				// // DBO Mechanical Ends
				//
				// // DBO Electrical Starts
				// ResultSet resultSetData9 = null;
				// List<DBOBean> quotDboEleList = new ArrayList<>();
				// List<DBOBean> quotDboEleSplAddList = new ArrayList<>();
				// if (callableStatement.getMoreResults()) {
				// // get DBO ELectrical Componenets and Special Addons Data
				// Starts
				// resultSetData9 = callableStatement.getResultSet();
				// while (resultSetData9.next()) {
				//
				// if (resultSetData9.getInt("SPL_ADD_FLG") == 1 ? true : false)
				// {
				// DBOBean addOnBean = new DBOBean();
				// addOnBean.setDboQuotDetId(resultSetData9.getInt("DBO_QUOT_DET_ID"));
				// addOnBean.setItemId(resultSetData9.getInt("ITEM_ID"));
				// addOnBean.setItemName(resultSetData9.getString("DBO_ELE_ITEM"));
				// addOnBean.setSplColNm(resultSetData9.getString("COL_NM"));
				// addOnBean.setColId(resultSetData9.getInt("COL_ID"));
				// addOnBean.setColValCd(resultSetData9.getString("COL_VAL_CD"));
				// addOnBean.setUserVal(resultSetData9.getInt("ME_COL_VAL"));
				// addOnBean.setDboEleType(resultSetData9.getString("PANEL_CD"));
				// addOnBean.setSplQuantity(resultSetData9.getInt("QTY"));
				// addOnBean.setPrice(Math.round(resultSetData9.getFloat(ItoConstants.COST)));
				// addOnBean.setTotalPrice(Math.round(resultSetData9.getFloat(ItoConstants.TOTAL_COST)));
				// addOnBean.setOverwrittenPriceFlag(resultSetData9.getInt(ItoConstants.COST_ME_FLG)
				// == 1 ? true : false);
				// addOnBean.setOverwrittenPrice(Math.round(resultSetData9.getFloat(ItoConstants.COST_ME)));
				// addOnBean.setSplCost(Math.round(resultSetData9.getFloat("COST_INSTR")));
				// addOnBean.setSplAddonFlag(resultSetData9.getInt("SPL_ADD_FLG")
				// == 1 ? true : false);
				//
				// quotDboEleSplAddList.add(addOnBean);
				// } else {
				// // whenever there is change in below result set either
				// // in DB or Java
				// // make changes in dBOMechanicalDao.getDboEditData as
				// // well without fail
				// DBOBean bean = new DBOBean();
				// bean.setDboQuotDetId(resultSetData9.getInt("DBO_QUOT_DET_ID"));
				// bean.setItemId(resultSetData9.getInt("ITEM_ID"));
				// bean.setItemName(resultSetData9.getString("DBO_ELE_ITEM"));
				// bean.setColNm(resultSetData9.getString("COL_NM"));
				// bean.setColId(resultSetData9.getInt("COL_ID"));
				// bean.setDefaultVal(resultSetData9.getInt("DEFLT_FLG") == 1 ?
				// true : false);
				// if (resultSetData9.getInt("DEFLT_FLG") == 1 ? true : false) {
				// bean.setColValCd(resultSetData9.getString("COL_VAL_CD") + "
				// (TTL STD)");
				//
				// } else {
				// bean.setColValCd(resultSetData9.getString("COL_VAL_CD"));
				// }
				// bean.setUserVal(resultSetData9.getInt("ME_COL_VAL"));
				// bean.setPrice(Math.round(resultSetData9.getFloat(ItoConstants.COST)));
				// bean.setTotalPrice(Math.round(resultSetData9.getFloat(ItoConstants.TOTAL_COST)));
				//
				// bean.setOverwrittenPriceFlag(resultSetData9.getInt(ItoConstants.COST_ME_FLG)
				// == 1 ? true : false);
				// bean.setOverwrittenPrice(Math.round(resultSetData9.getFloat(ItoConstants.COST_ME)));
				// bean.setDboEleType(resultSetData9.getString("PANEL_CD"));
				// bean.setQuantity(resultSetData9.getInt("QTY"));
				//
				// bean.setAddInstrCost(Math.round(resultSetData9.getFloat("COST_INSTR")));
				//
				// bean.setSplAddonFlag(resultSetData9.getInt("SPL_ADD_FLG") ==
				// 1 ? true : false);
				// // others
				// bean.setOthersFlag(resultSetData9.getInt("OTHR_COL_FLG") == 1
				// ? true : false);
				// bean.setOthQuantity(resultSetData9.getFloat("OTHR_COL_QTY"));
				// bean.setOthRemarks(resultSetData9.getString("OTHR_COL_COMMENTS"));
				// // others
				//
				// quotDboEleList.add(bean);
				// }
				// }
				// quotationForm.setQuotDboElectricalList(quotDboEleList);
				// quotationForm.setQuotDboEleSplAddOnList(quotDboEleSplAddList);
				// }
				// // get DBO ELectrical Componenets and Special Addons Data
				// Ends
				//
				// // get DBO ELectrical Additional Instruments Data Starts
				// // if (quotationForm.isEditQuotFlow()) {
				// ResultSet resultSetAddIntr = null;
				// List<DBOBean> quotDboEleAddInstrList = new ArrayList<>();
				// if (callableStatement.getMoreResults()) {
				// resultSetAddIntr = callableStatement.getResultSet();
				// while (resultSetAddIntr.next()) {
				// DBOBean bean = new DBOBean();
				// bean.setDboQuotDetId(resultSetAddIntr.getInt("DBO_QUOT_DET_ID"));
				// bean.setItemId(resultSetAddIntr.getInt("ITEM_ID"));
				// bean.setItemName(resultSetAddIntr.getString("DBO_ELE_ITEM"));
				// bean.setAddInstrCompNm(resultSetAddIntr.getString("COL_NM"));
				// bean.setColId(resultSetAddIntr.getInt("COL_ID"));
				// bean.setColValCd(resultSetAddIntr.getString("COL_VAL_CD") + "
				// (TTL STD)");
				// bean.setAddInstrQuantity(resultSetAddIntr.getInt("OTHR_COL_QTY"));
				// bean.setAddInstrCost(Math.round(resultSetAddIntr.getFloat("COST_INSTR")));
				// bean.setPrice(Math.round(resultSetAddIntr.getFloat(ItoConstants.COST)));
				// bean.setTotalPrice(Math.round(resultSetAddIntr.getFloat(ItoConstants.TOTAL_COST)));
				// bean.setOthQuantity(resultSetAddIntr.getInt("QTY"));
				// bean.setAddOnOthersFlag(resultSetAddIntr.getInt("OTHR_COL_VAL_FLG")
				// == 1 ? true : false);
				// bean.setAddOnOverwrittenFlag(resultSetAddIntr.getInt("ADD_ON_COST_ME_FLG")
				// == 1 ? true : false);
				//
				// bean.setOverwrittenPriceFlag(resultSetAddIntr.getInt("ADD_INSTR_FLG")
				// == 1 ? true : false);
				// bean.setAddInstrRemarks(resultSetAddIntr.getString("OTHR_COL_COMMENTS"));
				// bean.setDboEleType(resultSetAddIntr.getString("PANEL_CD"));
				//
				// quotDboEleAddInstrList.add(bean);
				// }
				// quotationForm.setAddInstrList(quotDboEleAddInstrList);
				// }
				// // }
				// // get DBO ELectrical Additional Instruments Data Ends
				//
				// // get DBO ELectrical Addons Data Starts
				// ResultSet resultSetDboEleAddOn = null;
				// List<DBOBean> addOnDboEleList = new ArrayList<>();
				// if (callableStatement.getMoreResults()) {
				// resultSetDboEleAddOn = callableStatement.getResultSet();
				// while (resultSetDboEleAddOn.next()) {
				// DBOBean bean = new DBOBean();
				// bean.setColId(resultSetDboEleAddOn.getInt("COL_ID"));
				// bean.setAddOnCompNm(resultSetDboEleAddOn.getString("COL_NM"));
				// bean.setColValCd(resultSetDboEleAddOn.getString("COL_VAL_CD"));
				// bean.setAddOnQuantity(resultSetDboEleAddOn.getFloat("OTHR_COL_QTY"));
				// bean.setOthersFlag(resultSetDboEleAddOn.getInt("OTHR_COL_VAL_FLG")
				// == 1 ? true : false);
				// bean.setAddOnOthersFlag(resultSetDboEleAddOn.getInt("OTHR_COL_VAL_FLG")
				// == 1 ? true : false);
				// bean.setAddOnOverwrittenFlag(resultSetDboEleAddOn.getInt("ADD_ON_COST_ME_FLG")
				// == 1 ? true : false);
				// bean.setAddOnCost(Math.round(resultSetDboEleAddOn.getFloat("COST_INSTR")));
				// bean.setOthRemarks(resultSetDboEleAddOn.getString("OTHR_COL_COMMENTS"));
				// bean.setAddOnListFlag(resultSetDboEleAddOn.getInt("ADD_ON_FLG")
				// == 1 ? true : false);
				// bean.setItemId(resultSetDboEleAddOn.getInt("ITEM_ID"));
				// bean.setItemName(resultSetDboEleAddOn.getString("ITEM_NAME"));
				// addOnDboEleList.add(bean);
				// // COL_ID COL_NM COL_VAL_CD COST_INSTR OTHR_COL_QTY
				// // OTHR_COL_VAL_FLG ADD_ON_COST_ME_FLG OTHR_COL_COMMENTS
				// // ADD_ON_FLG ITEM_ID ITEM_NAME
				// }
				// quotationForm.setDboEleAddOnList(addOnDboEleList);
				// }
				// for (DBOBean dboComp :
				// quotationForm.getQuotDboElectricalList()) {
				// for (DBOBean addon : quotationForm.getDboEleAddOnList()) {
				// if (dboComp.getItemId() == addon.getItemId()) {
				// dboComp.setPrice(dboComp.getPrice() - addon.getPrice());
				// }
				// }
				//
				// }
				// // get DBO ELectrical Addons Data Ends
				//
				// // get DBO Electrical Others Starts
				// ResultSet resultSetDbo = null;
				// List<DBOBean> genDboEleList = new ArrayList<>();
				// if (callableStatement.getMoreResults()) {
				// resultSetDbo = callableStatement.getResultSet();
				// while (resultSetDbo.next()) {
				// DBOBean bean = new DBOBean();
				// bean.setDboQuotDetId(resultSetDbo.getInt("DBO_QUOT_DET_ID"));
				// bean.setOtherCompId(resultSetDbo.getInt("ITEM_ID"));
				// bean.setOthCompName(resultSetDbo.getString("ITEM_NAME"));
				// bean.setOthPrice(Math.round(resultSetDbo.getFloat(ItoConstants.COST)));
				// bean.setOthRemarks(resultSetDbo.getString("COMMENTS"));
				// bean.setOthQuantity(resultSetDbo.getFloat("QTY"));
				// genDboEleList.add(bean);
				// }
				// quotationForm.setQuotDboEleOthersList(genDboEleList);
				//
				// }
				// // get DBO Electrical Others Ends
				// // DBO ELectrical Ends
				//
				// // Other Costs
				// // Variable Cost Starts
				ResultSet resultSetData10 = null;
				List<OtherCostsBean> quotVariableCostsList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetData10 = callableStatement.getResultSet();
					while (resultSetData10.next()) {
						OtherCostsBean otherCostsBean = new OtherCostsBean();

						otherCostsBean.setOtherItemId(resultSetData10.getInt("VR_ITEM_ID"));
						otherCostsBean.setQuotId(resultSetData10.getInt("QUOT_ID"));
						otherCostsBean.setInTravelExpensesReq(resultSetData10.getInt("IN_TRAVEL_EXPENSES_REQ"));
						otherCostsBean.setInsurance(resultSetData10.getFloat("INSURANCE"));
						otherCostsBean.setInNoOfVisit(resultSetData10.getInt("IN_NO_OF_VISIT"));
						otherCostsBean.setInCostForEachVisit(resultSetData10.getInt("IN_COST_FOR_EACH_VISIT"));
						otherCostsBean.setSplProvision(resultSetData10.getFloat("SPL_PROVISION"));
						otherCostsBean.setTravelExpenses(resultSetData10.getFloat(ItoConstants.TRAVEL_EXPENSES));
						otherCostsBean.setTurbineContigency(resultSetData10.getFloat(ItoConstants.CONT_FOR_TURBINE));
						otherCostsBean.setDboContigency(resultSetData10.getFloat(ItoConstants.CONT_FOR_DBO));
						otherCostsBean.setInpAgencyCommission(resultSetData10.getFloat("AGENCY_COMMISSION"));
						otherCostsBean.setSalesExpenses(resultSetData10.getFloat(ItoConstants.SALES_EXPENSES));
						otherCostsBean.setEngineCharges(resultSetData10.getFloat(ItoConstants.ENGIN_CHARGES));
						otherCostsBean.setIntrestNoOfMonths(resultSetData10.getFloat("INTR_NO_OF_MONTHS"));
						otherCostsBean.setOthers(resultSetData10.getFloat("OTHR"));
						otherCostsBean.setOthersRemarks(resultSetData10.getString("OTHR_REMARKS"));
						otherCostsBean.setWarrantyPeriod(resultSetData10.getFloat("WR"));
						otherCostsBean.setVarProfit(resultSetData10.getFloat("IN_PROFIT_PER"));
						otherCostsBean.setInpLdforLateDelivery(resultSetData10.getFloat("IN_LD_DL"));
						otherCostsBean.setInpLdPerformance(resultSetData10.getFloat("IN_LD_PER"));
						otherCostsBean.setIntrestPercentage(resultSetData10.getFloat("IN_INSTR_PER"));
						otherCostsBean.setInpBG1(resultSetData10.getFloat("IN_BG_CHR1"));
						otherCostsBean.setInpBG2(resultSetData10.getFloat("IN_BG_CHR2"));
						otherCostsBean.setTotOrderCost(resultSetData10.getFloat("ORDER_VALVE"));
						otherCostsBean.setContigencyGeneral(resultSetData10.getFloat(ItoConstants.CONT_FOR_GENERAL));
						otherCostsBean.setAgencyCommCharges(resultSetData10.getFloat("AGENCY_COMMISSION_CHRG"));
						otherCostsBean.setLdPerformance(resultSetData10.getFloat("LD_PERORMANCE"));
						otherCostsBean.setLdforLateDelivery(resultSetData10.getFloat("LD_LA_DEL"));
						otherCostsBean.setBankingCharges1(resultSetData10.getFloat("BG_CHR1"));
						otherCostsBean.setBankingCharges2(resultSetData10.getFloat("BG_CHR2"));
						otherCostsBean.setOvrheadSaleCost(resultSetData10.getFloat("OVR_SALE"));
						otherCostsBean.setOverRawMaterialCost(resultSetData10.getFloat("OVR_RAW_MTRL_COST"));
						otherCostsBean.setWarrantyCost(resultSetData10.getInt("WARREN"));
						otherCostsBean.setIntrestCharges(resultSetData10.getFloat("INTR_CHARG"));
						otherCostsBean.setTotalVariableCost(resultSetData10.getFloat(ItoConstants.TOTAL_COST));
						otherCostsBean
								.setVarNewFlag(resultSetData10.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						otherCostsBean.setVarNewCost(Math.round(resultSetData10.getFloat(ItoConstants.COST_ME)));

						quotVariableCostsList.add(otherCostsBean);
					}
					quotationForm.setVariableCostList(quotVariableCostsList);
				}
				// Variable Cost Ends

				// SPARES_COST Starts
				ResultSet resultSetData11 = null;
				List<OtherCostsBean> quotSparesList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetData11 = callableStatement.getResultSet();
					while (resultSetData11.next()) {
						OtherCostsBean otherCostsBean = new OtherCostsBean();

						otherCostsBean.setOtherItemId(resultSetData11.getInt("SP_ITEM_ID"));
						otherCostsBean.setInpSparesProfit(resultSetData11.getFloat("IN_PROFIT_PER"));
						otherCostsBean.setSparesCost(resultSetData11.getFloat(ItoConstants.SPARES_COST));
						otherCostsBean.setOvrheadSparesCost(resultSetData11.getFloat("OVERD_TURB"));
						otherCostsBean.setOvrheadTotSaleCost(resultSetData11.getFloat("OVERD_TOTAL_SALE"));
						otherCostsBean.setOrderBookingSpares(resultSetData11.getFloat("ORDER_BOOKING_SPARES"));
						otherCostsBean.setSparesNetProfit(resultSetData11.getFloat("NET_PROFIT_SPARES"));
						otherCostsBean.setPercentProfit(resultSetData11.getFloat("PERCENTT_PROFIT_SPARES"));
						otherCostsBean.setTotalSparesCost(resultSetData11.getFloat(ItoConstants.TOTAL_COST));
						otherCostsBean.setSparesNewCost(Math.round(resultSetData11.getFloat(ItoConstants.COST_ME)));
						otherCostsBean
								.setSparesNewFlag(resultSetData11.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						quotSparesList.add(otherCostsBean);
					}
					quotationForm.setSparesCostList(quotSparesList);
				}
				// // SPARES_COST Ends

				// // PROJECT COST Starts
				ResultSet resultSetData12 = null;
				List<OtherCostsBean> quotProjectCostList = new ArrayList<>();
				if (callableStatement.getMoreResults()) {
					resultSetData12 = callableStatement.getResultSet();
					while (resultSetData12.next()) {
						OtherCostsBean otherCostsBean = new OtherCostsBean();

						otherCostsBean.setOtherItemId(resultSetData12.getInt("PR_ITEM_ID"));
						otherCostsBean.setProjSupply(resultSetData12.getFloat("SUPPLY_PRICE"));
						otherCostsBean.setProjServices(resultSetData12.getFloat(ItoConstants.SERVICES_PRICE));
						otherCostsBean.setProjTransport(resultSetData12.getFloat("TRANSPORATION_PRICE"));
						otherCostsBean.setTotalProjectCost(resultSetData12.getFloat("TOTAL_PROJECT_COST_TURBINE"));
						otherCostsBean.setSupplyCost(resultSetData12.getFloat("SUPPLY_COST"));
						otherCostsBean.setServiceCost(resultSetData12.getFloat("SERVICES_COST"));
						otherCostsBean.setTransCost(resultSetData12.getFloat("TRANSP_COST"));
						otherCostsBean.setTotOrderCostNetProfit(resultSetData12.getFloat("NET_PROFIT"));
						otherCostsBean.setPercentProfit(resultSetData12.getFloat("PERCENT_PROFIT"));
						otherCostsBean.setTurbineOrderBookCost(resultSetData12.getFloat("ORDER_BOOK_COST_TURBINE"));
						otherCostsBean.setTotalFtfCost(resultSetData12.getFloat("TOTAL_FTF_COST"));
						otherCostsBean.setAddOnCost(resultSetData12.getFloat("ADDONCOST"));
						otherCostsBean.setPackageCost(resultSetData12.getFloat("PKG_COST"));
						otherCostsBean.setDboMechCost(resultSetData12.getFloat("DBO_MECH_COST"));
						otherCostsBean.setDboEleCost(resultSetData12.getFloat("DBO_ELE_COST"));
						otherCostsBean.setTotalVariableCost(resultSetData12.getFloat("VARIABLE_COST"));
						otherCostsBean.setProjectTotalCost(resultSetData12.getFloat(ItoConstants.TOTAL_COST));
						otherCostsBean.setProjectNewFlag(
								resultSetData12.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
						otherCostsBean.setProjectNewCost(Math.round(resultSetData12.getFloat(ItoConstants.COST_ME)));
						quotProjectCostList.add(otherCostsBean);
					}
					quotationForm.setProjectCostList(quotProjectCostList);
				}
				// // PROJECT COST Ends

				// // One line bom data below
				if (callableStatement.getMoreResults()) {
					resultSetMsg1 = callableStatement.getResultSet();
					while (resultSetMsg1.next()) {
						resultOutParameterInt = resultSetMsg1.getInt(1);
						resultOutParameterString = resultSetMsg1.getString(2);
						quotationForm.setSuccessCode(resultOutParameterInt);
						quotationForm.setSuccessMsg(resultOutParameterString);
						quotationForm.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);
					}
				}
				oneLineBomData(callableStatement, quotationForm);

				return quotationForm;
			}
			return quotationForm;
		} catch (SQLException e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);

			return quotationForm;
		} finally {
			UtilityMethods.closeResource(resultSetMsg);
			UtilityMethods.closeResource(resultSetQuot);
			UtilityMethods.closeResource(resultsetScope);
			UtilityMethods.closeResource(resultSetQuestions);
			UtilityMethods.closeResource(resultSetMsg1);
			UtilityMethods.closeResource(resultSetSap);
			UtilityMethods.closeResource(resultSetF2FExcel);
			UtilityMethods.closeResource(resultSetSap);
		}

	}

	private QuotationForm oneLineBomData(CallableStatement callableStatement, QuotationForm quotationForm) {

		ResultSet resultSetSS = null;
		ResultSet resultSetF2F = null;
		ResultSet resultSetF2FAddOn = null;
		ResultSet resultSetTrans = null;
		ResultSet resultSetEc = null;
		ResultSet resultSetPkg = null;
		ResultSet resultSetDboMech = null;
		ResultSet resultSetDboMechAux = null;
		ResultSet resultSetDboMechExt = null;
		ResultSet resultSetDboEle = null;
		ResultSet resultSetDboEleAux = null;
		ResultSet resultSetDboEleExt = null;
		ResultSet resultSetDboCI = null;
		ResultSet resultSetDboCIAux = null;
		ResultSet resultSetDboCIExt = null;
		ResultSet resultSetDBOEleCI = null;
		ResultSet resultSetDBOEleCIAux = null;
		ResultSet resultSetDBOEle = null;
		ResultSet resultSetDBOEleAux = null;
		ResultSet resultSetOtherCosts = null;
		ResultSet resultSetAddOnSelList = null;
		ResultSet resultSetProjCost = null;
		ResultSet resultSetSpares = null;
		ResultSet resultSetf2fExcel = null;
		ResultSet resultSetDBOF2f = null;

		OneLineBomData f2FForm = new OneLineBomData();
		List<TurbineDetails> scopeList1 = new ArrayList<>();
		DecimalFormat formatNum = new DecimalFormat("#,###");

		try {
			if (callableStatement.getMoreResults()) {
				resultSetSS = callableStatement.getResultSet();

				while (resultSetSS.next()) {

					TurbineDetails bean = new TurbineDetails();
					bean.setSsId(resultSetSS.getInt(ItoConstants.SS_ID));
					bean.setCategoryDetCode(resultSetSS.getString(ItoConstants.SCOPE_CD));
					bean.setSsName(resultSetSS.getString(ItoConstants.SCOPE_OF_SUPPLY));
					scopeList1.add(bean);
				}
				quotationForm.setSelectedScopeList(scopeList1);
			}

			if (callableStatement.getMoreResults()) {
				resultSetF2F = callableStatement.getResultSet();

				while (resultSetF2F.next()) {

					f2FForm.setF2fCostFlag(resultSetF2F.getFloat("F2F_COST_FLG") == 1 ? true : false);
					f2FForm.setF2fCostMeFlag(resultSetF2F.getFloat("F2F_COST_ME_FLG") == 1 ? true : false);
					f2FForm.setF2fCost(Math.round(resultSetF2F.getFloat("F2F_COST")));
					f2FForm.setTotalF2fCost(Math.round(resultSetF2F.getFloat("TOTAL_F2F_COST")));
					f2FForm.setF2fAddOnCostFlag(resultSetF2F.getFloat("F2F_ADD_ON_COST_FLG") == 1 ? true : false);
					f2FForm.setTotalF2fAddOnCost(Math.round(resultSetF2F.getFloat("TOTAL_F2F_ADD_ON_COST")));
					f2FForm.setTotalMechCost(Math.round(resultSetF2F.getFloat("TOTAL_MECH_COST")));
					f2FForm.setDboMechCost(Math.round(resultSetF2F.getFloat("DBO_MECH_COST")));
					f2FForm.setDboMechFlag(resultSetF2F.getFloat("DBO_MECH_FLG") == 1 ? true : false);
					f2FForm.setDboMechAuxCost(Math.round(resultSetF2F.getFloat("DBO_MECH_AUX_COST")));
					f2FForm.setDboMechAuxFlag(resultSetF2F.getFloat("DBO_MECH_AUX_FLG") == 1 ? true : false);
					f2FForm.setDboMechExtCost(Math.round(resultSetF2F.getFloat("DBO_MECH_EXT_COST")));
					f2FForm.setDboMechExtFlag(resultSetF2F.getFloat("DBO_MECH_EXT_FLG") == 1 ? true : false);
					f2FForm.setTotalDboEleCost(Math.round(resultSetF2F.getFloat("TOTAL_DBO_ELE_COST")));
					f2FForm.setDboEleFlag(resultSetF2F.getFloat("DBO_ELE_FLG") == 1 ? true : false);
					f2FForm.setDboEleCost(Math.round(resultSetF2F.getFloat("ELE_COST")));
					f2FForm.setEleFlag(resultSetF2F.getFloat("ELE_FLG") == 1 ? true : false);
					f2FForm.setDboEleAuxCost(Math.round(resultSetF2F.getFloat("ELE_AUX_COST")));
					f2FForm.setEleAuxFlag(resultSetF2F.getFloat("ELE_AUX_FLG") == 1 ? true : false);
					f2FForm.setDboEleExtCost(Math.round(resultSetF2F.getFloat("ELE_EXT_COST")));
					f2FForm.setDboEleExtFlag(resultSetF2F.getFloat("ELE_EXT_FLG") == 1 ? true : false);
					f2FForm.setTotalDboCiCost(Math.round(resultSetF2F.getFloat("TOTAL_DBO_CI_COST")));
					//f2FForm.setDboEleFlag(resultSetF2F.getFloat("DBO_ELE_FLG") == 1 ? true : false);
					f2FForm.setcICost(Math.round(resultSetF2F.getFloat("CI_COST")));
					f2FForm.setcIFlag(resultSetF2F.getFloat("CI_FLG") == 1 ? true : false);
					f2FForm.setcIAuxCost(Math.round(resultSetF2F.getFloat("CI_AUX_COST")));
					f2FForm.setcIAuxFlag(resultSetF2F.getFloat("CI_AUX_FLG") == 1 ? true : false);
					f2FForm.setcIExtCost(Math.round(resultSetF2F.getFloat("CI_EXT_COST")));
					f2FForm.setcIExtFlag(resultSetF2F.getFloat("CI_EXT_FLG") == 1 ? true : false);
					f2FForm.setVarCostFlag(resultSetF2F.getFloat("VARIABLE_COST_FLG") == 1 ? true : false);
					f2FForm.setSparesFlag(resultSetF2F.getFloat("SPARES_COST_FLG") == 1 ? true : false);
					f2FForm.setProjectCostFlag(resultSetF2F.getFloat("PROJECT_COST_FLG") == 1 ? true : false);
					f2FForm.setPkgNewCostFlag(resultSetF2F.getFloat("PKG_COST_ME_FLG") == 1 ? true : false);
					f2FForm.setEcNewCostFlag(resultSetF2F.getFloat("EC_COST_ME_FLG") == 1 ? true : false);
					f2FForm.setTransNewCostFlag(resultSetF2F.getFloat("TRNS_COST_ME_FLG") == 1 ? true : false);
					f2FForm.setDboMechNewCostFlag(resultSetF2F.getFloat("DBO_MECH_COST_ME_FLG") == 1 ? true : false);
					f2FForm.setDboEleNewCostFlag(resultSetF2F.getFloat("DBO_ELE_COST_ME_FLG") == 1 ? true : false);
					f2FForm.setVarNewCostFlag(resultSetF2F.getFloat("VARIABLE_COST_ME_FLG") == 1 ? true : false);
					f2FForm.setSparesNewCostFlag(resultSetF2F.getFloat("SPARES_COST_ME_FLG") == 1 ? true : false);
					f2FForm.setProjectNewCostFlag(resultSetF2F.getFloat("PROJECT_COST_ME_FLG") == 1 ? true : false);

				}
			}

			List<TurbineDetails> f2fAddOnList = new ArrayList<>();
			
				if ( f2FForm.isF2fAddOnCostFlag()) {

					if (callableStatement.getMoreResults()) {
						resultSetF2FAddOn = callableStatement.getResultSet();
						while (resultSetF2FAddOn.next()) {
							TurbineDetails bean = new TurbineDetails();

							bean.setCategoryDetDesc(resultSetF2FAddOn.getString("ITEM_NM"));
							bean.setPrice(Math.round(resultSetF2FAddOn.getFloat(ItoConstants.COST)));
							f2fAddOnList.add(bean);
						}
						f2FForm.setF2fAddOnList(f2fAddOnList);
					}
				}
			

			List<TurbineDetails> f2fList = new ArrayList<>();
			logger.info(f2FForm.isF2fCostFlag());
			
			if (f2FForm.isF2fCostFlag()) {
			for (TurbineDetails scope : scopeList1) {
				logger.info(scope.getCategoryDetCode());
				if (scope.getCategoryDetCode().equals("F2F") && f2FForm.isF2fCostFlag()) {
					logger.info("f2f check");	
					if (callableStatement.getMoreResults()) {
						resultSetF2F = callableStatement.getResultSet();
						while (resultSetF2F.next()) {
							TurbineDetails bean = new TurbineDetails();
							// bean.setCategoryDetCode("F2F");
							bean.setCategoryDetDesc(resultSetF2F.getString("F2F_ITEM"));
							bean.setPrice(Math.round(resultSetF2F.getFloat(ItoConstants.COST)));
							f2fList.add(bean);
						}
						f2FForm.setF2fList(f2fList);
					}
				}
			}
			}
			logger.info("f2f check2");
			if (f2FForm.isF2fCostFlag()) {
				logger.info("f2f check3");
				if (callableStatement.getMoreResults()) {
					resultSetf2fExcel = callableStatement.getResultSet();

					while (resultSetf2fExcel.next()) {
						f2FForm.setTurbineMaterialCost(Math.round(resultSetf2fExcel.getFloat("TURBINE_MTRL_COST")));
						f2FForm.setSubContrCost(Math.round(resultSetf2fExcel.getFloat("SUB_CONTR_COST")));
						f2FForm.setShopCoverCost(Math.round(resultSetf2fExcel.getFloat("SHOP_CONV_COST")));
						f2FForm.setTotalF2FCost(Math.round(resultSetf2fExcel.getFloat("TOTAL_F2F_COST")));
					}
				}
			}

			logger.info("trns start");
			if (callableStatement.getMoreResults()) {
				resultSetTrans = callableStatement.getResultSet();
				TurbineDetails bean = new TurbineDetails();
				while (resultSetTrans.next()) {
					logger.info("inside trns result");
					logger.info(resultSetTrans.getFloat("TRNS_COST"));
					logger.info(resultSetTrans.getString("CODE"));
					bean.setPrice(Math.round(resultSetTrans.getFloat("TRNS_COST")));
				
					bean.setCategoryDetCode(resultSetTrans.getString("CODE"));
					
				}
				f2FForm.getCostSheetList().add(bean);
			}
			logger.info("trns end");
			if (callableStatement.getMoreResults()) {
				resultSetEc = callableStatement.getResultSet();
				TurbineDetails bean = new TurbineDetails();
				while (resultSetEc.next()) {
					bean.setPrice(Math.round(resultSetEc.getFloat("EC_COST")));
					bean.setCategoryDetCode(resultSetEc.getString("CODE"));

				}
				f2FForm.getCostSheetList().add(bean);
			}

			if (callableStatement.getMoreResults()) {
				resultSetPkg = callableStatement.getResultSet();
				TurbineDetails bean = new TurbineDetails();
				while (resultSetPkg.next()) {
					bean.setPrice(Math.round(resultSetPkg.getFloat("PKG_COST")));
					bean.setCategoryDetCode(resultSetPkg.getString("CODE"));

				}
				f2FForm.getCostSheetList().add(bean);
			}

			List<TurbineDetails> dboMechList = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("MECH") && f2FForm.isDboMechFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboMech = callableStatement.getResultSet();
						while (resultSetDboMech.next()) {
							TurbineDetails bean = new TurbineDetails();

							bean.setCategoryDetDesc(resultSetDboMech.getString("DBO_MECH_ITEM"));
							bean.setPrice(Math.round(resultSetDboMech.getFloat(ItoConstants.COST)));
							dboMechList.add(bean);
						}
						f2FForm.setDboMechList(dboMechList);
					}

				}
			}

			List<TurbineDetails> dboMechAuxList = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("MECH") && f2FForm.isDboMechAuxFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboMechAux = callableStatement.getResultSet();
						while (resultSetDboMechAux.next()) {
							TurbineDetails bean = new TurbineDetails();

							bean.setCategoryDetDesc(resultSetDboMechAux.getString("DBO_MECH_AUX_ITEM"));
							bean.setPrice(Math.round(resultSetDboMechAux.getFloat(ItoConstants.COST)));
							dboMechAuxList.add(bean);
						}
						f2FForm.setDboMechAuxList(dboMechAuxList);
					}

				}
			}

			List<TurbineDetails> dboMechExtList = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("MECH") && f2FForm.isDboMechExtFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboMechExt = callableStatement.getResultSet();
						while (resultSetDboMechExt.next()) {
							TurbineDetails bean = new TurbineDetails();

							bean.setCategoryDetDesc(resultSetDboMechExt.getString("DBO_MECH_EXT_SCOPE"));
							bean.setPrice(Math.round(resultSetDboMechExt.getFloat(ItoConstants.COST)));
							dboMechExtList.add(bean);
						}
						f2FForm.setDboMechExtList(dboMechExtList);
					}

				}
			}

			List<TurbineDetails> dboEleList = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("ELE") && f2FForm.isEleFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboEle = callableStatement.getResultSet();
						while (resultSetDboEle.next()) {
							TurbineDetails bean = new TurbineDetails();
							bean.setQuotId(resultSetDboEle.getInt("QUOT_ID"));
							bean.setCategoryDetDesc(resultSetDboEle.getString("ELE_ITEM"));
							bean.setPrice(Math.round(resultSetDboEle.getFloat(ItoConstants.COST)));
							dboEleList.add(bean);
						}
						f2FForm.setDboEleList(dboEleList);
					}

				}
			}

			List<TurbineDetails> dboEleAuxList = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("ELE") && f2FForm.isEleAuxFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboEleAux = callableStatement.getResultSet();
						while (resultSetDboEleAux.next()) {
							TurbineDetails bean = new TurbineDetails();

							bean.setQuotId(resultSetDboEleAux.getInt("QUOT_ID"));
							bean.setCategoryDetDesc(resultSetDboEleAux.getString("ELE_AUX_ITEM"));
							bean.setPrice(Math.round(resultSetDboEleAux.getFloat(ItoConstants.COST)));
							dboEleAuxList.add(bean);
						}
						f2FForm.setDboEleAuxList(dboEleAuxList);
					}

				}
			}

			List<TurbineDetails> dboEleExtList1 = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("ELE") && f2FForm.isDboEleExtFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboEleExt = callableStatement.getResultSet();
						while (resultSetDboEleExt.next()) {
							TurbineDetails bean = new TurbineDetails();

							bean.setCategoryDetDesc(resultSetDboEleExt.getString("DBO_ELE_EXT_SCOPE"));
							bean.setPrice(Math.round(resultSetDboEleExt.getFloat(ItoConstants.COST)));
							dboEleExtList1.add(bean);
						}
						f2FForm.setDboEleExtList1(dboEleExtList1);
					}

				}
			}

			List<TurbineDetails> dboCIList = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("CI") && f2FForm.iscIFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboCI = callableStatement.getResultSet();
						while (resultSetDboCI.next()) {
							TurbineDetails bean = new TurbineDetails();
							bean.setQuotId(resultSetDboCI.getInt("QUOT_ID"));
							bean.setCategoryDetDesc(resultSetDboCI.getString("CI_ITEM"));
							bean.setPrice(Math.round(resultSetDboCI.getFloat(ItoConstants.COST)));
							dboCIList.add(bean);
						}
						f2FForm.setDboCIList(dboCIList);
					}

				}
			}

			List<TurbineDetails> dboCIAuxList = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("CI") && f2FForm.iscIAuxFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboCIAux = callableStatement.getResultSet();
						while (resultSetDboCIAux.next()) {
							TurbineDetails bean = new TurbineDetails();

							bean.setCategoryDetDesc(resultSetDboCIAux.getString("CI_AUX_ITEM"));
							bean.setPrice(Math.round(resultSetDboCIAux.getFloat(ItoConstants.COST)));
							dboCIAuxList.add(bean);
						}
						f2FForm.setDboCIAuxList(dboCIAuxList);
					}

				}
			}

			List<TurbineDetails> dboCIExtList = new ArrayList<>();
			for (TurbineDetails scope : scopeList1) {
				if (scope.getCategoryDetCode().equals("CI") && f2FForm.iscIExtFlag()) {
					if (callableStatement.getMoreResults()) {
						resultSetDboCIExt = callableStatement.getResultSet();
						while (resultSetDboCIExt.next()) {
							TurbineDetails bean = new TurbineDetails();

							bean.setCategoryDetDesc(resultSetDboCIExt.getString("CI_EXT_SCOPE"));
							bean.setPrice(Math.round(resultSetDboCIExt.getFloat(ItoConstants.COST)));
							dboCIExtList.add(bean);
						}
						f2FForm.setDboCIExtList(dboCIExtList);
					}

				}
			}

			if (f2FForm.isVarCostFlag()) {
				if (callableStatement.getMoreResults()) {
					resultSetOtherCosts = callableStatement.getResultSet();
					while (resultSetOtherCosts.next()) {
						f2FForm.getOtherCostsBean().setInsurance(Math.round(resultSetOtherCosts.getFloat("Insurance")));
						f2FForm.getOtherCostsBean()
								.setTravelExpenses(Math.round(resultSetOtherCosts.getFloat("Travel Expenses")));
						f2FForm.getOtherCostsBean().setTurbineContigency(
								Math.round(resultSetOtherCosts.getFloat("Contingency for Turbine")));
						f2FForm.getOtherCostsBean()
								.setDboContigency(Math.round(resultSetOtherCosts.getFloat("Contingency for DBO")));
						f2FForm.getOtherCostsBean()
								.setAgencyCommCharges(Math.round(resultSetOtherCosts.getFloat("Agency Commission")));
						f2FForm.getOtherCostsBean()
								.setSalesExpenses(Math.round(resultSetOtherCosts.getFloat("Sales Expenses")));
						f2FForm.getOtherCostsBean()
								.setEngineCharges(Math.round(resultSetOtherCosts.getFloat("Engineering Charges")));
						f2FForm.getOtherCostsBean().setOthers(Math.round(resultSetOtherCosts.getFloat("Others")));
						f2FForm.getOtherCostsBean()
								.setContigencyGeneral(Math.round(resultSetOtherCosts.getFloat("Contingency General")));

						f2FForm.getOtherCostsBean()
								.setLdPerformance(Math.round(resultSetOtherCosts.getFloat("LD For Performance")));
						f2FForm.getOtherCostsBean()
								.setLdforLateDelivery(Math.round(resultSetOtherCosts.getFloat("LD For Late Delivery")));
						f2FForm.getOtherCostsBean()
								.setBankingCharges1(Math.round(resultSetOtherCosts.getFloat("BG Charges For ABG")));
						f2FForm.getOtherCostsBean()
								.setBankingCharges2(Math.round(resultSetOtherCosts.getFloat("BG Charges For PBG")));
						f2FForm.getOtherCostsBean().setOvrheadSaleCost(
								Math.round(resultSetOtherCosts.getFloat("Overhead on Total Sale Value")));
						f2FForm.getOtherCostsBean().setOverRawMaterialCost(
								Math.round(resultSetOtherCosts.getFloat("Overhead on Raw Material Cost")));
						f2FForm.getOtherCostsBean()
								.setWarrantyCost(Math.round(resultSetOtherCosts.getFloat("Warranty Cost")));
						f2FForm.getOtherCostsBean()
								.setIntrestCharges(Math.round(resultSetOtherCosts.getFloat("Interest Charges")));
						f2FForm.getOtherCostsBean()
								.setTotalVariableCost(Math.round(resultSetOtherCosts.getFloat("Total Variable Cost")));

					}
				}

			}

			if (f2FForm.isSparesFlag()) {
				if (callableStatement.getMoreResults()) {
					resultSetSpares = callableStatement.getResultSet();
					while (resultSetSpares.next()) {
						f2FForm.getOtherCostsBean()
								.setOvrheadSparesCost(resultSetSpares.getFloat("Over Heads of Spares cost"));
						f2FForm.getOtherCostsBean()
								.setOvrheadTotSaleCost(resultSetSpares.getFloat("Over heads on Total Sale Value"));
						f2FForm.getOtherCostsBean()
								.setOrderBookingSpares(resultSetSpares.getFloat("Order Booking Price for spares"));
						f2FForm.getOtherCostsBean().setSparesNetProfit(resultSetSpares.getFloat("Net Profit"));
						f2FForm.getOtherCostsBean()
								.setTotalSparesCost(resultSetSpares.getFloat("Total Project Cost for Spares"));

					}

				}
			}

			if (f2FForm.isProjectCostFlag()) {
				if (callableStatement.getMoreResults()) {
					resultSetProjCost = callableStatement.getResultSet();
					while (resultSetProjCost.next()) {
						f2FForm.getOtherCostsBean()
								.setTurbineSparesCost(resultSetProjCost.getFloat("Cost For Turbine And Spares"));
						f2FForm.getOtherCostsBean().setTotOrderCostNetProfit(resultSetProjCost.getFloat("Net Profit"));
						f2FForm.getOtherCostsBean().setPercentProfit(resultSetProjCost.getFloat("Percent Profit"));
						f2FForm.getOtherCostsBean().setTurbineOrderBookCost(
								resultSetProjCost.getFloat("Order Booking Price For Turbine And Spares"));
					}
				}
			}

			List<AddOnComponent> addOnCompList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetAddOnSelList = callableStatement.getResultSet();
				while (resultSetAddOnSelList.next()) {
					AddOnComponent addOn = new AddOnComponent();
					addOn.setAddOnCompo_cd(resultSetAddOnSelList.getString(ItoConstants.ADD_ON_COMP_CD));
					addOn.setSelectedCost(Math.round(resultSetAddOnSelList.getFloat(ItoConstants.COST)));
					addOnCompList.add(addOn);
				}
				f2FForm.setAddOnsList(addOnCompList);
				quotationForm.setSubmittedAddOnListCost(addOnCompList);

			}
			quotationForm.setOneLineBomExcel(f2FForm);
		} catch (SQLException e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			quotationForm.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(resultSetSS);
			UtilityMethods.closeResource(resultSetF2F);
			UtilityMethods.closeResource(resultSetTrans);
			UtilityMethods.closeResource(resultSetEc);
			UtilityMethods.closeResource(resultSetPkg);
			UtilityMethods.closeResource(resultSetDboMech);
			UtilityMethods.closeResource(resultSetDBOEle);
			UtilityMethods.closeResource(resultSetAddOnSelList);
		}
		return quotationForm;

	}

	@Override
	public SaveBasicDetails scopeOfSupplyStatus(SaveBasicDetails saveBasicDetails) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;

		List<ScopeOfSupply> scopeOfSupplyList = new ArrayList<>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SCOPE_OF_SUPPLY_STATUS (?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());
			callableStatement.setInt("SCOPE_ID", saveBasicDetails.getScopeId());
			callableStatement.setInt(ItoConstants.STATUS_FLG, saveBasicDetails.getScopeStatusFlg());
			callableStatement.setInt(ItoConstants.MOD_BY, saveBasicDetails.getModifiedById());
			callableStatement.setInt("ROLES_ID", saveBasicDetails.getUserRoleId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();

				while (resultSetData.next()) {
					ScopeOfSupply scopeOfSupply = new ScopeOfSupply();
					scopeOfSupply.setScopeId(resultSetData.getInt(ItoConstants.SS_ID));
					scopeOfSupply.setScopeCode(resultSetData.getString(ItoConstants.SCOPE_CD));
					scopeOfSupply.setScopeName(resultSetData.getString(ItoConstants.SCOPE_OF_SUPPLY));
					scopeOfSupply.setStatus(resultSetData.getString(ItoConstants.STATUS));
					scopeOfSupplyList.add(scopeOfSupply);
				}
				saveBasicDetails.setScopeOfSupplyList(scopeOfSupplyList);
			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails quotWorkFlow(SaveBasicDetails saveBasicDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_QUOT_UPDATE_STATUS (?,?,?,?,?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());
			callableStatement.setInt(ItoConstants.ROLE_ID, saveBasicDetails.getUserRoleId());
			if (saveBasicDetails.isEditFlag()) {
				callableStatement.setInt("EDIIT_FLG", 1);
			} else {
				callableStatement.setInt("EDIIT_FLG", 0);
			}
			if (saveBasicDetails.isSubmitFlag()) {
				callableStatement.setInt("SUBMIT_FLG", 1);
			} else {
				callableStatement.setInt("SUBMIT_FLG", 0);
			}

			if (saveBasicDetails.isStatusFlag()) {
				callableStatement.setInt(ItoConstants.STATUS_FLG, 1);
			} else {
				callableStatement.setInt(ItoConstants.STATUS_FLG, 0);
			}
			callableStatement.setString(ItoConstants.STATUS, saveBasicDetails.getStatusCode());
			callableStatement.setInt(ItoConstants.ASSIGNED_TO, saveBasicDetails.getAssignedTo());
			callableStatement.setString(ItoConstants.REMARKS, saveBasicDetails.getRemarks());
			callableStatement.setInt(ItoConstants.MOD_BY, saveBasicDetails.getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				if (resultOutParameterInt == 0){
					List<List> list1 = new ArrayList<List>();
					list1 = getEmailList();
					//logger.info("Testlevel1");
					//logger.info(list1.size());
					//logger.info(list1);
					//logger.info(QuotationDaoImpl.test);
					for (int i = 0; i<list1.size();i++){
						//logger.info("User Details Start");
						//logger.info((int)list1.get(i).get(0));
						//logger.info((String)list1.get(i).get(1));
						//logger.info((String)list1.get(i).get(2));
						//logger.info("User Details End");
						if ((int)list1.get(i).get(0) == saveBasicDetails.getAssignedTo()){
							logger.info("Test Email");
							//logger.info(quotationForm.getSaveBasicDetails().getAssignedTo());
							logger.info((String)list1.get(i).get(1));
							logger.info((String)list1.get(i).get(2));
							saveBasicDetails.setName((String)list1.get(i).get(1));
							saveBasicDetails.setEmail((String)list1.get(i).get(2));
						}
					}
				}	
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}
			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();
				while (resultSetData.next()) {
					saveBasicDetails.setSuccessCode(resultSetData.getInt(1));
					saveBasicDetails.setSuccessMsg(resultSetData.getString(2));
					saveBasicDetails.getMsgToUser().put(resultSetData.getInt(1), resultSetData.getString(2));

				}

			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails quotStatusComplete(SaveBasicDetails saveBasicDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_QUOT_STATUS_COMPLETE (?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());
			callableStatement.setInt("STATUS_FLG", saveBasicDetails.getQuotStatusFlg());
			callableStatement.setInt(ItoConstants.MOD_BY, saveBasicDetails.getModifiedById());
			callableStatement.setInt("ROLES_ID", saveBasicDetails.getUserRoleId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails saveF2FSap(SaveBasicDetails saveBasicDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_F2F_SAP (?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());
			callableStatement.setString(ItoConstants.C_NUM, saveBasicDetails.getLatestCNum());
			callableStatement.setString("VARIANT_CD", saveBasicDetails.getVariantCode());
			callableStatement.setInt(ItoConstants.MOD_BY, saveBasicDetails.getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails saveRemarks(SaveBasicDetails saveBasicDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_QUOT_REMARKS (?,?,?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());
			callableStatement.setString(ItoConstants.GROUP_CD, saveBasicDetails.getGroupCode());
			callableStatement.setString(ItoConstants.SCOPE_CD, saveBasicDetails.getScopeCode());
			callableStatement.setFloat(ItoConstants.COST_ME, saveBasicDetails.getOverwrittenCost());
			callableStatement.setString(ItoConstants.REMARKS, saveBasicDetails.getRemarks());
			callableStatement.setInt(ItoConstants.ROLE_ID, saveBasicDetails.getUserRoleId());
			callableStatement.setInt(ItoConstants.MOD_BY, saveBasicDetails.getModifiedById());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails getF2FSapData(SaveBasicDetails saveBasicDetails) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_SAP (?) }");

			callableStatement.setString(ItoConstants.C_NUM, saveBasicDetails.getLatestCNum());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();

				while (resultSetData.next()) {

					saveBasicDetails.getSapData()
							.setTurbineMaterialCost(Math.round(resultSetData.getFloat(ItoConstants.TURBINE_MTRL)));
					saveBasicDetails.getSapData()
							.setShopCoverCost(Math.round(resultSetData.getFloat(ItoConstants.SHOP_CONV)));
					saveBasicDetails.getSapData()
							.setSubContrCost(Math.round(resultSetData.getFloat(ItoConstants.SUB_CONTRACT)));
					saveBasicDetails.getSapData().setTotal(Math.round(resultSetData.getFloat(ItoConstants.TOTAL)));

				}

			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails getF2FUboData(SaveBasicDetails saveBasicDetails) {

		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_F2F_UBO_MAST (?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();

				while (resultSetData.next()) {

					saveBasicDetails.getUboData()
							.setTurbineMaterialCost(Math.round(resultSetData.getFloat("TURB_COST")));
					saveBasicDetails.getUboData().setSubContrCost(Math.round(resultSetData.getFloat("SUB_CONTR_COST")));
					saveBasicDetails.getUboData()
							.setShopCoverCost(Math.round(resultSetData.getFloat("SHOP_CONV_COST")));
					saveBasicDetails.getUboData().setTotalF2FCost(Math.round(resultSetData.getFloat("TOTAL_FTF_COST")));

				}

			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails saveF2FUboData(SaveBasicDetails saveBasicDetails) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_F2F_UBO_MAST (?,?,?,?) }");

			callableStatement.setInt(ItoConstants.COST_ME_FLG,
					saveBasicDetails.getUboData().isOverwrittenPriceFlg() ? 1 : 0);
			callableStatement.setFloat(ItoConstants.COST_ME, saveBasicDetails.getUboData().getOverwrittenPrice());
			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());
			callableStatement.setInt(ItoConstants.MOD_BY, saveBasicDetails.getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails getQuotRemarks(SaveBasicDetails saveBasicDetails) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;

		List<CommentBean> remarksList = new ArrayList<>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_GET_QUOT_REMARKS (?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());
			callableStatement.setString(ItoConstants.GROUP_CD, saveBasicDetails.getGroupCode());
			callableStatement.setString(ItoConstants.SCOPE_CD, saveBasicDetails.getScopeCode());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}
			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();

				while (resultSetData.next()) {
					CommentBean bean = new CommentBean();
					bean.setRevNo(resultSetData.getInt(ItoConstants.REV_NO));
					bean.setRoleId(resultSetData.getInt(ItoConstants.ROLE_ID));
					bean.setRoleName(resultSetData.getString(ItoConstants.ROLE_NAME));
					bean.setComment(resultSetData.getString(ItoConstants.REMARKS));
					bean.setOverwrittenCost(resultSetData.getFloat(ItoConstants.COST_ME));
					bean.setStatusId(resultSetData.getInt(ItoConstants.STATUS_ID));
					bean.setStatusName(resultSetData.getString(ItoConstants.STATUS_NAME));
					bean.setModifiedBy(resultSetData.getInt(ItoConstants.MOD_BY));
					bean.setModifiedByName(resultSetData.getString("MOD_BY_NM"));
					bean.setModifiedDate(resultSetData.getString(ItoConstants.MOD_DT));

					remarksList.add(bean);
				}
				saveBasicDetails.setRemarksList(remarksList);
			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData);
		}
		return saveBasicDetails;
	}

	@Override
	public SaveBasicDetails saveQuotRemarks(SaveBasicDetails saveBasicDetails, String groupCode, String scopeCode,
			float overwrittenCost, String remarks) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_SAVE_QUOT_REMARKS (?,?,?,?,?,?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, saveBasicDetails.getQuotId());
			callableStatement.setString(ItoConstants.GROUP_CD, groupCode);
			callableStatement.setString(ItoConstants.SCOPE_CD, scopeCode);
			callableStatement.setFloat(ItoConstants.COST_ME, overwrittenCost);
			callableStatement.setString(ItoConstants.REMARKS, remarks);
			callableStatement.setInt(ItoConstants.ROLE_ID, saveBasicDetails.getUserRoleId());
			callableStatement.setInt(ItoConstants.MOD_BY, saveBasicDetails.getModifiedById());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				saveBasicDetails.setSuccessCode(resultOutParameterInt);
				saveBasicDetails.setSuccessMsg(resultOutParameterString);
				saveBasicDetails.getMsgToUser().put(resultOutParameterInt, resultOutParameterString);

			}

		} catch (Exception e) {
			saveBasicDetails.setSuccessCode(-1);
			saveBasicDetails.setSuccessMsg(TECHNICAL_EXCEPTION);
			saveBasicDetails.getMsgToUser().put(-1, TECHNICAL_EXCEPTION);
			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}
		return saveBasicDetails;
	}

	@Override
	public QuotationForm getQuotRevNo(Integer quotId) {

		QuotationForm quotationForm = new QuotationForm();
		try {

			String query = "SELECT  REV_NO FROM TURB_CONFIG_MAST_REV A WHERE QUOT_ID ='" + quotId + "';";

			List<Integer> data = jdbcTemplate.query(query, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt("REV_NO");
				}
			});

			logger.info("data " + data);

			quotationForm.getSaveBasicDetails().setRevList(data);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}
		return quotationForm;

	}

	@Override
	public QuotationForm getQuestionInfo(Integer framePowerId) {

		QuotationForm quotationForm = new QuotationForm();
		try {

			String query = " SELECT  B.QUST_DESC ,B.QUST_CD,A.ANS_DESC AS ANSWER, A.ANS_CD, (CASE WHEN C.DEFLT_FLG = 1 THEN 'TRUE' ELSE 'FALSE'  END) AS DEFLT_VALUE, "
					+ "(SELECT D.FRM_NM FROM FRAMES D WHERE D.FRM_ID = E.FRM_ID AND E.FRAME_POW_ID = C.FRM_POW_ID) AS FRAME_NAME "
					+ "FROM F2F_ANS_MAST A, F2F_QUST_MAST B,F2F_TURB_QUST_ANS C,FRAMES_MAST E WHERE A.QUST_ID = B.QUST_ID AND A.QUST_ID = C.QUST_ID AND A.ANS_ID = C.ANS_ID "
					+ "AND  A.IS_ACTIVE = 1 AND  B.IS_ACTIVE = 1 AND  C.IS_ACTIVE = 1 AND C.FRM_POW_ID = E.FRAME_POW_ID AND C.FRM_POW_ID  ='"
					+ framePowerId + "';";

			List<QuestionsEntity> data = jdbcTemplate.query(query, new RowMapper<QuestionsEntity>() {
				public QuestionsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
					QuestionsEntity questionsEntity = new QuestionsEntity();
					questionsEntity.setQuestionDesc((rs.getString(ItoConstants.QUST_DESC)));
					questionsEntity.setQuestionCode(rs.getString("QUST_CD"));
					questionsEntity.setAnswerDesc(rs.getString("ANSWER"));
					questionsEntity.setAnswerCd(rs.getString("ANS_CD"));
					questionsEntity.setDefaultVal((rs.getString(ItoConstants.DEFLT_VALUE) == "TRUE" ? true : false));
					if (questionsEntity.isDefaultVal()) {
						questionsEntity.setAnswerDesc(rs.getString("ANSWER") + " (TTL STD)");
					} else {
						questionsEntity.setAnswerDesc(rs.getString("ANSWER"));
					}
					questionsEntity.setFrameName(rs.getString(ItoConstants.FRAME_NAME));
					return questionsEntity;
				}
			});

			quotationForm.setQuestionsEntityList(data);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}
		return quotationForm;

	}

	@Override
	public QuotationForm getPackageCache(QuotationForm quotationForm) {

		Map<String, List<TurbineDetails>> packageList = new HashMap<>();
		try {

			String query = "SELECT CAT_DET_ID,GRP_CD,CAT_DET_CD,CAT_DET_DESC,CREAT_DT,MOD_DT,CREAT_BY,MOD_BY,IS_ACTIVE,EFF_FROM,EFF_TO FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'PKG' AND CAT_CD = 'PT' ) AND IS_ACTIVE = 1;";

			List<TurbineDetails> packageArray = jdbcTemplate.query(query, new RowMapper<TurbineDetails>() {
				public TurbineDetails mapRow(ResultSet resultSetPackage, int rowNum) throws SQLException {
					TurbineDetails turbineDetails = new TurbineDetails();
					turbineDetails.setCategoryDetId(resultSetPackage.getInt(ItoConstants.CAT_DET_ID));
					turbineDetails.setCategoryDetCode(resultSetPackage.getString(ItoConstants.CAT_DET_CD));
					turbineDetails.setDependentCode(resultSetPackage.getString(ItoConstants.GRP_CD));
					turbineDetails.setCategoryDetDesc(resultSetPackage.getString(ItoConstants.CAT_DET_DESC));
					turbineDetails.setCategoryCreatedDate(resultSetPackage.getDate(ItoConstants.CREAT_DT));
					turbineDetails.setCategoryModifiedDate(resultSetPackage.getDate(ItoConstants.MOD_DT));
					turbineDetails.setCategoryCreatedBy(resultSetPackage.getInt(ItoConstants.CREAT_BY));
					turbineDetails.setCategoryModifiedBy(resultSetPackage.getInt(ItoConstants.MOD_BY));
					turbineDetails
							.setIscategoryActive(resultSetPackage.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);
					return turbineDetails;
				}
			});

			if (!packageArray.isEmpty()) {
				packageList.put("packageList", packageArray);
				quotationForm.getDropDownColumnvalues().setPackageList(packageList);
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}
		return quotationForm;

	}

	public QuotationForm getCustomerData(QuotationForm quotationForm, ResultSet resultSetQuot) {

		try {
			while (resultSetQuot.next()) {
				quotationForm.getSaveBasicDetails().setQuotId(resultSetQuot.getInt(ItoConstants.QUOT_ID));
				quotationForm.getSaveBasicDetails().setCustId(resultSetQuot.getInt(ItoConstants.CUST_ID));
				quotationForm.getSaveBasicDetails()
						.setOpportunitySeqNum(resultSetQuot.getString(ItoConstants.OPP_SEQ_NO));
				quotationForm.getSaveBasicDetails().setCustName(resultSetQuot.getString(ItoConstants.CUST_NAME));
				quotationForm.getSaveBasicDetails().setOppName(resultSetQuot.getString(ItoConstants.OPP_NAME));
				quotationForm.getSaveBasicDetails().setOppContactName(resultSetQuot.getString("OPP_CONTACT_NAME"));
				quotationForm.getSaveBasicDetails().setOppContactEmail(resultSetQuot.getString("OPP_CONTACT_EMAIL"));
				quotationForm.getSaveBasicDetails().setOppContactPhone(resultSetQuot.getString("OPP_CONTACT_PHONE"));
				quotationForm.getSaveBasicDetails()
						.setOppContactAddress(resultSetQuot.getString("OPP_CONTACT_ADDRESS"));
				quotationForm.getSaveBasicDetails()
						.setOppContactStatName(resultSetQuot.getString("OPP_CONTACT_STATE_NAME"));
				quotationForm.getSaveBasicDetails().setCustCode(resultSetQuot.getString(ItoConstants.CUST_CODE));
				
				quotationForm.getSaveBasicDetails().setCustCodeNm(resultSetQuot.getString(ItoConstants.CUST_CODE_NM));
				quotationForm.getSaveBasicDetails().setCustType(resultSetQuot.getString(ItoConstants.CUST_TYPE));
				logger.info("Manjula cust type :"+(resultSetQuot.getString(ItoConstants.CUST_TYPE)));
				logger.info("Manjula cust code :"+(resultSetQuot.getString(ItoConstants.CUST_CODE)));
				logger.info("Manjula cust code name :"+(resultSetQuot.getString(ItoConstants.CUST_CODE_NM)));
				quotationForm.getSaveBasicDetails()
						.setCustContactPersonName(resultSetQuot.getString(ItoConstants.CUST_CONT_PERSON));
				quotationForm.getSaveBasicDetails()
						.setCustContactNumber(resultSetQuot.getString(ItoConstants.CUST_CONT_NO));
				quotationForm.getSaveBasicDetails().setCustEmailId(resultSetQuot.getString(ItoConstants.EMAIL));
				quotationForm.getSaveBasicDetails().setCustAddress(resultSetQuot.getString(ItoConstants.ADDRESS));
				quotationForm.getSaveBasicDetails().setCustPincode(resultSetQuot.getString(ItoConstants.PIN_CD));
				quotationForm.getSaveBasicDetails()
						.setConsultantName(resultSetQuot.getString(ItoConstants.CONST_FIRM_NAME));
				quotationForm.getSaveBasicDetails()
						.setConsultantSpocName(resultSetQuot.getString(ItoConstants.CONST_SPOC_NAME));
				quotationForm.getSaveBasicDetails()
						.setCustContactNumber(resultSetQuot.getString(ItoConstants.CONST_CONT_NO));
				quotationForm.getSaveBasicDetails()
						.setConsultantEmail(resultSetQuot.getString(ItoConstants.CONST_EMAIL));
				quotationForm.getSaveBasicDetails()
						.setConsultantAddress(resultSetQuot.getString(ItoConstants.CONST_ADDRESS));
				quotationForm.getSaveBasicDetails()
						.setIsEndUserAvailable(resultSetQuot.getString("IS_END_USER_AVAILABLE"));
				quotationForm.getSaveBasicDetails().setEndUserCustType(resultSetQuot.getString("END_USER_CUST_TYPE"));
				quotationForm.getSaveBasicDetails().setEndUserName(resultSetQuot.getString(ItoConstants.END_USER_NAME));
				quotationForm.getSaveBasicDetails()
						.setEndUserContactNo(resultSetQuot.getString(ItoConstants.END_USER_CONT_NO));
				quotationForm.getSaveBasicDetails()
						.setEndUserEmail(resultSetQuot.getString(ItoConstants.END_USER_EMAIL));
				quotationForm.getSaveBasicDetails()
						.setEndUserAddress(resultSetQuot.getString(ItoConstants.END_USER_ADDRESS));
				quotationForm.getSaveBasicDetails()
						.setEndUserStateNm(resultSetQuot.getString(ItoConstants.END_USER_STATE_NAME));
				quotationForm.getSaveBasicDetails().setRegionCode(resultSetQuot.getString("CUST_REGION_CD"));
				quotationForm.getSaveBasicDetails().setRegion(resultSetQuot.getString("CUST_REGION_NAME"));
				quotationForm.getSaveBasicDetails().setCustStatus(resultSetQuot.getString("CUST_STATUS"));
				quotationForm.getSaveBasicDetails().setSfdcLink(resultSetQuot.getString(ItoConstants.SFDC_LINK));
				quotationForm.getSaveBasicDetails().setFramePowerId(resultSetQuot.getInt(ItoConstants.FRM_POW_ID));
				quotationForm.getSaveBasicDetails().setFrameId(resultSetQuot.getInt(ItoConstants.FRM_ID));
				quotationForm.getSaveBasicDetails().setFrameName(resultSetQuot.getString(ItoConstants.FRAME_NAME));
				quotationForm.getSaveBasicDetails().setCapacity(resultSetQuot.getFloat(ItoConstants.CAPACITY));
				quotationForm.getSaveBasicDetails().setQuotNumber(resultSetQuot.getString(ItoConstants.QUOT_NUM));
				quotationForm.getSaveBasicDetails().setVariantCode(resultSetQuot.getString("VARIANT_CD"));
				quotationForm.getSaveBasicDetails().setStatusId(resultSetQuot.getInt(ItoConstants.STATUS_ID));
				quotationForm.getSaveBasicDetails().setStatusName(resultSetQuot.getString(ItoConstants.STATUS_NAME));
				quotationForm.getSaveBasicDetails().setStatusCode(resultSetQuot.getString("STATUS_CD"));
				quotationForm.getSaveBasicDetails().setcOldNumber(resultSetQuot.getString(ItoConstants.C_NUM_OLD));
				quotationForm.getSaveBasicDetails().setcNewNumber(resultSetQuot.getString(ItoConstants.C_NUM_NEW));
				quotationForm.getSaveBasicDetails().setRegionId(resultSetQuot.getInt(ItoConstants.REGION_ID));
				quotationForm.getSaveBasicDetails().setCondensingTypeId(resultSetQuot.getInt(ItoConstants.COND_TYP_ID));
				quotationForm.getSaveBasicDetails()
						.setCondensingTypeName(resultSetQuot.getString(ItoConstants.COND_TYP_NAME));
				//quotationForm.getSaveBasicDetails().setOrientationTypeId(resultSetQuot.getInt("ORNT_TYP_ID"));
				//quotationForm.getSaveBasicDetails().setOrientationType(resultSetQuot.getString("ORINTATION_TYPE"));
				quotationForm.getSaveBasicDetails().setIsNewProject(resultSetQuot.getInt("IS_NEW_PROJ"));
				quotationForm.getSaveBasicDetails().setPercentageVariation(resultSetQuot.getFloat("PER_VAR"));
				quotationForm.getSaveBasicDetails().setBleedTypeId(resultSetQuot.getInt(ItoConstants.BLEED_TYP_ID));
				quotationForm.getSaveBasicDetails().setBleedType(resultSetQuot.getString(ItoConstants.BLEED_TYP_NAME));
				quotationForm.getSaveBasicDetails().setCreatedDate(resultSetQuot.getString(ItoConstants.CREAT_DT));
				quotationForm.getSaveBasicDetails().setModifiedDate(resultSetQuot.getString(ItoConstants.MOD_DT));
				quotationForm.getSaveBasicDetails()
						.setAssignedToName(resultSetQuot.getString(ItoConstants.ASSIGNED_USER_NAME));
				quotationForm.getSaveBasicDetails().setAssignedEmpId(resultSetQuot.getString("ASSIGNED_USER_EMP_ID"));
				quotationForm.getSaveBasicDetails()
						.setAssignedPhoneNumber(resultSetQuot.getString("ASSIGNED_USER_PH_NUM"));
				quotationForm.getSaveBasicDetails().setAssignedEmail(resultSetQuot.getString("ASSIGNED_USER_EMAIL"));
				quotationForm.getSaveBasicDetails().setCreatedBy(resultSetQuot.getString("CREAT_BY_USER_NAME"));
				quotationForm.getSaveBasicDetails().setCreatorEmail(resultSetQuot.getString("CREAT_BY_EMAIL"));
				quotationForm.getSaveBasicDetails().setCreatorEmpId(resultSetQuot.getString("CREAT_BY_EMP_ID"));
				quotationForm.getSaveBasicDetails().setCreatorPhoneNumber(resultSetQuot.getString("CREAT_BY_PH_NUM"));
				quotationForm.getSaveBasicDetails().setCreatorDeptName(resultSetQuot.getString(ItoConstants.DEPT_NAME));
				quotationForm.getSaveBasicDetails().setDesignation(resultSetQuot.getString("DESIGNATION"));
				quotationForm.getSaveBasicDetails().setRevNum(resultSetQuot.getInt(ItoConstants.REV_NO));
				quotationForm.getSaveBasicDetails().setUserRoleId(resultSetQuot.getInt("ROLES_ID"));
				quotationForm.getSaveBasicDetails().setUserRoleName(resultSetQuot.getString("ROLE_NAME"));
				quotationForm.getSaveBasicDetails().setTypeOfOffer(resultSetQuot.getInt("TYP_OF_OFFER"));
				quotationForm.getSaveBasicDetails().setTypeOfQuot(resultSetQuot.getInt("TYP_OF_QUOTATION"));
				quotationForm.getSaveBasicDetails().setTypeOfOfferNm(resultSetQuot.getString("TYP_OF_OFFER_NM"));
				quotationForm.getSaveBasicDetails().setTypeOfQuotNm(resultSetQuot.getString("TYP_OF_QUOTATION_NM"));
				quotationForm.getSaveBasicDetails().setTypeOfVarient(resultSetQuot.getInt("VARIANT_TYP"));
				quotationForm.getSaveBasicDetails().setTypeOfVarientNm(resultSetQuot.getString("VARIANT_TYP_NM"));
				quotationForm.getSaveBasicDetails().setStateNm(resultSetQuot.getString("STATE_NM"));
				quotationForm.getSaveBasicDetails().setEnquiryReference(resultSetQuot.getString("ENQUIRY_REFERENCE"));
				quotationForm.getSaveBasicDetails().setImprovedImpulse(resultSetQuot.getInt("IMPROVED_IMPULSE"));
			}

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}
		return quotationForm;
	}

	private void createDboMechExcelJson(List<DBOBean> dataList, QuotationForm quotationForm) {
		Map<Integer, String> dropdownNameMap = new HashMap<>();
		for (DBOBean bean : dataList) {
			dropdownNameMap.put(bean.getColId(), bean.getColNm());
		}
		List<DBOBean> questionsBeanList = new ArrayList<>();
		for (Entry<Integer, String> dropDownType : dropdownNameMap.entrySet()) {
			DBOBean questionsBean = new DBOBean();
			questionsBean.getDropDownType().setKey(dropDownType.getKey());
			questionsBean.getDropDownType().setValue(dropDownType.getValue());
			quotationForm.getSavedDboList().add(questionsBean);
			questionsBean.setDropDownValueList(new ArrayList<SelectBox>());
			questionsBeanList.add(questionsBean);
		}
		quotationForm.setSavedDboList(questionsBeanList);
		for (DBOBean dropDownType : quotationForm.getSavedDboList()) {
			List<SelectBox> selectBoxList = new ArrayList<>();
			for (DBOBean bean : dataList) {
				if (dropDownType.getDropDownType().getKey() == bean.getColId()) {
					SelectBox box = new SelectBox();

					box.setCode(bean.getColValCd());
					box.setDefaultVal(true);
					// box.setDefaultVal(bean.isDefaultVal());
					box.setQuesKey(bean.getColId());
					box.setQuesDesc(bean.getColNm());
					selectBoxList.add(box);
				}
			}
		}
	}

	@Override
	public QuotationForm fetchUserData() {
		QuotationForm quotationForm = new QuotationForm();
		List<UserProfileDetails> userDetailsList = new ArrayList<>();
		try {

			String query = "SELECT B.USER_ID,EMP_ID,NAME,PH_NUM,EMAIL,B.DEPT_ID,(SELECT DEPT_NAME FROM DEPARTMENT A WHERE A.DEPT_ID = B.DEPT_ID  AND A.IS_ACTIVE = 1) AS DEPT_NAME,(SELECT PHOTO FROM PHOTO C WHERE C.USER_ID = B.USER_ID  AND C.IS_ACTIVE = 1 ) AS IMAGE,(SELECT D.NAME FROM REGION_MAST D WHERE D.REGION_ID = E.REGION_ID  AND D.IS_ACTIVE = 1 ) AS REGION,(SELECT G.ROLE_NAME FROM ROLES_MAST G WHERE G.ROLES_ID = F.ROLES_ID  AND G.IS_ACTIVE = 1) AS ROLES,(SELECT G.GROUP_CD FROM ROLES_MAST G WHERE G.ROLES_ID = F.ROLES_ID  AND G.IS_ACTIVE = 1) AS GROUP_CD,B.CREAT_DT,B.MOD_DT,B.CREAT_BY AS CREAT_BY_ID,B.MOD_BY AS MOD_BY_ID ,B.IS_ACTIVE,B.LAST_LOGIN_DT,E.REGION_ID,F.ROLES_ID,(SELECT NAME FROM USERS I WHERE I.USER_ID = B.CREAT_BY ) AS CREAT_BY,(SELECT NAME FROM USERS J WHERE J.USER_ID = B.MOD_BY ) AS MOD_BY FROM USERS B LEFT JOIN REGION E ON  E.USER_ID = B.USER_ID LEFT JOIN ROLES F ON  F.USER_ID = B.USER_ID WHERE  B.IS_ACTIVE = 1 ORDER BY NAME;";

			userDetailsList = jdbcTemplate.query(query, new RowMapper<UserProfileDetails>() {

				public UserProfileDetails mapRow(ResultSet resultSetUserInfo, int rowNum) throws SQLException {
					UserProfileDetails userDetails = new UserProfileDetails();

					userDetails.setEmpId(resultSetUserInfo.getInt("EMP_ID"));
					userDetails.setUserId(resultSetUserInfo.getInt("USER_ID"));
					userDetails.setEmpName(resultSetUserInfo.getString("NAME"));
					userDetails.setContactNumber(resultSetUserInfo.getString("PH_NUM"));
					userDetails.setEmailId(resultSetUserInfo.getString(ItoConstants.EMAIL));
					userDetails.setGroupId(resultSetUserInfo.getInt("DEPT_ID"));
					userDetails.setGroup(resultSetUserInfo.getString(ItoConstants.DEPT_NAME));
					userDetails.setRegionId(resultSetUserInfo.getInt(ItoConstants.REGION_ID));
					userDetails.setRegion(resultSetUserInfo.getString(ItoConstants.REGION));
					userDetails.setRoleId(resultSetUserInfo.getInt("ROLES_ID"));
					userDetails.setRole(resultSetUserInfo.getString("ROLES"));
					userDetails.setGroupCd(resultSetUserInfo.getString(ItoConstants.GROUP_CD));
					userDetails.setLastLoggedInDate(resultSetUserInfo.getDate("LAST_LOGIN_DT"));
					userDetails.setCreatedById(resultSetUserInfo.getInt(ItoConstants.CREAT_BY_ID));
					userDetails.setCreatedBy(resultSetUserInfo.getString(ItoConstants.CREAT_BY));
					userDetails.setModifiedById(resultSetUserInfo.getInt(ItoConstants.MOD_BY_ID));
					userDetails.setModifiedBy(resultSetUserInfo.getString(ItoConstants.MOD_BY));
					userDetails.setCreatedDate(resultSetUserInfo.getString(ItoConstants.CREAT_DT));
					userDetails.setModifiedDate(resultSetUserInfo.getString(ItoConstants.MOD_DT));
					if (resultSetUserInfo.getBytes("IMAGE") != null) {
						// setting Image

						String encoded = Base64.getEncoder().encodeToString(resultSetUserInfo.getBytes("IMAGE"));
						// convert array of bytes back to base 64 format to
						// display in UI
						// setting encoded base 64 format to userdetails bean
						userDetails.setImage(encoded);
					}
					userDetails.setActive(resultSetUserInfo.getInt(ItoConstants.IS_ACTIVE) == 1 ? true : false);
					return userDetails;
				}
			});
			quotationForm = getUserData(quotationForm, userDetailsList);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}

		return quotationForm;

	}

	@Override
	public QuotationForm getScopeOfSupStatus(QuotationForm quotationForm) {
		List<ScopeOfSupply> sosList = new ArrayList<>();
		int quotId = quotationForm.getSaveBasicDetails().getQuotId();
		try {

			String query = "SELECT SCOP_ID,QUOT_ID,SS_ID,(SELECT CAT_DET_DESC FROM CATEGORY_DET WHERE CAT_ID = (SELECT CAT_ID FROM CATEGORY_MAST WHERE GRP_CD = 'TB' AND CAT_CD = 'SS' ) AND IS_ACTIVE = 1 AND CAT_DET_ID = SS_ID) AS SCOPE_OF_SUPPLY,(SELECT NAME FROM TURB_STATUS WHERE ID = A.STATUS_ID) AS STATUS,A.CREAT_DT,A.MOD_DT,(SELECT NAME FROM USERS B WHERE B.USER_ID = A.CREAT_BY ) AS CREAT_BY,(SELECT NAME FROM USERS B WHERE B.USER_ID = A.MOD_BY ) AS MOD_BY,A.CREAT_BY AS CREAT_BY_ID,A.MOD_BY  AS MOD_BY_ID FROM TURB_CONFIG_SCOPE A WHERE QUOT_ID = '"
					+ quotId + "'";

			sosList = jdbcTemplate.query(query, new RowMapper<ScopeOfSupply>() {

				public ScopeOfSupply mapRow(ResultSet resultsetScope, int rowNum) throws SQLException {
					ScopeOfSupply supply = new ScopeOfSupply();
					supply.setScopeId(resultsetScope.getInt("SCOP_ID"));
					supply.setQuotID(resultsetScope.getInt(ItoConstants.QUOT_ID));
					supply.setSsId(resultsetScope.getInt(ItoConstants.SS_ID));
					supply.setScopeName(resultsetScope.getString(ItoConstants.SCOPE_OF_SUPPLY));
					supply.setStatus(resultsetScope.getString(ItoConstants.STATUS));
					supply.setDefaultValue(true);
					return supply;
				}
			});

			quotationForm.setScopeOfSupplyStatusList(sosList);
		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}

		return quotationForm;
	}

	public static String fmt(String s) {

		String formatted = "";
		if (s.length() > 1) {
			formatted = s.substring(0, 1);
			s = s.substring(1);
		}

		while (s.length() > 3) {
			formatted += "," + s.substring(0, 2);
			s = s.substring(2);
		}
		return formatted + "," + s + ".00";
	}

	@Override
	public ReportBean getReportData(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;
		ResultSet resultSetAllData = null;

		List<ReportBean> reportData = new ArrayList<>();
		DecimalFormat formatNum = new DecimalFormat("#,###");

		// Format format = java.text.NumberFormat.getCurrencyInstance(new
		// Locale("en", "in"));

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_REPORT_COST_SHEET (?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}
			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();

				while (resultSetData.next()) {
					reportBean.setCustName(resultSetData.getString("CUST_NAME"));
					reportBean.setOppName(resultSetData.getString("OPP_NAME"));
					reportBean.setEndUserName(resultSetData.getString("END_USER_NAME"));
					reportBean.setCustContactPersonName(resultSetData.getString("CUST_CONT_PERSON"));
					reportBean.setType(resultSetData.getString("TYPE"));
					reportBean.setFrameName(resultSetData.getString("FRAME_NAME"));
					reportBean.setTurbGeneratorMw(resultSetData.getString("TURB_GENERATOR_MW"));
					reportBean.setOrderDate(resultSetData.getString("Order date / LOI date"));
					reportBean.setDelivery(resultSetData.getString("Delievery / Commissioing"));
					reportBean.setQuotNum(resultSetData.getString("QUOT_NUM"));
					reportBean.setQuotStatus(resultSetData.getString("STATUS"));
					reportBean.setRevNum(resultSetData.getString("REV_NO"));
					reportBean.setDate(resultSetData.getString("DATE"));
					reportBean.setOpportunitySeqNum(resultSetData.getString("OPP_SEQ_NO"));
				}

			}
			if (callableStatement.getMoreResults()) {
				resultSetAllData = callableStatement.getResultSet();

				while (resultSetAllData.next()) {
					ReportBean bean = new ReportBean();
					bean.setScopeCode(resultSetAllData.getString("SCOPE_CD"));
					bean.setItemName(resultSetAllData.getString("ITEM_NM"));
					bean.setQuantity(resultSetAllData.getInt("QTY"));
			
					
					bean.setCost(resultSetAllData.getString("COST"));
					logger.info(bean.getCost());
//					String word = bean.getCost();
//					if (word.contains(".")) {
//						String result = word.replace(".", "");
//						String result1 = result.trim();
//						String result2 = result1.replaceAll(".$", "");
//						logger.info(result2);
//					}
//					logger.info(bean.getCost().split(".")[0]);
					bean.setOverwrittenPriceFlag(resultSetAllData.getInt("COST_ME_FLG") == 1 ? true : false);
					bean.setRemarks(resultSetAllData.getString("REMARKS"));
					reportData.add(bean);
				}

			}
			if (!reportData.isEmpty()) {
				reportBean.setReportData(reportData);
			}

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData);
		}
		return reportBean;
	}
	
	

	// add on report data
	@Override
	public ReportBean getAddOnReportData(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData1 = null;
		ResultSet resultSetData2 = null;

		List<ReportBean> addOnReportData = new ArrayList<>();
		DecimalFormat formatNum = new DecimalFormat("#,###");
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			callableStatement = connection.prepareCall("{ call dbo.PROC_REPORT_COST_SHEET_ADD_ON (?) }");
			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();

			resultSetMsg = callableStatement.getResultSet();
			logger.info(quotationForm.getSaveBasicDetails().getQuotId());
			while (resultSetMsg.next()) {
				logger.info("Level1");
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}
			if (callableStatement.getMoreResults()) {
				resultSetData1 = callableStatement.getResultSet();
				logger.info("Level2");
				while (resultSetData1.next()) {
					logger.info("Level3");
					reportBean.setCustName(resultSetData1.getString("CUST_NAME"));
					reportBean.setOppName(resultSetData1.getString("OPP_NAME"));
					reportBean.setEndUserName(resultSetData1.getString("END_USER_NAME"));
					reportBean.setCustContactPersonName(resultSetData1.getString("CUST_CONT_PERSON"));
					reportBean.setType(resultSetData1.getString("TYPE"));
					reportBean.setFrameName(resultSetData1.getString("FRAME_NAME"));
					reportBean.setTurbGeneratorMw(resultSetData1.getString("TURB_GENERATOR_MW"));
					reportBean.setOrderDate(resultSetData1.getString("Order date / LOI date"));
					reportBean.setDelivery(resultSetData1.getString("Delievery / Commissioing"));
					reportBean.setQuotNum(resultSetData1.getString("QUOT_NUM"));
					reportBean.setQuotStatus(resultSetData1.getString("STATUS"));
					reportBean.setRevNum(resultSetData1.getString("REV_NO"));
					reportBean.setDate(resultSetData1.getString("DATE"));
					reportBean.setOpportunitySeqNum(resultSetData1.getString("OPP_SEQ_NO"));
				}

			}
			if (callableStatement.getMoreResults()) {
				resultSetData2 = callableStatement.getResultSet();
				logger.info("Level4");
				while (resultSetData2.next()) {
					ReportBean bean = new ReportBean();
					bean.setSlNo(resultSetData2.getString("SL_NO"));
					bean.setScopeCode(resultSetData2.getString("SCOPE_CD"));
					bean.setSubGrpCd(resultSetData2.getString("SUB_GRP_CD"));
					bean.setItemId(resultSetData2.getInt("ITEM_ID"));
					bean.setSubItemId(resultSetData2.getInt("SUB_ITEM_ID"));
					bean.setSubItemTypeId(resultSetData2.getInt("SUB_ITEM_TYP_ID"));

					bean.setItemDesc(resultSetData2.getString("ITEM_DESC"));
					logger.info(bean.getItemDesc());
					bean.setColId(resultSetData2.getInt("COL_ID"));
					bean.setColNm(resultSetData2.getString("COL_NM"));
					bean.setColValCd(resultSetData2.getString("COL_VAL_CD"));
					
					bean.setRhsComrComments(resultSetData2.getString("RHS_COMR_COMMENTS"));
					bean.setComrRemarks(resultSetData2.getString("COMR_REMARKS"));
					bean.setQuantity(resultSetData2.getInt("QTY"));
					bean.setRhsCostNew(resultSetData2.getString("RHS_COST"));
					bean.setItemCostNew(resultSetData2.getString("ITEM_COST"));
					
					bean.setBasicCostNew(resultSetData2.getString("BASIC_COST"));
					bean.setAddOnCostNew(resultSetData2.getString("ADD_ON_COST"));
					
					bean.setDiscountPer(formatNum.format(Math.round(resultSetData2.getFloat("DISCOUNT_PER"))));
					bean.setItemOverwrittenPriceFlag(resultSetData2.getInt("ITEM_COST_ME_FLG") == 1 ? true : false);
					bean.setAddonOverwrittenPriceFlag(resultSetData2.getInt("ADD_ON_COST_ME_FLG") == 1 ? true : false);
					addOnReportData.add(bean);
				}

			}
			if (!addOnReportData.isEmpty()) {
				logger.info("addoncostsheet");
				for(int i=0; i<addOnReportData.size(); i++){
					logger.info(addOnReportData.get(i));
				}
				reportBean.setAddOnReportData(addOnReportData);
			}
			else{
				logger.info("empty addoncostsheet");
			}

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData2);
		}
		return reportBean;
	}
	@Override
	public ReportBean getWordDataRev(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetWord1 = null;
		ResultSet resultSetWord2 = null;
		ResultSet resultSetWord3 = null;
		ResultSet resultSetWord4 = null;
		ResultSet resultSetWord5 = null;
		ResultSet resultSetWord6 = null;
		ResultSet resultSetWord7 = null;
		ResultSet resultSetWordCi = null;
		ResultSet resultSetWord8 = null;
		ResultSet resultSetWord81 = null;
		ResultSet resultSetWord9 = null;
		ResultSet resultSetWord10 = null;
		ResultSet resultSetWord11 = null;
		ResultSet resultSetWord12 = null;
		ResultSet resultSetWord13 = null;
		ResultSet resultSetWord14 = null;
		ResultSet resultSetWord15 = null;
		ResultSet resultSetWord16 = null;
		ResultSet resultSetWord17 = null;
		ResultSet resultSetWord18 = null;
		ResultSet resultSetWord19 = null;
		ResultSet resultSetWord20 = null;
		ResultSet resultSetWord21 = null;
		ResultSet resultSetWord202 = null;
		List<ReportBean> wordData = new ArrayList<>();
		List<ReportBean> wordDataAux = new ArrayList<>();
		List<String> categoryname = new ArrayList<>();
		List<String> subcategoryname = new ArrayList<>();

		DecimalFormat formatNum = new DecimalFormat("#,###");
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RPT_TECH_OFFER_REV (?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.REV_NO, quotationForm.getSaveBasicDetails().getRevNum());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}
			
			
			// cover page
						if (callableStatement.getMoreResults()) {
							resultSetWord1 = callableStatement.getResultSet();

							while (resultSetWord1.next()) {

								reportBean.setOpportunitySeqNum(resultSetWord1.getString("SFDCNUM"));
								reportBean.setDate(resultSetWord1.getString("DATE"));
								reportBean.setAccountName(resultSetWord1.getString("ACCOUNT_NAME"));
								reportBean.setOppName(resultSetWord1.getString("OPP_NAME"));
								reportBean.setEndUserName(resultSetWord1.getString("END_USER_NAME"));
								reportBean.setDeptName(resultSetWord1.getString("DEPT_NAME"));
								reportBean.setDesignation(resultSetWord1.getString("DESIGNATION"));
								reportBean.setUserName(resultSetWord1.getString("USER_NAME"));
								reportBean.setEnquiryReference(resultSetWord1.getString("ENQUIRY_REFERENCE"));
								reportBean.setCustContactPersonName(resultSetWord1.getString(ItoConstants.CUST_CONT_PERSON));
								reportBean.setOppContactAddress(resultSetWord1.getString("OPP_CONTACT_ADDRESS"));
								reportBean.setSubject(resultSetWord1.getString("SUBJECT"));
								reportBean.setTurbineCode(resultSetWord1.getString("TURB_CD"));
								reportBean.setTypeOfOff(resultSetWord1.getString("TYP_OF_OFFER"));
								reportBean.setCustSpace(resultSetWord1.getString("CUST_SPACE"));
							}

						}
						// index

						if (callableStatement.getMoreResults()) {
							resultSetWord2 = callableStatement.getResultSet();

							while (resultSetWord2.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWord2.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord2.getString("SUB_CAT_NM"));
								wordData.add(reportbean);

							}

						}
						size = wordData.size();// 12
						// scope of supply chapter
						if (callableStatement.getMoreResults()) {
							resultSetWord3 = callableStatement.getResultSet();

							while (resultSetWord3.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWord3.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord3.getString("SUB_CAT_NM"));
								reportbean.setItemName(resultSetWord3.getString("ITEM_NM"));
								reportbean.setSubCatName(resultSetWord3.getString("SUBCAT_NM"));
								wordData.add(reportbean);

							}

						}

						size1 = wordData.size();// 14

						// technicaldetails chapter(1-8) f2f mech
						if (callableStatement.getMoreResults()) {
							resultSetWord4 = callableStatement.getResultSet();

							while (resultSetWord4.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWord4.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord4.getString("SUB_CAT_NM"));
								reportbean.setItemName(resultSetWord4.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWord4.getString("ITEM_CD"));
								reportbean.setSubItemName(resultSetWord4.getString("SUB_ITEM_NM"));
								reportbean.setSubItemTypeName(resultSetWord4.getString("SUB_ITEM_TYP_NM"));
								reportbean.setColNm(resultSetWord4.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord4.getString("COL_VAL_CD"));
								reportbean.setTechRemarks(resultSetWord4.getString("TECH_REMARKS"));
								reportbean.setTechComments(resultSetWord4.getString("TECH_COMMENTS"));
								reportbean.setVarientTypeName(resultSetWord4.getString("VARIANT_TYP_NM"));
								reportbean.setCategory(resultSetWord4.getString("CATEGORY"));
								reportbean.setFixedText1(resultSetWord4.getString("FIXED_TEXT1"));
								reportbean.setFixedText2(resultSetWord4.getString("FIXED_TEXT2"));
								reportbean.setFixedText3(resultSetWord4.getString("FIXED_TEXT3"));
								reportbean.setFixedText4(resultSetWord4.getString("FIXED_TEXT4"));
								reportbean.setFixedText5(resultSetWord4.getString("FIXED_TEXT5"));
								// reportbean.setFixedText6(resultSetWord4.getString("FIXED_TEXT6"));
								reportbean.setNumberOfRows(resultSetWord4.getInt("NO_OF_ROWS"));
								reportbean.setSpaceF2f(resultSetWord4.getString("SPACE_F2F"));
								reportbean.setQuantity(resultSetWord4.getInt("QTY"));

								wordData.add(reportbean);

							}
						}
						size2 = wordData.size();// 14

						// technicaldetails chapter(9) mech aux
						if (callableStatement.getMoreResults()) {
							resultSetWord5 = callableStatement.getResultSet();

							while (resultSetWord5.next()) {
								ReportBean reportbean = new ReportBean();

								reportbean.setCategoryName(resultSetWord5.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord5.getString("SUB_CAT_NM"));
								reportbean.setItemId(resultSetWord5.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord5.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWord5.getString("ITEM_CD"));
								reportbean.setSubItemCode(resultSetWord5.getString("SUB_ITEM_CD"));
								reportbean.setSubItemName(resultSetWord5.getString("SUB_ITEM_NM"));
								reportbean.setColNm(resultSetWord5.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord5.getString("COL_VAL_CD"));
								reportbean.setQuantityAux(resultSetWord5.getInt("QTY"));
								reportbean.setTechRemarks(resultSetWord5.getString("TECH_REMARKS"));
								reportbean.setTechComments(resultSetWord5.getString("TECH_COMMENTS"));
								reportbean.setNumberOfSubItems(resultSetWord5.getInt("NO_OF_SUB_ITEMS"));
								reportbean.setSubItemId(resultSetWord5.getInt("SUB_ITEM_ID"));
								reportbean.setAuxSpace(resultSetWord5.getString("AUX_SPACE"));
								reportbean.setAuxNewline(resultSetWord5.getString("AUX_NEWLINE"));
								reportbean.setDoubleQuot(resultSetWord5.getString("DOUBLE_QUOTE"));
								reportbean.setItemQuantity(resultSetWord5.getInt("ITEM_QTY"));
								

								wordData.add(reportbean);

							}
						}
						size3 = wordData.size();// 14

						// technicaldetails chapter(10) mech ext
						if (callableStatement.getMoreResults()) {
							resultSetWord6 = callableStatement.getResultSet();

							while (resultSetWord6.next()) {
								ReportBean reportbean = new ReportBean();

								reportbean.setCategoryName(resultSetWord6.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord6.getString("SUB_CAT_NM"));
								reportbean.setItemId(resultSetWord6.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord6.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWord6.getString("ITEM_CD"));
								reportbean.setSubItemCode(resultSetWord6.getString("SUB_ITEM_CD"));
								reportbean.setSubItemName(resultSetWord6.getString("SUB_ITEM_NM"));
								reportbean.setColNm(resultSetWord6.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord6.getString("COL_VAL_CD"));
								reportBean.setQuantityAux(resultSetWord6.getInt("QTY"));
								reportbean.setTechRemarks(resultSetWord6.getString("TECH_REMARKS"));
								reportbean.setTechComments(resultSetWord6.getString("TECH_COMMENTS"));
								reportbean.setNumberOfSubItems(resultSetWord6.getInt("NO_OF_SUB_ITEMS"));

								wordData.add(reportbean);

							}
						}
						size4 = wordData.size();// 14

						// technicaldetails electrical
						if (callableStatement.getMoreResults()) {
							resultSetWord7 = callableStatement.getResultSet();

							while (resultSetWord7.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWord7.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord7.getString("SUB_CAT_NM"));
								reportbean.setItemId(resultSetWord7.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord7.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWord7.getString("ITEM_CD"));
								reportbean.setSubItemCode(resultSetWord7.getString("SUB_ITEM_CD"));
								reportbean.setSubItemName(resultSetWord7.getString("SUB_ITEM_NM"));
								reportbean.setDesItemName(resultSetWord7.getString("DES_ITEM_NM"));
								reportbean.setDesSubItemName(resultSetWord7.getString("DES_SUB_ITEM_NM"));
								reportbean.setColNm(resultSetWord7.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord7.getString("COL_VAL_CD"));
								reportbean.setQuantityEle(resultSetWord7.getInt("QTY"));
								reportbean.setTechRemarks(resultSetWord7.getString("TECH_REMARKS"));
								// reportbean.setTechComments(resultSetWord7.getString("TECH_COMMENTS"));
								reportbean.setNumberOfSubItems(resultSetWord7.getInt("NO_OF_SUB_ITEMS"));
								reportbean.setOrderId(resultSetWord7.getInt("ORDER_ID"));
								reportbean.setDesSubItemOrderId(resultSetWord7.getInt("DES_SUB_ITEM_ORDER_ID"));
								reportbean.setDesColOrderId(resultSetWord7.getString("TECH_DES_COL_ORDER_ID"));
								reportbean.setSubColValCode(resultSetWord7.getString("SUB_COL_VAL_CD"));
								reportbean.setHeaderText(resultSetWord7.getString("HEADER_TEXT"));
								reportbean.setFooterText(resultSetWord7.getString("FOOTER_TEXT"));
								reportbean.setLinkFlag(resultSetWord7.getInt("LINK_FLG"));
								reportbean.setDesItemOrderId(resultSetWord7.getInt("DES_ITEM_ORDER_ID"));
								reportbean.setAddOnNewColFlag(resultSetWord7.getInt("ADD_ON_NEW_COL_FLG"));
								reportbean.setItemQuantity(resultSetWord7.getInt("ITEM_QTY"));
								reportbean.setSpaceF2f(resultSetWord7.getString("SPACE"));

								wordData.add(reportbean);

							}
						}
						size5 = wordData.size();// 14

						// ele aux
						// ele aux
						if (callableStatement.getMoreResults()) {
							resultSetWord10 = callableStatement.getResultSet();

							while (resultSetWord10.next()) {

								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWord10.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord10.getString("SUB_CAT_NM"));
								reportbean.setItemId(resultSetWord10.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord10.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWord10.getString("ITEM_CD"));
								reportbean.setSubItemId(resultSetWord10.getInt("SUB_ITEM_ID"));
								reportbean.setSubItemCode(resultSetWord10.getString("SUB_ITEM_CD"));
								reportbean.setSubItemName(resultSetWord10.getString("SUB_ITEM_NM"));
								reportbean.setSubItemTypeId(resultSetWord10.getInt("SUB_ITEM_TYP_ID"));
								reportbean.setSubItemTypeName(resultSetWord10.getString("SUB_ITEM_TYP_NM"));

								reportbean.setDesItemName(resultSetWord10.getString("DES_ITEM_NM"));
								reportbean.setDesSubItemName(resultSetWord10.getString("DES_SUB_ITEM_NM"));
								reportbean.setColNm(resultSetWord10.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord10.getString("COL_VAL_CD"));
								reportbean.setQuantityEle(resultSetWord10.getInt("QTY"));
								reportbean.setTechRemarks(resultSetWord10.getString("TECH_REMARKS"));
								// reportbean.setTechComments(resultSetWord10.getString("TECH_COMMENTS"));
								reportbean.setNumberOfSubItems(resultSetWord10.getInt("NO_OF_SUB_ITEMS"));
								reportbean.setOrderId(resultSetWord10.getInt("ORDER_ID"));
								reportbean.setDesSubItemOrderId(resultSetWord10.getInt("DES_SUB_ITEM_ORDER_ID"));
								reportbean.setDesColOrderId(resultSetWord10.getString("TECH_DES_COL_ORDER_ID"));
								reportbean.setSubColValCode(resultSetWord10.getString("SUB_COL_VAL_CD"));
								reportbean.setHeaderText(resultSetWord10.getString("HEADER_TEXT"));
								reportbean.setFooterText(resultSetWord10.getString("FOOTER_TEXT"));
								reportbean.setLinkFlag(resultSetWord10.getInt("LINK_FLG"));
								reportbean.setDesItemOrderId(resultSetWord10.getInt("DES_ITEM_ORDER_ID"));
								reportbean.setRhsColTechcomments(resultSetWord10.getString("RHS_COL_TECH_COMMENTS"));
								reportbean.setAddOnNewColFlag(resultSetWord10.getInt("ADD_ON_NEW_COL_FLG"));
								reportbean.setItemQuantity(resultSetWord10.getInt("ITEM_QTY"));
								reportbean.setSpaceF2f(resultSetWord10.getString("SPACE"));
								wordData.add(reportbean);

							}
						}
						size8 = wordData.size();// 14

						// ele ext
						if (callableStatement.getMoreResults()) {
							resultSetWord11 = callableStatement.getResultSet();

							while (resultSetWord11.next()) {
								ReportBean reportbean = new ReportBean();

								reportbean.setCategoryName(resultSetWord11.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord11.getString("SUB_CAT_NM"));
								// reportbean.setItemId(resultSetWord11.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord11.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWord11.getString("ITEM_CD"));
								// reportbean.setSubItemCode(resultSetWord11.getString("SUB_ITEM_CD"));
								reportbean.setSubItemName(resultSetWord11.getString("SUB_ITEM_NM"));
								reportbean.setColNm(resultSetWord11.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord11.getString("COL_VAL_CD"));
								reportbean.setQuantityAux(resultSetWord11.getInt("QTY"));
								reportbean.setTechRemarks(resultSetWord11.getString("TECH_REMARKS"));
								// reportbean.setTechComments(resultSetWord11.getString("TECH_COMMENTS"));
								// reportbean.setNumberOfSubItems(resultSetWord11.getInt("NO_OF_SUB_ITEMS"));

								wordData.add(reportbean);

							}
						}
						size9 = wordData.size();// 14

						// ci result set

						if (callableStatement.getMoreResults()) {
							resultSetWordCi = callableStatement.getResultSet();

							while (resultSetWordCi.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWordCi.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWordCi.getString("SUB_CAT_NM"));
								reportbean.setItemId(resultSetWordCi.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWordCi.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWordCi.getString("ITEM_CD"));
								reportbean.setSubItemCode(resultSetWordCi.getString("SUB_ITEM_CD"));
								reportbean.setSubItemName(resultSetWordCi.getString("SUB_ITEM_NM"));
								reportbean.setDesItemName(resultSetWordCi.getString("DES_ITEM_NM"));
								reportbean.setDesSubItemName(resultSetWordCi.getString("DES_SUB_ITEM_NM"));
								reportbean.setColNm(resultSetWordCi.getString("COL_NM"));
								reportbean.setColValCd(resultSetWordCi.getString("COL_VAL_CD"));
								reportbean.setQuantityEle(resultSetWordCi.getInt("QTY"));
								reportbean.setTechRemarks(resultSetWordCi.getString("TECH_REMARKS"));
								// reportbean.setTechComments(resultSetWord7.getString("TECH_COMMENTS"));
								reportbean.setNumberOfSubItems(resultSetWordCi.getInt("NO_OF_SUB_ITEMS"));
								reportbean.setOrderId(resultSetWordCi.getInt("ORDER_ID"));
								reportbean.setDesSubItemOrderId(resultSetWordCi.getInt("DES_SUB_ITEM_ORDER_ID"));
								reportbean.setDesColOrderId(resultSetWordCi.getString("TECH_DES_COL_ORDER_ID"));
								reportbean.setSubColValCode(resultSetWordCi.getString("SUB_COL_VAL_CD"));
								reportbean.setHeaderText(resultSetWordCi.getString("HEADER_TEXT"));
								reportbean.setFooterText(resultSetWordCi.getString("FOOTER_TEXT"));
								reportbean.setLinkFlag(resultSetWordCi.getInt("LINK_FLG"));
								reportbean.setDesItemOrderId(resultSetWordCi.getInt("DES_ITEM_ORDER_ID"));
								reportbean.setRhsColTechcomments(resultSetWordCi.getString("RHS_COL_TECH_COMMENTS"));
								reportbean.setAddOnNewColFlag(resultSetWordCi.getInt("ADD_ON_NEW_COL_FLG"));
								reportbean.setItemQuantity(resultSetWordCi.getInt("ITEM_QTY"));
								reportbean.setSpaceF2f(resultSetWordCi.getString("SPACE"));
								wordData.add(reportbean);

							}
						}
						sizeCi = wordData.size();// 14

						// vms
						if (callableStatement.getMoreResults()) {
							resultSetWord9 = callableStatement.getResultSet();

							while (resultSetWord9.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setItemId(resultSetWord9.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord9.getString("ITEM_NM"));
								reportbean.setType(resultSetWord9.getString("TYPE"));
								reportbean.setAddPrbFlag(resultSetWord9.getInt("ADD_PRB_FLG"));
								reportbean.setOrderBy(resultSetWord9.getInt("ORDER_BY"));
								reportbean.setTagNum(resultSetWord9.getString("TAG_NO"));
								reportbean.setEquipment(resultSetWord9.getString("EQUIPMENT"));
								reportbean.setApplication(resultSetWord9.getString("APPLICATION"));
								reportbean.setLocation(resultSetWord9.getString("LOCATION"));
								reportbean.setQuantity(resultSetWord9.getInt("QTY"));
								reportbean.setCost2(Math.round(resultSetWord9.getFloat("COST")));
								reportbean.setNewColValFlag(resultSetWord9.getInt("NEW_COL_VAL_FLG"));
								reportbean.setItemCost(Math.round(resultSetWord9.getFloat("ITEM_COST")));
								reportbean.setHeaderText(resultSetWord9.getString("HEADER_TEXT"));
								reportbean.setNote(resultSetWord9.getString("NOTE"));
								wordData.add(reportbean);

							}
						}
						size7 = wordData.size();// 14

						// stg
						if (callableStatement.getMoreResults()) {
							resultSetWord8 = callableStatement.getResultSet();

							while (resultSetWord8.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWord8.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord8.getString("SUB_CAT_NM"));
								reportbean.setItemNm(resultSetWord8.getString("ITEM_NM"));
								reportbean.setItemId(resultSetWord8.getInt("ITEM_ID"));
								reportbean.setId(resultSetWord8.getInt("ID"));

								reportbean.setInstrCode(resultSetWord8.getString("INSTR_CD"));
								reportbean.setInstrNm(resultSetWord8.getString("INSTR_NM"));
								reportbean.setInstrTypeNm(resultSetWord8.getString("INSTR_TYPE_NM"));
								reportbean.setInstrDesc(resultSetWord8.getString("INSTR_DESC"));
								reportbean.setInstrMounting(resultSetWord8.getString("INSTR_MOUNTING"));
								reportbean.setQuantityLogic(resultSetWord8.getInt("QTY_LOGIC"));
								reportbean.setCost1(resultSetWord8.getFloat("COST"));
								reportbean.setNoOfTable(resultSetWord8.getInt("NO_OF_TABLE"));
								reportbean.setHeaderText(resultSetWord8.getString("HEADER_TEXT"));
								reportbean.setFooterText(resultSetWord8.getString("FOOTER_TEXT"));
								reportbean.setItemId(resultSetWord8.getInt("ITEM_ID"));
								reportbean.setAddInstrId(resultSetWord8.getInt("ADD_INSTR_ID"));
								reportbean.setAddInstrCd(resultSetWord8.getString("ADD_INSTR_CD"));
								reportbean.setAddInstrNm(resultSetWord8.getString("ADD_INSTR_NM"));
								wordData.add(reportbean);

							}
						}
						size6 = wordData.size();// 14

						// save addinst

						if (callableStatement.getMoreResults()) {
							resultSetWord81 = callableStatement.getResultSet();

							while (resultSetWord81.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setDetQuotId(resultSetWord81.getInt("DET_QUOT_ID"));
								reportbean.setItemId(resultSetWord81.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord81.getString("ITEM_NM"));
								reportbean.setQuantity(resultSetWord81.getInt("QTY"));
								reportbean.setColId(resultSetWord81.getInt("COL_ID"));
								reportbean.setColNm(resultSetWord81.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord81.getString("COL_VAL_CD"));
								reportbean.setSubColValCode(resultSetWord81.getString("SUB_COL_VAL_CD"));
								reportbean.setAddOnNewColFlag(resultSetWord81.getInt("ADD_ON_NEW_COL_FLG"));
								reportbean.setRhsFlag(resultSetWord81.getInt("RHS_FLG") == 1 ? true : false);
								reportbean.setRhsColQuantity(resultSetWord81.getFloat("RHS_COL_QTY"));
								reportbean.setDesSubItemOrderId(resultSetWord81.getInt("DES_SUB_ITEM_ORDER_ID"));
								reportbean.setRhsCost(Math.round(resultSetWord81.getFloat("RHS_COST")));
								reportbean.setAddInstrCost(Math.round(resultSetWord81.getFloat("ADD_INSTR_COST")));
								reportbean.setTotalAddInstrCost(Math.round(resultSetWord81.getFloat("TOTAL_ADD_INSTR_COST")));
								reportbean.setItemFlag(resultSetWord81.getInt("ITEM_FLG") == 1 ? true : false);
								reportbean.setOthersFlag(resultSetWord81.getInt("OTHERS_FLG") == 1 ? true : false);
								reportbean.setAddInstrFlag(resultSetWord81.getInt("ADD_INSTR_FLG") == 1 ? true : false);
								wordData.add(reportbean);

							}
						}
						size12 = wordData.size();// 14

						// ci aux
						if (callableStatement.getMoreResults()) {
							resultSetWord12 = callableStatement.getResultSet();

							while (resultSetWord12.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWord12.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord12.getString("SUB_CAT_NM"));
								reportbean.setItemId(resultSetWord12.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord12.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWord12.getString("ITEM_CD"));
								reportbean.setSubItemCode(resultSetWord12.getString("SUB_ITEM_CD"));
								reportbean.setSubItemName(resultSetWord12.getString("SUB_ITEM_NM"));
								reportbean.setDesItemName(resultSetWord12.getString("DES_ITEM_NM"));
								reportbean.setDesSubItemName(resultSetWord12.getString("DES_SUB_ITEM_NM"));
								reportbean.setColNm(resultSetWord12.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord12.getString("COL_VAL_CD"));
								reportbean.setQuantityEle(resultSetWord12.getInt("QTY"));
								reportbean.setTechRemarks(resultSetWord12.getString("TECH_REMARKS"));
								// reportbean.setTechComments(resultSetWord12.getString("TECH_COMMENTS"));
								reportbean.setNumberOfSubItems(resultSetWord12.getInt("NO_OF_SUB_ITEMS"));
								reportbean.setOrderId(resultSetWord12.getInt("ORDER_ID"));
								reportbean.setDesSubItemOrderId(resultSetWord12.getInt("DES_SUB_ITEM_ORDER_ID"));
								reportbean.setDesColOrderId(resultSetWord12.getString("TECH_DES_COL_ORDER_ID"));
								reportbean.setSubColValCode(resultSetWord12.getString("SUB_COL_VAL_CD"));
								reportbean.setHeaderText(resultSetWord12.getString("HEADER_TEXT"));
								reportbean.setFooterText(resultSetWord12.getString("FOOTER_TEXT"));
								// reportbean.setLinkFlag(resultSetWord12.getInt("LINK_FLG"));
								reportbean.setDesItemOrderId(resultSetWord12.getInt("DES_ITEM_ORDER_ID"));
								reportbean.setAddOnNewColFlag(resultSetWord12.getInt("ADD_ON_NEW_COL_FLG"));
								reportbean.setItemQuantity(resultSetWord12.getInt("ITEM_QTY"));
								reportbean.setSpaceF2f(resultSetWord12.getString("SPACE"));
								wordData.add(reportbean);

							}
						}
						size10 = wordData.size();// 14

						// ci ext
						if (callableStatement.getMoreResults()) {
							resultSetWord13 = callableStatement.getResultSet();

							while (resultSetWord13.next()) {
								ReportBean reportbean = new ReportBean();

								reportbean.setCategoryName(resultSetWord13.getString("CAT_NM"));
								reportbean.setSubCategoryName(resultSetWord13.getString("SUB_CAT_NM"));
								// reportbean.setItemId(resultSetWord13.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord13.getString("ITEM_NM"));
								reportbean.setItemCode(resultSetWord13.getString("ITEM_CD"));
								// reportbean.setSubItemCode(resultSetWord13.getString("SUB_ITEM_CD"));
								reportbean.setSubItemName(resultSetWord13.getString("SUB_ITEM_NM"));
								reportbean.setColNm(resultSetWord13.getString("COL_NM"));
								reportbean.setColValCd(resultSetWord13.getString("COL_VAL_CD"));
								reportbean.setQuantityAux(resultSetWord13.getInt("QTY"));
								reportbean.setTechRemarks(resultSetWord13.getString("TECH_REMARKS"));
								// reportbean.setTechComments(resultSetWord13.getString("TECH_COMMENTS"));
								// reportbean.setNumberOfSubItems(resultSetWord13.getInt("NO_OF_SUB_ITEMS"));

								wordData.add(reportbean);

							}
						}
						size11 = wordData.size();// 14

						// performance
						// List<DBOBean> itemList = new ArrayList<>();
						if (callableStatement.getMoreResults()) {
							resultSetWord21 = callableStatement.getResultSet();
							while (resultSetWord21.next()) {
								ReportBean reportbean = new ReportBean();
								// DBOBean bean = new DBOBean();

								reportbean.setCategoryName(resultSetWord21.getString("CAT_NM"));

								reportbean.setItemId(resultSetWord21.getInt("ITEM_ID"));
								logger.info(resultSetWord21.getInt("ITEM_ID"));
								// reportbean.setItemNm(resultSetWord21.getString("ITEM_NM"));
								reportbean.setItemNewName(resultSetWord21.getString("ITEM_NM"));
								logger.info(resultSetWord21.getString("ITEM_NM"));
								// bean.setItemId(resultSetWord21.getInt("ITEM_ID"));
								// bean.setItemNm(resultSetWord21.getString("ITEM_NM"));
								//
								//
								wordData.add(reportbean);
								// itemList.add(bean);

							}
						}
						size13 = wordData.size();// 14

						for (int x1 = 0; x1 < wordData.size(); x1++) {
							logger.info("inside test for");
							logger.info(wordData.get(x1).getItemNewName());
							logger.info(x1);

						}

						if (callableStatement.getMoreResults()) {
							resultSetWord15 = callableStatement.getResultSet();
							logger.info(resultSetWord15.getFetchSize());
							while (resultSetWord15.next()) {
								ReportBean reportbean = new ReportBean();

								reportbean.setQuotId(resultSetWord15.getInt("QUOT_ID"));
								reportbean.setItemId(resultSetWord15.getInt("ITEM_ID"));
								reportbean.setParamId(resultSetWord15.getInt("PARAM_ID"));
								reportbean.setParamNm(resultSetWord15.getString("PARAM_NM"));
								logger.info(resultSetWord15.getString("PARAM_NM"));
								reportbean.setUnitId(resultSetWord15.getInt("UNIT_ID"));
								reportbean.setUnitNm(resultSetWord15.getString("UNIT_NM"));
								reportbean.setGuaranteed(resultSetWord15.getString("GUARNTEED"));
								logger.info(resultSetWord15.getString("GUARNTEED"));

								reportbean.setCond1(resultSetWord15.getInt("COND1"));
								reportbean.setCond2(resultSetWord15.getInt("COND2"));
								reportbean.setCond3(resultSetWord15.getInt("COND3"));
								reportbean.setCond4(resultSetWord15.getInt("COND4"));
								reportbean.setCond5(resultSetWord15.getInt("COND5"));
								reportbean.setCond6(resultSetWord15.getInt("COND6"));
								reportbean.setCond7(resultSetWord15.getInt("COND7"));
								reportbean.setCond8(resultSetWord15.getInt("COND8"));
								reportbean.setCond9(resultSetWord15.getInt("COND9"));
								reportbean.setCond10(resultSetWord15.getInt("COND10"));
								reportbean.setNoOfCondition(resultSetWord15.getInt("NO_OF_CONDITIONS"));
								reportbean.setConditiontableinput(resultSetWord15.getInt("CONDTION_TABLE_INPUT"));
								reportbean.setNote(resultSetWord15.getString("NOTE"));
								wordData.add(reportbean);

							}
						}
						size14 = wordData.size();// 14

						if (callableStatement.getMoreResults()) {
							resultSetWord14 = callableStatement.getResultSet();

							while (resultSetWord14.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setCategoryName(resultSetWord14.getString("CAT_NM"));
								reportbean.setItemId(resultSetWord14.getInt("ITEM_ID"));
								reportbean.setItemNm(resultSetWord14.getString("ITEM_NM"));
								reportbean.setHmbdTableInput(resultSetWord14.getInt("HMBD_TABLE_INPUT"));
								// reportbean.setNoOfCondition(resultSetWord14.getInt("NO_OF_CONDITIONS"));
								reportbean.setSubItemId(resultSetWord14.getInt("SUB_ITEM_ID"));
								reportbean.setSubItemNm(resultSetWord14.getString("SUB_ITEM_NM"));
								reportbean.setConsumerId1(resultSetWord14.getString("CONSUMER_ID"));
								reportbean.setStartUp(resultSetWord14.getString("STARTUP"));
								reportbean.setContinuous(resultSetWord14.getString("CONTINUOUS"));
								reportbean.setColValCd(resultSetWord14.getString("COL_VAL_CD"));
								reportbean.setEditFlag(resultSetWord14.getInt("EDIT_FLG"));
								reportbean.setItemType(resultSetWord14.getString("ITEM_TYPE"));
								reportbean.setItemCd(resultSetWord14.getString("ITEM_CD"));
								reportbean.setSpeed(resultSetWord14.getString("SPEED"));
								reportbean.setVoltage(resultSetWord14.getString("VOLTAGE"));
								reportbean.setFeeder(resultSetWord14.getString("FEEDER"));
								reportbean.setTechRemarks(resultSetWord14.getString("TECH_REMARKS"));
								reportbean.setNote(resultSetWord14.getString("NOTE"));

								wordData.add(reportbean);

							}
						}
						size15 = wordData.size();// 14

						if (callableStatement.getMoreResults()) {
							resultSetWord16 = callableStatement.getResultSet();

							while (resultSetWord16.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setItemId(resultSetWord16.getInt("ITEM_ID"));
								// reportbean.setItemNm(resultSetWord16.getString("ITEM_NM"));
								reportbean.setFixedText1(resultSetWord16.getString("FIXED_TEXT1"));
								reportbean.setFixedText2(resultSetWord16.getString("FIXED_TEXT2"));
								reportbean.setFixedText3(resultSetWord16.getString("FIXED_TEXT3"));
								wordData.add(reportbean);

							}
						}
						size16 = wordData.size();// 14

						if (callableStatement.getMoreResults()) {
							resultSetWord17 = callableStatement.getResultSet();

							while (resultSetWord17.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setItemId(resultSetWord17.getInt("ITEM_ID"));
								// reportbean.setItemNm(resultSetWord17.getString("ITEM_NM"));
								reportbean.setIdentifier(resultSetWord17.getString("IDENTIFIER"));
								reportbean.setUnits(resultSetWord17.getString("UNIT"));
								reportbean.setRecmd(resultSetWord17.getString("RECMD"));
								reportbean.setLimit(resultSetWord17.getString("LIMIT"));
								wordData.add(reportbean);

							}
						}
						size17 = wordData.size();// 14

						if (callableStatement.getMoreResults()) {
							resultSetWord18 = callableStatement.getResultSet();
							logger.info("size fo ");

							while (resultSetWord18.next()) {
								ReportBean reportbean = new ReportBean();
								reportbean.setItemId(resultSetWord18.getInt("ITEM_ID"));
								// reportbean.setItemNm(resultSetWord18.getString("ITEM_NM"));
								reportbean.setColNm(resultSetWord18.getString("COL_NM"));
								reportbean.setConductivity(resultSetWord18.getString("CONDUCTIVITY"));
								reportbean.setContinuous(resultSetWord18.getString("CONTINUOUS"));
								reportbean.setStartUp(resultSetWord18.getString("START_UP"));

								wordData.add(reportbean);

							}
						}
						size18 = wordData.size();// 14

						if (callableStatement.getMoreResults()) {
							resultSetWord19 = callableStatement.getResultSet();
							logger.info("result set 19");
							while (resultSetWord19.next()) {
								ReportBean reportbean = new ReportBean();

								reportbean.setItemId(resultSetWord19.getInt("ITEM_ID"));
								// reportbean.setItemNm(resultSetWord19.getString("ITEM_NM"));
								reportbean.setParameter(resultSetWord19.getString("PARAMETERS"));
								reportbean.setType(resultSetWord19.getString("TYPE"));
								reportbean.setUnits(resultSetWord19.getString("UNITS"));
								reportbean.setCirWater(resultSetWord19.getString("CIR_WATER"));

								wordData.add(reportbean);

							}
						}
						size19 = wordData.size();// 14

						if (callableStatement.getMoreResults()) {
							resultSetWord20 = callableStatement.getResultSet();
							logger.info("result set 20");
							while (resultSetWord20.next()) {
								ReportBean reportbean = new ReportBean();

								reportbean.setCategoryName(resultSetWord20.getString("CAT_NM"));
								reportbean.setSeqNo(resultSetWord20.getInt("SEQ_NO"));
								reportbean.setSsId(resultSetWord20.getInt("SS_ID"));
								reportbean.setScopeCd(resultSetWord20.getString("SCOPE_CD"));
								reportbean.setScopeNm(resultSetWord20.getString("SCOPE_NM"));
								reportbean.setItemId(resultSetWord20.getInt("ITEM_ID"));
								reportbean.setItemNm(resultSetWord20.getString("ITEM_NM"));
								reportbean.setInformation(resultSetWord20.getString("INFORMATION"));
								reportbean.setFinalts(resultSetWord20.getString("FINAL"));
								reportbean.setSubScopeCd(resultSetWord20.getString("SUB_SCOPE_CD"));
								reportbean.setDescription(resultSetWord20.getString("DESCRIPTION"));
								reportbean.setEquipment(resultSetWord20.getString("EQUIPMENT"));
								reportbean.setTest(resultSetWord20.getString("TEST"));
								reportbean.setEquivalent(resultSetWord20.getString("EQUIVALENT"));
								reportbean.setPanelType(resultSetWord20.getString("PANEL_TYPE"));
								reportbean.setCustType(resultSetWord20.getString("CUST_TYPE"));
								reportbean.setQuanti(resultSetWord20.getString("QTY"));
								reportbean.setCost1(resultSetWord20.getInt("COST"));
								reportbean.setEditFlag(resultSetWord20.getInt("EDIT_FLG"));
								reportbean.setNewColValFlag(resultSetWord20.getInt("NEW_COL_VAL_FLG"));
								reportbean.setNewColValFlag(resultSetWord20.getInt("NO_OF_MANDAYS"));
								reportbean.setRemarks(resultSetWord20.getString("REMARKS"));
								reportbean.setNote(resultSetWord20.getString("NOTE"));

								wordData.add(reportbean);
								
							}
						}
						size20 = wordData.size();// 14

						// if (callableStatement.getMoreResults()) {
						// resultSetWord202 = callableStatement.getResultSet();
						// logger.info("result set 202");
						// while (resultSetWord202.next()) {
						// ReportBean reportbean = new ReportBean();
						//
						// reportbean.setTypeOfChargeId(resultSetWord202.getInt("TYP_OF_CHARGE"));
						// reportbean.setTypeOfChargeName(resultSetWord202.getString("TYP_OF_CHARGE_NAME"));
						// reportbean.setLoadingId(resultSetWord202.getInt("LOADING_ID"));
						// reportbean.setLoadingName(resultSetWord202.getString("LOADING_NAME"));
						// reportbean.setLoadgingId(resultSetWord202.getInt("LOADGING_ID"));
						// reportbean.setLoadgingName(resultSetWord202.getString("LOADGING_NAME"));
						// reportbean.setNumberOfMandays(resultSetWord202.getString("NO_OF_MANDAYS"));
						// reportbean.setRemarks(resultSetWord202.getString("REMARKS"));
						//
						// wordData.add(reportbean);
						//
						// }
						// }
						// size202= wordData.size();// 14

						if (callableStatement.getMoreResults()) {
							resultSetWord21 = callableStatement.getResultSet();
							logger.info("result set 21");
							while (resultSetWord21.next()) {
								ReportBean reportbean = new ReportBean();

								reportbean.setItemId(resultSetWord21.getInt("ITEM_ID"));
								reportbean.setItemName(resultSetWord21.getString("ITEM_NM"));
								reportbean.setItem(resultSetWord21.getString("ITEM"));
								reportbean.setFilepath(resultSetWord21.getString("FILE_PATH"));
								reportbean.setSsId(resultSetWord21.getInt("SS_ID"));
								reportbean.setScopeCd(resultSetWord21.getString("SCOPE_CD"));
								reportbean.setScopeNm(resultSetWord21.getString("SCOPE_NM"));
								reportbean.setInformation(resultSetWord21.getString("INORMATION"));
								reportbean.setRemarks(resultSetWord21.getString("REMARKS"));
								reportbean.setSlNum(resultSetWord21.getInt("SL_NO"));
								reportbean.setName(resultSetWord21.getString("FILE_NM"));
								wordData.add(reportbean);

							}
						}
						size21 = wordData.size();// 14

						if (!wordData.isEmpty()) {
							reportBean.setWordData(wordData);
						}

					} catch (Exception e) {
						reportBean.setSuccessCode(-1);
						reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

						logger.error(ItoConstants.EXCEPTION, e);
					} finally {
						UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord1);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord2);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord3);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord4);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord5);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord6);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord7);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord8);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord9);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord10);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord11);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord12);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord13);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord14);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord15);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord16);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord17);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord18);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord19);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord20);
						UtilityMethods.closeResource(connection, callableStatement, resultSetWord21);

					}
					return reportBean;
				}

	@Override
	public ReportBean getWordData(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetWord1 = null;
		ResultSet resultSetWord2 = null;
		ResultSet resultSetWord3 = null;
		ResultSet resultSetWord4 = null;
		ResultSet resultSetWord5 = null;
		ResultSet resultSetWord6 = null;
		ResultSet resultSetWord7 = null;
		ResultSet resultSetWordCi = null;
		ResultSet resultSetWord8 = null;
		ResultSet resultSetWord81 = null;
		ResultSet resultSetWord9 = null;
		ResultSet resultSetWord10 = null;
		ResultSet resultSetWord11 = null;
		ResultSet resultSetWord12 = null;
		ResultSet resultSetWord13 = null;
		ResultSet resultSetWord14 = null;
		ResultSet resultSetWord15 = null;
		ResultSet resultSetWord16 = null;
		ResultSet resultSetWord17 = null;
		ResultSet resultSetWord18 = null;
		ResultSet resultSetWord19 = null;
		ResultSet resultSetWord20 = null;
		ResultSet resultSetWord21 = null;
		ResultSet resultSetWord202 = null;
		List<ReportBean> wordData = new ArrayList<>();
		List<ReportBean> wordDataAux = new ArrayList<>();
		List<String> categoryname = new ArrayList<>();
		List<String> subcategoryname = new ArrayList<>();

		DecimalFormat formatNum = new DecimalFormat("#,###");
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RPT_TECH_OFFER (?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}
			// cover page
			if (callableStatement.getMoreResults()) {
				resultSetWord1 = callableStatement.getResultSet();

				while (resultSetWord1.next()) {

					reportBean.setOpportunitySeqNum(resultSetWord1.getString("SFDCNUM"));
					reportBean.setDate(resultSetWord1.getString("DATE"));
					reportBean.setAccountName(resultSetWord1.getString("ACCOUNT_NAME"));
					reportBean.setOppName(resultSetWord1.getString("OPP_NAME"));
					reportBean.setEndUserName(resultSetWord1.getString("END_USER_NAME"));
					reportBean.setDeptName(resultSetWord1.getString("DEPT_NAME"));
					reportBean.setDesignation(resultSetWord1.getString("DESIGNATION"));
					reportBean.setUserName(resultSetWord1.getString("USER_NAME"));
					reportBean.setEnquiryReference(resultSetWord1.getString("ENQUIRY_REFERENCE"));
					reportBean.setCustContactPersonName(resultSetWord1.getString(ItoConstants.CUST_CONT_PERSON));
					reportBean.setOppContactAddress(resultSetWord1.getString("OPP_CONTACT_ADDRESS"));
					reportBean.setSubject(resultSetWord1.getString("SUBJECT"));
					reportBean.setTurbineCode(resultSetWord1.getString("TURB_CD"));
					reportBean.setTypeOfOff(resultSetWord1.getString("TYP_OF_OFFER"));
					reportBean.setCustSpace(resultSetWord1.getString("CUST_SPACE"));
				}

			}
			// index

			if (callableStatement.getMoreResults()) {
				resultSetWord2 = callableStatement.getResultSet();

				while (resultSetWord2.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord2.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord2.getString("SUB_CAT_NM"));
					wordData.add(reportbean);

				}

			}
			size = wordData.size();// 12
			// scope of supply chapter
			if (callableStatement.getMoreResults()) {
				resultSetWord3 = callableStatement.getResultSet();

				while (resultSetWord3.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord3.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord3.getString("SUB_CAT_NM"));
					reportbean.setItemName(resultSetWord3.getString("ITEM_NM"));
					reportbean.setSubCatName(resultSetWord3.getString("SUBCAT_NM"));
					wordData.add(reportbean);

				}

			}

			size1 = wordData.size();// 14

			// technicaldetails chapter(1-8) f2f mech
			if (callableStatement.getMoreResults()) {
				resultSetWord4 = callableStatement.getResultSet();

				while (resultSetWord4.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord4.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord4.getString("SUB_CAT_NM"));
					reportbean.setItemName(resultSetWord4.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord4.getString("ITEM_CD"));
					reportbean.setSubItemName(resultSetWord4.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeName(resultSetWord4.getString("SUB_ITEM_TYP_NM"));
					reportbean.setColNm(resultSetWord4.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord4.getString("COL_VAL_CD"));
					reportbean.setTechRemarks(resultSetWord4.getString("TECH_REMARKS"));
					reportbean.setTechComments(resultSetWord4.getString("TECH_COMMENTS"));
					reportbean.setVarientTypeName(resultSetWord4.getString("VARIANT_TYP_NM"));
					reportbean.setCategory(resultSetWord4.getString("CATEGORY"));
					reportbean.setFixedText1(resultSetWord4.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetWord4.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetWord4.getString("FIXED_TEXT3"));
					reportbean.setFixedText4(resultSetWord4.getString("FIXED_TEXT4"));
					reportbean.setFixedText5(resultSetWord4.getString("FIXED_TEXT5"));
					// reportbean.setFixedText6(resultSetWord4.getString("FIXED_TEXT6"));
					reportbean.setNumberOfRows(resultSetWord4.getInt("NO_OF_ROWS"));
					reportbean.setSpaceF2f(resultSetWord4.getString("SPACE_F2F"));
					reportbean.setQuantity(resultSetWord4.getInt("QTY"));

					wordData.add(reportbean);

				}
			}
			size2 = wordData.size();// 14

			// technicaldetails chapter(9) mech aux
			if (callableStatement.getMoreResults()) {
				resultSetWord5 = callableStatement.getResultSet();

				while (resultSetWord5.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord5.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord5.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord5.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord5.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord5.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWord5.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord5.getString("SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord5.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord5.getString("COL_VAL_CD"));
					reportbean.setQuantityAux(resultSetWord5.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord5.getString("TECH_REMARKS"));
					reportbean.setTechComments(resultSetWord5.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord5.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setSubItemId(resultSetWord5.getInt("SUB_ITEM_ID"));
					reportbean.setAuxSpace(resultSetWord5.getString("AUX_SPACE"));
					reportbean.setAuxNewline(resultSetWord5.getString("AUX_NEWLINE"));
					reportbean.setDoubleQuot(resultSetWord5.getString("DOUBLE_QUOTE"));
					reportbean.setItemQuantity(resultSetWord5.getInt("ITEM_QTY"));
					

					wordData.add(reportbean);

				}
			}
			size3 = wordData.size();// 14

			// technicaldetails chapter(10) mech ext
			if (callableStatement.getMoreResults()) {
				resultSetWord6 = callableStatement.getResultSet();

				while (resultSetWord6.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord6.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord6.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord6.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord6.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord6.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWord6.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord6.getString("SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord6.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord6.getString("COL_VAL_CD"));
					reportbean.setQuantityAux(resultSetWord6.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord6.getString("TECH_REMARKS"));
					reportbean.setTechComments(resultSetWord6.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord6.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setSpaceF2f(resultSetWord6.getString("SPACE"));
					
					wordData.add(reportbean);

				}
			}
			size4 = wordData.size();// 14

			// technicaldetails electrical
			if (callableStatement.getMoreResults()) {
				resultSetWord7 = callableStatement.getResultSet();

				while (resultSetWord7.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord7.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord7.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord7.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord7.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord7.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWord7.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord7.getString("SUB_ITEM_NM"));
					reportbean.setDesItemName(resultSetWord7.getString("DES_ITEM_NM"));
					reportbean.setDesSubItemName(resultSetWord7.getString("DES_SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord7.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord7.getString("COL_VAL_CD"));
					logger.info(reportbean.getColValCd());
					reportbean.setQuantityEle(resultSetWord7.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord7.getString("TECH_REMARKS"));
					// reportbean.setTechComments(resultSetWord7.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord7.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setOrderId(resultSetWord7.getInt("ORDER_ID"));
					reportbean.setDesSubItemOrderId(resultSetWord7.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setDesColOrderId(resultSetWord7.getString("TECH_DES_COL_ORDER_ID"));
					reportbean.setSubColValCode(resultSetWord7.getString("SUB_COL_VAL_CD"));
					reportbean.setHeaderText(resultSetWord7.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWord7.getString("FOOTER_TEXT"));
					reportbean.setLinkFlag(resultSetWord7.getInt("LINK_FLG"));
					reportbean.setDesItemOrderId(resultSetWord7.getInt("DES_ITEM_ORDER_ID"));
					reportbean.setAddOnNewColFlag(resultSetWord7.getInt("ADD_ON_NEW_COL_FLG"));
					reportbean.setItemQuantity(resultSetWord7.getInt("ITEM_QTY"));
					reportbean.setSpaceF2f(resultSetWord7.getString("SPACE"));

					wordData.add(reportbean);

				}
			}
			size5 = wordData.size();// 14

			// ele aux
			// ele aux
			if (callableStatement.getMoreResults()) {
				resultSetWord10 = callableStatement.getResultSet();

				while (resultSetWord10.next()) {

					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord10.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord10.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord10.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord10.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord10.getString("ITEM_CD"));
					reportbean.setSubItemId(resultSetWord10.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemCode(resultSetWord10.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord10.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeId(resultSetWord10.getInt("SUB_ITEM_TYP_ID"));
					reportbean.setSubItemTypeName(resultSetWord10.getString("SUB_ITEM_TYP_NM"));

					reportbean.setDesItemName(resultSetWord10.getString("DES_ITEM_NM"));
					reportbean.setDesSubItemName(resultSetWord10.getString("DES_SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord10.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord10.getString("COL_VAL_CD"));
					reportbean.setQuantityEle(resultSetWord10.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord10.getString("TECH_REMARKS"));
					// reportbean.setTechComments(resultSetWord10.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord10.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setOrderId(resultSetWord10.getInt("ORDER_ID"));
					reportbean.setDesSubItemOrderId(resultSetWord10.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setDesColOrderId(resultSetWord10.getString("TECH_DES_COL_ORDER_ID"));
					reportbean.setSubColValCode(resultSetWord10.getString("SUB_COL_VAL_CD"));
					reportbean.setHeaderText(resultSetWord10.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWord10.getString("FOOTER_TEXT"));
					reportbean.setLinkFlag(resultSetWord10.getInt("LINK_FLG"));
					reportbean.setDesItemOrderId(resultSetWord10.getInt("DES_ITEM_ORDER_ID"));
					reportbean.setRhsColTechcomments(resultSetWord10.getString("RHS_COL_TECH_COMMENTS"));
					reportbean.setAddOnNewColFlag(resultSetWord10.getInt("ADD_ON_NEW_COL_FLG"));
					reportbean.setItemQuantity(resultSetWord10.getInt("ITEM_QTY"));
					reportbean.setSpaceF2f(resultSetWord10.getString("SPACE"));
					wordData.add(reportbean);

				}
			}
			size8 = wordData.size();// 14

			// ele ext
			if (callableStatement.getMoreResults()) {
				resultSetWord11 = callableStatement.getResultSet();

				while (resultSetWord11.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord11.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord11.getString("SUB_CAT_NM"));
					// reportbean.setItemId(resultSetWord11.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord11.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord11.getString("ITEM_CD"));
					// reportbean.setSubItemCode(resultSetWord11.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord11.getString("SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord11.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord11.getString("COL_VAL_CD"));
					reportbean.setQuantityAux(resultSetWord11.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord11.getString("TECH_REMARKS"));
					reportbean.setSpaceF2f(resultSetWord11.getString("SPACE"));
					// reportbean.setTechComments(resultSetWord11.getString("TECH_COMMENTS"));
					// reportbean.setNumberOfSubItems(resultSetWord11.getInt("NO_OF_SUB_ITEMS"));

					wordData.add(reportbean);

				}
			}
			size9 = wordData.size();// 14

			// ci result set

			if (callableStatement.getMoreResults()) {
				resultSetWordCi = callableStatement.getResultSet();

				while (resultSetWordCi.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWordCi.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWordCi.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWordCi.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWordCi.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWordCi.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWordCi.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWordCi.getString("SUB_ITEM_NM"));
					reportbean.setDesItemName(resultSetWordCi.getString("DES_ITEM_NM"));
					reportbean.setDesSubItemName(resultSetWordCi.getString("DES_SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWordCi.getString("COL_NM"));
					reportbean.setColValCd(resultSetWordCi.getString("COL_VAL_CD"));
					reportbean.setQuantityEle(resultSetWordCi.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWordCi.getString("TECH_REMARKS"));
					// reportbean.setTechComments(resultSetWord7.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWordCi.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setOrderId(resultSetWordCi.getInt("ORDER_ID"));
					reportbean.setDesSubItemOrderId(resultSetWordCi.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setDesColOrderId(resultSetWordCi.getString("TECH_DES_COL_ORDER_ID"));
					reportbean.setSubColValCode(resultSetWordCi.getString("SUB_COL_VAL_CD"));
					reportbean.setHeaderText(resultSetWordCi.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWordCi.getString("FOOTER_TEXT"));
					reportbean.setLinkFlag(resultSetWordCi.getInt("LINK_FLG"));
					reportbean.setDesItemOrderId(resultSetWordCi.getInt("DES_ITEM_ORDER_ID"));
					reportbean.setRhsColTechcomments(resultSetWordCi.getString("RHS_COL_TECH_COMMENTS"));
					reportbean.setAddOnNewColFlag(resultSetWordCi.getInt("ADD_ON_NEW_COL_FLG"));
					reportbean.setItemQuantity(resultSetWordCi.getInt("ITEM_QTY"));
					reportbean.setSpaceF2f(resultSetWordCi.getString("SPACE"));
					wordData.add(reportbean);

				}
			}
			sizeCi = wordData.size();// 14

			// vms
			if (callableStatement.getMoreResults()) {
				resultSetWord9 = callableStatement.getResultSet();

				while (resultSetWord9.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setItemId(resultSetWord9.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord9.getString("ITEM_NM"));
					reportbean.setType(resultSetWord9.getString("TYPE"));
					reportbean.setAddPrbFlag(resultSetWord9.getInt("ADD_PRB_FLG"));
					reportbean.setOrderBy(resultSetWord9.getInt("ORDER_BY"));
					reportbean.setTagNum(resultSetWord9.getString("TAG_NO"));
					reportbean.setEquipment(resultSetWord9.getString("EQUIPMENT"));
					reportbean.setApplication(resultSetWord9.getString("APPLICATION"));
					reportbean.setLocation(resultSetWord9.getString("LOCATION"));
					reportbean.setQuantity(resultSetWord9.getInt("QTY"));
					reportbean.setCost2(Math.round(resultSetWord9.getFloat("COST")));
					reportbean.setNewColValFlag(resultSetWord9.getInt("NEW_COL_VAL_FLG"));
					reportbean.setItemCost(Math.round(resultSetWord9.getFloat("ITEM_COST")));
					reportbean.setHeaderText(resultSetWord9.getString("HEADER_TEXT"));
					reportbean.setNote(resultSetWord9.getString("NOTE"));
					wordData.add(reportbean);

				}
			}
			size7 = wordData.size();// 14

			// stg
			if (callableStatement.getMoreResults()) {
				resultSetWord8 = callableStatement.getResultSet();

				while (resultSetWord8.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord8.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord8.getString("SUB_CAT_NM"));
					reportbean.setItemNm(resultSetWord8.getString("ITEM_NM"));
					reportbean.setItemId(resultSetWord8.getInt("ITEM_ID"));
					reportbean.setId(resultSetWord8.getInt("ID"));

					reportbean.setInstrCode(resultSetWord8.getString("INSTR_CD"));
					reportbean.setInstrNm(resultSetWord8.getString("INSTR_NM"));
					reportbean.setInstrTypeNm(resultSetWord8.getString("INSTR_TYPE_NM"));
					reportbean.setInstrDesc(resultSetWord8.getString("INSTR_DESC"));
					reportbean.setInstrMounting(resultSetWord8.getString("INSTR_MOUNTING"));
					reportbean.setQuantityLogic(resultSetWord8.getInt("QTY_LOGIC"));
					reportbean.setCost1(resultSetWord8.getFloat("COST"));
					reportbean.setNoOfTable(resultSetWord8.getInt("NO_OF_TABLE"));
					reportbean.setHeaderText(resultSetWord8.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWord8.getString("FOOTER_TEXT"));
					reportbean.setItemId(resultSetWord8.getInt("ITEM_ID"));
					reportbean.setAddInstrId(resultSetWord8.getInt("ADD_INSTR_ID"));
					reportbean.setAddInstrCd(resultSetWord8.getString("ADD_INSTR_CD"));
					reportbean.setAddInstrNm(resultSetWord8.getString("ADD_INSTR_NM"));
					wordData.add(reportbean);

				}
			}
			size6 = wordData.size();// 14

			// save addinst

			if (callableStatement.getMoreResults()) {
				resultSetWord81 = callableStatement.getResultSet();

				while (resultSetWord81.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setDetQuotId(resultSetWord81.getInt("DET_QUOT_ID"));
					reportbean.setItemId(resultSetWord81.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord81.getString("ITEM_NM"));
					reportbean.setQuantity(resultSetWord81.getInt("QTY"));
					reportbean.setColId(resultSetWord81.getInt("COL_ID"));
					reportbean.setColNm(resultSetWord81.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord81.getString("COL_VAL_CD"));
					reportbean.setSubColValCode(resultSetWord81.getString("SUB_COL_VAL_CD"));
					reportbean.setAddOnNewColFlag(resultSetWord81.getInt("ADD_ON_NEW_COL_FLG"));
					reportbean.setRhsFlag(resultSetWord81.getInt("RHS_FLG") == 1 ? true : false);
					reportbean.setRhsColQuantity(resultSetWord81.getFloat("RHS_COL_QTY"));
					reportbean.setDesSubItemOrderId(resultSetWord81.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setRhsCost(Math.round(resultSetWord81.getFloat("RHS_COST")));
					reportbean.setAddInstrCost(Math.round(resultSetWord81.getFloat("ADD_INSTR_COST")));
					reportbean.setTotalAddInstrCost(Math.round(resultSetWord81.getFloat("TOTAL_ADD_INSTR_COST")));
					reportbean.setItemFlag(resultSetWord81.getInt("ITEM_FLG") == 1 ? true : false);
					reportbean.setOthersFlag(resultSetWord81.getInt("OTHERS_FLG") == 1 ? true : false);
					reportbean.setAddInstrFlag(resultSetWord81.getInt("ADD_INSTR_FLG") == 1 ? true : false);
					wordData.add(reportbean);

				}
			}
			size12 = wordData.size();// 14

			// ci aux
			if (callableStatement.getMoreResults()) {
				resultSetWord12 = callableStatement.getResultSet();

				while (resultSetWord12.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord12.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord12.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord12.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord12.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord12.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWord12.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord12.getString("SUB_ITEM_NM"));
					reportbean.setDesItemName(resultSetWord12.getString("DES_ITEM_NM"));
					reportbean.setDesSubItemName(resultSetWord12.getString("DES_SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord12.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord12.getString("COL_VAL_CD"));
					reportbean.setQuantityEle(resultSetWord12.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord12.getString("TECH_REMARKS"));
					// reportbean.setTechComments(resultSetWord12.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord12.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setOrderId(resultSetWord12.getInt("ORDER_ID"));
					reportbean.setDesSubItemOrderId(resultSetWord12.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setDesColOrderId(resultSetWord12.getString("TECH_DES_COL_ORDER_ID"));
					reportbean.setSubColValCode(resultSetWord12.getString("SUB_COL_VAL_CD"));
					reportbean.setHeaderText(resultSetWord12.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWord12.getString("FOOTER_TEXT"));
					// reportbean.setLinkFlag(resultSetWord12.getInt("LINK_FLG"));
					reportbean.setDesItemOrderId(resultSetWord12.getInt("DES_ITEM_ORDER_ID"));
					reportbean.setAddOnNewColFlag(resultSetWord12.getInt("ADD_ON_NEW_COL_FLG"));
					reportbean.setItemQuantity(resultSetWord12.getInt("ITEM_QTY"));
					reportbean.setSpaceF2f(resultSetWord12.getString("SPACE"));
					wordData.add(reportbean);

				}
			}
			size10 = wordData.size();// 14

			// ci ext
			if (callableStatement.getMoreResults()) {
				resultSetWord13 = callableStatement.getResultSet();

				while (resultSetWord13.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord13.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord13.getString("SUB_CAT_NM"));
					// reportbean.setItemId(resultSetWord13.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord13.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord13.getString("ITEM_CD"));
					// reportbean.setSubItemCode(resultSetWord13.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord13.getString("SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord13.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord13.getString("COL_VAL_CD"));
					reportbean.setQuantityAux(resultSetWord13.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord13.getString("TECH_REMARKS"));
					reportbean.setSpaceF2f(resultSetWord13.getString("SPACE"));
					// reportbean.setNumberOfSubItems(resultSetWord13.getInt("NO_OF_SUB_ITEMS"));

					wordData.add(reportbean);

				}
			}
			size11 = wordData.size();// 14

			// performance
			// List<DBOBean> itemList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetWord21 = callableStatement.getResultSet();
				while (resultSetWord21.next()) {
					ReportBean reportbean = new ReportBean();
					// DBOBean bean = new DBOBean();

					reportbean.setCategoryName(resultSetWord21.getString("CAT_NM"));

					reportbean.setItemId(resultSetWord21.getInt("ITEM_ID"));
					logger.info(resultSetWord21.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord21.getString("ITEM_NM"));
					reportbean.setItemNewName(resultSetWord21.getString("ITEM_NM"));
					logger.info(resultSetWord21.getString("ITEM_NM"));
					// bean.setItemId(resultSetWord21.getInt("ITEM_ID"));
					// bean.setItemNm(resultSetWord21.getString("ITEM_NM"));
					//
					//
					wordData.add(reportbean);
					// itemList.add(bean);

				}
			}
			size13 = wordData.size();// 14

			for (int x1 = 0; x1 < wordData.size(); x1++) {
				logger.info("inside test for");
				logger.info(wordData.get(x1).getItemNewName());
				logger.info(x1);

			}

			if (callableStatement.getMoreResults()) {
				resultSetWord15 = callableStatement.getResultSet();
				logger.info(resultSetWord15.getFetchSize());
				while (resultSetWord15.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setQuotId(resultSetWord15.getInt("QUOT_ID"));
					reportbean.setItemId(resultSetWord15.getInt("ITEM_ID"));
					reportbean.setParamId(resultSetWord15.getInt("PARAM_ID"));
					reportbean.setParamNm(resultSetWord15.getString("PARAM_NM"));
					logger.info(resultSetWord15.getString("PARAM_NM"));
					reportbean.setUnitId(resultSetWord15.getInt("UNIT_ID"));
					reportbean.setUnitNm(resultSetWord15.getString("UNIT_NM"));
					reportbean.setGuaranteed(resultSetWord15.getString("GUARNTEED"));
					logger.info(resultSetWord15.getString("GUARNTEED"));

					reportbean.setCond1(resultSetWord15.getInt("COND1"));
					reportbean.setCond2(resultSetWord15.getInt("COND2"));
					reportbean.setCond3(resultSetWord15.getInt("COND3"));
					reportbean.setCond4(resultSetWord15.getInt("COND4"));
					reportbean.setCond5(resultSetWord15.getInt("COND5"));
					reportbean.setCond6(resultSetWord15.getInt("COND6"));
					reportbean.setCond7(resultSetWord15.getInt("COND7"));
					reportbean.setCond8(resultSetWord15.getInt("COND8"));
					reportbean.setCond9(resultSetWord15.getInt("COND9"));
					reportbean.setCond10(resultSetWord15.getInt("COND10"));
					reportbean.setNoOfCondition(resultSetWord15.getInt("NO_OF_CONDITIONS"));
					reportbean.setConditiontableinput(resultSetWord15.getInt("CONDTION_TABLE_INPUT"));
					reportbean.setNote(resultSetWord15.getString("NOTE"));
					wordData.add(reportbean);

				}
			}
			size14 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord14 = callableStatement.getResultSet();

				while (resultSetWord14.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord14.getString("CAT_NM"));
					reportbean.setItemId(resultSetWord14.getInt("ITEM_ID"));
					reportbean.setItemNm(resultSetWord14.getString("ITEM_NM"));
					reportbean.setHmbdTableInput(resultSetWord14.getInt("HMBD_TABLE_INPUT"));
					// reportbean.setNoOfCondition(resultSetWord14.getInt("NO_OF_CONDITIONS"));
					reportbean.setSubItemId(resultSetWord14.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemNm(resultSetWord14.getString("SUB_ITEM_NM"));
					reportbean.setConsumerId1(resultSetWord14.getString("CONSUMER_ID"));
					reportbean.setStartUp(resultSetWord14.getString("STARTUP"));
					reportbean.setContinuous(resultSetWord14.getString("CONTINUOUS"));
					reportbean.setColValCd(resultSetWord14.getString("COL_VAL_CD"));
					reportbean.setEditFlag(resultSetWord14.getInt("EDIT_FLG"));
					reportbean.setItemType(resultSetWord14.getString("ITEM_TYPE"));
					reportbean.setItemCd(resultSetWord14.getString("ITEM_CD"));
					reportbean.setSpeed(resultSetWord14.getString("SPEED"));
					reportbean.setVoltage(resultSetWord14.getString("VOLTAGE"));
					reportbean.setFeeder(resultSetWord14.getString("FEEDER"));
					reportbean.setTechRemarks(resultSetWord14.getString("TECH_REMARKS"));
					reportbean.setNote(resultSetWord14.getString("NOTE"));

					wordData.add(reportbean);

				}
			}
			size15 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord16 = callableStatement.getResultSet();

				while (resultSetWord16.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setItemId(resultSetWord16.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord16.getString("ITEM_NM"));
					reportbean.setFixedText1(resultSetWord16.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetWord16.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetWord16.getString("FIXED_TEXT3"));
					wordData.add(reportbean);

				}
			}
			size16 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord17 = callableStatement.getResultSet();

				while (resultSetWord17.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setItemId(resultSetWord17.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord17.getString("ITEM_NM"));
					reportbean.setIdentifier(resultSetWord17.getString("IDENTIFIER"));
					reportbean.setUnits(resultSetWord17.getString("UNIT"));
					reportbean.setRecmd(resultSetWord17.getString("RECMD"));
					reportbean.setLimit(resultSetWord17.getString("LIMIT"));
					wordData.add(reportbean);

				}
			}
			size17 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord18 = callableStatement.getResultSet();
				logger.info("size fo ");

				while (resultSetWord18.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setItemId(resultSetWord18.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord18.getString("ITEM_NM"));
					reportbean.setColNm(resultSetWord18.getString("COL_NM"));
					reportbean.setConductivity(resultSetWord18.getString("CONDUCTIVITY"));
					reportbean.setContinuous(resultSetWord18.getString("CONTINUOUS"));
					reportbean.setStartUp(resultSetWord18.getString("START_UP"));

					wordData.add(reportbean);

				}
			}
			size18 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord19 = callableStatement.getResultSet();
				logger.info("result set 19");
				while (resultSetWord19.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setItemId(resultSetWord19.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord19.getString("ITEM_NM"));
					reportbean.setParameter(resultSetWord19.getString("PARAMETERS"));
					reportbean.setType(resultSetWord19.getString("TYPE"));
					reportbean.setUnits(resultSetWord19.getString("UNITS"));
					reportbean.setCirWater(resultSetWord19.getString("CIR_WATER"));

					wordData.add(reportbean);

				}
			}
			size19 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord20 = callableStatement.getResultSet();
				logger.info("result set 20");
				while (resultSetWord20.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord20.getString("CAT_NM"));
					reportbean.setSeqNo(resultSetWord20.getInt("SEQ_NO"));
					reportbean.setSsId(resultSetWord20.getInt("SS_ID"));
					reportbean.setScopeCd(resultSetWord20.getString("SCOPE_CD"));
					reportbean.setScopeNm(resultSetWord20.getString("SCOPE_NM"));
					reportbean.setItemId(resultSetWord20.getInt("ITEM_ID"));
					reportbean.setItemNm(resultSetWord20.getString("ITEM_NM"));
					reportbean.setInformation(resultSetWord20.getString("INFORMATION"));
					reportbean.setFinalts(resultSetWord20.getString("FINAL"));
					reportbean.setSubScopeCd(resultSetWord20.getString("SUB_SCOPE_CD"));
					reportbean.setDescription(resultSetWord20.getString("DESCRIPTION"));
					reportbean.setEquipment(resultSetWord20.getString("EQUIPMENT"));
					reportbean.setTest(resultSetWord20.getString("TEST"));
					reportbean.setEquivalent(resultSetWord20.getString("EQUIVALENT"));
					reportbean.setPanelType(resultSetWord20.getString("PANEL_TYPE"));
					reportbean.setCustType(resultSetWord20.getString("CUST_TYPE"));
					reportbean.setQuanti(resultSetWord20.getString("QTY"));
					reportbean.setCost1(resultSetWord20.getInt("COST"));
					reportbean.setEditFlag(resultSetWord20.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetWord20.getInt("NEW_COL_VAL_FLG"));
					reportbean.setNewColValFlag(resultSetWord20.getInt("NO_OF_MANDAYS"));
					reportbean.setRemarks(resultSetWord20.getString("REMARKS"));
					reportbean.setNote(resultSetWord20.getString("NOTE"));

					wordData.add(reportbean);
					
				}
			}
			size20 = wordData.size();// 14

			// if (callableStatement.getMoreResults()) {
			// resultSetWord202 = callableStatement.getResultSet();
			// logger.info("result set 202");
			// while (resultSetWord202.next()) {
			// ReportBean reportbean = new ReportBean();
			//
			// reportbean.setTypeOfChargeId(resultSetWord202.getInt("TYP_OF_CHARGE"));
			// reportbean.setTypeOfChargeName(resultSetWord202.getString("TYP_OF_CHARGE_NAME"));
			// reportbean.setLoadingId(resultSetWord202.getInt("LOADING_ID"));
			// reportbean.setLoadingName(resultSetWord202.getString("LOADING_NAME"));
			// reportbean.setLoadgingId(resultSetWord202.getInt("LOADGING_ID"));
			// reportbean.setLoadgingName(resultSetWord202.getString("LOADGING_NAME"));
			// reportbean.setNumberOfMandays(resultSetWord202.getString("NO_OF_MANDAYS"));
			// reportbean.setRemarks(resultSetWord202.getString("REMARKS"));
			//
			// wordData.add(reportbean);
			//
			// }
			// }
			// size202= wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord21 = callableStatement.getResultSet();
				logger.info("result set 21");
				while (resultSetWord21.next()) {
					ReportBean reportbean = new ReportBean();
					logger.info("inside result set 21");
					reportbean.setItemId(resultSetWord21.getInt("ITEM_ID"));
					reportbean.setItemNm(resultSetWord21.getString("ITEM_NM"));
					reportbean.setItem(resultSetWord21.getString("ITEM"));
					reportbean.setFilepath(resultSetWord21.getString("FILE_PATH"));
					reportbean.setSsId(resultSetWord21.getInt("SS_ID"));
					reportbean.setScopeCd(resultSetWord21.getString("SCOPE_CD"));
					reportbean.setScopeNm(resultSetWord21.getString("SCOPE_NM"));
					reportbean.setInformation(resultSetWord21.getString("INORMATION"));
					reportbean.setRemarks(resultSetWord21.getString("REMARKS"));
					reportbean.setSlNum(resultSetWord21.getInt("SL_NO"));
					reportbean.setFileName(resultSetWord21.getString("FILE_NM"));
					logger.info(resultSetWord21.getInt("ITEM_ID"));
					logger.info(resultSetWord21.getString("ITEM_NM"));
					logger.info(resultSetWord21.getString("ITEM"));
					logger.info(resultSetWord21.getString("FILE_PATH"));
					logger.info(resultSetWord21.getInt("SS_ID"));
					logger.info(resultSetWord21.getString("SCOPE_CD"));
					logger.info(resultSetWord21.getString("SCOPE_NM"));
					logger.info(resultSetWord21.getString("INORMATION"));
					logger.info(resultSetWord21.getString("REMARKS"));
					logger.info(resultSetWord21.getInt("SL_NO"));
					logger.info(resultSetWord21.getString("FILE_NM"));
					wordData.add(reportbean);

				}
			}
			size21 = wordData.size();// 14
			logger.info(size21);
			if (!wordData.isEmpty()) {
				logger.info("money222");
				reportBean.setWordData(wordData);
			}
			logger.info("1");


		} catch (Exception e) {
			
			logger.info("2");

			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			logger.info("3");

			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord2);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord3);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord4);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord5);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord6);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord7);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord8);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord9);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord10);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord11);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord12);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord13);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord14);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord15);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord16);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord17);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord18);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord19);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord20);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord21);

		}
		logger.info("5");

		return reportBean;
	}

	@Override
	public QuotationForm downloadPdf(Integer quotId) {
		QuotationForm quotationForm = new QuotationForm();
		quotationForm.getSaveBasicDetails().setQuotId(quotId);
		try {

			String query = "SELECT HMBD FROM HMBD_QUOT  WHERE QUOT_ID = '" + quotId + "';";

			List<QuotationForm> data = jdbcTemplate.query(query, new RowMapper<QuotationForm>() {
				public QuotationForm mapRow(ResultSet rs, int rowNum) throws SQLException {
					QuotationForm bean = new QuotationForm();
					if (!(rs.getBytes("HMBD") == null)) {
						String encoded = Base64.getEncoder().encodeToString(rs.getBytes("HMBD")); // convert
																									// array
																									// of
																									// bytes
																									// back
																									// to
																									// base
																									// 64
																									// format
																									// to
																									// display
																									// in
																									// UI
						bean.setHmbdImage(encoded); // setting encoded base 64
													// format to userdetails
													// bean
					}
					return bean;
				}
			});

			logger.info("data " + data);
			data.get(0).setSuccessCode(0);
			data.get(0).setSuccessMsg("SUCCESS");
			return data.get(0);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return quotationForm;
		}

	}

	@Override
	public ReportBean getTechReportData(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetWord1 = null;
		ResultSet resultSetWord2 = null;
		ResultSet resultSetWord3 = null;
		ResultSet resultSetWord4 = null;
		ResultSet resultSetWord5 = null;
		ResultSet resultSetWord6 = null;
		ResultSet resultSetWord7 = null;
		ResultSet resultSetWordCi = null;
		ResultSet resultSetWord8 = null;
		ResultSet resultSetWord81 = null;
		ResultSet resultSetWord9 = null;
		ResultSet resultSetWord10 = null;
		ResultSet resultSetWord11 = null;
		ResultSet resultSetWord12 = null;
		ResultSet resultSetWord13 = null;
		ResultSet resultSetWord14 = null;
		ResultSet resultSetWord15 = null;
		ResultSet resultSetWord16 = null;
		ResultSet resultSetWord17 = null;
		ResultSet resultSetWord18 = null;
		ResultSet resultSetWord19 = null;
		ResultSet resultSetWord20 = null;
		ResultSet resultSetWord21 = null;
		ResultSet resultSetWord202 = null;
		List<ReportBean> wordData = new ArrayList<>();
		List<ReportBean> wordDataAux = new ArrayList<>();
		List<String> categoryname = new ArrayList<>();
		List<String> subcategoryname = new ArrayList<>();

		DecimalFormat formatNum = new DecimalFormat("#,###");
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RPT_TECH_OFFER (?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}
			// cover page
			if (callableStatement.getMoreResults()) {
				resultSetWord1 = callableStatement.getResultSet();

				while (resultSetWord1.next()) {

					reportBean.setOpportunitySeqNum(resultSetWord1.getString("SFDCNUM"));
					reportBean.setDate(resultSetWord1.getString("DATE"));
					reportBean.setAccountName(resultSetWord1.getString("ACCOUNT_NAME"));
					reportBean.setOppName(resultSetWord1.getString("OPP_NAME"));
					reportBean.setEndUserName(resultSetWord1.getString("END_USER_NAME"));
					reportBean.setDeptName(resultSetWord1.getString("DEPT_NAME"));
					reportBean.setDesignation(resultSetWord1.getString("DESIGNATION"));
					reportBean.setUserName(resultSetWord1.getString("USER_NAME"));
					reportBean.setEnquiryReference(resultSetWord1.getString("ENQUIRY_REFERENCE"));
					reportBean.setCustContactPersonName(resultSetWord1.getString(ItoConstants.CUST_CONT_PERSON));
					reportBean.setOppContactAddress(resultSetWord1.getString("OPP_CONTACT_ADDRESS"));
					reportBean.setSubject(resultSetWord1.getString("SUBJECT"));
					reportBean.setTurbineCode(resultSetWord1.getString("TURB_CD"));
					reportBean.setTypeOfOff(resultSetWord1.getString("TYP_OF_OFFER"));
					reportBean.setCustSpace(resultSetWord1.getString("CUST_SPACE"));
				}

			}
			// index

			if (callableStatement.getMoreResults()) {
				resultSetWord2 = callableStatement.getResultSet();

				while (resultSetWord2.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord2.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord2.getString("SUB_CAT_NM"));
					wordData.add(reportbean);

				}

			}
			size = wordData.size();// 12
			// scope of supply chapter
			if (callableStatement.getMoreResults()) {
				resultSetWord3 = callableStatement.getResultSet();

				while (resultSetWord3.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord3.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord3.getString("SUB_CAT_NM"));
					reportbean.setItemName(resultSetWord3.getString("ITEM_NM"));
					reportbean.setSubCatName(resultSetWord3.getString("SUBCAT_NM"));
					wordData.add(reportbean);

				}

			}

			size1 = wordData.size();// 14

			// technicaldetails chapter(1-8) f2f mech
			if (callableStatement.getMoreResults()) {
				resultSetWord4 = callableStatement.getResultSet();

				while (resultSetWord4.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord4.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord4.getString("SUB_CAT_NM"));
					reportbean.setItemName(resultSetWord4.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord4.getString("ITEM_CD"));
					reportbean.setSubItemName(resultSetWord4.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeName(resultSetWord4.getString("SUB_ITEM_TYP_NM"));
					reportbean.setColNm(resultSetWord4.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord4.getString("COL_VAL_CD"));
					reportbean.setTechRemarks(resultSetWord4.getString("TECH_REMARKS"));
					reportbean.setTechComments(resultSetWord4.getString("TECH_COMMENTS"));
					reportbean.setVarientTypeName(resultSetWord4.getString("VARIANT_TYP_NM"));
					reportbean.setCategory(resultSetWord4.getString("CATEGORY"));
					reportbean.setFixedText1(resultSetWord4.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetWord4.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetWord4.getString("FIXED_TEXT3"));
					reportbean.setFixedText4(resultSetWord4.getString("FIXED_TEXT4"));
					reportbean.setFixedText5(resultSetWord4.getString("FIXED_TEXT5"));
					// reportbean.setFixedText6(resultSetWord4.getString("FIXED_TEXT6"));
					reportbean.setNumberOfRows(resultSetWord4.getInt("NO_OF_ROWS"));
					reportbean.setSpaceF2f(resultSetWord4.getString("SPACE_F2F"));

					wordData.add(reportbean);

				}
			}
			size2 = wordData.size();// 14

			// technicaldetails chapter(9) mech aux
			if (callableStatement.getMoreResults()) {
				resultSetWord5 = callableStatement.getResultSet();

				while (resultSetWord5.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord5.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord5.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord5.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord5.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord5.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWord5.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord5.getString("SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord5.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord5.getString("COL_VAL_CD"));
					reportbean.setQuantityAux(resultSetWord5.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord5.getString("TECH_REMARKS"));
					reportbean.setTechComments(resultSetWord5.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord5.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setSubItemId(resultSetWord5.getInt("SUB_ITEM_ID"));
					reportbean.setAuxSpace(resultSetWord5.getString("AUX_SPACE"));
					reportbean.setAuxNewline(resultSetWord5.getString("AUX_NEWLINE"));
					reportbean.setDoubleQuot(resultSetWord5.getString("DOUBLE_QUOTE"));

					wordData.add(reportbean);

				}
			}
			size3 = wordData.size();// 14

			// technicaldetails chapter(10) mech ext
			if (callableStatement.getMoreResults()) {
				resultSetWord6 = callableStatement.getResultSet();

				while (resultSetWord6.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord6.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord6.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord6.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord6.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord6.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWord6.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord6.getString("SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord6.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord6.getString("COL_VAL_CD"));
					reportBean.setQuantityAux(resultSetWord6.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord6.getString("TECH_REMARKS"));
					reportbean.setTechComments(resultSetWord6.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord6.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setSpaceF2f(resultSetWord6.getString("SPACE"));
					wordData.add(reportbean);

				}
			}
			size4 = wordData.size();// 14

			// technicaldetails electrical
			if (callableStatement.getMoreResults()) {
				resultSetWord7 = callableStatement.getResultSet();

				while (resultSetWord7.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord7.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord7.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord7.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord7.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord7.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWord7.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord7.getString("SUB_ITEM_NM"));
					reportbean.setDesItemName(resultSetWord7.getString("DES_ITEM_NM"));
					reportbean.setDesSubItemName(resultSetWord7.getString("DES_SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord7.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord7.getString("COL_VAL_CD"));
					reportbean.setQuantityEle(resultSetWord7.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord7.getString("TECH_REMARKS"));
					// reportbean.setTechComments(resultSetWord7.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord7.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setOrderId(resultSetWord7.getInt("ORDER_ID"));
					reportbean.setDesSubItemOrderId(resultSetWord7.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setDesColOrderId(resultSetWord7.getString("TECH_DES_COL_ORDER_ID"));
					reportbean.setSubColValCode(resultSetWord7.getString("SUB_COL_VAL_CD"));
					reportbean.setHeaderText(resultSetWord7.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWord7.getString("FOOTER_TEXT"));
					reportbean.setLinkFlag(resultSetWord7.getInt("LINK_FLG"));
					reportbean.setDesItemOrderId(resultSetWord7.getInt("DES_ITEM_ORDER_ID"));

					wordData.add(reportbean);

				}
			}
			size5 = wordData.size();// 14

			// ele aux
			// ele aux
			if (callableStatement.getMoreResults()) {
				resultSetWord10 = callableStatement.getResultSet();

				while (resultSetWord10.next()) {

					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord10.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord10.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord10.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord10.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord10.getString("ITEM_CD"));
					reportbean.setSubItemId(resultSetWord10.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemCode(resultSetWord10.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord10.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeId(resultSetWord10.getInt("SUB_ITEM_TYP_ID"));
					reportbean.setSubItemTypeName(resultSetWord10.getString("SUB_ITEM_TYP_NM"));

					reportbean.setDesItemName(resultSetWord10.getString("DES_ITEM_NM"));
					reportbean.setDesSubItemName(resultSetWord10.getString("DES_SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord10.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord10.getString("COL_VAL_CD"));
					reportbean.setQuantityEle(resultSetWord10.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord10.getString("TECH_REMARKS"));
					// reportbean.setTechComments(resultSetWord10.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord10.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setOrderId(resultSetWord10.getInt("ORDER_ID"));
					reportbean.setDesSubItemOrderId(resultSetWord10.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setDesColOrderId(resultSetWord10.getString("TECH_DES_COL_ORDER_ID"));
					reportbean.setSubColValCode(resultSetWord10.getString("SUB_COL_VAL_CD"));
					reportbean.setHeaderText(resultSetWord10.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWord10.getString("FOOTER_TEXT"));
					reportbean.setLinkFlag(resultSetWord10.getInt("LINK_FLG"));
					reportbean.setDesItemOrderId(resultSetWord10.getInt("DES_ITEM_ORDER_ID"));
					wordData.add(reportbean);

				}
			}
			size8 = wordData.size();// 14

			// ele ext
			if (callableStatement.getMoreResults()) {
				resultSetWord11 = callableStatement.getResultSet();

				while (resultSetWord11.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord11.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord11.getString("SUB_CAT_NM"));
					// reportbean.setItemId(resultSetWord11.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord11.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord11.getString("ITEM_CD"));
					// reportbean.setSubItemCode(resultSetWord11.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord11.getString("SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord11.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord11.getString("COL_VAL_CD"));
					reportbean.setQuantityAux(resultSetWord11.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord11.getString("TECH_REMARKS"));
					reportbean.setSpaceF2f(resultSetWord11.getString("SPACE"));
					// reportbean.setTechComments(resultSetWord11.getString("TECH_COMMENTS"));
					// reportbean.setNumberOfSubItems(resultSetWord11.getInt("NO_OF_SUB_ITEMS"));

					wordData.add(reportbean);

				}
			}
			size9 = wordData.size();// 14

			// ci result set

			if (callableStatement.getMoreResults()) {
				resultSetWordCi = callableStatement.getResultSet();

				while (resultSetWordCi.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWordCi.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWordCi.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWordCi.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWordCi.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWordCi.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWordCi.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWordCi.getString("SUB_ITEM_NM"));
					reportbean.setDesItemName(resultSetWordCi.getString("DES_ITEM_NM"));
					reportbean.setDesSubItemName(resultSetWordCi.getString("DES_SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWordCi.getString("COL_NM"));
					reportbean.setColValCd(resultSetWordCi.getString("COL_VAL_CD"));
					reportbean.setQuantityEle(resultSetWordCi.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWordCi.getString("TECH_REMARKS"));
					// reportbean.setTechComments(resultSetWord7.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWordCi.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setOrderId(resultSetWordCi.getInt("ORDER_ID"));
					reportbean.setDesSubItemOrderId(resultSetWordCi.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setDesColOrderId(resultSetWordCi.getString("TECH_DES_COL_ORDER_ID"));
					reportbean.setSubColValCode(resultSetWordCi.getString("SUB_COL_VAL_CD"));
					reportbean.setHeaderText(resultSetWordCi.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWordCi.getString("FOOTER_TEXT"));
					reportbean.setLinkFlag(resultSetWordCi.getInt("LINK_FLG"));
					reportbean.setDesItemOrderId(resultSetWordCi.getInt("DES_ITEM_ORDER_ID"));
					wordData.add(reportbean);

				}
			}
			sizeCi = wordData.size();// 14

			// vms
			if (callableStatement.getMoreResults()) {
				resultSetWord9 = callableStatement.getResultSet();

				while (resultSetWord9.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setItemId(resultSetWord9.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord9.getString("ITEM_NM"));
					reportbean.setType(resultSetWord9.getString("TYPE"));
					reportbean.setAddPrbFlag(resultSetWord9.getInt("ADD_PRB_FLG"));
					reportbean.setOrderBy(resultSetWord9.getInt("ORDER_BY"));
					reportbean.setTagNum(resultSetWord9.getString("TAG_NO"));
					reportbean.setEquipment(resultSetWord9.getString("EQUIPMENT"));
					reportbean.setApplication(resultSetWord9.getString("APPLICATION"));
					reportbean.setLocation(resultSetWord9.getString("LOCATION"));
					reportbean.setQuantity(resultSetWord9.getInt("QTY"));
					reportbean.setCost2(Math.round(resultSetWord9.getFloat("COST")));
					reportbean.setNewColValFlag(resultSetWord9.getInt("NEW_COL_VAL_FLG"));
					reportbean.setItemCost(Math.round(resultSetWord9.getFloat("ITEM_COST")));
					reportbean.setHeaderText(resultSetWord9.getString("HEADER_TEXT"));
					reportbean.setNote(resultSetWord9.getString("NOTE"));
					wordData.add(reportbean);

				}
			}
			size7 = wordData.size();// 14

			// stg
			if (callableStatement.getMoreResults()) {
				resultSetWord8 = callableStatement.getResultSet();

				while (resultSetWord8.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord8.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord8.getString("SUB_CAT_NM"));
					reportbean.setItemNm(resultSetWord8.getString("ITEM_NM"));
					reportbean.setItemId(resultSetWord8.getInt("ITEM_ID"));
					reportbean.setId(resultSetWord8.getInt("ID"));

					reportbean.setInstrCode(resultSetWord8.getString("INSTR_CD"));
					reportbean.setInstrNm(resultSetWord8.getString("INSTR_NM"));
					reportbean.setInstrTypeNm(resultSetWord8.getString("INSTR_TYPE_NM"));
					reportbean.setInstrDesc(resultSetWord8.getString("INSTR_DESC"));
					reportbean.setInstrMounting(resultSetWord8.getString("INSTR_MOUNTING"));
					reportbean.setQuantityLogic(resultSetWord8.getInt("QTY_LOGIC"));
					reportbean.setCost1(resultSetWord8.getFloat("COST"));
					reportbean.setNoOfTable(resultSetWord8.getInt("NO_OF_TABLE"));
					reportbean.setHeaderText(resultSetWord8.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWord8.getString("FOOTER_TEXT"));
					reportbean.setItemId(resultSetWord8.getInt("ITEM_ID"));
					reportbean.setAddInstrId(resultSetWord8.getInt("ADD_INSTR_ID"));
					reportbean.setAddInstrCd(resultSetWord8.getString("ADD_INSTR_CD"));
					reportbean.setAddInstrNm(resultSetWord8.getString("ADD_INSTR_NM"));
					wordData.add(reportbean);

				}
			}
			size6 = wordData.size();// 14

			// save addinst

			if (callableStatement.getMoreResults()) {
				resultSetWord81 = callableStatement.getResultSet();

				while (resultSetWord81.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setDetQuotId(resultSetWord81.getInt("DET_QUOT_ID"));
					reportbean.setItemId(resultSetWord81.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord81.getString("ITEM_NM"));
					reportbean.setQuantity(resultSetWord81.getInt("QTY"));
					reportbean.setColId(resultSetWord81.getInt("COL_ID"));
					reportbean.setColNm(resultSetWord81.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord81.getString("COL_VAL_CD"));
					reportbean.setSubColValCode(resultSetWord81.getString("SUB_COL_VAL_CD"));
					reportbean.setAddOnNewColFlag(resultSetWord81.getInt("ADD_ON_NEW_COL_FLG"));
					reportbean.setRhsFlag(resultSetWord81.getInt("RHS_FLG") == 1 ? true : false);
					reportbean.setRhsColQuantity(resultSetWord81.getFloat("RHS_COL_QTY"));
					reportbean.setDesSubItemOrderId(resultSetWord81.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setRhsCost(Math.round(resultSetWord81.getFloat("RHS_COST")));
					reportbean.setAddInstrCost(Math.round(resultSetWord81.getFloat("ADD_INSTR_COST")));
					reportbean.setTotalAddInstrCost(Math.round(resultSetWord81.getFloat("TOTAL_ADD_INSTR_COST")));
					reportbean.setItemFlag(resultSetWord81.getInt("ITEM_FLG") == 1 ? true : false);
					reportbean.setOthersFlag(resultSetWord81.getInt("OTHERS_FLG") == 1 ? true : false);
					reportbean.setAddInstrFlag(resultSetWord81.getInt("ADD_INSTR_FLG") == 1 ? true : false);
					wordData.add(reportbean);

				}
			}
			size12 = wordData.size();// 14

			// ci aux
			if (callableStatement.getMoreResults()) {
				resultSetWord12 = callableStatement.getResultSet();

				while (resultSetWord12.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord12.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord12.getString("SUB_CAT_NM"));
					reportbean.setItemId(resultSetWord12.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord12.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord12.getString("ITEM_CD"));
					reportbean.setSubItemCode(resultSetWord12.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord12.getString("SUB_ITEM_NM"));
					reportbean.setDesItemName(resultSetWord12.getString("DES_ITEM_NM"));
					reportbean.setDesSubItemName(resultSetWord12.getString("DES_SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord12.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord12.getString("COL_VAL_CD"));
					reportbean.setQuantityEle(resultSetWord12.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord12.getString("TECH_REMARKS"));
					// reportbean.setTechComments(resultSetWord12.getString("TECH_COMMENTS"));
					reportbean.setNumberOfSubItems(resultSetWord12.getInt("NO_OF_SUB_ITEMS"));
					reportbean.setOrderId(resultSetWord12.getInt("ORDER_ID"));
					reportbean.setDesSubItemOrderId(resultSetWord12.getInt("DES_SUB_ITEM_ORDER_ID"));
					reportbean.setDesColOrderId(resultSetWord12.getString("TECH_DES_COL_ORDER_ID"));
					reportbean.setSubColValCode(resultSetWord12.getString("SUB_COL_VAL_CD"));
					reportbean.setHeaderText(resultSetWord12.getString("HEADER_TEXT"));
					reportbean.setFooterText(resultSetWord12.getString("FOOTER_TEXT"));
					// reportbean.setLinkFlag(resultSetWord12.getInt("LINK_FLG"));
					reportbean.setDesItemOrderId(resultSetWord12.getInt("DES_ITEM_ORDER_ID"));
					wordData.add(reportbean);

				}
			}
			size10 = wordData.size();// 14

			// ci ext
			if (callableStatement.getMoreResults()) {
				resultSetWord13 = callableStatement.getResultSet();

				while (resultSetWord13.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord13.getString("CAT_NM"));
					reportbean.setSubCategoryName(resultSetWord13.getString("SUB_CAT_NM"));
					// reportbean.setItemId(resultSetWord13.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetWord13.getString("ITEM_NM"));
					reportbean.setItemCode(resultSetWord13.getString("ITEM_CD"));
					// reportbean.setSubItemCode(resultSetWord13.getString("SUB_ITEM_CD"));
					reportbean.setSubItemName(resultSetWord13.getString("SUB_ITEM_NM"));
					reportbean.setColNm(resultSetWord13.getString("COL_NM"));
					reportbean.setColValCd(resultSetWord13.getString("COL_VAL_CD"));
					reportbean.setQuantityAux(resultSetWord13.getInt("QTY"));
					reportbean.setTechRemarks(resultSetWord13.getString("TECH_REMARKS"));
					reportbean.setSpaceF2f(resultSetWord13.getString("SPACE"));
					// reportbean.setTechComments(resultSetWord13.getString("TECH_COMMENTS"));
					// reportbean.setNumberOfSubItems(resultSetWord13.getInt("NO_OF_SUB_ITEMS"));

					wordData.add(reportbean);

				}
			}
			size11 = wordData.size();// 14

			// performance
			// List<DBOBean> itemList = new ArrayList<>();
			if (callableStatement.getMoreResults()) {
				resultSetWord21 = callableStatement.getResultSet();
				while (resultSetWord21.next()) {
					ReportBean reportbean = new ReportBean();
					// DBOBean bean = new DBOBean();

					reportbean.setCategoryName(resultSetWord21.getString("CAT_NM"));

					reportbean.setItemId(resultSetWord21.getInt("ITEM_ID"));
					logger.info(resultSetWord21.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord21.getString("ITEM_NM"));
					reportbean.setItemNewName(resultSetWord21.getString("ITEM_NM"));
					logger.info(resultSetWord21.getString("ITEM_NM"));
					// bean.setItemId(resultSetWord21.getInt("ITEM_ID"));
					// bean.setItemNm(resultSetWord21.getString("ITEM_NM"));
					//
					//
					wordData.add(reportbean);
					// itemList.add(bean);

				}
			}
			size13 = wordData.size();// 14

			for (int x1 = 0; x1 < wordData.size(); x1++) {
				logger.info("inside test for");
				logger.info(wordData.get(x1).getItemNewName());
				logger.info(x1);

			}

			if (callableStatement.getMoreResults()) {
				resultSetWord15 = callableStatement.getResultSet();
				logger.info(resultSetWord15.getFetchSize());
				while (resultSetWord15.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setQuotId(resultSetWord15.getInt("QUOT_ID"));
					reportbean.setItemId(resultSetWord15.getInt("ITEM_ID"));
					reportbean.setParamId(resultSetWord15.getInt("PARAM_ID"));
					reportbean.setParamNm(resultSetWord15.getString("PARAM_NM"));
					logger.info(resultSetWord15.getString("PARAM_NM"));
					reportbean.setUnitId(resultSetWord15.getInt("UNIT_ID"));
					reportbean.setUnitNm(resultSetWord15.getString("UNIT_NM"));
					reportbean.setGuaranteed(resultSetWord15.getString("GUARNTEED"));
					logger.info(resultSetWord15.getString("GUARNTEED"));

					reportbean.setCond1(resultSetWord15.getInt("COND1"));
					reportbean.setCond2(resultSetWord15.getInt("COND2"));
					reportbean.setCond3(resultSetWord15.getInt("COND3"));
					reportbean.setCond4(resultSetWord15.getInt("COND4"));
					reportbean.setCond5(resultSetWord15.getInt("COND5"));
					reportbean.setCond6(resultSetWord15.getInt("COND6"));
					reportbean.setCond7(resultSetWord15.getInt("COND7"));
					reportbean.setCond8(resultSetWord15.getInt("COND8"));
					reportbean.setCond9(resultSetWord15.getInt("COND9"));
					reportbean.setCond10(resultSetWord15.getInt("COND10"));
					reportbean.setNoOfCondition(resultSetWord15.getInt("NO_OF_CONDITIONS"));
					reportbean.setConditiontableinput(resultSetWord15.getInt("CONDTION_TABLE_INPUT"));
					reportbean.setNote(resultSetWord15.getString("NOTE"));

					wordData.add(reportbean);

				}
			}
			size14 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord14 = callableStatement.getResultSet();

				while (resultSetWord14.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setCategoryName(resultSetWord14.getString("CAT_NM"));
					reportbean.setItemId(resultSetWord14.getInt("ITEM_ID"));
					reportbean.setItemNm(resultSetWord14.getString("ITEM_NM"));
					reportbean.setHmbdTableInput(resultSetWord14.getInt("HMBD_TABLE_INPUT"));
					// reportbean.setNoOfCondition(resultSetWord14.getInt("NO_OF_CONDITIONS"));
					reportbean.setSubItemId(resultSetWord14.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemNm(resultSetWord14.getString("SUB_ITEM_NM"));
					reportbean.setConsumerId1(resultSetWord14.getString("CONSUMER_ID"));
					reportbean.setStartUp(resultSetWord14.getString("STARTUP"));
					reportbean.setContinuous(resultSetWord14.getString("CONTINUOUS"));
					reportbean.setColValCd(resultSetWord14.getString("COL_VAL_CD"));
					reportbean.setEditFlag(resultSetWord14.getInt("EDIT_FLG"));
					reportbean.setItemType(resultSetWord14.getString("ITEM_TYPE"));
					reportbean.setItemCd(resultSetWord14.getString("ITEM_CD"));
					reportbean.setSpeed(resultSetWord14.getString("SPEED"));
					reportbean.setVoltage(resultSetWord14.getString("VOLTAGE"));
					reportbean.setFeeder(resultSetWord14.getString("FEEDER"));
					reportbean.setTechRemarks(resultSetWord14.getString("TECH_REMARKS"));
					reportbean.setNote(resultSetWord14.getString("NOTE"));

					wordData.add(reportbean);

				}
			}
			size15 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord16 = callableStatement.getResultSet();

				while (resultSetWord16.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setItemId(resultSetWord16.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord16.getString("ITEM_NM"));
					reportbean.setFixedText1(resultSetWord16.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetWord16.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetWord16.getString("FIXED_TEXT3"));
					wordData.add(reportbean);

				}
			}
			size16 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord17 = callableStatement.getResultSet();

				while (resultSetWord17.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setItemId(resultSetWord17.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord17.getString("ITEM_NM"));
					reportbean.setIdentifier(resultSetWord17.getString("IDENTIFIER"));
					reportbean.setUnits(resultSetWord17.getString("UNIT"));
					reportbean.setRecmd(resultSetWord17.getString("RECMD"));
					reportbean.setLimit(resultSetWord17.getString("LIMIT"));
					wordData.add(reportbean);

				}
			}
			size17 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord18 = callableStatement.getResultSet();
				logger.info("size fo ");

				while (resultSetWord18.next()) {
					ReportBean reportbean = new ReportBean();
					reportbean.setItemId(resultSetWord18.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord18.getString("ITEM_NM"));
					reportbean.setColNm(resultSetWord18.getString("COL_NM"));
					reportbean.setConductivity(resultSetWord18.getString("CONDUCTIVITY"));
					reportbean.setContinuous(resultSetWord18.getString("CONTINUOUS"));
					reportbean.setStartUp(resultSetWord18.getString("START_UP"));

					wordData.add(reportbean);

				}
			}
			size18 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord19 = callableStatement.getResultSet();
				logger.info("result set 19");
				while (resultSetWord19.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setItemId(resultSetWord19.getInt("ITEM_ID"));
					// reportbean.setItemNm(resultSetWord19.getString("ITEM_NM"));
					reportbean.setParameter(resultSetWord19.getString("PARAMETERS"));
					reportbean.setType(resultSetWord19.getString("TYPE"));
					reportbean.setUnits(resultSetWord19.getString("UNITS"));
					reportbean.setCirWater(resultSetWord19.getString("CIR_WATER"));

					wordData.add(reportbean);

				}
			}
			size19 = wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord20 = callableStatement.getResultSet();
				logger.info("result set 20");
				while (resultSetWord20.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setCategoryName(resultSetWord20.getString("CAT_NM"));
					reportbean.setSeqNo(resultSetWord20.getInt("SEQ_NO"));
					reportbean.setSsId(resultSetWord20.getInt("SS_ID"));
					reportbean.setScopeCd(resultSetWord20.getString("SCOPE_CD"));
					reportbean.setScopeNm(resultSetWord20.getString("SCOPE_NM"));
					reportbean.setItemId(resultSetWord20.getInt("ITEM_ID"));
					reportbean.setItemNm(resultSetWord20.getString("ITEM_NM"));
					reportbean.setInformation(resultSetWord20.getString("INFORMATION"));
					reportbean.setFinalts(resultSetWord20.getString("FINAL"));
					reportbean.setSubScopeCd(resultSetWord20.getString("SUB_SCOPE_CD"));
					reportbean.setDescription(resultSetWord20.getString("DESCRIPTION"));
					reportbean.setEquipment(resultSetWord20.getString("EQUIPMENT"));
					reportbean.setTest(resultSetWord20.getString("TEST"));
					reportbean.setEquivalent(resultSetWord20.getString("EQUIVALENT"));
					reportbean.setPanelType(resultSetWord20.getString("PANEL_TYPE"));
					reportbean.setCustType(resultSetWord20.getString("CUST_TYPE"));
					reportbean.setQuanti(resultSetWord20.getString("QTY"));
					reportbean.setCost1(resultSetWord20.getInt("COST"));
					reportbean.setEditFlag(resultSetWord20.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetWord20.getInt("NEW_COL_VAL_FLG"));
					reportbean.setNewColValFlag(resultSetWord20.getInt("NO_OF_MANDAYS"));
					reportbean.setRemarks(resultSetWord20.getString("REMARKS"));
					reportbean.setNote(resultSetWord20.getString("NOTE"));

					wordData.add(reportbean);

				}
			}
			size20 = wordData.size();// 14

			// if (callableStatement.getMoreResults()) {
			// resultSetWord202 = callableStatement.getResultSet();
			// logger.info("result set 202");
			// while (resultSetWord202.next()) {
			// ReportBean reportbean = new ReportBean();
			//
			// reportbean.setTypeOfChargeId(resultSetWord202.getInt("TYP_OF_CHARGE"));
			// reportbean.setTypeOfChargeName(resultSetWord202.getString("TYP_OF_CHARGE_NAME"));
			// reportbean.setLoadingId(resultSetWord202.getInt("LOADING_ID"));
			// reportbean.setLoadingName(resultSetWord202.getString("LOADING_NAME"));
			// reportbean.setLoadgingId(resultSetWord202.getInt("LOADGING_ID"));
			// reportbean.setLoadgingName(resultSetWord202.getString("LOADGING_NAME"));
			// reportbean.setNumberOfMandays(resultSetWord202.getString("NO_OF_MANDAYS"));
			// reportbean.setRemarks(resultSetWord202.getString("REMARKS"));
			//
			// wordData.add(reportbean);
			//
			// }
			// }
			// size202= wordData.size();// 14

			if (callableStatement.getMoreResults()) {
				resultSetWord21 = callableStatement.getResultSet();
				logger.info("result set 21");
				while (resultSetWord21.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setItemId(resultSetWord21.getInt("ITEM_ID"));
					reportbean.setItemNm(resultSetWord21.getString("ITEM_NM"));
					reportbean.setItem(resultSetWord21.getString("ITEM"));
					reportbean.setFilepath(resultSetWord21.getString("FILE_PATH"));
					reportbean.setSsId(resultSetWord21.getInt("SS_ID"));
					reportbean.setScopeNm(resultSetWord21.getString("SCOPE_NM"));
					reportbean.setInformation(resultSetWord21.getString("INORMATION"));
					wordData.add(reportbean);

				}
			}
			size21 = wordData.size();// 14

			if (!wordData.isEmpty()) {
				reportBean.setWordData(wordData);
			}

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord1);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord2);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord3);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord4);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord5);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord6);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord7);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord8);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord9);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord10);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord11);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord12);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord13);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord14);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord15);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord16);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord17);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord18);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord19);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord20);
			UtilityMethods.closeResource(connection, callableStatement, resultSetWord21);

		}
		return reportBean;
	}
	
	// add on report data
		@Override
		public ReportBean getAddOnReportDataRev(QuotationForm quotationForm, ReportBean reportBean) {
			CallableStatement callableStatement = null;
			Connection connection = null;
			int resultOutParameterInt = -1;
			String resultOutParameterString = null;
			ResultSet resultSetMsg = null;
			ResultSet resultSetData1 = null;
			ResultSet resultSetData2 = null;

			List<ReportBean> addOnReportData = new ArrayList<>();
			DecimalFormat formatNum = new DecimalFormat("#,###");
			try {
				connection = jdbcTemplate.getDataSource().getConnection();

				callableStatement = connection.prepareCall("{ call dbo.PROC_REPORT_COST_SHEET_ADD_ON_REV(?,?) }");
				callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
				callableStatement.setInt(ItoConstants.REV_NO, quotationForm.getSaveBasicDetails().getRevNum());

				callableStatement.execute();

				resultSetMsg = callableStatement.getResultSet();

				while (resultSetMsg.next()) {
					resultOutParameterInt = resultSetMsg.getInt(1);
					resultOutParameterString = resultSetMsg.getString(2);
					reportBean.setSuccessCode(resultOutParameterInt);
					reportBean.setSuccessMsg(resultOutParameterString);

				}
				if (callableStatement.getMoreResults()) {
					resultSetData1 = callableStatement.getResultSet();

					while (resultSetData1.next()) {

						reportBean.setCustName(resultSetData1.getString("CUST_NAME"));
						reportBean.setOppName(resultSetData1.getString("OPP_NAME"));
						reportBean.setEndUserName(resultSetData1.getString("END_USER_NAME"));
						reportBean.setCustContactPersonName(resultSetData1.getString("CUST_CONT_PERSON"));
						reportBean.setType(resultSetData1.getString("TYPE"));
						reportBean.setFrameName(resultSetData1.getString("FRAME_NAME"));
						reportBean.setTurbGeneratorMw(resultSetData1.getString("TURB_GENERATOR_MW"));
						reportBean.setOrderDate(resultSetData1.getString("Order date / LOI date"));
						reportBean.setDelivery(resultSetData1.getString("Delievery / Commissioing"));
						reportBean.setQuotNum(resultSetData1.getString("QUOT_NUM"));
						reportBean.setQuotStatus(resultSetData1.getString("STATUS"));
						reportBean.setRevNum(resultSetData1.getString("REV_NO"));
						reportBean.setDate(resultSetData1.getString("DATE"));
						reportBean.setOpportunitySeqNum(resultSetData1.getString("OPP_SEQ_NO"));
					}

				}
				if (callableStatement.getMoreResults()) {
					resultSetData2 = callableStatement.getResultSet();

					while (resultSetData2.next()) {
						ReportBean bean = new ReportBean();
						bean.setSlNo(resultSetData2.getString("SL_NO"));
						bean.setScopeCode(resultSetData2.getString("SCOPE_CD"));
						bean.setSubGrpCd(resultSetData2.getString("SUB_GRP_CD"));
						bean.setItemId(resultSetData2.getInt("ITEM_ID"));
						bean.setSubItemId(resultSetData2.getInt("SUB_ITEM_ID"));
						bean.setSubItemTypeId(resultSetData2.getInt("SUB_ITEM_TYP_ID"));

						bean.setItemDesc(resultSetData2.getString("ITEM_DESC"));
						bean.setColId(resultSetData2.getInt("COL_ID"));
						bean.setColNm(resultSetData2.getString("COL_NM"));
						bean.setColValCd(resultSetData2.getString("COL_VAL_CD"));

						bean.setRhsComrComments(resultSetData2.getString("RHS_COMR_COMMENTS"));
						bean.setComrRemarks(resultSetData2.getString("COMR_REMARKS"));
						bean.setQuantity(resultSetData2.getInt("QTY"));
						bean.setRhsCostNew(resultSetData2.getString("RHS_COST"));

						bean.setItemCostNew(resultSetData2.getString("ITEM_COST"));
						bean.setBasicCostNew(resultSetData2.getString("BASIC_COST"));
						bean.setAddOnCostNew(resultSetData2.getString("ADD_ON_COST"));
						bean.setDiscountPer(formatNum.format(Math.round(resultSetData2.getFloat("DISCOUNT_PER"))));

						addOnReportData.add(bean);
					}

				}
				if (!addOnReportData.isEmpty()) {
					reportBean.setAddOnReportData(addOnReportData);
				}

			} catch (Exception e) {
				reportBean.setSuccessCode(-1);
				reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

				logger.error(ItoConstants.EXCEPTION, e);
			} finally {
				UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
				UtilityMethods.closeResource(connection, callableStatement, resultSetData1);
				UtilityMethods.closeResource(connection, callableStatement, resultSetData2);
			}
			return reportBean;
		}
	@Override
	public ReportBean getReportDataRev(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetData = null;
		ResultSet resultSetAllData = null;

		List<ReportBean> reportData = new ArrayList<>();
		DecimalFormat formatNum = new DecimalFormat("#,###");

		// Format format = java.text.NumberFormat.getCurrencyInstance(new
		// Locale("en", "in"));

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_REPORT_COST_SHEET_REV (?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.REV_NO, quotationForm.getSaveBasicDetails().getRevNum());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();

			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}
			if (callableStatement.getMoreResults()) {
				resultSetData = callableStatement.getResultSet();

				while (resultSetData.next()) {
					reportBean.setCustName(resultSetData.getString("CUST_NAME"));
					reportBean.setOppName(resultSetData.getString("OPP_NAME"));
					reportBean.setEndUserName(resultSetData.getString("END_USER_NAME"));
					reportBean.setCustContactPersonName(resultSetData.getString("CUST_CONT_PERSON"));
					reportBean.setType(resultSetData.getString("TYPE"));
					reportBean.setFrameName(resultSetData.getString("FRAME_NAME"));
					reportBean.setTurbGeneratorMw(resultSetData.getString("TURB_GENERATOR_MW"));
					reportBean.setOrderDate(resultSetData.getString("Order date / LOI date"));
					reportBean.setDelivery(resultSetData.getString("Delievery / Commissioing"));
					reportBean.setQuotNum(resultSetData.getString("QUOT_NUM"));
					reportBean.setQuotStatus(resultSetData.getString("STATUS"));
					reportBean.setRevNum(resultSetData.getString("REV_NO"));
					reportBean.setDate(resultSetData.getString("DATE"));
					reportBean.setOpportunitySeqNum(resultSetData.getString("OPP_SEQ_NO"));
				}

			}
			if (callableStatement.getMoreResults()) {
				resultSetAllData = callableStatement.getResultSet();

				while (resultSetAllData.next()) {
					ReportBean bean = new ReportBean();
					bean.setScopeCode(resultSetAllData.getString("SCOPE_CD"));
					bean.setItemName(resultSetAllData.getString("ITEM_NM"));
					bean.setQuantity(resultSetAllData.getInt("QTY"));
					bean.setCost(resultSetAllData.getString("COST"));

					bean.setOverwrittenPriceFlag(resultSetAllData.getInt("COST_ME_FLG") == 1 ? true : false);
					bean.setRemarks(resultSetAllData.getString("REMARKS"));
					reportData.add(bean);
				}

			}
			if (!reportData.isEmpty()) {
				reportBean.setReportData(reportData);
			}

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetData);
		}
		return reportBean;
	}

	
	@Override
	public ReportBean getComercialWordDataRev(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetSectionA = null;
		ResultSet resultSetSectionB = null;

		List<ReportBean> comercialWordData = new ArrayList<>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RPT_COMR_OFFER_REV (?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.REV_NO, quotationForm.getSaveBasicDetails().getRevNum());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}

			// section A
			if (callableStatement.getMoreResults()) {
				resultSetSectionA = callableStatement.getResultSet();

				while (resultSetSectionA.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setSectionCd(resultSetSectionA.getString("SECTION_CD"));
					reportbean.setItemId(resultSetSectionA.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetSectionA.getString("ITEM_NM"));
					reportbean.setSubItemId(resultSetSectionA.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemName(resultSetSectionA.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeCd(resultSetSectionA.getString("SUB_ITEM_TYPE_CD"));
					reportbean.setSubItemTypeName(resultSetSectionA.getString("SUB_ITEM_TYPE_NM"));
					reportbean.setFixedText1(resultSetSectionA.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetSectionA.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetSectionA.getString("FIXED_TEXT3"));
					reportbean.setEditFlag(resultSetSectionA.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetSectionA.getInt("NEW_COL_FLG"));
					reportbean.setNote(resultSetSectionA.getString("NOTE"));
					reportbean.setOfferType(resultSetSectionA.getString("OFFER_TYPE"));
					comercialWordData.add(reportbean);

				}

			}

			sizeA = comercialWordData.size();// 14

			// section B
			if (callableStatement.getMoreResults()) {
				resultSetSectionB = callableStatement.getResultSet();
				while (resultSetSectionB.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setSectionCd(resultSetSectionB.getString("SECTION_CD"));
					reportbean.setItemId(resultSetSectionB.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetSectionB.getString("ITEM_NM"));
					reportbean.setSubItemId(resultSetSectionB.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemName(resultSetSectionB.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeCd(resultSetSectionB.getString("SUB_ITEM_TYPE_CD"));
					reportbean.setSubItemTypeName(resultSetSectionB.getString("SUB_ITEM_TYPE_NM"));
					reportbean.setFixedText1(resultSetSectionB.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetSectionB.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetSectionB.getString("FIXED_TEXT3"));
					reportbean.setEditFlag(resultSetSectionB.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetSectionB.getInt("NEW_COL_FLG"));
					reportbean.setNote(resultSetSectionB.getString("NOTE"));
					reportbean.setOfferType(resultSetSectionB.getString("OFFER_TYPE"));
					comercialWordData.add(reportbean);

				}
			}
			sizeB = comercialWordData.size();// 14

			if (!comercialWordData.isEmpty()) {
				reportBean.setComercialWordData(comercialWordData);
			}

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSectionA);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSectionB);

		}
		return reportBean;
	}

	

	@Override
	public ReportBean getComercialWordData(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetSectionA = null;
		ResultSet resultSetSectionB = null;

		List<ReportBean> comercialWordData = new ArrayList<>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RPT_COMR_OFFER (?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}

			// section A
			if (callableStatement.getMoreResults()) {
				resultSetSectionA = callableStatement.getResultSet();

				while (resultSetSectionA.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setSectionCd(resultSetSectionA.getString("SECTION_CD"));
					reportbean.setItemId(resultSetSectionA.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetSectionA.getString("ITEM_NM"));
					reportbean.setSubItemId(resultSetSectionA.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemName(resultSetSectionA.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeCd(resultSetSectionA.getString("SUB_ITEM_TYPE_CD"));
					reportbean.setSubItemTypeName(resultSetSectionA.getString("SUB_ITEM_TYPE_NM"));
					reportbean.setFixedText1(resultSetSectionA.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetSectionA.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetSectionA.getString("FIXED_TEXT3"));
					reportbean.setEditFlag(resultSetSectionA.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetSectionA.getInt("NEW_COL_FLG"));
					reportbean.setNote(resultSetSectionA.getString("NOTE"));
					reportbean.setOfferType(resultSetSectionA.getString("OFFER_TYPE"));
					comercialWordData.add(reportbean);

				}

			}

			sizeA = comercialWordData.size();// 14

			// section B
			if (callableStatement.getMoreResults()) {
				resultSetSectionB = callableStatement.getResultSet();
				while (resultSetSectionB.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setSectionCd(resultSetSectionB.getString("SECTION_CD"));
					reportbean.setItemId(resultSetSectionB.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetSectionB.getString("ITEM_NM"));
					reportbean.setSubItemId(resultSetSectionB.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemName(resultSetSectionB.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeCd(resultSetSectionB.getString("SUB_ITEM_TYPE_CD"));
					reportbean.setSubItemTypeName(resultSetSectionB.getString("SUB_ITEM_TYPE_NM"));
					reportbean.setFixedText1(resultSetSectionB.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetSectionB.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetSectionB.getString("FIXED_TEXT3"));
					reportbean.setEditFlag(resultSetSectionB.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetSectionB.getInt("NEW_COL_FLG"));
					reportbean.setNote(resultSetSectionB.getString("NOTE"));
					reportbean.setOfferType(resultSetSectionB.getString("OFFER_TYPE"));
					comercialWordData.add(reportbean);

				}
			}
			sizeB = comercialWordData.size();// 14

			if (!comercialWordData.isEmpty()) {
				reportBean.setComercialWordData(comercialWordData);
			}

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSectionA);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSectionB);

		}
		return reportBean;
	}

	@Override
	public ReportBean getComercialWordDataNew(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetSectionA = null;
		ResultSet resultSetSectionB = null;

		List<ReportBean> comercialWordDataNew = new ArrayList<>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RPT_COMR_OFFER (?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());

			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}

			// section A
			if (callableStatement.getMoreResults()) {
				resultSetSectionA = callableStatement.getResultSet();

				while (resultSetSectionA.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setSectionCd(resultSetSectionA.getString("SECTION_CD"));
					reportbean.setItemId(resultSetSectionA.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetSectionA.getString("ITEM_NM"));
					reportbean.setSubItemId(resultSetSectionA.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemName(resultSetSectionA.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeCd(resultSetSectionA.getString("SUB_ITEM_TYPE_CD"));
					reportbean.setSubItemTypeName(resultSetSectionA.getString("SUB_ITEM_TYPE_NM"));
					reportbean.setFixedText1(resultSetSectionA.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetSectionA.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetSectionA.getString("FIXED_TEXT3"));
					reportbean.setEditFlag(resultSetSectionA.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetSectionA.getInt("NEW_COL_FLG"));
					reportbean.setNote(resultSetSectionA.getString("NOTE"));
					reportbean.setOfferType(resultSetSectionA.getString("OFFER_TYPE"));

					comercialWordDataNew.add(reportbean);

				}

			}

			sizeA = comercialWordDataNew.size();// 14

			// section B
			if (callableStatement.getMoreResults()) {
				resultSetSectionB = callableStatement.getResultSet();
				while (resultSetSectionB.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setSectionCd(resultSetSectionB.getString("SECTION_CD"));
					reportbean.setItemId(resultSetSectionB.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetSectionB.getString("ITEM_NM"));
					reportbean.setSubItemId(resultSetSectionB.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemName(resultSetSectionB.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeCd(resultSetSectionB.getString("SUB_ITEM_TYPE_CD"));
					reportbean.setSubItemTypeName(resultSetSectionB.getString("SUB_ITEM_TYPE_NM"));
					reportbean.setFixedText1(resultSetSectionB.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetSectionB.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetSectionB.getString("FIXED_TEXT3"));
					reportbean.setEditFlag(resultSetSectionB.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetSectionB.getInt("NEW_COL_FLG"));
					reportbean.setNote(resultSetSectionB.getString("NOTE"));
					reportbean.setOfferType(resultSetSectionB.getString("OFFER_TYPE"));

					comercialWordDataNew.add(reportbean);

				}
			}
			sizeB = comercialWordDataNew.size();// 14

			if (!comercialWordDataNew.isEmpty()) {
				reportBean.setComercialWordDataNew(comercialWordDataNew);
			}

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSectionA);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSectionB);

		}
		return reportBean;
	}
	
	@Override
	public ReportBean getComercialWordDataNewRev(QuotationForm quotationForm, ReportBean reportBean) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetSectionA = null;
		ResultSet resultSetSectionB = null;

		List<ReportBean> comercialWordDataNew = new ArrayList<>();

		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableStatement = connection.prepareCall("{ call dbo.PROC_RPT_COMR_OFFER_REV (?,?) }");

			callableStatement.setInt(ItoConstants.QUOT_ID, quotationForm.getSaveBasicDetails().getQuotId());
			callableStatement.setInt(ItoConstants.REV_NO, quotationForm.getSaveBasicDetails().getRevNum());
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				reportBean.setSuccessCode(resultOutParameterInt);
				reportBean.setSuccessMsg(resultOutParameterString);

			}

			// section A
			if (callableStatement.getMoreResults()) {
				resultSetSectionA = callableStatement.getResultSet();

				while (resultSetSectionA.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setSectionCd(resultSetSectionA.getString("SECTION_CD"));
					reportbean.setItemId(resultSetSectionA.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetSectionA.getString("ITEM_NM"));
					reportbean.setSubItemId(resultSetSectionA.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemName(resultSetSectionA.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeCd(resultSetSectionA.getString("SUB_ITEM_TYPE_CD"));
					reportbean.setSubItemTypeName(resultSetSectionA.getString("SUB_ITEM_TYPE_NM"));
					reportbean.setFixedText1(resultSetSectionA.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetSectionA.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetSectionA.getString("FIXED_TEXT3"));
					reportbean.setEditFlag(resultSetSectionA.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetSectionA.getInt("NEW_COL_FLG"));
					reportbean.setNote(resultSetSectionA.getString("NOTE"));
					reportbean.setOfferType(resultSetSectionA.getString("OFFER_TYPE"));

					comercialWordDataNew.add(reportbean);

				}

			}

			sizeA = comercialWordDataNew.size();// 14

			// section B
			if (callableStatement.getMoreResults()) {
				resultSetSectionB = callableStatement.getResultSet();
				while (resultSetSectionB.next()) {
					ReportBean reportbean = new ReportBean();

					reportbean.setSectionCd(resultSetSectionB.getString("SECTION_CD"));
					reportbean.setItemId(resultSetSectionB.getInt("ITEM_ID"));
					reportbean.setItemName(resultSetSectionB.getString("ITEM_NM"));
					reportbean.setSubItemId(resultSetSectionB.getInt("SUB_ITEM_ID"));
					reportbean.setSubItemName(resultSetSectionB.getString("SUB_ITEM_NM"));
					reportbean.setSubItemTypeCd(resultSetSectionB.getString("SUB_ITEM_TYPE_CD"));
					reportbean.setSubItemTypeName(resultSetSectionB.getString("SUB_ITEM_TYPE_NM"));
					reportbean.setFixedText1(resultSetSectionB.getString("FIXED_TEXT1"));
					reportbean.setFixedText2(resultSetSectionB.getString("FIXED_TEXT2"));
					reportbean.setFixedText3(resultSetSectionB.getString("FIXED_TEXT3"));
					reportbean.setEditFlag(resultSetSectionB.getInt("EDIT_FLG"));
					reportbean.setNewColValFlag(resultSetSectionB.getInt("NEW_COL_FLG"));
					reportbean.setNote(resultSetSectionB.getString("NOTE"));
					reportbean.setOfferType(resultSetSectionB.getString("OFFER_TYPE"));

					comercialWordDataNew.add(reportbean);

				}
			}
			sizeB = comercialWordDataNew.size();// 14

			if (!comercialWordDataNew.isEmpty()) {
				reportBean.setComercialWordDataNew(comercialWordDataNew);
			}

		} catch (Exception e) {
			reportBean.setSuccessCode(-1);
			reportBean.setSuccessMsg(TECHNICAL_EXCEPTION);

			logger.error(ItoConstants.EXCEPTION, e);
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSectionA);
			UtilityMethods.closeResource(connection, callableStatement, resultSetSectionB);

		}
		return reportBean;
	}


	@Override
	public QuotationForm getVarCostDet(Integer quotId) {

		QuotationForm quotationForm = new QuotationForm();
		quotationForm.getSaveBasicDetails().setQuotId(quotId);
		try {

			String query = "SELECT VR_ITEM_ID,QUOT_ID,INSURANCE,TRAVEL_EXPENSES,CONT_FOR_TURBINE,CONT_FOR_DBO,AGENCY_COMMISSION,SALES_EXPENSES,ENGIN_CHARGES,INTR_NO_OF_MONTHS,OTHR,OTHR_REMARKS,WR,IN_PROFIT_PER,IN_LD_DL,IN_LD_PER,IN_INSTR_PER,IN_BG_CHR1,IN_BG_CHR2,ORDER_VALVE,CONT_FOR_GENERAL,AGENCY_COMMISSION_CHRG,LD_PERORMANCE,LD_LA_DEL,BG_CHR1,BG_CHR2,OVR_SALE,OVR_RAW_MTRL_COST,WARREN,INTR_CHARG,TOTAL_COST,COST_ME_FLG,COST_ME FROM QUOT_VARIABLE_COST WHERE QUOT_ID ='"
					+ quotId + "';";

			List<OtherCostsBean> quotVariableCostsList = jdbcTemplate.query(query, new RowMapper<OtherCostsBean>() {
				public OtherCostsBean mapRow(ResultSet resultSetData10, int rowNum) throws SQLException {
					OtherCostsBean otherCostsBean = new OtherCostsBean();

					otherCostsBean.setOtherItemId(resultSetData10.getInt("VR_ITEM_ID"));
					otherCostsBean.setInsurance(resultSetData10.getFloat("INSURANCE"));
					otherCostsBean.setTravelExpenses(resultSetData10.getFloat(ItoConstants.TRAVEL_EXPENSES));
					otherCostsBean.setTurbineContigency(resultSetData10.getFloat(ItoConstants.CONT_FOR_TURBINE));
					otherCostsBean.setDboContigency(resultSetData10.getFloat(ItoConstants.CONT_FOR_DBO));
					otherCostsBean.setInpAgencyCommission(resultSetData10.getFloat("AGENCY_COMMISSION"));
					otherCostsBean.setSalesExpenses(resultSetData10.getFloat(ItoConstants.SALES_EXPENSES));
					otherCostsBean.setEngineCharges(resultSetData10.getFloat(ItoConstants.ENGIN_CHARGES));
					otherCostsBean.setIntrestNoOfMonths(resultSetData10.getFloat("INTR_NO_OF_MONTHS"));
					otherCostsBean.setOthers(resultSetData10.getFloat("OTHR"));
					otherCostsBean.setOthersRemarks(resultSetData10.getString("OTHR_REMARKS"));
					otherCostsBean.setWarrantyPeriod(resultSetData10.getFloat("WR"));
					otherCostsBean.setVarProfit(resultSetData10.getFloat("IN_PROFIT_PER"));
					otherCostsBean.setInpLdforLateDelivery(resultSetData10.getFloat("IN_LD_DL"));
					otherCostsBean.setInpLdPerformance(resultSetData10.getFloat("IN_LD_PER"));
					otherCostsBean.setIntrestPercentage(resultSetData10.getFloat("IN_INSTR_PER"));
					otherCostsBean.setInpBG1(resultSetData10.getFloat("IN_BG_CHR1"));
					otherCostsBean.setInpBG2(resultSetData10.getFloat("IN_BG_CHR2"));
					otherCostsBean.setTotOrderCost(resultSetData10.getFloat("ORDER_VALVE"));
					otherCostsBean.setContigencyGeneral(resultSetData10.getFloat(ItoConstants.CONT_FOR_GENERAL));
					otherCostsBean.setAgencyCommCharges(resultSetData10.getFloat("AGENCY_COMMISSION_CHRG"));
					otherCostsBean.setLdPerformance(resultSetData10.getFloat("LD_PERORMANCE"));
					otherCostsBean.setLdforLateDelivery(resultSetData10.getFloat("LD_LA_DEL"));
					otherCostsBean.setBankingCharges1(resultSetData10.getFloat("BG_CHR1"));
					otherCostsBean.setBankingCharges2(resultSetData10.getFloat("BG_CHR2"));
					otherCostsBean.setOvrheadSaleCost(resultSetData10.getFloat("OVR_SALE"));
					otherCostsBean.setOverRawMaterialCost(resultSetData10.getFloat("OVR_RAW_MTRL_COST"));
					otherCostsBean.setWarrantyCost(resultSetData10.getInt("WARREN"));
					otherCostsBean.setIntrestCharges(resultSetData10.getFloat("INTR_CHARG"));
					otherCostsBean.setTotalVariableCost(resultSetData10.getFloat(ItoConstants.TOTAL_COST));
					otherCostsBean.setVarNewFlag(resultSetData10.getInt(ItoConstants.COST_ME_FLG) == 1 ? true : false);
					otherCostsBean.setVarNewCost(Math.round(resultSetData10.getFloat(ItoConstants.COST_ME)));

					return otherCostsBean;
				}
			});

			quotationForm.setVariableCostList(quotVariableCostsList);

			logger.info("data " + quotVariableCostsList);

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
		}
		return quotationForm;

	}
	public void setEmailList(List<List> emailList){
		list = emailList;
		logger.info("setEmailList");
		//logger.info(list);
		logger.info(list.size());
	}
	public List<List> getEmailList(){
		logger.info("getEmailList");
		/*for (int i = 0; i<list.size();i++){
			logger.info("newtest");
			logger.info((int)list.get(i).get(0));
			logger.info((String)list.get(i).get(1));
			logger.info((String)list.get(i).get(2));
			logger.info("newtestend");
		}	*/
		return list;
	}
	@Override
	public QuotationForm getDocument() {
		QuotationForm quotationForm = new QuotationForm();
		try {

			String query = "SELECT ITEM_ID,ITEM_NM,ITEM,FILE_PATH FROM TEST..OFFER_IMAGE";
			
			List<QuotationForm> documentList = jdbcTemplate.query(query, new RowMapper<QuotationForm>() {
				public QuotationForm mapRow(ResultSet rs, int rowNum) throws SQLException {

					QuotationForm bean = new QuotationForm();
					bean.setItemId(rs.getInt("ITEM_ID"));
					bean.setItemNm(rs.getString("ITEM_NM"));
					if (!(rs.getBytes("ITEM") == null)) {
						String encoded = Base64.getEncoder().encodeToString(rs.getBytes("ITEM"));
						bean.setItem(encoded);
					}
					bean.setFilePath(rs.getString("FILE_PATH"));
					//bean.setFileName(rs.getString("FILE_NM"));
					logger.info("getDocument Start");
					logger.info("Item Id "+rs.getInt("ITEM_ID"));
				//	logger.info("Item "+Base64.getEncoder().encodeToString(rs.getBytes("ITEM")));
				//	logger.info("Item Name "+rs.getString("ITEM_NM"));
					logger.info("getDocument End");
					return bean;
				}
			});
			logger.info("123215");

			quotationForm.setDocumentlist(documentList);
			logger.info("12321");

			return quotationForm;

		} catch (Exception e) {
			quotationForm.setSuccessCode(-1);
			quotationForm.setSuccessMsg(TECHNICAL_EXCEPTION);
			logger.error("Exception :" + e);
			return quotationForm;
		}

	}


}
