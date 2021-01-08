import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.DriverManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.PrintWriter;
@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException ,ServletException
	{
		String user = request.getUserPrincipal().getName();
		 if (request.isUserInRole("admin"))
		 {
			response.sendRedirect("AdminActions.jsp");
			System.out.println(user+"you are admin role is"+request.isUserInRole("admin"));
			return;
		}
		if(user!=null){
			//db->check secretKey if=null  ->j.sp(button)
			System.out.println(user+"you are user role is"+request.isUserInRole("user"));
			//HttpSession session = request.getSession(true);
			//session.setAttribute("userName", user);
			int userId = UserAction.getUserIdFromName(user);
			//session.setAttribute("userId",userId);
			String userMail=UserAction.getUserMailIdFromName(user);
			List<Integer> allowedOp=UserAction.userOperations(userId);
			List<String> displayList=new ArrayList<String>();
			List<String> fileList=new ArrayList<String>();
			String res=SendMail.sendMailToUser(userMail);
			for(int i:allowedOp)
			{
				displayList.add(operationsConstants.operationVsValues.get(i));
				fileList.add(operationsConstants.operationVsValues.get(i)+".jsp");
			}
			request.setAttribute("AllowedOperations",displayList);
			request.setAttribute("fileList",fileList);
			request.setAttribute("mailStatus",res);
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}
	}
	
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException , ServletException
	{
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		try
		{ 
			int userId=UserAction.checkValidCredentials(userName,password);
			if(userId==0){
				PrintWriter out = response.getWriter();
				out.println("<script>");
   				out.println("alert('User or password incorrect');");
				out.println("location='Login.jsp';");
   				out.println("</script>");
			}else{
				HttpSession session = request.getSession(true);
				session.setAttribute("userName", userName);
				session.setAttribute("userId",userId);
				doGet(request, response);
			}
		}
		catch(Exception e)
		{
			response.getWriter().write("error"+e.getMessage());
		}
		
	}*/
}
