package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entor.business.IEmpService;
import com.entor.entity.Emp;
import com.entor.mapper.EmpMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public  class EmpServiceImpl extends BaseServiceImpl<Emp> implements IEmpService{
	 @Autowired
	 private EmpMapper mapper;
	 
	@Override
	public Mapper<Emp> getMapper() {
		return this.mapper;
	}

}
