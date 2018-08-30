package com.entor.test;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.entor.javamail.Sender;

public class SendMail {  
    public ApplicationContext ctx = null;  
    public SendMail() {  
        ctx = new FileSystemXmlApplicationContext("src/main/java/spring-xml.xml");  
    }  
    /** 
     * �����Է��� 
     *  
     * @throws MessagingException 
     */  
    public static void main(String[] args) {  
        new SendMail().sendMail();  
    }  
    public void sendMail() {  
        JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");// ��ȡJavaMailSender  
        SimpleMailMessage mail = new SimpleMailMessage();  
        try {  
            mail.setTo("aaaa@qq.com");// ������  
            mail.setFrom("bbbb@163.com");// ������  
            mail.setSubject("s�ʼ�����");// ����  
            mail.setText("springMail �ļ򵥷��Ͳ���");// �ʼ�����  
            sender.send(mail);  
            System.out.println("�������");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}