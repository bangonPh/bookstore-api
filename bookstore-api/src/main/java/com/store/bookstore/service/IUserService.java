package com.store.bookstore.service;

import com.store.bookstore.entity.User;

public interface IUserService {
	
	public User findByUsernameAndPassword(String username, String password);
	public boolean saveUser(User user);
	public User findByUserId(Integer userId);

}
