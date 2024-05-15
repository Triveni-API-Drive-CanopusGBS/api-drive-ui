package com.ttl.ito.common.propertiesmanager;

import java.awt.Image;
import java.io.*;
import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

public class MysqlCon2 {
	public static void main(String[] args) {

		SQLServerPreparedStatement callableStatement = null;
		Connection connection = null;
		int resultOutParameterInt = -1;
		String resultOutParameterString = null;
		ResultSet resultSetMsg = null;
		ResultSet resultSetTurbineQues = null;
		try {

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String userName = "sa";
			String password = "dev@123";
			String url = "jdbc:sqlserver://TTLITODEV\\TTLDEVDB:1433;databaseName=TEST";
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("DB Connection started");

			
			PreparedStatement selectStmt = null;
			String Sql = null, stringDoc = null;
			ResultSet rs = null;
			
//			Sql = "SELECT CREATE_DT,MOD_BY FROM AUDITALL WHERE TABLENAME ='DEPARTMENT'";
//			Sql = "SELECT CREATE_DT,MOD_BY FROM AUDITALL WHERE TABLENAME ='DEPARTMENT'";
Sql = "SELECT DISTINCT SCOPE_CD FROM TABLE_SCOPE";
			selectStmt = connection.prepareStatement(Sql);
			rs = selectStmt.executeQuery();

			while (rs.next()) {
//				System.out.println("   " + rs.getString("CREATE_DT"));
//				System.out.println("   " + rs.getInt("MOD_BY"));
			System.out.println("   " + rs.getString("SCOPE_CD"));
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}