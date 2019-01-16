package service.impl;

import java.util.List;

import dao.IGoodsTypeDao;
import dao.impl.GoodsInfoDaoImpl;
import dao.impl.GoodsTypeDaoImpl;
import entity.GoodsInfo;
import entity.GoodsType;
import entity.Page;
import service.IGoodsTypeService;

public class GoodsTypeServiceImpl extends CommonServiceImpl<GoodsType> implements IGoodsTypeService  {
	private IGoodsTypeDao dao=new GoodsTypeDaoImpl();
	@Override
	public List<GoodsType> getBigClass() {
		// TODO Auto-generated method stub
		return dao.getBigClass();
	}
	@Override
	public List<GoodsType> getGoodsTypeListByFatherId(Integer id) {
		// TODO Auto-generated method stub
		return dao.getGoodsTypeListByFatherId(id);
	}


	@Override
	public List<GoodsType> getGoodsTypeName() {
		// TODO Auto-generated method stub
		return dao.getGoodsTypeName();
	}
	@Override
	public int updateGoodsType(GoodsType goodsInfo) {
		// TODO Auto-generated method stub
		return dao.updateGoodsType(goodsInfo);
	}

}
