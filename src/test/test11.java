package test;

import java.util.List;

import net.sf.json.JSONArray;

import org.dao.GroupsDao;
import org.dao.HostDao;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HostDaoImp;
import org.service.NVRService;
import org.service.imp.NVRServiceImp;
import org.view.VHostGroup;
import org.view.VHostGroupId;

public class test11 {
	public static void main(String[] args) {
		NVRService nvrService = new NVRServiceImp();
		nvrService.getNVRInfo();
	}
}
