package com.store.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.bookstore.dao.IOrderDetailDao;
import com.store.bookstore.dto.OrderDetailDto;
import com.store.bookstore.entity.Order;
import com.store.bookstore.entity.OrderDetail;

@Service
public class OrderDetailService implements IOrderDetailService{
	
	@Autowired
	public IOrderDetailDao orderDetailDao;

	@Override
	public List<OrderDetailDto> getOrderDetailsByUserId(Integer userId) {
		List<Object[]> objs = orderDetailDao.getOrderDetailsByUserId(userId);
		return mapOrderDetail(objs);
	}
	
	private List<OrderDetailDto> mapOrderDetail(List<Object[]> objs) {
		List<OrderDetailDto> orderDetailList = new ArrayList<OrderDetailDto>();
		try {
			OrderDetailDto orderDetailDto = null;
			for(Object[] obj : objs) {
				orderDetailDto = new OrderDetailDto();
				orderDetailDto.setOrderDetailId((Integer)obj[0]);
				orderDetailDto.setOrderId((Integer)obj[1]);
				orderDetailDto.setBookId((Integer)obj[2]);
				orderDetailDto.setPrice((Double)obj[3]);
				orderDetailList.add(orderDetailDto);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return orderDetailList;
	}
	
}
