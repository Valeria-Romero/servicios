<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${taskName}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Task: ${task.taskName}</h1>
		<h3>Creator: ${task.creator.name}</h3>
		<h3>Assignee: ${task.assignee}</h3>
		<h3>Priority: ${task.priority}</h3>
		<div>
			<a href="/edit/${task.id}" class="btn btn-warning">Edit</a>
			
		</div>
	</div>
</body>
</html>