package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.AddressDao;
import entity.Address;
import util.HibernateUtil;

public class AddressDaoImpl extends CommonDaoImpl<Address> implements AddressDao{


	@Override
	public List<Address> getAddrListByUserId(int id) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from t_address where id="+id);
		List<Address>list=query.list();
		session.getTransaction().commit();
		return list;
	}

}
