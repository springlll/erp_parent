package com.entor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entor.business.IOrdersService;
import com.entor.entity.Orders;
import com.github.pagehelper.PageHelper;

@Controller
public class OrdersController extends BaseController{

	@Autowired
	private IOrdersService ordersService;
	@RequestMapping("list.do")
	public void list() {
		
	}
	@RequestMapping(path="/getData.do",produces="application/json;charset=utf-8")
	public Map list(Integer page,Integer rows) {
		if(page == null) {
			page=1;
		}
		if (rows == null) {
			rows = 10;
		}
		PageHelper.startPage(page, rows);
		List<Orders> orders = ordersService.findAll();
		int total = ordersService.count(null);
		Map map = new HashMap();
		map.put("total", total);
		map.put("rows", orders);
		return map;
	}

	
}
