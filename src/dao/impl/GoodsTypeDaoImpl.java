package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.IGoodsTypeDao;
import entity.GoodsInfo;
import entity.GoodsType;
import entity.User;
import util.HibernateUtil;

public class GoodsTypeDaoImpl extends CommonDaoImpl<GoodsType> implements IGoodsTypeDao {

	@Override
	public List<GoodsType> getBigClass() {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from GoodsType u where u.gtype_parentid=0");
		List<GoodsType>list=query.list();
		session.getTransaction().commit();
		return list;
	}

	@Override
	public List<GoodsType> getGoodsTypeListByFatherId(Integer id) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from GoodsType u where u.gtype_parentid!=0");
		List<GoodsType>list=query.list();
		session.getTransaction().commit();
		return list;
	}

	@Override
	public List<GoodsType> getGoodsTypeName() {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from GoodsType u where u.gtype_parentid=0");
		List<GoodsType>list=query.list();
		session.getTransaction().commit();
		return list;
	}
	


	@Override
	public int updateGoodsType(GoodsType t) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		GoodsType user=(GoodsType) session.get(GoodsType.class, t.getId());
		user.setGtype_name(t.getGtype_name());
		session.update(user);
		session.getTransaction().commit();
		return 1;
	}


	
	
	

	








}
