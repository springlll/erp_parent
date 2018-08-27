package com.entor.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entor.business.IMenuService;
import com.entor.entity.Menu;
import com.entor.mapper.MenuMapper;

@Service
public class MenuServiceImpl implements IMenuService{
	@Autowired
	private MenuMapper mapper;
	@Override
	public List<Menu> findMenus(Menu menu) {
		
		List<Menu> menus1 = mapper.select(menu);
		for(Menu menu1 : menus1) {
			Menu m = new Menu();
			m.setPid(menu1.getMenuid());
			List menus2 = mapper.select(m);
			menu1.setMenus(menus2);
			
		}
		List<Menu> list = new ArrayList<>();
		for (int i=menus1.size()-1;i>=0;i--) {
			list.add(menus1.get(i));
		}
		return list;
	}

}
