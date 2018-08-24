package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entor.entity.OrdersDetail;
import com.entor.mapper.OrdersDetailMapper;

import tk.mybatis.mapper.common.Mapper;
@Service
public class OrdersDetailServiceImpl extends BaseServiceImpl<OrdersDetail>{
	@Autowired
	private OrdersDetailMapper mapper;
	@Override
	public Mapper getMapper() {
		
		return this.mapper;
	}
	
}
