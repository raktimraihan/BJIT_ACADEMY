package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDao {
	// JDBC driver name and database URL
	// For oracle: jdbc:oracle:thin:@localhost:1521:XE
	// For mysql: jdbc:mysql://localhost:3306/testdb

	static final String DB_URL = "jdbc:mysql://localhost:3306/students";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	static {
		// STEP 2: Register JDBC driver
		try { // For oracle: "oracle.jdbc.driver.OracleDriver"
			Class.forName("jdbc:mysql://localhost:3306/students");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Connection getDatabaseConnection() {
		//System.out.println("Connection Methods!");
		Connection conn = null;
		try {
			System.out.println("Connecting to the  database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected!");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		return conn;
	}

	public void createTables() {
		System.out.println("creating table....");
		String sql = "";
		sql = "drop table exam";
		execUpdateSQL(sql);
		sql = "CREATE TABLE exam (" + " exam_id int NOT NULL AUTO_INCREMENT,  " + " exam_name VARCHAR(255), "
				+ " total_question int(11), " + " valid_from date, " + "valid_to date," + "primary key (exam_id)" + ")";
		execUpdateSQL(sql);
	}
	
	public void createCourseTable() {
		String sql = "";
		sql = "drop table course";
		execUpdateSQL(sql);
		sql = "CREATE TABLE course (" + " course_id int NOT NULL AUTO_INCREMENT,  " + " course_name VARCHAR(255), "
				+ " total_hours int(3), primary key (course_id)" + ")";
		execUpdateSQL(sql);
	}
	
	public void insertData() { // date format i have changed from '10/09/2013' to '2013/05/11'
		String sql = "";
		sql = "INSERT INTO exam (exam_name,total_question,valid_from,valid_to) VALUES ( 'OOP', 10,'2013/05/11', '2013/10/01')";
		execUpdateSQL(sql);

		sql = "INSERT INTO exam ( exam_name,total_question,valid_from,valid_to) VALUES ( 'OperatingSystem',10, '2013/05/11', '2013/10/01')";
		execUpdateSQL(sql);
	}

	public void readAllData() {
		Connection conn = null;
		Statement stmt = null;
		String sql = "SELECT * FROM exam";
		try {
			System.out.println("------------ begin readAllData ------------------");
			System.out.println("SQL Query being executed:" + sql);
			conn = getDatabaseConnection();
			System.out.println("Executing SQL.....");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 4: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("exam_id: " + rs.getInt("exam_id"));
				System.out.print(", exam_name: " + rs.getString("exam_name"));
				System.out.print(", total_question: " + rs.getInt("total_question"));
				System.out.print(", valid_from: " + rs.getString("valid_from"));
				System.out.println(", valid_to: " + rs.getString("valid_to"));
				System.out.println();
			}

			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("------------ end readAllData -------");
	}

	public void updateData() {
		String sql = "UPDATE exam SET total_question = 30 WHERE exam_id in (1,2)";
		execUpdateSQL(sql);
	}

	public void deleteData() {
		String sql = "DELETE from Exam WHERE exam_id in (1,2)";
		execUpdateSQL(sql);
	}

	public int execUpdateSQL(String sql) {
		System.out.println("---------------begin execUpdateSQL--------------");
		Connection conn = null;
		Statement stmt = null;
		int numOfRowsUpdated = 0;
		try {
			System.out.println("SQL Query being executed:" + sql);

			conn = getDatabaseConnection();
			// STEP 4: Execute a query
			System.out.println("Executing SQL.....");
			stmt = conn.createStatement();
			numOfRowsUpdated = stmt.executeUpdate(sql);
			System.out.println("SQL Execution has been successfull.");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("------end execUpdateSQL--------------");
		return numOfRowsUpdated;
	}

}
