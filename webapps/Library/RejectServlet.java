import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import Objects.Books;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;
import Objects.GroupRequest;

@WebServlet("/RejectServlet")
public class RejectServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)  throws IOException,ServletException{
		String user=(String) request.getUserPrincipal().getName();
		if(user!=null)
		{
			String reqUserName=request.getParameter("userName");
			PrintWriter out=response.getWriter();
			String Message=UserAction.rejectRequest(Integer.valueOf(request.getParameter("groupId")),Integer.valueOf(request.getParameter("userId")));
			out.println("<script>");
			out.println("alert('"+Message+"');");
			out.println("location='/';");
			out.println("</script>");
			return;
		}
	}
}