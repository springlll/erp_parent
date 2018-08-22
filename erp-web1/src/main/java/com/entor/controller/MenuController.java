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
		List<Menu> menus = menuService.findMenus(menu);
		Map map = new HashMap<>();
		System.out.println(menus);
		map.put("menus", menus);
		return map;
	}
}
