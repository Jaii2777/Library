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

@WebServlet("/AcceptServlet")

public class AcceptServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String userName = request.getUserPrincipal().getName();
		if(userName!=null) {
			int userId = UserAction.getUserIdFromName(userName);
			List<GroupRequest> showMyRequestList=UserAction.viewMyRequestList(userId);
			request.setAttribute("reqInfo",showMyRequestList);
			request.getRequestDispatcher("/requestList.jsp").forward(request,response);
			return;
		}
	}	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)  throws IOException,ServletException{
		//String userId=(String) request.getParameter("userId");
		//String groupId=(String) request.getParameter("groupId");
		String user=(String) request.getUserPrincipal().getName();
		if(user!=null)
		{
			String reqUserName=request.getParameter("userName");
			PrintWriter out=response.getWriter();
			String Message=UserAction.acceptRequest(Integer.valueOf(request.getParameter("groupId")),Integer.valueOf(request.getParameter("userId")),request.getParameter("userName"));
			//PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('"+Message+"');");
			out.println("location='/';");
			out.println("</script>");
			return;
		}
	}
}