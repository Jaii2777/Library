import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import Objects.Books;
import Objects.BookPurchased;

@WebServlet("/BookPurchasedInfoServlet")


public class BookPurchasedInfoServlet extends HttpServlet {
	protected void doGet (HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
	String adminName=(String)request.getUserPrincipal().getName();
	if(adminName!=null) {
		if(request.getParameter("bookId")==null) {
		List<BookPurchased> countOfBookInfo=AdminAction.countOfBookInfo();
		request.setAttribute("countOfBookInfo",countOfBookInfo);
		request.getRequestDispatcher("ShowBookPurchasedDetails.jsp").forward(request,response);
		return;
	}
	else {
		List<String> userList=AdminAction.getUserListForBook(Integer.valueOf(request.getParameter("bookId")));
		request.setAttribute("userList",userList);
		request.getRequestDispatcher("userList.jsp").forward(request,response);
		return;
	}
	}	
}
}