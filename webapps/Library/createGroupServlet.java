import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import Objects.Books;
import javax.servlet.ServletException;
import java.io.PrintWriter;

@WebServlet("/createGroup")
public class createGroupServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
			
			String userName = request.getUserPrincipal().getName();
			if(userName!=null)
			{
				int userId = UserAction.getUserIdFromName(userName);
				String groupName=request.getParameter("groupName");
				String Message=UserAction.createGroup(userId,groupName,userName);
				PrintWriter  out=response.getWriter();
				out.println("<script>");
				out.println("alert('"+Message+"');");
				out.println("location='/';");
				out.println("</script>");
			return;
				// request.setAttribute("Message",Message);
				// request.getRequestDispatcher("/createGroup.jsp").forward(request,response);
			}
	}
}