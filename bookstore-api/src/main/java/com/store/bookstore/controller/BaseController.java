package com.store.bookstore.controller;

import javax.servlet.http.HttpSession;

import com.store.bookstore.dto.UserDto;

public class BaseController {
	
	protected static final String ATTRIBUTE_USER_KEY = "user";

	public UserDto getUserLogin(HttpSession session) {
        return (UserDto) session.getAttribute(ATTRIBUTE_USER_KEY);
    }
	
}
