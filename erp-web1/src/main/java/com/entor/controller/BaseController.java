package com.entor.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	
	protected Map ajaxRetrun(boolean success, String message) {
		Map map = new HashMap();
		map.put("success",success);
		map.put("message", message);
		return map;
		
	}
}
