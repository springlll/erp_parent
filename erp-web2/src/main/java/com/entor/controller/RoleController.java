package com.entor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public List<Role> getData(){
		return roleService.findAll();
	}
	
}
