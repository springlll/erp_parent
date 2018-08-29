package com.entor.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.entor.business.ISupplierService;
import com.entor.entity.Supplier;
import com.entor.mapper.SupplierMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
@Transactional(readOnly = true)
public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements ISupplierService{
	@Autowired
	private SupplierMapper mapper;
	@Override
	public Mapper<Supplier> getMapper() {
		
		return this.mapper;
	}
	@Override
	public List<Supplier> findByCond(Supplier supplier) {
		Example example = new Example(Supplier.class);
		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(supplier.getName())) {
			c.andLike("name", "%" + supplier.getName() + "%");
		}
		c.andEqualTo("type", supplier.getType());
		return mapper.selectByExample(example);
	}

	}

