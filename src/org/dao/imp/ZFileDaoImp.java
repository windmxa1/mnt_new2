package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZFileDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZFile;
import org.util.HibernateSessionFactory;
import org.view.VZfile;

public class ZFileDaoImp implements ZFileDao{

	@Override
	public boolean addFile(ZFile f) {
		try {
			Session session  = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			ZFile zf = new ZFile();
			zf.setUsername(f.getUsername());
			zf.setFilename(f.getFilename());
			zf.setUploadtime(f.getUploadtime());
			zf.setDir(f.getDir());
			
			session.save(zf);
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List getFileList(Integer start,Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from v_zfile order by time desc";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VZfile.class);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			sqlQuery.setFirstResult(start);
			sqlQuery.setMaxResults(limit);
			List<VZfile> li = sqlQuery.list();

			List list = new ArrayList<>();
			for (VZfile b : li) {
				list.add(b.getId());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public ZFile getFileById(int id) {
		try {
			Session session  = HibernateSessionFactory.getSession();
			
			Query query = session.createQuery("from ZFile where id = ?");
			query.setParameter(0, id);
			
			query.setMaxResults(1);
			ZFile f = (ZFile) query.uniqueResult();
			if(f!=null)
				return f;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean deleteFile(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			ZFile f = (ZFile) session.load(ZFile.class, id);
			session.delete(f);
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
	public Long getFileCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from ZFile";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return (long) -1;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
}
