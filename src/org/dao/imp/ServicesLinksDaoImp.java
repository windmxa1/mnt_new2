package org.dao.imp;

import java.util.List;

import org.dao.ServicesLinksDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Services;
import org.model.ServicesLinks;
import org.util.HibernateSessionFactory;

public class ServicesLinksDaoImp implements ServicesLinksDao {
	/**
	 *根据上一级的服务ID获取下一级服务列表 
	 */
	public List<ServicesLinks> getDownServices(Long serviceupid) {
		// 获取下一级列表，机房-主机-主机属性
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			
			Query query = session
					.createQuery("from ServicesLinks where serviceupid = ?");
			query.setParameter(0, serviceupid);
			// System.out.println("serviceupid:"+serviceupid);
			List<ServicesLinks> list = query.list();

			

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}

	}

	public Long getUpServiceId(Long servicedownid){
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("select serviceupid from ServicesLinks where servicedownid = ?");
			query.setParameter(0, servicedownid);
			query.setMaxResults(1);
			Long upserviceid = (Long) query.uniqueResult();

			

			return upserviceid;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
}
