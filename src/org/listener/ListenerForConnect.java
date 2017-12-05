package org.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dao.ZConnectCtlDao;
import org.dao.ZHostConfigDao;
import org.dao.ZIPCRecordingDao;
import org.dao.imp.ZConnectCtlDaoImp;
import org.dao.imp.ZHostConfigDaoImp;
import org.dao.imp.ZIPCRecordingDaoImp;

import speed.dao.SensorsDao;
import speed.dao.imp.SensorsDaoImp;

public class ListenerForConnect implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 服务器启动时，自动重置所有连接
		// ZMessDao mDao = new ZMessDaoImp();
		// mDao.resetConnectCount();
		ZConnectCtlDao ctlDao = new ZConnectCtlDaoImp();
		ctlDao.deleteAll();
		ZHostConfigDao hConfigDao = new ZHostConfigDaoImp();
		hConfigDao.init();
		ZIPCRecordingDao ipcDao = new ZIPCRecordingDaoImp();
		ipcDao.init();
		SensorsDao sDao = new SensorsDaoImp();
		sDao.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// System.out.println("close proxool");
		// ProxoolFacade.shutdown(0);
		// try {
		// System.out.println("close Driver");
		// while (DriverManager.getDrivers().hasMoreElements()) {
		// DriverManager.deregisterDriver(DriverManager.getDrivers()
		// .nextElement());
		// }
		// System.out.println("close AbandonedThread");
		// AbandonedConnectionCleanupThread.shutdown();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}
