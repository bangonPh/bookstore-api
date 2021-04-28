package com.store.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.bookstore.dao.IOrderDao;
import com.store.bookstore.dao.IOrderDetailDao;
import com.store.bookstore.entity.Order;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private IOrderDao orderDao;	
	
	@Autowired
	private IOrderDetailDao orderDetailDao;

	@Override
	public void saveOrder(Order order) {
		order = orderDao.save(order);
		orderDao.flush();
	}
	
	@Override
	@Transactional(rollbackFor = { SQLException.class })
	public void deleteOrderAndOrderDetail(Integer orderId) {
		// Delete Order Detail
		orderDetailDao.deleteOrderDetailByOrderId(orderId);
		
		// Delete Order
		orderDao.deleteById(orderId);
	}

	@Override
	public List<Order> findByUserId(Integer userId) {
		return orderDao.findAllByUserId(userId);
	}
	
}
