package org.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.dao.ZRoleAuthorityDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.model.ZAuthority;
import org.model.ZRole;
import org.model.ZRoleAuthority;
import org.model.ZUserRole;
import org.util.HibernateSessionFactory;

public class ZRoleAuthorityDaoImp implements ZRoleAuthorityDao {

	@Override
	public boolean addRA(int roleId, int AuthorityId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZRoleAuthority ra = new ZRoleAuthority();
			ra.setRoleid(roleId);
			ra.setAuthorityid(AuthorityId);
			session.save(ra);

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
	public List<ZRoleAuthority> getRAList() {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("from ZRoleAuthority");
			List<ZRoleAuthority> list = query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List getAListByR(int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("select authorityid from ZRoleAuthority ra where ra.roleid=?");
			query.setParameter(0, roleId);
			List list = query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteRA(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZRoleAuthority ra = (ZRoleAuthority) session.load(
					ZRoleAuthority.class, id);
			session.delete(ra);

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
	public List getIdListByR(int roleId) {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("select id from ZRoleAuthority ra where ra.roleid=?");
			query.setParameter(0, roleId);
			List list = query.list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean updateRoleAuthority(final int roleId, final int[] authorityId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("delete from ZRoleAuthority ra where ra.roleid=?");
			query.setParameter(0, roleId);
			query.executeUpdate();

			session.doWork(new Work() {
				public void execute(Connection connection) throws SQLException {
					PreparedStatement stmt = connection
							.prepareStatement("insert into z_role_authority(roleid,authorityid) values(?,?)");
					connection.setAutoCommit(false);
					for (Integer aId : authorityId) {
						stmt.setInt(1, roleId);
						stmt.setInt(2, aId);
						stmt.addBatch();
					}
					stmt.executeBatch();
				}
			});

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
