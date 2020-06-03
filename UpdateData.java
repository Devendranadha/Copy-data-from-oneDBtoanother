package com.test;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class UpdateData {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://localhost/test";
	static final String DB_URL2 = "jdbc:mysql://localhost/test1";

	private static final Logger logger = Logger.getLogger("DataUpdate log");

	static final String USER = "root";
	static final String PASS = "tiger";

	public static void main(String[] args) {
		Connection conn = null;
		Connection conn1 = null;
		Statement stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to a selected database...");
			conn = (Connection) DriverManager.getConnection(DB_URL1, USER, PASS);
			conn1 = (Connection) DriverManager.getConnection(DB_URL2, USER, PASS);
			stmt = (Statement) conn.createStatement();
			String sql = "INSERT INTO Registration " + "VALUES (100, 'Zara', 'Ali', 18)";
			stmt.executeUpdate(sql);
			stmt = (Statement) conn1.createStatement();
			stmt.executeUpdate(sql);
			logger.log(Level.INFO, "Testingn");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
				conn1.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}