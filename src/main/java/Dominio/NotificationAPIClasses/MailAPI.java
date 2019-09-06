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
	private String senderEmailID;
	private String senderPassword;
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	 
	public void enviarMail(String emisor, String contrasenia, String destinatario, String subject, String body)
	{	    
		   Properties props = new Properties();
		   props.put("mail.smtp.user",emisor);
		   props.put("mail.smtp.host", emailSMTPserver);
		   props.put("mail.smtp.port", emailServerPort);
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.socketFactory.port", emailServerPort);
		   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.socketFactory.fallback", "false");
		   this.senderEmailID = emisor;
		   this.senderPassword = contrasenia;
		   
		   try{  
			   Authenticator auth = new SMTPAuthenticator();
			   Session session = Session.getInstance(props, auth);
			   MimeMessage msg = new MimeMessage(session);
			   msg.setText(body);
			   msg.setSubject(subject);
			   msg.setFrom(new InternetAddress(emisor));
			   msg.addRecipient(Message.RecipientType.TO,
					   new InternetAddress(destinatario));
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
