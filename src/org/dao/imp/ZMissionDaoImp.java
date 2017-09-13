package org.dao.imp;

import java.util.List;

import org.dao.ZMissionDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZMission;
import org.util.HibernateSessionFactory;

public class ZMissionDaoImp implements ZMissionDao {
	@Override
	public boolean addMission(ZMission m) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZMission mission = new ZMission();
			mission.setContent(m.getContent());
			mission.setSenderId(m.getSenderId());
			mission.setReceiverId(m.getReceiverId());
			mission.setTime(m.getTime());
			mission.setIsRead(m.getIsRead());
			mission.setIsComplete(m.getIsComplete());
			mission.setIsConfirm(m.getIsConfirm());

			session.save(mission);
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<ZMission> getUnReadMission(Integer uid, int key) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "";
			if (key == 0) // 发送人
				sql = "from ZMission where senderId = ? and isRead = 0 order by time desc";
			else
				// 接收人
				sql = "from ZMission where receiverId = ? and isRead = 0 order by time desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, uid);
			List<ZMission> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<ZMission> getUnCompleteMission(Integer uid, int key,
			Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			if (limit == -1) {
				limit = 15;
			}
			if (start == -1) {
				start = 0;
			}
			String sql = "";
			if (key == 0) // 发送人
				sql = "from ZMission where senderId = ? and isComplete = 0";
			else
				// 接收人
				sql = "from ZMission where receiverId = ? and isComplete = 0";
			Query query = session.createQuery(sql);
			query.setParameter(0, uid);
			query.setMaxResults(limit);
			query.setFirstResult(start);
			List<ZMission> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getUnCompleteMissionCount(Integer uid, int key) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			if (key == 0) {
				sql = "select count(id) from ZMission where senderId = ? and isComplete = 0";
			} else {
				sql = "select count(id) from ZMission where receiverId = ? and isComplete = 0";
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, uid);
			query.setMaxResults(1);
			long a = (long) query.uniqueResult();

			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<ZMission> getUnConfirmMission(Integer uid, int key,
			Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();

			if (limit == -1) {
				limit = 15;
			}
			if (start == -1) {
				start = 0;
			}

			String sql = "";
			if (key == 0) // 发送人
				sql = "from ZMission where senderId = ? and isConfirm = 0";
			else
				// 接收人
				sql = "from ZMission where receiverId = ? and isConfirm = 0";
			Query query = session.createQuery(sql);
			query.setParameter(0, uid);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			List<ZMission> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getUnConfirmMissionCount(Integer uid, int key) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			if (key == 0) {
				sql = "select count(id) from ZMission where senderId = ? and isConfirm = 0";
			} else {
				sql = "select count(id) from ZMission where receiverId = ? and isConfirm = 0";
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, uid);
			query.setMaxResults(1);
			// List<Object[]> list = query.list();
			long a = (long) query.uniqueResult();

			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<ZMission> getAllMission(Integer uid, int key, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			if (limit == -1) {
				limit = 15;
			}
			if (start == -1) {
				start = 0;
			}
			String sql = "";
			if (key == 0) // 发送人
				sql = "from ZMission where senderId = ? order by isConfirm,isComplete desc,time desc ";
			else
				// 接收人
				sql = "from ZMission where receiverId = ? order by isComplete,time desc";
			Query query = session.createQuery(sql);
			query.setParameter(0, uid);
			query.setMaxResults(limit);
			query.setFirstResult(start);
			List<ZMission> list = query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getAllMissionCount(Integer uid, int key) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "";
			if (key == 0) {
				sql = "select count(id) from ZMission where senderId = ? ";
			} else {
				sql = "select count(id) from ZMission where receiverId = ?";
			}
			Query query = session.createQuery(sql);
			query.setParameter(0, uid);
			query.setMaxResults(1);
			// List<Object[]> list = query.list();
			long a = (long) query.uniqueResult();

			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long checkLastestMission(Integer receive_id) {
		try {
			Session session = HibernateSessionFactory.getSession();

			String sql = "select count(id) from ZMission where isRead = 0 and receiverId = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, receive_id);
			query.setMaxResults(1);
			// List<Object[]> list = query.list();
			long a = (long) query.uniqueResult();

			return a;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean readMission(Integer receive_id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("update ZMission m set m.isRead=1 where receiverId = ?");
			query.setParameter(0, receive_id);
			query.executeUpdate();
			ts.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean completeMission(Integer[] id) {
		String sql = "";
		for (int i = 0; i < id.length; i++) {
			if (i == 0) {
				sql = "id=?";
			} else {
				sql = sql + " or id=?";
			}
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("update ZMission m set m.isComplete=1,m.time=? where "
							+ sql);
			Long time = System.currentTimeMillis() / 1000;
			query.setParameter(0, time);
			for (int i = 1; i <= id.length; i++) {
				query.setParameter(i, id[i - 1]);
			}
			query.executeUpdate();
			ts.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean confirmMission(Integer[] id) {
		String sql = "";
		for (int i = 0; i < id.length; i++) {
			if (i == 0) {
				sql = "id=?";
			} else {
				sql = sql + " or id=?";
			}
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("update ZMission m set m.isConfirm=1,m.time=? where "
							+ sql);
			Long time = System.currentTimeMillis() / 1000;
			query.setParameter(0, time);
			for (int i = 1; i <= id.length; i++) {
				query.setParameter(i, id[i - 1]);
			}
			query.executeUpdate();
			ts.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean cancelMission(Integer id, String cancelReason) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("update ZMission m set m.isCancel=1,m.isRead=0,m.isComplete=1,m.isConfirm=1,m.cancelReason=?,m.time=? where m.id=?");
			query.setParameter(0, cancelReason);
			Long time = System.currentTimeMillis() / 1000;
			query.setParameter(1, time);
			query.setParameter(2, id);
			query.executeUpdate();
			ts.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
