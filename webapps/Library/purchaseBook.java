import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import Objects.Books;
import javax.servlet.ServletException;
import java.io.PrintWriter;

@WebServlet("/purchaseBookServlet")

public class purchaseBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userName = request.getUserPrincipal().getName();
		if (userName != null) {
			List<Books> listOfBooks = AdminAction.getBooks();
			request.setAttribute("listOfBooks", listOfBooks);
			request.getRequestDispatcher("/purchaseBooks.jsp").forward(request, response);
		} else
			request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userName = request.getUserPrincipal().getName();
		if(userName!=null)
		{
		int userId = UserAction.getUserIdFromName(userName);
		String purchaseStatus=UserAction.purchaseBook(Integer.valueOf(request.getParameter("book")), userId);
		request.setAttribute("Message", purchaseStatus);
		List<Books> listOfBooks = AdminAction.getBooks();
		request.setAttribute("listOfBooks", listOfBooks);
		request.getRequestDispatcher("purchaseBooks.jsp").forward(request, response);
		return;
			}
	}
}