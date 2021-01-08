<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<%
	ArrayList adminAction = (ArrayList)request.getAttribute("adminActionsList");
	ArrayList adminOperations = (ArrayList)request.getAttribute("adminOperationList");
	if(adminAction==null){
		response.sendRedirect("adminOperations");
		return;
	  }
%>
<body>
	<center>
		<h1>Welcome <%=request.getUserPrincipal().getName()%><h1><br/>
		<%
			for(int i=0;i<adminOperations.size();i++)
			{%>
				<a href="<%=adminAction.get(i)%>"><button><%= adminOperations.get(i)%></button>
				<br/><br/>
			<%}
		%>
		</center>
		<center><a href="LogOut.jsp">LogOut</a></center>
	</body>
<html>