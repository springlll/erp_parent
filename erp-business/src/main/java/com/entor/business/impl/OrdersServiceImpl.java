package com.entor.business.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import com.entor.business.IOrdersService;
import com.entor.entity.Orders;
import com.entor.entity.OrdersDetail;
import com.entor.mapper.OrdersDetailMapper;
import com.entor.mapper.OrdersMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public  class OrdersServiceImpl extends BaseServiceImpl<Orders> implements IOrdersService{
	@Autowired
	private OrdersMapper mapper;
	@Autowired
	private OrdersDetailMapper ordersDetailMapper;
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


}
