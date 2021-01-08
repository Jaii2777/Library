<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="Objects.BookPurchased"%>
<!DOCTYPE html>
	<%
		ArrayList userList=(ArrayList) request.getAttribute("userList");
	%>
<html>
	<body>
		<%
		for(int i=0;i<userList.size();i++){
			%>
			<p><%=userList.get(i)%></p>
		<%}
		%>
	</body>
</html>