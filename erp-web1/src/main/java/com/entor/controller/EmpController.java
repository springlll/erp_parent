package com.entor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IEmpService;
import com.entor.entity.Emp;

@Controller
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	private IEmpService empService;

	//根据员工ID获取该员工的名字
	@RequestMapping(path = "/getName.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map getEmpName(Emp emp) {
		List<Emp> emps = empService.find(emp);
		Map map = new HashMap();
		map.put("name", emps.get(0).getName());
		return map;
	}
	
}
