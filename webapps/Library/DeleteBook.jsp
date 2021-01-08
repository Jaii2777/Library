<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="Objects.Books" %>
<!DOCTYPE html>
<html>
<%
	ArrayList<Books> listOfBooks = (ArrayList) request.getAttribute("listOfBooks");
	if (listOfBooks == null) {
	response.sendRedirect("deletebook");
	return;
}
%>
<body>
	<center>
	<h1>Delete Book </h1><br>
	<form action="deletebook" method="post">
	<label>Select book to remove:</label>
	<select name="id">
	<option value="" disabled selected>Choose a book:</option>
	<%
		for(Books b:listOfBooks){
			%>
			<option value="<%=b.getId()%>"><%=b.getName()%></option>
		<%}%>
	</select>
	<button>Delete</button>
	</form>
	</center>
	</body>
</html>