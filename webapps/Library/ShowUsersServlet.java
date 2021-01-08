import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import Objects.Books;

@WebServlet("/ShowUsersServlet")

public class ShowUsersServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
			String adminName=(String) request.getUserPrincipal().getName();
			if(adminName!=null){
				if(request.getParameter("userName")==null) {
				List<String> usersList=AdminAction.getUsersName();
				request.setAttribute("usersList",usersList);
				request.getRequestDispatcher("ShowUsersInfo.jsp").forward(request,response);
				return;
				}
				else{
					String user=request.getParameter("userName");
					int userId=UserAction.getUserIdFromName(user);
					String mailMessage="";
					String userMail=UserAction.getUserMailIdFromName(user);
					List<Books> userBooks=UserAction.viewMyBooks(userId);
					if(userBooks.size()!=0)
					{
						mailMessage=sendAlertMail.sendMail(adminName,userMail);
					}
					request.setAttribute("bookList",userBooks);
					request.setAttribute("userName",user);
					request.setAttribute("mail",mailMessage);
					request.getRequestDispatcher("UserBooks.jsp").forward(request,response);
					return;
					}
			}
	}
}
