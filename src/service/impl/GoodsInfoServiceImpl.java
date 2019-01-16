package service.impl;

import dao.IGoodsInfoDao;
import dao.impl.GoodsInfoDaoImpl;
import entity.GoodsInfo;
import entity.Page;
import entity.User;
import service.IGoodsInfoService;

public class GoodsInfoServiceImpl extends CommonServiceImpl<GoodsInfo> implements IGoodsInfoService {
	private IGoodsInfoDao dao=new GoodsInfoDaoImpl();

	@Override
	public int updaeUser(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return dao.update(goodsInfo);
	}

}
