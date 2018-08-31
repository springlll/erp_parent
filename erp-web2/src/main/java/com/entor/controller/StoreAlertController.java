package com.entor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IStoreAlertService;
import com.entor.entity.StoreAlert;
import com.entor.util.JavamailUtil;

@Controller
@RequestMapping("/storealert")
public class StoreAlertController extends BaseController {
	@Autowired
	private IStoreAlertService storeAlertService;
	@Autowired
	private JavaMailSender javamailSender;
	@Autowired
	private Environment environment;
	
	@RequestMapping("/list.do")
	public void list() {}
	
	@RequestMapping(path="/getData.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public List<StoreAlert> getData() {
		return storeAlertService.findAll();
	}
	
	@RequestMapping(path="/send.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Map send() {
		System.out.println("正在检查库存预警的商品...");
		String from = environment.getProperty("mail.from");
		String subject = environment.getProperty("mail.subject");
		String content = environment.getProperty("mail.content");
		System.out.println("from = " + from + ", subject = " + subject + ", content = " + content);
		List list = storeAlertService.findAll();
		if (list.size() > 0) {
			content = content.replace("[count]", list.size() + "");
			String[] receivers = {"294870793@qq.com"};
			try {
				JavamailUtil.sendMail(javamailSender, from, receivers, subject, content);
				System.out.println("发送成功！");
				return ajaxReturn(true, "邮件发送成功!");
			} catch (Exception e) {
				e.printStackTrace();
				return ajaxReturn(false, "邮件发送失败!");
			}
		} else {
			return ajaxReturn(false, "没有库存预警的商品!");
		}
	}
	
	//登录首页时候检查是否存在库存预警记录
	@RequestMapping(path="/check.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map checkStoreAlert() {
		try {
			List<StoreAlert> storeAlerts = storeAlertService.findAll();
			if (storeAlerts.size() > 0) {
				return ajaxReturn(true, "<a href='javascript:openStoreAlertWindow();'>当前有" 
						+ storeAlerts.size() + "种商品出现库存预警</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "");
	}


}