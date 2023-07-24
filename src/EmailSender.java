import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

//This class is used for sending email
public class EmailSender {
	private final static String EMAIL_SENDER = "****@gmail.com";
	private final static String PASSWORD = "*****";
	
	public static void send(String emailReciever, String username, Task taskToSend)
	{
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.transport.protocl", "smtp");
		
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL_SENDER, PASSWORD);
				
			}
		});
		
        MimeMessage message = new MimeMessage(session);
        try {
        	message.setSubject("Reminder for your task!");
			Address addressTo = new InternetAddress(emailReciever);
			message.addRecipient(RecipientType.TO, addressTo);
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(createMessageToSend(taskToSend, username), "text/html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String createMessageToSend(Task task, String username)
	{
		String message = "<font size =\\\"25\\\" face=\\\"arial\\\" >Hello, " + username + 
				"!<br>I'm here to remind " + "you about : <br>";
		message += "<b>" + task.getTaskColumn() + "<b><br><br>Planned on : " + task.getDate() + ", at " 
				+ task.getTimeColumn() + "</font>";
		return message;
		
	}
}
