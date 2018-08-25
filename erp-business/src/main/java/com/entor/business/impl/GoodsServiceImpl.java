package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entor.business.IGoodsService;

import com.entor.entity.Goods;
import com.entor.mapper.GoodsMapper;

import tk.mybatis.mapper.common.Mapper;
@Service	
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService{
	@Autowired
	private GoodsMapper mapper;
	@Override
	public Mapper<Goods> getMapper() {
		
		return this.mapper;
	}
}
