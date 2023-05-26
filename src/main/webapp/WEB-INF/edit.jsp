<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Task</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Edit ${task.taskName}</h1>
		<form:form action="/update" method="post" modelAttribute="task">
			<div>
				<form:label path="taskName">Task: </form:label>
				<form:input path="taskName" class="form-control"/>
				<form:errors path="taskName" class="text-danger"/>
			</div>
			<div>
				<form:label path="assignee">Assignee: </form:label>
				<form:input path="assignee" class="form-control"/>
				<form:errors path="assignee" class="text-danger"/>
			</div>
			<div>
				<form:label path="priority">Priority: </form:label>
				<form:input path="priority" class="form-control"/>
				<form:errors path="priority" class="text-danger"/>
			</div>
			<form:hidden path="creator" value="${userInSession.id}"/>
			
			<input type="hidden" name="_method" value="put"/>
			
			<form:hidden path="id" value="${task.id}"/>
			
			<input type="submit" class="btn btn-success mt-3" value="Create"/>
		</form:form>
	</div>
</body>
</html>
