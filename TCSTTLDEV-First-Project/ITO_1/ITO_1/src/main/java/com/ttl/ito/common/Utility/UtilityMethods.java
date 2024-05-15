package com.ttl.ito.common.Utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;

public class UtilityMethods {

	private static Logger logger = Logger.getLogger(UtilityMethods.class);
	protected Map<String, Set<String>> errorMap = new HashMap<String, Set<String>>();

	public static final String INFO = "INFO";
	public static final String WARN = "WARN";
	public static final String ERROR = "ERROR";
	public static final String CONFIRM = "CONFIRM";
	public static final String EXCEPTION = "EXCEPTION";

	public static void closeResource(Connection conn, CallableStatement ca, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}

			if (ca != null) {
				ca.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("SQLException : ", e);
		}
	}
	
	public static void closeResource(Connection conn, Statement stmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}

			if (stmt != null) {
				stmt.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("SQLException : ", e);
		}
	}
	
	public static void closeResource(ResultSet rs) {

		try {

			if (rs != null) {
				rs.close();
			}

		} catch (SQLException e) {
			logger.error("SQLException : ", e);
		}
	}
	public static void closeResource(CallableStatement ca,ResultSet rs) {

		try {

			if (ca != null) {
				ca.close();
			}

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error("SQLException : ", e);
		}
	}
	
	public static void closeResource(Connection conn, CallableStatement ca) {

		try {

			if (ca != null) {
				ca.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("SQLException : ", e);
		}
	}
	
	public static void closeResource(Connection conn, SQLServerPreparedStatement ca, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}

			if (ca != null) {
				ca.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("SQLException : ", e);
		}
	}

	public void addMessage(String meessageType, String message) {
		if (null != getErrorMap()) {
			setErrorMap(new HashMap<String, Set<String>>());
		}

		if (!getErrorMap().containsKey(meessageType)) {
			getErrorMap().put(meessageType, new HashSet<String>());
		}

		getErrorMap().get(meessageType).add(message);
	}

	public void clearMessgae() {
		if (null != getErrorMap()) {
			getErrorMap().clear();
		}
	}

	public void handleMessage(String errTpe, String screenMessage, String loggerMessgae) {
		if (errTpe.equalsIgnoreCase(ERROR)) {
			logger.error(screenMessage);
			logger.error(loggerMessgae);
		} else if (errTpe.equalsIgnoreCase(WARN)) {
			logger.warn(loggerMessgae);
		} else if (errTpe.equalsIgnoreCase(INFO)) {
			logger.info(loggerMessgae);
		}
		addMessage(errTpe, screenMessage);
	}

	public Map<String, Set<String>> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, Set<String>> errorMap) {
		this.errorMap = errorMap;
	}
}
