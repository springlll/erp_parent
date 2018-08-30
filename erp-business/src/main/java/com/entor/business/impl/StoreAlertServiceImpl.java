package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entor.business.IStoreAlertService;
import com.entor.entity.StoreAlert;
import com.entor.mapper.StoreAlertMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public class StoreAlertServiceImpl extends BaseServiceImpl<StoreAlert> implements IStoreAlertService {
	
	@Autowired
	private StoreAlertMapper mapper;

	@Override
	public Mapper<StoreAlert> getMapper() {
		return this.mapper;
	}

}
