package UTILS.Mailer;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceMailer {
	private static Properties props = new Properties();
	static{
 		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}
	private Session session;
	private MimeMessage message;
	//
	private String from, to, subject, msg, fileURL, fileName;

	public ServiceMailer(String email, String pass) {
		System.out.println("email : " + email + " password : " + pass);
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, pass);
			}
		});
	}

	public static void main(String[] args) {
		final ServiceMailer smg = new ServiceMailer("", "");

		smg.send("fleghribf@gmail.com", "fleghribf@gmail.com",
				"Leghrib Badreddine est un programmeur Java \n Leghrib Badreddine is a Java Developper", "ABONNEE",
				new File(""), "");

	}

	public boolean send(String from, String to, String msg, String subject, File file, String fileName) {
		this.from = from;
		this.to = to;
		this.msg = msg;
		this.subject = subject;
		try {
			this.fileURL = file.getPath();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.fileName = fileName;

	//	emailSetting();
		try {
			prepareMessage();
		} catch (AddressException e1) {

			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileAttachment();

			// send the message
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("message has been send to " + to);
		return true;
	}

	private void prepareMessage() throws AddressException, MessagingException {
		// message.setContent(message, "text/plain; charset=UTF-8");
		// message.setContent(message, "text/html; charset=utf-8");
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setText(msg);

	}

	void fileAttachment() {
		// file attachement

		try {
			if (fileURL.equals(""))
				return;
			DataSource source = new FileDataSource(fileURL);
			message.setDataHandler(new DataHandler(source));
			message.setFileName(fileName);
		} catch (Exception e) {

		}
	}

 		
		// gmail or hotmail 587
	 
}