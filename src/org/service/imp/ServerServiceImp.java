package org.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.GroupsDao;
import org.dao.HistoryDao;
import org.dao.HistoryTextDao;
import org.dao.HistoryUintDao;
import org.dao.HostDao;
import org.dao.ItemsDao;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HistoryDaoImp;
import org.dao.imp.HistoryTextDaoImp;
import org.dao.imp.HistoryUintDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.model.History;
import org.model.HistoryText;
import org.model.HistoryUint;
import org.model.Items;
import org.service.ClientService;
import org.service.ServerService;
import org.tool.ChangeTime;

public class ServerServiceImp implements ServerService{
	private List getSomeInfo(String tmp){
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		
		List<Map<String, String>> returnList = new ArrayList<>();
		
		List<Object[]> list = gDao.getHostListByGroupid02(4L);		//获取服务器的所有主机
		for(Object[] o:list){
			String id = o[0].toString();	//host id
			String ip = o[1].toString();	//host ip
			
			List<Items> iList = iDao.getItemsByHostid(Long.parseLong(id));
			
			Map<String, String> map = new HashMap<>();
			map.put("ip", ip);
			for(Items i:iList){		//每一个item项对象
				int type = i.getValueType();
				if(type==0){		//浮点类型
					HistoryDao hDao = new HistoryDaoImp();
					History h = hDao.getItemsValueByItemId(i.getItemid());
					if(h!=null){
						if(i.getName().startsWith(tmp)){
							String iValue = ""+h.getId().getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
				else if(type==3){	//整数
					HistoryUintDao huDao = new HistoryUintDaoImp();
					HistoryUint hu = huDao.getItemsValueByItemId(i.getItemid());
					if(hu!=null){
						if(i.getName().startsWith(tmp)){
							String iValue = ""+hu.getId().getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
				else if(type==4){	//文本
					HistoryTextDao htDao = new HistoryTextDaoImp();
					HistoryText ht = htDao.getItemsValueByItemId(i.getItemid());
					if(ht!=null){
						if(i.getName().startsWith(tmp)){
							String iValue = ""+ht.getValue();
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
	public List getServerInfo() {
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		
		List<Map<String, String>> returnList = new ArrayList<>();
		
		List<Object[]> list = gDao.getHostListByGroupid02(4L);
		for(Object[] o:list){
			String id = o[0].toString();	//host id
			String ip = o[1].toString();	//host ip
			
			List<Items> iList = iDao.getItemsByHostid(Long.parseLong(id));
			
			Map<String, String> map = new HashMap<>();
			map.put("ip", ip);
			for(Items i:iList){		//每一个item项对象
				int type = i.getValueType();
				if(type==0){		//浮点类型
					HistoryDao hDao = new HistoryDaoImp();
					History h = hDao.getItemsValueByItemId(i.getItemid());
					if(h!=null){
						String iValue = ""+h.getId().getValue();
						map.put(i.getName(), iValue);
					}
				}
				else if(type==3){	//整数
					HistoryUintDao huDao = new HistoryUintDaoImp();
					HistoryUint hu = huDao.getItemsValueByItemId(i.getItemid());
					if(hu!=null){
						String iValue = ""+hu.getId().getValue();
						map.put(i.getName(), iValue);
					}
				}
				else if(type==4){	//文本
					HistoryTextDao htDao = new HistoryTextDaoImp();
					HistoryText ht = htDao.getItemsValueByItemId(i.getItemid());
					if(ht!=null){
						String iValue = ""+ht.getValue();
						map.put(i.getName(), iValue);
					}
				}
			}
			returnList.add(map);
		}
		return returnList;
	}

	@Override
	public List getServerCpuInfo() {
		String tmp  = "cpu";
		return getSomeInfo(tmp);
	}

	@Override
	public List getServerMemInfo() {
		String tmp = "mem";
		return getSomeInfo(tmp);
	}

	@Override
	public List getServerNetInfo() {
		String tmp = "net";
		return getSomeInfo(tmp);
	}

	@Override
	public List getServerDiskInfo() {
		String tmp = "disk";
		return getSomeInfo(tmp);
	}
	
	@Override
	public List getServerCpu10Info() {
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		
		List<Object[]> list = gDao.getHostListByGroupid02(4L);
		
		List<Map<String, String>> returnList = new ArrayList<>();
		
		for(Object[] o: list){
			String id = o[0].toString();
			String ip = o[1].toString();
			
			Items i = iDao.getItemByName(Long.parseLong(id), "cpu_percent");
			HistoryDao hDao = new HistoryDaoImp();
			
			List<Object[]> li = hDao.getItemsValueByItemId_10time(i.getItemid());
			for(Object[] oo:li){
				Map<String, String> map = new HashMap<>();
				map.put("ip", ip);
				String time = ChangeTime.TimeStamp2Date(""+oo[0], "HH:mm:ss");
				map.put("time", ""+time);
				map.put("cpu", ""+oo[1]);
				returnList.add(map);
			}
		}
		return returnList;
	}

	@Override
	public List getServerOtherInfo() {
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		
		List<Map<String, String>> returnList = new ArrayList<>();
		
		List<Object[]> list = gDao.getHostListByGroupid02(4L);		//获取服务器的所有主机
		for(Object[] o:list){
			String id = o[0].toString();	//host id
			String ip = o[1].toString();	//host ip
			
			List<Items> iList = iDao.getItemsByHostid(Long.parseLong(id));
			
			Map<String, String> map = new HashMap<>();
			map.put("ip", ip);
			for(Items i:iList){		//每一个item项对象
				int type = i.getValueType();
				if(type==0){		//浮点类型
					HistoryDao hDao = new HistoryDaoImp();
					History h = hDao.getItemsValueByItemId(i.getItemid());
					if(h!=null){
						if(!i.getName().startsWith("disk") && !i.getName().startsWith("mem") && !i.getName().startsWith("net") && !i.getName().startsWith("cpu")){
							String iValue = ""+h.getId().getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
				else if(type==3){	//整数
					HistoryUintDao huDao = new HistoryUintDaoImp();
					HistoryUint hu = huDao.getItemsValueByItemId(i.getItemid());
					if(hu!=null){
						if(!i.getName().startsWith("disk") && !i.getName().startsWith("mem") && !i.getName().startsWith("net") && !i.getName().startsWith("cpu")){
							String iValue = ""+hu.getId().getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
				else if(type==4){	//文本
					HistoryTextDao htDao = new HistoryTextDaoImp();
					HistoryText ht = htDao.getItemsValueByItemId(i.getItemid());
					if(ht!=null){
						if(!i.getName().startsWith("disk") && !i.getName().startsWith("mem") && !i.getName().startsWith("net") && !i.getName().startsWith("cpu")){
							String iValue = ""+ht.getValue();
							map.put(i.getName(), iValue);
						}
					}
				}
			}
			returnList.add(map);
		}
		return returnList;
	}
}
