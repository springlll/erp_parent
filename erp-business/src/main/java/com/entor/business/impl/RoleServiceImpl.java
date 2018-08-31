package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entor.business.IRoleService;
import com.entor.entity.Role;
import com.entor.mapper.RoleMapper;

import tk.mybatis.mapper.common.Mapper;

@Service

public class RoleServiceImpl  extends BaseServiceImpl<Role> implements IRoleService{
	 @Autowired
	 private RoleMapper mapper;
	@Override
	public Mapper<Role> getMapper() {

		return this.mapper;
	}
	@Override
	public void addRoleMenus(long roleId, String[] ids) {
		// TODO Auto-generated method stub
		
	}

}
