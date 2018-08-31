package com.entor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IMenuService;
import com.entor.entity.Menu;
import com.entor.entity.Tree;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	@Autowired
	private IMenuService menuService;
	@RequestMapping(path="/getData.do")
	@ResponseBody
	public Map getData() {
		
		Menu menu = new Menu();
		menu.setPid("0");
		List<Menu> menus1 = menuService.find(menu);
		for(Menu menu1 : menus1) {
			menu = new Menu();
			menu.setPid(menu1.getMenuid());
			List<Menu> menu2 = menuService.find(menu);
			menu1.setMenus(menu2);
		}
		Map map = new HashMap<>();
		System.out.println(menu);
		map.put("menus", menus1);
		return map;
	}
	
@RequestMapping("/tree.do")
	public void tree(){}

	@RequestMapping(path="/getRoleMenus.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public List<Tree> getRoleMenus(Long roleuuid){
		return menuService.getMenuTree(roleuuid);
	}
}
