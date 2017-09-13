package org.dao.imp;

import java.util.List;

import org.dao.ZArmingCaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZArmingCase;
import org.util.HibernateSessionFactory;

public class ZArmingCaseDaoImp implements ZArmingCaseDao {

	@Override
	public Integer saveOrUpdate(ZArmingCase zCase) {
		Integer id = 0;
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (zCase.getId() != null) {
				System.out.println("update");
				session.update(zCase);
			} else {
				System.out.println("save");
				id = (Integer) session.save(zCase);
			}
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public ZArmingCase getZalarmCase(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			Query query = null;
			if (id == null) {
				sql = "from ZArmingCase where chosen=1";
				query = session.createQuery(sql);
			} else {
				sql = "from ZArmingCase where id=?";
				query = session.createQuery(sql);
				query.setParameter(0, id);
			}
			query.setMaxResults(1);
			ZArmingCase armingCase = (ZArmingCase) query.uniqueResult();
			return armingCase;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<ZArmingCase> getZalarmCaseList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from ZArmingCase";
			Query query = session.createQuery(sql);
			List<ZArmingCase> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public ZArmingCase choseZalarmCase(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from ZArmingCase where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, id);
			ZArmingCase armingCase = (ZArmingCase) query.uniqueResult();
			armingCase.setChosen(1);

			String sql2 = "update ZArmingCase set chosen =0 where id !=?";
			Query query2 = session.createQuery(sql2);
			query2.setParameter(0, id);
			query2.executeUpdate();
			ts.commit();
			return armingCase;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
