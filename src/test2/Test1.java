package test2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.action.MapAction;
import org.dao.DeviceInfoDao;
import org.dao.GroupsDao;
import org.dao.HostDao;
import org.dao.ZArmingCaseDao;
import org.dao.ZAuthorityDao;
import org.dao.ZOperationDao;
import org.dao.ZRoleDao;
import org.dao.ZSwitchDao;
import org.dao.ZUserDao;
import org.dao.imp.DeviceDaoImp;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HostDaoImp;
import org.dao.imp.ZArmingCaseDaoImp;
import org.dao.imp.ZAuthorityDaoImp;
import org.dao.imp.ZOperationDaoImp;
import org.dao.imp.ZRoleDaoImp;
import org.dao.imp.ZSwitchDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.hibernate.Session;
import org.model.ZOperationTime;
import org.util.HibernateSessionFactory;

import speed.dao.SensorsDao;
import speed.dao.imp.SensorsDaoImp;
import speed.view.VSensorsId;

public class Test1 {
	static SensorsDao sDao = new SensorsDaoImp();

	// static GroupsDao gDao = new GroupsDaoImp();

	// static HostDao hDao = new HostDaoImp();
	// static ZUserDao uDao = new ZUserDaoImp();
	// static ZRoleDao rDao = new ZRoleDaoImp();
	// static ZAuthorityDao aDao = new ZAuthorityDaoImp();
	// static ZArmingCaseDao zArmingCaseDao = new ZArmingCaseDaoImp();
	// static ZOperationDao oDao = new ZOperationDaoImp();
	// static DeviceInfoDao dInfoDao = new DeviceDaoImp();
	public static void main(String[] args) {
		Long time = System.currentTimeMillis();
		sDao.getSensorsByType2(0, -1, (short) 224);
		System.out.println(System.currentTimeMillis()-time);
	}

}