package com.store.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.bookstore.dao.IUserDao;
import com.store.bookstore.entity.User;

@Service
public class UserService implements IUserService{
	
	@Autowired
	public IUserDao userDao;

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public boolean saveUser(User user) {
		return null != userDao.save(user);
	}
	
	@Override
	public User findByUserId(Integer userId) {
		return userDao.findByUserId(userId);
	}

}
