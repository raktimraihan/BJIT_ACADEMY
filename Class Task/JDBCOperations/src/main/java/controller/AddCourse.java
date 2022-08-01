package controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JDBCDao;
import model.Course;

/**
 * Servlet implementation class AddCourse
 */
@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cName=request.getParameter("coursename");
		Integer tHours=Integer.parseInt(request.getParameter("totalhours"));
		String enrollStatus = request.getParameter("status");
		
		Course course=new Course(cName,tHours,enrollStatus);
		JDBCDao jdbcDao = new JDBCDao();
		jdbcDao.createCourseTable();
		String result = jdbcDao.insertCourse(course);
		//System.out.println(result);
		response.getWriter().println(result);
		response.sendRedirect(request.getContextPath() + "/coursetable.jsp");
	}

}
