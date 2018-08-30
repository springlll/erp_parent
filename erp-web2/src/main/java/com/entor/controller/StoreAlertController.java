package com.entor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IStoreAlertService;
import com.entor.entity.StoreAlert;

@Controller
@RequestMapping("/storealert")
public class StoreAlertController extends BaseController{
	
	@Autowired
	private IStoreAlertService storeAlertService;
	
	@RequestMapping("/list.do")
	public void list() {}

	@RequestMapping(path="/getData.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public List<StoreAlert> getData(){
		return storeAlertService.findAll();
	}
	
}
