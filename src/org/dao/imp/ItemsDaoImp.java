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

public class ItemsDaoImp implements ItemsDao {

	public List<Items> getItemsByHostid(Long hostid) {
		try {
			Session session = HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("from Items where hostid=? order by name");
			query.setParameter(0, hostid);
			List<Items> list = query.list();
//			ts.commit();
			HibernateSessionFactory.closeSession();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		/*
		 * //用法：items - 通过 hostid 得到 itemsid ItemsDao iDao = new ItemsDaoImp();
		 * List<Items> iList= iDao.getHostidByItemid((long) 10151); for(Items i:
		 * iList){
		 * System.out.println(i.getItemid()+"-"+i.getName()+"-"+i.getHostid());
		 * }
		 */
	}

	//-------------------------没有关闭Session的dao接口----------------------------------
	public Items getItemByItemid(Long itemid) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from Items i where itemid = ?");
			query.setParameter(0, itemid);
			query.setMaxResults(1);
			Items items = (Items) query.uniqueResult();
			
			// ts.commit();
			HibernateSessionFactory.closeSession();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Items getItemByName(Long hostid,String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from Items i where i.name=? and i.hostid=?");
			query.setParameter(0, name);
			query.setParameter(1, hostid);
			query.setMaxResults(1);
			Items items = (Items) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
