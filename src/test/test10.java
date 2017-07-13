package test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test10 {
	public static void main(String[] args) {
		
		String j = "{'a':'1','b':2}";
		JSONObject jo = JSONObject.fromObject(j);
		System.out.println(jo.getString("a"));
		System.out.println(jo.getString("b"));
		
		
		String j2 = "[{'a':'1','b':2},{'a':'3','b':4}]";
		JSONArray jo2 = JSONArray.fromObject(j2);
		System.out.println(jo2.getJSONObject(1).getString("b"));
	}
}
