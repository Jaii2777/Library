<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="Objects.Books"%>
<!DOCTYPE html>

<html>
<%
		ArrayList<Books> listOfBooks=(ArrayList) request.getAttribute("listOfBooks");
		if(listOfBooks==null)
		{
			response.sendRedirect("ModifyBookServlet");
			return;
		}
%>
	<body>
	<h1>Modify data</h1>
		<form action="ModifyBookServlet" method="post">
			<label>Select a Book: </label> <select name="bookid" required>
				<option value="" disabled selected>Select your option</option>
				<%
					for (int count = 0; count < listOfBooks.size(); count++) {
					Books book = (Books) listOfBooks.get(count);
				%>
				<option value="<%=book.getId()%>"><%=book.getName()%>-<%=book.getAuthor() %>-<%=book.getYear() %></option>
				<%}%>
			</select> 
			<label>Choice</label>
			<select name="choice">
			<option value="1">ModifyBook</option>
			<option value="2">ModifyAuth</option>
			<option value="3">ModifYear</option>
			</select><label>Enter modify data:</label>
			<input type="text" name="changedata" />
			<input type="submit" value="change"/>
			</form>
			<br>
			<h1><%=(request.getAttribute("Message") == null) ? "" : request.getAttribute("Message")%></h1>			
	</body>
	
</html>