package Dominio.NotificationAPIClasses;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailAPI {
	final String senderEmailID = "melisarodrig2@gmail.com";
	final String senderPassword = "PGM191418";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String receiverEmailID = null;
	 
	public void enviarMail(String correo, String subject, String body)
	{	    
		   Properties props = new Properties();
		   props.put("mail.smtp.user",senderEmailID);
		   props.put("mail.smtp.host", emailSMTPserver);
		   props.put("mail.smtp.port", emailServerPort);
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.socketFactory.port", emailServerPort);
		   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.socketFactory.fallback", "false");
		   
		   try{  
			   Authenticator auth = new SMTPAuthenticator();
			   Session session = Session.getInstance(props, auth);
			   MimeMessage msg = new MimeMessage(session);
			   msg.setText(body);
			   msg.setSubject(subject);
			   msg.setFrom(new InternetAddress(senderEmailID));
			   msg.addRecipient(Message.RecipientType.TO,
					   new InternetAddress(correo));
			   Transport.send(msg);
		   }
		   
		   catch (Exception ex)
		   {
			   ex.printStackTrace();
		   }   
	}
		   
	public class SMTPAuthenticator extends javax.mail.Authenticator
	{
		   public PasswordAuthentication getPasswordAuthentication()
		   {
			   return new PasswordAuthentication(senderEmailID, senderPassword);
		   }
		   
	}
}
