package service;

import entity.Order;

public interface OrderService extends CommonService<Order>{

	int addReturnPrimarykey(Order order);

}
