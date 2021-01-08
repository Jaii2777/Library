<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="Objects.Books"%>

<html>
	<%
		ArrayList<Books> listOfBooks=(ArrayList) request.getAttribute("bookList");
		String userName=(String)request.getAttribute("userName");
		if(listOfBooks.size()==0)
		{
			out.println("This user dont have any books");
			return;
		}
	%>
<body>
	<%if(listOfBooks.size()!=0) {%>
	<h1><%=request.getAttribute("userName")%> Books Data</h1>
	<p><%=request.getAttribute("mail")%></p>
	<table>
		<tr>
			<th>BookId</th>
			<th>Book Name</th>
			<th>Author Name</th>
			<th>Year</th>
		</tr>
		<%
			int i = 1;
		for (Books k : listOfBooks) {
		%>
		<tr>
			<td><%=k.getId()%></td>
			<td><%=k.getName()%></td>
			<td><%=k.getAuthor()%></td>
			<td><%=k.getYear()%></td>
		</tr>
		<%}%>
	</table>
	<%} %>
			
</body>
</html>