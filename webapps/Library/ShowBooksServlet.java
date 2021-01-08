import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import Objects.Books;
@WebServlet("/showBookServlet")

public class ShowBooksServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String adminName = (String) request.getUserPrincipal().getName();
		if (adminName != null) {
			List<Books> listOfBooks = AdminAction.getBooks();
			request.setAttribute("listOfBooks", listOfBooks);
			request.getRequestDispatcher("/ShowBooks.jsp").forward(request, response);
		} else
			request.getRequestDispatcher("/AdminActions.jsp").forward(request, response);
	}
}