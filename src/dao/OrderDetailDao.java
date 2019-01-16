package dao;

import java.util.List;

import entity.OrderDetail;

public interface OrderDetailDao  extends CommonDao<OrderDetail>{

	void batchAdd(List<OrderDetail> orderDetails);

}
