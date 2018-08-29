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
import com.entor.entity.OrderReport;
import com.entor.entity.Orders;
import com.entor.entity.OrdersDetail;
import com.entor.exception.OutOfStockException;
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
	@RequestMapping("/outstore.do")
	public void outstore() {}
	@RequestMapping("/report.do")
	public void report() {}
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
	
			orderList =	ordersService.find(orders);
		total = ordersService.count(orders);
		

		Map map = new HashMap();
		map.put("rows", orderList);
		map.put("total", total);
		System.out.println(map);
		return map;	
	}
	//添加订单
	@RequestMapping(path="/addOrder.do",produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public Map addOrder(HttpSession session,Orders orders  ,String json,Long uuid) {
		
			System.out.println("uuid:"+uuid);
			Object o = session.getAttribute("emp");
			if(o == null) {
				return ajaxReturn(false, "当前还没有登录");
				}else {
			try {
			Emp emp = (Emp) o;
			orders.setCreater(emp.getUuid()); //设置下单人
			orders.setCreatetime(new Date()); //设置下单时间
			orders.setSupplieruuid(uuid);
			/*orders.setType("1"); //采购类型为“采购订单”
*/			orders.setState("0"); //订单状态为“未审核”
			//把表格的json格式数据转换成OrderDetail集合 
			List<OrdersDetail> orderDetails = JSON.parseArray(json, OrdersDetail.class);
			orders.setOrderDetails(orderDetails);
			ordersService.addOrders(orders);
				System.out.println("orders = "+ orders );
				return ajaxReturn(true, "添加成功");
	
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
			return ajaxReturn(false, "添加失败");
		}
		
	//订单审核
	@RequestMapping(path="/doCheck.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Map doCheck(HttpSession session, Orders orders) {
		try {
			Object o = session.getAttribute("emp");
			if (o == null) {
				return ajaxReturn(false, "请先登录");
			}
			orders.setState("1"); //修改订单的状态为“已审核”
			orders.setChecktime(new Date()); //审核时间
			orders.setChecker(((Emp)o).getUuid()); //审核人
			ordersService.update(orders);
			return ajaxReturn(true, "审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(false, "审核失败");
		}
	}

	
	//订单确认
	@RequestMapping(path="/doStart.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Map doStart(HttpSession session, Orders orders) {
		try {
			Object o = session.getAttribute("emp");
			if (o == null) {
				return ajaxReturn(false, "请先登录");
			}
			orders.setState("2"); //修改订单的状态为“已确认”
			orders.setStarttime(new Date()); //确认时间
			orders.setStarter(((Emp)o).getUuid()); //审核人
			ordersService.update(orders);
			return ajaxReturn(true, "订单确认成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(false, "订单确认失败");
		}
	}
	

	//订单入库
	@RequestMapping(path="/doInStore.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Map doInStore(HttpSession session, Integer ordersdetailuuid, Integer storeuuid) {
		System.out.println("ordersdetailuuid = " + ordersdetailuuid + ", storeuuid = " + storeuuid);
		try {
			Object o = session.getAttribute("emp");
			if (o == null) {
				return ajaxReturn(false, "请先登录");
			}
			ordersService.doInstore(ordersdetailuuid, storeuuid, ((Emp)o).getUuid());
			return ajaxReturn(true, "入库操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(false, "入库操作失败");
		}
	}
	//订单出库
	@RequestMapping(path="/doOutStore.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Map doOutStore(HttpSession session, Integer ordersdetailuuid, Integer storeuuid) {
		System.out.println("ordersdetailuuid = " + ordersdetailuuid + ", storeuuid = " + storeuuid);
		try {
			Object o = session.getAttribute("emp");
			if (o == null) {
				return ajaxReturn(false, "请先登录");
			}
			ordersService.doOutstore(ordersdetailuuid, storeuuid, ((Emp)o).getUuid());
			return ajaxReturn(true, "出库操作成功");
		} 
		catch(OutOfStockException e){
			return ajaxReturn(false, e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return ajaxReturn(false, "出库操作失败");
		}
	}
@RequestMapping(path="/report.do",produces= {"application/json;charset=utf-8"})
@ResponseBody
public List<OrderReport> getOrderReportData( Date startDate,Date endDate){
	System.out.println(startDate+":"+endDate);
	return ordersService.getOrderReport(startDate, endDate);
}


}

