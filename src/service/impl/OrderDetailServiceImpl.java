package service.impl;

import java.util.List;

import dao.OrderDetailDao;
import dao.impl.OrderDetailImpl;
import entity.OrderDetail;
import entity.Page;
import service.OrderDetailService;

public class OrderDetailServiceImpl extends CommonServiceImpl<OrderDetail> implements OrderDetailService {
	private OrderDetailDao dao=new OrderDetailImpl();
	@Override
	public void batchAdd(List<OrderDetail> orderDetails) {
		dao.batchAdd(orderDetails);
		
	}
	


}
