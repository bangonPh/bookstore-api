package com.store.bookstore.service;

import java.util.List;

import com.store.bookstore.entity.Order;

public interface IOrderService {

	public void saveOrder(Order order);
	public List<Order> findByUserId(Integer userId);
	public void deleteOrderAndOrderDetail(Integer orderId);
	
}
