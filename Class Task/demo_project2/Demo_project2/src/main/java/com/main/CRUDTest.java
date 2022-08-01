package com.main;

import com.jdbc.JDBCDao;

public class CRUDTest {

	public static void main(String[] args) {

		JDBCDao dao = new JDBCDao();
		 //check the DB connection
		 dao.getDatabaseConnection();
		 dao.createTables();
		 dao.createCourseTable();
		 dao.insertData();
//		 dao.readAllData();
//		 dao.updateData();
//		 dao.deleteData();

	}

}
