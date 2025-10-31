package com.modefinsever.kafka.utils;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	public HashMap<String, Object> sendMail(HashMap<String, Object> inputMap) {
		HashMap<String, Object> outputMap = new HashMap<>(inputMap);

		String toAddress = (String) inputMap.getOrDefault("ToAddress", "nswaroop7892gmail.com");
		String subject = (String) inputMap.getOrDefault("Subject", "Test kafka new mocroservice");
		String messageBody = (String) inputMap.getOrDefault("MailMessage", "mail come successfully  ......");

		// SMTP Config (Change according to your mail server)
		final String fromEmail = "swaroopnvarne@gmail.com";
		final String password = "fjxs ytrj rsni bmmi"; // Use app-specific password for Gmail

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // e.g., smtp.office365.com for Outlook
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		try {
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromEmail, "Notification Service"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			msg.setSubject(subject);

			// Email body as HTML
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent("<html><body>" + messageBody + "</body></html>", "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyPart);

			msg.setContent(multipart);

			Transport.send(msg);

			outputMap.put("ResultMessage", "✅ Email sent successfully to " + toAddress);
		} catch (Exception e) {
			e.printStackTrace();
			outputMap.put("ErrorMessage", "❌ Failed to send email: " + e.getMessage());
		}

		return outputMap;
	}

	// For manual testing
	public static void main(String[] args) throws Exception {
		SendMail mail = new SendMail();
		HashMap<String, Object> input = new HashMap<>();
		input.put("ToAddress", "swaroopnvarne@gmail.com");
		input.put("Subject", "Kafka Notification Test");
		input.put("MailMessage", "Hello, this is a test email from Kafka Notification Service!");

		HashMap<String, Object> output = mail.sendMail(input);
		System.out.println(output);
	}

}
