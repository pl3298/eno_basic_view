<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<%@ page import="com.cbp.api.models.*" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	if(request.getAttribute("error") != null) { 
		out.println("<span class=\"error\">" + request.getAttribute("error") + "</span>");		
	}
%>

<button><a href="trainer/create">Create Trainer</a></button>
<button><a href="training/create">Create Training</a></button>

<button style="float:right;"><a href="logout">Log Off</a></button>

<br><br>

<table border="2px" style="font-size: 20px">
	<thead>
		<tr>
			<td>Name</td>
			<td>Duration</td>
			<td>Topic</td>
			<td>Trainer</td>
			<td>Modify</td>
			<td>Delete</td>
		</tr>
	</thead>
	<tbody>
	
	<%
	if(request.getAttribute("trainings") != null) {
	ArrayList<Training> trainings = (ArrayList<Training>)request.getAttribute("trainings");
	for(Training t : trainings) {
		String trainer = "";
		if(t.getTrainer() != null) {
			trainer = t.getTrainer().getName();
		}
	%>
		<tr>
			<td><%=t.getName() %></td>
			<td><%=t.getDuration() %></td>
			<td><%=t.getTopic() %></td>
			<td><%=trainer%></td>
			<td><a href="training/modify?name=<%=t.getName()%>">Modify</a></td>
			<td><a href="training/delete?name=<%=t.getName()%>">Delete</a></td>
		</tr>
	<%} 
	}%>
	</tbody>
</table>

</body>
</html>