package com.store.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.bookstore.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
	
	public User findByUsernameAndPassword(String username, String password);
	public User findByUserId(Integer userId);

}
