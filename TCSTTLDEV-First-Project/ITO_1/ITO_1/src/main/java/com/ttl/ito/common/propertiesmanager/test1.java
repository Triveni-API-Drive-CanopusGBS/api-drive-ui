//3 inputs


package com.ttl.ito.common.propertiesmanager;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

import java.io.*;

import java.sql.Connection;

public class test1 {

	public static void main(String[] args) {
		DBA db = new DBA();
		Connection conn = db.dbConnect("jdbc:sqlserver://TTLITODEV\\TTLDEVDB:1433;databaseName=QA", "sa", "dev@123");
		db.insertImage(conn, "C:\\Users\\Public\\Pictures\\Sample Pictures\\TTL.doc");

	}

}

class DBA {
	public DBA() {
	}

	public Connection dbConnect(String db_connect_string, String db_userid, String db_password) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);

			System.out.println("connected");
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertImage(Connection conn, String img) {

		int len;

		SQLServerPreparedStatement callableStatement = null;

		try {
			File file = new File(img);
			FileInputStream fis = new FileInputStream(file);
			len = (int) file.length();

//			conn = jdbcTemplate.getDataSource().getConnection();

			callableStatement = (SQLServerPreparedStatement) conn.prepareCall("{ call dbo.PROC_CREATE_UTILITIES1(?,?,?) }");

			callableStatement.setString(1, file.getName());

			callableStatement.setInt(2, len);

			callableStatement.setBinaryStream(3, fis, len);

			callableStatement.execute();

			System.out.println("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
};
