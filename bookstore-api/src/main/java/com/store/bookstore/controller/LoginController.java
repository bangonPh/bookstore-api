package com.store.bookstore.controller;

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

import com.store.bookstore.dto.UserDto;
import com.store.bookstore.entity.Order;
import com.store.bookstore.entity.User;
import com.store.bookstore.exception.UserNotAvailableException;
import com.store.bookstore.service.IOrderService;
import com.store.bookstore.service.IUserService;

@RestController
public class LoginController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	public IUserService userService;
	
	@Autowired
	public IOrderService orderService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity login(@RequestBody UserDto userDto, HttpSession httpSession) {
		logger.debug("start login service");
		HttpStatus status = null;
		if(null != userDto && null != userDto.getUsername() && null != userDto.getPassword()) {
			// Find user with Username and Password from Request body
			User user = userService.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
			if (null != user) {
				UserDto sessionLogin = new UserDto();
				sessionLogin.setUserId(user.getUserId());
				sessionLogin.setName(user.getName());
				sessionLogin.setUsername(user.getUsername());
				sessionLogin.setDateOfbirthday(user.getBirthday());
				
				// Session store "user" and Return HTTP Status 200 success
				httpSession.setAttribute(ATTRIBUTE_USER_KEY, sessionLogin);
				status = HttpStatus.OK;
			}else {
				// Return HTTP Status 404 Not found
				status = HttpStatus.NOT_FOUND;
			}
		}else {
			// Return HTTP Status 400
			status = HttpStatus.BAD_REQUEST;
		}
		logger.debug("end login service");
		return new ResponseEntity<>(status);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	public ResponseEntity deleteUserSession(HttpSession httpSession) {
		// Check Session login 
		UserDto sessionLogin = getUserLogin(httpSession);
		if (null != sessionLogin) {
			httpSession.invalidate();
			
			// Find all Order by Session User Id
			List<Order> orders = orderService.findByUserId(sessionLogin.getUserId());
			if (null != orders) {
				// Iterator order list
				for (Order order : orders) {
					orderService.deleteOrderAndOrderDetail(order.getOrderId());
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
