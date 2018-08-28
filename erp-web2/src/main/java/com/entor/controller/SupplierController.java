package com.entor.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.ISupplierService;
import com.entor.entity.Supplier;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {
	@Autowired
	private ISupplierService supplierService;

	//加载供应商下拉表格数据
	@RequestMapping(path="/getComboData.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public List<Supplier> getComboData(String q,String type) {
		Supplier supplier = new Supplier();
		supplier.setType(type);
		supplier.setName(q);
		System.out.println(q);
		return supplierService.findByCond(supplier);
	}

}