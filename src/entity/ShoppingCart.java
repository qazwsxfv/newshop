package entity;

import java.util.ArrayList;
import java.util.List;



//创建购物车实体类
public class ShoppingCart {
	//购物车中有集合类，用于展示商品
	private List<GoodsInfoDomain> list = new ArrayList<>();

	public List<GoodsInfoDomain> getList() {
		return list;
	}

	public void setList(List<GoodsInfoDomain> list) {
		this.list = list;
	}
	
	//在购物车中添加商品
	public void add(GoodsInfoDomain domain){
		boolean flag = true;  //开关判断
		for(GoodsInfoDomain domain2 : list){
			if(domain2.getId().equals(domain.getId())){
				//购物车中有商品，则将数量加起来即可
				domain2.setCount(domain2.getCount()+domain.getCount());
				flag = false;
				break;
			}
		}
		
		if(flag){  //如果集合中没有匹配的id，则添加到购物车
			list.add(domain);
		}	
	}
	
	//在购物车中删除商品
	public void del(Integer id){
		
	}

	//获取总价的方法   ${对象.属性} 调get方法，getPrice
	public double getPrice() {
		double price = 0.0;
		for(GoodsInfoDomain domain : list){
			price += domain.getCount()*domain.getGoods_price();
		}
		return price;
	}

	public void update(Integer id, Integer count) {
		//根据id匹配到domain对象，然后将其count进行设置
		for(GoodsInfoDomain domain : list){
			if(domain.getId().equals(id)){
				domain.setCount(count);
				break;
			}
		}
		
	}

	
}

