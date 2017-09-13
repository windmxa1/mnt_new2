package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZMessDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZConnectState;
import org.model.ZMessage;
import org.util.HibernateSessionFactory;
import org.view.VMessage;
import org.view.VMessageId;

public class ZMessDaoImp implements ZMessDao {

	@Override
	public List<ZMessage> getLatest(Integer user_id, Integer friend_id,
			Integer start, Integer limit, boolean isSender) {
		if (start == null) {
			start = 0;
		}
		if (limit == null) {
			limit = 5;
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql;
			if (isSender) {
				sql = "select m.* from z_message m where sender_id = ?  and receiver_id = ? send_state=0 order by time desc limit ?,?";
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setParameter(0, user_id);
				sqlQuery.setParameter(1, friend_id);
				sqlQuery.setParameter(2, start);
				sqlQuery.setParameter(3, limit);
				sqlQuery.addEntity(ZMessage.class);
				List<ZMessage> list = sqlQuery.list();
				return list;
			} else {
				sql = "select m.* from z_message m where sender_id = ?  and receiver_id = ?  and receive_state=0 order by time desc limit ?,?";
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setParameter(0, friend_id);
				sqlQuery.setParameter(1, user_id);
				sqlQuery.setParameter(2, start);
				sqlQuery.setParameter(3, limit);
				sqlQuery.addEntity(ZMessage.class);
				List<ZMessage> list = sqlQuery.list();
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<VMessageId> getLatest1(Integer user_id, Integer friend_id,
			Integer start, Integer limit) {
		if (start == -1) {
			start = 0;
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql;
			sql = "select * from v_message where (sender_id = ? or sender_id=?) and ( receiver_id = ? or receiver_id = ? ) order by time desc limit ?,?";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, user_id);
			sqlQuery.setParameter(1, friend_id);
			sqlQuery.setParameter(2, user_id);
			sqlQuery.setParameter(3, friend_id);
			sqlQuery.setParameter(4, start);
			sqlQuery.setParameter(5, limit);
			sqlQuery.addEntity(VMessage.class);
			List<VMessage> list = sqlQuery.list();
			List<VMessageId> list2 = new ArrayList<>();
			for (VMessage v : list) {
				list2.add(v.getId());
			}
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer checkLastest(Integer user_id, Integer friend_id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql1 = "update z_message m set m.send_state=1 where sender_id = ?  and receiver_id = ? and m.send_state=0";
			SQLQuery sqlQuery = session.createSQLQuery(sql1);
			sqlQuery.setParameter(0, user_id);
			sqlQuery.setParameter(1, friend_id);
			int count = sqlQuery.executeUpdate();
			ts.commit();
			// HibernateSessionFactory.closeSession();
			Session session1 = HibernateSessionFactory.getSession();
			Transaction ts1 = session.beginTransaction();
			String sql2 = "update z_message m set m.receive_state=1 where sender_id = ?  and receiver_id = ? and m.receive_state=0";
			SQLQuery sqlQuery2 = session1.createSQLQuery(sql2);
			sqlQuery2.setParameter(0, friend_id);
			sqlQuery2.setParameter(1, user_id);
			int count2 = sqlQuery2.executeUpdate();
			ts1.commit();
			return count + count2;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer save(ZMessage msg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Integer count = 0;
			if (msg.getId() != null) {
				session.update(msg);
				count = msg.getId();
			} else {
				count = (Integer) session.save(msg);
			}
			ts.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			HibernateSessionFactory.closeSession();
		}

	}

	@Override
	public ZMessage findById(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZMessage where id = ?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			ZMessage msg = (ZMessage) query.uniqueResult();
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCount(Integer user_id, Integer friend_id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select count(id) from ZMessage m where (m.senderId = ? or m.senderId=?) and ( m.receiverId = ? or m.receiverId = ? )  ");
			query.setMaxResults(1);
			query.setParameter(0, user_id);
			query.setParameter(1, friend_id);
			query.setParameter(2, user_id);
			query.setParameter(3, friend_id);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return (long) 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer insertConnect(ZConnectState connectState) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Integer id = 0;
			if (connectState.getId() != null) {
				session.update(connectState);
				id = connectState.getId();
			} else {
				id = (Integer) session.save(connectState);
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
	public ZConnectState getConnectState(Integer sender_id, Integer receiver_id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from ZConnectState where senderId=? and receiverId=?");
			query.setParameter(0, sender_id);
			query.setParameter(1, receiver_id);
			query.setMaxResults(1);
			ZConnectState connectState = (ZConnectState) query.uniqueResult();
			return connectState;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer getConnectCount(Integer sender_id, Integer receiver_id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select c.count from ZConnectState c where senderId = ? and receiverId = ?");
			query.setMaxResults(1);
			query.setParameter(0, sender_id);
			query.setParameter(1, receiver_id);
			query.setMaxResults(1);
			Integer count = (Integer) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean resetConnectCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update ZConnectState c set c.count=0");
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteAllChatHistory(Integer sender_id, Integer receiver_id) {
		
		return false;
	}

}
