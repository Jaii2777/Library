<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="Objects.Books" %>
<!DOCTYPE html>
<html>
<%
	ArrayList<Books> listOfBooks = (ArrayList) request.getAttribute("listOfBooks");
	if (listOfBooks == null) {
	response.sendRedirect("showBookServlet");
	return;
}
%>
<body>
	<h1>All Books</h1><br>
	<table>
	<tr>
	<th>BookId</th>
	<th>Book Name</th>
	<th>Author Name</th>
	<th>Book Year</th>
	<th>NumberOfBooks</th>
	</tr>
	<%
		for(Books b:listOfBooks){
			%>
			<tr>
			<td><%=b.getId()%></td>
			<td><%=b.getName()%></td>
			<td><%=b.getAuthor()%></td>
			<td><%=b.getYear()%></td>
			<td><%=b.getBookCount()%></td>
		<tr/>
		<%}%>
	</table>
	</body>
</html>