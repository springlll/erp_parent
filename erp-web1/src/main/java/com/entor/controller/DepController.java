package com.entor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entor.business.IDepBus;
import com.entor.entity.Dep;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/dep")

public class DepController extends BaseController {
	@Autowired
	private IDepBus depBus;

	@RequestMapping(path="/list.do")
	public List<Dep> list(Dep dep)  {
		System.out.println("dep"+depBus);
		return depBus.findDeps(dep);
	}

	@RequestMapping(path="/getData.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map getData(Dep dep, @RequestParam(value="page",defaultValue="1")Integer page, @RequestParam(value="rows",defaultValue="16")Integer rows) {

		PageHelper.startPage(page, rows);
				if(page==null) {
			page=1;
		}
		if(rows==null) {
			rows=1;
		}
		List<Dep> depList = depBus.findDeps(dep);
		int total = depBus.getTotal(dep);
		Map map = new HashMap();
		map.put("rows", depList);
		map.put("total", total);
		return map;
	}

	@RequestMapping(path="/list1.do", produces="application/json;charset=utf-8")
	@ResponseBody
	public List<Dep> list1(Dep dep) {
		return depBus.findAllDeps(dep);
	}

@RequestMapping(path="/add.do",produces="application/json;charset=utf-8")
@ResponseBody
		public Map add(Dep dep) {
	try {
		depBus.addDep(dep);
		return ajaxRetrun(true, "添加成功");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return ajaxRetrun(true, "添加失败");
}
}



