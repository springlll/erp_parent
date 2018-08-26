package com.entor.controller;

import java.util.Date;
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
	@RequestMapping("/check.do")
	public void check() {}
	@RequestMapping("/start.do")
	public void start() {}
	@RequestMapping("/instore.do")
	public void instore() {}
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
	//添加订单
	@RequestMapping(path="/addOrder.do",produces= {"application/json;charset=utf-8"})
	
	public Map addOrder(HttpSession session,Orders orders  ,String json) {
		try {
			System.out.println("orders = "+ orders );
			Object o = session.getAttribute("emp");
			if(o==null) {
				return ajaxReturn(false, "当前还没有登录");}
				Emp emp = (Emp) o;
				orders.setCreater(emp.getUuid());
				orders.setCreatetime(new Date());
				orders.setType("1");
				orders.setState("0");
				List<OrdersDetail> orderDetails = JSON.parseArray(json,OrdersDetail.class);
				orders.setOrdersDetails(orderDetails);
				ordersService.addOrders(orders);
				return ajaxReturn(true, "添加成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(false, "添加失败");
		}
		}
	//订单审核
	@RequestMapping(path="/doCheck.do",produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public Map doCheck(HttpSession session, Orders orders) {
		try {
			Object o = session.getAttribute("emp");
			if(o==null) {
				return ajaxReturn(false, "当前用户未登录");
			}
			Emp emp = (Emp) o;
			orders.setChecktime(new Date()); //设置审核时间
			orders.setChecker(emp.getUuid()); //设置审核人
			orders.setState("1");
			ordersService.update(orders);
			return ajaxReturn(true, "审核成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "审核失败！");
	}

	
	//确认订单
	@RequestMapping(path="/doStart.do",produces="application/json;charset=utf-8")
		@ResponseBody
		public Map doStart(HttpSession session,Orders orders) {
		Object o = session.getAttribute("loginedEmp");
		if(o==null) {
			return ajaxReturn(true, "当前还有没登录");
		}
		Emp emp = (Emp) o;
		try {
			orders.setState("2"); //修改订单的状态为“已确认”
			orders.setStarttime(new Date()); //确认时间 
			orders.setStarter(((Emp)o).getUuid()); //审 核人
			ordersService.update(orders);
			return ajaxReturn(true,"订单确认成功");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ajaxReturn(false, "订单确认失败");
	}

	//订单入库
	@RequestMapping(path="/doInStore.do",produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public 	Map inStore (HttpSession session,Integer ordersdetailuuid, Integer storeuuid ) {
		try {
			Object o = session.getAttribute("emp");
			if(o==null) {
				return ajaxReturn(false, "当前还有没登录");
			}
			ordersService.doInstore(ordersdetailuuid,storeuuid,((Emp)o).getUuid());
			return ajaxReturn(true, "入库成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(false, "入库失败");
			
		}

	}
	
	}
