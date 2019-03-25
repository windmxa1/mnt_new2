package org.tool;

import java.util.HashMap;
import java.util.Map;

public class R {
	public static Object getJson(int code,String msg,Object data){
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);
		return map;
	}
}
