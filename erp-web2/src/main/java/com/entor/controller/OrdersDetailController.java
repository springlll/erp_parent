package com.entor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IOrdersDetailService;
import com.entor.entity.OrdersDetail;

@Controller
@RequestMapping("/ordersdetail")

public class OrdersDetailController extends BaseController{
	@Autowired
	private IOrdersDetailService ordersDetailService;
	@RequestMapping(path="/list",produces="application/json;charset=utf-8")
	@ResponseBody
	public List<OrdersDetail> listDetail(OrdersDetail ordersDetail){
		
		
		return ordersDetailService.find(ordersDetail);
		
		
	}
	
}
