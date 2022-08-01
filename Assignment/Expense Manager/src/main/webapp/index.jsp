<%@page import="com.bjit.training.dao.ConnectionFactory"%>
<%@ page language="java" contentType="text/html charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bjit.training.constant.ConstantContainer"%>
<%@page import="java.util.List"%>

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
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 20px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 60%;
}

input[type=submit]:hover {
  background-color: #45a049;
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>

<head>
<title><%=ConstantContainer.webTitle.toString()%></title>

</head>
<body>
<div>
	<h2>
		<b> Expense Insertion Form </b>
	</h2>
<form action="ExpenseController" method="post">
 	<table>
		<tr> 
			<td> Expense Name: </td>
			<td> <input type=text name= "eName"> </td>
		</tr>
		<tr> 
			<td> Expense amount: </td>
			<td> <input type=text name= "eAmount"> </td>
		</tr>
		<tr> 
			<td> Expense description: </td>
			<td> <input type=box name= "eDescription"></td>
		</tr>
		<tr> 
			<td> Expense category: </td>
			<td> 
				<select name="category">
  					<option value="Food">Foods</option>
					<option value="Bill">Bill</option>
					<option value="Fees">fees</option>
					<option value="Transport">Transport</option>
				</select>
			
			</td>
		<tr> 
			<td> Expense date: </td>
			<td> <input type=date name= "eDate"></td>
		</tr>	
		<tr> 
			<td> <input type=submit name= "Submit"></td>
		</tr>	
	</table>
 </form>
 </div>
</body>
<footer> </footer>
</html>