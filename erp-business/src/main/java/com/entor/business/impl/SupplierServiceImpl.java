package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.entor.business.IOrdersDetailService;
import com.entor.business.ISupplierService;
import com.entor.entity.OrdersDetail;
import com.entor.entity.Supplier;
import com.entor.mapper.OrdersDetailMapper;
import com.entor.mapper.SupplierMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements ISupplierService{
	@Autowired
	private SupplierMapper mapper;
	@Override
	public Mapper<Supplier> getMapper() {
		
		return this.mapper;
	}
	
}
