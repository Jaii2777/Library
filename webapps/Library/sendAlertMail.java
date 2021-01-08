import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport; 
import java.lang.*;

public class sendAlertMail
 {
	 public static String sendMail(String admin,String userMail)
	 {
		final String from ="libraryportal123@gmail.com";
        final String password = "Library@123";  
		Properties props = new Properties();    
		props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(from,password);  
		    }    
         });   
      try { 
        MimeMessage message = new MimeMessage(session); 
        message.setFrom(new InternetAddress(from));  
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail)); 
        message.setSubject("Alart"); 
        message.setText("Admin viewed your profile");
        //Transport.send(message); 
        return ("Mail successfully sent"); 
      } 
      catch (MessagingException mex)  
      { 
        return mex.getMessage();
      }  
   }
}