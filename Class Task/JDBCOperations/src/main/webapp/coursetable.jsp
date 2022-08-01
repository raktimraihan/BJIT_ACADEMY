<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"    
    %>
<%@page import="java.sql.ResultSet"%>
<%@page import="dao.JDBCDao"%>
<%
	dao.JDBCDao jdbcDao=new dao.JDBCDao();
	ResultSet resultSet=jdbcDao.getCourse();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head> 
<body>
	<table>
		<tr>

		</tr>
		<tr>
			<td><b>Course ID</b></td>
			<td><b>Course Name</b></td>
			<td><b>Total Hour</b></td>
			<td><b>Enrollment Status</b></td>
		</tr>
		<%

			while (resultSet.next()) {
		%>
		<tr>
			<td><%=resultSet.getString("course_id")%></td>
			<td><%=resultSet.getString("course_name")%></td>
			<td><%=resultSet.getString("total_hours")%></td>
			<td><%=resultSet.getString("status")%></td>


		</tr>

		<%
		}
		%>
	</table>
	<br> <br>
	
	<form action="DeleteCourse" method="post">
		<table>
			<tr> Enter a Course ID to Delete: </tr>
			<tr> <input type=number name="course_id"> </tr>
			<tr> <input type=submit value="delete"> </tr>
		</table>
	</form>
	
</body>
</html>