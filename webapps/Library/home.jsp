<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<title>Home</title>
<%
		
		String user = (String)request.getUserPrincipal().getName();
		if(user==null)
		{
			response.sendRedirect("LogOut.jsp");
			return;
		}
		

%>
<%
	ArrayList operations = (ArrayList) request.getAttribute("AllowedOperations");
	ArrayList fileList = (ArrayList) request.getAttribute("fileList");
	if(operations==null){
		//response.sendRedirect("homeServlet");
		//return;
	}
%>
<body>
	<center><h1>Welcome <%=user%>!</h1></center><br><br>
	<%
	for(int count = 0; count < operations.size(); count++) {%>
	
	<center><a href=<%=fileList.get(count) %>> <button><%=operations.get(count)%></button> </a></center><br><br>
	<%}
	%>
	<span color"red"><%= request.getAttribute("mailStatus")%></span>
	<center><a href="LogOut.jsp">LogOut</a></center>
</body>
</html>