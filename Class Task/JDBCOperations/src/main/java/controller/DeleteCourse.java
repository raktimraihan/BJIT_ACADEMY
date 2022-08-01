package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JDBCDao;

@WebServlet("/DeleteCourse")
public class DeleteCourse extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String courseID = req.getParameter("course_id");
		System.out.println("Course ID: "+courseID);
		JDBCDao jdbcDao = new JDBCDao();
		jdbcDao.deleteCourse(courseID);
		resp.sendRedirect(req.getContextPath() + "/coursetable.jsp");
	}
	
	

}
