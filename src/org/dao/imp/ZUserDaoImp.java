package org.dao.imp;

import java.util.List;

import javax.xml.registry.infomodel.User;

import org.dao.ZUserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZUser;
import org.util.HibernateSessionFactory;

public class ZUserDaoImp implements ZUserDao {

	public boolean checkUsername(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from ZUser where username=?");
			query.setParameter(0, username);
			query.setMaxResults(1);

			ZUser user = (ZUser) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			if (user != null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addUser(ZUser user) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZUser userin = new ZUser();
			userin.setUsername(user.getUsername());
			userin.setPassword(user.getPassword());
			userin.setName(user.getName());

			session.save(userin);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ZUser login(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();
			// Query query =
			// session.createQuery("from ZUser where username=? and password=?");
			Query query = session
					.createQuery("from ZUser where username=? and password=?");
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setMaxResults(1);
			ZUser usr = (ZUser) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			if (usr != null)
				return usr;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteUser(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZUser user = (ZUser) session.load(ZUser.class, id);
			session.delete(user);
//
//			Query query = session.createQuery("from ZUserRole where userid=?");
//			query.setParameter(0, id);
//			List<ZUserRole> urList = query.list();
//			for (ZUserRole r : urList) {
//				ZUserRole ur = (ZUserRole) session.load(ZUserRole.class,
//						r.getId());
//				session.delete(ur);
//			}
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ZUser> getUserList(){
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from ZUser order by id");
			List<ZUser> list = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getUserMaxid() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("select Max(id) from ZUser");
			query.setMaxResults(1);
			
			int maxId = (int) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return maxId;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getIdByUsername(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from ZUser where username=?");
			query.setParameter(0, username);
			query.setMaxResults(1);
			ZUser u = (ZUser) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return u.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
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
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkId(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from ZUser where id = ?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			ZUser u = (ZUser) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			if(u!=null)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public ZUser getOneUser(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from ZUser where username=? and password =?");
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setMaxResults(1);
			ZUser user = (ZUser) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
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
			Query query = session
					.createQuery("from ZUser where id!=? ");
			query.setParameter(0, userid);
			query.setMaxResults(limit);
			query.setFirstResult(start);
			List<ZUser> list = query.list();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	@Override
	public Long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select count(id) from ZUser");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return (long) 0;
		}
	}
}
