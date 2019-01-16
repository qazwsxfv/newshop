package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.IGoodsInfoDao;
import entity.GoodsInfo;
import entity.User;
import util.HibernateUtil;

public class GoodsInfoDaoImpl extends CommonDaoImpl<GoodsInfo> implements IGoodsInfoDao {

	
	@Override
	public int update(GoodsInfo goodsInfo) {
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		GoodsInfo g=(GoodsInfo) session.get(GoodsInfo.class, goodsInfo.getId());
		g.setGoods_name(goodsInfo.getGoods_name());
		g.setGoods_description(goodsInfo.getGoods_description());
		g.setGoods_fatherid(goodsInfo.getGoods_parentid());
		g.setGoods_parentid(goodsInfo.getGoods_parentid());
		g.setGoods_price(goodsInfo.getGoods_price());
		g.setGoods_pic(goodsInfo.getGoods_pic());
		session.getTransaction().commit();
		return 0;
	}

}
