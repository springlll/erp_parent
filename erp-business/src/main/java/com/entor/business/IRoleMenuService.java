package com.entor.business;

import com.entor.entity.RoleMenu;

public interface IRoleMenuService extends IBaseService<RoleMenu>{
	
	void addRoleMenus (long roleId, String[] menuIds);
}
