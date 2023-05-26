<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Task</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Create a new task</h1>
		<form:form action="/create" method="post" modelAttribute="task">
			<div>
				<form:label path="taskName">Task: </form:label>
				<form:input path="taskName" class="form-control"/>
				<form:errors path="taskName" class="text-danger"/>
			</div>
			<div>
				<form:label path="assignee">Assignee: </form:label>
				<form:select path="assignee" class="form-select">
					<c:forEach items="${assignees}" var="assignee">
						<option value="${assignee.name}">${assignee.name}</option>
					</c:forEach>
				</form:select>
			</div>
			<div>
				<form:label path="priority">Priority: </form:label>
				<form:select path="priority" class="form-select">
					<option value="high">High</option>
					<option value="medium">Medium</option>
					<option value="low">Low</option>
				</form:select>
			</div>
			<form:hidden path="creator" value="${userInSession.id}"/>
			<input type="submit" class="btn btn-success mt-3" value="Create"/>
		</form:form>
	</div>
</body>
</html>
