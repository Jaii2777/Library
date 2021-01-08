<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="Objects.GroupRequest"%>

<!DOCTYPE html>
<html>
<%
ArrayList<GroupRequest> listOfGroups = (ArrayList) request.getAttribute("reqInfo");
{
	if(listOfGroups==null)
	{
		response.sendRedirect("AcceptServlet");
		return;
	}
}
%>
<body>
	<%= listOfGroups.size()%>
	<h2>Search Result:</h2>
	<form action="AcceptServlet" method="post">
	<table>
		<tr>
			<th>SNo</th>
			<th>GroupId</th>
			<th>UserId</th>
			<th>UserName</th>
		</tr>
		<%
			//int i = 1;
		for ( int i=0;i<1;i++) {
			GroupRequest k=(GroupRequest)listOfGroups.get(i);
		%>
		<tr>
			out.println(<%=k.getGroupId()%>);
			out.println(<%=k.getReqUserId()%>);
			out.println(<%=k.getRequesterName()%>);
			<td><%=i%></td>
			<td><%=k.getGroupId()%></td>
			<td><%=k.getReqUserId()%></td>
			<td><%=k.getRequesterName()%></td>
			<td><input type="hidden" name="groupId" value="<%=k.getGroupId()%>"/></td>
			<td><input type="hidden" name="userId" value="<%=k.getReqUserId()%>"/></td>
			<td><input type="hidden" name="userName" value="<%=k.getRequesterName()%>"/></td>
			<td><button type="submit">Accept</button></td>
			<td><button type="submit" formaction="RejectServlet">Reject</button>
		<%}%>
	</table>
	</form>
</body>
</html>
