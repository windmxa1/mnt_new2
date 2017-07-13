package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.ZBoardDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.ZBoard;
import org.util.HibernateSessionFactory;
import org.view.VZboardZuser;

public class ZBoardDaoImp implements ZBoardDao {

	@Override
	public boolean addBoard(ZBoard board) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			ZBoard b = new ZBoard();
			b.setContent(board.getContent());
			b.setTime(board.getTime());
			b.setTitle(board.getTitle());
			b.setUserId(board.getUserId());

			session.save(b);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBoard(Integer[] id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

//			ZBoard board = (ZBoard) session.load(ZBoard.class, id);
//			session.delete(board);
			
//			delete from log where id=7 or id=9;
			String sql = "delete from z_board where id=";
			for(int i=0;i<id.length;i++){
				if(i==0)
					sql +=id[i];
				sql +=" or id="+id[i];
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.executeUpdate();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List getBoardList(Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from v_zboard_zuser order by time desc";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VZboardZuser.class);
			if (start == null) {
				start = 0;
			}
			if (limit == null) {
				limit = 15;
			}
			sqlQuery.setFirstResult(start);
			sqlQuery.setMaxResults(limit);
			List<VZboardZuser> li = sqlQuery.list();

			List list = new ArrayList<>();
			for (VZboardZuser b : li) {
				list.add(b.getId());
			}
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long getBoardCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select count(id) from ZBoard";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return (long) -1;
		}
	}
	

}
