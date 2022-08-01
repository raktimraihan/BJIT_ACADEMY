<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Course</title>
</head>
<body>
	<form action="AddCourse" method="post">
		<table>
			<tr>
				<td>Course Name</td>
				<td><input type="text" name="coursename"></td>
			</tr>
			<tr>
				<td>Total Hours</td>
				<td><input type="number" name="totalhours"></td>
			</tr>
			<tr>
				<td>Enroll Status</td>
				<td><input type="text" name="status"></td>
			</tr>
			<tr>
				<td>Submit</td>
				<td><input type="submit" value="register"></td>
			</tr>
		</table>
	</form>
</body>
</html>