import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import Objects.Books;
import Objects.BookPurchased;
import java.io.IOException;
@WebServlet("/requestServlet")

public class RequestServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	String mailId=request.getParameter("mail");
	PrintWriter out=response.getWriter();
	out.println("alert('"+mailId+"');");
	return;
	}
}
