package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.entor.entity.StoreAlert;
import com.entor.mapper.StoreAlertMapper;

import tk.mybatis.mapper.common.Mapper;

public class StoreAlertServiceImpl extends BaseServiceImpl<StoreAlert> {
	@Autowired
	private StoreAlertMapper mapper;
	
	@Override
	public Mapper getMapper() {
		
		return this.mapper;
	}

}
