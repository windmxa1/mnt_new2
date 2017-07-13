package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.tool.GetRandom;

import com.opensymphony.xwork2.ActionSupport;

public class DeviceTestAction extends ActionSupport {
	List result;

	public String execute() {
		return SUCCESS;
	}

	// 水浸 test1.action
	public String test1() {
		List<Map<String, String>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			String temp = GetRandom.get2random()+GetRandom.get1random();
			map.put("hostIp", "192.168.1." + temp);
			map.put("deviceType", "861");
			map.put("deviceSubType", "DS-K26012015" + GetRandom.get4random()
					+ "V010100CH57751" + GetRandom.get4random());
			map.put("deviceStatus", "OK");
			map.put("hostId", "1" + GetRandom.get4random());
			map.put("is_device_online", "OK");
			map.put("IPADDRESS", "192.168.1." + temp);
			map.put("contact", "防水工作负责人");
			map.put("location", GetRandom.getString());
			list.add(map);
		}
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;

	}

	// 消防 test2.action
	public String test2() {
		List<Map<String, String>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			String temp = GetRandom.get2random()+GetRandom.get1random();
			map.put("hostIp", "192.168.1." + temp);
			map.put("deviceType", "862");
			map.put("deviceSubType", "DS-K26012015" + GetRandom.get4random()
					+ "V010100CH57751" + GetRandom.get4random());
			map.put("deviceStatus", "OK");
			map.put("hostId", "1" + GetRandom.get4random());
			map.put("is_device_online", "OK");
			map.put("IPADDRESS", "192.168.1." + temp);
			map.put("contact", "消防工作负责人");
			map.put("location", GetRandom.getString());
			list.add(map);
		}
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}
	// UPS test3.action

	public String test3() {
		List<Map<String, String>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			String temp = GetRandom.get2random()+GetRandom.get1random();
			map.put("hostIp", "192.168.1." + temp);
			map.put("deviceType", "863");
			map.put("deviceSubType", "DS-K26012015" + GetRandom.get4random()
					+ "V010100CH57751" + GetRandom.get4random());
			map.put("deviceStatus", "OK");
			map.put("hostId", "1" + GetRandom.get4random());
			map.put("is_device_online", "OK");
			map.put("IPADDRESS", "192.168.1." + temp);
			map.put("contact", "UPS配电柜负责人");
			map.put("location", GetRandom.getString());
			list.add(map);
		}
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	// 空调监测 test4.action
	public String test4() {
		List<Map<String, String>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			String temp = GetRandom.get2random()+GetRandom.get1random();
			map.put("hostIp", "192.168.1." + temp);
			map.put("deviceType", "864");
			map.put("deviceSubType", "DS-K26012015" + GetRandom.get4random()
					+ "V010100CH57751" + GetRandom.get4random());
			map.put("deviceStatus", "OK");
			map.put("hostId", "1" + GetRandom.get4random());
			map.put("is_device_online", "OK");
			map.put("IPADDRESS", "192.168.1." + temp);
			map.put("contact", "空调监测负责人");
			map.put("location", GetRandom.getString());
			list.add(map);
		}
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	// 温湿度 test5.action
	public String test5() {
		List<Map<String, String>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			String temp = GetRandom.get2random()+GetRandom.get1random();
			map.put("hostIp", "192.168.1." + temp);
			map.put("deviceType", "865");
			map.put("deviceSubType", "DS-K26012015" + GetRandom.get4random()
					+ "V010100CH57751" + GetRandom.get4random());
			map.put("deviceStatus", "OK");
			map.put("hostId", "1" + GetRandom.get4random());
			map.put("is_device_online", "OK");
			map.put("IPADDRESS", "192.168.1." + temp);
			map.put("contact", "温湿度监测负责人");
			map.put("location", GetRandom.getString());
			list.add(map);
		}
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
}
