package test;

import java.util.List;

import org.dao.GroupsDao;
import org.dao.HistoryDao;
import org.dao.HistoryTextDao;
import org.dao.ItemsDao;
import org.dao.ZBoardDao;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HistoryDaoImp;
import org.dao.imp.HistoryTextDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.ZBoardDaoImp;
import org.model.History;
import org.model.Items;
import org.service.ServerService;
import org.service.imp.ServerServiceImp;
import org.tool.ChangeTime;

public class test12 {
	public static void main(String[] args) {
		ZBoardDao bDao = new ZBoardDaoImp();
		Integer[] a = {26,30,31};
		bDao.deleteBoard(a);

	}
}
