package org.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dao.ZMessDao;
import org.dao.imp.ZMessDaoImp;

public class ListenerForConnect implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 服务器启动时，自动重置所有连接
//		ZMessDao mDao = new ZMessDaoImp();
//		mDao.resetConnectCount();
	}

}
