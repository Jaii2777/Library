package jaasAuth;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import org.json.JSONObject;
import org.json.*;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;
public class ValidateLoginModule implements LoginModule { 
    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean success = false;
    private String username = null;
    private char[] password = null;
	private String otp=null;
	public HttpServletResponse response;
    private UserPrincipal userPrincipal = null;
    private RolePrincipal rolePrincipal =null; 
	private String AdminIkEY="DINAPAG86BENT09Y05TG";
	private String AdminSKEY="KyTkPJUNjwusrzlu6dNWhkIKc6Q3uXBLHYMfIRtQ";
	private String AdminHOST="api-11b78b55.duosecurity.com";
	private String IKEY="DII0KN3N7D9C2N1BWKME";
	private String SKEY="2vJDWQuGSGT7t61jIIEwQrKUMuHjTP4r1z27lcT5";
	private	 String AKEY="b6866282f944d776c62a08721d88ff6b6cf2a1c3";
	private String HOST="api-11b78b55.duosecurity.com";
	private String  deviceId=null;
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                Map<String, ?> sharedState, Map<String, ?> options) {

        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }
 
    @Override
    public boolean login() throws LoginException {
   
        if (callbackHandler == null){
            throw new LoginException("No CallbackHandler available");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password: ", false);
        try {
            callbackHandler.handle(callbacks);
            username = ((NameCallback)callbacks[0]).getName();
            password = ((PasswordCallback)callbacks[1]).getPassword();
            if (isValidUser()){	
				try{
					System.out.println("username is"+username);
					if(DuoAuth(username)){
					System.out.println(" after duo auth username is"+username);
					//openPage();
                    success = true;
				 return true;
				}
				}
				catch(Exception e){
				}
            } 
        } catch (IOException e) { 
             e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
             e.printStackTrace();
        }
        return false;
    }
 
    @Override
    public boolean commit() throws LoginException {
        if (success == false) {
            return false;
        } else { 
				userPrincipal = new UserPrincipal(username);
                subject.getPrincipals().add(userPrincipal);
				String role = getRole();
                RolePrincipal rolePrincipal = new RolePrincipal(role);
                subject.getPrincipals().add(rolePrincipal); 
                }
				return true;
	}
       
 
   @Override
   public boolean abort() throws LoginException {
      if (success == false) {
          return false;
      }
      return true;
   }
 
    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        success = false;
        userPrincipal = null;
        return true;
   }
   private static void serveContent(final String content, final int status, final HttpServletResponse response) throws IOException {
		System.out.println("before serve content"+response);
      response.setContentType("text/html; charset=utf-8");
      response.setStatus(status);
      response.getWriter().println(content);
	   System.out.println("after serve content"+response);
    }
   private boolean check(String username) throws Exception{
	try{
	System.out.println("inside check");
	Http checkagain=new Http("POST",HOST,"/auth/v2/preauth");  //check user status
	checkagain.addParam("username",username);
	System.out.println("ok");
	checkagain.signRequest(IKEY,SKEY);
	System.out.println("sigreq");
	JSONObject result =(JSONObject) checkagain.executeRequest();
	System.out.println("After check"+result);
	JSONObject res=(JSONObject)result.getJSONObject("response");
	String status=res.getString("result");
	if(status.equals("auth"))
	{
		System.out.println("method is "+status);
		return true;
	}else{
		System.out.println("method is "+status);
		return false;
	}
   }catch(Exception e){
	}
	return false;
   }
   private JSONObject preAuthStatus(String username) throws Exception {
	Http http=new Http("POST",HOST,"/auth/v2/preauth");  //check user status
	http.addParam("username",username);
	System.out.println("ok");
	http.signRequest(IKEY,SKEY);
	System.out.println("sigreq");
	JSONObject result =(JSONObject) http.executeRequest();
	System.out.println("status of the user"+result);
	JSONObject res=(JSONObject)result.getJSONObject("response");
	return res;
   }
   private String push(String username,String deviceId) throws Exception{
	   Http sendPush=new Http("POST",HOST,"/auth/v2/auth");
					sendPush.addParam("username",username);
					//String push="push";
					sendPush.addParam("factor","push");
					sendPush.addParam("device",deviceId);
					sendPush.signRequest(IKEY,SKEY);
					JSONObject pushResult =(JSONObject) sendPush.executeRequest();
					return pushResult.getJSONObject("response").getString("result");
   }
  private boolean  DuoAuth(String username) throws Exception   {
	//JSONObject result = null;
	try {
	System.out.println("inside duoauth");
	//System.out.println("status of the uresponse"+res);
	JSONObject preAuthStatus=preAuthStatus(username);
	String deviceId=null;
	if(preAuthStatus.getString("result").equals("enroll"))
	{
		System.out.println("method is"+preAuthStatus.getString("result"));
		String status=preAuthStatus.getString("result");
		String url=preAuthStatus.getString("enroll_portal_url");
		String userMailId=getaMailId(username);
		System.out.println(userMailId);
		String mailStatus=sendMailToDuoUser.sendMailToAuthentication(url,userMailId);
		System.out.println(mailStatus);
		if(mailStatus.equals("success")){
			System.out.println("before mail  20sec check");
			TimeUnit.SECONDS.sleep(20);
			while(true){
				JSONObject res=preAuthStatus(username);
				if(res.getString("result").equals("auth")){
					TimeUnit.SECONDS.sleep(35);
					System.out.println("yours mode is now"+res.getString("result").equals("auth"));
					System.out.println(res.getString("result"));
					JSONArray arr = res.getJSONArray("devices");
					for (int i = 0; i < arr.length(); i++) {
					   deviceId = arr.getJSONObject(i).getString("device");
						System.out.println(deviceId);
					}
					
					if(deviceId!=null){
					String pushStatus=push(username,deviceId); 
					if(pushStatus.equals("allow")){
					System.out.println("push status is"+pushStatus);
					return true;
					}
					else{
						System.out.println("push status is"+pushStatus);
						return false;
					}
					//return false;
				}
				TimeUnit.SECONDS.sleep(30);
				}
			}
			//System.out.println("after mail  20sec check");
		//return true;
		}
	}
	//String status=res.getString("result");
	//String url=res.getString("enroll_portal_url");//.getString("enroll_portal_url");
		JSONObject res=preAuthStatus(username);
		System.out.println("ok");
		System.out.println(res.getString("result"));
		JSONArray arr = res.getJSONArray("devices");
		for (int i = 0; i < arr.length(); i++) {
           deviceId = arr.getJSONObject(i).getString("device");
            System.out.println(deviceId);
        }
		if(deviceId!=null){
			String pushStatus=push(username,deviceId);
			if(pushStatus.equals("allow")){
					System.out.println("push status is"+pushStatus);
					return true;
					}
					else{
						System.out.println("push status is"+pushStatus);
						return false;
					}
		}
	}	
	catch(Exception e){
	}
	return false;
  }
  private String getaMailId(String username){
		Connection con = null;
      ResultSet rs = null;
      PreparedStatement stmt = null;
      try {
          con = getConnection();
		  String sql = "select mailId from userdetails where userName=?";
          stmt = con.prepareStatement(sql);
          stmt.setString(1, username);
         // stmt.setString(2, new String(password));
          rs = stmt.executeQuery();
    
          if (rs.next()) { 
		  String mailId=rs.getString(1);
			rs.close();
				stmt.close();
				con.close();
				return mailId;
		  }
	  } catch (Exception e) {
           e.printStackTrace();
       }
	   return null;
  }
	private boolean isValidUser() throws LoginException {
      Connection con = null;
      ResultSet rs = null;
      PreparedStatement stmt = null;
      try {
          con = getConnection();
		  String sql = "select userId from userdetails where userName=?  and password=?";
          stmt = con.prepareStatement(sql);
          stmt.setString(1, username);
          stmt.setString(2, new String(password));
		  String secretKey="";
          rs = stmt.executeQuery();
    
          if (rs.next()) { 
			rs.close();
				stmt.close();
				con.close();
				return true;
		  }
			//secretKey=rs.getString(2);
			/*if(secretKey==null){
				GoogleTwoFactAuth(username);
				String totp=OtpUtils.getTOTPCode(secretKey);
				if(otp.equals(totp)){
				rs.close();
				stmt.close();
				con.close();
				return true;
			}
			}
			else {*/
			else{
				rs.close();
				stmt.close();
				con.close();
			}
       } catch (Exception e) {
           e.printStackTrace();
       }
	   return false;
       }
  private String getRole() { 
   
      Connection con = null;
      PreparedStatement stmt = null;
      String role = "";
      try {
          con = getConnection();
          String sql = "select userRole from userroles where userName=?";
          stmt = con.prepareStatement(sql);
          stmt.setString(1, username);
    
           ResultSet rs= stmt.executeQuery();
    
          if (rs.next()) { 
              role=rs.getString(1);
				rs.close();
				stmt.close();
				 con.close();
          }
		  else{
			rs.close();
			stmt.close();
			con.close();
		  }
      } catch (Exception e) {
          e.printStackTrace();
      }
       return role;
 }
  private Connection getConnection() throws LoginException {
      Connection con = null;
      try {
         con = DriverManager.getConnection ("jdbc:mysql://localhost/library","root", "root");
      } 
      catch (Exception e) {
         e.printStackTrace();
      }
      return con;
   }
}