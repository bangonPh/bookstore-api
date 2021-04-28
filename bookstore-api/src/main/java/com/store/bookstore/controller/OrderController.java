package com.store.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.bookstore.bean.UserOrderResBean;
import com.store.bookstore.dto.ReqOrderDto;
import com.store.bookstore.dto.UserDto;
import com.store.bookstore.entity.Book;
import com.store.bookstore.entity.Order;
import com.store.bookstore.entity.OrderDetail;
import com.store.bookstore.entity.User;
import com.store.bookstore.exception.UserNotAvailableException;
import com.store.bookstore.service.IBookService;
import com.store.bookstore.service.IOrderDetailService;
import com.store.bookstore.service.IOrderService;
import com.store.bookstore.service.IUserService;

@RestController
public class OrderController extends BaseController{
	
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	public IUserService userService;
	
	@Autowired
	public IOrderDetailService orderDetailService;
	
	@Autowired
	public IBookService bookService;
	
	@Autowired
	public IOrderService orderService;
	
	@RequestMapping(value = "/user/order", method = RequestMethod.POST)	
	public UserOrderResBean user(@RequestBody ReqOrderDto reqOrderDto, HttpSession httpSession) throws UserNotAvailableException {
		UserOrderResBean responseBody = new UserOrderResBean();
		double totalPrice = 0;
		
		// Check Session login 
		UserDto sessionLogin = getUserLogin(httpSession);
		if (null == sessionLogin) {
			throw new UserNotAvailableException("Does not exits user login");
		}
		
		// Find user with userId
		User userLogin = userService.findByUserId(sessionLogin.getUserId());
		
		// DTO Does not be null
		if(null != reqOrderDto && null != reqOrderDto.getOrders() && !reqOrderDto.getOrders().isEmpty()) {
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			
			// Save Order and Order Detail
			Order order = new Order();
			order.setUserId(userLogin);
			order.setOrderDetailList(orderDetailList);
			
			// Fetch all request body
			for(Integer bookId : reqOrderDto.getOrders()) {
				
				// Find Book by ID
				Book book = bookService.findByBookId(bookId);
				if(null != book) {
					// Total price
					double price = book.getPrice();
					totalPrice += price;
					
					// Order detail
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrderId(order);
					orderDetail.setBookId(book);
					orderDetail.setPrice(price);
					orderDetailList.add(orderDetail);
				}
			}
			order.setPrice(totalPrice);
			orderService.saveOrder(order);
		}
		responseBody.setPrice(totalPrice);
		return responseBody;
	}

}
