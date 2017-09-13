package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.registry.infomodel.User;

import org.dao.ZUserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZUser;
import org.model.ZUserRole;
import org.util.HibernateSessionFactory;
import org.view.VUser;
import org.view.VUserId;

public class ZUserDaoImp implements ZUserDao {

	public ZUser getUser(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZUser where username=?");
			query.setParameter(0, username);
			query.setMaxResults(1);
			ZUser user = (ZUser) query.uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public boolean addUser(ZUser user, int roleid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			int id = (Integer) session.save(user);
			ZUserRole userRole = new ZUserRole(id, roleid);
			session.save(userRole);

			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	public ZUser getUser(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from ZUser where username=? and password=?");
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setMaxResults(1);
			ZUser usr = (ZUser) query.uniqueResult();
				return usr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	public boolean deleteUser(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			ZUser user = (ZUser) session.load(ZUser.class, id);
			session.delete(user);
			Query query = session
					.createQuery("delete from ZUserRole where userId=?");
			query.setParameter(0, id);
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

	public boolean changePassword(int userId, String newPwd) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update ZUser u set u.password=? where u.id=?");
			query.setParameter(0, newPwd);
			query.setParameter(1, userId);
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

	public List<VUserId> getUserList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from  VUser v ";
			Query query = session.createQuery(sql);
			if (start == null)
				start = 0;
			if (limit == null) {
				limit = 15;
				query.setMaxResults(limit);
			} else if (limit == -1) {
			} else {
				query.setMaxResults(limit);
			}
			List<VUser> list = query.list();
			List<VUserId> list2 = new ArrayList<>();
			for (VUser v : list) {
				list2.add(v.getId());
			}
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int getUserMaxid() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select Max(id) from ZUser");
			query.setMaxResults(1);

			int maxId = (int) query.uniqueResult();
			return maxId;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int getIdByUsername(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZUser where username=?");
			query.setParameter(0, username);
			query.setMaxResults(1);
			ZUser u = (ZUser) query.uniqueResult();
			return u.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean changeName(int userId, String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update ZUser u set u.name=? where u.id=?");
			query.setParameter(0, name);
			query.setParameter(1, userId);
			query.executeUpdate();

			ts.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean checkId(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZUser where id = ?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			ZUser u = (ZUser) query.uniqueResult();
			if (u != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	public List<ZUser> getList(Integer userid, Integer start, Integer limit) {
		try {
			if (start == -1) {
				start = 0;
			}
			if (limit == -1) {
				limit = 15;
			}
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from ZUser where id!=? ");
			query.setParameter(0, userid);
			query.setMaxResults(limit);
			query.setFirstResult(start);
			List<ZUser> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select count(id) from ZUser");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateUserInfo(ZUser user, ZUserRole userRole) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			if (user.getPassword().trim().equals("")) {
				String sql = "update ZUser set name = ? where username=?";
				Query query = session.createQuery(sql);
				query.setParameter(0, user.getName());
				query.setParameter(1, user.getUsername());
				query.executeUpdate();
			} else {
				session.update(user);
			}
			if (userRole.getId() == null) {
				session.save(userRole);
			} else {
				session.update(userRole);
			}
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
	public VUserId getUser(int userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from VUser where id=? ");
			query.setParameter(0, userid);
			query.setMaxResults(1);
			VUser vUser = (VUser) query.uniqueResult();
			VUserId user = vUser.getId();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public String getUserName(Integer userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select name from ZUser where id=? ");
			query.setParameter(0, userid);
			query.setMaxResults(1);
			String name = (String) query.uniqueResult();
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
