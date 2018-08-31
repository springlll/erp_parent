package com.entor.business;

import com.entor.entity.Role;

public interface IRoleService extends IBaseService<Role>{

	void addRoleMenus(long roleId, String[] ids);

}
