package com.entor.javamail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Sender {

	private JavaMailSender javaMailSender;

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMail() throws MessagingException {
		//创建数据包
		MimeMessage mime = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime);
		helper.setFrom("294870793@qq.com"); //发件人
		helper.setTo("13642413572@163.com");//收件人
		helper.setSubject("测试"); //主题
		helper.setText("你要是收到这封邮件，你就成功了，嘿嘿~~~~");// 内容
		//发送数据包
		javaMailSender.send(mime);
	}

}
