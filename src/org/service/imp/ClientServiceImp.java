package org.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.GroupsDao;
import org.dao.HistoryDao;
import org.dao.HistoryUintDao;
import org.dao.HostDao;
import org.dao.ItemsDao;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HistoryDaoImp;
import org.dao.imp.HistoryUintDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.model.History;
import org.model.HistoryUint;
import org.model.Items;
import org.service.ClientService;
import org.tool.ChangeTime;

public class ClientServiceImp implements ClientService{
	private List getSomeInfo(String tmp){
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		
		List<Object[]> list = gDao.getHostListByGroupid02((long)25);	//获取客户端的所有主机
		
		List<Map<String, String>> returnList = new ArrayList<>();
		
		for(Object[] o : list){		//每一个host主机的host对象
			String id = o[0].toString();	//id
			String ip = o[1].toString();	//ip
			List<Items> iList = iDao.getItemsByHostid(Long.parseLong(id));
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("ip", ip);
			for(Items i:iList){	//每一个item项的对象
				int type = i.getValueType();
				if(type==0){	//type==0，表示item项保存的数据为 浮点 类型，存在 history 表中
					HistoryDao hDao = new HistoryDaoImp();
					History h = hDao.getItemsValueByItemId(i.getItemid());//历史记录中最新的一条数据对象
					if(h!=null){	//有item项，但是还得在历史记录表中有数据，才能返回值
						if(i.getName().startsWith(tmp)){	//根据类型来分类，cpu,mem,net,disk
							if(tmp.equals("disk")){	//disk磁盘 有特殊情况（主要用于过滤掉移动硬盘和U盘插入后拔出造成的没有最新数据但返回当时的数据的情况）
								int clock = h.getId().getClock();	//最新数据的时间戳
								int now = (int) (new Date().getTime()/1000);
								if(now-clock<=300){		//最新数据距离当前时间<5分钟，才算存活
									String iValue = ""+h.getId().getValue();
									map.put(i.getName(), iValue);
								}
							}
							else{
//								String cl = ChangeTime.TimeStamp2Date(""+h.getId().getClock(), "HH:mm:ss");
								String iValue = ""+h.getId().getValue();
								map.put(i.getName(), iValue);
							}
						}
					}
				}
				else if(type==3){//type==3，表示item项保存的数据为 数字 类型，存在 history_uint 表中
					HistoryUintDao huDao = new HistoryUintDaoImp();
					HistoryUint hu = huDao.getItemsValueByItemId(i.getItemid());
					if(hu!=null){
						if(i.getName().startsWith(tmp)){
							String iValue = ""+hu.getId().getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
			}
			returnList.add(map);
		}
		return returnList;
	}
	
	public List getClientInfo() {
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		
		List<Object[]> list = gDao.getHostListByGroupid02((long)25);	//获取客户端的所有主机
		
		List<Map<String, String>> returnList = new ArrayList<>();
		
		for(Object[] o : list){		//每一个host主机的host对象
			String id = o[0].toString();	//id
			String ip = o[1].toString();	//ip
			List<Items> iList = iDao.getItemsByHostid(Long.parseLong(id));
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("ip", ip);
			for(Items i:iList){	//每一个item项的对象
				int type = i.getValueType();
				if(type==0){	//type==0，表示item项保存的数据为 浮点 类型，存在 history 表中
					HistoryDao hDao = new HistoryDaoImp();
					History h = hDao.getItemsValueByItemId(i.getItemid());
					if(h!=null){
						if(i.getName().startsWith("disk")){
							int clock = h.getId().getClock();	//最新数据的时间戳
							int now = (int) (new Date().getTime()/1000);
							if(now-clock<=300){		//最新数据距离当前时间<5分钟，才算存活
								String iValue = ""+h.getId().getValue();
								map.put(i.getName(), iValue);
							}
						}else {
							String iValue = ""+h.getId().getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
				else if(type==3){//type==3，表示item项保存的数据为 数字 类型，存在 history_uint 表中
					HistoryUintDao huDao = new HistoryUintDaoImp();
					HistoryUint hu = huDao.getItemsValueByItemId(i.getItemid());
					if(hu!=null){
						String iValue = ""+hu.getId().getValue();
						map.put(i.getName(), iValue);
					}
				}
			}
			returnList.add(map);
		}
		return returnList;
	}

	@Override
	public List getClientCpuInfo() {
		String tmp = "cpu";
		return getSomeInfo(tmp);
	}

	@Override
	public List getClientMemInfo() {
		String tmp ="mem";
		return getSomeInfo(tmp);
	}

	@Override
	public List getClientNetInfo() {
		String tmp="net";
		return getSomeInfo(tmp);
	}

	@Override
	public List getClientDiskInfo() {
		String tmp="disk";
		return getSomeInfo(tmp);
	}

//	@Override
//	public List getClientCpu10Info() {
//		GroupsDao gDao = new GroupsDaoImp();
//		ItemsDao iDao = new ItemsDaoImp();
//		
//		List<Object[]> list = gDao.getHostListByGroupid02((long)25);	//获取客户端的所有主机
//		
//		List<Map<String, String>> returnList = new ArrayList<>();
//		
//		for(Object[] o : list){		//每一个host主机的host对象
//			String id = o[0].toString();	//id
//			String ip = o[1].toString();	//ip
//			
//			Items i = iDao.getItemByName(Long.parseLong(id), "cpu_percent");
//			HistoryDao hDao = new HistoryDaoImp();
//			List<Object[]> li= hDao.getItemsValueByItemId_10time(i.getItemid());
//			for(Object[] oo:li){
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("ip", ip);
//				String time=ChangeTime.TimeStamp2Date(""+oo[0],"HH:mm:ss");
//				map.put("time", ""+time);
//				map.put("cpu", ""+oo[1]);
//				returnList.add(map);
//			}
//		}
//		return returnList;
//	}
	@Override
	public Map getClientCpu10Info() {
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		
		List<Object[]> list = gDao.getHostListByGroupid02((long)25);	//获取客户端的所有主机
		
		Map<String, List<Map<String,String>>> m1 = new HashMap<>();
		
		for(Object[] o : list){		//每一个host主机的host对象
			List<Map<String, String>> cList = new ArrayList<Map<String,String>>();	//用于存放map
			String id = o[0].toString();	//id
			String ip = o[1].toString();	//ip
			
			Items i = iDao.getItemByName(Long.parseLong(id), "cpu_percent");
			HistoryDao hDao = new HistoryDaoImp();
			List<Object[]> li =hDao.getItemsValueByItemId_10time(i.getItemid());
			for(Object[] oo : li){
				Map<String, String> map = new HashMap<>();
				String time=ChangeTime.TimeStamp2Date(""+oo[0],"HH:mm:ss");
				map.put("time", ""+time);
				map.put("cpu", ""+oo[1]);
				cList.add(map);
			}
			m1.put(ip, cList);
		}
		return m1;
	}

	@Override
	public List getClientOtherInfo() {
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		List<Object[]> list = gDao.getHostListByGroupid02((long)25);	//获取客户端的所有主机
		List<Map<String, String>> returnList = new ArrayList<>();		//最终数据结构
		for(Object[] o : list){		//每一个host主机的host对象
			String id = o[0].toString();	//id
			String ip = o[1].toString();	//ip
			List<Items> iList = iDao.getItemsByHostid(Long.parseLong(id));	//主机下的所有监控项
			
			Map<String, String> map = new HashMap<String, String>();		//用于存放监控项的键值对
			map.put("ip", ip);
			for(Items i:iList){	//每一个item项的对象
				int type = i.getValueType();
				if(type==0){	//type==0，表示item项保存的数据为 浮点 类型，存在 history 表中
					HistoryDao hDao = new HistoryDaoImp();
					History h = hDao.getItemsValueByItemId(i.getItemid());//历史记录中最新的一条数据对象
					if(h!=null){	//有item项，但是还得在历史记录表中有数据，才能返回值
						if(!i.getName().startsWith("disk") && !i.getName().startsWith("mem") && !i.getName().startsWith("net") && !i.getName().startsWith("cpu")){
							String iValue = ""+h.getId().getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
				else if(type==3){//type==3，表示item项保存的数据为 数字 类型，存在 history_uint 表中
					HistoryUintDao huDao = new HistoryUintDaoImp();
					HistoryUint hu = huDao.getItemsValueByItemId(i.getItemid());
					if(hu!=null){
						if(!i.getName().startsWith("disk") && !i.getName().startsWith("mem") && !i.getName().startsWith("net") && !i.getName().startsWith("cpu")){
							String iValue = ""+hu.getId().getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
			}
			returnList.add(map);
		}
		return returnList;
	}

	@Override
	public List getClientMemInfo02() {
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		List<Object[]> list = gDao.getHostListByGroupid02((long)25);	//获取客户端的所有主机
		List<Map<String, String>> returnList = new ArrayList<>();
		for(Object[] o : list){
			List<Map<String, String>> cList = new ArrayList<Map<String,String>>();	//用于存放map
			String id = o[0].toString();
			String ip = o[0].toString();

			Items mpu = iDao.getItemByName(Long.parseLong(id), "mem_pused");
			
			
			
		}
		
		
		return null;
	}
}
