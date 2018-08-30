package com.entor.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JavamailUtil {
	
	public static void sendMail(JavaMailSender javaMailSender, String from
			, String[] receivers, String subject,String content) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(receivers);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
	}
}

