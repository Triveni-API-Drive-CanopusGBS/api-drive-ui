//general example




package com.ttl.ito.common.propertiesmanager;

import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

import java.io.*;

public class image {

	
	        public static void main(String[] args) 
	        {
	                DB db = new DB();
	                Connection conn=db.dbConnect(
	                  "jdbc:sqlserver://TTLITODEV\\TTLDEVDB:1433;databaseName=TEST","sa","dev@123");
	                db.insertImage(conn,"C:\\Users\\Public\\Pictures\\Sample Pictures\\TTL.doc");
	                db.getImageData(conn);
	        }
	 
	}

class DB {
	public DB() {
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

	public void insertImage(Connection conn, String img) {
		int len;
		String query;
		PreparedStatement pstmt;

		try {
			File file = new File(img);
			FileInputStream fis = new FileInputStream(file);
			len = (int) file.length();

			query = ("INSERT INTO TABLEIMAGE VALUES(?,?,?)");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, file.getName());
			pstmt.setInt(2, len);

			pstmt.setBinaryStream(3, fis, len);
			
			pstmt.executeUpdate();
			System.out.println("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getImageData(Connection conn) {

		byte[] fileBytes;
		String query;
		try {
			query = "SELECT IMAGE FROM TABLEIMAGE" ;
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			if (rs.next()) {
				fileBytes = rs.getBytes(1);
				OutputStream targetFile = new FileOutputStream("E://Megha//new.doc");
				

				targetFile.write(fileBytes);
				System.out.println("Downloded");
				targetFile.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
};