package com.entor.business;

import java.util.List;

import com.entor.entity.Orders;

public interface IOrdersService extends IBaseService<Orders>{
	void addOrders(Orders orders);
}
