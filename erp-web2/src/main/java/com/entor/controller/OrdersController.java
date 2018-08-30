package com.entor.controller;

import java.awt.Font;
import java.io.IOError;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
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

@RequestMapping("/chart.do")
public void getOrdersChart(HttpServletResponse response,Date startDate,Date endDate) throws IOException {
	
	DefaultPieDataset dataset = new DefaultPieDataset();
	List<OrderReport> orderReports= ordersService.getOrderReport(startDate, endDate);
	for(OrderReport orderReport : orderReports) {
		
		dataset.setValue(orderReport.getName(),orderReport.getMoney());
	}
	JFreeChart chart = ChartFactory.createPieChart("销售统计表", dataset, false, false, false);
	chart.setTitle(new TextTitle());
	PiePlot plot = (PiePlot) chart.getPlot();
	plot.setLabelFont(new Font("宋体",Font.BOLD,15));
	ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 350, 500);
}

@RequestMapping(path="/exportExcel.do")
	
	public void exportExcel(HttpServletResponse response,Date startDate,Date endDate) throws IOException{
	response.setHeader("Content-Disposition", "attachement;filename="+URLEncoder.encode("销售统计表.xls","utf-8"));
	HSSFWorkbook wb = new HSSFWorkbook();
	HSSFSheet sheet = wb.createSheet("销售统计表");
	List<OrderReport> orderReports = getOrderReportData(startDate, endDate);
	int rowIndex = 0;
	for (OrderReport orderReport : orderReports) {
	
	HSSFRow row = sheet.createRow(rowIndex++);
	
	HSSFCell nameCell = row.createCell(0);
	HSSFCell moneyCell = row.createCell(1);
	nameCell.setCellValue(orderReport.getName());
	moneyCell.setCellValue(orderReport.getMoney());
	}
	wb.write(response.getOutputStream());
	wb.close();
	
}
}

