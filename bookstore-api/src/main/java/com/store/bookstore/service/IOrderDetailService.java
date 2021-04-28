package com.store.bookstore.service;

import java.util.List;

import com.store.bookstore.dto.OrderDetailDto;
import com.store.bookstore.entity.OrderDetail;

public interface IOrderDetailService {
	
	public List<OrderDetailDto> getOrderDetailsByUserId(Integer userId);
	
}
