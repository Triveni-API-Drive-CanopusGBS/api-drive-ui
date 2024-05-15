package com.ttl.ito.common.propertiesmanager;


import com.google.gson.*;
import com.ttl.ito.business.beans.AddOnComponent;
import com.ttl.ito.business.beans.F2FForm;
import com.ttl.ito.business.beans.QuestionsEntity;
import com.ttl.ito.business.beans.QuotationForm;
import com.ttl.ito.business.beans.TurbineDetails;
import com.ttl.ito.business.beans.UserProfileDetails;
import com.ttl.ito.common.Utility.UtilityMethods;
import com.ttl.ito.internal.beans.ItoConstants;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class ParentChildApp {
	public static void main(String[] args) {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultsetScope = null;
		ResultSet resultSetUser = null;
		ResultSet resultSetRoles = null;
		FileInputStream inputStream = null;
		QuotationForm quotationForm = new QuotationForm();
		ResultSet resultSetF2F = null;
		List<F2FForm> f2FDataList=null;
		ResultSet resultSetData1 = null;
		ResultSet resultSetPackage = null;
		List<UserProfileDetails> userDetailsList = new ArrayList<>();
	
		ResultSet resultSetSS = null;
		
		ResultSet rs = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String userName = "sa";
			String password = "dev@123";
			String url = "jdbc:sqlserver://TTLITODEV\\TTLDEVDB:1433;databaseName=TEST";
			connection = DriverManager.getConnection(url, userName, password);
			
			System.out.println("DB Connection started");

			
							//QuotationForm quotationForm= new QuotationForm();
				
					Map<String, List<TurbineDetails>> packageList = new HashMap<>();

		Integer framePowerId=5;
			String query = " SELECT DISTINCT SCOPE_CD FROM TABLE_SCOPE";
					Statement stmt = null;
					stmt = connection.createStatement();
					rs = stmt.executeQuery(query);

					List<QuestionsEntity> packageArray = new ArrayList<>();
					while (rs.next()) {
					
						QuestionsEntity questionsEntity = new QuestionsEntity();
	                	questionsEntity.setQuestionDesc((rs.getString(ItoConstants.QUST_DESC)));
	                	questionsEntity.setQuestionCode(rs.getString("QUST_CD"));
	                	questionsEntity.setAnswerDesc(rs.getString("ANSWER"));
	                	questionsEntity.setAnswerCd(rs.getString("ANS_CD"));
	                	questionsEntity.setDefaultVal((rs.getString(ItoConstants.DEFLT_VALUE)=="TRUE" ?true:false));
	                	questionsEntity.setFrameName(rs.getString(ItoConstants.FRAME_NAME));
						
	                	packageArray.add(questionsEntity);
					}
					
					
					System.out.println("data " +packageArray.size()+"   "+ packageArray);
					/*stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						quotationForm.getSaveBasicDetails().getRevList().add(rs.getInt("REV_NO"));
					}*/
			System.out.println(quotationForm);
			
} catch (Exception e) {
	e.printStackTrace();
} finally {
	//UtilityMethods.closeResource(connection, callableStatement, resultSetMsg);

}
	}	
	
}