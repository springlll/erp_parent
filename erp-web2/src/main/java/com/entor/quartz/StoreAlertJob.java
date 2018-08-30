package com.entor.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.entor.business.IStoreAlertService;
import com.entor.entity.StoreAlert;
import com.entor.util.JavamailUtil;

@Component
public class StoreAlertJob {
	@Autowired
	private IStoreAlertService storeAlertService;
	@Autowired
	private Environment environment;
	@Autowired
	private JavaMailSender javamailSender;
	
	public void sendStoreAlertMsg() {
		System.out.println("正在检查库存预警的商品...");
		String from = environment.getProperty("mail.from");
		String subject = environment.getProperty("mail.subject");
		String content = environment.getProperty("mail.content");
		System.out.println("from = " + from + ", subject = " + subject + ", content = " + content);
		List list = storeAlertService.findAll();
		if (list.size() > 0) {
			content = content.replace("[count]", list.size() + "");
			String[] receivers = {"896337156@qq.com"};
			try {
				JavamailUtil.sendMail(javamailSender, from, receivers, subject, content);
				System.out.println("发送成功！");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("发送失败");
			}
		} else {
			System.out.println("没有库存预警的商品!");
		}
	}

	
}