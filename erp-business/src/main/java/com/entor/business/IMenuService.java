package com.entor.business;

import java.util.List;

import com.entor.entity.Menu;
import com.entor.entity.Tree;

import tk.mybatis.mapper.common.Mapper;

public interface IMenuService extends IBaseService<Menu>{
	/*List<Menu> findMenus(Menu menu);*/
	List<Tree> getMenuTree(long  roleuuid);




	
}
