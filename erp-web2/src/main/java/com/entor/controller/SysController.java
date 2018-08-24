package com.entor.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IEmpService;
import com.entor.entity.Emp;

@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {
	@Autowired
	private IEmpService empService;
	
	@RequestMapping(path="/login.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map login(HttpSession session, Emp emp) {
		List<Emp> emps = empService.find(emp);
		System.out.println(emps);
		if (emps.size() > 0) {
			session.setAttribute("emp", emps.get(0)); //把当前登录用户保存在Session中
			return ajaxReturn(true, "登录成功");
			
		} else {
			return ajaxReturn(false, "用户名或密码不正确");
		}
	}
	
	//获取当前登录的用户名
	@RequestMapping(path="/getName.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map getName(HttpSession session) {
		Object o = session.getAttribute("emp");
		if (o == null) {
			return ajaxReturn(false, "请先登录");
		} else {
			Emp e = (Emp) o;
			return ajaxReturn(true, e.getName());
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login.html";
	}
	
}

