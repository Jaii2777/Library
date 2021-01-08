<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
	<%
		ArrayList usersList=(ArrayList) request.getAttribute("usersList");
		if(usersList==null)
		{
			response.sendRedirect("ShowUsersServlet");
			return;
		}
	%>
<body>
	<h1>Users</h1>
		<table>
		<tr>
			<th>User Names</th>
			<th>Action</th>
		</tr>
		<%
			for(int i=0;i<usersList.size();i++)
			{
			%>
				<tr>
					<td><%=usersList.get(i)%></td>
					<td><a href="ShowUsersServlet?userName=<%=usersList.get(i)%>">View</a><td>
				</tr>
			<%}
		%>
		</table>
</body>
</html>