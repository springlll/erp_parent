package com.entor.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entor.business.IOrdersService;
import com.entor.entity.Orders;
import com.entor.entity.OrdersDetail;
import com.entor.entity.StoreDetail;
import com.entor.entity.StoreOper;
import com.entor.mapper.OrdersDetailMapper;
import com.entor.mapper.OrdersMapper;
import com.entor.mapper.StoreDetailMapper;
import com.entor.mapper.StoreOperMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public  class OrdersServiceImpl extends BaseServiceImpl<Orders> implements IOrdersService{
	@Autowired
	private OrdersMapper mapper;
	@Autowired
	private OrdersDetailMapper ordersDetailMapper;
	@Autowired
	private StoreDetailMapper storeDetailMapper;
	@Autowired StoreOperMapper storeOperMapper;
	@Override
	public Mapper<Orders> getMapper() {

		return this.mapper;
	}
	@Override
	public void addOrders(Orders orders) {
		orders.setType("1");
		orders.setState("0");
		orders.setCreatetime(new Date());
		mapper.add(orders);
		for(OrdersDetail  orderDetail : orders.getOrdersDetails()) {
			orderDetail.setState("0");
			orderDetail.setOrdersuuid(orders.getUuid());
			ordersDetailMapper.insertSelective(orderDetail);
		}
		
	}

	@Override
	public void doInstore(Integer ordersdetailuuid, Integer storeuuid, Long empuuid) {
		OrdersDetail ordersDetail = ordersDetailMapper.selectByPrimaryKey(
				ordersdetailuuid.longValue());
		ordersDetail.setEnder(empuuid.intValue()); //设置操作员
		ordersDetail.setEndtime(new Date()); //设置入库时间
		ordersDetail.setState("1"); //修改为“已入库”状态
		ordersDetailMapper.updateByPrimaryKeySelective(ordersDetail);
		//2.修改商品的库存
		StoreDetail storeDetail = new StoreDetail();
		storeDetail.setStoreuuid(storeuuid.longValue());
		storeDetail.setGoodsuuid(ordersDetail.getGoodsuuid());
		StoreDetail sd = storeDetailMapper.selectOne(storeDetail);
		//判断仓库库存中是否存在该商品的记录
		if (sd != null) { //如果不为空就代表该商品已经存在
			int amount = sd.getNum() + ordersDetail.getNum();
			sd.setNum(amount);
			storeDetailMapper.updateByPrimaryKeySelective(sd);
		} else { //如果商品不存在，往库存表中添加一条新的记录
			storeDetail.setNum(ordersDetail.getNum());
			storeDetailMapper.insertSelective(storeDetail);
		}
		//3. 添加仓库操作记录
		StoreOper storeOper = new StoreOper();
		storeOper.setEmpuuid(empuuid);
		storeOper.setOpertime(new Date());
		storeOper.setStoreuuid(storeuuid.longValue());
		storeOper.setGoodsuuid(ordersDetail.getGoodsuuid());
		storeOper.setNum(ordersDetail.getNum());
		storeOper.setType("1");
		storeOperMapper.insertSelective(storeOper);
		//4. 判断当前操作的订单明细是否全部入库，如果全部入库，将订单主表中的state修改为3
		OrdersDetail temp = new OrdersDetail();
		temp.setState("0");
		temp.setOrdersuuid(ordersDetail.getOrdersuuid());
		List<OrdersDetail> ordersDetailList  = ordersDetailMapper.select(temp);
		if (ordersDetailList.size()  == 0) { //如果集合为空，那么就代表所有商品已入库
			Orders orders = new Orders();
			orders.setUuid(ordersDetail.getOrdersuuid());
			orders.setState("3");
			orders.setEndtime(new Date());
			orders.setEnder(empuuid);
			mapper.updateByPrimaryKeySelective(orders);
		}
	}
}

