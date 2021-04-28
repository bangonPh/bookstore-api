package com.store.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.bookstore.bean.UserReqBean;
import com.store.bookstore.bean.UserResBean;
import com.store.bookstore.dto.OrderDetailDto;
import com.store.bookstore.dto.UserDto;
import com.store.bookstore.entity.User;
import com.store.bookstore.exception.UserNotAvailableException;
import com.store.bookstore.service.IOrderDetailService;
import com.store.bookstore.service.IUserService;
import com.store.bookstore.util.Constant;
import com.store.bookstore.util.DateTimeUtil;

@RestController
public class UserController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public IUserService userService;
	
	@Autowired
	public IOrderDetailService orderService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public UserResBean user(HttpSession httpSession) throws UserNotAvailableException {
		UserResBean responseBody = new UserResBean();
		
		// Check Session login 
		UserDto sessionLogin = getUserLogin(httpSession);
		if (null == sessionLogin) {
			throw new UserNotAvailableException("Does not exits user login");
		}
		
		// Find user with userId
		User userLogin = userService.findByUserId(sessionLogin.getUserId());
		responseBody.setName(userLogin.getName());
		responseBody.setSurname(userLogin.getSurname());
		responseBody.setDateOfbirthday(null != userLogin.getBirthday() ? DateTimeUtil.dateToString(userLogin.getBirthday(), Constant.DDMMYYYY_Df) : "");
		
		// Get Order details from User Session Login
		List<OrderDetailDto> orderDetailDtoList = orderService.getOrderDetailsByUserId(userLogin.getUserId());
		List<Integer> bookList = new ArrayList<Integer>();
		if(null != orderDetailDtoList && !orderDetailDtoList.isEmpty()) {
			
			// Iterator to book list
			for(OrderDetailDto orderDetailDto : orderDetailDtoList) {
				bookList.add(orderDetailDto.getBookId());
			}
			
		}
		responseBody.setBooks(bookList);
		return responseBody;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity userCreate(@RequestBody UserReqBean userReqBean) {
		HttpStatus status = null;
		if(null != userReqBean && null != userReqBean.getUsername() && null != userReqBean.getPassword()) {
			User user = new User();
			user.setUsername(userReqBean.getUsername());
			user.setPassword(userReqBean.getPassword());
			user.setBirthday(null != userReqBean.getDateOfbirthday() ? DateTimeUtil.convertToDate(userReqBean.getDateOfbirthday(), Constant.DDMMYYYY_Df) : null);
			// Save and Return HTTP Status Code 200
			userService.saveUser(user);
			status = HttpStatus.OK;
		}else {
			// Return HTTP Status 400
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(status);
	}

}
