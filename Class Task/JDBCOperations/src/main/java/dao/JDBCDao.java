package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Course;

public class JDBCDao {
	// JDBC driver name and database URL
	// For oracle: jdbc:oracle:thin:@localhost:1521:XE
	// For mysql: jdbc:mysql://localhost:3306/testdb

	static final String DB_URL = "jdbc:mysql://localhost:3306/course";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	static final String JDBCDRIVER = "com.mysql.jdbc.Driver";

	public void loadDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getDatabaseConnection() {
		loadDriver(JDBCDRIVER);
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
	
	public void createCourseTable() {
		Connection con = getDatabaseConnection();
		String sql = "";
		sql = "CREATE TABLE IF NOT EXISTS course (" + " course_id int NOT NULL AUTO_INCREMENT," + " course_name VARCHAR(255), "
				+ "total_hours int(3)," + "status VARCHAR(255),"+"primary key (course_id))";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String insertCourse(Course course) {
		Connection con = getDatabaseConnection();
		String sql = "";
		sql = "INSERT INTO course (course_name,total_hours,status) VALUES (?,?,?)";
		String result="Data Entered Successfully";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, course.getName());
			ps.setInt(2, course.getTotalHour());
			ps.setString(3, course.getEnrollStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result="Data Not Entered Successfully";
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet getCourse() {
		Connection con = getDatabaseConnection();
		String sql = "";
		sql = "SELECT * FROM course";
		ResultSet result=null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			result=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=null;
			e.printStackTrace();
		}
		return result;
	}
	
	public void deleteCourse(String id) {
		Connection con = getDatabaseConnection();
		String sql = "DELETE FROM course WHERE course_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(id));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
