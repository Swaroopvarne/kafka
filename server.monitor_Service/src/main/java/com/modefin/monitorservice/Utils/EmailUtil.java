package com.modefin.monitorservice.Utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.modefin.monitorservice.config.MonitorProperties;

@Component
public class EmailUtil {

	private static final Logger log = LoggerFactory.getLogger(EmailUtil.class);

	private final String fromEmail;
	private final String password;

	public EmailUtil(MonitorProperties props) throws Exception {
		if (props.getMail() == null || props.getMail().getFrom() == null || props.getMail().getPassword() == null) {
			throw new Exception(
					"❌ Email configuration (monitor.mail.from/password) is missing in application.yml");
		}
		this.fromEmail = props.getMail().getFrom();
		this.password = props.getMail().getPassword();
	}

	public void sendEmail(String toEmail, String subject, String body) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromEmail));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			msg.setSubject(subject);
			msg.setText(body);

			Transport.send(msg);
			log.info("✅ Alert Email Sent to {}", toEmail);

		} catch (MessagingException e) {
			log.error("❌ Failed to send email to {}. Reason: {}", toEmail, e.getMessage(), e);
		}
	}
}