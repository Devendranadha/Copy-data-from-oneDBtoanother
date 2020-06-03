package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BatchProcess {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "tiger");

			System.out.println("db1 connected");

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "root", "tiger");

			System.out.println("db2 connected");

			Statement stmt1 = conn1.createStatement();

			ResultSet rsFrom1 = stmt1.executeQuery("SELECT  * from registration");

			PreparedStatement insertTo2 = conn2.prepareStatement(

					"INSERT INTO registration (id, pid,firstname,lastname,age)" + " VALUES ( ?, ?, ?, ?, ?)"
							+ "ON DUPLICATE KEY UPDATE id=id");
			int count = 0;
			while (rsFrom1.next()) {
				count++;
				insertTo2.setString(1, rsFrom1.getString("id"));
				insertTo2.setString(2, rsFrom1.getString("pid"));
				insertTo2.setString(3, rsFrom1.getString("firstname"));
				insertTo2.setString(4, rsFrom1.getString("lastname"));
				insertTo2.setString(5, rsFrom1.getString("age"));

				insertTo2.executeUpdate();
			}

			conn1.close();

		}

		catch (Exception e) {

			System.out.println(e);
		}

	}

}
