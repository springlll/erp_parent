package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.entor.business.IOrdersDetailService;
import com.entor.entity.OrdersDetail;
import com.entor.mapper.OrdersDetailMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public class OrdersDetailServiceImpl extends BaseServiceImpl<OrdersDetail> implements IOrdersDetailService{
	@Autowired
	private OrdersDetailMapper mapper;
	@Override
	public Mapper<OrdersDetail> getMapper() {
		
		return this.mapper;
	}
	
}
