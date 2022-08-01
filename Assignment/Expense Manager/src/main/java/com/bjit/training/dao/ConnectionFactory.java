package com.bjit.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import javax.crypto.spec.PSource;

import com.bjit.training.model.ExpenseModel;
import com.mysql.jdbc.Driver;

public class ConnectionFactory {
	
	String driver = "com.mysql.jdbc.Driver";
	String dbUrl = "jdbc:mysql://localhost:3306/expensedb";
	String dbName = "ExpenseManager";
	String dbUser = "root";
	String dbPass = "";
	String tableName = "Expense_Manager";
	
	public void loadDriver() throws ClassNotFoundException {
		Class.forName(driver);
		System.out.println("Connection Succesfully");
	}
	
	public void createTable(String tableName) throws SQLException, ClassNotFoundException {
		this.tableName = tableName;
		loadDriver();
		try {
			System.out.println("Table Creating...");
			Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			String sql = "CREATE TABLE IF NOT EXISTS expense("
					+ "id int NOT NULL AUTO_INCREMENT,"
					+ "name VARCHAR(20) NOT NULL,"
					+ "dExpense DATE,"
					+ "amount DOUBLE(10,2),"
					+ "description varchar(255),"
					+ "category varchar(255),"
					+ "PRIMARY KEY(id))";
			PreparedStatement pStatement = conn.prepareStatement(sql);
			pStatement.executeUpdate();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertData(ExpenseModel eModel) throws ClassNotFoundException, SQLException {
		loadDriver();
		Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		String sql = "INSERT INTO expense (name,dExpense,amount,description,category) "
				+ "VALUES (?, ?, ?, ?, ?)";
		String dataString = new SimpleDateFormat("yyyy-MM-dd").format(eModel.getdExpense());
		System.out.println(dataString);
		PreparedStatement pStatement = conn.prepareStatement(sql);
		pStatement.setString(1, eModel.getName());
		pStatement.setString(2, dataString);
		pStatement.setDouble(3, eModel.getAmount());
		pStatement.setString(4, eModel.getDescriptiopn());
		pStatement.setString(5, eModel.getCategory());
		pStatement.executeUpdate();
	}
	
	public ResultSet getAllExpenses() throws ClassNotFoundException, SQLException {
		loadDriver();
		Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPass);
		String sql = "SELECT * FROM expense ORDER BY dExpense ASC";
		PreparedStatement pStatement = conn.prepareStatement(sql);
		ResultSet resultSet = pStatement.executeQuery();
		
		return resultSet;
	}
	
}
