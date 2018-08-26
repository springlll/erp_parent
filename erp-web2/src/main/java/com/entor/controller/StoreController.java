package com.entor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entor.business.IStoreService;
import com.entor.entity.Store;

@Controller
@RequestMapping("/store")
public class StoreController extends BaseController{
	@Autowired
	private IStoreService storeService;
	@RequestMapping(path="/getComboData.do",produces="application/json;charset=utf-8")
	public List<Store> getComboData(){
		return storeService.findAll();
	}
}
