package org.dao.imp;

import java.util.List;

import org.dao.HistoryTextDao;
import org.dao.ItemsDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.HistoryText;
import org.model.Items;
import org.util.HibernateSessionFactory;

public class HistoryTextDaoImp implements HistoryTextDao {

	public HistoryText getItemsValueByItemId(Long itemid) {
		try {
			ItemsDao iDao = new ItemsDaoImp();
			Items i = iDao.getItemByItemid(itemid);
			Session session = HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
			// SELECT * FROM history_text his WHERE his.itemid = "24538" ORDER
			// BY his.id DESC LIMIT 0,1;
			String sql = "";
			if (i.getName().equals("deviceAlarm")) {
				sql = "from HistoryText where itemid=? order by id desc";
			} else {
				sql = "from HistoryText where itemid=? and value!='' order by id desc";
			}
			Query query = session.createQuery(sql);

			query.setParameter(0, itemid);
			query.setMaxResults(1);

			HistoryText ht = (HistoryText) query.uniqueResult();
//			ts.commit();
			HibernateSessionFactory.closeSession();

			return ht;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public HistoryText getDeviceAlarmByHostId(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			ItemsDao iDao = new ItemsDaoImp();
			List<Items> Ilist = iDao.getItemsByHostid(hostid);
			Query query = null;
			for (Items i : Ilist) {
				if (i.getName().equals("deviceAlarm")) {
					String sql = "from HistoryText where itemid = ? order by id desc";
					query = session.createQuery(sql);
					query.setParameter(0, i.getItemid());
					query.setMaxResults(1);
				}
			}
			HistoryText ht = (HistoryText) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return ht;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
