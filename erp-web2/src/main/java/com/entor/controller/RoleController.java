package com.entor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IRoleService;
import com.entor.entity.Role;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

	@Autowired
	private IRoleService roleService;
	@RequestMapping("/list.do")
	public void list () {}
	
	@RequestMapping(path="/getData.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public List<Role> getData(){
		return roleService.findAll();
	}
	@RequestMapping(path="/saveRoleMenu.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map saveRoleMenu (long roleId, String menuIds) {
	 	try {
			String[] ids = menuIds.split(",");
			roleService.addRoleMenus(roleId, ids);
			return ajaxReturn(true, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReturn(false, "保存失败！");
	}
	
}
