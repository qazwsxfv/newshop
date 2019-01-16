package dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.CommonDao;
import entity.User;
import util.HibernateUtil;

public class CommonDaoImpl<T> implements CommonDao<T>{
	@Override
	public int getCount(String a) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("select count(u) from "+a+" u");
		Long i=(Long) query.uniqueResult();
		session.getTransaction().commit();
		return i.intValue();
	}

	@Override
	public List<T> getList(int i, int pageSize,String a) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from "+a+"");
		query.setFirstResult(i);
		query.setMaxResults(pageSize);
		List<T>list=query.list();
		session.getTransaction().commit();
		return list;
	}

	@Override
	public T getById(Integer id,String a) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from "+a+" u where u.id=?");
		query.setInteger(0, id);
		T t=(T) query.uniqueResult();
		session.getTransaction().commit();
		return t;
	}

	@Override
	public int update(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id,String a) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql="delete from "+a+" where id="+id;
		Query query=session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
		return 1;
	}

	@Override
	public int add(T t) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		return 1;
	}

	@Override
	public int batchDel(String[] ids,String a) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		String id=Arrays.toString(ids);
		id=id.substring(1, id.length()-1);
		String sql="delete from "+a+" where id in("+id+")";
		Query query=session.createQuery(sql);
		query.executeUpdate();
		session.getTransaction().commit();
		return 1;
	}

	@Override
	public List<T> getList(String a) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from "+a+"");
		List<T>list=query.list();
		session.getTransaction().commit();
		return list;
	}
}
