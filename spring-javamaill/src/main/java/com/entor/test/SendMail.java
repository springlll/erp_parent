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
     * 主测试方法 
     *  
     * @throws MessagingException 
     */  
    public static void main(String[] args) {  
        new SendMail().sendMail();  
    }  
    public void sendMail() {  
        JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");// 获取JavaMailSender  
        SimpleMailMessage mail = new SimpleMailMessage();  
        try {  
            mail.setTo("aaaa@qq.com");// 接受者  
            mail.setFrom("bbbb@163.com");// 发送者  
            mail.setSubject("s邮件主题");// 主题  
            mail.setText("springMail 的简单发送测试");// 邮件内容  
            sender.send(mail);  
            System.out.println("发送完毕");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}