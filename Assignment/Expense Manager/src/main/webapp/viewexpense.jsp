<%@page import="java.sql.ResultSet"%>
<%@page import="com.bjit.training.dao.ConnectionFactory"%>
<%@page import="com.bjit.training.constant.ConstantContainer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"    
%>
<html>
<style>
html, body {
    height: 100%;
}

html {
    display: table;
    margin: auto;
}

body {
    display: table-cell;
    vertical-align: middle;
}
#expenses {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#expenses td, #expenses th {
  border: 1px solid #ddd;
  padding: 8px;
}

#expenses tr:nth-child(even){background-color: #f2f2f2;}

#expenses tr:hover {background-color: #ddd;}

#expenses th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}

</style>
<head>
	<title> <%=ConstantContainer.weTitleforView.toString()%> </title>
</head>
<body>
	<%
		ConnectionFactory cFactory = new ConnectionFactory();
		ResultSet resultSet = cFactory.getAllExpenses();
	%>
	<table style="border: 1px solid black; border-collapse: collapse;" id= "expenses">
		<tr style="border: 1px solid black; border-collapse: collapse;">
			<td style="border: 1px solid black; border-collapse: collapse;"> Expense Name </td>
			<td style="border: 1px solid black; border-collapse: collapse;"> Date of Expense </td>
			<td style="border: 1px solid black; border-collapse: collapse;"> Amount Spent </td>
			<td style="border: 1px solid black; border-collapse: collapse;"> Description </td>
			<td style="border: 1px solid black; border-collapse: collapse;"> Category </td>
		</tr>
		
		<% 
			while(resultSet.next()){ 
		%>
			<tr style="border: 1px solid black; border-collapse: collapse;">
				<td style="border: 1px solid black; border-collapse: collapse;"><%=resultSet.getString("name") %></td>
				<td style="border: 1px solid black; border-collapse: collapse;"><%=resultSet.getString("dExpense") %></td>
				<td style="border: 1px solid black; border-collapse: collapse;"><%=resultSet.getString("amount") %></td>
				<td style="border: 1px solid black; border-collapse: collapse;"><%=resultSet.getString("description") %></td>
				<td style="border: 1px solid black; border-collapse: collapse;"><%=resultSet.getString("category") %></td>
			</tr>
		<%
			}
		%>
	</table>
</body>

<footer>
</footer>

</html>