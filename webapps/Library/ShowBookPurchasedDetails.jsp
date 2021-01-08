<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="Objects.BookPurchased"%>
<!DOCTYPE html>
	<%
		ArrayList<BookPurchased> booksInfo=(ArrayList) request.getAttribute("countOfBookInfo");
		if(booksInfo==null){
			response.sendRedirect("BookPurchasedInfoServlet");
			return;
		}
	%>
<html>
	<body>
		<table>
		<tr>
		<th>Book Name</th>
		<th>Purchased Count</th>
		<th>Show Users</th>
		</tr>
		<%
			for(BookPurchased m:booksInfo){
				%>
				<tr>
				<td><%=m.getName()%></td>
				<td><%=m.getCount()%></td>
				<td><a href="BookPurchasedInfoServlet?bookId=<%=m.getId()%>">click here</a></td>
				</tr>
			<%}
			
		%>
		</table>
	</body>
</html>