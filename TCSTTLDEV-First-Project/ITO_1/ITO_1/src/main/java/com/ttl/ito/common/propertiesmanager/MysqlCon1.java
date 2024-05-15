package com.ttl.ito.common.propertiesmanager;

import java.awt.Image;
import java.io.*;
import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

public class MysqlCon1 {
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

			System.out.println(">> fetchToSQLXML: Get XML data as an SQLXML object " + "using getSQLXML");
			PreparedStatement selectStmt = null;
			String sqls = null, stringDoc = null;
			ResultSet rs = null;

			sqls = "SELECT AUDITENTRY FROM AUDITALL WHERE TABLENAME = 'DEPARTMENT'";
			selectStmt = connection.prepareStatement(sqls);
			rs = selectStmt.executeQuery();

			// Get metadata
			// Column type for XML column is the integer java.sql.Types.OTHER
			ResultSetMetaData meta = rs.getMetaData();
			int colType = meta.getColumnType(1);
			System.out.println("fetchToSQLXML: Column type = " + colType);
			while (rs.next()) {
				// Retrieve the XML data with getSQLXML.
				// Then write it to a string with
				// explicit internal ISO-10646-UCS-2 encoding.
				java.sql.SQLXML xml = rs.getSQLXML(1);
				System.out.println(xml.getString());
			}
			rs.close();
		} catch (SQLException sqle) {
			System.out.println("fetchToSQLXML: SQL Exception: " + sqle.getMessage());
			System.out.println("fetchToSQLXML: SQL State: " + sqle.getSQLState());
			System.out.println("fetchToSQLXML: SQL Error Code: " + sqle.getErrorCode());
		}
	}
}
