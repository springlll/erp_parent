package com.entor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.entor.business.IOrdersService;
import com.entor.entity.Emp;
import com.entor.entity.Orders;
import com.entor.entity.OrdersDetail;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {
	@Autowired
	private IOrdersService ordersService;
	
	@RequestMapping("/list.do")
	public void list() {}
	@RequestMapping("/add.do")
	
	public void add() {}
	//
	//加载订单表格的数据
	@RequestMapping(path="/getData.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Map getData(Orders orders ,Integer page, Integer rows) {
		if (page == null) {
			page = 1;
		}
		if (rows == null) {
			rows = 10;
		}
		PageHelper.startPage(page, rows); //设置分页的信息
		List<Orders> orderList = null;
		int total =0;
		if(!"".equals(orders.getState())) {
			orderList =	ordersService.find(orders);
		total = ordersService.count(orders);
		}else {
			orderList = ordersService.findAll();
			total = ordersService.count(null);
		}
		Map map = new HashMap();
		map.put("rows", orderList);
		map.put("total", total);
		return map;	
	}
	@RequestMapping(path="/addOrder.do",produces= {"application/json;charset=utf-8"})
	
	public Map addOrder(HttpSession session,Orders orders  ,String json) {
		try {
			System.out.println("orders = "+ orders );
			Object o = session.getAttribute("emp");
			if(o==null) {
				return ajaxReturn(false, "搞事情");}
				Emp emp = (Emp) o;
				orders.setCreater(emp.getUuid());
				List<OrdersDetail> orderDetails = JSON.parseArray(json,OrdersDetail.class);
				orders.setOrdersDetails(orderDetails);
				ordersService.addOrders(orders);
				return ajaxReturn(true, "添加");
			
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(false, "添加");
		}
		}
	}
