package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entor.business.IStoreService;
import com.entor.business.ISupplierService;
import com.entor.entity.Store;
import com.entor.mapper.StoreMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
@Transactional(readOnly=true)
public class StoreServiceImpl extends BaseServiceImpl<Store> implements IStoreService{
	@Autowired
	private StoreMapper storeMapper;
	@Override
	public Mapper<Store> getMapper() {

		
		return this.storeMapper;
	}

}
