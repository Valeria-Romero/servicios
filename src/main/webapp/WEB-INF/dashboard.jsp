<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Manager</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
		<nav class="d-flex justify-content-between aling-items-center">
			<h1>Welcome, ${userInSession.name}!</h1>
			<div>
				<a href="/order/high">Priority High - Low</a>
				<a href="/order/low">Priority Low - High</a>
			</div>
			<a href="/logout" class="btn btn-danger">Logout</a>
		</nav>
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Task</th>
						<th>Creator</th>
						<th>Assignee</th>
						<th>Priority</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${taskPriority}" var="t">
						<tr>
							<td><a href="/view/${t.id}">${t.taskName}</a></td>
							<td>${t.creator.name}</td>
							<td>${t.assignee}</td>
							<td>${t.priority}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<a href="/new" class="btn btn-success">Create Task</a>
	</div>
</body>
</html>
