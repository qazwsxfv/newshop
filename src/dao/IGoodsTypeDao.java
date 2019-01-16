package dao;

import java.util.List;

import entity.GoodsType;

public interface IGoodsTypeDao extends CommonDao<GoodsType> {

	List<GoodsType> getBigClass();

	List<GoodsType> getGoodsTypeListByFatherId(Integer id);

	List<GoodsType> getGoodsTypeName();



	int updateGoodsType(GoodsType goodsInfo);
	
	


}
