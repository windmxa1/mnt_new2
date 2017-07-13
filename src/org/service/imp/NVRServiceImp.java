package org.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dao.GroupsDao;
import org.dao.HistoryTextDao;
import org.dao.HostInventoryDao;
import org.dao.ItemsDao;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HistoryTextDaoImp;
import org.dao.imp.HostInventoryDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.model.HistoryText;
import org.model.HostInventory;
import org.model.Items;
import org.service.NVRService;
import org.view.VHostGroupId;

public class NVRServiceImp implements NVRService{
	@Override
	public List getNVRInfo() {
		GroupsDao gDao = new GroupsDaoImp();
		ItemsDao iDao = new ItemsDaoImp();
		HistoryTextDao htDao = new HistoryTextDaoImp();
		
		List<VHostGroupId> list = gDao.getLiveHostByGroupid(10L);
		
		List li = new ArrayList<>();
		for(VHostGroupId v:list){		//遍历每一个主机
			Long id = v.getHostid();		//主机id
			String ip = v.getHost();		//主机ip
			String gid = ""+v.getGroupid();	//组id
			String gname = v.getName();		//主机名

			System.out.println("id:"+id);
			System.out.println("ip:"+ip);
			System.out.println("gid:"+gid);
			System.out.println("gname:"+gname);
			
			List<Items> iList = iDao.getItemsByHostid(id);
			
			Map<String, String> infoMap = new HashMap<String, String>();
			for(Items i:iList){
				HistoryText ht = htDao.getItemsValueByItemId(i.getItemid());
				infoMap.put(i.getName(), ht.getValue());
			}
			HostInventoryDao hiDao = new HostInventoryDaoImp();
			HostInventory hi = hiDao.getHostInventory(id);
			
			JSONObject jo = JSONObject.fromObject(hi);
			jo.putAll(infoMap);
			li.add(jo);
		}
		return li;
	}

}
