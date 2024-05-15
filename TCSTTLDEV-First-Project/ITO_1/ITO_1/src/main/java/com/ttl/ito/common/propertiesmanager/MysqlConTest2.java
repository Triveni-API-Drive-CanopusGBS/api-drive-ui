package com.ttl.ito.common.propertiesmanager;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.Encoder.BinaryStream;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.ttl.ito.business.beans.AddOnComponent;
import com.ttl.ito.business.beans.AdminForm;
import com.ttl.ito.business.beans.CProjectList;
import com.ttl.ito.business.beans.ConsultantDetails;
import com.ttl.ito.business.beans.CustomerDetails;
import com.ttl.ito.business.beans.CustomerProfileForm;
import com.ttl.ito.business.beans.EndUserDetails;
import com.ttl.ito.business.beans.Entry;
import com.ttl.ito.business.beans.F2FForm;
import com.ttl.ito.business.beans.F2FUBOBean;
import com.ttl.ito.business.beans.QuestionsEntity;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.SaveBasicDetails;
import com.ttl.ito.business.beans.SaveQuesDetails;
import com.ttl.ito.business.beans.ScopeOfSupply;
import com.ttl.ito.business.beans.SelectBox;
import com.ttl.ito.business.beans.TreeObject;
import com.ttl.ito.business.beans.TurbineAnswers;
import com.ttl.ito.business.beans.TurbineDetails;
import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.common.Utility.UtilityMethods;

/**
 * @author SAP_User1
 *
 */
public class MysqlConTest2 {
	public static void main(String args[]) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetTurbineQues = null;

		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String userName = "sa";
			String password = "dev@123";
			String url = "jdbc:sqlserver://TTLITODEV\\TTLDEVDB:1433;databaseName=TEST";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("DB Connection started");
		
			//connection = jdbcTemplate.getDataSource().getConnection();
			SQLServerDataTable QUOT_UPD = new SQLServerDataTable();
			
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
			CONFIG.addColumnMetadata("COL_VAL_NM", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("ME_COL_VAL", java.sql.Types.REAL);
			CONFIG.addColumnMetadata("QTY", java.sql.Types.REAL);
			CONFIG.addColumnMetadata("COST", java.sql.Types.REAL);
			CONFIG.addColumnMetadata("COMMENTS", java.sql.Types.VARCHAR);
			CONFIG.addColumnMetadata("TECH_FLG", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("COMR_FLG", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("OTHR_COL_FLG", java.sql.Types.NUMERIC);
			CONFIG.addColumnMetadata("OTHR_COL_VAL_FLG", java.sql.Types.NUMERIC);
		
			CONFIG.addRow(24,17,null,0,null,0,null,1,null,"Hydraulically operated with integral steam strainer for inlet",null,0.0,0.0,0.0,null,1,1,0,0); 

			
			callableStatement = (SQLServerPreparedStatement) connection.prepareStatement("{ call dbo.PROC_GET_F2F_TECH (?,?,?,?)}");
			callableStatement.setStructured(1, "dbo.CONFIG ", CONFIG);
			
			callableStatement.setString(2, "DM"); // CUST_TYP Dm
			callableStatement.setFloat(3, 1);//1,24
			callableStatement.setInt(4, 1);//1 steam turbine model
			callableStatement.execute();
			resultSetMsg = callableStatement.getResultSet();
			System.out.println(resultSetMsg);
			while (resultSetMsg.next()) {
				resultOutParameterInt = resultSetMsg.getInt(1);
				resultOutParameterString = resultSetMsg.getString(2);
				
			}


			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
		
		}
		

	}
	
	public static SQLServerDataTable getF2FQuesTable(AdminForm adminForm) {
		String methodName = "getF2FQuesTable(AdminForm adminForm)";

		
		
		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSetMsg = null;

		try {
			String userName = "sa";
			String password = "dev@123";
			String url = "jdbc:sqlserver://TTLITODEV\\TTLDEVDB:1433;databaseName=dba";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("DB Connection started");
			
			SQLServerDataTable F2F_QUST_ANS = new SQLServerDataTable();
			F2F_QUST_ANS.addColumnMetadata("FRM_POW_ID", java.sql.Types.NUMERIC);
			F2F_QUST_ANS.addColumnMetadata("QUST_ID", java.sql.Types.NUMERIC);
			F2F_QUST_ANS.addColumnMetadata("QUST_NM",java.sql.Types.VARCHAR);
			F2F_QUST_ANS.addColumnMetadata("QUST_CD", java.sql.Types.VARCHAR);
			F2F_QUST_ANS.addColumnMetadata("ANS_ID", java.sql.Types.NUMERIC);
			F2F_QUST_ANS.addColumnMetadata("ANS_NM", java.sql.Types.VARCHAR);
			F2F_QUST_ANS.addColumnMetadata("ANS_CD", java.sql.Types.VARCHAR);
			F2F_QUST_ANS.addColumnMetadata("DEFLT_FLG", java.sql.Types.NUMERIC);
			F2F_QUST_ANS.addColumnMetadata("IS_ACTIVE_QUST", java.sql.Types.NUMERIC); // make active always
			F2F_QUST_ANS.addColumnMetadata("IS_ACTIVE_ANS", java.sql.Types.NUMERIC); // make active always
			
			//INSERT INTO @F2F_QUST_ANS VALUES (29,0,'QUST','QT',0,'ANS3','A3',0,1,1);
			System.out.println("code4444 ");
			//F2F_QUST_ANS.addRow(13, 0,"testQ1","TQ",0,"testA1","TA",1,1,1);  - new Ques
			F2F_QUST_ANS.addRow(13, 2,"Type of start","TS",0,"testAnswer11","A6",0,1,1); 
				
			/*if( adminForm.getAnswersList().size()>0){
				System.out.println("code ");
			for(TurbineAnswers ansList : adminForm.getAnswersList()){
				for(Integer framesList : ansList.getFramePowerList()){
					System.out.println("code111 ");
				F2F_QUST_ANS.addRow(framesList, ansList.getQuesKey(),ansList.getQuesDesc(),ansList.getQuesCode(),ansList.getAnsKey(),
						ansList.getAnsDesc(),ansList.getAnsCode(),(ansList.isDefaultFlag() ? 1 : 0),1,1);
				System.out.println("code222 ");
			
			}
			}
			}*/
			
			return F2F_QUST_ANS;
		} catch (Exception e) {
			adminForm.getMsgToUser().put(-1, "error");
			
			return null;
		} finally {
			UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);
			
		}
		
		
	}
	
}
