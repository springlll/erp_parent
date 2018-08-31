package com.entor.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entor.business.IMenuService;
import com.entor.entity.Menu;
import com.entor.entity.Orders;
import com.entor.entity.Tree;
import com.entor.mapper.MenuMapper;

import tk.mybatis.mapper.common.Mapper;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService{
	@Autowired
	private MenuMapper mapper;
	@Override
	public Mapper<Menu> getMapper(){
		return this.mapper;
	}
	@Override
	public List<Tree> getMenuTree(long roleuuid) {
		
		List<Tree> trees = new  ArrayList<Tree>();
		List<Menu> roleMenus = mapper.getRolesMenu(roleuuid);
		Menu menu = new Menu();
		menu.setPid("0");
		List<Menu> menus1 = mapper.select(menu);
		for(Menu menu1 : menus1) {
			Tree tree = new Tree();
			tree.setId(menu1.getMenuid());
			tree.setText(menu1.getMenuname());
			menu = new Menu();
			menu.setPid(menu1.getMenuid());
			
			List<Menu> menus2 = mapper.select(menu);
			for (Menu menu2:menus2) {
				Tree  tree2 = new Tree();
				tree2.setId(menu2.getMenuid());
				tree.getChildren().add(tree2);
				if(roleMenus.contains(menus2)) {
					tree2.setChecked(true);
				}
				tree.getChildren().add(tree2);
				}
			trees.add(tree);
		}

		return trees;
	}



}
