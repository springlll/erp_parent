package com.entor.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.entor.business.IDepBus;
import com.entor.entity.Dep;
import com.entor.mapper.DepMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
@Transactional(readOnly=true)
public class IDepBusImpl extends BaseServiceImpl<Dep> implements IDepBus{

	@Autowired
	private DepMapper mapper ;


	@Override
	public List<Dep> findAllDeps(Dep dep) {
		//构造查询条件
		Example example = new Example(Dep.class);
		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(dep.getName())) {
			c.andLike("name", "%" + dep.getName() + "%");
		}
		if (!StringUtils.isEmpty(dep.getTele())) {
			c.andLike("tele", dep.getTele());
		}
	//设置排序
		example.setOrderByClause("uuid desc");
		return mapper.selectByExample(example);
	}
	@Override
	public int getTotal(Dep dep) {
		//构造查询条件
		Example example = new Example(Dep.class);
		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(dep.getName())) {
			c.andLike("name", "%" + dep.getName() + "%");
		}
		if (!StringUtils.isEmpty(dep.getTele())) {
			c.andLike("tele", dep.getTele());
		}
		return mapper.selectCountByExample(example);
	

	}

	@Override
	public void update(Dep dep) {		
		mapper.updateByPrimaryKeySelective(dep);		
	}
	@Override
	public Mapper<Dep> getMapper() {
		// TODO Auto-generated method stub
		return this.mapper;
	}


	
}
