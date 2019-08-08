<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.cbp.api.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Select Trainer</title>
</head>
<body>
<%
	if(request.getAttribute("error") != null) { 
		out.println("<span class=\"error\">" + request.getAttribute("error") + "</span>");		
	}
%>

<button style="float:right;"><a href="dashboard">Dashboard</a></button>

<br><br>
<form method="get" action="training/<%=request.getAttribute("action")%>">
<table border="2px" style="font-size: 20px">
	<thead>
		<tr>
			<td>Select</td>
			<td>Name</td>
			<td>Description</td>
			<td>Modify</td>
			<td>Delete</td>
		</tr>
	</thead>
	<tbody>
	
	<%
	if(request.getAttribute("trainers") != null) {
	ArrayList<Trainer> trainers = (ArrayList<Trainer>)request.getAttribute("trainers");
	for(Trainer t : trainers) { %>
		<tr>
			<td><input type="radio" name="trainer" value="<%=t.getName() %>" ></td>
			<td><%=t.getName() %></td>
			<td><%=t.getDescription() %></td>
			<td><a href="#">Modify</a></td>
			<td><button>Delete</button></td>
		</tr>
	<%} 
	}%>
	
	</tbody>
</table>
<input type="hidden" name="name" value="<%=request.getAttribute("name")%>">
<input type="submit">
</form>
</body>
</html>