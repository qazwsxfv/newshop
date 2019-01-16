package service;

import java.util.List;

import entity.OrderDetail;

public interface OrderDetailService  extends CommonService<OrderDetail>{

	void batchAdd(List<OrderDetail> orderDetails);

}
