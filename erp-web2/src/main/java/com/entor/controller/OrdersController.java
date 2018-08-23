package com.entor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IOrdersService;
import com.entor.entity.Orders;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {
	@Autowired
	private IOrdersService ordersService;
	
	@RequestMapping("/list.do")
	public void list() {}
	
	
	//加载订单表格的数据
	@RequestMapping(path="/getData.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Map getData(Integer page, Integer rows) {
		if (page == null) {
			page = 1;
		}
		if (rows == null) {
			rows = 10;
		}
		PageHelper.startPage(page, rows); //设置分页的信息
		List<Orders> orderList = ordersService.find();
		int total = ordersService.count(null);
		Map map = new HashMap();
		map.put("rows", orderList);
		map.put("total", total);
		return map;
	}
	
}