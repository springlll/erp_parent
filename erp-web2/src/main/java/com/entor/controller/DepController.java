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

public class DepController extends SysController {
	@Autowired
	private IDepBus depBus;

	@RequestMapping(path="/list.do")
	public  void list()  {
		System.out.println("dep"+depBus);

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
		List<Dep> depList = depBus.findAllDeps(dep);
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
		depBus.add(dep);
		return ajaxReturn(true, "添加成功");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return ajaxReturn(false, "添加失败");
}

@RequestMapping(path="/del.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public Map delDep(Dep dep) {
	try {
		depBus.delete(dep);
		return ajaxReturn(true,"删除成功");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return ajaxReturn(false, "失败");
}

@RequestMapping(path="/get.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public Dep get(int uuid) {
	System.out.println("uuid"+uuid);
	return depBus.findById(uuid);
	
}
@RequestMapping(path="/update.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public Map updateDep(Dep dep) {
	try {
		depBus.update(dep);
		return ajaxReturn(true, "修改成功");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return ajaxReturn(false, "失败");
}
}



