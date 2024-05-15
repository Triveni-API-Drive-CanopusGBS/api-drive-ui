package com.ttl.ito.common.propertiesmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.ttl.ito.business.beans.AddOnComponent;
import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.CProjectList;
import com.ttl.ito.business.beans.ErectionCommissionBean;
import com.ttl.ito.business.beans.F2FForm;
import com.ttl.ito.business.beans.F2FUBOBean;
import com.ttl.ito.business.beans.PackageBean;
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
import com.ttl.ito.common.Utility.UtilityMethods;
import com.ttl.ito.internal.beans.ItoConstants;
import com.ttl.ito.internal.beans.LoginBO;

import sun.misc.BASE64Decoder;

public class MysqlConTestSaveBasicDetails {
	public static void main(String args[]) {
		/*
		 * CallableStatement callableStatement = null; Connection connection =
		 * null; int resultOutParameterInt = -1; String resultOutParameterString
		 * = null; ResultSet resultSetMsg = null; ResultSet resultSetUserDet =
		 * null; ResultSet resultSetUser= null; ResultSet resultSetRoles = null;
		 * FileInputStream inputStream = null;
		 */
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;

		ResultSet resultSetaddOns = null;

		ResultSet resultSetNewTypesOfTurbine = null;
		ResultSet resultSetTypesOfTurbine = null;
		ResultSet resultSetQuot = null;
		ResultSet resultVarCost = null;
		ResultSet resultSetQuestions = null;
		ResultSet resultSetData = null;
		ResultSet resultSetAddOns = null;
		ResultSet resultSetData1 = null;
		ResultSet resultProjectCost = null;
		ResultSet resultSetQuotHomeGrid = null;
		ResultSet resultSetAllData = null;
		ResultSet resultSetAddon = null;
		List<ScopeOfSupply> scopeList = new ArrayList<>();
		HashMap<String, TurbineDetails> costmap = new HashMap<>();
		F2FForm f2FForm = new F2FForm();
		List<TurbineDetails> scopeList1 = new ArrayList<TurbineDetails>();
		List<ErectionCommissionBean> ecDataList = new ArrayList<ErectionCommissionBean>();
		List<TurbineDetails> originalFramePowerList = new ArrayList<TurbineDetails>();
		List<TurbineDetails> newFramePowerList = new ArrayList<TurbineDetails>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String userName = "sa";
			String password = "dev@123";
			String url = "jdbc:sqlserver://TTLITODEV\\TTLDEVDB:1433;databaseName=dba";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("DB Connection started");
			ReportBean reportBean = new ReportBean();

			List<String> regionsListStr = new ArrayList<>();
			List<QuotationHomeGrid> quotationHomeGridList = new ArrayList<>();
			QuotationForm quotationForm= new QuotationForm(); 
callableStatement = connection.prepareCall("{ call dbo.[PROC_GET_ADDON_PRICE] (?) }");

			
			callableStatement.setInt(ItoConstants.FRM_POW_ID, 1);
			
			callableStatement.execute();
			resultSetAddon = callableStatement.getResultSet();
			List<AddOnComponent> addOnComponentsListTemp = new ArrayList<>();
					while (resultSetAddon.next()) {
						AddOnComponent addOn = new AddOnComponent();
						addOn.setCompoId(resultSetAddon.getInt(ItoConstants.COMP_ID));
						addOn.setAddOnCompoId(resultSetAddon.getInt(ItoConstants.ADD_ON_COMP_ID));
						addOn.setAddOnCompoName(resultSetAddon.getString("ADDONNAME"));
						addOn.setAddOnCompo_cd(resultSetAddon.getString("ADDON"));
						addOn.setSubtype1(resultSetAddon.getString("SUB_TYPE1_NM"));
						addOn.setSubtype2(resultSetAddon.getString("SUB_TYPE2_NM"));
						addOn.setMake(resultSetAddon.getString("MAKE_NM"));
						addOn.setSubtype1Code(resultSetAddon.getString(ItoConstants.SUB_TYPE1));
						addOn.setSubtype2Code(resultSetAddon.getString(ItoConstants.SUB_TYPE2));
						addOn.setMakeCode(resultSetAddon.getString("MAKE"));
						addOn.setQuantity(resultSetAddon.getInt("QTY"));
						addOn.setQuantityName(resultSetAddon.getString("QTY_NM"));
						addOn.setExcelCost(Math.round(resultSetAddon.getFloat(ItoConstants.COST)));
						addOn.setSubtype1Id(resultSetAddon.getInt("SUB_TYPE1_ID"));
						addOn.setSubtype2Id(resultSetAddon.getInt("SUB_TYPE2_ID"));
						addOn.setMakeId(resultSetAddon.getInt("MAKE_ID"));
						addOn.setSubtype1Id(resultSetAddon.getInt("SUB_TYPE1_ID"));
						addOn.setSubtype2Id(resultSetAddon.getInt("SUB_TYPE2_ID"));
						addOn.setMakeId(resultSetAddon.getInt("MAKE_ID"));
						addOn.setDefaultCostFlag(resultSetAddon.getInt("F2F_DEFULT_FLG")==1 ?true:false);
						
						addOnComponentsListTemp.add(addOn);
						
					}
					System.out.println(addOnComponentsListTemp);
		} catch (Exception e) {//2.1091498E7 
			e.printStackTrace();//21091498.00
			System.out.println(e); //21041348

		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		}

	}

}
