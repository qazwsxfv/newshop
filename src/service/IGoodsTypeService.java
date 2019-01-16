package service;

import java.util.List;

import entity.GoodsInfo;
import entity.GoodsType;
import entity.Page;

public interface IGoodsTypeService extends CommonService<GoodsType> {

	List<GoodsType> getBigClass();

	List<GoodsType> getGoodsTypeListByFatherId(Integer id);


	List<GoodsType> getGoodsTypeName();

	int updateGoodsType(GoodsType goodsInfo);

}
