package dao.impl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import dao.UserDao;
import entity.Page;
import entity.User;
import util.HibernateUtil;

public class UserDaoImpl extends CommonDaoImpl<User> implements UserDao {

	@Override
	public int update(User u) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user=(User) session.get(User.class, u.getId());
		user.setName(u.getName());
		user.setPassword(u.getPassword());
		user.setPhone(u.getPhone());
		user.setSex(u.getSex());
		user.setIsAdmin(u.getIsAdmin());
		user.setEmail(u.getEmail());
		session.update(user);
		session.getTransaction().commit();
		return 1;
	}

	@Override
	public User getBybackLogin(String name, String password) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from User u where name=? and password=? ");
		query.setString(0, name);
		query.setString(1, password);
		User u=(User) query.uniqueResult();
		session.getTransaction().commit();
		return u;
	}




}
