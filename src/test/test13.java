package test;

import java.util.Date;
import java.util.List;

import org.dao.GroupsDao;
import org.dao.HistoryDao;
import org.dao.HistoryTextDao;
import org.dao.ItemsDao;
import org.dao.ZFileDao;
import org.dao.imp.GroupsDaoImp;
import org.dao.imp.HistoryDaoImp;
import org.dao.imp.HistoryTextDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.ZBoardDaoImp;
import org.dao.imp.ZFileDaoImp;
import org.dao.imp.ZMessDaoImp;
import org.dao.imp.ZMissionDaoImp;
import org.model.History;
import org.model.Items;
import org.model.ZFile;
import org.service.ServerService;
import org.service.imp.ServerServiceImp;
import org.tool.ChangeTime;

public class test13 {
	public static void main(String[] args) {
		long time = new Date().getTime();
		
//		ZFile f = new ZFile();
//		f.setFilename("test");
//		f.setUploadtime(time);
//		f.setUsername("tom");
//		
//		ZFileDao fDao = new ZFileDaoImp();
//		fDao.addFile(f);
//		System.out.println("\\asdasd".replace("\\", ""));
		System.out.println(new ZMissionDaoImp().cancelMission(3, "我也不知道为什么"));
	}
}