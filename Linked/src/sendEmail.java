import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class sendEmail{
	
	private Random codeNum;
	private String verificationCode = "";
	
	public void send(String receiver, String firstname){
		
		codeNum = new Random();
		for(int i = 0; i < 6; i++)
			verificationCode += Integer.toString(codeNum.nextInt(10));
		
		final String from = "linkeduconfirmation@gmail.com";
		final String password = "registration";
		String host = "smtp.gmail.com";
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.stmp.password", password);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
              });
		
		try {
			
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			msg.setSubject("Linkedu confirmation code");
			
			msg.setText("Hey" + " " + firstname + "," + "\r\n"
			+ "You recently registered for Linkedu. To complete your Linkedu registration, "
			+ "please confirm your account with the following confirmation code" + "\r\n" 
			+ getVCode());
			
			Transport.send(msg);
			
		} catch (MessagingException mex){
			
			JOptionPane.showMessageDialog(null, mex);
		}
	}
	
	public String getVCode(){
		
		return String.valueOf(verificationCode);
	}
}
