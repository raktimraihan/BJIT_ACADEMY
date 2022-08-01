package com.bjit.training.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjit.training.dao.ConnectionFactory;
import com.bjit.training.model.ExpenseModel;

@WebServlet("/ExpenseController")
public class ExpenseController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("eName");
		String eDateString = req.getParameter("eDate");
		String category = req.getParameter("category");
		Double amount = Double.parseDouble(req.getParameter("eAmount"));
		String descriptoon = req.getParameter("eDescription");
		
		ConnectionFactory cFactory = new ConnectionFactory();
		try {
			
			cFactory.createTable("Expense_Maneger");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			Date eDate =  new SimpleDateFormat("yyyy-MM-dd").parse(eDateString);
			ExpenseModel eModel = new ExpenseModel(name, eDate, amount, descriptoon, category);
			cFactory.createTable("Expense");
			cFactory.insertData(eModel);
			resp.sendRedirect(req.getContextPath()+"/viewexpense.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
