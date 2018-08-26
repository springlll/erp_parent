
package com.entor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IGoodsService;
import com.entor.entity.Goods;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {
	@Autowired
	private IGoodsService goodsService;

	@RequestMapping(path="/getComboData.do", produces={"application/json;charset=utf-8"})
	@ResponseBody
	public List<Goods> getComboData() {
		return goodsService.findAll();
	}
	
}