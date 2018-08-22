package com.entor.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entor.business.IOrdersService;
import com.entor.entity.Orders;
import com.entor.mapper.OrdersMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements IOrdersService{
	@Autowired
	private OrdersMapper mapper;
	@Override
	public Mapper<Orders> getMapper() {

		return this.mapper;
	}
	@Override
	public List<Orders> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int count(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
