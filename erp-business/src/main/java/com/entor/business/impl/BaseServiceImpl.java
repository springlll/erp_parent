package com.entor.business.impl;

import java.util.List;

import com.entor.business.IBaseService;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseServiceImpl<T> implements IBaseService<T>{

	public abstract Mapper<T> getMapper();
	@Override
	public List<T> find() {
		// TODO Auto-generated method stub
		return getMapper().selectAll();
	}

	@Override
	public List<T> find(T entity) {
		// TODO Auto-generated method stub
		return getMapper().select(entity);
	}

	@Override
	public T get(T entity) {
		// TODO Auto-generated method stub
		return getMapper().selectOne(entity);
	}

	@Override
	public void add(T entity) {
		// TODO Auto-generated method stub
		getMapper().insertSelective(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getMapper().updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		getMapper().deleteByPrimaryKey(entity);
	}
	@Override
	public T findById(int uuid) {
		return getMapper().selectByPrimaryKey(uuid);
	}
}
