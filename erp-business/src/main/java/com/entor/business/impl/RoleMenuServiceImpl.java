package com.entor.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.entor.business.IRoleMenuService;
import com.entor.business.IRoleService;
import com.entor.entity.RoleMenu;
import com.entor.mapper.RoleMenuMapper;

import tk.mybatis.mapper.common.Mapper;

public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu> implements IRoleMenuService{
	
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Override
	public void addRoleMenus(long roleId, String[] menuIds) {
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setMenuuuid(roleId);
		roleMenuMapper.delete(roleMenu);
		for(String menuId : menuIds) {
			roleMenu.setMenuuuid(Long.parseLong(menuId));
			roleMenuMapper.insertSelective(roleMenu);
		}
		
	}
	@Override
	public Mapper<RoleMenu> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
