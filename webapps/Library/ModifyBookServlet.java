import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import Objects.Books;
@WebServlet("/ModifyBookServlet")

public class ModifyBookServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String adminName = (String) request.getUserPrincipal().getName();
		if (adminName != null) {
			List<Books> listOfBooks = AdminAction.getBooks();
			request.setAttribute("listOfBooks", listOfBooks);
			request.getRequestDispatcher("/ModifyBook.jsp").forward(request, response);
		}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
				String AdminName=(String)request.getUserPrincipal().getName();
				if(AdminName!=null){
					//String change=(String) request.getParameter("changedata");
					String Message=AdminAction.modifyBooks(Integer.valueOf(request.getParameter("bookid")),Integer.valueOf(request.getParameter("choice")),request.getParameter("changedata"));
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('"+Message+"');");
					out.println("location='AdminActions.jsp';");
					out.println("</script>");
				}
			}
}
/*String id=request.getParameter("modifyId");
					PrintWriter out=response.getWriter();
					String Message=AdminAction.temp(Integer.valueOf(request.getParameter("book")));
					//PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('"+request.getParameter("choice")+"');");
					out.println("location='/';");
					out.println("</script>");
			return;*/